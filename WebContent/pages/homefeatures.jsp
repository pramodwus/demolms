<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>QBis: Smart Learning Platform</title>
<meta charset="utf-8">
<meta name="description" content="QBis is Smart Learning Platform (SLP) that can be used by enterprises and educational organizations to train internal as well as external audience. It allows you to create multimedia content with powerful assessment that supports several different types of questions such as essay type, objective, match the following etc."/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="<spring:url value='/resources/images/qbisfavicon.png' />">
<link rel="canonical" href="http://www.bellurbis.com/qbis.html">
<link rel="stylesheet"	href="resources/adminlte/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/extra.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css'/>">

</head>
<body class="signup--body signup-v3--body">
	<div class="page">
 <nav class="navbar navbar-inverse navbar-fixed-top navTheme" >
                <div class="container sp__width">
                <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                        </button>
                    <a class="navbar-brand" href="#myCarousel">
                            <img src="resources/adminlte/dist/img/imageshome/logo.png" height="50">
                        </a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                                <li><a href="/"><spring:message code="lbl.home"/></a></li>
                                <li><a href="homefeatures" class="nav__link"><span class="btn btn-primary"><spring:message code="lbl.features"/></span></a></li>                                    		
                                <li><a href="reguser"><spring:message code="lbl.singup"/></a></li>
                                <li><a href="loginathome"><spring:message code="lbl.login"/></a></li>
                                <li><a href="requestatdemo"><spring:message code="lbl.req.demo"/></a></li>
                        </ul>
                </div>
                </div>
        </nav>


        <section id="featurediv" class="features__section">
            <div class="container" style="margin-top: 105px;">
                <h1 class="heading extra">Key Features</h1>  
            
                <div class="row">
                    <div class="col-md-4">
                        <article class="features">
                            <h1><b>Personalized And Adaptive Learning</b></h1>
                            <p>QBis facilitates computer-based learning that adapts learning material in response to trainee's progress
                            and performance. QBis captures fine-grained data and drills down the analytics to enable human tailoring
                            responses,audio,video and many more.</p>
                        </article>
                        <article class="features">
                            <h1><b>Flexible Schedule</b></h1>
                            <p>offer customizable class Schedule and optionally assign due dates with automatic reminders.</p>
                        </article>
                    </div>
                    <div class="col-md-8">
                        <img src="resources/adminlte/dist/img/imageshome/feature1.png" alt="feature.png" class="card">
                    </div>
                </div>
                <div class="row">                    
                    <div class="col-md-8">
                        <img src="resources/adminlte/dist/img/imageshome/feature3.png" alt="feature.png" class="card">
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <h1><b>Gamification</b></h1>
                            <p>Fill your LMS to life with badged,points,levels,rewards and leaderboards. Customize your gamification to
                            the maximum through the gamification engine.</p>
                        </article>
                        <article class="features">
                            <h1><b>Various Question Format</b></h1>
                            <p>QBis is powered by an assessment engine that allow trainers to create more than 20  question types including like of subjective assessment, video response etc.</p>
                        </article>
                    </div>
                </div> 
            </div>
        </section>
        
        <section id="features_section" class="features__section" style="padding-top: 100px;">
            <div class="container width--980">
                <h1 class="heading text-center">More Features</h1>
                
                <div class="row" style="padding: 10px 0;">
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/dashboard.png">
                            <h1>Dashboard</h1>
                            <p>Our simple to use dashboard and the granular user account will allow your organization to structure accounts and manage all aspects of the app's life cycle, allowing you to focus on business objectives.</p>
                        </article>
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/questions.png">
                            <h1>Essay Type Questions</h1>
                            <p>Apart from creating objective type questions, the system allows you to create essay type questions for students. Our advanced AI engines grade the essay questions based on your deﬁned parameters.</p>
                        </article>
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/security.png">
                            <h1>Extensive app security</h1>
                            <p>Communications between the app and QBis backend are industry-standard secured via a HTTPS connection. Login credentials sent by users are encrypted. Sensitive data is stored on-device, in an encrypted container.</p>
                        </article>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/integrations.png">
                            <h1>Integrations</h1>
                            <p>Integrating the key social networks into your mobile app will give you access to unprecedented user engagement and rapid content distribution.</p>
                        </article>
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/personalize.png">
                            <h1>Personalize & Adapt</h1>
                            <p>The machine learning algorithms in QBis understand the progress of students and personalizes and adapts the learning experience accordingly.</p>
                        </article>
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/cloud.png">
                            <h1>Secure Cloud</h1>
                            <p>Qbis scalable and durable cloud infrastructure matched by our global Content Delivery Network offer a solid, reliable foundation for your apps.</p>
                        </article>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/pushnotify.png">
                            <h1>Smart push notiﬁcations</h1>
                            <p>Reminders, changes and updates in lesson plans, events and tests to create a seamless educational experience.</p>
                        </article>
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/curriculum.png">
                            <h1>Curriculum</h1>
                            <p>Create different types of courses to license and customize for their own learning community.</p>
                        </article>
                    </div>
                    <div class="col-md-4">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/reporting.png">
                            <h1>Reporting</h1>
                            <p>Get in-depth reporting on student’s progress and performance, with analysis on his strengths and weakness.</p>
                        </article>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-6">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/privatecourses.png">
                            <h1>Private Courses</h1>
                            <p>Offer private courses which will be visible in the catalog to the assigned members only.</p>
                        </article>
                    </div>
                    <div class="col-md-6">
                        <article class="features">
                            <img src="resources/adminlte/dist/img/imageshome/tracks.png">
                            <h1>Tracks</h1>
                            <p>Create tracks to guide students through series of courses and reward them after their completion.</p>
                        </article>
                    </div>
                </div>
            </div>
        </section>
   <%@ include file="footer.jsp"%>
	</div>
</body>
<!-- jQuery 2.1.4 js library -->
<script src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<!-- Bootstrap 3.3.5 js library -->
<script src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap.min.js'/>"></script>
<script	src="<spring:url value='/resources/adminlte/dist/js/main.js'/>"></script>
<%-- <script	src="<spring:url value='/resources/adminlte/dist/js/chitchat.js'/>"></script> --%>
<!-- Fresh chat -->   
<script	src="<spring:url value='/resources/adminlte/dist/js/freshchat.js'/>"></script>   
<script type="text/javascript">
        var messages = new Array();
        messages['msg.success.password.link'] = "<spring:message code='msg.success.password.link' javaScriptEscape='true' />";
        messages['msg.success.verification.mail'] = "<spring:message code='msg.success.verification.mail' javaScriptEscape='true' />";
        messages['msg.success.requestdemo'] = "<spring:message code='msg.success.requestdemo' javaScriptEscape='true' />";
</script>
</html>
