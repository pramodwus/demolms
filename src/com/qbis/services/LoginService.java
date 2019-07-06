package com.qbis.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qbis.auth.AuthenticationService;

@Component
public class LoginService {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(LoginService.class);

	@Autowired
	private AuthenticationService authService;

	/**
	 * This method is used for getting user details based on token.
	 * 
	 * @param token
	 * @return
	 */
	public String getUserDetailsFromToken(String token) {
		logger.debug("Inside LoginService in getUserDetailsFromToken method::::::");
		if (token == null) {
			return null;
		}
		String emailId = authService.getUserProfile(token);
		if (emailId == null) {
			return null;
		}
		return emailId;
	}
}
