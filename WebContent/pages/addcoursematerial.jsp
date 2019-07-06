<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.qbis.common.ConstantUtil"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%@ include file="include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css?v=1'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/jquery.fancybox.css'/>">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
<style>

.well {
	min-height: 20px;
	padding: 10px;
	margin-bottom: 20px;
	background-color: #f5f5f5;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	width: 60%;
	margin-left: 25px;
}

.content-wrapper .btn {
	padding-left: 35px;
	padding-right: 35px;
	margin-top: 20px;
	margin-bottom: 30px;
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

.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
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

.content_icon {
	padding-right: 15px;
}

.section {
	height: 35%;
	width: 100%;
	background-color: white;
	margin-top: 20px;
	border: 1px solid #dedede;
}


.secbody {
	margin-top: 50px;
	padding-left: 20px;
}

.well1 {
	min-height: 20px;
	padding: 19px;
	margin-bottom: 20px;
	background-color: #fff;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	margin-right: 25px;
	margin-left: 25px;
}

.shape {
	float: right;
}

.icon-line-height {
	line-height: 4;
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
  
#suggestioncontentlist_paginate{
     padding-right: 10px !important; 
}

.suggestioncontentlistbody tr {
    height: 68px;
    background: #e5e5e5;
}

.suggestioncontentlistbody tr:hover {
    color: #ffffff;
    background-color:#4a7fbb;
}

#suggestioncontentlist td{
	 margin-top: 5px !important;
     margin-bottom: 5px !important;
     }   

.section_name_div {
	max-width: 30% !important;
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

	.no-left-right-margin{
	margin-left:0px !important;
	margin-right:0px !important;
	}    

.validation--form .notifications{
  display:none;
}

.validation--form .notifications .notification.notification-critical {
  background-color: #fff7f7;
  background-position: 12px -387px;
  background-position: left 12px top -387px;
  border-color: #c72e2e;
}
.validation--form .notifications .notification {
  margin: 0;
  padding: 15px 15px 15px 44px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  -khtml-border-radius: 5px;
  border-radius: 5px;
  border-width: 1px;
  border-style: solid;
  text-align: left;
  font-size: 1em;
  background: url('resources/images/msg_alert_icon.png') no-repeat;
  background-size: 20px;
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

		<div class="col-sm-12">
			<div class="content-wrapper">
				<form action="addEditCourseMaterial" method="post">
					<div class="col-sm-12">
						<div class="h4">
							<spring:message code="lbl.course" text="Course"/><i class="fa fa-angle-right" style="padding-left: 5px;"></i>
							${course.courseName}
						</div>
						<div style="background-color: #FFF" class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li style="border-left: 1px solid #dedede; width: 33.32%;"><a
									 style="text-align: center;">
									<c:if test="${course.courseTag!=''}">
									  <i class="fa fa-check-circle-o color-green"	id="fa"></i>
									</c:if>
									<c:if test="${course.courseTag==''}">
									  <i class="fa fa-circle-thin"	id="fa"></i>
									</c:if>	
										&nbsp;&nbsp;<spring:message code="lbl.courseinformation" text="Course Information"/></a></li>
								<li class="active"
									style="border-left: 1px solid #dedede; width: 33.32%;"><a
									href="#" style="text-align: center;">
									<c:if test="${not empty sectionlist}">
									  <i class="fa fa-check-circle-o color-green"	id="fa"></i>
									</c:if>
									<c:if test="${empty sectionlist}">
									  <i class="fa fa-circle-thin"	id="fa"></i>
									</c:if>
										&nbsp;&nbsp;<spring:message code="lbl.addcontent" text="Add Content"/></a></li>
								<li style="border-left: 1px solid #dedede; width: 33.32%;">
								<a href="courseViewController?courseId=${course.courseId}&isPublish=${isPublished}" style="text-align: center;">
								<i class="fa fa-circle-thin" id="fa"></i>&nbsp;&nbsp;<spring:message code="lbl.previewandpublish" text="Preview & Publish"/></a></li>
							</ul>
						</div>

						<!----------------------------------------------Header link end ---------------------------------------->
						<div class="hide">
							<h3 style="text-align: center;">
								<img src="resources/adminlte/dist/img/coursecontent.png">
							</h3>
							<h3 style="text-align: center;"><spring:message code="lbl.addcontenttocourse" text="Add content to course"/></h3>
							
						</div>
					</div>
                    <div class="col-sm-12"><p><spring:message code="lbl.note" text="Note"/>: <spring:message code="msg.coursemusthavesectionforpublish" text="Course must have minimum one section for publish."/></p></div>
					<!----------------------------------------------Form start ---------------------------------------->

					<div class="col-sm-12" id="section_content_div">
						<div class="row no-margin">
							<input type="hidden" id="courseid" name="courseid"
								value="${course.courseId}">
							<c:set var="i" value="1"></c:set>
							
							<div id="sectiondiv">
							
							<c:forEach items="${sectionlist}" var="section">


              		<div class="section totalSections row no-left-right-margin">
										<div class="input-group">
											<div class="section_name_div"
												id="sectionNameText${section.sectionId}"> ${section.sectionName}</div>
											<div class="input-group-addon">
												<div class="pull-right">
													<a style="color: #dd4b39" class="cursor-pointer"
														onclick="updateSectionName('${section.sectionId}');"
														title="<spring:message code="lbl.editname" text="Edit Name"/>"><i
														class="fa fa-edit"></i></a> &nbsp;&nbsp; <a
														style="color: #dd4b39"
														class="cursor-pointer sectionDelete"
														id="sectionDelete${section.sectionId}"
														title="<spring:message code="lbl.delete" text="Delete"/>"><i
														class="fa fa-trash-o"></i></a>&nbsp;&nbsp; <a
														style="color: #dd4b39" class="cursor-pointer"
														onclick="sectionSettingInfoPopup('${section.sectionId}')"
														title="<spring:message code="lbl.setting" text="Setting"/>"><i
														class="fa fa-gears"></i></a>
												</div>
												<%-- <div class="pull-right">
											<div class="dropdown pull-left">
												<a style="color:#BFBFBF" id="dLabel" data-target="#" data-toggle="dropdown"
													role="button" aria-haspopup="true" aria-expanded="false"
													class="icon-dropdown">
													<i class="fa fa-gears"></i>
												</a>
												<ul class="dropdown-menu dropdown-menu-right "
													aria-labelledby="dlabel">
													<li><a
														onclick="updateSectionName('${section.sectionId}');"
														class="cursor-pointer"><spring:message code="lbl.editname" text="Edit Name"/></a></li>
													<li class="divider"></li>
													<li><a class="cursor-pointer sectionDelete"
														id="sectionDelete${section.sectionId}"><spring:message code="lbl.delete" text="Delete"/></a></li>
												</ul>
											</div>
									</div> --%>
											</div>

										</div>
										<p>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<spring:message code="lbl.note" text="Note" />
											:
											<spring:message code="msg.sectionmusthavecontentforpublish"
												text="Section must have content for publish." />
										</p>

										<div class="row">
											<div class="col-sm-12" style="text-align: center;">
<%-- 											<c:if test="${section.isChapterTest==0}">
 --%>											<c:if test="${section.isPractice==1 }">


													<div class="col-sm-12">
													
													
													
													
											 <div>
											<div class="well1 content_dropzone"
												id="divfordragcontent_${section.sectionId}"
												style="display: none" ondrop="dropContent(event)"
												ondragover="allowDropContent(event)">
												<h2 class="text-align content_dropzone_h"
													id="divfordragcontenth_${section.sectionId}">
													<spring:message code="lbl.dragherefromlist"
														text="Drag here from list" />
												</h2>
											</div>
											<div id="sectionContent${section.sectionId}"
												class="totalSectionContent sortable">
												<c:forEach items="${section.sectionContent}"
													var="sectionContent">
													
													<c:if test="${sectionContent.sessionId == 0}">	
													<div class="well1 well-sm contentItem"
														id="sortable_${sectionContent.contentId}">
													      <img
															src="<spring:url value='/resources/images/${sectionContent.contentIconPath}'/>"
															class="contentIcon"
															id="contentIcon${sectionContent.contentId}"> <label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

															<c:if
																test="${sectionContent.contentType=='PPT' || sectionContent.contentType=='PPTX' || sectionContent.contentType=='PDF' || (sectionContent.contentType=='VIDEO' && fn:length(sectionContent.questionList)>0)}">
																<a id="contentName${sectionContent.contentId}"
																	class="cursor-pointer"
																	href="coursepreview?courseId=${course.courseId}&isPublish=0&contentId=${sectionContent.contentId}&origin=true">
																	<c:if test="${sectionContent.contentName!=null}"> 
																	  ${sectionContent.contentName}
																</c:if> <c:if test="${sectionContent.contentName==null}">
																	  ${sectionContent.content}  															
																	  							
																	  
																</c:if>
																</a>
															</c:if> <c:if
																test="${sectionContent.contentType!='PPT' && sectionContent.contentType!='PPTX' && sectionContent.contentType!='PDF' && (sectionContent.contentType!='VIDEO' || fn:length(sectionContent.questionList)==0)}">
																<a id="contentName${sectionContent.contentId}"
																	class="cursor-pointer"
																	onclick="showContent('${sectionContent.contentPath}','${sectionContent.contentType}')">
																	<c:if test="${sectionContent.contentName!=null}"> 
																	  ${sectionContent.contentName}
																</c:if> <c:if test="${sectionContent.contentName==null}">
																	  ${sectionContent.content}
																</c:if>
																</a>
															</c:if>
														</label>

														<div class="pull-right">
															${sectionContent.attemptName}


															<%-- </c:if> --%>
															<div class="pull-right">
																<div class="dropdown">
																	<a id="dLabel" data-target="#" data-toggle="dropdown"
																		role="button" aria-haspopup="true"
																		aria-expanded="false"
																		class="content-dropdown icon-dropdown"> <img
																		src="resources/adminlte/dist/img/ellipsis-v.png"
																		class="shape" />
																	</a>
																	<ul class="dropdown-menu dropdown-menu-right"
																		aria-labelledby=" dlabel">
																		<c:choose>
																			<c:when test="${sectionContent.contentType =='TEST'}">
																				<li class="hide"><a
																					id="${section.sectionId}##${sectionContent.contentId}"
																					onclick="location.href='addEditTest?courseId=${course.courseId}&sectionId=${section.sectionId}&testId=${sectionContent.content}&contentId=${sectionContent.contentId}'"
																					class="cursor-pointer"><spring:message
																							code="lbl.edit" text="Edit" /></a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a
																					id="${section.sectionId}##${sectionContent.contentId}"
																					onclick="renametitle('${section.sectionId}##${sectionContent.contentId}')"
																					class="cursor-pointer"><spring:message
																							code="lbl.editname" text="Edit Name" /></a></li>

																				<li class="divider"></li>
																			</c:otherwise>
																		</c:choose>
																		<li><a
																			id="${section.sectionId}contentDelete${sectionContent.contentId}"
																			class="btndelete cursor-pointer"><spring:message
																					code="lbl.delete" text="Delete" /></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>

													</c:if>
												</c:forEach>
											</div>

											<h3 style="text-align: center;" class="hide">
												<spring:message code="lbl.autouploadlogo"
													text="Auto Upload Logo" />
											</h3>
											<h3 style="text-align: center;" class="hide">
												<spring:message code="lbl.dropfilesheretoautoupload"
													text="Drop files here to auto upload" />
											</h3>
										</div>
													
													
													
													
													
													
													
													
													
													
													
													
														<h3 style="text-align: center;">
															<a style="cursor: pointer"
																id="addTest${section.sectionId}"
																onclick="testPopup('${section.sectionId}','1');"><img
																src="resources/adminlte/dist/img/courseinfo.png"></a>
														</h3>
														<h4 style="text-align: center;">
															<spring:message code="lbl.addassessment" text="Add Quiz" />
														</h4>
													</div>
											</div>
											</c:if>
												<c:if test="${section.isPractice==0 && section.isChapterTest==0}">
                                     
                                         
                                            <c:forEach items="${sessionList}" var="session"> 
                                            <c:if test="${session.isChapterTest==0 && session.sectionId==section.sectionId}">
                                            
							          		<div class="input-group">
											<div class="section_name_div" id="sectionNameText109">${session.sessionName}</div>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${session.sessonId}" >Doubts Session</button>

<!-- <button type="button" id="showFeedback">click me</button> 
<h3 class="feedbackTemplate">hello</h3>  -->
<!-- Modal -->
<div class="modal fade" id="exampleModal${session.sessonId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Doubts</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <div id="mytemplate">
      <table class="table">
  <thead>
    <tr>
      <th scope="col">User Name</th>
      <th scope="col">Doubts</th>
      <th scope="col">Feedback Review</th>
      <th scope="col">Reply</th>
    </tr>
  </thead>
  
      <c:forEach items="${feedbackList}" var="feedback" varStatus='loop'> 
   
     <c:if test="${feedback.sessionId == session.sessonId}">
     <c:if test="${ feedback.userFeedback != null && not empty feedback.userFeedback }">
  <tbody>
    <tr>
        <input type="hidden"  id="subject${session.sessonId}" value="${feedback.sessionId}"/>
   <input type="hidden"  id="mail${session.sessonId}" value="${feedback.secondryEmail}"/> 
      <th scope="row">${feedback.secondryEmail}</th>
      <td>${feedback.userFeedback}hello</td>
     <input type="hidden" value="In response to ${feedback.userFeedback}" id="subject"/> 
      <td>${feedback.review}</td>
      <td><button type="button" class="btn btn-secondary" style="margin: 0;" onclick="openModel('${feedback.userFeedback}','${feedback.secondryEmail}','${session.sessonId}')" id="showFeedback" >Reply</button></td>
    </tr>
  <!--   <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Average</td>
      <td><button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin: 0;">Reply</button></td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>Bad</td>
      <td><button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin: 0;">Reply</button></td>
    </tr> -->
  </tbody>
  </c:if>
  </c:if>
</c:forEach>
</table>
      </div>
   
	<div class="feedbackTemplate" style="display:none;">
	<textarea id='body${session.sessonId}' rows="" cols="" placeholder="enter your answer"></textarea>	
	</div>
      </div>
      <div class="modal-footer">
     
        <button type="button" class="btn btn-primary" id="hideTemplate" onclick="sendDoubtMail(${session.sessonId})">Submit Details</button>
      </div>
    </div>
  </div>
</div>

															<form name="checkboxform${session.sessonId}"
																id="checkboxform${session.sessonId}">
																<div class="col-xs-12" style="margin-left: 300">
																	<input type="checkbox" name="isLive" 
																		id="isLive${session.sessonId}"
																		onclick="submitCheckboxForm(${session.sessonId},'isLive${session.sessonId}')">Live
																	<input type="checkbox" name="isEnable" 
																		id="isEnable${session.sessonId}"
																		onclick="submitCheckboxForm(${session.sessonId},'isEnable${session.sessonId}')">Enable
																	<input type="checkbox" name="isFree" 
																		id="isFree${session.sessonId}"
																		onclick="submitCheckboxForm(${session.sessonId},'isFree${session.sessonId}')">Free
																	<input type="hidden" name="sessonId"
																		value="${session.sessonId}"
																		id="sessonId${session.sessonId}">
																</div>
															</form>


															<script>
															function submitCheckboxForm(sessionId,data){ 
																debugger;
																if($("#isLive" + sessionId).is(':checked')==false){
																	data='isLive'+sessionId;
																	submitCheckboxFormValid(sessionId,data);
																	$("#isFree" + sessionId).prop('checked',false)
																	data='isFree'+sessionId;
																	submitCheckboxFormValid(sessionId,data);
																	$("#isEnable" + sessionId).prop('checked',false)
																	data='isEnable'+sessionId;
																	submitCheckboxFormValid(sessionId,data);
																	}
																	if($("#isLive" + sessionId).is(':checked')==true)
																		submitCheckboxFormValid(sessionId,data);
																	else
																		$("#" + data).prop('checked',false)
																}
														function submitCheckboxFormValid(sessionId,data) {
															
														var sessonId= sessionId;
																if($("#" + data).is(':checked')==true)
																	val=1;
																if($("#" + data).is(':checked')==false)
																	val=0;
														var dataString= {
															"val":val, 
															"sessonId":sessonId,
															"attr":data
														}
														console.log(dataString);
														$.ajax({
														type:'POST',
														url:'saveSessionCheckbox',
														data: dataString,
														success: function(data){
														// $('#myResponse').html(data);
															
														}
														});
														return true;
														}
														
														
														</script>



															<div>
											<div class="well1 content_dropzone"
												id="divfordragcontent_${section.sectionId}"
												style="display: none" ondrop="dropContent(event)"
												ondragover="allowDropContent(event)">
												<h2 class="text-align content_dropzone_h"
													id="divfordragcontenth_${section.sectionId}">
													<spring:message code="lbl.dragherefromlist"
														text="Drag here from list" />
												</h2>
											</div>
											<div id="sectionContent${section.sectionId}"
												class="totalSectionContent sortable">
												<c:forEach items="${section.sectionContent}"
													var="sectionContent">
													
													<c:if test="${sectionContent.sessionId == session.sessonId}">	
													<div class="well1 well-sm contentItem"
														id="sortable_${sectionContent.contentId}">
													       <img
															src="<spring:url value='/resources/images/${sectionContent.contentIconPath}'/>"
															class="contentIcon"
															id="contentIcon${sectionContent.contentId}"> <label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

															<c:if
																test="${sectionContent.contentType=='PPT' || sectionContent.contentType=='PPTX' || sectionContent.contentType=='PDF' || (sectionContent.contentType=='VIDEO' && fn:length(sectionContent.questionList)>0)}">
																<a id="contentName${sectionContent.contentId}"
																	class="cursor-pointer"
																	href="coursepreview?courseId=${course.courseId}&isPublish=0&contentId=${sectionContent.contentId}&origin=true">
																	<c:if test="${sectionContent.contentName!=null}"> 
																	  ${sectionContent.contentName}
																</c:if> <c:if test="${sectionContent.contentName==null}">
																	  ${sectionContent.content}  															
																	  							
																	  
																</c:if>
																</a>
															</c:if> <c:if
																test="${sectionContent.contentType!='PPT' && sectionContent.contentType!='PPTX' && sectionContent.contentType!='PDF' && (sectionContent.contentType!='VIDEO' || fn:length(sectionContent.questionList)==0)}">
																<a id="contentName${sectionContent.contentId}"
																	class="cursor-pointer"
																	onclick="showContent('${sectionContent.contentPath}','${sectionContent.contentType}')">
																	<c:if test="${sectionContent.contentName!=null}"> 
																	  ${sectionContent.contentName}
																</c:if> <c:if test="${sectionContent.contentName==null}">
																	  ${sectionContent.content}
																</c:if>
																</a>
															</c:if>
														</label>

														<div class="pull-right">
															${sectionContent.attemptName}


															<%-- </c:if> --%>
															<div class="pull-right">
																<div class="dropdown">
																	<a id="dLabel" data-target="#" data-toggle="dropdown"
																		role="button" aria-haspopup="true"
																		aria-expanded="false"
																		class="content-dropdown icon-dropdown"> <img
																		src="resources/adminlte/dist/img/ellipsis-v.png"
																		class="shape" />
																	</a>
																	<ul class="dropdown-menu dropdown-menu-right"
																		aria-labelledby=" dlabel">
																		<c:choose>
																			<c:when test="${sectionContent.contentType =='TEST'}">
																				<li class="hide"><a
																					id="${section.sectionId}##${sectionContent.contentId}"
																					onclick="location.href='addEditTest?courseId=${course.courseId}&sectionId=${section.sectionId}&testId=${sectionContent.content}&contentId=${sectionContent.contentId}'"
																					class="cursor-pointer"><spring:message
																							code="lbl.edit" text="Edit" /></a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a
																					id="${section.sectionId}##${sectionContent.contentId}"
																					onclick="renametitle('${section.sectionId}##${sectionContent.contentId}')"
																					class="cursor-pointer"><spring:message
																							code="lbl.editname" text="Edit Name" /></a></li>

																				<li class="divider"></li>
																			</c:otherwise>
																		</c:choose>
																		<li><a
																			id="${section.sectionId}contentDelete${sectionContent.contentId}"
																			class="btndelete cursor-pointer"><spring:message
																					code="lbl.delete" text="Delete" /></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>

													</c:if>
												</c:forEach>
											</div>

											<h3 style="text-align: center;" class="hide">
												<spring:message code="lbl.autouploadlogo"
													text="Auto Upload Logo" />
											</h3>
											<h3 style="text-align: center;" class="hide">
												<spring:message code="lbl.dropfilesheretoautoupload"
													text="Drop files here to auto upload" />
											</h3>
										</div>
											
											
											
											
											
											
											
											
											
											
											
											
											<div class="input-group-addon">
												<div class="pull-right">
										<a style="color: #dd4b39" class="cursor-pointer" onclick="updateSessionName(${session.sessonId});" title="Edit Name"><i class="fa fa-edit"></i></a> &nbsp;&nbsp;
										 <a style="color: #dd4b39" class="cursor-pointer sessionDelete" id="sectionDelete${session.sessonId}" title="Delete"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;
										<!--   <a style="color: #dd4b39" class="cursor-pointer" onclick="sectionSettingInfoPopup('109')" title="Setting"><i class="fa fa-gears"></i></a>
										 -->		</div>
												
											</div>

										</div>
										
										
												<div class="col-sm-4">
													<h3 style="text-align: center;">
														<a style="cursor: pointer" id="btn"${session.sessonId}"
															onclick="contentlibrarypage(${session.sectionId},1,${session.sessonId })"><img
															src="resources/adminlte/dist/img/courseinfo.png"></a>
													</h3>
													<h4 style="text-align: center;">
														<spring:message code="lbl.addcontentfromlibrary"
															text="Add Content From Library" />
													</h4>
												</div>
												<c:forEach items="${attemptlist}" var="attempts">
													<c:forEach var = "i" begin = "1" end = "${attemptlist.size()-1}">
													<div class="col-sm-4">
														<!-- style="border-left: 1px solid #eee; border-right: 1px solid #eee;"> -->
														<h3 style="text-align: center;">
															<a style="cursor: pointer"
																id="bulkbtn${session.sectionId}"
																onclick="testPopup('${section.sectionId}', '${attempts.id}','${session.sessonId}');"><img
																src="resources/adminlte/dist/img/courseinfo.png"></a>
														</h3>
														<h4 style="text-align: center;">
															<spring:message code="lbl.addquizforAttempt"
																text="Add Quiz For Attempt 1" />
															${attempts.name}
														</h4>
													</div>
													</c:forEach>


												</c:forEach>


									            
	     								</c:if>
	     								
								         </c:forEach>   
								         
<div class="modal fade" id="sessionPop${section.sectionId}" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body" style="height: 250px">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading"><spring:message code="lbl.lbl.createnewsession" text="Create New Session"/></h3>
						<br/>
					
						<div class="col-xs-12">
					
							<form name="sessionForm" id="sessionForm${section.sectionId}">
							
							    <input type="hidden" name="courseId${section.sectionId}" value="${course.courseId}" id="courseId">
								<input type="hidden" name="sectionId${section.sectionId}" id="sessionFormsectionId" value="${section.sectionId}">
						
								  <input type="hidden" name="isChapterTest" value="0"> 
								<input type="text" class="form-control" maxlength="50"
									placeholder="Session Name" id="sessionName${section.sectionId}" name="sessionName"
									onkeydown="sectionNameKey();" onkeyup="sectionNameKey();">
								<label class="requireFld" id="sessionNameError1"><spring:message code="msg.empty" text="This field is required."/></label>
								<label class="requireFld" id="sessionNameError2"><spring:message code="msg.alphanumericallowed" text="Only alphanumeric value is acceptable."/></label>
							</form>
						</div>
					
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSession1(${course.courseId},${section.sectionId})" id="saveSessionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
								           
								           <button type="button" align="center" class="btn btn-success btn-flat" data-toggle="modal" data-target="#sessionPop${section.sectionId}" >Add New Session</button>
								               
								    
										 </c:if> 
										 <c:if test="${section.isPractice==0 && section.isChapterTest==1}">
                                     
                                         
                                            <c:forEach items="${sessionList}" var="session">
                                                <c:if test="${session.isChapterTest==1 && session.sectionId==section.sectionId}">
									<div class="input-group">
											<div class="section_name_div" id="sectionNameText109">${session.sessionName}</div>
											
											
											<!-- Chapter Exam Start Here -->
											<div>
											<div class="well1 content_dropzone"
												id="divfordragcontent_${section.sectionId}"
												style="display: none" ondrop="dropContent(event)"
												ondragover="allowDropContent(event)">
												<h2 class="text-align content_dropzone_h"
													id="divfordragcontenth_${section.sectionId}">
													<spring:message code="lbl.dragherefromlist"
														text="Drag here from list" />
												</h2>
											</div>
											<div id="sectionContent${section.sectionId}"
												class="totalSectionContent sortable">
												<c:forEach items="${section.sectionContent}"
													var="sectionContent">
													
													<c:if test="${sectionContent.sessionId == session.sessonId}">	
													<div class="well1 well-sm contentItem"
														id="sortable_${sectionContent.contentId}">
													<img
															src="<spring:url value='/resources/images/${sectionContent.contentIconPath}'/>"
															class="contentIcon"
															id="contentIcon${sectionContent.contentId}"> <label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;

															<c:if
																test="${sectionContent.contentType=='PPT' || sectionContent.contentType=='PPTX' || sectionContent.contentType=='PDF' || (sectionContent.contentType=='VIDEO' && fn:length(sectionContent.questionList)>0)}">
																<a id="contentName${sectionContent.contentId}"
																	class="cursor-pointer"
																	href="coursepreview?courseId=${course.courseId}&isPublish=0&contentId=${sectionContent.contentId}&origin=true">
																	<c:if test="${sectionContent.contentName!=null}"> 
																	  ${sectionContent.contentName}
																</c:if> <c:if test="${sectionContent.contentName==null}">
																	  ${sectionContent.content}  															
																	  							
																	  
																</c:if>
																</a>
															</c:if> <c:if
																test="${sectionContent.contentType!='PPT' && sectionContent.contentType!='PPTX' && sectionContent.contentType!='PDF' && (sectionContent.contentType!='VIDEO' || fn:length(sectionContent.questionList)==0)}">
																<a id="contentName${sectionContent.contentId}"
																	class="cursor-pointer"
																	onclick="showContent('${sectionContent.contentPath}','${sectionContent.contentType}')">
																	<c:if test="${sectionContent.contentName!=null}"> 
																	  ${sectionContent.contentName}
																</c:if> <c:if test="${sectionContent.contentName==null}">
																	  ${sectionContent.content}
																</c:if>
																</a>
															</c:if>
														</label>

														<div class="pull-right">
															${sectionContent.attemptName}


															<%-- </c:if> --%>
															<div class="pull-right">
																<div class="dropdown">
																	<a id="dLabel" data-target="#" data-toggle="dropdown"
																		role="button" aria-haspopup="true"
																		aria-expanded="false"
																		class="content-dropdown icon-dropdown"> <img
																		src="resources/adminlte/dist/img/ellipsis-v.png"
																		class="shape" />
																	</a>
																	<ul class="dropdown-menu dropdown-menu-right"
																		aria-labelledby=" dlabel">
																		<c:choose>
																			<c:when test="${sectionContent.contentType =='TEST'}">
																				<li class="hide"><a
																					id="${section.sectionId}##${sectionContent.contentId}"
																					onclick="location.href='addEditTest?courseId=${course.courseId}&sectionId=${section.sectionId}&testId=${sectionContent.content}&contentId=${sectionContent.contentId}'"
																					class="cursor-pointer"><spring:message
																							code="lbl.edit" text="Edit" /></a></li>
																			</c:when>
																			<c:otherwise>
																				<li><a
																					id="${section.sectionId}##${sectionContent.contentId}"
																					onclick="renametitle('${section.sectionId}##${sectionContent.contentId}')"
																					class="cursor-pointer"><spring:message
																							code="lbl.editname" text="Edit Name" /></a></li>

																				<li class="divider"></li>
																			</c:otherwise>
																		</c:choose>
																		<li><a
																			id="${section.sectionId}contentDelete${sectionContent.contentId}"
																			class="btndelete cursor-pointer"><spring:message
																					code="lbl.delete" text="Delete" /></a></li>
																	</ul>
																</div>
															</div>
														</div>
													</div>

													</c:if>
												</c:forEach>
											</div>

											<h3 style="text-align: center;" class="hide">
												<spring:message code="lbl.autouploadlogo"
													text="Auto Upload Logo" />
											</h3>
											<h3 style="text-align: center;" class="hide">
												<spring:message code="lbl.dropfilesheretoautoupload"
													text="Drop files here to auto upload" />
											</h3>
										</div>
											
									
											
											
											
											
											
											
											
											
											
											
											
											<div class="input-group-addon">
												<div class="pull-right">
											<a style="color: #dd4b39" class="cursor-pointer" onclick="updateSessionName(${session.sessonId})" title="Edit Name"><i class="fa fa-edit"></i></a> &nbsp;&nbsp;
										    <a style="color: #dd4b39" class="cursor-pointer sessionDelete" id="sectionDelete${session.sessonId}" title="Delete"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;
									
											 <a style="color: #dd4b39" class="cursor-pointer" onclick="sectionSettingInfoPopup('109')" title="Setting"><i class="fa fa-gears"></i></a>
												</div>
												
											</div>

										</div>
										
										
												<div class="col-sm-4">
												<%-- 	<h3 style="text-align: center;">
														<a style="cursor: pointer" id="btn"${session.sessonId}"
															onclick="contentlibrarypage(${session.sectionId})"><img
															src="resources/adminlte/dist/img/courseinfo.png"></a>
													</h3>
													<h4 style="text-align: center;">
														<spring:message code="lbl.addcontentfromlibrary"
															text="Add Content From Library" />
													</h4> --%>
												</div>
												<c:forEach items="${attemptlist}" var="attempts">
													<c:forEach var = "i" begin = "1" end = "${attemptlist.size()-1}">
													<div class="col-sm-4">
													
														<!-- style="border-left: 1px solid #eee; border-right: 1px solid #eee;"> -->
														<h3 style="text-align: center;">
															<a style="cursor: pointer"
																id="bulkbtn${session.sectionId}"
																onclick="testPopup('${section.sectionId}', '${attempts.id}','${session.sessonId}');"><img
																src="resources/adminlte/dist/img/courseinfo.png"></a>
														</h3>
														<h4 style="text-align: center;">
															<spring:message code="lbl.addquizforAttempt"
																text="Add Quiz For Attempt 1" />
															${attempts.name}
														</h4>
													</div>
													</c:forEach>


												</c:forEach>
     
     
									             
								               
	      									</c:if>
								         </c:forEach>     
	     
       <div class="modal fade" id="sessionPop${section.sectionId }" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body" style="height: 250px">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading"><spring:message code="lbl.createnewsession" text="Create New Session"/></h3>
						<br/>
					
						<div class="col-xs-12">
						
							<form name="sessionForm1" id="sessionForm1${section.sectionId}">
							
							    <input type="hidden" name="courseId${section.sectionId}" value="${course.courseId}">
								<input type="hidden" name="sectionId${section.sectionId}" id="sessionFormsectionId" value="${section.sectionId}">
								  <input type="hidden" name="isChapterTest" value="1"> 
								<input type="text" class="form-control" maxlength="50"
									placeholder="Session Name" id="sessionName${section.sectionId}" name="sessionName"
									onkeydown="sectionNameKey();" onkeyup="sectionNameKey();">
								<label class="requireFld" id="sessionNameError1"><spring:message code="msg.empty" text="This field is required."/></label>
								<label class="requireFld" id="sessionNameError2"><spring:message code="msg.alphanumericallowed" text="Only alphanumeric value is acceptable."/></label>
							</form>
						</div>
					
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSessionForTestChapter(${course.courseId},${section.sectionId})" id="saveSessionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	
								         
							           
								          <button type="button" align="center" class="btn btn-success btn-flat" data-toggle="modal" data-target="#sessionPop${section.sectionId}" >Add New Session</button>
								  
										 </c:if> 
										 
											

										<%-- 	<c:if test="${section.isPractice==0 && section.isChapterTest==1}">
                                     
                                         
                                            <c:forEach items="${sessionList}" var="session">
                                            
									<div class="input-group">
											<div class="section_name_div" id="sectionNameText109">${session.sessionName}</div>
											<div class="input-group-addon">
												<div class="pull-right">
													<a style="color: #dd4b39" class="cursor-pointer" onclick="updateSectionName('109');" title="Edit Name"><i class="fa fa-edit"></i></a> &nbsp;&nbsp; <a style="color: #dd4b39" class="cursor-pointer sectionDelete" id="sectionDelete109" title="Delete"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp; <a style="color: #dd4b39" class="cursor-pointer" onclick="sectionSettingInfoPopup('109')" title="Setting"><i class="fa fa-gears"></i></a>
												</div>
												
											</div>

										</div>
										
										
												<div class="col-sm-4">
													<h3 style="text-align: center;">
														<a style="cursor: pointer" id="btn"${session.sessonId}"
															onclick="contentlibrarypage(${session.sectionId})"><img
															src="resources/adminlte/dist/img/courseinfo.png"></a>
													</h3>
													<h4 style="text-align: center;">
														<spring:message code="lbl.addcontentfromlibrary"
															text="Add Content From Library" />
													</h4>
												</div>
												<c:forEach items="${attemptlist}" var="attempts">
													<c:forEach var = "i" begin = "1" end = "${attemptlist.size()-1}">
													<div class="col-sm-4">
														<!-- style="border-left: 1px solid #eee; border-right: 1px solid #eee;"> -->
														<h3 style="text-align: center;">
															<a style="cursor: pointer"
																id="bulkbtn${session.sectionId}"
																onclick="testPopup('${session.sectionId}', '${attempts.id}','${session.sessonId}');"><img
																src="resources/adminlte/dist/img/courseinfo.png"></a>
														</h3>
														<h4 style="text-align: center;">
															<spring:message code="lbl.addquizforAttempt"
																text="Add Quiz For Attempt 1" />
															${attempts.name}
														</h4>
													</div>
													</c:forEach>


												</c:forEach>

									 <button type="button" align="center" class="btn btn-success btn-flat" data-toggle="modal" data-target="#sessionPop" >Add New Session</button>
								               
								               
	
								               
										 </c:if> 
										 
										 
										 	<c:if test="${section.isPractice==0}">
                                     
                                         
                                            <c:forEach items="${sessionList}" var="session">
                                            
									<div class="input-group">
											<div class="section_name_div" id="sectionNameText109">${session.sessionName}</div>
											<div class="input-group-addon">
												<div class="pull-right">
													<a style="color: #dd4b39" class="cursor-pointer" onclick="updateSectionName('109');" title="Edit Name"><i class="fa fa-edit"></i></a> &nbsp;&nbsp; <a style="color: #dd4b39" class="cursor-pointer sectionDelete" id="sectionDelete109" title="Delete"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp; <a style="color: #dd4b39" class="cursor-pointer" onclick="sectionSettingInfoPopup('109')" title="Setting"><i class="fa fa-gears"></i></a>
												</div>
												
											</div>

										</div>
										
										
												<div class="col-sm-4">
													<h3 style="text-align: center;">
														<a style="cursor: pointer" id="btn"${session.sessonId}"
															onclick="contentlibrarypage(${session.sectionId})"><img
															src="resources/adminlte/dist/img/courseinfo.png"></a>
													</h3>
													<h4 style="text-align: center;">
														<spring:message code="lbl.addcontentfromlibrary"
															text="Add Content From Library" />
													</h4>
												</div>
												<c:forEach items="${attemptlist}" var="attempts">
													<c:forEach var = "i" begin = "1" end = "${attemptlist.size()-1}">
													<div class="col-sm-4">
														<!-- style="border-left: 1px solid #eee; border-right: 1px solid #eee;"> -->
														<h3 style="text-align: center;">
															<a style="cursor: pointer"
																id="bulkbtn${session.sectionId}"
																onclick="testPopup('${session.sectionId}', '${attempts.id}','${session.sessonId}');"><img
																src="resources/adminlte/dist/img/courseinfo.png"></a>
														</h3>
														<h4 style="text-align: center;">
															<spring:message code="lbl.addquizforAttempt"
																text="Add Quiz For Attempt 1" />
															${attempts.name}
														</h4>
													</div>
													</c:forEach>


												</c:forEach>

									 <button type="button" align="center" class="btn btn-success btn-flat" data-toggle="modal" data-target="#sessionPop" >Add New Session</button>
								               
								               
	
								               
										 </c:if> 
										 
 --%>										 
										 
									
									     </div>
									 	 
									</div>		
								</div>
								
								
								
								
								
								
								<c:set var="i" value="${i+1}"></c:set>
								</c:forEach>
							
						
                            </div>
                            
							<div class="col-sm-12 form-group"
								style="text-align: center; margin-top: 30px">
								<button type="button" class="btn btn-success btn-flat" onclick="sectionCreatePopup();"><spring:message code="lbl.addnewsection" text="Add New Section"/></button>
								<!-- <a onclick="sectionCreatePopup();" style="cursor: pointer">
									<img src="resources/adminlte/dist/img/addmore.png">
								</a> -->&nbsp;&nbsp;
								<button type="button" class="btn btn-success btn-flat"  onclick="getalreadycreatedcourse()"><spring:message code="lbl.importsection" text="Import Section"/></button>
							</div>
                            
                            	<div class="col-sm-12 form-group"
								style="text-align: center; margin-top: 20px">
								<c:if test="${fn:length(sectionlist)==0}">
								<button type="button" id="course-content-submit-button" class="btn btn-success btn-flat" style="margin-top: 30px;"><spring:message code="lbl.savelater" text="Save Later"/></button>
								</c:if>
								<c:if test="${fn:length(sectionlist)>0}">
								<input type="hidden" id="publish" value="${isPublished}">
								<button type="button" onclick="validateSectionSetting()" id="course-content-submit-button" class="btn btn-success btn-flat" style="margin-top: 30px;"><spring:message code="lbl.next" text="Next"/></button>
								</c:if>
							</div>
							
						</div>
					</div>
					
		                <div class="col-sm-3" style="margin-top:15px;" id="suggestion_content_div">  
                              <!-- <div class="row" data-spy="affix" data-offset-top="205"> -->      
                             <div class="row fixed-content">               
									<h4 class="text-align hide">Content List</h4>
									<table class="table table-bordered"
											id="suggestioncontentlist" style="width:100% !important;">
											<thead class="hide">
												<tr>
												    <th><em class="fa fa-cog"></em></th>
												</tr>
											</thead>
											<tbody class="suggestioncontentlistbody">
											</tbody>
											</table>
									</div>		
											</div>
											
											
				</form>
			</div>

			<!-- content-wrapper -->
		</div>
		<!-- ./wrapper -->
	</div>
	<!-- Section Model -->

	<div class="modal fade" id="sectionPop" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body" style="height: 250px">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading"><spring:message code="lbl.createnewsection" text="Create New Section"/></h3>
						<br/>
						<div class="col-xs-12">
							<form name="sectionForm" id="sectionForm">
								<input type="hidden" name="courseId" value="${course.courseId}">
								<input type="hidden" name="sectionId" id="sectionFormsectionId">
								<input type="text" class="form-control" maxlength="50"
									placeholder="<spring:message code="lbl.sectionname" text="Section Name"/>" id="sectionName" name="sectionName"
									onkeydown="sectionNameKey();" onkeyup="sectionNameKey();">
								<label class="requireFld" id="sectionNameError1"><spring:message code="msg.empty" text="This field is required."/></label>
								<label class="requireFld" id="sectionNameError2"><spring:message code="msg.alphanumericallowed" text="Only alphanumeric value is acceptable."/></label>
							</form>
						</div>
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSection()" id="saveSectionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	
		<div class="modal fade" id="sessionPop" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body" style="height: 250px">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading">Enter Session Name</h3>
						<br/>
						<div class="col-xs-12">
							<form name="sectionForm" id="sectionForm">
								<input type="hidden" name="courseId" value="${course.courseId}">
								<input type="hidden" name="sectionId" id="sectionFormsectionId">
								<input type="text" class="form-control" maxlength="50"
									placeholder="<spring:message code="lbl.sectionname" text="Section Name"/>" id="sectionname" name="sectionName"
									onkeydown="sectionNameKey();" onkeyup="sectionNameKey();">
								<label class="requireFld" id="sectionNameError1"><spring:message code="msg.empty" text="This field is required."/></label>
								<label class="requireFld" id="sectionNameError2"><spring:message code="msg.alphanumericallowed" text="Only alphanumeric value is acceptable."/></label>
							</form>
						</div>
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSessionUpdate()" id="saveSectionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.section model -->
	
<%-- 	<div class="modal fade" id="sessionPop" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body" style="height: 250px">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading"><spring:message code="lbl.lbl.createnewsession" text="Create New Session"/></h3>
						<br/>
					
						<div class="col-xs-12">
						<c:forEach items="sectionlist" var="section"></c:forEach>
							<form name="sessionForm" id="sessionForm">
							
							    <input type="hidden" name="courseId" value="${course.courseId}">
								<input type="text" name="sectionId" id="sessionFormsectionId" value="${sectionId}">
						
								  <input type="hidden" name="isChapterTest" value="0"> 
								<input type="text" class="form-control" maxlength="50"
									placeholder="Session Name" id="sessionName" name="sessionName"
									onkeydown="sectionNameKey();" onkeyup="sectionNameKey();">
								<label class="requireFld" id="sessionNameError1"><spring:message code="msg.empty" text="This field is required."/></label>
								<label class="requireFld" id="sessionNameError2"><spring:message code="msg.alphanumericallowed" text="Only alphanumeric value is acceptable."/></label>
							</form>
						</div>
					
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSession()" id="saveSessionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	 --%>
<%-- 	<div class="modal fade" id="sessionPop1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body" style="height: 250px">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading"><spring:message code="lbl.createnewsession" text="Create New Session"/></h3>
						<br/>
					
						<div class="col-xs-12">
						<c:forEach items="sectionlist" var="section"></c:forEach>
							<form name="sessionForm1" id="sessionForm1">
							
							    <input type="hidden" name="courseId" value="${course.courseId}">
								<input type="text" name="sectionId" id="sessionFormsectionId" value="${sectionId}">
								  <input type="hidden" name="isChapterTest" value="1"> 
								<input type="text" class="form-control" maxlength="50"
									placeholder="Session Name" id="sessionName" name="sessionName"
									onkeydown="sectionNameKey();" onkeyup="sectionNameKey();">
								<label class="requireFld" id="sessionNameError1"><spring:message code="msg.empty" text="This field is required."/></label>
								<label class="requireFld" id="sessionNameError2"><spring:message code="msg.alphanumericallowed" text="Only alphanumeric value is acceptable."/></label>
							</form>
						</div>
					
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSessionForTestChapter()" id="saveSessionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div> --%>

<!-------------------------------------------------- Section settings popup ------------------------------------------------------------>
	<div class="modal fade" id="sectionSettingPop" tabindex="-1" role="dialog"
		aria-labelledby="sectionSettingPop">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content" data-ng-app="sectionSettingApp">
					<div class="modal-body row page-background-color ng-scope" id="sectionSettingController" data-ng-controller="sectionSettingController" data-ng-cloak>
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 style="text-align: center" id="sectionHeading"><spring:message code="lbl.sectionsetting" text="Section Settings"/></h3>
						<br/>
						<div class="row" style="margin:0px">
						<form name="sectionSettingForm" id="sectionSettingForm" class="validation--form">
						<div class="col-xs-12">
								<input type="hidden" name="courseId" data-ng-value="courseId">
								<input type="hidden" name="sectionId" id="sectionSettingFormSectionId" data-ng-value="sectionContent.sectionId">
							 
							 <div class="col-xs-12 form-group notifications" tabindex="-1" id="setting-form-submit-error">
							 <p class="notification notification-critical" role="alert"><spring:message code="msg.somethingwentwrong" text="Something went wrong,please try again."/></p>
							 </div>
							 
							 <div class="col-xs-12 form-group">
								<label><spring:message code="lbl.isquizmandatory" text="Is Quiz Mandatory ?"/></label>
								<p id = "setting-quiz-radio-buttons">
								<input data-icheck type="radio" name="isQuizMandatory" data-ng-value="1" data-ng-model="sectionContent.isQuizMandatory" class="quiz_mandatory">&nbsp;<spring:message code="lbl.yes" text="Yes"/>&nbsp;
								<input data-icheck type="radio" name="isQuizMandatory" data-ng-value="0" data-ng-model="sectionContent.isQuizMandatory" class="quiz_mandatory">&nbsp;<spring:message code="lbl.no" text="No"/>
								</p>
								</div>
								<div class="col-xs-12 form-group form-row" id="section_passing_criteria" data-ng-show="IsVisible">
								<label><spring:message code="lbl.passingcriteria" text="Passing Criteria"/></label>
								<p>
								<input type="text" name="passingCriteria" data-ng-value="sectionContent.passingCriteria" data-ng-model="sectionContent.passingCriteria" class="form-control" style="display: inline;width:80px" data-ng-keyup="keyValidateforSettingForm()">&nbsp;&nbsp;%
								</p>
								<p class="requireFld error-msg validation--error" id="passingCriteria-required-error"><spring:message code="msg.empty" text="This field is required."/></p>
								<p class="requireFld error-msg validation--error" id="passingCriteria-value-invalid"><spring:message code="msg.sectionsectionformfieldvalueinvalid" text="Value should be numeric and between 1 to 100." arguments='1;100' htmlEscape='false' argumentSeparator=';'/></p>
								
								</div>
								<div class="col-xs-12 form-group form-row">
								<label><spring:message code="lbl.mintotalspenttimeonsection" text="Minimum total spent time on section for media content is"/></label>
								<p>
								<input type="text" name="minTimeSpent" class="form-control" style="display: inline;width:80px" data-ng-model="sectionContent.minTimeSpent" data-ng-keyup="keyValidateforSettingForm()" maxlength="4">&nbsp;<spring:message code="lbl.minute" text="Minute"/>
								</p>
								<p class="requireFld error-msg validation--error" id="minTimeSpent-required-error"><spring:message code="msg.empty" text="This field is required."/></p>
								<p class="requireFld error-msg validation--error" id="minTimeSpent-value-invalid"><spring:message code="msg.sectionsectionformfieldvalueinvalid" text="Value should be numeric and between 5 to 3600." arguments='5;3600' htmlEscape='false' argumentSeparator=';'/></p>
								</div>
						</div>
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a data-ng-click="updateSectionSettingInfo()" id="saveSectionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message code="lbl.save" text="Save"/></a>&emsp;&emsp;

							<a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message code="lbl.cancel" text="Cancel"/></a>&emsp;&emsp;
						</div>
						</form>
                       </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!------------------------------------------/.section setting pop up--------------------------------------------------------------------->
        <div class="modal fade" id="section-setting-updated" tabindex="-1" role="dialog" aria-labelledby="homeAlert">
            <div class=" col-md-12 col-sm-12 col-xs-12">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">			
						<div class="modal-body" style="text-align: center">
						<h3></h3>
							<p></p>
						</div>
					</div>
				</div>
			</div>
		</div>	
		
	<!-- Start of Alert box for file type -->
	<div class="modal fade" id="fileType" tabindex="-1" role="dialog"
		aria-labelledby="fileType">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p><spring:message code="msg.onlypdfcanupload" text="You can upload only pdf file."/></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal"><spring:message code="lbl.ok" text="Ok"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Alert box -->

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
	<!-- /.and section empty alert box -->
	
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
	
	<!--  Alert box for delete Section or its content -->
	<div class="modal fade" id="deleteAlert" tabindex="-1" role="dialog"
		aria-labelledby="deletetestAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p></p>
						<button type="button" class="btn btn-default button-width"
							data-dismiss="modal"><spring:message code="lbl.no" text="No"/></button>
						<a id="deleteButton"><button type="button"
								class="btn btn-success button-width"
								data-dismiss="modal"><spring:message code="lbl.yes" text="Yes"/></button></a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- pop up for adding existing test -->

	<div class="modal fade" id="testtablePopup" role="dialog"
		aria-labelledby="testtablePopup">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body  page-background-color">
					<div class="pull-right">
						<button type="button" id="addnewtestbutton"
							class="btn btn-success btn-flat hide"
							style="margin: auto">
							<spring:message code="lbl.addnewassessment" text="Add New Quiz"/>
							
						</button>
						<button type="button" class="close" data-dismiss="modal">×</button>
					</div>
					<div class="col-xs-12" style="min-height: 30px"></div>
					<table class="table table-hover table-striped" id="testTable">
						<thead>
							<tr>
								<th><spring:message code="lbl.date" text="Date"/></th>
								<th><spring:message code="lbl.assessmentname" text="Quiz Name"/></th>
								<th><spring:message code="lbl.status" text="status"/></th>
								<th style="width: 150px"></th>
							</tr>
						</thead>
						<tbody id="tbody">
						</tbody>
					</table>
					<p style="margin-top: -5px;margin-left: 0px;"><spring:message code="lbl.note" text="Note"/>: <spring:message code="msg.assessmentmustbepublishedforpublishthecourse" text="Quiz must be published for publish the course."/></p>
					<div class="col-xs-12" style="min-height: 30px"></div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div class="modal fade" id="showContent" role="dialog"
		aria-labelledby="showContent">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body page-background-color">
					<div class="row">
						<div class="col-xs-12" style="text-align: center" id="frame">
						</div>
						<div class="col-xs-12" style="text-align: center" id="promoVideoDiv">
																							
						</div>
						<div class="col-xs-12" style="min-height: 30px"></div>
						<div class="col-xs-12" style="min-height: 30px">
							<button type="button" onclick="closeModalnew()"
								class="btn btn-success btn-flat  button-width-large pull-right"
								style="margin: auto" data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div id="librarydiv"></div>
		
		<div class="modal fade" id="bulkmodal" tabindex="-1" role="dialog"
		aria-labelledby="">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="box-header with-border">
					  <h3 class="box-title"><spring:message code="lbl.uploadcontents" text="Upload Contents"/></h3>
					  <div class="box-tools pull-right">
                       <div class="nav-tabs-custom">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="button-width-large active" style="margin-right:0px;text-align:center;min-width: 150px;">
									<a href="#browseTab" style="padding-top: 0px;"
									data-toggle="tab"><spring:message code="lbl.uploadbybrowse" text="Upload by Browse"/></a></li>
								<li class=" button-width-large" style="margin-right:0px;text-align:center;min-width: 110px;">
								<a href="#urlTab" style="padding-top: 0px;"
									data-toggle="tab"><spring:message code="lbl.embedurl" text="Embed URL"/></a></li>																
							</ul>
						</div>                   
                      </div>
					</div>
					<div id="overlay" class="overlay1"
						style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
						<img id="loading" class="lazy"
							src="<spring:url value='/resources/images/loading.gif'/>"
							style="position: fixed; left: 50%; top: 50%;">
					</div>
					<div class="modal-body">
					 <div class="tab-content">
					  <div id="browseTab" class="tab-pane active">
						<div class="form-group" id="contentdiv">
							<!-- <label>Upload Contents : </label>  --><input type="file"
								id="bulkfile" name="bulkfile" multiple="multiple"
								onchange="fileprocess();">
								<span><spring:message code="lbl.note" text="Note"/> : <spring:message code="msg.contenttype.valid" text="You can upload maximum 5 files of type(image, pdf, ppt, video, url)" arguments="5" htmlEscape="false" argumentSeparator=";"/></span>
						 </div>
						 <div id="titlediv"></div>
						 <div id="errordiv"></div>
						 <div class="box-footer">
						 <!-- For adding file contents -->
						    <input type="hidden" id="sectionidmodal">
							<button class="btn btn-default btn-flat button-width" type="button"
								data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
							<button class="btn btn-success btn-flat pull-right button-width" type="button"
								onclick="savebulkfiles('course')"><spring:message code="lbl.submit" text="Submit"/></button>
						 </div>
						</div>
						
						<div id="urlTab" class="tab-pane">
						        <div id="urlset" class="row">
								<div id="urldiv0" class="row col-xs-12 form-group">
										<span class="pull-right" id="addmore0"> <a href="#"
											onclick="addMore();"> <font color="#00A65A"><i
													class="fa fa-plus-circle"></i></font></a>&nbsp; <a href="#"
											onclick="removeMore();"> <font color="#00A65A"><i
													class="fa fa-minus-circle"></i></font></a>
										</span>
										<div class="col-xs-7">
											<label><spring:message code="lbl.contenturlinorder" text="Content URL 1" arguments="1" htmlEscape="false" argumentSeparator=";"/></label> <input
												placeholder="<spring:message code="placeholder.promotionalvideo" text="Add link from youtube"/>" id="url0"
												class="form-control">
										</div>
										<div class="col-xs-4">
											<label><spring:message code="lbl.titleinorder" text="Title 1" arguments="1" htmlEscape="false" argumentSeparator=";"/></label> <input
												placeholder="<spring:message code="lbl.entertitle" text="Enter Title"/>" id="titleurl0"
												class="form-control">
										</div>
									</div>
								</div>
								<div class="box-footer">
							    <button class="btn btn-default btn-flat button-width" type="button" onclick="clearbulkmodal();"
								data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
							    <button class="btn btn-success btn-flat pull-right button-width" type="button"
								onclick="submitcontenturl('course')"><spring:message code="lbl.submit" text="Submit"/></button>
						 </div>
								 <input type="hidden" id="count" value="1">
						</div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<div class="modal fade" id="titlemodal" tabindex="-1" role="dialog"
		aria-labelledby="">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="box-header with-border">
						<h3 class="box-title"><spring:message code="lbl.renamecontent" text="Rename Content"/></h3>
					</div>
					<div id="overlay" class="overlay1"
						style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
						<img id="loading" class="lazy"
							src="<spring:url value='/resources/images/loading.gif'/>"
							style="position: fixed; left: 50%; top: 50%;">
					</div>
					<div class="modal-body">
						<div class="form-group" id="title_Div">
							<label><spring:message code="lbl.entertitle" text="Enter Title"/> : </label> <input type="text" id="title_new"
								class="form-control" placeholder="<spring:message code="lbl.entertitle" text="Enter Title"/>">
						</div>
						<div class="box-footer">
							<input type="hidden" id="sectionidtitle"> <input
								type="hidden" id="contentidtitle">
							<button class="btn btn-default btn-flat" type="button"
								data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
							<button class="btn btn-flat btn-success pull-right" type="button"
								onclick="saverenametitle()"><spring:message code="lbl.submit" text="Submit"/></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="courselist"></div>
</body>
<script type="text/javascript">
       var messages = new Array();
       messages['lbl.selectassessment'] = "<spring:message code='lbl.selectassessment' text='Select Quiz' javaScriptEscape='true' />";     
       messages['msg.maxcharacterlength'] = "<spring:message code='msg.maxcharacterlength' arguments='#maxlength' text='Maximum #maxlength characters allowed.' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
       messages['msg.titleismandatory'] = "<spring:message code='msg.titleismandatory' text='Title is mandatory.' javaScriptEscape='true' />";
       messages['msg.anysectioncontentcannotbeempty'] = "<spring:message code='msg.anysectioncontentcannotbeempty' text='Any Section Content can not be empty.' javaScriptEscape='true' />";
       messages['lbl.save'] = "<spring:message code='lbl.save' text='Save' javaScriptEscape='true' />";
       messages['lbl.savelater'] = "<spring:message code='lbl.savelater' text='Save Later' javaScriptEscape='true' />";
       messages['lbl.next'] = "<spring:message code='lbl.next' text='Next' javaScriptEscape='true' />";
       messages['lbl.update'] = "<spring:message code='lbl.update' text='Update' javaScriptEscape='true' />";
       messages['lbl.section'] = "<spring:message code='lbl.section' text='Section' javaScriptEscape='true' />";
       messages['lbl.editname'] = "<spring:message code='lbl.editname' text='Edit Name' javaScriptEscape='true' />";
       messages['lbl.delete'] = "<spring:message code='lbl.delete' text='Delete' javaScriptEscape='true' />";
       messages['lbl.setting'] = "<spring:message code='lbl.setting' text='Setting' javaScriptEscape='true' />";
       messages['lbl.note'] = "<spring:message code='lbl.note' text='Note' javaScriptEscape='true' />";
       messages['msg.sectionmusthavecontentforpublish'] = "<spring:message code='msg.sectionmusthavecontentforpublish' text='Section must have content for publish.' javaScriptEscape='true' />";
       messages['lbl.autouploadlogo'] = "<spring:message code='lbl.autouploadlogo' text='Auto Upload Logo' javaScriptEscape='true' />";
       messages['lbl.dropfilesheretoautoupload'] = "<spring:message code='lbl.dropfilesheretoautoupload' text='Drop files here to auto upload' javaScriptEscape='true' />";
       messages['lbl.addcontentfromlibrary'] = "<spring:message code='lbl.addcontentfromlibrary' text='Add Content From Library' javaScriptEscape='true' />";
       messages['lbl.addfilefromexternal'] = "<spring:message code='lbl.addfilefromexternal' text='Add File From External' javaScriptEscape='true' />";
       messages['lbl.addassessment'] = "<spring:message code='lbl.addassessment' text='Add Quiz' javaScriptEscape='true' />";
       messages['lbl.edit'] = "<spring:message code='lbl.edit' text='Edit' javaScriptEscape='true' />";
       messages['msg.content.delete'] = "<spring:message code='msg.content.delete' text='Are you sure to delete this content?' javaScriptEscape='true' />";
       messages['msg.deletesection'] = "<spring:message code='msg.deletesection' text='Are you sure for delete this section?' javaScriptEscape='true' />";
       messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' text='Something went wrong, try again.' javaScriptEscape='true' />";
       messages['lbl.createnewsection'] = "<spring:message code='lbl.createnewsection' javaScriptEscape='true' />";
       messages['lbl.createnewsession'] = "<spring:message code='lbl.createnewsession' javaScriptEscape='true' />";
       messages['lbl.editsectionname'] = "<spring:message code='lbl.editsectionname' javaScriptEscape='true' />";
       messages['lbl.dragherefromlist'] = "<spring:message code='lbl.dragherefromlist' text='Drag here from list'/>";
       messages['msg.cannotbedeletedalreadyinuse'] = "<spring:message code='msg.cannotbedeletedalreadyinuse' text='Cannot be deleted already in use.' javaScriptEscape='true' />";
       messages['msg.contentinusecannotdelete'] = "<spring:message code='msg.contentinusecannotdelete' text='#contentname is in use. Please remove content from following courses #course to delete permanently from Content Library.' arguments='#contentname;#course' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
       messages['msg.sectionsettingupdatedsuccess'] = "<spring:message code='msg.sectionsettingupdatedsuccess' text='Section setting has been successfully updated.'/>";
       messages['msg.sectionsettingvalidationfailed'] = "<spring:message code='msg.sectionsettingvalidationfailed' text='Please add minimum one quiz in following sections because you have provided in section\'s passing criteria that quiz is mandatory.'/>";
</script> 
<script src="<spring:url value='/resources/js/jquery.fancybox.js'/>"></script>
<script src="<spring:url value='/resources/js/addcoursemat.js'/>"></script>
<script src="<spring:url value='/resources/js/uploadcontent.js?v=1'/>"></script>
<script src="<spring:url value='/resources/js/youtubeplayer.js?v=1'/>"></script>
<script>
	var linkId=0;
	var currentObject;
	var sectionIdforTest=0;
	 $(function () {
   	  $(".treeview").removeClass("active");
	      $("#course").addClass("active");
	      $("#course .treeview-menu > #course").addClass("active");
	      //getSuggestionContentList();
	 });
	 	 
	 jQuery(document).ready(function ($) {
		 var that = this;
/**
 * 
 * @summary  This is used for when any click event is fired on element by user which has  iframe-btn class and opens a iframe inside a fancybox through which user can select media file as content. 
 *
 * @return no.
 */
	      $('.iframe-btn').fancybox( {
				  'width'	: 880,
				  'height'	: 570,
				  'type'	: 'iframe',
				  'autoScale'   : false,
				  beforeLoad:function(){
					  this.href ='<%=ConstantUtil.SERVER_IP + ConstantUtil.JAVA_BRIDGE_PATH%>'
												linkId = this.element.context.id;
											}

										});

						//
						// Handles message from ResponsiveFilemanager
						//
						fn_OnMessage = function fn_OnMessage(path) {
							$("#overlay").show();
							saveSectionData(path, linkId);

							//alert(e);
							//saveSectionData(path, sectionId);
						}

					});
	/**
	 * @summary This is used for open a popup for selecting test from drafted and published test list.
	 * @param sectionId This is only parameter which describes for which section of course, user will select a test from test list.
	 * @return no.
	 */
	var attemptIdForTest = null;
	var sessionIdForTest=null;
	function testPopup(sectionId, attemptId,sessionId) {
		/**
		 * showing loader for taking time in loading test list
		 */
		
		$("#overlay").show();
		/**
		 * putting sectionid in global variable beacause it can be accessed without passing in any function as argument.  
		 */
		sectionIdforTest = sectionId;
		attemptIdForTest = attemptId;function getDoubtSectionData(sessionId){
			var sessionId=sessionId;
			debugger;
			$.ajax({
				url:"getDoubtDetails?sessionId="+sessionId,
			//	url: 'getDoubtDetails',
				type:"GET",
				data:sessionId,
				
				success:function(response){
					if(response.code==200 && response.result != null)
						location.href
					
					else
						alert("fail");
					
				}
			});
		}
		if(sessionId<=0 ||sessionId==null)
			{
			sessionId=0;
			}
		
		sessionIdForTest =sessionId;
		/**
		 * getting course id
		 */
		var courseId = $("#courseid").val();
		/**
		 * adding a link for adding new test based on course id, section id.
		 */
		$("#addnewtestbutton").attr(
				'onclick',
				"location.href='addEditTest?courseId=" + courseId
						+ "&sectionId=" + sectionId + "'");
		/**
		 * call function for getting list of all test.
		 */
		getTestList();
	}
	/**
	 * @summary This is used for getting test list using ajax from where user can choose any test.
	 * 
	 * @return no.
	 */
	function getTestList() {
		/**
		 * calling ajax function for getting test list dynamically
		 */
		$.ajax({
			type : 'GET',
			url : 'gettestlistajax',
			success : function(testlist) {
				/**
				 * after successful getting data as string , parsing it into a json.
				 */
				var data = JSON.parse(testlist);
				/**
				 * calling function for extracting test details from test list json and adding in table.
				 */
				extractTestData(data);
			}
		});
	}
	/**
	 * @summary This is used for extracting test details from test list json and adding in table.
	 * @param testData This is only parameter which has details about all tests.
	 * @return no.
	 */
	function extractTestData(testData) {
		/**
		 * This is a empty object which would be used adding  html data as row inside table body. 
		 */
		var row = '';
		/**
		 * before adding any data in table body, make it empty for no duplicate data if operation performs again.
		 */
		$("#tbody").empty();
		/**
		 * a lopping on test list data for extract test details for a particular test.
		 */
		 
		for (var i = 0; i < testData.length; i++) {
			/**
			 * making an argument what would be passed inside a function for selecting a test.
			 */
			var argument = "'" + testData[i].testName + "','"
					+ testData[i].testId + "'";
			/**
			 * assign test is published or drafted based on test published status, if test is published then its published status would be 1.
			 */
			var testStatus = (testData[i].testPublishStatus == 1 ? "Published"
					: "Drafted");
			/**
			 * assing a html element for adding a row in table body.
			 */
			 if(testStatus!="Drafted"){
				 row = row
					+ '<tr><td>'
					+ testData[i].testCreatedDate
					+ '</td>'
					+ '<td>'
					+ testData[i].testName
					+ '</td>'
					+ '<td>'
					+ testStatus
					+ '</td>'
					+ '<td><a class="btn btn-success btn-flat" onclick="saveTestContent('
					+ argument + ')">'+messages['lbl.selectassessment']+'</a></td></tr>';				 
			 }			
		}
		/**
		 * append a table row inside table body.
		 */
		$("#tbody").append(row);
		/**
		 * initialize a table into data table
		 */
		var table = $("#testTable").dataTable({
			"filter" : false,
			"destroy" : true,
			"language": datatablelanguagejson,
			'aaSorting': []
		});
		/**
		 * hide the loader
		 */
		$("#overlay").hide();
		/**
		 * after loading all data into table , show pop up for choosing a test.
		 */
		$("#testtablePopup").modal('show');
	}
	/**
	 * @summary This is used for saving test details of choosed test by user inside a section's content.
	 * @param testName This is first parameter which describes the choosed test name.
	 * @param testId   This is second parameter which describes the choosed test id.
	 * @return no.
	 */
	function saveTestContent(testName, testId) {
		/**
		 * hide test table popup if it is showing.
		 */
		$("#testtablePopup").modal('hide');
		/**
		 * show loader for taking time.
		 */
		$("#overlay").show();
		/**
		 * calling ajax function for saving choosed test details 
		 */
		var data = "testId=" + testId + "&testName=" + testName + "&sectionId="
		+ sectionIdforTest+"&sessionId="+sessionIdForTest+"&attemptId="+attemptIdForTest;
		console.log("=============="+data);
		if(attemptIdForTest != null && attemptIdForTest > 0) {
			data = data + "&attemptId=" + attemptIdForTest;
		}
		$.ajax({
			type : 'POST',
			url : 'savecoursesectionmaterial?',
			data : data,
			success : function(data) {
				/**
				 * after successfully executed,it has retruned data about content details for section.
				 */
				var str = data.split("####");
				/**
				 * assign content type id (This is video, test, image e.t.c).
				 */
				var contentTypeId = str[0].trim();
				/**
				 * if content type id is greater than 1 than file is supported otherwise not.
				 */
				if (contentTypeId > 0) {
					/**
					 * calling function for adding section content details inside page.
					 */
					location.href="addEditCourseMaterial?courseId="+$("#courseid").val();
					//addSectionContent(data);
					/**
					 * hide the loader.
					 */
					$("#overlay").hide();
				} else {
					/**
					 * hide the loader.
					 */
					$("#overlay").hide();
					/**
					 * show pop up when choosing file type is not supported.
					 */
					$("#fileType").modal('show');
				}
			}

		});
	}

	$(function() {
		$(".sortable").sortable({
			axis : "y",
			cursor : "move", //Defines the cursor that is being shown while sorting.
			distance : 5, //Tolerance, in pixels, for when sorting should start. If specified, sorting will not start until after mouse is dragged beyond distance. Can be used to allow for clicks on elements within a handle.
			//containment: "parent", // Defines a bounding box that the sortable items are constrained to while dragging.
			opacity : 0.5, //Defines the opacity of the helper while sorting. From 0.01 to 1.
			revert : true, //Whether the sortable items should revert to their new positions using a smooth animation.
			deactivate : function(event, ui) {
				$(".contentItem").css("zIndex", ''); /*remove z-index from class.*/
			},
			update : function(event, ui) {
				updateContentOrder($(this).sortable("toArray"));
			}
		});
		$(".sortable").disableSelection();
	});
	
	var contentlibrarypage = function(sectionid,attemptid,sessionid){
		$("#overlay").show();
		$("#btn"+sectionid).attr("onclick","");
		$("#librarydiv").html("");
		$("#librarydiv").load("opencontentlibrary?sectionId="+sectionid+"&attemptid="+attemptid+"&sessionid="+sessionid,function(response,status,xhr){
		$("#add-content-from-external-for-empty-lib").attr('onclick','goFromLibraryToAddContent('+sectionid+')');
		$("#overlay").hide();
		});
		}
	
	var goFromLibraryToAddContent = function(secId){
		closeModal(secId);
		setTimeout(function(){$("#bulkmodal").modal('show');}, 1000);
		$("#sectionidmodal").val(id);
		
	}

	
	//This is for saving contents to the Section
	var openbulkmodal = function(id){
		$("#sectionidmodal").val(id);
		$("#bulkmodal").modal('show');		
	}
	
	var renametitle = function(ids){
		var str = ids.split("##");
		$("#sectionidtitle").val(str[0]);
		$("#contentidtitle").val(str[1]);
		$("#title_new").css({"border-color" : ""});	
		$("#title_Div").next('span').remove();
		var contentName = $.trim($("#contentName"+str[1]).text());
		$("#title_new").val(contentName);
		$("#titlemodal").modal('show');
	}
	
	var saverenametitle = function(){
		
	    if($("#title_new").val() == "")
	    {
	    	$('#title_Div').after("<span class='text-red'>"+messages['msg.titleismandatory']+"</span>"); 
	    	$('#title_new').css({"border-color" : "red","border-style":"solid","border-width":"1px"});
	    }
	    else
	    {
	    	if($("#title_new").val().length<=200){
	    		$('#overlay').show();   
		    	var data = JSON.stringify({'contentName': $("#title_new").val(),
		    			    'contentId': $("#contentidtitle").val(),
		    			    'sectionId': $("#sectionidtitle").val()
		    	           });
		    	
		        	$.ajax({
		       		   url:"saverenametitle",
		       		   type:'POST',
		       		   dataType : 'json',
		    		   contentType : "application/json",
		       		   data : data,
		       		   error : function(){
		       			alert("error");
		       		   },
		       		   success : function(status){
		       			$('#overlay').hide();
		       			$("#titlemodal").modal('hide');	       			
		       			location.href="addEditCourseMaterial?courseId="+$("#courseid").val();
		       		   }
		       		});
	    		
	    	}else{
	    		$('#title_Div').after("<span class='text-red'>"+messages['msg.maxcharacterlength'].replace('#maxlength',200)+"</span>"); 
		    	$('#title_new').css({"border-color" : "red","border-style":"solid","border-width":"1px"});	    		
	    	}
	    
	        
	    }
	}	
	
	var closeModal = function(secId){
		$("#btn"+secId).attr("onclick","contentlibrarypage("+secId+")");
	}
	
	$(document).ready(function() {
	    var element = $('.fixed-content'),
	        originalY = element.offset().top;
	    
	    // Space between element and top of screen (when scrolling)
	    var topMargin = 20;
	    
	    // Should probably be set in CSS; but here just for emphasis
	    element.css('position', 'relative');
	    
	    $(window).on('scroll', function(event) {
	        var scrollTop = $(window).scrollTop();
	        
	        element.stop(false, false).animate({
	            top: scrollTop < originalY
	                    ? 0
	                    : scrollTop - originalY + topMargin
	        }, 0);
	    });
	});
</script>
<script>
$(document).ready(function(){
	 $.ajax({
		type:'GET',
		url:'getcheckbox',
		success: function(data){
			
			 $.each(data, function (index, value) {
					       
			        if(value.isLive==1)				   
				        $("#isLive"+value.sessonId).attr('checked',true);
			        else if(value.isLive==0)        
				        $("#isLive"+value.sessonId).attr('checked',false);			
			        if(value.isEnable==1)
				        $("#isEnable"+value.sessonId).attr('checked',true);
			        else if(value.isEnable==0)
				        $("#isEnable"+value.sessonId).attr('checked',false);
			        if(value.isFree==1)
				        $("#isFree"+value.sessonId).attr('checked',true);
			        else if(value.isFree==0)
				        $("#isFree"+value.sessonId).attr('checked',false);
			    });
		}
		}); 
	});	
</script>
</html>