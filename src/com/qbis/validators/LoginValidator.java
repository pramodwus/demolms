package com.qbis.validators;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbis.model.User;

/**
 * This class is to validate user input fields while login into qbis web
 * application.
 * 
 * @author Vivek Kumar.
 *
 */
@Component
public class LoginValidator implements Validator {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(LoginValidator.class);

	@Override
	public boolean supports(Class<?> compClass) {
		return User.class.equals(compClass);
	}

	/**
	 * Method to validate login field values.
	 */
	@Override
	public void validate(Object obj, Errors err) {
		logger.log(Level.DEBUG, "Inside LoginValidator validate method ::::: ");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "email", "msg.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password",
				"msg.password");
	}

}
