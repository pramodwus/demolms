<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
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
			<section class="content">
			<div class="row">			 
				<div class="col-md-12">
				<div class="box no-border">
				<div class="box-header with-border">
				<c:if test="${action=='user' || action==null}">
				<h3 class="box-title"><spring:message code="lbl.inviteuser" text="Invite User"/></h3>
				</c:if> 
				<c:if test="${action=='trainer'}">
				<h3 class="box-title"><spring:message code="lbl.invitetrainer" text="Invite Trainer"/></h3>
				</c:if> 
				<c:if test="${action=='trainee'}">
				<h3 class="box-title"><spring:message code="lbl.invitetrainee" text="Invite Trainee"/></h3>
				</c:if>              
                </div><!-- /.box-header -->
				<form action="createUser" name="createUserForm" id="createUserForm" method="post">
				<input type="hidden" name="action" value="${action}">
                  <div class="box-body">
                    <div class="form-group col-sm-12">
                      <label for="email" class="col-sm-2 control-label"><spring:message code="lbl.emailid" text="Email Id"/></label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" placeholder="<spring:message code="lbl.emailid" text="Email Id"/>" onkeyup="keyValidate()">
						<label class="requireFld" for="form-field-1" id="emailError1"><spring:message code="msg.empty" text="This field is required."/></label>
						<label class="requireFld" for="email111" id="emailError2"><spring:message code="msg.email.invalid" text="Please provide a valid Email Id."/></label>
						<label class="requireFld" for="email" id="emailError3"><spring:message code="msg.email.already.exist" text="Email id is already registered! Please enter new."/></label>
                      </div>
                    </div>
                      
                      <c:if test="${roleId!=null && roleId >0}">
                      <select class="form-control hide" name="roleId" id="userRole" onchange="keyValidate()">
                      <option value="0"><spring:message code="lbl.selectuserrole" text="Select User Role"/></option>
                      <c:forEach items="${roleList}" var="role">
                      <c:choose>
                      <c:when test="${roleId == role.roleId}">
                      <option value="${role.roleId}" selected>${role.roleName}</option>
                      </c:when>
                      <c:otherwise>
                      <option value="${role.roleId}">${role.roleName}</option>
                      </c:otherwise>
                      </c:choose>
                      </c:forEach>
                      </select>
                      </c:if>
                      
                      
                      
                      <c:if test="${roleId==null}">
                      <div class="form-group col-sm-12">
                      <label for="role11" class="col-sm-2 control-label"><spring:message code="lbl.role" text="Role"/></label>
                      <div class="col-sm-10">
                      <select class="form-control" name="roleId" id="userRole" onchange="keyValidate()">
                      <option value="0"><spring:message code="lbl.selectuserrole" text="Select User Role"/></option>
                      <c:forEach items="${roleList}" var="role">
                      <option value="${role.roleId}">${role.roleName}</option>
                      </c:forEach>
                      </select>
                      <label class="requireFld" for="form-field-1" id="userRoleError"><spring:message code="msg.rolerequiredforuser" text="A role is required selected for user."/></label>
                      </div>
                    </div>
                      </c:if>
                      
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                    <div class="col-sm-12">
                    <div class="col-sm-12">
                    <button type="button" class="btn btn-default btn-flat button-width-large" onclick="location.href='userlist'"><spring:message code="lbl.cancel" text="Cancel"/></button>
                   <c:if test="${action=='user' || action==null}">
				   <button type="button" class="btn btn-success btn-flat button-width-large pull-right" onclick="submitForm()"><spring:message code="lbl.inviteuser" text="Invite User"/></button>
				   </c:if> 
				   <c:if test="${action=='trainer'}">
				    <button type="button" class="btn btn-success btn-flat button-width-large pull-right" onclick="submitForm()"><spring:message code="lbl.invitetrainer" text="Invite Trainer"/></button>
				   </c:if> 
				   <c:if test="${action=='trainee'}">
				    <button type="button" class="btn btn-success btn-flat button-width-large pull-right" onclick="submitForm()"><spring:message code="lbl.invitetrainee" text="Invite Trainee"/></button>
				   </c:if> 
                    </div>
                    </div>
                  </div><!-- /.box-footer -->
                </form>
                </div>
				</div>
				<!-- /.col -->
			</div>
			</section>
			</div>
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->
	
<div class="modal fade" id="successdialog" tabindex="-1"
		role="dialog" aria-labelledby="successdialog">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p><spring:message code="msg.success.verification.mail" text="Email verification link has been sent successfully! Verify your email address and login again."/></p>
						<buttons type="button" class="btn btn-success button-width-large btn-flat"
						data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script>
var action = '${action}';
$(document).ready(function(){
	 $(".treeview").removeClass("active");
 	 $("#users").addClass("active");
       if(action=="trainer"){    		  
 		  $("#teacherInvite").addClass("active");
 	  }
       else if(action=="trainee"){    		  
  		  $("#studentInvite").addClass("active");
  	  }
       else{    		  
    	   $("#users .treeview-menu > #usersInvite").addClass("active");
 	  }
       
       $('#createUserForm').on('keyup keypress', function(e) {
    	   var keyCode = e.keyCode || e.which;
    	   if (keyCode === 13) { 
    	     e.preventDefault();
    	     return false;
    	   }
    	 });
});
function validate(){
	 var email = document.getElementById('email').value;
	 if( email.trim() == "" ){
	  		
	  		$("#email").css("border-color","#c95b5b");
			$("#emailError1").fadeIn();
	  	  	document.createUserForm.email.focus();
	  	    return false;
	  	}
	  var filter =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test(email)) {
				    $("#email").css("border-color","#c95b5b");
					$("#emailError2").fadeIn();
			  	  	document.createUserForm.email.focus();
				    return false;
			 }
		var role = $("#userRole").val();
		if(role == 0){
			$("#userRole").css("border-color","#c95b5b");
			$("#userRoleError").fadeIn();
	  	  	document.createUserForm.userRole.focus();
		    return false;
		}
		return(true);
}
function keyValidate()
{	
			if((document.createUserForm.email.value.trim()).length>0){
				
			      $("#email").css("border-color","");; 
				  $("#emailError1").fadeOut();
				  $("#emailError2").fadeOut();
				  $("#emailError3").fadeOut();
			}
			if((document.createUserForm.userRole.value.trim()).length>0){	
			      $("#userRole").css("border-color","");; 
				  $("#userRoleError").fadeOut();
			}
}
function submitForm(){
	if(validate()){
		var email = document.createUserForm.email.value.trim();
		$.ajax({
			type:'POST',
			url : "checkEmail",
			data :"userEmail="+encodeURIComponent(email),
			error : function() {
			},
			success : function(data){
				if(data){
					$("#email").css("border-color","#c95b5b");
					$("#emailError3").fadeIn();
					document.createUserForm.email.focus();	
					}
				else
					{
					$("#createUserForm").submit();
					}
			}
		}); 
	}
}
</script>

</html>