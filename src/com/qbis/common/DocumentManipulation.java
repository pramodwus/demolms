package com.qbis.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.qbis.services.AwsS3Service;

@Component
public class DocumentManipulation {

	/**
	 * Instance of {@link AwsS3Service}
	 */
	@Autowired
	private AwsS3Service awsS3Service;

	/**
	 * =================not in use ===========================
	 * 
	 * Manipulates a PDF file src with the file dest as result
	 * 
	 * @param src
	 *            the original PDF
	 * @param dest
	 *            the resulting PDF
	 * @throws IOException
	 * @throws DocumentException
	 * @return n for numbers of pdf pages.
	 */
	public int manipulatePdf(String src, String dest) throws IOException,
			DocumentException {
		int n = 0;
		Document document = null;
		PdfCopy writer = null;
		try {
			String inFile = src;
			PdfReader reader = new PdfReader(inFile);
			n = reader.getNumberOfPages();
			int i = 0;
			while (i < n) {
				String outFile = dest + "/page_" + (i + 1) + ".pdf";
				document = new Document(reader.getPageSizeWithRotation(1));
				writer = new PdfCopy(document, new FileOutputStream(outFile));
				document.open();
				PdfImportedPage page = writer.getImportedPage(reader, ++i);
				writer.addPage(page);
				document.close();
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null)
				document.close();
			if (writer != null)
				writer.close();
		}
		return n;
	}

	/**
	 * Manipulates a PDF file src with the file dest as result
	 * 
	 * @param pdfDirName
	 * @param src
	 * @param dest
	 * @param orgName
	 * @return noOfPages
	 * @throws IOException
	 * @throws DocumentException
	 */
	public int manipulatePdfAndUploadInS3Bucket(final String pdfDirName,
			String src, String dest, final String orgName) throws IOException,
			DocumentException {
		int n = 0;
		Document document = null;
		PdfCopy writer = null;
		try {
			String inFile = src;
			PdfReader reader = new PdfReader(inFile);
			n = reader.getNumberOfPages();
			int i = 0;
			while (i < n) {
				String outFile = dest + "/page_" + (i + 1) + ".pdf";
				document = new Document(reader.getPageSizeWithRotation(1));
				writer = new PdfCopy(document, new FileOutputStream(outFile));
				document.open();
				PdfImportedPage page = writer.getImportedPage(reader, ++i);
				writer.addPage(page);
				final String fileNameBasedOnPageNum = "page_" + i + ".pdf";
				final File pdfFileForS3 = new File(dest + "/"
						+ fileNameBasedOnPageNum);
				final File pdfServerDirName = new File(dest);
				(new Thread() {
					@Override
					public void run() {
						Boolean idPdfPageUploaded = awsS3Service
								.uploadContentFromServer(pdfFileForS3,
										pdfDirName + "/"
												+ fileNameBasedOnPageNum,
										orgName);
						if (idPdfPageUploaded) {
							pdfFileForS3.delete();
							File filelist[] = pdfServerDirName.listFiles();
							if (filelist.length == 0) {
								try {
									FileUtils.deleteDirectory(pdfServerDirName);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}).start();
				document.close();
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null)
				document.close();
			if (writer != null)
				writer.close();
		}
		return n;
	}

	/**
	 * Convert a PDF from Office document file src with the file dest as result
	 * 
	 * @param src
	 *            the original Office Document (ppt, pptx, doc, docx etc.) file
	 * @param dest
	 *            the destination folder path
	 * @return true or false
	 */
	public Boolean convertPDFFromOfficeDocument(String src, String dest) {
		OfficeManager officeManager = null;
		Boolean flag = false;
		try {
			officeManager = new DefaultOfficeManagerConfiguration()
					.setOfficeHome(new File(ConstantUtil.OPEN_OFFICE_PATH))
					.buildOfficeManager();
			officeManager.start();
			OfficeDocumentConverter converter = new OfficeDocumentConverter(
					officeManager);
			converter.convert(new File(src), new File(dest));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (officeManager != null) {
				officeManager.stop();
			}
		}
		return flag;
	}

	/**
	 * =================not in use ===========================
	 * 
	 * Convert a PDF to image
	 * 
	 * @param src
	 *            the store pdf file path
	 * @param dest
	 *            the destination folder path
	 * @param imageType
	 * @return true or false
	 */
	@SuppressWarnings("unused")
	public int convertPDFtoImage(String src, String dest, String imageType) {
		int pageCounter = 0;
		try {
			File sourceFile = new File(src);
			PDDocument document = PDDocument.load(sourceFile);
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (PDPage page : document.getPages()) {
				BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter,
						100, ImageType.RGB);
				ImageIOUtil.writeImage(bim, dest + "/page_" + (++pageCounter)
						+ "." + imageType, 100);
			}
			document.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageCounter;
	}

	/**
	 * Convert a PDF to image
	 * 
	 * @param pptDirName
	 * @param src
	 *            the store pdf file path
	 * @param dest
	 *            the destination folder path
	 * @param imageType
	 * @param orgName
	 * @return true or false
	 */
	@SuppressWarnings("unused")
	public int convertPDFtoImageAndUploadInS3Bucket(final String pptDirName,
			String src, String dest, String imageType, final String orgName) {
		int pageCounter = 0;
		try {
			File sourceFile = new File(src);
			PDDocument document = PDDocument.load(sourceFile);
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (PDPage page : document.getPages()) {
				BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter,
						100, ImageType.RGB);
				Boolean isConverted = ImageIOUtil.writeImage(bim, dest
						+ "/page_" + (++pageCounter) + "." + imageType, 100);
				if (isConverted) {
					final String fileNameBasedOnPageNum = "page_" + pageCounter
							+ ".png";
					final File pptSlideForS3 = new File(dest + "/"
							+ fileNameBasedOnPageNum);
					final File pptServerDirName = new File(dest);
					(new Thread() {
						@Override
						public void run() {
							Boolean idPdfPageUploaded = awsS3Service
									.uploadContentFromServer(pptSlideForS3,
											pptDirName + "/"
													+ fileNameBasedOnPageNum,
											orgName);
							if (idPdfPageUploaded) {
								pptSlideForS3.delete();
								File filelist[] = pptServerDirName.listFiles();
								if (filelist.length == 0) {
									try {
										FileUtils
												.deleteDirectory(pptServerDirName);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}).start();
				}
			}
			document.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageCounter;
	}
}
