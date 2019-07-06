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

.input-group-addon:last-child {
    border-left: 0 !important;
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
					<h1><spring:message code='lbl.courseenrollstatusreport' text='Course Enroll Status Report'/></h1>
				</section>
				<!-- /.content-header -->
				<section class="content">				   
					<div class="row">
					<div class="row form-group">
					 <div class="col-sm-9">
					 <label><spring:message code='lbl.daterange' text='Select Date'/>:&nbsp;</label>					  
                      <div class="input-group">
                        <input type="text" class="form-control" id="startDate">
                        <div class="input-group-addon input-group-addon-child ">
						<i class="fa fa-calendar"></i>
						</div>
						</div>
					 </div>					 
					</div>
					<div class="row form-group">
					 <div class="col-sm-9">
					   <label><spring:message code='lbl.selecttrainer' text='Select Trainer'/>:&nbsp;</label>
							<select id="userId" class="form-control">
							  <c:forEach items="${userList}" var="list">
							  <option value="${list.userId}">${list.userName} (${list.userId})</option>
							  </c:forEach>
							</select>
					 </div>					
					</div>
					<div class="row form-group">
					 <div class="col-sm-9">
					   <label><spring:message code='lbl.selectcoursestatus' text='Select Course Status'/>:&nbsp;</label>
							<select id="status" class="form-control">							  
							  <option value="-1"><spring:message code='lbl.all' text='All'/></option>
							  <option value="1"><spring:message code='lbl.enrolled' text='Enrolled'/></option>
							</select>
					 </div>
					 <div class="col-sm-3">
					 <div>&nbsp;</div>
					  <button type="button"
									class="btn btn-flat btn-success button-width-large searchFilterButton" style="margin-top:5px">
									<spring:message code='lbl.search' text='Search'/>
					 </button>
					 </div>
					</div>					
					
						<div class="col-sm-12 table-responsive filteritems hide"
							id="usersreporttablediv">
							<table class="table table-hover table-striped table-bordered"
								id="usersreporttable">
								<thead>
									<tr id="usersreporttable_header">
									<th><spring:message code='lbl.courseid' text='Course Id'/></th>
									<th><spring:message code='lbl.coursename' text='Course Name'/></th>
									<th><spring:message code='lbl.startdate' text='Start Date'/></th>
									<th><spring:message code='lbl.trainername' text='Trainer Name'/></th>
									<th><spring:message code='lbl.totalenrolled' text='Total Enrolled'/></th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						
						
						<div class="col-sm-12 table-responsive filteritems hide"
							id="participantdiv">
							<table class="table table-hover table-striped table-bordered"
								id="participanttable">
								<thead>
									<tr id="participanttable_header">
									<th><spring:message code='lbl.courseid' text='Course Id'/></th>
									<th><spring:message code='lbl.coursename' text='Course Name'/></th>
									<th><spring:message code='lbl.participantname' text='Participant Name'/></th>
									<th><spring:message code='lbl.status' text='Status'/></th>
									<th><spring:message code='lbl.organization' text='Organization'/></th>
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
		$("#report .treeview-menu > #coursereportlist").addClass("active");	
		  $('#startDate').daterangepicker({
			  locale: {
			      format: 'YYYY-MM-DD'
			    },
		        "singleDatePicker": true,
		        "showDropdowns": true,
		        "showWeekNumbers": false,
		        "timePicker": false
		    });
	});
	
	/**
	 * call on click of search button. 
	 */
	$(document).on("click", ".searchFilterButton", function() {
		getDataByFilter();
		$('html, body').animate({ scrollTop: $(document).height() }, 1200);
	});

			        
	/**
	 * assessment list column data.
	 */
	var usersColumnData = [  {
		"data" : "courseId",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "courseName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "date",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "teacherName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "enrollCount",
		defaultContent : "",
		sortable : true
	}
	 ];
	
	/**
	 * created Course list column data.
	 */
	var participantColumnData = [{
		"data" : "courseId",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "courseName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "teacherName",
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
	}	
	 ];
	

	/**
	 * call function for getting list.
	 */
	function getDataByFilter() {		
		
		$.ajax({
			url : "getFilterCourseEnrollmentReportData?trainerId="+$('#userId').val()
			+"&status="+$('#status').val()+"&startDate="+$('#startDate').val(),
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
			  if(response.createdCourseList!=null){
				  $("#usersreporttablediv").removeClass("hide");
				  setReportDetails(response.createdCourseList,usersColumnData,'usersreporttable');  
			  }
			  if(response.courseList!=null){
				  $("#participantdiv").removeClass("hide");
				  setReportDetails(response.courseList,participantColumnData,'participanttable'); 
			  }			  		  
			}
		});
	}
	
	var hideReportData = function(){
		$("#usersreporttablediv").addClass("hide");		
		$("#participantdiv").addClass("hide");
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
