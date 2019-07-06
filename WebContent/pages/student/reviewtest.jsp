<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/pages/include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
.imgset img{
max-width:100% !important;
height:auto !important;
}
.no-margin{
margin:0px;
}
.correct_answer_table>table>tbody>tr {
	padding: 3px;
	height: 3.5em;
	min-height: 3.5em;
}

.correct_answer_table>table>tbody>tr>td {
	padding: 3px !important;
	border: 2px solid #ccc !important;
}

.given_answer_table>table>tbody>tr {
	padding: 3px;
	height: 4.5em;
	min-height: 4.5em;
}

.given_answer_table>table>tbody>tr>td {
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

.padding-left {
	padding-left: 10px;
}

.correct_answer_table .input-group {
	border: 1px solid #ccc;
}

.correct_answer_table .input-group-addon {
	color: #ECF0F5;
	background-color: #ccc !important;
}

.given_answer_table .input-group-first {
	border: 1px solid #ccc;
	min-height: 50px;
}

.given_answer_table .input-group-first .input-group-addon {
	color: #ECF0F5;
	background-color: #ccc !important;
}

.button-color-green {
	color: #ECF0F5;
	background-color: #00a65a !important;
}

.border-green {
	border: 1px solid green !important;
}

.border-red {
	border: 1px solid red !important;
}

.border-blue {
	border: 1px solid #4a5777 !important;
}

.active {
	background-color: black;
	color: white !important;
}

.active>input-group>input-group-addon>.fa {
	color: white
}

.given_answer_table th, .correct_answer_table th {
	border: 0px solid white !important;
}

.bg-blue {
	color: #ECF0F5;
	background-color: #4a5777 !important;
}

.btn-social-icon.btn-xs {
	width: 30px !important;
}

.btn-check {
	color: #fff;
	background-color: #05B26F;
	border-color: #05B26F;
}

.btn-close {
	color: #fff;
	background-color: #05B26F;
	border-color: #05B26F;
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
	height: 30px;
	min-height: 30px !important;
}

.classificationtable td:nth-child(1), .classificationtable th:nth-child(1)
	{
	border: 0px solid black !important;
}

.answerDivInClassificationTypeQuestion,.answerDivInMatchListTypeQuestion {
	padding: 5px;
	padding-left: 10px;
	padding-right: 10px;
	margin: 10px;
	min-width: 80px;
	max-width: 200px;
	border: 1px solid #ccc;
	background-color: white;
	margin-bottom: 0px;
	float: left;
}

.cellOrderNumberInClassificationTypeQuestion ,.cellOrderNumberInMatchListTypeQuestion{
	padding: 10px;
	padding-top: 6px;
	margin: 10px;
	width: 40px;
	height: 35px;
	border: 1px solid #9F9F9F;
	background-color: #9F9F9F;
	margin-bottom: 0px;
	float: left;
	color: #fff;
}

.bg-light-red {
	background-color: #FBE3E3 !important;
	border: 1px solid #FBE3E3 !important;
}

.bg-light-green {
	background-color: #EBF6E7 !important;
	border: 1px solid #EBF6E7 !important;
}

.bg-light-gray {
	background-color: #F0F0F0 !important;
	border: 1px solid #F0F0F0 !important;
}

.correctAnswerDivInClassificationQuestion,.correctAnswerDivInMatchListQuestion {
	background-color: #FCFCD3;
	min-height: 100px;
	margin: 0px;
	margin-top: 10px;
	padding: 10px;
}

.answerCellInClassificationTypeQuestion,.answerCellInMatchListTypeQuestion {
	padding: 5px;
	padding-left: 10px;
	padding-right: 10px;
	margin: 10px;
	min-width: 80px;
	max-width: 200px;
	border: 1px solid #EBEBEB;
	background-color: #EBEBEB;
	margin-bottom: 0px;
	float: left;
}

.correctAnswerContainDivInClassificationQuestion,.correctAnswerContainDivInMatchListQuestion {
	padding: 5px;
	margin: 10px;
	min-width: 100px;
	max-width: 400px;
	border: 1px solid #F0F0F0;
	background-color: #F0F0F0;
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
.match_list_type_ques_col2 img{
    max-width:100% !important;
    height:auto !important;
}
.match_list_type_question {
	padding: 0.7em 1em;
	background: transparent;
	text-align: center;
	border: 1px solid #e8e8e8;
	min-height: 2.7em;
}

.match_list_type_question img,.correctAnswerContainDivInMatchListQuestion img{
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
	border: 1px solid #9f9f9f;
	min-height: 2em;
	min-width: 2em;
	text-align: center;
}

.match_list_type_question_res_container {
	margin: 5px !important;
	max-width: 100% !important;
}

.match_list_type_question_res_container .answerDivInMatchListTypeQuestion
	{
	margin: 5px !important;
	max-width: 100% !important;
	padding:0px !important;
}

.match_type_ques_responseIndex {
    display: table-cell;
    width: 2.2em;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    padding: 0.8em;
    margin-left: 0px !important;
}
.match_list_option_text{
display: table-cell;
padding: 2.5em;
}
.bg-dark-gray{
    border: 1px solid #9F9F9F !important;
    background-color: #9F9F9F !important;
    color:#fff !important;
}
.correctAnswerContainDivInClassificationQuestion img{
max-width:100% !important;
height : auto !important;
}
</style>
</head>
<body
	class="hold-transition skin-blue sidebar-mini page-background-color">
	<div class="row" style="margin: 0">
		<div id="overlay" class="overlay1"
			style="position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<div class="" id="reviewModal">
			<input type="hidden" id="idd" value="${userTestAttemptId}"> <input
				type="hidden" id="userid" value="${userId}">
			<div class=" col-md-12 col-sm-12 col-xs-12">
				<div class="content">
					<div class="box-body">
						<div id="questionlist" class="row" style="display: none">
							<div class="col-md-12 col-sm-12 col-xs-12 ">
								<section class="content-header padding box-noborder "
									style="background: white">
									<div class="row"
										style="background-color: white; min-height: 50px;">
										<div class="col-sm-12" style="padding-left: 0px">
											<h4 class="col-sm-5">
												<span id="testname"></span> (<spring:message code="lbl.assessment" text="Assessment"/>)
											</h4>
											<div class="col-sm-2"></div>
											<h4 class="col-sm-2">
												<span class="pull-right"><spring:message code="lbl.currentsection" text="Current Section"/></span>
											</h4>
											<div class="col-sm-3">
												<select class="form-control"
													style="margin-top: 3%; margin-bottom: 3%;"
													id="sectionSelectList" onchange="changeSection()">
												</select>
											</div>
										</div>
									</div>
								</section>
								<div class="col-md-12 col-sm-12 col-xs-12 "
									style="background: white">

									<div class=" page-background-color">
										<div class="row" style="height: 45px">
											<div class="col-md-5 col-sm-5 col-xs-4 ">
												<div>
													<h4 class=" pull-left">
														&emsp; <spring:message code="lbl.question" text="Question"/>:<span id="questionNo"></span> of <span
															id="totalQuestions"></span>
													</h4>
												</div>
												<!-- /.description-block -->
												<div class="clearfix visible-sm-block"></div>
											</div>
											<div class=" col-md-4 col-sm-4 col-xs-3">
												<div id="divtime">
													<i class=" fa fa-clock-o color-mainblue line-height"></i>&nbsp;<span
														id="time"></span>
												</div>
												<!-- /.description-block -->
												<!-- /.col -->
											</div>
											<div class="col-md-3 col-sm-3 col-xs-5">
												<div>
													<div class="buttons pull-right ">
														<ul class="line-height">
															<li class="navlist">
															<li class=" navlist li-border bookmark-click-green">
															<li class=" navlist">
														</ul>
													</div>
												</div>
											</div>
										</div>
										<!-- /.description-block -->
										<!--</div>-->
									</div>
									<div id="allquestion"></div>
									<!-- /. allquestion-->
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
<script>
var messages = new Array();
messages['lbl.section'] = "<spring:message code='lbl.section' text='Section' javaScriptEscape='true'/>";
messages['lbl.correctanswer'] = "<spring:message code='lbl.correctanswer' text='Correct Answer' javaScriptEscape='true'/>";
messages['lbl.wronganswer'] = "<spring:message code='lbl.wronganswer' text='Wrong Answer' javaScriptEscape='true'/>";
messages['lbl.explanation'] = "<spring:message code='lbl.explanation' text='Explanation' javaScriptEscape='true'/>";
messages['lbl.quit'] = "<spring:message code='lbl.quit' text='Quit' javaScriptEscape='true'/>";
messages['lbl.next'] = "<spring:message code='lbl.next' text='Next' javaScriptEscape='true'/>";
messages['lbl.previous'] = "<spring:message code='lbl.previous' text='Previous' javaScriptEscape='true'/>";
messages['lbl.correctanswers'] = "<spring:message code='lbl.correctanswers' text='Correct Answers' javaScriptEscape='true'/>";
messages['lbl.givenorder'] = "<spring:message code='lbl.givenorder' text='Given Order' javaScriptEscape='true'/>";
messages['lbl.correctorder'] = "<spring:message code='lbl.correctorder' text='Correct Order' javaScriptEscape='true'/>";
</script>
<!--For Common Operation -->
<script
	src="<spring:url value='/resources/js/test/learner/review/reviewtest.js'/>"></script>
<!-- Sort List Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/review/multiplechoicetypequestion.js'/>"></script>
<!-- Sort List Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/review/sortlisttypequestion.js'/>"></script>
<!-- Choice Matrix Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/review/choicematrixtypequestion.js'/>"></script>
<!-- Classification Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/review/classificationtypequestion.js'/>"></script>
<!-- Classification Type Question -->
<script
	src="<spring:url value='/resources/js/test/learner/review/matchlisttypequestion.js'/>"></script>
<script type="text/javascript">
	/**
	 * An Object for json data
	 */
	var test = {};
	/**
	 * @summary This would be call when body would be loaded.
	 */
	$(document).ready(function() {
		try {
			getreviewdata();
			$("#overlay").hide();
		} catch (err) {
			$("#overlay").hide();
			console.log(err.message);
		}
	});
</script>
</html>