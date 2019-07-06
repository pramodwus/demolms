<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<script>
	var currentpagemenuid = 'test';
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
		customersCtrl margin-left: 230px;
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
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						<spring:message code="lbl.leftmenu.assessments" />
						<button id="addnew" class="btn btn-flat btn-success pull-right"
							onclick="createNewTest()">
							<spring:message code="lbl.addnewassessment" />
						</button>
					</h1>
					<div style="padding-top: 10px">
						<div style="background-color: #ECF0F5;" class="nav-tabs-custom">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="active button-width-large"
									style="margin-right: 0px; text-align: center;"><a
									href="#publishedTest" data-toggle="tab"><spring:message
											code="lbl.published" /></a></li>
								<li class="button-width-large"
									style="margin-right: 0px; text-align: center;"><a
									href="#scheduledTest" data-toggle="tab"><spring:message
											code="lbl.scheduled" /></a></li>
								<li class="button-width-large"
									style="margin-left: 0px; text-align: center;"><a
							testList		href="#draftedTest" data-toggle="tab"><spring:message
											code="lbl.drafted" /></a></li>
							</ul>
						</div>
					</div>
				</section>
				<section class="content">
					<div class="tab-content"
						style="background-color: #ECF0F5 !important;" data-ng-app="testListApp">
						<div id="publishedTest" class="tab-pane active"
							data-ng-controller="publishedTestController" data-ng-Cloak>
							<div style="background-color: white;"
								class="col-sm-12 form-group"
								data-ng-repeat="publishedTest in publishedtestlist">
								<div class="col-sm-3">
									<!--style="background-color:pink;">-->
									<div>
										<img class="modeler" data-ng-src="{{publishedTest.testIconUrl}}"
											onerror="this.src='<spring:url value='/resources/images/Trignometry.jpg'/>'">
									</div>
								</div>
								<div class="col-sm-9">
									<!--style="background-color:aqua;"-->
									<div>
										<h3>{{publishedTest.testName}}</h3>
										<h5>{{publishedTest.testTag}}</h5>
										<h5>
											<spring:message code="lbl.date" />
											: {{publishedTest.testCreatedDate}}
										</h5>
										<h5>
											<spring:message code="lbl.traineeattempts" />
											: {{publishedTest.totalAppearStudents}}
										</h5>
									</div>
									<div class="nav-tabs-custom" style="background-color: #FFF">
										<ul class="nav nav-tabs">
											<li>
												<button type="button"
													class="btn btn-success btn-flat button-width-large"
													data-ng-click="viewtestpage(publishedTest.testId)">
													<spring:message code="lbl.preview" />
												</button>
											</li>
											<li>
												<button type="button"
													class="btn btn-success btn-flat button-width-large"
													data-ng-click="redirectongivetestpage(publishedTest.testId)">
													<spring:message code="lbl.startassessment" />
												</button>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-12 form-inline"
								style="min-height: 50px; padding: 0px">
								<ul class="pull-left" id="publishedtestrecord"
									style="line-height: 5; list-style-type: none; padding: 0px;"
									data-ng-show="totalpublishentryshowing!=null">
									<li id="paginationentryinpublished">{{totalpublishentryshowing}}</li>
								</ul>
								<ul id="published-pagination-demo" class="pull-right" style=""></ul>
							</div>
						</div>
						<!-- /.publishedCourse -->

						<!-- Scheduled Test -->
						<div id="scheduledTest" class="tab-pane"
							data-ng-controller="scheduledTestController" data-ng-Cloak>
							<div style="background-color: white;"
								class="col-sm-12 form-group"
								data-ng-repeat="scheduledTest in scheduledtestlist">
								<div class="col-sm-3">
									<!--style="background-color:pink;">-->
									<div>
										<img class="modeler" data-ng-src="{{scheduledTest.testIconUrl}}"
											onerror="this.src='<spring:url value='/resources/images/Trignometry.jpg'/>'">
									</div>
								</div>
								<div class="col-sm-9">
									<!--style="background-color:aqua;"-->
									<div>
										<h3>{{scheduledTest.testName}}</h3>
										<h5>{{scheduledTest.testTag}}</h5>
										<h5>
											<spring:message code="lbl.date" />
											: {{scheduledTest.testCreatedDate}}
										</h5>
										<h5>
											<spring:message code="lbl.publishdate" />
											: {{scheduledTest.schedulePublishDate}}
										</h5>
									</div>
									<div class="nav-tabs-custom" style="background-color: #FFF">
										<ul class="nav nav-tabs">
											<li>
												<button type="button"
													class="btn btn-success btn-flat button-width-large"
													data-ng-click="viewtestpage(scheduledTest.testId)">
													<spring:message code="lbl.preview" />
												</button>
											</li>
											<li></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-12 form-inline"
								style="min-height: 50px; padding: 0px">
								<ul class="pull-left" id="scheduledtestrecord"
									style="line-height: 5; list-style-type: none; padding: 0px;"
									data-ng-show="totalscheduleentryshowing!=null">
									<li id="paginationentryinscheduled">{{totalscheduleentryshowing}}</li>
								</ul>
								<ul id="scheduled-pagination-demo" class="pull-right" style=""></ul>
							</div>
						</div>
						<!-- /.Scheduled Test -->

						<!--DraftedCourse -->
						<div id="draftedTest" class="tab-pane"
							data-ng-controller="draftedTestController" data-ng-Cloak>

							<div style="background-color: white;"
								class="col-sm-12 form-group"
								data-ng-repeat="draftedTest in draftedtestlist">
								<div class="dropdown pull-right">
									<a id="dLabel" data-target="#" data-toggle="dropdown"
										role="button" aria-haspopup="true" aria-expanded="false"
										class="icon-dropdown"> <img
										src="resources/adminlte/dist/img/ellipsis-h.png"> <!--  <i class=" fa fa-ellipsis-h"></i> -->
									</a>
									<ul class="dropdown-menu dropdown-menu-right "
										aria-labelledby="dlabel">
										<li><a class="cursor-pointer"
											data-ng-click="deleteDraftedTest(draftedTest.testId)"><spring:message
													code="lbl.delete" /></a></li>
									</ul>
								</div>
								<div class="col-sm-3">
									<!--style="background-color:pink;">-->
									<div>
										<img class="modeler" data-ng-src="{{draftedTest.testIconUrl}}"
											onerror="this.src='<spring:url value='/resources/images/Trignometry.jpg'/>'">
									</div>
								</div>
								<div class="col-sm-9">
									<!--style="background-color:aqua;"-->
									<div>
										<h3>{{draftedTest.testName}}</h3>
										<h5>{{draftedTest.testTag}}</h5>
										<h5>
											<spring:message code="lbl.date" />
											: {{draftedTest.testCreatedDate}}
										</h5>
									</div>

									<div class="nav-tabs-custom" style="background-color: #FFF">
										<ul class="nav nav-tabs">
											<li><button type="button"
													data-ng-click="redirectaddedittestpage(draftedTest.testId)"
													class="btn btn-default  button-width-large">
													&emsp; <i class="fa fa-check-circle-o color-green"></i>
													<spring:message code="lbl.addinfo" />
													&emsp;
												</button></li>
											<li>
												<button type="button"
													data-ng-click="viewtestpage(draftedTest.testId)"
													class="btn btn-success button-width-large">
													<spring:message code="lbl.preview" />
												</button>
											</li>
										</ul>
									</div>
								</div>
							</div>

							<div class="col-sm-12 form-inline"
								style="min-height: 50px; padding: 0px">
								<ul class="pull-left" id="draftedtestrecord"
									style="line-height: 5; list-style-type: none; padding: 0px;"
									data-ng-show="totaldraftentryshowing!=null">
									<li id="paginationentryindrafted">{{totaldraftentryshowing}}</li>
								</ul>
								<ul id="drafted-pagination-demo" class="pull-right" style=""></ul>
							</div>
							<!-- /.draftedCourse -->
						</div>

					</div>
				</section>
			</div>
		</div>
		<!-- content-wrapper -->
		<!-- End dataTable----->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<!-- Start of Alert box for delete test -->
	<div class="modal fade" id="deletetestAlert" tabindex="-1"
		role="dialog" aria-labelledby="deletetestAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p>
							<spring:message code="msg.assessment.delete" />
						</p>
						<button type="button" class="btn btn-default button-width"
							style="" data-dismiss="modal">
							<spring:message code="lbl.no" />
						</button>
						<a id="dId"><button type="button"
								class="btn btn-success button-width">
								<spring:message code="lbl.yes" />
							</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Alert box -->
	<%@ include file="dialogs.jsp"%>
</body>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
<script>
	/**
	 * @summary global variable for showing first index of published test on a particular page.
	 */
	var startPageNumberForPublished = 1;
	/**
	 * @summary global variable for showing index of first scheduled test on a particular page.
	 */
	var startPageNumberforScheduled = 1;
	/**
	 * @summary global variable for showing index of first drafted test on a particular page.
	 */
	var startPageNumberforDrafted = 1;
	/**
	 *@summary getting total number of published test.
	 */
	var totalPublishedTest = '${totalPublishedTest}';
	/**
	 *@summary getting total number of scheduled test.
	 */
	var totalScheduledTest = '${totalScheduledTest}';
	/**
	 *@summary getting total number of drafted test.
	 */
	var totalDraftedTest = '${totalDraftedTest}';
	/**
	 * @summary giving the limit for showing record per page.
	 */
	var pagelimit = 5;
	/**
	 * @summary getting how much pages are required for showing all record of published test.
	 */
	var totalpageforpublished = totalPublishedTest.length > 0 ? Math
			.ceil(totalPublishedTest / pagelimit) : 0;
	/**
	 * @summary getting how much pages are required for showing all record of scheduled test.
	 */
	var totalpageforscheduled = totalScheduledTest.length > 0 ? Math
			.ceil(totalScheduledTest / pagelimit) : 0;
	/**
	 * @summary getting how much pages are required for showing all record of drafted test.
	 */
	var totalpagefordrafted = totalDraftedTest.length > 0 ? Math
			.ceil(totalDraftedTest / pagelimit) : 0;

	$(document)
			.ready(
					function() {
						var userStatus = '${userStatus==0}';
						if (userStatus == 'true') {
							$("#addnew").attr("disabled", "disabled");
							$(".callout").removeClass('hide');
						}
						$(".treeview").removeClass("active");
						$("#test").addClass("active");
						$("#test .treeview-menu > #test").addClass("active");
						//Enable iCheck plugin for checkboxes
						//iCheck for checkbox and radio inputs
						$('.mailbox-messages input[type="checkbox"]').iCheck({
							checkboxClass : 'icheckbox_flat-blue',
							radioClass : 'iradio_flat-blue'
						});

						//Enable check and uncheck all functionality
						$(".checkbox-toggle")
								.click(
										function() {
											var clicks = $(this).data('clicks');
											if (clicks) {
												//Uncheck all checkboxes
												$(
														".mailbox-messages input[type='checkbox']")
														.iCheck("uncheck");
												$(".fa", this)
														.removeClass(
																"fa-check-square-o")
														.addClass('fa-square-o');
											} else {
												//Check all checkboxes
												$(
														".mailbox-messages input[type='checkbox']")
														.iCheck("check");
												$(".fa", this)
														.removeClass(
																"fa-square-o")
														.addClass(
																'fa-check-square-o');
											}
											$(this).data("clicks", !clicks);
										});

						//Handle starring for glyphicon and font awesome
						$(".mailbox-star").click(function(e) {
							e.preventDefault();
							//detect type
							var $this = $(this).find("a > i");
							var glyph = $this.hasClass("glyphicon");
							var fa = $this.hasClass("fa");

							//Switch states
							if (glyph) {
								$this.toggleClass("glyphicon-star");
								$this.toggleClass("glyphicon-star-empty");
							}

							if (fa) {
								$this.toggleClass("fa-star");
								$this.toggleClass("fa-star-o");
							}
						});

						/**
						 * @summary calling function for appending published test detail on body load and page click.
						 */
						try {
							$('#published-pagination-demo')
									.twbsPagination(
											{
												totalPages : totalpageforpublished,
												visiblePages : 2,
												first : paggingmessages['lbl.first'],
												prev : paggingmessages['lbl.previous'],
												next : paggingmessages['lbl.next'],
												last : paggingmessages['lbl.last'],
												onPageClick : function(event,
														page) {
													/**
													 * @summary  assign index of first page on page click.
													 */
													startPageNumberforPublished = (page - 1)
															* pagelimit + 1;
													angular
															.element(
																	document
																			.getElementById('publishedTest'))
															.scope()
															.getpagelist(page,
																	startPageNumberforPublished);
												}
											});
						} catch (err) {
							console.log(err.message);
						}
						/**
						 * @summary calling function for appending scheduled test detail on body load and page click.
						 */
						try {
							$('#scheduled-pagination-demo')
									.twbsPagination(
											{
												totalPages : totalpageforscheduled,
												visiblePages : 2,
												first : paggingmessages['lbl.first'],
												prev : paggingmessages['lbl.previous'],
												next : paggingmessages['lbl.next'],
												last : paggingmessages['lbl.last'],
												onPageClick : function(event,
														page) {
													/**
													 * @summary  assign index of first page on page click.
													 */
													startPageNumberforScheduled = (page - 1)
															* pagelimit + 1;
													angular
															.element(
																	document
																			.getElementById('scheduledTest'))
															.scope()
															.getpagelist(page,
																	startPageNumberforScheduled);
												}
											});
						} catch (err) {
							console.log(err.message);
						}
						/**
						 * @summary calling function for appending drafted test detail on body load and page click.
						 */
						try {
							$('#drafted-pagination-demo')
									.twbsPagination(
											{
												totalPages : totalpagefordrafted,
												visiblePages : 2,
												first : paggingmessages['lbl.first'],
												prev : paggingmessages['lbl.previous'],
												next : paggingmessages['lbl.next'],
												last : paggingmessages['lbl.last'],
												onPageClick : function(event,
														page) {
													/**
													 * @summary  assign index of first page on page click.
													 */
													startPageNumberforDrafted = (page - 1)
															* pagelimit + 1;
													angular
															.element(
																	document
																			.getElementById('draftedTest'))
															.scope()
															.getpagelist(page,
																	startPageNumberforDrafted);
												}
											});
						} catch (err) {
							console.log(err.message);
						}
					});

	var viewtestpage = function(testId) {
		location.href = 'viewTestDetail?testId=' + testId;
	}

	var redirectongivetestpage = function(testId) {
		window
				.open(
						'studentGivenTest?testId=' + testId,
						'',
						'width=1000,height=500,scrollbars=yes,menubar=no, resizable=yes,toolbar=no,location=no,status=no');
	}

	var redirectaddedittestpage = function(testId) {
		location.href = 'addEditTest?testId=' + testId;
	}

	$("#deleteNo").click(function() {
		$("#deletetestAlert,.modal-backdrop").fadeOut();
	});

	function deleteDraftedTest(testId) {
		if (testId > 0) {
			var str = "testList?testId=" + testId;
			$("#dId").attr('href', str);
			$('#deletetestAlert').modal('show');
		}
	}

	var createNewTest = function() {
		location.href = "addEditTest";
		/* $.ajax({
			url : "createNewTestLicesneValidate",
			type : 'GET',
			async : false,		
			error : (function() {
				alert("server error");
			}),
			success : function(data) {
				if(data){						
					location.href="addEditTest";				
				}else{
					$("#warningdialog p").text("Your organization can create maximum 25 tests.");
					$("#warningdialog").modal('show') ;
					$("#addnew").attr("disabled",true);						
				}
			}
		}); */

	}
</script>
<script>
	var app = angular.module('testListApp', []);
	app.controller('publishedTestController', publishedTestController);
	app.controller('scheduledTestController', scheduledTestController);
	app.controller('draftedTestController', draftedTestController);
	/**
	 * @summary This is used getting list of published test.
	 * @$scope
	 * @$http
	 */
	function publishedTestController($scope, $http) {
		$scope.viewtestpage = viewtestpage;
		$scope.redirectongivetestpage = redirectongivetestpage;
		$scope.getpagelist = function(page, startpage) {
			$http
					.get(
							"testlistwithpagination?testStatus=" + 1
									+ "&limit=" + pagelimit + "&offset="
									+ (page - 1))
					.then(
							function(response) {
								$scope.publishedtestlist = response.data.testlist;
								/**
								 * @summary set showing entry
								 */
								$scope.totalpublishentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.testlist.length
														- 1).replace(
												'#totalentry',
												totalPublishedTest);
							});
		};
	}

	/**
	 * @summary This is used getting list of scheduled test.
	 * @$scope
	 * @$http
	 */
	function scheduledTestController($scope, $http) {
		$scope.viewtestpage = viewtestpage;
		$scope.getpagelist = function(page, startpage) {
			$http
					.get(
							"testlistwithpagination?testStatus=" + 2
									+ "&limit=" + pagelimit + "&offset="
									+ (page - 1))
					.then(
							function(response) {
								$scope.scheduledtestlist = response.data.testlist;
								/**
								 * @summary set showing entry
								 */
								$scope.totalscheduleentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.testlist.length
														- 1).replace(
												'#totalentry',
												totalScheduledTest);
							});
		};
	}

	/**
	 * @summary This is used getting list of drafted test.
	 * @$scope
	 * @$http
	 */
	function draftedTestController($scope, $http) {
		$scope.viewtestpage = viewtestpage;
		$scope.deleteDraftedTest = deleteDraftedTest;
		$scope.redirectaddedittestpage = redirectaddedittestpage;
		$scope.getpagelist = function(page, startpage) {
			$http
					.get(
							"testlistwithpagination?testStatus=" + 0
									+ "&limit=" + pagelimit + "&offset="
									+ (page - 1))
					.then(
							function(response) {
								$scope.draftedtestlist = response.data.testlist;
								/**
								 * @summary set showing entry
								 */
								$scope.totaldraftentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.testlist.length
														- 1)
										.replace('#totalentry',
												totalDraftedTest);
							});
		};
	}
</script>
</html>