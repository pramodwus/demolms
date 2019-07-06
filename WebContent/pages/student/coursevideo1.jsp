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

<link rel="stylesheet"
href="<spring:url value='resources/css/newcommon.css'/>">
<link rel="stylesheet"
href="<spring:url value='resources/css/page4.css'/>">
<title>eluminate</title>
</head>

<body>
<!--------------------- Navigation Bar Start ------------------------------------------------>
<nav class="navbar navbar-expand-md sticky-top navbar-light border">
<div class="brand-logo">
<a href="https://dev.eluminate.in/#/"><img src="resources/images/eLuminateHeaderLogo.png" class="header-img-logo"></a>
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
<a href="#" class="nav-link px-4">
<span><i class="fas fa-mobile-alt px-3"></i>Download App</span>
</a>
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
<a class="dropdown-item" href="myprofile">Profile</a>
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
<a class="back-to-chapters" href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=1">
<i class="fa fa-arrow-left"></i>
<span>List of Chapters</span>
</a>
</div>
<div class="chapter-description-box px-3 pt-3 pb-3">
<h6>${courseName}</h6>
<small>${chapterName}</small>
</div>
<div class="chapter-content-box">
<small class="_heading">Chapter Content</small>
<c:set var="sno" value="1" /><c:set var="j" value= "1"></c:set>
<c:if test="${not empty attemptList}">
<c:forEach items="${attemptList}" var="ob" varStatus="loop">
<c:if test = "${ob.attemptId !=null }"> 

<c:forEach items="${ob.contentList}" var="attempt" >
<c:if test = "${attempt.contentType == 'VIDEO' || attempt.contentType == 'TEST'}"> 


<c:if test = "${id == j}">
<a class="act-div active" href="coursevideo?courseId=${courseId}&sectionId=${sectionId}&attemptId=${ob.attemptId}&contentId=${contentId}&id=${j}">

<i class="fas fa-book-open icon-active"></i>
<span class="act-name">Session ${j}</span>
<i class="fas fa-angle-right direction-arrow"></i></a>
</c:if>
<c:if test = "${id != j}"> <a class="act-div" href="coursevideo?courseId=${courseId}&sectionId=${sectionId}&attemptId=${ob.attemptId}&contentId=${contentId}&id=${j}">
<i class="fas fa-book-open"></i>
<span class="act-name">Session ${j}</span>
<i class="fas fa-angle-right direction-arrow"></i></a>
</c:if>
<c:set var="sno" value="2" />





<!-- <a class="act-div" href="#">
<i class="fas fa-book-open color-white"></i>
<span class="act-name">Act 2</span>
<i class="fas fa-angle-right direction-arrow"></i>
</a>
<a class="act-div" href="#">
<i class="fas fa-book-open color-white"></i>
<span class="act-name">Act 3</span>
<i class="fas fa-angle-right direction-arrow"></i>
</a>
--> 
</c:if> <c:set var="j" value="${j+1}"></c:set> 
</c:forEach>	
</c:if> 
</c:forEach>
</c:if> 
</div>
</div>
</div>
<!-- -----------Sidebar Code end Here------------- -->



<main class="col pl-2 pt-2 content-wrapper">
<nav aria-label="breadcrumb">
<ol class="breadcrumb">
<li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
<li class="breadcrumb-item"><a href="coursechapter?courseId=${courseId}&sectionId=${sectionId}">${courseName}</a></li>
<li class="breadcrumb-item active"><a href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=1">${chapterName}</a></li>
 <li class="breadcrumb-item active"><a href="">Session ${id}</a></li>
<!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
</ol>
</nav>
<div class="col-sm-12 mt-4 mb-4">
<div class="row bg-dark">
<div class="col-sm-1"></div>
<div class="col-sm-10">
<video width="100%" height="240" controls autoplay>
<source srcc="${videoUrl}"src="https://s3.us-east-1.amazonaws.com/ondemandtest-source-10o600jqb6ibb/VID-20160615-WA0000_2.mp4">
</video>
</div>
<div class="col-sm-1"></div>
</div>
</div>
<div class="col-sm-12 mb-4 pl-0 pr-0">
<div class="feedback">
<div class="feedback-container">
<span>
<h5 class="d-inline">Rate Feedback</h5>
<i class="far fa-frown px-2 fa-icon"></i>
<i class="far fa-laugh-beam px-2 fa-icon"></i>
<i class="far fa-grin-stars px-2 fa-icon"></i> 
</span>
</div>
</div>
</div>
</main>
</div>
</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
crossorigin="anonymous"></script>
</body>

</html>