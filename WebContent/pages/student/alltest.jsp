<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.qbis.model.User"%>
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

.tab-content .fa-angle-right.fa-minus {
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
					<!-- Content Header (Page header) -->
					<section class="content-header">
						<div class="col-sm-3">
							<label class="h3 form-group"> <spring:message code="lbl.myquizzes" text="My Quizzes"/> </label>
						</div>
						<div style="background-color: #ECF0F5;"
							class="nav-tabs-custom col-sm-12">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="active button-width"
									style="margin-right: 0px; text-align: center;"><a
									href="#currentTestList" data-toggle="tab"><spring:message code="lbl.current" text="Current"/></a></li>
								<li class="button-width"
									style="margin-left: 0px; text-align: center;"><a
									href="#upcomingTestList" data-toggle="tab"><spring:message code="lbl.upcoming" text="Upcoming"/></a></li>
							</ul>
						</div>
					</section>
					<!-- Main content -->
					<section class="content">
						<div class="col-md-12" style="height: 20px"></div>
						<div class="tab-content"
							style="background-color: #ECF0F5 !important;" data-ng-app="testListApp">
							<!-- current test list -->
							<div id="currentTestList" class="tab-pane active"  data-ng-controller="currentTestController" data-ng-Cloak>
							
								<div id="currentTest">
								
								
		<div class="col-md-12 col-sm-12 col-xs-12" data-ng-repeat="test in currenttestlist">
            <div class="box box-primary collapsed-box box-noborder">
            <div class="box-header with-border">
                <div class="info-box  info-box-custom">
                    <span class="info-box-icon imgset" style="width:90px"><img class="modeler" data-ng-src="{{test.testIconUrl}}" onerror="this.src='resources/images/Trignometry.jpg'"></span>
                    <div class="info-box-content col-md-6 col-sm-6 col-xs-6">
                        <h2 class="info-box-heading capitalize">{{test.testName}}</h2>
                        <span class="info-box-tags">{{test.testTag}}</span>
                        <span class="info-box-text"><spring:message code="lbl.by" text="by"/>&nbsp;{{test.teacherName}}</span>
                        <span data-ng-if="test.testTime!=null"><i class="fa fa-clock-o color-pink"></i>&nbsp;{{timeFilter(test.testTime)}}&nbsp;<spring:message code="lbl.hrs" text="hrs"/>&nbsp;</span>
                        <i class="fa fa-question-circle color-pink"></i>&nbsp;{{test.totalQuestion}}&nbsp;<small><spring:message code="lbl.qus" text="Qus"/></small>
                    </div>
                    <span class="info-box-icon pull-right cursor-pointer" data-widget="collapse"><i class="fa fa-angle-right"></i></span>
                    <span class="clearfix"></span>
               </div>
            </div>
            <div class="box-body">
                <h1 class="page-header box-noborder"><spring:message code="lbl.description" text="Description"/></h1>                 
                <p>{{test.testDesc}}</p>
                <nav>
                    <ul class="pager">
                        <li><button type="button" class="btn btn-success btn-flat btn-border-radius" style="width:200px" data-ng-click="givetestpage(test.testId)"><spring:message code="lbl.instructions" text="Instructions"/></button></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    
								</div>
								<div class="col-sm-12 form-inline" style="min-height: 50px">
									<ul class="pull-left" id="currentrecord"
										style="line-height: 5; list-style-type: none;padding: 0px;" data-ng-show="totalcurrententryshowing!=null">
										<li id="paginationentryincurrent">{{totalcurrententryshowing}}</li>
									</ul>
									<ul id="current-pagination-demo" class="pull-right"></ul>
								</div>
							</div>
							<!-- /.current test list -->
							
							
							<!-- upcoming test list -->
							<div id="upcomingTestList" class="tab-pane" data-ng-controller="upcomingTestController" data-ng-Cloak>
								<div id="upcomingTest">
	 <div class="col-md-12 col-sm-12 col-xs-12" data-ng-repeat="test in upcomingtestlist">
            <div class="box box-primary collapsed-box box-noborder">
            <div class="box-header with-border">
                <div class="info-box  info-box-custom">
                    <span class="info-box-icon imgset" style="width:90px"><img class="modeler" data-ng-src="{{test.testIconUrl}}" onerror="this.src='resources/images/Trignometry.jpg'"></span>
                    <div class="info-box-content col-md-6 col-sm-6 col-xs-6">
                        <h2 class="info-box-heading capitalize">{{test.testName}}</h2>
                        <span class="info-box-tags">{{test.testTag}}</span>
                        <span class="info-box-text"><spring:message code="lbl.by" text="By"/>&nbsp;{{test.teacherName}}</span>
                        <span class="info-box-text"><spring:message code="lbl.publishdate" text="Publish Date"/> :&nbsp;{{test.schedulePublishDate}}</span>
                        <span data-ng-if="test.testTime!=null"><i class=" fa fa-clock-o color-pink"></i>&nbsp;{{timeFilter(test.testTime)}}&nbsp;<spring:message code="lbl.hrs" text="hrs"/></span>
                        <i class="fa fa-question-circle color-pink"></i>&nbsp;{{test.totalQuestion}}&nbsp;<small><spring:message code="lbl.qus" text="Qus"/></small>
                    </div>
                    <span class="info-box-icon pull-right cursor-pointer" data-widget="collapse"><i class="fa fa-angle-right"></i></span>
                    <span class="clearfix"></span>
               </div>
            </div>
            <div class="box-body">
                <h1 class="page-header box-noborder"><spring:message code="lbl.description" text="Description"/></h1>                     
                <p>{{test.testDesc}}</p>
            </div>
        </div>
    </div>
    
								</div>
								<div class="col-sm-12 form-inline" style="min-height: 50px">
									<ul class="pull-left" id="upcomingrecord"
										style="line-height: 5; list-style-type: none;padding: 0px;" data-ng-show="totalupcomingentryshowing!=null">
										<li id="paginationentryinupcoming">{{totalupcomingentryshowing}}</li>
									</ul>
									<ul id="upcoming-pagination-demo" class="pull-right"></ul>
								</div>
							</div>
							<!-- /.upcoming test list -->
						</div>
					</section>
				</div>
				<!-- ./row -->
			</div>
			<!-- content-wrapper -->
		</div>
	</div>
	<!-- ./wrapper -->
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['lbl.by'] = "<spring:message code='lbl.by' text='By' javaScriptEscape='true' />";
        messages['lbl.description'] = "<spring:message code='lbl.description' text='Description' javaScriptEscape='true' />";
        messages['lbl.instructions'] = "<spring:message code='lbl.instructions' text='Instructions' javaScriptEscape='true' />";
        messages['lbl.hrs'] = "<spring:message code='lbl.hrs' text='hrs' javaScriptEscape='true' />";
        messages['lbl.qus'] = "<spring:message code='lbl.qus' text='Qus' javaScriptEscape='true' />";
        messages['lbl.publishdate'] = "<spring:message code='lbl.publishdate' text='Publish Date' javaScriptEscape='true' />";
</script> 
<script src="<spring:url value='/resources/js/list.min.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.js'/>"></script>
<script
	src="<spring:url value='/resources/js/jquery.twbsPagination.min.js'/>"></script>
<script>
	/**
	 * @summary id of user.
	 */
	 var userId = <%=((User)request.getSession().getAttribute("userSession")).getUserId()%>;
	/**
     * @summary global variable for showing index of first current test on a particular page.
     */
    var startPageNumber=1;
	/**
     * @summary global variable for showing index of first upcoming test on a particular page.
     */
    var startPageNumberforUpcoming=1;
	/**
	 *@summary getting total number of published test.
	 */
		var totalTest='${totalTest}';
	/**
	 *@summary getting total number of published test.
	 */
		var totalUpcomingTest='${upcomingTest}';
	/**
	 * reference of current open window.
	 */
	    var myWindow;	
	/**
	 * @summary function would be call when body would be load.
	 * @return no
	 */
      $(document).ready(function(){
    	  $(".treeview").removeClass("active");
    	  $("#test").addClass("active");
		  $("#allTest").addClass("active");
  		/**
		 * @summary giving the limit for showing record per page.
		 */
		var pagelimit = 10;
		/**
		 * @summary getting how much pages are required for showing all record of published course.
		 */
		var totalpage = totalTest.length > 0 ? Math.ceil(totalTest
				/ pagelimit) : 0;
		/**
		 * @summary calling function for appending course detail on body load and page click.
		 */
		$('#current-pagination-demo').twbsPagination({
			totalPages : totalpage, //@summary  giving no of pages for showing.
			visiblePages : 2, //@summary  number of limit for visible pages
			first: paggingmessages['lbl.first'],
		    prev: paggingmessages['lbl.previous'],
		    next: paggingmessages['lbl.next'],
		    last: paggingmessages['lbl.last'],
			/**
			 * @summary call function on page click
			 */
			onPageClick : function(event, page) {
				
			 try{
				/**
				 * @summary  assign index of first page on page click.
				 */
				startPageNumber=(page-1) * pagelimit+1
				/**
				 * @summary start loader  in taking time for loading data.
				 */
				$("#overlay").show();
				/**
				 * @summary call function for getting course list.
				 */
				angular.element(document.getElementById('currentTest')).scope().getcurrenttestlist(page-1,
						startPageNumber);
				/**
				 * @summary show showing record div
				 */
				$("#currentrecord").show();
				
				/**
				 * @summary hide loader.
				 */
				$("#overlay").hide();
				
			 }catch(err){
				 $("#overlay").hide();
				 console.log(err.message);
			 }
				
			}
		});
		
		/**
		 * @summary getting how much pages are required for showing all record of published course.
		 */
		var totalUpcomingpage = totalUpcomingTest.length > 0 ? Math.ceil(totalUpcomingTest
				/ pagelimit) : 0;
		/**
		 * @summary calling function for appending upcoming test detail on body load and page click.
		 */
		$('#upcoming-pagination-demo').twbsPagination({
			totalPages : totalUpcomingpage, //@summary  giving no of pages for showing.
			visiblePages : 2, //@summary  number of limit for visible pages
			first: paggingmessages['lbl.first'],
		    prev: paggingmessages['lbl.previous'],
		    next: paggingmessages['lbl.next'],
		    last: paggingmessages['lbl.last'],
			/**
			 * @summary call function on page click
			 */
			onPageClick : function(event, page) {
				
				try{
				/**
				 * @summary  assign index of first page on page click.
				 */
				 startPageNumberforUpcoming=(page-1) * pagelimit+1
				/**
				 * @summary start loader  in taking time for loading data.
				 */
				 $("#overlay").show();
				/**
				 * @summary call function for getting course list.
				 */
				angular.element(document.getElementById('upcomingTestList')).scope().getupcomingtestlist(page-1,startPageNumberforUpcoming);
				/**
				 * @summary hide loader
				 */
				 $("#overlay").hide();
				/**
				 * @summary show showing record div
				 */
				$("#upcomingrecord").show();
				
				}catch(err){
					console.log(err.message);
					$("#overlay").hide();
				}
			}
		});
		
      });

	/**
	 * @summary capitalize first letter of course name.
	 */
	 function capitalizeFirstLetter(string) {
		    return string.charAt(0).toUpperCase() + string.slice(1);
		}
	
		/**
		 * @function for redirect on gievn test page based on test id.
		 * @param testId
		 */
	 var givetestpage = function(testId) {
			if (myWindow && !myWindow.closed) {
				myWindow.close();
			}
			myWindow = window.open('studentGivenTest?testId='+testId,'','width=1000,height=500,scrollbars=yes,menubar=no, resizable=yes,toolbar=no,location=no,status=no');
		}
		
	 /**
	  * @summary define the application.
	  */ 
	var app = angular.module('testListApp',[]);
	
	/**
	 * @summary define the controller.
	 */ 
	app.controller('currentTestController',currentTestController);
	app.controller('upcomingTestController',upcomingTestController);
	
	/**
	 * @summary This is used getting list of current test.
	 * @$scope
	 * @$http
	 */
	function currentTestController($scope,$http){
		$scope.givetestpage=givetestpage;
		$scope.timeFilter = function(testTime){
			if(testTime!=null){
	  			var hours=Math.floor(testTime/3600)<10?"0"+Math.floor(testTime/3600):Math.floor(testTime/3600);
	  			var mins=Math.floor((testTime%3600)/60)<10?"0"+Math.floor((testTime%3600)/60):Math.floor((testTime%3600)/60);
	  			//var seconds=Math.floor((testTime%(3600*60))/60)<10?"0"+Math.floor((testTime%(3600*60))/60):Math.floor((testTime%(3600*60))/60);
	  			testTime=hours+":"+mins+":00";
	  			}
			return testTime;
		};
		$scope.getcurrenttestlist = function(offset,startpage){
			$http.get('api/testlist/' + userId + '/' + offset,{headers : {
				'authorization' : 'Browser',
				'timestamp' : 'Browser'
			}}).then(function(response){
				try{
				$scope.currenttestlist = response.data.testlist;
				/**
				 * @summary set showing entry
				 */
				$scope.totalcurrententryshowing = paggingmessages['lbl.paginationentrymsg']
						.replace('#firstentry', startpage)
						.replace(
								'#totalcurrectpageentry',
								startpage
										+ response.data.testlist.length
										- 1).replace(
								'#totalentry', totalTest);
			
				}catch(err){
					console.log(err.message);
					$("#overlay").hide();
				}
				})
			
		}
	}
	
	/**
	 * @summary This is used getting list of upcoming test.
	 * @$scope
	 * @$http
	 */
	 
	function upcomingTestController($scope,$http){
		$scope.timeFilter = function(testTime){
			if(testTime!=null){
	  			var hours=Math.floor(testTime/3600)<10?"0"+Math.floor(testTime/3600):Math.floor(testTime/3600);
	  			var mins=Math.floor((testTime%3600)/60)<10?"0"+Math.floor((testTime%3600)/60):Math.floor((testTime%3600)/60);
	  			//var seconds=Math.floor((testTime%(3600*60))/60)<10?"0"+Math.floor((testTime%(3600*60))/60):Math.floor((testTime%(3600*60))/60);
	  			testTime=hours+":"+mins+":00";
	  			}
			return testTime;
		};
		
		$scope.getupcomingtestlist = function(offset,startpage){
			$http.get('api/upcomingtestlist/' + userId + '/' + offset,{headers : {
				'authorization' : 'Browser',
				'timestamp' : 'Browser'
			}}).then(function(response){
			try{
				$scope.upcomingtestlist = response.data.testlist;
				/**
				 * @summary set showing entry
				 */
				$scope.totalupcomingentryshowing = paggingmessages['lbl.paginationentrymsg']
						.replace('#firstentry', startpage)
						.replace(
								'#totalcurrectpageentry',
								startpage
										+ response.data.testlist.length
										- 1).replace(
								'#totalentry', totalUpcomingTest);
				}catch(err){
					console.log(err.message);
					$("#overlay").hide();
				}
			})
		}
	}
      </script>
</html>