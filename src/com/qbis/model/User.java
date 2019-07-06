package com.qbis.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User {
/*
* An Integer which describes user's user id.
*/
private Integer userId;
/*
* A String which describes user's name.
*/
private String userName;
/*
* A String which describes user's password.
*/
private String password;
/*
* An Integer which describes user's status.
*/
private int userStatus;
/*
* message
*/
private String msg;
/*
* Organization id
*/
private Integer orgId;
/*
* Organization Name
*/
private String orgName;
/*
* Sub Domain Name
*/
private String subdomain;
/*
* User's Name
*/
private String Name;
/*
* User's email is exist or not.
*/
private Boolean exists;

/*
* User's first name
*/
private String firstName;
/*
* User's last name
*/
private String lastName;
/*
* User's Registration date
*/
private String joinedOn;
/*
* User's age
*/
private Integer age;
/*
* User's country name
*/
private String country;
/*
* User's state name
*/
private String state;
/*
* User's profile image
*/
private String image;
/*
* web address
*/
private String url;
/*
* access id of user
*/
private String accessId;
/*
* User's DOB
*/
private String dob;

/*
* User's details other
*/
private String about;
/*
* String for registrationId of user from app.
*/
private String registrationId;
/*
* An Integer which describes the group id.
*/
private Integer groupId;
/*
* An String which describes the group name.
*/
private String groupName;
/*
* A String which describes user's email id.
*/
private String email;
/*
* An Integer which describes company'id.
*/
private Integer companyId;
/*
* A String describes that User is Internal or External
*/
private String roleType;
/*
* An Integer which describes User's role according to user's role_id
*/
private Integer roleId;
/*
* A String which describes role description of a user.
*/
private String roleDesc;
/*
* A Integer which describes status
*/
private Integer status;
/*
* A String which describes user's device information
*/
private String deviceId;
/*
* A Integer which describes user's mobile number
*/
private String mobile;
/*
* A String which describes user's type.
*/
private String user_type;
/*
* A Integer which describes user role
*/
private Integer permissionId;

/*
* An Integer which describes user's creator id.
*/
private Integer createrId;
/*
* An Object which describes the license
*/
private License license;
/*
* An Integer which describes the license id.
*/
private Integer licenseId;
/*
* A string which defines action.
*/
private String action;
/*
* A boolean which describes user status. if flag is true
*/
private boolean flag;
/*
* An Integer Which describes total user map in group.
*/
private Integer userMapInGroupCount;
/*
* A String which describes the google re-captcha.
*/
private String gRecaptchaResponse;
/*
* A String which describes the domain name.
*/
private String domainName;

/*
* A string which describe last login date.
*/
private String lastLoginDate;

/*
* Collection of attempted tests for user reports.
*/
private List<Test> testList;
/*
* Collection of created course list for user reports.
*/
private List<Course> createdCourseList;
/*
* Reference variable of SectionContent object for upload content data in user reports.
*/
private SectionContent contentDetails;
/*
* Collection of created tests list for user reports.
*/
private List<Test> createdTestList;
/*
* integer to get questions count created by user.
*/
private int questionCount;
/*
* String for get browser name last login by user.
*/
private String browser;
/*
* String for get operating system last login by user.
*/
private String os;
/*
* String for get IP address from which user last login.
*/
private String ipAddress;
/*
* String for course name.
*/
private String courseName;
/*
* integer for enroll course count.
*/
private int enrollCourseCount;
/*
* String for test name.
*/
private String testName;
/*
* integer for display test attempts count.
*/
private int attemptsCount;
/*
* integer for display created test count.
*/
private int testCount;
/*
* integer for display created course count.
*/
private int courseCount;
/*
* integer for userId who modify role of user.
*/
private Integer roleModifiedById;
/*
* String for userName who modify role of user.
*/
private String roleModifiedByName;
/*
* String for display modified date of role of user.
*/
private String roleModifyDate;
/*
* String for display modified date of account of user.
*/
private String accountModifyDate;
/*
* String for userName who modify account of user.
*/
private String accountModifyByName;

/*
* Collection of course list for reports.
*/
private List<Course> courseList;
/*
* String for display last view course content for reports.
*/
private String lastViewContent;
/*
* Integer for display total count of viewed course content for reports.
*/
private Integer viewContentCount;

/*
* A String which describes the default language for system.
*/
private String systemLanguage;

private String registration_type;




public String getRegistration_type() {
return registration_type;
}

public void setRegistration_type(String registration_type) {
this.registration_type = registration_type;
}

public String getOldPassword() {
return oldPassword;
}

public void setOldPassword(String oldPassword) {
this.oldPassword = oldPassword;
}

public String getConfirmPassword() {
return confirmPassword;
}

public void setConfirmPassword(String confirmPassword) {
this.confirmPassword = confirmPassword;
}

private String oldPassword;
private String confirmPassword;



private String username;
private String token;
private String city;
private String school;
private String className;


public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public Integer getUserMapInGroupCount() {
return userMapInGroupCount;
}

public void setUserMapInGroupCount(Integer userMapInGroupCount) {
this.userMapInGroupCount = userMapInGroupCount;
}

public boolean isFlag() {
return flag;
}

public void setFlag(boolean flag) {
this.flag = flag;
}

public Integer getPermissionId() {
return permissionId;
}

public void setPermissionId(Integer permissionId) {
this.permissionId = permissionId;
}

public String getSubdomain() {
return subdomain;
}

public void setSubdomain(String subdomain) {
this.subdomain = subdomain;
}

public String getOrgName() {
return orgName;
}

public void setOrgName(String orgName) {
this.orgName = orgName;
}

public Integer getGroupId() {
return groupId;
}

public void setGroupId(Integer groupId) {
this.groupId = groupId;
}

public String getGroupName() {
return groupName;
}

public void setGroupName(String groupName) {
this.groupName = groupName;
}

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public int getUserStatus() {
return userStatus;
}

public void setUserStatus(int userStatus) {
this.userStatus = userStatus;
}

public Integer getCompanyId() {
return companyId;
}

public void setCompanyId(Integer companyId) {
this.companyId = companyId;
}

public String getRoleType() {
return roleType;
}

public void setRoleType(String roleType) {
this.roleType = roleType;
}

public Integer getRoleId() {
return roleId;
}

public void setRoleId(Integer roleId) {
this.roleId = roleId;
}

public String getRoleDesc() {
return roleDesc;
}

public void setRoleDesc(String roleDesc) {
this.roleDesc = roleDesc;
}

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public String getDeviceId() {
return deviceId;
}

public void setDeviceId(String deviceId) {
this.deviceId = deviceId;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getUser_type() {
return user_type;
}

public void setUser_type(String user_type) {
this.user_type = user_type;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public Integer getOrgId() {
return orgId;
}

public void setOrgId(Integer orgId) {
this.orgId = orgId;
}

public String getName() {
return Name;
}

public void setName(String name) {
Name = name;
}

public Boolean getExists() {
return exists;
}

public void setExists(Boolean exists) {
this.exists = exists;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getJoinedOn() {
return joinedOn;
}

public void setJoinedOn(String joinedOn) {
this.joinedOn = joinedOn;
}

public Integer getAge() {
return age;
}

public void setAge(Integer age) {
this.age = age;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getAccessId() {
return accessId;
}

public void setAccessId(String accessId) {
this.accessId = accessId;
}

public String getDob() {
return dob;
}

public void setDob(String dob) {
this.dob = dob;
}

public String getAbout() {
return about;
}

public void setAbout(String about) {
this.about = about;
}

public String getRegistrationId() {
return registrationId;
}

public void setRegistrationId(String registrationId) {
this.registrationId = registrationId;
}

public Integer getCreaterId() {
return createrId;
}

public void setCreaterId(Integer createrId) {
this.createrId = createrId;
}

public License getLicense() {
return license;
}

public void setLicense(License license) {
this.license = license;
}

public Integer getLicenseId() {
return licenseId;
}

public void setLicenseId(Integer licenseId) {
this.licenseId = licenseId;
}

public String getAction() {
return action;
}

public void setAction(String action) {
this.action = action;
}

public String getgRecaptchaResponse() {
return gRecaptchaResponse;
}

public void setgRecaptchaResponse(String gRecaptchaResponse) {
this.gRecaptchaResponse = gRecaptchaResponse;
}

public String getDomainName() {
return domainName;
}

public void setDomainName(String domainName) {
this.domainName = domainName;
}

public String getLastLoginDate() {
return lastLoginDate;
}

public void setLastLoginDate(String lastLoginDate) {
this.lastLoginDate = lastLoginDate;
}

public List<Test> getTestList() {
return testList;
}

public void setTestList(List<Test> testList) {
this.testList = testList;
}

public List<Course> getCreatedCourseList() {
return createdCourseList;
}

public void setCreatedCourseList(List<Course> createdCourseList) {
this.createdCourseList = createdCourseList;
}

public SectionContent getContentDetails() {
return contentDetails;
}

public void setContentDetails(SectionContent contentDetails) {
this.contentDetails = contentDetails;
}

public List<Test> getCreatedTestList() {
return createdTestList;
}

public void setCreatedTestList(List<Test> createdTestList) {
this.createdTestList = createdTestList;
}

public int getQuestionCount() {
return questionCount;
}
public void setQuestionCount(int questionCount) {
this.questionCount = questionCount;
}

public String getBrowser() {
return browser;
}

public void setBrowser(String browser) {
this.browser = browser;
}

public String getOs() {
return os;
}

public void setOs(String os) {
this.os = os;
}

public String getIpAddress() {
return ipAddress;
}

public void setIpAddress(String ipAddress) {
this.ipAddress = ipAddress;
}

public String getCourseName() {
return courseName;
}

public void setCourseName(String courseName) {
this.courseName = courseName;
}

public int getEnrollCourseCount() {
return enrollCourseCount;
}

public void setEnrollCourseCount(int enrollCourseCount) {
this.enrollCourseCount = enrollCourseCount;
}

public String getTestName() {
return testName;
}

public void setTestName(String testName) {
this.testName = testName;
}

public int getAttemptsCount() {
return attemptsCount;
}

public void setAttemptsCount(int attemptsCount) {
this.attemptsCount = attemptsCount;
}

public int getTestCount() {
return testCount;
}

public void setTestCount(int testCount) {
this.testCount = testCount;
}

public int getCourseCount() {
return courseCount;
}

public void setCourseCount(int courseCount) {
this.courseCount = courseCount;
}

public Integer getRoleModifiedById() {
return roleModifiedById;
}

public void setRoleModifiedById(Integer roleModifiedById) {
this.roleModifiedById = roleModifiedById;
}

public String getRoleModifiedByName() {
return roleModifiedByName;
}

public void setRoleModifiedByName(String roleModifiedByName) {
this.roleModifiedByName = roleModifiedByName;
}

public String getRoleModifyDate() {
return roleModifyDate;
}

public void setRoleModifyDate(String roleModifyDate) {
this.roleModifyDate = roleModifyDate;
}

public String getAccountModifyDate() {
return accountModifyDate;
}

public void setAccountModifyDate(String accountModifyDate) {
this.accountModifyDate = accountModifyDate;
}

public String getAccountModifyByName() {
return accountModifyByName;
}

public void setAccountModifyByName(String accountModifyByName) {
this.accountModifyByName = accountModifyByName;
}

public List<Course> getCourseList() {
return courseList;
}

public void setCourseList(List<Course> courseList) {
this.courseList = courseList;
}

public String getLastViewContent() {
return lastViewContent;
}

public Integer getViewContentCount() {
return viewContentCount;
}

public void setLastViewContent(String lastViewContent) {
this.lastViewContent = lastViewContent;
}

public void setViewContentCount(Integer viewContentCount) {
this.viewContentCount = viewContentCount;
}
public String getSystemLanguage() {
return systemLanguage;
}

public void setSystemLanguage(String systemLanguage) {
this.systemLanguage = systemLanguage;
}


public String getSchool() {
return school;
}

public void setSchool(String school) {
this.school = school;
}


public String getClassName() {
return className;
}

public void setClassName(String className) {
this.className = className;
}


}


