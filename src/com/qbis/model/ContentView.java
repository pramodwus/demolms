package com.qbis.model;

public class ContentView {

	private Integer id;
	private Integer testAttemptId;
	private Integer view;
	private Integer contentId;
	private Long sessionId;

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final Integer getTestAttemptId() {
		return testAttemptId;
	}

	public final void setTestAttemptId(Integer testAttemptId) {
		this.testAttemptId = testAttemptId;
	}

	public final Integer getView() {
		return view;
	}

	public final void setView(Integer view) {
		this.view = view;
	}

	public final Integer getContentId() {
		return contentId;
	}

	public final void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public final Long getSessionId() {
		return sessionId;
	}

	public final void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

}
