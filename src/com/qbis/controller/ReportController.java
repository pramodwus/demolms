package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qbis.common.ConstantUtil;
import com.qbis.model.Course;
import com.qbis.model.User;
import com.qbis.services.CourseService;
import com.qbis.services.ReportService;
import com.qbis.services.RoleService;
import com.qbis.services.UserService;
import com.qbis.validators.LoginValidator;

/**
 * This class is used for generating the report about test or course.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
public class ReportController {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(ReportController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	/**
	 * Instance of {@link ReportService}
	 */
	@Autowired
	private ReportService reportservice;
	/**
	 * Instance of {@link UserService}
	 */
	@Autowired
	private UserService userservice;
	
	/**
	 * Instance of {@link LoginValidator}
	 */
	@Autowired
	private RoleService roleService;

	/**
	 * This is used for redirect on custom report page.
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/customreport")
	public ModelAndView customReport() throws NoSuchAlgorithmException,
			InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in customReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User)request.getSession().getAttribute("userSession");
		model.addObject("creator", userservice.getUserListByCreaterId(user.getUserId(),ConstantUtil.TRAINER_ROLE_ID));
		model.addObject("taglist", reportservice.getAllTagList());
		model.addObject("publishstatus", reportservice.getPublishStatus());
		model.setViewName("customreport");
		return model;
	}

	/**
	 * This is used for getting course or test list based on filter.
	 * 
	 * @param searchFor
	 * @param creater
	 * @param tag
	 * @param publishStatus
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getfilterreportdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<?> getcustomReportData(
			@RequestParam(value = "searchFor") String searchFor,
			@RequestParam(value = "creator", required = false) String creater,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "publishStatus") Integer publishStatus,
			@RequestParam(value = "dateRange") String dateRange)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in getcustomReportData method ::::::");
		List<?> list;
		if (searchFor.equalsIgnoreCase("course")) {
			list = reportservice.getCourseListUsingFilter(creater, tag,
					publishStatus, dateRange);
		} else {
			list = reportservice.getTestListUsingFilter(creater, tag,
					publishStatus, dateRange);
		}
		return list;
	}
	
	/**
	 * This is used for open userReportsList page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/userReportsList")
	public ModelAndView usersReportListPage(){
		logger.log(Level.DEBUG,
				"Inside ReportController in usersReportListPage method ::::::");
		ModelAndView model = new ModelAndView();				
		model.setViewName("usersreportlist");
		return model;
	}
	
	/**
	 * This is used for open activeusergroupbydatereport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/activeUserGroupByDateReport")
	public ModelAndView activeUserGroupByDateReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in activeUserGroupByDateReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("rolelist",roleService.getRolesList(user.getUserId()));
		model.setViewName("activeusergroupbydatereport");
		return model;
	}
	
	
	/**
	 * This is used for getting users/group list based on filter.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getfilteractiveusersgroupdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<?> getFilterActiveUsersGroupData(
			@RequestParam(value = "searchFor") String searchFor,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "dateRange") String dateRange)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterActiveUsersGroupData method ::::::");
		System.out.println("getFilterActiveUsersGroupData");
		User user = (User) request.getSession().getAttribute("userSession");
		List<?> list;
		if (searchFor.equalsIgnoreCase("users")) {
			list = reportservice.getActiveUserByDate(user.getUserId(),roleId,dateRange);
		} else {
			list = reportservice.getActiveGroupByDate(user.getUserId(),roleId,dateRange);
		}
		System.out.println("list"+list.size());
		return list;
	}
	
	
	/**
	 * This is used for open completedcourseassessmentreport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/completedCourseAssessmentReport")
	public ModelAndView completedCourseAssessmentReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in completedCourseAssessmentReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userList",reportservice.getUsersListByCreaterId(user.getUserId()));
		model.setViewName("completedcourseassessmentreport");
		return model;
	}
	
	
	/**
	 * This is used for getting course or test list based on filter.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFilterCompletedAssessmentCourseData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getFilterCompletedAssessmentCourseData(			
			@RequestParam(value = "userId", required = false) Integer userId)			
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterCompletedAssessmentCourseData method ::::::");
		System.out.println("getFilterCompletedAssessmentCourseData");		
		
		User userobj = userservice.findOneUser(userId);
	    User obj = reportservice.getCompletedAssessmentCourseData(userobj);		
		return obj;
	}
	
	
	/**
	 * This is used for open userloginactivityreport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/userLoginActivityReport")
	public ModelAndView userLoginActivityReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in userLoginActivityReport method ::::::");
		ModelAndView model = new ModelAndView();		
		model.setViewName("userloginactivityreport");
		return model;
	}
	
	/**
	 * This is used for getting active login user details.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFilterActiveLoginUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getFilterActiveLoginUsers(
			@RequestParam(value = "dateRange") String dateRange)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterActiveLoginUsers method ::::::");
		System.out.println("getFilterActiveLoginUsers");
		User user = (User) request.getSession().getAttribute("userSession");
		List<User> list = null;
		list = reportservice.getActiveLoginUsersByDate(user.getUserId(),dateRange);
		System.out.println("list"+list.size());
		return list;
	}
	
	/**
	 * This is used for open activeusergroupactivityreport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/activeUserGroupActivityByDateReport")
	public ModelAndView activeUserGroupActivityByDateReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in activeUserGroupByDateReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("rolelist",roleService.getRolesList(user.getUserId()));
		model.setViewName("activeusergroupactivityreport");
		return model;
	}
	
	/**
	 * This is used for getting course or test list based on filter.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFilterActiveUsersGroupActivityByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<?> getFilterActiveUsersGroupActivityByDate(
			@RequestParam(value = "searchFor") String searchFor,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "dateRange") String dateRange)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterActiveUsersGroupActivityByDate method ::::::");
		System.out.println("getFilterActiveUsersGroupActivityByDate");
		User user = (User) request.getSession().getAttribute("userSession");
		List<?> list;
		if (searchFor.equalsIgnoreCase("users")) {
			list = reportservice.getActiveUserActivityByDate(user.getUserId(),roleId,dateRange);
		} else {
			list = reportservice.getActiveGroupByDate(user.getUserId(),roleId,dateRange);//not in use
		}
		System.out.println("list"+list.size());
		return list;
	}
	
	
	/**
	 * This is used for open individualuserprofileauditreport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/individualUserProfileAuditReport")
	public ModelAndView individualUserProfileAuditReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in individualUserProfileAuditReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userList",reportservice.getUsersListByCreaterId(user.getUserId()));
		model.setViewName("individualuserprofileauditreport");
		return model;
	}
	
	
	/**
	 * This is used for getting active login user details.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFilterAuditProfileUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getFilterAuditProfileUsers(
			@RequestParam(value = "userId") Integer userId)
			throws NoSuchAlgorithmException, InvalidKeySpecException,
			IOException {
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterAuditProfileUsers method ::::::");
		System.out.println("getFilterAuditProfileUsers");
		//User user = (User) request.getSession().getAttribute("userSession");
		List<User> list = null;
		list = reportservice.getFilterAuditProfileUsers(userId);		
		return list;
	}
	
	/**
	 * This is used for open userReportsList page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/coursereportlist")
	public ModelAndView courseReportListPage(){
		logger.log(Level.DEBUG,
				"Inside ReportController in courseReportListPage method ::::::");
		ModelAndView model = new ModelAndView();				
		model.setViewName("coursereportlist");
		return model;
	}
	
	
	/**
	 * This is used for open courseEnrollmentStatusReport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/courseEnrollmentStatusReport")
	public ModelAndView courseEnrollmentStatusReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in courseEnrollmentStatusReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userList",userservice.getTrainersList(user.getUserId()));
		model.setViewName("courseenrollmentstatusreport");
		return model;
	}
	
	/**
	 * This is used for getting course or test list based on filter.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFilterCourseEnrollmentReportData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getFilterCourseEnrollmentReportData(			
			@RequestParam(value = "trainerId", required = false) Integer trainerId,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "startDate", required = false) String startDate){
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterCourseEnrollmentReportData method ::::::");
		System.out.println("getFilterCompletedAssessmentCourseData");
	    User obj = reportservice.courseEnrollmentReportData(trainerId,status,startDate);		
		return obj;
	}
	
	
	/**
	 * This is used for open courseSessionTimeReport page.
	 * 
	 * @return model
	 * 
	 */
	@RequestMapping(value = "/courseSessionTimeReport")
	public ModelAndView courseSessionTimeReport(){
		logger.log(Level.DEBUG,
				"Inside ReportController in courseSessionTimeReport method ::::::");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userList",reportservice.getUsersListByCreaterId(user.getUserId()));
		model.addObject("courseList",new CourseService().getPublishedDraftedCourse(user.getUserId(),1));		
		model.setViewName("courseSessionTimeReport");
		return model;
	}
	
	/**
	 * This is used for getting course or test list based on filter.
	 * 
	 * @param searchFor
	 * @param dateRange
	 * @return List
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/getFilterCourseSessionReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Course> getFilterCourseSessionReport(			
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "courseId", required = false) Integer courseId){
		logger.log(Level.DEBUG,
				"Inside ReportController in getFilterCourseSessionReport method ::::::");
		User user = (User) request.getSession().getAttribute("userSession");
		List<Course> obj = reportservice.courseSessionReportData(userId,courseId,user.getUserId());		
		return obj;
	}
	
}
