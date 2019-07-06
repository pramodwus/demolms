package com.qbis.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.text.pdf.PdfReader;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.DocumentManipulation;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.DropboxFile;
import com.qbis.model.FileData;
import com.qbis.model.GoogleDriveFile;
import com.qbis.model.SectionContent;

/**
 * This class is used for performing operation on google drive or dropbox file.
 * 
 * @author ankur
 * 
 */
@Component
public class GoogleDriveDropboxFileService {

	@Autowired
	private DocumentManipulation documentManipulation;
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(GoogleDriveDropboxFileService.class);

	/**
	 * Instance of {@link AwsS3Service}
	 */
	@Autowired
	private AwsS3Service awsS3Service;

	/**
	 * Instance of {@link CourseService}
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * =============not in use ====================== This is used for
	 * downloading the google drive file using file id and token.
	 * 
	 * @param auth
	 * @param googleFileData
	 * @param userId
	 * @return contentId
	 */
	public Integer downloadFileFromGoogleDrive(String auth,
			GoogleDriveFile googleDriveFile, Integer userId) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService downloadFileFromGoogleDrive method:::::");
		Integer contentId = 0;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
					new ByteArrayHttpMessageConverter());

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + auth);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<byte[]> response = restTemplate.exchange(
					"https://www.googleapis.com/drive/v3/files/"
							+ googleDriveFile.getId() + "?alt=media",
					HttpMethod.GET, entity, byte[].class, "1");

			if (response.getStatusCode() == HttpStatus.OK) {
				String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
						+ ConstantUtil.COURSE_FILE_DIRECTORY;
				String uuid = UUID.randomUUID().toString();
				String fileName = uuid
						+ googleDriveFile.getName().substring(
								googleDriveFile.getName().lastIndexOf("."));
				googleDriveFile.setTitle(googleDriveFile.getName().substring(0,
						googleDriveFile.getName().lastIndexOf(".")));
				Path path = Files.write(Paths.get(uploadPath + fileName),
						response.getBody());
				if (path.isAbsolute()) {
					FileData fileData = new FileData();
					fileData.setName(fileName);
					fileData.setSizeBytes(googleDriveFile.getSizeBytes());
					fileData.setTitle(googleDriveFile.getTitle());
					contentId = saveMultipleContents(fileData, userId);
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService downloadFileFromGoogleDrive method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * This is used for uploading the google drive file using file id and token
	 * in S3Bucket or server.
	 * 
	 * @param auth
	 * @param googleFileData
	 * @param userId
	 * @return contentId
	 */
	public Integer uploadFileInS3BucketFromGoogleDrive(String auth,
			GoogleDriveFile googleDriveFile, Integer userId, String orgName) {
		logger.log(
				Level.DEBUG,
				"Inside GoogleDriveDropboxFileService uploadFileInS3BucketFromGoogleDrive method:::::");
		Integer contentId = 0;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(
					new ByteArrayHttpMessageConverter());

			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + auth);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<byte[]> response = restTemplate.exchange(
					"https://www.googleapis.com/drive/v3/files/"
							+ googleDriveFile.getId() + "?alt=media",
					HttpMethod.GET, entity, byte[].class, "1");

			if (response.getStatusCode() == HttpStatus.OK) {
				String uuid = UUID.randomUUID().toString();
				String fileName = uuid
						+ googleDriveFile.getName().substring(
								googleDriveFile.getName().lastIndexOf("."));
				googleDriveFile.setTitle(googleDriveFile.getName().substring(0,
						googleDriveFile.getName().lastIndexOf(".")));
				FileData fileData = new FileData();
				fileData.setName(fileName);
				fileData.setSizeBytes(googleDriveFile.getSizeBytes());
				fileData.setTitle(googleDriveFile.getTitle());
				fileData.setType(googleDriveFile.getMimeType());
				/**
				 * call method for processing the content for uploading either
				 * on server or S3 Bucket.
				 */
				contentId = uploadDropboxOrDriveContentInS3Bucket(
						response.getBody(), fileData, userId, orgName);
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService uploadFileInS3BucketFromGoogleDrive method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * ========================================================================
	 * ======================== Start of code message :
	 * 
	 * This is code for uploading the new content in s3 bucket because this
	 * segregates the content and uploaded it based on content type id either on
	 * S3 bucket or on server.
	 * 
	 * ========================================================================
	 * ========================
	 */

	/**
	 * This is used for uploading either on server or S3 Bucket.
	 * 
	 * @param fileContent
	 * @param fileData
	 * @param userId
	 * @param orgName
	 * @return contentId
	 */
	public Integer uploadDropboxOrDriveContentInS3Bucket(byte[] fileContent,
			FileData fileData, int userId, String orgName) {
		logger.log(
				Level.DEBUG,
				"Inside GoogleDriveDropboxFileService uploadDropboxOrDriveContentInS3Bucket method:::::::::::");
		Integer contentId = 0;
		try {

			SectionContent content = new SectionContent();
			content.setContent(fileData.getName());
			/**
			 * getting the content info(like content type id) based on content
			 * extension.
			 */
			String temp = courseService.checkFileTypeValid(fileData.getName()
					.substring(fileData.getName().lastIndexOf(".") + 1));
			String contentdata[] = temp.split("####");
			content.setContentTypeId(Integer.parseInt(contentdata[0]));
			content.setContentName(fileData.getTitle());

			switch (content.getContentTypeId()) {
			case 1: {
				contentId = uploadPDFFile(fileContent, fileData, userId,
						content, orgName);
				break;
			}
			case 13:
			case 14: {
				contentId = uploadPowerpointFile(fileContent, fileData, userId,
						content, orgName);
				break;
			}
			default: {
				contentId = uploadOtherFilesInS3Bucket(fileContent, fileData,
						userId, content, orgName);
				break;
			}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService uploadDropboxOrDriveContentInS3Bucket method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * This is used for uploading the pdf type content on server.
	 * 
	 * @param fileContent
	 * @param fileData
	 * @param userId
	 * @param content
	 * @return contentId
	 */
	public Integer uploadPDFFile(byte[] fileContent, FileData fileData,
			Integer userId, SectionContent content, String orgName) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService uploadPDFFile method:::::::::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
					+ ConstantUtil.COURSE_FILE_DIRECTORY;
			Path path = Files.write(Paths.get(uploadPath + fileData.getName()),
					fileContent);
			if (path.isAbsolute()) {
				/**
				 * saved content info in content list table.
				 */
				Integer id = QueryManager.execuateUpdate(
						QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS, new Object[] {
								fileData.getTitle(), fileData.getName(),
								content.getContentTypeId(), 0, userId, 0, null,
								fileData.getSizeBytes() });
				if (id > 0) {
					int pageNum = 0;
					contentId = id;
					String name = (id + "_pdf");
					File dir1 = new File(uploadPath + name + File.separator);
					if (!dir1.exists())
						dir1.mkdirs();
					pageNum = documentManipulation
							.manipulatePdfAndUploadInS3Bucket(name, uploadPath
									+ fileData.getName(), uploadPath + name,
									orgName);
					File dest = new File(uploadPath + fileData.getName());
					dest.delete();
					name = orgName + "/" + name;
					QueryManager.execuateUpdate(
							QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { name, pageNum, id });
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService uploadPDFFile method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * This is used for uploading the content in S3 bucket if content is not pdf
	 * or powerpoint document types.
	 * 
	 * @param fileContent
	 * @param fileData
	 * @param userId
	 * @param content
	 * @param orgName
	 * @return contentId
	 */
	public Integer uploadOtherFilesInS3Bucket(byte[] fileContent,
			FileData fileData, Integer userId, SectionContent content,
			String orgName) {
		logger.log(
				Level.DEBUG,
				"Inside GoogleDriveDropboxFileService uploadOtherFilesInS3Bucket method:::::::::::");
		Integer contentId = 0;
		try {

			/**
			 * This is used for uploading the content in S3 Bucket from server.
			 */
			Boolean isUploaded = awsS3Service
					.uploadGoogleDriveAndDropboxContent(fileContent, fileData,
							orgName);

			if (isUploaded) {
				Integer id = QueryManager.execuateUpdate(
						QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS, new Object[] {
								fileData.getTitle(), fileData.getName(),
								content.getContentTypeId(), 0, userId, 0, null,
								fileData.getSizeBytes() });
				if (id > 0) {
					contentId = id;
					int pageNum = 0;
					String name = new StringBuilder(fileData.getName()).insert(
							fileData.getName().lastIndexOf("."), ("_" + id))
							.toString();
					awsS3Service.renameContent(
							orgName + "/" + fileData.getName(), orgName + "/"
									+ name);
					name = orgName + "/" + name;
					/**
					 * This is used for updating the content name and total
					 * pages of content as content information based on content
					 * id.
					 */
					QueryManager.execuateUpdate(
							QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { name, pageNum, id });
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService uploadOtherFilesInS3Bucket method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * This is used for uploading the powerpoint document type content.
	 * 
	 * @param fileContent
	 * @param fileData
	 * @param userId
	 * @param content
	 * @return contentId
	 */
	public Integer uploadPowerpointFile(byte[] fileContent, FileData fileData,
			Integer userId, SectionContent content, final String orgName) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService uploadPowerpointFile method:::::::::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
					+ ConstantUtil.COURSE_FILE_DIRECTORY;
			Path path = Files.write(Paths.get(uploadPath + fileData.getName()),
					fileContent);
			if (path.isAbsolute()) {
				Integer id = QueryManager.execuateUpdate(
						QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS, new Object[] {
								fileData.getTitle(), fileData.getName(),
								content.getContentTypeId(), 0, userId, 0, null,
								fileData.getSizeBytes() });
				if (id > 0) {
					contentId = id;
					String name = (id + "_ppt");
					int pageNum = 0;
					File dir1 = new File(uploadPath + name + File.separator);
					if (!dir1.exists())
						dir1.mkdirs();
					/**
					 * This is used for converting the power point document to
					 * pdf document.
					 */
					Boolean isConvertPDF = documentManipulation
							.convertPDFFromOfficeDocument(
									uploadPath + fileData.getName(), uploadPath
											+ name + "/" + id + ".pdf");
					/**
					 * When power point document has been converted successfully
					 * into pdf.
					 */
					if (isConvertPDF) {
						File dest = new File(uploadPath + fileData.getName());
						dest.delete();
						pageNum = new PdfReader(uploadPath + name + "/" + id
								+ ".pdf").getNumberOfPages();
						final int id1 = id;
						final String uploadPathName = uploadPath + name;
						final String pptDirName = name;
						/**
						 * run the thread for making the png images for all
						 * pages of converted document.
						 */
						(new Thread() {
							@Override
							public void run() {
								/**
								 * this is used for convert pdf into png file.
								 */
								documentManipulation
										.convertPDFtoImageAndUploadInS3Bucket(
												pptDirName, uploadPathName
														+ "/" + id1 + ".pdf",
												uploadPathName, "png", orgName);
								File destpdf = new File(uploadPathName + "/"
										+ id1 + ".pdf");
								/**
								 * after successfully conversion of a pdf into
								 * png , delete the pdf file.
								 */
								destpdf.delete();
							}
						}).start();
						Thread.sleep(5000);
					}
					name = orgName + "/" + name;
					QueryManager.execuateUpdate(
							QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { name, pageNum, id });
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception GoogleDriveDropboxFileService Service uploadPowerpointFile method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * ========================================================================
	 * ====================== End of code Message :
	 * 
	 * End code for uploading the new content in s3 bucket because this
	 * segregates the content and uploaded it based on content type id either on
	 * S3 bucket or on server.
	 * 
	 * ========================================================================
	 * =======================
	 */

	/**
	 * ==================not in use for google drive ============= This is used
	 * for manipulate the operation on download file from google drive.
	 * 
	 * @param googleFileData
	 * @param userId
	 * @return contentId
	 */
	public Integer saveMultipleContents(FileData fileData, int userId) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService saveMultipleContents method:::::");
		Integer contentId = 0;
		String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
				+ ConstantUtil.COURSE_FILE_DIRECTORY;
		try {
			SectionContent content = new SectionContent();
			File dir = new File(uploadPath + File.separator);
			File serverFile = new File(dir.getAbsolutePath() + File.separator
					+ fileData.getName());
			content.setContent(fileData.getName());
			/**
			 * getting the content info(like content type id) based on content
			 * extension.
			 */
			String temp = courseService.checkFileTypeValid(fileData.getName()
					.substring(fileData.getName().lastIndexOf(".") + 1));
			String contentdata[] = temp.split("####");
			content.setContentTypeId(Integer.parseInt(contentdata[0]));
			content.setContentName(fileData.getTitle());

			/**
			 * if file is exist in directory for manipulating the operation.
			 */
			if (serverFile.exists()) {

				/**
				 * saved content info in content list table.
				 */
				Integer id = QueryManager.execuateUpdate(
						QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS, new Object[] {
								fileData.getTitle(), fileData.getName(),
								content.getContentTypeId(), 0, userId, 0, null,
								fileData.getSizeBytes() });
				/**
				 * if content has been uploaded successfully and mapped with
				 * content list of this user..
				 */
				if (id > 0) {
					String name = "";
					int pageNum = 0;

					/**
					 * If content is pdf type.
					 */
					if (content.getContentTypeId() == 1) {
						name = (id + "_pdf");
						File dir1 = new File(uploadPath + name + File.separator);
						if (!dir1.exists())
							dir1.mkdirs();
						pageNum = documentManipulation.manipulatePdf(uploadPath
								+ fileData.getName(), uploadPath + name);
						File dest = new File(uploadPath + fileData.getName());
						dest.delete();
					}
					/**
					 * if content is ppt or pptx type.
					 */
					else if (content.getContentTypeId() == 13
							|| content.getContentTypeId() == 14) {
						name = (id + "_ppt");
						File dir1 = new File(uploadPath + name + File.separator);
						if (!dir1.exists())
							dir1.mkdirs();
						/**
						 * This is used for converting the power point document
						 * to pdf document.
						 */
						Boolean isConvertPDF = documentManipulation
								.convertPDFFromOfficeDocument(uploadPath
										+ fileData.getName(), uploadPath + name
										+ "/" + id + ".pdf");
						/**
						 * When power point document has been converted
						 * successfully into pdf.
						 */
						if (isConvertPDF) {
							File dest = new File(uploadPath
									+ fileData.getName());
							dest.delete();
							pageNum = new PdfReader(uploadPath + name + "/"
									+ id + ".pdf").getNumberOfPages();
							final int id1 = id;
							final String uploadPathName = uploadPath + name;
							/**
							 * run the thread for making the png images for all
							 * pages of converted document.
							 */
							(new Thread() {
								@Override
								public void run() {
									/**
									 * this is used for convert pdf into png
									 * file.
									 */
									documentManipulation
											.convertPDFtoImage(uploadPathName
													+ "/" + id1 + ".pdf",
													uploadPathName, "png");
									File destpdf = new File(uploadPathName
											+ "/" + id1 + ".pdf");
									/**
									 * after successfully conversion of a pdf
									 * into png , delete the pdf file.
									 */
									destpdf.delete();
								}
							}).start();
							Thread.sleep(5000);
						}
					}
					/**
					 * If document is not ppt,ppts,pdf type then this is used
					 * for manipulation.
					 */
					else {
						name = new StringBuilder(fileData.getName())
								.insert(fileData.getName().lastIndexOf("."),
										("_" + id)).toString();
						File dest = new File(uploadPath + fileData.getName());
						dest.renameTo(new File(uploadPath + name));
					}

					/**
					 * This is used for updating the content name and total
					 * pages of content as content information based on content
					 * id.
					 */
					QueryManager.execuateUpdate(
							QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { name, pageNum, id });
				}
				contentId = id;
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService saveMultipleContents method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * This is used for downloading the google drive file using file id and
	 * token.
	 * 
	 * @param auth
	 * @param googleFileData
	 * @param userId
	 * @return contentId
	 */
	public Integer downloadFileFromDropbox(DropboxFile dropboxFile,
			Integer userId) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService downloadFileFromDropbox method:::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH
					+ ConstantUtil.COURSE_FILE_DIRECTORY;
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid
					+ dropboxFile.getName().substring(
							dropboxFile.getName().lastIndexOf("."));
			File file = new File(uploadPath + fileName);
			URL url = new URL(dropboxFile.getLink());
			FileUtils.copyURLToFile(url, file);
			if (file.exists()) {
				FileData fileData = new FileData();
				fileData.setName(fileName);
				fileData.setSizeBytes(dropboxFile.getBytes());
				fileData.setTitle(dropboxFile.getName().substring(0,
						dropboxFile.getName().lastIndexOf(".")));
				contentId = saveMultipleContents(fileData, userId);
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService downloadFileFromDropbox method:::::",
					e);
		}
		return contentId;
	}

	/**
	 * This is used for downloading the google drive file using file id and
	 * token.
	 * 
	 * @param auth
	 * @param googleFileData
	 * @param userId
	 * @return contentId
	 */
	public Integer uploadFileInS3BucketFromDropbox(DropboxFile dropboxFile,
			Integer userId, String orgName) {
		logger.log(Level.DEBUG,
				"Inside GoogleDriveDropboxFileService downloadFileFromDropbox method:::::");
		Integer contentId = 0;
		try {
			URL url = new URL(dropboxFile.getLink());
			InputStream is = null;
			try {
				is = url.openStream();
				byte[] bytes = IOUtils.toByteArray(is);
				if (bytes.length > 0) {
					String uuid = UUID.randomUUID().toString();
					String fileName = uuid
							+ dropboxFile.getName().substring(
									dropboxFile.getName().lastIndexOf("."));
					FileData fileData = new FileData();
					fileData.setName(fileName);
					fileData.setSizeBytes(dropboxFile.getBytes());
					fileData.setTitle(dropboxFile.getName().substring(0,
							dropboxFile.getName().lastIndexOf(".")));
					fileData.setType(CommonUtil.getMimeType(dropboxFile
							.getLink()));
					/**
					 * call method for processing the content for uploading
					 * either on server or S3 Bucket.
					 */
					contentId = uploadDropboxOrDriveContentInS3Bucket(bytes,
							fileData, userId, orgName);
				}
			} catch (IOException e) {
				System.err.printf("Failed while reading bytes from %s: %s",
						url.toExternalForm(), e.getMessage());
				e.printStackTrace();
				// Perform any other exception handling that's appropriate.
			} finally {
				if (is != null) {
					is.close();
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside GoogleDriveDropboxFileService downloadFileFromDropbox method:::::",
					e);
		}
		return contentId;
	}

}
