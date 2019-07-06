package com.qbis.model;

import java.util.List;

public class Attempts {

/*
* An Integer which describes an unique id of a user for a attempt.
*/
private Integer id;
/*
* An Integer which describes a unique key of attempt.
*/
private String key;
/*
* A String which describes Attempt's Name.
*/
private String name;
/*
* A String which describes Attempt is practice or not.
*/
private Integer attemptList;
/*
* A Integer which gives total no of attempts 
*/

private String isPractice;


private List<SectionContent> contents;

private String quizTitle;

private Integer contentId;

private Integer sessionId;
private Boolean testStatus;
private Integer testId;
private Integer videocompleted;
private Integer totalQuestion;
private Double testMark;

private Integer sectionId;
private String sectionName;
private Integer isChapterTest;	
private Integer parentId;
private Integer  ispractice;
private Integer courseId;
private String courseName;
private String courseDesc;
private String promoVideoUrl;

public Integer getCourseId() {
	return courseId;
}
public void setCourseId(Integer courseId) {
	this.courseId = courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getCourseDesc() {
	return courseDesc;
}
public void setCourseDesc(String courseDesc) {
	this.courseDesc = courseDesc;
}
public String getPromoVideoUrl() {
	return promoVideoUrl;
}
public void setPromoVideoUrl(String promoVideoUrl) {
	this.promoVideoUrl = promoVideoUrl;
}
public Integer getIspractice() {
	return ispractice;
}
public void setIspractice(Integer ispractice) {
	this.ispractice = ispractice;
}
public Integer getSectionId() {
	return sectionId;
}
public void setSectionId(Integer sectionId) {
	this.sectionId = sectionId;
}
public String getSectionName() {
	return sectionName;
}
public void setSectionName(String sectionName) {
	this.sectionName = sectionName;
}
public Integer getIsChapterTest() {
	return isChapterTest;
}
public void setIsChapterTest(Integer isChapterTest) {
	this.isChapterTest = isChapterTest;
}
public Integer getParentId() {
	return parentId;
}
public void setParentId(Integer parentId) {
	this.parentId = parentId;
}
public Integer getTotalQuestion() {
	return totalQuestion;
}
public void setTotalQuestion(Integer totalQuestion) {
	this.totalQuestion = totalQuestion;
}
public Double getTestMark() {
	return testMark;
}
public void setTestMark(Double testMark) {
	this.testMark = testMark;
}
public Integer getVideocompleted() {
	return videocompleted;
}
public void setVideocompleted(Integer videocompleted) {
	this.videocompleted = videocompleted;
}
public Integer getAttemptList() {
return attemptList;
}
public Integer setAttemptList(Integer attemptList) {
return this.attemptList = attemptList;
}
public Integer getId() {
return id;
}
public void setId(Integer id) {
this.id = id;
}
public String getKey() {
return key;
}
public void setKey(String key) {
this.key = key;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public String getIsPractice() {
return isPractice;
}
public void setIsPractice(String isPractice) {
this.isPractice = isPractice;
}
public List<SectionContent> getContents() {
return contents;
}
public void setContents(List<SectionContent> contents) {
this.contents = contents;
}
public String getQuizTitle() {
return quizTitle;
}
public void setQuizTitle(String quizTitle) {
this.quizTitle = quizTitle;
}
public Integer getContentId() {
return contentId;
}
public void setContentId(Integer contentId) {
this.contentId = contentId;
}
public Integer getSessionId() {
return sessionId;
}
public void setSessionId(Integer sessionId) {
this.sessionId = sessionId;
}
public Boolean getTestStatus() {
	return testStatus;
}
public void setTestStatus(Boolean testStatus) {
	this.testStatus = testStatus;
}
public Integer getTestId() {
	return testId;
}
public void setTestId(Integer testId) {
	this.testId = testId;
}




}


