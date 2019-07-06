package com.qbis.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScormCourseData {
private String courseName;
private String rootDirName;
private String scormCoursePath;
private String learnerId;
private String learnerName;
private Boolean isMultipleSco;
private ArrayList<ScormResource> resource;
private ArrayList<ScormOrganizationItem> items;
private ScormSequencing sequencing;
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getRootDirName() {
	return rootDirName;
}
public void setRootDirName(String rootDirName) {
	this.rootDirName = rootDirName;
}
public String getLearnerId() {
	return learnerId;
}
public void setLearnerId(String learnerId) {
	this.learnerId = learnerId;
}
public String getLearnerName() {
	return learnerName;
}
public void setLearnerName(String learnerName) {
	this.learnerName = learnerName;
}
public ArrayList<ScormOrganizationItem> getItems() {
	return items;
}
public void setItems(ArrayList<ScormOrganizationItem> items) {
	this.items = items;
}
public ArrayList<ScormResource> getResource() {
	return resource;
}
public void setResource(ArrayList<ScormResource> resource) {
	this.resource = resource;
}
public ScormSequencing getSequencing() {
	return sequencing;
}
public void setSequencing(ScormSequencing sequencing) {
	this.sequencing = sequencing;
}
public String getScormCoursePath() {
	return scormCoursePath;
}
public void setScormCoursePath(String scormCoursePath) {
	this.scormCoursePath = scormCoursePath;
}
public Boolean getIsMultipleSco() {
	return isMultipleSco;
}
public void setIsMultipleSco(Boolean isMultipleSco) {
	this.isMultipleSco = isMultipleSco;
}
}
