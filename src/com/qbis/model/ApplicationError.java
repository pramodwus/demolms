package com.qbis.model;

import org.springframework.stereotype.Component;

/**
 * Data Object for holding error details in qbis web application.
 */
@Component
public class ApplicationError {
	/**
	 * Variable for error status.
	 */
	private Integer status;
	/**
	 * Variable for error message.
	 */
	private String msg;

	public ApplicationError(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public ApplicationError() {

	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
