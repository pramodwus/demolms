<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
					<h1><spring:message code="lbl.adhocreport" text="Adhoc Report"/></h1>

				</section>
				<!-- /.content-header -->
				<section class="content">
					<div class="row">
					<div class=" col-sm-12">
					<table class="table">
					<tbody>
					<tr>
					<td class="col-sm-5">
					        <label><spring:message code="lbl.course" text="Course"/>/<spring:message code="lbl.assessment" text="Assessment"/>:&nbsp;</label>
								<input type="radio" name="searchFor" class="searchFor flat-red" value="course" checked> &nbsp;<spring:message code="lbl.course" text="Course"/>&nbsp;&nbsp;
								<input type="radio" name="searchFor" class="searchFor flat-red" value="test"> &nbsp;<spring:message code="lbl.assessment" text="Assessment"/>&nbsp;
						</td>
						<td class="col-sm-7">
								<label><spring:message code="lbl.createdby" text="Created By"/>:&nbsp;</label>
								<input type="radio" name="creator" class="creator flat-red" value="all" checked>&nbsp;<span><spring:message code="lbl.all" text="All"/></span>&nbsp;
								<c:if test="${fn:length(creator)>0}">
								<input type="radio" name="creator" class="creator flat-red" value="individual">&nbsp;<span><spring:message code="lbl.individual" text="Individual"/></span>&nbsp;&nbsp;
								</c:if>
								<c:if test="${fn:length(creator)==0}">
									<input type="radio" name="creator" class="creator flat-red" value="individual" disabled>&nbsp;<span><spring:message code="lbl.individual" text="Individual"/>&nbsp;&nbsp;(0 <spring:message code="lbl.trainer" text="Trainer"/>)</span>
								</c:if>
								<select style="display:inline-table;width: 220px"
										class="form-control" id="creator">
										<c:forEach items="${creator}" var="creator">
												<option value="${creator.email}">${creator.email}</option>
										</c:forEach>
									</select>
							</td>
						</tr>
						<tr>
								<td class="col-sm-5">
								<label style="margin-left:10%"><spring:message code="lbl.type" text="Type"/>:&nbsp;</label>
								<c:forEach items="${publishstatus}" var="publishStatus">
								<input type="radio" name="publishStatus" class="publishStatus flat-red" value="${publishStatus.value}" <c:if test="${publishStatus.value == 1}">checked</c:if>> &nbsp;<spring:message code="${publishStatus.key}"/>&nbsp;
								</c:forEach>
							   </td>
							   
							   <td class="col-sm-7">
					<label style="margin-left:7%"><spring:message code="lbl.tags" text="Tags"/>:&nbsp;&nbsp;&nbsp;</label>
								<input type="radio" name="tag" class="tag flat-red" value="all" checked>&nbsp;<span><spring:message code="lbl.all" text="All"/></span>&nbsp;
								<input type="radio" name="tag" class="tag flat-red" value="individual" onselect="tagToggle();">&nbsp;<span><spring:message code="lbl.individual" text="Individual"/></span>&nbsp;&nbsp;
								<select style="display:inline-table;width: 220px;"
										class="form-control" id="tag">
										<c:forEach items="${taglist}" var="taglist">
											<option value="${taglist}">${taglist}</option>
										</c:forEach>
									</select>
							 </td>
							</tr>
							
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
				    </div>
						
						<div class="col-sm-12 table-responsive filteritems "
							id="filteritems">
							<table class="table table-hover table-striped table-bordered"
								id="customreporttable">
								<thead>
									<tr id="customreporttable_header">
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
        messages['lbl.coursename'] = "<spring:message code='lbl.coursename' text='Course Name' javaScriptEscape='true'/>";
        messages['lbl.creator'] = "<spring:message code='lbl.creator' text='Creator' javaScriptEscape='true'/>";
        messages['lbl.publisheddate'] = "<spring:message code='lbl.publisheddate' text='Published Date' javaScriptEscape='true'/>";
        messages['lbl.status'] = "<spring:message code='lbl.status' text='Status' javaScriptEscape='true'/>";
        messages['lbl.enrollstudents'] = "<spring:message code='lbl.enrollstudents' text='Enroll Students' javaScriptEscape='true'/>";
        messages['lbl.drafted'] = "<spring:message code='lbl.drafted' text='Drafted' javaScriptEscape='true'/>";
        messages['lbl.published'] = "<spring:message code='lbl.published' text='Published' javaScriptEscape='true'/>";
        messages['lbl.scheduled'] = "<spring:message code='lbl.scheduled' text='Scheduled' javaScriptEscape='true'/>";
        messages['lbl.na'] = "<spring:message code='lbl.na' text='NA' javaScriptEscape='true'/>";
        messages['lbl.testname'] = "<spring:message code='lbl.testname' text='Test Name' javaScriptEscape='true'/>";
        messages['lbl.noofstudents'] = "<spring:message code='lbl.noofstudents' text='No. of Students' javaScriptEscape='true'/>";
        messages['lbl.createddate'] = "<spring:message code='lbl.createddate' text='Created Date' javaScriptEscape='true'/>";
        messages['lbl.scheduleddate'] = "<spring:message code='lbl.scheduleddate' text='Scheduled Date' javaScriptEscape='true'/>";
        
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
			var maxDate =  '<%final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			                  out.print(dateFormat.format(new Date()));%>';

	$(document).ready(function() {
		$("#creator").hide();
		$("#tag").hide();
		$(".treeview").removeClass("active");
		$("#report").addClass("active");
		$("#report .treeview-menu > #report").addClass("active");
		$('#daterange').daterangepicker({
			/* "maxDate" : maxDate, */
			locale : {
				"format" : 'YYYY/MM/DD',
				"applyLabel": paggingmessages['lbl.apply'],
		          "cancelLabel": paggingmessages['lbl.cancel'],
		          "fromLabel": paggingmessages['lbl.from'],
		          "toLabel": paggingmessages['lbl.to'],
		          "customRangeLabel": "Custom",
		          "daysOfWeek": [
		              paggingmessages['lbl.sunday'],
		              paggingmessages['lbl.monday'],
		              paggingmessages['lbl.tuesday'],
		              paggingmessages['lbl.wednesday'],
		              paggingmessages['lbl.thursday'],
		              paggingmessages['lbl.friday'],
		              paggingmessages['lbl.saturday']
		          ],
		          "monthNames": [
		              paggingmessages['lbl.jan'],
		              paggingmessages['lbl.feb'],
		              paggingmessages['lbl.mar'],
		              paggingmessages['lbl.apr'],
		              paggingmessages['lbl.may'],
		              paggingmessages['lbl.jun'],
		              paggingmessages['lbl.jul'],
		              paggingmessages['lbl.aug'],
		              paggingmessages['lbl.sep'],
		              paggingmessages['lbl.oct'],
		              paggingmessages['lbl.nov'],
		              paggingmessages['lbl.dec']
		          ],
			},
			/* "drops": "up" */
		});
		$('input[type="radio"].flat-red').iCheck({
	          radioClass: 'iradio_flat-green'
	        });
	});
	
	/**
	 * toggle on creator radio button 
	 */
	$('.creator').on('ifChecked', function(event){
		  $("#creator").toggle();
		});
	
	/**
	 * toggle on tag radio button 
	 */
	$('.tag').on('ifChecked', function(event){
		  $("#tag").toggle();
		});
	/**
	 * call on click of search button. 
	 */
	$(document).on("click", ".searchFilterButton", function() {
		getDataByFilter();
		$('html, body').animate({ scrollTop: $(document).height() }, 1200);
	});
	/**
	 * header for course list.
	 */
	var courseHeader = '<th>'+messages['lbl.coursename']+'</th>'
	                + '<th>'+messages['lbl.creator']+'</th>'
			        + '<th id="typeofdate">'+messages['lbl.publisheddate']+'</th>'
			        + '<th>'+messages['lbl.status']+'</td>'
			        + '<th>'+messages['lbl.enrollstudents']+'</th>';
	/**
	 * course list column data.
	 */
	var courseColumnData = [ {
		"data" : "courseName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "teacherName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "date",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "publish",
		defaultContent : "",
		sortable : true,
		render : function(data, type, row) {
			switch (data) {
			case 0:
				return '<span class="label label-warning">'+messages['lbl.drafted']+'</span>';
				break;
			case 1:
				return '<span class="label label-success">'+messages['lbl.published']+'</span>';
				break;
			case 2:
				return '<span class="label label-info">'+messages['lbl.scheduled']+'</span>';
				break;
			default:
				return messages['lbl.na'];
				break;
			}
		}
	}, {
		"data" : "enrollCount",
		defaultContent : "",
		sortable : true,
		render : function(data, type, row) {
			return '<span class="label label-success">' + data + '</span>';
			;
		}
	}, ];
	/**
	 * header for test list.
	 */
	var testHeader = '<th>'+messages['lbl.testname']+'</th>'
	              + '<th>'+messages['lbl.creator']+'</th>'
			      + '<th id="typeofdate">'+messages['lbl.publisheddate']+'</th>'
			      + '<th>'+messages['lbl.status']+'</th>'
			      + '<th>'+messages['lbl.noofstudents']+'</th>';

	/**
	 * test list column data.
	 */
	var testColumnData = [ {
		"data" : "testName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "teacherName",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "date",
		defaultContent : "",
		sortable : true
	}, {
		"data" : "testStatus",
		defaultContent : "",
		sortable : true,
		render : function(data, type, row) {
			switch (data) {
			case 0:
				return '<span class="label label-warning">'+messages['lbl.drafted']+'</span>';
				break;
			case 1:
				return '<span class="label label-success">'+messages['lbl.published']+'</span>';
				break;
			case 2:
				return '<span class="label label-info">'+messages['lbl.scheduled']+'</span>';
				break;
			default:
				return messages['lbl.na'];
				break;
			}
		}
	}, {
		"data" : "totalAppearStudents",
		defaultContent : "",
		sortable : true,
		render : function(data, type, row) {
			return '<span class="label label-success">' + data + '</span>';
		}
	}, ];

	/**
	 * call function for getting list.
	 */
	function getDataByFilter() {
		var columnData = courseColumnData;
		$("#customreporttable_header").empty();
		if ($.trim($('.searchFor:checked').val()) == 'test') {
			 columnData = testColumnData;
			$("#customreporttable_header").append(testHeader);
		} else {
			$("#customreporttable_header").append(courseHeader);
		}
		if($.trim($('.publishStatus:checked').val())==0){
			$("#typeofdate").text(messages['lbl.createddate']);
		}
		else if($.trim($('.publishStatus:checked').val())==1){
			$("#typeofdate").text(messages['lbl.publisheddate']);
		}
		else if($.trim($('.publishStatus:checked').val())==2){
			$("#typeofdate").text(messages['lbl.scheduleddate']);
		}
		var creator = $.trim($('.creator:checked').val()) == "all"?'':'&creator='+$.trim($('#creator').val());
		var tag = $.trim($('.tag:checked').val()) == "all"?'':'&tag='+$.trim($('#tag').val());
		$('#customreporttable').DataTable().destroy();
		oTable = $('#customreporttable')
				.dataTable(
						{
							"processing" : true,
							"serverSide" : false,
							"language": datatablelanguagejson,
							"deferRender" : true,
							 'dom': 'Bfrtip',
					          'buttons': [
					                'csv', 'excel', 'pdf', 'print'
					          ],
							"ajax" : {
								'url' : 'getfilterreportdata?searchFor='
									+ $.trim($('.searchFor:checked').val())
									+ creator
									+ tag
									+ '&publishStatus='
									+ Number($.trim($('.publishStatus:checked')
											.val())) + '&dateRange='
									+ $.trim($('#daterange').val()),
									
								"dataSrc" : "",
							},
							"columns" : columnData,
							'aaSorting' : []
						});
	}
</script>
</html>
