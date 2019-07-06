package com.qbis.common;

public enum StudentEnum {
	/**
	 * User test attempt status
	 */
	SUBMIT("1"), PARTIALSUBMIT("0"), PUBLISHED("1"),SCHEDULED("2");

	private String value;

	private StudentEnum(String code) {
		this.value = code;
	}

	public String getValue() {
		return value;
	}

}
