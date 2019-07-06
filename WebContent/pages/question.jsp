<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>
.imgset img {
	max-width: 100% !important;
	height: auto !important;
}

.body-color {
	background-color: #F0F0F0;
}

.color-mainblue {
	color: #05B26F;
}

.button-color-blue {
	background-color: white;
	color: #4B5960;
	border: 0.1em solid #4B5960;
}

.button-green-color {
	border-width: 0.1em;
	color: white;
	border: 1px solid #05B26F;
	background-color: #05B26F;
}

.bottom-border {
	border: 1px solid #F0F0F0;
	margin: 0px;
	height: 100%
}

.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

.source_table>table>tbody>tr {
	padding: 3px;
	height: 4.5em;
	min-height: 4.5em;
}

.target_table>table>tbody>tr {
	padding: 3px;
	height: 4.5em;
	min-height: 4.5em;
}

.source_table>table>tbody>tr>td {
	padding: 3px !important;
	border: 2px solid #ccc !important;
}

.target_table>table>tbody>tr>td {
	padding: 3px !important;
	border: 2px solid #ccc !important;
}

.lrn_btn_sort {
	background-color: #ffffff;
	color: #333333;
	border: 0px solid #ccc;
	display: inline-block;
	position: relative;
	text-transform: none;
	text-decoration: none;
	text-shadow: none;
	font-weight: normal;
	outline: none;
	padding: 0.6em 1.2em;
	border-radius: 0px;
	transition: 0.2s linear background-color, color;
	box-shadow: none;
	user-select: none;
	width: 100%;
	line-height: 2.2em;
	padding: 0.5em;
	transition: none;
}

.target_table th, .source_table th {
	border: 0px solid white !important;
}

.padding-left {
	padding-left: 10px;
}

.source_table .input-group {
	border: 1px solid #ccc;
}

.source_table .input-group-addon {
	color: #ECF0F5;
	background-color: #ccc !important;
}

.target_table .input-group {
	border: 1px solid #05B26F;
}

.target_table .input-group-addon {
	color: #FFFFFF;
	background-color: #05B26F !important;
}

.button-color-green {
	color: #ECF0F5;
	background-color: #00a65a !important;
}

.content-header .input-group-addon {
	background-color: transparent !important;
	padding-right: 0px !important;
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

#choicematrixtable td, #choicematrixtable th {
	border: 1px solid #dddddd !important;
}

#classificationtable td {
	border: 2px dashed #dddddd !important;
}

#classificationtable th {
	border-bottom: 2px dashed #dddddd !important;
}

#classificationtable tbody>tr>td {
	height: 50px;
	min-height: 50px !important;
}

#classificationtable td:nth-child(1), #classificationtable th:nth-child(1)
	{
	border: 0px solid black !important;
}

.answerDivInClassificationTypeQuestion {
	padding: 10px;
	margin: 10px;
	min-width: 50px;
	max-width: 200px;
	border: 1px solid #ccc;
	background-color: white;
	margin-bottom: 0px;
	float: left;
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
	padding-left: 2em;
	padding-right: 2em;
	width: 100%;
	border: 0px solid #ccc;
	margin: 0px;
}

.match_list_type_ques_row {
	display: table-row;
}

.match_list_type_ques_col2 {
	display: table-cell;
	vertical-align: middle;
	width: 45%;
}

.match_list_type_ques_col2 {
	max-width: 100% !important;
	height: auto !important;
}

.match_list_type_question {
	padding: 0.7em 1em;
	background: transparent;
	text-align: center;
	border: 1px solid #e8e8e8;
	min-height: 2.7em;
}

.match_list_type_ques_col2 img {
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
	text-align: center;
}

.match_list_type_question_res_container {
	margin: 5px !important;
	max-width: 100% !important;
}
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<div class="row">
					<div class="col-sm-12">
						<section class="content-header">
							<div class="input-group">
								<h3>
									<spring:message code="lbl.questionlibrary" />
								</h3>
								<div class="input-group-addon">
									<button type="button" id="uploadCSVQuestionData"
										class="btn btn-flat btn-success button-width-large"
										data-target="#uploadQuesPopup" data-toggle="modal"
										onclick="keyValidate();">
										<spring:message code="lbl.uploadcsv" />
									</button>
									&nbsp;
									<%-- <button type="button" id="addnew"
										class="btn btn-flat btn-success button-width-large"
										onclick="showCreateQuestionPopUp()">
										<spring:message code="lbl.addnewquestion" />
									</button> --%>
									<button type="button" id="addnew"
										class="btn btn-flat btn-success button-width-large"
										onclick="location.href='questiontypelist'">
										<spring:message code="lbl.addnewquestion" />
									</button>
								</div>
							</div>
						</section>
					</div>
				</div>

				<section class="content">
					<div class="row">
						<div class="col-md-12">
							<div class="callout callout-danger hide"
								style="margin-bottom: 5px;">
								<label><spring:message code="msg.account.not.verified" /></label>
								<label class="pull-right"> <a href="#"
									onclick="resendverificationlink()"><spring:message
											code="msg.resend.verification.mail" /></a>
								</label>
							</div>
							<div class="box no-border">
								<!-- /.box-header -->
								<div class="box-body no-padding">
									<div class="col-xs-12 hide">
										<div class="mailbox-controls col-xs-12">
											<!-- Check all button -->
											<div class="btn-group">
												<button class="btn btn-default btn-sm">
													<i class="fa fa-trash-o"></i>
												</button>
											</div>
											<!-- /.btn-group -->
											<button class="btn btn-default btn-sm"
												onclick="location.href='test';">
												<i class="fa fa-refresh"></i>
											</button>
										</div>
									</div>
									<br />
									<div class="table-responsive mailbox-messages col-xs-12">
										<table class="table table-hover table-striped"
											id="questionData">
											<thead>
												<tr>
													<th style="width: 10%"><spring:message code="lbl.date" /></th>
													<th><spring:message code="lbl.questiontitle" /></th>
													<th><spring:message code="lbl.assessments" /></th>
													<th style="width: 20%"><spring:message
															code="lbl.questiontype" /></th>
													<th><spring:message code="lbl.action" /></th>
												</tr>
											</thead>
											<tbody>
												<%-- 	<c:forEach items="${questionList}" var="question">
													<tr>
														<td>${question.createdDate}</td>
														<td class="imgset"><a style="cursor: pointer"
															class="pull-left"
															onclick="questionPreview(${question.questionId})">${question.questionName}</a></td>
														<td>${question.mappedTest}</td>
														<td>${question.questionTypeName}</td>
														<td style="width: 15%"><c:if
																test="${question.questionIsMap==0}">
																<a title="<spring:message code="lbl.editquestion"/>" style="cursor: pointer"
																	onclick="location.href='addeditquestion?questionId=${question.questionId}'"><font
																	color="#00A65A"><i class="fa fa-edit"></i></font></a>												  
										 &nbsp;&nbsp;
										<a title="<spring:message code="lbl.deletequestion"/>" style="cursor: pointer"
																	onclick="deleteDraftedQuestion(${question.questionId})"><font
																	color="#00A65A"><i class="fa fa-trash"></i></font></a>
															</c:if></td>
													</tr>
												</c:forEach> --%>
											</tbody>
										</table>
										<!-- /.table -->
									</div>
									<!-- /.mail-box-messages -->
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /. box -->
						</div>
						<!-- /.col -->
					</div>
				</section>
			</div>
		</div>
		<!-- content-wrapper -->
		<!-- End dataTable----->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<!-- Start of Alert box for delete test -->
	<div class="modal fade" id="deleteQuestionAlert" tabindex="-1"
		role="dialog" aria-labelledby="deletetestAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p>
							<spring:message code="msg.deletequestion" />
						</p>
						<button type="button" class="btn btn-default button-width"
							data-dismiss="modal">
							<spring:message code="lbl.no" />
						</button>
						<a id="dId"><button type="button" data-dismiss="modal"
								class="btn btn-success button-width">
								<spring:message code="lbl.yes" />
							</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Alert box -->
	<div class="modal fade" id="successdialog" tabindex="-1" role="dialog"
		aria-labelledby="successdialog">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p>
							<spring:message code="msg.success.verification.mail" />
						</p>
						<button type="button"
							class="btn btn-success button-width-large btn-flat"
							data-dismiss="modal">
							<spring:message code="lbl.close" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="questionErrorAlert" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body">
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
	<!-- pop up for uploading CSV File -->
	<div class="modal fade" id="uploadQuesPopup" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header" style="border: 0px">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

					</div>
					<div class="modal-body">
						<div class="row" style="margin: 0">
							<form name="questionFileForm" id="questionFileForm"
								enctype="multipart/form-data">
								<h4 style="margin-top: -10px; margin-bottom: 25px;">
									<spring:message code="lbl.importmcquestions" />
								</h4>
								<input type="file" class="form-group" id="questionFile"
									name="questionFile" onclick="keyValidate();"> <label
									class="requireFld" id="questionFileError"><spring:message
										code="msg.empty" /></label> <a href="${excelSheet}" class="pull-left"
									style="cursor: pointer" download><spring:message
										code="lbl.downloadmcqsample" /></a>
								<button type="button" id="uploadQuestionFile"
									class="btn btn-success pull-right">
									<spring:message code="lbl.upload" />
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="questionPreview" tabindex="-1"
		role="dialog" aria-labelledby="questionPreview">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header" style="border: 0px">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

					</div>
					<div class="modal-body">
						<section class="content">
							<div id="questionlist" class="row">
								<div class="row"
									style="background-color: white; padding-bottom: 10px">
									<div
										style="height: 45px; border-bottom: 1px solid #F0F0F0; border-top: 1px solid #F0F0F0; margin-top: 10px;">
										<h4 style="text-align: center;">
											&emsp;
											<spring:message code="lbl.questiontype" />
											: <span id="questionType"></span>
										</h4>
									</div>
									<div class="col-md-12 col-sm-12 col-xs-12 ">
										<div id="allquestion"
											style="max-height: 420px; overflow-y: auto; margin-top: 10px;"></div>
										<!-- /. allquestion-->
									</div>
								</div>
								<div style="text-align: center; margin-top: 10px;">
									<button type="button"
										class="btn btn-success button-width-large pull-right btn-flat"
										data-dismiss="modal">
										<spring:message code="lbl.close" />
									</button>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!---------------------------------------------------choose question type popup-------------------------------------------------------------------------------------->
	<!-- End of Alert box -->
	<div class="modal fade" id="CreateQuestionPagePopUp" tabindex="-1"
		role="dialog" aria-labelledby="CreateQuestionPagePopUp">
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

</body>
<script type="text/javascript">
        var messages = new Array();
        messages['lbl.explanation'] = "<spring:message code='lbl.explanation' javaScriptEscape='true' />";
        messages['lbl.mathematicalformula'] = "<spring:message code='lbl.mathematicalformula' javaScriptEscape='true' />";
        messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true' />";
        messages['msg.deleteerror'] = "<spring:message code='msg.deleteerror' javaScriptEscape='true' />";
        messages['msg.invalidfileselected'] = "<spring:message code='msg.invalidfileselected' arguments='#filetype' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['lbl.source'] = "<spring:message code='lbl.source' javaScriptEscape='true' />";
        messages['lbl.target'] = "<spring:message code='lbl.target' javaScriptEscape='true' />";
        messages['lbl.editquestion'] = "<spring:message code='lbl.editquestion'/>";
        messages['lbl.deletequestion'] = "<spring:message code='lbl.deletequestion'/>";
</script>
<!-- multiple choice type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/preview/question.js'/>"></script>
<!-- multiple choice type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/preview/multiplechoicetypequestion.js'/>"></script>
<!-- sort list type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/preview/sortlisttypequestion.js'/>"></script>
<!-- choice matrix type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/preview/choicematrixtypequestion.js'/>"></script>
<!-- classification type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/preview/classificationtypequestion.js'/>"></script>
<!-- match list type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/preview/matchlisttypequestion.js'/>"></script>
<script>
	/**
	 * @summary function would be called on document ready.
	 * @returns no.
	 */
	$(document)
			.ready(
					function() {
						var userStatus = '${userStatus==0}';
						$('.imgset img').css({
							'display' : 'block',
							'width' : '100%',
							'max-width' : '100%',
							'height' : 'auto'
						});
						if (userStatus == 'true') {
							$("#addnew").attr("disabled", "disabled");
							$(".callout").removeClass('hide');
						}
						$(".treeview").removeClass("active");
						$("#questionList").addClass("active");
						$("#questionList .treeview-menu > #questionList")
								.addClass("active");
						$('#questionData')
								.DataTable(
										{
											"processing" : true,
											"serverSide" : true,
											'aaSorting' : [],
											"pagingType" : "full_numbers",
											"language" : datatablelanguagejson,
											"ajax" : {
												"url" : "getQuestionListData?action=questionlibrary",
												"type" : "GET",
												"data" : function(data) {
													planify(data);
												}
											},
											"columns" : [ {
												"data" : "createdDate"
											}, {
												"data" : "questionName"
											}, {
												"data" : "mappedTest"
											}, {
												"data" : "questionTypeName"
											}, {
												"data" : "questionIsMap"
											} ],
											"columnDefs" : [
													{
														// The `data` parameter refers to the data for the cell (defined by the
														// `data` option, which defaults to the column being worked with, in
														// this case `data: 0`.
														"render" : function(
																data, type, row) {
															var questionHtml='<a style="cursor: pointer" class="pull-left" onclick="questionPreview('+row.questionId+')">'+data+'</a>';
															return questionHtml;
														},
														"targets" : 1
													},
													{
														"render" : function(
																data, type, row) {
															var html = "";
															if (data == 0) {
																html = '<a title="'+messages['lbl.editquestion']+'" style="cursor: pointer" onclick="location.href=\'addeditquestion?questionId='
																		+ row.questionId
																		+ '\'">'
																		+ '<font color="#00A65A"><i class="fa fa-edit"></i></font></a>&nbsp;&nbsp;'

																		+ '<a title="'+messages['lbl.deletequestion']+'" style="cursor: pointer"'
																		+ 'onclick="deleteDraftedQuestion('
																		+ row.questionId
																		+ ')"><font color="#00A65A"><i class="fa fa-trash"></i></font></a>';
															}
															return html;
														},
														"targets" : 4,
														"orderable" : false
													},
													{
														"render" : function(
																data, type, row) {
															return data == null ? ""
																	: data;
														},
														"targets" : 2
													}

											]
										});
						/*  $("#questionData").dataTable({'columnDefs': [{ 'orderable': false, 'targets': [4] }], // hide sort icon on action
						    'aaSorting': [],
						  "pagingType": "full_numbers",  
						  "language": datatablelanguagejson
						 }); */

					});
														
    /**
     * @summary this function is used for cpnverting 3d array data into 2d array data of columns of data tables.
     * @param data
     * @returns no.
     */
	function planify(data) {
		for (var i = 0; i < data.columns.length; i++) {
			column = data.columns[i];
			column.searchRegex = column.search.regex;
			column.searchValue = column.search.value;
			delete (column.search);
		}
	}
	
	/**
	 * @summary function for re-sending verification link in case of not-verify through Ajax Calling.
	 * @param email
	 * @returns no.
	 */
	var resendverificationlink = function(email) {
		var email = '<%=((User) request.getSession().getAttribute("userSession")).getEmail()%>';
		$.ajax({
			url : "sendVerifyMail",
			type : 'POST',
			//async : false,		
			error : (function() {
				alert("server error");
			}),
			success : function(data) {
				if (data) {
					$(".callout").addClass('hide');
					$("#successdialog").modal('show');
				}
			}
		});
	}
</script>
</html>