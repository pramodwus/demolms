package com.qbis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * A class which describes the activities of particular user for course.
 * 
 * @author ankur
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ContentActivity {

	/*
	 * An Integer which describes the activity id for content.
	 */
	private Integer contentActivityId;

	/*
	 * An Integer which describes the activity id for course.
	 */
	private Integer courseActivityId;

	/*
	 * An Integer which describes the id of course.
	 */
	private Integer courseId;

	/*
	 * An Integer which describes the id for user.
	 */
	private Integer userId;

	/*
	 * A String which describes the start time for course.
	 */
	private String startTime;

	/*
	 * A String which describes the end time for course.
	 */
	private String endTime;

	/*
	 * An Integer which describes the spent time on course.
	 */
	private String spentTime;

	/*
	 * An Integer which describes the section id for a content.
	 */
	private Integer sectionId;

	/*
	 * An Integer which describes the id for content.
	 */
	private Integer contentId;

	/*
	 * An Integer which describes the attempted id for a test.
	 */
	private Integer attemptedTestId;
	
	private Integer sessionId;
	
	private Integer attemptId;
    
	private Integer resumeTime;
	private Integer isMobile;
	
	
	

	public Integer getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Integer isMobile) {
		this.isMobile = isMobile;
	}

	public Integer getResumeTime() {
		return resumeTime;
	}

	public void setResumeTime(Integer resumeTime) {
		this.resumeTime = resumeTime;
	}

	public Integer getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Integer attemptId) {
		this.attemptId = attemptId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getContentActivityId() {
		return contentActivityId;
	}

	public void setContentActivityId(Integer contentActivityId) {
		this.contentActivityId = contentActivityId;
	}

	public Integer getCourseActivityId() {
		return courseActivityId;
	}

	public void setCourseActivityId(Integer courseActivityId) {
		this.courseActivityId = courseActivityId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(String spentTime) {
		this.spentTime = spentTime;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getAttemptedTestId() {
		return attemptedTestId;
	}

	public void setAttemptedTestId(Integer attemptedTestId) {
		this.attemptedTestId = attemptedTestId;
	}

}



