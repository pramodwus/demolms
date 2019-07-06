package com.qbis.model;

import com.qbis.common.QbisUtils;

/**
 * This Class is to represent object containing the service result message details.
 * 
 * @author Vivek Kumar.
 *
 */
public class ServiceMessage {

	/**
	 * Variable to store message code, corresponding message description will be picked from messages.properties.
	 */
	private String msgcode;
	/**
	 * Variable to store message description for a particular message code.
	 */
	private String msgdescription;
	/**
	 * Variable to store the message arguments.
	 */
	private Object[] arguments;
	/**
	 * Variable to store the name of the field, whose value has generated the error.
	 */
	private String field;
	/**
	 * Variable to store the name of the model object, whose field has generated the error.
	 */
	private String objectname;
	/**
	 * Variable to store the rejected value.
	 */
	private Object rejectedvalue;
	/**
	 * Variable to store field binding failure.
	 */
	private boolean bindingfailure;
	
	//------------------------------------------------------------------------------------------------------------------	
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param msgCode
	 * @param args
	 */
	public ServiceMessage(String msgcode) {
		this.msgcode 			= msgcode;				
	}
	
	
	public ServiceMessage(String msgcode, Object... arguments) {
		this.msgcode 			= msgcode;
		this.arguments 			= arguments;
		this.msgdescription 	= QbisUtils.getMessageSource().getMessage(msgcode, arguments, null);		
		
	}
	//------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param msgcode
	 * @param arguments
	 * @param field
	 * @param objectname
	 * @param rejectedvalue
	 * @param bindingfailure
	 */
	public ServiceMessage(String msgcode, Object[] arguments
		, String field, String objectname
		, Object rejectedvalue, boolean bindingfailure
	) {
		
		this.msgcode 		= msgcode;
		this.msgdescription = QbisUtils.getMessageSource().getMessage(msgcode, arguments, null);
		this.arguments 		= arguments;
		this.field 			= field;
		this.objectname 	= objectname;
		this.rejectedvalue	= rejectedvalue;
		this.bindingfailure = bindingfailure;
	
	}
	//------------------------------------------------------------------------------------------------------------------
	public String getMsgcode() {
		return msgcode;
	}
	public String getMsgdescription() {
		return msgdescription;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public String getField() {
		return field;
	}

	public String getObjectname() {
		return objectname;
	}

	public Object getRejectedvalue() {
		return rejectedvalue;
	}

	public boolean isBindingfailure() {
		return bindingfailure;
	}
	//------------------------------------------------------------------------------------------------------------------
	
}
