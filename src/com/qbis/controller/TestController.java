package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.LicenseEnum;
import com.qbis.common.Status;
import com.qbis.model.License;
import com.qbis.model.Question;
import com.qbis.model.Section;
import com.qbis.model.Session;
import com.qbis.model.Test;
import com.qbis.model.User;
import com.qbis.services.ConfigService;
import com.qbis.services.CourseService;
import com.qbis.services.NotificationService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.StudentTestService;
import com.qbis.services.TestService;

/**
 * Controller class for Test related operation.
 * 
 * @author Ankur Kumar.
 *
 */
@Controller
public class TestController {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(TestController.class);

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * Instance of {@link TestService}
	 */
	@Autowired
	private TestService testService;

	/**
	 * Instance of {@link CourseService}
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * Instance of {@link StudentTestService}
	 */
	@Autowired
	private StudentTestService studentTestService;

	/**
	 * Instance of {@link QbisLicenseService}
	 */
	@Autowired
	private QbisLicenseService qbisLicenseService;
	/**
	 * Instance of {@link ConfigService}
	 */
	@Autowired
	private ConfigService configService;

	/**
	 * Method for getting test list based on user id .
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/testList")
	public ModelAndView testList(@RequestParam(value = "testId", required = false) Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller testList method  ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		ModelAndView model = new ModelAndView();
		if (testId != null) {
			testService.deleteTest(testId, user.getUserId());
		}
		model.addObject("totalPublishedTest", studentTestService
				.countPublishedTestByUserId(Integer.parseInt(Status.PUBLISHED.getValue()), user.getUserId()));
		model.addObject("totalScheduledTest", studentTestService
				.countPublishedTestByUserId(Integer.parseInt(Status.SCHEDULED.getValue()), user.getUserId()));
		model.addObject("totalDraftedTest", studentTestService
				.countPublishedTestByUserId(Integer.parseInt(Status.DRAFTED.getValue()), user.getUserId()));
		model.setViewName("test");
		return model;
	}

	/**
	 * This is used getting list of test.
	 * 
	 * @param testStatus
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/testlistwithpagination", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> testListWithPagination(@RequestParam("testStatus") Integer testStatus,
			@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset) {
		User user = (User) request.getSession().getAttribute("userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("testlist", testService.getTestPublishedDraftedList(user.getUserId(), testStatus, limit, offset));
		return map;
	}

	/**
	 * This is used for delete the test data.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deletedraftedtest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean deleteDraftedTest(@RequestParam(value = "testId") Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		boolean isDeleted = false;
		logger.log(Level.DEBUG, "Inside Test Controller deleteDraftedTest method  ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		if (testId != null) {
			isDeleted = testService.deleteTest(testId, user.getUserId());
		}
		return isDeleted;
	}

	/**
	 * This is used for add and edit the test content.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/addEditTest", method = RequestMethod.GET)
	public ModelAndView addEditTest(@RequestParam(value = "testId", required = false) Integer testId,
			@RequestParam(value = "courseId", required = false) Integer courseId,
			@RequestParam(value = "sectionId", required = false) Integer sectionId,
			@RequestParam(value = "contentId", required = false) Integer contentId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller addEditTest method  ::::: ");
		ModelAndView model = new ModelAndView();
		
		User user = (User) request.getSession().getAttribute("userSession");
		if (testId != null) {
			Test test = testService.getTestDetailsforEdit(testId, user.getUserId());
			model.addObject("test", test);
		}
		model.addObject("chapters",configService.getSectionNames(courseId));
		model.addObject("acts",configService.getSessionNames(sectionId));
		
		model.addObject("testTags",
				configService.getConfigTags(new Object[] { "board", "session", "class", "subject", "chapter", "act" }));
		model.setViewName("addedittest");
		return model;
	}

	/**
	 * This is used for changing the status of test like published or drafted.
	 * 
	 * @param req
	 * @param result
	 * @param redirectAttributes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveTestStatus", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Boolean setTestStatus(@ModelAttribute("questionForm") Test req, BindingResult result,
			RedirectAttributes redirectAttributes)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller setTestStatus method :::::");
		boolean flag = false;
		flag = testService.saveTestStatus(req);
		/*
		 * if (req.getCourseId() != null) { flag = true;
		 * redirectAttributes.addAttribute("courseId", req.getCourseId()); //
		 * model.setViewName("redirect:/addEditCourseMaterial"); } else { //
		 * model.setViewName("redirect:/testList"); flag = false; }
		 */
		return flag;
	}

	/**
	 * This is used for saving the test details.
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/testSubmit", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String saveTest(@RequestParam("imageName") MultipartFile file, @ModelAttribute Test req,
			BindingResult result)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException {
		logger.log(Level.DEBUG, "Inside Test Controller saveTest method  ::::: ");
		Integer testId = 0;
		User user = (User) request.getSession().getAttribute("userSession");
		req.setUserId(user.getUserId());
		req.setCompanyId(user.getCompanyId());
		testId = testService.extractTestDetailsForSaving(req);
		if (file.getOriginalFilename().length() != 0) {
			testService.uploadTestIcon(file, testId, user.getUserId());
		}
		return testId.toString();
	}

	/**
	 * This is used for saving the questions'details.
	 * 
	 * @param sectionJson
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveQuestions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean saveTestQuestions(@RequestParam(value = "sectionData") String sectionJson)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller saveTestQuestions method :::::");
		User user = (User) request.getSession().getAttribute("userSession");
		boolean status = testService.performQuestionSaving(sectionJson, user.getUserId());
		return status;
	}

	/**
	 * This is used for getting test details.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewTestDetail", method = RequestMethod.GET)
	public ModelAndView getTestDetails(@RequestParam(value = "testId") Integer testId,
			@RequestParam(value = "nid", required = false) Integer nid)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller getTestDetails method :::::");
		if (nid != null) {
			NotificationService.updateNotificationReadStatus(nid, 1);
		}
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		Test test = testService.getTestByUserId(testId, user.getUserId());
		if (test.getTestId() != null) {
			model.addObject("test", test);
			model.setViewName("viewtestdetails");
		} else {
			model.setViewName("redirect:/testList");
		}
		return model;
	}

	/**
	 * This is used for redirecting the user on test preview page
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "testpreview", method = RequestMethod.GET)
	public ModelAndView forwardPreviewPage(@RequestParam(value = "testId") Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller forwardPreviewPage method :::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		Test test = testService.previewTestDetails(testId, user.getUserId());
		ObjectMapper object = new ObjectMapper();
		String jsonTest = object.writeValueAsString(test);
		model.addObject("test", jsonTest);
		model.setViewName("testpreview");
		return model;
	}

	/**
	 * This is used for getting list after importing question from question
	 * bank.
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "importQuestion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Question> saveQuestionMapping(@ModelAttribute("testForm") Test req, BindingResult result)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller saveQuestionMapping method :::::");
		User user = (User) request.getSession().getAttribute("userSession");
		List<Question> questionList = new ArrayList<Question>();
		questionList = testService.importQuestionList(user.getUserId(), req);
		return questionList;
	}

	/**
	 * This is used for getting test setting details based on provided test id.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "importTestSetting", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Test importTestSetting(@RequestParam(value = "testId") Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller importTestSetting method:::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Test test = testService.getTestSetting(testId, user.getUserId());
		return test;
	}

	/**
	 * This is used for getting test setting details based on provided test id.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "gettestdataforreview", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Test getTestDataForReview
	(@RequestParam(value = "testId") Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller getTestDataForReview method :::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Test test = testService.previewTestDetails(testId, user.getUserId());
		return test;
	}

	/**
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "prepareTestQuestions", method = RequestMethod.GET)
	public ModelAndView prepareTestQuestions(@RequestParam(value = "testId", required = false) Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller prepareTestQuestions method  ::::: ");
	 	Integer randomQuestionNo=0;
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		if (testId != null) {
			Test test = testService.getTestDetailsforEdit(testId, user.getUserId());
			/**
			 * checking that test is drafted or not.
			 */
			if (test.getTestId() != null) {
				ObjectMapper mapper = new ObjectMapper();
				Section sectionlist[] = testService.getQuestionDetailforEdit(testId);
				randomQuestionNo=testService.getTestQuestion(testId,user.getUserId());
				String sectionJsonList = mapper.writeValueAsString(sectionlist);
				model.addObject("randomQuestionNo",randomQuestionNo);
				model.addObject("test", test);
				model.addObject("sectionJsonList", sectionJsonList);
				model.addObject("sectionlist", sectionlist);
				License license = (License) request.getSession().getAttribute("licenseObj");
				int orgId = (Integer) request.getSession().getAttribute("orgId");
				License licensenew = qbisLicenseService.validateLicense(license, orgId, "QUESTION_TYPE");
				model.addObject("quesTypeList", licensenew.getListQuesType());
				model.setViewName("preparetestquestion");
			}
			/**
			 * if test is published then redirected on create new test page.
			 */
			else {
				model.setViewName("redirect:/addEditTest");
			}

		} else {
			model.setViewName("redirect:/addEditTest");
		}

		return model;
	}

	/**
	 * This is used for redirecting on test review page.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "testreview", method = RequestMethod.GET)
	public ModelAndView testReview(@RequestParam(value = "testId", required = false) Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller testreview method  ::::: ");
		ModelAndView model = new ModelAndView();
		if (testId != null) {
			User user = (User) request.getSession().getAttribute("userSession");
			Test test = testService.previewTestDetails(testId, user.getUserId());
			/**
			 * redirect on create new test page in case of no records found.
			 */
			if (test.getTestId() == null) {
				model.setViewName("redirect:/addEditTest");
			}
			/**
			 * else if test is not drafted then redirect on create new page
			 */
			else if (test.getTestPublishStatus() != 0) {
				model.setViewName("redirect:/addEditTest");
			}
			/**
			 * else test review page if test has minimum one section inside
			 * test.
			 */
			else if (test.getSectionlist().length > 0 || (test.getIsRandom() != null && test.getIsRandom() == 1)) {
				model.addObject("test", test);
				model.addObject("testTags", configService
						.getConfigTags(new Object[] { "board", "session", "class", "subject", "chapter", "act" }));
				model.setViewName("testreview");
			}
			/**
			 * else redirect on for adding new sections in it in case of no
			 * sections.
			 */
			else {
				model.setViewName("redirect:/prepareTestQuestions?testId=" + testId);
			}

		} else {
			model.setViewName("redirect:/addEditTest");
		}
		return model;
	}

	/**
	 * This is used for getting section list.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "gettestsectionlistajax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Section[] getSectionList(@RequestParam(value = "testId") Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller getSectionList method :::::");
		User user = (User) request.getSession().getAttribute("userSession");
		Section[] sectionList = testService.getSectionListForImport(user.getUserId(), testId);
		return sectionList;
	}

	/**
	 * This is used for getting section list.
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "importSectionInTest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Section[] importSectionInTest(@ModelAttribute("testForm") Test test, BindingResult result)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller importSectionInTest method :::::");
		Section[] sectionList = testService.getSectionListForImport(test);
		return sectionList;
	}

	/**
	 * 
	 * @param testId
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "checkTestScheduleTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean checkTestSchedule(@RequestParam(value = "testId") Integer testId)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller checkSchedule method :::::");
		Boolean status = testService.checkTestScheduleTimeStatus(testId);
		return status;
	}

	/**
	 * 
	 * @param testId
	 * @param emailList
	 * @param message
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sharetesttouser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean shareTestToUser(@RequestParam(value = "testId") Integer testId,
			@RequestParam(value = "emailList") String[] emailList,
			@RequestParam(value = "groupList", required = false) String[] groupList,
			@RequestParam(value = "emailMap", required = false) String emailMap,
			@RequestParam(value = "message") String message)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Course Controller publishedCourse method ::::: ");
		Boolean status = true;
		User user = (User) request.getSession().getAttribute("userSession");
		String path = request.getRequestURL().toString();
		path = path.substring(0, path.lastIndexOf('/'));
		Map<String, Integer> userList = new HashMap<>();
		Map<String, Integer> userListtemp = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		userListtemp = mapper.readValue(emailMap, new TypeReference<Map<String, Integer>>() {
		});
		if (groupList.length > 0) {
			userList = courseService.getUsersInGroup(groupList, "test");
		}
		userList.putAll(userListtemp);
		testService.updateTestSharedStatus(testId);
		status = testService.addUserTestMapping(testId, userList, user, path, emailList, message);
		return status;
	}

	/**
	 * Method to check license of organization that it can create new test or
	 * not.
	 * 
	 * @return boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "createNewTestLicesneValidate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean createNewTestLicesneValidate()
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Test Controller createNewTestLicesneValidate method :::::");
		License license = (License) request.getSession().getAttribute("licenseObj");
		int orgId = (Integer) request.getSession().getAttribute("orgId");
		License licenseobj = qbisLicenseService.validateLicense(license, orgId, LicenseEnum.MAXALLOWEDTEST.getValue());
		return licenseobj.getValid();
	}

	/**
	 * This mapping is used for delete section data from test.
	 * 
	 * @param testId
	 * @param sectionId
	 * @return Boolean
	 */
	@RequestMapping(value = "/deletetestsection", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteTestSection(@RequestParam(value = "testId") Integer testId,
			@RequestParam(value = "sectionId") Integer sectionId) {
		logger.log(Level.DEBUG, "Inside Test Controller deleteTestSection method :::::");
		Boolean isDeleted = testService.deleteTestSection(testId, sectionId);
		return isDeleted;
	}

	@RequestMapping(value = "/getdata", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String,Object> fetchTest( @RequestParam(value="chapter") String chapter)
	throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ParseException {
	logger.log(Level.DEBUG, "Inside Test Controller saveTest method ::::: ");
	Map<String,Object> map=new HashMap();
	List<Session> session=new ArrayList();
	List<Section> section=new ArrayList();
	Integer courseId=configService.getCourseId(chapter);
	System.out.println("chapter name="+chapter);
	System.out.println("Course Name="+courseId);
	if(courseId!= null)
	{
	section= configService.getSectionNames(courseId);
	
	}
	if(section.size()>0)
	{
	 session=configService.getSessionNames(section.get(0).getSectionId());
	}
	 if(section.size()>0)
	 {   
		 map.put("status", "success");
		 map.put("chapter",section.stream().filter(m->m.getSectionName()!=null).map(m->m.getSectionName()).collect(Collectors.toList()));
		 map.put("act",session.stream().filter(m->m.getSessionName()!=null).map(m->m.getSessionName()).collect(Collectors.toList()));
	 }
	 if(map.size()>0)
	return map;
	 else
      map.put("status", "error");
      return map;
	}
	
}
