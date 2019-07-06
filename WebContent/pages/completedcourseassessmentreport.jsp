<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<!DOCTYPE html>

<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/adminlte/plugins/datatables/buttons.dataTables.min.css">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
.input-group .input-group-addon {
	background-color: transparent;
}

.daterangepicker td.active, .daterangepicker td.active:hover {
	background-color: #00B06C;
}

.input-group-addon-child {
	background-color: white !important;
	border: 1px solid #ccc !important;
	border-left: 0px !important;
}

.filteritems {
	margin-top: 30px;
}

.table-striped>tbody>tr:nth-of-type(odd) {
	background-color: transparent;
}

.no_margin_padding {
	margin: 0px;
	padding: 0px;
}
td, th {
   border: 1px solid #dddddd !important;
}
.lastChild{
border: 0px solid #dddddd !important;
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
					<h1><spring:message code="lbl.completedtranscriptsbyuserreport" text="Completed Transcripts by User Report - Course/Assessment"/></h1>
				</section>
				<!-- /.content-header -->
				<section class="content">				   
					<div class="row">
					<div class="row form-group">
					 <div class="col-sm-9">
					   <label><spring:message code="lbl.selectuser" text="Select User"/>:&nbsp;</label>
							<select id="userId" class="form-control">
							  <c:forEach items="${userList}" var="list">
							  <option value="${list.userId}">${list.userName} (${list.userId})</option>
							  </c:forEach>
							</select>
					 </div>
					 <div class="col-sm-3">
					 <div>&nbsp;</div>
					  <button type="button"
									class="btn btn-flat btn-success button-width-large searchFilterButton" style="margin-top:5px"><spring:message code="lbl.search" text="Search"/>
					 </button>
					 </div>
					</div>					
						<div class="col-sm-12 invoice-info" id="">                     
                           <div class="col-sm-4 invoice-col" id="userdata">
                           </div> 
                          <div class="col-sm-4 invoice-col hide" id="contentdata">
                             <b><spring:message code="lbl.countoffileuploaded" text="Count Of File Uploaded"/> :</b> <span id="countContent"></span>                          
                             <br>
                             <b><spring:message code="lbl.amountofspacetakenbyuser" text="Amount Of Space Taken By User"/> :</b> <span id="space"></span>                           
                          </div>
                          <div class="col-sm-4 invoice-col hide" id="quesdata"> 
                             <b><spring:message code="lbl.countofcreatedquestion" text="Count of Created Question"/> :</b> <span id="countQue"></span>
                          </div>
                        </div>
						<div class="col-sm-12 table-responsive filteritems hide"
							id="usersreporttablediv">
							<table class="table table-hover table-striped table-bordered"
								id="usersreporttable">
								<thead>
									<tr id="usersreporttable_header">
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						
						
						<div class="col-sm-12 table-responsive filteritems hide"
							id="createtesttablediv">
							<table class="table table-hover table-striped table-bordered"
								id="createtesttable">
								<thead>
									<tr id="createtesttable_header">
									<th><spring:message code="lbl.assessmentid" text="Assessment ID"/></th>
									<th><spring:message code="lbl.assessmenttitle" text="Assessment Title"/></th>
									<th><spring:message code="lbl.scheduled" text="Scheduled"/>/<spring:message code="lbl.published" text="published"/>/<spring:message code="lbl.drafted" text="Drafted"/></th>
									<th><spring:message code="lbl.assignedto" text="Assigned to"/></th>
									<th><spring:message code="lbl.createddate" text="Created Date"/></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						
						<div class="col-sm-12 table-responsive filteritems hide"
							id="createcoursetablediv">
							<table class="table table-hover table-striped table-bordered"
								id="createcoursetable">
								<thead>
									<tr id="createcoursetable_header">
									<th><spring:message code="lbl.courseid" text="Course ID"/></th>
									<th><spring:message code="lbl.coursetitle" text="Course Title"/></th>
									<th><spring:message code="lbl.scheduled" text="Scheduled"/>/<spring:message code="lbl.published" text="published"/>/<spring:message code="lbl.drafted" text="Drafted"/></th>
									<th><spring:message code="lbl.assignedto" text="Assigned to"/></th>
									<th><spring:message code="lbl.createddate" text="Created Date"/></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
											
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
		<!-- /.col-sm-12 -->
	</div>
	<!-- /.wrapper -->
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['lbl.assessmentid'] = "<spring:message code='lbl.assessmentid' text='Assessment ID' javaScriptEscape='true'/>";
        messages['lbl.assessmenttitle'] = "<spring:message code='lbl.assessmenttitle' text='Assessment Title' javaScriptEscape='true'/>";
        messages['lbl.numberofattempts'] = "<spring:message code='lbl.numberofattempts' text='Number of Attempts' javaScriptEscape='true'/>";
        messages['lbl.lastattemptdate'] = "<spring:message code='lbl.lastattemptdate' text='Last Attempt Date' javaScriptEscape='true'/>";
        messages['lbl.scorestatusoflastattempt'] = "<spring:message code='lbl.scorestatusoflastattempt' text='Score/Status of Last Attempt' javaScriptEscape='true'/>";
        messages['lbl.userid'] = "<spring:message code='lbl.userid' text='User ID' javaScriptEscape='true'/>";
        messages['lbl.name'] = "<spring:message code='lbl.name' text='Name' javaScriptEscape='true'/>";
        messages['lbl.role'] = "<spring:message code='lbl.role' text='Role' javaScriptEscape='true'/>";
</script>        
<script src="resources/adminlte/plugins/datatables/dataTables.buttons.min.js"></script>
<script src="resources/adminlte/plugins/datatables/buttons.flash.min.js"></script>
<script src="resources/adminlte/plugins/datatables/jszip.min.js"></script>
<!-- <script src="resources/adminlte/plugins/datatables/pdfmake.min.js"></script> -->
<script src="resources/adminlte/plugins/datatables/vfs_fonts.js"></script> 
<script src="//cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
<!-- <script src="resources/adminlte/plugins/datatables/buttons.html5.min.js"></script> -->
<script src="resources/adminlte/plugins/datatables/buttons.print.min.js"></script>


<script>
			var oTable;
			
	$(document).ready(function() {		
		$(".treeview").removeClass("active");
		$("#report").addClass("active");
		$("#report .treeview-menu > #userReportsList").addClass("active");		
	});
	
	/**
	 * call on click of search button. 
	 */
	$(document).on("click", ".searchFilterButton", function() {
		getDataByFilter();
		$('html, body').animate({ scrollTop: $(document).height() }, 1200);
	});
	/**
	 * header for users list.
	 */
	var usersHeader = 
			         '<th>'+messages['lbl.assessmentid']+'</th>'
			        + '<th>'+messages['lbl.assessmenttitle']+'</th>'
			        + '<th>'+messages['lbl.numberofattempts']+'</td>'
			        + '<th>'+messages['lbl.lastattemptdate']+'</th>'			       
			        + '<th>'+messages['lbl.scorestatusoflastattempt']+'</td>'
			        ; 
			        
	/**
	 * assessment list column data.
	 */
	var usersColumnData = [  {
		"data" : "testId",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "testName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "noOfAttempted",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "date",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "testTag",
		defaultContent : "",
		sortable : true
	}	
	 ];
	
	/**
	 * created Course list column data.
	 */
	var createdCourseColumnData = [{
		"data" : "courseId",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "courseName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "status",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "courseInfo",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "date",
		defaultContent : "",
		sortable : true
	}	
	 ];
	
	/**
	 * created Test list column data.
	 */
	var createdTestColumnData = [{
		"data" : "testId",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "testName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "testTag",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "testDesc",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "date",
		defaultContent : "",
		sortable : true
	}	
	 ];
	
	
	

	/**
	 * call function for getting list.
	 */
	function getDataByFilter() {		
		
		$.ajax({
			url : "getFilterCompletedAssessmentCourseData?userId="+$('#userId').val(),			
			type : 'GET',
			dataType : 'json',
			contentType : "application/json",
			//async : false,
			error : function() {
				alert("error");
			},
			success : function(response) {
			  console.log(response);
			  hideReportData();
			  $("#userdata").empty();
			  $("#userdata").append("<b>"+messages['lbl.userid']+": </b> "+response.userId+"<br>"+
                      "<b>"+messages['lbl.name']+": </b>"+response.userName+"<br>"+
                      "<b>"+messages['lbl.role']+": </b>"+response.roleDesc);
			
			  if(response.testList!=null){
				  $("#usersreporttable_header").empty();
				  $("#usersreporttable_header").append(usersHeader);
				  $("#usersreporttablediv").removeClass("hide");
				  setReportDetails(response.testList,usersColumnData,'usersreporttable');  
			  }
			  if(response.createdTestList!=null){
				  $("#createtesttablediv").removeClass("hide");
				  setReportDetails(response.createdTestList,createdTestColumnData,'createtesttable'); 
			  }
			  if(response.createdCourseList!=null){
				  $("#createcoursetablediv").removeClass("hide");
				  setReportDetails(response.createdCourseList,createdCourseColumnData,'createcoursetable');
			  }
			  
			  if(response.questionCount!=0){
				  $("#quesdata").removeClass("hide");
				  $("#countQue").text(response.questionCount);
			  }
			 
			  if(response.contentDetails!=null){
				  $("#contentdata").removeClass("hide");
				  $("#countContent").text(response.contentDetails.contentOrder);
				  $("#space").text(response.contentDetails.additionalInfo);				  
			  }			  
			}
		});
	}
	
	var hideReportData = function(){
		$("#usersreporttablediv").addClass("hide");
		$("#contentdata").addClass("hide");
		$("#quesdata").addClass("hide");
		$("#createcoursetablediv").addClass("hide");
		$("#createtesttablediv").addClass("hide");
	}
	
	var setReportDetails = function(list,colData,tabname){
		var columnData = colData;
		$("#"+tabname).DataTable().destroy();
		$("#"+tabname+" tbody").empty();
		oTable = $("#"+tabname)
		.dataTable(
				{
					"processing" : true,
					"serverSide" : false,
					"deferRender" : true,
					"language": datatablelanguagejson,
					 'dom': 'Bfrtip',
			          'buttons': [
			                'excel'
			          ],
			        "data": list,  
					"columns" : columnData,
					'aaSorting' : []
				});
	}
</script>
</html>
