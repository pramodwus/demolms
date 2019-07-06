package com.qbis.common;

public enum LicenseEnum {
	/**
	 * License sub feature
	 */
	MAXALLOWEDTEST("MAXALLOWEDTEST"),
	MAXALLOWEDCOURSE("MAXALLOWEDCOURSE"),
	QUESTION_TYPE("QUESTION_TYPE"),
	COURSECONTENTSPACE("COURSECONTENTSPACE"),
	MAXALLOWEDUSER("MAXALLOWEDUSER"),
	/**
	 * License features
	 */
	SPACE("SPACE"),
	COURSE("COURSE"),
	TEST("TEST"),
	USERS("USERS")
	;

	private String value;

	private LicenseEnum(String code) {
		this.value = code;
	}

	public String getValue() {
		return value;
	}

}
