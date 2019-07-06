<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
 
 
<html lang="en">
<head>

<%@ include file="/pages/include.jsp"%>
<%@ include file="/pages/headerNew.jsp"%> 

<style>
.nav-tabs-custom>.nav-tabs{
	border-bottom:none;
}

.nav-tabs-custom {
    margin-bottom: 20px;
    box-shadow: none;
    border-radius: 3px;
}
.nav-tabs-custom{
	box-shadow: none;
}
.navbar {
    position: relative;
    min-height: 50px;
    margin-bottom: 0px;
    border: 1px solid transparent;
</style>

</head>

<body class="p-0">




	<div class="wrapper">
		<%-- <div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div> --%>

		

		<!-- start dataTable----->
		<div class="col-sm-12">

				<!-- Content Header (Page header) -->
				<div class="row" id="users">
					
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
										<div class="col-sm-9 pt-5">
										<button class="btn btn-success button-width btn-flat"
												type="button" style="float: right;    padding: 10px 22px;    font-size: 1.5rem;"
												data-ng-click="coursepreviewpage(course.courseId)">
												<spring:message code="lbl.preview" text="Preview" />
											</button>
											<div>
												<h1 class="capitalize">{{course.courseName}}</h1>
												<h3>{{course.courseTag}}</h3>
												<h4>
													<spring:message code="lbl.by" text="by"/>
													&nbsp;{{course.teacherName}}
												</h4>
												<h3>{{courseDescFilter(course.courseDesc)}}</h3>
												
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
												</ul>
											</div>
										</div>
									</div>


								</div>
								<div class="col-sm-12" style="min-height: 50px">
									<ul class="pull-left" id="publishedrecord"
										style="line-height: 5; list-style-type: none; padding: 0px"
										data-ng-show="totalcurrententryshowing!=null">
										<li id="paginationentryincurrent">{{totalcurrententryshowing}}</li>
									</ul>
									<ul id="published-pagination-demo" class="pull-right"></ul>
								</div>
							</div>
							<!-- /.current Course list -->
						</div>


					</section>
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
	 var userId = '${sessionScope.userSession.userId}';
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
</script>

</html>