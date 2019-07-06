package com.qbis.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class SubAppFunctions implements Serializable
{
	private int subFunctionId;
	private String subFunctionName;
	private String subFunctionService;
	private String subMenuId;
	private String subfunctionClass;
	
	public String getSubfunctionClass() {
		return subfunctionClass;
	}
	public void setSubfunctionClass(String subfunctionClass) {
		this.subfunctionClass = subfunctionClass;
	}
	public int getSubFunctionId() {
		return subFunctionId;
	}
	public void setSubFunctionId(int subFunctionId) {
		this.subFunctionId = subFunctionId;
	}
	public String getSubFunctionName() {
		return subFunctionName;
	}
	public void setSubFunctionName(String subFunctionName) {
		this.subFunctionName = subFunctionName;
	}
	public String getSubFunctionService() {
		return subFunctionService;
	}
	public void setSubFunctionService(String subFunctionService) {
		this.subFunctionService = subFunctionService;
	}
	public String getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}
}
