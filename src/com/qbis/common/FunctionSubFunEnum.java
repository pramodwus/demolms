package com.qbis.common;

public enum FunctionSubFunEnum {

	
	/**
	 * Qbis Function status
	 */
	Assessment(2), Courses(3), QuestionLibrary(4),ContentLibrary(5),
	
	/**
	 * Qbis Sub Function status
	 */
	 CreateNewCourse(3),MyCourses(4),CreateNewAssessment(5), MyAssessment(6),
	 QuestionLibrarySub(7),ContentLibrarySub(8);

	private String value;
	
	private Integer intValue;

	private FunctionSubFunEnum(String code) {
		this.value = code;
	}
	
	private FunctionSubFunEnum(Integer code) {
		this.intValue = code;
	}

	public String getValue() {
		return value;
	}
	
	public Integer getIntValue() {
		return intValue;
	}
}
