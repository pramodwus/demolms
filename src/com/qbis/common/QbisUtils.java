package com.qbis.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.qbis.model.ServiceMessage;

/**
 * Utility class containing some common method to be used in this application.
 * 
 * @author Vivek Kumar.
 *
 */
public class QbisUtils {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(QbisUtils.class);
	/**
	 * Variable to store instance of {@link MessageSource} configured in
	 * applicationContext.xml.
	 */
	private static MessageSource messageSource;
	/**
	 * Constant to load asaan.properties file.
	 */
	private static final Properties VR_PROPS;
	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Static code block to instantiate MessageSource.
	 */
	static {

		WebApplicationContext webAppContext = ContextLoader.getCurrentWebApplicationContext();
		messageSource = (MessageSource) webAppContext.getBean("messageSource");

		VR_PROPS = new Properties();
		try {
			VR_PROPS.load(QbisUtils.class.getClassLoader().getResourceAsStream("message.properties"));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Method to get lit of {@link ServiceMessage} from List of
	 * {@link ObjectError}
	 * 
	 * @param resultErrors
	 * @return
	 */
	public static List<ServiceMessage> getServiceMessage(List<ObjectError> resultErrors) {

		List<ServiceMessage> resultlist = new ArrayList<ServiceMessage>();
		for (ObjectError obj : resultErrors) {
			ServiceMessage code = null;
			if (obj instanceof FieldError) {
				FieldError fieldError = (FieldError) obj;
				code = new ServiceMessage(obj.getCode(), obj.getArguments(), fieldError.getField(), obj.getObjectName(),
						fieldError.getRejectedValue(), fieldError.isBindingFailure());
			} else {
				code = new ServiceMessage(obj.getCode(), obj.getArguments(), null, obj.getObjectName(), null, false);
			}

			resultlist.add(code);
		}

		return resultlist;

	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Method to get instance of {@link MessageSource} configured in
	 * applicationContext.xml.
	 * 
	 * @return
	 */
	public static MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * Method to fetch property value.
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropValue(String key) {

		return VR_PROPS.getProperty(key);

	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Method to convert Date object to end of day Date object by setting hour
	 * to 23, minutes to 59, seconds to 59 and milliseconds to 999
	 * 
	 * @param p_date
	 * @return
	 */
	public static Date convertDatetoEOD(Date p_date) {
		p_date = DateUtils.setHours(p_date, 23);
		p_date = DateUtils.setMinutes(p_date, 59);
		p_date = DateUtils.setSeconds(p_date, 59);
		p_date = DateUtils.setMilliseconds(p_date, 999);

		return p_date;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Method to convert Date object to beginning of day Date object by setting
	 * hour to 00, minutes to 00, seconds to 00 and milliseconds to 000
	 * 
	 * @param p_date
	 * @return
	 */
	public static Date convertDatetoBOD(Date p_date) {
		p_date = DateUtils.setHours(p_date, 0);
		p_date = DateUtils.setMinutes(p_date, 0);
		p_date = DateUtils.setSeconds(p_date, 0);
		p_date = DateUtils.setMilliseconds(p_date, 0);

		return p_date;
	}

	/**
	 * Method is used for converting to a date format into mysql date format.
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String convertDatetoMysqlDateFormat(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
		Date convertedCurrentDate = sdf.parse(date);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String converteddate = sdf2.format(convertedCurrentDate);
		return converteddate;
	}

	public static String convertMysqlDateFormatToDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date convertedCurrentDate = sdf.parse(date);
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
		String converteddate = sdf2.format(convertedCurrentDate);
		return converteddate;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {

		/*
		 * VRUser user = new VRUser(); user.setUserid("vradmin"); try {
		 * user.setPassword(encryptPassword("vradmin")); } catch (Exception e) {
		 * e.printStackTrace(); } user.setFname("VR admin");
		 * user.setLastname("User"); user.setAdminUser(true);
		 * user.setEmailId("test@testadmin.com"); MongoClient test = new
		 * MongoClient("localhost", 27017); MongoDatabase db =
		 * test.getDatabase("videoresume");
		 * 
		 * Document doc = new Document().append("userid", user.getUserid())
		 * .append("password", user.getPassword()) .append("fname",
		 * user.getFname()) .append("lname", user.getLastname())
		 * .append("isAdminUser", user.isAdminUser()) .append("emailId",
		 * user.getEmailId()); db.getCollection("users").insertOne(doc);
		 */
		/*
		 * QuestionInvite quest = new QuestionInvite();
		 * quest.setEmailId("abc@test.com"); quest.setQuestions(new String[] {
		 * "What is your qualification ?"}); MongoClient test = new
		 * MongoClient("localhost", 27017); MongoDatabase db =
		 * test.getDatabase("videoresume"); Document doc = new
		 * Document().append("emailId", quest.getEmailId()) .append("questions",
		 * quest.getQuestions());
		 * 
		 * db.getCollection("questioninvite").insertOne(doc);
		 */

	}
	// ------------------------------------------------------------------------------------------------------------------

}
