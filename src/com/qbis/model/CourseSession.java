package com.qbis.model;

import java.util.List;

public class CourseSession {
	private Integer isfree;
	public Integer getIsfree() {
		return isfree;
	}

	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}

	
	private Integer userSessionStatus;
	private Long id;
	private String name;
	private Integer videoStatus;
	
	public Integer getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}


	private List<SectionContent> contentList;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final List<SectionContent> getContentList() {
		return contentList;
	}

	public final void setContentList(List<SectionContent> contentList) {
		this.contentList = contentList;
	}
	
	

	public Integer getUserSessionStatus() {
		return userSessionStatus;
	}

	public void setUserSessionStatus(Integer userSessionStatus) {
		this.userSessionStatus = userSessionStatus;
	}

	@Override
	public String toString() {
		return "CourseSession [id=" + id + ", name=" + name + ", contentList=" + contentList + "]";
	}

}



