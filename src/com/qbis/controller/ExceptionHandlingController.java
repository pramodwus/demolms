package com.qbis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qbis.Exceptions.AuthException;
import com.qbis.Exceptions.ResourceNotFoundException;
import com.qbis.model.ApplicationError;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlingController {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApplicationError resourceNotFoundHandler(HttpServletRequest req,
			ResourceNotFoundException e) {
		System.out.println("NOT_FOUND");
		return e.getApplicationError();
	}

/*	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ApplicationError defaultErrorHandler(HttpServletRequest req,
			Exception e) {
		System.out.println("BAD_REQUEST");
		return new ApplicationError(HttpStatus.BAD_REQUEST.value(),
				e.getMessage());
	}*/

	@ExceptionHandler(AuthException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	public ApplicationError authHandler(HttpServletRequest req, AuthException e) {
		System.out.println("EXPECTATION_FAILED");
		return e.getApplicationError();
	}

}
