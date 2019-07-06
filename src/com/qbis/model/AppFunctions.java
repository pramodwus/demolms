package com.qbis.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class AppFunctions implements Serializable
{
private int functionId;
private String functionName;
private String functionService;
private String functionClass;
public String getFunctionClass() {
	return functionClass;
}
public void setFunctionClass(String functionClass) {
	this.functionClass = functionClass;
}
private String menuId;
private ArrayList<SubAppFunctions> subAppFuntion;
public ArrayList<SubAppFunctions> getSubAppFuntion() {
	return subAppFuntion;
}
public void setSubAppFuntion(ArrayList<SubAppFunctions> subAppFuntion) {
	this.subAppFuntion = subAppFuntion;
}
public String getMenuId() {
	return menuId;
}
public void setMenuId(String menuId) {
	this.menuId = menuId;
}
public int getFunctionId() {
	return functionId;
}
public void setFunctionId(int functionId) {
	this.functionId = functionId;
}
public String getFunctionName() {
	return functionName;
}
public void setFunctionName(String functionName) {
	this.functionName = functionName;
}
public String getFunctionService() {
	return functionService;
}
public void setFunctionService(String functionService) {
	this.functionService = functionService;
}

}
