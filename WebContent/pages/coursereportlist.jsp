<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>

.content-wrapper {
margin: auto;
margin-left:230px;
}

.content-header .input-group-addon{
background-color:transparent !important;
padding-right:0px !important;
}

@media only screen and (min-width: 767px) {
    .content-wrapper {
        /* background-color: yellow; */
        margin-left:230px;
    }
}
@media only screen and (max-width: 760px) {
  .content-wrapper {
       /*  background-color: pink; */
        margin-left:0px;
    }
}  
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position:fixed;left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>        
		<!-- start dataTable----->
		<div class="col-sm-12" >
		<div class="content-wrapper">
			<section class="content-header">
	            	<h1><spring:message code="lbl.coursereports" text="Course Reports"/></h1>					
			</section>
			<section class="content">
			 <div class="row">
		         <div class="form-group">
		           <a href="courseEnrollmentStatusReport">1. <spring:message code="lbl.courseenrollstatusreport" text="Course Enrollment Status Report"/></a>
		         </div>
		         <div class="form-group">
		           <a href="courseSessionTimeReport">2. <spring:message code="lbl.coursesessiontimeout" text="Course Session Time Report"/></a>
		         </div>
		          <!--
		         <div class="form-group">
		           <a href="completedCourseAssessmentReport">3. Completed Transcripts by User Report - Course/Assessment</a>
		         </div>
		         
		         <div class="form-group">
		           <a href="individualUserProfileAuditReport">4. Individual User Profile Audit Report</a>
		         </div>
		         
		         <div class="form-group">
		           <a href="userLoginActivityReport">5. User Login Activity Report</a>
		         </div>	 -->	         
		         
		      </div>   
			</section>
			</div>
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->
</body>
<script>
/**
 * @summary function would be `called on document ready.
 */
      $(function () {
    	  $(".treeview").removeClass("active");
  		  $("#report").addClass("active");
  		  $("#report .treeview-menu > #coursereportlist").addClass("active");      	  
          });    
 </script>
</html>