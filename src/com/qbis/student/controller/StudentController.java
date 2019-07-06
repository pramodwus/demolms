package com.qbis.student.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.CourseEnum;
import com.qbis.common.EmailSender;
import com.qbis.common.StudentEnum;
import com.qbis.controller.LoginController;
import com.qbis.model.Attempts;
import com.qbis.model.Course;
import com.qbis.model.CourseSession;
import com.qbis.model.Mailsender;
import com.qbis.model.Minute;
import com.qbis.model.Profile;
import com.qbis.model.Section;
import com.qbis.model.SectionAttempt;
import com.qbis.model.SectionContent;
import com.qbis.model.Session;
import com.qbis.model.TestStatus;
import com.qbis.model.User;
import com.qbis.model.UserProfile;
import com.qbis.services.CourseService;
import com.qbis.services.NotificationService;
import com.qbis.services.StudentCourseService;
import com.qbis.services.StudentTestService;
import com.qbis.services.UserService;

/**
 * This is used for handling all requests related to Test in student web portal.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
public class StudentController {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(StudentController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;


	/**
	 * Instance of {@link StudentTestService}
	 */
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private StudentTestService studenttestservice;

	@Autowired
	private UserService userservice;
	/**
	 * Instance of {@link StudentCourseService}
	 */
	@Autowired
	private StudentCourseService studentcourseservice;

	@Autowired
	private CourseService courseService;
 

	/**
	 * This is used for getting test list, review based on action parameter.
	 * 
	 * @param action
	 * @param userTestAttemptId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/studentTest")
	public ModelAndView getPublishedTestList(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "userTestAttemptId", required = false) Integer userTestAttemptId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside StudentTestController in studentTest method :::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userId", user.getUserId());
		if (action.equals("list")) {
			model.addObject("totalTest", studenttestservice.countPublishedTest(user.getUserId(), user.getCompanyId(),
					Integer.parseInt(StudentEnum.PUBLISHED.getValue())));
			model.addObject("upcomingTest", studenttestservice.countUpcomingTest(user.getUserId(), user.getCompanyId(),
					Integer.parseInt(StudentEnum.SCHEDULED.getValue())));
			model.setViewName("student/alltest");
		} else if (action.equals("review")) {
			model.addObject("userTestAttemptId", userTestAttemptId);
			model.setViewName("student/reviewtest");
		}
		return model;
	}

	/**
	 * This is used for getting course list.
	 * 
	 * @param action
	 * @param courseId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/studentCourse")
	public ModelAndView getPublishedCourseList(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "courseId", required = false) Integer courseId,
			@RequestParam(value = "view", required = false) String shared,
			@RequestParam(value = "nid", required = false) Integer nid)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside StudentTestController in studentCourse method :::::");
		HttpSession session = request.getSession(false);
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		String userName = user.getFirstName() == null || user.getFirstName().equals("")
				? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();

		if (nid != null) {
			NotificationService.updateNotificationReadStatus(nid, 1);
		}
		if (session == null || session.getAttribute("userSession") == null) {
			String redirectURL = request.getRequestURL()
					+ (request.getQueryString() != null ? "?" + request.getQueryString() : "");
			if (redirectURL.contains("studentCourse?action=coursedetail&courseId=" + courseId + "&view=shared")) {
				session = request.getSession(true);
				String redirectPath = "studentCourse?action=coursedetail&courseId=" + courseId;
				session.setAttribute("redirectURL", redirectPath);
			}
			model.setViewName("login");
		} else {
			if (action.equals("courseList")) {

				model.addObject("totalCourse", studentcourseservice.countPublishedCourses(user.getUserId(),
						user.getCompanyId(), Integer.parseInt(CourseEnum.PUBLISHED.getValue())));
				model.addObject("upcomingCourse", studentcourseservice.countUpcomingCourses(user.getUserId(),
						user.getCompanyId(), Integer.parseInt(CourseEnum.SCHEDULED.getValue())));
				model.addObject("userName", userName);
				model.setViewName("student/studentcourse");
			} else if (action.equals("coursedetail") && courseId != null) {

				Course course = new Course();

				course = studentcourseservice.getCourseDetail(courseId);

				/**
				 * String vcId = request.getSession().getAttribute(
				 * "viewedCourseId") != null ? request.getSession()
				 * .getAttribute("viewedCourseId").toString() : null; if (vcId
				 * == null) { int id =
				 * studentcourseservice.saveCourseViewDetailsByUser(
				 * user.getUserId(), Integer.parseInt(courseId));
				 * request.getSession().setAttribute("viewedCourseId", id); }
				 * 
				 * /* User
				 * user=(User)request.getSession().getAttribute("userSession");
				 * List listtemp = new ArrayList();
				 * if(request.getSession().getAttribute("recentview")==null){
				 * new
				 * StudentCourseService().saveRecentViewCourse(user.getUserId
				 * (),courseId); listtemp.add(courseId);
				 * request.getSession().setAttribute("recentview", listtemp);
				 * }else{ List list = ((List)
				 * request.getSession().getAttribute("recentview"));
				 * if(!list.contains(courseId)){ new
				 * StudentCourseService().saveRecentViewCourse(userId,courseId);
				 * list.add(courseId);
				 * request.getSession().setAttribute("recentview", list); } }
				 */
				model.addObject("userName", userName);
				model.addObject("courseDetail", course);
				model.addObject("courseId", courseId);
				model.setViewName("student/coursedetail");
			}
		}
		return model;
	}

	/**
	 * This is used for getting course id from shared course link and redirect
	 * on next page.
	 * 
	 * @param courseId
	 * @param redirectAttribute
	 * @return
	 */
	@RequestMapping(value = "/previewSharedCourse", method = RequestMethod.GET)
	public ModelAndView redirectOnCourseView(@RequestParam(value = "courseId") String courseId,
			RedirectAttributes redirectAttribute) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (courseId != null && courseId.matches("^-?\\d+$")) {
			if (session != null) {
				User user = (User) request.getSession().getAttribute("userSession");
				if (user == null) {
					session.setAttribute("sharedCourseId", Integer.parseInt(courseId.trim()));
					model.setViewName("login");
				} else {
					redirectAttribute.addAttribute("action", "coursedetail");
					redirectAttribute.addAttribute("courseId", Integer.parseInt(courseId.trim()));
					model.setViewName("redirect:/studentCourse");
				}
			} else {
			}
		}
		return model;
	}

	/**
	 * This is used for getting test id from shared course link and redirect on
	 * next page.
	 * 
	 * @param testId
	 * @param redirectAttribute
	 * @return
	 */
	@RequestMapping(value = "/previewSharedTest", method = RequestMethod.GET)
	public ModelAndView redirectOnAssessmentView(@RequestParam(value = "testId") String testId,
			RedirectAttributes redirectAttribute) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession(false);
		if (testId != null && testId.matches("^-?\\d+$")) {
			if (session != null) {
				User user = (User) request.getSession().getAttribute("userSession");
				if (user == null) {
					session.setAttribute("sharedTestId", Integer.parseInt(testId.trim()));
					model.setViewName("login");
				} else {
					redirectAttribute.addAttribute("testId", Integer.parseInt(testId.trim()));
					model.setViewName("redirect:/studentGivenTest");
				}
			} else {
			}
		}
		return model;
	}

	/**
	 * This is used for given test by user.
	 * 
	 * @param testId
	 * @param nid
	 * @param contentActivityId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/studentGivenTest")
	public ModelAndView givenTest(@RequestParam(value = "testId") Integer testId,
			@RequestParam(value = "nid", required = false) Integer nid,
			@RequestParam(value = "contentActivityId", required = false) Integer contentActivityId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside StudentTestController in studentGivenTest method :::::");
		if (nid != null) {
			NotificationService.updateNotificationReadStatus(nid, 1);
		}
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userId", user.getUserId());
		model.addObject("testId", testId);
		model.addObject("contentActivityId", contentActivityId);
		model.addObject("authorization", ConstantUtil.APPLICATION_ID);
		model.addObject("countAttempt", studenttestservice.getCountAttemptedTestByUser(user.getUserId(), testId));
		model.setViewName("student/instructions");
		return model;
	}

	/**
	 * This is used for getting library list of user.
	 * 
	 * @param action
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/myLibrary")
	public ModelAndView getMyGivenTestDetails(@RequestParam(value = "action", required = false) String action)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside StudentTestController in myLibrary method :::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userId", user.getUserId());
		model.addObject("totalGivenTest", studenttestservice.countTotalLibrary(user.getUserId()));
		model.setViewName("student/mylibrary");
		return model;
	}

	/**
	 * This is used for getting test result.
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewTestResult")
	public ModelAndView viewTestResults() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside StudentTestController in viewTestResult method :::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userId", user.getUserId());
		model.setViewName("student/viewtestresult");
		return model;
	}

	/**
	 * This is used for getting test result.
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewCourseContent")
	public ModelAndView viewCourseContent(@RequestParam(value = "courseId", required = false) String courseId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside StudentTestController in viewTestResult method :::::");
		ModelAndView model = new ModelAndView();
		if (courseId != null && courseId.trim().length() > 0 && StringUtils.isNumeric(courseId)) {
			model.addObject("courseId", courseId);
			model.setViewName("student/viewcoursecontent");
		} else {
			model.setViewName("login");
		}
		return model;
	}

	/**
	 * This is used for sending shared content Link
	 * 
	 * @param email
	 * @param contentPath
	 * @return Boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendSharedContentLink", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean sendSharedLink(@RequestParam(value = "email") String email,
			@RequestParam(value = "contentPath") String contentPath)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		Boolean status = false;
		status = studentcourseservice.shareContentLinkToUser(email, contentPath);
		return status;
	}

	@RequestMapping(value = "/saveAttemptContentQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer saveAttemptContentQuestion(@RequestParam(value = "sectionId") Integer sectionId,
			@RequestParam(value = "attemptCounter", required = false) Integer attemptCounter,
			@RequestParam(value = "questionId") Integer questionId,
			@RequestParam(value = "attemptedAnswer") String attemptedAnswer)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		Integer attemptCount = 0;
		User user = (User) request.getSession().getAttribute("userSession");
		attemptCount = studentcourseservice.saveAttemptContentQuestion(user.getUserId(), sectionId, questionId,
				attemptedAnswer, attemptCounter);
		return attemptCount;
	}

	@RequestMapping("/coursehistory1")
	public ModelAndView getCourseHistory1(Integer courseId, Integer sectionId, Integer attemptId, Boolean isPractice,
			Integer id) {
		logger.log(Level.DEBUG, "Inside StudentTestController in getCourseHistory method :::::" + courseId + sectionId
				+ attemptId + isPractice);
		ModelAndView model = new ModelAndView();
		model.setViewName("student/coursehistory");
		if (courseId != null && sectionId != null) {
			Course course = courseService.getCourseDetails(courseId);
			List<SectionAttempt> attemptList = course.getSectionList().stream()
					.filter(m -> m.getSectionId().intValue() == sectionId).map(m -> m.getAttemptList()).findFirst()
					.get();
			String chapterName = course.getSectionList().stream().filter(m -> m.getSectionId().intValue() == sectionId)
					.map(m -> m.getSectionName()).findFirst().get();
			User user1 = (User) request.getSession().getAttribute("userSession");
			User user = userservice.findOneUser(user1.getEmail());

			String userName = user.getFirstName() == null || user.getFirstName().equals("")
					? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();

			// model.addObject("contentList",contentList);
			// model.addObject("courseName",course.getSectionList().stream().map(m->m.getSectionName()).findFirst().get());
			// model.addObject("course",course);
			model.addObject("id", id);
			model.addObject("userName", userName);
			model.addObject("chapterName", chapterName);
			model.addObject("courseName", course.getCourseName());
			model.addObject("attemptList", attemptList);
			model.addObject("sectionId", sectionId);
			model.addObject("courseId", course.getCourseId());

		}

		return model;
	}

	@RequestMapping("/coursehistory")
	public ModelAndView getCourseHistory(Integer courseId, Integer sectionId, Integer attemptId, Boolean isPractice,
			Integer id) {
		logger.log(Level.DEBUG, "Inside StudentTestController in getCourseHistory method :::::" + courseId + sectionId
				+ attemptId + isPractice);
		ModelAndView model = new ModelAndView();
		model.setViewName("student/coursehistory");
		if (courseId != null && sectionId != null) {
			
		
			User user1 = (User) request.getSession().getAttribute("userSession");
			System.out.println(user1.getEmail());
			User user = userservice.findOneUser(user1.getEmail());
			List<Integer> isStatus=courseService.videoCompleted(courseId,sectionId,user.getUserId());
			System.out.println("================"+isStatus);
			Course course = courseService.getCourseDetails(courseId, sectionId, null,user.getUserId());
			System.out.println(course);
			String userName = user.getFirstName() == null || user.getFirstName().equals("")
					? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
		   Double r=studenttestservice.getTestPerformance(user.getUserId(), sectionId);
		   if(r.isNaN())
		   {
			   model.addObject("result",0);
		   }
		   else
		   {
			   model.addObject("result",studenttestservice.getTestPerformance(user.getUserId(), sectionId));
		   }
		//	model.addObject("sessionId",courseService.getSessionId(sectionId));
		    model.addObject("overall_Performance",studenttestservice.getoverallperformance(courseId, user.getUserId()));
			model.addObject("isStatus",isStatus);
			model.addObject("id", id);
			model.addObject("userName", userName);
			model.addObject("course", course);
			model.addObject("sectionId", sectionId);
			model.addObject("courseId", course.getCourseId());
			Section section = course.getSectionList().get(0);
			model.addObject("section", section);
		}

		return model;
	}


	@RequestMapping("/coursechapter")
	public ModelAndView getCourseChapter(Integer courseId, Integer sectionId, Integer attemptId, Boolean isPractice,
			Integer id) {
		logger.log(Level.DEBUG, "Inside StudentTestController in getCourseChapter method :::::" + courseId + sectionId
				+ attemptId + isPractice);

		Course course = studentcourseservice.getSectionDetails(courseId);
		User user1 = (User) request.getSession().getAttribute("userSession");
		Minute minutes = courseService.getTotalViewSession(courseId, user1.getUserId());
		User user = userservice.findOneUser(user1.getEmail());
		Map<String, Object> map = studentcourseservice.getSectionDetails1(courseId, user1.getUserId());
		Course course1 = (Course) map.get("CourseDetail");
		List<Session> sessionList = course1.getSectionList().stream().map(m -> m.getSession()).findFirst().get();
		System.out.println("sessionList/////////////" + sessionList);
		List<Attempts> attempts = sessionList.stream().map(m -> m.getAttempts()).findFirst().get();
		System.out.println("attempts/////////////" + attempts);
		List<Section> sections = studentcourseservice.getChaptersForGraph(courseId, user.getUserId());
		System.out.println("section ki size"+sections.size());
		// System.out.println("list of
		// sessions............."+session.get(0).getTestAttempts());
		boolean flag = studentcourseservice.getStudentSubscriptionStatus(courseId, user.getUserId());
		String userName = user.getFirstName() == null || user.getFirstName().equals("")
				? user.getEmail().substring(0, user.getEmail().indexOf('@'))
				: user.getFirstName();
		ModelAndView model = new ModelAndView();
		System.err.println("=======eetrtr=====" + sections);
		model.addObject("courses", studentcourseservice.getPublishedCourseListeluminate(user.getUserId(), 1));
		model.addObject("overallchartdata",studentcourseservice.getOverallChapterMarks(user.getUserId(),courseId));
        model.addObject("sessionId", courseService.getSessionId(courseId));
		model.addObject("performancetillnow", studenttestservice.getPerformanceTillNow(user.getUserId()));
		model.addObject("flag", flag);
		model.addObject("detail", minutes);
		model.addObject("id", id);
		model.addObject("courseId", courseId);
		model.addObject("userName", userName);
		model.addObject("courseName", course.getCourseName());
		model.addObject("course", course.getSectionList());
		model.addObject("chartdata", sections);
		model.setViewName("student/studentcoursechapter");
		return model;
	}





	@RequestMapping("/coursevideo")
	public ModelAndView getCourseVideo(Integer courseId, Integer sectionId, Integer attemptId, Boolean isPractice,
			Integer contentId, Integer id) {
		logger.log(Level.DEBUG, "Inside StudentTestController in getCourseVideo method :::::" + courseId + sectionId
				+ attemptId + isPractice);
		ModelAndView model = new ModelAndView();
		model.setViewName("student/coursevideo1");
		if (courseId != null && attemptId != null && contentId != null && sectionId != null) {
			Course course = courseService.getCourseDetails(courseId);
			List<SectionAttempt> attemptList = course.getSectionList().stream()
					.filter(m -> m.getSectionId().intValue() == sectionId).map(m -> m.getAttemptList()).findFirst()
					.get();
			String chapterName = course.getSectionList().stream().filter(m -> m.getSectionId().intValue() == sectionId)
					.map(m -> m.getSectionName()).findFirst().get();
			List<SectionContent> contents = attemptList.stream().map(m -> m.getContentList()).findFirst().get();
			String videourl = contents.stream().filter(m -> m.getContentId() == contentId).map(m -> m.getContentPath())
					.findFirst().get();
			String contantName = contents.stream().filter(m -> m.getContentId() == contentId)
					.map(m -> m.getContentName()).findFirst().get();
			// List<SectionContent>
			// contentList=attemptList.stream().filter(m->m.getAttemptId()==attemptId
			// &&
			// m.getIsPractice()==isPractice).map(m->m.getContentList()).findFirst().get();
			// System.out.println("======="+contentList);
			User user1 = (User) request.getSession().getAttribute("userSession");
			User user = userservice.findOneUser(user1.getEmail());

			String userName = user.getFirstName() == null || user.getFirstName().equals("")
					? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
			// model.addObject("contentList",contentList);
			// model.addObject("courseName",course.getSectionList().stream().map(m->m.getSectionName()).findFirst().get());
			// model.addObject("course",course);
			
			model.addObject("id", id);
			model.addObject("sectionId", sectionId);
			model.addObject("contentId", contentId);
			model.addObject("contantName", contantName);
			model.addObject("videoUrl", videourl);
			model.addObject("userName", userName);
			model.addObject("chapterName", chapterName);
			model.addObject("courseName", course.getCourseName());
			model.addObject("attemptList", attemptList);
			model.addObject("courseId", course.getCourseId());

		}
		return model;

	}

	@RequestMapping("/viewcoursecontent")
	public ModelAndView viewCourseContent(@RequestParam("courseId") Integer courseId,
			@RequestParam("sectionId") Integer sectionId, @RequestParam(value="attemptId",required=false) Integer attemptId,
			@RequestParam(value="sessionId",required=false) Long sessionId, @RequestParam(value="contentId",required=false) Integer contentId,Integer practiceId
			,@RequestParam(value="videoTime",required=false)String videoTime)
			throws JsonProcessingException {
		logger.log(Level.DEBUG, "Inside StudentTestController in viewCourseContent method :::::");
		ModelAndView model = new ModelAndView();
		Section section=new Section();
        User user1 = (User) request.getSession().getAttribute("userSession");
		Course course = courseService.getCourseDetails(courseId, sectionId, attemptId,user1.getUserId());
		System.out.println("-------------------"+new ObjectMapper().writeValueAsString(course));
		User user = userservice.findOneUser(user1.getEmail());
		Integer isStatus=courseService.videoCompleted(courseId,sectionId,attemptId,sessionId,contentId,user1.getUserId());
		List<Integer> isStatusVideo=courseService.videoCompleted(courseId,sectionId,user1.getUserId());
		String userName = user.getFirstName() == null || user.getFirstName().equals("")
				? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
	//String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTUsImV4cCI6MTU2NjU1NTUxMSwiaXNfc3RhZmYiOmZhbHNlfQ.tjXf5qirtzAiI8VcR_FrhFatDV5dx7B9KBvahMzladI";

     	String token = CommonUtil.getTokenFromCookie(request);
		Map<String, Object> map= studentcourseservice.getSectionDetails1(courseId,user1.getUserId());
		Course course1= (Course)map.get("CourseDetail"); 
	
		if(sessionId !=0)
	    {

			Integer parentId=course1.getSectionList().stream().filter(m->m.getSectionId().intValue() == sectionId && m.getIsChapterTest() == 1).map(m->m.getParentId()).findFirst().orElse(null);
		    
				//System.out.println("===="+studentcourseservice.getUserSessionLisenceDetails1(user1.getUserId(),Integer.parseInt((sessionId.toString()))));
			    model.addObject("lisence",studentcourseservice.getUserSessionLisenceDetails1(sessionId,user.getUserId()));
				model.addObject("token",token);
				model.addObject("goodreviewcount", studentcourseservice.getGoodReviewCount(contentId));
				model.addObject("badreviewcount", studentcourseservice.badReviewCount(contentId));
				model.addObject("excellentreviewcount", studentcourseservice.excellentReviewCount(contentId));
				model.addObject("isStatusVideo",isStatusVideo);
				model.addObject("isStatus",isStatus);
				model.addObject("userId",user1.getUserId());
				model.addObject("userName", userName);
				model.addObject("courseId", courseId);
				model.addObject("parentId",parentId);
				model.addObject("sectionId", sectionId);
				model.addObject("course", course);
				model.addObject("videoTime",videoTime);
				model.addObject("sessionId",sessionId);
				model.addObject("attemptId",attemptId);
				model.addObject("contentId",contentId);
				List<SectionAttempt> attemptList = course.getSectionList().stream()
						.filter(m -> m.getSectionId().intValue() == sectionId).map(m -> m.getAttemptList()).findFirst()
						.get();
				
				section= course.getSectionList().get(0);
	    
		List<CourseSession> sessionlist=   attemptList.stream().filter(m->m.getAttemptId().intValue()==attemptId).map(m->m.getSessionList()).findAny().get();
		 String sessionName=    sessionlist.stream().filter(m->m.getId()== sessionId).map(m->m.getName()).findFirst().get();
		model.addObject("alllicense",studentcourseservice.getUserSessionLisenceDetailsForAllSessions(user.getUserId(), sessionId));
		model.addObject("section", section);
	    //System.err.println("aiddddd="+section.getAttemptList().get(0).getContentList().get(0).getContentId());
		model.addObject("sessionName",sessionName);
	    }
	    else
	    {
	    	Integer parentId=course1.getSectionList().stream().filter(m->m.getIsChapterTest()==0 && m.getIsPractice() ==1).map(m->m.getParentId()).findFirst().get();
			  System.err.println("parent id"+parentId);     
	    	section.setSectionName(course.getSectionList().stream().map(m->m.getSectionName()).findFirst().get());
	    	  course1.getSectionList().stream().filter(m->m.getSectionId() == sectionId && m.getIsPractice() == 1).map(m->m.getAttempts()).findFirst().get();
	    	   model.addObject("lisence",studentcourseservice.getUserSessionLisenceDetails1(sessionId,user1.getUserId()));
	    	 	model.addObject("token",token);
				model.addObject("isStatusVideo",isStatusVideo);
				model.addObject("isStatus",isStatus);
				model.addObject("userId",user1.getUserId());
				model.addObject("userName", userName);
				model.addObject("courseId", courseId);
				model.addObject("parentId",parentId);
				model.addObject("sectionId", sectionId);
				model.addObject("course", course);
				model.addObject("videoTime",videoTime);
				model.addObject("practicetest",true);
				model.addObject("sessionId",sessionId);
				model.addObject("attemptId",attemptId);
				model.addObject("contentId",contentId);
				model.addObject("sessionName","Practice Test");
				model.addObject("section", section);
				
	    	
	    	
	    }
		model.setViewName("student/coursevideo");
		model.addObject("activeSessionId", sessionId);
		model.addObject("validatedCount", studentcourseservice.getEmailValidationFlag(user1.getUserId()));
		System.out.println(courseId+""+sectionId+""+attemptId+""+sessionId+""+contentId+""+user1.getUserId());
		SectionContent activeContent = courseService.getSectionContentForAttempt(courseId, sectionId, attemptId,
				sessionId, contentId, user1.getUserId());
		model.addObject("activeContent", activeContent);
		System.out.println("-------------------"+new ObjectMapper().writeValueAsString(activeContent));
		if (activeContent != null && "TEST".equals(activeContent.getContentType())) {
			model.addObject("activeContentJSON", new ObjectMapper().writeValueAsString(activeContent));
		}
		return model;
	}


	
	
	@RequestMapping("/myprofile")
	public ModelAndView editProfile(Integer userId) {
		ModelAndView model = new ModelAndView();
		User user1 = (User) request.getSession().getAttribute("userSession");
		User user = userservice.findOneUser(user1.getEmail());
		System.err.println("============================"+user.getImage());
		HttpSession session=request.getSession(false);
		session.setAttribute("userSession", user);
	
		
		model.addObject("user", user);
		String userName = user.getFirstName() == null || user.getFirstName().equals("")
				? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
 	String token = CommonUtil.getTokenFromCookie(request);
//		String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTU3LCJleHAiOjE1NjQzOTQ4MjAsImlzX3N0YWZmIjpmYWxzZX0.s4NwuZne29GDmEPnHXLZ9f4jhrp-86wmheRj5KqhXJ0";
				List<Integer> courseId=studentcourseservice.getCourseId();
	     AuthenticationService  authenticationService=new AuthenticationService();	  
		 UserProfile profile=authenticationService.getAllProfile(token);
		profile.getFirst_name();
		  model.addObject("profile",profile);
	     /*
		 * // Map<String, Object> map=
		 * studentcourseservice.getSectionDetails1(courseId,user1.getUserId()); Course
		 * course1= (Course)map.get("CourseDetail");
		 */
		
		List<Course> courses=studentcourseservice.getPublishedCourseListeluminate(user.getUserId(), 1);
		boolean flag=studentcourseservice.getCourseSubscribe(user.getUserId(),courseId);
		model.addObject("flag",flag);
		model.addObject("courses",courses);
		model.addObject("userName", userName);
		model.addObject("token",token);
		model.setViewName("student/myprofile");
		return model;
	}

	@RequestMapping("/userassesment")
	public ModelAndView getAssesmentPage() {
		logger.log(Level.DEBUG, "Inside StudentTestController in getAssesment method :::::");
		ModelAndView model = new ModelAndView();
		model.setViewName("student/assesment");
		return model;
	}

	@RequestMapping("/assesmentreview")
	public ModelAndView getReviewPage() {
		logger.log(Level.DEBUG, "Inside StudentTestController in getAssesment method :::::");
		ModelAndView model = new ModelAndView();
		model.setViewName("student/assesment_review");
		return model;
	}

@RequestMapping("/comingsoon")
	public ModelAndView getComingsoon() {
		logger.log(Level.DEBUG, "Inside StudentTestController in getAssesment method :::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		String userName = user.getFirstName() == null || user.getFirstName().equals("")
				? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
				model.addObject("userName",userName);
		model.setViewName("student/comingsoon");
		return model;
	}

@RequestMapping("/newdashboard")
public ModelAndView newDashboard(Integer courseId, Integer sectionId,Integer id) {
	logger.log(Level.DEBUG, "Inside StudentTestController in newDashboard method :::::");
	 ModelAndView model = new ModelAndView();
	model.setViewName("student/coursehistory");
	if (courseId != null && sectionId != null) {
		
	
		User user1 = (User) request.getSession().getAttribute("userSession");
		System.out.println(user1.getEmail());
		User user = userservice.findOneUser(user1.getEmail());
	
	//	Course course = courseService.getCourseDetails(courseId, sectionId, null);
		//System.out.println(course);
		String userName = user.getFirstName() == null || user.getFirstName().equals("")
				? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
	   Double r=studenttestservice.getTestPerformance(user.getUserId(), sectionId);
	 
	   Map<String, Object> map= studentcourseservice.getSectionDetails1(courseId,user1.getUserId());
	   Course course= (Course)map.get("CourseDetail");      
	   List<Session> sessionList=course.getSectionList().stream().filter(m->m.getSectionId().intValue()==sectionId).map(m->m.getSession()).findFirst().get();
	    		if(r.isNaN())
	   {
		   model.addObject("result",0);
	   }
	   else
	   {
		   model.addObject("result",studenttestservice.getTestPerformance(user.getUserId(), sectionId));
	   }
	    List<Attempts> attempts=sessionList.stream().map(m->m.getAttempts()).findFirst().orElse(null);	
	    List<TestStatus> listTestStatus=courseService.checkStatusTestSubmit(user1.getUserId(), courseId, sectionId,sessionList);
	    System.out.println("all test status"+listTestStatus);
	    boolean alltestStatus=courseService.checkAllTestStatus(user1.getUserId(), courseId, sectionId,sessionList);
	    System.out.println("All test status"+alltestStatus);
	    boolean allAttempt1Status=courseService.checkAllAttempt1TestStatus(user1.getUserId(), courseId, sectionId,sessionList);
	    System.out.println("allAttempt1Status"+allAttempt1Status);
	//	model.addObject("sessionId",courseService.getSessionId(sectionId));
	   // model.addObject("sectionDetails",studentcourseservice.getSessionDetailsForApi(sectionId));
	    model.addObject("testStatus",listTestStatus);
	    model.addObject("allAttempt1Status",allAttempt1Status);
	    model.addObject("alltestStatus",alltestStatus);
	    model.addObject("overall_Performance",studenttestservice.getoverallperformance(courseId, user.getUserId()));
		model.addObject("id", id);
		model.addObject("sectionname",course.getSectionList().stream().map(m->m.getSectionName()).findFirst().get());
		model.addObject("userName", userName);
		model.addObject("course", course);
		model.addObject("sectionId", sectionId);
		model.addObject("attempts",attempts);
		model.addObject("section",course.getSectionList());
		model.addObject("courseId", course.getCourseId());
		System.out.println("courseId"+courseId);
	//	Section section = course.getSectionList().get(0);
	//	model.addObject("section", section);
	    model.addObject("sessionList",sessionList);
	model.setViewName("student/newdashboard");
	}
	return model;
	
   }

    

@RequestMapping("/gettestsummary")
public ModelAndView getTestSummary(Integer courseId,Integer sectionId,Integer attemptId,Long sessionId,Integer contentId,Integer id) throws Exception {
	logger.log(Level.DEBUG, "Inside StudentTestController in getTestSummary method :::::");
	ModelAndView model = new ModelAndView();
	
	User user1 = (User) request.getSession().getAttribute("userSession");
	User user = userservice.findOneUser(user1.getEmail());
	
	Map<String, Object> map= studentcourseservice.getSectionDetails1(courseId,user.getUserId());
	Course course1= (Course)map.get("CourseDetail"); 
	if(sessionId != 0)
	{
		Integer parentId=course1.getSectionList().stream().filter(m->m.getSectionId().intValue() == sectionId && m.getIsChapterTest() == 1).map(m->m.getParentId()).findFirst().orElse(null);
		model.addObject("parentId",parentId);	
	}
	else
	{
		Integer parentId=course1.getSectionList().stream().filter(m->m.getIsChapterTest()==0 && m.getIsPractice() ==1).map(m->m.getParentId()).findFirst().get();	
		model.addObject("parentId",parentId);
	}
	String userName = user.getFirstName() == null || user.getFirstName().equals("")
			? user.getEmail().substring(0, user.getEmail().indexOf('@')) : user.getFirstName();
		
		
			model.addObject("userId",user.getUserId());
			model.addObject("userName", userName);
			model.addObject("courseId", courseId);
			model.addObject("sectionId", sectionId);
		
			model.addObject("sessionId",sessionId);
			model.addObject("attemptId",attemptId);
			model.addObject("contentId",contentId);
	model.addObject("testsummary",courseService.getTestSummary(user.getUserId(), courseId, sectionId, attemptId, sessionId, contentId));

	model.setViewName("student/testsummary");
	model.addObject("activeSessionId", sessionId);
	return model;
	
	
	
   } 

@RequestMapping("/getVideoPlayer")

	public ModelAndView gethlsplayer()
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("hlsplayer.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "/practicetest")

	public ModelAndView getPractice(Integer courseId, Integer sectionId, Integer id) {
		logger.log(Level.DEBUG, "Inside StudentTestController in newDashboard method :::::");
		ModelAndView model = new ModelAndView();
		if (courseId != null && sectionId != null) {

			User user1 = (User) request.getSession().getAttribute("userSession");
			System.out.println(user1.getEmail());
			User user = userservice.findOneUser(user1.getEmail());
           
//	Course course = courseService.getCourseDetails(courseId, sectionId, null);
//System.out.println(course);
			String userName = user.getFirstName() == null || user.getFirstName().equals("")
					? user.getEmail().substring(0, user.getEmail().indexOf('@'))
					: user.getFirstName();
			Double r = studenttestservice.getTestPerformance(user.getUserId(), sectionId);

			Map<String, Object> map = studentcourseservice.getSectionDetails1(courseId, user1.getUserId());
			Course course = (Course) map.get("CourseDetail");
			Integer parentId=course.getSectionList().stream().filter(m->m.getSectionId().intValue() == sectionId && m.getIsPractice() == 1).map(m->m.getParentId()).findFirst().orElse(null);
			List<Session> sessionList = course.getSectionList().stream()
					.filter(m -> m.getSectionId().intValue() == sectionId).map(m -> m.getSession()).findFirst().get();
			if (r.isNaN()) {
				model.addObject("result", 0);
			} else {
				model.addObject("result", studenttestservice.getTestPerformance(user.getUserId(), sectionId));
			}
			List<Attempts> attempts = sessionList.stream().map(m -> m.getAttempts()).findFirst().orElse(null);;
			List<TestStatus> listTestStatus = courseService.checkStatusTestSubmit(user1.getUserId(), courseId,
					sectionId, sessionList);
			System.out.println("all test status" + listTestStatus);
			boolean alltestStatus = courseService.checkAllTestStatus(user1.getUserId(), courseId, sectionId,
					sessionList);
			System.out.println("All test status" + alltestStatus);
			boolean allAttempt1Status = courseService.checkAllAttempt1TestStatus(user1.getUserId(), courseId, sectionId,
					sessionList);
			System.out.println("allAttempt1Status" + allAttempt1Status);
//	model.addObject("sessionId",courseService.getSessionId(sectionId));
// model.addObject("sectionDetails",studentcourseservice.getSessionDetailsForApi(sectionId));
			model.addObject("testStatus", listTestStatus);
			model.addObject("allAttempt1Status", allAttempt1Status);
			model.addObject("alltestStatus", alltestStatus);
			model.addObject("overall_Performance",
					studenttestservice.getoverallperformance(courseId, user.getUserId()));
			model.addObject("id", id);
			model.addObject("sectionname",
					course.getSectionList().stream().map(m -> m.getSectionName()).findFirst().get());
			model.addObject("userName", userName);
			model.addObject("course", course);
			model.addObject("sectionId", sectionId);
			model.addObject("attempts", attempts);
			model.addObject("parentId",parentId);
			model.addObject("section", course.getSectionList());
			model.addObject("courseId", course.getCourseId());
			System.out.println("courseId" + courseId);
//	Section section = course.getSectionList().get(0);
//	model.addObject("section", section);
			model.addObject("sessionList", sessionList);
			model.setViewName("student/practicetest");
		}
		return model;

	}
	
	@RequestMapping(value="/saveprofilepicture",method = RequestMethod.POST)
	@ResponseBody
	public String saveProfilepicture(@RequestParam("file") MultipartFile file )
	{
	String imageurl=null;
	Profile profile=new Profile();
	//String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNTYzNjkwMzM3LCJpc19zdGFmZiI6ZmFsc2V9.cJ-cKrL9_2Q-reYppK_I8d1iA6olFYTkYLDFV8Bx6Jo"; //CommonUtil.getTokenFromCookie(request);
	//System.out.println("token=="+token);
	String token = CommonUtil.getTokenFromCookie(request);
	Integer userIdOfLms= new AuthenticationService().getUserProfileUserId(token);	
	profile=authService.getUserShortProfile(token);
	imageurl=authService.uploadProfilePicture(token,profile.getUser_id(), file);
	if(imageurl!=null)
	{
	studentcourseservice.updateProfilepicture(userIdOfLms, imageurl);
	}

	System.out.println("profile"+profile.getFull_name());
	System.out.println("Image url"+imageurl);

	return imageurl;


	}
	
	@RequestMapping(value = "/validateEmail", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean validateEmail(@RequestBody User user,HttpServletRequest request)
	{
		boolean flag=false;
		System.out.println("validate mail send ho gaya");
		String token = CommonUtil.getTokenFromCookie(request);
		try {
			System.out.println(user.getEmail());
			EmailSender emailSender=new EmailSender();
			Mailsender mailsender=new Mailsender();
			mailsender.setMail(user.getEmail());
			mailsender.setBody("https://qalms.eluminate.in/api/getmailvalidated?token="+token);
			mailsender.setSubject("Eluminate Validation");
			emailSender.sendEmaildoubt(mailsender);
			HttpSession session =request.getSession();
			/*
			 * session.setAttribute("token",token); session.setAttribute("email",
			 * user.getEmail());
			 */
			flag=true;
			new LoginController().logout(response);
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		return flag;
		
	}

}


