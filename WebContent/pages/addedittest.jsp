<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="resources/css/custom.css">
<%@ include file="include.jsp"%>
<style>
.nav-tabs-custom>.nav-tabs>li.active {
	border-bottom-color: #00B06C;
}
/* .cke_textarea_inline {
			border: 1px solid #ccc;
			padding: 10px;
			background-color:white;
			min-height: 100px;
		} */
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
.input-group-addon {
    border: 1px solid #ccc !important;
}
.input-group-addon:first-child {
    border-right: 0 !important;
}
.padding-left{
padding-left:0px
}
.daterangepicker td.active, .daterangepicker td.active:hover {
background-color:#00B06C;
}
</style>

</head>

<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<!-- Show lazy loader image -->
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="<spring:url value='/resources/images/loading.gif'/>"
				style="position:fixed;left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
        <div class="col-sm-12" >
		<!-- start dataTable----->
		<div class="content-wrapper">
			<div id="createTest">
				<form method="post" id="testForm" name="testForm" enctype="multipart/form-data">
					<section class="content-header">
					<input type="hidden" name="courseId" value="<%=request.getParameter("courseId")%>">
					<input type="hidden" name="sectionId" value="<%=request.getParameter("sectionId")%>">
					<input type="hidden" name="contentId" value="<%=request.getParameter("contentId")%>">
					<input type="file" class="hidden" name="imageName"
					id="testImage" onchange="showMyImage(this)" value="${test.testIcon}">
					
					    <div class="h4"><spring:message code="lbl.assessment"/><i class="fa fa-angle-right" style="padding-left:5px;"></i><c:if test="${test.testId!=null}"><spring:message code="lbl.editassessment"/></c:if><c:if test="${test.testId==null}"><spring:message code="lbl.createnewassessment"/></c:if></div> 

<!--------------------------------------------Test Navigation Tag---------------------------------------------------------->

						<div style="margin-top:30px;background-color:#ECF0F5;" class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active" style="border-left: 1px solid #dedede; width: 33.32%;background-color: #FFF;"><a
									 style="text-align: center;">
									  <c:if test="${test.testId!=null}">
									  <i class="fa fa-check-circle-o color-green"	id="fa"></i>
									  </c:if>
									 <c:if test="${test.testId==null}"> 
									  <i class="fa fa-circle-thin" id="fa"></i>
									  </c:if>
										&nbsp;&nbsp;<spring:message code="lbl.assessmentinformation"/></a></li>
								<li style="border-left: 1px solid #dedede; width: 33.32%;background-color: #FFF;"><a
									 style="text-align: center;">
									  <i class="fa fa-circle-thin"	id="fa"></i>
										&nbsp;&nbsp;<spring:message code="lbl.addquestions"/></a></li>
								<li style="border-left: 1px solid #dedede; width: 33.32%;background-color: #FFF;">
								<a style="text-align: center;">
								<i class="fa fa-circle-thin" id="fa"></i>&nbsp;&nbsp;<spring:message code="lbl.saveandpublish"/></a></li>
							</ul>
						</div>
<!--------------------------------------------End of Test Navigation Tag---------------------------------------------------------->						
					</section>
					<section class="content">
					<div class="row">
<!--------------------------------------------Test Featured Image---------------------------------------------------------->								
		<%-- 		 <div class="col-sm-12 form-group">
									<c:if test="${test.testIconUrl!=null}">
										<img src="${test.testIconUrl}"
											style="width: 100%; height: 300px" id="TestImagePreview"
											onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'"
											alt="...">
									</c:if>
									<c:if test="${test.testIconUrl==null}">
										<img
											src="<spring:url value='resources/adminlte/dist/img/photo1.png'/>"
											style="width: 100%; height: 300px" id="TestImagePreview"
											onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'"
											alt="...">
									</c:if>
									<div class="row" style="margin-top: -3.0%;text-align:center">			
					<div style="text-align:center">
					<div>			
					<span><img src="resources/adminlte/dist/img/testUpload.png"></span>
					<span style="margin-left: -3px;"><img id="uploadTrigger" onclick="catValidate();" src="resources/adminlte/dist/img/camera.png"></span>
					</div>
					</div>					
					<br />
					<label class="requireFld" id="testImageError"><spring:message code="msg.images.required"/></label>
					<label class="requireFld" id="testImageError1"><spring:message code="msg.images.size"/></label>			                    
                 </div>
                 </div> --%>
                 
<!--------------------------------------------End Of Test Featured Image ---------------------------------------------------------->
                
<!--------------------------------------------Import Test Setting Button---------------------------------------------------------->                
							<div class="col-xs-12" style="margin-top: 22px;"><button type="button" class="pull-right btn btn-success btn-flat" id="testSetting"><spring:message code="lbl.importassessmentsetting"/>
								</button>
							</div>
<!--------------------------------------------End Of Import Test Setting Button---------------------------------------------------------->
							
<!--------------------------------------------Test Title---------------------------------------------------------->							
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label><sup><font color="red" size="3px">*</font></sup><spring:message code="lbl.assessmenttitle"/> :</label>
							<p><spring:message code="msg.maxlength50"/></p>
							<input type="text" name="testName" id="testName" class="form-control" onkeyup="catValidate();" value="${test.testName}" maxlength="50">
							<label class="requireFld" id="testNameError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="testNameError1"><spring:message code="msg.maxlength50"/></label>
							<label class="requireFld" id="testNameError2"><spring:message code="msg.assessment.title.exist"/></label>
						</div>
<!--------------------------------------------End Of Test Title---------------------------------------------------------->
						
<!--------------------------------------------Test Instruction---------------------------------------------------------->						
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label><sup><font color="red" size="3px">*</font></sup><spring:message code="lbl.instructions"/> :</label>
							<div id="testInstructDiv">
								<textarea name="testInstruct" id="testInstruct"
									class="form-control" onkeyup="catValidate();"
									style="height: 100px">${test.testInstruct}</textarea>
							</div>
							<label class="requireFld" id="testInstructError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="testInstructError1"><spring:message code="msg.maxlength1024"/></label>
						</div>
<!--------------------------------------------End of Test Instruction---------------------------------------------------------->
						
<!--------------------------------------------Test Description---------------------------------------------------------->
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label><sup><font color="red" size="3px">*</font></sup><spring:message code="lbl.description"/>
								:</label>
							<textarea name="testDesc" id="testDesc" class="form-control textAreaControl"
								onkeyup="catValidate();" maxlength="512">${test.testDesc}</textarea>
							<label class="requireFld" id="testDescError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="testDescError1"><spring:message code="msg.maxlength512"/></label>
						</div>
						<div class="col-xs-12" style="height: 50px"></div>
<!--------------------------------------------End of Test Description---------------------------------------------------------->
						
<!--------------------------------------------Visibility (disabled)--------------------------------------------------->
						<div class="col-xs-12 hide">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.visibility"/> :</label>
							<div class="col-xs-8">
								<input type="hidden" name="view" id="view1" value="0"><input
									type="checkbox" value="On" id="view2"
									onclick="changeState('view');" checked disabled>
							</div>
						</div>
						<div class="col-xs-12 hide">
							<p>(<spring:message code="lbl.visibility.note"/>)</p>
						</div>
						<div class="col-xs-12 hide" style="min-height: 20px"></div>
<!--------------------------------------------End of Visibility--------------------------------------------------->	

<!--------------------------------------------Question Score--------------------------------------------------->					
						<div class="col-xs-12">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.questionscore"/> :</label>
							<div class="col-xs-8">
								<input type="checkbox" value="1" name="equalMarkTest"
									id="scoreType" onclick="changeState('scoreType');" checked>
							</div>
						</div>
						<div class="col-xs-12">
							<p>(<spring:message code="lbl.questionscore.note"/>)</p>
							<div id="scoreTypeOnOff">
								<label><input type="text" style="width: 30px"
									name="everyQuestionMark" id="scoreMark" onkeyup="catValidate();" maxlength="2">
									<sup><font color="red" size="3px">&nbsp;*</font></sup><spring:message code="lbl.mark"/> </label><span>
									(<spring:message code="lbl.eachquestionscore"/>)</span>
							</div>
							<label class="requireFld" id="scoreMarkError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="scoreMarkError1"><spring:message code="msg.questionscore.numeric"/></label>
						</div>
<!--------------------------------------------End Of Question Score--------------------------------------------------->

<!--------------------------------------------Test Attempts--------------------------------------------------->
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.attemptsallowed"/> :</label>
							<div class="col-xs-8">
								<input type="checkbox" value="Off" name="limitAttempts" id="limitAttempts" onclick="changeState('limitAttempts');">
							</div>
						</div>
						<div class="col-xs-12">
							<p></p>
							<div id="limitAttemptsOnOff" style="display:none">
								<label>
								    <input type="text" style="width: 30px" name="maxAttempts" id="maxAttempts" onkeyup="catValidate();" maxlength="3">
									<sup><font color="red" size="3px">&nbsp;*</font></sup><spring:message code="lbl.noofallowedattempt"/></label>
							</div>
							<label class="requireFld" id="maxAttemptsError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="maxAttemptsError1"><spring:message code="msg.attemptsallowed.numeric"/></label>
						</div>
<!--------------------------------------------End of Test Attempts--------------------------------------------------->

<!--------------------------------------------Negative Mark---------------------------------------------------------->						
						
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.negativemarking"/> :</label>
							<div class="col-xs-8">
								<input type="checkbox" value="On" id="negType"
									onclick="changeState('negType');" checked>
							</div>
						</div>
						<div class="col-xs-12">
							<p>(<spring:message code="lbl.negativemarking.note"/>)</p>
							<div id="negTypeOnOff" class="form-inline form-group">
								<!-- <input type="text" style="width: 30px"
									name="negMark" id="negType1" onkeyup="catValidate();"> -->
								<select name="negMark" id="negMark" class="form-control"
									style="width: 100px" onchange="catValidate();">
									<option value="" selected><spring:message code="lbl.select"/></option>
									<option value="25">25%</option>
									<option value="50">50%</option>
									<option value="75">75%</option>
									<option value="100">100%</option>
								</select> <label><sup><font color="red" size="3px">&nbsp;*</font></sup><spring:message code="lbl.mark"/>
								</label>
								<span> (<spring:message code="lbl.negativemarkingforeveryquestion.note"/>)</span>
							</div>
							<label class="requireFld" id="negMarkError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="negMarkError1"><spring:message code="msg.allowednumericvalue"/></label>
						</div>
<!--------------------------------------------End Of Negative Mark--------------------------------------------------->

<!---------------------------------------------------Test Time ------------------------------------------------------>						
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.timebond"/> :</label>
							<div class="col-xs-8">
								<input type="checkbox" value="On" id="time" name="time"
									onclick="changeState('time');" checked>
							</div>
						</div>
						<div class="col-xs-12">
							<p>(<spring:message code="lbl.timebound.note"/>)</p>
							<div id="testTime">
								<label><input type="text" style="width: 30px"
									name="timeMinute" id="timeMin" onkeyup="catValidate();" maxlength="3"> <sup><font
										color="red" size="3px">&nbsp;*</font></sup><spring:message code="lbl.minute"/>
								<input type="hidden" style="width: 30px" name="timeSec" id="timeSec"
									onkeyup="catValidate();" value="0">
								<!--  <sup><font color="red"
										size="3px">&nbsp;*</font></sup>Sec.  --></label><span> (<spring:message code="lbl.entertime"/>)</span>
							</div>
							<label class="requireFld" id="timeError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="timeError1"><spring:message code="msg.allowednumericvalue"/></label>
							<label class="requireFld" id="timeError2"><spring:message code="msg.timerequiredmultipleoffive"/></label>
						</div>
<!--------------------------------------------End Of Test Time ------------------------------------------------------>

<!--------------------------------------------Test Pause Property--------------------------------------------------->
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.enablepause"/> :</label>
							<div class="col-xs-8">
								<input type="hidden" value="0" name="testPause" id="testPause1">
								<input type="checkbox" value="Off" id="testPause2"
									onclick="changeState('testPause');">
							</div>
						</div>
						<div class="col-xs-12">
							<p>(<spring:message code="lbl.enablepause.note"/>)</p>
						</div>
<!--------------------------------------------End Of Test Pause Property--------------------------------------------------->
			
<!--------------------------------------------Adaptive (hide)--------------------------------------------------->
						<div class="col-xs-12 hide" style="min-height: 20px"></div>
						<div class="col-xs-12 hide">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.adaptive"/> :</label>
							<div class="col-xs-8">
								<input type="hidden" value="0" name="testAdaptive"
									id="testAdaptive1"> <input type="checkbox" value="Off"
									id="testAdaptive2" onclick="changeState('testAdaptive');">
							</div>
						</div>
						<div class="col-xs-12 hide">
							<p>(<spring:message code="lbl.adaptive.note"/>)</p>
						</div>
<!--------------------------------------------End Of Adaptive--------------------------------------------------->

<!--------------------------------------------Test Review--------------------------------------------------->
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12 ">
							<label style="width: 20%" class="pull-left"><spring:message code="lbl.enablereview"/> :</label>
							<div class="col-xs-8">		
													
								<input type="checkbox" value="0" id="isReview" name="isReview"
									onclick="changeState('isReview');">
							</div>
						</div>
						<div class="col-xs-12">
							<div id="isReviewOnOff" style="display:none">
								<label>
								  <input type="radio" class="flat-red" value="1" id="withCorrect" name="reviewWithCorrect">&nbsp;<spring:message code="lbl.withcorrectanswer"/>
								  </label>
								  &nbsp;&nbsp;&nbsp;
								  <label>
								  <input type="radio" class="flat-red" value="0" checked="checked" id="withoutCorrect" name="reviewWithCorrect">&nbsp;<spring:message code="lbl.withoutcorrectanswer"/>
								</label>
							</div>
						</div>
<!--------------------------------------------End Of Test Review--------------------------------------------------->

<!--------------------------------------------Shuffle Sections--------------------------------------------------->						
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12 form-group">
						<label style="width: 20%" class="pull-left"><spring:message code="lbl.shufflesections"/> :</label>
							<div class="col-xs-8">
						<input type="checkbox" value="0" id="shuffleSection" name="shuffleSection"
									onclick="changeState('shuffleSection');">
									</div>
						</div>
<!--------------------------------------------End Of Shuffle Sections--------------------------------------------------->

<!--------------------------------------------Shuffle Questions--------------------------------------------------->						
						<div class="col-xs-12 form-group">
						    <label style="width: 20%" class="pull-left"><spring:message code="lbl.shufflequestions"/> :</label>
							<div class="col-xs-8">
						          <input type="checkbox" value="0" id="shuffleQuestion" name="shuffleQuestion" onclick="changeState('shuffleQuestion');">
							</div>
						</div>
<!--------------------------------------------End Of Shuffle Questions--------------------------------------------------->


<!--------------------------------------------Quiz Random--------------------------------------------------->						
						<div class="col-xs-12 form-group">
						    <label style="width: 20%" class="pull-left"><spring:message code="lbl.quizrandom"/> :</label>
							<div class="col-xs-8">
						        <input type="checkbox" value="0" id="quizRandom" name="isRandom" onclick="changeState('quizRandom');">
							</div>
						</div>
						<div class="col-xs-12">
							<p><spring:message code="msg.maxquestionheaderforrandomquiz"/></p>
							<div id="limitMaxQuestionsOnOff" style="display:none">
								<label>
								    <input type="text" style="width: 30px" name="maxQuestions" id="maxQuestions" onkeyup="catValidate();" maxlength="3">
									<sup><font color="red" size="3px">&nbsp;*</font></sup><spring:message code="lbl.maxquestions"/></label>
							</div>
							<label class="requireFld" id="maxQuestionsError"><spring:message code="msg.empty"/></label>
							<label class="requireFld" id="maxQuestionsError1"><spring:message code="msg.maxquestionallowed.numeric"/></label>
						</div>
<!--------------------------------------------End Of Shuffle Questions--------------------------------------------------->

						
<!------------------------------------------shuffle options (hide)---------------------------------------------->
						<div class="col-xs-12 form-group hide">
						<label style="width: 20%" class="pull-left"><spring:message code="lbl.shuffleoptions"/> :</label>
							<div class="col-xs-8">
						<input type="checkbox" value="0" id="shuffleOption" name="shuffleOption"
									onclick="changeState('shuffleOption');">
									</div>
						</div>
<!-------------------------------------End Of shuffle options--------------------------------------------------->	

<!--------------------------------------------Auto Generated Tags--------------------------------------------------->
                           <c:forEach items="${testTags}" var="config" varStatus="loop">
                               <div class="col-xs-12" style="min-height: 20px"></div>
	                           <div class="col-xs-12 form-group test-tags" data-type="${config.type}">
	                                 <input type="hidden" name="tagList[${loop.index}].id" value="${config.id}"/>
	                                 <!-- THis is for keys of tags -->
	                                 <label style="width: 20%" class="pull-left"><sup><font color="red" size="3px">&nbsp;*</font></sup>${config.name}:</label>
							         <div class="col-xs-8">
							            <c:choose>
							              <c:when test = "${config.type == 'select'}">
							                <select class="form-control test-tags-input" id="changeopt${config.name}" name="tagList[${loop.index}].value" style="width:150px" onchange="catValidate();" onmouseup="fun('${config.name}')">
								                <c:forEach items="${config.configList}" var="configMapping">
								                     <c:set var="isMatched" value = "false"/>
								                     <c:forEach items="${test.tagList}" var="tag">
								                         <c:choose>
								                            <c:when test="${isMatched == false && config.id == tag.id && configMapping.id == tag.value}">
								                                 <option value="${configMapping.id}" selected>${configMapping.value}</option>
								                                 <c:set var="isMatched" value = "true"/>
								                            </c:when>
								                         </c:choose>
								                     </c:forEach>
								                     <c:if test="${isMatched == false}">
								                              <option value="${configMapping.id}">${configMapping.value}</option>
								                     </c:if>
								                 </c:forEach>
								               <!-- dsssssssssssssssssssssssssssssssssssssssss -->
								               
								            </select>
								               
							                <label class="requireFld test-tags-input-error"><spring:message code="msg.empty"/></label>
							               </c:when>
							               <c:otherwise>
							                 <c:set var="isMatched" value = "false"/>
						                     <c:forEach items="${test.tagList}" var="tag">
						                         <c:choose>
						                            <c:when test="${isMatched == false && config.id == tag.id}">
						                            <%-- <c:forEach items="${chapter}" var="chapters">
						                             <option value="${configMapping.id}" selected>${configMapping.value}</option>
						                             </c:forEach> --%>
						                                 <input type="text" class="form-control test-tags-input" name ="tagList[${loop.index}].value" style="width:150px" onkeyup="catValidate();" value="${tag.value}">
						                                 <label class="requireFld test-tags-input-error"><spring:message code="msg.empty"/></label>
						                                 <c:set var="isMatched" value = "true"/>
						                            </c:when>
						                         </c:choose>
						                     </c:forEach>
						                     <c:if test="${isMatched == false}">
						                              <input type="text" class="form-control test-tags-input" name ="tagList[${loop.index}].value" style="width:150px" onkeyup="catValidate()" value="yes">
						                     </c:if>
							                 <label class="requireFld test-tags-input-error"><spring:message code="msg.empty"/></label>
							               </c:otherwise>
							            </c:choose>  
							         </div>
	                           </div>                           </c:forEach>
<!--------------------------------------------End of Auto Generated Tags-------------------------------------------->

                        
<!--------------------------------------------Immediate Publish--------------------------------------------------->	
<div class="hide">
						<div class="col-xs-12" style="min-height: 20px"></div>
						<div class="col-xs-12 form-group">
						<label style="width: 26%" class="pull-left"><spring:message code="lbl.doyouwantpublishimmediately" text="Do you want publish immediately ?"/></label>
							<div class="col-xs-8">
							<c:if test="${test.isSchedule==0 || test.isSchedule==null}">
								<input type="radio" name="isSchedule" value="0" checked>&nbsp;Yes&nbsp;
								<input type="radio" name="isSchedule" value="1">&nbsp;No&nbsp;
							</c:if>
							<c:if test="${test.isSchedule==1}">
							    <input type="radio" name="isSchedule" value="0">&nbsp;Yes&nbsp;
							    <input type="radio" name="isSchedule" value="1" checked>&nbsp;No&nbsp;
							</c:if>
							</div>
						</div>
						<div class="col-xs-12" id="schedulePublishDateDiv" style="display:none;margin-bottom:20px;">
						<div class="col-xs-4 padding-left" style="margin-left:0px;">
						<div class="input-group">
						<div class="input-group-addon">
						<i class="fa fa-calendar"></i>
						</div>
						<input type="text" id="schedulePublishDate" class="form-control" name="schedulePublishDate">
						</div>
									</div>
									<div class="col-xs-8 padding-left" style="vertical-align:middle;line-height:2.0">
									<span class="pull-left">(<spring:message code="lbl.immediatepublish.note"/>)</span>
									</div>
						</div>
						</div>
<!--------------------------------------------End Of Immediate Publish--------------------------------------------------->
<!--------------------------------------------Start Of feature image--------------------------------------------------->
                <div class="col-xs-12 form-group" style="min-height: 100px">
							<label class="form-group"><spring:message code="lbl.coverimage" text="Cover Image"/> :</label> <br />
							 <c:if test="${test.testIconUrl!=null}">
										<img src="${test.testIconUrl}"
											style="width: 150px; height: 100px" id="TestImagePreview"
											onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'"
											alt="...">
									</c:if>
									<c:if test="${test.testIconUrl==null}">
										<img
											src="<spring:url value='resources/adminlte/dist/img/photo1.png'/>"
											style="width: 150px; height: 100px" id="TestImagePreview"
											onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'"
											alt="...">
									</c:if>&nbsp;&nbsp;&nbsp;
							<button type="button"
								class="btn btn-success btn-flat  button-width-large"
								id="uploadTrigger" onclick="catValidate();"><spring:message code="lbl.uploadcoverimage" text="Upload Cover Image"/></button>							
								<label class="requireFld" id="testImageError"><spring:message code="msg.images.required"/></label>
					            <label class="requireFld" id="testImageError1"><spring:message code="msg.images.size"/></label>	

						</div> 
<!--------------------------------------------End Feature image---------------------------------------------------------->
<!--------------------------------------------Test Tag---------------------------------------------------------->						
						<div class="col-xs-12 form-group hide">
							<label><sup><font color="red" size="3px">*</font></sup>
							<spring:message code="lbl.tags"/>
								:</label>
							<p>Class/ Group Tags/ Subject Tags/ Test Tags</p>
							<div id="testTag" style="border: 1px solid white">
								<select class="select2 form-control" name="testTag"
									id="multiSelectTag" multiple="multiple"
									style="width: 100%; min-height: 100px"
									onchange="catValidate();">
									<option value="Class">Class</option>
									<option value="Group">Group</option>
									<option value="Subject">Subject</option>
									<option value="Test">Test</option>
									<option value="12th">12th</option>
									<option value="B.Tech">B.Tech</option>
									<option value="CBSE" selected>CBSE</option>
								</select>
							</div>
							<label class="requireFld" id="testTagError"><spring:message code="msg.empty"/></label>
						</div>
<!--------------------------------------------End of Test Tag---------------------------------------------------------->

<!--------------------------------------------Test Submit details Button---------------------------------------------------------->
						<div class="col-xs-12" style="height: 20px"></div>
						<div class="col-xs-12">
							<span class="btn btn-success btn-flat button-width-large  pull-right"
								onclick="submitTest();"><i class="icon-white icon-heart"></i>
								<spring:message code="lbl.next"/></span>
						</div>
<!--------------------------------------------Test Submit details Button---------------------------------------------------------->						
					</div>
					</section>
					<input type="hidden" id="updatedtestId" name="testId"
						value="${test.testId}">
				</form>
			</div>
          
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
						<h3>
							
						</h3>
						<p></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal"><spring:message code="lbl.ok"/></button>
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

	
		<!-- pop up for adding existing test setting -->

<div class="modal fade" id="testtablePopup" role="dialog"
		aria-labelledby="testtablePopup">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body  page-background-color">
					<div>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="col-xs-12" style="min-height: 30px"></div>
					<table class="table table-hover table-striped" id="testTable">
						<thead>
							<tr>
								<th><spring:message code="lbl.date"/></th>
								<th><spring:message code="lbl.assessmentname"/></th>
								<th><spring:message code="lbl.status"/></th>
								<th style="width: 150px"></th>
							</tr>
						</thead>
						<tbody id="tbody">
						</tbody>
					</table>
					<div class="col-xs-12" style="min-height: 30px"></div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true' />";
        messages['msg.refreshpage'] = "<spring:message code='msg.refreshpage' javaScriptEscape='true' />";
        messages['lbl.published'] = "<spring:message code='lbl.published' javaScriptEscape='true' />";
        messages['lbl.drafted'] = "<spring:message code='lbl.drafted' javaScriptEscape='true' />";
        messages['lbl.selectassessment'] = "<spring:message code='lbl.selectassessment' javaScriptEscape='true' />";
 </script>
<!-- page script -->
<script src="resources/js/test/author/build/test.js?v=3"></script>
<script src="resources/toggle/lib/ToggleSwitch.js"></script>
<!-- For Editor -->
<script src="resources/ckeditor/ckeditor.js"></script>
<!-- <script src="tiny_mce/tiny_mce.js" type="text/javascript"></script> -->
<!-- Select2 -->
<script src="resources/adminlte/plugins/select2/select2.full.min.js"></script>
<script src="resources/adminlte/plugins/jquery-loadTemplate/jquery.tmpl.min.js"></script>
<script>
var updatedTestId='${test.testId}';

var courseId = <%=request.getParameter("courseId")%>;
var courseSectionId = <%=request.getParameter("sectionId")%>;
var contentId = <%=request.getParameter("contentId")%>; // would be if test as content of course is in edit mode.
var startDate = '07-22-2016 10:30 AM';
var minDate =  '<%
final SimpleDateFormat dateFormat = new SimpleDateFormat("MM:dd:yyyy hh:mm a");
out.print(dateFormat.format(new Date()));
%>';
$(document).ready(function(){
		    $(window).on('beforeunload', function(){
		        return '<spring:message code="msg.datalostwarning"/>';
		    });
		    $('.imgset img').css({'max-width':'100%','height': 'auto'});
		        // disable warning
		      //  $(window).off('beforeunload');
		    $("#uploadTrigger").click(function() {
		    	$("#testImage").click();
		    });
		    
		    $('input[name="isSchedule"]').iCheck({
				radioClass : 'iradio_square-green'
			});
		$(".treeview").removeClass("active");
		$("#test").addClass("active");
		$("#test .treeview-menu > #test").addClass("active");
		
		$("#view2").toggleSwitch();
		$("#scoreType").toggleSwitch();
		$("#limitAttempts").toggleSwitch();
		$("#negType").toggleSwitch();
		$("#time").toggleSwitch();
		$("#testPause2").toggleSwitch();
		$("#testAdaptive2").toggleSwitch();
		$("#isReview").toggleSwitch();
		$("#shuffleSection").toggleSwitch();
		$("#shuffleQuestion").toggleSwitch();
		$("#quizRandom").toggleSwitch();
		$("#shuffleOption").toggleSwitch();
		$("#isSchedule").toggleSwitch();
		CKEDITOR.replace('testInstruct', {
			 toolbar:'MA'  ,height :100
		}).on('key', function(e) {
			catValidate();
		});
		//Initialize Select2 Elements
	   var tagData=[];
	   var testView='${test.view}';
	   var selectedNegMark='${test.negMark}';
	   var testTime='${test.testTime}';
	   var testPause='${test.testPause}';
	   var testAdaptive='${test.testAdaptive}';
	   var tag='${test.testTag}';
	   var isRandom='${test.isRandom}';
	   if(testView==1){
		   $("#view2").val('Off');
		   $("#view1").val('1');
		   $("#view2").prop('checked', false);
	   }
	   if(selectedNegMark>0){
		   $("#negMark").val(selectedNegMark);
	   }
	   if(selectedNegMark=='0'){
		   $("#negType").val('Off');
		   $("#negType").prop('checked', false);
		   $("#negMark").val('0');
		   $("#negTypeOnOff").hide();
	   }
	   if(testTime>0){
		   $("#timeMin").val(testTime);
	   }
	   if(testTime=='0'){
		   $("#time").val('Off');
		   $("#testTime").hide();
		   $("#time").prop('checked', false);
	   }
	  if(testPause==1){
		   $("#testPause2").val('On');
		   $("#testPause1").val('1');
		   $("#testPause2").prop('checked', true);
	   }
	  if(testAdaptive==1){
		  $("#testAdaptive2").val('On');
		  $("#testAdaptive1").val('1');
		  $("#testAdaptive2").prop('checked', true);
	   }
	  if(updatedTestId>0){
		  var equalMarkTest='${test.equalMarkTest}';
		  var everyQuestionMark='${test.everyQuestionMark}';
		  var maxAttempts='${test.maxAttempts}';
		  var isReview  = '${test.isReview}';
		  var withCorrect  = '${test.reviewWithCorrect}';
		  var shuffleSection = '${test.shuffleSection}';
		  var shuffleQuestion= '${test.shuffleQuestion}';
		  var shuffleOption = '${test.shuffleOption}';
		  var isSchedule = '${test.isSchedule}';
		 if(equalMarkTest==1){
			 $("#scoreMark").val(everyQuestionMark);
		 }
		 else
			 {
			 $("#scoreType").prop('checked', false);
			 $("#scoreType").val('0');
			 $("#scoreTypeOnOff").hide();
			 }
		 
		 if(maxAttempts!=0){
			 $("#maxAttempts").val(maxAttempts);
			 $("#limitAttemptsOnOff").show();
			 $("#limitAttempts").prop('checked', true);
			 $("#limitAttempts").val('On');
			 $("#limitAttemptsOnOff").show();
		 }
		 
		 if(isReview==1){
			 $("#isReviewOnOff").show();
			 $("#isReview").val('1');
			 $("#isReview").prop('checked', true);
			 if(withCorrect==0)
			     $("#withoutCorrect").prop('checked', true);
			 else
				 $("#withCorrect").prop('checked', true);
		 }
		 if(shuffleSection==1){
			 $("#shuffleSection").prop('checked', true);
			 $("#shuffleSection").val('1');
		 }
		 
		 if(shuffleQuestion==1){
			 $("#shuffleQuestion").prop('checked', true);
			 $("#shuffleQuestion").val('1');
		 }
		 
		 if(shuffleOption==1){
			 $("#shuffleOption").prop('checked', true);
			 $("#shuffleOption").val('1');
		 }
		 if(isSchedule==1){
			    startDate = '${test.schedulePublishDate}';
				$("#schedulePublishDateDiv").show();
		 }
		 if(isRandom == 1) {
			 $("#quizRandom").prop('checked', true);
			 $("#quizRandom").val('1');
			 $("#limitMaxQuestionsOnOff").show();
			 $("#maxQuestions").val('${test.maxQuestions}');
		 }
	  } 
	  if(tag!=''){
	  var tagArray=tag.split(',');
	  var tagIds=[];
	 for(var i=0;i<tagArray.length;i++){
		 var arr={id:tagArray[i],text:tagArray[i]};
		 tagData.push(arr);
		 tagIds.push(tagArray[i]);
	 }
	 $(".select2").select2({
	 		data:tagData, 
			tags : true,
			tokenSeparators : [ ',', ' ' ]
		});
	  $("#multiSelectTag").select2('val',tagIds);

	  } 
	  else
		  {
		  $(".select2").select2({
				tags : true,
				tokenSeparators : [ ',', ' ' ]
			});
		  }
      //Flat red color scheme for iCheck
      $('#withCorrect.flat-red').iCheck({
      radioClass: 'iradio_flat-green'
    });
    $('#withoutCorrect.flat-red').iCheck({
        radioClass: 'iradio_flat-green'
      });

    $('#schedulePublishDate').daterangepicker({
    	locale: {
    	      "format": 'MM-DD-YYYY h:mm A' ,
    	      "applyLabel": paggingmessages['lbl.apply'],
	          "cancelLabel": paggingmessages['lbl.cancel'],
	          "fromLabel": paggingmessages['lbl.from'],
	          "toLabel": paggingmessages['lbl.to'],
	          "customRangeLabel": "Custom",
	          "daysOfWeek": [
	              paggingmessages['lbl.sunday'],
	              paggingmessages['lbl.monday'],
	              paggingmessages['lbl.tuesday'],
	              paggingmessages['lbl.wednesday'],
	              paggingmessages['lbl.thursday'],
	              paggingmessages['lbl.friday'],
	              paggingmessages['lbl.saturday']
	          ],
	          "monthNames": [
	              paggingmessages['lbl.jan'],
	              paggingmessages['lbl.feb'],
	              paggingmessages['lbl.mar'],
	              paggingmessages['lbl.apr'],
	              paggingmessages['lbl.may'],
	              paggingmessages['lbl.jun'],
	              paggingmessages['lbl.jul'],
	              paggingmessages['lbl.aug'],
	              paggingmessages['lbl.sep'],
	              paggingmessages['lbl.oct'],
	              paggingmessages['lbl.nov'],
	              paggingmessages['lbl.dec']
	          ],
    	    },
        "singleDatePicker": true,
        "showDropdowns": true,
        "showWeekNumbers": true,
        "timePicker": true,
        "timePicker24Hour": true,
        "startDate": startDate,
        "minDate": minDate,
        "drops": "up"
    }, function(start, end, label) {
      console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
    });
	});
	
	

function fun(value) {

	if(value == 'Subject'){
	var e = document.getElementById("changeoptSubject");
	var strUser = e.options[e.selectedIndex].text;
	var dataString={
	"chapter":strUser
	}
	$.ajax({
	type : 'POST',
	url : 'getdata',
	data : dataString,
	success : function(data) {
		console.log(data);
		if(data.status=='success')
        {
			var testHtml=$("#chapteroptiontemplate").tmpl(data);
			$("#changeoptChapter").html(testHtml);
			
			var testHtml=$("#sessionoptiontemplate").tmpl(data);
			$("#changeoptAct").html(testHtml);
		}
	else
		{
		var testHtml=$("#chapteroptiontemplate").tmpl("");
		$("#changeoptChapter").html(testHtml);
		var testHtml=$("#sessionoptiontemplate").tmpl("");
		$("#changeoptAct").html(testHtml);
		}
		
	
		
	}
	});
	}
	}

</script>
</html>
<script type="text/html" id="chapteroptiontemplate">
 {{each(i) chapter}}
    <option value="${'$'}{chapter[i]}">${'$'}{chapter[i]}</option>
{{/each}}
</script>
<script type="text/html" id="sessionoptiontemplate">
 {{each(i) act}}
    <option value="${'$'}{act[i]}">${'$'}{act[i]}</option>
{{/each}}
</script>
