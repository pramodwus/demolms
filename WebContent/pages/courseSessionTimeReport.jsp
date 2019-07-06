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
					<h1> <spring:message code="lbl.coursesessiontimeout" text="Course Session Time Report"/></h1>
				</section>
				<!-- /.content-header -->
				<section class="content">
					<div class="row">
					 <div class="row form-group">
					 <div class="col-sm-9">
					   <label><spring:message code="lbl.selectuser" text="Select User"/>:&nbsp;</label>
							<select id="userId" class="form-control">
							  <option value="-1">All</option>
							  <c:forEach items="${userList}" var="list">
							  <option value="${list.userId}">${list.userName} (${list.userId})</option>
							  </c:forEach>
							</select>
					 </div>					 
					</div>
					
					<div class="row form-group">
					 <div class="col-sm-9">
					   <label><spring:message code="lbl.selectcourse" text="Select Course"/>:&nbsp;</label>
							<select id="courseId" class="form-control">
							  <option value="-1">All</option>
							  <c:forEach items="${courseList}" var="list">
							  <option value="${list.courseId}">${list.courseName}</option>
							  </c:forEach>
							</select>
					 </div>
					 <div class="col-sm-3">
					 <div>&nbsp;</div>
					  <button type="button"
									class="btn btn-flat btn-success button-width-large searchFilterButton" style="margin-top:5px">
									<spring:message code="lbl.search" text="Search"/>
					 </button>
					 </div>
					</div> 	
						
						<div class="col-sm-12 table-responsive filteritems "
							id="filteritems">
							<table class="table table-hover table-striped table-bordered"
								id="usersreporttable">
								<thead>
									<tr id="usersreporttable_header">
									<th><spring:message code="lbl.username" text="User Name"/></th>
									<th><spring:message code="lbl.coursename" text="Course Name"/></th>
									<th><spring:message code="lbl.timespent" text="Time Spent"/></th>
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
        
</script>        
<script src="resources/adminlte/plugins/datatables/dataTables.buttons.min.js"></script>
<script src="resources/adminlte/plugins/datatables/buttons.flash.min.js"></script>
<script src="resources/adminlte/plugins/datatables/jszip.min.js"></script>
<script src="resources/adminlte/plugins/datatables/pdfmake.min.js"></script>
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
		$('input[type="radio"].flat-red').iCheck({
	          radioClass: 'iradio_flat-green'
	        });
		//getDataByFilter();
	});
	
	/**
	 * call on click of search button. 
	 */
	$(document).on("click", ".searchFilterButton", function() {
		getDataByFilter();
		$('html, body').animate({ scrollTop: $(document).height() }, 1200);
	});   
			        
	/**
	 * course list column data.
	 */
	var usersColumnData = [ {
		"data" : "levelName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "courseName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "courseModifyTime",
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
		var columnData = '';
		columnData = usersColumnData;
		$('#usersreporttable').DataTable().destroy();
		//$('#usersreporttable tbody').empty();
		
		oTable = $('#usersreporttable')
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
							"ajax" : {
								'url' : 'getFilterCourseSessionReport?userId='+$("#userId").val()+'&courseId='+$("#courseId").val(),
								"dataSrc" : "",
							},
							"columns" : columnData,
							'aaSorting' : []
						});
	}
</script>
</html>
