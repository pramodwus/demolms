package com.qbis.common;

public enum QBisVersion {

	/**
	 * Version
	 */
	VERSION_1("v1"), VERSION_2("v2");

	private String value;

	private QBisVersion(String code) {
		this.value = code;
	}

	public String getValue() {
		return value;
	}
}
