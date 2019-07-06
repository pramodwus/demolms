package com.qbis.gcm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.qbis.common.ConstantUtil;
import com.qbis.common.EmailSender;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Course;
import com.qbis.model.Notification;
import com.qbis.model.SectionContent;
import com.qbis.model.Test;
import com.qbis.services.NotificationService;
/**
 * This class manage and contains all notifications methods used in Qbis application.
 * 
 * @author Neeraj singh.
 *
 */

public class NotificationManagement {
	
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(NotificationManagement.class);
	/*@Autowired
	private AppUserDAO appUserdao;*/
	static GCMNotifier gcmnotifier = new GCMNotifier(ConstantUtil.GCM_KEY);

	
	/**
	 * Pushed Notification Created By Admin 
	 **/
	public  void pushedNotificationCreatedByAdmin(Map<Integer,String> devices,Notification notification,String imagename)
	{
		try{
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("MessageTitle", "Qbis");
						jsonObject.put("MessageContent", notification.getNotificationMessage());
						jsonObject.put("IconUrl", (ConstantUtil.PROFILE_IMAGE_PATH + ConstantUtil.NOTIFICATION_FILE+imagename));
						jsonObject.put("notifyscreen", 4);				    	 
				    	gcmnotifier.sendNotification(devices, jsonObject.toString(), "");		    	    
		   }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Send Notification 
	 * For Upcoming test to student
	 * */
	public void sendNotificationUpcomingTest(Map<Integer,String> devices,String notificationMsg,int testId)
	{
		
		try
		{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("MessageTitle", "Upcoming Test");
			jsonObject.put("IconUrl",(ConstantUtil.SERVER_IP+ConstantUtil.IMAGE_DIRECTORY+"qbislogo.png"));
			jsonObject.put("MessageContent", notificationMsg);
			jsonObject.put("testId", testId);
			gcmnotifier.sendNotification(devices, jsonObject.toString(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Send Notification 
	 * on test publish to student
	 * */
	public void sendNotificationPublishedTest(Map<Integer,String> devices,String notificationMsg,int testId)
	{
		
		try
		{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("MessageTitle", "Published Test");
			jsonObject.put("IconUrl",(ConstantUtil.SERVER_IP+ConstantUtil.IMAGE_DIRECTORY+"qbislogo.png"));
			jsonObject.put("MessageContent", notificationMsg);
			jsonObject.put("testId", testId);
			gcmnotifier.sendNotification(devices, jsonObject.toString(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Send Notification 
	 * on course publish to student
	 * */
	public void sendNotificationPublishCourse(Map<Integer,String> devices,String notificationMsg,int courseId)
	{
		
		try
		{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("MessageTitle", "Published Course");
			jsonObject.put("IconUrl",(ConstantUtil.SERVER_IP+ConstantUtil.IMAGE_DIRECTORY+"qbislogo.png"));
			jsonObject.put("MessageContent", notificationMsg);
			jsonObject.put("courseId", courseId);
			gcmnotifier.sendNotification(devices, jsonObject.toString(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Send Notification 
	 * For Upcoming course to student
	 * */
	public void sendNotificationUpcomingCourse(Map<Integer,String> devices,String notificationMsg,int courseId)
	{		
		try
		{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("MessageTitle", "Upcoming Course");
			jsonObject.put("IconUrl",(ConstantUtil.SERVER_IP+ConstantUtil.IMAGE_DIRECTORY+"qbislogo.png"));
			jsonObject.put("MessageContent", notificationMsg);
			jsonObject.put("courseId", courseId);
			gcmnotifier.sendNotification(devices, jsonObject.toString(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Send Notification 
	 * to incomplete profile trainee.
	 * */
	public void sendNotificationIncompleteProfileUsers(Map<Integer,String> devices,String notificationMsg,int userId)
	{		
		try
		{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("MessageTitle", "Profile Incomplete");
			jsonObject.put("IconUrl",(ConstantUtil.SERVER_IP+ConstantUtil.IMAGE_DIRECTORY+"qbislogo.png"));
			jsonObject.put("MessageContent", notificationMsg);
			jsonObject.put("userId", userId);
			gcmnotifier.sendNotification(devices, jsonObject.toString(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to get list of drafted test and notify to Authors that drafted test are pending from long time
	 */
	public static void draftedTestReminder() {
		
		try {
			logger.log(Level.DEBUG,
					"Inside NotificationManagement in draftedTestReminder method ::::::");						
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.DRAFTED_TEST_FROM_LONG_PERIOD,
					new Object[] {  });
			for (QueryData.Row row : data.getRows()) {
				Test test = new Test();
				test.setTestId(Integer.parseInt(row.getRowItem(0)));
				test.setUserId(Integer.parseInt(row.getRowItem(1)));
				test.setTestName(row.getRowItem(2));
				String creater = row.getRowItem(3)!=null?row.getRowItem(3):"Guest";
				String text = (ConstantUtil.DRAFTED_TEST_PENDING
						      .replace("#TEACHERNAME",creater))
						      .replace("#TESTNAME",test.getTestName());
						
	            String url = "viewTestDetail?testId="+test.getTestId();
	            String[] targetIds = {row.getRowItem(1)};
				NotificationService.saveNotifcation(text, url, targetIds);
				
			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Method to get list of drafted course and notify to Authors that drafted course are pending from long time
	 */
	public static void draftedCourseReminder() {
		
		try {
			logger.log(Level.DEBUG,
					"Inside NotificationManagement in draftedCourseReminder method ::::::");						
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.DRAFTED_COURSE_FROM_LONG_PERIOD,
					new Object[] {  });
			for (QueryData.Row row : data.getRows()) {
				Course course = new Course();
				course.setCourseId(Integer.parseInt(row.getRowItem(0)));
				course.setUserId(Integer.parseInt(row.getRowItem(1)));
				course.setCourseName(row.getRowItem(2));
				String creater = row.getRowItem(3)!=null?row.getRowItem(3):"Guest";
				String text = (ConstantUtil.DRAFTED_COURSE_PENDING
						      .replace("#TEACHERNAME",creater))
						      .replace("#COURSENAME",course.getCourseName());
						
	            String url = "courseViewController?courseId="+course.getCourseId()+"&isPublish=0";
	            String[] targetIds = {row.getRowItem(1)};
				NotificationService.saveNotifcation(text, url, targetIds);
				
			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Method to notify authour that student report some abuse comment on course content.
	 */
	public static void abuseReportNotificationToTeacher(SectionContent content) {
		
		try {
			logger.log(Level.DEBUG,
					"Inside NotificationManagement in abuseReportNotificationToTeacher method ::::::");			
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_TEACHER_STUDENT_CONTENT_BY_ABUSE_REPORTID,
					new Object[] { content.getReportId() });
			for (QueryData.Row row : data.getRows()) {
				SectionContent contentobj = new SectionContent();
				contentobj.setContentName(row.getRowItem(0)!=null?row.getRowItem(0):row.getRowItem(1));
				contentobj.setContentId(Integer.parseInt(row.getRowItem(4)));
				String teacher = row.getRowItem(2)!=null?row.getRowItem(2):"Trainer";
				String student = row.getRowItem(3)!=null?row.getRowItem(3):"Traniee";
				
				String text = ((ConstantUtil.REPORT_ABUSED_CONTENT
						      .replace("#TEACHERNAME",teacher))
						      .replace("#STUDENTNAME",student))
						      .replace("#CONTENTNAME",contentobj.getContentName());
						
	            String url = "viewuploadcontent?contentId="+contentobj.getContentId()+"&courseId="+content.getCourseId();
	            String[] targetIds = {row.getRowItem(5)};
				NotificationService.saveNotifcation(text, url, targetIds);				
			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	
	/**
	 * Method to notify user that their license is expire in 3 days.
	 */
	public static void reminderLicenseExpireScheduler(){
		
		try {
			logger.log(Level.DEBUG,
					"Inside NotificationManagement in reminderLicenseExpireScheduler method ::::::");			
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_ORGID_EXPIRE_IN_FEW_DAYS,
					new Object[] {  });
			if(data.getRows().size()>0){
				
				for (QueryData.Row row : data.getRows()) {	
					
					QueryData datauser = QueryManager.execuateQuery(
							QueryStrings.GET_USERS_DATA_BY_ORGID,
							new Object[] {Integer.parseInt(row.getRowItem(0))});
					
					for (QueryData.Row rowuser : datauser.getRows()) {
						
						final String username = rowuser.getRowItem(2)!=null?rowuser.getRowItem(2):"Guest";
						final String email = rowuser.getRowItem(1);
						String text = (ConstantUtil.LICENSE_EXPIRE_FEW_DAYS
								      .replace("#USERNAME",username));
								
			            String url = "dummy?userId="+rowuser.getRowItem(0);
			            String[] targetIds = {rowuser.getRowItem(0)};
						NotificationService.saveNotifcation(text, url, targetIds);
						
						final Map<Object, Object> dataobject = new HashMap<Object, Object>();						
						dataobject.put("name", username);
						dataobject.put("days", 3);
						(new Thread() {
							@Override
							public void run() {
								try {
									EmailSender.sendEmail(email,
											"IMPORTANT - QBis Expiry Notification",
											EmailSender.generateQbisExpireAlertMessg(dataobject)
											);					
								} catch (Exception e) {					
									e.printStackTrace();
								}
								
							}
						}).start();
						
					}					
					
				}
				
			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}