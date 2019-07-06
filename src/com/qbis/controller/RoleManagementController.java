package com.qbis.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qbis.model.Roles;
import com.qbis.model.User;
import com.qbis.services.RoleService;
import com.qbis.validators.LoginValidator;

/**
 * This class is handle Role Management  request created by Super admin.
 * 
 * @author kuldeep Singh
 *
 */
@Controller
public class RoleManagementController
{
private static final Logger logger = Logger.getLogger(RoleManagementController.class);
	
	/**
	 * Instance of {@link HttpServletRequest}
	 */
	@Autowired
	private HttpServletRequest request;	
	/**
	 * Instance of DAO {@link RoleValidator}.
	 */
	
	
	private String addNewRole="addnewrole";
	
	private String roleListPage="rolelist";
	/**
	 * Instance of {@link LoginValidator}
	 */
	@Autowired
	private RoleService roleService;
	/**
	 * Show All role credited By user
	 * @return modelView
	 */
	@RequestMapping(value="/rolelist")
	public ModelAndView  roleListPage(){
		logger.log(Level.DEBUG, "Get All Role Created User");
		ModelAndView model = new ModelAndView();
		User user=(User)request.getSession().getAttribute("userSession");
		model.addObject("rolelist",roleService.getRolesList(user.getUserId()));
		model.setViewName(roleListPage);
		return model;
	}
	/**
	 * Open New Role Page
	 * */
	@RequestMapping(value="/addNewRole")
	public ModelAndView openAddNewRolePage()
	{
		logger.log(Level.DEBUG, "Add New Role Page");
		User user=(User)request.getSession().getAttribute("userSession");
		ModelAndView model = new ModelAndView();
		   model.setViewName(addNewRole);
		   model.addObject("appfunctions",roleService.getAllApplicationMenu());
		return model;
	}
	/**
	 * Add New Role 
	 * */
	@RequestMapping(value="/addrole", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean saveRole(@RequestBody Roles role)
	{
		
		Integer roleId=roleService.saveRole(role);
		if(roleId>0)
		{
			
		}
		return true;          
		
		
	}
}
