package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.model.AppFunctions;
import com.qbis.model.User;
import com.qbis.services.LoginService;
import com.qbis.services.OrganizationService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.RoleService;
import com.qbis.services.UserService;
import com.qbis.validators.LoginValidator;

/**
 * Controller class for user login and displaying home page.
 * 
 * @author Ankur Kumar.
 *
 */
@Controller
public class LoginController {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * Instance of {@link LoginValidator}
	 */
	@Autowired
	private LoginValidator validator;

	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	/**
	 * Instance of {@link UserService}
	 */
	@Autowired
	private UserService userService;

	/**
	 * Instance of {@link LoginValidator}
	 */
	@Autowired
	private QbisLicenseService qbisLicenseService;
	/**
	 * Instance of {@link LoginService}
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * Method for displaying home page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletResponse response, @RequestParam(value = "token", required = false) String t1)
			throws Exception {
		logger.log(Level.DEBUG, "Inside Login Controller home method ::::: ");
		// t1="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNTU3NTc3MjkwfQ.gMD0x_fDk5YW3_Br-z1nx6EwRdMsV7kt2iTjHURkvY4";
		ModelAndView model = new ModelAndView();
		String token = CommonUtil.getTokenFromCookie(request);
		// String
		//token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTU5LCJleHAiOjE1NjE1NDQyNjZ9.55gELLDPQiZqTIJFO7t9eE-rBIuq7p7DtTxdhy-1t9s";
		logger.log(Level.DEBUG, "Inside Login Controller home method ::::: token = " + token);
		String emailId = loginService.getUserDetailsFromToken(token);
		logger.log(Level.DEBUG, "Inside Login Controller home method ::::: emailId = " + emailId);
		if (emailId == null) {
			model.setViewName("login");
	       response.sendRedirect(ConstantUtil.LOGOUT);
			return model;
		}
	
		if (t1 != null) {
			CommonUtil.setUserCookie(t1, response);
		}
		// logger.log(Level.DEBUG, "Set User Cookie ::::: " +
		// CommonUtil.setUserCookie("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNTU3NTc3MjkwfQ.gMD0x_fDk5YW3_Br-z1nx6EwRdMsV7kt2iTjHURkvY4",
		// response);
		// logger.log(Level.DEBUG, "Delete User Cookie ::::: " +
		// CommonUtil.deleteUserCookie(request, response));
		User user = userService.findOneUser(emailId);
		if(user == null)
		{
			response.sendRedirect(ConstantUtil.LOGOUT);
		}
		ArrayList<AppFunctions> appfunctionList = RoleService.getApplicationMenu(user.getPermissionId());
		request.getSession().setAttribute("appfunctions", appfunctionList);
		request.getSession().setAttribute("userSession", user);
		/*
		 * int orgId = (int) request.getSession().getAttribute("orgId"); Boolean flag
		 * =jdbc.databaseurl=jdbc:mysql://localhost:3306/qbis?autoReconnect=true&
		 * characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull da.name=qbis
		 * jdbc.username=root jdbc.password=root
		 * QbisLicenseService.checkLicenseValidity(orgId); if (flag != null) { if
		 * (!flag) { model.addObject("expired", "Qbis License has been expired.");
		 * model.setViewName("login"); return model; } }
		 */
		userService.updateUserLoginTime(emailId);
		userService.saveIPAddressAndBrowserDetails(request, user);
		List<String> listFunctions = new ArrayList<String>();
		for (int i = 0; i < appfunctionList.size(); i++) {
			listFunctions.add(appfunctionList.get(i).getSubAppFuntion().get(0).getSubFunctionService());
		}
		if (user.getSystemLanguage() == null) {
			model.setViewName("redirect:/" + listFunctions.get(0));
		} else if (listFunctions.get(0).lastIndexOf('?') < 0) {
			model.setViewName("redirect:/" + listFunctions.get(0));
		} else {
			model.setViewName("redirect:/" + listFunctions.get(0));
		}
		return model;
	}

	/**
	 * Method for application login.
	 * 
	 * @param req
	 * @param result
	 * @return
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/auth")
	public ModelAndView adminLogin(@ModelAttribute("userLogin") User req, BindingResult result,
			RedirectAttributes attributes) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

		logger.log(Level.DEBUG, "Inside Login Controller auth method ::::: ");
		ModelAndView model = new ModelAndView();
		validator.validate(req, result);
		if (result.hasErrors()) {
			model.setViewName("login");
			return model;
		}
		Boolean isValid = userService.userAuthenticate(req.getEmail(), req.getPassword(), req.getOrgId());
		if (!isValid) {
			result.reject("msg.invalidlogin");
			model.addObject("loginStatus", "fail");
			model.setViewName("login");
			return model;
		}

		User user = userService.findOneUser(req.getEmail());
		if (user.getUserStatus() == 0) {
			model.addObject("loginStatus", "unverified");
			model.addObject("mailid", user.getEmail());
			model.setViewName("login");
			return model;
		}
		ArrayList<AppFunctions> appfunctionList = RoleService.getApplicationMenu(user.getPermissionId());
		request.getSession().setAttribute("appfunctions", appfunctionList);
		request.getSession().setAttribute("userSession", user);

		int orgId = (int) request.getSession().getAttribute("orgId");
		Boolean flag = QbisLicenseService.checkLicenseValidity(orgId);
		if (flag != null) {
			if (!flag) {
				model.addObject("expired", "Qbis License has been expired.");
				model.setViewName("login");
				return model;
			}
		}
		userService.updateUserLoginTime(req.getEmail());
		userService.saveIPAddressAndBrowserDetails(request, user);

		Integer courseId = (Integer) request.getSession().getAttribute("sharedCourseId");
		if (courseId != null && courseId > 0) {
			attributes.addAttribute("action", "coursedetail");
			attributes.addAttribute("courseId", courseId);
			model.setViewName("redirect:/studentCourse");
			request.getSession().removeAttribute("sharedCourseId");
			;
		} else {
			List<String> listFunctions = new ArrayList<String>();
			for (int i = 0; i < appfunctionList.size(); i++) {
				listFunctions.add(appfunctionList.get(i).getSubAppFuntion().get(0).getSubFunctionService());
			}
			if (user.getSystemLanguage() == null) {
				model.setViewName("redirect:/" + listFunctions.get(0));
			} else if (listFunctions.get(0).lastIndexOf('?') < 0) {
				model.setViewName("redirect:/" + listFunctions.get(0) + "?locale=" + user.getSystemLanguage());
			} else {
				model.setViewName("redirect:/" + listFunctions.get(0) + "&locale=" + user.getSystemLanguage());
			}

		}
		return model;

	}

	/**
	 * Method for User Registration Page.
	 * 
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/reguser")
	public ModelAndView userRegister() throws JsonProcessingException {
		logger.log(Level.DEBUG, "Inside Login Controller userRegister method::::::::: ");
		ModelAndView model = new ModelAndView();
		User user = new User();
		user.setDomainName(ConstantUtil.DOMAIN_NAME);
		model.addObject("userReg", user);
		model.addObject("licenselist", qbisLicenseService.getLicense());
		model.setViewName("registeruser");
		return model;
	}

	/**
	 * This is used for redirect on forget password page.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/forgetPass")
	public ModelAndView forgetPass() {
		logger.log(Level.DEBUG, "Inside Login Controller forgetPass method ::::: ");
		ModelAndView model = new ModelAndView();
		Integer orgId = (Integer) request.getSession().getAttribute("orgId");
		if (orgId != null && orgId > 0) {
			model.addObject("user", new User());
			model.setViewName("forgetpassword");
		}
		return model;
	}

	/**
	 * Method for User Logout.
	 * 
	 * @return
	 * @throws IOException
	 * @throws InvalidKeySpecException1
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletResponse response) {
		logger.log(Level.DEBUG, "Inside Login Controller Logout method ::::: ");
		ModelAndView modelview = new ModelAndView();
		request.getSession().removeAttribute("userSession");
		request.getSession().invalidate();
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		boolean cookieStatus = CommonUtil.deleteUserCookie(request, response);
		if (orgStatus == true && cookieStatus == true) {
			modelview.setViewName("redirect:" + ConstantUtil.LOGOUT);
		} else {
			modelview.setViewName("redirect:" + ConstantUtil.LOGOUT);
		}
		return modelview;
	}

	/**
	 * This is used for redirect on home page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String qbisHome(ModelMap model) {
		logger.log(Level.DEBUG, "Inside Login Controller qbisHome method::::::::: ");
		model.addAttribute("userReg", new User());
		model.addAttribute("licenselist", qbisLicenseService.getLicenseList());
		return "home";
	}

	/**
	 * This is used for login from home page of qbis with subdomain name
	 * 
	 * @param req
	 * @param attributes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/authuseratrequestdemo")
	public ModelAndView authuser(HttpServletRequest req, RedirectAttributes attributes)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG, "Inside Login Controller authuser method ::::: ");
		ModelAndView model = new ModelAndView();
		User user = userService.findOneUser(req.getParameter("loginemail"));
		ArrayList<AppFunctions> appfunctionList = RoleService.getApplicationMenu(user.getPermissionId());
		request.getSession().setAttribute("appfunctions", appfunctionList);
		request.getSession().setAttribute("userSession", user);

		List<String> listFunctions = new ArrayList<String>();
		for (int i = 0; i < appfunctionList.size(); i++) {
			listFunctions.add(appfunctionList.get(i).getSubAppFuntion().get(0).getSubFunctionService());
		}
		if (user.getSystemLanguage() == null) {
			model.setViewName("redirect:/" + listFunctions.get(0));
		} else if (listFunctions.get(0).lastIndexOf('?') < 0) {
			model.setViewName("redirect:/" + listFunctions.get(0) + "?locale=" + user.getSystemLanguage());
		} else {
			model.setViewName("redirect:/" + listFunctions.get(0) + "&locale=" + user.getSystemLanguage());
		}
		return model;

	}

	/**
	 * Method for Open User Login Page at home page.
	 * 
	 */
	@RequestMapping(value = "/loginathome")
	public ModelAndView userLoginPage() {
		logger.log(Level.DEBUG, "Inside Login Controller userLoginPage method::::::::: ");
		ModelAndView model = new ModelAndView();
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		if (orgStatus) {
			model.setViewName("redirect:/login");
		} else {
			User user = new User();
			user.setDomainName(ConstantUtil.DOMAIN_NAME);
			model.addObject("userLogin", user);
			model.setViewName("loginathome");
		}

		return model;
	}

	/**
	 * Method for Open qbis features page.
	 * 
	 */
	@RequestMapping(value = "/homefeatures")
	public ModelAndView qbisFeaturesPage() {
		logger.log(Level.DEBUG, "Inside Login Controller qbisFeaturesPage method::::::::: ");
		ModelAndView model = new ModelAndView();
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		if (orgStatus) {
			model.setViewName("redirect:/login");
		} else {
			model.setViewName("homefeatures");
		}

		return model;
	}

}



