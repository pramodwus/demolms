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
<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script> -->
  
<link rel="stylesheet" href="<spring:url value='resources/css/newcommon.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='resources/css/page4.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/assesment.css?v=1'/>">
    <link rel="icon" href="resources/images/allimages/favicon.ico" type="image/x-icon" />
 <link rel="stylesheet"
	href="<spring:url value='/resources/css/newcommon.css'/>">
	<link rel="stylesheet"
	href="<spring:url value='/resources/css/assesment_review.css'/>">
        
<title>eLuminate</title>
<style>
    
    /* Small devices (landscape phones, 576px and up) */
@media (max-width: 575.98px) {
	#sidebar{
	display:none;
	}	
	.breadcrumb{
	padding:1rem 1rem;
	}
	.float-right{
	float:none !important;
	}
	.float-left{
	float:none !important;
	}
	.text-sm-center{
	text-align:center !important;
	margin-bottom:1rem;
	margin-top:1rem;
	
	}
}

/* Medium devices (tablets, 768px and up) */
@media (min-width: 768px) { 
/* 	#sidebar{
	display:inline-block;
	} */
}

/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) {
	/* #sidebar{
	display:inline-block;
	}  */
}

/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {
/* 	#sidebar{
	display:inline-block;
	} */
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
                        <a class="back-to-chapters" href="coursechapter?courseId=${courseId}&sectionId=${sectionId}">
                            <i class="fa fa-arrow-left"></i>
                            <span>List of Chapters</span>
                        </a>
                    </div>
                </div>
            </div>
					
					<!-- -----------Sidebar Code end Here------------- -->
					
					
					
					<main class="col pl-2 pt-2 content-wrapper">
						<nav aria-label="breadcrumb" id="navBar">
							<ol class="breadcrumb">
							<li class="breadcrumb-item">
								<form action="newdashboard" method="post" id="newdashboardback">
																	     <input type="hidden" name="courseId" value="${courseId}"/>
																        <c:if test="${parentId != null }">
                                                                          <input type="hidden" name="sectionId" value="${parentId}"/>
                                                                          </c:if>
                                                                          <c:if test="${parentId == null }">
                                                                          <input type="hidden" name="sectionId" value="${sectionId}"/>
                                                                           </c:if>
																         <input type="hidden" name="id" value="${activeSessionId}"/>
																       </form>										
								<a  href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit();  return false;" style="text-decoration:none;color:#fff;margin-bottom:20px;opacity:.9;font-weight:700;line-height:0px;font-size: 20px;"><span class="pr-2"><i class="fas fa-arrow-left"></i></span>Back</a>
							</li>
							<%-- <li class="breadcrumb-item"><a href="coursechapter?courseId=${courseId}&sectionId=${sectionId}">${course.courseName}</a></li>
							<li class="breadcrumb-item active"><a href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=${activeSessionId}">${section.sectionName}</a></li>
							<li class="breadcrumb-item active"><a href="newdashboard?courseId=${courseId}&sectionId=${sectionId}&id=${activeSessionId}">${section.sectionName}</a></li>
							
							 <li class="breadcrumb-item active"><a href="javascript:void(0);">${sessionName}</a></li> --%>
							<!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
							</ol>
						</nav>
						<div id="course-content-view-div" class="col-md-12 col-sm-12 col-xs-12">
							 
							 <!-- <main class="col pl-2 pt-2 content-wrapp"> -->
   <!-- <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
            <li class="breadcrumb-item"><a href="#">Profile</a></li>
        </ol>
    </nav>-->
       <%--  <div class="main-right-div">
            <h1 class="course-heading">Assessment Summary</h1>
            <div class="row py-3 bg-white rounded">
                    <h5 class="question-heading">Questions Asked</h5>                               
                <div class="col-sm-12 d-inline-flex"> 
                                           
                    <div class="col-6 d-inline-flex">                               
                        
                            <div class="col">
                                <h6 class="text-primary">Attempt</h6>
                                <h6 class="text-success">Correct</h6>
                                <h6 class="text-danger">Wrong</h6>
                                <h6 class="text-danger">UnAttempt</h6>
                            
                            </div>
                            <div class="col text-right">
                                <h6 class="text-primary">1${testsummary.correctQueAttempt+testsummary.wrongQueAttempt}</h6>
                                <h6 class="text-success">${testsummary.correctQueAttempt}</h6>
                                <h6 class="text-danger">${testsummary.wrongQueAttempt}</h6>
                                     <h6 class="text-danger">${testsummary.unAttemptQue}</h6>
                           
                            </div>                                                                                                                              
                    </div>
                    <div class="col-6 d-inline-flex border-left">                               
                        
                        <div class="col">
                          <!--  <span class="d-inline-flex">
                                <i class="far fa-clock"> Time Taken</i>
                                <h6 class="text-success pl-5">00:00:00 / 05:00:45</h6>
                            </span> -->
                            <span class="d-inline-flex">
                                <i class="far fa-clipboard"> Marks Obtained</i>
                                <h6 class="text-danger pl-4">${testsummary.obtainMarks}/${(testsummary.correctQueAttempt+testsummary.wrongQueAttempt+testsummary.unAttemptQue)*5} </h6>
                            </span>
                           
                        </div>
                                                                                                          
                </div>
                </div>
            </div>
            
           
        </div>  --%>
        
        
        
        <main class="col pl-2 pt-2">
		
        <div class="main-right-div">
			
            <h1 class="course-heading">Test Summary</h1>
            <div class="row py-3 bg-white rounded">
                    <h5 class="question-heading font-weight-bold">Questions Asked</h5>                               
                <div class="col-sm-12 d-inline-flex"> 
                                           
                    <div class="col-6 d-inline-flex">                               
                        
                            <div class="col">
                                 <h6 class="font-weight-bold">Total Questions</h6>
                                <h6 class="text-primary font-weight-bold">Attempt</h6>
                                <h6 class="text-success font-weight-bold">Correct</h6>
                                <h6 class="text-danger font-weight-bold">Wrong</h6>
                                 <h6 class="text-danger font-weight-bold">Unattempted Questions</h6>
                        
                            </div>
                            <div class="col text-right">
                                    <h6 class="font-weight-bold">${testsummary.correctQueAttempt+testsummary.wrongQueAttempt+testsummary.unAttemptQue}</h6>
                                <h6 class="text-primary font-weight-bold">${testsummary.correctQueAttempt+testsummary.wrongQueAttempt}</h6>
                                <h6 class="text-success font-weight-bold">${testsummary.correctQueAttempt}</h6>
                                <h6 class="text-danger font-weight-bold">${testsummary.wrongQueAttempt}</h6>
                                <h6 class="text-danger font-weight-bold">${testsummary.unAttemptQue}</h6>
                                
                              
                            </div>                                                                                                                              
                    </div>
                    <div class="col-6 d-inline-flex border-left">                               
                        
                        <div class="col">
                          <!--  <span class="d-inline-flex">
                                <i class="far fa-clock"> Time Taken</i>
                                <h6 class="text-success pl-5">00:00:00 / 05:00:45</h6>
                            </span> -->
                            <span class="d-inline-flex">
                             <!-- <i class="far fa-clipboard"> Marks Obtained</i> -->
                                 <h6 class="font-weight-bold">Marks Obtained</h6>
                                <h6 class="text-danger pl-4 font-weight-bold">${testsummary.obtainMarks}/${(testsummary.correctQueAttempt+testsummary.wrongQueAttempt+testsummary.unAttemptQue)*testsummary.everyquestionmark} </h6>
                            </span>
                           
                        </div>
                                                                                                          
                </div>
                </div>
            </div>
            <!-- <div class="row py-4">
                <div class="col text-left">
                    <div class="button-main-div">
                    		<div class="width-class button-css button-color button-shadow">  <a href="javascript:void(0);" onClick="reviewClick()" style="font-size:12px;font-weight:normal;text-decoration: none;" >   Review</a></div>
                        
						    
                  </div>
                </div> 
                <div class="col text-right">
                    <div class="button-main-div">
                       
						 <a href="javascript:void(0);" onClick=redirectOnNextContent(${'$'}{sessionId},${'$'}{contentId})> <div class="width-class button-css button-color button-shadow">Next Assesment</div></a>
                    </div>
                </div>
            </div> -->
           
        </div> 
       
                 
    
    
       
         <!--         
    </div>
 </main>  -->
    
	
			</div>
	</div>
 </body>

</html>
<script>
function goBack() {
	
	  window.history.go(-1);
	}
</script>


