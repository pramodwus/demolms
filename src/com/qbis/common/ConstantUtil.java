package com.qbis.common;

import java.util.Properties;

/**
 * @author Mukesh
 * @version 1.0
 */
public class ConstantUtil {

	// location to store file uploaded
	public static String PDF_UPLOAD_PATH;
	public static String UPLOAD_DIRECTORY;
	public static String SQLLITE_FILE_PATH;
	public static String SQLLITE_FILE_DIRECTORY;
	public static String SERVER_IP;
	public static String ICON_PATH;
	public static String PROFILE_IMAGE_PATH;
	public static String IMAGE_DIRECTORY;
	public static String FILE_MANAGER_PATH;
	public static String COURSE_FILE_DIRECTORY;
	public static String JAVA_BRIDGE_PATH;
	public static String APPLICATION_ID;
	public static String NEW_VERSION_AVAILABLE;
	public static String EMAIL_VERIFY_URL;
	public static String PROJECT_NAME;
	public static String EXCEL_SHEET_PATH;
	public static String UPCOMING_TEST;
	public static String PUBLISH_TEST;
	public static String PUBLISH_COURSE;
	public static String UPCOMING_COURSE;
	public static String INCOMPLETE_PROFILE_MSG;
	public static String NOTIFICATION_FILE;
	public static String DRAFTED_COURSE_PENDING;
	public static String DRAFTED_TEST_PENDING;
	public static String REPORT_ABUSED_CONTENT;
	public static String LICENSE_EXPIRE_FEW_DAYS;
	public static String INVITEE_REG_IN_SYSTEM;
	public static String DOMAIN_NAME;
	public static String UPLOAD_USER_EXCEL_SHEET_PATH;
	public static String OPEN_OFFICE_PATH;
	/**
	 * Paypal REST API constants;
	 */
	public static String PAYPAL_SANDBOX_FLAG;
	public static String PAYPAL_TOKEN_URL;
	public static String PAYPAL_CREATE_PAYMENT_URL;
	public static String PAYPAL_APP_CLIENT_ID;
	public static String PAYPAL_APP_SECRET;
	/**
	 * AWS S3 constants
	 */
	public static String AWS_S3_BUCKET_NAME;
	public static String AWS_S3_ACCESS_KEY;
	public static String AWS_S3_PRIVATE_KEY;
	public static String AWS_S3_BUCKET_CONTENT_ACCESS_PATH;
	public static String CURRENT_VERSION;
	public static String STATIC_ORG_ID;
	public static String WORKSTATUS="Complete";
	//logout propertie
	public static String LOGOUT;
	/**
	 * scorm course
	 */
	public static String SCORM_COURSE_DIRECTORY;
	static {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("message.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		PDF_UPLOAD_PATH = prop.getProperty("pdf_upload_path");
		UPLOAD_DIRECTORY = prop.getProperty("directory_name");
		LOGOUT=prop.getProperty("logout");
		SQLLITE_FILE_PATH = prop.getProperty("pdf_upload_path");
		SQLLITE_FILE_DIRECTORY = prop.getProperty("sqllitefile_name");
		SERVER_IP = prop.getProperty("Server_IP");
		ICON_PATH = prop.getProperty("icon_directory");
		IMAGE_DIRECTORY = prop.getProperty("image_directory");
		PROFILE_IMAGE_PATH = prop.getProperty("profile_image_path");
		FILE_MANAGER_PATH = prop.getProperty("file_manager_path");
		COURSE_FILE_DIRECTORY = prop.getProperty("course_file_directory");
		JAVA_BRIDGE_PATH = prop.getProperty("Java_Bridge_path");
		APPLICATION_ID = prop.getProperty("application_id");
		NEW_VERSION_AVAILABLE = prop.getProperty("is_new_app_version_available");
		EMAIL_VERIFY_URL = prop.getProperty("email_verify_url");
		PROJECT_NAME = prop.getProperty("project_name");
		EXCEL_SHEET_PATH = prop.getProperty("excel_sheet_path");
		UPCOMING_TEST = prop.getProperty("upcoming_test");
		PUBLISH_TEST = prop.getProperty("publish_test");
		PUBLISH_COURSE = prop.getProperty("publish_course");
		UPCOMING_COURSE = prop.getProperty("upcoming_course");
		INCOMPLETE_PROFILE_MSG = prop.getProperty("incomplete_profile_msg");
		NOTIFICATION_FILE = prop.getProperty("notification_file");
		DRAFTED_COURSE_PENDING = prop.getProperty("drafted_course_pending");
		DRAFTED_TEST_PENDING = prop.getProperty("drafted_test_pending");
		REPORT_ABUSED_CONTENT = prop.getProperty("report_abused_content");
		LICENSE_EXPIRE_FEW_DAYS = prop.getProperty("license_expire_few_days");
		INVITEE_REG_IN_SYSTEM = prop.getProperty("invitee_reg_in_system");
		DOMAIN_NAME = prop.getProperty("domain_name");
		UPLOAD_USER_EXCEL_SHEET_PATH = prop.getProperty("upload_user_excel_sheet_path");
		OPEN_OFFICE_PATH = prop.getProperty("open_office_path");

		PAYPAL_SANDBOX_FLAG = prop.getProperty("PAYPAL_SANDBOX_FLAG");
		if (PAYPAL_SANDBOX_FLAG.equalsIgnoreCase("true")) {
			PAYPAL_TOKEN_URL = prop.getProperty("PAYPAL_TOKEN_URL_SANDBOX");
			PAYPAL_CREATE_PAYMENT_URL = prop.getProperty("PAYPAL_CREATE_PAYMENT_URL_SANDBOX");
		} else {
			PAYPAL_TOKEN_URL = prop.getProperty("PAYPAL_TOKEN_URL");
			PAYPAL_CREATE_PAYMENT_URL = prop.getProperty("PAYPAL_CREATE_PAYMENT_URL");
		}
		PAYPAL_APP_CLIENT_ID = prop.getProperty("PAYPAL_APP_CLIENT_ID");
		PAYPAL_APP_SECRET = prop.getProperty("PAYPAL_APP_SECRET");

		AWS_S3_BUCKET_NAME = prop.getProperty("AWS_S3_BUCKET_NAME");
		AWS_S3_ACCESS_KEY = prop.getProperty("AWS_S3_ACCESS_KEY");
		AWS_S3_PRIVATE_KEY = prop.getProperty("AWS_S3_PRIVATE_KEY");
		AWS_S3_BUCKET_CONTENT_ACCESS_PATH = prop.getProperty("AWS_S3_BUCKET_CONTENT_ACCESS_PATH");
		SCORM_COURSE_DIRECTORY = prop.getProperty("SCORM_COURSE_DIRECTORY");
		CURRENT_VERSION = prop.getProperty("CURRENT_QBIS_VERSION");
		STATIC_ORG_ID = prop.getProperty("STATIC_ORG_ID"); 
	}

	// File type for acp_otherinformation table
	public static final int INFORMTION_PDF = 1;
	public static final int LAB_VALUE_PDF = 2;

	// file upload settings
	public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	public static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public static final int LIMIT = 10;
	
	public static final int MAX_LIMIT = 100;

	public static final String GCM_KEY = "AIzaSyBYkBnv4rSTvrH4EWR-l8-bKYYHoNDwwy8";
	/**
	 * trainee role id.
	 */
	public static final int TRAINEE_ROLE_ID = 3;
	/**
	 * trainer role id.
	 */
	public static final int TRAINER_ROLE_ID = 2;
	/**
	 * super user permission Id
	 */
	public static final int SUPER_USER_ROLE_ID = 1;
	/**
	 * Question Type
	 */
	public static final int MULTIPLE_CHOICE_TYPE = 1;
	public static final int SINGLE_CHOICE_TYPE = 2;
	public static final int SORT_LIST_TYPE = 3;
	public static final int CHOICE_MATRIX_TYPE = 4;
	public static final int CLASSIFICATION_TYPE = 5;
	public static final int MATCH_LIST = 6;
	public static final String USER_COOKIE_KEY = "_l_a_t";
	public static final String Cloud_Front_policy = "CloudFront-Policy";
	public static final String Cloud_Front_signature = "CloudFront-Signature";
	public static final String Cloud_Front_key_pair_id = "CloudFront-Key-Pair-Id";

}
