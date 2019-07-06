package com.qbis.model;

public class CourseAttemptResponse {
	private Course course;
	private SectionContent content;

	public final Course getCourse() {
		return course;
	}

	public final void setCourse(Course course) {
		this.course = course;
	}

	public final SectionContent getContent() {
		return content;
	}

	public final void setContent(SectionContent content) {
		this.content = content;
	}
}
