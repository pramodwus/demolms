<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="/pages/include.jsp"%>
<link rel="stylesheet" href="<spring:url value='/resources/css/custom.css'/>">
<style>
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

</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
	<div id="overlay" class="overlay1" style="display: none;position: fixed; left: 0;top: 0;  bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity=80); z-index:9999;">
 		<img id="loading" class="lazy" src="<spring:url value='/resources/images/loading.gif'/>" style="position:fixed;left: 50%; top: 50%;">
	</div>
         <%@ include file="/pages/header.jsp"%>
		<%@ include file="/pages/leftmenu.jsp"%>
		<%@ include file="/pages/dialogs.jsp"%>
       <div class="col-sm-12" >
		<div class="content-wrapper" style="min-height:0px">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<div class="col-md-12 h3"><spring:message code="lbl.course" text="Course"/><i class="fa fa-angle-right" style="padding-left:5px;"></i>
			 <span id="courseName" class="capitalize"></span></div>
			</section>
			<!-- Main content -->
			<section class="content">
			<div class="row"> 	
			   <div class="col-md-12 col-sm-12 col-xs-12">					
				  	<div class="col-sm-12">
                     <div class="form-group">	
                          <img id="courseImage" src="${course.courseImageUrl}" height="100%" width="300px" class="pull-right"
								onerror="this.src='<spring:url value='resources/adminlte/dist/img/photo1.png'/>'" alt="...">                    
                      </div> 
                      
         	                    <div class="form-group">
										<strong><spring:message code="lbl.levelofcourse" text="Level Of Course"/>:&nbsp;&nbsp;</strong><span id="levelName"></span>
								</div>
								
								<div class="form-group">
								<strong><spring:message code="lbl.promotionalvideo" text="Promotional Video"/>:&nbsp;&nbsp;</strong><span id="promoVideoURLSpan"></span>
								<div class="form-group" id="promoVideoDiv2">
							    </div>
							    <div class="form-group">
										<strong><spring:message code="lbl.language" text="Language"/>:&nbsp;&nbsp;</strong>
										<span id="languageName"></span>
								</div>
									
								<div class="form-group">
										<strong><spring:message code="lbl.tags" text="Tags"/>:&nbsp;&nbsp;</strong>
										<span id="courseTag"></span>										
								</div>
								
								<div class="form-group">
										<strong><spring:message code="lbl.description" text="Description"/>:&nbsp;&nbsp;</strong>
										<p class="justifyText"><span id="courseDesc"></span></p>										
								</div>		
								<!-------------------------- /.hide hightlights------------------------------->								
									<div class="form-group hide">
										<strong><spring:message code="lbl.coursehighlights" text="Course Highlights"/> :</strong>										
											<span id="Blankhighlightsspan"></span>
											<ul id="Blankhighlightslist"></ul>										
									</div>
									</div>
					</div>
					</div>
					</div>
					</section>
			<input type="hidden" id="userid" value="<%= ((User)request.getSession().getAttribute("userSession")).getUserId() %>">
			<div class="row page">
					<div class="col-xs-12" style="min-height: 30px"></div>
					<div class="col-md-12 col-sm-12 col-xs-12 "
						style="min-height: 50px">
						<div class="col-xs-12">
							<nav>
							<ul class="pager pull-right" style="margin: auto">
							   <li class="hide">
								  <button type="button" id="enrollbtn"  class="btn btn-success btn-flat button-width-large" onclick="enrollcoursebyuser(${courseId})">
									   <spring:message code="lbl.enroll" text="Enroll"/>
								  </button>
								</li>
								<li>
								  <button type="button" onclick=""
								  class="btn btn-success btn-flat button-width-large" id="start_course_button"><spring:message code="lbl.startcourse" text="Start Course"/></button>
								</li>										
							</ul>
							</nav>
						</div>

					</div>
	    </div>
	    </div>
	</div>	
	</div>	
	<!-- content-wrapper -->
	<input type="hidden" id="userId"
									value="<%=((User) request.getSession().getAttribute("userSession"))
					.getUserId()%>">
	
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['lbl.na'] = "<spring:message code='lbl.na' text='NA' javaScriptEscape='true' />";
        messages['lbl.resumecourse'] = "<spring:message code='lbl.resumecourse' text='Resume Course' javaScriptEscape='true' />";
        messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' text='Something went wrong,Please try again.' javaScriptEscape='true' />";
        messages['msg.courseenrolledsuccessfully'] = "<spring:message code='msg.courseenrolledsuccessfully' text='Course enrolled successfully.' javaScriptEscape='true' />";
</script> 
<script>
	$(function() {
		$(".treeview").removeClass("active");
		$("#course").addClass("active");
		$("#studentCourse").addClass("active");
		var courseId='${courseId}';
		callAjax(courseId);
		getActivityList(courseId);
	})
	
	var callAjax= function(courseId){
		$("#overlay").show();
		$.ajax({
			url : "api/coursedetails/"+courseId,
			type : "GET", // @summary request type is get. 
			async : false,
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', 'Browser');
			},

			error : function() { // @summary would be execute if any error occurs.
				$("#overlay").hide();
				location.href='studentCourse?action=courseList';
			},
			success : function(data) { //@summary would be execute on successfull getting course list.
             
				/**
				 * @summary if successfully received course details based on provided course id.
				 */
				 if(data.course.courseId!=null && data.course.courseId>0){
					/**
					 * @summary calling function for extracting course details from list.
					 */
					 extractCoursedata(data);
				 }
				 else {
					 location.href='studentCourse?action=courseList';
				 }
				 $("#overlay").hide();
			}
		});
	}
	
	var getActivityList = function(courseId){
		$("#overlay").show();
		$.ajax({
			url : "api/getCourseAttemptDetails/"+courseId+"/"+parseInt($.trim($("#userId").val())),
			type : "GET", // @summary request type is get. 
			async : false,
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', 'Browser');
			},

			error : function() { // @summary would be execute if any error occurs.
				$("#overlay").hide();
			},
			success : function(data) { //@summary would be execute on successfull getting course list.
				/**
				 * @summary calling function for extracting course details from list.
				 */
				 if(data.courseActivityList!=null && data.courseActivityList.length>0){
					 $("#start_course_button").text(messages['lbl.resumecourse']);
				 }
				 $("#overlay").hide();
			}
		});
	}
	
	function checkPromoURL(promoUrl){
		var urlStatus=false;
		var myRegExp=/^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
		if (myRegExp.test(promoUrl)) {
			urlStatus=true;
		}
		return urlStatus;
	}
	var tag;
	var videoId;
	// 1. function for playing video.
	function playVideo(videoUrl){ 
		var promoVideoUrl = videoUrl.trim();

		if(checkPromoURL(promoVideoUrl)){
		    // 2. This code loads the IFrame Player API code asynchronously.
			    tag = document.createElement('script');
			    tag.src = "https://www.youtube.com/iframe_api";
			    var firstScriptTag = document.getElementsByTagName('script')[0];
			    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
	                    var isV=promoVideoUrl.indexOf("v=");
	                    videoId = (isV!= -1)?(promoVideoUrl.split('v=')[1]):(promoVideoUrl.substring(promoVideoUrl.lastIndexOf("/")+1));
	                    var indexofamp = videoId.indexOf("&");
	        			if(indexofamp!=-1){
	        				videoId = videoId.substring(0,videoId.indexOf("&"));
	        			}
	                  // alert(videoId);
		    }
	}
	    // 3. This function creates an <iframe> (and YouTube player)
	    //    after the API code downloads.
	    var player;
	    function onYouTubeIframeAPIReady() {
	      player = new YT.Player('promoVideo', {
	        height: '250',
	        width: '400',
	        videoId: videoId,
	        events: {
	          'onReady': onPlayerReady,
	          'onStateChange': onPlayerStateChange
	        }
	      });
	    }
        
	    var done = false;
	    // 4. The API will call this function when the video player is ready.
	    function onPlayerReady(event) {
	      //event.target.playVideo();
	    	event.target.stopVideo();
			done = true;
	    }

	    // 5. The API calls this function when the player's state changes.
	    //    The function indicates that when playing a video (state=1),
	    //    the player should play for six seconds and then stop.
	    function onPlayerStateChange(event) {
	      if (event.data == YT.PlayerState.PLAYING && !done) {
	        setTimeout(stopVideo, 6000);
	        done = true;
	      }
	    }
	    function stopVideo() {
	      player.stopVideo();
	    }
	    var extractCoursedata=function(data){
	    	try{
	    	var previewAction = "location.href='viewCourseContent?courseId="+data.course.courseId+"'";
	    	if(data.course.courseType==1){
	    		previewAction = "location.href='viewscormcoursecontent?courseId="+data.course.courseId+"'";
	    	}		
	    	$("#start_course_button").attr('onclick',previewAction);	
	    	$("#courseName").text(data.course.courseName);
	    	$("#courseTag").html(data.course.courseTag);
	    	/* $("#courseImageDiv").empty();
	    	var imageHtml = '<img src="'+data.course.courseImageUrl+'" onerror="this.src=\'resources/images/Trignometry.jpg\'" style="width:100%;height:100%;padding-top:50%" id="courseImageUrl">';
	    	$("#courseImageDiv").append(imageHtml); */
	    	$("#courseImage").attr("src",data.course.courseImageUrl);
	    	//$('.imgset img').imgCentering({'forceWidth': true});
	    	$("#languageName").html(data.course.languageName);
	    	$("#levelName").html(data.course.levelName);
	    	$("#courseInfo").html(data.course.courseInfo);
	    	$("#courseDesc").html(data.course.courseDesc);
	    	if(data.course.isEnroll!=0){
	    		$("#enrollbtn").hide();
	    	}
	    	if(data.course.highlights.length==0){
	    		$("#Blankhighlightsspan").text(messages['lbl.na']);
	    		$("#Blankhighlightslist").remove();
	    	}
	    	else
	    		{
	    		$("#Blankhighlightsspan").remove();
				    	for(var i=0;i<data.course.highlights.length;i++){
				    			$("#Blankhighlightslist").append('<li>'+data.course.highlights[i]+'</li>');
	    	                }
	    		}
	    	if(data.course.promoVideoUrl==""){
	    	$("#promoVideoDiv1").append('&nbsp;&nbsp;&nbsp;&nbsp;<span class="h3">'+messages['lbl.na']+'</span>');
	    	}
	    	else
	    		{
	    	$("#promoVideoDiv2").append('<div id="promoVideo"></div>');
	    	$("#promoVideoURLSpan").append((data.course.promoVideoUrl));
	    	playVideo(data.course.promoVideoUrl);
	    		}
	    }catch(err){$("#overlay").hide();}
	    }
	    
	    /* Method for save course enrollment by user */
	    var enrollcoursebyuser = function(courseid){
	      	var data = JSON.stringify({
			    'courseId': courseid,
			    'userId': $("#userid").val()
	           });
	         
    	 $.ajax({
   		   url:"api/enrollmentcoursebyuser",
   		   beforeSend : function(xhr) { // @summary appending data in header before sending request.
			xhr.setRequestHeader('authorization', 'Browser');
			xhr.setRequestHeader('timestamp', "Browser");
		   },
   		   type:'POST',
   		   dataType : 'json',
		   contentType : "application/json",
   		   data : data,
   		   error : function(){
   			alert("error");
   		   },
   		   success : function(response){
   			if(response.status==200){
   				$("#enrollbtn").attr("disabled","true");
   				$("#successdialog p").text(messages['msg.courseenrolledsuccessfully']);
   				$('#successdialog').modal('show') ;
   			}else{
   				$("#errordialog p").text(messages['msg.somethingwentwrong']);
				$("#errordialog").modal('show') ;   				
   			}
   		   }
   		});    	
	    }
</script>
</html>