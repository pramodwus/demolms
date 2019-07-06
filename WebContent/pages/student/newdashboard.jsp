<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
        crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>  
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <link rel="icon" href="resources/images/allimages/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<spring:url value='/resources/css/newcommon.css'/>">
    <link rel="stylesheet" href="<spring:url value='/resources/css/course_detail1.css'/>">   
          
<title>eLuminate</title>
<style>
	.nav-pills .nav-link.active, .nav-pills .show>.nav-link{
		border-bottom:5px solid #68c721;
		background:transparent;
		color: #212529;
	}
	.nav-pills .nav-link {
    	border-radius: 0rem;
	}
	.themeBtn {
	    background: rgb(97, 151, 220);
	    color: #ffffff !important;
	    display: inline-block;
	    font-size: 15px;
	    font-weight: 500;
	    height: 40px;
	    line-height: 0.8;
	    padding: 5px 30px;
	    text-transform: capitalize;
	    border-radius: 1px;
	    letter-spacing: 0.5px;
	    border: 0px !important;
	    cursor: pointer;
	    border-radius: 100px;
	}
	.themeBtn:hover {
	    background: #68c721;
	    color: #ffffff;
	    -webkit-transform: scale(1.02);
	    -ms-transform: scale(1.02);
	    transform: scale(1.02);
	    box-shadow: 0 2px 8px 0 rgba(0,0,0,.2);
	}
	.session-title{
		flex: 1;
	    font-size: 18px;
	    line-height: 22px;
	    font-weight: 600;
	    margin-bottom:1rem;
	}
	@media (max-width: 575.98px) {
	.responsive-button{
	font-size: 12px;
	padding: 5px 12px;
	height: 25px;
    line-height: 0.8;
	}	
}
</style>
</head>
<body>
<!--------------------- Navigation Bar Start ------------------------------------------------>
    <nav class="navbar navbar-expand-md sticky-top navbar-light border">
        <div class="brand-logo">
            <a href="https://qa.eluminate.in"><img src="resources/images/eLuminateHeaderLogo.png" class="header-img-logo"></a>
        </div>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#eluminateNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="eluminateNavbar">
            <ul class="navbar-nav align-items-center ml-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link px-3"></a>
                </li>
                <li class="nav-item">
                    <div class="dropdown">
                        <ul class="list-inline dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <li class="list-inline-item mr-0">
                                <a href="#" class="nav-link font-weight-bold text-dark">${userName}</a>
                            </li>
                            <li class="list-inline-item">
                                <a href="#" class="">
                                    <img src="resources/images/userimage.png" class="header-user-image">
                                </a>
                            </li>
                        </ul>
                        <div class="dropdown-menu dropdown-menu-right new-style" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="https://qa.eluminate.in/eluminate/profile">Profile</a>
                            <a class="dropdown-item" href="logout">Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
  <!--------------------------- Navigation Bar End ---------------------------------------->
   
    <div class="container-fluid">
        <div class="row"> 
            <!-- -----------Sidebar Code Comes Here------------- -->
            <div class="col-md-3 col-xs-12 pl-0 pr-0" id="sidebar">
                <div class="list-group sidebar text-center text-md-left">
                    <div class="back-to-chapters-box px-3 pt-3 pb-3">
                    <form method="post" action="coursechapter" id="coursechapterform">
                     <input type="hidden" name="courseId" value="${courseId}"/>
					 <input type="hidden" name="sectionId" value="${sectionId}"/>
                    </form>
                      <%--   <a class="back-to-chapters" href="coursechapter?courseId=${courseId}&sectionId=${sectionId}">--%>
                            <a class="back-to-chapters" href="javascript:void(0)" onclick="document.getElementById('coursechapterform').submit(); return false;">
                             <i class="fa fa-arrow-left"></i>
                            <span>Back to Courses</span>
                        </a>
                    </div>
                </div>
            </div>
            <!-- -----------Sidebar Code end Here------------- -->
            
            <main class="col pl-2 pt-2 content-wrapper" style="min-height: 101vh;"> 
                <c:forEach items="${section}" var="sectionlist">
				<c:if test="${sectionlist.isPractice==1  && sectionlist.isChapterTest == 0}">
                    <form action="practicetest" method="post"
						id="practicetest${sectionlist.sectionId}">
						<input type="hidden" name="courseId" value="${courseId}" /> <input
							type="hidden" name="sectionId" value="${sectionlist.sectionId}" />
					</form>
					<a href="javascript:void(0)"
						onclick="document.getElementById('practicetest${sectionlist.sectionId}').submit(); return false;">
						<button target="_blank" class="themeBtn float-right mt-2 responsive-button">Practice
							Test</button>
					</a>
                 </c:if>
			   </c:forEach>
                           
            		<nav aria-label="breadcrumb" id="navBar">
							<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
							<form action="coursechapter" method="post" id="coursechapterback">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																       </form>
																       &nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item "><a href="javascript:void(0)" onclick="document.getElementById('coursechapterback').submit(); return false;" style="font-weight:bold;"><%-- <a href="coursechapter?courseId=${courseId}&sectionId=${sectionId}"> --%>${course.courseName}</a></li>

																&nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;text-decoration: underline;">${sectionname}</a></li>
						
							</ol>
						</nav>
                
             
		     
                <div class="main-right-div">
                    <div class="row my-2">
                        <div class="col-sm-12">
                  <h1 class="course-heading" style="color:white;opacity:1;font-size:30px;">${course.courseName}</h1>
                   
                            <p class="sub-course-heading" style="color:white;opacity:1;font-size:25px;">${sectionname}</p>
                             
                             <div class="bg-white progress-box">
                                <h2 style="padding: 0px 20px 10px 0px;font-weight: 500;font-size: 22px;">Chapter Progress</h2>
                                <div class="row">
									<div class="col-sm-12 align-items-center">
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">
											</div>	
										</div>
										<p class="bar-status">
											Completed  <span class="percentage-color">${overall_Performance}%</span>
										</p>
									</div>
                                </div>                             
                            </div>
                        </div>
                    </div>
                 	<div class="container">
                 		<div class="row">
						<div class="col-sm-12 p-0 bg-white mt-4 rounded">						
							<ul class="nav nav-pills nav-justified" id="pills-tab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="pills-home-tab" data-toggle="pill" href="#session-video"
									role="tab" aria-controls="pills-session-video" aria-selected="true">Session Videos</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									id="pills-profile-tab" data-toggle="pill" href="#attempt1-test"
									role="tab" aria-controls="pills-attempt1-test" aria-selected="false">Attempt 1 Test</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									id="pills-contact-tab" data-toggle="pill" href="#attempt2-test"
									role="tab" aria-controls="pills-attempt2-test" aria-selected="false">Attempt 2 Test</a>
								</li>
							</ul>
						</div>		
						
						<div class="tab-content mt-4" id="pills-tabContent" style="width:100%;">
						<%-- 	<div class="tab-pane fade show active" id="session-video"
								role="tabpanel" aria-labelledby="session-video">
								<!-- <h1>Session Video</h1> -->
								<c:if test="${not empty sessionList}">  
								<c:forEach items="${sessionList}" var="session" varStatus="loop">
								<c:forEach items="${session.attempts}" var="attempts">
								<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop="" controls>
													<source src="resources/images/slide2.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">${session.videContent}</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
								          
												<c:if test="${session.videocompleted==1}">  
												   <form action="viewcoursecontent" method="post" id="videocompleted${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${session.videoContentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												  </form>
												     <a href="javascript:void(0)" onclick="document.getElementById('videocompleted${attempts.sessionId}').submit(); return false;">
													<a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${session.videoContentId}&sessionId=${attempts.sessionId}">
														<button target="_blank" class="themeBtn float-right">Watch
															Again</button>
													</a>
													</c:if>
													<c:if test="${session.videocompleted==0}">  
													      <form action="viewcoursecontent" method="post" id="videonotcompleted${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${session.videoContentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												  </form>
														<a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${session.videoContentId}&sessionId=${attempts.sessionId}">
														<a href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button target="_blank" class="themeBtn float-right">Start
															Learning</button>
													</a>
												
													</c:if>
												</div>
												<div>
											<c:forEach items="${testStatus}" var="testStatus">
								                      <ul class="nav">
														<li class="nav-item">
														
														 <c:if test="${testStatus.attemptId == attempts.id && testStatus.status eq true && attempts.sessionId == testStatus.sessionId}">
														 <form action="viewcoursecontent" method="post" id="reviewform1${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${attempts.contentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												           </form>
																<a href="javascript:void(0)" onclick="document.getElementById('reviewform1${attempts.sessionId}').submit(); return false;">
													 
															  <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
															${attempts.id}	Review Test 1	</a>
														</c:if>
														</li>
														<li class="nav-item">
														<c:if test="${testStatus.attemptId == attempts.id && testStatus.status eq true && attempts.sessionId == testStatus.sessionId}}">
													  <form action="viewcoursecontent" method="post" id="reviewform2${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${attempts.contentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												       </form>
												         <a href="javascript:void(0)" onclick="document.getElementById('reviewform2${attempts.sessionId}').submit(); return false;">
														dsd	Review Test 2</a>
															</c:if>
														</li>
														
													</ul>
												</c:forEach>	
												</div>
												
											</div>
										</div>
									</div>
								</div>
								</c:forEach>
								</c:forEach>
								</c:if>
							</div> --%>
							<div class="tab-pane fade show active" id="session-video"
								role="tabpanel" aria-labelledby="session-video">
								<!-- <h1>Session Video</h1> -->
									<c:if test="${not empty sessionList}">  
								<c:forEach items="${sessionList}" var="session" varStatus="i">
							<c:if test="${session.isLive==1}"> 
								<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-2">
												<video width="100%" height="auto" loop  autoplay  muted>
													<source src="${session.videoUrl}" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										
										<div class="col-sm-8 d-flex align-items-center p-2">
											<div class="media-body px-3">
												<h4 class="session-title">${session.sessionName}</h4>
													<c:forEach items="${session.attempts}" var="attempts" varStatus="j">
								
												<div style="width:90%;">
													
													
													<c:if test="${session.videocompleted==1 && attempts.id == 1 && session.isResume==0}">  
													 <i class="far fa-clock"> ${session.sessionVideoDuration}</i>
												   <form action="viewcoursecontent" method="post" id="videocompleted${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${session.videoContentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												  </form>
												    <c:if test="${session.isEnable == 0}">  
														<a disabled href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button disabled target="_blank" class="themeBtn float-right">Comming Soon
															</button>
													</a>
													</c:if>
													    <c:if test="${session.isEnable == 1}">  
												     <a href="javascript:void(0)" onclick="document.getElementById('videocompleted${attempts.sessionId}').submit(); return false;">
														<button target="_blank" class="themeBtn float-right">Watch
															Again</button>
													</a>
													 </c:if>
													</c:if>
													
													<c:if test="${session.videocompleted==0 && attempts.id == 1 && session.isResume==0}">  
													      <i class="far fa-clock"> ${session.sessionVideoDuration}</i>
													      <form action="viewcoursecontent" method="post" id="videonotcompleted${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${session.videoContentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
											       </form>
											        <c:if test="${session.isEnable == 0}">  
														<a disabled href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button disabled target="_blank" class="themeBtn float-right">Comming Soon
															</button>
													</a>
													</c:if>
													<c:if test="${session.isEnable == 1}">  
														<a  href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button target="_blank" class="themeBtn float-right">Start
															Learning</button>
													</a>
													</c:if>
												
													</c:if>
													<c:if test="${session.videocompleted==0 && attempts.id == 1 && session.isResume==1}">  
													      <i class="far fa-clock"> ${session.sessionVideoDuration}</i>
													      <form action="viewcoursecontent" method="post" id="videonotcompleted${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${session.videoContentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												           <input type="hidden" name="videoTime" value="${session.videoTime}"/>
												  </form>
												      
												      <c:if test="${session.isEnable == 0}">  
														<a disabled href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button disabled target="_blank" class="themeBtn float-right">Comming Soon
															</button>
												     	</a>
													 </c:if>
												       <c:if test="${session.isEnable == 1}">  
														<a href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button target="_blank" class="themeBtn float-right">Resume
															Learning</button>
													</a>
													 </c:if>
													</c:if>
													<c:if test="${session.videocompleted==1 && attempts.id == 1 && session.isResume==1}">  
													      <i class="far fa-clock">  ${session.sessionVideoDuration}</i>
													     <form action="viewcoursecontent" method="post" id="videonotcompleted${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${session.videoContentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												           <input type="hidden" name="videoTime" value="${session.videoTime}"/>
												         </form>
												      <c:if test="${session.isEnable == 0}">  
														<a disabled href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button disabled target="_blank" class="themeBtn float-right">Comming Soon
															</button>
												     	</a>
													 </c:if>
													      <c:if test="${session.isEnable == 1}">  
														<a href="javascript:void(0)" onclick="document.getElementById('videonotcompleted${attempts.sessionId}').submit(); return false;">
													    <button target="_blank" class="themeBtn float-right">Resume 
															Learning</button>
													     </a>
													      </c:if>
													</c:if>
													
													
												</div>
												
												<div>
										
							                        	<ul class="nav">
														<li class="nav-item">
														  <c:if test="${attempts.id == 1 && attempts.testStatus eq true}">
												         <form action="viewcoursecontent" method="post" id="reviewform1${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${attempts.contentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												           </form>
														  <a href="javascript:void(0)" onclick="document.getElementById('reviewform1${attempts.sessionId}').submit(); return false;">
													     	Review Test 1	</a>
													     </c:if>
														</li>
														<li class="nav-item">
														<c:if test="${attempts.id == 2 && attempts.testStatus eq true }">
													  <form action="viewcoursecontent" method="post" id="reviewform2${attempts.sessionId}">
													     <input type="hidden" name="courseId" value="${courseId}"/>
												         <input type="hidden" name="sectionId" value="${sectionId}"/>
												         <input type="hidden" name="attemptId" value="${attempts.id}"/>
												         <input type="hidden" name="contentId" value="${attempts.contentId}">
												         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
												       </form>
												         <a href="javascript:void(0)" onclick="document.getElementById('reviewform2${attempts.sessionId}').submit(); return false;">
														  Review Test 2</a>
															 </c:if>
														</li>
														
													</ul>
													  
													
												</div>
												</c:forEach>
												
											</div>
										</div>
										 
									
									</div>
									
								</div>
								  </c:if>
								  
								</c:forEach>
								</c:if>
								
									<!-- new     video -->
								 <%--  <c:if test="${courseId==5}">
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto"  loop  autoplay  muted>
													<source src="resources/images/GEO_ACT3.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Resources: Planning for our better future</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
													<!-- <ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" disabled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%"   height="auto" loop  autoplay muted>
													<source src="resources/images/GEO_ACT4.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Land: Where we live and we die</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
												<!-- 	<a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
													<!-- <ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" disabled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/GEO_ACT5.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Soil: Womb of our Nourishment</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
													<!-- <ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" disabled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabed>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/GEO_ACT6.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Soil: There is more to it</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
												<!-- 	<ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" diesbled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
								</c:if>		 --%>
								<%--  <c:if test="${courseId==3}">
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT5.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Aftermath of Treaty of Vienna</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
													<!-- <ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" disabled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT6.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Unification  And Establishment of Italian Nation</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
												<!-- 	<a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
													<!-- <ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" disabled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT7.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Rise Of Romantic Nationalism</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
													<!-- <ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" disabled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabed>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
									<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT8.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Art: a form of Nationalism</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
												<!-- 	<ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" diesbled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
								<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT9.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Iron Chancellor: Otto Von Bismarck</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
												<!-- 	<ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" diesbled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
								<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT10.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">Unification of German States</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
												<!-- 	<ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" diesbled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
								<div class="bg-white rounded">
									<div class="row mb-3">
										<div class="col-sm-4">
											<div class="media media-video p-1">
												<video width="100%" height="auto" loop  autoplay muted>
													<source src="resources/images/HIS_ACT11.mp4" type="video/mp4">
													Your browser does not support the video tag.
												</video>
											</div>
										</div>
										<div class="col-sm-8 d-flex align-items-center">
											<div class="media-body">
												<h4 class="session-title">The story of Great Britain</h4>
												<div style="width:90%;">
													<i class="far fa-clock"> 00:00</i>
													<!-- <a href="">
														<button target="_blank" class="themeBtn float-right" disabled>Start
															Learning</button>
													</a> -->
												</div>
												<div>
												<!-- 	<ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#" diesbled>Review Test 1</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#" disabled>Review Test 2</a>
														</li>
														
													</ul> -->
												</div>
												
											</div>
										</div>
									</div>
								</div>	
								</c:if>				 --%>
							
								 	
								 		
								
							</div>
							<div class="tab-pane fade" id="attempt1-test" role="tabpanel"
								aria-labelledby="attempt1-test-tab">

								<!-- <h1>Attempt1 Test</h1> -->
								<c:forEach items="${sessionList}" var="session" varStatus="loop">
								 <c:if test="${session.isLive==1 && session.isEnable == 1}"> 
									<c:forEach items="${session.attempts}" var="attempts">
										<c:if test="${attempts.id==1 }">
											<div class="bg-white rounded mb-3">
												<div class="row d-inline">
													<div class="col align-items-center">

														<div class="media-body">
															<h4 class="session-title">${attempts.quizTitle}</h4>

															<div style="width:100%;display: flex;align-items: baseline;">
														 		<!-- <i class="far fa-clock"> 00:00</i> -->
																	<%-- <c:forEach items="${testStatus}" var="testStatus"> --%>
																 <c:if test="${session.videocompleted == 1}">
															    	 <c:if test="${attempts.id == 1 && attempts.testStatus eq false}">
																	 
															            <form action="viewcoursecontent" method="post" id="starttestattempt1${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																         <fmt:parseNumber var = "totalmark" integerOnly = "true" type = "number" value = "${attempts.testMark}" />
																       	<h4 class="session-title font-weight-normal">${attempts.totalQuestion} Questions(${attempts.testMark} Marks)</h4>
													                    <h4 class="session-title font-weight-normal">Duration: 00:00</h4>	
																       <a href="javascript:void(0)" onclick="document.getElementById('starttestattempt1${attempts.sessionId}').submit(); return false;">
														      			<button target="_blank" class="themeBtn">Start Test</button>
																     </a> 
																   <%-- <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
																	 
																  </c:if>
																
																</c:if>
															<%-- 	</c:forEach> --%>
															</div>

   																 <c:if test="${session.videocompleted == 1}">
															     	
																	 <c:if test="${attempts.id == 1 && attempts.testStatus eq true}">
															           
															      <div>
																<ul class="nav">
																	<li class="nav-item">
																	 <form action="gettestsummary" method="post" id="testsummaryattempt1${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																<%-- 	<a class="nav-link active"
														             	href="gettestsummary?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
														             	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('testsummaryattempt1${ attempts.sessionId}').submit(); return false;">
														
														             	Test Summary</a></li>
																	
																	<li class="nav-item">
																	   <form action="viewcoursecontent" method="post" id="reviewattempttest1${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																	<%-- <a class="nav-link" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
																	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('reviewattempttest1${ attempts.sessionId}').submit(); return false;">
														             Review Test</a>
																	</li>

																</ul>
															</div>
															 </c:if>
															  
															  </c:if>

														</div>

													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
									</c:if>
								</c:forEach>
								
								<!-- is chapater test attempt-1 -->
								
								<c:forEach items="${section}" var="sectionlist">
									<c:if test="${sectionlist.isChapterTest==1 }">
					                 <c:forEach items="${sectionlist.session}" var="session" varStatus="loop">
					                    <c:if test="${session.isChapterTest == 1}">
					                         <c:forEach items="${session.attempts}" var="attempts">
					                          <c:if test="${attempts.id==1 }">
					                  <div class="bg-white rounded">
									    <div class="row d-inline mb-3">
									    	 <div class="col align-items-center">
											   <div class="media-body">
												<h4 class="session-title">${attempts.quizTitle}</h4>
												 <div style="width:100%;display: flex;align-items: baseline;">
															    	  <c:if test="${alltestStatus eq true}">
															    	    <c:if test="${attempts.id == 1 && attempts.testStatus eq false}">
																	    <form action="viewcoursecontent" method="post" id="chapterstartattempt1${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																       <fmt:parseNumber var = "totalmark" integerOnly = "true" type = "number" value = "${attempts.testMark}" />
																       	<h4 class="session-title font-weight-normal">${attempts.totalQuestion} Questions(${attempts.testMark} Marks)</h4>
													                    <h4 class="session-title font-weight-normal">Duration: 00:00</h4>	
																        <a href="javascript:void(0)" onclick="document.getElementById('chapterstartattempt1${attempts.sessionId}').submit(); return false;">
														      			<button target="_blank" class="themeBtn">Start Test</button>
																     </a> 
																   <%-- <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
																  </c:if>
														 		</c:if>
													 </div>	 		
														 <c:if test="${attempts.id == 1 && attempts.testStatus eq true}">		
											    	<div>
													<ul class="nav">
																	<li class="nav-item">
																	 <form action="gettestsummary" method="post" id="chaptersummaryattempt1${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																<%-- 	<a class="nav-link active"
														             	href="gettestsummary?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
														             	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('chaptersummaryattempt1${ attempts.sessionId}').submit(); return false;">
														
														             	Test Summary</a></li>
													       	<li class="nav-item">
																	   <form action="viewcoursecontent" method="post" id="chapterreviewattempttest1${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																	<%-- <a class="nav-link" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
																	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('chapterreviewattempttest1${ attempts.sessionId}').submit(); return false;">
														             Review Test</a>
																	</li>
														
													</ul>
												</div>
												</c:if>
												
											</div>
										</div>
									</div>
								</div>
								 </c:if>
								 </c:forEach>
								  </c:if>
                                     </c:forEach> 
									</c:if>
								</c:forEach>


							</div>
<%-- 							<div class="tab-pane fade" id="attempt2-test" role="tabpanel"
								aria-labelledby="attempt2-test-tab">
								<!-- <h1>Attempt1 Test</h1> -->
								<c:forEach items="${sectionDetails}" var="sections">
									<c:forEach items="${sections.attempts}" var="attempts">
										<c:if test="${attempts.id==2 }">
											<div class="bg-white rounded mb-3">
												<div class="row d-inline">
													<div class="col align-items-center">

														<div class="media-body">
															<h4 class="session-title">${attempts.quizTitle}</h4>

															<div style="width: 100%;">
																<!-- <i class="far fa-clock"> 00:00</i> -->
																 
																<c:forEach items="${testStatus}" var="testStatus"> 
																	 <c:if test="${testStatus.attemptId == 1 && testStatus.status eq true && attempts.sessionId == testStatus.sessionId}">
															
																<a
																	href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
																	<button target="_blank" class="themeBtn float-right">Start
																		Test</button>
																</a> </c:if></c:forEach>
															</div>


															<div>
																<ul class="nav">
																 	<c:forEach items="${testStatus}" var="testStatus"> 
																	 <c:if test="${testStatus.attemptId == 2 && testStatus.status eq true && attempts.sessionId == testStatus.sessionId}">
															
																	<li class="nav-item"><a class="nav-link active"
																		href="#">Test Summary</a></li>
																	
																		<li class="nav-item"><a class="nav-link" href="#">Review
																				Test</a></li>
																	
																	</c:if>
																	</c:forEach>
																</ul>
															</div>

														</div>

													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</c:forEach> --%>
								<!-- 								<div class="bg-white rounded">
									<div class="row d-inline mb-3">
										<div class="col align-items-center">
											<div class="media-body">
												<h4 class="session-title">Session 1: What are they and how are they classified?</h4>
												<div style="width:100%;">
													<i class="far fa-clock"> 00:00</i>
													<a href="">
														<button target="_blank" class="themeBtn float-right">Start Test</button>
													</a>
												</div>
												<div>
													<ul class="nav">
														<li class="nav-item">
															<a class="nav-link active" href="#">Test Summary</a>
														</li>
														<li class="nav-item">
															<a class="nav-link" href="#">Review Test</a>
														</li>
														
													</ul>
												</div>
												
											</div>
										</div>
									</div>
								</div>

							</div>-->
							<div class="tab-pane fade" id="attempt2-test" role="tabpanel"
								aria-labelledby="attempt2-test-tab">
								 <c:forEach items="${sessionList}" var="session" >
								    <c:if test="${session.isLive==1 && session.isEnable == 1}"> 
								 	<c:forEach items="${session.attempts}" var="attempts" varStatus="loop">
										<c:if test="${attempts.id==2 }">
								<div class="bg-white rounded mb-3">
									<div class="row d-inline mb-3">
										<div class="col align-items-center">
											<div class="media-body">
												<h4 class="session-title">${attempts.quizTitle}</h4>
												<div style="width:100%;display: flex;align-items: baseline;">
											
													<!-- <i class="far fa-clock"> 00:00</i> -->
														 <c:if test="${session.videocompleted == 1}">
															     <c:if test="${allAttempt1Status eq true}">
																	 <c:if test="${attempts.id == 2 && attempts.testStatus eq false}">
														
														  
														
																	     <form action="viewcoursecontent" method="post" id="starttestattempt2${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>	
																        <fmt:parseNumber var = "totalmark" integerOnly = "true" type = "number" value = "${attempts.testMark}" />
																       	<h4 class="session-title font-weight-normal">${attempts.totalQuestion} Questions(${attempts.testMark} Marks)</h4>
													                    <h4 class="session-title font-weight-normal">Duration: 00:00</h4>		       
													<a class="float-right" href="javascript:void(0)" onclick="document.getElementById('starttestattempt2${attempts.sessionId}').submit(); return false;">
													 		<button target="_blank" class="themeBtn">Start Test</button>
													</a>
												 	</c:if>
												   </c:if>
													
													</c:if>
												</div>
												 	 <c:if test="${session.videocompleted == 1}">
												     	 <c:if test="${attempts.id == 2 && attempts.testStatus eq true}">
													  <div>
													      <ul class="nav">
														  <li class="nav-item">
														  <form action="gettestsummary" method="post" id="testsummaryattempt2${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
															<a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('testsummaryattempt2${attempts.sessionId}').submit(); return false;">Test Summary</a>
														</li>
														<li class="nav-item">
														 <form action="viewcoursecontent" method="post" id="reviewattempttest2${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
															<a class="nav-link" href="javascript:void(0)" onclick="document.getElementById('reviewattempttest2${attempts.sessionId}').submit(); return false;">Review Test</a>
														</li>
														
													</ul>
												</div>
												</c:if>
												</c:if>
												
											</div>
										</div>
									</div>
								</div>
								</c:if>
								</c:forEach>
								 </c:if>
								</c:forEach>
								<!-- chapter test Attempt 2 -->
								<c:forEach items="${section}" var="sectionlist">
									<c:if test="${sectionlist.isChapterTest==1 }">
					                 <c:forEach items="${sectionlist.session}" var="session" varStatus="loop">
					                    <c:if test="${session.isChapterTest == 1}">
					                         <c:forEach items="${session.attempts}" var="attempts">
					                          <c:if test="${attempts.id==2 }">
					                  <div class="bg-white rounded mb-3">
									    <div class="row d-inline mb-3">
									    	 <div class="col align-items-center">
											   <div class="media-body">
												<h4 class="session-title">${attempts.quizTitle}</h4>
												 	<div style="width:100%;display: flex;align-items: baseline;">
															    	  <c:if test="${alltestStatus eq true}">
															    	 <c:if test="${attempts.id == 2 && attempts.testStatus eq false}">
																	    <form action="viewcoursecontent"
																	     method="post" id="chapterstartattempt2${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																         <fmt:parseNumber var = "totalmark" integerOnly = "true" type = "number" value = "${attempts.testMark}" />
																       	<h4 class="session-title font-weight-normal">${attempts.totalQuestion} Questions(${attempts.testMark} Marks)</h4>
													                    <h4 class="session-title font-weight-normal">Duration: 00:00</h4>
																        <a href="javascript:void(0)" onclick="document.getElementById('chapterstartattempt2${attempts.sessionId}').submit(); return false;">
														      			<button target="_blank" class="themeBtn">Start Test</button>
																     </a> 
																   <%-- <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
																  </c:if>
														 		</c:if>
														  </div>		
														 <c:if test="${attempts.id == 2 && attempts.testStatus eq true}">		
											    	<div>
													<ul class="nav">
																	<li class="nav-item">
																	 <form action="gettestsummary" method="post" id="chaptersummaryattempt2${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																<%-- 	<a class="nav-link active"
														             	href="gettestsummary?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
														             	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('chaptersummaryattempt2${ attempts.sessionId}').submit(); return false;">
														
														             	Test Summary</a></li>
													       	<li class="nav-item">
																	   <form action="viewcoursecontent" method="post" id="chapterreviewattempttest2${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																	<%-- <a class="nav-link" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}"> --%>
																	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('chapterreviewattempttest2${ attempts.sessionId}').submit(); return false;">
														             Review Test</a>
																	</li>
														
													</ul>
												</div>
												</c:if>
												
											</div>
										</div>
									</div>
								</div>
								 </c:if>
								 </c:forEach>
								  </c:if>
                                     </c:forEach> 
									</c:if>
								</c:forEach>
								
								
								
								</div> 
								
							</div>
						</div>
                 	</div>
                  </div>
               </main>
             </div>
           </div>
                    
<script>
	$('.progress-bar').each(function() {
		var valueNow = $(this).attr('aria-valuenow');
		$(this).animate({
			width : valueNow + '%',
			percent : 100
		}, {
			progress : function(a, p, n) {
				$(this).css('width', ('${overall_Performance}' * p + '%'));
				// $(this).css('width', (valueNow p + '%')).html(Math.round(valueNow p) + '%');
			}
		});
	});
</script>	
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</body>
</html>









