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
	background-image: url('searchicon.png');
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

.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

@media only screen and (min-width: 767px) {
	.content-wrapper {
		/* background-color: yellow; */
		margin-left: 230px;
	}
}

@media only screen and (max-width: 760px) {
	.content-wrapper {
		/*  background-color: pink; */
		margin-left: 0px;
	}
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

img.lazy.loading , img.lazy-first_ppt.loading{
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
	border-top:0px;
}

#courseName .course-name {
	font-size: 18px;
}

.list-group-item-first-div {
	border: 1px solid #eeeeee;
	height: 50px;
	width: 70%;
	margin: 30px 0% 40px 15%;
}

.text-align-center {
	text-align: center;
}

.no-visibility {
	visibility: hidden
}

.user-panel>.info {
   left: 0px;
}

@media (max-width: 1010px){
     body{
	    min-width:1010px;
	    overflow-x: scroll;
        }
     }
        
@media (max-width: 767px){
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
	     margin:0px 0px 0px 230px;
	    }	
	.main-header .logo, .main-header .navbar {
	     width: initial;
	   }	
	.main-header .logo{
	     width: 230px;
	  }
}
</style>
<script type="text/javascript">
	var timeList = [];
	var setTimeoutId = "";
</script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
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
						<li class="user user-menu"><a class="no-pointer-events"><span
								class="hidden-xs user-menu-user-name"> <%=((User) request.getSession().getAttribute("userSession"))
					.getFirstName() == "" ? ((User) request.getSession()
					.getAttribute("userSession")).getEmail() : ((User) request
					.getSession().getAttribute("userSession")).getFirstName()
					+ "  "
					+ ((User) request.getSession().getAttribute("userSession"))
							.getLastName() + " "%></span> <img
								src="<%=((User) request.getSession().getAttribute("userSession"))
					.getImage()%>"
								onerror="this.src='resources/images/icon-admin.png'"
								class="user-image" alt="User Image"> </a></li>

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
									</p></li>
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
						<c:if test="${origin}">
							<a class="all_course_content"
								href="addEditCourseMaterial?courseId=${course.courseId}"> <span
								class="fa fa-chevron-left"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<spring:message code="lbl.allcourseContent"
									text="All Course Content" />
							</a>
						</c:if>
						<c:if test="${!origin}">
							<a class="all_course_content"
								href="courseViewController?courseId=${course.courseId}&isPublish=${course.publish}">
								<span class="fa fa-chevron-left"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<spring:message code="lbl.allcourseContent"
									text="All Course Content" />
							</a>
						</c:if>
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
				<li class="list-group-item"><div id="courseName"
						class="capitalize text-align-center">
						<b class="course-name">${course.courseName}</b>&nbsp;
					</div></li>
				<li class="list-group-item-margin-top">
					<div id="frameLoad"></div>
				</li>
				<li class="list-group-item no-border">

					<div class="hide list-group-item-first-div"></div>
					<div id="nextContentButton"></div>
				</li>

			</ul>

		</div>

	</div>
	<!-- ./wrapper -->
</body>
<script>
	var messages = new Array();
	messages['lbl.iconstandfor'] = "<spring:message code='lbl.iconstandfor' text='icon stand for #filetype' arguments='#filetype' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
	messages['lbl.assessment'] = "<spring:message code='lbl.assessment' text='Assessment' javaScriptEscape='true'/>";
	messages['lbl.startassessment'] = "<spring:message code='lbl.startassessment' text='Start Assessment' javaScriptEscape='true'/>";
	messages['lbl.nextsection'] = "<spring:message code='lbl.nextsection' text='Next Section' javaScriptEscape='true'/>";
	messages['lbl.previoussection'] = "<spring:message code='lbl.previoussection' text='Previous Section' javaScriptEscape='true'/>";

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
<script src="resources/js/videocontroll.js?v=6"></script>
<!-- <script src="resources/js/blazy.min.js"></script> -->
<script>
	var vid;
	var aud;
	try {
		/**
		 * @summary Empty Instance for json object.
		 * 
		 */
		var sectionJson = ${sectionList}
		;
		var audioList;
		var contentId = '${contentId}';
		/**
		 * @summary calling on document loading
		 */
		$(document).ready(function() {
			if (sectionJson.length != 0) {
				var sectionId = findSectionId(contentId);
				if (sectionId > 0) {
					showContent(sectionId, contentId, 'sectionChange');
				}
			}
		});

		/**
		 * @summary This is used for finding section id based on content id.
		 * @return sectionId;
		 */
		var findSectionId = function(contId) {
			var sectionId = 0;
			for (var section = 0; section < sectionJson.length; section++) {
				for (var c = 0; c < sectionJson[section].sectionContent.length; c++) {
					if (sectionJson[section].sectionContent[c].contentId == contId) {
						sectionId = sectionJson[section].sectionId;
						break;
					}
				}

			}
			return sectionId;
		}

		/**
		 * @summary This is used for fetching all related data from json object 
		 * @param sectionId
		 * @param contentId
		 * @param action
		 * @return no
		 */
		var showContent = function(sectionId, contentId, action) {
			for (var i = 0; i < sectionJson.length; i++) {
				if (sectionJson[i].sectionId == sectionId) {
					if (action == 'sectionChange') {
						$("#leftMenuForContent").empty();
						$("#frameLoad").empty();
						var str = '<li class="header capitalize"><i class="fa fa-align-left"></i>&nbsp;&nbsp;&nbsp;&nbsp;'
								+ sectionJson[i].sectionName
								+ '</li>'
								+ addContent(sectionJson[i].sectionId,
										sectionJson[i].sectionContent)
								+ (i == 0 ? ''
										: addPreviousButton(
												sectionJson[i - 1].sectionId,
												sectionJson[i - 1].sectionContent.length == 0 ? ''
														: sectionJson[i - 1].sectionContent[0].contentId,
												'sectionChange'))
								+ (i == (sectionJson.length - 1) ? ''
										: addNextButton(
												sectionJson[i + 1].sectionId,
												sectionJson[i + 1].sectionContent.length == 0 ? ''
														: sectionJson[i + 1].sectionContent[0].contentId,
												'sectionChange'));
						$("#leftMenuForContent").append(str);
					}
					loadContent(sectionJson[i].sectionContent, contentId,
							sectionJson[i].sectionId);
				}
			}
		}

		/**
		 * @summary This is used for showing content on page based on content id and content's type. 
		 * @param sectionContentList
		 * @param contentId
		 * @param sectionId
		 * @return no
		 */
		var loadContent = function(sectionContentList, contentId, sectionId) {
			for (var con = 0; con < sectionContentList.length; con++) {
				if (sectionContentList[con].contentId == contentId) {
					$("#frameLoad").empty();
					$(".treeview").removeClass('active');
					$("#treeview" + contentId).addClass('active');
					var frameHTML = '';
					switch (sectionContentList[con].contentType) {
					case 'PDF':
						/* frameHTML = '<iframe src="'
							+ sectionContentList[con].contentPath
							+ '" onerror="this.src=\'resources/images/body.jpg\'" style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>'; */
						frameHTML = createPDFQuestionUI(
								sectionContentList[con], 1);
						break;
					case 'AUDIO':
						frameHTML = '<iframe src="'
								+ sectionContentList[con].contentPath
								+ '" onerror="this.src=\'resources/images/body.jpg\'" style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>';
						break;
					case 'VIDEO':
						if (sectionContentList[con].questionList.length == 0) {
							frameHTML = '<iframe src="'
									+ sectionContentList[con].contentPath
									+ '" onerror="this.src=\'resources/images/body.jpg\'" style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>';
						} else {
							frameHTML = createVideoQuestionUI(
									sectionContentList[con], 1);
						}
						break;

					case 'IMAGE':
						frameHTML = '<img src="'
								+ sectionContentList[con].contentPath
								+ '" style="width: 100%;height:100%" onerror="this.src=\'resources/images/c-png.png\'">';
						break;

					case 'AUDIO1':
						frameHTML = '<audio  style="text-align:center" controls><source src="'+sectionContentList[con].contentPath+'" type="audio/mpeg">Your browser does not support the audio element.</audio>';
						break;

					case 'TEST':
						var URL = sectionContentList[con].contentPath
								.includes("action=frame&") ? sectionContentList[con].contentPath
								.replace("action=frame&", "")
								: sectionContentList[con].contentPath;
						frameHTML = '<div class="col-sm-12">'
								+ '<div class="modal-body">'
								+ '<h1 class="form-group">'
								+ messages['lbl.assessment']
								+ '</h1>'
								+ '<p>'
								+ sectionContentList[con].contentName
								+ '</p>'
								+ '<div class="col-xs-12" style="min-height: 30px"></div>'
								+ '<button type="button" class="btn btn-flat btn-lg btn-success" onclick="window.open(\''
								+ URL
								+ '\', \'\', \'width=1000,height=450,scrollbars=yes,menubar=no, resizable=yes,toolbar=no,location=no,status=no\'\)">'
								+ messages['lbl.startassessment'] + '</button>'
								+ '</div>' + '</div>';
						break;
					case 'PPT':
						audioList = sectionContentList[con].questionList;
						frameHTML = createPPTVoiceUI(sectionContentList[con]);
						/* var frameURl='http://docs.google.com/viewerng/viewer?url='+sectionContentList[con].contentPath+'&embedded=true';
						frameHTML = '<iframe src="'+frameURl+'" style="width:100%;height:100%" frameborder="0" allowfullscreen webkitallowfullscreen></iframe>'; */
						break;
					case 'URL':
						frameHTML = '<div class="col-xs-12" style="text-align: center; padding-top: 75px;" id="promoVideoDiv"><div id="promoVideo" style="width:80% !important;height:450px !important"></div></div>'
						break;
					}
					$("#frameLoad").append(frameHTML);
					if (sectionContentList[con].contentType == "URL") {
						try {
							playVideo(sectionContentList[con].content);
						} catch (err) {
							console.log(err.message);
						}
					}

					if (sectionContentList[con].contentType == "VIDEO") {
						try {
							timeList = [];
							for (var i = 0; i < parseInt($("#questionCount")
									.val()); i++) {
								timeList
										.push(parseInt($("#timeslot" + i).val()));
							}
							vid = document.getElementById("myVideo");
							vid.addEventListener("play", function() {
								setTimeForQuestionAtCourse();
							}, false);
							vid.addEventListener("pause", function() {
								clearInterval(setTimeoutId);
							}, false);
							vid.addEventListener("seekable", function() {
								vid.pause();
								setTimeForQuestionAtCourse();
							}, false);
							$(
									'input[type="checkbox"].flat-red, input[type="radio"].flat-red')
									.iCheck(
											{
												checkboxClass : 'icheckbox_square-green',
												radioClass : 'iradio_square-green'
											});
						} catch (err) {
							console.log(err.message);
						}
					}

					if (sectionContentList[con].contentType == "PDF") {
						try {
							var _totalpages =  $("#contentPages").val();
							if(_totalpages!=null && _totalpages == 1){
								$("#next").prop('disabled',true);
							}
							timeList = [];
							for (var i = 0; i < parseInt($("#questionCount")
									.val()); i++) {
								timeList
										.push(parseInt($("#timeslot" + i).val()));
							}
							$(
									'input[type="checkbox"].flat-red, input[type="radio"].flat-red')
									.iCheck(
											{
												checkboxClass : 'icheckbox_square-green',
												radioClass : 'iradio_square-green'
											});
						} catch (err) {
							console.log(err.message);
						}
					}

					if (sectionContentList[con].contentType == "PPT"
							|| sectionContentList[con].contentType == "PPTX") {
					 try {	
						var _totalpages =  $("#contentPages").val();
						if(_totalpages!=null && _totalpages == 1){
							$("#next").prop('disabled',true);
						}
						aud = document.getElementById("myaudio");
						aud.addEventListener("ended", function() {
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
					$("#nextContentButton").empty();
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
						+ contentList[j].contentName + '</span>' + '</a>'
						+ '</li>';
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
			buttonstr = '<li class="treeview next-previous-section-button">'
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
	} catch (err) {
		console.log(err);
	}
</script>
</html>