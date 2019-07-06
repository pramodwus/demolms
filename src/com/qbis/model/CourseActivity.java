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
public class CourseActivity {
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
	private Integer spentTime;
	
	/*
	 * An Integer which describes the content id of latest attempted content.
	 */
    private Integer contentId;
    
    /*
	 * An Integer which describes the section id of latest attempted section.
	 */
    private Integer sectionId;
    
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

	public Integer getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(Integer spentTime) {
		this.spentTime = spentTime;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

}
