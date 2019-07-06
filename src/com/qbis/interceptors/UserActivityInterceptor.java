package com.qbis.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qbis.services.StudentCourseService;

/**
 * Interceptor for validating session id of the user. This class has been
 * plugged in qbis-servlet.xml file
 * 
 * @author Neeraj Singh.
 *
 */
public class UserActivityInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Instance of {@link Logger}
	 */
	@Autowired
	private StudentCourseService studentCourseService;
	private static final Logger logger = Logger
			.getLogger(UserActivityInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.log(Level.DEBUG, "Inside UserActivityInterceptor Interceptor ::::: ");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession(false);
		if(session!=null){
			String vcId = request.getSession().getAttribute("viewedCourseId")!=null?
					request.getSession().getAttribute("viewedCourseId").toString():null;
			if(vcId!=null){
				
				String uri = request.getRequestURI();				
				String queryUri = request.getQueryString();

				System.out.println("RequestURI====="+request.getRequestURI());
				System.out.println("QueryString====="+request.getQueryString());
				
				int flag = 0;
				if(uri!=null && uri.length()>13){
					if(uri.contains("studentCourse")){
						if(queryUri!=null){							
							if(queryUri.contains("coursedetail")){
							  flag = 1;							  
							}
						}
					}else if(uri.contains("viewCourseContent")){
						flag = 1;						
					}else if(uri.contains("getnotifications")){
						flag = 1;						
					}else if(uri.contains("resources")){
						flag = 1;
					}
					
					System.out.println("======finally flag====="+flag);
					if(flag==0){
						studentCourseService.editCourseViewDetailsByUser(
								Integer.parseInt(vcId));
						request.getSession().setAttribute("viewedCourseId",null);
					}
				}		
				
			}			
		}
		
	}
}
