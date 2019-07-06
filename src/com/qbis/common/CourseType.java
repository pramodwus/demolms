package com.qbis.common;

public enum CourseType {
SYSTEM_COURSE("0"),SCORM_COURSE("1");
private String value;
private CourseType(String code){
	this.value = code;
}

public String getValue(){
	return value;
}
}
