package com.qbis.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.auth.AuthRequest;
import com.qbis.auth.AuthUser;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.CourseEnum;
import com.qbis.common.EmailSender;
import com.qbis.controller.LoginController;
import com.qbis.model.ContentActivity;
import com.qbis.model.Course;
import com.qbis.model.CourseActivity;
import com.qbis.model.Device;
import com.qbis.model.Mailsender;
import com.qbis.model.Question;
import com.qbis.model.SectionContent;
import com.qbis.model.ServiceResult;
import com.qbis.model.ServiceResult.StatusCode;
import com.qbis.model.ServiceResult1;
import com.qbis.model.ServiceResult1.StatusCode1;
import com.qbis.model.SessionFeedback;
import com.qbis.model.Test;
import com.qbis.model.User;
import com.qbis.model.UserProfile;
import com.qbis.model.UserTestAttempt;
import com.qbis.services.CourseService;
import com.qbis.services.DeviceService;
import com.qbis.services.LoginService;
import com.qbis.services.OrganizationService;
import com.qbis.services.ScormCourseService;
import com.qbis.services.StudentCourseService;
import com.qbis.services.StudentTestService;
import com.qbis.services.StudentUserService;
import com.qbis.services.UserService;

/**
 * This is used for performing all operations related to api.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class QbisWebService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(QbisWebService.class);

	/**
	 * Instance of {@link DeviceService}
	 */
	@Autowired
	private DeviceService deviceService;

	@Autowired
	private HttpServletRequest request;
	/**
	 * Instance of {@link StudentUserService}
	 */
	@Autowired
	private StudentUserService studentUserService;

	/**
	 * String email=loginservice.getUserDetailsFromToken(access_token); Instance of
	 * {@link ScormCourseService}
	 */
	@Autowired
	private ScormCourseService scormCourseService;
	/**
	 * Instance of {@link StudentTestService}
	 */
	@Autowired
	private StudentTestService studentTestService;

	/**
	 * Instance of {@link StudentCourseService}
	 */
	@Autowired
	private StudentCourseService studentCourseService;

	@Autowired
	private UserService userservice;

	@Autowired
	LoginService loginService;

	@Autowired
	private HttpServletRequest httpRequest;

	@Autowired
	private CourseService courseService;

	@Autowired
	private HttpServletResponse httpResponse;
	@Autowired
	UserService userService;

	@Autowired
	CourseService courseservice;

	@Autowired
	LoginService loginservice;

	@Autowired
	AuthenticationService authenticationservice;

	/**
	 * This api would be call on initialization of application.
	 * 
	 * @param authorization
	 * @param device_id
	 * @param timestamp
	 * @param device
	 * @return Map<String,Object>
	 */

	@Autowired
	private HttpServletRequest httprequest;

	@RequestMapping(value = "/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> init(@RequestHeader("authorization") String authorization,
			@RequestHeader("device_id") String device_id, @RequestHeader("timestamp") String timestamp,
			@RequestBody Device device) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.log(Level.DEBUG, "");
		if (authorization.equals(ConstantUtil.APPLICATION_ID)) {
			device.setDevice_id(device_id);
			map = deviceService.initialization(device);
		} else {
			map.put("status", 401);
			map.put("msg", "Application id not match");
		}
		return map;
	}

	/**
	 * This is used for purpose of user's registration through application.
	 * 
	 * @param authorization
	 * @param device_id
	 * @param timestamp
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> registerUser(@RequestHeader("authorization") String authorization,
			@RequestHeader("device_id") String device_id, @RequestHeader("timestamp") String timestamp,
			@RequestBody User user) {
		if (authorization.equals(ConstantUtil.APPLICATION_ID)) {
			ResponseEntity<Object> response = studentUserService.appUserRegistrationNew(device_id, user);
			return response;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			HttpHeaders responseHeaders = new HttpHeaders();
			map.put("status", 401);
			map.put("msg", "Application id not match");
			return new ResponseEntity<Object>(map, responseHeaders, HttpStatus.OK);
		}

	}

	/**
	 * This is used for set up password through application.
	 * 
	 * @param authorization
	 * @param device_id
	 * @param timestamp
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value = "/setuppassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> setUpPassword(@RequestHeader("authorization") String authorization,
			@RequestHeader("device_id") String device_id, @RequestHeader("timestamp") String timestamp,
			@RequestBody User user) {
		ResponseEntity<Object> response = studentUserService.setUpPassword(authorization, user);
		return response;
		/*
		 * if (authorization.equals(ConstantUtil.APPLICATION_ID)) {
		 * ResponseEntity<Object> response = studentUserService
		 * .appUserRegistrationNew(device_id, user); return response; } else {
		 * Map<String, Object> map = new HashMap<String, Object>(); HttpHeaders
		 * responseHeaders = new HttpHeaders(); map.put("status", 401); map.put("msg",
		 * "Application id not match"); return new ResponseEntity<Object>(map,
		 * responseHeaders, HttpStatus.OK); }
		 */

	}

	/**
	 * This is used for handling user's login authentication.
	 * 
	 * @param authorization
	 * @param device_id
	 * @param timestamp
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> signIn(@RequestHeader("authorization") String authorization,
			@RequestHeader("device_id") String device_id, @RequestHeader("timestamp") String timestamp,
			@RequestBody User user) {
		if (authorization.equals(ConstantUtil.APPLICATION_ID)) {
			ResponseEntity<Object> response = studentUserService.authenticationLogin(device_id, user);
			return response;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			HttpHeaders responseHeaders = new HttpHeaders();
			map.put("status", 401);
			map.put("msg", "Application id not match");
			return new ResponseEntity<Object>(map, responseHeaders, HttpStatus.OK);
		}

	}

	/**
	 * This is used when user performs log out operation.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logoutUser(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = studentUserService.logoutAppUser(access_token);
		return map;
	}

	/**
	 * api for reseting app user's Password
	 * 
	 * @param authorization
	 * @param device_id
	 * @param timestamp
	 * @param user
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> forgotPassword(@RequestHeader("authorization") String authorization,
			@RequestHeader("device_id") String device_id, @RequestHeader("timestamp") String timestamp,
			@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = studentUserService.changeAppUserPassword(device_id, user);
		return map;
	}

	/**
	 * Get all published Test List Details
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param offset
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/testlist/{userId}/{offset}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> testlist(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId,
			@PathVariable("offset") int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentTestService.getTestList(userId, offset);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * Get all upcoming Test List Details
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param offset
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/upcomingtestlist/{userId}/{offset}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> upcomingtestlist(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId,
			@PathVariable("offset") int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentTestService.getUpcomingTestList(userId, offset);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * Get all published Test List Details without login of user.
	 * 
	 * @param timestamp
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/testlistonskip/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> testlistonskip(@RequestHeader("timestamp") String timestamp,
			@PathVariable("userId") Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = studentTestService.getTestList(userId, 0);
		return map;
	}

	/**
	 * Get Questions for a particular Test.
	 * 
	 * @param access_token
	 * @param testId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/starttest/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getQuestions(@RequestHeader("authorization") String access_token,
			@PathVariable("testId") int testId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token)) {
			map = studentTestService.getQuestionDetail(testId, access_token);
		} else {
			map.put("status", 401);
			map.put("msg", "Operation failed");
		}
		return map;
	}

	/**
	 * Get Questions for a particular Test.
	 * 
	 * @param access_token
	 * @param testId
	 * @param userId
	 * @return Test
	 */
	@RequestMapping(value = "/givetest/{testId}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Test giveTest(@RequestHeader("authorization") String access_token, @PathVariable("testId") int testId,
			@PathVariable("userId") int userId) {
		Test test = new Test();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			test = studentTestService.testDetailforGivenTest(testId, userId);
		} else {
		}
		return test;
	}

	/**
	 * api for Saving Answer JSON.
	 * 
	 * @param test
	 * @return Test
	 */
	@RequestMapping(value = "/saveAnswerJson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Test saveGivenTestJSON(@RequestBody Test test) {
		Test givenTest = studentTestService.saveGivenTestJson(test);
		return givenTest;
	}

	/**
	 * This is used for submitting drafted test.
	 * 
	 * @param access_token
	 * @param test
	 * @return UserTestAttempt
	 */
	@RequestMapping(value = "/submitDraftedTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserTestAttempt submitDraftedTest(@RequestHeader("authorization") String access_token,
			@RequestBody Test test) {
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		userTestAttempt = studentTestService.extractGivenTestData(test.getUserTestAttemptId());
		return userTestAttempt;
	}

	/**
	 * API for getting result of last attempted test and all attempted test.
	 * 
	 * @param access_token
	 * @param userTestAttemptId
	 * @param userId
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getattemptedtestresult/{userTestAttemptId}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTestResult(@RequestHeader("authorization") String access_token,
			@PathVariable("userTestAttemptId") int userTestAttemptId, @PathVariable("userId") int userId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		try {
			if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
				map.put("status", 200);
				map.put("msg", "Result");
				userTestAttempt = studentTestService.getAttemptedTestResult(userTestAttemptId, userId);
				map.put("userTestAttempt", userTestAttempt);
				map.put("testHistory", studentTestService.getAttemptedTestHistory(userTestAttempt, userId));
				map.put("testSummary", studentTestService.getAttemptedTestSummary(userTestAttempt, userId));
			} else {
				map.put("status", 401);
				map.put("msg", "Operation failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * service for get user profile details
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param userId
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userProfile(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token)) {
			map = studentUserService.getAppUserProfile(userId);
		} else {
			map.put("status", 401);
			map.put("msg", "Operation failed");
		}
		return map;

	}

	/**
	 * service for update user profile details
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param user
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> userUpdateProfile(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token)) {
			map = studentUserService.updateAppUserProfile(user);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * This is used for submitting the user's given test details.
	 * 
	 * @param accessToken
	 * @param test
	 * @return
	 */
	@RequestMapping(value = "/submitGivenTest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserTestAttempt saveTest(@RequestHeader("authorization") String accessToken, @RequestBody Test test) {
		UserTestAttempt userTestAttempt = new UserTestAttempt();
		userTestAttempt = studentTestService.submitTestDetails(test);
		return userTestAttempt;
	}

	/**
	 * Get published course list for app side
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param offset
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/courselist/{userId}/{offset}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> courseList(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId,
			@PathVariable("offset") int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.getPublishedCourseList(userId, Integer.parseInt(CourseEnum.PUBLISHED.getValue()),
					offset);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * Get upcoming course list for consumption side
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param offset
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/upcomingcourselist/{userId}/{offset}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> upcomingcourseList(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId,
			@PathVariable("offset") int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.getUpcomingCourseList(userId, Integer.parseInt(CourseEnum.SCHEDULED.getValue()),
					offset);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * get details of a particular course.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/coursedetails/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "*")
	/*
	 * public Map<String, Object> getCourseDetails(@RequestHeader("authorization")
	 * String access_token,
	 * 
	 * @RequestHeader("timestamp") String timestamp, @PathVariable("courseId")
	 * Integer courseId) {
	 */
	public Map<String, Object> getCourseDetails(@PathVariable("courseId") Integer courseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * if (StudentUserService.checkAccessToken(access_token) ||
		 * access_token.equals("Browser")) { map.put("status", 200); map.put("msg",
		 * "CourseResult"); map.put("course",
		 * studentCourseService.getCourseDetail(courseId)); } else { map.put("status",
		 * 401); map.put("msg", "Invalid authorization"); }
		 */
		map.put("course", studentCourseService.getCourseDetail(courseId));
		return map;
	}

	/**
	 * Get published course list for app side
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param courseid
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/sectionlistbycourseid/{courseid}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> sectionListByCourseId(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("courseid") int courseid,
			@PathVariable("userId") int userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
				map = studentCourseService.getSectionList(courseid, userId);
			} else {
				map.put("status", 401);
				map.put("msg", "Invalid authorization");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * API for getting review attempted test.
	 * 
	 * @param access_token
	 * @param userTestAttemptId
	 * @param userId
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/getreviewattemptedtest/{userTestAttemptId}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReviewAttemptedTest(@RequestHeader("authorization") String access_token,
			@PathVariable("userTestAttemptId") int userTestAttemptId, @PathVariable("userId") int userId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
				map.put("status", 200);
				map.put("msg", "Result");
				map.put("review", studentTestService.getAttemptedQuestionAnswerByUser(userTestAttemptId, userId));
			} else {
				map.put("status", 401);
				map.put("msg", "Invalid authorization");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "operation failed");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * This is used for getting all given test's details of user.
	 * 
	 * @param access_token
	 * @param userId
	 * @param offset
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/mylibrary/{userId}/{offset}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserTest(@RequestHeader("authorization") String access_token,
			@PathVariable("userId") int userId, @PathVariable("offset") int offset) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		try {
			if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
				map.put("status", 200);
				map.put("msg", "Result");
				map.put("myLibrary", studentTestService.getMyTestByUserId(userId, offset));
			} else {
				map.put("status", 401);
				map.put("msg", "Invalid authorization");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "operation failed");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * API Get test details by testId
	 * 
	 * @param authorization
	 * @param timestamp
	 * @param testId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/testdetailsbyid/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTestDetailsById(@RequestHeader("authorization") String authorization,
			@RequestHeader("timestamp") String timestamp, @PathVariable("testId") String testId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(authorization) || authorization.equals("Browser")) {
			map.put("status", 200);
			map.put("msg", "TestResult");
			map.put("test", studentTestService.getTestById(testId));
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * <p>
	 * Service for enable enrollment of course by user
	 * </p>
	 * 
	 * @param course , object contains userId,courseId
	 * @return map
	 */
	@RequestMapping(value = "/enrollmentcoursebyuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> enableEnrollment(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody Course course) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.enableEnrollment(course);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * <p>
	 * Service for save note given by user for question at test attempt time
	 * </p>
	 * 
	 * @param question , object contains userId,questionId,testAttemptId,notes
	 * @return map
	 */
	@RequestMapping(value = "/savenotestoquestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> saveNotesToQuestion(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody Question question) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentTestService.saveNotesToQuestion(question);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * <p>
	 * Service for save note given by user for question at test attempt time
	 * </p>
	 * 
	 * @param question , object contains userId,questionId,testAttemptId,notes
	 * @return map
	 */
	@RequestMapping(value = "/getnoteofquestion/{questionId}/{userId}/{attemptId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getNoteOfQuestion(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("questionId") Integer questionId,
			@PathVariable("userId") Integer userId, @PathVariable("attemptId") Integer attemptId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			Question question = new Question();
			question.setQuestionId(questionId);
			question.setUserId(userId);
			question.setUserTestAttemptId(attemptId);
			map = studentTestService.getNoteOfQuestion(question);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * <p>
	 * Service for set favorite question by user at test attempt time
	 * </p>
	 * 
	 * @param question , object contains userId,questionId and favorites status (1
	 *                 for yes,0 for no)
	 * @return map
	 */
	@RequestMapping(value = "/setresetfavoriatequestion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> setResetFavoriateQuestion(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody Question question) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentTestService.setResetFavoriateQuestion(question);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * <p>
	 * Service for set favorite content by user
	 * </p>
	 * 
	 * @param content , object contains userId,contentId and favorites status (1 for
	 *                yes,0 for no)
	 * @return map
	 */
	@RequestMapping(value = "/setresetfavoriatecontent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> setResetFavoriateContent(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody SectionContent content) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.setResetFavoriateContent(content);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * <p>
	 * Service for get notes list by user
	 * </p>
	 * 
	 * @param userId
	 * @return map
	 */
	@RequestMapping(value = "/getallquesnotes/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getAllQuesNotesList(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentTestService.getAllQuesNotesList(userId);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * <p>
	 * Service for get favorite contents list by user
	 * </p>
	 * 
	 * @param userId
	 * @return map
	 */
	@RequestMapping(value = "/getfavoritecontents/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getFavoriteContentsList(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.getFavoriteContentsList(userId);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * <p>
	 * Service for get favorite questions list by user
	 * </p>
	 * studentGivenTest
	 * 
	 * @param userId
	 * @return map
	 */
	@RequestMapping(value = "/getfavoritequestions/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getFavoriteQuestionList(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable("userId") Integer userId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentTestService.getFavoriteQuestionList(userId);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * service for set registrationId of user for push notifications.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param user
	 * @return map
	 */
	@RequestMapping(value = "/registrationpushtoken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> setRegisterationId(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (access_token.equals(ConstantUtil.APPLICATION_ID)) {
			if (!StudentUserService.checkRegistrationId(user.getUserId())) {
				map = studentUserService.setRegisterationId(user);
			}

		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * <p>
	 * Service for save abuse report given by user for course content
	 * </p>
	 * 
	 * @param content , object contains userId,reportId,abuseReport
	 * @return map NotWorking
	 */
	@RequestMapping(value = "/saveabusereportoncontent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> saveAbuseReportonContent(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody SectionContent content) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.saveAbuseReportonContent(content);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;

	}

	/**
	 * This is used updating the course view end date.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param courseActivity
	 * @return map
	 */
	@RequestMapping(value = "/updateCourseViewEndDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updateCourseViewEndDate(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody CourseActivity courseActivity) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.updateCourseViewEndDate(courseActivity);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * <p>
	 * Service for save view content activity of user
	 * </p>
	 * 
	 * @param content , object contains userId,contentId,courseId
	 * @return map
	 */
	@RequestMapping(value = "/saveViewContentActivity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> saveViewContentActivity(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody ContentActivity contentActivity) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.saveViewContentActivity(contentActivity);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	@RequestMapping(value = "/saveViewContentActivity1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> saveViewContentActivity1(@RequestHeader("authorization") String access_token,
			@RequestBody ContentActivity contentActivity) {
		logger.log(Level.DEBUG, "Inside Student Course Service in saveViewContentActivity1 method::::::");
		Integer userId = new AuthenticationService().getUserProfileUserId(access_token);
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (userId != null) {

			Integer status = studentCourseService.getCompleteStatus(contentActivity, userId);
			studentCourseService.updateVideoStatus(contentActivity, userId);
			System.out.println("status==============saveViewContentActivity1" + status);
			if (status == 0) {
				map = studentCourseService.saveViewContentActivity1(contentActivity, userId);
			}
			/*
			 * // if(status == 1) // { // studentCourseService.updateViewContentActivity1();
			 * // } //
			 */ else
				map.put("status", 201);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	@RequestMapping(value = "/updateresumevideo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object updateResumeVideo(@RequestHeader("authorization") String access_token,
			@RequestBody ContentActivity contentActivity) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		System.err.println("update time" + contentActivity.getResumeTime());
		Integer userId = new AuthenticationService().getUserProfileUserId(access_token);
		if (userId != null) {
			boolean status = studentCourseService.updateVideoStatus1(contentActivity, userId);
			if (status) {
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", status);
			} else {
				return new ServiceResult1(StatusCode1.ERROR, "Error", status);
			}
		} else {
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", null);
		}
	}

	/**
	 * This is used updating the end date time for course content.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param content
	 * @return map
	 */
	@RequestMapping(value = "/updateCourseContentViewEndDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updateCourseContentViewEndDate(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @RequestBody ContentActivity ContentActivity) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.updateCourseContentViewEndDate(ContentActivity);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * This is used updating the end date time for course content.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param content
	 * @return map WORKING
	 */
	@RequestMapping(value = "/updateattemptedtestidincontentactivity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updateAttemptedTestIdInContentActivity(
			@RequestHeader("authorization") String access_token, @RequestHeader("timestamp") String timestamp,
			@RequestBody ContentActivity ContentActivity) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.updateAttemptedTestIdInContentActivity(ContentActivity);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * This is used getting attempt list of a particular course.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param courseId
	 * @param userId
	 * @return map WORKING
	 */
	@RequestMapping(value = "/getCourseAttemptDetails/{courseId}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getCourseAttemptDetails(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable(value = "courseId") Integer courseId,
			@PathVariable(value = "userId") Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.getCourseAttemptDetails(userId, courseId);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * This is used getting details of passed attempt of user if there is any quiz
	 * in this section.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param userId
	 * @param courseId
	 * @param sectionId
	 * @return WORKING
	 */
	@RequestMapping(value = "sectionvalidationforquizmanadatory/{userId}/{courseId}/{sectionId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> sectionValidationforQuERRORizManadatory(
			@RequestHeader("authorization") String access_token, @RequestHeader("timestamp") String timestamp,
			@PathVariable(value = "userId") Integer userId, @PathVariable(value = "courseId") Integer courseId,
			@PathVariable(value = "sectionId") Integer sectionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = studentCourseService.sectionValidationforQuizManadatory(userId, courseId, sectionId);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	/**
	 * This is used getting scorm course data based on course id.
	 * 
	 * @param access_token
	 * @param timestamp
	 * @param userId
	 * @param courseId
	 * @return map WORKING
	 */
	@RequestMapping(value = "/getscormcoursedata/{userId}/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getScormCourseData(@RequestHeader("authorization") String access_token,
			@RequestHeader("timestamp") String timestamp, @PathVariable(value = "userId") Integer userId,
			@PathVariable("courseId") Integer courseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StudentUserService.checkAccessToken(access_token) || access_token.equals("Browser")) {
			map = scormCourseService.extractInfoFromXML(userId, courseId);
		} else {
			map.put("status", 401);
			map.put("msg", "Invalid authorization");
		}
		return map;
	}

	@RequestMapping(value = "/authlogin", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getScormCourseData() throws JsonProcessingException {
		AuthRequest request = new AuthRequest();
		AuthUser user = new AuthUser();
		// user.setEmail("prabhatiitbhu@gmail.com");
		// user.setPassword("01Pass1234");
		request.setUser(user);
		new AuthenticationService().login(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		return map;
	}

	@RequestMapping(value = "/authRegister", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAuthRegistered() throws JsonProcessingException {
		AuthRequest request = new AuthRequest();
		AuthUser user = new AuthUser();
		// user.setEmail("shivansh.pandey521@gmail.com");
		// user.setPassword("01Pass1234");
		request.setUser(user);
		new AuthenticationService().signup(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		return map;
	}

	@RequestMapping(value = "/authupdateProfile", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updateProfile() throws JsonProcessingException {
		AuthRequest request = new AuthRequest();
		AuthUser user = new AuthUser();
		/*
		 * user.setFirst_name("S"); user.setLast_name("s");
		 * user.setContact_info("1212"); user.setImage("a.jpg");
		 * user.setAddress("GURUGdsRAM"); request.setUser(user); new
		 * AuthenticationService().updateUserProfile(request);
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		return map;

	}

	@RequestMapping(value = "/getProfile", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProfile() throws JsonProcessingException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();

		AuthRequest request = new AuthRequest();
		boolean flag = new AuthenticationService().getUserProfile(request);
		if (flag)
			map.put("status", "success");
		else
			map.put("status", "false");
		return map;
	}

	@RequestMapping(value = "/signupforusers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServiceResult regUser(@RequestBody User user) {
		String orgName = CommonUtil.getOrgIdFromURL(httprequest);

		user.setOrgId(OrganizationService.getOrganizationId(orgName));
		System.out.println("=========================>>" + user.getOrgId());

		boolean flag = userservice.addNewUser(user);
		if (flag)
			return new ServiceResult(StatusCode.SUCCESS, flag);
		else
			return new ServiceResult(StatusCode.ERROR, flag);
	}

	@RequestMapping(value = "/getCourseDetails/{courseId}")
	@ResponseBody
	public Map<String, Object> getCourse(@PathVariable(value = "courseId") Integer courseId) {
		Map<String, Object> map = studentCourseService.getAttemptDetailsForApi(courseId);
		return map;
	}

	@RequestMapping(value = "/getCourseSessionDetails/{courseId}")
	@ResponseBody
	public Map<String, Object> getCourseSessionDetails(@PathVariable(value = "courseId") Integer courseId) {
		User user = (User) httpRequest.getSession().getAttribute("userSession");
		// System.out.println("========="+user.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("course", studentCourseService.getSectionDetails(courseId));
		return map;
	}

	@RequestMapping(value = "/getCourseSessionDetails1/{courseId}/{userId}")
	@ResponseBody
	public Map<String, Object> getCourseSessionDetails1(@PathVariable(value = "courseId") Integer courseId,
			@PathVariable(value = "userId") Integer userId) {

		Map<String, Object> map = studentCourseService.getSectionDetails1(courseId, userId);
		return map;
	}

	@RequestMapping(value = "/gettestdetails/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getTestDetails(@PathVariable("testId") String testId) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("test", studentTestService.getTestById(testId));
		/*
		 * } else { map.put("status", 401); map.put("msg", "Invalid authorization"); }
		 */
		return map;
	}

	@RequestMapping(value = "/eligible/{sessionId}/{userId}")
	@ResponseBody
	public Map<String, Object> getSessionLisence(@PathVariable("sessionId") Integer sessionId,
			@PathVariable("userId") Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionLisence", studentCourseService.getUserSessionLisenceDetails(userId, sessionId));
		if (map.get("userId") == null) {
			// map.put("status", 401); map.put("msg", "Invalid authorization");
		}
		return map;

	}

	@RequestMapping(value = "/eligible1/{sessionId}/{userId}")
	@ResponseBody
	public Map<String, Object> getSessionLisence1(@PathVariable("sessionId") Long sessionId,
			@PathVariable("userId") Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionLisence", studentCourseService.getUserSessionLisenceDetails1(sessionId, userId));
		if (map.get("userId") == null) {
			// map.put("status", 401); map.put("msg", "Invalid authorization");
		}
		return map;

	}

	@RequestMapping(value = "/allsessionsonuser/{sessionId}/{userId}")
	@ResponseBody
	public Map<String, Object> getSessionLisenceForAllSessions(@PathVariable("sessionId") Long sessionId,
			@PathVariable("userId") Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionLisence", studentCourseService.getUserSessionLisenceDetailsForAllSessions(userId, sessionId));
		if (map.get("userId") == null) {
			// map.put("status", 401); map.put("msg", "Invalid authorization");
		}
		return map;

	}

	@RequestMapping(value = "/getAllCourseApi")
	@ResponseBody
	public Object getAllCourseDetails(@RequestHeader(value = "Authorization", required = false) String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (token != null) {
			Integer userId = new AuthenticationService().getUserProfileUserId(token);
			boolean flag = userService.checkUserIdExists(userId);
			// Integer userId=0;
			if (flag == true) {
				map.put("Courses", studentCourseService.getAllPublishedCourse(userId));
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			}

			else {
				map.put("Courses", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Invalid UserId", map);
			}

		} else {
			map.put("Courses", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}
	}

	@RequestMapping(value = "/getUserId/")
	@ResponseBody
	public Object getAllCourseDetails1(@RequestHeader(value = "Authorization", required = false) String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (token != null) {
			System.out.println("token" + token);
			Integer userId = new AuthenticationService().getUserProfileUserId(token);
			if (userId != null) {
				map.put("userId", userId);
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			} else {
				map.put("userId", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", map);
			}
		} else {
			map.put("userId", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}
	}

	@RequestMapping(value = "/getAllSection/{courseId}/{userId}")
	@ResponseBody
	public Object getAllSectionDetails(@PathVariable(value = "courseId") Integer courseId, @PathVariable Integer userId,
			@RequestHeader(value = "Authorization", required = false) String access_token) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (access_token != null) {
			boolean flag = userService.checkUserIdExists(userId);
			boolean courseFlag = courseservice.checkCourseExists(courseId);
			if (flag == true && courseFlag == true) {
				map.put("userId", null);
				return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
			}
		}
		return access_token;
	}

	@RequestMapping(value = "/getAllSection/{courseId}")
	@ResponseBody
	public Object getAllSectionDetails(@PathVariable(value = "courseId") Integer courseId,
			@RequestHeader(value = "Authorization", required = false) String access_token) {
		Integer userId = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if (access_token != null) {
			userId = new AuthenticationService().getUserProfileUserId(access_token);

			boolean courseFlag = courseservice.checkCourseExists(courseId);
			if (userId != null && courseFlag == true) {
				map.put("chapterdetail", studentCourseService.getAllSectionDetailForApis(courseId, userId));
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			} else {
				map.put("chapterdetail", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", map);
			}
		} else {
			map.put("chapterdetail", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}
	}

	/*
	 * @RequestMapping(value =
	 * "/getAllSessionDetails/{courseId}/{sectionId}/{sessionId}")
	 * 
	 * @ResponseBody public Map<String, Object> getSessionDetails(@PathVariable
	 * Integer courseId,@PathVariable Integer sectionId,@PathVariable Integer
	 * sessionId) { Integer userId = 0; Map<String, Object> map =
	 * studentCourseService.getSessionDetail(courseId,sectionId,sessionId); return
	 * map; }
	 */

	@RequestMapping(value = "/getSectionDetails/{sectionId}")
	@ResponseBody
	public Object getSectionDetailsNew(@PathVariable(value = "sectionId") Integer sectionId,
			@RequestHeader(value = "Authorization", required = false) String access_token) {
		// Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if (access_token != null) {
			userId = new AuthenticationService().getUserProfileUserId(access_token);
			if (userId != null) {

				map.put("sections", studentCourseService.getAllSectionDetailsNew(sectionId, userId));
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);

			}

			else {
				map.put("sections", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", map);
			}
		}

		else {
			map.put("sections", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);

		}

	}

	@RequestMapping(value = "/setPalyerCookie")
	@ResponseBody
	public Object setPalyerCookie(@RequestHeader(value = "Authorization", required = true) String access_token) {
		authenticationservice.getCookieDataForPlayer(httpResponse, access_token);
		return "sucess";
	}

	@RequestMapping(value = "/testdetail/{courseId}/{sectionId}/{attemptId}/{sessionId}/{contentId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getCourseDetails(@RequestHeader(value = "Authorization", required = false) String token,
			@PathVariable("courseId") Integer courseId, @PathVariable("sectionId") Integer sectionId,
			@PathVariable("attemptId") Integer attemptId, @PathVariable("sessionId") Long sessionId,
			@PathVariable("contentId") Integer contentId)throws JsonProcessingException {

		logger.debug("Inside QbisWebService in getCourseDetails api :::::::courseId = " + courseId);
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = new AuthenticationService().getUserProfileUserId(token);

		if (userId != null) {
			SectionContent response = courseService.getSectionContentForAttempt(courseId, sectionId, attemptId,
					sessionId, contentId, userId);
			System.out.println("-------------------"+new ObjectMapper().writeValueAsString(response));
			map.put("testdetail", response);
			if (response != null) {
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			}
			return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", null);
		} else {
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", null);
		}
	}

	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object updateProfileUser(@RequestHeader("authorization") String access_token, @RequestBody UserProfile req)
			throws Exception {
		logger.log(Level.DEBUG, "Inside QbisWebService updateProfileUser method ::::: ");
		String response = "false";
		boolean flag = false;
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		// req.setUserId(user.getUserId());
		Map map = new HashMap<>();
		if (access_token != null) {
			String emailId = loginService.getUserDetailsFromToken(access_token);
			System.out.println("email============" + emailId);
			if (emailId.equals(req.getEmail())) {
				AuthRequest authRequest = new AuthRequest();
				AuthUser authUser = new AuthUser();

				authUser.setFirst_name(req.getFirst_name());
				authUser.setLast_name(req.getLast_name());
				authUser.setImage("");
				authUser.setAddress(req.getAddress());
				authUser.setContact_info(req.getContact_info());

				authRequest.setUser(authUser);
				flag = new AuthenticationService().updateUserProfile(authRequest, access_token, emailId);
				System.out.println("flag" + flag);
				if (flag) {

					user = userservice.findOneUser(emailId);
					String userName = user.getFirstName() == null || user.getFirstName().equals("")
							? user.getEmail().substring(0, user.getEmail().indexOf('@'))
							: user.getFirstName();

					model.addObject("name", userName);
					HttpSession session = request.getSession(false);
					session.setAttribute("userSession", user);
					return new ServiceResult1(StatusCode1.SUCCESS, "Success", null);

				} else {
					return new ServiceResult1(StatusCode1.SERVERERROR, "ERROR", null);
				}
			} else {
				return new ServiceResult1(StatusCode1.SERVERERROR, false);
			}

		} else {
			return new ServiceResult1(StatusCode1.NOTFOUND, null);
		}
//		model.addObject("user",user);
//		model.addObject("flag",flag);
//		model.setViewName("student/myprofile");
	}

	@RequestMapping(value = "giveuserFeedback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object saveFeedback(@RequestHeader(value = "authorization", required = false) String token,
			@RequestBody SessionFeedback feedback) {
		logger.log(Level.DEBUG, "Inside Qbis Web Service  saveFeedback method ::::: ");
		System.err.println("feedback===" + feedback.getIsBad());
		Map<String, Object> map = new HashMap<String, Object>();
		Integer userId = null;
		System.out.println("content_id is" + feedback.getContentId());
		System.out.println("token=======" + token);
		// token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTA5LCJleHAiOjE1NjY3MTUzNTIsImlzX3N0YWZmIjpmYWxzZX0.VgFoJXAbmvmSD9-9mRctCrZmv3AVt_aB34VLqhnKcvI";
		if (token != null) {
			userId = new AuthenticationService().getUserProfileUserId(token);

			if (userId != null) {
				studentCourseService.smileFeedback(feedback);
				map.put("feedbackDetails", studentCourseService.getSmileDataForContent(feedback));
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			} else {
				map.put("feedbackDetails", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", null);
			}
		} else {
			map.put("feedbackDetails", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}

	}

	@RequestMapping(value = "getuserFeedback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getFeedback(@RequestHeader(value = "authorization", required = false) String token,
			@RequestBody SessionFeedback feedback) {
		System.err.println("aagay");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(token);
		if (token != null) {
			Integer userId = new AuthenticationService().getUserProfileUserId(token);
			System.err.println("user===" + feedback);
			if (userId != null) {

				map.put("feedbackDetails", studentCourseService.getSmileDataForContent(feedback));
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			} else {
				map.put("feedbackDetails", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", null);
			}
		} else {
			map.put("feedbackDetails", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);

		}

	}

	@RequestMapping(value = "getInstruction/{testId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getInstruction(@RequestHeader(value = "authorization", required = false) String token,
			@PathVariable String testId) {

		System.err.println("aagay");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(token);
		if (token != null) {
			Integer userId = new AuthenticationService().getUserProfileUserId(token);

			if (userId != null) {

				Test test = studentTestService.getTestById(testId);
				String html = "<dt>#1 &nbsp&nbsp&nbsp This test is based on <strong>" + test.getTestDesc()
						+ "</strong> </dt><br/>"
						+ "<dt class='form-group'>#2 &nbsp&nbsp&nbsp All Questions are compulsory.</dt><br/>"
						+ "<dt class='form-group'>#3 &nbsp&nbsp&nbsp You will not be able to <strong>PAUSE</strong> the test.</dt><br/>"
						+ "<dt class='form-group'>#4 &nbsp&nbsp&nbsp This test may contain multiple choice or fill in the blank type of Questions. No marks will be awarded for unattempted questions.</dt><br/>"
						+ "<dt class='form-group'>#5 &nbsp&nbsp&nbsp This test has <strong>NO</strong> negative marking.</dt>";
				map.put("result", html);
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			} else {
				map.put("result", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", map);
			}
		} else {
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}

	}

	@RequestMapping(value = "getPreInstruction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getPreInstruction(@RequestHeader(value = "authorization", required = false) String token,
			@RequestBody ContentActivity contentActivity) {

		System.err.println("aagay");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(token);
		if (token != null) {

			Integer userId = new AuthenticationService().getUserProfileUserId(token);
			SectionContent response = courseService.getSectionContentForAttempt(contentActivity.getCourseId(),
					contentActivity.getSectionId(), contentActivity.getAttemptId(),
					new Long(contentActivity.getSessionId()), contentActivity.getContentId(), userId);

			if (userId != null && response != null) {

				Test test = studentTestService.getTestById(response.getTest().getTestId().toString());
				String html = "<dt>#1 &nbsp&nbsp&nbsp This test is based on <strong>" + test.getTestDesc()
						+ "</strong> </dt><br/>"
						+ "<dt class='form-group'>#2 &nbsp&nbsp&nbsp All Questions are compulsory.</dt><br/>"
						+ "<dt class='form-group'>#3 &nbsp&nbsp&nbsp You will not be able to <strong>PAUSE</strong> the test.</dt><br/>"
						+ "<dt class='form-group'>#4 &nbsp&nbsp&nbsp This test may contain multiple choice or fill in the blank type of Questions. No marks will be awarded for unattempted questions.</dt><br/>"
						+ "<dt class='form-group'>#5 &nbsp&nbsp&nbsp This test has <strong>NO</strong> negative marking.</dt>";
				map.put("result", html);
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			} else {
				map.put("result", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", map);
			}
		} else {
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}

	}

	@RequestMapping(value = "/getstudentchartapi/{courseId}/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getChart(@PathVariable("courseId") Integer courseId, @PathVariable("userId") Integer userId) {
		System.err.println("inside api");
		// String url=null;
		ModelAndView map = createChartpage(courseId, userId);
		// map.setViewName("userchartforapi");
		return map;

	}

	@RequestMapping(value = "chart")
	public ModelAndView createChartpage(Integer courseId, Integer userId) {
		ModelAndView model = new ModelAndView();
		try {
			System.err.println("inside model");
			model.addObject("chartdata", studentCourseService.getChaptersForGraph(courseId, userId));
			model.addObject("overallchartdata", studentCourseService.getOverallChapterMarks(userId, courseId));

			model.setViewName("student/userchartforapi");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return model;

	}
	
	@RequestMapping("/getmailvalidatedfrommobile")
	public Object getMailvalidatedMobile(@RequestParam("token") String token, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Map<String, Object> map =new HashMap<String, Object>();
		if (token != null) {
			String email = loginService.getUserDetailsFromToken(token);
			Integer userId = new AuthenticationService().getUserProfileUserId(token);
			if(userId!=null)
			{
				studentCourseService.getUserValidated(userId, email);
				map.put("Details", "done");
				return new ServiceResult1(StatusCode1.SUCCESS, "Success", map);
			}
			else {
				map.put("feedbackDetails", null);
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", null);
				
			}
	}
		else {
			map.put("feedbackDetails", null);
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", map);
		}
		
	}

	@RequestMapping("/getmailvalidated")
	public String getMailvalidated(@RequestHeader("authorization")String token) throws IOException {
		
		// String email=(String) session.getAttribute("email");
		String email = loginService.getUserDetailsFromToken(token);
		Integer userId = new AuthenticationService().getUserProfileUserId(token);
		studentCourseService.getUserValidated(userId, email);
		ModelAndView map = getValidatedLoginpage();
		return email;

	}

	public ModelAndView getValidatedLoginpage()

	{
		ModelAndView model = new ModelAndView();

		new LoginController().logout(httpResponse);

		model.setViewName("student/comingsoon");
		return model;
	}
	
	
	
	@RequestMapping(value = "/validateEmailFromMobile", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object validateEmailFromMobile(@RequestHeader("authorization")String token,@RequestBody User user,HttpServletRequest request,HttpServletResponse response)
	{
		boolean flag=false;
		
		if(token!=null)
		{
			Integer userId=new AuthenticationService().getUserProfileUserId(token);
			if(userId!=null)
			{
				user.setToken(token);
			flag=studentCourseService.sendValidationEmailFromMObile(user, request, response);
			return new ServiceResult1(StatusCode1.SUCCESS, "Success");
			}
			else
			{
				return new ServiceResult1(StatusCode1.NOTFOUND, "Not Found", null);
			}
			
			
		}
		

		else {
			return new ServiceResult1(StatusCode1.UNAUTHORIZED, "Invalid Token", null);
		}
		
}
}
