package com.qbis.model;

import java.util.List;

public class UserAndGroup 
{
/*
 * An List which describes userlist .
 */	
private List<User> userList;
/*
 * An List which describes GroupList.
 */	
private List<User> groupList;
public List<User> getUserList() {
	return userList;
}
public void setUserList(List<User> userList) {
	this.userList = userList;
}
public List<User> getGroupList() {
	return groupList;
}
public void setGroupList(List<User> groupList) {
	this.groupList = groupList;
}
}
