package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qbis.auth.AuthRequest;
import com.qbis.auth.AuthUser;
import com.qbis.auth.AuthenticationService;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.common.LicenseEnum;
import com.qbis.common.VerifyRecaptcha;
import com.qbis.model.AppFunctions;
import com.qbis.model.ChangePassword;
import com.qbis.model.License;
import com.qbis.model.ServiceResult;
import com.qbis.model.ServiceResult1;
import com.qbis.model.User;
import com.qbis.model.UserAndGroup;
import com.qbis.model.UserProfile;
import com.qbis.model.ServiceResult.StatusCode;
import com.qbis.model.ServiceResult1.StatusCode1;
import com.qbis.services.LoginService;
import com.qbis.services.NotificationService;
import com.qbis.services.OrganizationService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.RoleService;
import com.qbis.services.UserService;

/**
 * Controller class for user related operation.
 * 
 * @author Ankur Kumar
 *
 */
@Controller
public class UserController {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(UserController.class);

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	/**
	 * Instance of {@link UserService}
	 */
	@Autowired
	private UserService userservice;
	/**
	 * Instance of {@link RoleService}
	 */
	@Autowired
	private RoleService roleservice;
     
	@Autowired
	private LoginService loginService;
	
	
	
	@Autowired AuthenticationService authenticationService;

	/**
	 * Method for new user registration.
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public ModelAndView userRegister(@ModelAttribute("userDetails") User req, BindingResult result,
			RedirectAttributes attributes) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller userRegister method ::::: ");
		ModelAndView model = new ModelAndView();
		req.setCompanyId(userservice.addNewOrganization(req));
		req.setPermissionId(1);
		boolean status = userservice.userRegistration(req);
		if (!status) {
			result.reject("msg.invalidlogin");
			model.setViewName("registeruser");
			return model;
		}

		userservice.saveOrgLicenseDetails(req);

		String path = request.getRequestURL().toString();
		path = "http://" + req.getSubdomain() + path.replace("http://", ".").replace("https://", ".");
		userservice.sendVerifyEmail(req.getEmail(), path);
		User user = userservice.findOneUser(req.getEmail());
		request.getSession().setAttribute("userSession", user);
		ArrayList<AppFunctions> appfunctionList = RoleService.getApplicationMenu(user.getPermissionId());
		request.getSession().setAttribute("appfunctions", appfunctionList);
		request.getSession().setAttribute("userSession", user);
		model.setViewName("home");
		return model;
	}

	/**
	 * This is used for checking that provided email address already exists or not.
	 * 
	 * @param email
	 * @return boolean returns true if email is already exist otherwise false.
	 */
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean checkEmail(@ModelAttribute("userEmail") String userEmail) throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller checkEmail method ::::: ");
		boolean isExist = UserService.checkUserEmail(userEmail);
		return isExist;
	}

	/**
	 * This is used for checking that provided email address already exists or not.
	 * 
	 * @param email
	 * @return boolean returns true if email is already exist otherwise false.
	 */
	@RequestMapping(value = "/checkEmailWithURL", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean checkEmailWithSubdomain(@ModelAttribute("userEmail") String userEmail)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller checkEmail method ::::: ");
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		boolean isExist = false;
		if (orgStatus) {
			isExist = UserService.checkUserEmailWithSubDomain(userEmail, orgName);
		}
		return isExist;
	}

	/**
	 * This is used for checking that provided Organization already exists or not.
	 * 
	 * @param orgName
	 * @param email
	 * @return map.
	 */
	@RequestMapping(value = "/checkOrganization", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Boolean> checkOrganizationAndEmail(@RequestParam("orgName") String orgName,
			@RequestParam(value = "email", required = false) String email) throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller checkOrganization method ::::: ");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean emailExist = UserService.checkUserEmail(email);
		map.put("email", emailExist);
		boolean orgExist = OrganizationService.checkOrganizationExist(orgName);
		map.put("subdomain", orgExist);
		if (!emailExist && !orgExist) {
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		return map;
	}

	/**
	 * This method is used for processing the operation for forget password.
	 * 
	 * @param req
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendPassLink", method = RequestMethod.POST)
	public ModelAndView sendPasswordLink(@ModelAttribute("userDetails") User req, ModelMap modelMap)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller sendPasswordLink method ::::: ");
		ModelAndView model = new ModelAndView();
		String otp = CommonUtil.generateRandomString(new Random(), "ABC12340DXYZ@^efgh", 6);
		String path = request.getRequestURL().toString();
		int index = path.lastIndexOf("/");
		String newUrl = path.substring(0, index) + "/resetPassword?access_token=" + otp;
		boolean status = userservice.saveUserToken(req.getEmail(), otp);
		if (status) {
			req.setUrl(newUrl);
			userservice.sendPasswordMail(req);
			model.addObject("success", "successful");
		}
		model.setViewName("forgetpassword");
		modelMap.addAttribute("user", new User());
		return model;
	}

	/**
	 * redirect on reset password page.
	 * 
	 * @param accessToken
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView updatePasswordLink(@RequestParam(value = "access_token", required = false) String accessToken)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller resetPassword method ::::: ");
		ModelAndView model = new ModelAndView();
		if (accessToken != null || accessToken != "") {
			User user = userservice.tokenCorrect(accessToken, true);
			if (user.getEmail() != null) {
				request.getSession().setAttribute("email", user.getEmail());
				model.setViewName("updatepassword");
			} else {
				model.addObject("errormsg", "Link has been expired,try again");
				model.setViewName("forgetpassword");
			}
		}
		return model;
	}

	/**
	 * redirect on reset password page.
	 * 
	 * @param accessToken
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/setPass", method = RequestMethod.GET)
	public ModelAndView setPassword(@RequestParam(value = "token", required = false) String accessToken,
			@RequestParam(value = "courseId", required = false) String courseId,
			@RequestParam(value = "testId", required = false) String testId) throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller setPass method ::::: ");
		ModelAndView model = new ModelAndView();
		if (accessToken != null || accessToken != "") {
			User user = userservice.tokenCorrect(accessToken, false);
			if (user.getEmail() != null) {
				userservice.setEmailVerify(user.getEmail());
				request.getSession().setAttribute("email", user.getEmail());
				request.getSession().setAttribute("setUpPass", "setUpPass");
				model.addObject("setUpPass", "setUpPass");
				model.setViewName("updatepassword");
				if (courseId != null && courseId.matches("^-?\\d+$")) {
					request.getSession().setAttribute("sharedCourseId", Integer.parseInt(courseId.trim()));
				} else if (testId != null && testId.matches("^-?\\d+$")) {
					request.getSession().setAttribute("sharedTestId", Integer.parseInt(testId.trim()));
				}
			} else {
				model.addObject("setUpPass", "setUpPass");
				model.addObject("password", "Your password has been successfully set up.");
				model.setViewName("relogin");
			}
		}
		return model;
	}

	/**
	 * This is used for update the password.
	 * 
	 * @param req
	 * @param result
	 * @param attributes
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ModelAndView updateUserPassword(@ModelAttribute("myForm") User req, BindingResult result,
			RedirectAttributes attributes) throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller updatePassword method ::::: ");
		ModelAndView model = new ModelAndView();
		String email = (String) request.getSession().getAttribute("email");
		String issetUpPass = (String) request.getSession().getAttribute("setUpPass");
		boolean status = userservice.updateUserPass(email, req.getPassword());
		if (status) {
			if (issetUpPass != null) {
				model.addObject("setUpPass", "setUpPass");
				model.addObject("password", "Your password has been successfully set up.");
				request.getSession().removeAttribute("setUpPass");
				User userobj = userservice.findOneUser(email);
				String text = ConstantUtil.INVITEE_REG_IN_SYSTEM;
				if (userobj.getCreaterId() != null) {
					String url = "dummy?userId=" + userobj.getCreaterId();
					String[] targetIds = { userobj.getCreaterId().toString() };
					NotificationService.saveNotifcation(text, url, targetIds);
				}
			} else {
				model.addObject("password", "Your password has been successfully updated.");
			}
			model.setViewName("relogin");
		} else {
			model.addObject("errormsg", "Try again");
			model.setViewName("forgetpassword");
		}
		return model;
	}

	/**
	 * This is used redirect on login page when method type is GET
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public ModelAndView redirectLogin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:login");
		return model;
	}

	/**
	 * This is used for sending verification mail to user.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "sendVerifyMail", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailVerification() throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller sendVerifyMail method ::::: ");
		String result = "";
		boolean status = false;
		User user = (User) request.getSession().getAttribute("userSession");
		if (user.getEmail() != null) {
			String path = request.getRequestURL().toString();
			status = userservice.sendVerifyEmail(user.getEmail(), path);
		}
		result = status ? "true" : "false";
		return result;
	}

	/**
	 * This is used for verify the user's account.
	 * 
	 * @param email
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "accountVerify", method = RequestMethod.GET)
	public ModelAndView accountVerification(@RequestParam(value = "email") String email)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller accountVerify method ::::: ");
		ModelAndView model = new ModelAndView();
		boolean status = false;
		if (email != null) {
			status = userservice.setEmailVerify(email);
			model.setViewName(status ? "relogin" : "login");
		}
		return model;
	}

	/**
	 * This is used for add edit profile of the user.
	 * 
	 * @param userId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "addeditprofile", method = RequestMethod.GET)
	public ModelAndView addProfile() throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller addeditprofile method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		User profile = userservice.getUserProfile(user.getUserId());
		model.addObject("profile", profile);
		@SuppressWarnings("unchecked")
		ArrayList<AppFunctions> appfunctionList = (ArrayList<AppFunctions>) request.getSession()
				.getAttribute("appfunctions");
		List<String> listFunctions = new ArrayList<String>();
		for (int i = 0; i < appfunctionList.size(); i++) {
			listFunctions.add(appfunctionList.get(i).getSubAppFuntion().get(0).getSubFunctionService());
		}
		model.addObject("backlocation", listFunctions.get(0));
		model.setViewName("addeditprofile");
		return model;
	}

	/**
	 * This is used for add edit profile of the user.
	 * 
	 * @param userId
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "updateProfile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@RequestParam("image") MultipartFile file,
			@ModelAttribute("profileForm") User req, BindingResult result, RedirectAttributes attributes)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller updateProfile method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		req.setUserId(user.getUserId());
		if (file.getOriginalFilename().length() != 0) {
			String iconName = userservice.uploadProfileImage(file, user.getUserId());
			if (iconName.length() > 0) {
				req.setImage(iconName);
			}
		}
		userservice.updateUserProfile(req);
		User userSession = userservice.findOneUser(user.getEmail());
		request.getSession().removeAttribute("userSession");
		request.getSession().setAttribute("userSession", userSession);
		@SuppressWarnings("unchecked")
		ArrayList<AppFunctions> appfunctionList = (ArrayList<AppFunctions>) request.getSession()
				.getAttribute("appfunctions");
		List<String> listFunctions = new ArrayList<String>();
		for (int i = 0; i < appfunctionList.size(); i++) {
			listFunctions.add(appfunctionList.get(i).getSubAppFuntion().get(0).getSubFunctionService());
		}
		if (user.getSystemLanguage() == null) {
			model.setViewName("redirect:/" + listFunctions.get(0));
		} else if (listFunctions.get(0).lastIndexOf('?') < 0) {
			model.setViewName("redirect:/" + listFunctions.get(0) + "?locale=" + userSession.getSystemLanguage());
		} else {
			model.setViewName("redirect:/" + listFunctions.get(0) + "&locale=" + userSession.getSystemLanguage());
		}
		return model;
	}

	/*
	 * @RequestMapping(value = "update", method = RequestMethod.POST) public
	 * ModelAndView updateProfileUser(@RequestBody User req) {
	 * logger.log(Level.DEBUG,
	 * "Inside User Controller updateProfileUser method ::::: "); ModelAndView model
	 * = new ModelAndView(); User user = (User)
	 * request.getSession().getAttribute("userSession");
	 * //req.setUserId(user.getUserId()); userservice.updateStudentProfile(req);
	 * 
	 * user=userservice.findOneUser(user.getEmail());
	 * 
	 * System.out.println(user.getUserId()+"====================="+user.getEmail());
	 * //model.addObject("user",user);
	 * System.out.println(user.getFirstName()+"----------------------------------");
	 * 
	 * model.addObject("name",user.getFirstName());
	 * model.setViewName("student/myprofile");
	 * 
	 * return model; }
	 */
	/**
	 * This is used for get User List.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public ModelAndView getUserList(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "status", required = false) String status)

			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller userlist method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		if (action == null) {
			if (status == null) {
				model.addObject("status", -1);
				model.addObject("userlist", userservice.getUserListBasedOnUserRole(user.getUserId(),
						user.getCompanyId(), user.getCreaterId()));
			} else {
				model.addObject("status", Integer.parseInt(status));
				model.addObject("userlist", userservice.getUserListBasedOnUserRoleAndStatus(user.getUserId(),
						user.getCompanyId(), user.getCreaterId(), Integer.parseInt(status)));
			}

			model.addObject("grouplist", userservice.getGroupListByCreaterId(user.getUserId()));
		} else if (action.equals("student")) {
			model.addObject("userlist",
					userservice.getUserListByOrgId(user.getCompanyId(), ConstantUtil.TRAINEE_ROLE_ID));
		} else {
			model.addObject("userlist",
					userservice.getUserListByCreaterId(user.getUserId(), ConstantUtil.TRAINER_ROLE_ID));
		}
		model.addObject("excelSheet", ConstantUtil.SERVER_IP + ConstantUtil.UPLOAD_USER_EXCEL_SHEET_PATH);
		model.addObject("action", action);
		model.setViewName("users");
		return model;
	}

	/**
	 * This is used for get trainee List.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "traineelist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getTraineeList() throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller getTraineeList method ::::: ");
		List<User> userList = userservice.getALLUserList();
		return userList;
	}

	/**
	 * This is used for redirect on create user page.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "addedituser")
	public ModelAndView addEditUser(@RequestParam(value = "action", required = false) String action)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller addEditUser method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("roleList", roleservice.getRolesList(user.getUserId()));
		if (action == null) {
			model.addObject("action", "user");
		} else if (action.equals("trainer")) {
			model.addObject("roleId", 2);
			model.addObject("action", "trainer");
		} else if (action.equals("trainee")) {
			model.addObject("roleId", 3);
			model.addObject("action", "trainee");
		} else {
			model.addObject("action", "user");
		}
		model.setViewName("addedituser");
		return model;
	}

	/**
	 * This is user create user account.
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public ModelAndView createUserAccount(@ModelAttribute("createUserForm") User req, BindingResult result)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller createUserAccount method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		req.setOrgId(user.getCompanyId());
		req.setCreaterId(user.getUserId());
		String path = request.getRequestURL().toString();
		path = path.substring(0, path.lastIndexOf('/'));
		String action = req.getAction();
		userservice.createUserAccount(req, user.getEmail(), path);
		if (action == null) {
			model.setViewName("redirect:userlist");
		} else if (action.equals("trainer")) {
			model.setViewName("redirect:userlist?action=teacher");
		} else if (action.equals("trainee")) {
			model.setViewName("redirect:userlist?action=student");
		} else {
			model.setViewName("redirect:userlist");
		}
		return model;
	}

	/**
	 * This is used for sending email reset link to user after verify email id with
	 * sub domain.
	 * 
	 * @param subdomain
	 * @param email
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "checkEmailWithOrg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<Object, Boolean> checkEmailWithOrg(@RequestParam(value = "subdomain") String subdomain,
			@RequestParam(value = "email") String email) throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller checkEmailWithOrg method ::::: ");
		Map<Object, Boolean> map = new HashMap<Object, Boolean>();
		boolean isSubDomainExist = UserService.checkOrganization(subdomain);
		boolean isEmailLinkWithSubdomain = UserService.checkUserEmailWithSubDomain(email, subdomain);
		map.put("subdomain", isSubDomainExist);
		map.put("email", isEmailLinkWithSubdomain);
		if (isSubDomainExist && isEmailLinkWithSubdomain) {
			String otp = CommonUtil.generateRandomString(new Random(), "ABC12340DXYZ@^efgh", 6);
			String path = request.getRequestURL().toString();
			String serverName = request.getServerName();
			path = path.replaceFirst(serverName, subdomain + "." + serverName);
			int index = path.lastIndexOf("/");
			String newPath = path.substring(0, index) + "/resetPassword?access_token=" + otp;
			boolean status = userservice.saveUserToken(email, otp);
			map.put("passLink", status);
			if (status) {
				User req = new User();
				req.setUrl(newPath);
				req.setEmail(email);
				userservice.sendPasswordMail(req);
			}
		}
		return map;
	}

	/**
	 * Method to check license of organization that it can create new test or not.
	 * 
	 * @return boolean
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "createNewUserLicesneValidate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public License createNewTestLicesneValidate()
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller createNewUserLicesneValidate method :::::");
		License license = (License) request.getSession().getAttribute("licenseObj");
		int orgId = (Integer) request.getSession().getAttribute("orgId");
		License licenseobj = new QbisLicenseService().validateLicense(license, orgId,
				LicenseEnum.MAXALLOWEDUSER.getValue());
		return licenseobj;
	}

	/**
	 * This is used for sending verification mail to user.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "resendVerifyEmail", method = RequestMethod.POST)
	@ResponseBody
	public String resendEmailVerification(@RequestParam(value = "email") String email)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller resendVerifyEmail method ::::: ");
		String result = "";
		boolean status = false;
		User req = new User();
		req.setEmail(email);
		User user = userservice.getSubDomainBasedOnEmailId(req);
		String path = request.getRequestURL().toString();
		status = userservice.sendVerifyEmailBasedOnLicence(user.getEmail(), path, user.getLicenseId());
		result = status ? "true" : "false";
		return result;
	}

	/**
	 * This is used for sending verification mail to user from home page origin.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "resendVerifyEmailfromHome", method = RequestMethod.POST)
	@ResponseBody
	public String resendEmailVerification2(@RequestParam(value = "email") String email)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller resendVerifyEmail method ::::: ");
		String result = "";
		boolean status = false;
		User req = new User();
		req.setEmail(email);
		User user = userservice.getSubDomainBasedOnEmailId(req);
		String path = request.getRequestURL().toString();
		path = "http://" + user.getSubdomain() + path.replace("http://", ".").replace("https://", ".");
		status = userservice.sendVerifyEmailBasedOnLicence(req.getEmail(), path, user.getLicenseId());

		result = status ? "true" : "false";
		return result;
	}

	/**
	 * This is used for registration of new organization.
	 * 
	 * @param req
	 * @param result
	 * @param attributes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/newUserRegister", method = RequestMethod.POST)
	@ResponseBody
	public String newUserRegister(final @ModelAttribute("userDetails") User req, BindingResult result,
			RedirectAttributes attributes) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller newUserRegister method ::::: ");
		boolean isCaptchaValid = VerifyRecaptcha.verify(req.getgRecaptchaResponse());
		if (!isCaptchaValid) {
			return "reCaptcha is not valid!";
		}
		req.setCompanyId(userservice.addNewOrganization(req));
		req.setOrgId(req.getCompanyId());
		req.setPermissionId(1);
		boolean status = userservice.userRegistration(req);
		if (!status) {
			return "Data not saved!";
		}
		userservice.saveOrgLicenseDetails(req);

		String path = request.getRequestURL().toString();
		path = "http://" + req.getSubdomain() + path.replace("http://", ".").replace("https://", ".");
		final String pathnew = path;
		final Integer licenceId = req.getLicenseId();
		(new Thread() {
			@Override
			public void run() {
				userservice.sendVerifyEmailBasedOnLicence(req.getEmail(), pathnew, licenceId);
			}
		}).start();
		request.getSession().setAttribute("userSession", req);
		return "";
	}

	/**
	 * Method is used for import users from the excel.
	 * 
	 * @param req
	 * @param fileName
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadUserFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> uploadUserFile(@RequestParam(value = "userFile") MultipartFile req,
			@RequestParam(value = "fileName") String fileName, @RequestParam(value = "action") String action)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, Exception {
		logger.log(Level.DEBUG, "Inside User Controller uploadUserFile::::::");
		User user = (User) request.getSession().getAttribute("userSession");
		String path = request.getRequestURL().toString();
		path = path.substring(0, path.lastIndexOf('/'));
		int roleId = action.equals("student") ? 3 : 2;
		return userservice.saveUserFromUploadFile(req, fileName, user, path, roleId);
	}

	/**
	 * This is used for redirect on create group page.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "addeditgroup")
	public ModelAndView addEditGroup() throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller addEditGroup method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		model.addObject("userlist", userservice.getUserListByCreaterId(user.getUserId(), 3));
		model.setViewName("addeditgroup");
		return model;
	}

	/**
	 * This is used for checking that provided group name already exists or not.
	 * 
	 * @param groupName
	 * @return boolean returns true if group name is already exist otherwise false.
	 */
	@RequestMapping(value = "/checkGroupName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean checkGroupName(@ModelAttribute("groupName") String groupName)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller checkGroupName method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		boolean isExist = UserService.checkGroupName(groupName, user.getUserId());
		return isExist;
	}

	/**
	 * This is user create user account.
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "createGroup", method = RequestMethod.POST)
	public ModelAndView createGroup(HttpServletRequest req) throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller createGroup method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		int groupId = userservice.createGroup(req.getParameter("groupName"), user.getUserId());
		int userCount = Integer.parseInt(req.getParameter("userCount"));
		for (int i = 0; i < userCount; i++) {
			if (req.getParameter("checkbox" + i) != null) {
				userservice.mapUserGroup(groupId, Integer.parseInt(req.getParameter("checkbox" + i)));
			}
		}
		model.setViewName("redirect:userlist");
		return model;
	}

	/**
	 * This is used for get User List.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "allUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserAndGroup getAllUserList(@RequestParam(value = "action") String action)
			throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller getAllUserList method ::::: ");
		User user = (User) request.getSession().getAttribute("userSession");
		UserAndGroup userAndGroup = new UserAndGroup();
		userAndGroup.setUserList(userservice.getUserListByCreaterId(user.getUserId(), action));
		userAndGroup.setGroupList(userservice.getGroupListByCreaterId(user.getUserId()));
		return userAndGroup;
	}

	/**
	 * This is used for redirect on login page after successfully registration.
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "regsuccess")
	public ModelAndView userRegistrationSuccess() throws IllegalStateException, IOException {
		logger.log(Level.DEBUG, "Inside User Controller userRegistrationSuccess method ::::: ");
		ModelAndView model = new ModelAndView();
		request.getSession().setAttribute("regSuccess", "regSuccess");
		model.setViewName("redirect:login");
		return model;
	}
	
	@RequestMapping(value = "updatepassword1",method=RequestMethod.POST)
	public @ResponseBody String updatePassword1(@RequestHeader("authorization") String access_token,@RequestBody User user) 
	{
	//
	logger.log(Level.DEBUG,
	"Inside User Controller updatePassword1 method ::::: "+access_token);
	if(access_token!=null)
	{
	     String emailId = loginService.getUserDetailsFromToken(access_token); 
	     if(emailId != null)
	     {
	     ChangePassword changePassword=new ChangePassword();
	    		 changePassword.setOld_password(user.getOldPassword());
	     changePassword.setNew_password(user.getPassword());
	     changePassword.setConfirm_password(user.getConfirmPassword());
	     boolean status = authenticationService.changePassword(access_token, changePassword);
	     System.out.println(status);
	    //userservice.updateUserPass(emailId, user.getPassword());
	        if(status)
	             return "true";
	        else
	             return "false";
	     }
	}
	return "false";
	}
	
	




	



}



