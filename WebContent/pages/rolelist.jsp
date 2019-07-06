<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>
.body-color {
background-color:#F0F0F0;
}
.color-mainblue{
color:#05B26F;
}
.button-color-blue{
background-color:white;
color:#4B5960;
border:0.1em solid #4B5960;
}
.button-green-color{
border-width: 0.1em;
color:white;
border:1px solid #05B26F;
background-color:#05B26F;
}

.bottom-border{
border:1px solid #F0F0F0;
margin:0px;
height:100%
}
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
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position:fixed;left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>        
		<!-- start dataTable----->
		<div class="col-sm-12" >
		<div class="content-wrapper">
			<section class="content-header">
		<h3 style="margin:0"><spring:message code='lbl.rolemanagement' text='Role Management'/>
				<button id="addnew" class="btn btn-flat btn-success button-width-large pull-right" onclick="location.href='addNewRole'"><spring:message code="lbl.addnewrole" text="Add New Role"/></button>
				</h3>
			</section>
			<section class="content">
			<div class="row">			 
				<div class="col-md-12">
					<div class="box no-border">
						<!-- /.box-header -->
						<div class="box-body ">
							<div class="table-responsive mailbox-messages col-xs-12">
								<table class="table table-hover table-striped" id="userList">
									<thead>
										<tr>
										    <th style="width:100px"><spring:message code="lbl.roleid" text="Role Id"/></th>
											<th style="width:100px"><spring:message code="lbl.rolename" text="Role Name"/></th>
											<th><spring:message code="lbl.application" text="Application"/></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${rolelist}" var="rolelist">
										<tr>
											<td>
											${rolelist.roleId}
											</td>
											<td>
											${rolelist.roleName}
											</td>
											<td>											
											 <c:forTokens items="${rolelist.functionsName}" delims="," var="name" varStatus="loop">                                              
                                              <spring:message code="${fn:trim(name)}"></spring:message>
                                              ${!loop.last ? ',' : ''}
                                             </c:forTokens>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- /.table -->
							</div>
							<!-- /.mail-box-messages -->
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /. box -->
				</div>
				<!-- /.col -->
			</div>
			</section>
			</div>
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->


</body>
<script type="text/javascript">
$(function () {
	
	  $(".treeview").removeClass("active");
      $("#rolemanagement").addClass("active");
      $("#rolemanagement .treeview-menu > #rolemanagement").addClass("active");
    $("#userList").dataTable({'aaSorting': [],"language": datatablelanguagejson});
    });
</script>

</html>