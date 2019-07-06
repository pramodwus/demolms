<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<script>
	var currentpagemenuid = 'course';
</script>
<%@ include file="include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
.list-group-item {
	position: relative;
	display: block;
	padding: 17px 15px;
	margin-bottom: -1px;
	background-color: #fff;
	border: 1px solid #ddd;
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

#sider {
	height: auto;
	background-color: #FFFFFF;
	border: 1px solid;
	margin-top: 15px;
}

.btn1 {
	display: inline-block;
	padding: 10px 30px;
	float: right;
	margin-top: 15px;
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

.modeler {
	width: 80%;
	height: 150px;
	margin: 10px;
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
</style>


</head>

<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>

		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper"
				style="overflow-x: hidden; overflow-y: auto;" id="users">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<h1>
						<spring:message code="lbl.courses" text="Courses" />
						<span class="pull-right">
						<%-- <button id="upload_scorm_course" type="button"
							class="btn btn-flat btn-success button-width-large"
							onclick="location.href='uploadscormcourse'">
							<spring:message code="lbl.uploadscormcourse" text="Upload Scorm Course"/>
						</button> --%>
						&nbsp;&nbsp;<button id="addnew" type="button"
							class="btn btn-flat btn-success button-width-large"
							onclick="opencreatemodal()">
							<spring:message code="lbl.addnewcourse" text="Add New Course" />
						</button>
						</span>
					</h1>
					<div style="padding-top: 10px">
						<div style="background-color: #ECF0F5;" class="nav-tabs-custom">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="active button-width-large"
									style="margin-right: 0px; text-align: center;"><a
									href="#publishedCourse" data-toggle="tab"><spring:message
											code="lbl.published" text="Published" /></a></li>
								<li class="button-width-large"
									style="margin-right: 0px; text-align: center;"><a
									href="#scheduledCourse" data-toggle="tab"><spring:message
											code="lbl.scheduled" text="Scheduled" /></a></li>
								<li class="button-width-large"
									style="margin-left: 0px; text-align: center;"><a
									href="#draftedCourse" data-toggle="tab"><spring:message
											code="lbl.drafted" text="Drafted" /></a></li>
							</ul>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<!-- published course -->
					<div class="tab-content"
						style="background-color: #ECF0F5 !important;"
						data-ng-app="courseListApp">
						<div id="publishedCourse" class="tab-pane active"
							data-ng-controller="publishedCourseController" data-ng-Cloak>
							<div style="background-color: white;"
								class="col-sm-12 form-group"
								data-ng-repeat="publishedCourse in publishedcourselist">
								<div class="col-sm-3">
									<!--style="background-color:pink;">-->
									<div>
										<img class="modeler"
											data-ng-src="{{publishedCourse.courseImageUrl}}"
											onerror="this.src='<spring:url value='/resources/images/Trignometry.jpg'/>'">
									</div>
								</div>
								<div class="col-sm-9">
									<!--style="background-color:aqua;"-->
									<div>
										<h3>{{publishedCourse.courseName}}</h3>
										<h5>{{publishedCourse.courseTag}}</h5>
										
										<h5>
											<spring:message code="lbl.numberofenroll"
												text="Number of Enroll" />
											: {{publishedCourse.enrollCount}}
										</h5>
									</div>
												<button type="button" class="btn btn-success"
										data-ng-click="viewcoursepreview(publishedCourse.courseId,publishedCourse.courseType)"
										style="float: right;">
										<spring:message code="lbl.preview" text="Preview" />
									</button>
									<div style="background-color: #FFF" class="nav-tabs-custom">
										<ul class="nav nav-tabs">
											<li>
												<h5>
													<spring:message code="lbl.language" text="Language" />
													: {{publishedCourse.languageName}}
												</h5>
											</li>
											
											<li>
												<h5>
													<spring:message code="lbl.difficultylevel"
														text="Difficulty Level" />
													: {{publishedCourse.levelName}}
												</h5>
											</li>
											<li data-ng-if="publishedCourse.courseType==0"><button type="button"
													data-ng-click="EditPublishedCourseMaterial(publishedCourse.courseId)"
													class="btn btn-default button-width-large">
													<spring:message code="lbl.editcontent" text="Edit Content" />
												</button></li>
										</ul>
									</div>
								</div>
							</div>

							<div class="col-sm-12 form-inline"
								style="min-height: 50px; padding: 0px">
								<ul class="pull-left" id="publishedcourserecord"
									style="line-height: 5; list-style-type: none; padding: 0px;"
									data-ng-show="totalpublishentryshowing!=null">
									<li id="paginationentryinpublished">{{totalpublishentryshowing}}</li>
								</ul>
								<ul id="published-pagination-demo" class="pull-right" style=""></ul>
							</div>
						</div>
						<!-- /.publishedCourse -->

						<!-- scheduled Course -->
						<div id="scheduledCourse" class="tab-pane"
							data-ng-controller="scheduledCourseController" data-ng-Cloak>
							<div style="background-color: white;"
								class="col-sm-12 form-group"
								data-ng-repeat="scheduledCourse in scheduledcourselist">
								<div class="col-sm-3">
									<!--style="background-color:pink;">-->
									<div>
										<img class="modeler"
											data-ng-src="{{scheduledCourse.courseImageUrl}}"
											onerror="this.src='<spring:url value='/resources/images/Trignometry.jpg'/>'">
									</div>
								</div>
								<div class="col-sm-9">
									<!--style="background-color:aqua;"-->
									<div>
										<h3>{{scheduledCourse.courseName}}</h3>
										<h5>{{scheduledCourse.courseTag}}</h5>
										<h5>
											<spring:message code="lbl.numberofenroll"
												text="Number of Enroll" />
											: {{scheduledCourse.enrollCount}}
										</h5>
										<h5>
											<spring:message code="lbl.publishdate" text="Publish Date" />
											: {{scheduledCourse.schedulePublishDate}}
										</h5>
									</div>
									<button type="button" class="btn btn-success"
										data-ng-click="viewcoursepreview(scheduledCourse.courseId,scheduledCourse.courseType)"
										style="float: right;">
										<spring:message code="lbl.preview" text="Preview" />
									</button>
									<div style="background-color: #FFF" class="nav-tabs-custom">
										<ul class="nav nav-tabs">
											<li>
												<h5>
													<spring:message code="lbl.language" text="Language" />
													: {{scheduledCourse.languageName}}
												</h5>
											</li>
											<li>
												<h5>
													<spring:message code="lbl.difficultylevel"
														text="Difficulty Level" />
													: {{scheduledCourse.levelName}}
												</h5>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-12 form-inline"
								style="min-height: 50px; padding: 0px">
								<ul class="pull-left" id="scheduledcourserecord"
									style="line-height: 5; list-style-type: none; padding: 0px;"
									data-ng-show="totalscheduleentryshowing!=null">
									<li id="paginationentryinscheduled">{{totalscheduleentryshowing}}</li>
								</ul>
								<ul id="scheduled-pagination-demo" class="pull-right" style=""></ul>
							</div>
						</div>
						<!-- /.scheduled Course -->

						<!--DraftedCourse -->
						<div id="draftedCourse" class="tab-pane"
							data-ng-controller="draftedCourseController" data-ng-Cloak>
							<div style="background-color: white;"
								class="col-sm-12 form-group"
								data-ng-repeat="draftedCourse in draftedcourselist">
								<div class="dropdown pull-right">
									<a id="dLabel" data-target="#" data-toggle="dropdown"
										role="button" aria-haspopup="true" aria-expanded="false"
										class="icon-dropdown"> <img
										src="resources/adminlte/dist/img/ellipsis-h.png">
									</a>
									<ul class="dropdown-menu dropdown-menu-right "
										aria-labelledby="dlabel">
										<li><a class="cursor-pointer"
											data-ng-click="deletecourse(draftedCourse.courseId);"><spring:message
													code="lbl.delete" text="Delete" /></a></li>
									</ul>
								</div>
								<div class="col-sm-3">
									<!--style="background-color:pink;">-->
									<div>
										<img class="modeler" data-ng-src="{{draftedCourse.courseImageUrl}}"
											onerror="this.src='<spring:url value='/resources/images/Trignometry.jpg'/>'">
									</div>
								</div>
								<div class="col-sm-9">
									<!--style="background-color:aqua;"-->
									<div>
										<h3>{{draftedCourse.courseName}}</h3>
										<h4>{{draftedCourse.subTitle}}</h4>
										<h5>
											<spring:message code="lbl.lastupdate" text="Last Update" />
											: {{draftedCourse.courseModifyDate}}
										</h5>
									</div>

									<div class="nav-tabs-custom" style="background-color: #FFF">
										<ul class="nav nav-tabs">
											<li><button type="button"
													data-ng-click="createCourse(draftedCourse.courseId,draftedCourse.courseType)"
													class="btn btn-default  button-width-large">
													&emsp; <i class="fa fa-circle-thin"
														data-ng-if="draftedCourse.courseDesc==null"></i> <i
														class="fa fa-check-circle-o color-green"
														data-ng-if="draftedCourse.courseDesc!=null"></i>
													<spring:message code="lbl.addinfo" text="Add Info" />
													&emsp;
												</button></li>
											<li data-ng-if="draftedCourse.courseType==0"><button type="button"
													data-ng-click="addEditCourseMaterial(draftedCourse.courseId)"
													<%-- onclick="location.href='addEditCourseMaterial?courseId=${draftedCourse.courseId}'" --%>
														class="btn btn-default button-width-large">
													<i class="fa fa-circle-thin"
														data-ng-if="draftedCourse.courseInfo=='0 Content'"></i> <i
														class="fa fa-check-circle-o color-green"
														data-ng-if="draftedCourse.courseInfo!='0 Content'"></i>
													<spring:message code="lbl.addcontent" text="Add Content" />
												</button></li>
											<li>
												<button type="button"
													data-ng-click="viewcoursepreview(draftedCourse.courseId,draftedCourse.courseType)"
													class="btn btn-success button-width-large"
													data-ng-if="draftedCourse.courseInfo!='0 Content' || (draftedCourse.courseType==1 && draftedCourse.courseDesc!=null)">
													<spring:message code="lbl.previewandpublish"
														text="Preview & Publish" />
												</button>

												<button type="button"
													class="btn btn-default button-width-large"
													data-ng-if="draftedCourse.courseInfo=='0 Content' && (draftedCourse.courseType==0 || (draftedCourse.courseType==1 && draftedCourse.courseDesc==null))">
													<spring:message code="lbl.previewandpublish"
														text="Preview & Publish" />
												</button>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-12 form-inline"
								style="min-height: 50px; padding: 0px">
								<ul class="pull-left" id="draftedcourserecord"
									style="line-height: 5; list-style-type: none; padding: 0px;"
									data-ng-show="totaldraftentryshowing!=null">
									<li id="paginationentryinscheduled">{{totaldraftentryshowing}}</li>
								</ul>
								<ul id="drafted-pagination-demo" class="pull-right"></ul>
							</div>

							<!-- /.draftedCourse -->
						</div>

					</div>

				</section>


			</div>
			<!-- content-wrapper -->
			<!-- End dataTable----->
			<div class="control-sidebar-bg"></div>
		</div>
		<!-- ./wrapper -->
	</div>
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4></h4>
				</div>
				<div>
					<img src="images/qq.png" style="width: 100%">
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade in" id="createModal">
		<div class="modal-dialog modal-md">
			<div class="modal-content" style="background-color: #ECF0F5;">
				<div class="modal-header" style="border-bottom-width: 0px;">
					<button aria-label="Close" data-dismiss="modal"
						onclick="onclosemodal();" class="close" type="button">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body" id="addDiv">
					<div style="text-align: center" class="form-group">
						<img src="resources/adminlte/dist/img/course.png"
							onerror="this.src='/qbis_spring/resources/dist/img/course.png'">
					</div>
					<div class="form-group" id="courseName_div">
						<label><sup><font color="red" size="3px">*</font></sup> <spring:message
								code="lbl.coursetitle" text="Course Title" /> : </label> <input
							type="text" id="courseName" class="form-control"
							placeholder="<spring:message code="placeholder.coursetitle" text="Enter course title"/>"
							maxlength="50">
					</div>
					<div class="form-group">
						<label><spring:message code="lbl.subtitle"
								text="Sub Title" /> : </label> <input type="text" id="subTitle"
							class="form-control"
							placeholder="<spring:message code="placeholder.subtitle" text="Enter sub title"/>"
							maxlength="50">
					</div>
					<div class="modal-footer"
						style="text-align: center; border-top-width: 0px;">
						<button type="button" class="btn  bg-green"
							onclick="savecoursetitle();">
							<spring:message code="lbl.createcourse" text="Create Course" />
						</button>
					</div>
				</div>
				<div class="modal-body hide" id="successDiv">
					<div style="text-align: center" class="form-group">
						<img src="resources/adminlte/dist/img/course1.png">
					</div>
					<div class="" id="courseName_div" style="text-align: center">
						<label class="h2"><spring:message
								code="lbl.congratulation" text="Congratulation" />!</label>
					</div>
					<div class="form-group" id="courseName_div"
						style="text-align: center">
						<label class="h3"><spring:message
								code="msg.coursecreatedsuccessfully"
								text="Your course is created successfully. Add content & information about course." /></label>
					</div>

					<div style="text-align: center; border-top-width: 0px;"
						class="modal-footer">
						<input type="hidden" id="course_id">
						<div class="col-md-6">
							<a onclick="createcoursepage('tab1');" href="#"> <img
								src="resources/adminlte/dist/img/courseinfo.png">
							</a>
							<div class="h3">
								<spring:message code="lbl.addbasicinfo" text="Add Basic Info" />
							</div>
						</div>
						<div class="col-md-6">
							<a onclick="createcoursepage('tab2');" href="#"> <img
								src="resources/adminlte/dist/img/coursecontent.png"></a>
							<div class="h3">
								<spring:message code="lbl.addcontent" text="Add Content" />
							</div>
						</div>
					</div>
					<div class="row"
						style="margin-bottom: -15px; background-color: #212121; text-align: center; color: white">
						<spring:message code="msg.coursemanage"
							text="This course is now added to your draft. You can manage it from there at anytime." />
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Start of Alert box for delete test -->
	<div class="modal fade" id="deleteAlert" tabindex="-1" role="dialog"
		aria-labelledby="deletetestAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p>
							<spring:message code="msg.course.delete"
								text="Are you sure to delete this course?" />
						</p>
						<button type="button" class="btn btn-default button-width"
							style="" data-dismiss="modal">
							<spring:message code="lbl.no" text="No" />
						</button>
						<button type="button" id="yesbtn"
							class="btn btn-success button-width">
							<spring:message code="lbl.yes" text="Yes" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Alert box -->
	<%@ include file="dialogs.jsp"%>
</body>
<script type="text/javascript">
	var messages = new Array();
	messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true'  text='Something went wrong,Please try again.'/>";
	messages['msg.empty'] = "<spring:message code='msg.empty' javaScriptEscape='true'  text='This field is required.'/>";
	messages['msg.maxlength200'] = "<spring:message code='msg.maxlength200' javaScriptEscape='true' text='Maximum 200 characters allowed.'/>";
</script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
<script src="<spring:url value='/resources/js/list.min.js'/>"></script>
<script>
	/**
	 * @summary global variable for showing first index of published course on a particular page.
	 */
	var startPageNumberForPublished = 1;
	/**
	 * @summary global variable for showing index of first scheduled course on a particular page.
	 */
	var startPageNumberforScheduled = 1;
	/**
	 * @summary global variable for showing index of first drafted course on a particular page.
	 */
	var startPageNumberforDrafted = 1;
	/**
	 *@summary getting total number of published course.
	 */
	var totalPublishedCourse = '${totalPublishedCourse}';
	/**
	 *@summary getting total number of scheduled course.
	 */
	var totalScheduledCourse = '${totalScheduledCourse}';
	/**
	 *@summary getting total number of drafted course.
	 */
	var totalDraftedCourse = '${totalDraftedCourse}';
	/**
	 * @summary giving the limit for showing record per page.
	 */
	var pagelimit = 5;
	/**
	 * @summary getting how much pages are required for showing all record of published course.
	 */
	var totalpageforpublished = totalPublishedCourse.length > 0 ? Math
			.ceil(totalPublishedCourse / pagelimit) : 0;
	/**
	 * @summary getting how much pages are required for showing all record of scheduled course.
	 */
	var totalpageforscheduled = totalScheduledCourse.length > 0 ? Math
			.ceil(totalScheduledCourse / pagelimit) : 0;
	/**
	 * @summary getting how much pages are required for showing all record of drafted course.
	 */
	var totalpagefordrafted = totalDraftedCourse.length > 0 ? Math
			.ceil(totalDraftedCourse / pagelimit) : 0;

	$(function() {
		$(".treeview").removeClass("active");
		$("#course").addClass("active");
		$("#course .treeview-menu > #course").addClass("active");
		$('.imgset img').imgCentering({
			'forceWidth' : true,
		});
		var userStatus = '${userStatus==0}';
		if (userStatus == 'true') {
			$("#addnew").attr("disabled", "disabled");
			$(".callout").removeClass('hide');
		}
		/**
		 * @summary calling function for appending published course detail on body load and page click.
		 */
		try {
			$('#published-pagination-demo').twbsPagination(
					{
						totalPages : totalpageforpublished,
						visiblePages : 2,
						first : paggingmessages['lbl.first'],
						prev : paggingmessages['lbl.previous'],
						next : paggingmessages['lbl.next'],
						last : paggingmessages['lbl.last'],
						onPageClick : function(event, page) {
							/**
							 * @summary  assign index of first page on page click.
							 */
							startPageNumberforPublished = (page - 1)
									* pagelimit + 1;
							angular.element(
									document.getElementById('publishedCourse'))
									.scope().getpagelist(page,
											startPageNumberforPublished);
						}
					});
		} catch (err) {
			console.log(err.message);
		}
		/**
		 * @summary calling function for appending scheduled course detail on body load and page click.
		 */
		try {
			$('#scheduled-pagination-demo').twbsPagination(
					{
						totalPages : totalpageforscheduled,
						visiblePages : 2,
						first : paggingmessages['lbl.first'],
						prev : paggingmessages['lbl.previous'],
						next : paggingmessages['lbl.next'],
						last : paggingmessages['lbl.last'],
						onPageClick : function(event, page) {
							/**
							 * @summary  assign index of first page on page click.
							 */
							startPageNumberforScheduled = (page - 1)
									* pagelimit + 1;
							angular.element(
									document.getElementById('scheduledCourse'))
									.scope().getpagelist(page,
											startPageNumberforScheduled);
						}
					});
		} catch (err) {
			console.log(err.message);
		}
		/**
		 * @summary calling function for appending drafted course detail on body load and page click.
		 */
		try {
			$('#drafted-pagination-demo').twbsPagination(
					{
						totalPages : totalpagefordrafted,
						visiblePages : 2,
						first : paggingmessages['lbl.first'],
						prev : paggingmessages['lbl.previous'],
						next : paggingmessages['lbl.next'],
						last : paggingmessages['lbl.last'],
						onPageClick : function(event, page) {
							/**
							 * @summary  assign index of first page on page click.
							 */
							startPageNumberforDrafted = (page - 1) * pagelimit
									+ 1;
							angular.element(
									document.getElementById('draftedCourse'))
									.scope().getpagelist(page,
											startPageNumberforDrafted);
						}
					});
		} catch (err) {
			console.log(err.message);
		}
	});
</script>
<script>
	function changeCourseStatus(object) {
		if (object.id == "publishButton") {
			$("#publishButton").removeClass("btn-default");
			$("#publishButton").addClass("btn btn-primary button-blue");
			$("#draftButton").removeClass("btn-primary button-blue");
			$("#draftButton").addClass("btn btn-default");
			$("#draftededCourse").hide();
			$("#publishedCourse").show();
			$("#draftededCourse").removeClass();
			$("#publishedCourse").addClass("list");
		}
		if (object.id == "draftButton") {
			$("#draftButton").removeClass("btn-default");
			$("#draftButton").addClass("btn btn-primary button-blue");
			$("#publishButton").removeClass("btn-primary button-blue");
			$("#publishButton").addClass("btn btn-default");
			$("#publishedCourse").hide();
			$("#draftededCourse").show();
			$("#publishedCourse").removeClass();
			$("#draftededCourse").addClass("list");
		}
	}

	var opencreatemodal = function() {
        location.href="createCourse";
		/* $.ajax({
			url : "createNewCourseLicesneValidate",
			type : 'GET',
			async : false,		
			error : (function() {
				alert("server error");
			}),
			success : function(data) {
				if(data){						
					$("body").css("overflow", "hidden");
					$("#createModal").fadeIn();			
				}else{
					$("#warningdialog p").text("Your organization can create maximum 10 courses.");
					$("#warningdialog").modal('show') ;
					$("#addnew").attr("disabled",true);
					//$("#calloutdiv").removeClass('hide');
				}
			}
		 }); */
		//$("body").css("overflow", "hidden");
		//$("#createModal").fadeIn();

	}

	var savecoursetitle = function() {
		clearformerror();
		if (validatecourse()) {
			$('#overlay').show();
			var data = JSON.stringify({
				'subTitle' : $("#subTitle").val(),
				'courseName' : $("#courseName").val()
			});

			$.ajax({
				url : "savecoursetitle",
				type : 'POST',
				dataType : 'json',
				contentType : "application/json",
				data : data,
				error : function() {
					alert("error");
				},
				success : function(response) {
					$('#overlay').hide();
					$("#addDiv").addClass('hide');
					$("#course_id").val(response.courseId);
					$("#successDiv").removeClass('hide');
				}
			});

		}

	}

	var onclosemodal = function() {
		location.href = "courselist";
	}

	var createcoursepage = function(tab) {
		if (tab == "tab1")
			location.href = "createCourse?courseId=" + $("#course_id").val();
		else
			location.href = "addEditCourseMaterial?courseId="
					+ $("#course_id").val();
	}

	var deletecourse = function(id) {
		$("#yesbtn").attr('onclick', 'ajaxDelete(' + id + ')');
		$('#deleteAlert').modal('show');
		//location.href="deleteuploadedcontent?id="+id;
	}

	var viewcoursepreview = function(courseId, publishStatus,courseType) {
		if(courseType==1){
			location.href = 'scormcoursepreview?courseId=' + courseId
			+ '&isPublish=' + publishStatus;
		}
		else
			{
		location.href = 'courseViewController?courseId=' + courseId
				+ '&isPublish=' + publishStatus;
			}
	}

	var addEditCourseMaterial = function(courseId) {
		location.href = 'addEditCourseMaterial?courseId=' + courseId;
	}
	var EditPublishedCourseMaterial = function(courseId) {
		location.href = 'addEditCourseMaterial?courseId=' + courseId;
	} 
	var createCourse = function(courseId,courseType) {
		if(courseType==1){
			location.href = 'addeditscormcourse?courseId=' + courseId;
		}
		else{
		location.href = 'createCourse?courseId=' + courseId;
		}
	}
	var ajaxDelete = function(id) {

		$.ajax({
			url : "deletedraftedcourse?courseId=" + id,
			type : 'POST',
			error : function() {
				$("#overlay").hide();
				$("#contentErrorAlert p").text(
						messages['msg.somethingwentwrong']);
				$("#contentErrorAlert").modal('show');
			},
			success : function(status) {
				//$("#overlay").hide();
				if (status) {
					location.href = 'courselist';
				}
			}
		});

	}

	var validatecourse = function() {
		var flag = 0;
		if ($("#courseName").val() == "") {
			$('#courseName').after(
					'<span class="text-red">' + messages['msg.empty']
							+ '</span>');
			$('#courseName').css({
				"border-color" : "red",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			flag = 1;
		}
		if ($("#courseName").val().length > 200) {
			$('#courseName').after(
					'<span class="text-red">' + messages['msg.maxlength200']
							+ '</span>');
			$('#courseName').css({
				"border-color" : "red",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			flag = 1;
		}
		if ($("#subTitle").val() != "") {
			if ($("#subTitle").val().length > 200) {
				$('#subTitle').after(
						'<span class="text-red">'
								+ messages['msg.maxlength200'] + '</span>');
				$('#subTitle').css({
					"border-color" : "red",
					"border-style" : "solid",
					"border-width" : "1px"
				});
				flag = 1;
			}
		}
		if (flag == 1)
			return false;
		else
			return true;
	}

	var clearformerror = function() {
		$("#courseName").css({
			"border-color" : ""
		});
		$("#courseName").next('span').remove();
		$("#subTitle").css({
			"border-color" : ""
		});
		$("#subTitle").next('span').remove();
	}
</script>
<script>
	var app = angular.module('courseListApp', []);
	app.controller('publishedCourseController', publishedCourseController);
	app.controller('scheduledCourseController', scheduledCourseController);
	app.controller('draftedCourseController', draftedCourseController);

	/**
	 * @summary This is used getting list of published Course.
	 * @$scope
	 * @$http
	 */
	function publishedCourseController($scope, $http) {
		$scope.viewcoursepreview = function(courseId,courseType) {
			viewcoursepreview(courseId, 1,courseType);
			
		};
		$scope.EditPublishedCourseMaterial = function(courseId) {
			EditPublishedCourseMaterial(courseId);
		}; 
		$scope.getpagelist = function(page, startpage) {
			$http
					.get(
							"courselistwithpagination?courseStatus=" + 1
									+ "&limit=" + pagelimit + "&offset="
									+ (page - 1))
					.then(
							function(response) {
								$scope.publishedcourselist = response.data.courselist;
								/**
								 * @summary set showing entry
								 */
								$scope.totalpublishentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.courselist.length
														- 1).replace(
												'#totalentry',
												totalPublishedCourse);
							});
		};
	}

	/**
	 * @summary This is used getting list of scheduled Course.
	 * @$scope
	 * @$http
	 */
	function scheduledCourseController($scope, $http) {
		$scope.viewcoursepreview = function(courseId,courseType) {
			viewcoursepreview(courseId, 2,courseType);
		};
		$scope.getpagelist = function(page, startpage) {
			$http
					.get(
							"courselistwithpagination?courseStatus=" + 2
									+ "&limit=" + pagelimit + "&offset="
									+ (page - 1))
					.then(
							function(response) {
								$scope.scheduledcourselist = response.data.courselist;
								/**
								 * @summary set showing entry
								 */
								$scope.totalscheduleentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.courselist.length
														- 1).replace(
												'#totalentry',
												totalScheduledCourse);
							});
		};
	}

	/**
	 * @summary This is used getting list of drafted course.
	 * @$scope
	 * @$http
	 */
	function draftedCourseController($scope, $http) {
		debugger;
		$scope.deletecourse = deletecourse;
		$scope.createCourse = createCourse;
		$scope.addEditCourseMaterial = addEditCourseMaterial;
		$scope.viewcoursepreview = function(courseId, courseType) {
			viewcoursepreview(courseId, 0, courseType);
		};
		$scope.getpagelist = function(page, startpage) {
			$http
					.get(
							"courselistwithpagination?courseStatus=" + 0
									+ "&limit=" + pagelimit + "&offset="
									+ (page - 1))
					.then(
							function(response) {
								$scope.draftedcourselist = response.data.courselist;
								/**
								 * @summary set showing entry
								 */
								$scope.totaldraftentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.courselist.length
														- 1).replace(
												'#totalentry',
												totalDraftedCourse);
							});
		};
	}
</script>
</html>