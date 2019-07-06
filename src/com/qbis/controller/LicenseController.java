package com.qbis.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LicenseController {
	
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(DashboardController.class);
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * Method to get create license page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/createlicense", method=RequestMethod.GET)
	public ModelAndView createLicensePage(){		
		logger.log(Level.DEBUG, "LicenseController createLicensePage method.... ");
		ModelAndView model = new ModelAndView();
		//model.addObject("jobprofilelist",dao.getAllJobProfiles());
		model.setViewName("createlicense");
		return model;
	}

}
