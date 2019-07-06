package com.qbis.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

import com.qbis.model.User;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.TestService;
@Controller
public class SettingController 
{
	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private static final Logger logger = Logger
			.getLogger(SettingController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	/**
	 * Instance of {@link TestService}
	 */
	@Autowired
	private QbisLicenseService license;
	/**
	 * This is used to getting created course list on based of user's id
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 */
	@RequestMapping(value = "/settings")
	public ModelAndView setting() throws NoSuchAlgorithmException,
			InvalidKeySpecException, IOException {
		logger.log(Level.DEBUG,
				"Inside Setting Controller ::::  ");
		User user = (User) request.getSession().getAttribute("userSession");
		System.out.println("Org Id Is "+user.getOrgId());
		ModelAndView model = new ModelAndView();
		model.addObject("licenseDeatils", license.getLicenseDetails(user.getOrgId()));
		model.addObject("licenseList", license.getLicense());
		model.setViewName("settings");
		return model;
	}
	/**
	 * Open New Role Page
	 * */
	@RequestMapping(value="/upgrade")
	public ModelAndView openAddNewRolePage()
	{
		logger.log(Level.DEBUG, "Upgrade Page ");
	/*	User user=(User)request.getSession().getAttribute("userSession");*/
		ModelAndView model = new ModelAndView();
		User user = (User) request.getSession().getAttribute("userSession");
		System.out.println("User Org Id iS "+user);
	   	model.addObject("licenseDeatils", license.getLicenseDetails(user.getOrgId()));
		   model.addObject("licenseList", license.getLicense());
		   model.setViewName("index");
		return model;
	}
	@RequestMapping(value="/upGradeSignUP")
	public ModelAndView openAddNewRolePage1()
	{
		logger.log(Level.DEBUG, "Upgrade Page ");
		User user=(User)request.getSession().getAttribute("userSession");
		ModelAndView model = new ModelAndView();
	    System.out.println("User Org Id iS "+user+"action is ");
	   //	model.addObject("licenseDeatils", license.getLicenseDetails(user.getOrgId()));
		   model.addObject("licenseList", license.getLicense());
		   model.setViewName("upgradeSignup");
		return model;
	}
}
