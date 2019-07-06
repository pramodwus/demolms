<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="<spring:url value='/resources/css/common.css'/>">
        <link rel="stylesheet" href="<spring:url value='/resources/css/style.css'/>">
         
        <link rel="stylesheet" href="<spring:url value='/resources/css/course_history.css'/>">
        
<style>
.img-logo {
    width: 14rem;
}
.navbar-light .navbar-nav .nav-link {
    color: #060606;
    font-size: 1.50rem;
    font-weight: 600;
}
</style>

</head>
<!-- <section class="p-0">   
        <nav class="navbar navbar-expand-md sticky-top navbar-light bg-white p-0">
            <div class="container">
				<div class="brand-logo py-3">
                <a href="studentdashboard"><img src="resources/images/allimages/logo.png" class="img-logo"></a>
            </div>
            <button class="navbar-toggler" data-toggle="collapse" data-target="#eluminateNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="eluminateNavbar">
                <ul class="navbar-nav align-items-center ml-auto">
                	<li class="nav-item">
                        <a href="studentdashboard" class="nav-link px-3" active>Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a href="studentCourse?action=courseList" class="nav-link px-3">Courses</a>
                    </li>
                    
                    <li class="nav-item">
                        <a href="logout" class="nav-link px-3">Logout</a>
                    </li>
                    
                    
                </ul>
            </div>
            </div>
        </nav>          
          
  
    </section> -->

 <!--------------------- Navigation Bar Start ------------------------------------------------>

    <section class="p-0">
        <nav class="navbar navbar-expand-md sticky-top navbar-light bg-white">                
            <div class="brand-logo py-3">
                <img src="resources/images/eLuminateLogo.png" class="img-logo">
            </div>
            <a href="#sidebar" data-toggle="collapse" class="sidebar-toggle-icon">
                <i class="fas fa-bars"></i>
            </a>
            <button class="navbar-toggler" data-toggle="collapse" data-target="#eluminateNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="eluminateNavbar">
            <ul class="navbar-nav align-items-center ml-auto">
                	<li class="nav-item">
                        <a href="studentdashboard" class="nav-link px-3" active>Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a href="studentCourse?action=courseList" class="nav-link px-3">Courses</a>
                    </li>
                    
                    
                    <li class="nav-item">
                        <a href="#" class="nav-link px-3 font-weight-normal">Sushil</a>
                    </li>
                    <li>
                        <div class="dropdown">
                            <!-- <a href="#" class="nav-link px-3 font-weight-normal">Sushil</a> -->
                            <a href="#" class="avator dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown"
                                aria-haspopup="true" aria-expanded="false">
                                <img src="resources/images/allimages/userimage.png" class="user-image">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="#">Profile</a>
                                <a class="dropdown-item" href="#">Change Password</a>
                                <a class="dropdown-item" href="#">Logout</a>
                            </div>
                        </div>                       
                    </li>
                    
                    
                </ul>                     
            </div>
        </nav>

   </section>
</body>
</html>