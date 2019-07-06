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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
        crossorigin="anonymous">
              
                <link rel="stylesheet" href="<spring:url value='/resources/css/page2.css'/>">
                <link rel="stylesheet" href="<spring:url value='/resources/css/studentdashboard.css'/>">
                 <link rel="stylesheet" href="<spring:url value='/resources/css/newcommon.css'/>">
                    
             
                
        <link rel="icon" href="resources/images/allimages/favicon.ico" type="image/x-icon" />
    <title>eLuminate</title>
     
        <style>
    .modal-header{
	border-bottom:none !important;
}
.comingSoon-heading{
    text-align: center !important;
    color: #fff !important;
}
.size-50{
    font-size: 30px !important;
}
.comingSoon-main-div{
    width: 100% !important;
    height: 100% !important;
    position: absolute !important;
    /* left: 50%; */
    top: 35% !important;
    /* right: 50%; */
    margin: -39px 0 0 -15px !important;
}
.image-div{
    text-align: center !important;
}
.smile-img{
    width: 130px !important;
}
.modal-header .close {
    padding: .1rem .5rem !important;
    margin: -2.3rem -1.8rem 0rem auto !important;
    font-size: 35px !important;
    background: #fff !important;
    border-radius: 50% !important;
    color: darkslategray!important;
}
.modal-background {
        background: #6197dc !important;
}
.close{
	opacity:.7  !important;
}
.close:not(:disabled):not(.disabled):hover {
    opacity: 1 !important;
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

	
<script>
   
 
    $( window ).on( "load", function() {
        console.log( "window loaded" );
    });
    </script>

<body>
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

    <div class="container-fluid">
        <div class="row">
			<div class="col-md-3 col-xs-12 pl-0 pr-0" id="sidebar">
				<div class="list-group sidebar text-center text-md-left">
					<div class="course-list-div">
						<small class="course-div-heading">COURSES</small>
						<c:forEach  items="${courses}" var="course">
							<div class="course-div-margin course-main-div">
							    <form id="newdashboardform${course.courseId}" action="coursechapter" method="post">
								  <input type="hidden" name="courseId" value="${course.courseId}"/>
			 				 	 </form>
							     <a class="course-div courses-div flex_alignMiddle" href="javascript:void(0)" onclick="document.getElementById('newdashboardform${course.courseId}').submit(); return false;"/>
							<%-- 	<a  class="course-div courses-div flex_alignMiddle"
									href="coursechapter?courseId=${course.courseId}"> --%>
           
									<div class="icon-div">
										<i class="fas fa-${course.courseIcon}"></i>
									</div>
									<div class="course-name">${course.courseName}</div>
								<!-- 	<a href="newdashboard">Click Here</a>
								 --></a>
							</div>
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

			<!-- Page Content -->
            <div class="col page-content-wrapper">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
								 
                        <li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
                        <!-- <li class="breadcrumb-item active"><a href="#">Dashboard</a></li>
                        <li class="breadcrumb-item " aria-current="courses"><a href="#">Courses</a></li> -->
                    </ol>
                </nav>





                <div class="course-preview">
                    <h1 class="course-heading" style="color:white;opacity:1;font-size:30px;">Courses</h1>
                    <c:forEach  items="${courses}" var="course">
                    <div class="tile tile-shadow tile-scale mb-5">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="media media-video">
                                 <c:if test="${course.courseId==5 }">
                                    <video width="100%" height="auto"  loop  autoplay>
                                        <source src="resources/images/slide2.mp4" type="video/mp4">
                                        Your browser does not support the video tag.
                                    </video>
                                     </c:if>
                                     <c:if test="${course.courseId==3 }">
                                     <video width="100%" height="auto"  loop  autoplay>
                                        <source src="resources/images/slide1.mp4" type="video/mp4">
                                        Your browser does not support the video tag.
                                    </video>
                                     
                                     </c:if>
                                     <c:if test="${course.courseId==8 }">
                                     <video width="100%" height="auto"  loop  autoplay>
                                        <source src="resources/images/slide1.mp4" type="video/mp4">
                                        Your browser does not support the video tag.
                                    </video>
                                     
                                     </c:if>
                                </div>
                            </div>
                            <div class="col-sm-8 media-content">
                                <div class="media-body">
                                    <!-- <h3><a href="coursehistory?courseId=73&sectionId=151">Indian History</a></h3> -->
                                    <h4>${course.courseName}</h4>
                                    <p>${course.courseDesc}
                                        </p>
                                        
                              <c:if test="${course.isKeepLearning==0}">
                                 <form id="newdashboardform${course.courseId}" action="coursechapter" method="post">
								  <input type="hidden" name="courseId" value="${course.courseId}"/>
			 				 	 </form>
                                 <a  href="javascript:void(0)" onclick="document.getElementById('newdashboardform${course.courseId}').submit(); return false;">   <button   target="_blank" class="themeBtn">Start Learning</button></a>
                                </c:if>
                                    <c:if test="${course.isKeepLearning==1}">
                                  <form id="newdashboardform${course.courseId}" action="coursechapter" method="post">
								  <input type="hidden" name="courseId" value="${course.courseId}"/>
			 				 	 </form>
                                 <a  href="javascript:void(0)" onclick="document.getElementById('newdashboardform${course.courseId}').submit(); return false;">   <button   target="_blank" class="themeBtn">Keep Learning</button></a>
                                </c:if>



                                </div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

                   <!--  <div class="tile tile-shadow tile-scale mt-5">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="media media-video">
                                    <video width="100%" height="auto" controls loop autoplay>
                                        <source src="resources/images/slide2.mp4" type="video/mp4">
                                        Your browser does not support the video tag.
                                    </video>
                                </div>
                            </div>
                            <div class="col-sm-8 media-content">
                                <div class="media-body">
                                	<h3>Indian Geography</h3>
                                    <h3><a href="coursehistory?courseId=74&sectionId=159">Indian Geography</a></h3>
                                    <p>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                        </p>
                                   <a href="coursechapter?courseId=74&sectionId=159">  <button   target="_blank" class="themeBtn">Start Learning</button></a>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    
                 <!--    <div class=""style="visibility: hidden;"> 
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="tile tile-shadow tile-scale mt-5">
                                    <div class="row">
                                        <div class="col">
                                            <h6 class="py-3 text-center font-weight-bold">RECENT ACTIVITIES</h6>
                                            <div class="dropdown-divider"></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col text-center">
                                            <h6 class="py-3">lorem ipsum Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                                sollicitudin. Cras purus odio,</h6>
                                            <button  href="#" target="_blank" class="themeBtn2">TAKE A TEST</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="tile tile-shadow tile-scale mt-5">
                                    <div class="row">
                                        <div class="col">
                                            <h6 class="py-3 text-center font-weight-bold">LEADERBOARD</h6>
                                            <div class="dropdown-divider"></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col text-center">
                                            <h6 class="py-3">lorem ipsum Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                                                sollicitudin. Cras purus odio,</h6>
                                            <button  href="#" target="_blank" class="themeBtn2">LET'S PRACTICE</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    
                </div>
            </div>
        </div>
    </div>
    
    
        
        
    


<!-- Modal -->
<div class="modal fade" id="comingSoonModal" tabindex="-1" role="dialog" aria-labelledby="comingSoonModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content modal-background">
      <div class="modal-header">
        
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
           <div class="row">
                        <div class="my-3 col-lg-12 col-md-12 col-sm-12 col-xs-12 image-div">
                           <img class="smile-img" src="resources/images/smile.png" />
                        </div>
                        <div class="mt-2 mb-0 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <h1 class="comingSoon-heading size-50">We are coming soon!!!</h1>
                        </div>
                        <div class="my-1 col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <h6 class="comingSoon-heading">Stay tuned for something amazing</h6>
                        </div>
                    </div>
        
      </div>
      
    </div>
  </div>
</div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
   
</body>
</html>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<script>
function createJson() {
	var firstName= $("#fname").val();
	var lastName= $("#lname").val();
	var mobileNo=$("#phone").val();
	var email=$("#email").val();

	var userData = {
		"first_name" : firstName,
		"last_name" : lastName,
		"contact_info": mobileNo,
		"email":email
	};
	return userData;
}

function save() {
	debugger;
	var flag = true;
	if (flag == true) {

   var updateData=createJson();
   var data=JSON.stringify(updateData);
    console.log(data);  
   $.ajax({
		    type :'POST',
		    crossDomain: true,
			dataType : 'json',
			contentType : "application/json",
		    url:'api/update',
		    headers: {
		    	 'Authorization': $("#token").val() 
		    	 },
		    data:data,
		    success: function (response) {
		    	
		    	 if(response.code == 200)
		    		 {
		    		 alert("Profile  Updated Sucessfully");
			    		
		    	  location.reload();
		    		 
		    		 }
		    	   else
		    		 {
		    		 alert("Profile Not Updated Sucessfully");
		    		 location.reload();
		    		 }
		   }
		}); 
	}
   
 }
 


</script>


