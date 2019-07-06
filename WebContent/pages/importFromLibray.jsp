<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<link rel="stylesheet" href="resources/css/custom.css">
<%@ include file="include.jsp"%>
 <script src="<spring:url value='/resources/js/uploadcontent.js'/>"></script>
 <style>

.content-wrapper {
margin: auto;
margin-left:230px;
}

@media only screen and (min-width: 767px) {
    .content-wrapper {
        /* background-color: yellow; */
        margin-left:230px;
    }
}
@media only screen and (max-width: 760px) {
  .content-wrapper {
       /*  background-color: pink; */
        margin-left:0px;
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
		<div class="col-sm-12" >
		<div class="content-wrapper">
		<div class="row">
		<div class="col-sm-12">
			<section class="content-header">
				<div class="pull-left">
					<h3 style="margin: 0"><spring:message code="lbl.importfromlibrary" text="Import From Library"/></h3><h3></h3>
				</div>
				<br />
			</section>
			</div></div>
			<section class="content">
				<form id="importContentForm" action="saveImportContentData" method="post">
					<div class="row">
						<div class="col-md-12">
							<div class="box no-border">
								<div class="box-header">
									<div class="form-group">
		   								<label><spring:message code="lbl.newtitle" text="New Title"/>: &nbsp;</label>
										<input type="text" class="form-control" id="title" name="title" placeholder="<spring:message code="lbl.entercontenttitle" text="Enter content title"/>" maxlength="50" onkeyup="keyValidate()">
										<div class="col-xs-12" ><label class="requireFld" id="titleError"><spring:message code="msg.empty" text="This field is required."/></label></div>
		   							</div>
								</div>
								<%-- <div class="col-xs-12" ><label><spring:message code="sg.pleaseselectvideofromlist" text="Please select video from list."/></label></div> --%>
								<!-- /.box-header -->
								<div class="box-body no-padding">
									<div class="table-responsive mailbox-messages col-xs-12" style="max-height: 500px;">
										<table class="table table-hover table-striped"
											id="questionData">
											<thead>
												<tr>
													<th style="width: 3%">
														&nbsp;
													</th>
													<th><spring:message code="lbl.title" text="Title"/></th>
												</tr>
											</thead>
											<tbody>
												<c:set var="cnt" value="0"/>
												<c:forEach items="${contentlist}" var="list">
													<c:if test="${list.contentType=='VIDEO' && flag==0}">
														<tr>
															<td style="width: 3%">
																<c:if test="${cnt==0}">
																	<input type="radio" checked="checked" name="contentId" id="contentId${list.contentId}" value="${list.contentId}">
																</c:if>
																<c:if test="${cnt!=0}">
																	<input type="radio" name="contentId" id="contentId${list.contentId}" value="${list.contentId}">
																</c:if>
															</td>
															<td>
																<c:if test="${list.contentName!=null}">													  
															  		<%-- <a class="cursor-pointer"onclick="showContentNew('${list.contentPath}','${list.contentType}')"> --%>
																		${list.contentName}	
																	<!-- </a> -->
																</c:if>
																<c:if test="${list.contentName==null}">
																  	<%-- <a class="cursor-pointer" onclick="showContentNew('${list.contentPath}','${list.contentType}')"> --%>
																		${list.content}	
																	<!-- </a> -->
																</c:if>													
															</td>
														</tr>
														<c:set var="cnt" value="1"/>
													</c:if>
													<c:if test="${list.contentType=='PDF' && flag==1}">
														<tr>
															<td style="width: 3%">
																<c:if test="${cnt==0}">
																	<input type="radio" checked="checked" name="contentId" id="contentId${list.contentId}" value="${list.contentId}">
																</c:if>
																<c:if test="${cnt!=0}">
																	<input type="radio" name="contentId" id="contentId${list.contentId}" value="${list.contentId}">
																</c:if>
															</td>
															<td>
																<c:if test="${list.contentName!=null}">													  
															  		<%-- <a class="cursor-pointer"onclick="showContentNew('${list.contentPath}','${list.contentType}')"> --%>
																		${list.contentName}	
																	<!-- </a> -->
																</c:if>
																<c:if test="${list.contentName==null}">
																  	<%-- <a class="cursor-pointer" onclick="showContentNew('${list.contentPath}','${list.contentType}')"> --%>
																		${list.content}	
																	<!-- </a> -->
																</c:if>													
															</td>
														</tr>
														<c:set var="cnt" value="1"/>
													</c:if>
													<c:if test="${(list.contentType=='PPT' || list.contentType=='PPTX') && flag==2}">
														<tr>
															<td style="width: 3%">
																<c:if test="${cnt==0}">
																	<input type="radio" checked="checked" name="contentId" id="contentId${list.contentId}" value="${list.contentId}">
																</c:if>
																<c:if test="${cnt!=0}">
																	<input type="radio" name="contentId" id="contentId${list.contentId}" value="${list.contentId}">
																</c:if>
															</td>
															<td>
																<c:if test="${list.contentName!=null}">													  
															  		<%-- <a class="cursor-pointer"onclick="showContentNew('${list.contentPath}','${list.contentType}')"> --%>
																		${list.contentName}	
																	<!-- </a> -->
																</c:if>
																<c:if test="${list.contentName==null}">
																  	<%-- <a class="cursor-pointer" onclick="showContentNew('${list.contentPath}','${list.contentType}')"> --%>
																		${list.content}	
																	<!-- </a> -->
																</c:if>													
															</td>
														</tr>
														<c:set var="cnt" value="1"/>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
										<!-- /.table -->
									</div>
									<!-- /.mail-box-messages -->
								</div>
								<!-- /.box-body -->
								<div class="box-footer">
									<input type="hidden" id="mode" name="mode" value="${flag}"/>
									<button class="btn btn-default btn-flat button-width-large" id="cancel" type="button"><spring:message code="lbl.cancel" text="Cancel"/></button>
	       							<button class="btn btn-success btn-flat button-width-large pull-right" id="savebtn" onclick="importContent();" type="button"><spring:message code="lbl.submit" text="Submit"/></button>
								</div>
							</div>
							<!-- /. box -->
						</div>
						<!-- /.col -->
					</div>
				</form>
			</section>
		</div>
		<!-- content-wrapper -->
		</div>
		<!-- End dataTable----->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<div class="modal fade" id="contentErrorAlert" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p></p>
						<button type="button"
							class="btn btn-primary button-width-large button-blue"
							data-dismiss="modal"><spring:message code="lbl.ok" text="Ok"/></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="showContentid" role="dialog"
						aria-labelledby="showContent">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content ">
								<div class="modal-body page-background-color">
									<div class="row">
										<div class="col-xs-12" style="text-align: center" id="frame">
										</div>
										<div class="col-xs-12" style="text-align: center" id="promoVideoDiv">
								          <div id="promoVideo"></div>																
							            </div>
										<div class="col-xs-12" style="min-height: 30px"></div>
										<div class="col-xs-12" style="min-height: 30px">
											<button type="button" onclick="closeModalnew()"
												class="btn btn-success btn-flat  button-width-large pull-right"
												style="margin: auto" data-dismiss="modal"><spring:message code="lbl.close" text="close"/></button>
										</div>
									</div>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
</body>
<script src="<spring:url value='/resources/js/youtubeplayer.js?v=1'/>"></script>
<script>
      $(function () {   
    	  $(".treeview").removeClass("active");
	      $("#uploadcontent").addClass("active");
	      $("#uploadcontent .treeview-menu > #uploadcontent").addClass("active");
            $('.mailbox-messages input[type="radio"]').iCheck({
            	checkboxClass: 'icheckbox_square-green',
		          radioClass: 'iradio_square-green'
            });     
            $("#cancel").click(function(){
				location.href="listuploadcontent";
			});
         });
      function showContentNew(contentURL,type) {
       		$('.modal-lg').css('width', '90%');
       		$("#frame").empty();
       		var frameHTML = '<iframe src="'
       				+ contentURL
       				+ '" style="width:100%;height:500px" allowfullscreen webkitallowfullscreen></iframe>';
       		$("#frame").append(frameHTML); 
       		$("#showContentid").modal('show');
       }
     function closeModalnew(){
    		$("#promoVideo").remove();
    		$('#promoVideoDiv').empty();
    		$('#frame').empty();
    		
    	}  
     function importContent(){
    	 if(validate()){
    		$("#overlay").show();
    	 	$("#importContentForm").submit();
    	 }
     }
     function validate(){
    	 if($.trim($("#title").val())==""){
   		 	$("#title").css("border-color","#c95b5b");
			$("#titleError").fadeIn();
   			$('#title').focus();
   	  	    return false;
    	 }
    	 return true;
     }
     function keyValidate(){
   	 	$("#title").css("border-color","");
		$("#titleError").fadeOut();
     }
    </script>
</html>