package com.qbis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the result object returned from the service. It
 * contains the return code and the list of {@link ServiceMessage} and object
 * containing the service response data.
 * 
 * @author Vivek Kumar.
 *
 */
public class ServiceResult1 {

	/**
	 * Variable to store status code.
	 */
	private int code;
	
	private String msg;
	/**
	 * List containing the {@link ResultMessageObject}
	 */
	//private List<ServiceMessage> resultmsg;
	/**
	 * Object containing service response object.
	 */
	private Object result;

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param resultmsg
	 * @param result
	 */
	public ServiceResult1(StatusCode1 code, String msg, Object result) {
		System.out.println(msg);
		this.code = code.statusCode;
		this.msg = msg;
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/*
	 * public ServiceResult(StatusCode code, List<ServiceMessage> resultmsg, Object
	 * result) { this.code = code.statusCode; this.resultmsg = resultmsg;
	 * this.result = result; }
	 */

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param resultmsg
	 * @param result
	 */
	/*
	 * public ServiceResult(StatusCode code, ServiceMessage resultmsg, Object
	 * result) { this.code = code.statusCode; this.resultmsg = new
	 * ArrayList<ServiceMessage>(); this.resultmsg.add(resultmsg); this.result =
	 * result; }
	 */

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param result
	 */
	public ServiceResult1(StatusCode1 code, Object result) {
		this.code = code.statusCode;
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public int getCode() {
		return code;
	}

	

	public Object getResult() {
		return result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Enumeration for Status Codes.
	 * 
	 * @author Vivek Kumar.
	 *
	 */
	public enum StatusCode1 {

		SUCCESS(200), SERVERERROR(500), WARNING(-1),NOTFOUND(404),UNAUTHORIZED(401),FORBIDDEN(403),ERROR(0);

		private int statusCode;

		private StatusCode1(int code) {
			this.statusCode = code;
		}

	}
	// ------------------------------------------------------------------------------------------------------------------

	public enum IsPractice {

		YES(1), NO(0);

		private int value;

		private IsPractice(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	/**
	 * Tag Mapping Type
	 * 
	 * @author bellurbis
	 *
	 */
	public enum TagMappingType {

		TEST("test"), Question("question");

		private String value;

		private TagMappingType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum ContentStatus {

		PROCESSING(0), FINISHED(1), FAILED(2);

		private int value;

		private ContentStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	public enum TagTypeKey {

		BAORD("board"), SESSION("session"), CLASS("class"), SUBJECT("subject"), CHAPTER("chapter"), ACT("act");

		private String value;

		private TagTypeKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
}
