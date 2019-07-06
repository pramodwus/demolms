<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/pages/include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
.modeler {
	width: 80%;
	height: 150px;
	margin: 10px;
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

.content-wrapper {
	margin: auto;
	margin-left: 230px;
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

.capitalize {
	text-transform: capitalize;
}

</style>

</head>

<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>

		<%@ include file="/pages/header.jsp"%>
		<%@ include file="/pages/leftmenu.jsp"%>

		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
				<div class="row" id="users">
					<section class="content-header">
						<div class="col-sm-2">
							<label class="h3 form-group"> <spring:message
									code="lbl.courses" text="Courses" />
							</label>
						</div>
						<div style="background-color: #ECF0F5;"
							class="nav-tabs-custom col-sm-12">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="active button-width"
									style="margin-right: 0px; text-align: center;"><a
									href="#publishedCourseList" data-toggle="tab"><spring:message
											code="lbl.current" text="Current" /></a></li>
								<li class="button-width"
									style="margin-left: 0px; text-align: center;"><a
									href="#upcomingCourseList" data-toggle="tab"><spring:message
											code="lbl.upcoming" text="Upcoming" /></a></li>
							</ul>
						</div>
					</section>
					<!-- Main content -->
					<section class="content">
						<div class="tab-content"
							style="background-color: #ECF0F5 !important;"
							data-ng-app="courseListApp">
							<!-- current Course list -->
							<div id="publishedCourseList" class="tab-pane active"
								data-ng-controller="currentCourseController" data-ng-Cloak>
								<div id="publishedCourse" class="list col-sm-12">

									<div class="col-sm-12 form-group"
										style="background-color: white;"
										data-ng-repeat="course in currentcourselist">
										<div class="col-sm-3">
											<div>
												<img class="modeler" data-ng-src="{{course.courseImageUrl}}"
													onerror="this.src='resources/images/Trignometry.jpg'">
											</div>
										</div>
										<div class="col-sm-9">
											<div>
												<h3 class="capitalize">{{course.courseName}}</h3>
												<h5>{{course.courseTag}}</h5>
												<h5>
													<spring:message code="lbl.by" text="by"/>
													&nbsp;{{course.teacherName}}
												</h5>
												<h5>{{courseDescFilter(course.courseDesc)}}</h5>
											</div>
											<button class="btn btn-success button-width btn-flat"
												type="button" style="float: right;"
												data-ng-click="coursepreviewpage(course.courseId)">
												<spring:message code="lbl.preview" text="Preview" />
											</button>
											<div class="nav-tabs-custom" style="background-color: #FFF">
												<ul class="nav nav-tabs">
													<li><h5>
															<spring:message code="lbl.language" text="Language" />
															&nbsp;: {{course.languageName}}
														</h5></li>
													<li><h5>
															<spring:message code="lbl.level" text="Level" />
															&nbsp;: {{course.levelName}}
														</h5></li>
												</ul>
											</div>
										</div>
									</div>


								</div>
								<div class="col-sm-12 form-inline" style="min-height: 50px">
									<ul class="pull-left" id="publishedrecord"
										style="line-height: 5; list-style-type: none; padding: 0px"
										data-ng-show="totalcurrententryshowing!=null">
										<li id="paginationentryincurrent">{{totalcurrententryshowing}}</li>
									</ul>
									<ul id="published-pagination-demo" class="pull-right"></ul>
								</div>
							</div>
							<!-- /.current Course list -->

							<!-- upcoming Course list -->
							<div id="upcomingCourseList" class="tab-pane"
								data-ng-controller="upcomingCourseController" data-ng-Cloak>
								<div id="upcomingCourse" class="list col-sm-12">

									<div class="col-sm-12 form-group"
										style="background-color: white;"
										data-ng-repeat="course in upcomingcourselist">
										<div class="col-sm-3">
											<div>
												<img class="modeler" data-ng-src="{{course.courseImageUrl}}"
													onerror="this.src='resources/images/Trignometry.jpg'">
											</div>
										</div>
										<div class="col-sm-9">
											<div>
												<h3 class="capitalize">{{course.courseName}}</h3>
												<h5>{{course.courseTag}}</h5>
												<h5>
													<spring:message code="lbl.by" text="by" />
													&nbsp;{{course.teacherName}}
												</h5>
												<h5>{{courseDescFilter(courseDesc)}}</h5>
											</div>
											<div class="nav-tabs-custom" style="background-color: #FFF">
												<ul class="nav nav-tabs">
													<li><h5>
															<spring:message code="lbl.language" text="Language" />
															&nbsp;: {{course.languageName}}
														</h5></li>
													<li><h5>
															<spring:message code="lbl.level" text="Level" />
															&nbsp;: {{course.levelName}}
														</h5></li>
													<li><h5>
															<spring:message code="lbl.publishdate"
																text="Publish Date" />
															&nbsp;:&nbsp;{{course.schedulePublishDate}}
														</h5></li>
												</ul>
											</div>
										</div>
									</div>

								</div>
								<div class="col-sm-12 form-inline" style="min-height: 50px">
									<ul class="pull-left" id="upcomingrecord"
										style="line-height: 5; list-style-type: none; padding: 0px"
										data-ng-show="totalupcomingentryshowing!=null">
										<li id="paginationentryinupcoming">{{totalupcomingentryshowing}}</li>
									</ul>
									<ul id="upcoming-pagination-demo" class="pull-right"></ul>
								</div>
							</div>
							<!-- /.upcoming Course list -->
						</div>


					</section>
				</div>
			</div>
			<!-- content-wrapper -->
		</div>
	</div>
	<!-- ./wrapper -->
</body>
<script src="<spring:url value='/resources/js/list.min.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
<script>
	/**
	 * @summary id of user.
	 */
	var userId = <%=((User)request.getSession().getAttribute("userSession")).getUserId()%>
	;
	/**
	 * @summary global variable for showing index of first course on a particular page.
	 */
	var startPageNumber = 1;
	/**
	 * @summary global variable for showing index of first upcoming course on a particular page.
	 */
	var startPageNumberforUpcoming = 1;
	/**
	 *@summary getting total number of published courses.
	 */
	var totalCourse = '${totalCourse}';
	
	/**
	 *@summary getting total number of upcoming courses.
	 */
	var totalUpcomingCourse = '${upcomingCourse}';
	/**
	 * @summary function would be call when body would be load.
	 * @return no
	 */
	$(function() {
		$(".treeview").removeClass("active");
		$("#course").addClass("active");
		$("#studentCourse").addClass("active");
		/**
		 * @summary giving the limit for showing record per page.
		 */
		var pagelimit = 10;
		/**
		 * @summary getting how much pages are required for showing all record of published course.
		 */
		var totalpage = totalCourse.length > 0 ? Math.ceil(totalCourse
				/ pagelimit) : 0;
		/**
		 * @summary calling function for appending course detail on body load and page click.
		 */
		$('#published-pagination-demo')
				.twbsPagination(
						{
							totalPages : totalpage, //@summary  giving no of pages for showing.
							visiblePages : 2, //@summary  number of limit for visible pages
							first : paggingmessages['lbl.first'],
							prev : paggingmessages['lbl.previous'],
							next : paggingmessages['lbl.next'],
							last : paggingmessages['lbl.last'],
							/**
							 * @summary call function on page click
							 */
							onPageClick : function(event, page) {
								try {
									/**
									 * @summary  assign index of first page on page click.
									 */
									startPageNumber = (page - 1) * pagelimit
											+ 1
									/**
									 * @summary start loader  in taking time for loading data.
									 */
									$("#overlay").show();
									/**
									 * @summary call function for getting course list.
									 */
									angular.element(document.getElementById('publishedCourseList'))
											.scope().getcurrentcourselist(page - 1, startPageNumber);
									/**
									 * @summary show showing record div
									 */
									$("#publishedrecord").show();

									/**
									 * @summary hide the loader.
									 */
									$("#overlay").hide();

								} catch (err) {
									$("#overlay").hide();
									console.log(err.message);
								}

							}
						});

		/**
		 * @summary getting how much pages are required for showing all record of upcoming course.
		 */
		var upcomingtotalpage = totalUpcomingCourse.length > 0 ? Math
				.ceil(totalUpcomingCourse / pagelimit) : 0;
		/**
		 * @summary calling function for appending course detail on body load and page click.
		 */
		$('#upcoming-pagination-demo')
				.twbsPagination(
						{
							totalPages : upcomingtotalpage, //@summary  giving no of pages for showing.
							visiblePages : 2, //@summary  number of limit for visible pages
							first : paggingmessages['lbl.first'],
							prev : paggingmessages['lbl.previous'],
							next : paggingmessages['lbl.next'],
							last : paggingmessages['lbl.last'],
							/**
							 * @summary call function on page click
							 */
							onPageClick : function(event, page) {
								try {
									/**
									 * @summary  assign index of first page on page click.
									 */
									startPageNumber = (page - 1) * pagelimit
											+ 1
									/**
									 * @summary start loader  in taking time for loading data.
									 */
									$("#overlay").show();
									/**
									 * @summary call function for getting course list.
									 */
									angular.element(document.getElementById('upcomingCourseList'))
											.scope().getupcomingcourselist(page - 1, startPageNumber);
									/**
									 * @summary show showing record div
									 */
									$("#upcomingrecord").show();
									/**
									 * @summary hide the loader.
									 */
									$("#overlay").hide();

								} catch (err) {
									$("#overlay").hide();
									console.log(err.message);
								}

							}
						});
	});
</script>
<script>
	/**
	 * @summary This is used redirect on course preview page.
	 * @param courseId
	 * @returns no.
	 */
	function coursepreviewpage(courseId) {
		if (courseId != null) {
			location.href = 'studentCourse?action=coursedetail&courseId='
					+ courseId;
		}
	}
	/**
	 * @summary define the application.
	 */
	var app = angular.module('courseListApp', []);
	/**
	 * @summary define the controller for current and upcoming course list.
	 */
	app.controller('currentCourseController', currentCourseController);
	app.controller('upcomingCourseController', upcomingCourseController);

	/**
	 * @summary This controller is used for controlling of getting list of current course.
	 * @$scope
	 * @$http
	 */
	function currentCourseController($scope, $http) {
		$scope.courseDescFilter = function(courseDesc) {
			if (courseDesc != null) {
				courseDesc = courseDesc.length > 320 ? courseDesc.substring(0,
						320)
						+ '........' : courseDesc;
			}
			return courseDesc;
		};
		$scope.coursepreviewpage = coursepreviewpage;
		$scope.getcurrentcourselist = function(offset, startpage) {
			$http.get("api/courselist/" + userId + "/" + offset, {
						headers : {
							'authorization' : 'Browser',
							'timestamp' : 'Browser'
						}
					})
					.then(
							function(response) {
								try {
									$scope.currentcourselist = response.data.courselist;
									/**
									 * @summary set showing entry
									 */
									$scope.totalcurrententryshowing = paggingmessages['lbl.paginationentrymsg']
											.replace('#firstentry', startpage)
											.replace(
													'#totalcurrectpageentry',
													startpage
															+ response.data.courselist.length
															- 1).replace(
													'#totalentry', totalCourse);
								} catch (err) {
									$("#overlay").hide();
									console.log(err.message);
								}
							});
		}
	}

	/**
	 * @summary This controller is used for controlling of getting list of upcoming course.
	 * @$scope
	 * @$http
	 */
	function upcomingCourseController($scope, $http) {
		$scope.courseDescFilter = function(courseDesc) {
			if (courseDesc != null) {
				courseDesc = courseDesc.length > 320 ? courseDesc.substring(0,
						320)
						+ '........' : courseDesc;
			}
			return courseDesc;
		};
		$scope.getupcomingcourselist = function(offset, startpage) {
			$http.get("api/upcomingcourselist/" + userId + "/" + offset, {
						headers : {
							'authorization' : 'Browser',
							'timestamp' : 'Browser'
						}
					})
					.then(
							function(response) {
								try {
									$scope.upcomingcourselist = response.data.courselist;
									/**
									 * @summary set showing entry
									 */
									$scope.totalupcomingentryshowing = paggingmessages['lbl.paginationentrymsg']
											.replace('#firstentry', startpage)
											.replace(
													'#totalcurrectpageentry',
													startpage
															+ response.data.courselist.length
															- 1).replace(
													'#totalentry',
													totalUpcomingCourse);
								} catch (err) {
									$("#overlay").hide();
									console.log(err.message);
								}
							});
		}
	}
</script>
</html>