package com.qbis.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qbis.common.CommonUtil;
import com.qbis.common.CourseEnum;
import com.qbis.common.FunctionSubFunEnum;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.common.Status;
import com.qbis.model.AppFunctions;
import com.qbis.model.Course;
import com.qbis.model.SectionContent;
import com.qbis.model.Test;
import com.qbis.model.User;

/**
 * This class is used for performing operation related to report.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class ReportService {
	
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(ReportService.class);
	
	/**
	 * This is used for getting all tag list from test and course.
	 * 
	 * @return Set<String>
	 */
	public Set<String> getAllTagList() {
		Set<String> set = new TreeSet<String>();
		try {
			/**
			 * getting all available tags from test.
			 */
			QueryData testTag = QueryManager.execuateQuery(
					QueryStrings.GET_TEST_TAG_LIST, new Object[] {});
			for (QueryData.Row testRow : testTag.getRows()) {
				if (testRow.getRowItem(0) != null) {
					if (testRow.getRowItem(0).contains(",")) {
						for (String tag : testRow.getRowItem(0).split(",")) {
							set.add(tag.trim());
						}
					}
				}
			}
			/**
			 * getting all available tags from course.
			 */
			QueryData courseTag = QueryManager.execuateQuery(
					QueryStrings.GET_COURSE_TAG_LIST, new Object[] {});
			for (QueryData.Row courseRow : courseTag.getRows()) {
				if (courseRow.getRowItem(0) != null) {
					if (courseRow.getRowItem(0).contains(",")) {
						for (String tag : courseRow.getRowItem(0).split(",")) {
							set.add(tag.trim());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	/**
	 * This method is used for getting all available status for test or course
	 * as list.
	 * 
	 * @return Map<String,Integer>
	 */
	public Map<String, Integer> getPublishStatus() {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("lbl.published", Integer.parseInt(Status.PUBLISHED.getValue()));
		map.put("lbl.scheduled", Integer.parseInt(Status.SCHEDULED.getValue()));
		map.put("lbl.drafted", Integer.parseInt(Status.DRAFTED.getValue()));
		return map;
	}

	/**
	 * This method is used for getting test list using search filter.
	 * 
	 * @param creater
	 * @param tag
	 * @param publishStatus
	 * @param dateRange
	 * @return
	 */
	public List<Test> getTestListUsingFilter(String creater, String tag,
			Integer publishStatus, String dateRange) {
		/**
		 * ArrayList
		 */
		List<Test> list = new ArrayList<Test>();
		try {
			/**
			 * for order of getting list.
			 */
			String orderSubQuery = "";
			/**
			 * get query for getting test data.
			 */
			String queryfinal = (QueryStrings.GET_TEST_LIST_USING_FILTER)
					.getQuery();
			/**
			 * if publish status is published or scheduled or drafted then
			 * modify test list query.
			 */
			queryfinal = queryfinal
					+ ((publishStatus != null) ? " WHERE qbis_test.published="
							+ publishStatus : " WHERE ");
			/**
			 * if creater's name is not null then added creater's name as
			 * condition in query.
			 */
			queryfinal = queryfinal
					+ ((creater != null) ? " AND qbis_users.user_email='"
							+ creater + "'" : "");
			/**
			 * if tag is not null then added tag as condition in query.
			 */
			queryfinal = queryfinal
					+ ((tag != null) ? " AND qbis_test.tags LIKE '%" + tag
							+ "%'" : "");
			/**
			 * if date range is not null
			 */
			if (dateRange != null) {
				/**
				 * split date range.
				 */
				String date[] = dateRange.split("-");
				/**
				 * if test type is published then date range would be on publish
				 * date so added date range condition on published date
				 */
				if (publishStatus == Integer.parseInt(Status.PUBLISHED
						.getValue())) {
					/**
					 * append published date range in query.
					 */
					queryfinal = queryfinal
							+ " AND qbis_test.published_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					/**
					 * list would be get based on published date of test
					 */
					orderSubQuery = " ORDER BY qbis_test.published_date DESC";
				}
				/**
				 * if test type is scheduled then date range would be on
				 * scheduled date so added date range condition on scheduled
				 * date
				 */
				else if (publishStatus == Integer.parseInt(Status.SCHEDULED
						.getValue())) {
					/**
					 * append schedule publish date range in query.
					 */
					queryfinal = queryfinal
							+ " AND qbis_test.schedule_publish_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					/**
					 * list would be get based on schedule publish date of test
					 */
					orderSubQuery = " ORDER BY qbis_test.schedule_publish_date DESC";
				}
				/**
				 * if test type is drafted then date range would be on drafted
				 * date so added date range condition on drafted date
				 */
				else if (publishStatus == Integer.parseInt(Status.DRAFTED
						.getValue())) {
					/**
					 * append schedule created date range in query.
					 */
					queryfinal = queryfinal
							+ " AND qbis_test.created_date BETWEEN '" + date[0]
							+ "' AND '" + date[1] + "'";
					/**
					 * list would be get based on created date of test
					 */
					orderSubQuery = " ORDER BY qbis_test.created_date DESC";
				}
			}
			/**
			 * added group by condition in query
			 */
			queryfinal = queryfinal + " GROUP BY qbis_test.test_id"
					+ orderSubQuery;
			/**
			 * getting test list based on final query.
			 */
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				Test test = new Test();
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setTestName(row.getRowItem(1));
				test.setTestStatus(Integer.parseInt(row.getRowItem(8)));
				if (row.getRowItem(8).equals(Status.PUBLISHED.getValue())) {
					/**
					 * set published date for published test.
					 */
					test.setDate(row.getRowItem(5));
				} else if (row.getRowItem(8)
						.equals(Status.SCHEDULED.getValue())) {
					/**
					 * set scheduled date for scheduled test.
					 */
					test.setDate(row.getRowItem(7));
				} else {
					/**
					 * set created date for created test.
					 */
					test.setDate(row.getRowItem(4));
				}
				test.setTeacherName(row.getRowItem(19));
				test.setTotalAppearStudents(Integer.parseInt(row.getRowItem(21)));
				list.add(test);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method is used for getting course list using search filter.
	 * 
	 * @param creater
	 * @param tag
	 * @param publishStatus
	 * @param dateRange
	 * @return
	 */
	public List<Course> getCourseListUsingFilter(String creater, String tag,
			Integer publishStatus, String dateRange) {
		/**
		 * ArrayList
		 */
		List<Course> list = new ArrayList<Course>();

		try {
			/**
			 * for order of getting list.
			 */
			String orderSubQuery = "";
			/**
			 * get query for getting course data.
			 */
			String queryfinal = (QueryStrings.GET_COURSE_LIST_USING_FILTER)
					.getQuery();
			/**
			 * if publish status is published or scheduled or drafted then
			 * modify course list query.
			 */
			queryfinal = queryfinal
					+ ((publishStatus != null) ? " WHERE course_manager.published="
							+ publishStatus
							: " WHERE ");
			/**
			 * if creater's name is not null then added creater's name as
			 * condition in query.
			 */
			queryfinal = queryfinal
					+ ((creater != null) ? " AND qbis_users.user_email='"
							+ creater + "'" : "");
			/**
			 * if tag is not null then added tag as condition in query.
			 */
			queryfinal = queryfinal
					+ ((tag != null) ? " AND course_manager.tags LIKE '%" + tag
							+ "%'" : "");
			/**
			 * if date range is not null
			 */
			if (dateRange != null) {
				/**
				 * split date range.
				 */
				String date[] = dateRange.split("-");
				/**
				 * if course type is published then date range would be on
				 * publish date so added date range condition on published date
				 */
				if (publishStatus == Integer.parseInt(Status.PUBLISHED
						.getValue())) {
					/**
					 * append published date range in query.
					 */
					queryfinal = queryfinal
							+ " AND course_manager.published_on BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					/**
					 * list would be get based on published date of course
					 */
					orderSubQuery = " ORDER BY course_manager.published_on DESC";
				}
				/**
				 * if course type is scheduled then date range would be on
				 * scheduled date so added date range condition on scheduled
				 * date
				 */
				else if (publishStatus == Integer.parseInt(Status.SCHEDULED
						.getValue())) {
					/**
					 * append scheduled publish date range in query.
					 */
					queryfinal = queryfinal
							+ " AND course_manager.schedule_publish_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					/**
					 * list would be get based on scheduled publish date of
					 * course.
					 */
					orderSubQuery = " ORDER BY course_manager.schedule_publish_date DESC";
				}
				/**
				 * if course type is drafted then date range would be on drafted
				 * date so added date range condition on drafted date
				 */
				else if (publishStatus == Integer.parseInt(Status.DRAFTED
						.getValue())) {
					/**
					 * append created date range in query.
					 */
					queryfinal = queryfinal
							+ " AND course_manager.created_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					/**
					 * list would be get based on created date of test
					 */
					orderSubQuery = " ORDER BY course_manager.created_date DESC";
				}
			}
			/**
			 * added order condition in query
			 */
			queryfinal = queryfinal + orderSubQuery;
			/**
			 * getting course list based on final query.
			 */
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setPublish(Integer.parseInt(row.getRowItem(2)));
				course.setEnrollCount(Integer.parseInt(row.getRowItem(13)));
				if (row.getRowItem(2).equals(CourseEnum.PUBLISHED.getValue())) {
					/**
					 * set published date for created course.
					 */
					course.setDate(row.getRowItem(17));
				} else if (row.getRowItem(2).equals(
						CourseEnum.SCHEDULED.getValue())) {
					/**
					 * set scheduled date for created course.
					 */
					course.setDate(row.getRowItem(15));
				} else {
					/**
					 * set created date for created course.
					 */
					course.setDate(row.getRowItem(10));
				}
				course.setTeacherName(row.getRowItem(18));
				list.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * This method is used for getting Active users list using filter.
	 * (Active Users OR Group by Date Report)
	 * @param userId
	 * @param roleId	 
	 * @param dateRange
	 * @return
	 */
	public List<User> getActiveUserByDate(Integer userId,Integer roleId,String dateRange) {
		
		List<User> list = new ArrayList<User>();

		try {
			
			String orderSubQuery = "";
			
			String queryfinal = (QueryStrings.GET_ACTIVE_USERS_LIST_BY_LASTLOGIN_DATE)
					.getQuery();
			
			if(roleId!=null)
				  queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId+" AND qbis_users.permissionId = "+roleId;
				else
					queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId;
			
			if (dateRange != null) {
				
				String date[] = dateRange.split("-");
					
					queryfinal = queryfinal
							+ " AND user_login_activity_details.last_login_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					
					orderSubQuery = "GROUP BY qbis_users.user_id ORDER BY qbis_users.last_login DESC";
			}
			
			queryfinal = queryfinal + orderSubQuery;
			
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1));
				user.setLastName(row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRoleDesc(row.getRowItem(5));
				user.setGroupName(row.getRowItem(6));
				user.setLastLoginDate(row.getRowItem(7));				
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	
	/**
	 * This method is used for getting Active group list using filter.
	 * (Active Users OR Group by Date Report)
	 * @param userId
	 * @param roleId	 
	 * @param dateRange
	 * @return list
	 */
	public List<User> getActiveGroupByDate(Integer userId,Integer roleId,String dateRange) {
		
		List<User> list = new ArrayList<User>();

		try {
									
			String queryfinal = (QueryStrings.GET_ACTIVE_USERS_LIST_BY_LASTLOGIN_FOR_GROUP)
					.getQuery();
			if(roleId!=null)
			  queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId+" AND qbis_users.permissionId = "+roleId;
			else
				queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId;
			if (dateRange != null) {
				
				String date[] = dateRange.split("-");
					
					queryfinal = queryfinal
							+ " AND user_login_activity_details.last_login_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
			}
						
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				if(row.getRowItem(0)!=null){
					QueryData datagroup = QueryManager.execuateQuery(
							QueryStrings.GET_ACTIVE_GROUP_LIST_BY_USERIDS,
							new Object[] {userId,row.getRowItem(0)});
					for (QueryData.Row rowgroup : datagroup.getRows()) {
						User user = new User();
						user.setGroupId(Integer.parseInt(rowgroup.getRowItem(0)));
						user.setGroupName(rowgroup.getRowItem(1));
						user.setUserMapInGroupCount(Integer.parseInt(rowgroup.getRowItem(2)));
						user.setJoinedOn(rowgroup.getRowItem(3));
						user.setUserName(rowgroup.getRowItem(4));
						list.add(user);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	
	/**
	 * This method is used for getting list of users.
	 * 
	 * @param userId	 
	 * @return list
	 */
	public List<User> getUsersListByCreaterId(Integer userId) {
		List<User> list = new ArrayList<User>();
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_USERS_LIST_BY_CREATERID,
				new Object[] {userId});
		for (QueryData.Row row : datagroup.getRows()) {
			User user = new User();
			user.setUserId(Integer.parseInt(row.getRowItem(0)));
			if(row.getRowItem(1)!=null){
				user.setUserName(row.getRowItem(2)!=null?(row.getRowItem(1)+" "+row.getRowItem(2)):row.getRowItem(1));
			}else{
				user.setUserName("Guest User");
			}
			list.add(user);
		}
		return list;
	}
	
	/**
	 * This method is used for getting  Course/Assessment/Question Library/Content Library details of user.
	 * (Completed Transcripts by User Report - Course/Assessment Report)
	 * @param userId	 
	 * @return list
	 */
	public User getCompletedAssessmentCourseData(User user) {		
		
		ArrayList<AppFunctions> appfunctionList = RoleService
				.getApplicationMenu(user.getRoleId());
		
		/*try {
		    ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(appfunctionList));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(user.getFirstName()!=""){
			user.setUserName(user.getLastName()!=""?(user.getFirstName()+" "+user.getLastName()):user.getFirstName());
		}else{
			user.setUserName("Guest User");
		}
		if(checkAppFunctionSubFunction(FunctionSubFunEnum.Assessment.getIntValue(),
				FunctionSubFunEnum.MyAssessment.getIntValue(),appfunctionList)){			
			user.setTestList(getAttemptedTestDetails(user.getUserId()));			
		}
		
		if(checkAppFunctionSubFunction(FunctionSubFunEnum.Courses.getIntValue(),
				FunctionSubFunEnum.CreateNewCourse.getIntValue(),appfunctionList)){
			user.setCreatedCourseList(getCreatedCourseDetails(user.getUserId()));			
		}
		
		if(checkAppFunctionSubFunction(FunctionSubFunEnum.Assessment.getIntValue(),
				FunctionSubFunEnum.CreateNewAssessment.getIntValue(),appfunctionList)){			
			user.setCreatedTestList(getCreatedTestDetails(user.getUserId()));
		}
		
		
		if(checkAppFunctionSubFunction(FunctionSubFunEnum.ContentLibrary.getIntValue(),
				FunctionSubFunEnum.ContentLibrarySub.getIntValue(),appfunctionList)){			
				user.setContentDetails(getUploadContentDetails(user.getUserId()));
		}
		
		if(checkAppFunctionSubFunction(FunctionSubFunEnum.QuestionLibrary.getIntValue(),
				FunctionSubFunEnum.QuestionLibrarySub.getIntValue(),appfunctionList)){
			
			user.setQuestionCount(getQuestionLibraryDetails(user.getUserId()));
		}
		
		return user;
	}
	
	private int getQuestionLibraryDetails(Integer userid) {
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_CREATED_QUESTION_COUNT_BY_USERID,
				new Object[] {userid});
		int k = 0;
		if(datagroup.getRows().size()>0){				
			for (QueryData.Row row : datagroup.getRows()) {					    
				k = Integer.parseInt(row.getRowItem(0));
			}				
		}
		return k;
	}

	/**
	 * This method for check function/sub function assign to user.
	 * @param functionId
	 * @param subFunctionId	 
	 * @return boolean, if assign then true,else false
	 */
	public boolean checkAppFunctionSubFunction(int functionId,int subFunctionId,ArrayList<AppFunctions> list){
		boolean flag = false;
		ArrayList<AppFunctions> appFunctions = list;
        for (int i = 0; i < appFunctions.size(); i++) {			
			if(appFunctions.get(i).getFunctionId()==functionId){
				for (int j = 0; j < appFunctions.get(i).getSubAppFuntion().size(); j++) {
					if(appFunctions.get(i).getSubAppFuntion().get(j).getSubFunctionId()==subFunctionId){
						flag = true;
						break;
					}
				}
			}
			
		}        
        return flag;
	}

	
	/**
	 * This method is used for getting Active users list using filter.
	 * (User Login Activity Report)
	 * @param userId	 
	 * @param dateRange
	 * @return list of users
	 */
	public List<User> getActiveLoginUsersByDate(Integer userId, String dateRange) {
		List<User> list = new ArrayList<User>();

		try {
			
			String orderSubQuery = "";
			
			String queryfinal = (QueryStrings.GET_ACTIVE_USERS_LIST_BY_LASTLOGIN)
					.getQuery();
			
			queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId;
			
			if (dateRange != null) {
				
				String date[] = dateRange.split("-");
					
					queryfinal = queryfinal
							+ " AND user_login_activity_details.last_login_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					
					orderSubQuery = "GROUP BY qbis_users.user_id ORDER BY qbis_users.last_login DESC";
			}
			
			queryfinal = queryfinal + orderSubQuery;
			
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1));
				user.setLastName(row.getRowItem(2));				
				user.setEmail(row.getRowItem(3));
				user.setBrowser(row.getRowItem(4));
				user.setOs(row.getRowItem(5));
				user.setIpAddress(row.getRowItem(6));
				user.setLastLoginDate(row.getRowItem(7));				
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ERROR,
					"Exception Inside Report service getActiveLoginUsersByDate method:::::", e);
		}
		return list;
	}
	
	
	/**
	 * This method is used for getting Active users activities list using filter.
	 * (Active User OR Group Activity Report)
	 * @param userId
	 * @param roleId	 
	 * @param dateRange
	 * @return
	 */
	public List<User> getActiveUserActivityByDate(Integer userId,Integer roleId,String dateRange) {
		
		List<User> list = new ArrayList<User>();		
		try {
			
			String orderSubQuery = "";
			
			String queryfinal = (QueryStrings.GET_ACTIVE_USERS_LIST_BY_LASTLOGIN_DATE)
					.getQuery();
			if(roleId!=null)
			  queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId +" AND qbis_users.permissionId = "+roleId;
			else
				queryfinal = queryfinal	+ " WHERE qbis_users.created_by = "+userId;
			if (dateRange != null) {
				
				String date[] = dateRange.split("-");
					
					queryfinal = queryfinal
							+ " AND user_login_activity_details.last_login_date BETWEEN '"
							+ date[0] + "' AND '" + date[1] + "'";
					
					orderSubQuery = "GROUP BY qbis_users.user_id ORDER BY qbis_users.last_login DESC";
			}
			
			queryfinal = queryfinal + orderSubQuery;
			
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1));
				user.setLastName(row.getRowItem(2));				
				user.setEmail(row.getRowItem(4));
				user.setRoleId(Integer.parseInt(row.getRowItem(8)));
				ArrayList<AppFunctions> appfunctionList = RoleService
						.getApplicationMenu(user.getRoleId());
				if(checkAppFunctionSubFunction(FunctionSubFunEnum.Courses.getIntValue(),
						FunctionSubFunEnum.MyCourses.getIntValue(),appfunctionList)){
					String enrollStr = getEnrolledCourseDetails(user.getUserId());
					if(enrollStr!=null){
						user.setEnrollCourseCount(Integer.parseInt(enrollStr.split("##")[0]));
						user.setCourseName(enrollStr.split("##")[1]);						
					}
					
					String str = getLastViewedContentNameAndCount(user.getUserId());
					if(str!=null){
						String arr[] = str.split("@@");
						user.setLastViewContent(arr[0]+"/"+arr[1]);						
						user.setViewContentCount(Integer.parseInt(arr[2]));						
					}else{						
						user.setViewContentCount(0);
					}
					
				}else{
					user.setEnrollCourseCount(0);
					user.setViewContentCount(0);
				}
				
				if(checkAppFunctionSubFunction(FunctionSubFunEnum.Assessment.getIntValue(),
						FunctionSubFunEnum.MyAssessment.getIntValue(),appfunctionList)){
					List<Test> testList = getAttemptedTestDetails(user.getUserId());
					if(testList.size()>0){
					   user.setTestName(testList.get(0).getTestName());
					   user.setAttemptsCount(testList.size());
					}					
				}else{
					user.setAttemptsCount(0);
				}
				
				if(checkAppFunctionSubFunction(FunctionSubFunEnum.Courses.getIntValue(),
						FunctionSubFunEnum.CreateNewCourse.getIntValue(),appfunctionList)){					
					user.setCourseCount(getCreatedCourseDetails(user.getUserId()).size());
				}else{
					user.setCourseCount(0);
				}
				
				if(checkAppFunctionSubFunction(FunctionSubFunEnum.Assessment.getIntValue(),
						FunctionSubFunEnum.CreateNewAssessment.getIntValue(),appfunctionList)){
					user.setTestCount(getCreatedTestDetails(user.getUserId()).size());					
				}else{
					user.setTestCount(0);
				}
				
				
				if(checkAppFunctionSubFunction(FunctionSubFunEnum.ContentLibrary.getIntValue(),
						FunctionSubFunEnum.ContentLibrarySub.getIntValue(),appfunctionList)){			
						user.setContentDetails(getUploadContentDetails(user.getUserId()));
				}
				
				if(checkAppFunctionSubFunction(FunctionSubFunEnum.QuestionLibrary.getIntValue(),
						FunctionSubFunEnum.QuestionLibrarySub.getIntValue(),appfunctionList)){					
					user.setQuestionCount(getQuestionLibraryDetails(user.getUserId()));
				}else{
					user.setQuestionCount(0);
				}
								
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	private String getLastViewedContentNameAndCount(Integer userId) {
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_CONTENT_VIEWED_ACTIVITY_BY_USERID,
				new Object[] {userId});
			String str = "";
			int i = 0;
			for (QueryData.Row row : datagroup.getRows()) {
				i++;
				if(i==1){
					str += row.getRowItem(1)+"@@"+row.getRowItem(2)+"@@"+row.getRowItems().size();
					System.out.println("str==========="+str);
				}
			}
			return str;		
	}

	/**
	 * This method is used for getting attempted test data by userId.
	 * @param userId
	 * @return list of test
	 */
	private List<Test> getAttemptedTestDetails(Integer userId) {
		List<Test> testList = new ArrayList<Test>();
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_USER_ATTEMPTED_TEST_DATA_BY_USERID,
				new Object[] {userId});			
		if(datagroup.getRows().size()>0){			
			for (QueryData.Row row : datagroup.getRows()) {					
									
					Test test = new Test();												
					test.setTestId(Integer.parseInt(row.getRowItem(0)));
					test.setTestName(row.getRowItem(1));
					test.setNoOfAttempted(Integer.parseInt(row.getRowItem(2)));
					test.setDate(row.getRowItem(3));
					test.setMaxMark(Double.parseDouble(row.getRowItem(4)));
					
					double flag = (Double.parseDouble(row.getRowItem(4)) * 100)
							/ Double.parseDouble(row.getRowItem(5));

					if (flag < 33.0) {
						test.setTestTag(test.getMaxMark()+"/Fail");						
					} else {
						test.setTestTag(test.getMaxMark()+"/Pass");						
					}
					testList.add(test);										
			}
			
		}
		return testList;
	}
	
	/**
	 * Get enrolled course count and last enrolled date by userId.
	 * @param userId
	 * @return String
	 */
	private String getEnrolledCourseDetails(Integer userId) {
		QueryData data = QueryManager.execuateQuery(
				QueryStrings.GET_ENROLLED_COURSE_DETAILS_USERID,
				new Object[] {userId});
		String str = null;				
						
			for (QueryData.Row row : data.getRows()) {
				if(Integer.parseInt(row.getRowItem(0))!=0){
					str = row.getRowItem(0)+"##"+row.getRowItem(1);					
				}				
			}				
		
		return str;
	}
	
	
	/**
	 * Get created course details by userId.
	 * @param userId
	 * @return list of courses
	 */
	private List<Course> getCreatedCourseDetails(Integer userId){
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_CREATED_COURSE_DATA_BY_USERID,
				new Object[] {userId});
		List<Course> courseList = new ArrayList<Course>();
		if(datagroup.getRows().size()>0){
			
			for (QueryData.Row row : datagroup.getRows()) {					
									
				    Course course = new Course();												
				    course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				    course.setCourseName(row.getRowItem(1));
					if(row.getRowItem(3).equals("1")){
						course.setStatus("Scheduled");	
					}else if(row.getRowItem(2).equals("1")){
						course.setStatus("Published");
					}else{
						course.setStatus("Drafted");
					}
					course.setCourseInfo(row.getRowItem(4));// for assignee
					course.setDate(row.getRowItem(5));												
					courseList.add(course);										
			}			
	      }
		return courseList;
	}
	
	/**
	 * Get created test details by userId.
	 * @param userId
	 * @return list of test
	 */
	private List<Test> getCreatedTestDetails(Integer userId){
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_CREATED_TEST_DATA_BY_USERID,
				new Object[] {userId});
		List<Test> testList = new ArrayList<Test>();
		if(datagroup.getRows().size()>0){			
			for (QueryData.Row row : datagroup.getRows()) {					
									
				    Test test = new Test();												
					test.setTestId(Integer.parseInt(row.getRowItem(0)));
					test.setTestName(row.getRowItem(1));						
					if(row.getRowItem(3).equals("1")){
						test.setTestTag("Scheduled");	// for status
					}else if(row.getRowItem(2).equals("1")){
						test.setTestTag("Published");
					}else{
						test.setTestTag("Drafted");
					}
					test.setTestDesc(row.getRowItem(4)); // for assignee
					test.setDate(row.getRowItem(5));												
					testList.add(test);										
			}
			
		}
		return testList;
	}
	
	
	/**
	 * Get uploaded contents details by userId.
	 * @param userId
	 * @return SectionContent
	 */
	private SectionContent getUploadContentDetails(Integer userId){
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_CONTENT_LIBRARY_DATA_BY_USERID,
				new Object[] {userId});			
		    DecimalFormat df = new DecimalFormat("####0.00");
			SectionContent content = new SectionContent();
			for (QueryData.Row row : datagroup.getRows()) {
				if(Integer.parseInt(row.getRowItem(0))>0){
				    content.setContentOrder(Integer.parseInt(row.getRowItem(0)));					    
				    Long usedSpaceGB = CommonUtil.convertBytesToGB(Long.parseLong(row.getRowItem(1)));				    
					if(usedSpaceGB>0){
						content.setAdditionalInfo((df.format(usedSpaceGB)+" GB"));							
					}else{						
						usedSpaceGB = CommonUtil.convertBytesToMB(Long.parseLong(row.getRowItem(1)));
						content.setAdditionalInfo((df.format(usedSpaceGB)+" MB"));						
					}
				}else{
					content.setContentOrder(0);
					content.setAdditionalInfo("0");
				}
			}
			return content;
	}

	
	/**
	 * This method is used for getting Individual User Profile Audit Report using filter.
	 * (Individual User Profile Audit Report)
	 * @param userId	 
	 * @return
	 */	
	public List<User> getFilterAuditProfileUsers(Integer userId) {
		List<User> list = new ArrayList<User>();

		try {
			
			String orderSubQuery = "";
			
			String queryfinal = (QueryStrings.GET_AUDIT_PROFILE_USER)
					.getQuery();
			
			queryfinal = queryfinal	+ " WHERE qbis_users.user_id = "+userId;
			
			queryfinal = queryfinal + orderSubQuery;
			
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1));
				user.setLastName(row.getRowItem(2));				
				user.setEmail(row.getRowItem(3));
				user.setRoleDesc(row.getRowItem(4));				
				user.setAccountModifyDate(row.getRowItem(5));				
				user.setAccountModifyByName(row.getRowItem(8)!=null?
						(row.getRowItem(7)+" "+row.getRowItem(8)):row.getRowItem(7));
				user.setRoleModifyDate(row.getRowItem(9));
				user.setRoleModifiedByName(row.getRowItem(12)!=null?
						(row.getRowItem(11)+" "+row.getRowItem(12)):row.getRowItem(11));
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ERROR,
					"Exception Inside Report service getFilterAuditProfileUsers method:::::", e);
		}
		return list;
	}

	
	/**
	 * This method is used for getting enroll courses and participant details.
	 * (Course Enrollment Status Report)
	 * @param userId	 
	 * @return
	 */	
	public User courseEnrollmentReportData(Integer trainerId, Integer status, String dateRange) {
		User user = new User();
		List<Course> list = new ArrayList<Course>();
		String inCourseId = null;
		StringBuilder sb = new StringBuilder(); 
		
		try {
			
			String orderSubQuery = "";
			
			String queryfinal = (QueryStrings.GET_COURSE_ENROLLMENT_DATA_BY_FILTERS)
					.getQuery();
			
			queryfinal = queryfinal	+ " WHERE course_manager.user_id = "+trainerId;
			
			if (dateRange != null) {
					queryfinal = queryfinal
							+ " AND DATE(course_assessment.enroll_date_time) ='" + dateRange + "'";
			}
			
			if (status!=-1 ) {
				queryfinal = queryfinal
						+ " AND course_assessment.isEnroll ="+status;
		     }
			
			orderSubQuery = " group by course_assessment.course_id ORDER BY qbis_users.first_name";			
			queryfinal = queryfinal + orderSubQuery;
			
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setCourseName(row.getRowItem(1));
				course.setDate(row.getRowItem(2));
				if(row.getRowItem(3)!=null){
					if(row.getRowItem(4)!=null){
						course.setTeacherName(row.getRowItem(3)+" "+row.getRowItem(4));	
					}						
				}
				course.setEnrollCount(Integer.parseInt(row.getRowItem(5)));				
				sb.append(row.getRowItem(0)+",");				
				list.add(course);
			}
			user.setCreatedCourseList(list);
			System.out.println(sb);
			if(list.size()>0){
				inCourseId = sb!=null?sb.deleteCharAt(sb.lastIndexOf(",")).toString():null;
				
				if(inCourseId!=null){
					user.setCourseList(getEnrollParticipantDetails(inCourseId));
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ERROR,
					"Exception Inside Report service courseEnrollmentReportData method:::::", e);
		}
		return user;
	}
	
	/**
	 * Get Enroll Participant Details by multiple course id's.
	 * @param inCourseId
	 * @return list of courses
	 */
	private List<Course> getEnrollParticipantDetails(String inCourseId){
		QueryData datagroup = QueryManager.execuateQuery(
				QueryStrings.GET_ENROLL_COURSE_PARTICIPANT_DATA,
				new Object[] {inCourseId});
		List<Course> courseList = new ArrayList<Course>();
		if(datagroup.getRows().size()>0){			
			for (QueryData.Row row : datagroup.getRows()) {					
									
				    Course course = new Course();												
				    course.setCourseId(Integer.parseInt(row.getRowItem(0)));
					course.setCourseName(row.getRowItem(1));					
					if(row.getRowItem(2)!=null){
						if(row.getRowItem(3)!=null){
							course.setTeacherName(row.getRowItem(2)+" "+row.getRowItem(3));	
						}						
					}
					course.setStatus(row.getRowItem(4).equals("1")?"Enrolled":"Cancel");
					course.setCourseInfo(row.getRowItem(5));
					courseList.add(course);										
			}			
	      }
		return courseList;
	}
	
	/**
	 * Get course session/view report data.
	 * @param userId who view the report
	 * @param courseId
	 * @param loginUserId who created courses
	 * @return list of courses
	 */
	public List<Course> courseSessionReportData(Integer userId, Integer courseId,Integer loginUserId) {
		List<Course> list = new ArrayList<Course>();

		try {
			
			String orderSubQuery = "";
			
			String queryfinal = (QueryStrings.GET_VIEWED_COURSE_BY_USER)
					.getQuery();
			
			queryfinal = queryfinal	+ " WHERE course_manager.user_id = "+loginUserId;
			
			if(userId!=-1){
				queryfinal = queryfinal	+ " AND activity_user_course_view.user_id = "+userId;
			}
			
			if(courseId!=-1){
				queryfinal = queryfinal	+ " AND activity_user_course_view.course_id = "+courseId;
			}
			
			queryfinal = queryfinal + orderSubQuery;
			
			QueryData data = QueryManager.execuateQuery(queryfinal,
					new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseName(row.getRowItem(0));
				String username = row.getRowItem(2)!=null?row.getRowItem(1)+" "+row.getRowItem(2):row.getRowItem(1);
				course.setLevelName(username); //user name
				course.setDate(row.getRowItem(3)); //start date
				course.setCourseModifyTime(row.getRowItem(4)); // Time spent
				list.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.ERROR,
					"Exception Inside Report service courseSessionReportData method:::::", e);
		}
		return list;		
	}
	
}
