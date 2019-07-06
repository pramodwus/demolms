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
	.new-display{
		display:block !important;
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
                            <span>List of Chapters</span>
                        </a>
                    </div>
                </div>
            </div>
            <!-- -----------Sidebar Code end Here------------- -->
            
            <main class="col pl-2 pt-2 content-wrapper" style="min-height: 101vh;">
        
                   	<nav aria-label="breadcrumb" id="navBar">
							<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
							<form action="coursechapter" method="post" id="coursechapterback">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																       </form>
																       &nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item "><a href="javascript:void(0)" onclick="document.getElementById('coursechapterback').submit(); return false;" style="font-weight:bold;"><%-- <a href="coursechapter?courseId=${courseId}&sectionId=${sectionId}"> --%>${course.courseName}</a></li>
                            							<form action="newdashboard" method="post" id="newdashboardback">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																	     <c:if test="${parentId != null }">
                                                                          <input type="hidden" name="sectionId" value="${parentId}"/>
                                                                          </c:if>
                                                                          <c:if test="${parentId == null }">
                                                                          <input type="hidden" name="sectionId" value="${sectionId}"/>
                                                                           </c:if>
																         <%-- <input type="hidden" name="sectionId" value="${sectionId}"/>
																          --%><input type="hidden" name="id" value="${activeSessionId}"/>
																         
																         
																       </form>
																&nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;">${sectionname}</a></li>
						    	<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;text-decoration: underline;">Practice Test</a></li>
						
							</ol>
						</nav>
         
                <div class="main-right-div">
                    <div class="row my-2">
                        <div class="col-sm-12">
                            <h1 class="course-heading" style="color:white;opacity:1;font-size:25px;">${course.courseName}</h1>
                            <p class="sub-course-heading" style="color:white;opacity:1;font-size:25px;">${sectionname}</p>
                            <%--  <div class="bg-white progress-box">
                                <h2 style="padding: 0px 20px 10px 0px;font-weight: 500;font-size: 22px;">Chapter Progress</h2>
                                <div class="row">
									<div class="col-sm-8 align-items-center">
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
                            </div> --%>
                        </div>
                    </div>
                 	<div class="container">
                 		<div class="row">
						<div class="col-sm-12 p-0 bg-white mt-4 rounded">						
							<ul class="nav nav-pills nav-justified" id="pills-tab" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									id="pills-home-tab" data-toggle="pill" href="#session-video"
									role="tab" aria-controls="pills-session-video" aria-selected="true">Practice Test</a>
								</li>
							</ul>
						</div>		
						
						<div class="tab-content mt-4" id="pills-tabContent" style="width:100%;">
						
							<div class="tab-pane fade show active" id="session-video"
								role="tabpanel" aria-labelledby="session-video">
								<!-- <h1>Session Video</h1> -->
								<%-- <c:forEach items="${sessionList}" var="session" varStatus="loop">
								
									<c:forEach items="${session.attempts}" var="attempts">
										<c:if test="${attempts.id==1 }">
											<div class="bg-white rounded mb-3">
												<div class="row d-inline">
													<div class="col align-items-center">

														<div class="media-body">
															<h4 class="session-title">${attempts.quizTitle}</h4>

															<div style="width: 100%;text-align:right;">
																<!-- <i class="far fa-clock"> 00:00</i> -->
																	<c:forEach items="${testStatus}" var="testStatus">
																 <c:if test="${session.videocompleted == 1}">
															    	 <c:if test="${attempts.id == 1 && attempts.testStatus eq false}">
																	 
															            <form action="viewcoursecontent" method="post" id="starttestattempt1${attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																       <a href="javascript:void(0)" onclick="document.getElementById('starttestattempt1${attempts.sessionId}').submit(); return false;">
														      			<button target="_blank" class="themeBtn">Start Test</button>
																     </a> 
																   <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
																	 
																  </c:if>
																
																</c:if>
																</c:forEach>
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
																	<a class="nav-link active"
														             	href="gettestsummary?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
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
																	<a class="nav-link" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
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
								</c:forEach>
 --%>								
								<!-- is chapater test attempt-1 -->
								
								<c:forEach items="${section}" var="sectionlist">
									<c:if test="${sectionlist.isChapterTest==0 &&  sectionlist.isPractice==1}">
					                         <c:forEach items="${sectionlist.attempts}" var="attempts">
					                   <div class="bg-white rounded mb-3">
									    <div class="row d-inline mb-3">
									    	 <div class="col align-items-center">
											   <div class="media-body">
												<h4 class="session-title">${attempts.quizTitle}</h4>
												    <div style="width:100%;display: flex;align-items: baseline;" class="new-display">
															    	  
															    	    <c:if test="${attempts.testStatus eq false}">
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
														</div>		  
														 		
														 <c:if test="${attempts.testStatus eq true}">		
											    	<div>
													<%-- <ul class="nav">
																	<li class="nav-item">
																	 <form action="gettestsummary" method="post" id="chaptersummaryattempt1${ attempts.sessionId}">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																         <input type="hidden" name="sectionId" value="${sectionlist.sectionId}"/>
																         <input type="hidden" name="attemptId" value="${attempts.id}"/>
																         <input type="hidden" name="contentId" value="${attempts.contentId}">
																         <input type="hidden" name="sessionId" value="${attempts.sessionId}"/>
																       </form>
																	<a class="nav-link active"
														             	href="gettestsummary?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
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
																	<a class="nav-link" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempts.id}&contentId=${attempts.contentId}&sessionId=${attempts.sessionId}">
																	 <a class="nav-link active" href="javascript:void(0)" onclick="document.getElementById('chapterreviewattempttest1${ attempts.sessionId}').submit(); return false;">
														             Review Test</a>
																	</li>
														
													</ul> --%>
												</div>
												</c:if>
												
											</div>
										</div>
									</div>
								</div>
								 
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
</body>
</html>





