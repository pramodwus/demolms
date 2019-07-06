package com.qbis.model;

import java.util.List;

public class Session {
private Integer sessonId;
private Integer sectionId;
private String sessionName;
private Integer isLive;
private Integer isChapterTest;
private Integer isEnable;
private Integer isFree;
private String videContent;
private String VideoUrl;
private String createdDate;
private List<Attempts> attempts;
private List<SectionContent> contents;
private Integer videocompleted;
private Integer videoContentId;
private Boolean isSubscriber;
private String slugImageUrl;
private String videoTime;
//private Integer contentId;
private Integer attemptId;
private Integer isResume;
private List<UserTestAttempt> testAttempts;
private List<UserTestAttempt> userTestAttemptsforchapterTest;
private Integer isStatus;
private String videoTitle;
private String sessionVideoDuration;
private List<Integer> contentId;
private Integer userSessionStatus;


public Integer getUserSessionStatus() {
	return userSessionStatus;
}

public void setUserSessionStatus(Integer userSessionStatus) {
	this.userSessionStatus = userSessionStatus;
}

public List<Integer> getContentId() {
	return contentId;
}

public void setContentId(List<Integer> contentId) {
	this.contentId = contentId;
}

public String getSessionVideoDuration() {
	return sessionVideoDuration;
}

public void setSessionVideoDuration(String sessionVideoDuration) {
	this.sessionVideoDuration = sessionVideoDuration;
}





public String getVideoTitle() {
	return videoTitle;
}

public void setVideoTitle(String videoTitle) {
	this.videoTitle = videoTitle;
}

public Integer getIsStatus() {
	return isStatus;
}

public void setIsStatus(Integer isStatus) {
	this.isStatus = isStatus;
}

public Integer getIsResume() {
	return isResume;
}

public void setIsResume(Integer isResume) {
	this.isResume = isResume;
}


public List<UserTestAttempt> getTestAttempts() {
	return testAttempts;
}

public void setTestAttempts(List<UserTestAttempt> testAttempts) {
	this.testAttempts = testAttempts;
}

public Integer getVideoContentId() {
	return videoContentId;
}

public void setVideoContentId(Integer videoContentId) {
	this.videoContentId = videoContentId;
}

public Integer getVideocompleted() {
	return videocompleted;
}

public void setVideocompleted(Integer videocompleted) {
	this.videocompleted = videocompleted;
}

public List<SectionContent> getContents() {
return contents;
}

public void setContents(List<SectionContent> contents) {
this.contents = contents;
}

public Integer getIsChapterTest() {
return isChapterTest;
}

public void setIsChapterTest(Integer isChapterTest) {
this.isChapterTest = isChapterTest;
}

public Integer getSessonId() {
return sessonId;
}

public void setSessonId(Integer sessonId) {
this.sessonId = sessonId;
}

public Integer getSectionId() {
return sectionId;
}

public void setSectionId(Integer sectionId) {
this.sectionId = sectionId;
}

public String getSessionName() {
return sessionName;
}

public void setSessionName(String sessionName) {
this.sessionName = sessionName;
}

public Integer getIsLive() {
return isLive;
}

public void setIsLive(Integer isLive) {
this.isLive = isLive;
}



public Integer getIsEnable() {
return isEnable;
}

public void setIsEnable(Integer isEnable) {
this.isEnable = isEnable;
}

public Integer getIsFree() {
return isFree;
}

public void setIsFree(Integer isFree) {
this.isFree = isFree;
}



public List<Attempts> getAttempts() {
return attempts;
}

public void setAttempts(List<Attempts> attempts) {
this.attempts = attempts;
}


public String getVideContent() {
return videContent;
}

public void setVideContent(String videContent) {
this.videContent = videContent;
}

public String getVideoUrl() {
return VideoUrl;
}

public void setVideoUrl(String videoUrl) {
VideoUrl = videoUrl;
}

public String getCreatedDate() {
return createdDate;
}

public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

public Boolean getIsSubscriber() {
	return isSubscriber;
}

public void setIsSubscriber(Boolean isSubscriber) {
	this.isSubscriber = isSubscriber;
}

public String getSlugImageUrl() {
	return slugImageUrl;
}

public void setSlugImageUrl(String slugImageUrl) {
	this.slugImageUrl = slugImageUrl;
}

public String getVideoTime() {
	return videoTime;
}

public void setVideoTime(String videoTime) {
	this.videoTime = videoTime;
}


public Integer getAttemptId() {
	return attemptId;
}

public void setAttemptId(Integer attemptId) {
	this.attemptId = attemptId;
}



public List<UserTestAttempt> getUserTestAttemptsforchapterTest() {
	return userTestAttemptsforchapterTest;
}

public void setUserTestAttemptsforchapterTest(List<UserTestAttempt> userTestAttemptsforchapterTest) {
	this.userTestAttemptsforchapterTest = userTestAttemptsforchapterTest;
}


}



