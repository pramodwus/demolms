<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.qbis.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/pages/studentinclude.jsp"%>
<style>
.navbar-nav>.user-menu>.dropdown-menu>li.user-header {
    margin-left: -1px;
    margin-right: -1px;
}

.list-group-item {
	position: relative;
	display: block;
	padding: 12px 15px;
	margin-bottom: -1px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.search-input {
	width: 350px;
	margin-top: 4px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	/* background-image: url('searchicon.png'); */
	background-position: 10px 10px;
	background-repeat: no-repeat;
	padding: 5px 10px 10px 10px;
	-webkit-transition: width 0.4s ease-in-out;
	transition: width 0.4s ease-in-out;
}

.ndfHFb-c4YZDc-GSQQnc-LgbsSe {
	display: none;
}

.navbar-nav {
	float: right;
	margin: 0;
}

.skin-blue .main-header .logo {
	background-color: #fff;
	color: #fff;
	border-bottom: 0 solid transparent;
	float: left;
	margin-left: 0%;
}

.requireFld {
	color: #c95b5b !important;
	font-size: 12px;
	display: none;
	width: 100%;
}

.color-mainblue {
	color: #05B26F !important;
}

.badge {
	background-color: #05B26F;
}

.sidebar-menu>li.header {
	white-space: initial !important;
}

.next-previous-section-button, .next-previous-section-button:hover {
	background-color: #00A65A !important;
	color: white !important;
}

.next-previous-section-button:hover>a, .next-previous-section-button.active>a,
	.next-previous-section-button a {
	background-color: #00A65A !important;
	color: white !important;
	border-right-color: #00AA60;
}

img.lazy.loading , img.lazy-first_ppt.loading {
	background: transparent url(resources/images/loading.gif) no-repeat 50%
		50%;
}

.button-width-large {
	width: 150px;
}

.capitalize {
	text-transform: capitalize;
}

#leftMenuForContent {
	margin-top: 5px;
}

.user-menu-user-name {
	float: left;
	padding-right: 15px;
	color: #7d7d7d;
}

.dropdown-usermenu-margin-left {
	margin-left: -20px;
}

.dropdown-menu .user-header {
	background-color: #222 !important;
}

.navbar-custom-menu .dropdown .fa-caret-down {
	color: #7d7d7d !important;
}

.no-pointer-events {
	pointer-events: none;
}

.all_course_content {
	color: #7d7d7d !important;
	font-size: 15px !important;
	font-weight: 400 !important;
	cursor: pointer !important;
}

.list-group-item-margin-top {
	margin-top: -20px;
}

/**
 * only for firefox
 */
@-moz-document url-prefix() { 
  .list-group-item-margin-top {
	margin-top: 0px;
   }
}

#frameLoad {
	width: 100%;
	height: 620px;
	border: 1px solid #DEDEDE;
	background-color: #FFFFFF;
	border-top: 0px;
}

#courseName {
	font-size: 18px;
}

.list-group-item-first-div {
	border: 1px solid #eeeeee;
	height: 50px;
	width: 70%;
	margin: 30px 0% 40px 15%;
}

.text-align-center {
	text-align: center !important;
}

.operation-button-div-margin {
	margin-top: 7px;
	margin-right: 7px;
}

#sharedContentButton {
	display: none;
}

#sharedContentPopup .modal-body {
	height: 250px;
}

.footer-button-layer-padding {
	padding-top: 30px;
}

#notes {
	max-width: 100%;
	max-height: 200px;
}

.no-visibility {
	visibility: hidden
}

.user-panel>.info {
	left: 0px;
}

.list-li-disabled {
	cursor: not-allowed;
	filter: alpha(opacity = 65);
	-webkit-box-shadow: none;
	box-shadow: none;
	opacity: .65;
	background-color: #5cb85c !important;
	border-color: #5cb85c !important;
}

.list-li-disabled>* {
	pointer-events: none !important;
}

.list-li-disabled>a {
	background-color: #5cb85c !important;
	border-color: #5cb85c !important;
}

.list-li-disabled:hover>a {
	background-color: #5cb85c !important;
	border-color: #5cb85c !important;
}

.list-group-item-min-height {
	min-height: 50px;
}

@media ( max-width : 1010px) {
	body {
		min-width: 1010px;
		overflow-x: scroll;
	}
}

@media ( max-width : 767px) {
	.main-sidebar, .left-side {
		padding-top: 50px;
		-webkit-transform: translate(0, 0);
		-ms-transform: translate(0, 0);
		-o-transform: translate(0, 0);
		transform: translate(0, 0);
	}
	.content-wrapper, .right-side, .main-footer {
		margin-left: 230px;
	}
	.main-header .navbar {
		margin: 0px 0px 0px 230px;
	}
	.main-header .logo, .main-header .navbar {
		width: initial;
	}
	.main-header .logo {
		width: 230px;
	}
}
</style>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<script type="text/javascript">
	var timeList = [];
	var setTimeoutId = "";
	var audioList;
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<header class="main-header">
			<!-- Logo -->
			<div>
				<a href="javascript:void(0)" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini"><b>Q</b></span> <!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><img
						src="<spring:url value='/resources/images/logo.png'/>" /></span>
				</a>

			</div>

			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<div class="navbar-custom-menu">

					<ul class="nav navbar-nav">


						<li>
							<form class="no-visibility">
								<input type="text" name="search" class="search-input"
									placeholder="Search Courses/Test/Categories/Tags">
							</form>
						</li>

						<!-- User Account: style can be found in dropdown.less -->
						<li class="user user-menu"><a class="no-pointer-events">
								<span class="hidden-xs user-menu-user-name"> <%=((User) request.getSession().getAttribute("userSession"))
					.getFirstName() == "" ? ((User) request.getSession()
					.getAttribute("userSession")).getEmail() : ((User) request
					.getSession().getAttribute("userSession")).getFirstName()
					+ "  "
					+ ((User) request.getSession().getAttribute("userSession"))
							.getLastName() + " "%></span> <img
								src="<%=((User) request.getSession().getAttribute("userSession"))
					.getImage()%>"
								onerror="this.src='resources/images/icon-admin.png'"
								class="user-image" alt="User Image">
						</a></li>

						<li class="dropdown user user-menu dropdown-usermenu-margin-left"><a
							href="javascript:void(0)" class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-caret-down"></i></a>

							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img
									src="<%=((User) request.getSession().getAttribute("userSession"))
					.getImage()%>"
									onerror="this.src='resources/images/icon-admin.png'"
									class="img-circle" alt="User Image">
									<p>
										<%=((User) request.getSession().getAttribute("userSession"))
					.getFirstName() == "" ? ((User) request.getSession()
					.getAttribute("userSession")).getEmail() : ((User) request
					.getSession().getAttribute("userSession")).getFirstName()
					+ "  "
					+ ((User) request.getSession().getAttribute("userSession"))
							.getLastName() + " "%>
									</p> <input type="hidden" id="userId"
									value="<%=((User) request.getSession().getAttribute("userSession"))
					.getUserId()%>">
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="javascript:void(0)" data-toggle="dropdown"
											class="btn btn-default btn-flat dropdown-toggle"><spring:message
												code="lbl.cancel" text="Cancel" /></a>
									</div>
									<div class="pull-right">
										<a href="logout" class="btn btn-default btn-flat"><spring:message
												code="lbl.signout" text="Sign out" /></a>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left info">
						<a class="all_course_content"> <span
							class="fa fa-chevron-left"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<spring:message code="lbl.allcourseContent"
								text="All Course Content" />
						</a>
					</div>
				</div>
				<ul class="sidebar-menu" id="leftMenuForContent">

				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<ul class="list-group">
				<li class="list-group-item list-group-item-min-height">
					<div class="text-align-center">
						<span class="pull-left"><b><spring:message
									code="lbl.minspenttime" text="Min. Spent Time" /></b>&nbsp;:&nbsp;<span
							id="minimum_section_spent_time"></span></span> <b id="courseName"
							class="capitalize"></b> <span class="pull-right"><b><spring:message
									code="lbl.spenttime" text="Spent Time" /></b>&nbsp;:&nbsp;<span
							id="total_spent_time_on_section"></span></span>
					</div>
				</li>
				<li class="list-group-item-margin-top">
					<div id="frameLoad"></div>
				</li>
				<li class="list-group-item no-border">
					<div class="list-group-item-first-div">

						<div class="pull-right operation-button-div-margin">
							<button type="button" class="btn btn-default"
								id="sharedContentButton">
								<i class="fa fa-share"></i>&nbsp;
								<spring:message code="lbl.share" text="Share" />
							</button>
							<button type="button" id="favbtn" class="btn btn-default">
								<i class="fa fa-heart-o" id="favicon"></i>&nbsp;
								<spring:message code="lbl.favorite" text="Favorite" />
							</button>
							<button type="button" id="reportbtn" class="btn btn-default">
								<i class="fa fa-flag-o" id="reporticon"></i>&nbsp;
								<spring:message code="lbl.report" text="Report" />
							</button>
						</div>

					</div>
					<div id="nextContentButton"></div>
				</li>

			</ul>

		</div>

	</div>
	<!-- ./wrapper -->
	<!-- Start of Pop up box for sharing content -->
	<div class="modal fade" id="sharedContentPopup" tabindex="-1"
		role="dialog" aria-labelledby="sharedContentPopup">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog ">
				<div class="modal-content">
					<div class="modal-body">
						<button type="button" class="close pull-right"
							data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h3 class="text-align-center">
							<spring:message code="lbl.sharecontent" text="Share Content" />
						</h3>
						<br />
						<div class="col-xs-12">
							<form name="shareContentForm" id="shareContentForm">
								<input type="hidden" name="contentPath" id="sharedContentPath">
								<input type="text" class="form-control" placeholder="Email Id"
									id="sharedEmailId" name="email" onkeydown="catValidate();"
									onkeyup="catValidate();"> <label class="requireFld"
									id="emailerror1"><spring:message code="msg.empty"
										text="This field is required." /></label> <label class="requireFld"
									id="emailerror2"><spring:message
										code="msg.email.invalid"
										text="Please provide a valid Email Id." /></label>
							</form>
						</div>
						<div
							class="col-xs-12 text-align-center footer-button-layer-padding">
							<a onclick="sharedContentAjax()" id="saveSectionButton"
								class="btn btn-success btn-flat  button-width-large"><spring:message
									code="lbl.share" text="Share" /></a>&emsp;&emsp; <a
								class="btn btn-flat btn-default button-width-large"
								data-dismiss="modal" aria-label="Close"><spring:message
									code="lbl.cancel" text="Cancel" /></a>&emsp;&emsp;
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="alertPopup" tabindex="-1" role="dialog"
		aria-labelledby="alertPopup">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body text-align-center">
						<h3>
							<strong></strong>
						</h3>
						<p></p>
						<button type="button" class="btn btn-success button-width-large"
							style="" data-dismiss="modal">
							<spring:message code="lbl.ok" text="Ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="contentErrorAlert" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body text-align-center">
						<h3></h3>
						<p></p>
						<button type="button" class="btn btn-success button-width-large"
							data-dismiss="modal">
							<spring:message code="lbl.ok" text="Ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Add Notes popup -->
	<div class="modal fade" id="notesModal" tabindex="-1" role="dialog"
		aria-labelledby="notesPopup">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label><spring:message code="lbl.enternotes"
									text="Enter Notes" /></label>
							<textarea id="notes" class="form-control" maxlength="2000"></textarea>
						</div>
						<div class="">
							<input type="hidden" id="content_id"> <input
								type="hidden" id="reportId"> <input type="hidden"
								id="sec_con">
							<button type="button" class="btn btn-success"
								onclick="saveAbuseReport()">
								<spring:message code="lbl.submit" text="Submit" />
							</button>
							<button type="button" class="btn btn-success" id="notebtnclose"
								data-dismiss="modal">
								<spring:message code="lbl.close" text="Close" />
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var messages = new Array();
	messages['lbl.report'] = "<spring:message code='lbl.report' text='Report' javaScriptEscape='true'/>";
	messages['lbl.favorite'] = "<spring:message code='lbl.favorite' text='Favorite' javaScriptEscape='true'/>";
	messages['lbl.iconstandfor'] = "<spring:message code='lbl.iconstandfor' text='icon stand for #filetype' arguments='#filetype' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
	messages['lbl.assessment'] = "<spring:message code='lbl.assessment' text='Assessment' javaScriptEscape='true'/>";
	messages['lbl.startassessment'] = "<spring:message code='lbl.startassessment' text='Start Assessment' javaScriptEscape='true'/>";
	messages['lbl.nextsection'] = "<spring:message code='lbl.nextsection' text='Next Section' javaScriptEscape='true'/>";
	messages['lbl.previoussection'] = "<spring:message code='lbl.previoussection' text='Previous Section' javaScriptEscape='true'/>";
	messages['msg.empty'] = "<spring:message code='msg.empty' text='This field is required.' javaScriptEscape='true'/>";
	messages['msg.maxcharacterlength'] = "<spring:message code='msg.maxcharacterlength' arguments='#maxlength' text='Maximum #maxlength characters allowed.' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
	messages['msg.contentsuccessfullyshared'] = "<spring:message code='msg.contentsuccessfullyshared' text='Your content has been successfully shared.' javaScriptEscape='true'/>";
	messages['msg.reportsuccessfullysaved'] = "<spring:message code='msg.reportsuccessfullysaved' text='Report has been successfully saved.' javaScriptEscape='true'/>";

	messages['lbl.min'] = "<spring:message code='lbl.min' text='Min' javaScriptEscape='true'/>";
	messages['lbl.sec'] = "<spring:message code='lbl.sec' text='Sec' javaScriptEscape='true'/>";
	messages['msg.jumponnextsection'] = "<spring:message code='msg.jumponnextsection' text='Now you can jump on next section.' javaScriptEscape='true'/>";
	messages['msg.coursecompleted'] = "<spring:message code='msg.coursecompleted' text='You have completed course.' javaScriptEscape='true'/>";

	/**
	 * videocontroll.js
	 */
	messages['msg.datanotsaved'] = "<spring:message code='msg.datanotsaved' text='Data could not saved. Please try again later.' javaScriptEscape='true'/>";
	messages['lbl.title'] = "<spring:message code='lbl.title' text='Title' javaScriptEscape='true'/>";
	messages['lbl.question'] = "<spring:message code='lbl.question' text='Question' javaScriptEscape='true'/>";
	messages['lbl.answers'] = "<spring:message code='lbl.answers' text='Answers' javaScriptEscape='true'/>";
	messages['lbl.continue'] = "<spring:message code='lbl.continue' text='Continue' javaScriptEscape='true'/>";
	messages['lbl.submit'] = "<spring:message code='lbl.submit' text='Submit' javaScriptEscape='true'/>";
	messages['msg.attemptquestionforsubmit'] = "<spring:message code='msg.attemptquestionforsubmit' text='Please attempt question for submit otherwise click on continue.' javaScriptEscape='true'/>";
	messages['lbl.previous'] = "<spring:message code='lbl.previous' text='Previous' javaScriptEscape='true'/>";
	messages['lbl.showingpage'] = "<spring:message code='lbl.showingpage' text='showing #currectpage of #totalpage' arguments='#currectpage;#totalpage' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
	messages['lbl.next'] = "<spring:message code='lbl.next' text='Next' javaScriptEscape='true'/>";
	messages['lbl.questiontype'] = "<spring:message code='lbl.questiontype' text='Question Type' javaScriptEscape='true'/>";
	messages['lbl.slideholdtill'] = "<spring:message code='lbl.slideholdtill' text='Slide Hold Till:' javaScriptEscape='true'/>";
</script>
<script src="<spring:url value='/resources/js/youtubeplayer.js?v=1'/>"></script>
<script src="resources/js/videocontroll.js?v=5"></script>
<script src="resources/js/viewcoursecontent.js?v=2"></script>
<script>
	/**
	 * @summary instance of course id.
	 */
	var viewCourseId = '${courseId}';

	/**
	 * @summary instance of activity id for current viewing content and it will use for describing the details for a every view attempt of content. 
	 */
	var contentActivityId = 0;

	/**
	 * @summary instance of activity id for current giving course and it will use for describing the details for a every attempt of course. 
	 */
	var courseActivityId = 0;

	/**
	 * @summary instance of received data's status which will describes that section list has been received succesfully or not.
	 */
	var datastatus;

	/**
	 * @summary instance of video player.
	 */
	var vid;

	/**
	 * @summary instance of latest activity on this course which will describes from which content user will resume the course.
	 */
	var latestCourseActivity;

	/**
	 * @summary Empty Instance for json object.
	 * 
	 */
	var sectionJson = {};

	/**
	 * @summary calling on document loading
	 */
	$(document)
			.ready(
					function() {
						/**
						 * @summary getting section list of course based on course Id.
						 */
						getCourseSectionList();

						/**
						 * @summary if data is successfully received based on course id.
						 */
						if (datastatus != null && datastatus == 200) {

							/**
							 * @summary checking that all required data is available for resume for course from latest attempt's data.
							 */
							if (latestCourseActivity != null
									&& latestCourseActivity.courseId != null
									&& latestCourseActivity.courseActivityId != null
									&& latestCourseActivity.contentId != null
									&& latestCourseActivity.sectionId != null
									&& latestCourseActivity.courseId > 0
									&& latestCourseActivity.courseActivityId > 0
									&& latestCourseActivity.contentId > 0
									&& latestCourseActivity.sectionId > 0) {
								/**
								 * @summary jump on content based on information of latest attempted course data.
								 */
								showContent(latestCourseActivity.sectionId,
										latestCourseActivity.contentId,
										'sectionChange');
							}

							/**
							 * @summary start from first content if there is not latest activity or unavilable correct data.
							 */
							else {
								/**
								 * @summary show first content of first section to user.
								 */
								showContent(
										sectionJson[0].sectionId,
										sectionJson[0].sectionContent[0].contentId,
										'sectionChange');
							}

							/**
							 * @summary start timer which will use for saving updating the course activity on every after 10 sec.
							 */
							setInterval(updatecourseviewactivity, 10000);
						}

						/**
						 * @summary if data is not received then redirect on course list page.
						 */
						else {
							location.href = 'studentCourse?action=courseList';
						}

						/**
						 * @summary update course activity data before leaving the window.
						 */
						$(window).on('beforeunload', function() {
							updatecourseviewactivity();
						});

					});

	/**
	 * @summary This is used for ajax calling for getting section and its content's list based on course id.
	 * @return no
	 */
	var getCourseSectionList = function() {
		var courseId = viewCourseId;
		$("#overlay").show();
		/**
		 * @summary calling api using ajax for getting course content.
		 */
		$.ajax({
			url : "api/sectionlistbycourseid/" + courseId + "/"
					+ $("#userId").val(),
			type : "GET", // @summary request type is get. 
			async : false,
			contentType : 'application/json',
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', 'Browser');
			},

			error : function() { // @summary would be execute if any error occurs.
				$("#overlay").hide();
				//alert("error");
			},
			success : function(data) { //@summary would be execute on successfull getting course list.
				/**
				 * @summary calling function for extracting course content from list.
				 */
				datastatus = data.status;
				if (data.status == 200) {
					latestCourseActivity = data.courseActivity;
					sectionJson = data.sectionList;
					courseActivityId = data.courseActivityId;
					$("#courseName").html(data.courseName);
				}
				$("#overlay").hide();
			}
		});
	}

	/**
	 * @summary This is used for fetching all related data from json object 
	 * @param sectionId
	 * @param contentId
	 * @param action
	 * @return no
	 */
	var showContent = function(sectionId, contentId, action) {
		$("#overlay").show();
		try {
			/**
			 * @summary call function for updating the course view activity details.
			 */
			updatecourseviewactivity();
			/**
			 * @summary call function for saving the current content activity details.
			 */
			saveViewContentActivity(contentId, sectionId);
			/**
			 * @summary iterate on section list
			 */
			for (var i = 0; i < sectionJson.length; i++) {
				if (sectionJson[i].sectionId == sectionId) {
					/**
					 * @summary if action is equal to sectionChange then it indicates the section has been changed.
					 */
					if (action == 'sectionChange') {
						$("#leftMenuForContent").empty();
						var str = '<li class="header capitalize"><i class="fa  fa-align-left"></i>&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="courseSectionId" value="'+sectionId+'"/>'
								+ sectionJson[i].sectionName
								+ '</li>'
								+ addContent(sectionJson[i].sectionId,
										sectionJson[i].sectionContent)
								+ (i == 0 ? ''
										: addPreviousButton(
												sectionJson[i - 1].sectionId,
												sectionJson[i - 1].sectionContent[0].contentId,
												'sectionChange'))
								+ (i == (sectionJson.length - 1) ? ''
										: addNextButton(
												sectionJson[i + 1].sectionId,
												sectionJson[i + 1].sectionContent[0].contentId,
												'sectionChange'));

						/**
						 * @summary append content list with next and previous button of current section in left menu.
						 */
						$("#leftMenuForContent").append(str);

						try {

							/**
							 * @summary extract minimum required spent minutes from mimimum required spent time.
							 */
							var _minSpentMinute = sectionJson[i].minTimeSpent / 60;

							/**
							 * @summary extract mimimum required spent seconds from mimimum required spent time.
							 */
							var _minSpentSecond = sectionJson[i].minTimeSpent % 60;

							/**
							 * @summary show mimimum required spent time in combination of minute and seconds to user.
							 */
							$("#minimum_section_spent_time")
									.html(
											_minSpentMinute
													+ " "
													+ messages['lbl.min']
													+ (_minSpentSecond > 0 ? (" "
															+ _minSpentSecond
															+ " " + messages['lbl.sec'])
															: ""));

							/**
							 * @summary stop if any timer is running.
							 */
							stopSectionTrackingTimer();

							/**
							 * @summary if total spent time is equal or greater than minimum
							 *  required time.
							 */
							if (sectionJson[i].totalSpentTime >= sectionJson[i].minTimeSpent) {

								/**
								 * @summary if quiz is mandatory.
								 */
								if (sectionJson[i].isQuizMandatory == 1) {

									/**
									 * @summary if user has been passed the passing criteria for quiz.
									 */
									if (sectionJson[i].isPassedTheCriteria) {

										/**
										 * @summary enable the button for jumping the next section.
										 */
										$("#next-section-button").removeClass(
												"list-li-disabled");

										/**
										 * @summary make false to variable which indicates that spent
										 *          time of user on current section is greater than minimum
										 *          required time.
										 */
										var spentTimeIsLessThanMinRequiredSpentTime = false;

										/**
										 * @summary start the timer for tracking spent time in current section.
										 */
										startSectionTrackingTimer(
												sectionJson[i].totalSpentTime,
												i,
												spentTimeIsLessThanMinRequiredSpentTime);
									}

									/**
									 * @summary user did not pass the passing criteria for section.
									 */
									else {

										/**
										 * @summary make true to variable which indicates that spent
										 *          time of user on current section is less than minimum
										 *          required time.
										 */
										var spentTimeIsLessThanMinRequiredSpentTime = true;

										/**
										 * @summary start the timer for tracking spent time in current section.
										 */
										startSectionTrackingTimer(
												sectionJson[i].totalSpentTime,
												i,
												spentTimeIsLessThanMinRequiredSpentTime);

									}
								}

								/**
								 * @summary if quiz is not mandatory.
								 */
								else {

									/**
									 * @summary enable the button for jumping the next section.
									 */
									$("#next-section-button").removeClass(
											"list-li-disabled");

									/**
									 * @summary make false to variable which indicates that spent
									 *          time of user on current section is greater than minimum
									 *          required time.
									 */
									var spentTimeIsLessThanMinRequiredSpentTime = false;

									/**
									 * @summary start the timer for tracking spent time in current section.
									 */
									startSectionTrackingTimer(
											sectionJson[i].totalSpentTime, i,
											spentTimeIsLessThanMinRequiredSpentTime);
								}
							}

							/**
							 * @summary if total spent time is less than minimum
							 *  required time.
							 */
							else {
								/**
								 * @summary make true to variable which indicates that spent
								 *          time of user on current section is less than minimum
								 *          required time.
								 */
								var spentTimeIsLessThanMinRequiredSpentTime = true;

								/**
								 * @summary start the timer for tracking spent time in current section.
								 */
								startSectionTrackingTimer(
										sectionJson[i].totalSpentTime, i,
										spentTimeIsLessThanMinRequiredSpentTime);
							}
						} catch (err) {
							$("#overlay").hide();
							console.log(err.message);
						}
					}
					
					/**
					 * @summary this would be call for loading the content.
					 */
					loadContent(sectionJson[i].sectionContent, contentId,
							sectionJson[i].sectionId, i);
				}
			}
		} catch (err) {
			$("#overlay").hide();
			console.log(err.message);
		}
		$("#overlay").hide();
	}

	/**
	 * @summary This is used for save view contents activity of user  
	 * @param contentId
	 */
	var saveViewContentActivity = function(contentId, sectionId) {
		var courseId = viewCourseId;
		var data = JSON.stringify({
			'contentId' : contentId,
			'userId' : $("#userId").val(),
			'sectionId' : sectionId,
			'courseId' : courseId,
			'courseActivityId' : courseActivityId
		});

		$.ajax({
			url : "api/saveViewContentActivity",
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', "Browser");
			},
			async : false,
			type : 'POST',
			dataType : 'json',
			contentType : "application/json",
			data : data,
			error : function() {
				//alert("error");
			},
			success : function(response) {
				if (response.contentActivityId != null
						&& response.contentActivityId > 0) {
					contentActivityId = response.contentActivityId;
				}
			}
		});
	}

	/**
	 * @summary This is used for showing content on page based on content id and content's type. 
	 * @param sectionContentList
	 * @param contentId
	 * @param sectionId
	 * @return no
	 */
	var loadContent = function(sectionContentList, contentId, sectionId, i) {
		/**
		 * @summary iteration on content list.
		 */
		for (var con = 0; con < sectionContentList.length; con++) {
			/**
			 * @summary when content id is matched.
			 */
			if (sectionContentList[con].contentId == contentId) {
				/**
				 * @summary make empty to div in which content would be load.
				 */
				$("#frameLoad").empty();
				/**
				 * @summary if content type is not test and its visibility is public.
				 */
				if (sectionContentList[con].visiblity == 1
						&& sectionContentList[con].contentType != 'TEST') {
					/**
					 * @summary show shared button to user so that user can shared this content to any user.
					 */
					$("#sharedContentButton").show();
					/**
					 * @summary set content path for shared content inside click function.
					 */
					$("#sharedContentButton").attr(
							'onclick',
							'openShareContentPopup("'
									+ sectionContentList[con].contentPath
									+ '")');
				}
				/**
				 * @summary if content is test type or content is private then hide the shared button.
				 */
				else {
					$("#sharedContentButton").hide();
					$("#sharedContentButton").attr('onclick', '');
				}

				/**
				 * @summary initialize favoriate id for content in this variable.
				 */
				var favid = sectionContentList[con].favoriateId;

				/**
				 * @summary make a combination of section id and content id so that this id can be set on favorite icon tag which would be show to user for choosing favorite this content.
				 */
				var abc = sectionId + '_' + contentId;

				/**
				 * @summary if this content is favorite of current acitve user.
				 */
				if (sectionContentList[con].favorites == 1) {
					/**
					 * @summary make empty to onclick function.
					 */
					$("#favbtn").attr('onclick', '');
					/**
					 * @summary set new action on click of favorite content button so that user can dislike this content.
					 */
					$("#favbtn").attr(
							"onclick",
							"setresetfavoriate(" + contentId + "," + 0 + ",'"
									+ abc + "'," + favid + ")");
					/**
					 * @summary new html for favorite button.
					 */
					var ss = '<i class="fa fa-heart color-mainblue" id="favicon'+sectionId+'_'+contentId+'"></i>&nbsp'
							+ messages['lbl.favorite'];
					/**
					 * @summary make empty of fav button html .
					 */
					$("#favbtn").html('');
					/**
					 * @summary append html inside favorite button.
					 */
					$("#favbtn").append(ss);
				}
				/**
				 * @summary if content is not set favorite by user.
				 */
				else {
					/**
					 * @summary make empty to onclick function.
					 */
					$("#favbtn").attr('onclick', '');
					/**
					 * @summary set new action on click of favorite content button so that user can like this content.
					 */
					$("#favbtn").attr(
							"onclick",
							"setresetfavoriate(" + contentId + "," + 1 + ",'"
									+ abc + "'," + favid + ")");
					/**
					 * @summary new html for favorite button.
					 */
					var ss = '<i class="fa fa-heart-o color-mainblue" id="favicon'+sectionId+'_'+contentId+'"></i>&nbsp'
							+ messages['lbl.favorite'];
					/**
					 * @summary make empty of fav button html .
					 */
					$("#favbtn").html('');
					/**
					 * @summary append html inside favorite button.
					 */
					$("#favbtn").append(ss);
				}
				/**
				 * @summary initialize report id for content in this variable.
				 */
				var reportid = sectionContentList[con].reportId;
				/**
				 * @summary getting abuse report text of current active user for this content. 
				 */
				var report = sectionContentList[con].abuseReport;
				/**
				 * @summary if user did not abuse this content yet.
				 */
				if (reportid == 0) {
					/**
					 * @summary make empty to onclick function.
					 */
					$("#reportbtn").attr('onclick', '');
					/**
					 * @summary set new action on click of report abuse button so that user can report about this content.
					 */
					$("#reportbtn").attr(
							"onclick",
							"setAbuseReportModal(" + contentId + ",'" + abc
									+ "'," + reportid + ",'" + report + "')");
					/**
					 * @summary new html for report button.
					 */
					var ss = '<i class="fa fa-flag-o color-mainblue" id="reporticon'+sectionId+'_'+contentId+'"></i>&nbsp'
							+ messages['lbl.report'];
					/**
					 * @summary make empty of report button html .
					 */
					$("#reportbtn").html('');
					/**
					 * @summary append html inside report button.
					 */
					$("#reportbtn").append(ss);
				}
				/**
				 * @summary if user has already abused about this content.
				 */
				else {
					/**
					 * @summary make empty to onclick function.
					 */
					$("#reportbtn").attr('onclick', '');
					/**
					 * @summary set new action on click of report abuse button so that user can change report text about this content.
					 */
					$("#reportbtn").attr(
							"onclick",
							"setAbuseReportModal(" + contentId + ",'" + abc
									+ "'," + reportid + ",'" + report + "')");
					/**
					 * @summary new html for report button.
					 */
					var ss = '<i class="fa fa-flag color-mainblue" id="reporticon'+sectionId+'_'+contentId+'"></i>&nbsp'
							+ messages['lbl.report'];
					/**
					 * @summary make empty of report button html.
					 */
					$("#reportbtn").html('');
					/**
					 * @summary append html inside report button.
					 */
					$("#reportbtn").append(ss);
				}

				/**
				 * @summary remove active content tag from any content.
				 */
				$(".treeview").removeClass('active');

				/**
				 * @summary show active content tag for current content.
				 */
				$("#treeview" + contentId).addClass('active');
				/**
				 * @summary initialize a varibale which would be used for load the html content of current content inside the frame div.
				 */
				var frameHTML = '';
				/**
				 * @summary make switch case on content type.
				 */
				switch (sectionContentList[con].contentType) {
				/**
				 * @summary if content is pdf type.
				 */
				case 'PDF':
					/* frameHTML = '<iframe src="'
						+ sectionContentList[con].contentPath
						+ '" onerror="this.src=\'resources/images/body.jpg\'" style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>'; */

					/**
					 * @summary call function which will load the pdf content type html inside frame div.
					 */
					frameHTML = createPDFQuestionUI(sectionContentList[con], 0);
					break;

				/**
				 * @summary if content is audio type.
				 */
				case 'AUDIO':
					/**
					 * @summary make html for audio type content.
					 */
					frameHTML = '<iframe src="'
							+ sectionContentList[con].contentPath
							+ '" onerror="this.src=\'resources/images/body.jpg\'" style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>';
					break;

				/**
				 * @summary if content is video type.
				 */
				case 'VIDEO':
					/**
					 * @summary if video type content does not contain the any question.
					 */
					if (sectionContentList[con].questionList.length == 0) {
						/**
						 * @summary simple make html for video cotent type.
						 */
						frameHTML = '<iframe src="'
								+ sectionContentList[con].contentPath
								+ '" onerror="this.src=\'resources/images/body.jpg\'" style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>';
					}
					/**
					 * @summary if video content type contain the question list.
					 */
					else {
						/**
						 * @summary call function for getting html of video type content which contains question list.
						 */
						frameHTML = createVideoQuestionUI(
								sectionContentList[con], 0);
					}
					break;

				/**
				 * @summary if content is image type.
				 */
				case 'IMAGE':
					/**
					 * @summary make html for image cotent type.
					 */
					frameHTML = '<img src="'
							+ sectionContentList[con].contentPath
							+ '" style="width: 100%;height:100%" onerror="this.src=\'resources/images/c-png.png\'">';
					break;

				/**
				 * @summay This case is not in used.
				 */
				case 'AUDIO1':
					frameHTML = '<audio  style="text-align:center" controls><source src="'+sectionContentList[con].contentPath+'" type="audio/mpeg">Your browser does not support the audio element.</audio>';
					break;

				/**
				 * @summary if content is test type.
				 */
				case 'TEST':
					/**
					 * @summary make html for test type content.
					 */
					frameHTML = '<div class="col-sm-12">'
							+ '<div class="modal-body">'
							+ '<h1 class="form-group">'
							+ messages['lbl.assessment']
							+ '</h1>'
							+ '<p>'
							+ sectionContentList[con].contentName
							+ '</p>'
							+ '<div class="col-xs-12" style="min-height: 30px"></div>'
							+ '<button type="button" class="btn btn-flat btn-lg btn-success" onclick="window.open(\'studentGivenTest?testId='
							+ sectionContentList[con].content
							+ '&contentActivityId='
							+ contentActivityId
							+ '\', \'\', \'width=1000,height=500,scrollbars=yes,menubar=no, resizable=yes,toolbar=no,location=no,status=no\'\)">'
							+ messages['lbl.startassessment'] + '</button>'
							+ '</div>' + '</div>';
					break;

				/**
				 * @summary if content is ppt type.
				 */
				case 'PPT':
					/**
					 * @summary load question list in this variable this question list is different from question list of pdf or video because this question list contain the slide hold time or audio clip for every page.
					 */
					audioList = sectionContentList[con].questionList;
					/**
					 * @summary make html for ppt type content.
					 */
					frameHTML = createPPTVoiceUI(sectionContentList[con]);
					/* var frameURl='http://docs.google.com/viewerng/viewer?url='+sectionContentList[con].contentPath+'&embedded=true';
					frameHTML = '<iframe src="'+frameURl+'" style="width:100%;height:100%" frameborder="0" allowfullscreen webkitallowfullscreen></iframe>'; */
					break;
				/**
				 * @summary if content is url type.
				 */
				case 'URL':
					frameHTML = '<div class="col-xs-12" style="text-align: center; padding-top: 75px;" id="promoVideoDiv"><div id="promoVideo" style="width:80% !important;height:450px !important"></div></div>'
					break;
				}
				/**
				 * @summary load html content inside frame div.
				 */
				$("#frameLoad").append(frameHTML);

				/**
				 * @summary if content type is url 
				 */
				if (sectionContentList[con].contentType == "URL") {
					try {
						/**
						 * @summary call function for play this video.
						 */
						playVideo(sectionContentList[con].content);
					} catch (err) {
						console.log(err.message);
					}
				}

				/**
				 * @summary if content is video type.
				 */
				if (sectionContentList[con].contentType == "VIDEO") {
					try {
						/**
						 * @summary this contains all time slot where question is set.
						 */
						timeList = [];
						/**
						 * @summary iteratre on question list for this content.
						 */
						for (var i = 0; i < parseInt($("#questionCount").val()); i++) {
							/**
							 * @summary push time slot.
							 */
							timeList.push(parseInt($("#timeslot" + i).val()));
						}

						/**
						 * @summary get video type object by id.
						 */
						vid = document.getElementById("myVideo");
						/**
						 * @summary add event listener which would be call on video play.
						 */
						vid.addEventListener("play", function() {
							/**
							 * @summary start timer which would capture the time and if this time would match with any time slot where question is set then video would be pause at that matching time and question would be display.
							 */
							setTimeForQuestionAtCourse();
						}, false);
						/**
						 * @summary add event listener which would be call on video pause.
						 */
						vid.addEventListener("pause", function() {
							/**
							 * @summary stop timer.
							 */
							clearInterval(setTimeoutId);
						}, false);

						/**
						 * @summary add event listener on seeking the video.
						 */
						vid.addEventListener("seekable", function() {
							/**
							 * @summary pause the video
							 */
							vid.pause();
							/**
							 * @summary start timer which would capture the time and if this time would match with any time slot where question is set then video would be pause at that matching time and question would be display.
							 */
							setTimeForQuestionAtCourse();
						}, false);

						/**
						 * @summary call icheck funtion for radio or check box in options.
						 */
						$(
								'input[type="checkbox"].flat-red, input[type="radio"].flat-red')
								.iCheck({
									checkboxClass : 'icheckbox_square-green',
									radioClass : 'iradio_square-green'
								});
					} catch (err) {
						console.log(err.message);
					}
				}

				/**
				 * @summary if content is pdf type.
				 */
				if (sectionContentList[con].contentType == "PDF") {
					try {
						/**
						 * @summary get total number of pages in this pdf.
						 */
						var _totalpages = $("#contentPages").val();
						/**
						 * @summary if pdf contains only single page.
						 */
						if (_totalpages != null && _totalpages == 1) {
							/**
							 * @summary make disable the next button.
							 */
							$("#next").prop('disabled', true);
						}
						/**
						 * @summary make array which conatins the array of pages which have questions.
						 */
						timeList = [];

						for (var i = 0; i < parseInt($("#questionCount").val()); i++) {
							/**
							 * @summary 
							 */
							timeList.push(parseInt($("#timeslot" + i).val()));
						}

						/**
						 * @summary call icheck function for radio and checkbox button.
						 */
						$(
								'input[type="checkbox"].flat-red, input[type="radio"].flat-red')
								.iCheck({
									checkboxClass : 'icheckbox_square-green',
									radioClass : 'iradio_square-green'
								});
					} catch (err) {
						console.log(err.message);
					}
				}

				/**
				 * @summary if content is ppt or pptx type.
				 */
				if (sectionContentList[con].contentType == "PPT"
						|| sectionContentList[con].contentType == "PPTX") {
					try {
						/**
						 * @summary get total number of slides in powerpoint slide.
						 */
						var _totalpages = $("#contentPages").val();
						/**
						 * @summary if slide contains the only single page.
						 */
						if (_totalpages != null && _totalpages == 1) {
							/**
							 * @summary disable the next button.
							 */
							$("#next").prop('disabled', true);
						}
						/**
						 * @summary get audio type object based on id.
						 */
						aud = document.getElementById("myaudio");
						/**
						 * @summary set event listner for audio when audio clip has been end.
						 */
						aud.addEventListener("ended", function() {
							/**
							 * @summary call function for show next slide on audio clip ended.
							 */
							showNewSlide(1);
						}, false);
						/**
						 * @summary lazy loader for first slide.
						 */
						$('.lazy-first_ppt').Lazy({
			      		    bind:'event',
			      		    chainable: false,
			      	    	beforeLoad: function(element) {
			      	        },
			      	        afterLoad: function(element) {
								/**
								 * @summary call function for showing audio or slide holding time to user on current slide.
								 */
								addAudioAndTime(0);
			      	        },
			      	        onError: function(element) {
			      	        },
			      	        onFinishedAll: function(element) {
			      	        }
			      	 });
					} catch (err) {
						console.log(err.message);
					}
				}
				/**
				 * @summary make next content empty.
				 */
				$("#nextContentButton").empty();
				/**
				 * @summary if current content is not last content of this section then show button for moving next content.
				 */
				if (con !== sectionContentList.length - 1) {
					var nextContentButton = '<div style="cursor:pointer;border:1px solid #eeeeee;height:50px;width:70%;margin: 30px 0% 40px 15%;" onclick="showContent(\''
							+ sectionId
							+ '\',\''
							+ sectionContentList[con + 1].contentId
							+ '\');">'
							+ '<p style="white-space: normal;color:#7d7d7d;text-align:center;line-height: 50px;" class="capitalize">'
							+ '<i class="'
							+ (loadSubjectIcon(sectionContentList[con + 1].contentType))
							+ '" style="color:#00B06C"></i>&nbsp;'
							+ sectionContentList[con + 1].contentName
							+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-chevron-right"></span></p>'
							+ '</div>';
					$("#nextContentButton").append(nextContentButton);
				}

				/* 	else {
						var nextContentButton = '<div style="cursor:pointer;height:50px;width:70%;margin: 30px 0% 40px 15%;">'
							+ '<p style="white-space: normal;color:#7d7d7d;text-align:center;line-height: 50px;" >'
							+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=""></span></p>'
							+ '</div>';
					    $("#nextContentButton").append(nextContentButton);
					} */
			}
		}
	}

	/**
	 * @summary This is used for adding left menu content for a particular section. 
	 * @param sectionId
	 * @param contentList
	 * @return left meu content list as html
	 */
	var addContent = function(sectionId, contentList) {
		var content = '';
		for (var j = 0; j < contentList.length; j++) {
			content = content
					+ '<li class="treeview" id="treeview'+contentList[j].contentId+'">'
					+ '<a onclick="showContent(\''
					+ sectionId
					+ '\',\''
					+ contentList[j].contentId
					+ '\')" style="cursor:pointer;"><i class="'
					+ loadSubjectIcon(contentList[j].contentType)
					+ '" style="color:#00B06C"></i>'
					+ '<span style="white-space: normal;color:#7d7d7d;" class="capitalize">'
					+ contentList[j].contentName + '</span>' + '</a>' + '</li>';
		}
		return content;
	}

	/**
	 * @summary This is used for adding next button in leftmenu for next section if available. 
	 * @param sectionId
	 * @param contentId
	 * @param action
	 * @return next Button content as html
	 */
	var addNextButton = function(sectionId, contentId, action) {
		var buttonstr = '';
		buttonstr = '<li class="treeview next-previous-section-button list-li-disabled" id="next-section-button">'
				+ '<a onclick="showContent(\''
				+ sectionId
				+ '\',\''
				+ contentId
				+ '\',\''
				+ action
				+ '\');" style="cursor:pointer;text-align:center">'
				+ '<span style="white-space: normal;">'
				+ messages['lbl.nextsection']
				+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-arrow-right"></i></span>'
				+ '</a>' + '</li>';
		return buttonstr;
	}

	/**
	 * @summary This is used for adding previous button in leftmenu for previous section if available. 
	 * @param sectionId
	 * @param contentId
	 * @param action
	 * @return previous button content as html
	 */
	var addPreviousButton = function(sectionId, contentId, action) {
		var buttonstr = '';
		buttonstr = '<li class="treeview next-previous-section-button">'
				+ '<a onclick="showContent(\''
				+ sectionId
				+ '\',\''
				+ contentId
				+ '\',\''
				+ action
				+ '\');" style="cursor:pointer;">'
				+ '<span style="white-space: normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-arrow-left"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
				+ messages['lbl.previoussection'] + '</span>' + '</a>'
				+ '</li>';
		return buttonstr;
	}

	/**
	 * @summary This is used for adding content's icon in leftmenu according to its type. 
	 * @param contentType
	 * @return icon content class as html
	 */
	var loadSubjectIcon = function(contentType) {
		var icon = '';
		switch (contentType) {
		case 'PDF':
			icon = 'fa fa-file-pdf-o';
			break;
		case 'IMAGE':
			icon = 'fa fa-image';
			break;
		case 'VIDEO':
			icon = 'fa fa-file-video-o';
			break;
		case 'PPT':
			icon = 'fa fa-file-powerpoint-o';
			break;
		case 'TEST':
			icon = 'fa fa-file-o';
			break;
		case 'AUDIO':
			icon = 'fa fa-play-circle-o';
			break;
		default:
			icon = 'fa fa-file-o';
		}
		return icon;
	}

	/**
	 * @summary function would be use for open the popup for share the content.
	 */
	var openShareContentPopup = function(path) {
		$("#sharedEmailId").val('');
		$("#sharedEmailId").css("border-color", "");
		$("#emailerror1").fadeOut();
		$("#emailerror2").fadeOut();
		$("#sharedContentPath").val(path);
		$("#sharedContentPopup").modal('show');
	}

	/**
	 * @summary function for validate the email Id
	 */
	function validate() {
		if (document.shareContentForm.sharedEmailId.value.trim() == "") {

			$("#sharedEmailId").css("border-color", "#c95b5b");
			$("#emailerror1").fadeIn();
			document.shareContentForm.sharedEmailId.focus();
			return false;
		} else {
			var email = document.getElementById('sharedEmailId').value;
			var MN = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!MN.test(email)) {
				$("#sharedEmailId").css("border-color", "#c95b5b");
				$("#emailerror2").fadeIn();
				document.shareContentForm.sharedEmailId.focus();
				return false;
			}
		}
		return true;
	}

	/**
	 * @summary function for fading out the error related to shared email Id.
	 */
	function catValidate() {

		if ((document.shareContentForm.sharedEmailId.value.trim()).length > 0) {
			$("#sharedEmailId").css("border-color", "");
			$("#emailerror1").fadeOut();
			$("#emailerror2").fadeOut();
		}
	}

	/**
	 * @summary function for calling ajax for sending mail for shared content to user.
	 */
	function sharedContentAjax() {
		if (validate()) {
			var email = $("#sharedEmailId").val().trim();
			var contentPath = $("#sharedContentPath").val().trim();
			$("#sharedContentPopup").modal('hide');
			$
					.ajax({
						url : 'sendSharedContentLink',
						type : 'POST',
						data : 'email=' + encodeURIComponent(email)
								+ '&contentPath='
								+ encodeURIComponent(contentPath),
						success : function(status) {
							if (status) {
								$("#alertPopup p")
										.text(
												messages['msg.contentsuccessfullyshared']);
								$("#alertPopup").modal('show');
							} else {
								$("#alertPopup p").text(
										messages['msg.somethingwentwrong']);
								$("#alertPopup").modal('show');
							}
						},
						error : function() {
							$("#alertPopup p").text(
									messages['msg.somethingwentwrong']);
							$("#alertPopup").modal('show');
						},

					});
		}
	}

	/**
	 * @summary function for set/reset favoriate to question by user
	 */
	var setresetfavoriate = function(contentId, flag, i, favid) {

		var data = JSON.stringify({
			'contentId' : contentId,
			'userId' : $("#userId").val(),
			'favorites' : flag,
			'favoriateId' : favid
		});

		$.ajax({
			url : "api/setresetfavoriatecontent",
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', "Browser");
			},
			type : 'POST',
			dataType : 'json',
			contentType : "application/json",
			data : data,
			async : false,
			error : function() {
				//alert("error");
			},
			success : function(response) {
				if (response.status == 200) {
					setFavoriteInSectionJson(i, flag);
					if (response.flag == 1) {
						$("#favicon" + i).removeClass(
								"fa fa-heart-o color-mainblue");
						$("#favicon" + i)
								.addClass("fa fa-heart color-mainblue");
						$("#favbtn").attr("onclick", "");
						$("#favbtn").attr(
								"onclick",
								"setresetfavoriate(" + contentId + "," + 0
										+ ",'" + i + "'," + favid + ")");
					} else {
						$("#favicon" + i).removeClass(
								"fa fa-heart color-mainblue");
						$("#favicon" + i).addClass(
								"fa fa-heart-o color-mainblue");
						$("#favbtn").attr("onclick", "");
						$("#favbtn").attr(
								"onclick",
								"setresetfavoriate(" + contentId + "," + 1
										+ ",'" + i + "'," + favid + ")");
					}

				} else {
					$("#errordialog p")
							.text(messages['msg.somethingwentwrong']);
					$("#errordialog").modal('show');
				}
			}
		});
	}

	/**
	 * @summary function for set latest flag in json.
	 */
	var setFavoriteInSectionJson = function(secid_conid, flag) {
		var str = secid_conid.split("_");
		var contentId = Number(str[1]);
		var sectionId = Number(str[0]);
		for (var i = 0; i < sectionJson.length; i++) {
			if (sectionJson[i].sectionId == sectionId) {
				for (var con = 0; con < sectionJson[i].sectionContent.length; con++) {
					if (sectionJson[i].sectionContent[con].contentId == contentId) {
						sectionJson[i].sectionContent[con].favorites = flag;
					}
				}

			}
		}

	}

	/**
	 * @summary function for set latest notes in json.
	 */
	var setAbuseReportInSectionJson = function(secid_conid, text, rid) {
		var str = secid_conid.split("_");
		var contentId = Number(str[1]);
		var sectionId = Number(str[0]);
		for (var i = 0; i < sectionJson.length; i++) {
			if (sectionJson[i].sectionId == sectionId) {
				for (var con = 0; con < sectionJson[i].sectionContent.length; con++) {
					if (sectionJson[i].sectionContent[con].contentId == contentId) {
						sectionJson[i].sectionContent[con].abuseReport = text;
						sectionJson[i].sectionContent[con].reportId = rid;
					}
				}

			}
		}

	}

	/**
	 * @summary function for open add notes modal.
	 */
	var setAbuseReportModal = function(c_id, abc, rid, text) {
		$("#content_id").val('');
		$("#notes").val('');
		$('#reportId').val('');
		$("#notes").css({
			"border-color" : ""
		});
		$("#notes").next('span').remove();
		$("#sec_con").val('');

		$("#notes").val(text);
		$("#content_id").val(c_id);
		$('#reportId').val(rid);
		$("#sec_con").val(abc);
		$("#notesModal").modal('show');
	}

	/**
	 * @summary function for save abuse report notes.
	 */
	var saveAbuseReport = function() {
		var courseId = viewCourseId;
		var data = JSON.stringify({
			'contentId' : $("#content_id").val(),
			'userId' : $("#userId").val(),
			'abuseReport' : $("#notes").val(),
			'reportId' : $("#reportId").val(),
			'courseId' : courseId
		});
		if (validateNotes()) {
			$.ajax({
				url : "api/saveabusereportoncontent",
				beforeSend : function(xhr) { // @summary appending data in header before sending request.
					xhr.setRequestHeader('authorization', 'Browser');
					xhr.setRequestHeader('timestamp', "Browser");
				},
				type : 'POST',
				dataType : 'json',
				contentType : "application/json",
				data : data,
				error : function() {
					alert("error");
				},
				success : function(response) {
					if (response.status == 200) {
						var ss = $("#sec_con").val();
						setAbuseReportInSectionJson(ss, $("#notes").val(),
								response.reportId);
						$("#reporticon" + ss).removeClass(
								"fa fa-flag-o color-mainblue");
						$("#reporticon" + ss).addClass(
								"fa fa-flag color-mainblue");
						$("#reportbtn").attr("onclick", "");
						$("#reportbtn").attr(
								"onclick",
								"setAbuseReportModal(" + $("#content_id").val()
										+ ",'" + ss + "',"
										+ $("#reportId").val() + ",'"
										+ $("#notes").val() + "')");
						$("#notebtnclose").trigger('click');
						$("#successdialog p").text(
								messages['msg.reportsuccessfullysaved']);
						$('#successdialog').modal('show');
					} else {
						$("#notebtnclose").trigger('click');
						$("#errordialog p").text(
								messages['msg.somethingwentwrong']);
						$("#errordialog").modal('show');
					}
				}
			});
		}

	}

	/**
	 * @summary function for validate abuse report.
	 */
	var validateNotes = function() {
		if ($("#notes").val() == "") {
			$('#notes').css({
				"border-color" : "red"
			});
			$('#notes').after(
					"<span class='text-red'>" + messages['msg.empty']
							+ "</span>");
			return false;
		}

		if ($("#notes").val().length > 2000) {
			$('#notes').css({
				"border-color" : "red"
			});
			$('#notes').after(
					"<span class='text-red'>"
							+ messages['msg.maxcharacterlength'].replace(
									'#maxlength', '2000') + "</span>");
			return false;
		}

		return true;
	}

	/**
	 * @summary This is used updating the user activity for course.
	 */
	var updatecourseviewactivity = function() {
		updatecoursecontentviewendtime();
		updatecourseviewendtime();
	}

	/**
	 * @summary This is used updating the view end date for course content.
	 */
	var updatecoursecontentviewendtime = function() {
		if (contentActivityId != null && contentActivityId > 0) {
			var viewContentInfo = JSON.stringify({
				'contentActivityId' : contentActivityId
			});
			$.ajax({
				url : 'api/updateCourseContentViewEndDate',
				beforeSend : function(xhr) { // @summary appending data in header before sending request.
					xhr.setRequestHeader('authorization', 'Browser');
					xhr.setRequestHeader('timestamp', "Browser");
				},
				type : 'POST',
				dataType : 'json',
				contentType : "application/json",
				data : viewContentInfo,
				error : function() {
				},
				success : function(response) {
				}
			});
		}
	}

	/**
	 * @summary This is used updating the course view end time.
	 */
	var updatecourseviewendtime = function() {
		if (courseActivityId != null && courseActivityId > 0) {
			var viewCourseInfo = JSON.stringify({
				'courseActivityId' : courseActivityId
			});
			$.ajax({
				url : 'api/updateCourseViewEndDate',
				beforeSend : function(xhr) { // @summary appending data in header before sending request.
					xhr.setRequestHeader('authorization', 'Browser');
					xhr.setRequestHeader('timestamp', "Browser");
				},
				type : 'POST',
				dataType : 'json',
				contentType : "application/json",
				data : viewCourseInfo,
				error : function() {
				},
				success : function(response) {
				}
			});
		}
	}

	/**
	 * @summary This is used redirect on course detail page.
	 */
	$(document).on(
			'click',
			'.all_course_content',
			function() {
				updatecourseviewactivity();
				location.href = 'studentCourse?action=coursedetail&courseId='
						+ $.trim(viewCourseId);
			});
</script>
</html>