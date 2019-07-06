<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/pages/include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
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

.list .fa-angle-right.fa-minus {
  -webkit-transform: rotate(90deg);
  -ms-transform: rotate(90deg);
  -o-transform: rotate(90deg);
  transform: rotate(90deg);
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
				<div class="row" id="users">
					<section class="content-header">

						<div class="col-sm-12">
							<label class="h3 form-group"> <spring:message
									code="lbl.mylibrary" text="My Library"/>
							</label>
						</div>
					</section>
					<!-- Main content -->
					<section class="content">
						<div class="col-md-12" style="height: 20px"></div>
						<input type="hidden" id="user_id"
							value="<%=((User)request.getSession().getAttribute("userSession")).getUserId()%>">
						<div style="background-color: #ECF0F5;" class="nav-tabs-custom">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="active button-width"
									style="margin-right: 0px; text-align: center; width: 220px; height: 65px;">
									<a href="#givenTest_tab" data-toggle="tab"><spring:message
											code="lbl.attemptedassessments" text="Attempted Assessments" /></a>
								</li>
								<li class="button-width"
									style="margin-left: 0px; text-align: center; width: 200px; height: 65px;">
									<a href="#notes_tab" data-toggle="tab"><spring:message
											code="lbl.questionsnotes" text="Questions Notes"/></a>
								</li>
								<li class="button-width"
									style="margin-left: 0px; text-align: center; width: 200px; height: 65px;">
									<a href="#fav_content_tab" data-toggle="tab"><spring:message
											code="lbl.favoritecontents" text="Favorite Contents" /></a>
								</li>
								<li class="button-width"
									style="margin-left: 0px; text-align: center; width: 200px; height: 65px;">
									<a href="#fav_question_tab" data-toggle="tab"><spring:message
											code="lbl.favoritequestions" text="Favorite Questions" /></a>
								</li>
							</ul>
						</div>

						<div class="tab-content"
							style="background-color: #ECF0F5 !important;"
							data-ng-app="testListApp">
							<div id="givenTest_tab" class="tab-pane active"
								data-ng-controller="attemptedTestController" data-ng-Cloak>
								<div id="givenTest" class="list">
									<div class="col-md-12 col-sm-12 col-xs-12 no-background-color"
										data-ng-repeat="test in attemptedTestList">
										<div class="box box-primary collapsed-box box-noborder ">
											<div class="box-header with-border">
												<div class="info-box  info-box-custom">
													<span class="info-box-icon imgset" style="width: 90px"><img
														class="modeler" data-ng-src="{{test.testIconUrl}}"
														onerror="this.src='resources/images/Trignometry.jpg'"></span>
													<div class="info-box-content col-sm-6">
														<h2 class="info-box-heading capitalize">{{test.testName}}</h2>
														<span class="info-box-tags">{{test.testTag}}</span> <span
															class="info-box-text"><spring:message
																code="lbl.by" text="by" />&nbsp;{{test.teacherName}}</span> <span
															data-ng-if="test.testTime!=null"><i
															class="fa fa-clock-o color-pink"></i>&nbsp;{{test.testTime}}&nbsp;<spring:message
																code="lbl.hrs" text="hrs" />&nbsp;&nbsp;</span> <i
															class="fa fa-question-circle color-pink"></i>&nbsp;{{test.totalQuestion}}<small>&nbsp;<spring:message
																code="lbl.qus" text="Qus" /></small>&nbsp;&nbsp;&nbsp; <i
															class="fa fa-pencil-square-o color-green"></i>&nbsp;
														{{test.noOfAttempted}}{{test.maxAttempts==0?'':'/'+
														test.maxAttempts}}&nbsp;<small><spring:message
																code="lbl.attempts" /></small>
													</div>
													<span class="info-box-icon pull-right cursor-pointer collapse-icon"
														data-widget="collapse"><i
														class="fa fa-angle-right"></i></span>
														<span class="clearfix"></span>
												</div>
											</div>
											<div class="box-body" id="testAttempted{{test.testId}}">

												<div class="col-md-12 col-sm-12  col-xs-12"
													data-ng-repeat="attemptData in test.userTestAttempt">

													<div class="info-box  info-box-custom height"
														data-ng-if="attemptData.submitStatus!=0">
														<div class="info-box-content col-sm-12 height">
															<div class="row" style="line-height: 4">
																<div class="col-md-3 col-sm-3 col-xs-3 ">
																	<div>
																		<h3 data-ng-init="currentAttempt=increment($index)">
																			<spring:message code="lbl.attempt" text="Attempt" />
																			&nbsp;{{currentAttempt}}
																		</h3>
																	</div>
																</div>
																<div class=" col-md-2 col-sm-2 col-xs-2">
																	<div>
																		<h5
																			class="info-box-custom  info-box-content info-box-text pull-right">
																			<spring:message code="lbl.score" text="Score" />
																			&nbsp;{{attemptData.obtainMarks}}/{{attemptData.totalMarks}}
																		</h5>
																	</div>
																</div>
																<div class=" col-md-2 col-sm-2 col-xs-2">
																	<div>
																		<h5
																			class="info-box-custom  info-box-content info-box-text pull-left">
																			<i class=" fa fa-clock-o color-green"></i>&nbsp;{{attemptData.timeTaken}}&nbsp;
																			<spring:message code="lbl.minute" text="Minute" />
																		</h5>
																	</div>
																</div>
																<div class="col-md-5 col-sm-5 col-xs-5">
																	<div class=" pull-right ">
																		<span>
																			<button type="button"
																				class="btn  btn-success btn-rounded"
																				data-ng-click="viewDetails(attemptData.userTestAttemptId)">
																				<i class="fa fa-th"></i>
																				<spring:message code="lbl.details" text="Details" />
																			</button> <span class="info-box-icon pull-right hide"><i
																				class=" fa fa-angle-right"></i></span>
																		</span>
																	</div>
																</div>
																<div class="clearfix visible-sm-block"></div>
															</div>
														</div>
													</div>


													<div class="info-box  info-box-custom height"
														data-ng-if="attemptData.submitStatus==0 && attemptData.jsonStatus==1">
														<div class="info-box-content col-sm-12 height">
															<div class="row" style="line-height: 4">
																<div class="col-md-3 col-sm-3 col-xs-3">
																	<div>
																		<h3 data-ng-init="currentAttempt=increment($index)">
																			<spring:message code="lbl.attempt" text="Attempt" />
																			&nbsp;{{currentAttempt}}
																		</h3>
																	</div>
																</div>
																<div class=" col-md-4 col-sm-4 col-xs-4">
																	<div>
																		<h5
																			class="info-box-custom  info-box-content info-box-text pull-right">
																			<spring:message code="msg.notsubmittedyet"
																				text="Not Submitted Yet" />
																		</h5>
																	</div>
																</div>
																<div class="col-md-5 col-sm-5 col-xs-5">
																	<div class="pull-right">
																		<span>
																			<button type="button"
																				class="btn  btn-success btn-rounded"
																				data-ng-click="submitDraftedTest(attemptData.userTestAttemptId)">
																				<i class="fa fa-th"></i>&nbsp;
																				<spring:message code="lbl.submit" text="Submit" />
																			</button> <span class="info-box-icon pull-right hide"><i
																				class="fa fa-angle-right"></i></span>
																		</span>
																	</div>
																</div>
																<div class="clearfix visible-sm-block"></div>
															</div>


														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="col-sm-12 form-inline" style="min-height: 50px;">
									<ul class="pull-left" id="record"
										style="line-height: 5; list-style-type: none; padding: 0px;"
										data-ng-show="totalentryshowing!=null">
										<li id="givenassessmententry">{{totalentryshowing}}</li>
									</ul>
									<ul id="pagination-demo" class="pull-right"></ul>
								</div>
							</div>

							<div id="notes_tab" class="tab-pane">
								<div class="table-responsive mailbox-messages col-xs-12"
									style="background-color: white; padding-top: 10px;">
									<table class="table" id="notestable">
										<thead>
											<tr>
												<th class="col-md-1"><spring:message code="lbl.sno"
														text="S.No." /></th>
												<th class="col-md-8"><spring:message
														code="lbl.questiontitle" text="Question Title" /></th>
												<th class="col-md-3"><spring:message code="lbl.note"
														text="Note" /></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>

							</div>

							<div id="fav_content_tab" class="tab-pane">
								<div class="table-responsive mailbox-messages col-xs-12"
									style="background-color: white; padding-top: 10px;">
									<table class="table" id="contentstable">
										<thead>
											<tr>
												<th class="col-md-1"><spring:message code="lbl.sno"
														text="S.No." /></th>
												<th class="col-md-9"><spring:message
														code="lbl.contenttitle" text="Content Title" /></th>
												<th class="col-md-2"><spring:message
														code="lbl.contenttype" text="Content Type" /></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>

							<div id="fav_question_tab" class="tab-pane">
								<div class="table-responsive mailbox-messages col-xs-12"
									style="background-color: #fff; padding-top: 10px;margin-bottom: 20px;">
									<table class="table" id="favquestable">
										<thead>
											<tr>
												<th><spring:message code="lbl.sno" text="S.No." /></th>
												<th><spring:message code="lbl.question" text="Question" /></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>

						</div>
					</section>
				</div>
			</div>
			<!-- content-wrapper -->
		</div>
		<!-- End dataTable----->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<div class="modal fade" id="testAlert" tabindex="-1" role="dialog"
		aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p></p>
						<button type="button" class="btn btn-primary button-width"
							data-dismiss="modal">
							<spring:message code="lbl.ok" text="Ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	var messages = new Array();
	messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' text='Something went wrong,Please try again.' javaScriptEscape='true' />";
	messages['lbl.by'] = "<spring:message code='lbl.by' text='By' javaScriptEscape='true' />";
	messages['lbl.hrs'] = "<spring:message code='lbl.hrs' text='hrs' javaScriptEscape='true' />";
	messages['lbl.qus'] = "<spring:message code='lbl.qus' text='Qus' javaScriptEscape='true' />";
	messages['lbl.score'] = "<spring:message code='lbl.score' text='Score' javaScriptEscape='true' />";
	messages['lbl.minute'] = "<spring:message code='lbl.minute' text='Minute' javaScriptEscape='true' />";
	messages['lbl.submit'] = "<spring:message code='lbl.submit' text='Submit' javaScriptEscape='true' />";
	messages['lbl.details'] = "<spring:message code='lbl.details' text='Details' javaScriptEscape='true' />";
	messages['lbl.attempts'] = "<spring:message code='lbl.attempts' text='Attempts' javaScriptEscape='true' />";
	messages['lbl.attempt'] = "<spring:message code='lbl.attempt' text='Attempt' javaScriptEscape='true' />";
	messages['msg.notsubmittedyet'] = "<spring:message code='msg.notsubmittedyet' text='You have not submitted yet.' javaScriptEscape='true' />";
</script>
<script src="<spring:url value='/resources/js/list.min.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
<script>
	/**
	 * @summary global variable for showing index of first course on a particular page.
	 */
	var startPageNumber = 1;
	/**
	 *@summary getting total number of Given test.
	 */
	var totalTest = '${totalGivenTest}';
	/**
	 * reference of current open window.
	 */
	var myWindow;
	/**
	 * @summary function would be call when body would be load.
	 * @return no
	 */
	$(document).ready(
			function() {
				$(".treeview").removeClass("active");
				$("#giveTest").addClass("active");
				$("#giveTest .treeview-menu > #giveTest").addClass("active");
				$(window).on('beforeunload', function() {
					if (myWindow && !myWindow.closed) {
						myWindow.close();
					}
				});
				/* var table = $("#notestable").dataTable({
				  "aaSorting": []	    	  	    		  
				 }); */

				getAllQuestionNotesList();
				getFavoriteContentList();
				getFavoriteQuestionList();
				/**
				 * @summary giving the limit for showing record per page.
				 */
				var pagelimit = 10;
				/**
				 * @summary getting how much pages are required for showing all record of published course.
				 */
				var totalpage = totalTest.length > 0 ? Math.ceil(totalTest
						/ pagelimit) : 0;
				try{
				/**
				 * @summary calling function for appending course detail on body load and page click.
				 */
				$('#pagination-demo').twbsPagination(
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
								/**
								 * @summary  assign index of first page on page click.
								 */
								startPageNumber = (page - 1) * pagelimit + 1
								/**
								 * @summary start loader  in taking time for loading data.
								 */
								$("#overlay").show();
								/**
								 * @summary call function for getting course list inside attemptedTestController.
								 */
								angular.element(
										document.getElementById('givenTest'))
										.scope().getattemptedtestlist(page,
												startPageNumber);
								/**
								 * @summary hide loader after loading data.
								 */
								$("#overlay").hide();
								/**
								 * @summary show showing record div
								 */
								$("#record").show();

							}
						});
				}catch(err){
					$("#overlay").hide();
					console.log(err.message);
				}

			});

	/**
	 * @summary function for open view mytest details page 
	 * @param testId
	 * @param testName
	 */
	var viewmytestbytestid = function(testId, testName) {
		location.href = 'StudentGiveTestController?action=view&testId='
				+ testId + '&testName=' + testName;
	}

	/**
	 * @function for open view test page
	 * @param testId
	 */
	var viewtestpage = function(testId) {
		location.href = 'student?action=view&testId=' + testId;
	}
    
	/**
	 * @function for view test details based on test attempted id.
	 * @param userTestAttemptId
	 */
	var viewDetails = function(userTestAttemptId) {
		if (myWindow && !myWindow.closed) {
			myWindow.close();
		}
		myWindow = window.open('viewTestResult?id=' + userTestAttemptId,'',
						'scrollbars=yes,menubar=no,width=1000,height=500, resizable=yes,toolbar=no,location=no,status=no');
	}
    
	/**
	 * @summary This function is used for submmitted details of un submitted test.
	 * @param userTestAttemptId
	 */
	var submitDraftedTest = function(userTestAttemptId) {
		if (userTestAttemptId > 0) {
			var submitTestJson = {
				"userTestAttemptId" : userTestAttemptId
			};
			$.ajax({
						url : "api/submitDraftedTest",
						async : false,
						type : "POST",
						data : JSON.stringify(submitTestJson),
						contentType : "application/json",
						beforeSend : function(xhr) { // @summary appending data in header before sending request.
							xhr.setRequestHeader('authorization', 'Browser');
						},
						error : function() {
						},
						success : function(data) {
							if (data.submitStatus != null) {
								if (myWindow && !myWindow.closed) {
									myWindow.close();
								}
								myWindow = window.open('viewTestResult?id='+ data.userTestAttemptId,
												'',' scrollbars=yes,menubar=no,width=1000, resizable=yes,toolbar=no,location=no,status=no');
							} else {
								$("#testAlert p").text(
										messages['msg.somethingwentwrong']);
								$("#testAlert").modal('show');
							}
						}
					});
		}
	}

	/**
	 * @summary capitalize first letter of course name.
	 * @param string
	 */
	function capitalizeFirstLetter(string) {
		return string.charAt(0).toUpperCase() + string.slice(1);
	}

	/** 
	 * @summary function to get questions notes list by userId
	 */
	var getAllQuestionNotesList = function() {

		var columnName = $('#notestable').DataTable({
			"language" : datatablelanguagejson,
			"aaSorting" : [],
			"columnDefs" : [ {
				"orderable" : false,
				"targets" : [ 0, 1 ]
			}, ],
			"bAutoWidth" : false

		});

		$.ajax({
			url : "api/getallquesnotes/" + Number($("#user_id").val()),
			type : "GET",
			dataType : 'json',
			contentType : "application/json",
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', "Browser");
			},
			async : false,
			error : function() {
			},
			success : function(res) {
				var data = res.notelist;
				var str = '';
				for (var i = 0; i < data.length; i++) {
					columnName.row.add(
							[
									(i + 1),
									(data[i].questionName == null ? '--'
											: data[i].questionName),
									(data[i].notes == null ? '--'
											: data[i].notes) ]).draw();
				}
				$('#notestable img').css({
					'display' : 'block',
					'width' : '100%',
					'max-width' : '100%',
					'height' : 'auto'
				});
			}
		});
	}

	/**
	 * @summary function to get favorite contents list by userId
	 */
	var getFavoriteContentList = function() {
		var columnName = $('#contentstable').DataTable({
			"language" : datatablelanguagejson,
			"aaSorting" : [],
			"columnDefs" : [ {
				"orderable" : false,
				"targets" : [ 0, 1 ]
			} ],
			"bAutoWidth" : false
		});

		$.ajax({
					url : "api/getfavoritecontents/"
							+ Number($("#user_id").val()),
					type : "GET",
					dataType : 'json',
					contentType : "application/json",
					beforeSend : function(xhr) { // @summary appending data in header before sending request.
						xhr.setRequestHeader('authorization', 'Browser');
						xhr.setRequestHeader('timestamp', "Browser");
					},
					async : false,
					error : function() {
					},
					success : function(res) {
						var data = res.favcontentlist;
						for (var i = 0; i < data.length; i++) {
							columnName.row
									.add([(i + 1),(data[i].contentName == "" ? data[i].content
															: data[i].contentName),
													(data[i].contentType) ]).draw();
						}
					}
				});

	}

	/**
	 * @summary function to get favorite questions list by userId 
	 */
	var getFavoriteQuestionList = function() {
		var columnName = $('#favquestable').DataTable({
			"language" : datatablelanguagejson,
			"aaSorting" : [],
			"columnDefs" : [ {
				"orderable" : false,
				"targets" : [ 0 ]
			}, ],
			"bAutoWidth" : false
		});

		$.ajax({
			url : "api/getfavoritequestions/" + Number($("#user_id").val()),
			type : "GET",
			dataType : 'json',
			contentType : "application/json",
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', "Browser");
			},
			async : false,
			error : function() {
			},
			success : function(res) {
				var data = res.favoritequelist;
				for (var i = 0; i < data.length; i++) {
					columnName.row.add([ (i + 1), (data[i].questionName) ])
							.draw();
				}
				$('#favquestable img').css({
					'display' : 'block',
					'width' : '100%',
					'max-width' : '100%',
					'height' : 'auto'
				});
			}
		});

	}
    
	/**
	 * @summary define the application.
	 */ 
	var app = angular.module('testListApp', []);
	app.controller('attemptedTestController', attemptedTestController);

	/**
	 * @summary This is used getting list of given test.
	 * @$scope
	 * @$http
	 */
	function attemptedTestController($scope, $http) {
		$scope.currentAttempt = 0;
		$scope.increment = function(index) {
			if ($scope.currentAttempt >= (index + 1)) {
				$scope.currentAttempt = 1;
			} else {
				$scope.currentAttempt = $scope.currentAttempt + 1;
			}
			return $scope.currentAttempt;
		};
		$scope.submitDraftedTest = submitDraftedTest;
		$scope.viewDetails = viewDetails;
		$scope.getattemptedtestlist = function(page, startpage) {
			$http
					.get("api/mylibrary/${userId}/" + (page - 1), {
						headers : {
							'authorization' : 'Browser',
							'timestamp' : 'Browser'
						}
					})
					.then(
							function(response) {
								$scope.attemptedTestList = response.data.myLibrary;
								/**
								 * @summary set showing entry
								 */
								$scope.totalentryshowing = paggingmessages['lbl.paginationentrymsg']
										.replace('#firstentry', startpage)
										.replace(
												'#totalcurrectpageentry',
												startpage
														+ response.data.myLibrary.length
														- 1).replace(
												'#totalentry', totalTest);
							});

		};
	}
</script>


</html>