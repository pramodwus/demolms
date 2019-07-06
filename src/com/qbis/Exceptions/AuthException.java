package com.qbis.Exceptions;

import com.qbis.model.ApplicationError;

public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 3749106216742085920L;
	// @Autowired
	private ApplicationError applicationError;

	/**
	 * @param applicationError
	 */
	public AuthException(Integer errorCode, String msg) {
		applicationError = new ApplicationError();
		applicationError.setStatus(errorCode);
		applicationError.setMsg(msg);
	}

	/**
	 * @return the applicationError
	 */
	public ApplicationError getApplicationError() {
		return applicationError;
	}

	public void setApplicationError(ApplicationError applicationError) {
		this.applicationError = applicationError;
	}
}
