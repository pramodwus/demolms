package com.qbis.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.qbis.common.CommonUtil;
import com.qbis.common.ConstantUtil;
import com.qbis.model.User;
import com.qbis.services.OrganizationService;
import com.qbis.services.QbisLicenseService;
import com.qbis.services.UserService;

/**
 * Interceptor for validating session id of the user. This class has been
 * plugged in qbis-servlet.xml file
 * 
 * @author Vivek Kumar.
 *
 */
public class UserAuthenticator extends HandlerInterceptorAdapter {

	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private HttpServletRequest httpRequest;
	private static final Logger logger = Logger
			.getLogger(UserAuthenticator.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.log(Level.DEBUG, "Inside UserAuthenticator Interceptor ::::: ");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession(true);
		String orgName = CommonUtil.getOrgIdFromURL(httpRequest);
		boolean orgStatus = OrganizationService.checkOrganizationExist(orgName);
		if (orgStatus) {
			int orgId = OrganizationService.getOrganizationId(orgName);
			session.setAttribute("orgId", orgId);
			if (session != null) {
				User user = (User) session.getAttribute("userSession");
				if (session.getAttribute("licenseObj") == null) {
					session.setAttribute("licenseObj",
							QbisLicenseService.companyLicenseDetails(orgId));
				}
				if (user != null) {
					if (user.getUserStatus() == 0) {
						User u = new UserService().findOneUser(user.getEmail());
						session.setAttribute("userStatus", u.getUserStatus());
						session.removeAttribute("userSession");
						session.setAttribute("userSession", u);
					}
					/*
					 * if(modelAndView!=null &&
					 * modelAndView.getViewName().equals("login")){
					 * if(user.getRoleId()==4){ modelAndView.setViewName(
					 * "redirect:/studentdashboard?action=list"); }else{
					 * //modelAndView
					 * .setViewName("redirect:/dashboardcontroller"); } }
					 */
				} else {
					String path = new UrlPathHelper()
							.getPathWithinApplication(request);
					if (path != null && path.equalsIgnoreCase("/reguser")) {
						modelAndView.setViewName("redirect:/login");
					}
				}
			}
		} else if (orgName.trim().length() > 0) {
			if (modelAndView == null) {
				modelAndView = new ModelAndView();
			}
			String serverName = httpRequest.getServerName();
			String redirectPath = httpRequest.getRequestURL().toString();
			redirectPath = redirectPath.replaceFirst(serverName,
					ConstantUtil.DOMAIN_NAME);
			String path = new UrlPathHelper().getPathWithinApplication(request);
			if (path == null || path.equalsIgnoreCase("/")
					|| path.equalsIgnoreCase("/reguser")) {
				modelAndView.setViewName("redirect:" + redirectPath);
			}
			if (session != null) {
				session.removeAttribute("orgId");
			}
		}

		else {
			if (modelAndView == null) {
				modelAndView = new ModelAndView();
			}
			String path = new UrlPathHelper().getPathWithinApplication(request);
			if (path == null || path.equalsIgnoreCase("/")) {
				modelAndView.setViewName("forward:home");
			}
			if (session != null) {
				session.removeAttribute("orgId");
			}
		}
	}
}
