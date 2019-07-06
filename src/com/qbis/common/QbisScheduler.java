package com.qbis.common;

import java.text.SimpleDateFormat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qbis.gcm.NotificationManagement;
import com.qbis.services.CourseService;
import com.qbis.services.TestService;
import com.qbis.services.UserService;

@Component
@EnableScheduling
public class QbisScheduler {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(QbisScheduler.class);
	/**
	 * Instance of {@link SimpleDateFormat}
	 */
   // private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:mm:dd HH:mm:ss");

  @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        TestService.makePublishedtoScheduleTest();
        CourseService.makePublishedtoScheduleCourse();
    }
    
    
	/**
	 * Scheduler to notify to users that their profile is incomplete.  
	 * Duration every seven day at 12:45 pm	 
	 */
    @Scheduled(cron = "0 37 12 1/7 * ?")
    public void profileInCompleteScheduler() {
    	logger.log(Level.DEBUG, "inside qbis scheduler profileInCompleteScheduler method");
        UserService.getListInCompleteProfileUsers();        
    }
    
    /**
	 * Scheduler to notify to Authors that drafted course are pending from long time  
	 * Duration every 3 days at 11:26 am	 
	 */
    @Scheduled(cron = "0 26 11 1/3 * ?")	
	public void reminderDratedCourseScheduler(){
		logger.log(Level.DEBUG, "reminderDratedCourse schedule::::::::");
		NotificationManagement.draftedCourseReminder();
	}
    
    /**
	 * Scheduler to notify to Authors that drafted test are pending from long time  
	 * Duration every 3 days at 12:38 pm	 
	 */
    @Scheduled(cron = "0 38 12 1/3 * ?")	
	public void reminderDratedTestScheduler(){
		logger.log(Level.DEBUG, "reminderDratedTestScheduler schedule::::::::");
		NotificationManagement.draftedTestReminder();
	}
    
    /**
	 * Scheduler to notify to users that their organization license is expire in 3 days.  
	 * Duration every 1 days at 17:02 pm	 
	 */
    @Scheduled(cron = "0 02 17 1/1 * ?")	
	public void reminderLicenseExpireScheduler(){
		logger.log(Level.DEBUG, "reminderLicenseExpireScheduler schedule::::::::");
		NotificationManagement.reminderLicenseExpireScheduler();
	}
    
}
