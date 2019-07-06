package com.qbis.Exceptions;

/**
 * Class to manage resource not found exception, set error code and error message.
 * 
 * @author Kuldeep Kumar
 *
 */

import com.qbis.model.ApplicationError;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7840974262199833503L;

	private ApplicationError applicationError;

	public ResourceNotFoundException(Integer errorCode, String msg) {
		System.out.println("error 1" + applicationError);
		applicationError = new ApplicationError();
		applicationError.setStatus(errorCode);
		applicationError.setMsg(msg);
	}

	/**
	 * @return the applicationError
	 */
	public ApplicationError getApplicationError() {
		System.out.println("error 2" + applicationError);
		return applicationError;
	}

}
