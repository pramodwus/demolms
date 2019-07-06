package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.CourseEnum;
import com.qbis.common.CourseType;
import com.qbis.common.EmailSender;
import com.qbis.common.LicenseEnum;
import com.qbis.model.Attempts;
import com.qbis.model.ContentAttemptRelation;
import com.qbis.model.Course;
import com.qbis.model.License;
import com.qbis.model.Mailsender;
import com.qbis.model.Section;
import com.qbis.model.SectionContent;
import com.qbis.model.Session;
import com.qbis.model.SessionFeedback;
import com.qbis.model.Test;
import com.qbis.model.User;
import com.qbis.services.CourseService;
import com.qbis.services.NotificationService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.StudentCourseService;
import com.qbis.services.TestService;

@Controller
public class CourseController {
	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private static final Logger logger = Logger
			.getLogger(CourseController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * Instance of {@link CourseService}
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * Instance of {@link TestService}
	 */
	@Autowired
	private TestService testService;

	/**
	 * Instance of {@link StudentCourseService}
	 */
	@Autowired
	private StudentCourseService studentCourseService;

	/**
	 * Instance of {@link QbisLicenseService}
	 */
	@Autowired
	private QbisLicenseService qbisLicenseService;

	/**
	 * This is used to getting created course list on based of user's id
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/courselist")
	public ModelAndView getCourseList() throws NoSuchAlgorithmException,
			InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller courselist method :::::  ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject(
				"totalPublishedCourse",
				studentCourseService.countPublishedTestByUserId(
						Integer.parseInt(CourseEnum.PUBLISHED.getValue()),
						user.getUserId()));
		model.addObject(
				"totalScheduledCourse",
				studentCourseService.countPublishedTestByUserId(
						Integer.parseInt(CourseEnum.SCHEDULED.getValue()),
						user.getUserId()));
		model.addObject(
				"totalDraftedCourse",
				studentCourseService.countPublishedTestByUserId(
						Integer.parseInt(CourseEnum.DRAFTED.getValue()),
						user.getUserId()));
		model.setViewName("course");
		return model;
	}

	/**
	 * This is used getting list of course.
	 * 
	 * @param courseStatus
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/courselistwithpagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> courseListWithPagination(
			@RequestParam("courseStatus") Integer courseStatus,
			@RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {
		User user = (User) request.getSession().getAttribute("userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courselist", courseService.getPublishedDraftedCourse(
				user.getUserId(), courseStatus, limit, offset));
		return map;
	}

	/**
	 * This is used for redirecting to create course page.
	 * 
	 * @param courseId
	 *            (optional)
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/createCourse", method = RequestMethod.GET)
	public ModelAndView createEditCourse(
			@RequestParam(value = "courseId", required = false) Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller createeditcourse method :::::  ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		if (courseId != null && courseId > 0) {
			Course course = courseId != null ? (courseService.getCourseDetails(
					courseId, user.getUserId())) : new Course();
			String url = request.getRequestURL().toString();
			System.out.println(url);
			url = url.substring(0, url.lastIndexOf('/'));
			model.addObject("sectionlist",
					courseService.getSectionList(courseId, url));
			model.addObject("course", course);
		}

		model.setViewName("createcourse");
		return model;
	}

	/**
	 * This is used for saving and updating the details of course.
	 * 
	 * @param file
	 * @param req
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
	public ModelAndView saveCourseDetails(
			@RequestParam("imageName") MultipartFile file,
			@ModelAttribute("courseform") Course req, BindingResult result,
			RedirectAttributes redirectAttributes)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException, ParseException {
		logger.log(Level.DEBUG,
				"Inside Course Controller saveCourse method :::::  ");
		User user = (User) request.getSession().getAttribute("userSession");
		ModelAndView model = new ModelAndView();
		req.setUserId(user.getUserId());
		if (req.getCourseId() != null) {
			courseService.updateCourseDetails(req);
			logger.log(Level.DEBUG,
					"Inside Course Controller COURSE UPDATED :::::  ");
		} else {
			req.setCourseType(Integer.parseInt(CourseType.SYSTEM_COURSE
					.getValue()));
			courseService.saveCourseDetails(req);
			logger.log(Level.DEBUG,
					"Inside Course Controller COURSE SAVED :::::  ");
		}
		if (file.getOriginalFilename().length() != 0) {
			courseService.uploadCourseIcon(file, req.getCourseId(),
					user.getUserId());
		}
		redirectAttributes.addAttribute("courseId", req.getCourseId());
		model.setViewName("redirect:/addEditCourseMaterial");
		return model;
	}

	/**
	 * This is used to getting the list of language and level for course.
	 * 
	 * @param action
	 * @return
	 */
	@RequestMapping("/getlevelandlanguage")
	public @ResponseBody String getLevelAndlanguageList(
			@RequestParam(value = "action") String action)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller getlevelandlanguage method :::::  ");
		String str = "";
		if (action.equals("levelList")) {
			str = courseService.getCourseLevelList();
		} else {
			str = courseService.getCourseLanguageList();
		}
		return str;
	}

	/**
	 * This is used for getting course's details and its section's content for
	 * showing content's purpose.
	 * 
	 * @param courseId
	 * @param isPublish
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/courseViewController")
	public ModelAndView viewCourseContent(
			@RequestParam("courseId") Integer courseId,
			@RequestParam(value = "isPublish") Integer isPublish,
			@RequestParam(value = "nid", required = false) Integer nid)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG, "Inside Course Controller method ::::: ");
		if (nid != null) {
			NotificationService.updateNotificationReadStatus(nid, 1);
		}
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		Course course = courseService.getCourseDetailsById(courseId,
				user.getUserId(), isPublish);
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf('/'));
		List<Section> seclist = courseService.getSectionList(courseId, url);
		model.addObject("sectionlist", seclist);
		model.addObject("course", course);
		model.setViewName("viewcoursedetails");
		return model;
	}

	/**
	 * This is used for redirecting on course preview.
	 * 
	 * @param courseId
	 * @param isPublish
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/coursepreview", method = RequestMethod.GET)
	public ModelAndView coursePreviewDetail(
			@RequestParam("courseId") Integer courseId,
			@RequestParam(value = "isPublish") Integer isPublish,
			@RequestParam("contentId") Integer contentId,
			@RequestParam(value = "origin", required = false) boolean origin)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller savecoursesectionmaterial method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		Course course = courseService.getCourseDetailsById(courseId,
				user.getUserId(), isPublish);
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf('/'));
		if (course.getCourseId() != null) {
			List<Section> seclist = courseService.getSectionList(courseId, url);
			ObjectMapper object = new ObjectMapper();
			String courseJSON = object.writeValueAsString(seclist);
			model.addObject("course", course);
			model.addObject("sectionList", courseJSON);
			model.addObject("contentId", contentId);
			model.addObject("origin", origin);
		}
		model.setViewName("coursepreview");
		return model;
	}

	/**
	 * This is used for getting course's details and its section's content for
	 * adding new content purpose.
	 * 
	 * @param courseId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping("/addEditCourseMaterial")
	public ModelAndView addEditCourseMaterial(
			@RequestParam(value = "courseId") Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,NullPointerException,
			IOException {
		logger.log(Level.DEBUG, "Inside Course Controller ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		int isPublished=courseService.getIsPublishedByCourseId(courseId);
		Course course = new Course();
		
		if(isPublished==1) {
			course = courseService.getCourseDetailsById(courseId,
					user.getUserId(),
					Integer.parseInt(CourseEnum.PUBLISHED.getValue()));
			}
		if(isPublished==0) {
		course = courseService.getCourseDetailsById(courseId,
				user.getUserId(),
				Integer.parseInt(CourseEnum.DRAFTED.getValue()));
		}
		Integer sectionId=null;
		List<Section> sectionlist = new ArrayList<Section>();
		List<Section> chapterlist = new ArrayList<Section>();
		List<ContentAttemptRelation> contentSectionRel = new ArrayList<ContentAttemptRelation>();
	    List<Attempts> listOfAttempts=new ArrayList<Attempts>();
	    List<Session> sessionList=new ArrayList<Session>(); 
	    List<Session> sessionListForChapterTest=new ArrayList<Session>(); 
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf('/'));
		System.out.println("courseid............."+course.getCourseId());
		if (course.getCourseId() != null) {
			sectionlist = courseService.getSectionList(courseId, url);
			listOfAttempts=courseService.getAttemptList(null);
			contentSectionRel=courseService.getContentAttemptRelation();
			sessionList=courseService.getSessionList(courseId);
			sessionListForChapterTest=courseService.getSessionListForChapterTest(courseId);
			chapterlist=courseService.getChapterTest(courseId);
			if(sectionlist!=null && sectionlist.size()>0)
			{
				Section s=sectionlist.get(sectionlist.size()-1);
				sectionId=s.getSectionId();
				}
		}
		sectionlist.forEach(m->{
		         SectionContent[]  SectionContent=m.getSectionContent();
		         for(int i=0;i<SectionContent.length;i++)
		         {
		        	 System.out.println("sessionId="+SectionContent[i].getSessionId());
		         }
		});
		List<SessionFeedback> list=studentCourseService.listOfFeedback(courseId);
		/*
		 * for(int i=0;i<=list.size();i++) {
		 * System.err.println(list.get(i).getSecondryEmail()); }
		 */
	   //list.forEach(m->{System.out.println(m.getSecondryEmail());});
        //model.addObject("chapterTest",courseService.saveChapterTestDEtails(course));
		model.addObject("sessionChapter",sessionListForChapterTest);
	    model.addObject("chapterTestList",chapterlist);
		model.addObject("sessionList",sessionList);
		model.addObject("course", course);
		model.addObject("sectionlist", sectionlist);
		model.addObject("attemptlist", listOfAttempts);
		model.addObject("ContentAttempt", contentSectionRel);
		model.addObject("sectionId",sectionId);
		model.addObject("isPublished",isPublished);
		model.addObject("feedbackList",studentCourseService.listOfFeedback(courseId));
		
		model.setViewName("addcoursematerial");
		return model;
	}

	
	
	@RequestMapping(value = "/deleteSession", method = RequestMethod.GET)
	@ResponseBody
	public  String deleteSession(@RequestParam("sessionId") Integer sessionId) {
		boolean status=false;
		String response="false";
		if (sessionId != null) {
			System.out.println("sessionId="+sessionId);
			status = courseService.deleteSession(sessionId);
			if(status)
				response= "true";
			else
				response="false";
		}
	return response;

	}

	@RequestMapping(value = "/updateSession", method = RequestMethod.POST)
	public @ResponseBody String updateSession(Session session) {
		boolean status = true;
		String response="false";
		if (session.getSessonId() != null) {
		 status = courseService.editSession(session);
			if(status)
				response= "true";
			else
				response="false";
		}
		return response;
	}
	

	/**
	 * This is used for adding and updating the section in a particular course.
	 * 
	 * @param req
	 * @param result
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savecoursesection", method = RequestMethod.POST)
	public @ResponseBody String createCourseSection(
			@ModelAttribute("") Section req, BindingResult result)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller savecoursesection Method");
		String str = "";
		boolean status = false;
		int flag = 0; // O means saved
		if (req.getSectionId() != null) {
			status = courseService.updateSectionDetails(req);
			flag = 1; // for update
		} else {
			status = courseService.saveSectionDetails(req);
			//System.out.println("======================>"+req.getCourseId()+req.getSectionId()+req.getIsChapterTest());
		}
		if (status) {
			str = flag + "####" + req.getSectionId() + "####"
					+ req.getSectionName();
		}
		return str;
	}
	
	
	
	
	@RequestMapping(value = "/savecoursesession", method = RequestMethod.POST)
	public @ResponseBody String createCourseSession(
			@ModelAttribute("") Section req, BindingResult result,@RequestParam(value="sectionId") Integer sectionId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller savecoursesection Method");
		String str = "";
		boolean status = false;
		System.out.println("======================="+req.getSessionName());
		System.out.println("======================="+sectionId);
		System.out.println("========================"+req.getIsChapterTest());
		System.out.println("sessionName"+req.getCourseId());
		System.out.println("sectionId "+req.getSectionId());
		
		/*
		 * if(req.getSectionName().contains("Test")) { System.out.println("null"); }
		 * 
		 * System.out.println("========================"+req.getIsChapterTest());
		 */
	    status = courseService.saveSessionDetails(req,sectionId);
	
		
			if (status) {
				str = status + "####" + req.getSectionId() + "####"
						+ req.getSessionName();
			}
			return str;
	
		}
	

	/**
	 * This is used for deleting section with its content or only a particular
	 * section's content.
	 * 
	 * @param sectionId
	 * @param contentId
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deletecoursecontent", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteSectionOrContent(
			@RequestParam(value = "sectionId", required = false) Integer sectionId,
			@RequestParam(value = "contentId", required = false) Integer contentId,
			@RequestParam(value = "mapSectionId", required = false) Integer mapSectionId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller deletecoursecontent method ::::: ");
		boolean successfullydelete = false;
		if (sectionId != null) {
			successfullydelete = courseService.deleteCourseSection(sectionId);
		}
		if (contentId != null) {
			successfullydelete = courseService.deleteCourseContent(contentId,
					mapSectionId);
		}
		return successfullydelete;
	}

	/**
	 * This is used for adding or updating the content of course.
	 * 
	 * @param req
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savecoursesectionmaterial", method = RequestMethod.POST)
	public @ResponseBody String saveSectionMaterial(
			@ModelAttribute("sectionContent") SectionContent req)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller savecoursesectionmaterial method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		
	String response = courseService.saveCourseContentOperation(req,user.getUserId());
		
		
			
		return response;
	}

	/**
	 * This is used for updating the order of content.
	 * 
	 * @param contentOrder
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatecontentorder")
	public @ResponseBody String updateContentOrder(
			@RequestParam(value = "contentDivID") String contentDivID)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller updatecontentorder method ::::: ");
		String str = "";
		String idArray[] = contentDivID.split(",");
		int orderNumber = 1;
		for (String id : idArray) {
			int contentId = Integer
					.parseInt(id.substring(id.lastIndexOf("_") + 1));
			courseService.updateContentOrder(contentId, orderNumber);
			orderNumber++;
		}

		return str;
	}

	/**
	 * This is used for getting test list for selecting as course content;
	 * 
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/gettestlistajax", method = RequestMethod.GET)
	public @ResponseBody String getTestList() throws NoSuchAlgorithmException,
			InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller gettestlistajax method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		List<Test> list = testService.getTestDetails(user.getUserId());
		ObjectMapper mapper = new ObjectMapper();
		String testJsonList = mapper.writeValueAsString(list);
		return testJsonList;
	}

	/**
	 * This is used for publishing and sharing the course.
	 * 
	 * @param action
	 * @param courseId
	 * @param selectedSection
	 * @param emailList
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/publishedCourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean publishedCourse(
			@RequestParam(value = "action") String action,
			@RequestParam(value = "courseId") Integer courseId,
			@RequestParam(value = "selectedSection", required = false) String[] selectedSection,
			@RequestParam(value = "emailList", required = false) String[] emailList,
			@RequestParam(value = "groupList", required = false) String[] groupList,
			@RequestParam(value = "emailMap", required = false) String emailMap,
			@RequestParam(value = "message", required = false) String message)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller publishedCourse method ::::: ");

		User user = (User) request.getSession().getAttribute("userSession");
		Boolean status = false;
		if (action.equalsIgnoreCase("share")) {
			String path = request.getRequestURL().toString();
			path = path.substring(0, path.lastIndexOf('/'));
			Map<String, Integer> userList = new HashMap<>();
			Map<String, Integer> userListtemp = new HashMap<>();
			ObjectMapper mapper = new ObjectMapper();
			userListtemp = mapper.readValue(emailMap,
					new TypeReference<Map<String, Integer>>() {
					});
			if (groupList.length > 0)
				userList = courseService.getUsersInGroup(groupList, "course");
			userList.putAll(userListtemp);
			courseService.updateSharedCourseStatus(courseId);
			status = courseService.performUserCourseMapping(courseId, userList,
					message, user, path);
		} else {
			if (courseId > 0 && selectedSection != null) {
				status = courseService.savepublishedCourse(courseId,
						selectedSection, user.getUserId());
			}
		}
		return status;
	}

	/**
	 * This is used for rename content title
	 * 
	 * @return Boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saverenametitle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean saveRenameTitle(@RequestBody SectionContent content)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside UploadContentController saveRenameTitle method ::::: ");
		return courseService.saveRenameTitle(content);
	}

	/**
	 * This is used for rename content title
	 * 
	 * @return Boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/savecoursetitle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Course saveCourseTitle(@RequestBody Course course)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller saveCourseTitle method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		return courseService.saveCourseTitle(course, user.getUserId());
	}

	/**
	 * This is used for delete drafted course
	 * 
	 * @return Boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deletedraftedcourse", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteDraftedCourse(
			@RequestParam(value = "courseId", required = false) Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller deletedraftedcourse method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		return courseService.deleteDraftedCourse(courseId, user.getUserId());
	}

	/**
	 * This is used to get all sections list
	 * 
	 * @return section page
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getallsectionlist")
	public ModelAndView getAllSectionList(
			@RequestParam(value = "courseId") Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller getAllSectionList method :::::  ");
		ModelAndView model = new ModelAndView();
		int userId = ((User) request.getSession().getAttribute("userSession"))
				.getUserId();
		model.addObject("sectionlist",
				courseService.getAllSectionList(userId, courseId));
		model.setViewName("sectionlist");
		return model;
	}

	/**
	 * This is used save import sections and its content mapping
	 * 
	 * @param sections
	 *            [] integer array of section id to import
	 * @param course
	 *            id in which section is import
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/importsectioncontents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean importSectionContents(
			@RequestParam("sections") int[] sections,
			@RequestParam("courseId") Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside CourseController importSectionContents method ::::: ");
		courseService.importSectionContents(sections, courseId);
		return true;
	}

	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getdraftedtestcountbycourseid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean getDraftedTestCountByCourseid(
			@RequestParam("id") Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside CourseController getDraftedTestCountByCourseid method ::::: ");

		return courseService.getDraftedTestCountByCourseid(courseId);
	}

	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "checkCourseScheduleTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean checkCourseSchedule(
			@RequestParam(value = "courseId") Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Test Controller checkSchedule method :::::");
		Boolean status = courseService.checkCourseScheduleTimeStatus(courseId);
		return status;
	}

	/**
	 * Method to check license of organization that it can create new course or
	 * not.
	 * 
	 * @return boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "createNewCourseLicesneValidate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean createNewCourseLicesneValidate()
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Course Controller createNewCourseLicesneValidate method :::::");
		License license = (License) request.getSession().getAttribute(
				"licenseObj");
		int orgId = (Integer) request.getSession().getAttribute("orgId");
		License licenseobj = qbisLicenseService.validateLicense(license, orgId,
				LicenseEnum.MAXALLOWEDCOURSE.getValue());
		return licenseobj.getValid();
	}

	/**
	 * This is used getting setting information of course section.
	 * 
	 * @param courseId
	 * @param sectionId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	@RequestMapping(value = "getCourseSectionSettingInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Section getCourseSectionSettingInfo(
			@RequestParam(value = "courseId") Integer courseId,
			@RequestParam(value = "sectionId") Integer sectionId)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		logger.log(Level.DEBUG,
				"Inside Course Controller getCourseSectionSettingInfo method :::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Section section = courseService.getCourseSectionSettingInfo(
				user.getUserId(), courseId, sectionId);
		return section;
	}

	/**
	 * This is used for updating the setting information of course section.
	 * 
	 * @param section
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	@RequestMapping(value = "updateCourseSectionSettingInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean updateCourseSectionSettingInfo(@RequestBody Section section)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		logger.log(Level.DEBUG,
				"Inside Course Controller updateCourseSectionSettingInfo method :::::");
		Boolean isUpdated = courseService
				.updateCourseSectionSettingInfo(section);
		return isUpdated;
	}

	/**
	 * This is used for validating the section's setting.
	 * 
	 * @param courseId
	 * @return map
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	@RequestMapping(value = "checksectionsettingvalidation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> checkSectionSettingValidation(
			@RequestParam(value = "courseId") Integer courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		logger.log(Level.DEBUG,
				"Inside Course Controller checkSectionSettingValidation method :::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Map<String, Object> map = courseService.checkSectionSettingValidation(
				courseId, user.getUserId());
		return map;
	}
	
	@RequestMapping(value = "/saveSessionCheckbox", method = RequestMethod.POST)
	public @ResponseBody String sessionCheckBoxdata(@RequestParam("val") Integer val,
			@RequestParam("sessonId") Integer sessonId,
			@RequestParam("attr") String attr )
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Course Controller sessionCheckBoxdata Method");
		String str = "";
		boolean status = false;
		System.out.println(val+" "+sessonId+" "+attr);
		courseService.saveSessionCheckbox(val,sessonId,attr);
		return str;

	}
	@RequestMapping(value = "/getcheckbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Session> fetch()
			 {
		logger.log(Level.DEBUG,
				"Inside Course Controller createNewCourseLicesneValidate method :::::");
		System.out.println("hello");
		ModelAndView model=new ModelAndView();
		List<Session> list=courseService.fetchData();
		/*
		 * model.addObject("list",list); model.setViewName("addcoursematerial");
		 */		System.out.println("done");
		return list;
	}

	
	@RequestMapping(value="/sendMail" ,method=RequestMethod.POST)
	@ResponseBody
	public void sendMailForFeedback(@RequestBody Mailsender mailsender) throws AddressException, MessagingException
	{
		System.err.println("aagay"+mailsender.getBody());
		
		  EmailSender sender=new EmailSender(); 
		  
		  sender.sendEmaildoubt(mailsender);
		 
	}
	
	

}


