<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<%@ include file="../include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
fieldset.scheduler-border {
	border: 1px groove #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #000;
	box-shadow: 0px 0px 0px 0px #000;
}

legend.scheduler-border {
	font-size: 1.2em !important;
	font-weight: bold !important;
	text-align: left !important;
	width: auto;
	padding: 0 10px;
	border-bottom: none;
}

.list-group-item {
	position: relative;
	display: block;
	padding: 17px 15px;
	margin-bottom: -1px;
	background-color: #fff;
	border: 1px solid #ddd;
}

h3, h4, h5 {
	word-wrap: break-word;
}

.main-header {
	background-color: white;
}

.box-left-margin {
	margin-left: 0px;
}

.pull-right {
	float: right !important;
}

.li-border {
	border-left-color: #eee;
	border-left-style: solid;
	border-left-width: 2px;
}

.btn {
	display: inline-block;
	padding: 6px 25px;
}

.btn-default {
	background-color: white;
}

.headertext {
	white-space: normal;
	color: #7d7d7d;
	text-align: left;
	margin-left: 25%;
}

.name {
	float: left;
	padding-right: 15px;
	color: #7d7d7d;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-bottom-color: #7d7d7d;
}

.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-right: 5px;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-top-color: #f1f1f1;
}

.nav-tabs-custom>.nav-tabs>li.active>a, .nav-tabs-custom>.nav-tabs>li.active:hover>a
	{
	background-color: #ECF0F5;
	color: #444;
}

.nav-tabs-custom>.nav-tabs>li {
	margin-left: 20px;
	margin-right: 20px;
}

.nav-tabs-custom {
	margin-bottom: 20px;
	background: #fff;
	box-shadow: 0 0px 0px rgba(0, 0, 0, 0.1);
	border-radius: 3px;
}

.nav-tabs {
	border-bottom: 0px solid #ddd;
}

#fa {
	padding-right: 15px;
}

.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
}

.sidecontent {
	color: #7d8181;
	font-size: 15px;
}

.form-control {
	border-radius: 0;
	box-shadow: none;
	border-color: #d0d0d0;
}

.skin-black-light .wrapper, .skin-black-light .main-sidebar,
	.skin-black-light .left-side {
	background-color: #f9fafc;
	border: 1px solid #d0d0d0;
	margin-top: 15px;
}

.content-wrapper {
	margin: auto;
	margin-left: 300px;
}

@media only screen and (min-width: 767px) {
	.content-wrapper {
		/* background-color: yellow; */
		customersCtrl margin-left: 230px;
	}
}

@media only screen and (max-width: 760px) {
	.content-wrapper {
		/*  background-color: pink; */
		margin-left: 0px;
	}
}

/* header */
.navbar-nav>.user-menu>.dropdown-menu>li.user-header {
	height: 190px;
	margin-left: -1px;
	margin-right: -1px;
}

/* .main-header .logo {
	min-height: 59px !important;
} */
.sidebar-toggle, .sr-only {
	font-family: fontAwesome !important;
}

.navbar-nav>.user-menu>.dropdown-menu>li.user-header>p {
	font-size: 15px !important;
}

.main-header .logo {
	width: 300px;
}

.main-header>.navbar {
	margin-left: 300px;
}

@media ( max-width : 767px) {
	.main-header .logo, .main-header .navbar {
		width: 100%;
	}
}

@media ( max-width : 767px) {
	.main-header .navbar {
		margin: 0;
	}
}

@media ( max-width : 767px) {
	.skin-black-light .main-header>.logo {
		background-color: #fff;
		border-bottom: 1px solid #EEEEEE;
		border-right: none;
	}
	.skin-black-light .main-header>.logo:hover {
		background-color: #fff;
	}
	.navbar-custom-menu .navbar-nav>li.user-menu>a {
		padding-top: 20px;
		padding-bottom: 30px;
	}
}

/* leftmenu  */
.aside-main-sidebar {
	margin-top: -15px !important;
}

@media ( max-width : 767px) {
	.aside-main-sidebar {
		margin-top: -14px !important;
	}
}

.main-sidebar {
	width: 300px;
	padding-top: 0px;
}

.carousel, .item, .active {
	height: auto !important;
}

.main-sidebar, .left-side {
	min-height: 0%;
	border-bottom: 0px !important;
}

.btn-success-outline {
	background-color: transparent;
	border-color: #ccc;
}
</style>
</head>
<body data-ng-app="scormApp"
	class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper" data-ng-controller="scormcoursecontroller"
		data-ng-init="getScormCourseData()">
		<header class="main-header">
			<!-- Logo -->
			<a class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"> <!-- <b style="color:green;">Q</b> -->
					<img src="<spring:url value='/resources/images/logo-mini.png'/>" />
			</span> <!-- logo for regular state and mobile devices --> <span
				class="logo-lg"><img
					src="<spring:url value='/resources/images/logo.png'/>" /></span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<div class="col-sm-3">
					<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
						role="button"> <span class="sr-only">Toggle navigation</span>
					</a>
				</div>


				<div class="input-group col-sm-4 pull-left hide">
					<input type="text" class="form-control" placeholder="Search...">
					<span class="input-group-addon"><i class="fa fa-search"></i></span>
				</div>


				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown notifications-menu" id="notificationLi"></li>
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img src=""
								onerror="this.src='<spring:url value='/resources/images/icon-admin.png'/>'"
								class="user-image" alt="User Image"> <span
								class="hidden-xs">Ankur Bajwa&nbsp;</span>
						</a></li>
						<!-- Control Sidebar Toggle Button -->


					</ul>
				</div>
			</nav>
		</header>

		<div class="col-sm-2 main-sidebar"
			style="padding-left: 0px; border: 0px solid; height: 0;"
			data-ng-if="isMultipleSco">
			<aside class="main-sidebar aside-main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<ul class="sidebar-menu" id="leftMenuForContent"
						style="background-color: white;">
						<li data-ng-repeat="item in scorm.coursedata.items"
							data-ng-if="scorm.coursedata.items.length > 0" class="treeview">
							<a href="javascript:void(0)"
							data-ng-click="lauchSCOFiles(item,$index)"><i
								class="fa fa-share"></i>{{item.title}}</a>
							<ul class="treeview-menu" data-ng-if="item.items.length>0"
								data-ng-init="itemIndex=$index">
								<li data-ng-repeat="item in item.items"
									data-ng-include="'tree_item_renderer.html'"></li>
							</ul>
						</li>
					</ul>
				</section>
				<!-- /.sidebar -->
			</aside>
		</div>
		<div class="content-wrapper" data-ng-if="isMultipleSco">
			<section class="content-header">
				<h1>
					<button
						class="btn btn-flat btn-width-large pull-right btn-success-outline"
						data-ng-click="redirectoncourselist()">
						<spring:message code="lbl.exitcourse" text="Exit Course" />
					</button>
				</h1>
			</section>
			<!-- Content Header (Page header) -->
			<section class="content">
				<div class="row">
					<div class="tab-content">
						<div class="col-sm-12">
							<fieldset class="scheduler-border">
								<legend class="scheduler-border" id="course_launch_header">{{scorm.coursedata.courseName}}</legend>
								<p style="text-align: center" data-ng-if="iFramelink==null">Please
									make a selection.</p>
								<div class="row control-group" data-ng-if="iFramelink!=null">
									<object style="overflow: hidden; height: 100%; width: 100%;"
										data="{{iFramelink}}" height="100%" width="100%"></object>
								</div>
							</fieldset>
						</div>
					</div>
				</div>
			</section>
		</div>

		<div class="container" data-ng-if="!isMultipleSco">
			<!-- Content Header (Page header) -->
			<section class="content" style="margin-top: 50px;">
				<fieldset class="scheduler-border">
					<legend class="scheduler-border" id="course_launch_header">{{scorm.coursedata.courseName}}</legend>
					<div class="control-group">
						<label class="control-label input-label" id="course_launch_msg">
							Your course is going to launch.</label> <span
							data-ng-repeat="item in scorm.coursedata.items" class="hide"> <a
							data-ng-init="lauchSCOFiles(item,$index)">{{item.title}}</a>
						</span>
					</div>
				</fieldset>
			</section>

		</div>
		<!-- </div> -->
		<input type="hidden" data-ng-value="isMultipleSco"
			id="is_course_has_multiple_sco">
	</div>
	<!-- /.wrapper -->
	<script type="text/ng-template" id="tree_item_renderer.html">
    <a href="javascript:void(0)" data-ng-click="lauchSCOFiles(item,itemIndex + '-' + $index)"><i class="fa fa-circle-o"></i> {{item.title}}</a>
    <ul class="treeview-menu" data-ng-if="item.items.length>0" data-ng-init="itemIndex=itemIndex + '-' + $index">
        <li ng-repeat="item in item.items" ng-include="'tree_item_renderer.html'"></li>
    </ul>
    </script>
	<script src="resources/js/jquery.redirect.js"></script>
	<script src="resources/js/scormcourse/scorm_2004_api.js?v=1"></script>
	<script src="resources/js/scormcourse/scormcourselauncher.js?v=1"></script>
	<script>
		var userId = parseInt($.trim('${userId}'));
		var courseId = parseInt($.trim('${courseId}'));
		$(document)
		.ready(
				function() {
					$(window).on('beforeunload', function() {
						if (WINDOW_OBJECT && !WINDOW_OBJECT.closed) {
							WINDOW_OBJECT.close();
						}
					});
				});
		
		var app = angular.module("scormApp", []);
		app.controller('scormcoursecontroller', scormCourseController);
		/**
		 * @summary This controller is used for controlling the scorm course.
		 */
		function scormCourseController($scope, $http, $location, $window) {
			$scope.scorm;
			$scope.iFramelink;
			$scope.isMultipleSco = false;

			/**
			 * @summary This is used for getting scorm data from server.
			 */
			$scope.getScormCourseData = function() {
				var config = {
					headers : {
						'Authorization' : 'Browser',
						'timestamp' : 'Browser'
					}
				};
				$http.get("api/getscormcoursedata/" + userId + "/" + courseId,
						config).then(successCallback, errorCallback);
				/**
				 * @summary This is used while successfully response.
				 */
				function successCallback(response) {
					var scorm_course_data = response.data;
					$scope.scorm = scorm_course_data;
					$scope.isMultipleSco = scorm_course_data.coursedata.isMultipleSco;
				}
				/**
				 * @summary This is used while error response.
				 */
				function errorCallback(response) {
					console
							.log("error while fetching data from api for scorm course")
				}
			};

			/**
			 * @summary This is used for redirecting on course list.
			 */
			$scope.redirectoncourselist = function() {
				$window.location.href = "studentCourse?action=courseList";
			}

			/**
			 * @summary This is used for launching the sco files for current item on which user has just clicked.
			 * @param item.
			 * @param index_for_single_sco
			 */
			$scope.lauchSCOFiles = function(item, index_for_item) {
				var identifier = item.identifier;//angular.element(item).data('identifier');
				var identifierref = item.identifierref;//angular.element(item).data('identifierref');
				var parameters = item.parameters;//angular.element(item).data('parameters');
				if (identifierref != null && identifierref != '') {
					/**
					 * @summary This is used for getting item sco link from resources based on identifierref.
					 */
					var scofilelink = getSCOFileLinkFromResource(identifierref);
					var scorm_url = $location.$$protocol + "://"
							+ $location.$$host + "/"
							+ $scope.scorm.coursedata.rootDirName + "/"
							+ $scope.scorm.coursedata.scormCoursePath;
					if (scofilelink != null) {
						scorm_url = scorm_url + "/" + scofilelink;
						if (parameters != null && parameters != '') {
							scorm_url = scorm_url + parameters;
						}

						if ($scope.iFramelink != null) {
							var _API = window.API_1484_11;
							/**
							 * if STATE_INITIALIZED ==1 then lms is already initialized.
							 */
							var STATE_INITIALIZED = 1;
							if (_API.currentState === STATE_INITIALIZED) {
								_API.Commit("");
								var _is_terminate = _API.Terminate("");
								if (_is_terminate) {
									closeScormCourse();
									window.API_1484_11 = new API();
								}
							}
						}
						var item_index = index_for_item.toString(); //angular.element(item).data('item-index').toString();
						/**
						 * set the leaner information.
						 */
						setLearnerInfo();
						/**
						 * set the item sequencing information.
						 */
						setScormSequencing(item_index);
						/**
						 * if course has multiple sco files.
						 */
						if ($scope.isMultipleSco) {
							/**
							 * set the iframe link.
							 */
							$scope.iFramelink = scorm_url;

						} else if (!$scope.isMultipleSco && index_for_item == 0) {
							/**
							 * launch single sco type course.
							 */
							lanchScormCourse(scorm_url);
						}
					}
				}
			};

			/**
			 * @summary This is used for getting sco link from resources based on identifierref.
			 * @param identifierref
			 */
			var getSCOFileLinkFromResource = function(identifierref) {
				var link = null;
				try {
					var resources = $scope.scorm.coursedata.resource;
					for (var i = 0; i < resources.length; i++) {
						if (resources[i].identifier != null
								&& resources[i].identifier == identifierref) {
							link = resources[i].href;
							break;
						}
					}
				} catch (err) {
					console.log("error while fetching sco link from resources "
							+ err.message);
				}
				return link;
			};

			/**
			 * @summary This is used set the information about leaner while launching the sco files.
			 */
			var setLearnerInfo = function() {
				var learner = $scope.scorm.userdata;
				if (learner != null) {
					var leaner_id = learner.email;
					var learner_name = learner.firstName + " "
							+ learner.lastName;
					if (leaner_id != null) {
						_ScormProcessSetValue("cmi.learner_id", leaner_id);
					}
					if (learner_name != null && learner_name != ' ') {
						_ScormProcessSetValue("cmi.learner_name", learner_name);
					}

				}

			}
			/**
			 * @summary This is used set the sequencing values for scorm.
			 * @index This would be used findout the index number for current item in item list. 
			 */
			var setScormSequencing = function(index) {
				try {
					var item = [];
					var scorm_course = $scope.scorm.coursedata;
					if (index.indexOf("-") > 0) {
						var index_arr = index.split("-");
						for (var i = 0; i < index_arr.length; i++) {
							if (i == 0) {
								item = scorm_course.items[i];
							} else {
								item = item.items[i];
							}
						}
					} else {
						item = scorm_course.items[index];
					}
					if (item != null && item.sequencing != null
							&& item.sequencing.objectives != null) {
						var scorm_objectives = item.sequencing.objectives;
						if (scorm_objectives.objective != null) {
							var objective_arr = scorm_objectives.objective;
							setObjectiveForApi(objective_arr);
						}
					}
				} catch (err) {
					console
							.log("error while setting the values for item sequencing "
									+ err.message);
				}
			};

			/**
			 * @summary This is used setting the values for objectives for current item.
			 * @param objective_arr This is list of objective.
			 */
			var setObjectiveForApi = function(objective_arr) {
				for (var obj = 0; obj < objective_arr.length; obj++) {
					var intCurrentIndex = _ScormProcessGetValue("cmi.objectives._count");
					_ScormProcessSetValue("cmi.objectives." + intCurrentIndex
							+ ".id", objective_arr[obj].objectiveID);
				}
			}
		}
	</script>
</body>
</html>