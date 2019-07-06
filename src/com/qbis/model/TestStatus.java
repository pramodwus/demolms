package com.qbis.model;

public class TestStatus {
private Integer attemptId;
private Boolean status;
private Integer sessionId;
private Integer contentId;
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
public Integer getAttemptId() {
return attemptId;
}
public void setAttemptId(Integer attemptId) {
this.attemptId = attemptId;
}
public Boolean getStatus() {
return status;
}
public void setStatus(Boolean status) {
this.status = status;
}

}