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
<%@ include file="/pages/headerNew.jsp"%> 
<%@ include file="/pages/include-lms.jsp"%>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
        integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="<spring:url value='/resources/css/common.css'/>">
        <link rel="stylesheet" href="<spring:url value='/resources/css/style.css'/>">
        <link rel="stylesheet" href="<spring:url value='/resources/css/course_profile.css'/>">
        
    <title>eluminate</title>
    
    
</head>

<body class="p-0">
    <!--------------------- Navigation Bar Start ------------------------------------------------>
   
    <!--------------------------- Navigation Bar End ---------------------------------------->


    <!-------------------------My Profile Section Start----------------------------------->
    <section class="myprofile-group">
        <div class="container py-5">
            <h2 class="font-weight-bold pb-3">My Profile</h2>
            <div class="row myprofile-container">
                <div class="col-lg-12">
                    <div class="col-sm-2 float-left">
                        <div class="">
                           <a ><img src="resources/images/allimages/userimage.png" class="user-image py-5"></a> 
                        </div>
                    </div>
                    <div class="col-sm-10 float-right px-4">
                   
                        <table class="profile-table">
                            <h6 class="text-white pt-5">Last Updated : 03/03/2019</h6>
                            <tbody>
                                <tr>
                                <td>Name :</td>
                                <td>
                                    <h1 class="text-white">${userInfo.name}</h1>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Email ID :</td>
                                    <td>${userInfo.email}</td>
                                </tr>
                                <tr>
                                    <td>Phone No :</td>
                                    <td>${userInfo.mobile}</td>
                                </tr>
                                <tr>
                                    <td>City :</td>
                                    <td>${userInfo.city}</td>
                                </tr>
                            </tbody>
                        </table>
                        <button class="btn edit-profile-button font-weight-bolder text-white my-4 px-4 py-2">Edit
                            Profile</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!------------------------- My Profile Session End Here ----------------------------------->

    <!-------------------------Group3 Div Start Here------------------------------>
    <section class="progress-courses-section">
        <div class="container py-4">
            <h2 class="font-weight-bold pb-3 text-white">In Progress Courses</h2>
            <div class="row progress-course-container p-2">
                <div class="col-lg-12 p-0 progress-course">
                    <div class="col-sm-2 float-left p-0">
                        <div class="p-2">
                            <img src="resources/images/allimages/home_page1/imageindian_geography.png" class="user-image">
                        </div>
                    </div>
                    <div class="col-sm-10 float-right">
                        <div class="row">
                            <div class="col-sm-12 d-flex">
                                <div class="col-sm-6">
                                    <p class="h3 font-weight-bold">Indian Geography</p>
                                    <p class="h4 font-weight-bold">Session 1: Lesson 2</p>
                                </div>
                                <div class="col-sm-3">
                                    <h5 class="py-4"><span class="pr-2"><i class="fa fa-history"
                                                aria-hidden="true"></i></span>5th Match 19</h5>
                                </div>
                                <div class="col-sm-3">
                                    <h5 class="py-4"><span class="pr-2"><i class="fa fa-file ml-5"
                                                aria-hidden="true"></i></span>3rd March 19</h5>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-12 d-flex">
                                <div class="col-sm-6">
                                    <div class="container-fluid">
                                        <div class="row py-2 justify-content-around">
                                            <div class="col-md-3 col-sm-6">
                                                <div class="progress reddish">
                                                    <span class="progress-left">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <span class="progress-right">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <div class="progress-value">
                                                        <span>90%</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-3 col-sm-6">
                                                <div class="progress violet">
                                                    <span class="progress-left">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <span class="progress-right">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <div class="progress-value">75%</div>
                                                </div>
                                            </div>
                                            <div class="col-md-3 col-sm-6">
                                                <div class="progress blue">
                                                    <span class="progress-left">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <span class="progress-right">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <div class="progress-value">75%</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <h6 class="progress-status">PROGRESS STATUS</h6>
                                    <div class="progress2">
                                        <div class="progress-bar" role="progressbar" style="width: 100%"
                                            aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <button
                                        class="btn edit-profile-button font-weight-bolder text-white my-2 px-4">RESUME</button>
                                </div>
                                <div class="col-sm-6">


                                    <div class="container">
                                        <div class="row py-2 justify-content-around">
                                            <div class="col-md-3 col-sm-6">
                                                <div class="progress gray">
                                                    <span class="progress-left">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <span class="progress-right">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <div class="progress-value">0%</div>
                                                </div>
                                            </div>
                                            <div class="col-md-3 col-sm-6">
                                                <div class="progress gray">
                                                    <span class="progress-left">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <span class="progress-right">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <div class="progress-value">0%</div>
                                                </div>
                                            </div>
                                            <div class="col-md-3 col-sm-6">
                                                <div class="progress gray">
                                                    <span class="progress-left">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <span class="progress-right">
                                                        <span class="progress-bar"></span>
                                                    </span>
                                                    <div class="progress-value">0%</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <h6 class="progress-status">PROGRESS STATUS</h6>
                                    <div class="progress2">
                                        <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0"
                                            aria-valuemax="100"></div>
                                    </div>
                                    <button
                                        class="btn edit-profile-button2 font-weight-bolder text-white my-2 px-4">START
                                        NOW</button>
                                </div>
                            </div>
                        </div>

                        <!-- <button class="btn edit-profile-button font-weight-bolder text-white my-4 px-4 py-2">Edit Profile</button>   -->
                    </div>
                </div>


                <!-- <div class="container">
                    <h2 class="py-4 font-weight-bold">Some Recommended Courses</h2>
                    <div class="row">

                        <div class="col-sm-4 p-0">
                            <div class="card-body rounded border">
                                <div>
                                    <small class="font-weight-bold">Class X</small>
                                    <h5 class="font-weight-bold">History</h5>
                                    <img src="http://lorempixel.com/300/300/sports/" alt="Card image cap"
                                        class="card-image">
                                    <h5 class="font-weight-bold py-3">Indian History (Second Edition)</h5>
                                    <button type="button"
                                        class="btn subscribe-button text-white font-weight-bold px-4 py-2 mb-2">SUBSCRIBE</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 p-0">
                            <div class="card-body rounded border">
                                <div>
                                    <small class="font-weight-bold">Class X</small>
                                    <h5 class="font-weight-bold">History</h5>
                                    <img src="http://lorempixel.com/300/300/sports/" alt="Card image cap"
                                        class="card-image">
                                    <h5 class="font-weight-bold py-3">Indian History (Second Edition)</h5>
                                    <button type="button"
                                        class="btn subscribe-button text-white font-weight-bold px-4 py-2 mb-2">SUBSCRIBE</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> -->



            </div>

        </div>
    </section>
    <!-------------------------Group3 Div End Here ------------------------------>



    <!-------------------------Footer Start Here ------------------------------>
    <footer class="footer-container">
        <div class="container">
            <div class="row responsive-padding">
                <div class="col-sm-6">
                    <div class="brand-logo py-1">
                    	<a href="#/"><img class="img-logo" src="resources/images/footerlogo.png"></a>
                    </div>

                    <p class="text-white">We take our mission of increasing global access to quality education
                        seriously. we connect learners to the best universities and institutions from around the world.
                    </p>
                    <p class="text-white">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Labore totam
                        nihil enim unde officia esse dolor doloremque pariatur. Sint nisi illum error quod vero, iure
                        tempore maiores laudantium aspernatur dolores!</p>
                    <ul class="list-inline list-unstyled">
                        <li class="text-white list-inline-item pr-3 footer-link-border">Courses</li>
                        <li class="text-white list-inline-item pr-3 footer-link-border">About</li>
                        <li class="text-white list-inline-item">FAQs</li>
                    </ul>
                </div>
                <div class="col-sm-3 ml-auto">
                    <h5 class="text-light pt-2 pb-1 float-right">Subscribe Now</h5>
                    <div class="input-group">
                        <input type="text" class="form-control transparent-input" placeholder="Enter Email Address"
                            aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <span class="input-group-text send-span" id=""></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-------------------------Footer End Here ------------------------------>




    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>