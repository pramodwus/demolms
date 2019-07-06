
<!-- Page for view content library -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<%--  <%@ include file="/pages/dialogs.jsp"%> --%>
<%-- <%@ include file="include.jsp"%> --%>
</head>
<script type="text/javascript">

$(document).ready(function() {
	$("#contentModal").modal('show');
});
</script>
<div class="modal fade" id="contentModal" tabindex="-1" role="dialog"
	aria-labelledby="">
	<div class=" col-md-12 col-sm-12 col-xs-12">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="box-header with-border">
					<button type="button" class="close"
						onclick="closeModal(${sectionId})" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">
						<spring:message code="lbl.contentlibrary" text="Content Library" />
					</h3>
					<input type="hidden" id="section_id" value="${sectionId}">
					<input type="hidden" id="attemptd_id" value="${attemptId}">
					<input type="hidden" id="session_id" value="${sessionId}">
				</div>
				<div id="overlay" class="overlay1"
					style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
					<img id="loading" class="lazy"
						src="<spring:url value='/resources/images/loading.gif'/>"
						style="position: fixed; left: 50%; top: 50%;">
				</div>
				<div class="modal-body">
					<%-- <c:if test="${fn:length(contentlist) == 0}">
					       	   <div class="col-sm-12" style="padding-left:0px"><button type="button" class="btn btn-flat btn-success pull-left" style="margin-bottom:10px" id="add-content-from-external-for-empty-lib" data-dismiss="modal"><spring:message code="lbl.addfilefromexternal" text="Add File From External"/></button></div>
				    </c:if> --%>
					<table class="table table-hover table-striped"
						id="contentdatatable">
						<thead>
							<tr>
								<th style="width: 3%"></th>
								<th><spring:message code="lbl.title" text="Title" /></th>
								<th><spring:message code="lbl.type" text="Type" /></th>
								<!--  <th>Size(in KB)</th>	 -->
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${contentlist}" var="list">
								<c:set var="i" value="0"></c:set>
								<tr>
									<c:forEach items="${mappedcontentlist}" var="maplist">
										<c:if test="${maplist==list.contentId}">
											<c:set var="i" value="1"></c:set>
										</c:if>
									</c:forEach>
									<c:if test="${i==0}">
										<td style="width: 3%"><input type="checkbox"
											class="selectedContentIds" value="${list.contentId}"></td>
									</c:if>
									<c:if test="${i==1}">
										<td style="width: 3%"><input type="checkbox"
											checked="checked" class="selectedContentIds"
											value="${list.contentId}"></td>
									</c:if>
									<td><c:if test="${list.contentName!=''}">
												 ${list.contentName}
												</c:if> <c:if test="${list.contentName==''}">
												 ${list.content}
												</c:if></td>
									<td>${list.contentType}</td>
									<%-- <td>												  
												   ${list.size}												  					  											  
												  </td>	 --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="box-footer">
						<button type="button" onclick="setintocontentlist()"
							id="importContentButtonInTable"
							class="btn btn-flat btn-success button-width" disabled>
							<spring:message code="lbl.import" text="Import" />
						</button>
						<span id="warning" class="text-red"></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
       var opencontentmessages = new Array();
       opencontentmessages['msg.pleaseselectatleastonetobeimport'] = "<spring:message code='msg.pleaseselectatleastonetobeimport' text='Please select at least one to be import.' javaScriptEscape='true' />";
</script>
<script type="text/javascript">
var oTable;
$(function () {
	
	oTable = $("#contentdatatable").dataTable({
	"aaSorting": [],
	"language": datatablelanguagejson,
	"columnDefs": [
		               { "orderable": false, "targets": 0 }	  			               
		             ]		      		  
		  });
	
	/**
	 * listner on selectedQuestionIds class for making enable and disable the import question button.
	 */
	$(document).on('click', '.selectedContentIds', function() {
		var boxes = $('.selectedContentIds');
	   $('#importContentButtonInTable').prop('disabled', !boxes.filter(':checked').length);
	}).trigger('change');
});

var arr='${mappedcontentlist}';

/*Function for */
var setintocontentlist = function(){
	//debugger;
	var val=[]; 
	var dummy=[];  
	 console.log(arr);   
	 var j=0;
    $(":checkbox:checked", oTable.fnGetNodes()).each(function(i) {
    	dummy[i] = $(this).val(); 
    	//if(arr.indexOf($(this).val()) == -1){    		
    		val[j]=$(this).val();
    		j++;
    	//}
    	
    });
    
    if(dummy.length == 0)
    {   $("#warning").html('');     
        $("#warning").text(opencontentmessages['msg.pleaseselectatleastonetobeimport']);			
    }
    else
    {       
      // debugger;
    	var sectionId=$("#section_id").val();
    	var attemptId=$("#attemptd_id").val();
    	var sessionId=$("#session_id").val();
    	$("#warning").hide();
    	$("#overlay").show();
    	$.ajax({
    	url:"mapcontentintosection?contents="+val+"&sectionId="+sectionId+"&attempId="+attemptId+"&sessionId="+sessionId,
    	type:'POST',
    	error : function(){
    	alert("error");
    	},
    	success : function(status){
    	$("#overlay").hide(); 
    	$("#contentModal").modal('hide');
    	location.href="addEditCourseMaterial?courseId="+$("#courseid").val();
    	}
    	});
    }
}

</script>
