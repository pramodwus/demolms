 <!-- Page for view content library -->
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <head>
<%-- <%@ include file="include.jsp"%> --%>
</head>
<script type="text/javascript">

$(document).ready(function() {
	$("#courseModal").modal('show');
});
</script>
	<div class="modal fade" id="courseModal" tabindex="-1" role="dialog"
		aria-labelledby="">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
				    <div class="box-header with-border">
                      <button type="button" class="close"  data-dismiss="modal">&times;</button>
				      <h3 class="modal-title"><spring:message code="lbl.importsection" text="Import Section"/></h3>				      
                    </div>
                    <div id="overlay" class="overlay1"
			           style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			         <img id="loading" class="lazy"
				     src="<spring:url value='/resources/images/loading.gif'/>"
				     style="position: fixed; left: 50%; top: 50%;">
		             </div>
					<div class="modal-body">
					       	
				               <table class="table table-hover table-striped" id="contentdatatable">
									<thead>
										<tr>
											<th style="width: 3%">											
											</th>
											<th><spring:message code="lbl.section" text="Section"/></th>
											<th><spring:message code="lbl.course" text="Course"/></th>
											<th><spring:message code="lbl.contentinfo" text="Content Info."/></th>																																
										</tr>
									</thead>
									<tbody>									   
										<c:forEach items="${sectionlist}" var="list">
											<tr>
												<td style="width: 3%">  <input type="checkbox" class="selectedSectionIds" value="${list.sectionId}"></td>
												<td>${list.sectionName}</td>
												<td>${list.courseName}</td>
												<td>${list.contentInfo}</td>
												<%-- <td> </td> --%>
																																			 										
											</tr>
										</c:forEach>
									</tbody>									
								</table>								
						<div class="box-footer">
						    <button type="button" id="importSectionButtonInTable" onclick="importsectioncontents()" class="btn btn-success btn-flat button-width-large" disabled>
						    <spring:message code="lbl.import" text="Import"/>
						    </button>
						    <span id="warning" class="text-red"></span>                  
                       </div>						
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
       var sectionlistmessages = new Array();
       sectionlistmessages['msg.pleaseselectatleastonetobeimport'] = "<spring:message code='msg.pleaseselectatleastonetobeimport' text='Please select at least one to be import.' javaScriptEscape='true' />";
</script>  	
<script type="text/javascript">
var oTable;
$(function () { 
	if(oTable!=null){
		$('#contentdatatable').DataTable().destroy();
	}
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
	$(document).on('click', '.selectedSectionIds', function() {
		var boxes = $('.selectedSectionIds');
	   $('#importSectionButtonInTable').prop('disabled', !boxes.filter(':checked').length);
	}).trigger('change');
});


/*Function for */
var importsectioncontents = function(){
        
        	var val=[];
        	var name=[];
            /* $(':checkbox:checked').each(function(i){
                val[i]=$(this).val();
            }); */            
            $(":checkbox:checked", oTable.fnGetNodes()).each(function(i) {
            	val[i]=$(this).val();
            });
             if(val.length == 0)
            {
                //alert("Please select at least one checkbox to be import!");
            	 $("#warning").html('');     
                 $("#warning").text(sectionlistmessages['msg.pleaseselectatleastonetobeimport']);
            }
            else
            {
                //if(confirm("Are you sure you want to import selected item(s)?")){
                	$("#overlay").show();
                	$.ajax({
               		   url:"importsectioncontents?sections="+val+"&courseId="+$("#courseid").val(),
               		   type:'POST',
               		   error : function(){
               			alert("error");
               		   },
               		   success : function(status){
               			//$("#overlay").hide();   
               			$("#courseModal").modal('hide');
               			location.href="addEditCourseMaterial?courseId="+$("#courseid").val();
               		   }
               		});
                
            }
        
}
</script>
