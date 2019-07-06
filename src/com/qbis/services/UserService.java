package com.qbis.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.qbis.auth.AuthUser;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.EmailSender;
import com.qbis.common.QueryData;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.gcm.NotificationManagement;
import com.qbis.model.User;

/**
 * This Class is used for performing all operations related to user.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class UserService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(UserService.class);

	/**
	 * A method for user authentication
	 * 
	 * @param email
	 * @param password
	 * @return boolean
	 */
	public boolean userAuthenticate(String email, String password, Integer orgId) {
		logger.log(Level.DEBUG, "Inside User service userAuthenticate method:::::");
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.USER_VALIDATE_LOGIN,
					new Object[] { email, password, orgId });
			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service userAuthenticate method:::::", e);
		}
		return flag;
	}

	/**
	 * get organization id based on user Id.
	 * 
	 * @param userId
	 * @return Integer
	 */
	public static Integer getOrgId(Integer userId) {
		logger.log(Level.DEBUG, "Inside User service getOrgId method:::::");
		Integer orgId = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ORG_ID_BY_USER_ID, new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				orgId = Integer.parseInt(row.getRowItem(0));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getOrgId method:::::", e);
		}
		return orgId;
	}

	/**
	 * Getting user's Details based on user's email id.
	 * 
	 * @param email
	 * @return User
	 */
	public User findOneUser(String email) {
		logger.log(Level.DEBUG, "Inside User service findOneUser method:::::");
		User user = new User();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_ONE_USER_DATA_BASED_EMAIL_ID,
					new Object[] { email });
			for (QueryData.Row row : data.getRows()) {
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setEmail(row.getRowItem(1));
				user.setRoleId(Integer.parseInt(row.getRowItem(2)));
				user.setRoleDesc(row.getRowItem(3));
				user.setUserStatus(Integer.parseInt(row.getRowItem(4)));
				user.setCompanyId(row.getRowItem(5) != null ? Integer.parseInt(row.getRowItem(5)) : null);
				user.setOrgId(row.getRowItem(5) != null ? Integer.parseInt(row.getRowItem(5)) : 0);
				user.setFirstName(row.getRowItem(6) != null ? row.getRowItem(6) : "");
				user.setLastName(row.getRowItem(7) != null ? row.getRowItem(7) : "");
				user.setUserName(row.getRowItem(8) != null ? row.getRowItem(8) : "");
				user.setAccessId(row.getRowItem(9) != null ? row.getRowItem(9) : "");
				user.setPermissionId(row.getRowItem(11) != null ? Integer.parseInt(row.getRowItem(11)) : null);
				user.setCreaterId(row.getRowItem(12) != null ? Integer.parseInt(row.getRowItem(12)) : null);
				user.setSystemLanguage(row.getRowItem(13));
				user.setCity(row.getRowItem(14));
				user.setMobile(row.getRowItem(15));
				user.setSchool(row.getRowItem(16));
				user.setClassName(row.getRowItem(17));
				String path = ConstantUtil.SERVER_IP + ConstantUtil.IMAGE_DIRECTORY + row.getRowItem(10);
				if (row.getRowItem(10) != null) {
					user.setImage(path);
				}
				user.setSubdomain(row.getRowItem(18));
				user.setImage(row.getRowItem(19)!= null?row.getRowItem(19):"");
				System.err.println(user.getImage());
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service findOneUser method:::::", e);
		}
		return user;
	}

	/**
	 * A Method for checking that email id is already registered or not.
	 * 
	 * @param email
	 * @return boolean if exists return true otherwise false;
	 */
	public static boolean checkUserEmail(String email) {
		logger.log(Level.DEBUG, "Inside User service checkUserEmail method:::::");
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_USER_EMAIL, new Object[] { email });
			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service checkUserEmail method:::::", e);
		}
		return flag;
	}

	/**
	 * This is used for verify that email address is registered with given sub
	 * domain.
	 * 
	 * @param email
	 * @param subdomain
	 * @return boolean
	 */
	public static boolean checkUserEmailWithSubDomain(String email, String subdomain) {
		logger.log(Level.DEBUG, "Inside User service checkUserEmailWithSubDomain method:::::");
		boolean flag = false;
		try {

			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_USER_EMAIL_WITH_SUBDOMAIN,
					new Object[] { email, subdomain });
			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service checkUserEmailWithSubDomain method:::::", e);
		}
		return flag;
	}

	/**
	 * A Method for checking that Organization Name is already Exist Or Not.
	 * 
	 * @param OrgName
	 * @return boolean if exists return true otherwise false;
	 */
	public static boolean checkOrganization(String orgName) {
		logger.log(Level.DEBUG, "Inside User service checkOrganization method:::::");
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_ORGANIZATION, new Object[] { orgName });
			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service checkOrganization method:::::", e);
		}
		return flag;
	}

	/**
	 * method for user's registration.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean userRegistration(User user) {
		logger.log(Level.DEBUG, "Inside User service userRegistration method:::::");
		boolean flag = false;
		try {
			int id = QueryManager.execuateUpdate(QueryStrings.REGISTER_SUPER_USER, new Object[] { user.getPassword(),
					user.getEmail(), user.getCompanyId(), user.getPermissionId(), user.getSystemLanguage() });
			if (id > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service userRegistration method:::::", e);
		}
		return flag;
	}

	/**
	 * method for user's registration.
	 * 
	 * @param user
	 * @return int
	 */
	public int addNewOrganization(User user) {
		logger.log(Level.DEBUG, "Inside User service addNewOrganization method:::::");
		int orgId = 0;
		try {
			int id = QueryManager.execuateUpdate(QueryStrings.ADD_ORGANIZATION,
					new Object[] { user.getSubdomain(), user.getOrgName() });
			if (id > 0) {
				orgId = id;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service addNewOrganization method:::::", e);
		}
		return orgId;
	}

	/**
	 * A method for generating user token for reseting password.
	 * 
	 * @param email
	 * @param token
	 * @return boolean
	 */
	public boolean saveUserToken(String email, String token) {
		logger.log(Level.DEBUG, "Inside User service saveUserToken method:::::");
		boolean flag = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.PASSWORD_RESET_TOKEN, new Object[] { token, email });
			if (id > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service saveUserToken method:::::", e);
		}
		return flag;
	}

	/**
	 * Method for sending a password resetting link on email id.
	 * 
	 * @param user
	 */
	public void sendPasswordMail(User user) {
		logger.log(Level.DEBUG, "Inside User service sendPasswordMail method:::::");
		try {
			final String url = user.getUrl();
			final String email = user.getEmail();
			final Map<Object, Object> dataobject = new HashMap<Object, Object>();
			dataobject.put("name", user.getFirstName() != null ? user.getFirstName() : "Guest User");
			dataobject.put("link", url);
			(new Thread() {
				@Override
				public void run() {
					try {
						EmailSender.sendEmail(email, "Forget Password",
								EmailSender.generateResetPasswordMessg(dataobject));
					} catch (Exception e) {
						logger.log(Level.ERROR,
								"Exception Inside User service sendPasswordMail method during sending password reset link:::::",
								e);
					}

				}
			}).start();
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service sendPasswordMail method:::::", e);
		}
	}

	/**
	 * A method for verify token for password resetting.
	 * 
	 * @param token
	 * @param isPassReset
	 * @return User
	 */
	public User tokenCorrect(String token, Boolean isPassReset) {
		logger.log(Level.DEBUG, "Inside User service tokenCorrect method:::::");
		User user = new User();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.VERIFY_PASSWORD_RESET_TOKEN,
					new Object[] { token });
			for (QueryData.Row row : data.getRows()) {
				user.setEmail(row.getRowItem(0));
				if (isPassReset) {
					QueryManager.execuateQuery(QueryStrings.EXPIRE_PASSWORD_RESET_TOKEN,
							new Object[] { user.getEmail() });
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service tokenCorrect method:::::", e);
		}
		return user;
	}

	/**
	 * A method for update the password of user.
	 * 
	 * @param email
	 * @param Password
	 * @return boolean
	 */
	public boolean updateUserPass(String email, String Password) {
		logger.log(Level.DEBUG, "Inside User service updateUserPass method:::::");
		boolean status = false;
		try {
			int id = QueryManager.execuateUpdate(QueryStrings.UPDATE_PASSWORD, new Object[] { Password, email });
			if (id > 0) {
				status = true;
				QueryManager.execuateQuery(QueryStrings.EXPIRE_PASSWORD_RESET_TOKEN, new Object[] { email });
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service updateUserPass method:::::", e);
		}
		return status;
	}

	/**
	 * A Method for re-sending verification email when user click on Re-send
	 * verification link.
	 * 
	 * @param email
	 * @param path
	 * @return boolean
	 */
	public boolean sendVerifyEmail(String email, String path) {
		logger.log(Level.DEBUG, "Inside User service sendVerifyEmail method:::::");
		boolean status = false;
		int index = path.lastIndexOf("/");
		final String newUrl = path.substring(0, index) + "/accountVerify?email=" + email;
		final String emailId = email;
		try {
			final Map<Object, Object> dataobject = new HashMap<Object, Object>();
			dataobject.put("link", newUrl);
			(new Thread() {
				@Override
				public void run() {
					try {
						EmailSender.sendEmail(emailId, "Please verify your email address",
								EmailSender.generateVerifyAccountMessg(dataobject));
					} catch (Exception e) {
						logger.log(Level.ERROR,
								"Exception Inside User service sendVerifyEmail method during sending email verify link:::::",
								e);
					}
				}
			}).start();
			status = true;
		} catch (Exception e) {
			status = false;
			logger.log(Level.ERROR, "Exception Inside User service sendVerifyEmail method:::::", e);
		}

		return status;
	}

	/**
	 * A Method for sending verification mail to user based on licence.
	 * 
	 * @param email
	 * @param path
	 * @return boolean
	 */
	public boolean sendVerifyEmailBasedOnLicence(String email, String path, final Integer LicenceId) {
		logger.log(Level.DEBUG, "Inside User service sendVerifyEmail method:::::");
		boolean status = false;
		int index = path.lastIndexOf("/");
		final String newUrl = path.substring(0, index) + "/accountVerify?email=" + email;
		final String emailId = email;
		try {
			final Map<Object, Object> dataobject = new HashMap<Object, Object>();
			dataobject.put("link", newUrl);
			(new Thread() {
				@Override
				public void run() {
					try {
						if (LicenceId == 1) {
							EmailSender.sendEmail(emailId, "Please verify your email address",
									EmailSender.generateVerifyAccountMessg(dataobject));
						} else if (LicenceId == 2) {
							EmailSender.sendEmail(emailId, "Please verify your email address",
									EmailSender.generateVerifyAccountMessgForAdvLicence(dataobject));
						}
					} catch (Exception e) {
						logger.log(Level.ERROR,
								"Exception Inside User service sendVerifyEmailBasedOnLicence method during sending email verify link:::::",
								e);
					}
				}
			}).start();
			status = true;
		} catch (Exception e) {
			status = false;
			logger.log(Level.ERROR, "Exception Inside User service sendVerifyEmail method:::::", e);
		}

		return status;
	}

	/**
	 * A method which describes that email has been verify.
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean setEmailVerify(String email) {
		logger.log(Level.DEBUG, "Inside User service setEmailVerify method:::::");
		boolean status = false;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.EMAIL_VERIFICATION, new Object[] { email });
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service setEmailVerify method:::::", e);
		}
		return status;
	}

	/**
	 * A method for fetching the detail of user.
	 * 
	 * @param userId
	 * @return User
	 */
	public User getUserProfile(Integer userId) {
		logger.log(Level.DEBUG, "Inside User service getUserProfile method:::::");
		User userdata = new User();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_APP_USER_PROFILE, new Object[] { userId });
			if (data.getRows().size() > 0) {
				for (QueryData.Row row : data.getRows()) {
					userdata.setUserId(Integer.parseInt(row.getRowItem(0)));
					userdata.setFirstName(row.getRowItem(5) != null ? row.getRowItem(5) : "");
					userdata.setLastName(row.getRowItem(6) != null ? row.getRowItem(6) : "");
					userdata.setMobile(row.getRowItem(8) != null ? row.getRowItem(8) : "");
					userdata.setDob(row.getRowItem(13) != null ? row.getRowItem(13) : "");
					userdata.setAbout(row.getRowItem(14) != null ? row.getRowItem(14) : "");
					if (row.getRowItem(15) == null || row.getRowItem(15).isEmpty()) {
						userdata.setImage("");
					} else {
						String path = ConstantUtil.SERVER_IP + ConstantUtil.IMAGE_DIRECTORY + row.getRowItem(15);
						userdata.setImage(path);
					}
					userdata.setRegistrationId(row.getRowItem(16));
					userdata.setSystemLanguage(row.getRowItem(17));
				}
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserProfile method:::::", e);

		}
		return userdata;
	}

	/**
	 * Method is used for updating the user profile.
	 * 
	 * @param user
	 * @return Boolean
	 */
	
	public boolean updateStudentProfile(User user)
	{
		logger.log(Level.DEBUG, "Inside User service updateUserProfile method:::::");
		Boolean status = false;
		System.out.println("-----"+user.getEmail());
		Integer abc = user.getRoleModifiedById() != null ? 0 : null;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_STUDENT_PROFILE,
					new Object[] { user.getFirstName(),user.getClassName(),user.getMobile(),user.getSchool(),
							user.getEmail()});
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service updateUserProfile method:::::", e);
		}
		return status;
		
	}
	
	public boolean updateStudentProfile(AuthUser user,String email)
	{
		logger.log(Level.DEBUG, "Inside User service updateUserProfile method:::::");
		Boolean status = false;
		System.out.println("-----"+user.getFirst_name());
		//Integer abc = user.getRoleModifiedById() != null ? 0 : null;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_STUDENT_PROFILE,
					new Object[] { user.getFirst_name(),user.getLast_name(),user.getContact_info(),user.getAddress(),
							email});
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service updateUserProfile method:::::", e);
		}
		return status;
		
	}



	
	public Boolean updateUserProfile(User user) {
		logger.log(Level.DEBUG, "Inside User service updateUserProfile method:::::");
		Boolean status = false;
		System.out.println("-----"+user.getMobile());
		Integer abc = user.getRoleModifiedById() != null ? 0 : null;
		try {
			Integer id = QueryManager.execuateUpdate(QueryStrings.UPDATE_USER_PROFILE,
					new Object[] { user.getFirstName(), user.getLastName(), user.getMobile(), user.getDob(),
							user.getImage(), user.getImage(), user.getAbout(), user.getUserId(),
							user.getRoleModifiedById(), user.getRoleModifiedById(), abc, user.getSystemLanguage(),user.getSchool(),user.getClassName(),
							user.getUserId() });
			System.out.println(user.getClassName()+"=========================="+user.getSchool());
			if (id > 0) {
				status = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service updateUserProfile method:::::", e);
		}
		return status;
	}

	/**
	 * This function is used for getting list of all student type user.
	 * 
	 * @return userList
	 */
	public List<User> getUserList() {
		logger.log(Level.DEBUG, "Inside User service getUserList method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_ALL_STUDENT_LIST, new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserList method:::::", e);
		}
		return userList;
	}

	/**
	 * This function is used for getting list of all users.
	 * 
	 * @return userList
	 */
	public List<User> getALLUserList() {
		logger.log(Level.DEBUG, "Inside User service getALLUserList method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST, new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setRoleId(Integer.parseInt(row.getRowItem(6)));
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getALLUserList method:::::", e);
		}
		return userList;
	}

	/**
	 * This function is used for uploading the image of user.
	 * 
	 * @param file
	 * @param userId
	 * @return String
	 */
	public String uploadProfileImage(MultipartFile file, Integer userId) {
		logger.log(Level.DEBUG, "Inside User service uploadProfileImage method:::::");
		String newName = userId + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String PROFILE_IMAGE_PATH = ConstantUtil.PROFILE_IMAGE_PATH;
		String IMAGE_DIRECTORY = ConstantUtil.IMAGE_DIRECTORY;
		String uploadPath = PROFILE_IMAGE_PATH + IMAGE_DIRECTORY;

		if (!file.isEmpty()) {
			try {
				// Creating the directory to store file
				File dir = new File(uploadPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + newName);
				file.transferTo(serverFile);
			} catch (Exception e) {
				logger.log(Level.ERROR, "Exception Inside User service uploadProfileImage method:::::", e);
			}
		}
		return newName;
	}

	/**
	 * Method to send notification to incomplete profile users.
	 */
	public static void getListInCompleteProfileUsers() {
		logger.log(Level.DEBUG, "Inside User service getListInCompleteProfileUsers method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_INCOMPLETE_PROFILE_USER_LIST, new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setUserName(row.getRowItem(1) != null ? row.getRowItem(1) : "Guest");
				user.setRegistrationId(row.getRowItem(2));
				userList.add(user);
			}

			if (userList.size() > 0) {
				final List<User> userlist = new ArrayList<User>(userList);
				if (userlist != null) {

					(new Thread() {
						@Override
						public void run() {
							Map<Integer, String> notification = new LinkedHashMap<Integer, String>();
							for (int i = 0; i < userlist.size(); i++) {
								String textMsg = (ConstantUtil.INCOMPLETE_PROFILE_MSG.replace("#NAME",
										userlist.get(i).getUserName()));
								if (userlist.get(i).getRegistrationId() != null) {
									int userId = userlist.get(i).getUserId();
									notification.put(userlist.get(i).getUserId(), userlist.get(i).getRegistrationId());
									NotificationManagement notificationManagement = new NotificationManagement();
									notificationManagement.sendNotificationIncompleteProfileUsers(notification, textMsg,
											userId);
								}

								String url = "addeditprofile";
								String[] targetIds = { userlist.get(i).getUserId().toString() };
								NotificationService.saveNotifcation(textMsg, url, targetIds);
							}

						}
					}).start();

				}
			}

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getListInCompleteProfileUsers method:::::", e);
		}

	}

	/**
	 * This method is used creating account of user as trainee.
	 * 
	 * @param email
	 * @param user
	 * @param path
	 * @return userId
	 */
	public Integer createTraineeAccount(final String email, User user, String path) {
		logger.log(Level.DEBUG, "Inside User service createTraineeAccount method:::::");
		Integer userId = 0;
		try {
			final String otp = CommonUtil.generateRandomString(new Random(), "ABC12340DXYZ@^efgh", 6);
			final Integer id = QueryManager.execuateUpdate(QueryStrings.CREATE_USER_ACCOUNT,
					new Object[] { email, user.getOrgId(), user.getRoleId(), otp, user.getUserId() });
			userId = id;
			if (id > 0) {
				String loginLink = path + "/setPass?token=" + otp;
				final Map<Object, Object> dataobject = new HashMap<Object, Object>();
				dataobject.put("name", "Guest User");
				dataobject.put("link", loginLink);
				(new Thread() {
					@Override
					public void run() {
						try {
							EmailSender.sendEmail(email, "User Account",
									EmailSender.generateMsgForNewAccountCreation(dataobject));
						} catch (Exception e) {
							logger.log(Level.ERROR,
									"Exception Inside User service createTraineeAccount method during sending set up password link:::::",
									e);
						}
					}
				}).start();
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service createTraineeAccount method:::::", e);
		}
		return userId;
	}

	/**
	 * This method is used creating account of user as trainee for sharing
	 * course.
	 * 
	 * @param email
	 * @param user
	 * @param path
	 * @param courseId
	 * @param message
	 * @return userId
	 */
	public Integer createTraineeAccountWithCouseSharing(final String email, User user, String path, Integer courseId,
			String message) {
		logger.log(Level.DEBUG, "Inside User service createTraineeAccountWithCouseSharing method:::::");
		Integer userId = 0;
		try {
			final String otp = CommonUtil.generateRandomString(new Random(), "ABC12340DXYZ@^efgh", 6);
			final Integer id = QueryManager.execuateUpdate(QueryStrings.CREATE_USER_ACCOUNT,
					new Object[] { email, user.getOrgId(), user.getRoleId(), otp, user.getUserId() });
			userId = id;
			if (id > 0) {
				String loginLink = path + "/setPass?token=" + otp + "&courseId=" + courseId + "&roleType=trainee";
				final Map<Object, Object> dataobject = new HashMap<Object, Object>();
				if (message != null && message.length() > 0) {

					dataobject.put("message", message);
				} else {

					dataobject.put("message", "You have been invited for Course.");
				}
				dataobject.put("typename", "course");
				dataobject.put("link", loginLink);
				(new Thread() {
					@Override
					public void run() {
						try {
							EmailSender.sendEmail(email, "Invitation for accessing course",
									EmailSender.generateSharedMessgForNewCreatedUser(dataobject));
						} catch (Exception e) {
							logger.log(Level.ERROR,
									"Exception Inside User service createTraineeAccountWithCouseSharing method during sending course sharing link:::::",
									e);
						}
					}
				}).start();
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service createTraineeAccountWithCouseSharing method:::::",
					e);
		}
		return userId;
	}

	/**
	 * This method is used creating account of user as trainee for sharing
	 * assessment.
	 * 
	 * @param email
	 * @param user
	 * @param path
	 * @param testId
	 * @param message
	 * @return userId
	 */
	public Integer createTraineeAccountWithTestSharing(final String email, User user, String path, Integer testId,
			String message) {
		logger.log(Level.DEBUG, "Inside User service createTraineeAccountWithTestSharing method:::::");
		Integer userId = 0;
		try {
			final String otp = CommonUtil.generateRandomString(new Random(), "ABC12340DXYZ@^efgh", 6);
			final Integer id = QueryManager.execuateUpdate(QueryStrings.CREATE_USER_ACCOUNT,
					new Object[] { email, user.getOrgId(), user.getRoleId(), otp, user.getUserId() });
			userId = id;
			if (id > 0) {
				String loginLink = path + "/setPass?token=" + otp + "&testId=" + testId + "&roleType=trainee";
				final Map<Object, Object> dataobject = new HashMap<Object, Object>();
				if (message != null && message.length() > 0) {

					dataobject.put("message", message);
				} else {

					dataobject.put("message", "You have been invited for Assessment.");
				}
				dataobject.put("typename", "assessment");
				dataobject.put("link", loginLink);
				(new Thread() {
					@Override
					public void run() {
						try {
							EmailSender.sendEmail(email, "Invitation for accessing assessment",
									EmailSender.generateSharedMessgForNewCreatedUser(dataobject));
						} catch (Exception e) {
							logger.log(Level.ERROR,
									"Exception Inside User service createTraineeAccountWithTestSharing method during sending test sharing link:::::",
									e);
						}
					}
				}).start();
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service createTraineeAccountWithTestSharing method:::::", e);
		}
		return userId;
	}

	/**
	 * This is used for validate login credentials.
	 * 
	 * @param subdomain
	 * @param email
	 * @param password
	 * @return isSuccess
	 */
	public int checkLoginDetails(String subdomain, String email, String password) {
		logger.log(Level.DEBUG, "Inside User service checkLoginDetails method:::::");
		Integer userId = 0;
		Integer isSuccess = 0;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_LOGIN_DETAILS,
					new Object[] { subdomain, email, password });
			for (QueryData.Row row : data.getRows()) {
				userId = Integer.parseInt(row.getRowItem(0));
			}
			if (userId > 0) {
				isSuccess = 2;
				User user = findOneUser(email);
				if (user.getUserStatus() == 0) {
					isSuccess = 1;
				}
			}

			if (isSuccess == 2) {
				Integer orgId = OrganizationService.getOrganizationId(subdomain.trim());
				Boolean flag = QbisLicenseService.checkLicenseValidity(orgId);
				if (flag != null) {
					if (!flag) {
						isSuccess = 3;
					}
				}
			}
		} catch (Exception e) {
			isSuccess = 0;
			logger.log(Level.ERROR, "Exception Inside User service checkLoginDetails method:::::", e);
		}
		return isSuccess;
	}

	/**
	 * This is used create user account.
	 * 
	 * @param user
	 * @param adminEmail
	 * @param path
	 * @return isCreated
	 */
	public Boolean createUserAccount(User user, final String adminEmail, final String path) {
		logger.log(Level.DEBUG, "Inside User service createUserAccount method:::::");
		Boolean isCreated = false;
		try {
			final String otp = CommonUtil.generateRandomString(new Random(), "ABC12340DXYZ@^efgh", 6);
			final Integer id = QueryManager.execuateUpdate(QueryStrings.CREATE_USER_ACCOUNT,
					new Object[] { user.getEmail(), user.getOrgId(), user.getRoleId(), otp, user.getCreaterId() });
			if (id > 0) {
				isCreated = true;
				final String email = user.getEmail();
				String loginLink = path + "/setPass?token=" + otp;
				if (user.getRoleId() != null && (user.getRoleId() == ConstantUtil.TRAINEE_ROLE_ID)) {
					loginLink = loginLink + "&roleType=trainee";
				}
				final Map<Object, Object> dataobject = new HashMap<Object, Object>();
				dataobject.put("name", "Guest User");
				dataobject.put("link", loginLink);
				(new Thread() {
					@Override
					public void run() {
						try {
							EmailSender.sendEmail(email, "User Account",
									EmailSender.generateMsgForNewAccountCreation(dataobject));
						} catch (Exception e) {
							logger.log(Level.ERROR,
									"Exception Inside User service createUserAccount method during sending set up password link:::::",
									e);
						}
					}
				}).start();
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service createUserAccount method:::::", e);
		}
		return isCreated;
	}

	/**
	 * This is used save organization's license details.
	 * 
	 * @param req
	 */
	public void saveOrgLicenseDetails(User req) {
		logger.log(Level.DEBUG, "Inside User service saveOrgLicenseDetails method:::::");
		try {
			QueryManager.execuateUpdate(QueryStrings.INSERT_LICENSE_COMPANY_DATA,
					new Object[] { req.getLicenseId(), req.getCompanyId() });

		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service saveOrgLicenseDetails method:::::", e);
		}

	}

	/**
	 * Method is used for read excel and save Users in database.
	 * 
	 * @param fileData
	 * @param fileName
	 * @param userobj
	 *            , User object of creator
	 * @param path
	 * @param roleId
	 * @return Workbook
	 * @throws IOException
	 */
	public Map<String, Object> saveUserFromUploadFile(MultipartFile fileData, String fileName, User userobj,
			String path, int roleId) throws Exception {
		logger.log(Level.DEBUG, "Inside User service saveUserFromUploadFile method:::::");
		Map<String, Object> map = new HashMap<String, Object>();
		ByteArrayInputStream bis;
		bis = new ByteArrayInputStream(fileData.getBytes());
		Workbook workbook = CommonUtil.getWorkbook(bis, fileName);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		List<User> userlist = new ArrayList<User>();
		while (iterator.hasNext()) {
			User user = null;
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			if (nextRow.getRowNum() < 1) {
				continue;
			}

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getColumnIndex()) {
				case 0:
					if (CommonUtil.getCellValue(cell) != null) {
						user = new User();
						user.setEmail(CommonUtil.getCellValue(cell).toString());
						user.setRoleId(roleId);
						user.setOrgId(userobj.getCompanyId());
						user.setCreaterId(userobj.getUserId());
						userlist.add(user);
					}
					break;
				}
			}

		}

		List<String> list = new ArrayList<String>();
		List<Integer> arr1 = new ArrayList<Integer>();
		List<Integer> arr2 = new ArrayList<Integer>();
		int flag = 0;
		for (int i = 0; i < userlist.size(); i++) {
			if (CommonUtil.validateEmailAddress(userlist.get(i).getEmail())) {
				if (checkUserEmail(userlist.get(i).getEmail())) {
					arr1.add((i + 2));
					flag = 1;
				} else {
					createUserAccount(userlist.get(i), userobj.getEmail(), path);
				}

			} else {
				flag = 1;
				arr2.add((i + 2));
			}

		}
		if (arr1.size() > 0)
			list.add("Row " + arr1.toString() + " contain already exists email address.");
		if (arr2.size() > 0)
			list.add("Row " + arr2.toString() + "contain invalid email address.");
		map.put("status", flag == 1 ? 0 : 1);
		map.put("errorlist", list);
		return map;
	}

	/**
	 * This is used for getting user list based on created id and role.
	 * 
	 * @param userId
	 * @param orgId
	 * @param createdBy
	 * @return userList
	 */
	public List<User> getUserListBasedOnUserRole(Integer userId, Integer orgId, Integer createdBy) {
		logger.log(Level.DEBUG, "Inside User service getUserListBasedOnUserRole method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST_BASED_ON_USER_ROLE,
					new Object[] { userId, orgId, createdBy, userId, ConstantUtil.TRAINEE_ROLE_ID });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setUserStatus(Integer.parseInt(row.getRowItem(6)));
				user.setRoleType(row.getRowItem(7) != null ? row.getRowItem(7) : "");
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserListBasedOnUserRole method:::::", e);
		}
		return userList;
	}

	/**
	 * This function is used for getting list of all users by creator userId.
	 * 
	 * @param userid
	 * @return userList
	 */
	public List<User> getUserListByCreaterId(Integer userid) {
		logger.log(Level.DEBUG, "Inside User service getUserListByCreaterId method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST_BY_CREATERID,
					new Object[] { userid });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setUserStatus(Integer.parseInt(row.getRowItem(6)));
				user.setRoleType(row.getRowItem(7) != null ? row.getRowItem(7) : "");
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserListByCreaterId method:::::", e);
		}
		return userList;
	}

	/**
	 * This function is used for getting list of all users by creator userId.
	 * 
	 * @param userid
	 * @param action
	 * @return userList
	 */
	public List<User> getUserListByCreaterId(Integer userid, String action) {
		logger.log(Level.DEBUG, "Inside User service getUserListByCreaterId method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			int subfunctionId = 0;
			if (action.equalsIgnoreCase("course")) {
				subfunctionId = 4;
			}
			if (action.equalsIgnoreCase("test")) {
				subfunctionId = 6;
			}
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST_BY_CREATERID_AND_SUBFUNCTION,
					new Object[] { userid, subfunctionId });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setUserStatus(Integer.parseInt(row.getRowItem(6)));
				user.setRoleType(row.getRowItem(7) != null ? row.getRowItem(7) : "");
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserListByCreaterId method:::::", e);
		}
		return userList;
	}

	/**
	 * This function is used for getting list of all users by based on user's
	 * role and status.
	 * 
	 * @param userId
	 * @param orgId
	 * @param createdby
	 * @param status
	 * @return userList
	 */
	public List<User> getUserListBasedOnUserRoleAndStatus(Integer userId, Integer orgId, Integer createdBy,
			Integer status) {
		logger.log(Level.DEBUG, "Inside User service getUserListBasedOnUserRoleAndStatus method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST_BY_BASED_ON_USER_ROLE_AND_STATUS,
					new Object[] { status, userId, orgId, createdBy, userId, ConstantUtil.TRAINEE_ROLE_ID });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setUserStatus(Integer.parseInt(row.getRowItem(6)));
				user.setRoleType(row.getRowItem(7) != null ? row.getRowItem(7) : "");
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserListBasedOnUserRoleAndStatus method:::::", e);
		}
		return userList;
	}

	/**
	 * This function is used for getting list of all student/teachers by creator
	 * userId.
	 * 
	 * @param userid
	 * @param roleId
	 * @return userList
	 */
	public List<User> getUserListByCreaterId(Integer userid, int roleId) {
		logger.log(Level.DEBUG, "Inside User service getUserListByCreaterId method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST_BY_CREATERID_ROLEID,
					new Object[] { userid, roleId });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setUserStatus(Integer.parseInt(row.getRowItem(6)));
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserListByCreaterId method:::::", e);
		}
		return userList;
	}

	/**
	 * This is used for getting list of all trainee user of a organization.
	 * 
	 * @param orgId
	 * @param roleId
	 * @return List<User>
	 */
	public List<User> getUserListByOrgId(Integer orgId, int roleId) {
		logger.log(Level.DEBUG, "Inside User service getUserListByOrgId method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_USER_LIST_BY_ORGID_ROLEID,
					new Object[] { orgId, roleId });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setFirstName(row.getRowItem(1) != null ? row.getRowItem(1).toUpperCase() : row.getRowItem(1));
				user.setLastName(row.getRowItem(2) != null ? row.getRowItem(2).toUpperCase() : row.getRowItem(2));
				user.setMobile(row.getRowItem(3));
				user.setEmail(row.getRowItem(4));
				user.setRegistrationId(row.getRowItem(5));
				user.setUserStatus(Integer.parseInt(row.getRowItem(6)));
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getUserListByOrgId method:::::", e);
		}
		return userList;
	}

	/**
	 * This is used getting group list based on creator id.
	 * 
	 * @param userId
	 * @return userList
	 */
	public List<User> getGroupListByCreaterId(Integer userId) {
		logger.log(Level.DEBUG, "Inside User service getGroupListByCreaterId method:::::");
		List<User> userList = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_GROUP_LIST_BY_CREATERID,
					new Object[] { userId });
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setGroupId(Integer.parseInt(row.getRowItem(0)));
				user.setGroupName(row.getRowItem(1));
				user.setUserMapInGroupCount(row.getRowItem(2) != null ? Integer.parseInt(row.getRowItem(2)) : 0);
				userList.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service getGroupListByCreaterId method:::::", e);
		}
		return userList;
	}

	/**
	 * This is used that group name is already created or not.
	 * 
	 * @param groupName
	 * @param userId
	 * @return flag
	 */
	public static boolean checkGroupName(String groupName, Integer userId) {
		logger.log(Level.DEBUG, "Inside User service checkGroupName method:::::");
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_GROUP_NAME,
					new Object[] { groupName, userId });
			if (data.getRows().size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service checkGroupName method:::::", e);
		}
		return flag;
	}

	/**
	 * This is used for creating new group of user.
	 * 
	 * @param groupName
	 * @param userId
	 * @return id
	 */
	public int createGroup(String groupName, int userId) {
		logger.log(Level.DEBUG, "Inside User service createGroup method:::::");
		int id = 0;
		try {
			id = QueryManager.execuateUpdate(QueryStrings.CREATE_GROUP, new Object[] { groupName, userId });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service createGroup method:::::", e);
		}
		return id;
	}

	/**
	 * This is used for mapping the user with group.
	 * 
	 * @param groupId
	 * @param userId
	 */
	public void mapUserGroup(Integer groupId, Integer userId) {
		logger.log(Level.DEBUG, "Inside User service mapUserGroup method:::::");
		try {
			QueryManager.execuateUpdate(QueryStrings.MAP_USER_GROUP, new Object[] { userId, groupId });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service mapUserGroup method:::::", e);
		}
	}

	/**
	 * This is used for updating user's login time.
	 * 
	 * @param email
	 */
	public void updateUserLoginTime(String email) {
		QueryManager.execuateUpdate(QueryStrings.UPDATE_LAST_LOGIN_TIME_BY_EMAIL, new Object[] { email });

	}

	/**
	 * Getting user's Details based on user's userId.
	 * 
	 * @param userId
	 * @return User
	 */
	public User findOneUser(int userid) {
		logger.log(Level.DEBUG, "Inside User service findOneUser method:::::");
		User user = new User();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.FIND_ONE_USER_BY_ID, new Object[] { userid });
			for (QueryData.Row row : data.getRows()) {
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setEmail(row.getRowItem(1));
				user.setRoleId(Integer.parseInt(row.getRowItem(2)));
				user.setRoleDesc(row.getRowItem(3));
				user.setUserStatus(Integer.parseInt(row.getRowItem(4)));
				user.setCompanyId(row.getRowItem(5) != null ? Integer.parseInt(row.getRowItem(5)) : null);
				user.setOrgId(row.getRowItem(5) != null ? Integer.parseInt(row.getRowItem(5)) : 0);
				user.setFirstName(row.getRowItem(6) != null ? row.getRowItem(6) : "");
				user.setLastName(row.getRowItem(7) != null ? row.getRowItem(7) : "");
				user.setUserName(row.getRowItem(8) != null ? row.getRowItem(8) : "");
				user.setAccessId(row.getRowItem(9) != null ? row.getRowItem(9) : "");
				user.setPermissionId(row.getRowItem(11) != null ? Integer.parseInt(row.getRowItem(11)) : null);
				user.setCreaterId(row.getRowItem(12) != null ? Integer.parseInt(row.getRowItem(12)) : null);
				String path = ConstantUtil.SERVER_IP + ConstantUtil.IMAGE_DIRECTORY + row.getRowItem(10);
				if (row.getRowItem(10) != null)
					user.setImage(path);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service findOneUser method:::::", e);
		}
		return user;
	}

	/**
	 * This is used saved browser detaild and ip details.
	 * 
	 * @param request
	 * @param p_user
	 */
	public void saveIPAddressAndBrowserDetails(HttpServletRequest request, User p_user) {
		logger.log(Level.DEBUG, "Inside User service saveIPAddressAndBrowserDetails method:::::");
		try {
			// is client behind something?
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}

			String str[] = CommonUtil.getOSAndBrowser(request).split("##");
			QueryManager.execuateUpdate(QueryStrings.SAVE_IP_AND_BROWSER_DETAILS,
					new Object[] { p_user.getUserId(), ipAddress, str[0].toString(), str[1].toString() });
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside User service saveIPAddressAndBrowserDetails method:::::", e);
		}
	}

	/**
	 * This is used getting sub domain based on user email.
	 * 
	 * @param user
	 * @return User
	 */
	public User getSubDomainBasedOnEmailId(User user) {
		logger.log(Level.DEBUG, "Inside UserService getSubDomainBasedOnEmailId method::::::::");
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_SUB_DOMAIN_BY_USER_EMAIL,
					new Object[] { user.getEmail() });
			for (QueryData.Row row : data.getRows()) {
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setSubdomain(row.getRowItem(1));
				user.setLicenseId(row.getRowItem(3) != null ? Integer.parseInt(row.getRowItem(3)) : null);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside UserService getSubDomainBasedOnEmailId method::::::::");
		}
		return user;
	}

	/**
	 * This is used for getting list of trainer.
	 * 
	 * @param userId
	 * @return List<User>
	 */
	public List<User> getTrainersList(Integer userId) {
		logger.log(Level.DEBUG, "Inside UserService getTrainersList method::::::::");
		List<User> list = new ArrayList<User>();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.GET_TRAINERS_LIST, new Object[] {});
			for (QueryData.Row row : data.getRows()) {
				User user = new User();
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				if (row.getRowItem(2) != null)
					user.setUserName(row.getRowItem(1) + " " + row.getRowItem(2));
				else
					user.setUserName(row.getRowItem(1));

				list.add(user);
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside UserService getTrainersList method::::::::");
		}
		return list;
	}

	/**
	 * This is used getting super admin details by organization id.
	 * 
	 * @param orgId
	 * @return User
	 */
	public User getSuperUserDetailsByOrgId(Integer orgId) {
		logger.log(Level.DEBUG, "Inside UserService getSuperUserDetailsByOrgId method::::::::");
		User user = new User();
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.SUPER_USER_DETAIL_BASED_ON_ORG_ID,
					new Object[] { ConstantUtil.SUPER_USER_ROLE_ID, orgId });
			for (QueryData.Row row : data.getRows()) {
				user.setUserId(Integer.parseInt(row.getRowItem(0)));
				user.setEmail(row.getRowItem(1));
				user.setSubdomain(row.getRowItem(2));
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception Inside UserService getSuperUserDetailsByOrgId method::::::::");
		}
		return user;
	}

	public boolean addNewUser(User user) {
		logger.log(Level.DEBUG, "Inside User service userRegistration method:::::"+user.getRegistration_type());
		boolean flag = false;
		boolean isExist = false;
		try {
		isExist = checkEmailExists(user.getEmail());
		if (isExist) {
		return flag;
		}
		int id = QueryManager.execuateUpdate(QueryStrings.SAVE_USERS_IN_USER, new Object[] { user.getFirstName(),
		user.getLastName(), user.getEmail(),user.getRoleId(),1,user.getPassword(),user.getOrgId(),user.getRegistration_type()});
		if (id > 0) {
		flag = true;
		}
		} catch (Exception e) {
		logger.log(Level.ERROR, "Exception Inside User service addNewUser method:::::", e);
		}
		return flag;

		}

	public boolean checkEmailExists(String email) {
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_EMAIL_IN_USER, new Object[] { email });
			User user = new User();
			for (QueryData.Row row : data.getRows()) {
				user.setUserId(Integer.parseInt(row.getRowItem(0)));

			}
			if (user.getUserId() != null) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
		}
		return flag;
	}
	
	public boolean checkUserIdExists(Integer userId) {
		boolean flag = false;
		try {
			QueryData data = QueryManager.execuateQuery(QueryStrings.CHECK_USERID_IN_USER, new Object[] { userId });
			User user = new User();
			for (QueryData.Row row : data.getRows()) {
				user.setEmail((row.getRowItem(0)));

			}
			if (user.getEmail() != null) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception e) {
		}
		return flag;
	}
	
}



