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
<script src="https://cdn.jsdelivr.net/npm/hls.js@latest"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script> -->
  
<link rel="stylesheet" href="<spring:url value='resources/css/newcommon.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='resources/css/page4.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/assesment.css?v=1'/>">
    <link rel="icon" href="resources/images/allimages/favicon.ico" type="image/x-icon" />
 <link rel="stylesheet"
	href="<spring:url value='/resources/css/newcommon.css'/>">
	<link rel="stylesheet"
	href="<spring:url value='/resources/css/assesment_review.css'/>">
	 <script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
    
    
 
          
<title>eLuminate</title>


<style>
video:focus{
	outline:0px;
}
.preBtn {
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
.preBtn:hover {
background: #68c721;
color: #ffffff;
-webkit-transform: scale(1.02);
-ms-transform: scale(1.02);
transform: scale(1.02);
box-shadow: 0 2px 8px 0 rgba(0,0,0,.2);
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
.content {
min-height: 250px;
padding: 15px;
margin-right: auto;
margin-left: auto;
padding-left: 15px;
padding-right: 15px;
}
.page-background-color {
background-color: #ebeef1 !important;
}
.test-details {
background-color: #fff !important;
margin: 0px !important;
padding-top: 15px !important;
}
.dl-horizontal dt {
float: left;
width: 25px;
overflow: hidden;
clear: left;
text-overflow: ellipsis;
white-space: nowrap; 
}
.dl-horizontal dd {
margin-bottom: 10px;
}
.form-group {
margin-bottom: 10px;
}
.themeBtn {
    width: 40%;
margin-left: 30%;
margin-right: 30%
}

h6 {
  color: #edefe8	;
}
.maximum{
max-height: 29px;
}
@media (max-width: 575.98px) {
	.maximum {
	max-height: 50px;
	display: block !important;
	text-align: center;
	}	
	.vedio{
		height:200px !important; 
	}
}
</style>



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
      <style>
  ._1TUMc {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    padding: 9px 0;
    margin: 15px auto 20px;
    font-size: 14px;
    color: rgba(20,32,49,.3);
    border-top: 1px solid rgba(20,32,49,.1);
    border-bottom: 1px solid rgba(20,32,49,.1);
}
._1TUMc ._1s8Rr {
    -webkit-box-flex: 1;
    -ms-flex: 1;
    flex: 1;
    text-align: left;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
}
._1TUMc .fdX0F ._2ItfS {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    outline: transparent;
}
._1TUMc .fdX0F ._2ItfS ._3yfJm {
    font-weight: 700;
    margin-right: 8px;
    color: rgba(20,32,49,.4);
    font-size: 12px;
    letter-spacing: 1px;
    text-transform: uppercase;
}
._1TUMc .fdX0F ._2ItfS ._1inE5 {
    margin-right: 10px;
}
._3iwce {
    margin-bottom: 16px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
}
._2oeEO.g4jOE {
    opacity: 1;
}
._2oeEO {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
    cursor: pointer;
    opacity: .4;
}

._2oeEO ._3ctp0 {
    width: 60px;
    height: 60px;
    margin-bottom: 6px;
    border-radius: 50%;
    background: #fff;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,.1);
}
.flex_alignLeft, .flex_centered{
  display: flex;
  justify-content: center;
  align-items: center;
}
._2oeEO.g4jOE .gqpVv {
    font-weight: 700;
    color: #000;
    font-size: 14px;
    line-height: 19px;
}
.aau7W+.aau7W {
    margin-left: 20px;
}
._2tcMl{
  font-size: 16px;
    font-weight: 600;
    color: #142031;
    margin-bottom: 16px;
    text-align: center;
}
.zR4n5{
  margin-top: 40px;
}
._2DOhr ._19d-S .H0Z93 {
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    max-width: 480px;
    margin: 0 auto 40px;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    justify-content: center;
}
._3SW7W {
    padding: 5px 11px 6px;
    font-size: 14px;
    color: rgba(20,32,49,.5);
    border: 1px solid rgba(20,32,49,.15);
    border-radius: 50px;
    margin-right: 5px;
    margin-bottom: 10px;
    cursor: pointer;
    display: inline-block;
}
li {
    list-style-type: none;
}
.mb-10, .mv-10 {
    margin-bottom: 10px;
}
._2U6rr {
    /* height: auto; */
    line-height: 100%;
    height: 80px;
    /* padding: 10px; */
    border: 1px solid #edeeef;
    border-radius: 3px;
    padding: 0 10px;
    line-height: 40px;
    color: #000;
    resize: none;
}
._2DOhr ._19d-S .kpatr {
    background-color: #fff
}
.btn-circle {
  width: 30px;
  height: 30px;
  text-align: center;
  padding: 6px 0;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 15px;
}

.btn-circle.btn-xl {
  width: 70px;
  height: 70px;
  padding: 10px 16px;
  font-size: 24px;
  line-height: 1.33;
  border-radius: 35px;
  margin: 1rem 1rem 4rem;
}

  </style>
</head>
<body>

		     									 <c:if test="${lisence.userSessionStatus==0 && lisence.isFree==0}">
	                         <c:redirect url="https://qa.eluminate.in/#/all-courses"></c:redirect>	     									 
	<%-- 						 <%
        String redirectURL = "https://dev.eluminate.in/#/all-courses";
           response.sendRedirect(redirectURL);
%> --%>
</c:if>
		
			

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
							<!-- <li class="nav-item">
							     <a href="#" class="nav-link px-4">
							        <span><i class="fas fa-mobile-alt px-3"></i>Download App</span>
							     </a>
							</li> -->
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
						<form method="post" action="newdashboard" id="chapterback">
							<input type="hidden" name="courseId" value="${courseId}" /> 
							<c:if test="${parentId != null }">
						   <input type="hidden" name="sectionId" value="${parentId}" />
							</c:if>
							<c:if test="${parentId == null }">
								<input type="hidden" name="sectionId" value="${sectionId}" />
							</c:if>
							<input type="hidden" name="id" value="${activeSessionId}" />
						</form>
						<a class="back-to-chapters" href="javascript:void(0)" onclick="document.getElementById('chapterback').submit(); return false;">
                        
							<%-- 	<a class="back-to-chapters" href="newdashboard?courseId=${courseId}&sectionId=${sectionId}&id=${activeSessionId}"> --%>
									<i class="fa fa-arrow-left"></i>
									<span>List of Chapters </span>
								</a>
							</div>
							<div class="chapter-description-box px-3 pt-3 pb-3">
								<h6>${course.courseName}</h6>
								<small>${section.sectionName}</small>
							</div>
							<div class="chapter-content-box">
								<small class="_heading">Chapter Content</small>
							  <c:set var="j" value="0"/>	
								<c:if test="${not empty section.attemptList}">
								 <c:forEach items="${section.attemptList}" var="attempt">
									        <c:set var="cardOrder" value="1"/>
									        <c:forEach items="${attempt.sessionList}" var="coursesession" >
									      <%--        <p id="required-user-data${coursesession.id}" data-sessionactiveid=${activeSessionId } data-activeid="${coursesession.id}" data-courseid="${courseId}" data-sectionid="${sectionId}"  data-attemptid="${attempt.attemptId}" style="display:none"></p>
									       --%>       <p id="required-user-data${coursesession.id}" data-sessionactiveid=${activeSessionId } data-activeid="${coursesession.id}" data-courseid="${courseId}" data-sectionid="${sectionId}"   style="display:none"></p>
									       
									       				<%-- <p id="required-user-data2${coursesession.id}" data-sessionactiveid2="${activeSessionId }" data-activeid2="${coursesession.id}" data-courseid2="${courseId}" data-sectionid2="${sectionId}"  data-attemptid2="2" style="display:none"></p> --%>
									       <c:if test="${coursesession.id eq activeSessionId}">
									             <a  id="act-div-${coursesession.id}"   class="act-div <c:if test="${coursesession.id eq activeSessionId}">active</c:if>"  data-toggle="collapse" href="#collapseExample${coursesession.id}" role="button"  aria-expanded="false" aria-controls="collapseExample" >
													<i class="fas fa-book-open icon-active"></i>
													<span class="act-name">${coursesession.name}</span>
												    <i class="fas fa-angle-right direction-arrow"></i>
												 </a>
									        </c:if>
									            <%--  <a id="act-div-${coursesession.id}" class="act-div <c:if test="${coursesession.id eq activeSessionId}">active</c:if>"  data-toggle="collapse" href="#collapseExample${coursesession.id}" role="button"  aria-expanded="false" aria-controls="collapseExample" >
													<i class="fas fa-book-open icon-active"></i>
													<span class="act-name">${coursesession.name}</span>
												    <i class="fas fa-angle-right direction-arrow"></i>
												 </a>  
												  
												 --%>  
											<c:if test="${coursesession.id ne activeSessionId}">	   
								     	     <c:if test="${coursesession.videoStatus==1}">
								     	         <a id="act-div-${coursesession.id}" class="act-div <c:if test="${coursesession.id eq activeSessionId}">active</c:if>"  data-toggle="collapse" href="#collapseExample${coursesession.id}" role="button"  aria-expanded="false" aria-controls="collapseExample" >
													<i class="fas fa-book-open icon-active"></i>
													<span class="act-name">${coursesession.name}</span>
												    <i class="fas fa-angle-right direction-arrow"></i>
												 </a>
								     	     
								     	    </c:if>
								     	    <c:if test="${coursesession.videoStatus==0}">
								     	     <a id="act-div-${coursesession.id}" class="act-div <c:if test="${coursesession.id eq activeSessionId}">active</c:if>" style="pointer-events:none" data-toggle="collapse" href="#collapseExample${coursesession.id}" role="button"  aria-expanded="false" aria-controls="collapseExample" >
													<i class="fas fa-book-open icon-active"></i>
													<span class="act-name">${coursesession.name}</span>
												    <i class="fas fa-angle-right direction-arrow"></i>
												 </a>
								     	   
								     	    </c:if>
								     	    <%--  <c:if test="${ empty isStatusVideo[j]}">
								     	     <a id="act-div-${coursesession.id}" class="act-div <c:if test="${coursesession.id eq activeSessionId}">active</c:if>" style="pointer-events:none" data-toggle="collapse" href="#collapseExample${coursesession.id}" role="button"  aria-expanded="false" aria-controls="collapseExample" >
													<i class="fas fa-book-open icon-active"></i>
													<span class="act-name">${coursesession.name}</span>
												    <i class="fas fa-angle-right direction-arrow"></i>
												 </a>
								     	   
								     	    </c:if> --%>
											 </c:if>	 
												 
												 <div class="collapse course-session-menu" id="collapseExample${coursesession.id}" data-sessionid="${coursesession.id}" >
							                            <div class="card-body py-2" style="padding:0px 0px;">
														 <%--                                <c:forEach items="${coursesession.contentList}" var="content">
							                                <div class=bg-white>
							                             
							                                       <c:if test="${content.contentType eq 'TEST'}">
							                                             <div class="d-block py-1"><i class="fas fa-clipboard icon-active pl-5"></i>
								                                             <span class="act-name">
								                                               <a id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-dark load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                       </c:if>
							                                       <c:if test="${content.contentType eq 'VIDEO'}">
							                                             <div class="d-block py-1" ><i class="fas fa-play-circle icon-active pl-5" ></i>
							                                                  <span class="act-name">
							                                                     <a id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-dark load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                       </c:if>
							                                       </div>
							                                </c:forEach> --%>
							                                
							                                
							                           <%--     <c:forEach items="${attempt.sessionList}" var="coursesession" > --%>
							                           <c:forEach items="${coursesession.contentList}" var="content" varStatus="loop">
							                                <div >
							                             			<c:if test="${content.contentType eq 'TEST'}">
							                             			 <!-- print Attempt 1 Test -->
							                             			 <c:if test="${content.attemptId == 1}">
							                                            <c:if test="${coursesession.videoStatus==1}">
							                                             <c:if test="${coursesession.isfree == 1 && coursesession.userSessionStatus==0}">
							                                             <div class="d-flex py-1 text-white">
							                                             <div>
							                                             <i class="fas fa-clipboard px-3 text-white"></i>
							                                             </div>
								                                             <div>
								                                             <span class="act-name text-white ml-0">
								                                              <a id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                            
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                             
							                                             <c:if test="${coursesession.videoStatus==1}">
							                                             <c:if test="${coursesession.isfree == 0 && coursesession.userSessionStatus == 0}">
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             
																			<span class="act-name text-white ml-0">
								                                              <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                             
							                                             <c:if test="${coursesession.videoStatus==1}">
							                                             <c:if test="${coursesession.userSessionStatus == 1   }">
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             	<span class="act-name text-white ml-0">
								                                             	<input type="hidden" id="dataorderreturn${coursesession.id}${content.contentId}" value="${cardOrder}"> 
								                                               <a  id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}"  data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                             
							                                             
							                                             <c:if test="${coursesession.videoStatus==0}">
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             <span class="act-name text-white ml-0">
								                                               <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                        </c:if>   
							                                        <!--  print Attempt 2 Test -->
							                                         <c:if test="${content.attemptId == 2}">
							                                            <c:if test="${coursesession.videoStatus==1}">
							                                             <c:if test="${coursesession.isfree == 1 && coursesession.userSessionStatus==0}">
							                                              <c:if test="${attempt.allAttempt1Status eq true}">						                                             <div class="d-flex py-1 text-white">
							                                             <div>
							                                             <i class="fas fa-clipboard px-3 text-white"></i>
							                                             </div>
								                                             <div>
								                                             <span class="act-name text-white ml-0">
								                                              <a id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                            
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             <c:if test="${attempt.allAttempt1Status ne true}">						                                             <div class="d-flex py-1 text-white">
							                                             <div>
							                                             <i class="fas fa-clipboard px-3 text-white"></i>
							                                             </div>
								                                             <div>
								                                             <span class="act-name text-white ml-0">
								                                              <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                            
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                             </c:if>
							                                             
							                                             <c:if test="${coursesession.videoStatus==1}">
							                                             <c:if test="${coursesession.isfree == 0 && coursesession.userSessionStatus == 0}">
							                                               <c:if test="${attempt.allAttempt1Status eq true}">	
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             
																			<span class="act-name text-white ml-0">
								                                              <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                              <c:if test="${attempt.allAttempt1Status ne true}">	
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             
																			<span class="act-name text-white ml-0">
								                                              <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                             </c:if>
							                                             
							                                             <c:if test="${coursesession.videoStatus==1}">
							                                             <c:if test="${coursesession.userSessionStatus == 1   }">
							                                               <c:if test="${attempt.allAttempt1Status eq true}">	
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             	<span class="act-name text-white ml-0">
								                                             	<input type="hidden" id="dataorderreturn${coursesession.id}${content.contentId}" value="${cardOrder}"> 
								                                               <a  id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}"  data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             <c:if test="${attempt.allAttempt1Status ne true}">	
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             	<span class="act-name text-white ml-0">
								                                             	<input type="hidden" id="dataorderreturn${coursesession.id}${content.contentId}" value="${cardOrder}"> 
								                                               <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}"  data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                             </c:if>
							                                             
							                                             
							                                             <c:if test="${coursesession.videoStatus==0}">
							                                                <c:if test="${attempt.allAttempt1Status eq true}">	
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             <span class="act-name text-white ml-0">
								                                               <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             
							                                              <c:if test="${attempt.allAttempt1Status ne true}">	
							                                             <div class="d-flex py-1 text-white">
							                                             <div><i class="fas fa-clipboard px-3 text-white"></i></div>
								                                             <div>
								                                             <span class="act-name text-white ml-0">
								                                               <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
								                                             </span>
								                                             </div>
							                                             </div>
							                                             <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                             </c:if>
							                                        </c:if>   
							                                        
							                                       </c:if>
							                                       <c:if test="${content.contentType eq 'VIDEO'}">
 
                                                                      <c:if test="${coursesession.isfree == 1 && coursesession.userSessionStatus==0}">
                                                                       
											 			                  <div class="d-flex py-1 text-white">
											 			                  <div><i class="fas fa-play-circle icon-active px-3 text-white"></i></div>
											 			                  <div>
											 			                  	<span class="act-name text-white ml-0">
							                 
							                                                   <a id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
											 			                  </div>
							                                                  
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                              
							                                            <c:if test="${coursesession.userSessionStatus == 1 }">
                                                                        <div class="d-flex py-1 text-white">
                                                                        <div><i class="fas fa-play-circle icon-active px-3 text-white"></i></div>
							                                                  <div>
							                                                  	<span class="act-name text-white ml-0">
							                 
							                                                   <a id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                                  </div>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                              
							                                              
							                                              <c:if test="${coursesession.isfree == 0 && coursesession.userSessionStatus==0}">
											 			                  <div class="d-flex py-1 text-white">
											 			                 <div><i class="fas fa-play-circle icon-active px-3 text-white"></i></div>
							                                                  <div>
							                                                  	<span class="act-name text-white ml-0">
							                 
							                                                   <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-attemptid="${content.attemptId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                                  </div>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                             </c:if>
							                                              
							                 
							                                              
							                                           
							                                          <%--  <c:if test="${lisence.userSessionStatus == 0 && lisence.sessionId == coursesession.id && lisence.isFree==0} ">
							                                           			                  <div class="d-block py-1 text-white"><i class="fas fa-play-circle icon-active pl-2 text-white" ></i>
							                                                  <span class="act-name text-white">
							                 
							                                                   <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                           
							                                           </c:if>
							                                                      <c:if test="${lisence.userSessionStatus == 1 && lisence.sessionId == coursesession.id }">
							                                                        
							                                           			      <div class="d-block py-1 text-white"><i class="fas fa-play-circle icon-active pl-2 text-white" ></i>
							                                                  <span class="act-name text-white">
							                 
							                                                   <a style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                           
							                                           </c:if>
							                                                   
							                                                    <c:if test="${lisence.userSessionStatus == 0 && lisence.isFree==1 && lisence.sessionId == coursesession.id }">
							                                                   <div class="d-block py-1 text-white"><i class="fas fa-play-circle icon-active pl-2 text-white" ></i>
							                                                    <span class="act-name text-white">
							                                                   ggg <a  id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                                     </div>
							                                                    <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                           
							                                           </c:if>
							                                            
							                                            <c:if test="${lisence.userSessionStatus == 0  && lisence.isFree==0 && lisence.sessionId != coursesession.id}">
							                                            <div class="d-block py-1 text-white"><i class="fas fa-play-circle icon-active pl-2 text-white" ></i>
							                                                  <span class="act-name text-white">
							                                                   sss<a  style="pointer-events:none" id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                               </c:if>
							                                               
							                                              
							                                               
							                                               <c:if test="${lisence.userSessionStatus == 0  && lisence.isFree==1 && lisence.sessionId != coursesession.id}">
							                                            <div class="d-block py-1 text-white"><i class="fas fa-play-circle icon-active pl-2 text-white" ></i>
							                                                  <span class="act-name text-white">
							                                                   <a style="pointer-events:none"  id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                              </div>
							                                              <c:set var="cardOrder" value="${cardOrder + 1}"/>
							                                               </c:if>-->
							                                           
							                                           
							                 								<%-- <c:if test="${lisence.userSessionStatus==0 && lisence.isFree==0}">
							                                                 <div style="pointer-events:none" class="d-block py-1 text-white"><i class="fas fa-play-circle icon-active pl-2 text-white" ></i>
							                                                  <span class="act-name text-white">
							                 
							                                                 ${lisence.userSessionStatus}  <a id="load-content-${coursesession.id}-${content.contentId}" data-sessionid="${coursesession.id}" data-order="${cardOrder}" href="javascript:void(0);" class="text-white load-content" data-contentid="${content.contentId}" style="font-size:12px;font-weight:normal;text-decoration: none;">${content.contentName}</a>
							                                                   </span>
							                                              </div>
							                                              </c:if> --%>
							                                       </c:if>
							                                       </div>
							                                </c:forEach>
							                             <%--    </c:forEach> --%>
							                               
							                                
							                                
							                                
							                                
							                            </div>
							                     </div>
							                     <c:set var="j" value="${j+1}"/>
									        </c:forEach>
									        
									</c:forEach>
								</c:if> 
							</div>
						</div>
					</div>
					<!-- -----------Sidebar Code end Here------------- -->
					
				
					<main class="col pl-2 pt-2 content-wrapper">
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
																       <c:if test="${sessionName != null }"> 
																&nbsp; <h7 style="color:gray">/</h7> &nbsp;
																 </c:if>
							<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;">${section.sectionName}</a></li>
							 <li class="breadcrumb-item active"><a href="javascript:void(0);" style="font-weight:bold;text-decoration: underline;">${sessionName}</a></li>
							<!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
							</ol>
						</nav>
					
					<div id="course-content-view-div" class="col-md-12 col-sm-12 col-xs-12">
		              
							
							 
							 <c:if test="${activeContent.contentType == 'VIDEO'}">
							     
								<div class="col-sm-12 mt-4 mb-4">
								       <div class="row bg-dark">
											<div class="col-sm-1"></div>
											<div class="col-sm-10">


                            <%--       <video id="video"	onplay="videoStart()"  data-contentid="${activeContent.contentId}" data-sessionid="${activeContent.sessionId}" width="100%" height="400" controls autoplay onended="videoEnd()" controls controlsList="nodownload">
												   <source src="${activeContent.streamingUrl} ">
												</video>
							 --%>	   
									<video id="video" onplay="videoStart()" data-contentid="${activeContent.contentId}" data-sessionid="${activeContent.sessionId}" width="100%" height="400"   crossorigin="" preload="none" class="vedio"  onended="videoEnd()" controls autoplay controlsList="nodownload">
												<source src="${activeContent.streamingUrl}">
												<track kind="captions" label="English" srclang="en"
									src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.en.vtt"
									default />
											</video>	   
									<input type="hidden" value="${activeContent.streamingUrl}" id="videourl">	
										
											</div>
											<div class="col-sm-1"></div>
									    </div> 
								</div>
							<!-- 	<div class="col-sm-12 mb-4 pl-0 pr-0">
									<div class="feedback">
										<div class="feedback-container"> -->
										<!-- 	<span>
											 
											<h5 class="d-inline">Rate Feedback</h5>
											<i class="far fa-frown px-2 fa-icon"></i>
											<i class="far fa-laugh-beam px-2 fa-icon"></i>
											<i class="far fa-grin-stars px-2 fa-icon"></i> 
											</span> -->


								
						    </c:if>
						    
						    



						    
				           <div id="gotoassesment">
				           
				           
				          
				             <c:if test="${isStatus==1}">
				             <center><button id='submit' type='button' class='btn btn-info' onClick='redirectOnNextContent("${sessionId}","${contentId}");'>Go To Session Test</button></center>
	 	    					<%-- <input type="text" value="${sessionId}${contentId}"> --%>
				             </c:if>
						      <input type="hidden" id="courseId" value="${courseId}"/>
						      <input type="hidden" id="sectionId" value="${sectionId}"/>
						      <input type="hidden" id="contentId" value="${contentId}"/>
						      <input type="hidden" id="attemptId" value="${attemptId}"/>
						      <input type="hidden" id="token" value="${token}"/>
						      <input type="hidden" id="sessionId" value="${sessionId}"/>
						      <input type="hidden" id="userId" value="${userId}"/>
						      <input type="hidden" id="isStatus" value="${isStatus}"/>
						      
							</div>
					    
<div id="feedback-div" class="col-md-12 col-sm-12 col-xs-12">
   <div class="_1TUMc" style="background:#fff;border-radius:3px;padding: 0.8rem 0rem;">    
      <div class="d-inline-flex maximum" id="ratelecture">
      <h4 class="h5 font-weight-normal text-dark pl-3 pr-2">Rate Lecture</h4>
        <button class="_2ItfS" data-toggle="modal"  id="smilebutton" style="background:transparent;border:none;">
          
          <img src="resources/images/bad.png" class="_1inE5" alt="Bad" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold">
          		<input type="text" id="bads" size="1" style="border:none;background:#ffff" disabled/>
          	</span>
          <img src="resources/images/good.png" class="_1inE5" alt="Good" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold">
          		<input type="text" id="goods" size="1" style="border:none;background:#ffff" disabled/>
          	</span>
          <img src="resources/images/excellent.png" class="_1inE5" alt="Excellent" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold">
          		<input type="text" id="excellents" size="1" style="border:none;background: #ffff" disabled/>
          	</span>
        </button>
      </div>

</div> 
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 650px;margin: 3.75rem auto;">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">
            
          <!-- <h5 class="modal-title text-center" id="exampleModalLabel">VIDEO FEEDBACK</h5> -->
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <!-- <div class="_2tcMl">VIDEO FEEDBACK</div> -->
          <div class="_2tcMl">Session Feedback</div>
          <div class="_3iwce">
            <div id ="bad2" class="_2oeEO aau7W ">
               <div class="_3ctp0 flex_centered" id="bad" onclick="feedback('bad')">
                 <img  id="bad1" src="resources/images/bad.png" alt="Bad" width="40" height="40" style="filter: grayscale(100%);"></div>
               <div class="gqpVv">Bad</div>
            </div>
            <div id="good2" class="_2oeEO aau7W">
               <div class="_3ctp0 flex_centered" id="good" onclick="feedback('good')">
                 <img  id="good1" src="resources/images/good.png"  alt="Good" width="40" height="40" style="filter: grayscale(100%);"></div>
               <div class="gqpVv">Good</div>
            </div>
            <div id="excellent2" class="_2oeEO aau7W">
               <div class="_3ctp0 flex_centered" id="excellent" onclick="feedback('excellent')">
                 <img  id="excellent1" src="resources/images/excellent.png"  alt="Excellent" width="40" height="40" style="filter: grayscale(100%);"></div>
               <div class="gqpVv">Excellent</div>
            </div>
         </div>
         <div class="zR4n5">
            <h6 class="text-dark" style="padding-left: 0.5rem;">Please provide your feedback :</h6>
            <div class="mb-10" style="text-align:center"><textarea id="feedbackData" class="kpatr _2U6rr" style="width: 98%;" required></textarea></div>
         </div>
        </div>
        <div class="modal-footer" style="border:none;">
         <button target="_blank" class="themeBtn float-right" id="submitbutton" onclick="saveFeedBack();" data-dismiss="modal" data-toggle="modal" data-target="#submitFeedback" style="align:center;">Submit</button>
        </div>
        
        
        
        
        
        
      </div>
    </div>
  </div>
  
 <div class="modal fade" id="modalemail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Validate your email</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p style="color: red;">*You are restricted to give your feedback because your email is not validated.</p>
       <input type="email" id="email" placeholder="Enter valid email">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="emailValidation();">Validate email</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="modalemailconfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p style="color: green;">A link has been sent to your mail. Please, validate the link and proceed for feedback.</p>
     
      </div>
      <div class="modal-footer">
       
        <button type="button"  class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 
 
 
  <div class="modal fade" id="submitFeedback" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="overflow-y:hidden;">
    <div class="modal-dialog" role="document" style="max-width: 550px;margin: 3.75rem auto;overflow-y:hidden;">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">           
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
          	<span aria-hidden="true">&times;</span>
          </button>         
        </div>
        <div class="modal-body">         
          <div class="_2tcMl">
         	<button type="button" class="btn btn-info btn-circle btn-xl"><i class="fas fa-check"></i></button>
          	<h3>Thanks for providing your feedback</h3>
          	<h6 class="text-secondary">We will use it to improve your experience on eLuminate!</h6>
         </div>
        </div>
        <div class="modal-footer" style="border:none;">
       <!--   <button target="_blank" class="themeBtn float-right" data-dismiss="modal" style="align:center;">Close</button> -->
        </div>
      </div>
    </div>
  </div>
  </div>
  </div>
					</main>
			</div>
	</div>
	
	
<!-- 	
<p>Enter chat and press enter.</p>
<input id="input" placeholder="Your Message Here"/>
<p>Chat Output:<p>
<div id="box"></div>
<script src="https://cdn.pubnub.com/sdk/javascript/pubnub.4.23.0.min.js"></script>
<script>(function(){
  var pubnub = new PubNub({publishKey : 'pub-c-90dd3699-0727-44c2-9cc2-86bb2472f4aa', subscribeKey : 'sub-c-b9805752-dfd0-11e7-a9dc-b2bd6a03f293'}); // Your PubNub keys here. Get them from https://dashboard.pubnub.com.
  var box = document.getElementById("box"), input = document.getElementById("input"), channel = 'chat';
  pubnub.subscribe({channels: [channel]}); // Subscribe to a channel.
  pubnub.addListener({message: function(m) {
       box.innerHTML = (''+m.message).replace( /[<>]/g, '' ) + '<br>' + box.innerHTML; // Add message to page.
    }});
  input.addEventListener('keypress', function (e) {
      (e.keyCode || e.charCode) === 13 && pubnub.publish({ // Publish new message when enter is pressed. 
          channel : channel, message : input.value, x : (input.value='')
      });
  });
})();</script>
 -->
	<script type="text/html" id="pretesttemplate">
     

<div class="main-right-div">
<div class="row my-2">
<div class="col-sm-12">
<div id="instructions">
<!-- Main content -->
<section class="content">
<div class="row justify-content-center">
<div class="col-sm-8 test-details form-group rounded">
<div>
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
  <a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="text-decoration:none;color:#333;"><span class="pr-2"><i class="fas fa-arrow-left"></i></span>Back</a>	
</div>
<h3 class="pb-4 pt-1" style="font-weight: 500;font-size: 22px;text-align:center">Instructions</h3>
<input type="hidden" id="numAttempt" value="0"> <input type="hidden" id="countAttempt" value="1">
<dl class="dl-horizontal">
<dt class="form-group">#1</dt>
<dd id="">This test is based on <strong>${'$'}{testDesc}</strong>.</dd>
<dt class="form-group">#2</dt>
<dd id="">All Questions are compulsory.</dd>
<dt class="form-group">#3</dt>
<dd id="">You will not be able to <strong>PAUSE</strong> the test.</dd>
<dt class="form-group">#4</dt>
<dd id="">This test may contain multiple choice or fill in the blank type of Questions. No marks will be awarded for unattempted questions.</dd>
<dt class="form-group">#5</dt>
<dd id="">This test has <strong>NO</strong> negative marking.</dd>


<h3 class="py-4" style="font-weight: bold;font-size: 22px;text-align:center;color:rgb(97, 151, 220);">Good Luck!</h3>

<div class="col-sm-12 form-group text-center">

<button type="button" class="preBtn px-5" onclick=loadtesttemplate()>Start Test</button>
</div>
</div>


</div>
</section>
</div> 
</div>
</div> 
</div>
                             <input type="hidden" id="courseId" value="${courseId}"/>
						      <input type="hidden" id="sectionId" value="${sectionId}"/>
						      <input type="hidden" id="contentId" value="${'$'}{contentId}"/>
						      <input type="hidden" id="attemptId" value="${attemptId}"/>
						      <input type="hidden" id="token" value="${token}"/>
						      <input type="hidden" id="sessionId" value="${'$'}{sessionId}"/>
						      <input type="hidden" id="userId" value="${userId}"/>
						      <input type="hidden" id="isStatus" value="${isStatus}"/>











    </script>
	
<script type="text/html" id="feedbacktemplate">

 
  <div class="_1TUMc" style="background:#fff;border-radius:3px;padding: 0.8rem 0rem;">    
      <div class="d-inline-flex maximum" id="ratelecture">
      <h4 class="h5 font-weight-normal text-dark pl-3 pr-2">Rate Lecture</h4>
        <button class="_2ItfS" data-toggle="modal"  id="smilebutton" style="background:transparent;border:none;">
          
          <img src="resources/images/bad.png" class="_1inE5" alt="Bad" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold">${badreviewcount}</span>
          <img src="resources/images/good.png" class="_1inE5" alt="Good" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold">${goodreviewcount}</span>
          <img src="resources/images/excellent.png" class="_1inE5" alt="Excellent" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold">${excellentreviewcount}</span>
        </button>
      </div>

</div>
 


</script>

	<script type="text/html" id="videocontentloadtemplate">
        <nav aria-label="breadcrumb">
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
 																			 <input type="hidden" name="id" value="${activeSessionId}"/>
																        </form>
                                		&nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;">${section.sectionName}</a></li>
							
                              <li class="breadcrumb-item active"><a href="javascript:void(0);" style="font-weight:bold;text-decoration:underline;">${'$'}{sessionName}</a></li>
							<!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
							</ol>
						</nav>        

                                        <div class="col-sm-12 mt-4 mb-4">
								          <div class="row bg-dark">
											<div class="col-sm-12">
											<video id="video" onplay="videoStart()" data-contentid="${'$'}{contentId}" data-sessionid="${'$'}{sessionId}" width="100%" height="400"  crossorigin="" preload="none"   onended="videoEnd()" controls  autoplay controlsList="nodownload">
												<source src="${'$'}{streamingUrl}">
                                             <track kind="captions" label="English" srclang="en"
									src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.en.vtt"
									default />
											</video>
                                          <input type="hidden" value="${'$'}{streamingUrl}" id="videourl"/>
											</div>
											<div class="col-sm-1"></div>
											<div class="col-sm-10">
												 
											<!--	<video id="video"	onplay="videoStart()"  data-contentid="${'$'}{contentId}" data-sessionid="${'$'}{sessionId}" width="100%" height="400" controls autoplay onended="videoEnd()" controls controlsList="nodownload">
												   <source src="${'$'}{streamingUrl}">
												</video>-->
												 

               
											</div>
											<div class="col-sm-1"></div>
									    </div> 
								</div>


  

								<div class="col-sm-12 mb-4 pl-0 pr-0">
								<!--	<div class="feedback">
										<div class="feedback-container">
											<span>
											<h5 class="d-inline">Rate Feedback</h5>
											<i class="far fa-frown px-2 fa-icon"></i>
											<i class="far fa-laugh-beam px-2 fa-icon"></i>
											<i class="far fa-grin-stars px-2 fa-icon"></i> 
											</span>
										</div>
									</div> -->
                              <div id="gotoassesment">
     						  {{if isStatus==1}}
				             <center><button type='button' class='btn btn-info' onClick='redirectOnNextContent("${'$'}{sessionId}","${'$'}{contentId}");'>Go To Session Test</button></center>
	 	    	 			  {{/if}}
                              <input type="hidden" id="courseId" value="${courseId}"/>
						      <input type="hidden" id="sectionId" value="${'$'}{sectionId}"/>
						      <input type="hidden" id="contentId" value="${'$'}{contentId}"/>
						      <input type="hidden" id="attemptId" value="${'$'}{attemptId}"/>
						      <input type="hidden" id="token" value="${token}"/>
						      <input type="hidden" id="sessionId" value="${'$'}{sessionId}"/>
						      <input type="hidden" id="userId" value="${userId}"/>
						      <input type="hidden" id="isStatus" value="${'$'}{isStatus}"/>
                              <input type="hidden"   id="sessionName" value="${'$'}{sessionName}"/>
						      </div>
		</div> 
<div id="feedback-div" class="col-md-12 col-sm-12 col-xs-12">
   <div class="_1TUMc" style="background:#fff;border-radius:3px;padding: 0.8rem 0rem;">    
      <div class="d-inline-flex maximum" id="ratelecture">
      <h4 class="h5 font-weight-normal text-dark pl-3 pr-2">Rate Lecture</h4>
          <button class="_2ItfS" data-toggle="modal" data-target="#exampleModal" style="background:transparent;border:none;">
          
          <img src="resources/images/bad.png" class="_1inE5" alt="Bad" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold"><input type="text" id="badt" size="1" style="border:none;background:#ffff" disabled/></span>
          <img src="resources/images/good.png" class="_1inE5" alt="Good" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold"><input type="text" id="goodt" size="1" style="border:none;background:#ffff" disabled/></span>
          <img src="resources/images/excellent.png" class="_1inE5" alt="Excellent" width="25" height="25" style="">
          	<span class="pl-1 pr-3 font-weight-bold"><input type="text" id="excellentt" size="1" style="border:none;background:#ffff" disabled/></span>
        </button>
      </div>

</div> 
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 650px;margin: 3.75rem auto;">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">
            
          <!-- <h5 class="modal-title text-center" id="exampleModalLabel">VIDEO FEEDBACK</h5> -->
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <!-- <div class="_2tcMl">VIDEO FEEDBACK</div> -->
          <div class="_2tcMl">Session Feedback</div>
          <div class="_3iwce">
            <div id ="bad2" class="_2oeEO aau7W ">
               <div class="_3ctp0 flex_centered" id="bad" onclick="feedback('bad')">
                 <img  id="bad1" src="resources/images/bad.png" alt="Bad" width="40" height="40" style="filter: grayscale(100%);"></div>
               <div class="gqpVv">Bad</div>
            </div>
            <div id="good2" class="_2oeEO aau7W">
               <div class="_3ctp0 flex_centered" id="good" onclick="feedback('good')">
                 <img  id="good1" src="resources/images/good.png"  alt="Good" width="40" height="40" style="filter: grayscale(100%);"></div>
               <div class="gqpVv">Good</div>
            </div>
            <div id="excellent2" class="_2oeEO aau7W">
               <div class="_3ctp0 flex_centered" id="excellent" onclick="feedback('excellent')">
                 <img  id="excellent1" src="resources/images/excellent.png"  alt="Excellent" width="40" height="40" style="filter: grayscale(100%);"></div>
               <div class="gqpVv">Excellent</div>
            </div>
         </div>
         <div class="zR4n5">
            <h6 class="text-dark" style="padding-left: 0.5rem;">Please provide your feedback :</h6>
            <div class="mb-10" style="text-align:center"><textarea id="feedbackData" class="kpatr _2U6rr" style="width: 98%;" required></textarea></div>
         </div>
        </div>
        <div class="modal-footer" style="border:none;">
         <button target="_blank" class="themeBtn float-right" id="submitbutton" onclick="saveFeedBack();" data-dismiss="modal" data-toggle="modal" data-target="#submitFeedback" style="align:center;">Submit</button>
        </div>
        
        
        
        
        
        
      </div>
    </div>
  </div>
  
 <div class="modal fade" id="modalemail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Validate your email</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p style="color: red;">*You are restricted to give your feedback because your email is not validated.</p>
       <input type="email" id="email" placeholder="Enter valid email">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="emailValidation();">Validate email</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="modalemailconfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p style="color: green;">A link has been sent to your mail. Please, validate the link and proceed for feedback.</p>
     
      </div>
      <div class="modal-footer">
       
        <button type="button" onclick="pageReload()" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 
 
 
  <div class="modal fade" id="submitFeedback" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="overflow-y:hidden;">
    <div class="modal-dialog" role="document" style="max-width: 550px;margin: 3.75rem auto;overflow-y:hidden;">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">           
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
          	<span aria-hidden="true">&times;</span>
          </button>         
        </div>
        <div class="modal-body">         
          <div class="_2tcMl">
         	<button type="button" class="btn btn-info btn-circle btn-xl"><i class="fas fa-check"></i></button>
          	<h3>Thanks for providing your feedback</h3>
          	<h6 class="text-secondary">We will use it to improve your experience on eLuminate!</h6>
         </div>
        </div>
        <div class="modal-footer" style="border:none;">
       <!--   <button target="_blank" class="themeBtn float-right" data-dismiss="modal" style="align:center;">Close</button> -->
        </div>
      </div>
    </div>
  </div>
  </div>

    </script>
	<script type="text/html" id="reviewtestcontentloadtemplate">

    <nav aria-label="breadcrumb">
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
 																			 <input type="hidden" name="id" value="${activeSessionId}"/>
																        </form>
                            		&nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;">${section.sectionName}</a></li>
							 <li class="breadcrumb-item active"><a href="javascript:void(0);" style="font-weight:bold;text-decoration:underline;"><c:if test="${practicetest eq true}">Practice Test</c:if>${'$'}{sessionName}</a></li>
							<!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
							</ol>
						</nav>  
                       
          
        <div class="row">
                        <div class="modal-main-div">
                            <div class="modal-div">
                                
                                <div class="mt-5">
                       
                       
                                    <div class="modal-box-div question-modal" id="given-test-content">
                                        <div class="card card-main-div">
                                            {{each test.sectionlist}}
                                               <div class="test-section-div">
                                                {{each questionList}}
	                                            <div class="box-body questionAllDiv {{if $index == 0}}active{{/if}}">
													   <div>
													      <div class="imgset pull-left question-title">
													         <div><strong>${'$'}{questionNo}. </strong>{{html questionName}}</div>
													      </div>
													   </div>
													   <div class="singleMultiChoice options-div">
													      {{each optionList}}
														      <div class="option-click box tools pull-left input-group">
														         <p class="input-group-addon"> 
                                                                 
                                                                 {{if optionPk == optionId && answerStatus == 1}}
                                                                       <span class="badge button-green-color bagde-style">
                                                                           {{html getOptionIndexHtml($index)}}
                                                                       </span> 
                                                                   {{else}}
                                                                  {{if optionPk == optionId && answerStatus == 0}}
                                                                       <span class="badge button-red-color bagde-style">
                                                                           {{html getOptionIndexHtml($index)}}
                                                                       </span>
                                                                   {{else}}  
																  {{if answerStatus ==1}}
 																   <span class="badge button-green-color bagde-style">
                                                                           {{html getOptionIndexHtml($index)}}
                                                                       </span>		
                                                                    
                                                                   {{else}}
                                                                        {{if isCorrect ==-1}}
 																	    <span class="badge button-red-color bagde-style">
                                                                           {{html getOptionIndexHtml($index)}}
                                                                       </span>			
                                                                          {{else}}
                                                                       <span class="badge button-color-blue bagde-style">
                                                                           {{html getOptionIndexHtml($index)}}
                                                                       </span>
                                                                 {{/if}} {{/if}}{{/if}}{{/if}}
                                                                 </p>
														         <div class="pull-left imgset">
														            <p style="position:absolute;">{{html optionName}}</p>
														         </div>
														         <div class="clearfix"></div>
														      </div>
														   {{/each}}   
													   </div>
													</div>
												 {{/each}}	
                                                </div>
											   {{/each}}	
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                    <div class="row mt-4">
						 <div class="col-sm-4 text-sm-center">
							<button class="btn btn-success float-right previous-question-btn"> << Previous</button>
							<!-- <div class="navigation-div previous-question-btn">
                                    <i class="fas fa-arrow-left white-arrow"></i>
                                </div> -->
						</div>
                        <c:if test="${lisence.userSessionStatus == 1}">
<div class="col-sm-4">
                                                                 {{if isChapterTest==0 }}                
<a href="javascript:void(0);" class="go-to-next-content" onClick=redirectOnNextContent(${'$'}{sessionId},${'$'}{contentId}) data-contentid="${'$'}{contentId}" data-sessionid="${'$'}{sessionId}" style="text-decoration:none;"><div class="width-class button-css button-color button-shadow">Next Assesment</div></a>
</div>
               {{/if}} 
          </c:if>
                                <c:if test="${lisence.userSessionStatus == 0}">

									<div class="col-sm-4">
 <a href="https://qa.eluminate.in/all-courses" onClick=redirectOnNextContent(${'$'}{sessionId},${'$'}{contentId})> <div class="width-class button-css button-color button-shadow">Subscribe this course</div></a>
									</div>
                                  </c:if>
						
						<div class="col-sm-4 text-sm-center">
							<!-- <div class="navigation-div next-question-btn">
                                    <i class="fas fa-arrow-right white-arrow"></i>
                                </div>--> 
							<button class="btn btn-success float-left px-4 next-question-btn">Next >> </button>
						</div>
						

                    </div>
 <!--  <div class="_1TUMc" style="background:#fff;border-radius:3px;">
    <div class="_1s8Rr">
    <h4 class="h5 font-weight-normal text-dark pl-3 pr-2 pt-1">Rate Lecture</h4>
      <div class="">
       <button class="_2ItfS" data-toggle="modal" data-target="#exampleModal" style="background:transparent;border:none;">
          
          <img src="resources/images/bad.png" class="_1inE5" alt="Bad" width="25" height="25" style=""><span class="px-2 font-weight-bold">${badreviewcount}</span>
          <img src="resources/images/good.png" class="_1inE5" alt="Good" width="25" height="25" style=""><span class="px-2 font-weight-bold">${goodreviewcount}</span>
          <img src="resources/images/excellent.png" class="_1inE5" alt="Excellent" width="25" height="25" style=""><span class="px-2 font-weight-bold">${excellentreviewcount}</span>
        </button>
      </div>
    </div>
  </div> -->
  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">
            
          <!-- <h5 class="modal-title text-center" id="exampleModalLabel">VIDEO FEEDBACK</h5> -->
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <!-- <div class="_2tcMl">VIDEO FEEDBACK</div> -->
          <div class="_2tcMl">Session Feedback</div>
          
        <div class="zR4n5">
            <h6 style="color: #0c0c0c;">Please provide your feedback :</h6>
            <div class="mb-10"><textarea id="feedbackData" class="kpatr _2U6rr" style="width: 100%;" required></textarea></div>
         </div>
        </div>
        <div class="modal-footer" style="border:n one;">
         <button target="_blank" id="submitbutton" class="themeBtn float-right"  onclick="saveFeedBack();" data-dismiss="modal" style="align:center;">Submit</button>
        </div>
      </div>
    </div>
  </div>
    </script>
	<script type="text/html" id="testcontentloadtemplate">
   <nav aria-label="breadcrumb">
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
 																			 <input type="hidden" name="id" value="${activeSessionId}"/>
																        </form>
                            	&nbsp; <h7 style="color:gray">/</h7> &nbsp;
							<li class="breadcrumb-item active"><a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="font-weight:bold;">${section.sectionName}</a></li>
							 <li class="breadcrumb-item active"><a href="javascript:void(0);" style="font-weight:bold;text-decoration:underline;"><c:if test="${practicetest eq true}">Practice Test</c:if>${'$'}{sessionName}</a></li>
							<!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
							</ol>
						</nav>  
        <div class="row">
                        <div class="modal-main-div">
                            <div class="modal-div">
                               <!-- <div class="navigation-div flex_center margin-left next-question-btn">
                                    <i class="fas fa-arrow-right white-arrow"></i>
                                </div> -->
                                <div class="mt-5">
                                    <div class="modal-box-div question-modal" id="given-test-content" data-viewid="${'$'}{contentViewId}">
                                        <div class="card card-main-div">
                                            {{each test.sectionlist}}
                                               <div class="test-section-div" data-sectionid="${'$'}{sectionId}">
												 			<div class="row">
														<div class="col-sm-6">
									                   		<div class="imgset pull-left question-title">
									                       		<div>
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
															 	
                                                           <a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="text-decoration:none;color:#333;"><span class="pr-2"><i class="fas fa-arrow-left"></i></span>Back</a></div>	
															</div>	
                                                       												 
									                  	</div>
														<div class="col-sm-6">
															<div class="imgset float-right question-title" id="basicUsage"></div>
                                                         </div>
								                 	</div>	
                                                {{each questionList}}
	                                            <div class="box-body questionAllDiv {{if $index == 0}}active{{/if}}" data-questionid="${'$'}{questionId}" data-type="${'$'}{questionType}">
										
													   <div>
													      <div class="imgset pull-left question-title">
													         <div><strong>${'$'}{questionNo}. </strong>{{html questionName}}</div>
													      </div>
												 	   </div>


																

													   <div class="singleMultiChoice options-div">
													      {{each option}}
														      <div class="option-click box tools pull-left input-group" data-optionid="${'$'}{optionId}">
														         <p class="input-group-addon check-answer" style="cursor:pointer"> <span class="badge button-color-blue bagde-style">{{html getOptionIndexHtml($index)}}</span></p>
														         <div class="pull-left imgset">
														            <p style="position:absolute;">{{html optionName}}</p>
														         </div>
														         <div class="clearfix"></div>
														      </div>
														   {{/each}}   
													   </div>
													</div>
												 {{/each}}	
                                                </div>
											   {{/each}}	
                                        </div>
                                    </div>
                                </div>
                              <!-- <div class="navigation-div flex_center previous-question-btn">
                                    <i class="fas fa-arrow-left white-arrow"></i>
                                </div> -->
                            </div>
                        </div>
                    </div>
                     <div class="row mt-4">
						 <div class="col-sm-4 text-sm-center">
							<button class="btn btn-success float-right previous-question-btn"> << Previous</button>
							<!-- <div class="navigation-div previous-question-btn">
                                    <i class="fas fa-arrow-left white-arrow"></i>
                                </div> -->
						</div>
                        
						<div class="col-sm-4">
							<div class="button-main-div">
						         	<a href="javascript:void(0);" class="submit-test-details"><div class="width-class button-css button-color button-shadow">Submit</div></a>
								</div>
							
						</div>
						<div class="col-sm-4 text-sm-center">
							<!-- <div class="navigation-div next-question-btn">
                                    <i class="fas fa-arrow-right white-arrow"></i>
                                </div>--> 
							<button class="btn btn-success float-left px-4 next-question-btn">Next >> </button>
						</div>
						
                    </div>
                   <!-- <div class="row">
                        <div class="button-main-div">
						        <a href="javascript:void(0);" class="submit-test-details"><div class="width-class button-css button-color button-shadow">Submit</div></a>
						</div>
                    </div>-->
{{if isPractice==0}}
  <div class="_1TUMc" style="background:#fff;border-radius:3px;padding: 0.8rem 0rem;">
    <div class="d-inline-flex maximum">
    <h4 class="h5 font-weight-normal text-dark pl-3 pr-2">Rate Lecture</h4>
      <div class="">
        <button class="_2ItfS" data-toggle="modal" data-target="#exampleModal" style="background:transparent;border:none;">
          
          <img src="resources/images/bad.png" class="_1inE5" alt="Bad" width="25" height="25" style="">
				<span class="pl-1 pr-3 font-weight-bold"><input type="text" id="badt" size="1" style="border:none;background:#ffff" disabled/></span>
          <img src="resources/images/good.png" class="_1inE5" alt="Good" width="25" height="25" style="">
				<span class="pl-1 pr-3 font-weight-bold"><input type="text" id="goodt" size="1" style="border:none;background:#ffff" disabled/></span>
          <img src="resources/images/excellent.png" class="_1inE5" alt="Excellent" width="25" height="25" style="">
				<span class="pl-1 pr-3 font-weight-bold"><input type="text" id="excellentt" size="1" style="border:none;background:#ffff" disabled/></span>
        </button>
      </div>
    </div>
  </div>

{{/if}}




   <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 650px;margin: 3.75rem auto;">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">
            
          <!-- <h5 class="modal-title text-center" id="exampleModalLabel">VIDEO FEEDBACK</h5> -->
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <!-- <div class="_2tcMl">VIDEO FEEDBACK</div> -->
          <div class="_2tcMl">Session Feedback</div>
          
         <div class="zR4n5">
            <h6 class="text-dark" style="padding-left: 0.5rem;">Please provide your feedback :</h6>
            <div class="mb-10" style="text-align:center"><textarea id="feedbackData" class="kpatr _2U6rr" style="width: 98%;" required></textarea></div>
         </div>
        </div>
        <div class="modal-footer" style="border:none;">
         <button target="_blank" class="themeBtn float-right" id="submitbutton" onclick="saveFeedBack();" data-dismiss="modal" data-toggle="modal" data-target="#submitFeedback" style="align:center;">Submit</button>
        </div>
        
        
        
        
        
        
      </div>
    </div>
  </div>
  
 <div class="modal fade" id="modalemail" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Validate your email</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p style="color: red;">*You are restricted to give your feedback because your email is not validated.</p>
       <input type="email" id="email" placeholder="Enter valid email">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="emailValidation();">Validate email</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="modalemailconfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
       
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <p style="color: green;">A link has been sent to your mail. Please, validate the link and proceed for feedback.</p>
     
      </div>
      <div class="modal-footer">
       
        <button type="button" onclick="pageReload()" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 
 
 
  <div class="modal fade" id="submitFeedback" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="overflow-y:hidden;">
    <div class="modal-dialog" role="document" style="max-width: 550px;margin: 3.75rem auto;overflow-y:hidden;">
      <div class="modal-content" style="background: #f2f4f6;">
        <div class="modal-header" style="border:none;">           
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" >
          	<span aria-hidden="true">&times;</span>
          </button>         
        </div>
        <div class="modal-body">         
          <div class="_2tcMl">
         	<button type="button" class="btn btn-info btn-circle btn-xl"><i class="fas fa-check"></i></button>
          	<h3>Thanks for providing your feedback</h3>
          	<h6 class="text-secondary">We will use it to improve your experience on eLuminate!</h6>
         </div>
        </div>
        <div class="modal-footer" style="border:none;">
       <!--   <button target="_blank" class="themeBtn float-right" data-dismiss="modal" style="align:center;">Close</button> -->
        </div>
      </div>
    </div>
  </div>
  </div>
                               <input type="hidden" id="courseId" value="${courseId}"/>
						      <input type="hidden" id="sectionId" value="${'$'}{sectionId}"/>
						      <input type="hidden" id="contentId" value="${'$'}{contentId}"/>
						      <input type="hidden" id="attemptId" value="${'$'}{attemptId}"/>
						      <input type="hidden" id="token" value="${token}"/>
						      <input type="hidden" id="sessionId" value="${'$'}{sessionId}"/>
						      <input type="hidden" id="userId" value="${userId}"/>
						      <input type="hidden" id="isStatus" value="${'$'}{isStatus}"/>
                              <input type="hidden"   id="sessionName" value="${'$'}{sessionName}"/>
 							  <input type="hidden" id="practiceId" value="${'$'}{isPractice}"/>



    </script>

	<script type="text/html" id="submitiontemplate">
    <main class="col pl-2 pt-2 content-wrapper">
   <nav aria-label="breadcrumb">
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
    
				<a href="javascript:void(0)" onclick="document.getElementById('newdashboardback').submit(); return false;" style="text-decoration:none;color:#fff;margin-bottom: 20px;    opacity: .9;font-weight: 700;    line-height: 38px;font-size: 25px;"><span class="pr-2"><i class="fas fa-arrow-left"></i></span>Back</a></li>
            
        </ol>
    </nav>
		
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
                                <h6 class="font-weight-bold">${'$'}{wrongQueAttempt+correctQueAttempt+unAttemptQue}</h6>
                                <h6 class="text-primary font-weight-bold">${'$'}{correctQueAttempt+wrongQueAttempt}</h6>
                                <h6 class="text-success font-weight-bold">${'$'}{correctQueAttempt}</h6>
                                <h6 class="text-danger font-weight-bold">${'$'}{wrongQueAttempt}</h6>
                                <h6 class="text-danger font-weight-bold">${'$'}{unAttemptQue}</h6>
                           </div>                                                                                                                              
                    </div>
                    <div class="col-6 d-inline-flex border-left">                               
                        
                        <div class="col">
                          <!--  <span class="d-inline-flex">
                                <i class="far fa-clock font-weight-bold"> Time Taken</i>
                                <h6 class="text-success pl-5 font-weight-bold">00:00:00 / 05:00:45</h6>
                            </span> -->
                            <span class="d-inline-flex">
                                <h6 class="font-weight-bold"> Marks Obtained</h6>
                                <h6 class="text-danger pl-4 font-weight-bold">${'$'}{obtainMarks}.0/${'$'}{(wrongQueAttempt+correctQueAttempt+unAttemptQue)*everyquestionmark} </h6>
                            </span>
                           
                        </div>
                                                                                                          
                </div>
                </div>
            </div>
            <div class="row py-4">
                <div class="col text-left">
                    <div class="button-main-div">
                     	{{if isPractice != 1}}<div class="width-class button-css button-color button-shadow">  <a href="javascript:void(0);" onClick="reviewClick()" style="font-size:12px;font-weight:normal;text-decoration: none;" >   Review</a></div>
                        {{/if}}
						    
                  </div>
                </div> 
                <div class="col text-right">
                    <div class="button-main-div"> 
                       <c:if test="${lisence.userSessionStatus == 1}">
						 <a href="javascript:void(0);" onClick=redirectOnNextContent(${'$'}{sessionId},${'$'}{contentId})> <div class="width-class button-css button-color button-shadow">Next Assesment</div></a>
                          </c:if>
                                <c:if test="${lisence.userSessionStatus == 0}">
 <a href="https://qa.eluminate.in/all-courses" onClick=redirectOnNextContent(${'$'}{sessionId},${'$'}{contentId})> <div class="width-class button-css button-color button-shadow">Subscribe this course</div></a>
                                  </c:if>
                    </div>
                </div>
            </div>
           
        </div> 
       
                 
    </div>
 </main> 
    						  <input type="hidden" id="courseId" value="${courseId}"/>
						      <input type="hidden" id="sectionId" value="${sectionId}"/>
						      <input type="hidden" id="contentId" value="${'$'}{contentId}"/>
						      <input type="hidden" id="attemptId" value="${attemptId}"/>
						      <input type="hidden" id="token" value="${token}"/>
						      <input type="hidden" id="sessionId" value="${'$'}{sessionId}"/>
						      <input type="hidden" id="userId" value="${userId}"/>
						      <input type="hidden" id="isStatus" value="${isStatus}"/>
						     
    
    </script>

	<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script> -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script
		src="resources/adminlte/plugins/jquery-loadTemplate/jquery.tmpl.min.js"></script>
	<script src="<spring:url value='resources/js/common.js'/>"></script>
	<script src="<spring:url value='resources/js/coursevideo.js?v=5'/>"></script>
	<script src="<spring:url value='resources/js/mousetrap.min.js?v=7'/>"></script>
	
   





	<script>
		var dateTime;
		var flag = false;
		var interval;
		function videoStart() {
			interval = setInterval(videotimeupdate, 5000);

			// alert(vid.duration);
			var today = new Date();
			var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-'
					+ today.getDate();
			var time = today.getHours() + ":" + today.getMinutes() + ":"
					+ today.getSeconds();
			dateTime = date + ' ' + time;
			//  document.getElementsByTagName("video")[0].removeAttribute("onplay"); 

		}
		function createJson() {

		}
		function videoEnd() {
			clearInterval(interval);
			var vid = document.getElementById("video");
			clearInterval(interval);
			var secion = Math.floor((vid.duration % 60));
			var timeDuration = Math.floor(vid.duration / 60);
			var totalTime = timeDuration + ":" + secion;

			var courseId = $("#courseId").val();
			var contentId = $("#contentId").val();
			var sectionId = $("#sectionId").val();
			var attemptId = $("#attemptId").val();
			var sessionId = $("#sessionId").val();
			var userId = $("#userId").val();
			var token = $("#token").val();
			console.log(token);
			var userData = {

				"courseId" : courseId,
				"contentId" : contentId,
				"sectionId" : sectionId,
				"attemptId" : attemptId,
				"sessionId" : sessionId,
				"startTime" : dateTime,
				"spentTime" : totalTime,
				"isMobile" : 0
			};
			console.log(JSON.stringify(userData));
			$
					.ajax({
						type : 'POST',
						crossDomain : true,
						dataType : 'json',

						contentType : "application/json",
						url : 'api/saveViewContentActivity1',
						data : JSON.stringify(userData),
						headers : {
							'Authorization' : token
						},
						success : function(response) {
							if (response.status == 200) {
								$("#gotoassesment")
										.html(
												"<center><button type='button' class='btn btn-info' onClick='redirectOnNextContent("
														+ sessionId
														+ ","
														+ contentId
														+ ");'>Go To Session Test</button></center>");

							} else {
								$("#gotoassesment")
										.html(
												"<center><button type='button' class='btn btn-info' onClick='redirectOnNextContent("
														+ sessionId
														+ ","
														+ contentId
														+ ");'>Go To Session Test</button></center>");

							}
						}
					});

		}

		$(document).ready(function() {
			(function(d, s, id) {
				 debugger;
				  var js, fjs = d.getElementsByTagName(s)[0];
				  if (d.getElementById(id)) return;
				  js = d.createElement(s); js.id = id;
				  js.src = "https://connect.facebook.net/en_US/sdk/xfbml.customerchat.js";
				  fjs.parentNode.insertBefore(js, fjs);
				}(document, 'script', 'facebook-jssdk'));
			
			
			
			var activeContentType = '${activeContent.contentType}';

			if (activeContentType == 'VIDEO') {
				var token = $("#token").val();
				document.getElementById("video").currentTime = '${videoTime}';
				$.ajax({
					type : 'get',
					crossDomain : true,
					dataType : 'json',

					contentType : "application/json",
					url : 'api/setPalyerCookie',
					headers : {
						'Authorization' : token
					},
					success : function(response) {

					}
				});
				playM3u8($("#videourl").val());
			}

			//document.oncontextmenu = document.body.oncontextmenu = function() {return false;}
			//	$("#navBar").remove();

			if (activeContentType == 'TEST') {
				debugger;
				console.log(JSON.parse('${activeContentJSON}'))
				startAssessment(JSON.parse('${activeContentJSON}'));
			}
			/*   else {
			bindVideoEvent();
			} */
		});

		function videotimeupdate() {

			var vid = document.getElementById("video");

			var currTime = vid.currentTime;
			var courseId = $("#courseId").val();
			var contentId = $("#contentId").val();
			var sectionId = $("#sectionId").val();
			var attemptId = $("#attemptId").val();
			var sessionId = $("#sessionId").val();
			var userId = $("#userId").val();
			console.log($("#token").val());
			var data = {
				"courseId" : courseId,
				"contentId" : contentId,
				"sectionId" : sectionId,
				"attemptId" : attemptId,
				"sessionId" : sessionId,
				"resumeTime" : currTime,
				"isMobile" : 0
			};
			console.log(JSON.stringify(data));
			$.ajax({
				type : 'POST',
				crossDomain : true,
				dataType : 'json',
				contentType : "application/json",
				url : 'api/updateresumevideo',
				data : JSON.stringify(data),
				headers : {
					'Authorization' : $("#token").val()
				},
				success : function(response) {
					//  alert("sucess");
				}
			});
		}
				
	</script>
	
<script>
	/**
	 * @license easytimer.js v1.0
	 * Created by Albert González
	 * Licensed under The MIT License.
	 *
	* @class Timer
	*/

	var module;

	var Timer = (

	    function (module) {
	        'use strict';

	        /*
	         * Polyfill por IE9, IE10 and IE11
	         */
	        var CustomEvent = typeof window !== 'undefined' ? window.CustomEvent : undefined;

	        if (typeof window !== 'undefined' && typeof CustomEvent !== "function" ) {
	            CustomEvent = function ( event, params ) {
	                params = params || { bubbles: false, cancelable: false, detail: undefined };
	                var evt = document.createEvent( 'CustomEvent' );
	                evt.initCustomEvent( event, params.bubbles, params.cancelable, params.detail );
	                return evt;
	            };

	            CustomEvent.prototype = window.Event.prototype;

	            window.CustomEvent = CustomEvent;
	        }

	        /*
	         * General functions, variables and constants
	         */
	        var SECOND_TENTHS_PER_SECOND = 10,
	            SECONDS_PER_MINUTE = 60,
	            SECOND_TENTHS_PER_MINUTE = 600,
	            MINUTES_PER_HOUR = 60,
	            SECONDS_PER_HOUR = 3600,
	            SECOND_TENTHS_PER_HOUR = 36000,
	            HOURS_PER_DAY = 24,

	            SECOND_TENTHS_POSITION = 0,
	            SECONDS_POSITION = 1,
	            MINUTES_POSITION = 2,
	            HOURS_POSITION = 3,
	            DAYS_POSITION = 4,

	            SECOND_TENTHS = 'secondTenths',
	            SECONDS = 'seconds',
	            MINUTES = 'minutes',
	            HOURS = 'hours',
	            DAYS = 'days',

	            unitsInMilliseconds = {
	                secondTenths: 100,
	                seconds: 1000,
	                minutes: 60000,
	                hours: 3600000,
	                days: 86400000
	            },

	            events = module && module.exports? require('events') : undefined,

	            prototype;

	        function hasDOM() {
	            return typeof document !== 'undefined';
	        }

	        function hasEventEmitter() {
	            return events;
	        }

	        function mod(number, module) {
	            return ((number % module) + module) % module;
	        }

	        function leftPadding(string, padLength, character) {
	            var i,
	                characters = '';

	            for (i = 0; i < padLength; i = i + 1) {
	                characters += String(character);
	            }

	            return (characters + string).slice(-characters.length);
	        }

	        /**
	         * [TimeCounter Stores the units counted by the timer]
	         */
	        function TimeCounter() {
	            this.secondTenths = 0;
	            this.seconds = 0;
	            this.minutes = 0;
	            this.hours = 0;
	            this.days = 0;

	            /**
	             * [toString convert the counted values on a string]
	             * @param  {[array]} units           [array with the units to display]
	             * @param  {[string]} separator       [separator of the units]
	             * @param  {[integer]} leftZeroPadding [number of zero padding]
	             * @return {[string]}                 [result string]
	             */
	            this.toString = function(units, separator, leftZeroPadding) {
	                units = units || ['hours', 'minutes', 'seconds'];
	                separator = separator || ':';
	                leftZeroPadding = leftZeroPadding || 2;

	                var stringTime,
	                    arrayTime = [],
	                    i,
	                    zeros = '';

	                for (i = 0; i < leftZeroPadding; i = i + 1) {
	                    zeros += '0';
	                }

	                for (i = 0; i < units.length; i = i + 1) {
	                    if (this[units[i]] !== undefined) {
	                        arrayTime.push(leftPadding(this[units[i]], leftZeroPadding, '0'));
	                    }
	                }
	                stringTime = arrayTime.join(separator);

	                return stringTime;
	            };
	        }

	        /**
	         * [Timer Timer/Chronometer/Countdown compatible with AMD and NodeJS.
	         * Can update time values with different time intervals: tenth of seconds,
	         * seconds, minutes and hours.]
	         */
	        function Timer() {

	            /*
	             * PRIVATE Variables and Functions
	             */
	            var counters = new TimeCounter(),
	                totalCounters =new TimeCounter(),

	                intervalId,
	                eventEmitter = hasDOM()? document.createElement('span') :
	                    hasEventEmitter()? new events.EventEmitter() : undefined,
	                running = false,
	                paused = false,
	                precision,
	                valueToAdd,
	                customCallback,
	                timerConfig = {},
	                target,
	                startValues,
	                countdown;

	            function isCountdownTimer() {
	                return timerConfig.countdown;
	            }

	            function updateCounters(counter, value) {
	                counters[counter] += value;
	                totalCounters[counter] += value;
	            }

	            function updateDays(value) {
	                updateCounters(DAYS, value);

	                dispatchEvent('daysUpdated');
	            }

	            function updateHours(value) {
	                updateCounters(HOURS, value);

	                counters.hours = mod(counters.hours, HOURS_PER_DAY);

	                if ((isCountdownTimer() && counters.hours === HOURS_PER_DAY - 1) ||
	                        (!isCountdownTimer() && counters.hours === 0)) {
	                    updateDays(value);
	                }

	                if (precision === HOURS) {
	                    totalCounters[MINUTES] += isCountdownTimer() ? -MINUTES_PER_HOUR : MINUTES_PER_HOUR;
	                    totalCounters[SECONDS] += isCountdownTimer() ? -SECONDS_PER_HOUR : SECONDS_PER_HOUR;
	                    totalCounters[SECOND_TENTHS] += isCountdownTimer() ? -SECOND_TENTHS_PER_HOUR : SECOND_TENTHS_PER_HOUR;
	                }

	                dispatchEvent('hoursUpdated');
	            }

	            function updateMinutes(value) {
	                updateCounters(MINUTES, value);

	                counters.minutes = mod(counters.minutes, MINUTES_PER_HOUR);

	                if ((isCountdownTimer() && counters.minutes === MINUTES_PER_HOUR - 1) ||
	                    (!isCountdownTimer() && counters.minutes === 0)) {
	                    updateHours(value);
	                }

	                if (precision === MINUTES) {
	                    totalCounters[SECONDS] += isCountdownTimer() ? -SECONDS_PER_MINUTE : SECONDS_PER_MINUTE;
	                    totalCounters[SECOND_TENTHS] += isCountdownTimer() ? -SECOND_TENTHS_PER_MINUTE : SECOND_TENTHS_PER_MINUTE;
	                }

	                dispatchEvent('minutesUpdated');
	            }

	            function updateSeconds(value) {
	                updateCounters(SECONDS, value);

	                counters.seconds = mod(counters.seconds, SECONDS_PER_MINUTE);

	                if ((isCountdownTimer() && counters.seconds === SECONDS_PER_MINUTE - 1) ||
	                    (!isCountdownTimer() && counters.seconds === 0)) {
	                    updateMinutes(value);
	                }

	                if (precision === SECONDS) {
	                    totalCounters[SECOND_TENTHS] += isCountdownTimer() ? -SECOND_TENTHS_PER_SECOND : SECOND_TENTHS_PER_SECOND;
	                }

	                dispatchEvent('secondsUpdated');
	            }

	            function updateSecondTenths(value) {
	                updateCounters(SECOND_TENTHS, value);

	                counters.secondTenths = mod(counters.secondTenths, SECOND_TENTHS_PER_SECOND);

	                if ((isCountdownTimer() && counters.secondTenths === SECOND_TENTHS_PER_SECOND - 1) ||
	                    (!isCountdownTimer() && counters.secondTenths === 0)) {
	                    updateSeconds(value);
	                }

	                dispatchEvent('secondTenthsUpdated');
	            }

	            function stopTimer() {
	                clearInterval(intervalId);
	                intervalId = undefined;
	                running = false;
	                paused = false;
	            }

	            function startTimer() {
	                var callback,
	                    interval = unitsInMilliseconds[precision];

	                switch (precision) {
	                case DAYS:
	                    callback = updateDays;
	                    break;
	                case HOURS:
	                    callback = updateHours;
	                    break;
	                case MINUTES:
	                    callback =  updateMinutes;
	                    break;
	                case SECOND_TENTHS:
	                    callback =  updateSecondTenths;
	                    break;
	                default:
	                    callback = updateSeconds;
	                }

	                intervalId = setInterval(
	                    function () {
	                        callback(valueToAdd);
	                        customCallback(counters);
	                        if (isTargetAchieved()) {
	                            dispatchEvent('targetAchieved');
	                            stop();
	                        }
	                    },
	                    interval
	                );

	                running = true;
	                paused = false;
	            }

	            function isRegularTimerTargetAchieved() {
	                return counters.hours > target[HOURS_POSITION]
	                    || (counters.hours === target[HOURS_POSITION] && (counters.minutes > target[MINUTES_POSITION]
	                        || (counters.minutes === target[MINUTES_POSITION]) && counters.seconds >= target[SECONDS_POSITION]));
	            }

	            function isCountdownTimerTargetAchieved() {
	                return counters.hours < target[HOURS_POSITION]
	                    || (counters.hours === target[HOURS_POSITION] && (counters.minutes < target[MINUTES_POSITION]
	                    || (counters.minutes === target[MINUTES_POSITION] && (counters.seconds < target[SECONDS_POSITION]
	                    || (counters.seconds === target[SECONDS_POSITION] && (counters.secondTenths < target[SECOND_TENTHS_POSITION]
	                    || counters.secondTenths === target[SECOND_TENTHS_POSITION] ))))));
	            }

	            function isTargetAchieved() {
	                return target instanceof Array &&
	                    (timerConfig.countdown && isCountdownTimerTargetAchieved() || !timerConfig.countdown && isRegularTimerTargetAchieved());
	            }

	            function resetCounters() {
	                for (var counter in counters) {
	                    if(counters.hasOwnProperty(counter) && typeof counters[counter] === 'number'){
	                        counters[counter] = 0;
	                    }
	                }

	                for (var counter in totalCounters) {
	                    if(totalCounters.hasOwnProperty(counter) && typeof totalCounters[counter] === 'number'){
	                        totalCounters[counter] = 0;
	                    }
	                }
	            }

	            function setParams(params) {
	                precision = params && typeof params.precision === 'string' ? params.precision : SECONDS;
	                customCallback = params && typeof params.callback === 'function'? params.callback : function () {};
	                valueToAdd = params && params.countdown === true? -1 : 1;
	                countdown = params && params.countdown == true;
	                if (params && (typeof params.target === 'object')) { setTarget(params.target)};
	                if (params && (typeof params.startValues === 'object')) { setStartValues(params.startValues)};
	                target = target || !countdown? target : [0, 0, 0, 0, 0];

	                timerConfig = {
	                    precision: precision,
	                    callback: customCallback,
	                    countdown: typeof params === 'object' && params.countdown == true,
	                    target: target,
	                    startValues: startValues
	                }
	            }

	            function configInputValues(inputValues) {
	                var secondTenths, seconds, minutes, hours, days, values;
	                if (typeof inputValues === 'object') {
	                    if (inputValues instanceof Array) {
	                        if (inputValues.length != 5) {
	                            throw new Error('Array size not valid');
	                        }
	                        values = inputValues;
	                    } else {
	                        values = [
	                            inputValues.secondTenths || 0, inputValues.seconds || 0,
	                            inputValues.minutes || 0, inputValues.hours || 0,
	                            inputValues.days || 0
	                        ];
	                    }
	                }

	                for (var i = 0; i < inputValues.length; i = i + 1) {
	                    if (inputValues[i] < 0) {
	                        inputValues[i] = 0;
	                    }
	                }

	                secondTenths = values[SECOND_TENTHS_POSITION];
	                seconds = values[SECONDS_POSITION] + Math.floor(secondTenths / SECOND_TENTHS_PER_SECOND);
	                minutes = values[MINUTES_POSITION] + Math.floor(seconds / SECONDS_PER_MINUTE);
	                hours = values[HOURS_POSITION] + Math.floor(minutes / MINUTES_PER_HOUR);
	                days = values[DAYS_POSITION] +  Math.floor(hours / HOURS_PER_DAY);

	                values[SECOND_TENTHS_POSITION] = secondTenths % SECOND_TENTHS_PER_SECOND;
	                values[SECONDS_POSITION] = seconds % SECONDS_PER_MINUTE;
	                values[MINUTES_POSITION] = minutes % MINUTES_PER_HOUR;
	                values[HOURS_POSITION] = hours % HOURS_PER_DAY;
	                values[DAYS_POSITION] = days;

	                return values;
	            }

	            function setTarget(inputTarget) {
	                target = configInputValues(inputTarget);

	            }

	            function setStartValues(inputStartValues) {
	                startValues = configInputValues(inputStartValues);
	                counters.secondTenths = startValues[SECOND_TENTHS_POSITION];
	                counters.seconds = startValues[SECONDS_POSITION];
	                counters.minutes = startValues[MINUTES_POSITION];
	                counters.hours = startValues[HOURS_POSITION]
	                counters.days = startValues[DAYS_POSITION]

	                totalCounters.days = counters.days;
	                totalCounters.hours = totalCounters.days * HOURS_PER_DAY + counters.hours;
	                totalCounters.minutes = totalCounters.hours * MINUTES_PER_HOUR + counters.minutes;
	                totalCounters.seconds = totalCounters.minutes * SECONDS_PER_MINUTE + counters.seconds;
	                totalCounters.secondTenths = totalCounters.seconds * SECOND_TENTHS_PER_SECOND + counters.secondTenths;
	            }

	            /*
	             * PUBLIC functions
	             */

	            /**
	             * [stop stops the timer and resets the counters. Dispatch stopped event]
	             */
	            function stop() {
	                stopTimer();
	                resetCounters();
	                dispatchEvent('stopped');
	            }

	            /**
	             * [start starts the timer configured by the params object. Dispatch started event]
	             * @param  {[object]} params [Configuration parameters]
	             */
	            function start(params) {
	                if (this.isRunning()) {
	                    throw new Error('Timer already running');
	                }

	                if (!this.isPaused()) {
	                    setParams(params);
	                }
	                if (!isTargetAchieved()) {
	                    startTimer();
	                    dispatchEvent('started');
	                }
	            }

	            /**
	             * [pause stops the timer without resetting the counters. The timer it can be restarted with start function.
	             * Dispatch paused event]
	             * @return {[type]} [description]
	             */
	            function pause() {
	                stopTimer();
	                paused = true;
	                dispatchEvent('paused');
	            }

	            /**
	             * [addEventListener Adds event listener to the timer]
	             * @param {[string]} event      [event to listen]
	             * @param {[function]} listener   [the event listener function]
	             */
	            function addEventListener(event, listener) {
	                if (hasDOM()) {
	                    eventEmitter.addEventListener(event, listener);
	                } else if (hasEventEmitter()) {
	                    eventEmitter.on(event, listener)
	                }
	            }

	            /**
	             * [removeEventListener Removes event listener to the timer]
	             * @param  {[string]} event    [event to remove listener]
	             * @param  {[function]} listener [listener to remove]
	             */
	            function removeEventListener(event, listener) {
	                if (hasDOM()) {
	                    eventEmitter.removeEventListener(event, listener);
	                } else if (hasEventEmitter()) {
	                    eventEmitter.removeListener(event, listener);
	                }
	            }

	            /**
	             * [dispatchEvent dispatchs an event]
	             * @param  {string} event [event to dispatch]
	             */
	            function dispatchEvent(event) {
	                if (hasDOM()) {
	                    eventEmitter.dispatchEvent(new CustomEvent(event));
	                } else if (hasEventEmitter()) {
	                    eventEmitter.emit(event)
	                }
	            }

	            /**
	             * [isRunning return true if the timer is running]
	             * @return {Boolean}
	             */
	            function isRunning() {
	                return running;
	            }

	            /**
	             * [isPaused returns true if the timer is paused]
	             * @return {Boolean}
	             */
	            function isPaused() {
	                return paused;
	            }

	            /**
	             * [getTimeValues returns the counter with the current timer values]
	             * @return {[TimeCounter]}
	             */
	            function getTimeValues() {
	                return counters;
	            };

	            /**
	             * [getTotalTimeValues returns the counter with the current timer total values]
	             * @return {[TimeCounter]}
	             */
	            function getTotalTimeValues() {
	                return totalCounters;
	            };

	            /**
	             * [getConfig returns the configuration paramameters]
	             * @return {[type]}
	             */
	            function getConfig () {
	                return timerConfig;
	            };

	            /**
	             * Public API
	             * Definition of Timer instance public functions
	             */
	            if (typeof this !== 'undefined') {
	                this.start= start;

	                this.pause = pause;

	                this.stop = stop;

	                this.isRunning = isRunning;

	                this.isPaused = isPaused;

	                this.getTimeValues = getTimeValues;

	                this.getTotalTimeValues = getTotalTimeValues;

	                this.getConfig = getConfig;

	                this.addEventListener = addEventListener

	                this.removeEventListener = removeEventListener;
	            }

	        };

	        if (module && module.exports) {
	            module.exports = Timer;
	        } else if (typeof define === 'function' && define.amd) {
	            define([], function() {
	                return Timer;
	            });
	        }

	        return  Timer;
	    }(module)
	);
	</script>
<script type="text/javascript">
var i=0;
var feedbackdata=null;
var isBadr=false;
var isGoodr=false;
var isExcellentr=false;  
function feedback(emoji){
	debugger;
	i=1;
	if(emoji=='bad'){
	 $("#bad2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
	 $('#bad1').addClass('emoji');
	 $('#bad1').removeAttr("style");
		
	 $('#good1').prop("style","filter: grayscale(100%)");
	 $('#excellent1').prop("style","filter: grayscale(100%)");
	 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
	 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
	 $("#excellent1").removeClass("emoji");
	 $("#good1").removeClass("emoji");
	 isBadr=true;
	 isGoodr=false;
	 isExcellentr=false; 
	 saveEmoji(emoji);
	}
	if(emoji=='good'){
		 $("#good2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
		 
		 $('#good1').addClass('emoji');
		 $('#good1').removeAttr("style");
		 $('#bad1').prop("style","filter: grayscale(100%)");
		 $('#excellent1').prop("style","filter: grayscale(100%)");
		 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
		 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
		 $("#excellent1").removeClass("emoji");
		 $("#bad1").removeClass("emoji");
		 isBadr=false;
		 isGoodr=true;
		 isExcellentr=false; 
		 saveEmoji(emoji);
		}
	if(emoji=='excellent'){
		 $("#excellent2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
		 $('#excellent1').addClass('emoji');
		 $('#excellent1').removeAttr("style");
		 $('#bad1').prop("style","filter: grayscale(100%)");
		 $('#good1').prop("style","filter: grayscale(100%)");
		 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
		 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
		 $("#bad1").removeClass("emoji");
		 $("#good1").removeClass("emoji");
		  isBadr=false;
		 isGoodr=false;
		 isExcellentr=true; 
		 saveEmoji(emoji);
		}
	}
 function saveEmoji(emoji){
	 var isBad;
	 var isGood;
	 var isExcellent;
	 if(emoji=='bad'){
		 isBad=true;
		 isGood=false;
		 isExcellent=false;
		 }
	 if(emoji=='good'){
		 isBad=false;
		 isGood=true;
		 isExcellent=false;
		 }
	 if(emoji=='excellent'){
		 isBad=false;
		 isGood=false;
		 isExcellent=true;
		 }
	var data1=JSON.stringify(createJson(isBad,isGood,isExcellent));

	 $.ajax({
			type : 'POST',
			url : 'api/giveuserFeedback',
			crossDomain : true,
			dataType : 'json',
			
			headers: {
				 'Authorization': $("#token").val() 
				 },
			contentType : "application/json",
			data : data1,
			success : function(data) {
				$('#bads').val(data.result.feedbackDetails.badReviewCount);
				$('#goods').val(data.result.feedbackDetails.goodReviewCount);
				$('#excellents').val(data.result.feedbackDetails.excellentReviewCount);
				$('#badt').val(data.result.feedbackDetails.badReviewCount);
				$('#goodt').val(data.result.feedbackDetails.goodReviewCount);
				$('#excellentt').val(data.result.feedbackDetails.excellentReviewCount);
				//$('#feedbackData').attr('disabled',true);
				//$('#feedbackData').val(data.result.feedbackDetails.userFeedback);
			}
		});
	} 
	function createJson(isBad,isGood,isExcellent){
		debugger;
		var courseId=$('#courseId').val();
		var sectionId=$('#sectionId').val();
		var sessionId =$('#sessionId').val();
		var userId=$('#userId').val();
		var contentId=$('#contentId').val();
		var obj={
				"courseId":courseId,
				"sectionId":sectionId,
				"sessionId":sessionId,
				"contentId":contentId,
		        "userId":userId,
		        "isBad":isBad,
		        "isGood":isGood,
		        "isExcellent":isExcellent,
		        "userFeedback":feedbackdata
				}
		return obj;
		}
 function saveFeedBack(){
	debugger;
	var savedata=JSON.stringify(createJsonforSubmit());

	var token = $("#token").val();
	$.ajax({
		type : 'POST',
		url : 'api/giveuserFeedback',
		crossDomain : true,
		dataType : 'json',
		contentType : "application/json",
		data : savedata,
		headers : {
			'Authorization' : token
		},
		success : function(data) {
			debugger;
			//$("#feedback-div ._1TUMc").remove();
			//$("#feedback-div").html("");
			/*  var feedBackHTML = $("#feedbacktemplate").tmpl({});
			$("#feedback-div ._1TUMc").html(feedBackHTML); */
			//$("#submitFeedback").modal('hide');
			$('#bads').val(data.result.feedbackDetails.badReviewCount);
			$('#goods').val(data.result.feedbackDetails.goodReviewCount);
			$('#excellents').val(data.result.feedbackDetails.excellentReviewCount);
			$('#feedbackData').attr('disabled',true);
			$('#submitbutton').hide();
			$('#feedbackData').val(data.result.feedbackDetails.userFeedback);
			 console.log(data.result);
			/* location.reload(); */
			
		}
	});
	}
function createJsonforSubmit(){
	var feedback=$('#feedbackData').val();
	var courseId=$('#courseId').val();
	var sectionId=$('#sectionId').val();
	var sessionId =$('#sessionId').val();
	var userId=$('#userId').val();
	var contentId=$('#contentId').val();
	
	feedbackdata=$('#feedbackData').val();
	var obj={
			"courseId":courseId,
			"sectionId":sectionId,
			"sessionId":sessionId,
			"contentId":contentId,
	        "userId":userId,
	        "isBad":isBadr,
	        "isGood":isGoodr,
	        "isExcellent":isExcellentr,
	        "userFeedback":feedback
			}
	return obj;
	}


$( document ).ready(function() {
	debugger;
	
	$('#smilebutton').on('click',function(){
		debugger;
		if('${validatedCount}'==1)
			{
			$('#exampleModal').modal();
			}
		if('${validatedCount}'==0)
			{
			$('#modalemail').modal();
			}

	})
	


	
	var getdata=JSON.stringify(createJsonforGet());
	$.ajax({
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : getdata,
		contentType : "application/json",
		url : 'api/getuserFeedback',
		headers : {
			 'Authorization': $("#token").val()
		},
		success : function(response) {
			console.log(response.result);
			var feed=response.result.feedbackDetails.userFeedback;
			var getBad=response.result.feedbackDetails.isBad;
			var getGood=response.result.feedbackDetails.isGood;
			var getExcellent=response.result.feedbackDetails.isExcellent;
			$('#bads').val(response.result.feedbackDetails.badReviewCount);
			$('#goods').val(response.result.feedbackDetails.goodReviewCount);
			$('#excellents').val(response.result.feedbackDetails.excellentReviewCount);
		
			$('#feedbackData').val(response.result.feedbackDetails.userFeedback);
			$('#badt').val(response.result.feedbackDetails.badReviewCount);
			$('#goodt').val(response.result.feedbackDetails.goodReviewCount);
			$('#excellentt').val(response.result.feedbackDetails.excellentReviewCount);
					$('#feedbackData').val(response.result.feedbackDetails.userFeedback);
			if(getBad==true){
				isBadr=true;
				isGoodr=false;
				isExcellentr=false; 
				$("#bad2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#bad1').removeAttr("style");
				 $('#good1').prop("style","filter: grayscale(100%)");
				 $('#excellent1').prop("style","filter: grayscale(100%)");
				 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(getGood==true){
				isBadr=false;
				isGoodr=true;
				isExcellentr=false;
				 $("#good2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#good1').removeAttr("style");
				 $('#bad1').prop("style","filter: grayscale(100%)");
				 $('#excellent1').prop("style","filter: grayscale(100%)");
				 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(getExcellent==true){
				isBadr=false;
				isGoodr=false;
				isExcellentr=true;
				$("#excellent2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#excellent1').removeAttr("style");
				 $('#bad1').prop("style","filter: grayscale(100%)");
				 $('#good1').prop("style","filter: grayscale(100%)");
				 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(feed!=null && feed!="")
				{
				$('#feedbackData').prop('value',feed);
				$('#feedbackData').prop('disabled', true);
				$('.themeBtn').hide();
				}
		}
	});
	});

function createJsonforGet(){
	var courseId=$('#courseId').val();
	var sectionId=$('#sectionId').val();
	var sessionId =$('#sessionId').val();
	var userId=$('#userId').val();
	var contentId=$('#contentId').val();
	
	var obj={
			"courseId":courseId,
			"sectionId":sectionId,
			"sessionId":sessionId,
			"contentId":contentId,
	        "userId":userId,
	        
			}
	return obj;
	}

function pageReload(){
	debugger;
	if(i==1)
	location.reload();
}


function emailValidation()
{
	debugger;
	var obj={
			"email":$('#email').val()
	}
	 $.ajax({
		 	type: 'POST',
			url : 'validateEmail',
          	dataType : 'json',
			contentType : "application/json",
			data :JSON.stringify(obj),
			success : function(data) {
				if(data){
					$('#modalemail').modal('hide');
					$('#modalemailconfirm').modal();
				}
			}
		});
	}
	function saveFeedBack1(){
	}
</script>



</body>

</html>







