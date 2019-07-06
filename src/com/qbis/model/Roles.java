package com.qbis.model;

import java.util.ArrayList;



public class Roles 
{
private int roleId;
private String roleName;
private String roleDesc;
private int roleCreatorId;

private String functionsName;
public String getFunctionsName() {
	return functionsName;
}
public void setFunctionsName(String functionsName) {
	this.functionsName = functionsName;
}
private ArrayList<AppFunctions> appfunctions;
public ArrayList<AppFunctions> getAppfunctions() {
	return appfunctions;
}
public void setAppfunctions(ArrayList<AppFunctions> appfunctions) {
	this.appfunctions = appfunctions;
}
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getRoleDesc() {
	return roleDesc;
}
public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
}
public int getRoleCreatorId() {
	return roleCreatorId;
}
public void setRoleCreatorId(int roleCreatorId) {
	this.roleCreatorId = roleCreatorId;
}

}
