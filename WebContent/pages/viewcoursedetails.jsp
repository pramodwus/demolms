<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="<spring:url value='/resources/css/custom.css'/>">
   	<style>
.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
}

.sidecontent {
	color: #7d8181;
	font-size: 15px;
}

.form-control {
	border-radius: 0;
	box-shadow: none;
	border-color: #d0d0d0;
}

.skin-black-light .wrapper, .skin-black-light .main-sidebar,
	.skin-black-light .left-side {
	background-color: #f9fafc;
	border: 1px solid #d0d0d0;
	margin-top: 15px;
}

.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
}

.sidecontent {
	color: #7d8181;
	font-size: 15px;
}

.form-control {
	border-radius: 0;
	box-shadow: none;
	border-color: #d0d0d0;
}

.skin-black-light .wrapper, .skin-black-light .main-sidebar,
	.skin-black-light .left-side {
	background-color: #f9fafc;
	border: 1px solid #d0d0d0;
	margin-top: 15px;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-bottom-color: #00B06C;
}

.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-right: 0px !important;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-top-color: #fff;
}

#fa {
	padding-right: 15px;
}

.input-lg {
	height: 200px;32.51%
}

#jumbodiv {
	width: 41%;
	margin-left: 34%;
	color: white;
}

#kona {
	background-color: #fff;
	height: 28%;
}

.whiteback {
	background-color: #fff;
}
/*#dedecolor{background-color:#dedede;}*/
.detail {
	border-bottom: 1px solid #eee;
	width: 80%;
	margin-top: 20px;
}

.section {
	height: 35%;
	width: 80%;
	background-color: white;
	margin-top: 20px;
	border: 1px solid #dedede;
}

.secbody {
	margin-top: 30px;
	padding-left: 20px;
	list-style: none;
}

.justifyText{
text-align: justify;
}
.sectionButton{
margin-top:15px;
}
.content-wrapper {
margin: auto;
margin-left:230px;
}

@media only screen and (min-width: 767px) {
    .content-wrapper {
        /* background-color: yellow; */
        margin-left:230px;
    }
}
@media only screen and (max-width: 760px) {
  .content-wrapper {
       /*  background-color: pink; */
        margin-left:0px;
    }
}  
.select-email-list {
    background-color: #00A65A;;
    border-color: #367fa9;
    padding: 3px 10px;
    color: #fff;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: text;
}
.select-email-list1 {
    background-color: #00A65A;;
    border-color: #367fa9;
    padding: 3px 10px;
    color: #fff;
    text-overflow: ellipsis;
    white-space: nowrap;
    cursor: text;
}
.list-margin{
margin:5px;
margin-left: 0px;
}
.list-inline>li {
    display: inline-block;
    padding-right: 0px;
    adding-left: 0px;
}

.section_name_div {
	max-width: 25% !important;
	min-height: 40px;
	padding: 12px 35px;
	margin-top: 20px;
	margin-left: 0px;
	margin-bottom: 20px;
	border-bottom-width: 0px;
	background-color: #008d4c;
	border-radius: 0px;
	color: white;
}

p{
	word-break: break-word;
}

.share-button{
    min-width:245px;
}

.section_setting_validation_failed{
    border:1px solid red !important;
}

.section_setting_validation .notifications {
    outline: 0;
}
.section_setting_validation .notifications .notification.notification-critical {
    background-position: left 12px top -387px;
}
.section_setting_validation .notifications .notification {
    padding: 12px 15px 15px 44px;
    background: url('resources/images/msg_alert_icon.png') no-repeat;
    background-size: 20px;
}
</style>

</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
			<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<div class="col-sm-12" >
		<div class="content-wrapper">
		
		  				<div class="col-sm-12" >
	  				<!----------------------------------------------Header link start ---------------------------------------->		
		<div class="h4"><spring:message code="lbl.course" text="Course"/><i class="fa fa-angle-right" style="padding-left:5px;"></i> ${course.courseName}</div>
			    <div style="background-color:#FFF" class="nav-tabs-custom">
                <ul class="nav nav-tabs" >
                <c:choose>
                <c:when test="${course.publish==0}">
                <li  style="border-left:1px solid #dedede;width:33%;"><a href="createCourse?courseId=${course.courseId}" style="text-align:center;">
                <c:if test="${course.courseTag!=''}">
										<i class="fa fa-check-circle-o color-green" id="fa"></i>
									</c:if>
									 <c:if test="${course.courseTag==''}">
										<i class="fa fa-circle-thin" id="fa"></i></c:if>
                <spring:message code="lbl.courseinformation" text="Course Information"/></a></li>
                  <li style="border-left:1px solid #dedede;width:33%;">
                  <a href="addEditCourseMaterial?courseId=${course.courseId}"  style="text-align:center;">
                  <c:if test="${not empty sectionlist}">
									  <i class="fa fa-check-circle-o color-green"	id="fa"></i>
									</c:if>
									<c:if test="${empty sectionlist}">
									  <i class="fa fa-circle-thin"	id="fa"></i>
									</c:if>
                  <spring:message code="lbl.addcontent" text="Add Content"/></a></li>
                  <li class="active" style="border-left:1px solid #dedede;width:34%;"><a href="#" data-toggle="tab" style="text-align:center;"><i class="fa fa-circle-thin" id="fa"></i><spring:message code="lbl.previewandpublish" text="Preview & Publish"/></a></li>
                </c:when>
                <c:otherwise>
                  <li  style="border-left:1px solid #dedede;width:33%;"><a style="text-align:center;"><i class="fa fa-check-circle-o color-green" id="fa"></i> <spring:message code="lbl.courseinformation" text="Course Information"/></a></li>
                  <li style="border-left:1px solid #dedede;width:33%;"><a style="text-align:center;"><i class="fa fa-check-circle-o color-green" id="fa"></i><spring:message code="lbl.addcontent" text="Add Content"/></a></li>
                  <li class="active" style="border-left:1px solid #dedede;width:34%;"><a  data-toggle="tab" style="text-align:center;"><i class="fa fa-check-circle-o color-green" id="fa"></i><spring:message code="lbl.preview" text="Preview"/></a></li>
                </c:otherwise>
                </c:choose>
                </ul>

              </div>
 	</div>
  <!----------------------------------------------Header link end ----------------------------------------> 
  	<div class="col-sm-12">
		<input type="hidden" id="courseid" name="courseid"
								value="${course.courseId}">
		 <!-- <div class="jumbotron" style="background-image: url('resources/adminlte/dist/img/photo1.png');">
                    <div id="jumbodiv">
						 
                   </div> 
         </div> -->
                                 <div class="form-group" style="margin-top: 13px;">
                                 <c:if test="${course.courseImageUrl != null}">	
                                  <img src="${course.courseImageUrl}" height="100%" width="300px" alt="..." class="pull-right"
								  onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'" alt="...">                    
                                 </c:if>
                                 </div>
         	                    <div class="form-group">
										<strong><spring:message code="lbl.levelofcourse" text="Level of Course"/>:&nbsp;&nbsp;</strong>${course.levelName}
								</div>
								
								<div class="form-group">
								<strong><spring:message code="lbl.promotionalvideo" text="Promotional Video"/>:&nbsp;&nbsp;</strong>${course.promoVideoUrl}
							    </div>
							    
							    <c:if test="${course.promoVideoUrl!='NA'}">
							<div class="form-group">
								<div id="promoVideo"></div>																
							</div>
                            </c:if>
                            
							    <div class="form-group">
										<strong><spring:message code="lbl.language" text="Language"/>:&nbsp;&nbsp;</strong>${course.languageName}
									</div>
									
								<div class="form-group">
								<c:choose>
								<c:when test="${course.courseTag!=null && course.courseTag!=''}">
								<strong><spring:message code="lbl.tags" text="Tags"/>:&nbsp;&nbsp;</strong>${course.courseTag}
								</c:when>
								<c:otherwise>
								<strong><spring:message code="lbl.tags" text="Tags"/>:&nbsp;&nbsp;</strong><spring:message code="lbl.na" text="NA"/>
								</c:otherwise>
								</c:choose>
										<input type="hidden" id="ctags" value="${course.courseTag}">
										<input type="hidden" id="cname" value="${course.courseName}">
								</div>
									
								<div class="form-group">
										<strong>Visibility:&nbsp;&nbsp;</strong>
										<c:if test="${course.publish==0}"><spring:message code="lbl.drafted" text="Drafted"/></c:if>
										<c:if test="${course.publish==1}"><spring:message code="lbl.published" text="published"/></c:if>
									</div>
									<div class="form-group">
										<strong><spring:message code="lbl.immediatepublish" text="Immediate Publish"/>:&nbsp;&nbsp;</strong>
										<c:choose>
										<c:when test="${course.isSchedule == 1}">
                                              <spring:message code="lbl.no" text="No"/> ( <spring:message code="lbl.publishdateis" text="Publish Date is"/> <b>${course.schedulePublishDate}</b> )
										</c:when>
										<c:otherwise><spring:message code="lbl.yes" text="Yes"/></c:otherwise>
										</c:choose>
										<c:if test="${course.publish==0}"><a href="createCourse?courseId=${course.courseId}" class="cursor-pointer"><spring:message code="lbl.edit" text="edit"/></a></c:if>
									</div>
								
								<div class="form-group">
								        <c:choose>
											<c:when test="${course.courseDesc!=null && course.courseDesc!=''}">
												<strong><spring:message code="lbl.description" text="Description"/>:&nbsp;&nbsp;</strong>
											    <p class="justifyText">${course.courseDesc}</p>
											</c:when>
											<c:otherwise>
												<strong><spring:message code="lbl.description" text="Description"/>:&nbsp;&nbsp;</strong><spring:message code="lbl.na" text="NA"/>
											</c:otherwise>
								      </c:choose>
										
										<input type="hidden" id="cdesc" value="${course.courseDesc}">
								</div>
									<c:if test="${not empty course.highlights}">	
									<div class="form-group">
										<strong><spring:message code="lbl.coursehighlights" text="Course Highlights"/> :</strong>
										<ul>
											<c:forEach items="${course.highlights}" var="highlights">
												<c:if test="${highlights!=''}"><li class="">${highlights}</li></c:if>
											</c:forEach>
										</ul>
									</div>
									</c:if>
									<!-- <div class="form-group">
										<strong>Course Detail&nbsp;&nbsp;</strong>
									</div> -->
									<span class="clearfix"></span>
									</div>
                                    <div class="col-sm-12">
									 <c:set var="i" value="1"></c:set>								
									<c:forEach items="${sectionlist}" var="section">
										   <div class="section totalSections">
                    <%-- <button type="button" class="btn btn-success sectionButton btn-flat button-width">Section ${i}</button> --%>
                    <div class="section_name_div">${section.sectionName}</div>
                    <c:if test="${course.publish ==0 }">
                    <div class="pull-right line-height col-sm-2">
                    <div class=" pull-right">
                                                  <c:if test="${fn:length(section.sectionContent)>0 }">
											&emsp;<input type="checkbox" name="sectionSelect" checked="checked" disabled="disabled"
												value="${section.sectionId}" class="sectionSelect hide" id="">
												</c:if>
												</div>
												
										</div>
										</c:if>
                        <ul class="secbody" id="sectionContent${section.sectionId}">
                            
                            <c:forEach items="${section.sectionContent}"
														var="sectionContent">	
														<c:set var="contentType" value="${fn:toLowerCase(sectionContent.contentType)}" />
                            <li class="treeview form-group countSectionContent"><a class="cursor-pointer" href="coursepreview?courseId=${course.courseId}&isPublish=${course.publish}&contentId=${sectionContent.contentId}"><i class="fa fa-file-o" style="color:#00B06C"></i>&nbsp;&nbsp;<span>${sectionContent.contentName}
                            
                            </span>
                            </a>
                            </li>
                            </c:forEach>
                        </ul>
                                               </div>
								    <c:set var="i" value="${i+1}"></c:set>	
									</c:forEach>
								<c:if test="${course.publish ==0 }">
									    	<div class="form-group"
													style="text-align: center; margin-top: 30px;width:80%">
												<button type="button" class="btn btn-flat share-button btn-success"
																		onclick="publishPopup('sharewithall');"><spring:message code="lbl.publish" text="Publish"/></button>
												<%-- &nbsp;&nbsp;
												<button type="button" class="btn btn-flat share-button  btn-success"
																		onclick="publishPopup('sharewithusersgroups');"><spring:message code="lbl.sharewithusersgroups" text="Publish & share with users/groups"/></button> --%>						
												</div>
											</c:if>
											<c:if test="${course.publish ==1 }">
									    	<div class="form-group"
													style="text-align: center; margin-top: 30px;width:80%">
												<button type="button" class="btn btn-flat share-button btn-success"
																		onclick="editCorseDetail('sharewithall');"><spring:message code="lbl.publish" text="Publish"/></button>
												<%-- &nbsp;&nbsp;
												<button type="button" class="btn btn-flat share-button  btn-success"
																		onclick="publishPopup('sharewithusersgroups');"><spring:message code="lbl.sharewithusersgroups" text="Publish & share with users/groups"/></button> --%>						
												</div>
											</c:if>
							<span class="clearfix"></span>
							<div class="form-group"></div>
			                       </div>
			                       
			                       
			                   
	
	</div>	
	</div>
	</div>	
	
		<!--  Alert box for section is empty -->
	<div class="modal fade" id="emptySection" tabindex="-1" role="dialog"
		aria-labelledby="emptySection">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal"><spring:message code="lbl.ok" text="Ok"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="publishAlert" tabindex="-1"
		role="dialog" aria-labelledby="publishAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							
						</h3>
						<p></p>
						<button type="button" class="btn btn-default button-width"
							data-dismiss="modal"><spring:message code="lbl.no" text="No"/></button>
						<button type="button" id="dId"
							class="btn btn-success button-width"><spring:message code="lbl.yes" text="Yes"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- pop up for course publish -->	
<div class="modal fade" id="coursePublished" role="dialog"
		aria-labelledby="coursePublished">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="text-align: center">
				<div class="modal-body row page-background-color">
					<form name="publishForm" id="publishForm">
						<input type="hidden" name="courseId" value="${course.courseId}">
						<h1 style="font-size: large">
							<strong><spring:message code="lbl.publishit" text="Publish it"/></strong>
						</h1>
						<h6><spring:message code="msg.beforecoursepublish" text="Note: Once you publish the course you would not be able to change it."/></h6>
						<div class="radio hide">
							<label><input type="radio" name="content" checked><spring:message code="lbl.allcontent" text="All Content"/></label>
						</div>
						<div class="form-group" id="selectedSection">
						</div>
					</form>
				<button type="button" onclick="coursePublished();"
									class="btn btn-success btn-flat button-width"
									data-dismiss="modal"><spring:message code="lbl.publish" text="Publish"/></button>
               <button type="button"
									class="btn btn-default btn-flat button-width"
									data-dismiss="modal" aria-label="Close"><spring:message code="lbl.later" text="Later"/></button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- pop up after successfully publishing the course -->
	<div class="modal fade"
		id="success" role="dialog" aria-labelledby="success">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content page-background-color">
					<div class="modal-header" style="text-align:center">
						<h1 style="font-size: large">
							<strong><spring:message code="lbl.shareyourcourse" text="Share Your Course"/></strong>
						</h1>
						<h4><spring:message code="msg.beforecoursepublish" text="Note: Once you share the course you would not be able to change it."/></h4>
					</div>
					<div class="modal-body">
						<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="col-sm-6">
							<input type="radio" id="alluser" name="searchFor" class="searchFor flat-red" onclick="getUsers()" value="allUser" checked> &nbsp;<spring:message code="lbl.allusers" text="All Users"/>&nbsp;&nbsp;
							<input type="radio" id="groups" name="searchFor" class="searchFor flat-red" onclick="getGroups()" value="group"> &nbsp;<spring:message code="lbl.groups" text="Groups"/>&nbsp;
							<div id="users1" style="margin-top:20px">
								<table class="table" id="sharedCourseTable">
									<thead>
										<tr style="background-color: #00a65a;color:white;">
											<th></th>
											<th><spring:message code="lbl.emailid" text="Email Id"/></th>
										</tr>
									</thead>
									<tbody id="sharedEmailList">
									</tbody>
									
								</table>
								</div>
								<div id="grouptable" style="display: none;margin-top:20px">
									<table class="table" id="sharedGroupTable" >
										<thead>
											<tr style="background-color: #00a65a;color:white;">
												<th></th>
												<th><spring:message code="lbl.groupname" text="Group Name"/></th>
											</tr>
										</thead>
										<tbody id="sharedGroupList" >
										</tbody>
									</table>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="" style="height: 50px"></div>
								<div class="form-group">
									<label><spring:message code="lbl.addattendees" text="Add Attendees"/>: </label>
								</div>
								
								<div class="form-group form-control" style="height:auto">
								<div class="col-sm-1"><b><spring:message code="lbl.to" text="To"/>:</b></div>
								<div style="width:90%;min-height:100px;">
								<ul style="list-style: none;" class="list-inline" id="emaillistgroup">
								</ul>
								<div class="form-group"></div>
								</div>
								</div>
								
								<div class="form-group">
								<label><spring:message code="lbl.message" text="Message"/> :</label>
								<textarea class="textAreaControl form-control" id="shareCourseMessage" name="shareCourseMessage" maxlength="512" onkeyup="emailKeyUp()"></textarea>
								<label class="requireFld" id="shareCourseMessageError"><spring:message code="msg.empty" text="This field is required."/></label>
								</div>
								
								
								<div class="form-inline">
									<div class="form-group">
										<input type="text" placeholder="Email Id" id="sharedEmail"
											class="form-control" onkeyup="emailKeyUp()">
									</div>
									<div class="form-group">
										<button type="button"
											class="btn btn-success btn-flat"
											onclick="addSharedList();"><spring:message code="lbl.add" text="Add"/></button>
									</div>
								</div>
								<div class="">
									<label class="requireFld" id="sharedEmailError1"><spring:message code="msg.empty" text="This field is required."/></label>
									<label class="requireFld" id="sharedEmailError2"><spring:message code="msg.email.invalid" text="Please provide a valid Email Id."/></label>
									<label class="requireFld" id="sharedEmailError3"><spring:message code="msg.emailchoosealready" text="You have been choosen already."/></label>
								    <label class="requireFld" id="sharedEmailError4"><spring:message code="msg.emailcannotchooseforcourse" text="You can't choose this user for share the course."/></label>
								</div>
							</div>
							<!-- <button type="button"
							class="btn btn-primary button-width-large button-blue"
							data-dismiss="modal" onclick="location.href='courselist'">Close</button> -->
						</div>
						</div>
					</div>
					<!-- /. modal-body -->
					<div class="modal-footer">
						<button type="button"
							class="btn btn-default btn-flat button-width-large"
							data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						<button type="button" id="sharedCourseButton"
							class="btn btn-success btn-flat button-width-large" onclick="shared();" disabled><spring:message code="lbl.share" text="Share"/></button>
					</div>
				</div>
			</div>
	</div>
		
<!-- pop up for share the course between user.-->
<div style="display: none" style="display: block" class="modal fade in"
		id="sharedPopUp" tabindex="-1" role="dialog"
		aria-labelledby="sharedPopUp" aria-hidden="false"
		style="display: block;">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h4 class="modal-title"><spring:message code="msg.coursesuccessfullyshared" text="Your course has been shared to users."/></h4>
						<br />
						<button type="button"
							class="btn btn-flat btn-success button-width-large"
							onclick="location.href='courselist'"><spring:message code="lbl.close" text="Close"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<div class="modal fade" id="showContent" role="dialog"
		aria-labelledby="showContent">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body page-background-color">
				    <div class="row">
				    <div class="col-xs-12" style="text-align:center" id="frame">
					</div>
					<div class="col-xs-12" style="min-height: 30px"></div>
					<div class="col-xs-12" style="min-height: 30px"><button type="button" class="btn btn-primary btn-flat  button-width-large button-blue pull-right"
							style="margin: auto" data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button></div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
		<!--  Alert box for quiz is mandatory is empty -->
	<div class="modal fade" id="section_setting_validation_failed" tabindex="-1" role="dialog"
		aria-labelledby="section_setting_validation_failed">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
				    <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <div class="modal-title section_setting_validation">
			         <div class="notifications" tabindex="-1">
			    <span class="notification notification-critical" role="alert">
			    <spring:message code="lbl.validationfailed" text="Validation Failed"/>
			    </span>
	              </div></div>
			        </div>
					<div class="modal-body">
						<div class="row no-margin">
						<p></p>
						<ul id="setting_validation_failed_section_list">
						</ul>
							</div>
					</div>
					<div class="modal-footer">
                     <button type="button" class="btn btn-danger button-width btn-flat" data-dismiss="modal"><spring:message code="lbl.ok" text="OK"/></button>
                  </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['msg.shared.error'] = "<spring:message code='msg.shared.error' text='Could not Shared,try again.' javaScriptEscape='true' />";
        messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' text='Something went wrong,Please try again.' javaScriptEscape='true' />";
        messages['lbl.edit'] = "<spring:message code='lbl.edit' text='Edit' javaScriptEscape='true' />";
        messages['msg.minonesectionrequiredforpublishcourse'] = "<spring:message code='msg.minonesectionrequiredforpublishcourse' text='Minimum One section is required for publish the course.' javaScriptEscape='true' />";
        messages['msg.selectedsectioncontent.empty'] = "<spring:message code='msg.selectedsectioncontent.empty' text='Selected section content can not be empty.' javaScriptEscape='true' />";
        messages['msg.coursecotaindraftedassessment'] = "<spring:message code='msg.coursecotaindraftedassessment' text='Selected Section Content must not contain drafted assessment for publish the course.' javaScriptEscape='true' />";
        messages['msg.coursemandatoryinfo.required'] = "<spring:message code='msg.coursemandatoryinfo.required' text='Enter mandatory basic information (title,description,tags) about course for publish.' javaScriptEscape='true' />";
        messages['msg.nosectionselectedforpublishcourse'] = "<spring:message code='msg.nosectionselectedforpublishcourse' text='You have no chosen any section for publishing.' javaScriptEscape='true' />";
        messages['msg.coursescheduledateexpire'] = "<spring:message code='msg.coursescheduledateexpire' text='The scheduled publish date of course has been expired.' javaScriptEscape='true' />";
        messages['lbl.save'] = "<spring:message code='lbl.save' text='Save' javaScriptEscape='true' />";
        messages['msg.sectionsettingvalidationfailed'] = "<spring:message code='msg.sectionsettingvalidationfailed' text='Please add minimum one quiz in following sections because you have provided in section\'s passing criteria that quiz is mandatory.'/>";
</script>  
<script src="<spring:url value='/resources/js/youtubeplayer.js?v=1'/>"></script>
<script>
var isSchedule = '${course.isSchedule}';
var courseId = ${course.courseId};
$(function() {
	$(".treeview").removeClass("active");
	$("#course").addClass("active");
	$("#course .treeview-menu > #course").addClass("active");
	$('.imgset img').imgCentering({'forceWidth': true,});
	var videoUrl='${course.promoVideoUrl}';
      //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green'
    });
    $('input[type="radio"].flat-red').iCheck({
          radioClass: 'iradio_flat-green'
        });
	/**
	 * calling function for playing video.
	 */
	playVideo(videoUrl);
});
</script>
<script src="<spring:url value='/resources/js/viewcoursedetails.js?v=2'/>"></script>
</html>