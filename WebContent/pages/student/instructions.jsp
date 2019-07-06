<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.qbis.model.User"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/pages/include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
#TimeOutModal p {
	word-break:break-word !important;
}

#question_mark {
	background-color: #f1f1f1;
	color: black;
	opacity: .65;
	display: inline;
	padding: .2em .6em .3em;
	line-height: 1;
	text-align: center;
	white-space: nowrap;
	vertical-align: baseline;
	border: 1px solid #ccc;
	font-family: initial;
}

#sectiontitle {
	color: #FFF !important;
	background-color: #00A65A !important;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
	min-height: 40px !important;
	padding: 8px 5px 8px 15px !important;
}

.test-details {
	background-color: #fff !important;
	margin: 0px !important;
	padding-top: 15px !important;
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

.quit {
	line-height: 3.4;
}

.sidebar-menu>li>a {
	padding: 8px 5px 8px 15px;
	display: block;
}

ul.pagination {
	display: inline-block;
	margin-top: 10px;
	margin-left: 40%;
}

ul.pagination li {
	display: inline;
}

.cursor {
	cursor: pointer;
}

.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

.textarea {
	resize: none;
}

.source_table>table>tbody>tr {
	padding: 3px;
	height: 3.5em;
	min-height: 3.5em;
}

.source_table>table>tbody>tr>td {
	padding: 3px !important;
	border: 2px solid #ccc !important;
}

.target_table>table>tbody>tr {
	padding: 3px;
	height: 4.5em;
	min-height: 4.5em;
}

.target_table>table>tbody>tr>td {
	padding: 3px !important;
	border: 2px solid #ccc !important;
}

.lrn_btn_sort {
	background-color: #ffffff;
	color: #333333;
	border: 1px solid #ccc;
	cursor: pointer;
	display: inline-block;
	position: relative;
	text-transform: none;
	text-decoration: none;
	text-shadow: none;
	font-weight: normal;
	outline: none;
	padding: 0.6em 1.2em;
	border-radius: 2px;
	transition: 0.2s linear background-color, color;
	box-shadow: none;
	user-select: none;
	cursor: move;
	width: 100%;
	line-height: 2.2em;
	padding: 0.5em;
	transition: none;
}

.lrn_btn_sort:hover {
	border: 2px solid black;
}

.active {
	background-color: black;
	color: white !important;
}

.active>input-group>input-group-addon>.fa {
	color: white
}

.target_table th, .source_table th {
	border: 0px solid white !important;
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

.choicematrixtable td, .choicematrixtable th {
	border: 1px solid #dddddd !important;
}

.classificationtable td {
	border: 2px dashed #dddddd !important;
}

.classificationtable th {
	border-bottom: 2px dashed #dddddd !important;
}

.classificationtable tbody>tr>td {
	height: 50px;
	min-height: 50px !important;
}

.classificationtable td:nth-child(1), .classificationtable th:nth-child(1)
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

.answerDivInClassificationTypeQuestion img {
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
	text-align: center;
}

.match_list_type_question_res_container {
	margin: 5px !important;
	max-width: 100% !important;
}

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

.answerDivInMatchListTypeQuestion img {
	max-width: 100% !important;
	height: auto !important;
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

.opposite_triangle {
	content: '';
	width: 0;
	width: 0;
	height: 0;
	border-left: 12px solid transparent;
	border-right: 12px solid transparent;
	border-top: 12px solid #00A65A;
	margin-left: 45%;
}

p{
	word-break: break-word;
}

@media only screen and (max-width: 900px) {
	#allQuestionListDiv,#questionlist {
		width:100%;
		margin-left:0px;
	}
}

.question-details-header .input-group-addon {
  padding: 6px 6px;
  }
</style>
</head>
<body
	class="hold-transition skin-blue sidebar-mini page-background-color" style="min-width:700px;overflow-x: auto;">
	<div class="col-sm-12">
		<div id="overlay" class="overlay1"
			style="position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<div id="instructions">
			<!-- Main content -->
			<section class="content">
				<h2 class="page-background-color"
					style="margin: 10px -10px 10px 0px;" id="testName"></h2>
				<div class="row test-details">
					<div class="col-sm-12 form-group">
						<input type="hidden" id="numAttempt"> <input type="hidden"
							id="countAttempt" value="${countAttempt}">
						<dl class="dl-horizontal">
							<dt class="form-group"><spring:message code="lbl.timebound" text="Time Bound"/> (<spring:message code="lbl.minutes" text="minutes"/>)</dt>
							<dd id="testTime"></dd>
							<dt class="form-group"><spring:message code="lbl.numberofattempts" text="Number of Attempts"/></dt>
							<dd id="maxAttempts"></dd>
							<dt class="form-group"><spring:message code="lbl.visibility" text="Visibility"/></dt>
							<dd id="isPublic"></dd>
							<dt class="form-group"><spring:message code="lbl.enablereview" text="Enable Review"/></dt>
							<dd id="isReview"></dd>
							<dt class="form-group"><spring:message code="lbl.numberofquestions" text="Number of Questions"/></dt>
							<dd id="totalQuestion"></dd>
							<dt class="form-group"><spring:message code="lbl.totalmarks" text="Total Marks"/></dt>
							<dd id="maxMark"></dd>
							<dt class="form-group"><spring:message code="lbl.negativemarks" text="Negative Marks"/></dt>
							<dd id="negMark"></dd>

							<dt class="form-group"><spring:message code="lbl.pausepossible" text="Pause Possible"/></dt>
							<dd id="testPause">
						</dl>
					</div>
					<div class="col-sm-12 form-group">
						<dl>
							<dt class="h3"><spring:message code="lbl.description" text="Description"/></dt>
							<dd id="testDesc"></dd>
						</dl>
					</div>
					<div class="col-sm-12 form-group">
						<h3><spring:message code="lbl.instructions" text="Instructions"/></h3>
						<p id="testInstruct"></p>
						<nav>
							<ul class="pager">
								<li>
									<button type="button" onclick="window.close();" id="canceltest"
										class="btn btn-default btn-flat button-width-large"><spring:message code="lbl.cancel" text="Cancel"/></button>
								</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
								<li>
									<%-- <button type="button"
									onclick="callajax('${testId}','${userId}')" id="starttest"
									class="btn btn-success btn-flat button-width-large">Start
									Test</button> --%>

									<button type="button"
										onclick="givetestpage('${testId}','${userId}')" id="starttest"
										class="btn btn-success btn-flat button-width-large">
										<spring:message code="lbl.startassessment" text="Start Assessment"/>
										</button>

								</li>
							</ul>
						</nav>
						<!-- /.description-block -->
					</div>
				</div>
			</section>

		</div>
		<!-- /.instructions -->
		<div id="questionlist" style="display: none">
			<div>
				<!-- Content Header (Page header) -->
				<div class="row" style="background-color: white; min-height: 50px;">
				<div class="col-sm-1"></div>
				<div class="col-sm-8" style="padding-left: 0px">
				<div class="input-group">
				<div class="input-group-addon"><h4 style="text-align:left"><span id="testNameInQuestionList"></span> (<spring:message code="lbl.assessment" text="Assessment"/>)</h4></div>
				<div class="input-group-addon">
				<h4 style="text-align:right"><spring:message code="lbl.currentsection" text="Current Section"/></h4>
				</div>
				<div class="input-group-addon">
				<select class="form-control"
						style="margin-top: 3%; margin-bottom: 3%;min-width:100px" id="sectionSelectList"
						onchange="changeSection()">
					</select>
				</div>
				</div>
			</div>
					<!-- <div class="col-sm-1"></div>
					<div class="col-sm-11" style="padding-left: 0px">
						<h4 class="pull-left col-sm-7">
							<span id="testNameInQuestionList"></span> (Assessment)
						</h4>
						<h4 class="col-sm-2">
							<span class="pull-right">Current Section</span>
						</h4>
						<div class="col-sm-3">
							<select class="form-control"
								style="margin-top: 3%; margin-bottom: 3%;"
								id="sectionSelectList" onchange="changeSection()">
							</select>
						</div>
					</div> -->
					<!-- <div class="col-sm-2 hide">
						<div class="quit pull-right" data-toggle="modal"
							data-target="#testQuit"
							style="text-align: center; cursor: pointer; background-color: #00A65A; color: white; height: 50px; width: 125px">
							<span><i class="fa fa-close">&nbsp;&nbsp;&nbsp;</i>Quit</span>
						</div>
					</div> -->
				</div>
				<section class="content" style="margin-top: 10px">
					<div class="col-sm-1"></div>
					<div id="questionlist" class="col-sm-8 row"
						style="min-height: 550px">

						<!-- Content Header (Page header) -->
						<div class="row"
							style="background-color: white; padding-bottom: 10px; min-height: 370px">
							<h4 style="text-align: center; margin: 20px">
								&emsp; Question: <span id="questionNo"></span> of <span
									id="totalQuestions"></span>&emsp;<span
									id="selectedSectionForCurrentQues"></span>
							</h4>
							<div
								style="height: 68px; border-bottom: 1px solid #F0F0F0; border-top: 1px solid #F0F0F0; margin-top: 10px;">
								<div class="input-group question-details-header">
								<div class="input-group-addon">
									<div id="divtime" class="pull-left" style="padding-left:10%">
									    <span  id="timeType"><spring:message code="lbl.timeleft" text="Time left"/>:</span>
										<i class="fa fa-clock-o color-mainblue line-height"></i>&nbsp;<span
											id="time"></span>
									</div>
									</div>
									<div class="input-group-addon">
										<h4>
											<span id="question_mark"></span>&nbsp;&nbsp;<spring:message code="lbl.mark" text="Mark"/>
										</h4>
									</div>
									<!-- /.description-block -->
									<!-- /.col -->
								
								<div class="input-group-addon">
									<div>
										<div class="buttons pull-right ">
											<ul class="line-height" id="noteul">
												<li class="navlist"></li>
												<li class="navlist li-border bookmark-click-green"></li>
												<li class=" navlist" id="pausebutton" onclick="pause()">
													<i class="fa fa-pause color-mainblue" id="pause"></i>
												</li>
												<li class="navlist" style="cursor: pointer" id="mathformula"
													data-toggle="modal" data-target="#formulaPopup"><i
													class="color-mainblue">f(x)</i></li>
											</ul>
										</div>
									</div>
								</div>
								</div>
								<!-- /.description-block -->
							</div>
							<div class="col-md-12 col-sm-12 col-xs-12 ">
								<!-- allquestion-->
								<div id="allquestion"></div>
								<!-- /. allquestion-->
							</div>
						</div>


						<div
							style="margin-top: 10px; margin-bottom: 10px; height: 45px; width: 104%"
							class="btn-group row">
							<button type="button" id="submit" data-toggle="modal"
								data-target="#testSubmit" class="btn btn-flat btn-danger"
								style="width: 25%; height: 100%; border: 1px solid #F0F0F0"><spring:message code="lbl.endassessment" text="End Assessment"/></button>
							<button type="button" class="btn btn-flat" id="whiteSpace"
								style="width: 25%; background-color: white; height: 100%; border: 1px solid #F0F0F0; cursor: default"
								disabled></button>
							<button type="button" class="btn btn-flat btn-success"
								id="previous" onclick="previous(1,0)"
								style="width: 25%; height: 100%; border: 1px solid #F0F0F0"
								disabled>
								<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;<spring:message code="lbl.previous" text="Previous"/>
							</button>
							<button type="button" class="btn btn-flat btn-success" id="next"
								onclick="next(1,1)"
								style="width: 25%; height: 100%; border: 1px solid #F0F0F0">
								<spring:message code="lbl.next" text="Next"/>&nbsp;&nbsp;&nbsp;<font size="4px"><i
									class="fa fa-angle-double-right"></i></font>
							</button>
						</div>
					</div>
					<div class="col-sm-3" style="margin: 10px; margin-top: 0px;margin-right: 0px;"
						id="allQuestionListDiv">
						<h5 id="sectiontitle"></h5>
						<div class="opposite_triangle"></div>
					</div>
				</section>


			</div>
		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- ./wrapper -->


	<div class="modal fade" id="testSubmit" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong><spring:message code="lbl.submit" text="Submit"/></strong>
						</h3>
						<p><spring:message code="msg.beforeassessmentsubmit" text="Are you sure to submit the Assessment?"/></p>
						<button type="button" class="btn btn-success button-width"
							data-dismiss="modal" onclick="submitTestProcess(1)"><spring:message code="lbl.submit" text="Submit"/></button>
						&nbsp;
						<button type="button" class="btn btn-default button-width"
							data-dismiss="modal"><spring:message code="lbl.cancel" text="Cancel"/></button>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="display: none" class="modal fade in" id="TimeOutModal"
		tabindex="-1" role="dialog" aria-labelledby="TimeOutModal"
		aria-hidden="false" style="display: block;">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong><spring:message code="lbl.timeout" text="TimeOut"/>!</strong>
						</h3>
						<p><spring:message code="msg.submittedassessmentafterexpiretime" text="Your time has expired and your Assessment has been successfully submitted."/></p>
						<button type="button" id="viewResult"
							class="btn btn-success button-width" data-dismiss="modal"
							onclick="location.href='viewTestResult'"><spring:message code="lbl.viewresult" text="View Result"/></button>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="testQuit" tabindex="-1" role="dialog"
		aria-labelledby="testQuit">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong><spring:message code="lbl.quit" text="Quit"/>!</strong>
						</h3>
						<p><spring:message code="msg.quitassessment" text="Are you sure you want to quit?"/></p>
						<button type="button" class="btn btn-success button-width"
							data-dismiss="modal" onclick="submitTestProcess(2)"><spring:message code="lbl.quit" text="Quit"/></button>
						&nbsp;
						<button type="button" class="btn btn-default button-width"
							style="background: red" data-dismiss="modal"><spring:message code="lbl.cancel" text="Cancel"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div style="display: none" class="modal fade in" id="testResume"
		tabindex="-1" role="dialog" aria-labelledby="testResume"
		aria-hidden="false" style="display: block;">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong><spring:message code="lbl.assessmentpaused" text="Assessment Paused"/>!</strong>
						</h3>
						<p><spring:message code="msg.assessmentpaused" text="Do you want to resume the Assessment?"/></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal" onclick="resume();">
							<spring:message code="lbl.resumeassessment" text="Resume Assessment"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="attemptModal" tabindex="-1" role="dialog"
		aria-labelledby="testQuit">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p></p>
						<button type="button" class="btn btn-success button-width"
							data-dismiss="modal"><spring:message code="lbl.ok" text="Ok"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- math formula popup -->
	<div class="modal fade" id="formulaPopup" tabindex="-1" role="dialog"
		aria-labelledby="formulaPopup">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog" style="width: 85%;">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<p id="formulaContent" class="imgset"></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Add Notes popup -->
	<div class="modal fade" id="notesModal" tabindex="-1" role="dialog"
		aria-labelledby="notesPopup">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label><spring:message code="lbl.enternotes" text="Enter Notes"/></label>
							<textarea id="notes" class="form-control textarea"
								style="max-length: 100%"></textarea>
						</div>
						<div class="">
							<input type="hidden" id="q_id"> <input type="hidden"
								id="noteId">
							<button type="button" class="btn btn-success"
								onclick="savenotes()"><spring:message code="lbl.submit" text="Submit"/></button>
							<button type="button" class="btn btn-success" id="notebtnclose"
								data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/pages/dialogs.jsp"%>
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['lbl.nolimit'] = "<spring:message code='lbl.nolimit' text='No Limit' javaScriptEscape='true' />";
        messages['lbl.unlimited'] = "<spring:message code='lbl.unlimited' text='Unlimited' javaScriptEscape='true' />";
        messages['lbl.public'] = "<spring:message code='lbl.public' text='Public' javaScriptEscape='true' />";
        messages['lbl.private'] = "<spring:message code='lbl.private' text='Private' javaScriptEscape='true' />";
        messages['lbl.yes'] = "<spring:message code='lbl.yes' text='Yes' javaScriptEscape='true' />";
        messages['lbl.no'] = "<spring:message code='lbl.no' text='No' javaScriptEscape='true' />";
        messages['lbl.withoutcorrectanswer'] = "<spring:message code='lbl.withoutcorrectanswer' text='Without Correct Answer' javaScriptEscape='true' />";
        messages['lbl.withcorrectanswer'] = "<spring:message code='lbl.withcorrectanswer' text='With Correct Answer' javaScriptEscape='true' />";
        messages['bl.nonegativemarking'] = "<spring:message code='bl.nonegativemarking' text='No Negative Marking' javaScriptEscape='true' />";
        messages['msg.maximumassessmentattemptlimit'] = "<spring:message code='msg.maximumassessmentattemptlimit' text='You can attempt this assessment maximum upto #maxattempt times.' arguments='#maxattempt' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';' />";
        messages['lbl.timetaken'] = "<spring:message code='lbl.timetaken' text='Time Taken' javaScriptEscape='true' />";
        messages['lbl.section'] = "<spring:message code='lbl.section' text='Section' javaScriptEscape='true' />";
        messages['lbl.question'] = "<spring:message code='lbl.question' text='Question' javaScriptEscape='true' />";
        messages['lbl.next'] = "<spring:message code='lbl.next' text='Next' javaScriptEscape='true' />";
        messages['lbl.previous'] = "<spring:message code='lbl.previous' text='Previous' javaScriptEscape='true' />";
        messages['lbl.nextsection'] = "<spring:message code='lbl.nextsection' text='Next Section' javaScriptEscape='true' />";
        messages['lbl.previoussection'] = "<spring:message code='lbl.previoussection' text='Previous Section' javaScriptEscape='true' />";
        messages['lbl.assessmentsubmitted'] = "<spring:message code='lbl.assessmentsubmitted' text='Assessment Submitted' javaScriptEscape='true' />";
        messages['msg.afterassessmentsubmit'] = "<spring:message code='msg.afterassessmentsubmit' text='Your assessment has been successfully  submitted.' javaScriptEscape='true' />";
        messages['msg.empty'] = "<spring:message code='msg.empty' text='This field is required.' javaScriptEscape='true' />";
        messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' text='Something went wrong,Please try again.' javaScriptEscape='true' />";
        messages['msg.aftersavednotesuccess'] = "<spring:message code='msg.aftersavednotesuccess' text='Note has been saved successfully.' javaScriptEscape='true' />";
        messages['msg.maxcharacterlength'] = "<spring:message code='msg.maxcharacterlength' arguments='#maxlength' text='Maximum #maxlength characters allowed.' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';' />";
        messages['lbl.nonegativemarking'] = "<spring:message code='lbl.nonegativemarking' text='No Negative Marking' javaScriptEscape='true' />";
        messages['lbl.target'] = "<spring:message code='lbl.target' javaScriptEscape='true' />";
        messages['lbl.source'] = "<spring:message code='lbl.source' javaScriptEscape='true' />";
        messages['lbl.target'] = "<spring:message code='lbl.target' javaScriptEscape='true' />";
 </script>
<!-- for common operation -->
<script
	src="<spring:url value='/resources/js/test/learner/build/giventest.js?v=1'/>"></script>
<!-- Multiple Choice Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/build/multiplechoicetypequestion.js'/>"></script>
<!-- Sort List Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/build/sortlisttypequestion.js'/>"></script>
<!-- Choice Matrix Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/build/choicematrixtypequestion.js'/>"></script>
<!-- Classification Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/build/classificationtypequestion.js'/>"></script>
<!-- Match List Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/build/matchlisttypequestion.js'/>"></script>
<!-- Drag, Drop and Sorting -->
<script src="<spring:url value='/resources/js/jquery-ui.js'/>"></script>
<script>
	/**
	 * @summary An object for putting data from json object
	 */
	var submitTestJson = {
		"userTestAttemptId" : 0,
		"userId" : Number('${userId}'),
		"testId" : 0,
		"testTime" : "0",
		"testStatus" : 0,
		"totalQuestion" : 0,
		"sectionlist" : []

	};
	/**
	 * @summary user id.
	 */
	var userId = '${userId}';
	/**
	 * @summary An Object for json data
	 */
	var test = {};

	var qlist = [];
	/**
	 * @summary limit size of question in list.
	 */
	var listlimitsize = 10;
	
	/**
	 * @summary This is used for describe that this assessment is accessed as content inside course.
	 */
	 var contentActivityId = '${contentActivityId}';
	 
	/*
	 * @summary call on body load.
	 */
	$(document).ready(function() {
		try {
			testDeatils('${testId}', '${userId}');
			$("#overlay").hide();
		} catch (err) {
			$("#overlay").hide();
			console.log(err.message);
		}
	});
</script>
</html>
