package com.qbis.model;

import java.util.List;

public class SectionAttempt {

	private Integer attemptId;
	private Boolean isPractice;
	private List<SectionContent> contentList;
	private List<CourseSession> sessionList;
	private Boolean allAttempt1Status;
	

	public Boolean getAllAttempt1Status() {
		return allAttempt1Status;
	}

	public void setAllAttempt1Status(Boolean allAttempt1Status) {
		this.allAttempt1Status = allAttempt1Status;
	}

	public final Integer getAttemptId() {
		return attemptId;
	}

	public final void setAttemptId(Integer attemptId) {
		this.attemptId = attemptId;
	}

	public final Boolean getIsPractice() {
		return isPractice;
	}

	public final void setIsPractice(Boolean isPractice) {
		this.isPractice = isPractice;
	}

	public final List<SectionContent> getContentList() {
		return contentList;
	}

	public final void setContentList(List<SectionContent> contentList) {
		this.contentList = contentList;
	}

	public final List<CourseSession> getSessionList() {
		return sessionList;
	}

	public final void setSessionList(List<CourseSession> sessionList) {
		this.sessionList = sessionList;
	}


}
