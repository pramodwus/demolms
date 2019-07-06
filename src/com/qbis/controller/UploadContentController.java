package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.LicenseEnum;
import com.qbis.model.Datatable;
import com.qbis.model.License;
import com.qbis.model.LicenseSubDetails;
import com.qbis.model.Question;
import com.qbis.model.SectionContent;
import com.qbis.model.User;
import com.qbis.services.NotificationService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.QuestionService;
import com.qbis.services.UploadContentService;

@Controller
public class UploadContentController {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(UploadContentController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * Instance of {@link UploadContentService}
	 */
	@Autowired
	private UploadContentService uploadContentService;

	/**
	 * Instance of {@link QbisLicenseService}
	 */
	@Autowired
	private QbisLicenseService qbisLicenseService;

	/**
	 * Instance of {@link QuestionService}
	 */
	@Autowired
	private QuestionService questionService;

	/**
	 * This is used for getting all uploaded contents
	 * 
	 * @return listuploadcontent page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/listuploadcontent")
	public ModelAndView getUploadedContentList()
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController getUploadedContentList method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		ModelAndView model = new ModelAndView();
		model.addObject("currentLocale", user.getSystemLanguage());
		model.addObject("domainName", ConstantUtil.DOMAIN_NAME);
		model.addObject("contentTypeList",
				uploadContentService.findAllContentType());
		model.setViewName("listuploadcontent");
		return model;
	}

	/**
	 * This is used for getting content list for server side data processing in
	 * data table.
	 * 
	 * @param req
	 * @param type
	 * @param visiblity
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/listuploadcontentserversideprocessing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getUploadedContentListFromServer(
			@ModelAttribute("contentData") Datatable req,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "visiblity", required = false) Integer visiblity)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController getUploadedContentListFromServer method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			result = uploadContentService.getContentListData(user.getUserId(),
					req, type, visiblity);
		} else {
			result.put("error", "your session has been expired.");
		}
		return result;
	}

	/**
	 * Method to open adduploadcontent page
	 * 
	 * @return adduploadcontent page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/adduploadcontent")
	public ModelAndView addUploadedContentPage()
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController addUploadedContentPage method ::::: ");
		ModelAndView model = new ModelAndView();
		model.setViewName("adduploadcontent");
		return model;
	}

	/**
	 * Method to save new uploaded content by user
	 * 
	 * @return listuploadcontent page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveuploadedcontent")
	public ModelAndView saveUploadedContent(
			@RequestParam(value = "filepath", required = false) MultipartFile file,
			@ModelAttribute("uploadcontentform") SectionContent content,
			BindingResult result, RedirectAttributes redirectAttributes)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController saveUploadedContent method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		ModelAndView model = new ModelAndView();
		content.setVisiblity(content.getVisiblity() != null ? 1 : 0);
		if (content.getIsExternalURL() == 0) {
			String orgName = user.getSubdomain();
			/*
			 * uploadContentService.updateUploadedContent(content,
			 * user.getUserId(), file);
			 */
			uploadContentService.updateUploadedContentInS3Bucket(content,
					user.getUserId(), file, orgName);
		} else {
			uploadContentService.updateUploadedContentEmbedUrl(content,
					user.getUserId());
		}

		model.setViewName("redirect:/listuploadcontent");
		return model;
	}

	/**
	 * Method to open adduploadcontent page for edit
	 * 
	 * @return adduploadcontent page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/edituploadedcontent")
	public ModelAndView editUploadedContentPage(
			@RequestParam("id") int contentId) throws NoSuchAlgorithmException,
			InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController editUploadedContentPage method ::::: ");
		ModelAndView model = new ModelAndView();
		model.addObject("content",
				uploadContentService.findOneContent(contentId));
		model.setViewName("adduploadcontent");
		return model;
	}

	/**
	 * Method to delete uploaded content
	 * 
	 * @param contentId
	 * @return boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteuploadedcontent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteUploadedContent(
			@RequestParam(value = "contentId") Integer contentId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController deleteUploadedContent method ::::: ");
		return uploadContentService.findAndRemoveContent(contentId);
	}

	/**
	 * This is used for save multiple contents
	 * 
	 * @return array of contents id's
	 * @param array
	 *            of content files
	 * @param array
	 *            of content titles
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savemultiplecontents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> savemultiplecontents(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "title", required = false) String[] title,
			@RequestParam(value = "duration", required = false) String[] duration,
 			@RequestParam(value = "contentUrl", required = false) String[] contentUrl)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController savemultiplecontents method ::::: ");
		Map<String, Object> map = new HashMap<String, Object>();
		int arr[] = null;
		User user = (User) request.getSession().getAttribute("userSession");
		if (contentUrl != null && contentUrl.length != 0) {
			arr = uploadContentService.saveMultipleContentsByURL(contentUrl,
					title, user.getUserId());
			map.put("status", true);
		} else {
			Long size = 0L;
			for (int i = 0; i < files.length; i++) {
				size += files[i].getSize();
			}
			License license = (License) request.getSession().getAttribute(
					"licenseObj");
			int orgId = (Integer) request.getSession().getAttribute("orgId");
			license.setFileSize(size);
			license.setUserId(user.getUserId());
			License licensenew = qbisLicenseService.validateLicense(license,
					orgId, LicenseEnum.COURSECONTENTSPACE.getValue());
			if (true) {
				String orgName = user.getSubdomain();
				// arr = uploadContentService.saveMultipleContents(files,
				// title,user.getUserId());
				arr = uploadContentService.saveMultipleContentsInS3Bucket(
						files, title, user.getUserId(), orgName,duration);
				map.put("status", true);
			} else {
				map.put("remainSpaceMB", licensenew.getFileSize());
				map.put("status", false);
			}

		}
		map.put("contentsId", arr);
		return map;
	}

	/**
	 * This is used for getting all uploaded contents
	 * 
	 * @return listuploadcontent page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/opencontentlibrary")
	public ModelAndView openContentLibrary(
	@RequestParam("sectionId") int sectionId,@RequestParam("attemptid") int attemptid,@RequestParam("sessionid") int sessionid)
	throws NoSuchAlgorithmException, InvalidKeySpecException,
	IOException {
	logger.log(Level.DEBUG,
	"Inside UploadContentController openContentLibrary method ::::: ");
	User user = (User) request.getSession().getAttribute("userSession");
	ModelAndView model = new ModelAndView();
	model.addObject("mappedcontentlist",
	uploadContentService.getMappedContentBySectionId(sectionId));
	model.addObject(
	"contentlist",
	uploadContentService.findAllContentNotInSection(
	user.getUserId(), sectionId));
	model.addObject("sectionId", sectionId);
	model.addObject("attemptId",attemptid);
	model.addObject("sessionId",sessionid);
	model.setViewName("opencontentlibrary");
	return model;
	}

	/**
	 * This is used for mapping contents into section
	 * 
	 * @return Boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/mapcontentintosection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean mapContentIntoSection(
	@RequestParam("contents") int[] contents,
	@RequestParam("sectionId") int sectionId,@RequestParam("attempId") int attempId,@RequestParam("sessionId") int sessionId)
	throws NoSuchAlgorithmException, InvalidKeySpecException,
	IOException {
	logger.log(Level.DEBUG,
	"Inside UploadContentController mapContentIntoSection method ::::: ");
	//System.out.println(sectionId+""+attempId+""+sessionId);
	// User user = (User) request.getSession().getAttribute("userSession");
	uploadContentService.mapContentIntoSection(contents, sectionId,attempId,sessionId);
	return true;
	}

	/**
	 * This is used for view uploaded contents
	 * 
	 * @return viewuploadcontent page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewuploadcontent")
	public ModelAndView viewUploadedContent(
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "courseId", required = false) Integer courseId,
			@RequestParam(value = "nid", required = false) Integer nid)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController viewUploadedContent method ::::: ");
		if (nid != null) {
			NotificationService.updateNotificationReadStatus(nid, 1);
		}
		ModelAndView model = new ModelAndView();
		SectionContent content = uploadContentService.findOneContent(contentId);
		model.addObject("content", content);
		model.addObject("list",
				uploadContentService.getAbuseDataContent(contentId, courseId));
		model.setViewName("viewuploadcontent");
		return model;
	}

	/**
	 * Method to validate available space of contents at edit time.
	 * 
	 * @param fileSize
	 *            in bytes
	 * @return map
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "validateContentAvailableSpace", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> validateContentAvailableSpace(
			@RequestParam("fileSize") Long fileSize)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContent Controller validateContentAvailableSpace method :::::");
		Map<String, Object> map = new HashMap<String, Object>();
		License license = (License) request.getSession().getAttribute(
				"licenseObj");
		int orgId = (Integer) request.getSession().getAttribute("orgId");
		license.setFileSize(fileSize);
		License licensenew = qbisLicenseService.validateLicense(license, orgId,
				LicenseEnum.COURSECONTENTSPACE.getValue());
		if (licensenew.getValid()) {
			map.put("status", true);
		} else {
			map.put("remainSpaceMB", licensenew.getFileSize());
			map.put("status", false);
		}
		return map;
	}

	/**
	 * Method to open createvideowithquestion page
	 * 
	 * @param id
	 *            for content ID
	 * @param mode
	 *            for request getting from video(0) or PDF(1)
	 * @return createvideowithquestion page
	 */
	@RequestMapping(value = "/createvideowithquestion")
	public ModelAndView createVideoWithQuestion(
			@RequestParam("id") int contentId,
			@RequestParam(value = "mode", required = false) Integer mode) {
		logger.log(Level.DEBUG,
				"Inside UploadContentController createvideowithquestionPage method ::::: ");
		ModelAndView model = new ModelAndView();
		model.addObject("content",
				uploadContentService.findOneContent(contentId));
		License license = (License) request.getSession().getAttribute(
				"licenseObj");
		if (mode != null && mode != 2) {
			int orgId = (Integer) request.getSession().getAttribute("orgId");
			License licensenew = qbisLicenseService.validateLicense(license,
					orgId, "QUESTION_TYPE");
			model.addObject("quesTypeList", licensenew.getListQuesType());
		}
		model.addObject("mode", mode == null ? 0 : mode);
		if (mode != null && mode == 2) {
			if (license.getLicenseFeatureList() != null)
				for (int i = 0; i < license.getLicenseFeatureList().size(); i++) {
					if (license.getLicenseFeatureList().get(i).getFeatureName()
							.equalsIgnoreCase(LicenseEnum.SPACE.getValue())) {
						List<LicenseSubDetails> list = license
								.getLicenseFeatureList().get(i)
								.getLicenseSubDetails();
						for (int j = 0; j < list.size(); j++) {
							if (list.get(j).getSubFeatureName()
									.equalsIgnoreCase("COURSECONTENTSPACE")) {
								model.addObject("availableSpace", CommonUtil
										.convertBytesToMB(Long.parseLong(list
												.get(j).getMaxCount())));
								break;
							}
						}
					}
				}
		}
		model.setViewName("createvideowithquestion");
		return model;
	}

	/**
	 * Method to submit data for video or pdf with question
	 * 
	 * @param questionList
	 *            for list of added question on content
	 * @return map
	 */
	@RequestMapping(value = "/videoQuestionDataSubmit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> videoQuestionDataSubmit(
			@ModelAttribute("questionList") String questionList,
			BindingResult bind) throws JsonParseException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		User user = (User) request.getSession().getAttribute("userSession");
		Question section[] = mapper.readValue(questionList,
				new TypeReference<Question[]>() {
				});
		for (int i = 0; i < section.length; i++) {
			questionService.saveVideoQuestion(section[i], user.getUserId());
		}
		map.put("Success", "Success");
		return map;
	}

	/**
	 * Method to open importFromLibray page
	 * 
	 * @param flag
	 *            for request getting from video(0) or PDF(1)
	 * @return importFromLibray page
	 */
	@RequestMapping(value = "/importFromLibray")
	public ModelAndView importFromLibray(@RequestParam("flag") int flag) {
		logger.log(Level.DEBUG,
				"Inside UploadContentController importFromLibray method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("contentlist", uploadContentService.findAllContent(
				user.getUserId(), null, null));
		model.addObject("flag", flag);
		model.setViewName("importFromLibray");
		return model;
	}

	/**
	 * Method to open importFromLibray page
	 * 
	 * @param request
	 *            for requesting data from jsp input
	 * @return createvideowithquestion page
	 */
	@RequestMapping(value = "/saveImportContentData")
	public ModelAndView saveImportContentData(HttpServletRequest request) {
		logger.log(Level.DEBUG,
				"Inside UploadContentController saveImportContentData method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		SectionContent content = uploadContentService.findOneContent(Integer
				.parseInt(request.getParameter("contentId")));
		content.setContentName(request.getParameter("title"));
		String orgName = user.getSubdomain();
		Integer contId = uploadContentService.saveVideoContent(content,
				user.getUserId(), orgName);
		model.setViewName("redirect:createvideowithquestion?id=" + contId
				+ "&mode=" + request.getParameter("mode"));
		return model;
	}

	/**
	 * Method to open viewVideoQuestion page
	 * 
	 * @param contentId
	 *            for content ID
	 * @return viewVideoQuestion page
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/viewVideoQuestion")
	public ModelAndView viewVideoQuestion(
			@RequestParam("contentId") int contentId)
			throws JsonProcessingException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController viewVideoQuestion method ::::: ");
		ModelAndView model = new ModelAndView();
		model.addObject("content",
				uploadContentService.findOneContent(contentId));
		model.addObject("questionList",
				questionService.findVideoQuestionByContentId(contentId));
		model.addObject("audioList", new ObjectMapper()
				.writeValueAsString(uploadContentService
						.findAudioByContentId(contentId)));
		model.setViewName("viewVideoQuestion");
		return model;
	}

	/**
	 * This is used for saving audio in ppt.
	 * 
	 * @param file
	 * @param pageNum
	 * @param contentId
	 * @param slideHoldTime
	 * @return Map<String, String>
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savePPTAudio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> savePPTAudio(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "pageNum", required = false) int pageNum,
			@RequestParam(value = "contentId", required = false) int contentId,
			@RequestParam(value = "fileHoldTime", required = false) Integer slideHoldTime)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController savemultiplecontents method ::::: ");
		Map<String, String> map = new HashMap<String, String>();

		User user = (User) request.getSession().getAttribute("userSession");
		if (slideHoldTime != null) {
			/*uploadContentService.savePPTAudio(null, contentId,
					user.getUserId(), slideHoldTime, pageNum);*/
			uploadContentService.savePPTAudioInS3Bukcet(null, contentId,
					user.getUserId(), slideHoldTime, pageNum, user.getSubdomain());
		} else if (file != null) {
			/*uploadContentService.savePPTAudio(file, contentId,
					user.getUserId(), 0, pageNum);*/
			uploadContentService.savePPTAudioInS3Bukcet(file, contentId,
					user.getUserId(), 0, pageNum, user.getSubdomain());
		}
		map.put("Success", "success");
		return map;
	}

	/**
	 * This is used getting content list as suggestion list.
	 * 
	 * @param req
	 * @param sectionId
	 * @param courseId
	 * @return Map<String, Object>
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getContentSuggestionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getContentSuggestionList(
			@ModelAttribute("contentData") Datatable req,
			@RequestParam(value = "sectionId", required = false) Integer sectionId,
			@RequestParam(value = "courseId", required = false) Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController getUploadedContentList method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			result = uploadContentService.getContentSuggestionList(req,
					user.getUserId(), courseId);
		} else {
			result.put("error", "your session has been expired.");
		}
		return result;
	}

	/**
	 * This is used creating mapping of content and section.
	 * 
	 * @param courseId
	 * @param sectionId
	 * @param contentId
	 * @return SectionContent
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/mapsuggestioncontentintosection", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SectionContent mapSuggestionContentIntoSection(
			@RequestParam("courseId") Integer courseId,
			@RequestParam("sectionId") Integer sectionId,
			@RequestParam("contentId") Integer contentId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController mapContentIntoSection method ::::: ");
		String serverPath = request.getRequestURL().toString();
		serverPath = serverPath.substring(0, serverPath.lastIndexOf('/'));
		SectionContent sectionContent = uploadContentService
				.mapSuggestionContentIntoSection(sectionId, contentId,
						serverPath);
		return sectionContent;
	}
}



