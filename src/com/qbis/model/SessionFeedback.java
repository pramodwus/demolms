package com.qbis.model;
public class SessionFeedback {
	
	private Integer userId;
	private Integer sessionId;
	private Integer courseId;
	private Integer sectionId;

	private Integer contentId;
	private boolean isGood;
	private boolean isBad;
	private boolean isExcellent;
	private String userFeedback;
	private String goodReviewCount;
	private String badReviewCount;
	private String excellentReviewCount;
	private String email;
	private String review;
	private String secondryEmail;
	private boolean isEmailValidated;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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
	public boolean getIsGood() {
		return isGood;
	}
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}
	public boolean getIsBad() {
		return isBad;
	}
	public void setBad(boolean isBad) {
		this.isBad = isBad;
	}
	public boolean getIsExcellent() {
		return isExcellent;
	}
	public void setExcellent(boolean isExcellent) {
		this.isExcellent = isExcellent;
	}
	public String getUserFeedback() {
		return userFeedback;
	}
	public void setUserFeedback(String userFeedback) {
		this.userFeedback = userFeedback;
	}
	public String getGoodReviewCount() {
		return goodReviewCount;
	}
	public void setGoodReviewCount(String goodReviewCount) {
		this.goodReviewCount = goodReviewCount;
	}
	public String getBadReviewCount() {
		return badReviewCount;
	}
	public void setBadReviewCount(String badReviewCount) {
		this.badReviewCount = badReviewCount;
	}
	public String getExcellentReviewCount() {
		return excellentReviewCount;
	}
	public void setExcellentReviewCount(String excellentReviewCount) {
		this.excellentReviewCount = excellentReviewCount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public String getSecondryEmail() {
		return secondryEmail;
	}
	public void setSecondryEmail(String secondryEmail) {
		this.secondryEmail = secondryEmail;
	}
	
	public boolean isEmailValidated() {
		return isEmailValidated;
	}
	public void setEmailValidated(boolean isEmailValidated) {
		this.isEmailValidated = isEmailValidated;
	}
	@Override
	public String toString() {
		return "SessionFeedback [userId=" + userId + ", sessionId=" + sessionId + ", courseId=" + courseId
				+ ", sectionId=" + sectionId + ", contentId=" + contentId + ", isGood=" + isGood + ", isBad=" + isBad
				+ ", isExcellent=" + isExcellent + ", userFeedback=" + userFeedback + ", goodReviewCount="
				+ goodReviewCount + ", badReviewCount=" + badReviewCount + ", excellentReviewCount="
				+ excellentReviewCount + ", email=" + email + ", review=" + review + "]";
	}
	
	

}
