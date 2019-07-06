<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>
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
	border: 1px solid #F0F0F0;;
	margin: 0px;
	height: 100%
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

ul.pagination li {
	display: inline;
}

.cursor {
	cursor: pointer;
}

.lrn_btn_sort {
	background-color: #ffffff;
	color: #333333;
	border: 0px solid #ccc;
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
	width: 100%;
	line-height: 2.2em;
	padding: 0.5em;
	transition: none;
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

.match_list_type_ques_col2 img {
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
<body class="hold-transition skin-blue sidebar-mini body-color" style="min-width:500px;overflow-x: auto;">
	<div class="col-sm-12">
		<div id="overlay" class="overlay1"
			style="position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<div class="row" style="background-color: white; min-height: 50px;">
		        <div class="col-sm-1"></div>
				<div class="col-sm-8" style="padding-left: 0px">
				<div class="input-group">
				<div class="input-group-addon"><h4 style="text-align:left"><span id="testname"></span> (<spring:message code="lbl.assessment"/>)</h4></div>
				<div class="input-group-addon">
				<h4 style="text-align:right"><spring:message code="lbl.currentsection"/></h4>
				</div>
				<div class="input-group-addon">
				<select class="form-control"
						style="margin-top: 3%; margin-bottom: 3%;min-width:100px" id="sectionSelectList"
						onchange="changeSection()">
					</select>
				</div>
				</div>
			</div>
		</div>
		<section class="content" style="margin-top: 10px">
			<div class="col-sm-1"></div>
			<div id="questionlist" class="col-sm-8 row" style="min-height: 550px">
				<!-- Content Header (Page header) -->
				<div class="row"
					style="background-color: white; padding-bottom: 10px; min-height: 370px">
					<div style="height: 50px; text-align: center" class="row">
						<h4>
							&emsp; <spring:message code="lbl.question"/>: <span id="questionNo"></span> of <span
								id="totalQuestions"></span>&emsp;<span
								id="selectedSectionForCurrentQues"></span>
						</h4>
					</div>
					<div
						style="height: 68px; border-bottom: 1px solid #F0F0F0; border-top: 1px solid #F0F0F0; margin-top: 0px;">
						<div class="input-group question-details-header">
						<div class="input-group-addon">
							<div id="divtime" class="pull-left" style="padding-left:10%">
								<i class=" fa fa-clock-o color-mainblue line-height"></i>&nbsp;<span
									id="time"></span>
							</div>
							</div>
						
						<div class="input-group-addon">
										<h4>
											<span id="question_mark"></span>&nbsp;&nbsp;<spring:message code="lbl.mark"/>
										</h4>
						</div>
						<div class="input-group-addon">
							<div>
								<div class="buttons pull-right">
									<ul class="line-height">
										<li class="navlist"></li>
										<li class="navlist li-border bookmark-click-green"></li>
										<li class=" navlist" id="pausebutton" onclick="pause()"
											style="visibility: hidden"><i
											class="fa fa-pause color-mainblue" id="pause"></i></li>
										<li class="navlist" style="cursor: pointer" id="mathformula"
											data-toggle="modal" data-target="#formulaPopup"><i
											class="color-mainblue">f(x)</i></li>
										<li class="navlist"></li>
									</ul>
								</div>
							</div>
						</div>
						</div>
						<!-- /.description-block -->
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12 ">
						<div id="allquestion"></div>
						<!-- /. allquestion-->
					</div>
				</div>

				<div
					style="margin-top: 10px; margin-bottom: 10px; height: 45px; width: 104%"
					class="btn-group row">
					<button type="button" id="quit" class="btn btn-success"
						style="width: 25%; height: 100%; border: 1px solid #F0F0F0"
						onclick="quit()"><spring:message code="lbl.quit"/></button>
					<button type="button" id="whiteSpace" class="btn btn-success"
						style="width: 25%; background-color: white; height: 100%; border: 1px solid #F0F0F0; cursor: default"
						disabled></button>
					<button type="button" class="btn btn-success" id="previous"
						onclick="previous(1,0)"
						style="width: 25%; height: 100%; border: 1px solid #F0F0F0"
						disabled>
						<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;
						<spring:message code="lbl.previous"/>
					</button>
					<button type="button" class="btn btn-success" id="next"
						onclick="next(1,1)"
						style="width: 25%; height: 100%; border: 1px solid #F0F0F0">
						<spring:message code="lbl.next"/>
						&nbsp;&nbsp;&nbsp;<font size="4px"><i
							class="fa fa-angle-double-right"></i></font>
					</button>
				</div>
			</div>
			<div class="col-sm-3" style="margin: 10px; margin-top: 2px;margin-right:0px;"
				id="allQuestionListDiv">
				<h5 id="sectiontitle"></h5>
				<div class="opposite_triangle"></div>
			</div>
		</section>
	</div>
	<!-- math formula popup -->
	<div class="modal fade" id="formulaPopup" tabindex="-1" role="dialog"
		aria-labelledby="formulaPopup">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<p id="formulaContent" class="imgset"></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal"><spring:message code="lbl.close"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['lbl.section'] = "<spring:message code='lbl.section' javaScriptEscape='true' />";
        messages['lbl.question'] = "<spring:message code='lbl.question' javaScriptEscape='true' />";
        messages['lbl.next'] = "<spring:message code='lbl.next' javaScriptEscape='true' />";
        messages['lbl.previous'] = "<spring:message code='lbl.previous' javaScriptEscape='true' />";
        messages['lbl.nextsection'] = "<spring:message code='lbl.nextsection' javaScriptEscape='true' />";
        messages['lbl.previoussection'] = "<spring:message code='lbl.previoussection' javaScriptEscape='true' />";
        messages['lbl.explanation'] = "<spring:message code='lbl.explanation' javaScriptEscape='true' />";
        messages['lbl.source'] = "<spring:message code='lbl.source' javaScriptEscape='true' />";
        messages['lbl.target'] = "<spring:message code='lbl.target' javaScriptEscape='true' />";
 </script>
<!-- common operation -->
<script
	src="<spring:url value='/resources/js/test/author/preview/testpreview.js'/>"></script>
<!-- Multiple Choice Type Question -->
<script
	src="<spring:url value='/resources/js/test/author/preview/multiplechoicetypequestion.js'/>"></script>
<!-- Sort List Type Question -->
<script
	src="<spring:url value='/resources/js/test/author/preview/sortlisttypequestion.js'/>"></script>
<!-- Choice Matrix Type Question -->
<script
	src="<spring:url value='/resources/js/test/author/preview/choicematrixtypequestion.js'/>"></script>
<!-- Classification Type Question -->
<script
	src="<spring:url value='/resources/js/test/author/preview/classificationtypequestion.js'/>"></script>
<!-- Match List Type Question -->
<script
	src="<spring:url value='/resources/js/test/author/preview/matchlisttypequestion.js'/>"></script>

<script src="<spring:url value='/resources/js/jquery-ui.js'/>"></script>
<script>
	/**
	 * @summary An Object for json data.
	 */

	var test = ${test};

	var qlist = [];
	/**
	 * @summary size of question in list.
	 */
	var listlimitsize = 10;
	/**
	 * @summary would be call on body load.
	 */
	$(document).ready(function() {
		try{
		var testId = getUrlVars()["testId"];
		var action = getUrlVars()["action"];
		if (action == 'frame') {
			$("#quit").hide();
			$("#whiteSpace").css('width', '50%');
			$("#questionlist").css('min-height', '100%');
		}
		$("#time").html(test.testTime);
		if (test.testTime == null) {
			$("#time").html('<spring:message code="lbl.notimebond"/>');
			$("#divtime").css("visibility", "hidden");
		}
		$("#testname").html(test.testName);
		addQuestionList();
		$('.imgset img').css({
			'display' : 'block',
			'width' : '100%',
			'max-width' : '100%',
			'height' : 'auto'
		});
		$("#overlay").hide();
		}catch(err){
			$("#overlay").hide();
			console.log(err.message);
		}
	});
</script>
</html>