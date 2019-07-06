package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.CommonUtil;
import com.qbis.common.CourseEnum;
import com.qbis.common.LicenseEnum;
import com.qbis.common.StudentEnum;
import com.qbis.model.Course;
import com.qbis.model.License;
import com.qbis.model.Profile;
import com.qbis.model.ServiceResult;
import com.qbis.model.ServiceResult.IsPractice;
import com.qbis.model.ServiceResult.StatusCode;
import com.qbis.model.User;
import com.qbis.model.UserTestAttempt;
import com.qbis.services.CourseService;
import com.qbis.services.DashboardService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.StudentCourseService;
import com.qbis.services.StudentTestService;
import com.qbis.services.TestService;
import com.qbis.services.UserService;

@Controller
public class DashboardController {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(DashboardController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserService userservice;
	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentTestService studentTestService;
	
	@Autowired
	private StudentCourseService studentCourseService;
	
	@Autowired
	private QbisLicenseService licenseService;
	
	@Autowired
	private StudentCourseService studentcourseservice;
	@Autowired
	
	private AuthenticationService authenticationService;
	/**
	 * This is used for getting all details for Teacher's side Dashboard
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/dashboardcontroller")
	public ModelAndView getTeacherDashboardData()
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Dashboard Controller getTeacherDashboardData method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");

		model.addObject("testList",
				testService.getTestDetails(user.getUserId()));
		
		model.addObject("teamsCount",
				dashboardService.getTotalTeamsCount(user.getUserId()));
		model.addObject("traineeCount",
				dashboardService.getTotalTraineeCount(user.getUserId()));
		
		model.addObject("countPubTest", studentTestService
				.countPublishedTestByUserId(Integer.parseInt(StudentEnum.PUBLISHED
						.getValue()),user.getUserId()));
		model.addObject("scheduledTest",
				testService.getTestPublishedDraftedDetails(user.getUserId(), 2).size());
		model.addObject("draftedTest",
				testService.getTestPublishedDraftedDetails(user.getUserId(), 0).size());
		
		model.addObject("countPubCourse", studentCourseService
				.countPublishedTestByUserId(Integer
						.parseInt(CourseEnum.PUBLISHED.getValue()),user.getUserId()));
		model.addObject("draftedCourseCount",
				courseService.getPublishedDraftedCourse(user.getUserId(),
						Integer.parseInt(CourseEnum.DRAFTED.getValue())).size());
		model.addObject("scheduledCourseCount",
				courseService.getPublishedDraftedCourse(user.getUserId(),
						Integer.parseInt(CourseEnum.SCHEDULED.getValue())).size());		
		
		License license = (License)request.getSession().getAttribute("licenseObj");
		int orgId = (Integer)request.getSession().getAttribute("orgId");
		license.setFileSize(0l);
		license.setUserId(user.getUserId());
		License licensenew = licenseService.validateLicense(license, orgId, LicenseEnum.COURSECONTENTSPACE.getValue());		
		model.addObject("license",licensenew);
		Long usedSpaceBytes = licensenew.getUsedSpace();
		Long usedSpaceGB = CommonUtil.convertBytesToGB(usedSpaceBytes);
		if(usedSpaceGB>0){
			model.addObject("usedSpace",usedSpaceGB);
			model.addObject("usedSpaceFileSizeType","GB");
		}else{
			model.addObject("usedSpace",(CommonUtil.convertBytesToMB(usedSpaceBytes)));
			model.addObject("usedSpaceFileSizeType","MB");
		}
		
		model.addObject("courseList", courseService.getAllCourseList(user.getUserId()));
		
		model.addObject("activityList",dashboardService.getLatestActivitiesOfTrainer(user.getUserId()));
		Map<String,Object> map = dashboardService.getPercentageOfContent(user.getUserId(),license);
		ObjectMapper mapper = new ObjectMapper(); 
		model.addObject("diskmap",mapper.writeValueAsString(map));
		/*model.addObject("totaltest",
		dashboardService.getTotalTestCount(user.getUserId()));
model.addObject("totaluser",
		dashboardService.getTotalUsersCount(user.getUserId()));
model.addObject("activeuser",
		dashboardService.getTotalActiveStudentCount(user.getUserId()));*/
/*model.addObject("totalcourse",
dashboardService.getTotalCourseCount(user.getUserId()));*/

/*List<Test> testlist = dashboardService.getMyTestByUserId(user
		.getUserId());
model.addObject("latestAttempt", testlist);
model.addObject("publishedCourse", new CourseService()
		.getPublishedDraftedCourse(user.getUserId(),
				Integer.parseInt(CourseEnum.PUBLISHED.getValue()))); */
		
		model.setViewName("dashboard");
		return model;
	}
	
	
	/**
	 * This is used for getting all details for Student's side Dashboard
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/studentdashboard")
	public ModelAndView getStudentDashboardData()
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside Dashboard Controller getStudentDashboardData method ::::: ");
		List<Course> courses=new ArrayList<>();
		String token = CommonUtil.getTokenFromCookie(request);
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		user.setName(user.getFirstName()+" "+user.getLastName());
		//	boolean flag=studentcourseservice.getStudentSubscriptionStatus(courseId, user.getUserId());
		//	model.addObject("flag",flag);
			List<Integer> courseId=new ArrayList<>();
			courseId.add(3);
			courseId.add(5);
			boolean flag=studentCourseService.getCourseSubscribe(user.getUserId(),courseId);
			model.addObject("flag",flag);
		model.addObject("userInfo",user);
		model.addObject("totaltest", studentTestService
				.countPublishedTest(user.getUserId(), user.getCompanyId(),
						Integer.parseInt(StudentEnum.PUBLISHED.getValue())));
		model.addObject(
				"totalcourse",studentCourseService.countPublishedCourses(
						user.getUserId(),
						user.getCompanyId(),
						Integer.parseInt(CourseEnum.PUBLISHED.getValue())));
		int pass = 0;
		int fail = 0;
		List<UserTestAttempt> testlist = dashboardService
				.getAttemptedTestByUserId(user.getUserId());
		for (int i = 0; i < testlist.size(); i++) {
			if (testlist.get(i).getGrade() == "Pass")
				pass++;
			else
				fail++;
		}
		User user1=userservice.findOneUser(user.getEmail());	
		
		String userName=user1.getFirstName()==null || user1.getFirstName().equals("")?user1.getEmail().substring(0,user1.getEmail().indexOf('@')):user1.getFirstName();
	 
		/*
		 * model.addObject("countpass", pass); model.addObject("countfail", fail);
		 * model.addObject("allAttempt", testlist); model.addObject("allAttemptCount",
		 * testlist.size());
		 */
		 courses=studentcourseservice.getPublishedCourseListeluminate(user.getUserId(), 1);
		 Profile profile = new Profile();
		 profile=authenticationService.getUserShortProfile(token);
		 
		 model.addObject("profiledata", profile);
		model.addObject("courses",courses);
//		model.addObject("courses",studentcourseservice.getCourses(user.getUserId(), 1));
//		System.out.println("Published courses------->"+studentcourseservice.getPublishedCourseList(user.getUserId(), 1, 2));
//	    model.addObject("totalCourse", studentcourseservice.countPublishedCourses(user.getUserId(),
				//user.getCompanyId(), Integer.parseInt(CourseEnum.PUBLISHED.getValue())));
//	    System.out.println("========"+ studentcourseservice.countPublishedCourses(user.getUserId(),
//				user.getCompanyId(), Integer.parseInt(CourseEnum.PUBLISHED.getValue())));
//	    System.out.println("=========="+studentcourseservice.countUpcomingCourses(user.getUserId(),
//				user.getCompanyId(), Integer.parseInt(CourseEnum.SCHEDULED.getValue())));
		model.addObject("upcomingCourse", studentcourseservice.countUpcomingCourses(user.getUserId(),
				user.getCompanyId(), Integer.parseInt(CourseEnum.SCHEDULED.getValue())));
		model.addObject("userName",userName);
		model.setViewName("student/studentdashboard");
		return model;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Service to get Attempted Test Statistics data.
	 * 
	 * @param lastNMonths
	 * @return
	 */
	@RequestMapping(value = "/attemptedTestStatistics/{lastNMonths}", method = RequestMethod.GET)
	public @ResponseBody Object getAttemptedTestStatistics(
			@PathVariable Integer lastNMonths) {

		logger.log(Level.DEBUG,
				"Dashboard Controller getAttemptedTestStatistics method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		List<String> attemptStatistics = dashboardService
				.attemptedPassFailCount(user.getUserId(), lastNMonths);
		// String color = "95,245,235~105,35,245";
		String color = "0,166,81~239,40,64";
		return new ServiceResult(StatusCode.SUCCESS, new Object[] {
				attemptStatistics, color });
	}
	
	// ------------------------------------------------------------------------------------------------------------------
		/**
		 * Service to get Attempted Test Statistics data.
		 * 
		 * @param lastNMonths
		 * @return
		 */
		@RequestMapping(value = "/activeUserStatistics/{lastNMonths}", method = RequestMethod.GET)
		public @ResponseBody Map<String,Object> getTotalActiveUsersStatistics(
				@PathVariable Integer lastNMonths) {
			logger.log(Level.DEBUG,
					"Dashboard Controller getTotalActiveUsersStatistics method ::::: ");
			User user = (User) request.getSession().getAttribute("userSession");
			return dashboardService
					.getTotalActiveUsersStatistics(user.getUserId(), lastNMonths);
		}

}



