package com.qbis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentProcessingResponse {
private String srcVideo;
private String hlsUrl;
private String dashUrl;
private String workflowStatus;
private String status;
private Integer exprire;
public String getSrcVideo() {
return srcVideo;
}
public void setSrcVideo(String srcVideo) {
this.srcVideo = srcVideo;
}
public String getHlsUrl() {
return hlsUrl;
}
public void setHlsUrl(String hlsUrl) {
this.hlsUrl = hlsUrl;
}
public String getDashUrl() {
return dashUrl;
}
public void setDashUrl(String dashUrl) {
this.dashUrl = dashUrl;
}
public String getWorkflowStatus() {
return workflowStatus;
}
public void setWorkflowStatus(String workflowStatus) {
this.workflowStatus = workflowStatus;
}
public String getStatus() {
return status;
}
public void setStatus(String status) {
this.status = status;
}
public Integer getExprire() {
return exprire;
}
public void setExprire(Integer exprire) {
this.exprire = exprire;
}


}