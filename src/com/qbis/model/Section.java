package com.qbis.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
* 
* @author Ankur Kumar
*
*/
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Section {
/*
* An Integer which describes an unique id for a test.
*/
private Integer testId;
/*
* An Integer which describes an unique id of a section in a test or course.
*/
private Integer sectionId;
/*
* A String which describes section's name.
*/
private String sectionName;
/*
* A Question type array which describes a question list for a section.
*/
private Question questionList[];
/*
* An Integer which describes the ordering of section for a test.
*/
private Integer sectionSortOrder;
/*
* An Integer which describes an unique id for a course.
*/
private Integer courseId;
/*
* A SectionContent type array for section content of course.
*/
private SectionContent sectionContent[];
/*
* An Integer which describes that section should be update or not at the
* time of test saving.
*/
private Integer isUpdate;
/*
* A String which describes the course's name of section
*/
private String courseName;
/*
* A String has all information about section's content.
*/
private String contentInfo;
/*
* An Integer which describes the total marks for a section.
*/
private Integer sectionScore;
/*
* An Integer which describes that section is deleted or not.
*/
private Integer isDelete;
/*
* A String which describes that mapped test's name of this test.
*/
private String testName;
/*
* An Integer which describes how many questions are in this sections.
*/
private Integer totalQuestions;
/*
* A String which describes Time Bound of a test.
*/
private String sectionTime;
/*
* An Integer which describes that section is attempted by user.
*/
private Integer sectionStatus;
/*
* An Integer which describes user_section_attempt's id.
*/
private Integer userSectionAttemptId;

/*
* An Integer which describes that quiz is mandatory in section or not.
*/
private Integer isQuizMandatory;
/*
* An Integer which describes that how much passing criteria(in percentage)
* is for every section.
*/
private Integer passingCriteria;
/*
* An Integer which describes that how much minimum time is required for
* spending on a particular section for passing it.
*/
private Integer minTimeSpent;

/*
* An Integer which describes that how much time is required for spending on
* a particular section for passing it.
*/
private Integer totalSpentTime;

/*
* A Boolean which describes that user has passed the passing criteria or
* not.
*/
private Boolean isPassedTheCriteria;

/*
* A Boolean which describes that user has passed the passing criteria or
* not.
*/

private String chapterImageUrl;

private Integer isChapterTest;	
private Integer sessionId;
private String sessionName; 
private Integer chapterProgress;
private Integer videoview;
private String timeSpent;
private String performnceTillDate;
private Boolean allAttemptStatus;
private Boolean allAttempt1Status;
private Integer isPractice;

private Integer attempt_id;
private Integer attempt_name;
private Integer sectionWithinSameCourse;

private List<SectionContent> contents;

private List<SectionAttempt> attemptList;
private List<Session> session;
private Integer parentId;
private Integer containsChapterTest;
private List<Section> chapterSection;
private String introVIdeoUrl;

private List<Attempts> attempts;
private List<Double> marks;


public List<Double> getMarks() {
	return marks;
}

public void setMarks(List<Double> marks) {
	this.marks = marks;
}

public List<Attempts> getAttempts() {
	return attempts;
}

public void setAttempts(List<Attempts> attempts) {
	this.attempts = attempts;
}

public Boolean getAllAttemptStatus() {
	return allAttemptStatus;
}

public Boolean getAllAttempt1Status() {
	return allAttempt1Status;
}

public void setAllAttempt1Status(Boolean allAttempt1Status) {
	this.allAttempt1Status = allAttempt1Status;
}

public void setAllAttemptStatus(Boolean allAttemptStatus) {
	this.allAttemptStatus = allAttemptStatus;
}

public Integer getParentId() {
return parentId;
}

public void setParentId(Integer parentId) {
this.parentId = parentId;
}

public List<Session> getSession() {
return session;
}

public void setSession(List<Session> session) {
this.session = session;
}

public Integer getAttempt_id() {
return attempt_id;
}

public void setAttempt_id(Integer attempt_id) {
this.attempt_id = attempt_id;
}

public Integer getAttempt_name() {
return attempt_name;
}

public void setAttempt_name(Integer attempt_name) {
this.attempt_name = attempt_name;
}

public Integer getIsPractice() {
return isPractice;
}

public void setIsPractice(Integer isPractice) {
this.isPractice = isPractice;
}

public Integer getSectionSortOrder() {
return sectionSortOrder;
}

public void setSectionSortOrder(Integer sectionSortOrder) {
this.sectionSortOrder = sectionSortOrder;
}

public Question[] getQuestionList() {
return questionList;
}

public void setQuestionList(Question questionList[]) {
this.questionList = questionList;
}

public String getSectionName() {
return sectionName;
}

public void setSectionName(String sectionName) {
this.sectionName = sectionName;
}

public Integer getSectionId() {
return sectionId;
}

public void setSectionId(Integer sectionId) {
this.sectionId = sectionId;
}

public Integer getTestId() {	

return testId;
}

public Integer getIsChapterTest() {
return isChapterTest;
}

public void setIsChapterTest(Integer isChapterTest) {
this.isChapterTest = isChapterTest;
}

public void setTestId(Integer testId) {
this.testId = testId;
}

public Integer getCourseId() {
return courseId;
}

public void setCourseId(Integer courseId) {
this.courseId = courseId;
}

public SectionContent[] getSectionContent() {
return sectionContent;
}

public void setSectionContent(SectionContent sectionContent[]) {
this.sectionContent = sectionContent;
}

public Integer getIsUpdate() {
return isUpdate;
}

public void setIsUpdate(Integer isUpdate) {
this.isUpdate = isUpdate;
}

public String getCourseName() {
return courseName;
}

public void setCourseName(String courseName) {
this.courseName = courseName;
}

public String getContentInfo() {
return contentInfo;
}

public void setContentInfo(String contentInfo) {
this.contentInfo = contentInfo;
}

public Integer getSectionScore() {
return sectionScore;
}

public void setSectionScore(Integer sectionScore) {
this.sectionScore = sectionScore;
}

public Integer getIsDelete() {
return isDelete;
}

public void setIsDelete(Integer isDelete) {
this.isDelete = isDelete;
}

public String getTestName() {
return testName;
}

public void setTestName(String testName) {
this.testName = testName;
}

public Integer getTotalQuestions() {
return totalQuestions;
}

public void setTotalQuestions(Integer totalQuestions) {
this.totalQuestions = totalQuestions;
}

public String getSectionTime() {
return sectionTime;
}

public void setSectionTime(String sectionTime) {
this.sectionTime = sectionTime;
}

public Integer getSectionStatus() {
return sectionStatus;
}

public void setSectionStatus(Integer sectionStatus) {
this.sectionStatus = sectionStatus;
}

public Integer getUserSectionAttemptId() {
return userSectionAttemptId;
}

public void setUserSectionAttemptId(Integer userSectionAttemptId) {
this.userSectionAttemptId = userSectionAttemptId;
}

public Integer getIsQuizMandatory() {
return isQuizMandatory;
}

public void setIsQuizMandatory(Integer isQuizMandatory) {
this.isQuizMandatory = isQuizMandatory;
}

public Integer getPassingCriteria() {
return passingCriteria;
}

public void setPassingCriteria(Integer passingCriteria) {
this.passingCriteria = passingCriteria;
}

public Integer getMinTimeSpent() {
return minTimeSpent;
}

public void setMinTimeSpent(Integer minTimeSpent) {
this.minTimeSpent = minTimeSpent;
}

public Integer getTotalSpentTime() {
return totalSpentTime;
}

public void setTotalSpentTime(Integer totalSpentTime) {
this.totalSpentTime = totalSpentTime;
}

public Boolean getIsPassedTheCriteria() {
return isPassedTheCriteria;
}

public void setIsPassedTheCriteria(Boolean isPassedTheCriteria) {
this.isPassedTheCriteria = isPassedTheCriteria;
}

public List<SectionContent> getContents() {
return contents;
}

public void setContents(List<SectionContent> contents) {
this.contents = contents;
}

public Integer getSectionWithinSameCourse() {
return sectionWithinSameCourse;
}

public void setSectionWithinSameCourse(Integer sectionWithinSameCourse) {
this.sectionWithinSameCourse = sectionWithinSameCourse;
}

public final List<SectionAttempt> getAttemptList() {
return attemptList;
}

public final void setAttemptList(List<SectionAttempt> attemptList) {
this.attemptList = attemptList;
}

public Integer getSessionId() {
return sessionId;
}

public void setSessionId(Integer sessionId) {
this.sessionId = sessionId;
}

public String getSessionName() {
return sessionName;
}

public void setSessionName(String sessionName) {
this.sessionName = sessionName;
}

public Integer getChapterProgress() {
	return chapterProgress;
}

public void setChapterProgress(Integer chapterProgress) {
	this.chapterProgress = chapterProgress;
}

public Integer getVideoview() {
	return videoview;
}

public void setVideoview(Integer videoview) {
	this.videoview = videoview;
}

public String getTimeSpent() {
	return timeSpent;
}

public void setTimeSpent(String timeSpent) {
	this.timeSpent = timeSpent;
}

public String getPerformnceTillDate() {
	return performnceTillDate;
}

public void setPerformnceTillDate(String performnceTillDate) {
	this.performnceTillDate = performnceTillDate;
}

public Integer getContainsChapterTest() {
	return containsChapterTest;
}

public void setContainsChapterTest(Integer containsChapterTest) {
	this.containsChapterTest = containsChapterTest;
}

public List<Section> getChapterSection() {
	return chapterSection;
}

public void setChapterSection(List<Section> chapterSection) {
	this.chapterSection = chapterSection;
}



public String getChapterImageUrl() {
	return chapterImageUrl;
}

public void setChapterImageUrl(String chapterImageUrl) {
	this.chapterImageUrl = chapterImageUrl;
}

public String getIntroVIdeoUrl() {
	return introVIdeoUrl;
}

public void setIntroVIdeoUrl(String introVIdeoUrl) {
	this.introVIdeoUrl = introVIdeoUrl;
}





}


