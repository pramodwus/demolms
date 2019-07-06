<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<c:import url="/WEB-INF/studentinclude.jsp" />
<style>
/*.main-header {
    position: relative;
    max-height: 100px;
    z-index: 1030;
    border-bottom: 1px solid #eeeeee;
}  */
.list-group-item {
	position: relative;
	display: block;
	padding: 17px 15px;
	margin-bottom: -1px;
	background-color: #fff;
	border: 1px solid #ddd;
}

input[type=text] {
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
/*input[type=text]:focus {
    width: 100%;
}*/
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<header class="main-header">
			<!-- Logo -->


			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<!--<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                </a>-->
				<div class="navbar-custom-menu">
					<a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
						<span class="logo-mini"><b>Q</b></span> <!-- logo for regular state and mobile devices -->
						<span class="logo-lg" style="color: #7d7d7d;"><b>Qbis</b></span>
					</a>
					<ul class="nav navbar-nav">
						<!-- Messages: style can be found in dropdown.less-->


						<li>
							<form>
								<input type="text" name="search"
									placeholder="Search Courses/Test/Categories/Tags">
							</form>
						</li>

						<!-- User Account: style can be found in dropdown.less -->
						<li class="user user-menu" style=""><a
							style="pointer-events: none;"> <span class="hidden-xs"
								style="float: left; padding-right: 15px; color: #7d7d7d;"><%=session.getAttribute("email")%></span>
								<img src="adminlte/dist/img/user2-160x160.jpg" onerror="this.src='images/icon-admin.png'"
								class="user-image" alt="User Image">
						</a></li>

						<li class="dropdown user user-menu"><a
							style="margin-left: -20px" href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-caret-down"
								style="color: #7d7d7d;"></i></a>

							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header" style="background-color: #DEDEDE">
									<img src="adminlte/dist/img/user2-160x160.jpg" onerror="this.src='images/icon-admin.png'"
									class="img-circle" alt="User Image">

									<p style="color: black">
										<%=session.getAttribute("email")%>
									</p>
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" data-toggle="dropdown"
											class="btn btn-default btn-flat dropdown-toggle">Cancel</a>
									</div>
									<div class="pull-right">
										<a href="logout" class="btn btn-default btn-flat">Sign out</a>
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
						<p style="color: #7d7d7d; font-size: 15px; font-weight: 400;">
							<span class="fa fa-chevron-left"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;All
							Courses Content
						</p>
						<!-- <a href="#"><i class="fa fa-circle text-success"></i> Online/a>-->
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
				<li class="list-group-item"><div id="CourseContentName"
						style="text-align: center"></div></li>
				<li>
					<div
						style="width: 60%; height: 350px; margin-top: 5%; margin-left: 20%; margin-bottom: 5%; border: 1px solid #DEDEDE; background-color: #FFFFFF;"
						id="frameLoad"></div>
				</li>
				<li class="list-group-item" style="border: 0px">

					<div
						style="border: 1px solid #eeeeee; height: 50px; width: 70%; margin: 30px 0% 40px 15%;"
						class="hide"></div>
					<div id="nextContentButton"></div>
				</li>

			</ul>

		</div>

	</div>
	<!-- ./wrapper -->
</body>
<script>
	/**
	 * @summary Instance for json object.
	 * 
	 */
	var sectionJson = ${jsonsectionlist};

	/**
	 * @summary calling on document loading
	 */
	$(document).ready(
			function() {
				showContent(sectionJson[0].sectionId,
						sectionJson[0].sectionContent[0].contentId,
						'sectionChange');
			});
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
					var str = '<li class="header"><i class="fa  fa-align-left"></i>&nbsp;&nbsp;&nbsp;&nbsp;'
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
				$("#CourseContentName").html(
						sectionContentList[con].contentName
								+ ' (icon stand for '
								+ sectionContentList[con].contentType + ')');
				var frameHTML = '';
				switch (sectionContentList[con].contentType) {
				case 'PDF':
				case 'AUDIO':
				case 'VIDEO':
					frameHTML = '<iframe src="'
							+ sectionContentList[con].contentPath
							+ '" onerror="this.src=\'images/body.jpg\'"style="width:100%;height:100%" allowfullscreen webkitallowfullscreen></iframe>';
					break;

				case 'IMAGE':
					frameHTML = '<img src="'
							+ sectionContentList[con].contentPath
							+ '" style="width: 100%;height:100%">';
					break;

				case 'AUDIO1':
					frameHTML = '<audio  style="text-align:center" controls><source src="'+sectionContentList[con].contentPath+'" type="audio/mpeg">Your browser does not support the audio element.</audio>';
					break;

				case 'TEST':
					frameHTML = '<div class="col-sm-12">'
							+ '<div class="modal-body">'
							+ '<h1 class="form-group">Test</h1>'
							+ '<p>'
							+ sectionContentList[con].contentName
							+ '</p>'
							+ '<div class="col-xs-12" style="min-height: 30px"></div>'
							+ '<button type="button" class="btn btn-flat btn-lg btn-success" onclick="window.open(\'student?action=instruct&testId='
							+ sectionContentList[con].content
							+ '\', \'\', \'width=1000,height=500,scrollbars=yes,menubar=no, resizable=yes,toolbar=no,location=no,status=no\'\)">Start Test</button>'
							+ '</div>' + '</div>';
					break;
				}
				$("#frameLoad").append(frameHTML);
				$("#nextContentButton").empty();
				if (con !== sectionContentList.length - 1) {
					var nextContentButton = '<div style="cursor:pointer;border:1px solid #eeeeee;height:50px;width:70%;margin: 30px 0% 40px 15%;" onclick="showContent(\''
							+ sectionId
							+ '\',\''
							+ sectionContentList[con + 1].contentId
							+ '\');">'
							+ '<p style="white-space: normal;color:#7d7d7d;text-align:center;line-height: 50px;" >'
							+ sectionContentList[con + 1].contentName
							+ ' (icon stand for '
							+ sectionContentList[con + 1].contentType
							+ ')&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-chevron-right"></span></p>'
							+ '</div>';
					$("#nextContentButton").append(nextContentButton);
				}
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
					+ '<a onclick="showContent(\'' + sectionId + '\',\''
					+ contentList[j].contentId
					+ '\')" style="cursor:pointer;"><i class="'+loadSubjectIcon(contentList[j].contentType)+'" style="color:#00B06C"></i>'
					+ '<span style="white-space: normal;color:#7d7d7d;">'
					+ contentList[j].contentName + ' (icon stand for '
					+ contentList[j].contentType + ')</span>' + '</a>'
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
		buttonstr = '<li class="treeview">'
				+ '<a onclick="showContent(\''
				+ sectionId
				+ '\',\''
				+ contentId
				+ '\',\''
				+ action
				+ '\');" style="cursor:pointer;text-align:center">'
				+ '<span style="white-space: normal;color:#7d7d7d;">Next Section&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-arrow-right"></i></span>'
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
		buttonstr = '<li class="treeview">'
				+ '<a onclick="showContent(\''
				+ sectionId
				+ '\',\''
				+ contentId
				+ '\',\''
				+ action
				+ '\');" style="cursor:pointer;">'
				+ '<span style="white-space: normal;color:#7d7d7d;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-arrow-left"></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Previous Section</span>'
				+ '</a>' + '</li>';
		return buttonstr;
	}
	
	var loadSubjectIcon = function(contentType){
		var icon='';
		switch (contentType){
		case 'PDF':
			icon='fa fa-file-pdf-o';
			break;
		case 'IMAGE':
			icon='fa fa-image';
			break;
		case 'VIDEO':
			icon='fa fa-file-video-o';
			break;
		case 'PPT':
			icon = 'fa fa-file-powerpoint-o';
			break;
		case 'TEST':
			icon = 'fa  fa-file-o';
			break;
		case 'AUDIO':
			icon = 'fa fa-play-circle-o';
			break;
		default:
			icon = 'fa  fa-file-o';
		}
		return icon;
	}
</script>
</html>