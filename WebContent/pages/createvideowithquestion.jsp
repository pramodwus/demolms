<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
	<head>
		<%@ include file="include.jsp"%>
		<link rel="stylesheet" href="resources/css/custom.css">
		<style>
			.content-wrapper {
				margin: auto;
				margin-left:230px;
			}
			@media only screen and (min-width: 767px) {
			    .content-wrapper {
			        /* background-color: yellow; */
			        margin-left:0px;
			    }
			}
			@media only screen and (max-width: 760px) {
			  .content-wrapper {
			       /*  background-color: pink; */
			        margin-left:0px;
			    }
			}  
			.cke_textarea_inline {
			    background-color: white;
			    border: 1px solid #ccc;
			    min-height: 50px;
			    padding: 10px;
			}
			.showquestion{
				border: 1px solid #000000; 
				padding:5px 10px;
			}
			.badge {
				background-color: #05B26F;
			}
		
		</style>
		<script type="text/javascript">
	      		var dynamicId = 1;
	      		var questionList = [];
	      		var timeList = [];
		</script>
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
			<%-- <%@ include file="leftmenu.jsp"%> --%>
			<div class="col-sm-12" >		
				<div class="content-wrapper">
					<section class="content-header">
						<div class="pull-left">
							<c:if test="${mode==0}">
								<h3 style="margin:0"><spring:message code="lbl.createvideowithquestion" text="Create Video With Question"/></h3>
							</c:if>
							<c:if test="${mode==1}">
								<h3 style="margin:0"><spring:message code="lbl.createpdfwithquestion" text="Create PDF With Question"/></h3>
							</c:if>
						</div>			
						<br/>			
					</section>
					<section class="content">
						<form id="form" name="uploadcontentform" enctype="multipart/form-data">
							<div class="row">		
								<div class="col-md-12">	
									<%-- For Video Adding --%>		
									<c:if test="${mode==0}"> 		
										<div class="box no-border col-md-12" style="margin: 0px;">
											<!-- /.box-header -->
											<div class="box-body no-padding">
					  							<div class="box-body" style="width: 50%; float: left;">
	                    							<div class="form-group">
	                      								<label><spring:message code="lbl.title" text="Title"/>: &nbsp;</label><c:out value="${content.contentName}"></c:out>
	                      							</div>
	                    							<div class="form-group">
	                   							 		<div class="">
							                          		<video id="myVideo" style="width: 98%;" controls >
								                          		<source src="${content.contentPath}">
								                          	</video>
	                        							</div>
	                        							<button type="button" id="play" class="margin-left btn btn-flat btn-success button-width-large" onclick="playVid();"><span><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;<spring:message code="lbl.addquestion" text="Add Question"/></span></button>
	                          						</div>
	                  							</div><!-- /.box-body -->
	                  							
												<div class="box-body" style="width: 50%;float: right;max-height: 600px;overflow: auto;">
													<div id="videoQuestion" style="display: none;">
														<div class="form-group">
															<label><spring:message code="lbl.choosequestiontype" text="Choose Question Type"/></label>
															<select class="form-control" id="selectedQuestionType" onchange="changeAnswerTypeForVideo();">
																<c:forEach items="${quesTypeList}" var="quesTypeList" end="1">
																	<option value="${quesTypeList.questionType}">${quesTypeList.questionTypeName}</option>
																</c:forEach>
															</select>
														</div>
														<div class="form-group">
															<label><spring:message code="lbl.question" text="Question"/></label>
															<textarea class="form-control myTextEditor" rows="4" id="question" name="question"></textarea>
														</div>
														<div class="col-xs-12" ><label class="requireFld" id="questionError"><spring:message code="msg.empty" text="This field is required."/></label></div>
														<div class="form-group">
															<label><spring:message code="lbl.answers" text="Answers"/> (<spring:message code="lbl.clickforrightanswer" text="Please click for right answer."/>)</label>
														</div>
														<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="selectError"><spring:message code="lbl.clickforrightanswer" text="Please click for right answer."/></label></div></div>
														
														<div id="ansType">
														</div>
														<div class="col-xs-12" style="min-height: 10px"></div>
														<div class="col-xs-12 formBody">
															<div class="col-xs-1"></div>
															<div class="col-xs-11">
																<button type="button" class="margin-left btn btn-flat btn-success button-width-large" onclick="addOptions();"><span><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;<spring:message code="lbl.addanswer" text="Add Answer"/></span></button>
															</div>
														</div>
														<div class="col-xs-12" style="min-height: 10px"></div>
														<div class="col-xs-12 formBody">
															<div class="col-xs-6">
																<button type="button" class="btn btn-default btn-flat button-width-large" onclick="backToVideo();"><span><spring:message code="lbl.back" text="Back"/></span></button>
															</div>
															<div class="col-xs-6">
																<button type="button" class="margin-left btn btn-flat btn-success button-width-large" id="savequestionbutton"></button>
																<!-- <span id="plus_minus" style="display:none">
																 <a id="plus" onclick="extendtime(1)">Inc.</a>&nbsp;&nbsp;
																 <a id="minus" onclick="extendtime(0)">Dec.</a>
																</span> -->
															</div>
														</div>
													</div>
													<div id="videoQuestionAdd" style="display: none;">
													</div>
												</div>		
											</div>
										</div>
										<div style="clear: both;">
											<input type="hidden" id="contentId" name="contentId" value="${content.contentId}">
											<input type="hidden" id="totalOptions" name="totalOptions" value="0">
											<input type="hidden" id="time" name="time">
										</div>
	           							<div class="box-footer">
	               							<button class="btn btn-default btn-flat button-width-large" id="cancel" type="button"><spring:message code="lbl.cancel" text="Cancel"/></button>
	               							<button class="btn btn-success btn-flat button-width-large pull-right" id="savebtn" onclick="videoQuestionDataSubmit()" type="button"><spring:message code="lbl.submit" text="Submit"/></button>
	           							</div>
           							</c:if>
           							<%-- For PDF Adding --%>
           							<c:if test="${mode==1}"> 		
										<div class="box no-border col-md-12" style="margin: 0px;">
											<!-- /.box-header -->
											<div class="box-body no-padding">
					  							<div class="box-body" style="width: 50%; float: left;">
	                    							<div class="form-group">
	                      								<label><spring:message code="lbl.title" text="Title"/>: &nbsp;</label><c:out value="${content.contentName}"></c:out>
	                      							</div>
	                    							<div class="form-group">
	                   							 		<div>
	                   							 			<object type="text/html" id="pafContent" data="https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc=${content.contentPath}/page_1.pdf" style="width: 98%; height: 450px"></object>								                          	
	                        							</div>
	                        							<div style="float: left; width: 30%; padding: 2%;">
							                          		<button disabled="disabled" type="button" id="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCall(0,'pdf');"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp;<spring:message code="lbl.previous" text="Previous"/></span></button>
							                          	</div>
							                          	<div style="float: left; width: 40%; padding: 2%; text-align: center;">
							                          	    <spring:message code="lbl.showingpage" text="Showing <span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span> of ${content.numPages}"  arguments="<span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span>^${content.numPages}" htmlEscape="false" argumentSeparator="^"/>
							  
							                          	</div>
							                          	<div style="float: right; width: 30%; padding: 2%;">
							                          		<button type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCall(1,'pdf');"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
							                          	</div>
							                          	<div style="width: 98%;">
	                        								<button type="button" id="play" style="float: right;" class="margin-left btn btn-flat btn-success button-width-large" onclick="addQuestionForPDF();"><span><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;<spring:message code="lbl.addquestion" text="Add Question"/></span></button>
	                        							</div>
	                          						</div>
	                  							</div><!-- /.box-body -->
	                  							
												<div class="box-body" style="width: 50%;float: right;max-height: 600px;overflow: auto;">
													<div id="videoQuestion" style="display: none;">
														<div class="form-group">
															<label><spring:message code="lbl.choosequestiontype" text="Choose Question Type"/></label>
															<select class="form-control" id="selectedQuestionType" onchange="changeAnswerTypeForVideo();">
																<c:forEach items="${quesTypeList}" var="quesTypeList" end="1">
																	<option value="${quesTypeList.questionType}">${quesTypeList.questionTypeName}</option>
																</c:forEach>
															</select>
														</div>
														<div class="form-group">
															<label><spring:message code="lbl.question" text="Question"/></label>
															<textarea class="form-control myTextEditor" rows="4" id="question" name="question"></textarea>
														</div>
														<div class="col-xs-12" ><label class="requireFld" id="questionError"><spring:message code="msg.empty" text="This Field is required."/></label></div>
														<div class="form-group">
															<label><spring:message code="lbl.answers" text="Answers"/> (<spring:message code="lbl.clickforrightanswer" text="Please click for right answer."/>)</label>
														</div>
														<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="selectError"><spring:message code="lbl.clickforrightanswer" text="Please click for right answer."/></label></div></div>
														
														<div id="ansType">
														</div>
														<div class="col-xs-12" style="min-height: 10px"></div>
														<div class="col-xs-12 formBody">
															<div class="col-xs-1"></div>
															<div class="col-xs-11">
																<button type="button" class="margin-left btn btn-flat btn-success button-width-large" onclick="addOptions();"><span><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;<spring:message code="lbl.addanswer" text="Add Answer"/></span></button>
															</div>
														</div>
														<div class="col-xs-12" style="min-height: 10px"></div>
														<div class="col-xs-12 formBody">
															<div class="col-xs-6">
																<button type="button" class="btn btn-default btn-flat button-width-large" onclick="backToPDF();"><span><spring:message code="lbl.back" text="Back"/></span></button>
															</div>
															<div class="col-xs-6">
																<button type="button" class="margin-left btn btn-flat btn-success button-width-large" id="savequestionbutton"></button>																
															</div>
														</div>
													</div>
													<div id="videoQuestionAdd" style="display: none;">
													</div>
												</div>		
											</div>
										</div>
										<div style="clear: both;">
											<input type="hidden" id="contentId" name="contentId" value="${content.contentId}">
											<input type="hidden" id="contentUrl" name="contentUrl" value="https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc=${content.contentPath}">
											<input type="hidden" id="contentPages" name="contentPages" value="${content.numPages}">
											<input type="hidden" id="totalOptions" name="totalOptions" value="0">
											<input type="hidden" id="time" name="time" value="1">
											<input type="hidden" id="currtime" name="currtime" value="1">
										</div>
	           							<div class="box-footer">
	               							<button class="btn btn-default btn-flat button-width-large" id="cancel" type="button"><spring:message code="lbl.cancel" text="Cancel"/></button>
	               							<button class="btn btn-success btn-flat button-width-large pull-right" id="savebtn" onclick="videoQuestionDataSubmit()" type="button"><spring:message code="lbl.submit" text="Submit"/></button>
	           							</div>
           							</c:if>
           							<c:if test="${mode==2}"> 		
										<div class="box no-border col-md-12" style="margin: 0px;">
											<!-- /.box-header -->
											<div class="box-body no-padding">
					  							<div class="box-body" style="width: 50%; float: left;">
	                    							<div class="form-group">
	                      								<label><spring:message code="lbl.title" text="Title"/>: &nbsp;</label><c:out value="${content.contentName}"></c:out>
	                      							</div>
	                    							<div class="form-group">
	                   							 		<div>
	                   							 			<img id="pafContent" src="${content.contentPath}/page_1.png" style="width: 98%; height: 450px"/>							                          	
	                        							</div>
	                        							<div style="float: left; width: 30%; padding: 2%;">
							                          		<button disabled="disabled" type="button" id="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="slideCall(0);"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp;<spring:message code="lbl.previous" text="Previous"/></span></button>
							                          	</div>
							                          	<div style="float: left; width: 40%; padding: 2%; text-align: center;">
							                          	    <spring:message code="lbl.showingpage" text="Showing <span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span> of ${content.numPages}"  arguments="<span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span>^${content.numPages}" htmlEscape="false" argumentSeparator="^"/>
							  
							                          	</div>
							                          	<div style="float: right; width: 30%; padding: 2%;">
							                          		<button disabled="disabled" type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="slideCall(1);"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
							                          	</div>
	                          						</div>
	                  							</div><!-- /.box-body -->
	                  							
												<div id="pptVoiceDiv" class="box-body" style="width: 50%;float: right;max-height: 600px;overflow: auto;">
													<div id="addVoice1">
														<div class="form-group">
															<div class="input-group" style="float: left">
																<input type="radio" checked="checked" class="flat-red questionType"  name="answerStatus1" id="answerStatus11"/>&nbsp;&nbsp;
															</div>
															<div style="padding:2px;float: left;"><label><spring:message code="lbl.uploadvoicefile" text="Upload Voice File (Only mp3 allow)"/></label></div>
															<div style="padding:2px;float: right;"><a href="#" id="editButton1" style="display: none" onclick="editaddedAudio(1)"><font color="#00A65A"><i class="fa fa-edit"></i></font></a>&nbsp;&nbsp;</div>
															<div style="padding:2px; clear: both;"><input type="file" id="uploadfile1" name="uploadfile1" onclick="hideRequiredError()"></div>
															<div class="col-xs-12" >
																<label class="requireFld" id="fileUploadError1"><spring:message code="msg.pleaseuploadfile" text="Please upload file."/></label>
																<label class="requireFld" id="fileUploadSpaceError1"><spring:message code="msg.pleaseuploadspacefile" text="Your total space is running full. Please contect to admin."/></label>
																<label class="requireFld" id="fileUploadExtError1"><spring:message code="msg.pleaseuploadextfile" text="Please upload correct file (Only mp3)."/></label>
															</div>
														</div>
														<div class="form-group" style="display: none;"><spring:message code="lbl.or" text="OR"/></div>
														<div class="form-group" style="display: none;">
															<div class="input-group" style="float: left">
																<input type="radio" class="flat-red questionType"  name="answerStatus1" id="answerStatus12"/>&nbsp;&nbsp;
															</div>
															<div style="padding:2px"><label><spring:message code="lbl.recordvoice" text="Record Voice"/></label></div>
															<input type="file" id="recordfile1" name="recordfile1" disabled="disabled">
														</div>
														<div class="form-group"><spring:message code="lbl.or" text="OR"/></div>
														<div class="form-group">
															<div class="input-group" style="float: left">
																<input type="radio" class="flat-red questionType"  name="answerStatus1" id="answerStatus13"/>&nbsp;&nbsp;
															</div>
															<div style="padding:2px"><label><spring:message code="lbl.provideslideholdtime" text="Provide slide hold time(In Second)"/></label></div>
															<input type="text" class="form-control eOnlyNum" id="pptHoldNumber1" name="pptHoldNumber1" onclick="hideRequiredError()" maxlength="3">
															<div class="col-xs-12" >
																<label class="requireFld" id="requiredError1"><spring:message code="msg.empty" text="This Field is required."/></label>
															</div>
														</div>
														<div class="col-xs-12" style="min-height: 10px"></div>
														<div class="col-xs-12 formBody">
															<div class="col-xs-12">
																<button type="button" class="margin-left btn btn-flat btn-success button-width-large" id="savequestionbutton1" onclick="savePPTSlideAudio(1)"><span><spring:message code="lbl.saveatslide" text="Save at Slide #slidenum" arguments="1" argumentSeparator=";"/></span></button>
															</div>
														</div>
														<div class="col-xs-12 formBody" id="editButtons1" style="display: none;">
															<div class="col-xs-6">
																<button type="button" class="btn btn-default btn-flat button-width-large" onclick="backToPPT(1);"><span><spring:message code="lbl.cancel" text="Cancel"/></span></button>
															</div>
															<div class="col-xs-6">
																<button type="button" class="margin-left btn btn-flat btn-success button-width-large" id="savequestionbutton" onclick="updatePPTAudio(1);"><span><spring:message code="lbl.update" text="Update"/></span></button>
															</div>
														</div>
													</div>
												</div>		
											</div>
										</div>
										<div style="clear: both;">
											<input type="hidden" id="contentId" name="contentId" value="${content.contentId}">
											<input type="hidden" id="contentUrl" name="contentUrl" value="${content.contentPath}">
											<input type="hidden" id="contentPages" name="contentPages" value="${content.numPages}">
											<input type="hidden" id="availableSpace" name="availableSpace" value="${availableSpace}">
											<input type="hidden" id="time" name="time" value="1">
											<input type="hidden" id="reachtime" name="reachtime" value="1">
										</div>
	           							<div class="box-footer">
	               							<button class="btn btn-default btn-flat button-width-large" id="cancel" type="button"><spring:message code="lbl.cancel" text="Cancel"/></button>
	               							<button disabled="disabled" class="btn btn-success btn-flat button-width-large pull-right" id="savebtn" onclick="pptAudioDataSubmit()" type="button"><spring:message code="lbl.submit" text="Submit"/></button>
	           							</div>
           							</c:if>
           							<input type="hidden" id="mode" value="${mode}">
           						</div>
           					</div>	
   						</form>
					</section>
				</div>
				<div class="control-sidebar-bg"></div>
			</div>
			<!-- ./wrapper -->
		</div>	
		
		<div class="modal fade" id="contentErrorAlert" tabindex="-1" role="dialog" aria-labelledby="testAlert">
			<div class=" col-md-12 col-sm-12 col-xs-12">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-body" style="text-align: center">
							<h3></h3>
							<p></p>
							<button type="button"
								class="btn btn-success button-width-large"
								data-dismiss="modal"><spring:message code="lbl.ok" text="Ok"/></button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
        var messages = new Array();
        messages['msg.empty'] = "<spring:message code='msg.empty' text='This field is required.' javaScriptEscape='true' />";
        messages['lbl.saveat'] = "<spring:message code='lbl.saveat' arguments='#time' text='Save at #time' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['msg.questionsaverrorforvideo'] = "<spring:message code='msg.questionsaverrorforvideo' arguments='#currenttime' text='Question already save at #currenttime time.' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['lbl.saveatpage'] = "<spring:message code='lbl.saveatpage' arguments='#time' text='Save at page #time' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['msg.questionsaverrorforvideoonpage'] = "<spring:message code='msg.questionsaverrorforvideoonpage' arguments='#currenttime' text='Question already save at page #currenttime time.' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        /**
         * multichoicetypequestion.js
         */
        messages['lbl.answernumber'] = "<spring:message code='lbl.answernumber' text='Answer No.' javaScriptEscape='true' />";
        messages['lbl.removeanswer'] = "<spring:message code='lbl.removeanswer' text='Remove Answer' javaScriptEscape='true' />";
        messages['msg.atleastonequestionisrequired'] = "<spring:message code='msg.atleastonequestionisrequired' text='Please create at least one question.' javaScriptEscape='true' />";
        messages['lbl.questiontime'] = "<spring:message code='lbl.questiontime' text='Question Time' javaScriptEscape='true' />";
        messages['lbl.questionpage'] = "<spring:message code='lbl.questionpage' text='Question Page' javaScriptEscape='true' />";
        messages['lbl.questionat'] = "<spring:message code='lbl.questionat' arguments='#time' text='Question at #time' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['lbl.questionatpage'] = "<spring:message code='lbl.questionatpage' arguments='#time' text='Question at page #time' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['lbl.questiontype'] = "<spring:message code='lbl.questiontype' text='Question Type' javaScriptEscape='true' />";
        messages['lbl.answers'] = "<spring:message code='lbl.answers' text='Answers' javaScriptEscape='true' />";
        messages['lbl.question'] = "<spring:message code='lbl.question' text='Question' javaScriptEscape='true' />";
        messages['lbl.cancel'] = "<spring:message code='lbl.cancel' text='Cancel' javaScriptEscape='true' />";
        messages['lbl.update'] = "<spring:message code='lbl.update' text='Update' javaScriptEscape='true' />";
        messages['lbl.saveatslide'] = "<spring:message code='lbl.saveatslide' arguments='#slidenum' text='Save at page #slidenum' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['msg.pleaseuploadfile'] = "<spring:message code='msg.pleaseuploadfile' text='Please upload file.' javaScriptEscape='true' />";
        messages['msg.pleaseuploadspacefile'] = "<spring:message code='msg.pleaseuploadextfile' text='Your total space is running full. Please contect to admin.' javaScriptEscape='true' />";
        messages['msg.pleaseuploadextfile'] = "<spring:message code='msg.pleaseuploadextfile' text='Please upload correct file (Only mp3).' javaScriptEscape='true' />";
</script> 
	<!-- For Editor -->
	<script src="resources/ckeditor/ckeditor.js"></script>
	<script src="resources/js/questionlibrary/build/multichoicetypequestion.js?v=4"></script>
	<script src="<spring:url value='/resources/js/uploadcontent.js'/>"></script>
	<script>

 		var vid = document.getElementById("myVideo");
      	$(function () {
			$("#cancel").click(function(){
				location.href="listuploadcontent";
			});
			if($("#mode").val()!=2){
				CKEDITOR.inline('question').on('key', function(e) {
					videoquestionKeyValidate();
				});
			}
			$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
			       checkboxClass: 'icheckbox_square-green',
			          radioClass: 'iradio_square-green'
		     });
   		});
     	function playVid() {
     		$("#videoQuestion").hide();
   	  		$("#videoQuestionAdd").hide();
  	   	    vid.pause();
   	 		var flag=true;
   	 		for(var i=0;i<timeList.length;i++){
   	 			if(timeList[i]===parseInt(vid.currentTime)){
   	 				flag=false;
   	 			}
   	 		}
   	 		if(flag){
   	 			$("#ansType").empty();
   	 			$("#totalOptions").val(0);
   				addOptions();
   				addOptions();
   	   	    	$("#videoQuestion").show();
   	   	    	var currTime=getTimeInFormat(vid.currentTime);
   	   	    	$("#savequestionbutton").text(messages['lbl.saveat'].replace('#time',currTime));
   	   	    	$("#time").val(parseInt(vid.currentTime));
   	   	    	$("#play").attr("disabled", true);
   	   	  		$("#myVideo").removeAttr("controls");
   	   			$("#savequestionbutton").removeAttr("onClick");
   	   			$("#savequestionbutton").attr("onClick","saveVideoQuestionInJson()");
   	   		    //$("#plus_minus").show();
   	 		}else{
   	 			$("#videoQuestionAdd").show();
   	 			$("#contentErrorAlert p").text(messages['msg.questionsaverrorforvideo'].replace('#currenttime',getTimeInFormat(vid.currentTime)));
			   	$("#contentErrorAlert").modal('show');
   	 			vid.play();
   	 		}
   		}
     	function backToVideo(){
     		$("#videoQuestion").hide();
     		$("#play").attr("disabled", false);
   	  		$("#myVideo").attr("controls","controls");
   	  		$("#time").val("");
   	  		clearVideoQuestionData()
   	  		$("#videoQuestionAdd").show();
     		vid.play();
     	}
     	
     	function addQuestionForPDF() {
     		$("#videoQuestion").hide();
   	  		$("#videoQuestionAdd").hide();
   	 		var flag=true;
   	 		for(var i=0;i<timeList.length;i++){
   	 			if(timeList[i]===parseInt($("#time").val())){
   	 				flag=false;
   	 				break;
   	 			}
   	 		}
   	 		if(flag){
   	 			$("#ansType").empty();
   	 			$("#totalOptions").val(0);
   				addOptions();
   				addOptions();
   	   	    	$("#videoQuestion").show();
   	   	    	//var currTime=getTimeInFormat(vid.currentTime);
   	   	    	//console.log("Page Numbare====>"+$("#time").val());
   	   	    	$("#savequestionbutton").text(messages['lbl.saveatpage'].replace('#time',$("#time").val()));
   	   	    	//$("#time").val(parseInt(vid.currentTime));
   	   	    	$("#play").attr("disabled", true);
   	   	  		$("#previous").attr("disabled", true);
   	   	  		$("#next").attr("disabled", true);
   	   	  		//$("#myVideo").removeAttr("controls");
   	   			$("#savequestionbutton").removeAttr("onClick");
   	   			$("#savequestionbutton").attr("onClick","saveVideoQuestionInJson()");
   	 		}else{
   	 			$("#videoQuestionAdd").show();
   	 			$("#contentErrorAlert p").text(messages['msg.questionsaverrorforvideoonpage'].replace('#currenttime',$("#time").val()));
		   		$("#contentErrorAlert").modal('show');
   	 		}
   		}
     	
     	function backToPDF(){
     		$("#videoQuestion").hide();
     		$("#play").attr("disabled", false);
     		if(parseInt($("#time").val())>1){
     			$("#previous").attr("disabled", false);
     		}
     		if(parseInt($("#time").val())<parseInt($("#contentPages").val())){
     			$("#next").attr("disabled", false);
     		}
     		$("#next").attr("disabled", false);
   	  		clearVideoQuestionData()
   	  		$("#videoQuestionAdd").show();
     	}
     	
     	function extendtime(i){
     		//alert("total time="+vid.duration);
     		var currTime = parseInt($("#time").val());
     		var strCurrTime = getTimeInFormat(currTime);
     		if(i==1){
     			if(currTime<parseInt(vid.duration)){
     				strCurrTime=getTimeInFormat(currTime+1);     			 
        			$("#time").val(parseInt(currTime+1));	
     			}     			 
     		}else{
     			 if(currTime>0){
     				strCurrTime=getTimeInFormat(currTime-1);
        			 $("#time").val(parseInt(currTime-1));	 
     			 }     			 
     		}
     		$("#savequestionbutton").text(messages['lbl.saveat'].replace('#time',strCurrTime));     		
     	}
     	
	</script>
</html>