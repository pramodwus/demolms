<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="resources/css/custom.css">
<%@ include file="include.jsp"%>
<style>
.nav-tabs-custom>.nav-tabs>li.active {
	border-bottom-color: #00B06C;
}

.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-right: 0px;
}

.nav-tabs-custom>.nav-tabs>li.active {
    border-top-color: transparent;
}

.nav-tabs-custom>.tab-content {
	padding: 0px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 0px;
}

.section {
	height: 35%;
	width: 100%;
	background-color: white;
	margin-top: 20px;
	margin-bottom: 50px;
	border: 1px solid #dedede;
}

.nohover {
	background-color: #f1f1f1 !important;
	color: black !important;
}

.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td,
	.table>tbody>tr>td, .table>tfoot>tr>td {
	border-top: 0px solid #f4f4f4 !important;
}

.content-wrapper {
	margin: auto;
	margin-left:230px;
}

.select-email-list {
    background-color: #00A65A;
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



.publish-share-alert-msg {
  padding: 8px 35px 8px 14px;
  margin-bottom: 20px;
  text-shadow: 0 1px 0 rgba(255,255,255,0.5);
  background-color: #fff7f7;
  border:1px solid #c72e2e;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
}

</style>

</head>

<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<!-- Show lazy loader image -->
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<div class="col-sm-12">
			<!-- start dataTable----->
			<div class="content-wrapper">
					<form action="saveTestStatus" method="post" id="questionForm"
						name="questionForm">
						<input type="hidden" id="testId" name="testId" value="${test.testId}"> 
						<input type="hidden" id="testStatus" name="testStatus">
                        <input type="hidden" name="courseId" value="<%=request.getParameter("courseId")%>">
						<div id="testReview">
							<section class="content-header">
								<div style="background-color: #FFF" class="nav-tabs-custom">
									<ul class="nav nav-tabs">
										<li onclick="testInfoTabFromPreviewTab()"
											style="border-left: 1px solid #dedede; cursor: pointer; width: 33.32%;"><a
											style="text-align: center;"> <i
												class="fa fa-check-circle-o color-green" id="fa"></i>
												&nbsp;&nbsp;<spring:message code="lbl.assessmentinformation"/>
										</a></li>
										<li onclick="addQuestionTabFromPreviewTab()"
											style="border-left: 1px solid #dedede; cursor: pointer; width: 33.32%;"><a
											style="text-align: center;"> <i
												class="fa fa-check-circle-o color-green" id="fa"></i>
												&nbsp;&nbsp;<spring:message code="lbl.addquestions"/>
										</a></li>
										<li class="active"
											style="border-left: 1px solid #dedede; width: 33.32%;">
											<a href="#" style="text-align: center;"> <i
												class="fa fa-circle-thin" id="fa"></i>&nbsp;&nbsp;
												<spring:message code="lbl.saveandpublish"/>
										</a>
										</li>
									</ul>
								</div>
							</section>

							<!----------------------------------------------Header link end ---------------------------------------->

							<div class="col-sm-12">

								<h3 style="text-align: center;">
									<img src="resources/adminlte/dist/img/eye.png" />
								</h3>
								<h3 style="text-align: center;"><spring:message code="header.testreview"/></h3>
							</div>

							<!----------------------------------------------Form start ---------------------------------------->


							<div class="col-sm-12">
								<div class="col-sm-12">
									<h5 class="form-group">
										<label><spring:message code="lbl.nameofassessment"/>:&nbsp;</label><span id="testNameInPreview">${test.testName}</span>
										<input type="hidden" name="testName" value="${test.testName}">
										<input type="hidden" name="schedulePublishDate" value="${test.schedulePublishDate}">										
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.assessmenttype"/>:&nbsp;</label><span id="testTypeInPreview">
										<c:choose>
										<c:when test="${test.isPublic == 0}"><spring:message code="lbl.public"/></c:when>
										<c:otherwise><spring:message code="lbl.private"/></c:otherwise>
										</c:choose>
										</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.tags"/>:&nbsp;</label><span id="testTagsInPreview">${test.testTag}</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.assessmenttime"/>:&nbsp;</label><span id="testTimePreview">
										<c:choose>
										<c:when test="${test.testTime == null}"><spring:message code="lbl.nolimit"/></c:when>
										<c:otherwise>${test.timeMinute} <spring:message code="lbl.minute"/></c:otherwise>
										</c:choose>
										</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.totalmarks"/>:&nbsp;</label><span id="totalMarkInPreview">${test.maxMark}</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.numberofattempts"/>:&nbsp;</label><span
											id="testAttemptsInPreview">
										<c:choose>
										<c:when test="${test.maxAttempts == 0}"><spring:message code="lbl.unlimited"/></c:when>
										<c:otherwise>${test.maxAttempts}</c:otherwise>
										</c:choose>
											</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.negativemarks"/>:&nbsp;</label><span
											id="negMarkInPreview">${test.negMark} %</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.pausepossible"/>:&nbsp;</label><span
											id="isPauseInPreview">
										<c:choose>
										<c:when test="${test.testPause == 0}"><spring:message code="lbl.no"/></c:when>
										<c:otherwise><spring:message code="lbl.yes"/></c:otherwise>
										</c:choose>
											</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.shufflesections"/>:&nbsp;</label><span
											id="shuffleSectionInPreview">
										<c:choose>
										<c:when test="${test.shuffleSection == 0}"><spring:message code="lbl.no"/></c:when>
										<c:otherwise><spring:message code="lbl.yes"/></c:otherwise>
										</c:choose>
											</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.shufflequestions"/>:&nbsp;</label><span
											id="shuffleQuestionInPreview">
										<c:choose>
										<c:when test="${test.shuffleQuestion == 0}"><spring:message code="lbl.no"/></c:when>
										<c:otherwise><spring:message code="lbl.yes"/></c:otherwise>
										</c:choose>
										</span>
									</h5>
									<h5 class="form-group hide">
										<label><spring:message code="lbl.shuffleoptions"/>:&nbsp;</label><span
											id="shuffleOptionInPreview">
										<c:choose>
										<c:when test="${test.shuffleOption == 0}"><spring:message code="lbl.no"/></c:when>
										<c:otherwise><spring:message code="lbl.yes"/></c:otherwise>
										</c:choose>
											</span>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.reviewassessmentafterfinished"/>:&nbsp;</label><span
											id="isReviewInPreview">
										<c:choose>
										<c:when test="${test.isReview == 1}">
										     <c:choose>
										     <c:when test="${test.reviewWithCorrect != 0}"><spring:message code="lbl.no"/> (<spring:message code="lbl.withcorrectanswer"/>)</c:when>
										     <c:otherwise><spring:message code="lbl.yes"/> (<spring:message code="lbl.withoutcorrectanswer"/>)</c:otherwise>
										     </c:choose>
										</c:when>
										<c:otherwise><spring:message code="lbl.no"/></c:otherwise>
										</c:choose>
											</span>
									</h5>
									<h5 class="form-group">
										<label>Is Random:&nbsp;</label>
										<span id="isRandomQuiz">
											<c:choose>
												<c:when test="${test.isRandom != null && test.isRandom == 1}">
												     <spring:message code="lbl.yes"/>
												</c:when>
												<c:otherwise>
												  <spring:message code="lbl.no"/>
												</c:otherwise>
											</c:choose>
										</span>
									</h5>
								   <c:if test="${test.isRandom != null && test.isRandom == 1}">
											<h5 class="form-group">
												<label><spring:message code="lbl.maxquestions"/>:&nbsp;</label>
												<span id="maxQuestions">
													${test.maxQuestions}
												</span>
											</h5>
                                   </c:if>
									
							<!-- Start Test Tags -->
							  <c:forEach items="${testTags}" var="config">
			                           <h5 class="form-group">
			                                 <label>${config.name}:</label>
									         <span>
									            <c:choose>
									              <c:when test = "${config.type == 'select'}">
										                <c:forEach items="${config.configList}" var="configMapping">
										                     <c:set var="isMatched" value = "false"/>
										                     <c:forEach items="${test.tagList}" var="tag">
										                            <c:if test="${isMatched == false && config.id == tag.id && configMapping.id == tag.value}">
										                                 ${configMapping.value}
										                                 <c:set var="isMatched" value = "true"/>
										                            </c:if>
										                     </c:forEach>
										                </c:forEach>
									               </c:when>
									               <c:otherwise>
									                 <c:set var="isMatched" value = "false"/>
								                     <c:forEach items="${test.tagList}" var="tag">
								                            <c:if test="${isMatched == false && config.id == tag.id}">
								                                 ${tag.value}
								                                 <c:set var="isMatched" value = "true"/>
								                            </c:if>
								                     </c:forEach>
									               </c:otherwise>
									            </c:choose>  
									         </span>
			                           </h5>
	                           </c:forEach>
						   <!-- End Test Tags -->
									
									<h5 class="form-group">
										<label><spring:message code="lbl.immediatepublish"/>:&nbsp;</label><span>
										<c:choose>
										<c:when test="${test.isSchedule == 1}">
                                              <spring:message code="lbl.no"/> ( <spring:message code="lbl.publishdateis"/> <b>${test.schedulePublishDate}</b> )
										</c:when>
										<c:otherwise><spring:message code="lbl.yes"/></c:otherwise>
										</c:choose>
											</span>
											<a class="cursor-pointer" onclick="testInfoTabFromPreviewTab()"><spring:message code="lbl.edit"/></a>
									</h5>
									<h5 class="form-group">
										<label><spring:message code="lbl.description"/>:</label>
									</h5>
									<h5 class="form-group" id="testDescInPreview">${test.testDesc}</h5>
									<br>
									<h5 class="form-group">
										<label><spring:message code="lbl.instructions"/>:</label>
									</h5>
									<h5 class="form-group" id="testInstructInPreview">${test.testInstruct}</h5>
									<br>
									<br>
								</div>
                        
								<div class="row" id="dedecolor">
									<div class="col-sm-12">
									<c:choose>
									  <c:when test="${test.isRandom != null && test.isRandom == 1}">
										<div class="publish-share-alert-msg">
										        <p class="text-danger form-group">
													<b><spring:message code="lbl.note"/>:- </b><spring:message code="msg.questionheaderforrandomquiz"/>
											    </p>
										</div>
									  </c:when>
									  <c:otherwise>
										<div style="background-color: #ECF0F5;" class="nav-tabs-custom">
											<ul class="nav nav-tabs" style="border-bottom: 1px solid #dedede;">
												<li class="active" style="border-bottom-color: #7d8181;">
												   <a style="background-color: #ECF0F5;" data-toggle="tab">
												     <label><spring:message code="lbl.assessmentdetail"/></label>
												   </a>
												</li>
											</ul>
										</div>
										<c:set var="isErrorforPublish" value="false"></c:set>
                                        <c:forEach items="${test.sectionlist}" var="section">
                                        <c:if test="${fn:length(section.questionList )< 2}">
                                        <c:set var="isErrorforPublish" value="true"></c:set>
                                        </c:if>
										<div class="section">
										<div class="input-group">
										<div class="section_name_div">${section.sectionName}</div>
										
										<div class="input-group-addon">
																	<div class="pull-right" style="">
																		<div class="dropdown">
																			<a id="dLabel" data-target="#" data-toggle="dropdown"
																				role="button" aria-haspopup="true"
																				aria-expanded="false" class="icon-dropdown"><img
																				src="resources/adminlte/dist/img/ellipsis-h.png"></a>
																			<ul class="dropdown-menu dropdown-menu-right"
																				aria-labelledby="dlabel">
																				<li><a class="cursor-pointer sectionDelete"
																					id="sectionDelete${section.sectionId}">
																					<spring:message code="lbl.delete"/>
																					</a></li>
																			</ul>
																		</div>
																	</div>
																</div>
																</div>
											<div class="row secbody" style="margin-top: 20px"
												id="testDetailsInReview">
												<div class="col-sm-12">
													<div class="col-sm-12" id="testDetailsDataTablesDiv">
													<c:if test="${fn:length(section.questionList)>0}">
														<table class="table sectionListTables" id="testDetailsDataTables">
															<thead class="hide">
																<tr>
																	<th style="width: 8%"><spring:message code="lbl.questionnumber"/></th>
																	<th><spring:message code="lbl.questiontitle"/></th>
																	<th><spring:message code="lbl.marks"/></th>
																</tr>
															</thead>
															<tbody id="testDetailsTableBody">
															<c:set var="questionNumber" value="1"></c:set>
															<c:forEach items="${section.questionList}" var="question">
															<tr>
		                                                            <td style="width:8%"><spring:message code="lbl.questionnumber"/> ${questionNumber}</td>
			                                                        <td class="imgset"><div class="pull-left">${question.questionName}</div></td>
			                                                        <td style="width:15%"><button type="button" class="btn nohover btn-flat btn-default" style="cursor:default" disabled>${question.questionMark}</button>&nbsp;&nbsp;&nbsp;<spring:message code="lbl.marks"/></td>
		                                                     </tr>
		                                                      <c:set var="questionNumber" value="${questionNumber+1}"/>
		                                                     </c:forEach>
															</tbody>
														</table>
														</c:if>
													</div>
												</div>
											</div>
										</div>
										</c:forEach>
										<div class="publish-share-alert-msg">
									        <p class="text-danger form-group">
											<b><spring:message code="lbl.note"/>:- </b><spring:message code="msg.minquestionrequiredforpublishassessment"/>
											<br/>
											<c:if test="${isErrorforPublish eq true}">
											<b><spring:message code="msg.errormessage"/>:-</b> <spring:message code="msg.minquestionrequiredforpublishassessment.error"/>
										    </c:if>
										    </p>
										</div>
									  </c:otherwise>
									  </c:choose>
										<h3 style="text-align: center;">
											<button type="button" class="btn btn-flat btn-success button-width-large" id="save" onclick="saveAllDetail(this);">
											  <spring:message code="lbl.save"/>
											</button>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:choose>
												<c:when test="${isErrorforPublish eq true}">
													<button type="button" class="btn btn-flat btn-success button-width-large" disabled>
															<spring:message code="lbl.publish"/>
													</button>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												    <button type="button" class="btn btn-flat btn-success button-width-large" disabled>
														<spring:message code="lbl.share"/>
													</button>
												</c:when>
												<c:otherwise>
												  <button type="button" class="btn btn-flat btn-success button-width-large" id="publish" onclick="saveAllDetail(this);">
													  <spring:message code="lbl.publish" text="Publish"/>
											      </button>
												  <%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												  <button type="button" id="share" class="btn btn-flat btn-success" onclick="saveAllDetail(this);">
														<spring:message code="lbl.sharewithusersgroups" text="Publish & share with users/groups"/>
												  </button> --%>
											   </c:otherwise>
											</c:choose>
										</h3>
									</div>
								</div>
							</div>

						</div>
					</form>
			</div>
			<!-- content-wrapper -->
		</div>
	</div>
	<!-- ./wrapper -->
	<!-- Start of Alert box for add test -->
<div class="modal fade" id="testAlert" tabindex="-1" role="dialog"
		aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal"><spring:message code="lbl.ok"/></button>
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
							data-dismiss="modal"><spring:message code="lbl.no"/></button>
						<button type="button" id="dId"
							class="btn btn-success button-width"><spring:message code="lbl.yes"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- pop up for course publish -->	
<div class="modal fade" id="testPublishPopUp" role="dialog"
		aria-labelledby="testPublishPopUp">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="text-align: center">
				<div class="modal-body row page-background-color">
						<h1 style="font-size: large">
							<strong><spring:message code="lbl.publishit"/></strong>
						</h1>
						<h6><spring:message code="msg.beforeassessmentpublish"/></h6>
						<button type="button" onclick="publishTest();"
											class="btn btn-success btn-flat button-width"
											data-dismiss="modal"><spring:message code="lbl.publish"/></button>
		                <button type="button"
											class="btn btn-default btn-flat button-width"
											data-dismiss="modal" aria-label="Close"><spring:message code="lbl.later"/></button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- pop up for share the test -->
	<div class="modal fade"
		id="success" role="dialog" aria-labelledby="success">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content page-background-color">
					<div class="modal-header">
						<h4 class="modal-title" style="text-align: center"><spring:message code="lbl.shareyourassessment"/></h4>
						<h5 style="text-align: center"><spring:message code="msg.beforeassessmentpublish"/></h5>
					</div>
					<div class="modal-body">
					   <div class="col-sm-12 form-group">
						<input type="radio" id="alluser" name="searchFor" class="searchFor flat-red" onclick="getUsers()" value="allUser" checked> &nbsp;<spring:message code="lbl.allusers"/>&nbsp;&nbsp;
							<input type="radio" id="groups" name="searchFor" class="searchFor flat-red" onclick="getGroups()" value="group"> &nbsp;<spring:message code="lbl.groups"/>&nbsp;
						</div>
						<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="col-sm-6">
							<div id="users1">
								<table class="table" id="sharedTestTable">
									<thead>
										<tr style="background-color: #00a65a;color:white;">
											<th></th>
											<th><spring:message code="lbl.emailid"/></th>
										</tr>
									</thead>
									<tbody id="sharedEmailList">
									</tbody>
								</table>
								</div>
								<div id="grouptable" style="display: none;">
									<table class="table" id="sharedGroupTable" >
										<thead>
											<tr style="background-color: #00a65a;color:white;">
												<th></th>
												<th><spring:message code="lbl.groupname"/></th>
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
									<label><spring:message code="lbl.addattendees"/>: </label>
								</div>
								
								<div class="form-group form-control" style="height:auto">
								<div class="col-sm-1" style="padding-left: 0px;"><b><spring:message code="lbl.to"/>:</b></div>
								<div style="width:90%;min-height:100px;">
								<ul style="list-style: none;" class="list-inline" id="emaillistgroup">
								</ul>
								<div class="form-group"></div>
								</div>
								</div>
								
								<div class="form-group">
								<label><spring:message code="lbl.message"/> :</label>
								<textarea class="textAreaControl form-control" id="shareTestMessage" name="shareTestMessage" maxlength="512" onkeyup="emailKeyUp()"></textarea>
								<label class="requireFld" id="shareTestMessageError"><spring:message code="msg.empty"/></label>
								</div>
								
								
								<div class="form-inline">
									<div class="form-group">
										<input type="text" placeholder="<spring:message code="lbl.emailid"/>" id="sharedEmail"
											class="form-control" onkeyup="emailKeyUp()">
									</div>
									<div class="form-group">
										<button type="button"
											class="btn btn-success btn-flat"
											onclick="addSharedList();"><spring:message code="lbl.add"/></button>
									</div>
								</div>
								<div class="">
									<label class="requireFld" id="sharedEmailError1"><spring:message code="msg.empty"/></label>
									<label class="requireFld" id="sharedEmailError2"><spring:message code="msg.email.invalid"/></label>
									<label class="requireFld" id="sharedEmailError3"><spring:message code="msg.emailchoosealready"/></label>
									<label class="requireFld" id="sharedEmailError4"><spring:message code="msg.emailcannotchooseforassessment"/></label>
								</div>
							</div>
						</div>
						</div>
					</div>
					<!-- /. modal-body -->
					<div class="modal-footer">
						<button type="button"
							class="btn btn-default btn-flat button-width-large"
							data-dismiss="modal"><spring:message code="lbl.close"/></button>
						<button type="button" id="sharedTestButton"
							class="btn btn-success btn-flat button-width-large" onclick="shared();" disabled><spring:message code="lbl.share"/></button>
					</div>
				</div>
			</div>
	</div>
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['msg.shared.error'] = "<spring:message code='msg.shared.error' javaScriptEscape='true' />";
        messages['msg.assessmentscheduledateexpire'] = "<spring:message code='msg.assessmentscheduledateexpire' javaScriptEscape='true' />";
        messages['msg.deleteerror'] = "<spring:message code='msg.deleteerror' javaScriptEscape='true' />";
        messages['msg.deletesection'] = "<spring:message code='msg.deletesection' javaScriptEscape='true' />";
        messages['lbl.edit'] = "<spring:message code='lbl.edit' javaScriptEscape='true' />";
        messages['lbl.save'] = "<spring:message code='lbl.save' javaScriptEscape='true' />";
</script>        
<script>

var courseId = <%=request.getParameter("courseId")%>;
var courseSectionId = <%=request.getParameter("sectionId")%>;
var contentId = <%=request.getParameter("contentId")%>; // would be if test as content of course is in edit mode.
var isSchedule = '${test.isSchedule}';
var testId = '${test.testId}';
/**
 * array of emails of all trainer.
 */
var trainerList = [];
/**
 * array of emails for sharing the course.
 */
var emailList=[];
/**
 * Instance of data table.
 */
 var map = {};
 /**
  * array of Groups for sharing the course.
  */
var groupList=[];
var sharedTestTable;
var sharedGroupTable;
$(document).ready(function(){
	$(".treeview").removeClass("active");
    $("#test").addClass("active");
    $("#test .treeview-menu > #test").addClass("active");
	$('.imgset img').css({'display': 'block',
	    'width': '100%',
		'max-width' :'100%',
		'height' : 'auto' });
	$(".sectionListTables").dataTable({'aaSorting': [],'language': datatablelanguagejson});
	  $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
          checkboxClass: 'icheckbox_flat-green'
        });
        $('input[type="radio"].flat-red').iCheck({
	          radioClass: 'iradio_flat-green'
	        });
});
</script>
<script src="resources/js/test/author/build/testreview.js"></script>
</html>