<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
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
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<div class="col-sm-12">
			<div class="content-wrapper">
				<section class="content-header">
					
				</section>
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box no-border">
								<div class="box-body">
									<div class="form-group" style="text-align: center" id="frame">
									
									</div>
									<div>
									 <label class="h3"><spring:message code="lbl.comments" text="Comments"/></label>
									</div>
									<div class="form-group">
						            <table class="table" id="contentdatatable">
									<thead>
										<tr>
											<th style="width: 3%">											
											</th>
											<th><spring:message code="lbl.report" text="Report"/></th>
											<th><spring:message code="lbl.coursetitle" text="Course Title"/></th>
											<!--  <th>Size(in KB)</th>	 -->										
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="list">
										<c:set var="i" value="0"></c:set>
											<tr>
											    <td></td>									    												
												<td>
												    ${list.abuseReport}
												</td>
												<td>
												  ${list.courseMapped}
												  <%-- <a href="coursepreview?courseId=${list.courseId}&isPublish=1&contentId=${list.contentId}">${list.courseMapped}</a> --%>
												</td>												
												 										
											</tr>
										</c:forEach>
									</tbody>									
								</table>
									</div>
									
									
								</div>

								<div class="box-footer ">
									<div class="pull-right">										
										<button type="button" id="cancel"
											class="btn btn-default btn-flat"><spring:message code="lbl.cancel" text="Cancel"/></button>
									</div>
								</div>

							</div>
							<!-- /.box -->

						</div>
						<!-- /.col -->
					</div>
				</section>
			</div>
			<div class="control-sidebar-bg"></div>
		</div>
		<!-- ./wrapper -->
	</div>
</body>
<script type="text/javascript">

$(function () {
	
	$(".treeview").removeClass("active");
	$("#uploadcontent").addClass("active");
	$("#uploadcontent .treeview-menu > #uploadcontent").addClass("active");
	showContentNew('${content.contentPath}','${content.contentType}');
	
	$('#cancel').click(function() {
		  location.href="listuploadcontent";
      });
});

/**
 * @summary This is used for showing content of Course.
 * 
 * @return no.
 */
function showContentNew(contentURL,type) {
	console.log(contentURL+" "+type);     	
	if(type=="PPT"){
		/*window.open("http://docs.google.com/viewerng/viewer?url="+contentURL);*/
		$('.modal-lg').css('width', '90%');
		$("#frame").empty();
		var frameHTML = '<iframe src="http://docs.google.com/viewerng/viewer?url='
				+ contentURL
				+ '&embedded=true" style="width:100%;height:500px" frameborder="0" allowfullscreen webkitallowfullscreen></iframe>';
		$("#frame").append(frameHTML);
		$("#showContentid").modal('show');
	}else{
		$('.modal-lg').css('width', '90%');
		$("#frame").empty();
		var frameHTML = '<iframe src="'
				+ contentURL
				+ '" style="width:100%;height:500px" allowfullscreen webkitallowfullscreen></iframe>';
		$("#frame").append(frameHTML);
		
		$("#showContentid").modal('show');		
	}
	
}
</script>
</html>