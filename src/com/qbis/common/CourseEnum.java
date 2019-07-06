package com.qbis.common;

public enum CourseEnum {
	/**
	 * COURSE status
	 */
	PUBLISHED("1"), DRAFTED("0"), ENROLLED("1"),SCHEDULED("2");

	private String value;

	private CourseEnum(String code) {
		this.value = code;
	}

	public String getValue() {
		return value;
	}

}
