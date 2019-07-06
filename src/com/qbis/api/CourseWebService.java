package com.qbis.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.CommonUtil;
import com.qbis.model.Attempts;
import com.qbis.model.ContentActivity;
import com.qbis.model.Course;
import com.qbis.model.Question;
import com.qbis.model.Response;
import com.qbis.model.Section;
import com.qbis.model.SectionContent;
import com.qbis.model.ServiceResult;
import com.qbis.model.ServiceResult.StatusCode;
import com.qbis.model.ServiceResult1;
import com.qbis.model.Session;
import com.qbis.model.ServiceResult1.StatusCode1;
import com.qbis.model.Test;
import com.qbis.model.User;
import com.qbis.model.UserTestAttempt;
import com.qbis.services.CourseService;
import com.qbis.services.StudentCourseService;

@RestController
@RequestMapping(value = "/api/course/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CourseWebService {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(CourseWebService.class);
	/**
	 * Instance of {@link CourseService}
	 */
	@Autowired
	private CourseService courseService;

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest httpRequest;
	
	@Autowired
	private StudentCourseService studentCourseService;

	/**
	 * This method is used for getting product list.
	 * 
	 * @param token
	 *            {@link String}
	 * @param courseId
	 *            {@link Integer}
	 * @return {@link Response}
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/detail/{courseId}", method = RequestMethod.GET)
	public Object getCourseDetails(@RequestHeader(value = "Authorization", required = false) String token,
			@PathVariable("courseId") Integer courseId) {
		logger.debug("Inside CourseWebService in getCourseDetails api :::::::courseId = " + courseId);
		Course course = courseService.getCourseDetails(courseId);
		if (course != null) {
			return new ServiceResult(StatusCode.SUCCESS, course);
		}
		return new ServiceResult(StatusCode.ERROR, null);
	}

	@RequestMapping(value = "/detail1/{courseId}", method = RequestMethod.GET)
	public Object getCourseDetails1(@RequestHeader(value = "Authorization", required = false) String token,
			@PathVariable("courseId") Integer courseId) {
		logger.debug("Inside CourseWebService in getCourseDetails api :::::::courseId = " + courseId);
		Course course = courseService.getCourseDetails(courseId, null,null,null);
		if (course != null) {
			return new ServiceResult(StatusCode.SUCCESS, course);
		}
		return new ServiceResult(StatusCode.ERROR, null);
	}

	/**
	 * This method is used for getting particular content details.
	 * 
	 * @param token
	 * @param courseId
	 * @param sectionId
	 * @param attemptId
	 * @param sessionId
	 * @param contentId
	 * @return
	 */
	@RequestMapping(value = "/detail/{courseId}/{sectionId}/{attemptId}/{sessionId}/{contentId}", method = RequestMethod.GET)
	public Object getCourseDetails(@RequestHeader(value = "Authorization", required = false) String token,
			@PathVariable("courseId") Integer courseId, @PathVariable("sectionId") Integer sectionId,
			@PathVariable("attemptId") Integer attemptId, @PathVariable("sessionId") Long sessionId,
			@PathVariable("contentId") Integer contentId) 	throws JsonProcessingException {
		logger.debug("Inside CourseWebService in getCourseDetails api :::::::courseId = " + courseId);
		User user = (User) httpRequest.getSession().getAttribute("userSession");
		SectionContent response = courseService.getSectionContentForAttempt(courseId, sectionId, attemptId, sessionId,
				contentId, user.getUserId());
		System.out.println("-------------------"+new ObjectMapper().writeValueAsString(response));
		           
		if (response != null) {
			return new ServiceResult(StatusCode.SUCCESS, response);
		}
		return new ServiceResult(StatusCode.ERROR, null);
	}

	/**
	 * This method is used for submitting test detail.
	 * 
	 * @param viewId
	 * @param test
	 * @return
	 */
	@RequestMapping(value = "/savetest/{contentViewId}", method = RequestMethod.POST)
	public Object submitTestDetails(@PathVariable("contentViewId") Integer viewId, @RequestBody Test test) {
		User user = (User) httpRequest.getSession().getAttribute("userSession");		
		UserTestAttempt attempt = courseService.submitTestAsContent(viewId, test, user.getUserId());
		if (attempt != null) {
			return new ServiceResult(StatusCode.SUCCESS, attempt);
		}
		return new ServiceResult(StatusCode.ERROR, null);
	}
	
	@RequestMapping(value = "/savetestmobile", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object submitTestDetails1(@RequestHeader(value = "Authorization", required = false) String token,@RequestBody Test test) {
		UserTestAttempt attempt=null;
		  try {
		  System.out.println("====="+test.getContentViewId()+"="+test.getTestTime()+"="+test.getTotalQuestion());
		  Integer userId= new AuthenticationService().getUserProfileUserId(token);
		  if(userId != null)
		  	{
		  attempt =courseService.submitTestAsContent(test.getContentViewId(), test,userId);
		  if (attempt != null)
		   return new  ServiceResult(StatusCode.SUCCESS, attempt); 
		
		  else
		  return new ServiceResult(StatusCode.ERROR, null);
		  }
		  else
		  {
				 return new ServiceResult1(StatusCode1.UNAUTHORIZED,"Invalid Token",null);  
		  }
		  }
		  catch (Exception e)
		  { // TODO:handle exception 
			  e.printStackTrace();
		  
		  }
	  return attempt;
	
	}
	
	@RequestMapping(value="/randomquestionlist/{testId}")
	public List<Question> randomTest(@PathVariable(value="testId") Integer testId)
			throws JsonProcessingException {
		logger.log(Level.DEBUG, "Inside StudentTestController in viewCourseContent method :::::");
	//	User user = (User) httpRequest.getSession().getAttribute("userSession");
		List<String> allValue=courseService.getTagList(testId);
		List<Question> questionList = courseService.getRandomQuestion(allValue,testId);
		
	//	System.out.println("========="+questionList);
		return questionList;
	}

	@RequestMapping(value = "/getTestSummary", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object getTestSummary(@RequestHeader(value = "Authorization", required = false) String token,@RequestBody ContentActivity contentActivity) {
		UserTestAttempt attempt=null;
		  try {
			  System.out.println("====="+contentActivity.getSessionId()+"="+contentActivity.getSectionId()+"="+contentActivity.getAttemptId());
		  Integer userId= new AuthenticationService().getUserProfileUserId(token);
		  if(userId != null)
		  	{
		  attempt =courseService.getTestSummary(userId, contentActivity.getCourseId(), contentActivity.getSectionId(), contentActivity.getAttemptId(), new Long(contentActivity.getSessionId()) , contentActivity.getContentId());
		  if (attempt != null)
		   return new  ServiceResult(StatusCode.SUCCESS, attempt); 
		
		  else
		  return new ServiceResult(StatusCode.ERROR, null);
		  }
		  else
		  {
				 return new ServiceResult1(StatusCode1.UNAUTHORIZED,"Invalid Token",null);  
		  }
		  }
		  catch (Exception e)
		  { // TODO:handle exception 
			  e.printStackTrace();
		  
		  }
	  return attempt;
	
	}
	
	@RequestMapping(value="/getChartData/{courseId}")
	public Object getChartData(@RequestHeader(value = "Authorization", required = false) String token,@PathVariable(value="courseId") Integer courseId)
			throws JsonProcessingException {
		logger.log(Level.DEBUG, "Inside StudentTestController in getChartData method :::::");
		  Integer userId= new AuthenticationService().getUserProfileUserId(token);
		  List<Section> section=new ArrayList<>();
		  if(userId != null)
		  	{
		 section=studentCourseService.getChaptersForGraph(courseId, userId);
		  if (section != null && section.size()>0)
			  return new  ServiceResult1(StatusCode1.SUCCESS, section); 
        else
       return new ServiceResult1(StatusCode1.NOTFOUND, null);
		  	}
		  else
		  {
		return new ServiceResult1(StatusCode1.UNAUTHORIZED,"Invalid Token",null);    
		  }
	}

	@RequestMapping(value="/getPracticeTest")
	public Object getPracticeSession(@RequestHeader(value = "Authorization", required = false) String token)
	{
		logger.log(Level.DEBUG, "Inside CourseWebService in getPracticeTest method :::::");
		List<Attempts> attemptList=new ArrayList<Attempts>();
		Course course=null;
		try {
		  Integer userId= new AuthenticationService().getUserProfileUserId(token);
		  if(userId != null)
		  	{
			     List<Course> courseList=studentCourseService.getPublishedCourseListeluminate(userId, 1);
			      courseList= studentCourseService.getPracticeTest(courseList,userId);
			      
		  if (courseList != null && courseList.size()>0)
		   return new  ServiceResult1(StatusCode1.SUCCESS, courseList); 
		
		  else
		  return new ServiceResult1(StatusCode1.NOTFOUND, null);
		  }
		  else
		  {
				 return new ServiceResult1(StatusCode1.UNAUTHORIZED,"Invalid Token",null);  
		  }
		  }
		  catch (Exception e)
		  { // TODO:handle exception 
			  e.printStackTrace();
		  
		  }
		  return attemptList;
	}



}


