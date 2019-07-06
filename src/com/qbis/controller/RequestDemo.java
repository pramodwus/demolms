package com.qbis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qbis.common.CommonUtil;
import com.qbis.common.EmailSender;
import com.qbis.common.SimpleMail;
import com.qbis.model.User;
import com.qbis.services.OrganizationService;
import com.qbis.services.UserService;

import freemarker.template.TemplateException;

@Controller
public class RequestDemo {
	
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger
			.getLogger(RequestDemo.class);
	
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;

	@Autowired
	SimpleMail simpleMail;
	
	@Autowired
	UserService userService;
		
	@RequestMapping(value = "/sendDemoRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean sendRequestDemo(@ModelAttribute("user") User user, BindingResult result){
		logger.log(Level.DEBUG,
				"Inside UploadContentController getUploadedContentList method ::::: ");
		boolean flag = true;
		StringBuilder sb = new StringBuilder();
		sb.append("<p>Following user has expressed intrest in Qbis.</p><br/><p><b>User Name: </b>");
		sb.append(user.getName());
		sb.append("</p><p><b>Organization Name: </b>");
		sb.append(user.getOrgName());
		sb.append("</p><p><b>Email: </b>");
		sb.append(user.getEmail());
		sb.append("</p><p><b>Comment: </b>");
		sb.append(user.getAbout());
		final String str = sb.toString();
		System.out.println("Email " + user.getEmail());
		try {			
			(new Thread() {
				@Override
				public void run() {
					simpleMail.sendEmail("info@qbis.in", "info@qbis.in", "Request a demo for Qbis", str);
				}
			  }).start();
		}catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		
		if(flag){
		final String username = user.getName();
		final String useremail = user.getEmail();		
		
		final Map<Object, Object> dataobject = new HashMap<Object, Object>();						
		dataobject.put("name", username);
		
		(new Thread() {
			@Override
			public void run() {
				try {
					EmailSender.sendEmail(useremail,
							"Welcome to Qbis Demo",
							EmailSender.generateRequestDemoWelcomeMessg(dataobject)
							);
				}catch (NoSuchMessageException e) {			
					e.printStackTrace();
				}catch(MessagingException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}catch(TemplateException e){
					e.printStackTrace();
				}
			}
		  }).start();
		}
		
		return flag;		
	}
	
	@RequestMapping(value = "/requestfordemo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean requestForDemo(@RequestBody User user){
		logger.log(Level.DEBUG,
				"Inside UploadContentController getUploadedContentList method ::::: ");
		boolean flag = true;
		StringBuilder sb = new StringBuilder();
		sb.append("<p>Following user has expressed interest in Qbis.</p><br/><p><b>Email: </b>");
		sb.append(user.getEmail());
		final String str = sb.toString();
		try {			
			(new Thread() {
				@Override
				public void run() {
					simpleMail.sendEmail("info@qbis.in", "info@qbis.in", "Request a demo for Qbis", str);
				}
			  }).start();
		}catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		
		if(flag){
		//final String username = user.getName();
		final String useremail = user.getEmail();		
		
		final Map<Object, Object> dataobject = new HashMap<Object, Object>();						
		dataobject.put("name", useremail);
		
		(new Thread() {
			@Override
			public void run() {
				try {
					EmailSender.sendEmail(useremail,
							"Welcome to Qbis Demo",
							EmailSender.generateRequestDemoWelcomeMessg(dataobject)
							);
				}catch (NoSuchMessageException e) {			
					e.printStackTrace();
				}catch(MessagingException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}catch(TemplateException e){
					e.printStackTrace();
				}
			}
		  }).start();
		}
		
		return flag;		
	}
	
	@RequestMapping(value = "/sendcontactusrequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean requestForContactUs(@RequestBody User user){
		boolean flag = true;
		StringBuilder sb = new StringBuilder();
		sb.append("<p>Following user wants contact with qbis team.</p><br/><p><b>Name: </b>");
		sb.append(user.getName());
		sb.append("</p><br/><p><b>Email: </b>");
		sb.append(user.getEmail());
		sb.append("</p>");
		final String str = sb.toString();
		try {			
			(new Thread() {
				@Override
				public void run() {
					simpleMail.sendEmail("info@qbis.in", "info@qbis.in", "Request for contact", str);
				}
			  }).start();
		}catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		
		if(flag){
		final String username = user.getName();
		final String useremail = user.getEmail();		
		
		final Map<Object, Object> dataobject = new HashMap<Object, Object>();						
		dataobject.put("name", username);
		
		(new Thread() {
			@Override
			public void run() {
				try {
					EmailSender.sendEmail(useremail,
							"Contact us",
							EmailSender.generateContactUsWelcomeMessg(dataobject)
							);
				}catch (NoSuchMessageException e) {			
					e.printStackTrace();
				}catch(MessagingException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}catch(TemplateException e){
					e.printStackTrace();
				}
			}
		  }).start();
		}
		return flag;
	}
	@RequestMapping(value = "/logincheck")
	public 
	@ResponseBody
	int logincheck(HttpServletRequest request) throws IOException{
		return userService.checkLoginDetails(request.getParameter("loginsubdomain"),request.getParameter("loginemail"),request.getParameter("loginpassword"));
	}
	
	/**
	 * Method for Open qbis requestatdemo page.
	 * 
	 */
	@RequestMapping(value = "/requestatdemo")
	public ModelAndView qbisRequestAtDemoPage() {
		logger.log(Level.DEBUG,
				"Inside Login Controller qbisRequestAtDemoPage method::::::::: ");
		ModelAndView model = new ModelAndView();
		String orgName = CommonUtil.getOrgIdFromURL(request);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		if(orgStatus){
			model.setViewName("redirect:/login");
		}else{
			model.setViewName("requestatdemo");	
		}
		
		return model;
	}
}
