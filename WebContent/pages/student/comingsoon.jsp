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
        <link rel="stylesheet" href="<spring:url value='/resources/css/newcommon.css'/>">
        <link rel="stylesheet" href="<spring:url value='/resources/css/comingsoon.css'/>">
            <link rel="icon" href="resources/images/allimages/favicon.ico" type="image/x-icon" />
   <!--  <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/coming-soon.css"> -->
    <title>eLuminate</title>
</head>

<body>
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
                <li class="nav-item">
                    <a href="#" class="nav-link px-4">
                        <span><i class="fas fa-mobile-alt fa-icon px-3"></i>Download App</span>
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

    <div class="container-fluid">
        <div class="row">
<!--             <div class="col-md-3 col-xs-12 pl-0 pr-0" id="sidebar">
                <div class="list-group sidebar text-center text-md-left">
                    <div class="course-list-div">
                        <small class="course-div-heading">COURSES</small>
                        <div class="course-div-margin course-main-div">
                            <a class="course-div flex_alignMiddle active non-active" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-mosque"></i>
                                </div>
                                <div class="course-name">History</div>
                            </a>
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-globe"></i>
                                </div>
                                <div class="course-name">Geography</div>
                            </a>
                        </div>
                    </div>
                    <div class="course-list-div">
                        <small class="course-div-heading">My Study Box</small>
                        <div class="course-div-margin course-main-div">
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-clipboard-list"></i>
                                </div>
                                <div class="course-name">Practise Tests</div>
                            </a>
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-bookmark"></i>
                                </div>
                                <div class="course-name">Bookmarks</div>
                            </a>
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-comment-alt"></i>
                                </div>
                                <div class="course-name">Doubts</div>
                            </a>
                        </div>
                    </div>
                </div>
            </div> -->

            <!-- Page Content -->
            <div class="col page-content-wrapper">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
                        
                        <!-- <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li> -->
                    </ol>
                </nav>
                <div class="comingSoon-main-div">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 image-div">
                           <img class="smile-img" src="resources/images/smile.png" />
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <h1 class="comingSoon-heading size-50">Your email has been validated successfully</h1>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                           <a href="https://dev.eluminate.in/" class="comingSoon-heading"><h4>Click here to login again</h4></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

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