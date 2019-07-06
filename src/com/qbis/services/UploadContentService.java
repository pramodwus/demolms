

package com.qbis.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.PdfReader;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.ConstantUtil;
import com.qbis.common.DocumentManipulation;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.ContentProcessingResponse;
import com.qbis.model.Course;
import com.qbis.model.Datatable;
import com.qbis.model.HlsurlRequest;
import com.qbis.model.Question;
import com.qbis.model.SectionContent;
import com.qbis.model.ServiceResult.ContentStatus;

@Component
public class UploadContentService {

	/**
	 * Instance of {@link CourseService}
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * Instance of {@link DocumentManipulation}
	 */
	@Autowired
	private DocumentManipulation documentManipulation;

	/**
	 * Instance of {@link QuestionService}
	 */
	@Autowired
	private QuestionService questionService;

	/**
	 * Instance of {@link AwsS3Service}
	 */
	@Autowired
	private AwsS3Service awsS3Service;
	
	@Autowired
	private AuthenticationService authenticationService;
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(UploadContentService.class);
	private static final String PDF_DIRECTORY_CONTAINS_S3_BUCKET = "_pdf";
	private static final String PPT_DIRECTORY_CONTAINS_S3_BUCKET = "_ppt";

	/**
	 * This is used to getting all question list based on user id.
	 * 
	 * @param userId
	 * @return List
	 */
	public Map<String, Object> getContentListData(Integer userId, Datatable req, String type, Integer visiblity) {
		logger.log(Level.DEBUG, "Inside Upload Content Service getContentListData method:::::::::::");
		Map<String, Object> result = new HashMap<String, Object>();
		String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
				+ ConstantUtil.AWS_S3_BUCKET_NAME;
		SectionContent content;
		try {
			List<Object> contentList = new ArrayList<Object>();
			result.put("draw", req.getDraw());
			String searchValue = req.getSearch().get(Datatable.SearchCriterias.value);
			Integer totalRecord = 0;
			String query = QueryManager.QueryStrings.GET_UPLOADED_CONTENT_LIST.getQuery();
			String groupquery = " GROUP BY course_content_manager.content_id ORDER BY course_content_manager.uploaded_datetime DESC";
			String queryData = query + groupquery;
			QueryData totalRecorddata = QueryManager.execuateQuery(queryData, new Object[] { userId });
			totalRecord = totalRecorddata.getRows().size();
			if (type != null && !type.equals("-1")) {
				query = query + " AND content_type.type_name= '" + type + "'";
			}

			if (visiblity != null && visiblity != -1) {
				query = query + " AND course_content_manager.visibility=" + visiblity;
			}
			if (searchValue != null && searchValue.length() > 0) {
				query = query + " AND (course_content_manager.content_name like ?"
						+ " OR course_content_manager.content like ? " + " OR content_type.type_name like ? "
						+ " OR course_content_manager.visibility like ?"
						+ " OR DATE_FORMAT(course_content_manager.uploaded_datetime,'%d-%m-%Y') like ?"
						+ " OR course_manager.title like ?" + ")";
			}
			query = query + " GROUP BY course_content_manager.content_id";
			if (req.getOrder() != null) {
				Integer columnIndex = Integer.parseInt(req.getOrder().get(0).get(Datatable.OrderCriterias.column));
				String orderType = req.getOrder().get(0).get(Datatable.OrderCriterias.dir);
				switch (columnIndex) {
				case 0:
					query = query + " ORDER BY course_content_manager.content_name " + orderType;
					break;
				case 1:
					query = query + " ORDER BY content_type.type_name " + orderType;
					break;
				case 3:
					query = query + " ORDER BY course_content_manager.visibility " + orderType;
					break;
				case 4:
					query = query + " ORDER BY course_manager.title " + orderType;
					break;
				case 5:
					query = query + " ORDER BY course_content_manager.uploaded_datetime " + orderType;
					break;
				default:
					query = query + " ORDER BY course_content_manager.uploaded_datetime DESC";
				}
			} else {
				query = query + " ORDER BY course_content_manager.uploaded_datetime DESC";
			}
			QueryData data;
			String publicMediaType = "public";
			String privateMediaType = "private";
			if (searchValue != null && searchValue.length() > 0) {
				data = QueryManager.execuateQuery(query,
						new Object[] { userId, "%" + searchValue.trim() + "%", "%" + searchValue.trim() + "%",
								"%" + searchValue.trim() + "%",
								"%" + (publicMediaType.contains(searchValue.trim().toLowerCase()) ? 1
										: (privateMediaType.contains(searchValue.trim().toLowerCase()) ? 0
												: searchValue.trim()))
										+ "%",
								"%" + searchValue.trim() + "%", "%" + searchValue.trim() + "%" });
			} else {
				data = QueryManager.execuateQuery(query, new Object[] { userId });
			}
			result.put("recordsTotal", totalRecord);
			result.put("recordsFiltered", data.getRows().size());

			int i = 0;

			for (QueryData.Row row : data.getRows()) {
				if (i < (req.getStart() + req.getLength()) && i >= req.getStart()) {
					content = new SectionContent();
					content.setContentId(Integer.parseInt(row.getRowItem(0)));
					content.setContentName(row.getRowItem(1));
					content.setContent(row.getRowItem(2));
					content.setNumPages(row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0);
					content.setUserId((row.getRowItem(7) != null ? Integer.parseInt(row.getRowItem(7)) : null));
					content.setUploadedBy(row.getRowItem(8) != null ? row.getRowItem(8) : null);
					content.setVisiblity(Integer.parseInt(row.getRowItem(9)));
					content.setDescription(row.getRowItem(10));
					content.setCreatedDate(row.getRowItem(12));
					content.setCourseMapped(row.getRowItem(13));
					content.setIsExternalURL(Integer.parseInt(row.getRowItem(14)));
					if (content.getIsExternalURL() == 0) {
						content.setContentType(row.getRowItem(11));
						content.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + content.getContent());
					} else {
						content.setContentPath(content.getContent());
						content.setContentType("URL");
					}
					if (row.getRowItem(15) != null) {
						content.setSize(Double.parseDouble(row.getRowItem(15)));
					}
					content.setVideoContentId(Integer.parseInt(row.getRowItem(16)));
					content.setAudioContentId(Integer.parseInt(row.getRowItem(17)));
					contentList.add(content);
				}
				i++;
			}
			result.put("data", contentList);

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service getContentListData method:::::", e);
		}

		return result;
	}

	/**
	 * Method to get list of all contents
	 * 
	 * @return list
	 */
	public List<SectionContent> findAllContent(Integer userId, String type, Integer visiblity) {

		logger.log(Level.DEBUG, "Inside Upload Content Service findAllContent method:::::::::::");
		String query = "GROUP BY course_content_manager.content_id ORDER BY course_content_manager.uploaded_datetime DESC";
		String queryfinal = (QueryStrings.GET_UPLOADED_CONTENT_LIST).getQuery();

		List<SectionContent> list = new ArrayList<SectionContent>();
		SectionContent content;
		String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
				+ ConstantUtil.AWS_S3_BUCKET_NAME;
		try {
			if (type != null && !type.equals("-1")) {
				queryfinal = queryfinal + " AND content_type.type_name= '" + type + "'";
			}

			if (visiblity != null && visiblity != -1) {
				queryfinal = queryfinal + " AND course_content_manager.visibility=" + visiblity;
			}

			queryfinal = (queryfinal + " " + query);
			QueryData data = QueryManager.execuateQuery(queryfinal, new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				content = new SectionContent();
				content.setContentId(Integer.parseInt(row.getRowItem(0)));
				content.setContentName(row.getRowItem(1));
				content.setContent(row.getRowItem(2));
				content.setNumPages(row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0);
				content.setUserId((row.getRowItem(7) != null ? Integer.parseInt(row.getRowItem(7)) : null));
				content.setUploadedBy(row.getRowItem(8) != null ? row.getRowItem(8) : null);
				content.setVisiblity(Integer.parseInt(row.getRowItem(9)));
				content.setDescription(row.getRowItem(10));
				content.setCreatedDate(row.getRowItem(12));
				content.setCourseMapped(row.getRowItem(13));
				content.setIsExternalURL(Integer.parseInt(row.getRowItem(14)));
				if (content.getIsExternalURL() == 0) {
					// content.setContentPath(uploadPath +
					// content.getContent());
					content.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + content.getContent());
					content.setContentType(row.getRowItem(11));
				} else {
					content.setContentPath(content.getContent());
					content.setContentType("URL");
				}
				if (row.getRowItem(15) != null) {
					content.setSize(Double.parseDouble(row.getRowItem(15)));
				}
				content.setVideoContentId(Integer.parseInt(row.getRowItem(16)));
				content.setAudioContentId(Integer.parseInt(row.getRowItem(17)));
				list.add(content);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service findAllContent method:::::", e);
		}
		return list;
	}

	/**
	 * 
	 * ==============not in use =================
	 * 
	 * This is used save new uploaded content.
	 * 
	 * @param content
	 * @param userId
	 * @param file
	 */
	public void saveNewUploadedContent(SectionContent content, Integer userId, MultipartFile file) {
		logger.log(Level.DEBUG, "Inside Upload Content Service saveNewUploadedContent method:::::::::::");
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			boolean flag = false;

			if (!file.isEmpty()) {

				// Creating the directory to store file
				File dir = new File(uploadPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
				flag = true;

			}

			if (flag) {
				String temp = courseService.checkFileTypeValid(
						file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
				String contentdata[] = temp.split("####");
				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS,
						new Object[] { content.getContentName(), file.getOriginalFilename(), contentdata[0], 0, userId,
								content.getVisiblity(), content.getDescription() });
				if (id > 0) {
					String name = new StringBuilder(file.getOriginalFilename())
							.insert(file.getOriginalFilename().lastIndexOf("."), ("_" + id)).toString();
					QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID, new Object[] { name, 0, id });
					File dest = new File(uploadPath + file.getOriginalFilename());
					dest.renameTo(new File(uploadPath + name));
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service saveNewUploadedContent method:::::", e);
		}
	}

	/**
	 * Method to get details details of uploaded content by contentId
	 * 
	 * @param contentId
	 */
	public SectionContent findOneContent(int contentId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service findOneContent method:::::::::::");
		SectionContent content = null;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_ONE_UPLOAD_CONTENT,
					new Object[] { contentId });
			for (QueryData.Row row : data.getRows()) {
				content = new SectionContent();
				content.setContentId(Integer.parseInt(row.getRowItem(0)));
				content.setContentName(row.getRowItem(1));
				content.setContent(row.getRowItem(2));
				content.setContentTypeId(row.getRowItem(4) != null ? Integer.parseInt(row.getRowItem(4)) : 0);
				content.setNumPages(row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0);
				content.setUserId((row.getRowItem(7) != null ? Integer.parseInt(row.getRowItem(7)) : null));
				content.setVisiblity(Integer.parseInt(row.getRowItem(8)));
				content.setDescription(row.getRowItem(9));
				content.setIsExternalURL(Integer.parseInt(row.getRowItem(11)));
				String AWS_S3_BUCKET_CONTENT_ACCESS_PATH = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH
						+ ConstantUtil.AWS_S3_BUCKET_NAME;
				content.setContentType(row.getRowItem(10));
				if (content.getIsExternalURL() == 1) {
					content.setContentPath(content.getContent());
				} else {
					content.setContentPath(AWS_S3_BUCKET_CONTENT_ACCESS_PATH + "/" + content.getContent());

				}
				if (row.getRowItem(12) != null) {
					content.setSize(Double.parseDouble(row.getRowItem(12)));
				}
				content.setContentIconPath(row.getRowItem(13));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service findOneContent method:::::", e);
		}
		return content;
	}

	/**
	 * 
	 * ======================= not in use ===========================
	 * 
	 * Method to edit details of uploaded content
	 * 
	 * @param object
	 *            of SectionContent
	 * @param userId
	 * @param file
	 *            to be upload
	 */
	public void updateUploadedContent(SectionContent content, Integer userId, MultipartFile file) {
		logger.log(Level.DEBUG, "Inside Upload Content Service updateUploadedContent method:::::::::::");
		String orgName = "orgName";
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			Long fileSize = 0L;
			if (!file.isEmpty()) {
				// Ist remove old file before upload new one
				File dest = new File(uploadPath + content.getContent());
				dest.isDirectory();
				if (dest.isDirectory()) {
					FileUtils.deleteDirectory(dest);
				} else {
					/**
					 * delete object from S3 Content
					 */
					awsS3Service.deleteS3Content(content.getContent());
				}

				File dir = new File(uploadPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
				fileSize = file.getSize();
				content.setContent(file.getOriginalFilename());
				String temp = courseService.checkFileTypeValid(
						file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
				String contentdata[] = temp.split("####");
				content.setContentTypeId(Integer.parseInt(contentdata[0]));
			} else {
				content.setContent(null);
			}

			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_UPLOAD_CONTENT,
					new Object[] { content.getContentName(), content.getContent(), content.getContent(),
							content.getContentTypeId(), content.getContentTypeId(), 0, userId, content.getVisiblity(),
							content.getDescription(), new Date(), fileSize, content.getContentId() });
			if (id > 0 && !file.isEmpty()) {
				Integer contentId = content.getContentId();
				String name = "";
				int pageNum = 0;
				if (content.getContentTypeId() == 1) {
					name = (contentId + "_pdf");
					File dir1 = new File(uploadPath + name + File.separator);
					if (!dir1.exists())
						dir1.mkdirs();
					pageNum = documentManipulation.manipulatePdf(uploadPath + file.getOriginalFilename(),
							uploadPath + name);
					File dest = new File(uploadPath + file.getOriginalFilename());
					dest.delete();
				} else if (content.getContentTypeId() == 13 || content.getContentTypeId() == 14) {
					name = (contentId + "_ppt");
					File dir1 = new File(uploadPath + name + File.separator);
					if (!dir1.exists())
						dir1.mkdirs();
					Boolean isConvertPDF = documentManipulation.convertPDFFromOfficeDocument(
							uploadPath + file.getOriginalFilename(), uploadPath + name + "/" + contentId + ".pdf");
					if (isConvertPDF) {
						File dest = new File(uploadPath + file.getOriginalFilename());
						dest.delete();
						pageNum = new PdfReader(uploadPath + name + "/" + contentId + ".pdf").getNumberOfPages();
						final int id1 = contentId;
						final String uploadPathName = uploadPath + name;
						(new Thread() {
							@Override
							public void run() {
								documentManipulation.convertPDFtoImage(uploadPathName + "/" + id1 + ".pdf",
										uploadPathName, "png");
								File destpdf = new File(uploadPathName + "/" + id1 + ".pdf");
								destpdf.delete();
							}
						}).start();
						Thread.sleep(5000);
					}
				}

				else {
					name = new StringBuilder(file.getOriginalFilename())
							.insert(file.getOriginalFilename().lastIndexOf("."), ("_" + content.getContentId()))
							.toString();
					File dest = new File(uploadPath + file.getOriginalFilename());
					/**
					 * This is used for uploading the content in S3 Bucket from
					 * server.
					 */
					Boolean isUploaded = awsS3Service.uploadContentFromServer(dest, name, orgName);
					/**
					 * When Content is uploaded successfully in s3 bucket, then
					 * delete the content from server.
					 */
					if (isUploaded) {
						//name = orgName + "/" + name;
						dest.delete();
					}
				}
				QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
						new Object[] { name, pageNum, content.getContentId() });

			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service updateUploadedContent method:::::", e);
		}
	}

	/**
	 * 
	 * ========================================================================
	 * ========= start of code for update the content ============ ==== = ====
	 * ==== ==== ==== This code is used for replacing the old content data with
	 * new uploaded content's data. ====
	 * ========================================
	 * ========================================================
	 * 
	 */

	/**
	 * This is used for processing operation for updating the content.
	 * 
	 * @param object
	 *            of SectionContent
	 * @param userId
	 * @param file
	 *            to be upload
	 */
	public void updateUploadedContentInS3Bucket(SectionContent content, Integer userId, MultipartFile file,
			String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service updateUploadedContentInS3Bucket method:::::::::::");
		try {
			/**
			 * if there is no file for replacing the old file.
			 */
			if (!file.isEmpty()) {

				/**
				 * This is used for checking that object exists or not in S3
				 * bucket if it does not exist than this content may be a
				 * directory.
				 */
				Boolean isObject = awsS3Service.isObjectExist(content.getContent());

				/**
				 * If exist then delete the object.
				 */
				if (isObject) {
					/**
					 * delete object from S3 Content
					 */
					awsS3Service.deleteS3Content(content.getContent());
				} else if (content.getContent().contains(PDF_DIRECTORY_CONTAINS_S3_BUCKET)
						|| content.getContent().contains(PPT_DIRECTORY_CONTAINS_S3_BUCKET)) {
					/**
					 * delete directory with its contents from S3 Content
					 */
					awsS3Service.deleteS3DirWithitsContent(content.getContent());
				}
				content.setContent(file.getOriginalFilename());
				/**
				 * This is used getting information about content using
				 * content's extension.
				 */
				String temp = courseService.checkFileTypeValid(
						file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
				String contentdata[] = temp.split("####");
				content.setContentTypeId(Integer.parseInt(contentdata[0]));
				switch (content.getContentTypeId()) {
				case 1: {
					updatePDFContent(file, content, userId, orgName);
					break;
				}
				case 13:
				case 14: {
					updatePPTContent(file, content, userId, orgName);
					break;
				}
				default: {
					updateOtherS3Content(file, content, userId, orgName);
					break;
				}
				}
			} else {
				content.setContent(null);
				long fileSize = 0L;
				QueryManager.execuateUpdate(QueryStrings.UPDATE_UPLOAD_CONTENT,
						new Object[] { content.getContentName(), content.getContent(), content.getContent(),
								content.getContentTypeId(), content.getContentTypeId(), 0, userId,
								content.getVisiblity(), content.getDescription(), new Date(), fileSize,
								content.getContentId() });
			}

		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Upload Content Service updateUploadedContentInS3Bucket method:::::", e);
		}
	}

	/**
	 * This is used for updating the old content with new pdf type content.
	 * 
	 * @param file
	 * @param content
	 * @param userId
	 * @return contentId
	 */
	public Integer updatePDFContent(MultipartFile file, SectionContent content, Integer userId, String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service updatePDFContent method:::::::::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			File dir = new File(uploadPath + File.separator);
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Long fileSize = file.getSize();
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

			/**
			 * upload pdf file on server
			 */
			file.transferTo(serverFile);

			/**
			 * update the content info in content manager.
			 */
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_UPLOAD_CONTENT,
					new Object[] { content.getContentName(), content.getContent(), content.getContent(),
							content.getContentTypeId(), content.getContentTypeId(), 0, userId, content.getVisiblity(),
							content.getDescription(), new Date(), fileSize, content.getContentId() });
			if (id > 0) {
				contentId = content.getContentId();
				String name = contentId + "_pdf";
				int pageNum = 0;
				File dir1 = new File(uploadPath + name + File.separator);
				if (!dir1.exists())
					dir1.mkdirs();
				/**
				 * break the pdf into pages.
				 */
				pageNum = documentManipulation.manipulatePdfAndUploadInS3Bucket(name, uploadPath + fileName,
						uploadPath + name, orgName);
				File dest = new File(uploadPath + fileName);
				dest.delete();

				//name = orgName + "/" + name;
				/**
				 * update the content name and page number information about
				 * content.
				 */
				QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
						new Object[] { name, pageNum, content.getContentId() });
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service updatePDFContent method:::::", e);
		}
		return contentId;
	}

	/**
	 * This is used for updating the old content with new ppt type content.
	 * 
	 * @param file
	 * @param content
	 * @param userId
	 * @return contentId
	 */
	public Integer updatePPTContent(MultipartFile file, SectionContent content, Integer userId, final String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service updatePPTContent method:::::::::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			File dir = new File(uploadPath + File.separator);
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Long fileSize = file.getSize();
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
			/**
			 * upload ppt on server.
			 */
			file.transferTo(serverFile);

			/**
			 * update content information in content manager.
			 */
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_UPLOAD_CONTENT,
					new Object[] { content.getContentName(), content.getContent(), content.getContent(),
							content.getContentTypeId(), content.getContentTypeId(), 0, userId, content.getVisiblity(),
							content.getDescription(), new Date(), fileSize, content.getContentId() });

			if (id > 0) {
				contentId = content.getContentId();
				String name = contentId + "_ppt";
				int pageNum = 0;
				File dir1 = new File(uploadPath + name + File.separator);
				if (!dir1.exists())
					dir1.mkdirs();

				/**
				 * convert ppt into pdf.
				 */
				Boolean isConvertPDF = documentManipulation.convertPDFFromOfficeDocument(uploadPath + fileName,
						uploadPath + name + "/" + contentId + ".pdf");
				/**
				 * if ppt has been successfully converted into pdf.
				 */
				if (isConvertPDF) {
					File dest = new File(uploadPath + fileName);
					dest.delete();
					/**
					 * getting total pages in pdf.
					 */
					pageNum = new PdfReader(uploadPath + name + "/" + contentId + ".pdf").getNumberOfPages();
					final int id1 = contentId;
					final String uploadPathName = uploadPath + name;
					final String pptDirName = name;
					(new Thread() {
						@Override
						public void run() {
							/**
							 * This method is used for converting the pdf into
							 * png type image.
							 */
							documentManipulation.convertPDFtoImageAndUploadInS3Bucket(pptDirName,
									uploadPathName + "/" + id1 + ".pdf", uploadPathName, "png", orgName);
							File destpdf = new File(uploadPathName + "/" + id1 + ".pdf");
							destpdf.delete();
						}
					}).start();
					Thread.sleep(5000);
				}

				//name = orgName + "/" + name;
				/**
				 * update the content name and page number information about
				 * content.
				 */
				QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
						new Object[] { name, pageNum, content.getContentId() });
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service updateUploadedContent method:::::", e);
		}
		return contentId;
	}

	/**
	 * This is used for updating the content if content type is not pdf or
	 * powerpoint document.
	 * 
	 * @param file
	 * @param content
	 * @param userId
	 * @param orgName
	 * @return contentId
	 */
	public Integer updateOtherS3Content(MultipartFile file, SectionContent content, Integer userId, String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service updateOtherS3Content method:::::::::::");
		Integer contentId = 0;
		try {
			String name = new StringBuilder(file.getOriginalFilename())
					.insert(file.getOriginalFilename().lastIndexOf("."), ("_" + content.getContentId())).toString();
			/**
			 * This is used for uploading the content in S3 Bucket from server.
			 */
			Boolean isUploaded = awsS3Service.uploadMultipartContent(file, name, orgName);
			Long fileSize = file.getSize();
			/**
			 * update content information in content manager.
			 */
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_UPLOAD_CONTENT,
					new Object[] { content.getContentName(), content.getContent(), content.getContent(),
							content.getContentTypeId(), content.getContentTypeId(), 0, userId, content.getVisiblity(),
							content.getDescription(), new Date(), fileSize, content.getContentId() });
			/**
			 * When Content is uploaded successfully in s3 bucket, then delete
			 * the content from server.
			 */
			if (id > 0 && isUploaded) {
				int pageNum = 0;
				//name = orgName + "/" + name;
				/**
				 * update the content name and page number information about
				 * content.
				 */
				QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
						new Object[] { name, pageNum, content.getContentId() });
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service updatePPTContent method:::::", e);
		}
		return contentId;
	}

	/**
	 * 
	 * ========================================================================
	 * ======================== ==== End of code for update the content ====
	 * ==== ==== ==== This code is used for replacing the old content data with
	 * new uploaded content's data. ====
	 * ========================================
	 * ========================================================
	 * 
	 */

	/**
	 * Method to remove uploaded content by contentId
	 * 
	 * @param contentId
	 * @return boolean
	 */
	public boolean findAndRemoveContent(int contentId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service findAndRemoveContent method:::::::::::");
		boolean status = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_CONTENT_BEFORE_DELETE,
					new Object[] { contentId });
			if (data.getRows().size() == 0) {
				boolean flag = false;
				SectionContent content = findOneContent(contentId);
				if (content.getIsExternalURL() == 0) {
					/**
					 * This is used for checking that object exists or not in S3
					 * bucket if it does not exist than this content may be a
					 * directory.
					 */
					Boolean isObject = awsS3Service.isObjectExist(content.getContent());

					/**
					 * If exist then delete the object.
					 */
					if (isObject) {
						/**
						 * delete object from S3 Content
						 */
						flag = awsS3Service.deleteS3Content(content.getContent());
					} else if (content.getContent().contains(PDF_DIRECTORY_CONTAINS_S3_BUCKET)
							|| content.getContent().contains(PPT_DIRECTORY_CONTAINS_S3_BUCKET)) {
						/**
						 * delete object from S3 Content
						 */
						flag = awsS3Service.deleteS3DirWithitsContent(content.getContent());
					}
				} else {
					flag = true;
				}
				if (flag) {
					QueryManager.execuateUpdate(QueryStrings.DELETE_UPLOADCONTENT_BY_CONTENTID,
							new Object[] { contentId });
					status = true;
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service findAndRemoveContent method:::::", e);
		}
		return status;
	}

	/**
	 * ========================== not in use ===================================
	 * 
	 * This is used for save multiple contents
	 * 
	 * @return array of contents id's
	 * @param array
	 *            of content files
	 * @param array
	 *            of content titles
	 */
	public int[] saveMultipleContents(MultipartFile[] files, String[] title, int userId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service saveMultipleContents method:::::::::::");
		String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
		int[] contents = new int[files.length];
		String orgName = "orgName";
		try {
			for (int i = 0; i < files.length; i++) {
				SectionContent content = new SectionContent();
				if (!files[i].isEmpty()) {
					String uuid = UUID.randomUUID().toString();
					File dir = new File(uploadPath + File.separator);
					if (!dir.exists())
						dir.mkdirs();
					File serverFile = new File(
							dir.getAbsolutePath() + File.separator + uuid + files[i].getOriginalFilename());
					files[i].transferTo(serverFile);
					Long fileSize = files[i].getSize();
					content.setContent(files[i].getOriginalFilename());
					String temp = courseService.checkFileTypeValid(files[i].getOriginalFilename()
							.substring(files[i].getOriginalFilename().lastIndexOf(".") + 1));
					String contentdata[] = temp.split("####");
					content.setContentTypeId(Integer.parseInt(contentdata[0]));
					if (title.length != 0) {
						content.setContentName(title[i].isEmpty() ? files[i].getOriginalFilename() : title[i]);
					} else {
						content.setContentName(files[i].getOriginalFilename());
					}

					if (serverFile.exists()) {
						Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS,
								new Object[] { content.getContentName(), content.getContent(),
										content.getContentTypeId(), 0, userId, 0, null, fileSize });
						if (id > 0) {
							contents[i] = id;
							String name = "";
							int pageNum = 0;
							if (content.getContentTypeId() == 1) {
								name = (id + "_pdf");
								File dir1 = new File(uploadPath + name + File.separator);
								if (!dir1.exists())
									dir1.mkdirs();
								pageNum = documentManipulation.manipulatePdf(
										uploadPath + uuid + files[i].getOriginalFilename(), uploadPath + name);
								File dest = new File(uploadPath + uuid + files[i].getOriginalFilename());
								dest.delete();
							} else if (content.getContentTypeId() == 13 || content.getContentTypeId() == 14) {
								name = (id + "_ppt");
								File dir1 = new File(uploadPath + name + File.separator);
								if (!dir1.exists())
									dir1.mkdirs();
								Boolean isConvertPDF = documentManipulation.convertPDFFromOfficeDocument(
										uploadPath + uuid + files[i].getOriginalFilename(),
										uploadPath + name + "/" + id + ".pdf");
								if (isConvertPDF) {
									File dest = new File(uploadPath + uuid + files[i].getOriginalFilename());
									dest.delete();
									pageNum = new PdfReader(uploadPath + name + "/" + id + ".pdf").getNumberOfPages();
									final int id1 = id;
									final String uploadPathName = uploadPath + name;
									(new Thread() {
										@Override
										public void run() {
											documentManipulation.convertPDFtoImage(uploadPathName + "/" + id1 + ".pdf",
													uploadPathName, "png");
											File destpdf = new File(uploadPathName + "/" + id1 + ".pdf");
											destpdf.delete();
										}
									}).start();
									Thread.sleep(5000);
								}
							} else {
								name = new StringBuilder(files[i].getOriginalFilename())
										.insert(files[i].getOriginalFilename().lastIndexOf("."), ("_" + id)).toString();
								File dest = new File(uploadPath + uuid + files[i].getOriginalFilename());
								/**
								 * This is used for uploading the content in S3
								 * Bucket from server.
								 */
								Boolean isUploaded = awsS3Service.uploadContentFromServer(dest, name, orgName);
								/**
								 * When Content is uploaded successfully in s3
								 * bucket, then delete the content from server.
								 */
								if (isUploaded) {
									//name = orgName + "/" + name;
									dest.delete();
								}
							}
							QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
									new Object[] { name, pageNum, id });
						}
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service saveMultipleContents method:::::", e);
		}
		return contents;
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
	 * 
	 * @param files
	 * @param title
	 * @param userId
	 * @return
	 */
	public int[] saveMultipleContentsInS3Bucket(MultipartFile[] files, String[] title, int userId, String orgName,String duration[]) {
		logger.log(Level.DEBUG, "Inside Upload Content Service saveMultipleContentsInS3Bucket method:::::::::::");
		int[] contents = new int[files.length];
		String fileName=null;
		String titleName=null;
		try {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isEmpty()) {
					SectionContent content = new SectionContent();
					content.setContent(files[i].getOriginalFilename());
					String temp = courseService.checkFileTypeValid(files[i].getOriginalFilename()
							.substring(files[i].getOriginalFilename().lastIndexOf(".") + 1));
					String contentdata[] = temp.split("####");
					content.setContentTypeId(Integer.parseInt(contentdata[0]));
					if (title.length != 0) {
						fileName=files[i].getOriginalFilename();
					
						titleName=title[i];
						/*if(titleName.contains(" "))
						{
							titleName=titleName.replace(" ", "_");
							
						}*/
						content.setContentName(title[i].isEmpty() ? fileName : titleName);
						
					} else {
						content.setContentName(fileName);
					}
					switch (content.getContentTypeId()) {
					case 1: {
						Integer contentId = uploadPDFFile(files[i], userId, content, orgName);
						if (contentId != null && contentId > 0) {
							contents[i] = contentId;
						}
						break;
					}
					case 13:
					case 14: {
						Integer contentId = uploadPowerpointFile(files[i], userId, content, orgName);
						if (contentId != null && contentId > 0) {
							contents[i] = contentId;
						}
						break;
					}
					default: {
						Integer contentId = uploadOtherFilesInS3Bucket(files[i], userId, content, orgName,duration[i]);
						if (contentId != null && contentId > 0) {
							contents[i] = contentId;
						}
						break;
					}
					}
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Upload Content Service saveMultipleContentsInS3Bucket method:::::", e);
		}
		return contents;
	}

	/**
	 * This is used for uploading the content in S3 bucket if content is not pdf
	 * or powerpint document types.
	 * 
	 * @param file
	 * @param userId
	 * @param content
	 * @param orgName
	 * @return contentId
	 */
	public Integer uploadOtherFilesInS3Bucket(MultipartFile file, Integer userId, SectionContent content,
			String orgName,String duration) {
		logger.log(Level.DEBUG, "Inside Upload Content Service uploadOtherFilesInS3Bucket method:::::::::::");
		Integer contentId = 0;
		String getContent=content.getContent();
		
		try {
			Long fileSize = file.getSize();
			String uuid = UUID.randomUUID().toString();
			String fileName =file.getOriginalFilename();

			/**
			 * This is used for uploading the content in S3 Bucket from server.
			 */
			Boolean isUploaded =awsS3Service.uploadMultipartContent(file, fileName, orgName);
			System.out.println("isUploaded"+isUploaded);
			if (isUploaded) {
			if(getContent.contains(" "))
			{
				getContent=getContent.replace(" ", "_");
				System.out.println("=====<<=====>>"+content.getContentName());
			}
				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS,
						new Object[] { content.getContentName(), getContent, content.getContentTypeId(), 0,
								userId, 0, null, fileSize ,ContentStatus.PROCESSING.getValue(),duration});
				if (id > 0) {
					contentId = id;
					int pageNum = 0;
					String newFileName = new StringBuilder(file.getOriginalFilename())
							.insert(file.getOriginalFilename().lastIndexOf("."), ("_" + id)).toString();
					awsS3Service.renameContent(fileName, newFileName);
					QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { newFileName, pageNum, id });
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service uploadOtherFilesInS3Bucket method:::::",
					e);
			e.printStackTrace();
		}
		return contentId;
	}

	/**
	 * This is used for uploading the powerpoint document type content.
	 * 
	 * @param file
	 * @param userId
	 * @param content
	 * @return contentId
	 */
	public Integer uploadPowerpointFile(MultipartFile file, Integer userId, SectionContent content,
			final String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service uploadPowerpointFile method:::::::::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File dir = new File(uploadPath + File.separator);
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
			file.transferTo(serverFile);
			Long fileSize = file.getSize();

			if (serverFile.exists()) {
				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS,
						new Object[] { content.getContentName(), content.getContent(), content.getContentTypeId(), 0,
								userId, 0, null, fileSize });
				if (id > 0) {
					contentId = id;
					int pageNum = 0;
					String name = (id + "_ppt");
					File dir1 = new File(uploadPath + name + File.separator);
					if (!dir1.exists())
						dir1.mkdirs();
					Boolean isConvertPDF = documentManipulation.convertPDFFromOfficeDocument(uploadPath + fileName,
							uploadPath + name + "/" + id + ".pdf");
					if (isConvertPDF) {
						File dest = new File(uploadPath + fileName);
						dest.delete();
						pageNum = new PdfReader(uploadPath + name + "/" + id + ".pdf").getNumberOfPages();
						final int id1 = id;
						final String uploadPathName = uploadPath + name;
						final String pptDirName = name;
						(new Thread() {
							@Override
							public void run() {
								try {
									documentManipulation.convertPDFtoImageAndUploadInS3Bucket(pptDirName,
											uploadPathName + "/" + id1 + ".pdf", uploadPathName, "png", orgName);
									File destpdf = new File(uploadPathName + "/" + id1 + ".pdf");
									destpdf.delete();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}).start();
						Thread.sleep(5000);
					}
					//name = orgName + "/" + name;
					QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { name, pageNum, id });
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service uploadPowerpointFile method:::::", e);
		}
		return contentId;
	}

	/**
	 * This is used for uploading the pdf type content.
	 * 
	 * @param file
	 * @param userId
	 * @param content
	 * @return contentId
	 */
	public Integer uploadPDFFile(MultipartFile file, Integer userId, SectionContent content, final String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service uploadPDFFile method:::::::::::");
		Integer contentId = 0;
		try {
			String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
			String uuid = UUID.randomUUID().toString();
			File dir = new File(uploadPath + File.separator);
			String fileName = uuid + file.getOriginalFilename();
			if (!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
			file.transferTo(serverFile);
			Long fileSize = file.getSize();
			if (serverFile.exists()) {
				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_DETAILS,
						new Object[] { content.getContentName(), content.getContent(), content.getContentTypeId(), 0,
								userId, 0, null, fileSize });
				if (id > 0) {
					contentId = id;
					String name = (id + "_pdf");
					;
					int pageNum = 0;
					File dir1 = new File(uploadPath + name + File.separator);
					if (!dir1.exists())
						dir1.mkdirs();
					pageNum = documentManipulation.manipulatePdfAndUploadInS3Bucket(name, uploadPath + fileName,
							uploadPath + name, orgName);
					File dest = new File(uploadPath + fileName);
					dest.delete();
					//name = orgName + "/" + name;
					QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
							new Object[] { name, pageNum, id });
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service uploadPDFFile method:::::", e);
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
	 * This is used for save multiple contents by URL
	 * 
	 * @return array of contents id's
	 * @param array
	 *            of content url
	 * @param int
	 *            userId
	 * @return int[]
	 */
	public int[] saveMultipleContentsByURL(String[] contentUrl, String[] p_title, Integer userId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service saveMultipleContentsByURL method:::::::::::");
		int[] contents = new int[contentUrl.length];
		try {
			String title = null;
			for (int i = 0; i < contentUrl.length; i++) {
				if (p_title.length != 0) {
					title = p_title[i].isEmpty() ? contentUrl[i].substring(contentUrl[i].indexOf("=") + 1) : p_title[i];
				} else {
					title = contentUrl[i].substring(contentUrl[i].indexOf("=") + 1);
				}

				Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_UPLOAD_CONTENT_BY_URL,
						new Object[] { title, contentUrl[i], userId, 1, 15 });
				if (id > 0) {
					contents[i] = id;
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service saveMultipleContentsByURL method:::::", e);
		}
		return contents;
	}

	/**
	 * This is used for mapping contents into section
	 * 
	 * @param array
	 *            of contents
	 * @param sectionId
	 */
	public void mapContentIntoSection(int[] contents, int sectionId,int attemptid,int sessionid) {
		logger.log(Level.DEBUG, "Inside Upload Content Service mapContentIntoSection method:::::::::::");
		SectionContent content = null;
		try {
		for (int i = 0; i < contents.length; i++) {
		content = findOneContent(contents[i]);
		QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_SECTION_MAPPING,
		new Object[] { contents[i], sectionId, content.getContentName(), attemptid,sessionid });
		}
		} catch (Exception e) {
		logger.log(Level.ERROR, "Exception Inside Upload Content Service mapContentIntoSection method:::::", e);
		}
		}

	/**
	 * Method to get list of contents by userId
	 * 
	 * @param userId
	 * @param sectionId
	 * @return list of contents
	 */
	public List<SectionContent> findAllContentNotInSection(Integer userId, int sectionId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service findAllContentNotInSection method:::::::::::");
		List<SectionContent> list = new ArrayList<SectionContent>();
		SectionContent content;

		try {
			// QueryData data =
			// QueryManager.execuateQuery(QueryStrings.CONTENT_LIST_NOT_IN_SECTION,
			// new Object[] {userId,sectionId});
			QueryData data = QueryManager.execuateQuery(QueryStrings.CONTENT_LIST_NOT_IN_SECTION,
					new Object[] { userId, ContentStatus.FINISHED.getValue() });
			for (QueryData.Row row : data.getRows()) {
				content = new SectionContent();
				content.setContentId(Integer.parseInt(row.getRowItem(0)));
				content.setContentName(row.getRowItem(1));
				content.setContent(row.getRowItem(2));
				content.setNumPages(row.getRowItem(6) != null ? Integer.parseInt(row.getRowItem(6)) : 0);
				content.setUserId((row.getRowItem(7) != null ? Integer.parseInt(row.getRowItem(7)) : null));
				content.setUploadedBy(row.getRowItem(8) != null ? row.getRowItem(8) : null);
				content.setVisiblity(Integer.parseInt(row.getRowItem(9)));
				content.setDescription(row.getRowItem(10));
				content.setContentType(row.getRowItem(11));
				/*
				 * String PROFILE_IMAGE_PATH = ConstantUtil.PROFILE_IMAGE_PATH;
				 * String IMAGE_DIRECTORY = ConstantUtil.COURSE_FILE_DIRECTORY;
				 * String uploadPath = PROFILE_IMAGE_PATH + IMAGE_DIRECTORY;
				 * File dest = new File(uploadPath + content.getContent());
				 * if(dest.exists()){ double bytes = dest.length(); double
				 * kilobytes = (bytes / 1024); ; //double megabytes = (kilobytes
				 * / 1024); DecimalFormat df = new DecimalFormat("####0.00");
				 * content.setSize(Double.parseDouble(df.format(kilobytes))); }
				 */

				list.add(content);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service findAllContentNotInSection method:::::",
					e);
		}
		return list;
	}

	/**
	 * This is used for getting list of content type
	 * 
	 * @return list
	 */
	public List<SectionContent> findAllContentType() {
		logger.log(Level.DEBUG, "Inside Upload Content Service findAllContentType method:::::::::::");
		List<SectionContent> list = new ArrayList<SectionContent>();
		SectionContent content;

		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_ALL_CONTENT_TYPE, new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				content = new SectionContent();
				content.setContentTypeId(Integer.parseInt(row.getRowItem(0)));
				content.setContentType(row.getRowItem(1));
				content.setExtension(row.getRowItem(3));
				list.add(content);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service findAllContentType method:::::", e);
		}
		return list;
	}

	/**
	 * Method to get list of contentId's that are mapped with sections
	 * 
	 * @param sectionId
	 * @return List<Integer>
	 */
	public List<Integer> getMappedContentBySectionId(int sectionId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service getMappedContentBySectionId method:::::::::::");
		List<Integer> list = new ArrayList<Integer>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_MAPPED_CONTENT_BY_SECTION_ID,
					new Object[] { sectionId });
			for (QueryData.Row row : data.getRows()) {
				list.add(Integer.parseInt(row.getRowItem(0)));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service getMappedContentBySectionId method:::::",
					e);
		}
		return list;
	}

	/**
	 * Method to get abuse comments details about content by student
	 * 
	 * @param contentId
	 * @param courseId
	 * @return list of SectionContent
	 */
	public List<SectionContent> getAbuseDataContent(Integer contentId, Integer courseId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service getAbuseDataContent method:::::::::::");
		List<SectionContent> list = new ArrayList<SectionContent>();
		SectionContent content;

		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.ABUSE_REPORT_BY_CONTENT_ID,
					new Object[] { contentId });
			for (QueryData.Row row : data.getRows()) {
				content = new SectionContent();
				content.setReportId(Integer.parseInt(row.getRowItem(0)));
				content.setContentId(contentId);
				content.setAbuseReport(row.getRowItem(3));
				content.setCourseMapped(row.getRowItem(5));
				content.setCourseId(Integer.parseInt(row.getRowItem(6)));
				list.add(content);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service getAbuseDataContent method:::::", e);
		}
		return list;
	}

	/**
	 * Method to edit details of uploaded content of you tube URL type
	 * 
	 * @param object
	 *            of SectionContent
	 * @param userId
	 */
	public void updateUploadedContentEmbedUrl(SectionContent content, Integer userId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service updateUploadedContentEmbedUrl method:::::::::::");
		try {
			content.setContentTypeId(15);
			QueryManager.execuateUpdate(QueryStrings.UPDATE_UPLOAD_CONTENT,
					new Object[] { content.getContentName(), content.getContent(), content.getContent(),
							content.getContentTypeId(), content.getContentTypeId(), 0, userId, content.getVisiblity(),
							content.getDescription(), new Date(), 0, content.getContentId() });

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service updateUploadedContentEmbedUrl method:::::",
					e);
		}

	}

	/**
	 * This is used saving video as content.
	 * 
	 * @param content
	 * @param userId
	 * @return Integer
	 */
	public Integer saveVideoContent(SectionContent content, Integer userId, String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service saveVideoContent method:::::::::::");
		Integer id = null;
		try {
			id = QueryManager.execuateUpdate(QueryStrings.INSERT_VIDEO_CONTENT,
					new Object[] { content.getContentName(), content.getContent(), content.getContentTypeId(), userId,
							content.getSize(), content.getNumPages() });
			if (id > 0) {
				String fileName = "";
				/**
				 * This is pdf content.
				 */
				if (content.getContentTypeId() == 1) {
					//fileName = orgName + "/" + id + "_pdf";
					fileName = id + "_pdf";
				}
				/**
				 * This is ppt content.
				 */
				else if (content.getContentTypeId() == 13 || content.getContentTypeId() == 14) {
					//fileName = orgName + "/" + id + "_ppt";
					fileName = id + "_ppt";
				}
				/**
				 * this is video content.
				 */
				else if (content.getContent() != null && content.getContent().contains("_")) {
					fileName = content.getContent().substring(0, content.getContent().indexOf("_") + 1) + id
							+ content.getContent().substring(content.getContent().lastIndexOf("."));
				}

				/**
				 * This is used for checking that object exists or not in S3
				 * bucket if it does not exist than this content may be a
				 * directory.
				 */
				Boolean isObject = awsS3Service.isObjectExist(content.getContent());
				/**
				 * If exist then delete the object.
				 */
				if (isObject) {
					/**
					 * copy object in S3 Bucket.
					 */
					awsS3Service.copyContent(content.getContent(), fileName);

				} else if (content.getContent().contains(PDF_DIRECTORY_CONTAINS_S3_BUCKET)
						|| content.getContent().contains(PPT_DIRECTORY_CONTAINS_S3_BUCKET)) {
					/**
					 * copy directory with its contents
					 */
					awsS3Service.copyS3Directory(content.getContent(), fileName);
				}
				QueryManager.execuateUpdate(QueryStrings.SET_CONTENT_BY_CONTENTID,
						new Object[] { fileName, content.getNumPages(), id });
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service saveVideoContent method:::::", e);
		}
		return id;
	}

	/**
	 * ================not in use ==============
	 * 
	 * This is used saving audio with ppt.
	 * 
	 * @param file
	 * @param contentId
	 * @param userId
	 * @param slideHoldTime
	 * @param pageNum
	 */
	public void savePPTAudio(MultipartFile file, int contentId, Integer userId, Integer slideHoldTime, int pageNum) {
		logger.log(Level.DEBUG, "Inside Upload Content Service savePPTAudio method:::::::::::");
		String uploadPath = ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.COURSE_FILE_DIRECTORY;
		try {
			String name = (contentId + "_audio");
			QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_AUDIO,
					new Object[] { contentId, userId, name, slideHoldTime, pageNum });
			if (file != null && !file.isEmpty()) {
				String uuid = UUID.randomUUID().toString();
				File dir = new File(uploadPath + name + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename() + uuid);
				file.transferTo(serverFile);
				if (serverFile.exists()) {
					File dest = new File(uploadPath + name + File.separator + file.getOriginalFilename() + uuid);
					name += File.separator + "audio_" + pageNum + ".mp3";
					dest.renameTo(new File(uploadPath + name));
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service savePPTAudio method:::::", e);
		}
	}

	/**
	 * This is used saving audio with ppt in s3 bucket.
	 * 
	 * @param file
	 * @param contentId
	 * @param userId
	 * @param slideHoldTime
	 * @param pageNum
	 */
	public void savePPTAudioInS3Bukcet(MultipartFile file, int contentId, Integer userId, Integer slideHoldTime,
			int pageNum, String orgName) {
		logger.log(Level.DEBUG, "Inside Upload Content Service savePPTAudioInS3Bukcet method:::::::::::");
		try {
			String name = (contentId + "_audio");
			String dirName = orgName + File.separator + name;
			QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_AUDIO,
					new Object[] { contentId, userId, dirName, slideHoldTime, pageNum });
			if (file != null && !file.isEmpty()) {
				String fileName = name + File.separator + "audio_" + pageNum + ".mp3";
				awsS3Service.uploadMultipartContent(file, fileName, orgName);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service savePPTAudio method:::::", e);
		}
	}

	/**
	 * This is used getting question list of audio clip type content based on
	 * content id.
	 * 
	 * @param contentId
	 * @return List<Question>
	 */
	public List<Question> findAudioByContentId(int contentId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service findAudioByContentId method:::::::::::");
		List<Question> questionList = new ArrayList<Question>();
		try {
			QueryData questionData = QueryManager.execuateQuery(QueryStrings.GET_AUDIO_BY_CONTENT_ID,
					new Object[] { contentId });
			for (QueryData.Row quesRow : questionData.getRows()) {
				Question question = new Question();
				question.setQuestionId(Integer.parseInt(quesRow.getRowItem(0)));
				if (quesRow.getRowItem(2) != null) {
					/*
					 * String contentURL = ConstantUtil.SERVER_IP +
					 * ConstantUtil.COURSE_FILE_DIRECTORY +
					 * quesRow.getRowItem(2);
					 */
					String contentURL = ConstantUtil.AWS_S3_BUCKET_CONTENT_ACCESS_PATH + ConstantUtil.AWS_S3_BUCKET_NAME
							+ File.separator + quesRow.getRowItem(2);
					question.setFileName(contentURL);
				}
				if (quesRow.getRowItem(3) != null)
					question.setPageNum(Integer.parseInt(quesRow.getRowItem(3)));
				if (quesRow.getRowItem(4) != null) {
					question.setSlideHoldTime(Integer.parseInt(quesRow.getRowItem(4)));
				}
				questionList.add(question);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service findAudioByContentId method:::::", e);
		}
		return questionList;
	}

	/**
	 * This is used getting suggestion list of content based on course's title,
	 * description and tags.
	 * 
	 * @param req
	 * @param userId
	 * @param courseId
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getContentSuggestionList(Datatable req, Integer userId, Integer courseId) {
		logger.log(Level.DEBUG, "Inside Upload Content Service getContentSuggestionList method:::::::::::");
		Map<String, Object> result = new HashMap<String, Object>();
		SectionContent content;
		try {
			Course course = courseService.getCourseDetails(courseId, userId);
			List<Object> contentList = new ArrayList<Object>();
			result.put("draw", req.getDraw());
			String query = QueryManager.QueryStrings.GET_SUGGESTION_CONTENT_LIST.getQuery();
			String groupquery = " ORDER BY course_content_manager.uploaded_datetime DESC";
			String[] tags;
			if (course.getCourseTag() != null) {
				if (course.getCourseTag().contains(",")) {
					tags = course.getCourseTag().split(",");
				} else if (course.getCourseTag().trim().length() > 0) {
					tags = new String[] { course.getCourseTag().trim() };
				} else {
					tags = new String[0];
				}
			} else {
				tags = new String[0];
			}

			String tagQuery = "";
			for (int len = 0; len < tags.length; len++) {
				tagQuery = tagQuery + " OR course_manager.tags LIKE ? ";
			}
			QueryData data;
			if (course.getCourseId() != null) {

				query = query + " AND (course_manager.title LIKE ?" + " OR course_manager.description LIKE ? "
						+ tagQuery + " )";

				Object object[] = new Object[4 + tags.length];

				for (int i = 0; i < object.length; i++) {
					switch (i) {
					case 0:
						object[i] = userId;
						break;
					case 1:
						object[i] = courseId;
						break;
					case 2:
						object[i] = "%" + course.getCourseName() + "%";
						break;
					case 3:
						object[i] = "%" + course.getCourseDesc() + "%";
						break;
					default:
						object[i] = "%" + tags[i - 4].trim() + "%";
						break;
					}
				}

				query = query + groupquery;
				data = QueryManager.execuateQuery(query, object);
			} else {
				query = query + groupquery;
				data = QueryManager.execuateQuery(query, new Object[] { userId, courseId });

			}
			result.put("recordsTotal", data.getRows().size());
			result.put("recordsFiltered", data.getRows().size());

			int i = 0;

			for (QueryData.Row row : data.getRows()) {
				if (i < (req.getStart() + req.getLength()) && i >= req.getStart()) {
					content = new SectionContent();
					content.setContentId(Integer.parseInt(row.getRowItem(0)));
					content.setContentName(row.getRowItem(1));
					content.setContent(row.getRowItem(2));
					content.setContentTypeId(Integer.parseInt(row.getRowItem(4)));
					content.setContentType(row.getRowItem(5));
					content.setCreatedDate(row.getRowItem(6));
					content.setContentIconPath(row.getRowItem(7));
					contentList.add(content);
				}
				i++;
			}
			result.put("data", contentList);

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside Upload Content Service getContentSuggestionList method:::::", e);
		}

		return result;
	}

	/**
	 * This is used for creating mapping between section and content drag from
	 * suggestion list by user.
	 * 
	 * @param sectionId
	 * @param contentId
	 * @return SectionContent
	 */
	public SectionContent mapSuggestionContentIntoSection(Integer sectionId, Integer contentId, String serverPath) {
		logger.log(Level.DEBUG, "Inside Upload Content Service mapSuggestionContentIntoSection method:::::::::::");
		SectionContent content = null;
		try {
			content = findOneContent(contentId);
			Integer id = QueryManager.execuateUpdate(QueryStrings.SAVE_CONTENT_SECTION_MAPPING,
					new Object[] { contentId, sectionId, content.getContentName() });
			if (id == 0) {
				content = null;
			} else {
				if (content.getIsExternalURL() == 0 && content.getContentType().equalsIgnoreCase("TEST")) {
					content.setContentPath(serverPath + "/testpreview?action=frame&testId=" + content.getContent());
				} else if (content.getIsExternalURL() == 1 && content.getContentType().equalsIgnoreCase("VIDEO")) {
					content.setContentType("URL");
				}

				else if (content.getContentType().equalsIgnoreCase("VIDEO")
						|| content.getContentType().equalsIgnoreCase("PDF")) {
					content.setQuestionList(questionService.findVideoQuestionByContentId(content.getContentId()));
				} else if (content.getContentType().equalsIgnoreCase("PPT")
						|| content.getContentType().equalsIgnoreCase("PPTX")) {
					content.setQuestionList(findAudioByContentId(content.getContentId()));
				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR,
					"Exception Inside Upload Content Service mapSuggestionContentIntoSection method:::::", e);
		}
		return content;
	}

	/**
	 * This method is used for updating the content status.
	 * 
	 * @param content
	 *            {@link ContentStatus}
	 * @return
	 */
	public boolean updateContentStatus(ContentProcessingResponse content) {
		logger.debug("Inside Upload Content Service in updateContentStatus method ::::::");
		int status = ContentStatus.FAILED.getValue();
		  if (ConstantUtil.WORKSTATUS.equals(content.getWorkflowStatus()))
		  {
			  status=ContentStatus.FINISHED.getValue();
				  int rows
				  =QueryManager.execuateUpdate(QueryStrings.UPDATE_CONTENT_STREAMING_DATA, new
				  Object[] { status, content.getHlsUrl(), content.getSrcVideo(),
				  ContentStatus.FINISHED.getValue() }); 
				  if (rows > 0) 
				  { 
					  return true; 
				  }
				 
		  } 
		return false;
	}
	/**
	* This method is used for updating the content status via api.
	* 
	* @param content {@link ContentStatus}
	* @return
	*/

	public boolean updateContentprestatus( HttpServletResponse response, ContentProcessingResponse content) {
	boolean flag = true;
	System.out
	.println("status=========> " + content.getStatus() + "--SrcVideo============> " + content.getSrcVideo()
	+ "--HLS==========> " + content.getHlsUrl() + "--WorkFlow ==>" + content.getWorkflowStatus());
	SectionContent sectionContent = new SectionContent();
	String securedUrl=content.getHlsUrl().replace(content.getHlsUrl().substring(8,37),"cdntest.eluminate.in");
	System.out.println("securedUrl==========>"+securedUrl);
	logger.debug("Inside Upload Content Service in updatePreContentStatus method ::::::");

	{

	QueryData data = QueryManager.execuateQuery(QueryStrings.GETCONTENTDATA,
	new Object[] { content.getSrcVideo() });
	for (QueryData.Row row : data.getRows()) {

	sectionContent.setContentURL(row.getRowItem(0));
	}

	if (content.getHlsUrl() != null) {
	System.out.println("QueryUpdateHoRahi");
	System.out.println("securedUrl==========>"+securedUrl);
	if (content.getWorkflowStatus().equals(ConstantUtil.WORKSTATUS)) {
	Integer status = ContentStatus.FINISHED.getValue();
	//int rows = QueryManager.execuateUpdate(QueryStrings.UPDATEFINALCONTENTDATA,
	int rows = QueryManager.execuateUpdate(QueryStrings.UPDATE_STREAMING_URL,
	new Object[] { securedUrl,1, content.getSrcVideo() });
                   // authenticationService.getCookieDataForPlayer(response,null);
					/*
					 * HlsurlRequest request = new HlsurlRequest();
					 * request.setURL(content.getHlsUrl()); request.setEXPIRE(10); String hlsUrl =
					 * authenticationService.getHlsUrl(request);
					 * 
					 * int rowss = QueryManager.execuateUpdate(QueryStrings.UPDATE_STREAMING_URL,
					 * new Object[] { hlsUrl, status, content.getSrcVideo() });
					 */
	flag = true;
	}

	}

	}

	return flag;

	}
}


