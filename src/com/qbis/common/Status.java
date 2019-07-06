package com.qbis.common;

public enum Status {
	
	DRAFTED("0"), PUBLISHED("1"),SCHEDULED("2"),SHARED("1");

	private String value;

	private Status(String code) {
		this.value = code;
	}

	public String getValue() {
		return value;
	}
}
