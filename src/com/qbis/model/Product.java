package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
private Long id;
private String title;
private String description;
private Double price;
private boolean isSubscriber;
private List<Course> courseList;
private String icon;
private Integer sessions;
private String videocounts;
private String chaptercount;
private Integer courseId;
private String courseName;
private String courseDesc;
private String courseTag;
private String promoVideoUrl;
private String courseImageUrl;
//private String chaptercount;



public Integer getCourseId() {
return courseId;
}

public void setCourseId(Integer courseId) {
this.courseId = courseId;
}

public String getCourseName() {
return courseName;
}

public void setCourseName(String courseName) {
this.courseName = courseName;
}

public String getCourseDesc() {
return courseDesc;
}

public void setCourseDesc(String courseDesc) {
this.courseDesc = courseDesc;
}

public String getCourseTag() {
return courseTag;
}

public void setCourseTag(String courseTag) {
this.courseTag = courseTag;
}

public String getPromoVideoUrl() {
return promoVideoUrl;
}

public void setPromoVideoUrl(String promoVideoUrl) {
this.promoVideoUrl = promoVideoUrl;
}

public String getCourseImageUrl() {
return courseImageUrl;
}

public void setCourseImageUrl(String courseImageUrl) {
this.courseImageUrl = courseImageUrl;
}

public String getChaptercount() {
return chaptercount;
}

public void setChaptercount(String chaptercount) {
this.chaptercount = chaptercount;
}

public boolean isSubscriber() {
return isSubscriber;
}

public void setSubscriber(boolean isSubscriber) {
this.isSubscriber = isSubscriber;
}

public final Long getId() {
return id;
}

public final void setId(Long id) {
this.id = id;
}


public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public final Double getPrice() {
return price;
}

public final void setPrice(Double price) {
this.price = price;
}

public final List<Course> getCourseList() {
return courseList;
}

public final void setCourseList(List<Course> couseList) {
this.courseList = couseList;
}

public String getIcon() {
return icon;
}

public void setIcon(String icon) {
this.icon = icon;
}

public Integer getSessions() {
return sessions;
}

public void setSessions(Integer sessions) {
this.sessions = sessions;
}

public String getVideocounts() {
return videocounts;
}

public void setVideocounts(String videocounts) {
this.videocounts = videocounts;
}



}