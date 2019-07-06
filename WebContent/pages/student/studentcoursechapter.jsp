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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script> -->
<link rel="icon" href="resources/images/allimages/favicon.ico"
	type="image/x-icon" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>


<link rel="stylesheet"
	href="<spring:url value='/resources/css/course.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/newcommon.css'/>">
<title>eLuminate</title>
<style>



.box {
  width: 200px; height: 300px;
  position: relative;
  border: 1px solid #BBB;
  background: #EEE;
}
.ribbon {
    position: absolute;
    right: 0px;
    top: -5px;
    z-index: 1;
    overflow: hidden;
    width: 100%;
    height: 75px;
    text-align: right;
}
.ribbon span {
    font-size: 9px;
    font-weight: bold;
    color: #FFF;
    text-transform: uppercase;
    text-align: center;
    line-height: 25px;
    /* transform: rotate(45deg); */
    -webkit-transform: rotate(0deg);
    width: 85px;
    display: block;
    background: #79A70A;
    background: linear-gradient(#F70505 0%, red 100%);
    box-shadow: 0 3px 10px -5px rgba(0, 0, 0, 1);
    position: absolute;
    top: 16px;
    right: 5px;
}
.ribbon span::before {
  content: "";
  position: absolute; left: 0px; top: 100%;
  z-index: -1;
  /* border-left: 3px solid #8F0808;
  border-right: 3px solid transparent;
  border-bottom: 3px solid transparent;
  border-top: 3px solid #8F0808; */
}
.ribbon span::after {
  content: "";
  position: absolute; right: 0px; top: 100%;
  z-index: -1;
  /* border-left: 3px solid transparent;
  border-right: 3px solid #8F0808;
  border-bottom: 3px solid transparent;
  border-top: 3px solid #8F0808; */
}




/* Small devices (landscape phones, 576px and up) */
@media ( max-width : 575.98px) {
	#sidebar {
		display: none;
	}
	.breadcrumb {
		padding: 1rem 1rem;
	}
	.ribbon span {
    top: 16px;
    right: -10px;
    line-height: 17px;
    -webkit-transform: rotate(28deg);
}
	
}

/* Medium devices (tablets, 768px and up) */
@media ( min-width : 768px) { /* 	#sidebar{
	display:inline-block;
	} */
}

/* Large devices (desktops, 992px and up) */
@media ( min-width : 992px) { /* #sidebar{
	display:inline-block;
	}  */
}

/* Extra large devices (large desktops, 1200px and up) */
@media ( min-width : 1200px) { /* 	#sidebar{
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
                 <!-- <li class="nav-item">
                    <a href="comingsoon" class="nav-link px-4" data-toggle="modal" data-target="#comingSoonModal">
                        <span><i class="fas fa-mobile-alt fa-icon px-3"></i>Download App</span>
                    </a>
                </li> -->
                <c:if test="${flag eq false}">
                <li class="nav-item"><a
						href="https://qa.eluminate.in/all-courses" class="nav-link px-4">
							<!--  <span><i
								class="fas fa-mobile-alt px-3"></i>PreBook This Course</span> -->
							<button class="btn subscribe-button text-white font-weight-bold"
								type="button">Subscribe NOW!</button>
					</a></li>
					</c:if>
				 
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
                           <form method="post" action="myprofile" id="myprofile${userInfo.userId}">
		                      <input type="hidden" name="userId" value="${userInfo.userId}""/>
						      </form>
					 
					
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
					<div class="course-list-div">
						<small class="course-div-heading">COURSES</small>
						<c:forEach items="${courses}" var="course">
							<c:if test="${courseId==course.courseId}">
								<div class="course-div-margin course-main-div">
								 <form id="newdashboardform1${course.courseId}" action="coursechapter" method="post">
								    <input type="hidden" name="courseId" value="${course.courseId}"/>
							 	 </form>
							     <a class="active course-div courses-div flex_alignMiddle" href="javascript:void(0)" onclick="document.getElementById('newdashboardform1${course.courseId}').submit(); return false;"/>
								
							<%-- 		<a class="active course-div courses-div flex_alignMiddle"
										href="coursechapter?courseId=${course.courseId}"> --%>

										<div class="icon-div">
											<i class="fas fa-${course.courseIcon}"></i>
										</div>
										<div class="course-name">${course.courseName}</div>
									</a>
								</div>
							</c:if>
							<c:if test="${courseId!=course.courseId}">
								<div class="course-div-margin course-main-div">
								  <form id="newdashboardform2${course.courseId}" action="coursechapter" method="post">
								  <input type="hidden" name="courseId" value="${course.courseId}"/>
							 	 </form>
							 <a class="course-div courses-div flex_alignMiddle" href="javascript:void(0)" onclick="document.getElementById('newdashboardform2${course.courseId}').submit(); return false;"/>
						<%-- 			<a class="course-div courses-div flex_alignMiddle"
										href="coursechapter?courseId=${course.courseId}"> --%>

										<div class="icon-div">
											<i class="fas fa-${course.courseIcon}"></i>
										</div>
										<div class="course-name">${course.courseName}</div>
									</a>
								</div>
							</c:if>
						</c:forEach>
					</div>
					<!-- <div class="course-list-div">
						<small class="course-div-heading">My Study Box</small>
						<div class="course-div-margin course-main-div">
							<a class="course-div flex_alignMiddle" href="comingsoon">
								<div class="icon-div">
									<i class="fas fa-clipboard-list"></i>
								</div>
								<div class="course-name">Practise Tests</div>
							</a> <a class="course-div flex_alignMiddle" href="comingsoon">
								<div class="icon-div">
									<i class="fas fa-bookmark"></i>
								</div>
								<div class="course-name">Bookmarks</div>
							</a> <a class="course-div flex_alignMiddle" href="comingsoon">
								<div class="icon-div">
									<i class="fas fa-comment-alt"></i>
								</div>
								<div class="course-name">Doubts</div>
								
							</a>
						</div>
					</div> -->
				</div>
			</div>
			<!-- -----------Sidebar Code end Here------------- -->



			<main class="col pl-2 pt-2 page-content-wrapper"
				style="background: #6197dc!important;min-height: 150vh;">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="studentdashboard"><i
							class="fas fa-home"></i></a></li>
					<!-- <li class="breadcrumb-item"><a href="#">Dashboard</a></li> -->
					<li class="breadcrumb-item active" aria-current="courses"><a
						href="" style="font-weight:bold;text-decoration: underline;">${courseName}</a></li>
				</ol>
			</nav>
			<div class="main-right-div">
				<div class="row">
					<div class="col-sm-12">
						<h1 class="course-heading" style="color:white;opacity:1;font-size:25px;">${courseName}</h1>
						<!--  <div class="bg-white progress-box">
                                <h2 style="padding: 0px 20px 10px 0px;font-weight: 500;font-size: 22px;">Overall Progress</h2>
                                <div class="row">
                                    <div class="col-sm-8 align-items-center">
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" aria-valuenow="75" aria-valuemin="0"
                                                aria-valuemax="100">
                                            </div>
                                           
                                        </div>
                                        <p class="bar-status">Completed <span class="percentage-color">70%</span></p>
                                    </div>
                                   <a href="coursevideo?courseId=73&sectionId=151&attemptId=1&contentId=104" style="text-decoration:none;">  <div class="col-sm-4">
                                       <button class="btn continue-learning-button">watch video</button>
                                    </div></a>

                                </div>
                               
                               
                            </div> -->
					</div>
				</div>
				<!------------------------------------------------------canvas bar starts here---------------------------------------------------------->
			 <div style="border:1px solid #000; background:white;" class="mb-5">
				<canvas id="courseChart" style="width:100%;height:300px;" ></canvas>
				</div>



				<!---------------------------------------------------------------Chart ends here----------------------------------------------------------->
				
<%-- 				<div class="col-sm-12 mb-4 pl-0 pr-0 rounded">
					<div class="card">
						<div class="card-body stats-container">
							<!-- <div>
                                    <div class="stats-div">Stats</div>
                                </div> -->
							<div class="stat-content">
								<div class="stat">
									<i class="fas fa-video stat-icon"></i>
									<div class="ml-20 icon-data">
										<div class="stat-value">${detail.sessionView}</div>
										<div class="stat-data">Video View</div>
									</div>
								</div>
								<div class="stat">
									<i class="far fa-clock stat-icon"></i>
									<div class="icon-data">
										<div class="stat-value">${detail.min}:${detail.second}</div>
										<div class="stat-data">Time spent</div>
									</div>
								</div>
								<div class="stat">
									<i class="fas fa-chart-line stat-icon"></i>
									<div class="icon-data">
										<div class="stat-value">${performancetillnow}%</div>
										<div class="stat-data">Performance till date</div>
									</div>
								</div>

								<a
									href="coursevideo?courseId=73&sectionId=151&attemptId=1&contentId=104"
									style="text-decoration: none;">
									<div class="col-sm-4">
										<!-- <button class="btn continue-learning-button">Resume Learning</button> -->
									</div>
								</a>

							</div>

						</div>
					</div>
				</div> --%>


				<!-- Line Charts Code comes here -->
				<div class="row mt-5">
					<div class="col-sm-12">
						<h3 class="course-heading sub-course-heading"style="color:white;opacity:1;font-size:25px";>Chapters</h3>
					</div>
				</div>


				<%--                     <div class="row my-5">
                        <div class="col-sm-12">
                            <div class="accordion" id="accordionExample">
                            
                             <c:if test="${not empty course}">
                            <c:forEach  items="${course}" var="ob" varStatus="loop">
                                <a class="chapter-div" href="coursehistory?courseId=${courseId}&sectionId=${ob.sectionId}&id=${sessionId}"> 
                                    <div class="card mb-3 chapter-div">
                                        <div class="card-header" id="headingOne" data-toggle="collapse" data-target="#collapseOne"
                                            aria-expanded="true" aria-controls="collapseOne">
                                            <h5 class="mb-0">${ob.sectionName}</h5>
                                        </div>
                                    </div>
                                </a>
                             
                                </c:forEach></c:if>
                                
                                
                            </div>
                            
                            
                        </div>
                         <div class="col-sm-3">
                         </div>
                         <div class="col-sm-4">
                         <a href="coursevideo?courseId=73&sectionId=151&attemptId=1&contentId=104" style="text-decoration:none;">  <div class="col-sm-4">
                                      
                                    </div></a></div>
                                    <div class="col-sm-3">
                         </div>
                                    
                    </div> --%>
                     <input type="hidden" id="overall" value="${overallchartdata}">
                <c:forEach var="chart" items="${chartdata}" varStatus="loop">
				<input type="hidden" id="size" value="${chartdata.size()}">
				<input type="hidden" id="chartsname${loop.index}" value="${chart.sectionName}">
				<input type="hidden" id="charts${status.index}" value="${chart.marks}">
				<input type="hidden" id="size" value="${chart.marks.size()}">
						<c:forEach var="chartdatas" items="${chart.marks}" varStatus="status">
					<%-- <input type="hidden" id="charts${status.index}" value="${chartdatas}charts${status.index}${loop.index}"> --%>
					<input type="hidden" id="charts${loop.index}${status.index}" value="${chartdatas}">
						<input type="hidden" id="chart" value="${chartdatas}charts${loop.index}${status.index}">
					</c:forEach>
				</c:forEach>



				<div class="row mb-3">
					<div class="col-sm-12">
						<div class="accordion" id="accordionExample">
							<c:if test="${not empty course}">
								<c:forEach items="${course}" var="ob" varStatus="loop">
								<c:if test="${ob.isChapterTest==0 && ob.isPractice==0 }">
								 <form id="newdashboardform${ob.sectionId}" action="newdashboard" method="post">
								  <input type="hidden" name="courseId" value="${courseId}"/>
								  <input type="hidden" name="sectionId" value="${ob.sectionId}"/>
								  <input type="hidden" name="sessionId" value="${sessionId}"/>
								 </form><%-- 
									<a class="chapter-div"
<<<<<<< HEAD
										href="newdashboard?courseId=${courseId}&sectionId=${ob.sectionId}&sectionId=${sessionId}"> --%>
									 <a class="chapter-div" href="javascript:void(0)" onclick="document.getElementById('newdashboardform${ob.sectionId}').submit(); return false;">
									
										<div class="card chapter-div">
											<div class="card-header" id="headingOne"
												data-toggle="collapse" data-target="#collapseOne"
												aria-expanded="true" aria-controls="collapseOne">
												<h5 class="mb-0">${ob.sectionName}</h5>
											</div>
										</div>
									</a>
									</c:if>
								</c:forEach>
								</c:if>
								
								
							
						</div>
					</div>
					<!-- <div class="col-sm-3"></div>
<div class="col-sm-4">
<a href="coursevideo?courseId=73&sectionId=151&attemptId=1&contentId=104" style="text-decoration:none;"> ssfsf
<div class="col-sm-4"></div>
</a>
</div>
<div class="col-sm-3"></div> -->
				</div>
				<c:if test="${courseId==3}">

					<div class="" id="historyContent">
						<!-- <div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded chapter-disable">
										<div class="card-header">
											<h5 class="mb-0 text-white">The Nationalist Movement in Indo-China</h5>
										</div>
									</div>
								</div>
							</div>
						</div> -->
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<!-- div class="box"> -->
										   <div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
										<!-- </div> -->
											<h5 class="mb-0">Nationalism in India</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">The Making of a Global World</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">The Age of Industrialisation</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded chapter-disable">
										<div class="card-header">
											<h5 class="mb-0 text-white">Work, Life and Leisure</h5>
										</div>
									</div>
								</div>
							</div>
						</div> -->

						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Print Culture and the Modern World</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded chapter-disable">
										<div class="card-header">
											<h5 class="mb-0 text-white">Novels, Society and History</h5>
										</div>
									</div>
								</div>
							</div>
						</div> -->
					</div>
				</c:if>
				<c:if test="${courseId==5}">

					<div class="" id="geographyContent">
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Forest and Wildlife Resources</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Water Resources</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Agriculture</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Minerals and Energy Resources</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Manufacturing Industries</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row my-4">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div rounded">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Lifelines of National Economy</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>




			</div>
			</main>
		</div>
	</div>
	

   <script>
		var ctx = document.getElementById("courseChart");
	debugger;
	Chart.defaults.global.defaultFontFamily = "Lato";
	Chart.defaults.global.defaultFontSize = 16;
	var labels = [];
	var i;
	var allChapter=['ressdsds'];
	var values = [];
	var attempt1 = [];
	var attempt2 = [];
	var labelss=[];
	var chaptertestattempt1 = [];
	var chaptertestattempt2 = [];
	var overall=[];
	var count;
	for (i = 0; i < $('#size').val(); i++){
		if ($('#chartsname' + i).val() != 'flag'){
			count++;
		}
		}
	
	 for (i = 0; i < $('#size').val(); i++) {
		 if($('#charts' + i + '4').val() == 1){
		for (j = 0; j <= 4; j++) {
		if (j == 0 && $('#charts' + i + '4').val() == 0){
				attempt1.push($('#charts0' + j).val());
			}
			if (j == 1 && $('#charts' + i + '4').val() == 0){
				attempt2.push($('#charts0' + j).val());
			}
			if (j == 2 && $('#charts' + i + '4').val() == 1){
				chaptertestattempt1.push($('#charts1' + j).val());
			}
			if (j == 3 && $('#charts' + i + '4').val() == 1){
				chaptertestattempt2.push($('#charts1' + j).val());
			}
			if (j == 4 && $('#charts' + i + '4').val() == 0){
				overall.push(0);
			}
		
		}
		 }
		 if($('#charts' + i + '4').val() == 0)
			 {
			 for (j = 0; j <= 4; j++) {
					if (j == 0 && $('#charts' + i + '4').val() == 0){
							attempt1.push($('#charts0' + j).val());
						}
						if (j == 1 && $('#charts' + i + '4').val() == 0){
							attempt2.push($('#charts0' + j).val());
						}
						if (j == 2 && $('#charts' + i + '4').val() == 1){
							chaptertestattempt1.push(0);
						}
						if (j == 3 && $('#charts' + i + '4').val() == 1){
							chaptertestattempt2.push(0);
						}
						if (j == 4 && $('#charts' + i + '4').val() == 0){
							overall.push(0);
						}
					
					}
			 }
		if ($('#chartsname' + i).val() != 'flag'){
			labels.push($('#chartsname' + i).val());
			labelss.push('Ch'+(i+1));
		}
		
	} 
	 attempt1.push(0);
	 attempt2.push(0);
	 chaptertestattempt1.push(0);
	 chaptertestattempt2.push(0);
	 overall.push($('#overall').val());
	 labelss.push('Overall');
	 
	
	var attempt1 = {
	  label: 'Attempt1',
	  data: attempt1,
	  backgroundColor: 'rgba(100, 99, 132, 255)',
	  borderWidth: 0,
	};

	var attempt2 = {
	  label: 'Attempt2',
	  data: attempt2,
	  backgroundColor: 'rgba(150, 132, 156, 255)',
	  borderWidth: 0,
	};
	var chaptertest1 = {
			  label: 'Chapter test attempt1',
			  data: chaptertestattempt1,
			  backgroundColor: 'rgba(99, 100, 100, 255)',
			  borderWidth: 0,
			};
	var chaptertest2 = {
			  label: 'Chapter test attempt2',
			  data: chaptertestattempt2,
			  backgroundColor: 'rgba(55, 132, 100, 255)',
			  borderWidth: 0,
			};
	var overall = {
			  label:'Overall',
			  data: overall,
			  backgroundColor: 'rgba(100, 255, 100, 255)',
			  borderWidth: 0,
			};
	
	var planetData = {
	  labels: labelss,
	  datasets: [attempt1, attempt2,chaptertest1,chaptertest2,overall]
	};

	var chartOptions = {
			tooltips: {
				callbacks: {
					title: function () {
						return "";
					},
					label: function (t, e) {
						debugger;
						var i = e.datasets[t.datasetIndex].label || "",
							n = e.datasets[t.datasetIndex].data[t.index];
						for(j=1;j<=5;j++){
						if(t.xLabel=='Ch'+j)
						return labels[j-1] + ": (" + t.yLabel+ "%)"
						}
						if(t.xLabel=='Overall')
							return 'overall' + ": (" + t.yLabel+ "%)"
							
					} 
				}
			},	
	  scales: {
	    xAxes: [{
	      barPercentage: 1,
	      categoryPercentage:0.6,
	      
	   
	    }],
	    yAxes : [ {
			ticks : {
				beginAtZero : true,
				steps : 10,
				stepValue : 5,
				max : 100
			},
			scaleLabel : {
				display : true,
				labelString : "Marks(%)",
				fontColor : "black"
			}
		} ] 
	  }
	  
	};

	var barChart = new Chart(ctx, {
		
	  type: 'bar',
	  data: planetData,
	  options:chartOptions
	});
	function newdatacall(){
		debugger;
	      
		
		barChart.update();
		} 
	</script> 


	<%-- </c:if> --%>


<%-- 
	<c:if test="${chartdata[4]==0.0}">
		<script>
			console.log('${chartdata}');
			$('.progress-bar').each(function() {
				var valueNow = $(this).attr('aria-valuenow');
				$(this).animate({
					width : valueNow + '%',
					percent : 100
				}, {
					progress : function(a, p, n) {
						$(this).css('width', (valueNow * p + '%'));
						// $(this).css('width', (valueNow * p + '%')).html(Math.round(valueNow * p) + '%');
					}
				});
			});

			var ctx = document.getElementById('canvas').getContext('2d');
			var myChart = new Chart(ctx, {
				type : 'bar',
				data : {

					labels : [ 'Attempt 1', 'Attempt2' ],
					datasets : [ {
						label : '',
						data : [ $('#charts0').val(), $('#charts1').val() ],
						backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
								'rgba(54, 162, 235, 0.2)',
								'rgba(255, 206, 86, 0.2)',
								'rgba(75, 192, 192, 0.2)',
								'rgba(153, 102, 255, 0.2)',
								'rgba(255, 159, 64, 0.2)' ],
						borderColor : [ 'rgba(255, 99, 132, 1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)',
								'rgba(255, 159, 64, 1)' ],
						borderWidth : 2
					} ]
				},
				options : {
					scales : {
						yAxes : [ {
							ticks : {
								beginAtZero : false,
								steps : 10,
								stepValue : 5,
								max : 100
							}
						} ]
					}
				}
			});
		</script>
	</c:if> --%>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>

</html>

<script>
	
</script>









