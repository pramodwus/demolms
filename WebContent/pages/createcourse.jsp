<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>


<link rel="stylesheet" href="resources/css/custom.css">
<%@ include file="include.jsp"%>

<style>
.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
}


.form-control {
	border-radius: 0;
	box-shadow: none;
	border-color: #d0d0d0;
}

.skin-black-light .wrapper, .skin-black-light .main-sidebar,
	.skin-black-light .left-side {
	background-color: #f9fafc;
	border: 1px solid #d0d0d0;
	margin-top: 15px;
}

.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
}



.skin-black-light .wrapper, .skin-black-light .main-sidebar,
	.skin-black-light .left-side {
	background-color: #f9fafc;
	border: 1px solid #d0d0d0;
	margin-top: 15px;
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

#fa {
	padding-right: 15px;
}

.input-lg {   
	height: 200px;
}

.img-container {
    width: 100%;
    position: relative;
    display: inline-block;
    margin: 10px 5px 5px;
}

.img-text {
    width: 100%;
    display: block;
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
	<%@ include file="header.jsp"%>
	<%@ include file="leftmenu.jsp"%>  

		<div class="col-sm-12" style="padding-bottom: 20px;">
		<div class=" content-wrapper">
		<div class="col-sm-12">		
		    <div class="h4 capitalize"><spring:message code="lbl.course" text="Learn Path"/><i class="fa fa-angle-right" style="padding-left:5px;"></i> ${course.courseName}</div>
           <div class="nav-tabs-custom" style="margin-top:30px" style="background-color:#FFF" >           
            <ul class="nav nav-tabs" >
                  <li id="li1" class="active" style="border-left:1px solid #dedede;width:33.32%;">
                  <a href="#basic_info"  style="text-align:center;">
                  <c:if test="${course.courseTag!=null}">
										<i class="fa fa-check-circle-o color-green" id="fa"></i>
									</c:if>
									 <c:if test="${course.courseTag==null}">
										<i class="fa fa-circle-thin" id="fa"></i>
									</c:if><spring:message code="lbl.courseinformation" text="Learn Path Information"/></a></li>
                  <li id="li2" style="border-left:1px solid #dedede;width:33.32%;">
                  <a href="#" onclick="ontabsclick(2)"  style="text-align:center;">
                  <c:if test="${not empty sectionlist}">
									  <i class="fa fa-check-circle-o color-green"	id="fa"></i>
									</c:if>
									<c:if test="${empty sectionlist}">
									  <i class="fa fa-circle-thin"	id="fa"></i>
									</c:if>
									<spring:message code="lbl.addcontent" text="Add Content"/>
                           </a></li>
                  <li style="border-left:1px solid #dedede;width:33.32%;">
                  <a href="#"  style="text-align:center;">
                  <i class="fa fa-circle-thin" id="fa"></i>
                  <spring:message code="lbl.previewandpublish" text="Preview & Publish"/>
                  </a></li> 
                </ul>
            </div>    
				<%-- <div style="text-align: center; margin-top: 10px;"
					class="col-sm-12 form-group">
					<div>
						<img src="resources/adminlte/dist/img/courseinfo.png">
					</div>
					<label><spring:message code="header.createcourse" text="Enter basic information about course"/></label>
				</div> --%>
								
				 <%-- <div class="col-sm-12 form-group">	
				
                    <img src="${course.courseImageUrl}" width="100%"  height="400px" id="courseImagePreview"
								onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'" alt="...">
								
					<div class="row" style="margin-top: -3.0%;text-align:center">			
					<div class="col-sm-12" style="text-align:center">
					<div>			
					<span><img src="resources/adminlte/dist/img/imgmsg.png" style="max-width:100%;height:auto;"></span>
					<span style="margin-left: -3px;"><img id="uploadTrigger" onclick="coursekeyValidate();" src="resources/adminlte/dist/img/camera.png"></span>
					</div>
					</div>					
					<br />
					<label class="requireFld" id="courseImageError"><spring:message code="msg.images.required" text="Only images are supported."/></label>
					<br />
					<label class="requireFld" id="courseImageError1"><spring:message code="msg.images.size" text="Maximum image size is 350KB."/></label>			                    
                 </div>
                 </div> --%> 
            <div class="tab-content" style="background-color: #ECF0F5 !important;">
               <div id="basic_info" class="tab-pane active">
                <form name="courseform" id="courseform" action="saveCourse"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="courseId" value="${course.courseId}" id="courseId">
						<div class="col-xs-12 form-group">
							<label> <sup><font color="red" size="3px">*</font></sup>
							<spring:message code="lbl.coursetitle" text="Learning Objective"/>:
							</label>
							<p><spring:message code="msg.maxcharacterlength" text="Maximum 50 characters allowed." arguments="50" htmlEscape="false" argumentSeparator=";"/></p>
							<input type="text" name="courseName" id="courseName"
								class="col-lg-6 form-control" onkeyup="coursekeyValidate();" value="${course.courseName}" maxlength="50">
							<label class="requireFld" id="courseNameError"><spring:message code="msg.empty" text="This Field is required."/></label>
						    <label class="requireFld" id="courseNameError1"><spring:message code="msg.maxcharacterlength" text="Maximum 50 characters allowed." arguments="50" htmlEscape="false" argumentSeparator=";"/></label>
						</div>			
						<!--------------------------- subtitle hide------------------------------------------------------->			
						<div class="col-xs-12 form-group hide">
							<label><spring:message code="lbl.coursesubtitle" text="Leaning Sub Objective"/>:</label>
							<p><spring:message code="msg.maxcharacterlength" text="Maximum 50 characters allowed." arguments="50" htmlEscape="false" argumentSeparator=";"/></p>
							<input type="text" name="subTitle" id="subTitle"
								class="col-lg-6 form-control" value="${course.subTitle}" maxlength="50">
						   <label class="requireFld" id="courseNameError"><spring:message code="msg.empty" text="This Field is required."/></label>
						   <label class="requireFld" id="courseNameError1"><spring:message code="msg.maxcharacterlength" text="Maximum 50 characters allowed." arguments="50" htmlEscape="false" argumentSeparator=";"/></label>
						</div>
						<!--------------------------- subtitle------------------------------------------------------->		
						<div class="col-xs-12 form-group" style="padding: 0px 0px 0px 0px">
							<div class="col-xs-4 form-inline" >
                                   <div class="form-group ">
                                    <label><spring:message code="lbl.levelofcourse" text="Difficulty Level"/>:&emsp;</label>
									<select class="form-control"
										id="courselevel" name="levelId">
									</select>
								</div>
							</div>
						</div>
						<div class="col-xs-12 form-group">
							<label for="promoVideoUrl"><spring:message code="lbl.promotionalvideo" text="Promotional Video"/>:</label>
							<br/>
							<input type="text" name="promoVideoUrl"
								id="promoVideoUrl" class="form-control"
								onkeyup="coursekeyValidate();" onblur="playVideo('${course.promoVideoUrl}')"
								style="height: 30px; width: 500px" placeholder="<spring:message code="placeholder.promotionalvideo" text="Add link from youtube"/>" value="${course.promoVideoUrl}">
								<label class="requireFld" id="promoVideoUrlError"><spring:message code="msg.youtubeurl.invalid" text="URL is not valid."/></label>		
						</div>
						<div class="col-xs-12 form-group">
						<div id="promoVideo"></div>
						</div>
						<div class="col-xs-12 form-group" style="padding: 0px 0px 0px 0px">							
							<div class="col-xs-12" style="padding: 0px 0px 0px 0px">
								<div class="col-xs-4 form-inline">

									<div class="form-group">
                                        <label><spring:message code="lbl.language" text="Language"/>:&emsp;</label>
										<select class="form-control"
											id="courselanguage" name="languageId">
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 form-group">
						<label style="width: 26%;" class="pull-left"><spring:message code="lbl.doyouwantpublishimmediately" text="Do you want publish immediately ?"/></label>
							<div class="col-xs-8">
							<c:if test="${course.isSchedule==0 || course.isSchedule==null}">
							<input type="radio" value="0" name="isSchedule" checked>&nbsp;Yes&nbsp;
						    <input type="radio" value="1" name="isSchedule">&nbsp;No
							</c:if>
							<c:if test="${course.isSchedule==1}">
							<input type="radio" value="0" name="isSchedule">&nbsp;Yes&nbsp;
						    <input type="radio" value="1" name="isSchedule" checked>&nbsp;No
							</c:if>
										
						<!-- <input type="checkbox" value="0" id="isSchedule" name="isSchedule"
									onclick="changeState('isSchedule');" checked> -->
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
									<div class="col-xs-8 padding-left" style="vertical-align:middle;line-height:2.4">
									<span class="pull-left">(<spring:message code="lbl.immediatepublishcourse.note" text="Enter schedule date for publish the learn path."/>)</span>
									</div>
						</div>
						<div class="col-xs-12 form-group">
							<label> <sup><font color="red" size="3px">*</font></sup><spring:message code="lbl.tags" text="Tags"/>:
							</label>
							<p>Class/ Group Tags/ Subject Tags/ Test Tags</p>
							<div id="courseTag" style="border: 1px solid white">
								<select class="select2 form-control" name="courseTag" id="courseTagList"
									multiple="multiple" style="width: 100%; min-height: 100px"
									onchange="coursekeyValidate();">
									<option value="Class">Class</option>
									<option value="Group">Group</option>
									<option value="Subject">Subject</option>
									<option value="Test">Test</option>
									<option value="12th">12th</option>
									<option value="B.Tech">B.Tech</option>
									<option value="CBSE">CBSE</option>
								</select>
							</div>
							<label class="requireFld" id="courseTagError"><spring:message code="msg.empty" text="This field is required."/></label>
						</div>
						<div class="col-xs-12 form-group">
							<label> <sup><font color="red" size="3px">*</font></sup><spring:message code="lbl.description" text="Description"/>:
							</label>
							<textarea name="courseDesc" id="courseDesc" class="col-lg-6 form-control textAreaControl"
							 maxlength="2000" onkeyup="coursekeyValidate();">${course.courseDesc}</textarea>
							<label class="requireFld" id="courseDescError"><spring:message code="msg.empty" text="This field is required."/></label>
							<label class="requireFld" id="courseDescError1"><spring:message code="msg.maxcharacterlength" text="Maximum 2000 characters allowed." arguments="2000" htmlEscape="false" argumentSeparator=";"/></label>
						</div>
						<div class="col-xs-12 form-group" style="min-height: 100px">
							<label><spring:message code="lbl.coverimage" text="Cover Image"/>:</label> <br />
							<c:if test="${course.courseImageUrl != null}">
							 <img src="${course.courseImageUrl}"
								onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'" alt="..."
								style="width: 150px; height: 100px;" class="margin"
								id="courseImagePreview">
							</c:if>
							<c:if test="${course.courseImageUrl == null}">
							<img src="resources/adminlte/dist/img/photo1.png" alt="..."
								style="width: 150px; height: 100px;" class="margin"
								id="courseImagePreview">
							</c:if> 
							&emsp;&emsp;
							<button type="button"
								class="btn btn-success btn-flat  button-width-large"
								id="uploadTrigger" onclick="coursekeyValidate();"><spring:message code="lbl.uploadcoverimage" text="Upload Cover Image"/></button>
							<input type="file" class="hidden" name="imageName"
					            id="courseImage" onchange="showMyImage(this)" value="${course.imageName}">
								<br />
								<label	class="requireFld" id="courseImageError">
								<spring:message code="msg.images.required" text="Only images are supported."/></label>
								<br />
								<label
								class="requireFld" id="courseImageError1">
								<spring:message code="msg.images.size" text="Maximum image size is 350KB."/>
								</label>

						</div>
						
						<!--------------------------- subtitle hide------------------------------------------------------->		
						<div class="hide">
						<div class="col-xs-12 form-group">
							<label><spring:message code="lbl.coursehighlights" text="Learn Path Highlights"/>: </label> <input type="text"
								name="highlights" id="coursehighlights1"
								class="form-control" maxlength="500">
						</div>
						<div class="col-xs-12 form-group">
							<input type="text" name="highlights" id="coursehighlights2"
								class="col-lg-6 form-control" maxlength="500">
						</div>
						<div class="col-xs-12 form-group">
							<input type="text" name="highlights" id="coursehighlights3"
								class="col-lg-6 form-control" maxlength="500">
						</div>	
						</div>
						<!--------------------------- subtitle hide------------------------------------------------------->		
											
						<div class="hide"><!-- hide not using content -->
						<div class="col-xs-12" style="height: 30px"></div>
						<div class="col-xs-12">
							<label><b><spring:message code="lbl.price" text="Price"/>:</b></label><br>
						</div>
						<div class="col-xs-12">
							<div class="col-xs-1">							
                            <div class="radio"><label><input type="radio" name="isPaid" value="0" checked><spring:message code="lbl.free" text="Free"/></label></div> 
							</div>					
						</div>

<!-- 						<div class="col-xs-12" style="height: 30px"></div>
						<div class="col-xs-12">
							<label><b>Scheduled:</b></label><br>
						</div>
						<div class="col-xs-12">
							<div class="col-xs-2">
                            <div class="radio"><label><input type="radio" name="isSchedule" checked value="0">Any Time</label></div> 
							</div>
							<div class="col-xs-2 hide">							
                           <div class="radio"><label><input type="radio" value="1" name="isSchedule">Time Bound</label></div> 
							</div>		
						</div> -->
						</div><!-- /. hide not using content -->		
						
										
						<div style="text-align: center; margin-top: 10px;">							
							<button type="button" onclick="submitCourseForm();"
							 class="btn btn-success btn-flat  button-width-large">
							 <spring:message code="lbl.saveandnext" text="Save & Next"/>
							 </button>
						</div>										
			</form>
               </div>               
               <div id="settings" class="tab-pane">
               </div>
            </div>			
			<!-- nav tabs -->
			
		</div> 	<!-- content-wrapper -->
		</div>
		</div>
		</div>	
	<!-- ./wrapper -->

	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4></h4>
				</div>
				<div>
					<img src="<spring:url value='/resources/images/qq.png'/>" style="width: 100%">
				</div>
			</div>
		</div>
	</div>
</body>
<script src="<spring:url value='/resources/toggle/lib/ToggleSwitch.js'/>"></script>
<script src="<spring:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<script src="<spring:url value='/resources/js/Custom.js'/>"></script>
<script src="<spring:url value='/resources/js/createcourse.js?v=7'/>"></script>
<script src="<spring:url value='/resources/js/youtubeplayer.js'/>"></script>
<!-- Select2 -->
<script src="<spring:url value='/resources/adminlte/plugins/select2/select2.full.min.js'/>"></script>
<script>
var startDate = '07-22-2016 10:30 AM';
var minDate =  '<%
final SimpleDateFormat dateFormat = new SimpleDateFormat("MM:dd:yyyy hh:mm a");
out.print(dateFormat.format(new Date()));
%>';
	$(function() {
		$(window).on('beforeunload', function() {
			return '<spring:message code="msg.datalostwarning"/>';
		});
		$(document).on("submit", "#courseform", function(event) {
			// disable warning
			$(window).off('beforeunload');
		});
		$(".treeview").removeClass("active");
		$("#course").addClass("active");
		$("#course .treeview-menu > #course").addClass("active");
		/* $("#isSchedule").toggleSwitch(); */
		$("#uploadTrigger").click(function() {
			$("#courseImage").click();

		});
		
		$('input[name="isSchedule"]').iCheck({
			radioClass : 'iradio_square-green'
		});
		
		var isSchedule = '${course.isSchedule}';
		if(isSchedule==1){
			    //$("#isSchedule").val('1');
			    //$("#isSchedule").prop('checked', false);
			    startDate = '${course.schedulePublishDate}';
				$("#schedulePublishDateDiv").show();
		     }
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
</script>
<script> 
        var tagData=[];
        var tag='${course.courseTag}';
        var levelId=0;
        var languageId=0;
	    $(function() {
           /*  $("#ansType1Button").toggleSwitch();
            $("#topause").toggleSwitch();
            $("#toreview").toggleSwitch();
            $("#toview").toggleSwitch(); */
	    /* 	 $(".select2").select2({ 
	 			tags : true,
	 			tokenSeparators : [ ',', ' ' ]
	 		}); */
            
            
        	if($("#courseId").val()>0)
        		{
        		levelId='${course.levelId}';
        		languageId='${course.languageId}';
        		var highlights='${course.coursehighlights}';
        		
        		if(highlights.length>0){
        			var coursehighlights=highlights.split('####');
        			for(var i=0;i<coursehighlights.length-1;i++){
        				$("#coursehighlights"+(i+1)).val(coursehighlights[i]);
        			}
        		}
        		}
	    });
	    function showAdvance(){
	    	if(coursebasicValidate()){
	    	$(window).scrollTop(0);
	    	$("#basicInformation").hide();
	    	$("#advanceSetting").show();
	    	}
	    	if($("#courseId").val()>0){
	    		playVideo();
	    	}
	    }
	    function showBasicInformation(){
	    	$(window).scrollTop(0);
	    	$("#advanceSetting").hide();
	    	$("#basicInformation").show();
	    	
	    }
	    function publishPage(){
	    	$("#advanceSetting").hide();
	    	$("#publishPage").show();
	    }
	    
	    
		  if(tag!=''){
			  var tagArray=tag.split(',');
			  var tagIds=[];
			 for(var i=0;i<tagArray.length-1;i++){
				 var arr={id:tagArray[i],text:tagArray[i]};
				 tagData.push(arr);
				 tagIds.push(tagArray[i]);
			 }
			 $(".select2").select2({
			 		data:tagData, 
					tags : true,
					tokenSeparators : [ ',', ' ' ]
				});
			  $("#courseTagList").select2('val',tagIds);

			  } 
			  else
				  {
				  $(".select2").select2({
						tags : true,
						tokenSeparators : [ ',', ' ' ]
					});
				  }
		  
    </script>
</html>
