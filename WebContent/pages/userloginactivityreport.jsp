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
					<h1><spring:message code="lbl.userloginactivityreport" text="User Login Activity Report"/></h1>
				</section>
				<!-- /.content-header -->
				<section class="content">
					<div class="row">
					<table class="table">
					<tbody>					
					<tr>
					<td class="col-sm-5">
					<div class="input-group no_margin_padding">
					<label class="input-group-addon no_margin_padding"><b><spring:message code="lbl.daterange" text="Date Range"/>:</b>&nbsp;</label> 
									 <input
										type="text" class="form-control" id="daterange">
									<div class="input-group-addon input-group-addon-child">
										<i class="fa fa-calendar"></i>
									</div>
								</div>
								</td>
					<td class="col-sm-7 lastChild" ><button type="button"
									class="btn btn-flat btn-success button-width-large searchFilterButton" style="margin-left:7%"><spring:message code="lbl.search" text="Search"/></button></td></tr>
					</tbody>
					</table>
						
						<div class="col-sm-12 table-responsive filteritems "
							id="filteritems">
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
        messages['lbl.userid'] = "<spring:message code='lbl.userid' text='User ID' javaScriptEscape='true'/>";
        messages['lbl.firstname'] = "<spring:message code='lbl.firstname' text='First Name' javaScriptEscape='true'/>";
        messages['lbl.lastname'] = "<spring:message code='lbl.lastname' text='Last Name' javaScriptEscape='true'/>";
        messages['lbl.emailid'] = "<spring:message code='lbl.emailid' text='Email ID' javaScriptEscape='true'/>";
        messages['lbl.lastlogindateandtime'] = "<spring:message code='lbl.lastlogindateandtime' text='Last Login Date and Time' javaScriptEscape='true'/>";
        messages['lbl.referringip'] = "<spring:message code='lbl.referringip' text='Referring IP' javaScriptEscape='true'/>";
        messages['lbl.webbrowsername'] = "<spring:message code='lbl.webbrowsername' text='Web Browser Name' javaScriptEscape='true'/>";
        messages['lbl.deviceosname'] = "<spring:message code='lbl.deviceosname' text='Device OS Name' javaScriptEscape='true'/>";
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
		$('#daterange').daterangepicker({
			/* "maxDate" : maxDate, */
			locale : {
				format : 'YYYY/MM/DD'
			},
			/* "drops": "up" */
		});
		$('input[type="radio"].flat-red').iCheck({
	          radioClass: 'iradio_flat-green'
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
	 * header for users list.
	 */
	var usersHeader = '<th>'+messages['lbl.userid']+'</th>'
	                + '<th>'+messages['lbl.firstname']+'</th>'
			        + '<th>'+messages['lbl.lastname']+'</th>'
			        + '<th>'+messages['lbl.emailid']+'</td>'
			        + '<th>'+messages['lbl.lastlogindateandtime']+'</th>'
			        + '<th>'+messages['lbl.referringip']+'</th>'
			        + '<th>'+messages['lbl.deviceosname']+'</th>'
			        + '<th>'+messages['lbl.webbrowsername']+'</th>'
			        ;			        
			        
	/**
	 * course list column data.
	 */
	var usersColumnData = [ {
		"data" : "userId",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "firstName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "lastName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "email",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "lastLoginDate",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "ipAddress",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "os",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "browser",
		defaultContent : "",
		sortable : true
	}
		
	 ];
	

	/**
	 * call function for getting list.
	 */
	function getDataByFilter() {
		var columnData = '';
		
		$("#usersreporttable_header").empty();		
			columnData = usersColumnData;
			$("#usersreporttable_header").append(usersHeader);
		
		$('#usersreporttable').DataTable().destroy();
		$('#usersreporttable tbody').empty();
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
								'url' : 'getFilterActiveLoginUsers?dateRange='+ $.trim($('#daterange').val()),
								"dataSrc" : "",
							},
							"columns" : columnData,
							'aaSorting' : []
						});
	}
</script>
</html>
