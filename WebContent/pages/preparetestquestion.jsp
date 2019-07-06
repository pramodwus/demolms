<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="resources/css/custom.css">
<%@ include file="include.jsp"%>
<style>
p {
	word-break: break-all;
}

.cke_textarea_inline {
	border: 1px solid #ccc;
	padding: 10px;
	background-color: white;
	min-height: 50px;
}

.ui-tooltip {
	display: none;
}

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

.nav-tabs-custom>.nav-tabs>li.active {
	border-bottom-color: #00B06C;
}

.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-right: 0px;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-top-color: #fff;
}

.nav-tabs-custom>.tab-content {
	padding: 0px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 0px;
}

.badge {
	background-color: #05B26F;
}

.button-margin {
	margin-left: 22px;
}

.button-label-margin {
	margin-left: -13%;
	margin-right: 10px;
}

.section {
	height: 35%;
	width: 100%;
	background-color: white;
	margin-top: 20px;
	margin-bottom: 50px;
	border: 1px solid #dedede;
}

.spanVisible {
	visibility: hidden
}

.box {
	background: none;
	box-shadow: 0 0px 0px rgba(0, 0, 0, 0);
}

.input-group .input-group-addon {
	background-color: transparent;
}

.text_editor_margin {
	margin-top: 5px;
	margin-bottom: 5px;
}

.well2 {
	min-height: 20px;
	padding: 10px;
	margin-bottom: 20px;
	background-color: #f1f1f1;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	margin-right: 25px;
	margin-left: 25px;
}

.nohover {
	background-color: #f1f1f1 !important;
	color: black !important;
}

.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

@media only screen and (min-width: 767px) {
	.content-wrapper {
		/* background-color: yellow; */
		margin-left: 230px;
	}
}

@media only screen and (max-width: 760px) {
	.content-wrapper {
		/*  background-color: pink; */
		margin-left: 0px;
	}
}

.cke_textarea_inline img {
	max-width: 100% !important;
	height: auto !important;
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

#choiceMatrixOptionTable td, #choiceMatrixOptionTable th {
	border: 1px solid #dddddd !important;
}

#classificationOptionTable td {
	border: 2px dashed #dddddd !important;
}

#classificationOptionTable th {
	border-bottom: 2px dashed #dddddd !important;
}

#classificationOptionTable tbody>tr>td {
	height: 50px;
	min-height: 50px !important;
}

#classificationOptionTable td:nth-child(1), #classificationOptionTable th:nth-child(1)
	{
	border: 0px solid black !important;
}

.answerDivInClassificationTypeQuestion,
	.answerDivInMatchListTypeQuestion {
	padding: 10px;
	margin: 10px;
	min-width: 50px;
	max-width: 200px;
	border: 1px solid #ccc;
	background-color: white;
	margin-bottom: 0px;
	float: left;
}

.answerDivInClassificationTypeQuestion img,
	.answerDivInMatchListTypeQuestion img {
	max-width: 100% !important;
	height: auto !important;
}

/* CSS for match list type question  */
.match_list_type_ques_arrows {
	padding: 0 2px;
	min-width: 2.5em;
}

.match_list_type_ques_col1 {
	display: table-cell;
	vertical-align: middle;
}

.match_list_type_ques_arrows .match_list_type_ques_arrow {
	border-bottom: 0.36em solid #696969;
	width: 100%;
	position: relative;
}

.match_list_type_ques_arrow:before, .match_list_type_ques_arrow:after {
	position: absolute;
	content: ' ';
	height: 0;
	width: 0;
	top: -0.15em;
	border: 0.4em solid #696969;
	border-radius: 50%;
}

.match_list_type_ques_arrow:after {
	right: -0.1em;
}

.match_list_type_ques_arrow:before {
	left: -0.1em;
}

.match_list_assoc_table {
	display: table;
	border-spacing: 0 1em;
	padding: 0.35em 2em 0 2em;
	width: 100%;
}

.match_list_type_ques_row {
	display: table-row;
}

.match_list_type_ques_col2 {
	display: table-cell;
	vertical-align: middle;
	width: 45%;
}

.match_list_type_question {
	padding: 0.7em 1em;
	background: transparent;
	text-align: center;
	border: 1px solid #e8e8e8;
	min-height: 2.7em;
}

.match_list_type_question img {
	max-width: 100% !important;
	height: auto !important;
}

.match_list_type_question_res_container {
	position: relative;
}

.match_list_type_question_res_container.match_list_type_ques_dropzone {
	border-color: #e8e8e8;
}

.match_list_type_question_res_container {
	border-radius: 2px;
	margin-top: -0.35714286em;
}

.match_list_type_ques_dropzone {
	border: 2px dashed #dddddd;
	min-height: 2em;
	min-width: 2em;
}

.match_list_type_question_res_container .answerDivInMatchListTypeQuestion
	{
	margin: 5px !important;
	max-width: 100% !important;
}

.triangle {
	content: '';
	width: 0;
	width: 0;
	height: 0;
	border-left: 12px solid transparent;
	border-right: 12px solid transparent;
	border-bottom: 12px solid #ccc;
	margin-left: 47%;
}

p{
	word-break: break-word;
}

.question.bg-gray {
    background-color: #ccc;
    cursor: pointer;
}

.question-badge {
    display: inline-block;
    min-width: 10px;
    padding: 3px 7px;
    font-size: 12px;
    font-weight: 700;
    line-height: 1;
    color: #fff;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    background-color: #777;
    border-radius: 10px;
}

/* Tooltip */
.questiontype-tooltip+.tooltip>.tooltip-inner {
	background-color: #1BB16C;
	color: #FFFFFF;
	border: 1px solid #1BB16C;
	padding: 5px;
	max-width: 350px;
}
/* Tooltip on top */
.tooltip.top .tooltip-arrow {
	border-top-color: #1BB16C;
}
/* Tooltip on bottom */
.tooltip.bottom .tooltip-arrow {
	border-bottom-color: #1BB16C;
}
/* Tooltip on left */
.tooltip.left .tooltip-arrow {
	border-left-color: #1BB16C;
}
/* Tooltip on right */
.tooltip.right .tooltip-arrow {
	border-right-color: #1BB16C;
}

.bagde-style {
    padding: 8px 12px 8px 12px;
    border-radius: 15px;
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
				<div id="createQuestion">
					<div id="questionPage" style="">
						<section class="content-header">
							<div style="background-color: #FFF" class="nav-tabs-custom">
								<ul class="nav nav-tabs">
									<li
										style="border-left: 1px solid #dedede; cursor: pointer; width: 33.32%;"
										onclick="testInfoTabFromQuestionTab()"><a
										style="text-align: center;"> <i
											class="fa fa-check-circle-o color-green" id="fa"></i>
											&nbsp;&nbsp;<spring:message code="lbl.assessmentinformation" />
									</a></li>
									<c:choose>
					                    <c:when test="${test.isRandom != null && test.isRandom == 1}">
					                       <li class="active" style="border-left: 1px solid #dedede; width: 33.32%;">
												<a style="text-align: center;"> 
												   <i class="fa fa-check-circle-o color-green" id="fa"></i>&nbsp;&nbsp;<spring:message code="lbl.addquestions" />
												</a>
										   </li>
					                    </c:when>
						                <c:otherwise>
						                   <li class="active" style="border-left: 1px solid #dedede; width: 33.32%;">
												<a style="text-align: center;"> 
												   <i class="fa fa-circle-thin" id="fa"></i> &nbsp;&nbsp;<spring:message code="lbl.addquestions" />
												</a>
										   </li>
						                </c:otherwise>
					                </c:choose>
									<li style="border-left: 1px solid #dedede; width: 33.32%;">
										<a style="text-align: center;"> <i
											class="fa fa-circle-thin" id="fa"></i>&nbsp;&nbsp; <spring:message
												code="lbl.saveandpublish" />
									</a>
									</li>
								</ul>
							</div>
				 <c:choose>
                    <c:when test="${test.isRandom != null && test.isRandom == 1}">
                         <div class="form-group" style="text-align:center;height:200px;padding-top:100px">
                                <h3>No Of Question Matched Based on Tag</h3><h4>${randomQuestionNo}</h4>
                              <h3><spring:message code="msg.questionheaderforrandomquiz" /></h3>
                         </div>
                         <div class="form-group" id="submitbtn" style="text-align: center; margin-top: 50px">
									<button type="button" <c:if test="${randomQuestionNo == 0}">disabled</c:if> class="btn btn-success btn-flat button-width-large" onclick="redirectOnTestPreviewPage();">
										Next
									</button>
						 </div>
                    </c:when>
                    <c:otherwise>			
							<!-- three button div on empty questions. -->
							<div class="form-group" id="defaultSectionDiv">

								<!----------------------------------------------Form start ---------------------------------------->

								<div class="col-sm-12" id="createsectiontab">
									<!----------------------------------------------Header link end ---------------------------------------->
									<div class="row">
										<h3 style="text-align: center;">
											<img src="resources/adminlte/dist/img/coursecontent.png" />
										</h3>
										<h3 style="text-align: center;">
											<spring:message code="header.preparetestquestion" />
										</h3>
										<hr />
									</div>
									<div class="row">
										<div id="totalTestSections">
											<c:forEach items="${sectionlist}" var="section">
												<div class="section">
													<div class="row">
														<div class="col-sm-12" style="padding: 14px">
															<div class="input-group">
																<div class="section_name_div">
																	<p id="sectionNameText${section.sectionId}">${section.sectionName}</p>
																</div>
																<div class="input-group-addon">
																	<div class="pull-right" style="">
																		<div class="dropdown">
																			<a style="color:#BFBFBF" id="dLabel" data-target="#" data-toggle="dropdown"
																				role="button" aria-haspopup="true"
																				aria-expanded="false" class="icon-dropdown">
																				<i class="fa fa-gears"></i></a>
																			<ul class="dropdown-menu dropdown-menu-right"
																				aria-labelledby="dlabel">
																				<li><a
																					onclick="updateSectionName(${section.sectionId});"
																					class="cursor-pointer"><spring:message
																							code="lbl.editname" /></a></li>
																				<li class="divider"></li>
																				<li><a class="cursor-pointer sectionDelete"
																					id="sectionDelete${section.sectionId}"><spring:message
																							code="lbl.delete" /></a></li>
																			</ul>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div id="questionDivOfSection${section.sectionId}">
															<c:forEach items="${section.questionList}" var="question">
																<div class="well1 well-sm sectionQuestion"
																	id="sortable_1">
																	<div class="input-group">
																		<div class="imgset pull-left"
																			id="questionText${section.sectionId}##${question.questionId}">${question.questionName}</div>
																		<div class="input-group-addon">
																			<button type="button"
																				id="questionMark${section.sectionId}##${question.questionId}"
																				class="btn nohover btn-flat btn-default"
																				style="cursor: default" disabled>${question.questionMark}</button>
																			&nbsp;&nbsp;&nbsp;
																			<spring:message code="lbl.marks" />
																		</div>
																		<div class="input-group-addon">
																			<div class="dropdown pull-right">
																				<a id="dLabel" data-target="#"
																					data-toggle="dropdown" role="button"
																					aria-haspopup="true" aria-expanded="false"
																					class="content-dropdown icon-dropdown"> <img
																					src="resources/adminlte/dist/img/ellipsis-v.png"
																					class="shape" /></a>
																				<ul class="dropdown-menu dropdown-menu-right"
																					aria-labelledby=" dlabel">
																					<li><a
																						id="questionEdit${section.sectionId}##${question.questionId}"
																						class="questionEdit cursor-pointer"><spring:message
																								code="lbl.edit" /></a></li>
																					<li class="divider"></li>
																					<li><a
																						id="questionDelete${section.sectionId}##${question.questionId}"
																						class="questionDelete cursor-pointer"><spring:message
																								code="lbl.delete" /></a></li>
																				</ul>
																			</div>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
														<c:if test="${fn:length(section.questionList) >0 }">
															<div class="well2 well-sm">
																<div class="input-group">
																	<div class="input-group-addon imgset pull-left">
																		<b><spring:message code="lbl.totalquestionadded" />
																			:&nbsp;<span
																			id="totalSectionQuestion${section.sectionId}">${fn:length(section.questionList)}</span>
																			<spring:message code="lbl.questions" /> </b>
																	</div>
																	<div class="input-group-addon">
																		<b><spring:message code="lbl.totalmarkscontain" />
																			:&nbsp;<span
																			id="totalSectionMarks${section.sectionId}">${section.sectionScore}</span></b>
																	</div>
																</div>
															</div>
														</c:if>

														<div class="col-sm-12">
															<div class="col-sm-4"></div>
															<div class="col-sm-4 hide" style="border-right: 1px solid #eee;">
																<h3 style="text-align: center;">
																	<img
																		src="resources/adminlte/dist/img/coursecontent.png"
																		onclick="showCreateQuestionPagePopUp('${section.sectionId}');"
																		style="cursor: pointer" />
																</h3>
																<h4 style="text-align: center;">
																	<spring:message code="lbl.createquestion" />
																</h4>
															</div>
															<!-- 	<div class="col-sm-4"
												style="border-left: 1px solid #eee; border-right: 1px solid #eee;">
												<h3 style="text-align: center;">
													<img src="resources/adminlte/dist/img/coursecontent.png" class="importExistTestQues" style="cursor:pointer" />
												</h3>
												<h4 style="text-align: center;">Import Question from <br>Old Test</h4>
											</div> -->
															<div class="col-sm-4">
																<h3 style="text-align: center;">
																	<img onclick="getQuestionBank(${section.sectionId});"
																		src="resources/adminlte/dist/img/coursecontent.png"
																		style="cursor: pointer" />
																</h3>
																<h4 style="text-align: center;">
																	<spring:message code="lbl.addquestionfromquestionbank" />
																</h4>
															</div>
															<div class="col-sm-2"></div>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>

										<div class="col-sm-12 form-group" style="text-align: center;">
											<button type="button"
												class="btn btn-success button-width-large btn-flat"
												onclick="sectionCreatePopup();">
												<spring:message code="lbl.addnewsection" />
											</button>
											&nbsp;&nbsp;&nbsp;
											<button type="button"
												class="btn btn-success button-width-large btn-flat"
												id="importExistSections">
												<spring:message code="lbl.importsection" />
											</button>
										</div>
										<div class="col-sm-12 form-group" id="submitbtn"
											style="text-align: center; margin-top: 50px">
											<button type="button"
												class="btn btn-success btn-flat button-width-large"
												onclick="submitAllData();" id="submitAllData">
												<spring:message code="lbl.submit" />
											</button>
										</div>

									</div>
								</div>
								<!----------------------------------------------question editor tab ---------------------------------------->
								<div id="questionPageDiv"
									style="display: none; background-color: white; border: 1px solid #ccc;">
									<!-- </section> -->
									<section class="content">
										<div class="row">
											<div class="col-xs-12 addQues">
												<div class="box box-solid">
													<div class="box-body" id="addQuestionsBody">
														<div class="col-xs-12 addQues">
															<div class="col-xs-4">
																<ul class="todo-list" id="todoList">
																</ul>
															</div>
															<div class="addQuestions" id="addQuestions">
															<h2><span  id="question-type-header"></span>&nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-type-header-tooltip"><i class="fa fa-question"></i></span></h2>
																<input type="hidden" id="sectionIdForAddedQues">
																<!-- creating new question -->
																<div id="questiondiv" style="margin-top:30px"></div>
																<!-- end. creating new question -->
															</div>
														</div>
													</div>
													<br /> <br />
													<!-- /.box-body-->
													<!--  -->
												</div>
												<!-- /.box box-widget -->
											</div>
											<div class="col-xs-12 formBody" style="text-align: center">
												<button class="btn btn-default btn-flat button-width-large"
													onclick="backonSectionPage();">
													<spring:message code="lbl.cancel" />
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button class="btn btn-flat btn-success button-width-large"
													onclick="saveQuestionInJson();" id="submitQuestion">
													<spring:message code="lbl.save" />
												</button>
											</div>
										</div>
									</section>
								</div>
								<!----------------------------------------------end question editor tab ---------------------------------------->
							</div>
						   </c:otherwise>
                         </c:choose>	
						</section>
						<!-- /.three button div on empty questions. -->
						<!-- question page -->

						<!-- /.question bank -->
					</div>
				</div>
			</div>
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
							data-dismiss="modal">
							<spring:message code="lbl.ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Alert box -->
	<div class="modal fade" id="clearquestionAlert" tabindex="-1"
		role="dialog" aria-labelledby="clearquestionAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p></p>
						<button type="button" class="btn btn-default button-width"
							data-dismiss="modal">
							<spring:message code="lbl.no" />
						</button>
						<button type="button" id="dId"
							class="btn btn-success button-width">
							<spring:message code="lbl.yes" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------choose question type popup-------------------------------------------------------------------------------------->
	<!-- End of Alert box -->
	<div class="modal fade" id="CreateQuestionPagePopUp" tabindex="-1"
		role="dialog" aria-labelledby="clearquestionAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body row page-background-color"
						style="text-align: center">
						<div class="col-sm-12 form-group">
							<h4>
								<spring:message code="lbl.choosequestiontype" />
							</h4>
							<select class="form-control" id="selectedQuestionType">
								<c:forEach items="${quesTypeList}" var="quesTypeList">
									<option value="${quesTypeList.questionType}">${quesTypeList.questionTypeName}</option>
								</c:forEach>
							</select>
						</div>
						<button type="button"
							class="btn btn-default btn-flat button-width-large"
							data-dismiss="modal">
							<spring:message code="lbl.close" />
						</button>
						<button type="button" id="createQuestionType"
							class="btn btn-success button-width-large btn-flat"
							data-dismiss="modal">
							<spring:message code="lbl.createquestion" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-----------------------------------------------end of choose question type popup--------------------------------------------------------------------->

	<!-- pop up for showing question from question Bank -->

	<div class="modal fade" id="questionBankPopup" role="dialog"
		aria-labelledby="questionBankPopup">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body  page-background-color">
					<div>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="col-xs-12" style="min-height: 30px"></div>
							<form name="questionBankForm" id="questionBankForm">
								<table class="table table-hover table-striped"
									id="questionBankTable">
									<thead>
										<tr>
											<th style="width: 50px"><spring:message code="lbl.date" /></th>
											<th><spring:message code="lbl.questiontitle" /></th>
											<th><spring:message code="lbl.questiontype" /></th>
											<th style="width: 10px"><spring:message
													code="lbl.action" /></th>
										</tr>
									</thead>
									<tbody id="questionBankBody">
									</tbody>
								</table>
								<div class="col-xs-12" style="min-height: 30px"></div>
								<div style="text-align: center">
									<button type="button"
										class="btn btn-success button-width-large btn-flat pull-right"
										id="importQuestionButtonInTable">
										<spring:message code="lbl.import" />
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- pop up for adding existing test setting -->

	<div class="modal fade" id="sectionListtablePopup" role="dialog"
		aria-labelledby="sectionListtablePopup">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body  page-background-color">
					<div>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="col-xs-12" style="min-height: 30px"></div>
							<form name="sectionListForm" id="sectionListForm">
								<table class="table table-hover table-striped"
									id="sectionListTable">
									<thead>
										<tr>
											<th><spring:message code="lbl.sectionname" /></th>
											<th><spring:message code="lbl.mappedassessmentname" /></th>
											<th><spring:message code="lbl.numberofquestions" /></th>
											<th style="width: 150px"></th>
										</tr>
									</thead>
									<tbody id="sectonListbody">
									</tbody>
								</table>
								<div class="col-xs-12" style="min-height: 30px"></div>
								<div style="text-align: center">
									<button type="button"
										class="btn btn-success button-width-large btn-flat pull-right"
										id="importSectionButtonInTable">
										<spring:message code="lbl.import" />
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- popup for creating new section. -->
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
						<h3 style="text-align: center" id="sectionHeading">
							<spring:message code="lbl.createnewsection" />
						</h3>
						<br />
						<div class="col-xs-12">
							<form name="sectionForm" id="sectionForm">
								<input type="text" class="form-control"
									placeholder="<spring:message code="lbl.entersectionname"/>"
									id="newSectionName" onkeydown="sectionNameKey();"
									onkeyup="sectionNameKey();" maxlength="50"> <label
									class="requireFld" id="newSectionNameError1"><spring:message
										code="msg.empty" /></label> <label class="requireFld"
									id="newSectionNameError2"><spring:message
										code="msg.alphanumericallowed" /></label> <label class="requireFld"
									id="newSectionNameError3"><spring:message
										code="msg.maxlength50" /></label>
							</form>
						</div>
						<div class="col-xs-12"
							style="text-align: center; padding-top: 30px">
							<a onclick="saveSection()" id="saveSectionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message
									code="lbl.save" /></a>&emsp;&emsp; <a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message
									code="lbl.cancel" /></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
        var messages = new Array();
        messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true' />";
        messages['msg.refreshpage'] = "<spring:message code='msg.refreshpage' javaScriptEscape='true' />";
        messages['lbl.published'] = "<spring:message code='lbl.published' javaScriptEscape='true' />";
        messages['lbl.drafted'] = "<spring:message code='lbl.drafted' javaScriptEscape='true' />";
        messages['lbl.selectassessment'] = "<spring:message code='lbl.selectassessment' javaScriptEscape='true' />";
        messages['msg.deletesection'] = "<spring:message code='msg.deletesection' javaScriptEscape='true' />";
        messages['msg.submitdetail'] = "<spring:message code='msg.submitdetail' javaScriptEscape='true' />";
        messages['lbl.save'] = "<spring:message code='lbl.save' javaScriptEscape='true' />";
        messages['lbl.createnewsection'] = "<spring:message code='lbl.createnewsection' javaScriptEscape='true' />";
        messages['lbl.editsectionname'] = "<spring:message code='lbl.editsectionname' javaScriptEscape='true' />";
        messages['lbl.update'] = "<spring:message code='lbl.update' javaScriptEscape='true' />";
        messages['lbl.editname'] = "<spring:message code='lbl.editname' javaScriptEscape='true' />";
        messages['lbl.delete'] = "<spring:message code='lbl.delete' javaScriptEscape='true' />";
        messages['lbl.createquestion'] = "<spring:message code='lbl.createquestion' javaScriptEscape='true' />";
        messages['lbl.addquestionfromquestionbank'] = "<spring:message code='lbl.addquestionfromquestionbank' javaScriptEscape='true' />";
        messages['lbl.totalquestionadded'] = "<spring:message code='lbl.totalquestionadded' javaScriptEscape='true' />";
        messages['lbl.questions'] = "<spring:message code='lbl.questions' javaScriptEscape='true' />";
        messages['lbl.totalmarkscontain'] = "<spring:message code='lbl.totalmarkscontain' javaScriptEscape='true' />";
        messages['msg.deletequestion'] = "<spring:message code='msg.deletequestion' javaScriptEscape='true' />";
        messages['lbl.question'] = "<spring:message code='lbl.question' javaScriptEscape='true' />";
        messages['placeholder.question'] = "<spring:message code='placeholder.question' javaScriptEscape='true' />";
        messages['msg.empty'] = "<spring:message code='msg.empty' javaScriptEscape='true' />";
        messages['lbl.answer'] = "<spring:message code='lbl.answer' javaScriptEscape='true' />";
        messages['lbl.addanswer'] = "<spring:message code='lbl.addanswer' javaScriptEscape='true' />";
        messages['lbl.clearquestion'] = "<spring:message code='lbl.clearquestion' javaScriptEscape='true' />";
        messages['lbl.suboptions'] = "<spring:message code='lbl.suboptions' javaScriptEscape='true' />";
        messages['lbl.addsuboption'] = "<spring:message code='lbl.addsuboption' javaScriptEscape='true' />";
        messages['lbl.answerexplanation'] = "<spring:message code='lbl.answerexplanation' javaScriptEscape='true' />";
        messages['lbl.enterthemarkforthisquestion'] = "<spring:message code='lbl.enterthemarkforthisquestion' javaScriptEscape='true' />";
        messages['msg.allowednumericvalue'] = "<spring:message code='msg.allowednumericvalue' javaScriptEscape='true' />";
        messages['lbl.answernumber'] = "<spring:message code='lbl.answernumber' javaScriptEscape='true' />";
        messages['lbl.removeanswer'] = "<spring:message code='lbl.removeanswer' javaScriptEscape='true' />";
        messages['msg.suboption.required'] = "<spring:message code='msg.suboption.required' javaScriptEscape='true' />";
        messages['msg.clearquestion'] = "<spring:message code='msg.clearquestion' javaScriptEscape='true' />";
        messages['msg.successquestiondetailupdate'] = "<spring:message code='msg.successquestiondetailupdate' javaScriptEscape='true' />";
        messages['lbl.option'] = "<spring:message code='lbl.option' javaScriptEscape='true' />";
        messages['lbl.suboption'] = "<spring:message code='lbl.suboption' javaScriptEscape='true' />";
        messages['lbl.columns'] = "<spring:message code='lbl.columns' javaScriptEscape='true' />";
        messages['lbl.rows'] = "<spring:message code='lbl.rows' javaScriptEscape='true' />";
        messages['lbl.addcolumn'] = "<spring:message code='lbl.addcolumn' javaScriptEscape='true' />";
        messages['lbl.addrow'] = "<spring:message code='lbl.addrow' javaScriptEscape='true' />";
        messages['lbl.column'] = "<spring:message code='lbl.column' javaScriptEscape='true' />";
        messages['lbl.row'] = "<spring:message code='lbl.row' javaScriptEscape='true' />";
        messages['lbl.removecolumn'] = "<spring:message code='lbl.removecolumn' javaScriptEscape='true' />";
        messages['lbl.removerow'] = "<spring:message code='lbl.removerow' javaScriptEscape='true' />";
        messages['lbl.list'] = "<spring:message code='lbl.list' javaScriptEscape='true' />";
        messages['lbl.addlistitem'] = "<spring:message code='lbl.addlistitem' javaScriptEscape='true' />";
        messages['lbl.removelistitem'] = "<spring:message code='lbl.removelistitem' javaScriptEscape='true' />";
        messages['lbl.multipleanswer'] = "<spring:message code='lbl.multipleanswer' javaScriptEscape='true' />";
        messages['msg.multipleanswer'] = "<spring:message code='msg.multipleanswer' javaScriptEscape='true' />";
        messages['lbl.clickforrightanswer'] = "<spring:message code='lbl.clickforrightanswer' javaScriptEscape='true' />";
        messages['lbl.mathematicalformula'] = "<spring:message code='lbl.mathematicalformula' javaScriptEscape='true' />";
        messages['lbl.answers'] = "<spring:message code='lbl.answers' javaScriptEscape='true' />";
        messages['lbl.edit'] = "<spring:message code='lbl.edit' javaScriptEscape='true' />";
        messages['msg.matchingitemempty'] = "<spring:message code='msg.matchingitemempty' javaScriptEscape='true' />";
        
        messages['lbl.multiplechoice'] = "<spring:message code='lbl.multiplechoice' text='Multiple Choice'/>";
        messages['lbl.singlechoice'] = "<spring:message code='lbl.singlechoice' text='Single Choice'/>";
        messages['lbl.sortlist'] = "<spring:message code='lbl.sortlist' text='Sort List'/>";
        messages['lbl.choicematrix'] = "<spring:message code='lbl.choicematrix' text='Choice Matrix'/>";
        messages['lbl.classification'] = "<spring:message code='lbl.classification' text='Classification'/>";
        messages['lbl.matchlist'] = "<spring:message code='lbl.matchlist' text='Match List'/>";
        
        messages['lbl.choicematrixtable'] = "<spring:message code='lbl.choicematrixtable' text='Choice Matrix Table'/>";
        messages['lbl.questiontypedesc'] = "<spring:message code='lbl.questiontypedesc' text='This describes the type of selected question.'/>";
        messages['lbl.questiondescriptionintooltip'] = "<spring:message code='lbl.questiondescriptionintooltip' text='HTML/Text content displayed in all states (initial, resume, review) rendered above the response area.'/>";
        messages['lbl.listofmatrixitems'] = "<spring:message code='lbl.listofmatrixitems' text='List of Matrix items.'/>";
        messages['lbl.choiematrixsuboptiondesc'] = "<spring:message code='lbl.choiematrixsuboptiondesc' text='Choice column headings for example Yes, No, True, False.'/>";
        messages['lbl.choicematrixtabledescriptionforselectedoption'] = "<spring:message code='lbl.choicematrixtabledescriptionforselectedoption' text='This is the preview of choice matrix type question and select the right answer for every row.'/>";
        
        messages['lbl.classificationtable'] = "<spring:message code='lbl.classificationtable' text='Classification Table'/>";
        messages['lbl.listofpossibleresponsevalues'] = "<spring:message code='lbl.listofpossibleresponsevalues' text='List of possible response values.'/>";
        messages['lbl.columntitlesforclassificationtable'] = "<spring:message code='lbl.columntitlesforclassificationtable' text='Column titles for classification table.'/>";
        messages['lbl.rowtitlesclassificationtable'] = "<spring:message code='lbl.rowtitlesclassificationtable' text='Row titles classification table.'/>";
        messages['lbl.classificationtypeselectedoptiondescription'] = "<spring:message code='lbl.classificationtypeselectedoptiondescription' text='This is classification table and minimum one response should be dragged inside minimum one cell.'/>";
        messages['lbl.questionexpdescriptionintooltip'] = "<spring:message code='lbl.questionexpdescriptionintooltip' text='Text content displayed in all states (initial, resume, review) rendered above the response area.'/>";
        
        messages['lbl.valuestobedisplayedinthequestionlist'] = "<spring:message code='lbl.valuestobedisplayedinthequestionlist' text='Values to be displayed in the question list.'/>";
        messages['lbl.thisismatchinglistanddragtheiteminmatchinglist'] = "<spring:message code='lbl.thisismatchinglistanddragtheiteminmatchinglist' text='This is matching list and drag the item in matching list.'/>";
        messages['lbl.matchinglistorder'] = "<spring:message code='lbl.matchinglistorder' text='Matching List Order'/>";
        
        messages['lbl.multipletypeoptiondesctooltip'] = "<spring:message code='lbl.multipletypeoptiondesctooltip' text='Label to be displayed for this option - plain string with HTML allowed for formatting or mathjax syntax.'/>";
        
        messages['lbl.sortlistunorderlistdesc'] = "<spring:message code='lbl.sortlistunorderlistdesc' text='Array of strings that represent the unordered list for the question.'/>";
        messages['lbl.sortlistorderlistdesc'] = "<spring:message code='lbl.sortlistorderlistdesc' text='Array of strings that represent the ordered list for the question.'/>";
        messages['lbl.correctorder'] = "<spring:message code='lbl.correctorder' text='Correct Order'/>";
 </script>
<!-- page script -->
<script src="resources/toggle/lib/ToggleSwitch.js"></script>
<!-- For Editor -->
<script src="resources/ckeditor/ckeditor.js"></script>
<!-- <script src="tiny_mce/tiny_mce.js" type="text/javascript"></script> -->
<!-- Select2 -->
<script src="resources/adminlte/plugins/select2/select2.full.min.js"></script>
<!-- common operation -->
<script
	src="<spring:url value='/resources/js/test/author/build/preparetestquestion.js?v=6'/>"></script>
<!-- multiple choice type question -->
<script
	src="<spring:url value='/resources/js/test/author/build/multiplechoicetypequestion.js?v=3'/>"></script>
<!-- sort list type question -->
<script
	src="<spring:url value='/resources/js/test/author/build/sortlisttypequestion.js?v=3'/>"></script>
<!-- choice matrix type question -->
<script
	src="<spring:url value='/resources/js/test/author/build/choicematrixtypequestion.js?v=3'/>"></script>
<!-- classification type question -->
<script
	src="<spring:url value='/resources/js/test/author/build/classificationtypequestion.js?v=3'/>"></script>
<!-- match list type question -->
<script
	src="<spring:url value='/resources/js/test/author/build/matchlisttypequestion.js?v=3'/>"></script>
<script>
var courseId = <%=request.getParameter("courseId")%>;
var courseSectionId = <%=request.getParameter("sectionId")%>;
var contentId = <%=request.getParameter("contentId")%>; // would be if test as content of course is in edit mode.
var dynamicId=1;
var negMark=parseFloat('${test.negMark}');
var testId='${test.testId}';
var equalMarkTest='${test.equalMarkTest}';
var everyQuestionMark='${test.everyQuestionMark}';
var totalQuestions=0;
var currentObject;
$(document).ready(function(){	  
	$("#okk").click(function() {
		$("#testAlert,.modal-backdrop").fadeOut();
	});
	
	$("#question-type-header-tooltip").tooltip({title: messages['lbl.questiontypedesc'], trigger: "hover",placement:"right"});
	
		    $(window).on('beforeunload', function(){
		        return '<spring:message code="msg.datalostwarning"/>';
		    });
		    $('.imgset img').css({'max-width':'400px','height': 'auto'});
		$(".treeview").removeClass("active");
		$("#test").addClass("active");	
		$("#test .treeview-menu > #test").addClass("active");
		
		/**
		 * listener on selectedSectionIds class for making enable the import section button when there is min. one section is selected..
		 */
		$(document).on('ifChecked', '.selectedSectionIds', function() {
			var boxes = $('.selectedSectionIds');
		   $('#importSectionButtonInTable').prop('disabled', !boxes.filter(':checked').length);
		}).trigger('change');
		/**
		 * listener on selectedSectionIds class for making disable the import section button when there is no section is selected.
		 */
		$(document).on('ifUnchecked', '.selectedSectionIds', function() {
			var boxes = $('.selectedSectionIds');
		   $('#importSectionButtonInTable').prop('disabled', !boxes.filter(':checked').length);
		}).trigger('change');
        var length = $("#totalTestSections .section").length;
        if(length!=null && length==0){
        	$("#submitAllData").prop('disabled',true);
        }
});
/**
 * @summary added listener on click of button which have flat-red class.
 */
	$(document).on('ifChecked', '.flat-red', function(event) {
		questionKeyValidate();
	});
var questionSectionList;
var importQuesId=[];
var importTestId=[];
if(testId>0){
         questionSectionList=${sectionJsonList};
}
else
	{
	questionSectionList=[];
	}
</script>
</html>