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
			        margin-left:230px;
			    }
			}
			@media only screen and (max-width: 760px) {
			  .content-wrapper {
			        margin-left:0px;
			    }
			}  
			.badge {
				background-color: #05B26F;
			}
			
			img.lazy.loading, img.lazy-first_ppt.loading {
             background: transparent url(resources/images/loading.gif) no-repeat 50% 50%;
           }
           
           .button-width-large{
              width:150px;
           }
		</style>
		<script type="text/javascript">
			var timeList = [];
			var setTimeoutId = "";
			var audioList=${audioList};
		</script>
	</head>
	<body class="hold-transition skin-black-light sidebar-mini">
		<div class="wrapper">
			<%@ include file="header.jsp"%>
			<%@ include file="leftmenu.jsp"%>
			<div class="col-sm-12" >		
				<div class="content-wrapper">
					<section class="content-header">
						<div class="pull-left">
							<c:if test="${content.contentType=='VIDEO'}">
								<h3 style="margin:0"><spring:message code="lbl.previewvideoquestioncontent" text="Preview Video Question Content"/></h3>
							</c:if>
							<c:if test="${content.contentType=='PDF'}">
								<h3 style="margin:0"><spring:message code="lbl.previewpdfquestioncontent" text="Preview PDF Question Content"/></h3>
							</c:if>
							<c:if test="${content.contentType=='PPT' || content.contentType=='PPTX'}">
								<h3 style="margin:0"><spring:message code="lbl.previewpptvoicecontent" text="Preview PPT Voice Content"/></h3>
							</c:if>
						</div>			
						<br/>			
					</section>
					<section class="content">
						<div class="row">			 
							<div class="col-md-12">				
								<div class="box no-border col-md-12">
									<!-- /.box-header -->
									<div class="box-body no-padding">
				  						<form id="form" name="uploadcontentform" enctype="multipart/form-data">
                  							<div class="box-body" style="width: 100%;float: left;" id="content-show-body">
                    							<div class="form-group" style="text-align:center">
                      								<label class="capitalize"><spring:message code="lbl.title" text="Title"/>: &nbsp;</label><span>${content.contentName}</span>
                    							</div>
                      							<c:if test="${content.contentType=='VIDEO'}">
                        							<div class="">
                          								<video id="myVideo" style="width: 98%;" controls>
                          									<source src="${content.contentPath}">
                          								</video>
                          							</div>
                      							</c:if>
                      							<c:if test="${content.contentType=='PDF'}">
                   							 		<div>
                   							 		<%-- <c:set var="pdfsrc" value="${fn:split(content.contentPath, '/')}" /> --%>
                   							 		<object id="pafContent" style="width: 98%;height:450px;" type="text/html" data="https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc=${content.contentPath}/page_1.pdf"></object>
                   							 		<%-- <iframe id="pafContent" src="http://localhost:8080/pdfjs/web/viewer.html?pdfsrc=${content.contentPath}/page_1.pdf" style="width: 98%; height: 450px"></iframe> --%>
                   							 		<!-- <iframe id="pafContent" src="http://localhost:8080/ViewerJS/#../qbis_course/21_pdf/page_3.pdf" style="width: 98%; height: 450px"></iframe> -->
                   							 		<%-- <iframe id="pafContent" src="${content.contentPath}/page_1.pdf" style="width: 98%; height: 450px"></iframe> --%>								                          	
                        							</div>
                        							<div style="float: left; width: 30%; padding: 2%;">
						                          		<button disabled="disabled" type="button" id="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCallView(0,'pdf');"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp;<spring:message code="lbl.previous" text="Previous"/></span></button>
						                          	</div>
						                          	<div style="float: left; width: 40%; padding: 2%; text-align: center;">
						                          	    <spring:message code="lbl.showingpage" text="Showing <span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span> of ${content.numPages}"  arguments="<span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span>^${content.numPages}" htmlEscape="false" argumentSeparator="^"/>
						                          	</div>
						                          	<div style="float: right; width: 30%; padding: 2%;">
						                          		<c:if test="${content.numPages==1}">
						                          		    <button disabled="disabled" type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCallView(1,'pdf');"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
						                          		</c:if>
						                          		<c:if test="${content.numPages>1}">
						                          		    <button type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCallView(1,'pdf');"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
						                          		</c:if>
						                          	</div>
                      							</c:if>
                      							<c:if test="${content.contentType=='PPT' || content.contentType=='PPTX'}">
                   							 		<div id="lazy-image">
                   							 			<%-- <img id="pafContent" src="${content.contentPath}/page_1.png" style="width: 98%; max-height: 450px"> --%>
                   							 			<img id="pafContent" data-src="${content.contentPath}/page_1.png" style="width: 98%; max-height: 450px" class="lazy-first_ppt loading">
                   							 		</div>
                   							 		<div>
                   							 			<audio id="myaudio" style="width: 98%; height: 30px;" controls></audio>
                   							 		</div>
                   							 		<div>
                   							 			<label id="timeholdlabel"><spring:message code="lbl.slideholdtill" text="Slide Hold Till:"/>&nbsp;&nbsp;<span id="timehold"></span></label>
                   							 		</div>
                   							 		<div>
	                        							<div style="float: left; width: 30%; padding: 2%;">
							                          		<button disabled="disabled" type="button" id="previous" name="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="showNewSlide(0);"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp;<spring:message code="lbl.previous" text="Previous"/></span></button>
							                          	</div>
							                          	<div style="float: left; width: 40%; padding: 2%; text-align: center;">
							                          	    <spring:message code="lbl.showingpage" text="Showing <span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span> of ${content.numPages}"  arguments="<span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span>^${content.numPages}" htmlEscape="false" argumentSeparator="^"/>
							                          	</div>
							                          	<div style="float: right; width: 30%; padding: 2%;">
							                          	<c:if test="${content.numPages == 1}">
							                          	  <button disabled="disabled" type="button" style="float: right;" id="next" name="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="showNewSlide(1);"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
							                          	</c:if>
							                          	<c:if test="${content.numPages > 1}">
							                          	<button type="button" style="float: right;" id="next" name="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="showNewSlide(1);"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
							                          	</c:if>
							                          	</div>
						                          	</div>
                      							</c:if>
                   								<input type="hidden" id="contentId" name="contentId" value="${content.contentId}">
                   								<c:if test="${content.contentType=='PDF'}">
                   								<input type="hidden" id="contentUrl" name="contentUrl" value="https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc=${content.contentPath}">
                   								</c:if>
                   								<c:if test="${content.contentType!='PDF'}">
                   								<input type="hidden" id="contentUrl" name="contentUrl" value="${content.contentPath}">
                   								</c:if>
												<input type="hidden" id="contentPages" name="contentPages" value="${content.numPages}">
												<input type="hidden" id="contentType" name="contentType" value="${content.contentType}">
												<input type="hidden" id="time" name="time" value="1">
                   							</div>
                   							
				  							<div id="questionListDiv" style="width: 40%;float: right; display: none;margin-top:45px">
				  								<div class="form-group">
                      								<c:set var="cnt" value="1"></c:set>
                      								<c:forEach items="${questionList}" var="question"> 
                      									<div class="form-group" style="display: none;" id="showQuestionDiv${question.time}">
                      										<input type="hidden" id="timeslot${cnt}" value="${question.time}">
                      										<%-- <label>Question Time: &nbsp;</label><span>Question at ${question.time}</span> --%>
															<label><spring:message code="lbl.questiontype" text="Question Type"/>: &nbsp;</label><span>${question.questionTypeName}</span><br/>
															<div class="input-group" style="float: left"><label><spring:message code="lbl.question" text="Question"/>: &nbsp;</label></div><div style="padding-left:40px"><span>${question.questionName}</span></div>
															<label><spring:message code="lbl.answers" text="Answers"/></label><br/>
															<c:forEach items="${question.option}" var="option" varStatus="loop"> 
																<div class="input-group" style="float: left">
																	<span class="badge bagde-style">&#${loop.index + 65};</span>&nbsp;&nbsp;&nbsp;&nbsp;
																	<c:if test="${question.questionType==1}">
																		<input type="checkbox" class="flat-red questionType"  name="answerStatus" id="answerStatus${loop.index}" value="${option.optionId}">
																	</c:if>
																	<c:if test="${question.questionType==2}">
																		<input type="radio" class="flat-red questionType"  name="answerStatus" id="answerStatus${loop.index}" value="${option.optionId}">
																	</c:if>
																	&nbsp;&nbsp;&nbsp;&nbsp;
																</div>
																<div style="padding-left:40px"><span>${option.optionName}</span></div>
															</c:forEach>
														</div>
														<c:set var="cnt" value="${cnt+1}"></c:set>
                      								</c:forEach>
                      								<input type="hidden" id="questionCount" value="${cnt}">
                      								<button class="btn btn-default btn-flat button-width-large" id="continue" type="button"><spring:message code="lbl.continue" text="Continue"/></button>
                    							</div>
				  							</div>
                						</form>
									</div>
									<!-- /.box-body -->
									<div class="box-footer">
										<button class="btn btn-default btn-flat button-width-large" id="cancel" type="button"><spring:message code="lbl.back" text="Back"/></button>
									</div>
								</div>
								<!-- /. box -->
							</div>
							<!-- /.col -->
						</div>
					</section>
				</div>
				<div class="control-sidebar-bg"></div>
			</div>
			<!-- ./wrapper -->
		</div>	
	</body>
	<script>
var messages = new Array();
/**
 * multichoicetypequestion.js
 */
 messages['msg.empty'] = "<spring:message code='msg.empty' text='This field is required.' javaScriptEscape='true' />";
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
/**
 * videocontroll.js
 */
messages['msg.datanotsaved'] = "<spring:message code='msg.datanotsaved' text='Data could not saved. Please try again later.' javaScriptEscape='true'/>";
messages['lbl.title'] = "<spring:message code='lbl.title' text='Title' javaScriptEscape='true'/>";
messages['lbl.question'] = "<spring:message code='lbl.question' text='Question' javaScriptEscape='true'/>";
messages['lbl.answers'] = "<spring:message code='lbl.answers' text='Answers' javaScriptEscape='true'/>";
messages['lbl.continue'] = "<spring:message code='lbl.continue' text='Continue' javaScriptEscape='true'/>";
messages['lbl.submit'] = "<spring:message code='lbl.submit' text='Submit' javaScriptEscape='true'/>";
messages['msg.attemptquestionforsubmit'] = "<spring:message code='msg.attemptquestionforsubmit' text='Please attempt question for submit otherwise click on continue.' javaScriptEscape='true'/>";
messages['lbl.previous'] = "<spring:message code='lbl.previous' text='Previous' javaScriptEscape='true'/>";
messages['lbl.showingpage'] = "<spring:message code='lbl.showingpage' text='showing #currectpage of #totalpage' arguments='#currectpage;#totalpage' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
messages['lbl.next'] = "<spring:message code='lbl.next' text='Next' javaScriptEscape='true'/>";
messages['lbl.questiontype'] = "<spring:message code='lbl.questiontype' text='Question Type' javaScriptEscape='true'/>";
</script>
	<script src="resources/js/questionlibrary/build/multichoicetypequestion.js"></script>
	<script src="resources/js/videocontroll.js?v=3"></script>
	<script>
		var vid = document.getElementById("myVideo");

		var aud = document.getElementById('myaudio');
      	$(function () {
    	  	$(".treeview").removeClass("active");
	      	$("#uploadcontent").addClass("active");
	      	$("#uploadcontent .treeview-menu > #uploadcontent").addClass("active");
	      	$('input').iCheck({
	          	checkboxClass: 'icheckbox_square-green',
	          	radioClass: 'iradio_square-green',
	          	increaseArea: '20%' // optional
        	});
	      	$("#cancel").click(function(){
				location.href="listuploadcontent";
			});
	      	for(var i=1;i<parseInt($("#questionCount").val()); i++){
	      		timeList.push(parseInt($("#timeslot"+i).val()));
	      	}
	      	if($("#contentType").val()=="VIDEO"){
	      	 	vid.addEventListener("play", function() {
		      		setTimeForQuestion();
	           	}, false);
		      	vid.addEventListener("pause", function() {
		   	   		clearInterval(setTimeoutId);
	           	}, false);
		      	vid.addEventListener("seekable", function() {
	      			vid.pause();
	      			setTimeForQuestion();
	           	}, false); 
	      	}
	      	if($("#contentType").val()=="PPT" || $("#contentType").val()=="PPT"){
				aud.addEventListener("ended", function() {
					showNewSlide(1);
	           	}, false);
	      		$('.lazy-first_ppt').Lazy({
	      		    bind:'event',
	      		    chainable: false,
	      	    	beforeLoad: function(element) {
	      	        },
	      	        afterLoad: function(element) {
	      	        	addAudioAndTime(0);
	      	        },
	      	        onError: function(element) {
	      	        },
	      	        onFinishedAll: function(element) {
	      	        }
	      	});
	      	}
      	});
	</script>
</html>