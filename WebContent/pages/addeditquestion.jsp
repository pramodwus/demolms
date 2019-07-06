<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
.badge {
	background-color: #05B26F;
}

.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

.cke_textarea_inline {
	border: 1px solid #ccc;
	padding: 10px;
	background-color: white;
	min-height: 50px;
}

.text_editor_margin {
	margin-top: 10px;
	margin-bottom: 10px;
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

.margin-left {
	margin-left: 1.5%;
}

.section {
	min-height: 50px;
	width: 100%;
	background-color: white;
	margin-top: 20px;
	margin-bottom: 50px;
	border: 1px solid #dedede;
}

.well {
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

.ui-tooltip {
	display: none;
}

.cke_textarea_inline img {
	max-width: 100% !important;
	height: auto !important;
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

</style>

</head>

<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<!-- Show lazy loader image -->
		<div id="overlay" class="overlay1"
			style="position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<!-- /.createTest -->
				<div id="createQuestion">
					<form action="question" method="post" id="questionForm"
						name="questionForm">
						<div id="questionPage">
							<section class="content">
							
							
								<!----------------------------------------------create question div ---------------------------------------------->
								<div class="row" id="createQuestionPage">
							
									<div class="col-xs-12 addQues">
										<div class="box box-solid"
											style="border: 1px solid #ccc; border-radius: 0;"> 
											<div class="box-body" id="addQuestionsBody">
												<div class="col-xs-12 form-group">
							
												<div class="col-xs-12 addQues">
													<div class="addQuestions" id="addQuestions">
													    <h2><span  id="question-type-header"></span>&nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-type-header-tooltip"><i class="fa fa-question"></i></span></h2>
														<input type="hidden" value="0" id="totalQuestions"
															name="totalQuestions">
														<!-------------------------------------------------------------------Question Creation form----------------------------------------------------------------------->
														<div id="questiondiv" style="margin-top:30px"></div>
														<!--------------------------------------------------------------------/.End of Question Creation form-------------------------------------------------------------->

													
												
												
												
													
			<!--------------------------------------------Auto Generated Tags--------------------------------------------------->
			 <div class="col-xs-12" id="textTags">
			<c:if test="${questionId== null }">
                           <c:forEach items="${testTags}" var="config"> 
                               <div class="col-xs-12" style="min-height: 20px"></div>
	                           <div class="col-xs-12 form-group question-tags" data-id="${config.id}">
	                                 <label style="width: 20%" class="pull-left">${config.name} :</label>
							         <div class="col-xs-8">
							            <c:choose>
							              <c:when test = "${config.type == 'select'}">
							                <select class="form-control question-tags-input" style="width:150px">
								                <c:forEach items="${config.configList}" var="configMapping">
								                     <option value="${configMapping.id}">${configMapping.value}</option> 
								                </c:forEach>
							                </select>
							               </c:when>
							               <c:otherwise>
							                <input type="text" class="form-control question-tags-input"style="width:150px" onkeyup="keyCustomQuestionTagValidate();">
							                <label class="requireFld question-tags-input-error"><spring:message code="msg.empty"/></label>
							               </c:otherwise>
							            </c:choose>  
							         </div>
	                           </div>
                           </c:forEach>
                           </c:if>
                           
                                                    	

                    	<c:if test="${questionId!= null }">
                           <c:forEach items="${testTags}" var="config"> 
                               <div class="col-xs-12" style="min-height: 20px"></div>
	                           <div class="col-xs-12 form-group question-tags" data-id="${config.id}">
	                                 <label style="width: 20%" class="pull-left">${config.name} :</label>
							         <div class="col-xs-8">
							            <c:choose>
							              <c:when test = "${config.type == 'select'}">
							              
							                <select class="form-control question-tags-input" style="width:150px">
								                <c:forEach items="${config.configList}" var="configMapping">
								                     <c:set var="isMatched" value="false"/>
								                     <c:forEach items="${listOfTags}" var="tag">
								                        <c:if test="${isMatched == false && config.id == tag.id && configMapping.id == tag.value}">
								                           <option value="${configMapping.id}" selected>${configMapping.value}</option>
								                           <c:set var="isMatched" value="true"/>
								                        </c:if>
								                     </c:forEach>
								                     <c:if test="${isMatched == false}">
								                        <option value="${configMapping.id}">${configMapping.value}</option>
								                     </c:if>
								                </c:forEach>
							                </select>
							               </c:when>
							               <c:otherwise>
							                        <c:set var="isMatched" value="false"/>
								                     <c:forEach items="${listOfTags}" var="tag">
								                        <c:if test="${isMatched == false && config.id == tag.id}">
								                           <input type="text" class="form-control question-tags-input" style="width:150px" value="${tag.value}" onkeyup="keyCustomQuestionTagValidate();">
								                           <label class="requireFld question-tags-input-error"><spring:message code="msg.empty"/></label>
								                           <c:set var="isMatched" value="true"/>
								                        </c:if>
								                     </c:forEach>
								                     <c:if test="${isMatched == false}">
								                         <input type="text" class="form-control question-tags-input" style="width:150px" onkeyup="keyCustomQuestionTagValidate();">
								                         <label class="requireFld question-tags-input-error"><spring:message code="msg.empty"/></label>
								                     </c:if>
							               </c:otherwise>
							            </c:choose>  
							         </div>
	                           </div>
                           </c:forEach>
                           </c:if>
                            </div>                        	
                           
                           
                           
                           </div>
										</div>		
			<!--------------------------------------------End of Auto Generated Tags--------------------------------------------------->
												

												<div class="col-xs-12 formBody" style="text-align: center">
													<button type="button"
														class="btn btn-default btn-flat button-width-large"
														onclick="backOnPage();" id="cancelQuestion">
														<spring:message code="lbl.cancel"/></button>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<button type="button"
														class="btn btn-flat btn-success button-width-large"
														onclick="saveQuestionInJson();" id="submitQuestion">
														<spring:message code="lbl.save"/></button>
												</div>
											</div>
											<br /> <br />
											<!-- /.box-body-->
										</div>
										<!-- /.box box-widget -->
									</div>
								</div>
								</div>
								<!----------------------------------------------end of create question div ---------------------------------------------->
							</section>
						</div>
						<!-- /#testReview -->
					</form>
				</div>
			</div>
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->
	<!------------------------------------------ Start of Question Error Alert---------------------------------------->
	<div class="modal fade" id="questionErrorAlert" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
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
	<!------------------------------------------ End of Question Error Alert---------------------------------------->
	

	<!------------------------------------------ Start of common alert box using in question---------------------------------------->
	<div class="modal fade" id="clearquestionAlert" tabindex="-1"
		role="dialog" aria-labelledby="clearquestionAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
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
	<!------------------------------------------ End  of common alert box using in question---------------------------------------->
</body>
<script type="text/javascript">
        var messages = new Array();
            messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true' />";
            messages['msg.submitdetail'] = "<spring:message code='msg.submitdetail' javaScriptEscape='true' />";
            messages['lbl.save'] = "<spring:message code='lbl.save' javaScriptEscape='true' />";
            messages['msg.sureforquestionupdate'] = "<spring:message code='msg.sureforquestionupdate' javaScriptEscape='true' />";
            messages['lbl.update'] = "<spring:message code='lbl.update' javaScriptEscape='true' />";
            messages['lbl.question'] = "<spring:message code='lbl.question' javaScriptEscape='true' />";
            messages['placeholder.question'] = "<spring:message code='placeholder.question' javaScriptEscape='true' />";
            messages['msg.empty'] = "<spring:message code='msg.empty' javaScriptEscape='true' />";
            messages['lbl.answer'] = "<spring:message code='lbl.answer' javaScriptEscape='true' />";
            messages['lbl.addanswer'] = "<spring:message code='lbl.addanswer' javaScriptEscape='true' />";
            messages['lbl.clearquestion'] = "<spring:message code='lbl.clearquestion' javaScriptEscape='true' />";
            messages['lbl.suboptions'] = "<spring:message code='lbl.suboptions' javaScriptEscape='true' />";
            messages['lbl.addsuboption'] = "<spring:message code='lbl.addsuboption' javaScriptEscape='true' />";
            messages['lbl.answerexplanation'] = "<spring:message code='lbl.answerexplanation' javaScriptEscape='true' />";
            messages['lbl.answernumber'] = "<spring:message code='lbl.answernumber' javaScriptEscape='true' />";
            messages['lbl.removeanswer'] = "<spring:message code='lbl.removeanswer' javaScriptEscape='true' />";
            messages['msg.suboption.required'] = "<spring:message code='msg.suboption.required' javaScriptEscape='true' />";
            messages['msg.clearquestion'] = "<spring:message code='msg.clearquestion' javaScriptEscape='true' />";
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
<script src="resources/js/questionlibrary/build/addeditquestion.js?v=2"></script>
<!-- Toggle Button script -->
<script src="resources/toggle/lib/ToggleSwitch.js"></script>
<!-- For Editor -->
<script src="resources/ckeditor/ckeditor.js"></script>
<!-- Select2 -->
<script src="resources/adminlte/plugins/select2/select2.full.min.js"></script>
<!-- Multi Type Question -->
<script
	src="resources/js/questionlibrary/build/multichoicetypequestion.js?v=4"></script>
<!-- Sort List Type Question -->
<script src="resources/js/questionlibrary/build/sortlisttypequestion.js?v=4"></script>
<!-- Choice Matrix Type Question -->
<script
	src="resources/js/questionlibrary/build/choicematrixtypequestion.js?v=3"></script>
<!-- classification type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/build/classificationtypequestion.js?v=3'/>"></script>
<!-- match list type question -->
<script
	src="<spring:url value='/resources/js/questionlibrary/build/matchlisttypequestion.js?v=3'/>"></script>
	
<script
 src="<spring:url value='/resources/adminlte/plugins/select2/select2.full.min.js'/>"></script>
 
 
 
 
 
 
 <script>

	var updatedquestionId = '${questionId}';
	var questionJSON;
	var questionSectionList = [];
	/**
	 * used as question id in new created question.
	 */
	var dynamicId = 1;
	/**
	 * @summary This would be call when body has been loaded.
	 */
	$(document)
			.ready(
					function() {
						
						$("#question-type-header-tooltip").tooltip({title: messages['lbl.questiontypedesc'], trigger: "hover",placement:"right"});
						/**
						 * @summary This is used for showing the data lost warning when user wants leave the page.
						 */
						$(window)
								.on(
										'beforeunload',
										function() {
											return '<spring:message code="msg.datalostwarning"/>';
										});
						/**
						 * @summary This is used adding css for adjust the image size in page.
						 */
						$('.imgset img').css({
							'max-width' : '100%',
							'height' : 'auto'
						});
						try {
							/**
							 * @summary removing the active class from left menu.
							 */
							$(".treeview").removeClass("active");
							/**
							 * @summary adding the active class on listm item in left menu.
							 */
							$("#questionList").addClass("active");
							
							$("#questionList .treeview-menu > #questionList").addClass("active");
							/**
							 * @summary getting type of question
							 */
							var questionType = '${type}';
							/**
							 * @summary create json array for question.
							 */
							createQuestionArray();
							/**
							 * @summary If user comes in edit mode for a particular question.
							 */
							if (updatedquestionId > 0) {
								/**
								 * @summary assign getting question json for question detail.
								 */
								questionJSON = ${questionJson}
								;
								/**
								 * @summary checking question json has question list.
								 */
								if (questionJSON.questionList != null
										&& questionJSON.questionList.length > 0) {
									/**
									 * @summary assign the gotten question json. 
									 */
									questionSectionList[0].questionList = questionJSON.questionList
									/**
									 * @summary redirect on edit mode for this question. 
									 */
									editParticularQuestion(updatedquestionId);
								} else {
									/**
									 * make empty to json array
									 */
									questionJSON = [];
									/**
									 * @summary create question acording to provided question type.
									 */
									showCreateQuestionPage(1);
								}

							}
							/**
							 * @summary if user has provided any question type
							 */
							else if (questionType > 0) {
								/**
								 * make empty to json array
								 */
								questionJSON = [];
								/**
								 * @summary create question acording to provided question type.
								 */
								showCreateQuestionPage(questionType);
							} else {
								/**
								 * @summary create question acording to provided question type.
								 */
								showCreateQuestionPage(1);
							}
							/**
							 * @summary hide the loader.
							 */
							$("#overlay").hide();

						} catch (err) {
							/**
							 * @summary hide the loader.
							 */
							$("#overlay").hide();
							console.log(err.message);
						}
					});
	
	function validateQuestionTags() {
		return false;
	} 

	
	
</script>
</html>