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
    right: 10px;
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
@media (max-width: 575.98px) {
	#sidebar{
	display:none;
	}	
	.breadcrumb{
	padding:1rem 1rem;
	}
	.media-video {
    padding: 0rem 0rem 0.80rem 0rem;
}
}
    </style>
</head>

<body>
    <!--------------------- Navigation Bar Start ------------------------------------------------>
    <nav class="navbar navbar-expand-md sticky-top navbar-light border">
        <div class="brand-logo">
            <a href="https://eluminate.in/#/"><img src="resources/images/eLuminateHeaderLogo.png" class="header-img-logo"></a>
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
                        <a class="back-to-chapters" href="coursechapter?courseId=${courseId}&sectionId=${sectionId}">
                            <i class="fa fa-arrow-left"></i>
                            <span>List of Chapters </a></span>
                        </a>
                    </div>
                    <div class="chapter-description-box px-3 pt-3 pb-3">
                        <h6>${course.courseName}</h6>
                        <small>${section.sectionName}</small>
                    </div>
<%--                     <div class="chapter-content-box">
                        <small class="_heading">Chapter Content</small>
                        <c:set var="sno" value="0" />
                              <c:if test="${not empty section.attemptList}">
                                  <c:set var="j" value="0"></c:set>
								   <c:forEach items="${section.attemptList}" var="attempt" varStatus="loop1">
								       <c:if test = "${attempt.attemptId == 1 }">  
								     	<c:forEach items="${attempt.sessionList}" var="contentSession" varStatus="loop">
								     	 <c:if test="${contentSession.id == id}">
								     	     <a class="act-div <c:if test="${contentSession.id == id}"></c:if>" href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=${contentSession.id}">
					                            <i class="fas fa-book-open"></i>
					                            <span class="act-name">${contentSession.name}</span>
					                            <i class="fas fa-angle-right direction-arrow"></i>
					                        </a>
								     	 </c:if>
								     	    
								     	   
								     	  <c:if test="${contentSession.id != id}">   
								     	   <c:if test="${isStatus[j]==1}">
								     	   <a class="act-div"  href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=${contentSession.id}">
					                            <i class="fas fa-book-open"></i>
					                            <span class="act-name">${contentSession.name}</span>
					                            <i class="fas fa-angle-right direction-arrow"></i>
					                        </a>
								     	     
								     	    </c:if>
								     	   
								     	   <c:if test="${isStatus[j]==0}">
								     	   <a class="act-div" style="pointer-events:none" href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=${contentSession.id}">
					                            <i class="fas fa-book-open"></i>
					                            <span class="act-name">${contentSession.name}</span>
					                            <i class="fas fa-angle-right direction-arrow"></i>
					                        </a>
								     	     
								     	    </c:if>
								     	   <c:if test="${ empty isStatus[j]}">
								     	   <a class="act-div" style="pointer-events:none" href="coursehistory?courseId=${courseId}&sectionId=${sectionId}&id=${contentSession.id}">
					                            <i class="fas fa-book-open"></i>
					                            <span class="act-name">${contentSession.name}</span>
					                            <i class="fas fa-angle-right direction-arrow"></i>
					                        </a>
								     	     
								     	    </c:if>
								     	   </c:if>
								     	     
					                        
					                        
					                        <c:set var="j" value="${j+1}"></c:set>
									</c:forEach>	
								</c:if>  
								</c:forEach>
								</c:if>                        
                       
                    </div> --%>
                </div>
            </div>
            <!-- -----------Sidebar Code end Here------------- -->
<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>


            <main class="col pl-2 pt-2 content-wrapper" style="min-height: 101vh;">

                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="coursechapter?courseId=${courseId}&sectionId=${sectionId}">${course.courseName}</a></li>
                        <li class="breadcrumb-item active" aria-current="courses"><a href="javascript:void(0);">${section.sectionName}</a></li>
                    </ol>
                </nav>
                <div class="main-right-div">
                    <div class="row my-2">
                        <div class="col-sm-12">
                            <h1 class="course-heading">${course.courseName}</h1>
                            <p class="sub-course-heading">${section.sectionName}</p>
                             <div class="bg-white progress-box">
                                <h2 style="padding: 0px 20px 10px 0px;font-weight: 500;font-size: 22px;">Chapter Progress</h2>
                                <div class="row">
                                   <!--  <div class="col-sm-8 align-items-center">
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" aria-valuenow="75" aria-valuemin="0"
                                                aria-valuemax="100">
                                            </div>
                                           
                                        </div>
                                        <p class="bar-status">Completed <span class="percentage-color">70%</span></p>
                                    </div>
                                    -->

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
								<div class="col-sm-4">
                                 <%--  <a hreff="coursevideo?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attemptList[0].attemptId }&contentId=${attemptList[0].contentList[0].contentId}&id=1" style="text-decoration:none;">      <button class="btn continue-learning-button">watch video</button> --%>
                                 </a>   </div>
                                </div>
                               
                               
                            </div>
                        </div>
                    </div>
                   <!--  <div class="col-sm-12 mb-5 pl-0 pr-0">
                        <div class="card">
                            <div class="card-body">
                                <div>
                                    <div class="stats-div">Stats</div>
                                </div>
                                <div class="stat-content">
                                    <div class="stat">
                                        <i class="fas fa-bolt stat-icon"></i>
                                        <div class="ml-20 icon-data">
                                            <div class="stat-value">0</div>
                                            <div class="stat-data">Questions/Hour</div>
                                        </div>
                                    </div>
                                    <div class="stat">
                                        <i class="fab fa-squarespace stat-icon"></i>
                                        <div class="icon-data">
                                            <div class="stat-value">0%</div>
                                            <div class="stat-data">Accuracy</div>
                                        </div>
                                    </div>
                                    <div class="stat">
                                        <i class="fas fa-tasks stat-icon"></i>
                                        <div class="icon-data">
                                            <div class="stat-value">0</div>
                                            <div class="stat-data">Recent Activities</div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
 -->



                    <!-- Line Charts Code comes here -->
                    <!-- <div class="row mt-5 mb-5">
                        <div class="col-sm-12">
                            <div id="chartContainer" style="height: 300px; width: 100%;"></div>
                        </div>
                    </div> -->



                    <div class="row my-5 mt-5">
                        <div class="col-sm-12">
                            <div class="accordion" id="accordionExample">
                                        <c:set var="i" value="0"/>
                                         <c:if test="${not empty section.attemptList}">                                                
											  <c:forEach items="${section.attemptList}" var="attempt"  >
													<c:if test = "${attempt.attemptId == 1}">
													<c:forEach items="${attempt.sessionList}" var="contentSession">
													     <c:if test="${contentSession.id == id}">
													       <div class="card mb-3">
							                                    <div class="card-header" id="headingOne" data-toggle="collapse" data-target="#collapseOne${contentSession.id}" aria-expanded="true" aria-controls="collapseOne">
							                                        <h5 class="mb-0">${contentSession.name}</h5>
							                                    </div>
							                                    <div id="collapseOne${contentSession.id}" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
							                                          <div class="card-body">
							                                                
							                                                  <c:forEach items="${contentSession.contentList}" var="content">
							                                                  
							                                                  <c:if test="${content.contentType == 'VIDEO'}">
							                                                      <p><span><i class="fas fa-play-circle pr-2"></i></span><a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a></p>
							                                                  </c:if>
							                                                  <c:if test="${content.contentType == 'TEST'}">
							                                                      <p class="m-0"><span><i class="fas fa-clipboard pr-2"></i></span>
							                                                       <c:if test="${isStatus[i]==1}">
							                                                      <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a>
							                                                       </c:if>
							                                                       <c:if test="${isStatus[i]==0}">
							                                                      <a  style="pointer-events:none" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a>
							                                                       </c:if>
							                                                         <c:if test="${ empty isStatus[i] }">
							                                                      <a  style="pointer-events:none" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a>
							                                                       </c:if>
							                                                      <!-- <div class="dropdown-divider mb-3"></div> -->
							                                                  </c:if>
							                                                    
							                                               </c:forEach>
													                   </div>
							                                    </div>
							                               </div>
													     </c:if>
													     <c:if test="${contentSession.id != id}">
													       <div class="card mb-3">
													              <c:if test="${isStatus[i]==1}">
							                                          <div class="card-header" id="headingOne"  data-toggle="collapse" data-target="#collapseOne${contentSession.id}" aria-expanded="true" aria-controls="collapseOne">
							                                          <h5 class="mb-0">${contentSession.name}</h5>
							                                           </div>
							                                      </c:if>
							                                       <c:if test="${isStatus[i]==0}">
							                                             <div class="card-header" id="headingOne" style="pointer-events:none" data-toggle="collapse" data-target="#collapseOne${contentSession.id}" aria-expanded="true" aria-controls="collapseOne">
							                                             <h5 class="mb-0">${contentSession.name}</h5>
							                                            </div>
							                                       </c:if>
							                                        <c:if test="${ empty isStatus[i] }">
							                                               <div class="card-header" id="headingOne" style="pointer-events:none" data-toggle="collapse" data-target="#collapseOne${contentSession.id}" aria-expanded="true" aria-controls="collapseOne">
							                                               <h5 class="mb-0">${contentSession.name}</h5>
							                                               </div>
							                                        </c:if>
							                                    
							                                    <div id="collapseOne${contentSession.id}" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
							                                          <div class="card-body">
							                                               <c:forEach items="${contentSession.contentList}" var="content">
							                                                  <c:if test="${content.contentType == 'VIDEO'}">
							                                                      <p><span><i class="fas fa-play-circle pr-2"></i></span><a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a></p>
							                                                  </c:if>
							                                                     <c:if test="${content.contentType == 'TEST'}">
							                                                      <p class="m-0"><span><i class="fas fa-clipboard pr-2"></i></span>
							                                                       <c:if test="${isStatus[i]==1}">
							                                                      <a href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a>
							                                                       </c:if>
							                                                       <c:if test="${isStatus[i]==0}">
							                                                      <a  style="pointer-events:none" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a>
							                                                       </c:if>
							                                                        <c:if test="${ empty isStatus[i] }">
							                                                      <a  style="pointer-events:none" href="viewcoursecontent?courseId=${courseId}&sectionId=${sectionId}&attemptId=${attempt.attemptId}&contentId=${content.contentId}&sessionId=${contentSession.id}">${content.contentName}</a>
							                                                       </c:if>
							                                                      <!--  <div class="dropdown-divider mb-3"></div> -->
							                                                  </c:if>
							                                               </c:forEach>
													                   </div>
							                                    </div>
							                               </div>
													     </c:if>
													     <c:set var="i" value="${i+1}"/>  
						                               </c:forEach>
						                               </c:if>
							                   </c:forEach>
							                   
							                      <c:if test="${courseId==3}">

					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-1">Session 5: Aftermath of Treaty of Vienna</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
				
					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 6: Unification  And Establishment of Italian Nation</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 7: Rise Of Romantic Nationalism</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 8 : Art : a form of Nationalism</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 9: Iron Chancellor : Otto Von Bismarck</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 10: Unification of German States</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
					<div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 11: The story of Great Britain</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
                            </div>
                            
                            
                            </c:if>    
                            
                                <c:if test="${courseId==5}">
                               
                                
                                <div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 3: Resource Planning for our better future</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>           
						
						            <div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 4: Land : Where we live and we die</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>   
						
						            <div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 5: Soil: Womb of our Nourishment</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>   
						
						            <div class="" id="historyContent">
						<div class="row my-3">
							<div class="col-sm-12">
								<div class="accordion" id="accordionExample">
									<div class="card chapter-div">
										<div class="card-header">
										<div class="ribbon">
										   	<span>Coming Soon</span>
										   </div>
											<h5 class="mb-0">Session 6: There is more to it</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>                           
                                           </c:if>
                                           </c:if>
                                        
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
    	width: valueNow + '%',
    	percent: 100
    	},
    	{
    	progress: function(a, p, n) {
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



<script>

    window.onload = function () {

        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            theme: "light2",
            title: {
                text: "My Performance"
            },
            axisY: {
                includeZero: false
            },
            data: [{
                type: "line",
                dataPoints: [
                    { y: 450 },
                    { y: 414 },
                    { y: 520 },
                    { y: 460 },
                    { y: 450 },
                    { y: 500 },
                    { y: 480 },
                    { y: 480 },
                    { y: 410 },
                    { y: 500 },
                    { y: 480 },
                    { y: 510 }
                ]
            }]
        });
        chart.render();

    }
    
</script>