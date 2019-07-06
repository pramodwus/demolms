package com.qbis.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.qbis.common.Base64Image;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.common.SimpleMail;
import com.qbis.model.User;

/**
 * This class is used for performing all operations related to details of
 * application's user.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class StudentUserService {
	/**
	 * Instance of {@link Logger}
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(StudentUserService.class);

	/**
	 * This is used for saving the details for new user.
	 * 
	 * @param device_id
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> appUserRegistrationNew(String device_id,
			User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService appUserRegistrationNew method ::::::");
		User userdetail = new User();
		int userIdforEmail = 0;
		String access = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		try {

			QueryData emailData = QueryManager.execuateQuery(
					QueryStrings.CHECK_USER_EMAIL,
					new Object[] { user.getEmail() });
			if (emailData.getRows().size() > 0) {
				/*
				 * Email Id is already Exist.
				 */
				for (QueryData.Row row : emailData.getRows()) {
					userIdforEmail = Integer.parseInt(row.getRowItem(0));
				}
				userdetail.setStatus(401);
				userdetail.setMsg("User already exist. login to continue");

			} else {
				/*
				 * Email Id doesn't exist ,So enter new entry for this Email Id.
				 */
				access = CommonUtil
						.generateRandomString(
								new Random(),
								"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
								40);
				userIdforEmail = QueryManager.execuateUpdate(
						QueryStrings.SAVE_APP_USER_NEW,
						new Object[] { user.getPassword(), user.getEmail(),
								access });

				if (userIdforEmail != 0) {
					final String path = ConstantUtil.EMAIL_VERIFY_URL;
					final String email = user.getEmail();
					(new Thread() {
						@Override
						public void run() {
							new StudentUserService().sendVerifyEmail(email,
									path);
						}
					}).start();
				}

				userdetail.setUserId(userIdforEmail);
				userdetail.setStatus(200);
				userdetail.setMsg("Successful Signup");
				userdetail.setOrgId(0);

			}

		} catch (Exception e) {
			/*
			 * User's registration failed.
			 */
			userdetail.setStatus(401);
			userdetail.setMsg("Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService appUserRegistrationNew method ::::::",
					e);
		}
		if (access != null) {
			responseHeaders.set("access_token", access);
		} else {
			responseHeaders.set("access_token", access);
		}

		return new ResponseEntity<Object>(userdetail, responseHeaders,
				HttpStatus.OK);
	}

	/**
	 * A Method for validate the login credentials provided by app user.
	 * 
	 * @param deviceId
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> authenticationLogin(String deviceId, User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService authenticationLogin method ::::::");
		User userdetail = new User();
		String access = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			if (!emailExist(user.getEmail(), deviceId)) {
				userdetail.setStatus(401);
				userdetail.setMsg("Wrong credentials.");
			} else if (!checkPasswordCorrect(user)) {
				userdetail.setStatus(401);
				userdetail.setMsg("Wrong credentials.");
			} else {

				QueryManager.execuateUpdate(
						QueryStrings.UPDATE_LAST_LOGIN_TIME_BY_EMAIL,
						new Object[] { user.getEmail() });

				checkLoginLifeTime(user.getEmail());

				User userobj = new UserService().findOneUser(user.getEmail());
				userdetail.setStatus(200);
				userdetail.setUserId(userobj.getUserId());
				userdetail.setName(userobj.getUserName());
				userdetail.setFirstName(userobj.getFirstName());
				userdetail.setLastName(userobj.getLastName());
				userdetail.setMsg("Successful Logged In");
				userdetail.setOrgId(userobj.getOrgId());
				if (userobj.getAccessId() == "") {
					access = CommonUtil
							.generateRandomString(
									new Random(),
									"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
									40);
					QueryManager.execuateUpdate(
							QueryStrings.UPDATE_ACCESS_ID_BY_EMAIL,
							new Object[] { access, user.getEmail() });
					
				} else {

					if (checkLoginLifeTime(user.getEmail())) {
						access = userobj.getAccessId();
					} else {
						access = CommonUtil
								.generateRandomString(
										new Random(),
										"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
										40);
						QueryManager.execuateUpdate(
								QueryStrings.UPDATE_ACCESS_ID_BY_EMAIL,
								new Object[] { access, user.getEmail() });
						
					}

				}

			}
		} catch (Exception e) {
			userdetail.setStatus(401);
			userdetail.setMsg("Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService authenticationLogin method ::::::",
					e);
		}
		if (access != null) {
			responseHeaders.set("access_token", access);
			return new ResponseEntity<Object>(userdetail, responseHeaders,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(userdetail, responseHeaders,
					HttpStatus.OK);
		}
	}

	/**
	 * A method for checking last login time by user emailId
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean checkLoginLifeTime(String email) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService checkLoginLifeTime method ::::::");
		boolean flag = false;
		int HOUR = 3;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.CHECK_LOGIN_LIFE_TIME, new Object[] { email,
							HOUR });

			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService checkLoginLifeTime method ::::::",
					e);
		}
		return flag;
	}

	/**
	 * A method for checking mobile is exist in our system with given device or
	 * not.
	 * 
	 * @param email
	 * @param deviceId
	 * @return boolean
	 */
	public static boolean emailExist(String email, String deviceId) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService emailExist method ::::::");
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(

			QueryStrings.CHECK_EMAIL_EXIST, new Object[] { email });

			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService emailExist method ::::::",
					e);
		}
		return flag;
	}

	/**
	 * This method is used for checking user's credentials are correct or not.
	 * 
	 * @param user
	 * @return boolean
	 */
	private boolean checkPasswordCorrect(User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService checkPasswordCorrect method ::::::");
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.CHECK_USER_PASSOWORD_BY_EMAIL, new Object[] {
							user.getEmail(), user.getPassword() });
			for (QueryData.Row row : data.getRows()) {
				if (row.getRowItem(0) != null) {
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService checkPasswordCorrect method ::::::",
					e);
		}
		return flag;
	}

	/**
	 * A Method for sending verification email for registration purpose.
	 * 
	 * @param email
	 * @param path
	 * @return boolean
	 */
	public boolean sendVerifyEmail(String email, String path) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService sendVerifyEmail method ::::::");
		boolean status = false;
		int index = path.lastIndexOf("/");
		String newUrl = path.substring(0, index) + "/verify?email=" + email;
		try {
			SimpleMail smail = new SimpleMail();
			String content = "<h3>Account Verification Link</h3></br></br>"
					+ "<p>Dear User,</p><br/>"
					+ "<p>You just signed up for Qbis. Please click below to confirm that this is your e-mail address.</p>"
					+ "<p>Verify your account : Click <a href='" + newUrl
					+ "' style='text_decoration:true'> here</a></p><br/><br/>"
					+ "<p>Regards,</p>" + "<p>QBis Team</p>";
			smail.sendEmail("info@qbis.com", email, "User detail", content);
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService sendVerifyEmail method ::::::",
					e);
		}
		return status;
	}

	/**
	 * Method for Logout Application User
	 * 
	 * @param accessToken
	 * @return Map<String, Object>
	 */
	public Map<String, Object> logoutAppUser(String accessToken) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService logoutAppUser method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		String token = null;
		try {

			Integer id = QueryManager.execuateUpdate(
					QueryStrings.ACCESS_TOKEN_NULL_NEW, new Object[] { token,
							accessToken });

			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "Successful Logged Out");
			} else {
				map.put("status", 401);
				map.put("msg", "Operation failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService logoutAppUser method ::::::",
					e);
		}
		return map;
	}

	/**
	 * A method for checking that accessToken is valid or not.
	 * 
	 * @param accessToken
	 * @return boolean
	 */
	public static boolean checkAccessToken(String accessToken) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService checkAccessToken method ::::::");
		boolean status = false;
		try {
			QueryData query = QueryManager.execuateQuery(
					QueryStrings.CHECK_ACCESS_TOKEN,
					new Object[] { accessToken });
			if (query.getRows().size() > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService checkAccessToken method ::::::",
					e);
		}
		return status;
	}

	/**
	 * Reset Application User Password send mail with reset password link
	 * 
	 * @param deviceId
	 * @param user
	 * @return Map<String, Object>
	 */
	public Map<String, Object> changeAppUserPassword(String deviceId, User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService changeAppUserPassword method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserService userservice = new UserService();
			userservice.getSubDomainBasedOnEmailId(user);
			if (user.getUserId() != null && user.getUserId() > 0) {
				if (user.getSubdomain() != null
						&& user.getSubdomain().length() > 0) {
					String otp = CommonUtil.generateRandomString(new Random(),
							"ABC12340DXYZ@^efgh", 6);
					boolean status = userservice.saveUserToken(user.getEmail(),
							otp);
					String path = user.getSubdomain().trim() + "."
							+ ConstantUtil.DOMAIN_NAME
							+ "/resetPassword?access_token=" + otp
							+ "&roleType=trainee";
					if (status) {
						user.setUrl(path);
						userservice.sendPasswordMail(user);
						map.put("status", 200);
						map.put("msg",
								"Forget password email sent successfully");
					} else {
						map.put("status", 401);
						map.put("msg", "Operation failed.");
					}

				} else {
					map.put("status", 401);
					map.put("msg", "You are not user of any organization.");
				}
			} else {
				map.put("status", 401);
				map.put("msg", "Email Id does not exist.");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService changeAppUserPassword method ::::::",
					e);
		}
		return map;
	}

	/**
	 * A method for fetching the detail of user.
	 * 
	 * @param userId
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getAppUserProfile(int userId) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService getAppUserProfile method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		User userdata = new User();
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.GET_APP_USER_PROFILE, new Object[] { userId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					userdata.setUserId(Integer.parseInt(row.getRowItem(0)));
					userdata.setFirstName(row.getRowItem(5) != null ? row
							.getRowItem(5) : "");
					userdata.setLastName(row.getRowItem(6) != null ? row
							.getRowItem(6) : "");
					userdata.setMobile(row.getRowItem(8) != null ? row
							.getRowItem(8) : "");
					userdata.setDob(row.getRowItem(13) != null ? row
							.getRowItem(13) : "");
					userdata.setAbout(row.getRowItem(14) != null ? row
							.getRowItem(14) : "");
					if (row.getRowItem(15) == null
							|| row.getRowItem(15).isEmpty()) {
						userdata.setImage("");
					} else {
						String path = ConstantUtil.SERVER_IP
								+ ConstantUtil.IMAGE_DIRECTORY
								+ row.getRowItem(15);
						userdata.setImage(path);
					}

				}
				map.put("status", 200);
				map.put("msg", "Profile Data");
				map.put("profile", userdata);
			} else {
				map.put("status", 401);
				map.put("msg", "Operation failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService getAppUserProfile method ::::::",
					e);

		}
		return map;
	}

	/**
	 * A Method for updating the application user's profile details.
	 * 
	 * @param user
	 * @return Map<String, Object>
	 */
	public Map<String, Object> updateAppUserProfile(User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService updateAppUserProfile method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (user.getImage() == null || user.getImage().isEmpty()) {
			} else {
				user = Base64Image.uploadUserProfileImage(user);
			}

			Integer id = QueryManager
					.execuateUpdate(
							QueryStrings.UPDATE_APP_USER_PROFILE,
							new Object[] { user.getFirstName(),
									user.getLastName(), user.getMobile(),
									user.getDob(), user.getImage(),
									user.getImage(), user.getAbout(),
									user.getUserId() });
			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "Profile Updated");
			} else {
				map.put("status", 401);
				map.put("msg", "Operation Failed");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService updateAppUserProfile method ::::::",
					e);
		}
		return map;
	}

	/**
	 * A method for checking that user registrationId exist or not.
	 * 
	 * @param userId
	 * @return boolean
	 */
	public static boolean checkRegistrationId(int userId) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService checkRegistrationId method ::::::");
		boolean status = false;
		try {
			QueryData data = QueryManager.execuateQuery(
					QueryStrings.CHECK_REGISTRAION_ID, new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				if (row.getRowItem(0) != null)
					status = true;
			}
		} catch (Exception e) {
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService checkRegistrationId method ::::::",
					e);
		}
		return status;
	}

	/**
	 * This is used set registration id of user.
	 * 
	 * @param user
	 * @return Map<String, Object>
	 */
	public Map<String, Object> setRegisterationId(User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService setRegisterationId method ::::::");
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			Integer id = QueryManager.execuateUpdate(
					QueryStrings.SET_REGISTRATIONID_BY_USERID, new Object[] {
							user.getRegistrationId(), user.getUserId() });
			if (id > 0) {
				map.put("status", 200);
				map.put("msg", "RegistrationId saved");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "Operation Failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService setRegisterationId method ::::::",
					e);
		}
		return map;
	}

	/**
	 * A Method for set up password or app user
	 * 
	 * @param accessToken
	 * @param user
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> setUpPassword(String accessToken, User user) {
		logger.log(Level.DEBUG,
				"Inside StudentUserService setUpPassword method ::::::");
		HttpHeaders responseHeaders = new HttpHeaders();
		UserService userservice = new UserService();
		boolean isSuccessFullySetUp = false;
		String access = null;
		User userdetail = new User();
		try {
			if (accessToken != null || accessToken != "") {
				userdetail = userservice.tokenCorrect(accessToken, false);
				if (userdetail.getEmail() != null) {
					userservice.setEmailVerify(userdetail.getEmail());
					isSuccessFullySetUp = userservice.updateUserPass(
							userdetail.getEmail(), user.getPassword());
				}
			}

			if (isSuccessFullySetUp) {
				access = CommonUtil
						.generateRandomString(
								new Random(),
								"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
								40);
				QueryManager.execuateUpdate(
						QueryStrings.UPDATE_ACCESS_ID_BY_EMAIL, new Object[] {
								access, userdetail.getEmail() });
				User userobj = userservice.findOneUser(userdetail.getEmail());
				userdetail.setStatus(200);
				userdetail.setUserId(userobj.getUserId());
				userdetail.setName(userobj.getUserName());
				userdetail.setFirstName(userobj.getFirstName());
				userdetail.setLastName(userobj.getLastName());
				userdetail.setMsg("Successful Password Setup");
				userdetail.setOrgId(userobj.getOrgId());
			}
		} catch (Exception e) {
			userdetail.setStatus(401);
			userdetail.setMsg("Operation failed");
			logger.log(
					Level.ERROR,
					"Exception Inside StudentUserService setUpPassword method ::::::",
					e);
		}
		if (access != null) {
			responseHeaders.set("access_token", access);
			return new ResponseEntity<Object>(userdetail, responseHeaders,
					HttpStatus.OK);
		} else {
			userdetail.setStatus(401);
			userdetail.setMsg("Operation failed");
			return new ResponseEntity<Object>(userdetail, responseHeaders,
					HttpStatus.OK);
		}
	}

}