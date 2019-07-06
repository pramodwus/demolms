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
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <link rel="stylesheet" href="css/newcommon.css">
    <link rel="stylesheet" href="css/assessment.css">
    <link rel="stylesheet"
	href="<spring:url value='/resources/css/newcommon.css'/>">
	<link rel="stylesheet"
	href="<spring:url value='/resources/css/assesment.css'/>">
    <title>eluminate</title>
</head>

<body>
    <!--------------------- Navigation Bar Start ------------------------------------------------>
    <nav class="navbar navbar-expand-md sticky-top navbar-light border">
        <div class="brand-logo">
            <a href="#"><img src="resources/images/eLuminateHeaderLogo.png" class="header-img-logo"></a>
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
                        <span><i class="fas fa-tablet-alt fa-icon px-3"></i>Download App</span>
                    </a>
                </li>
                <li class="nav-item">
                    <div class="dropdown">
                        <ul class="list-inline dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <li class="list-inline-item mr-0">
                                <a href="#" class="nav-link font-weight-bold text-dark">Sushil</a>
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
                        <a class="back-to-chapters" href="#">
                            <i class="fa fa-arrow-left"></i>
                            <span>List of Chapters</span>
                        </a>
                    </div>
                    <div class="chapter-description-box px-3 pt-3 pb-3">
                        <h6>History</h6>
                        <small>Chapter 1</small>
                    </div>
                    <div class="chapter-content-box">
                        <small class="_heading">Assessment 1</small>
                        <a class="act-div active" href="#">
                            <i class="fas fa-book-open icon-active"></i>
                            <span class="act-name">Question 1</span>
                            <i class="fas fa-angle-right direction-arrow"></i>
                        </a>
                        <a class="act-div" href="#">
                            <i class="fas fa-book-open color-white"></i>
                            <span class="act-name">Question 2</span>
                            <i class="fas fa-angle-right direction-arrow"></i>
                        </a>
                        <a class="act-div" href="#">
                            <i class="fas fa-book-open color-white"></i>
                            <span class="act-name">Question 3</span>
                            <i class="fas fa-angle-right direction-arrow"></i>
                        </a>
                        <a class="act-div" href="#">
                            <i class="fas fa-book-open color-white"></i>
                            <span class="act-name">Question 4</span>
                            <i class="fas fa-angle-right direction-arrow"></i>
                        </a>
                        <a class="act-div" href="#">
                            <i class="fas fa-book-open color-white"></i>
                            <span class="act-name">Question 5</span>
                            <i class="fas fa-angle-right direction-arrow"></i>
                        </a>
                    </div>
                </div>
            </div>
            <!-- -----------Sidebar Code end Here------------- -->



            <main class="col pt-2 content-wrapper">

                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                        <li class="breadcrumb-item active" aria-current="courses"><a href="#">Courses</a></li>
                    </ol>
                </nav>
                <div class="main-right-div">
                    <div class="row">
                        <div class="modal-main-div">
                            <div class="modal-div">
                                <div class="navigation-div flex_center margin-left">
                                    <i class="fas fa-arrow-right white-arrow"></i>
                                </div>
                                <div class="mt-50">
                                    <div class="modal-box-div question-modal">
                                        <div class="card card-main-div">
                                            <div class="card-div"></div>
                                            <div class="question-no-div">
                                                <div class="card-header">
                                                    <div>
                                                        <small class="question-no">
                                                            <strong>1</strong>
                                                        </small>
                                                    </div>
                                                </div>
                                                <div class="question-main-div">
                                                    <div class="question-div">
                                                        <div class="question">
                                                            <span>&nbsp;What among the following pushed up the demand
                                                                for water?&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="answer-div">
                                                <div class="option-main-div">
                                                    <div class="option-div">
                                                        <div class="option-no-div center">
                                                            <strong>A</strong>
                                                        </div>
                                                        <div class="option-answer-div">
                                                            <div>Rising population</div>
                                                        </div>
                                                        <div class="right-arrow"></div>
                                                    </div>
                                                </div>
                                                <div class="option-main-div">
                                                    <div class="option-div">
                                                        <div class="option-no-div center">
                                                            <strong>B</strong>
                                                        </div>
                                                        <div class="option-answer-div">
                                                            <div>Growing Indistrialization</div>
                                                        </div>
                                                        <div class="right-arrow"></div>
                                                    </div>
                                                </div>
                                                <div class="option-main-div">
                                                    <div class="option-div">
                                                        <div class="option-no-div center">
                                                            <strong>C</strong>
                                                        </div>
                                                        <div class="option-answer-div">
                                                            <div>Expanding Agriculture</div>
                                                        </div>
                                                        <div class="right-arrow"></div>
                                                    </div>
                                                </div>
                                                <div class="option-main-div">
                                                    <div class="option-div">
                                                        <div class="option-no-div center">
                                                            <strong>D</strong>
                                                        </div>
                                                        <div class="option-answer-div">
                                                            <div>All of these</div>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="navigation-div flex_center">
                                    <i class="fas fa-arrow-left white-arrow"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="button-main-div">
                <a href="assesmentreview"><div class="width-class button-css button-color button-shadow">Next</div></a>
                    
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