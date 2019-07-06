package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author Ankur Kumar
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SectionContent {
	/*
	 * An Integer which describes an unique id for a content of a section.
	 */
	private Integer contentId;
	/*
	 * A String which describes content's name of a section for a course.
	 */
	private String contentName;
	/*
	 * An Integer which describes an unique id for a section of course.
	 */
	private Integer sectionId;
	/*
	 * A String describes attempts of a course inside a section.
	 */

	private Integer attemptId;

	/*
	 * A String describes content material of a course inside a section.
	 */

	private String content;
	/*
	 * A String which describes additional info about content of section.
	 */
	private String additionalInfo;
	/*
	 * An Integer which describes an unique id for every content inside a
	 * section in course.
	 */
	private Integer contentTypeId;
	/*
	 * A String which is used to describe what is content type (like
	 * pdf,video,test) of section.
	 */
	private String contentType;
	/*
	 * An Integer which describes an unique id for a course.
	 */
	private Integer courseId;
	/*
	 * An Integer which describes an unique id for a test.
	 */
	private Integer testId;
	/*
	 * An Integer which describes an unique id for a course's user.
	 */
	private Integer userId;
	/*
	 * A String which describes about content icon's path
	 */
	private String contentIconPath;
	/*
	 * An Integer which describes the order of content.
	 */
	private Integer contentOrder;

	/*
	 * A String which describes about content icon's path for qbisapp api
	 */
	private String contentIconURL;

	/*
	 * A String describes content download url of a course inside a section for
	 * qbisapp api.
	 */
	private String contentURL;

	/*
	 * A String which describes the path of content.
	 */
	private String contentPath;

	/*
	 * A String which describes the test's name as course's content
	 */
	private String testName;

	private int numPages;

	private String uploadedBy;

	private String description;

	private Integer visiblity;

	private double size;

	/*
	 * A String which describes the course name in which content is mapped or
	 * used
	 */
	private String courseMapped;

	private String createdDate;

	private String extension;

	private String attemptName;
	private String sectionName;

	private Integer contentViewId;

	private Test test;

	private Long sessionId;
	
	private Integer isStatus=0;
	private String sessionName;
    private String streamingUrl;
    private Integer isPractice;
    private Integer isChapterTest;
    private Boolean testStatus;
    private Boolean allAttempt1Status;
    private List<Session> sessions;
    
    

    
    

    
    

	public Boolean getAllAttempt1Status() {
		return allAttempt1Status;
	}

	public void setAllAttempt1Status(Boolean allAttempt1Status) {
		this.allAttempt1Status = allAttempt1Status;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	
	public Boolean getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(Boolean testStatus) {
		this.testStatus = testStatus;
	}

	public Integer getIsChapterTest() {
		return isChapterTest;
	}

	public void setIsChapterTest(Integer isChapterTest) {
		this.isChapterTest = isChapterTest;
	}

	public Integer getIsPractice() {
		return isPractice;
	}

	public void setIsPractice(Integer isPractice) {
		this.isPractice = isPractice;
	}

	public String getStreamingUrl() {
		return streamingUrl;
	}

	public void setStreamingUrl(String streamingUrl) {
		this.streamingUrl = streamingUrl;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public Integer getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(Integer isStatus) {
		this.isStatus = isStatus;
	}

	public String getAttemptName() {
		return attemptName;
	}

	public void setAttemptName(String attemptName) {
		this.attemptName = attemptName;
	}

	/*
	 * An String which describes the content is favorite or not by student
	 */
	private int favorites;

	private Integer favoriateId;

	private String abuseReport;

	private Integer reportId;

	/*
	 * An String which describes the content is embed url or not.
	 */
	private int isExternalURL;

	private List<Question> questionList;

	private Integer videoContentId;

	private Integer audioContentId;

	public Integer getAudioContentId() {
		return audioContentId;
	}

	public void setAudioContentId(Integer audioContentId) {
		this.audioContentId = audioContentId;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Integer getVideoContentId() {
		return videoContentId;
	}

	public void setVideoContentId(Integer videoContentId) {
		this.videoContentId = videoContentId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public Integer getAttemptId() {
		return attemptId;
	}

	public void setAttemptId(Integer attemptId) {
		this.attemptId = attemptId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Integer getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Integer contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentIconPath() {
		return contentIconPath;
	}

	public void setContentIconPath(String contentIconPath) {
		this.contentIconPath = contentIconPath;
	}

	public Integer getContentOrder() {
		return contentOrder;
	}

	public void setContentOrder(Integer contentOrder) {
		this.contentOrder = contentOrder;
	}

	public String getContentIconURL() {
		return contentIconURL;
	}

	public void setContentIconURL(String contentIconURL) {
		this.contentIconURL = contentIconURL;
	}

	public String getContentURL() {
		return contentURL;
	}

	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getNumPages() {
		return numPages;
	}

	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVisiblity() {
		return visiblity;
	}

	public void setVisiblity(Integer visiblity) {
		this.visiblity = visiblity;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getCourseMapped() {
		return courseMapped;
	}

	public void setCourseMapped(String courseMapped) {
		this.courseMapped = courseMapped;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	public Integer getFavoriateId() {
		return favoriateId;
	}

	public void setFavoriateId(Integer favoriateId) {
		this.favoriateId = favoriateId;
	}

	public String getAbuseReport() {
		return abuseReport;
	}

	public void setAbuseReport(String abuseReport) {
		this.abuseReport = abuseReport;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public int getIsExternalURL() {
		return isExternalURL;
	}

	public void setIsExternalURL(int isExternalURL) {
		this.isExternalURL = isExternalURL;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public final Test getTest() {
		return test;
	}

	public final void setTest(Test test) {
		this.test = test;
	}

	public final Integer getContentViewId() {
		return contentViewId;
	}

	public final void setContentViewId(Integer contentViewId) {
		this.contentViewId = contentViewId;
	}

	public final Long getSessionId() {
		return sessionId;
	}

	public final void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}


}
