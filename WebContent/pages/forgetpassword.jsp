<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp" %>
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/qbis.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/custom.css?v=1'/>">
<style>
</style>
</head>
<body class="qbis--body signup--body">
	<div class="hold-transition login-page user-login-page-with-subdomain">
	     <div id="overlay" class="user-login-page-overlay">
						<img id="loading" class="lazy"
							src="<spring:url value='/resources/images/loading.gif'/>">
	     </div>
		<div class="login-box pull-right">
			<div class="login-box-body">
				<div class="login-logo">
					<img src="<spring:url value='/resources/images/logo.png'/>" />
				</div>
				<!-- /.login-logo -->
				<p class="login-box-msg"><spring:message code="lbl.forget.password"/></p>
				<form action="sendPassLink" method="post" id="resetPass" name="myForm" class="signup-form">
					<p class="requireFld otp-fail-error" id="otpFail"><spring:message code="msg.try.again"/></p>
				
				<c:if test="${errormsg!=null}">
				<p id="errormsg" class="link-expire-error"><spring:message code="msg.password.reset.link.expired"/></p>
				</c:if>
					<div class="form-group has-feedback form-row">
						<input id="email" type="text" class="form-control input-lg"
							name="email" placeholder="<spring:message code="placeholder.email"/>"
							onkeyup="catValidate();">
							<div class="input-postpend signup-email-icon"><i class="glyphicon glyphicon-envelope"></i></div>
							<p class="requireFld error-msg validation--error" id="emailerror"><spring:message code="msg.empty"/></p>
							<p class="requireFld error-msg validation--error" id="emailerror1"><spring:message code="msg.email.invalid"/></p> 
							<p class="requireFld error-msg validation--error" id="emailerror2"><spring:message code="msg.email.not.exist"/></p>
					</div>
					<div class="row">
						<div class="col-xs-12 no-left-padding">
							<div class="pull-left">
								<div class="checkbox">
									<label class="text-align:left"><span
										onclick="location.href='login'" style="cursor: pointer;"><a><spring:message code="lbl.back"/></a></span>
									</label>
								</div>
								<!-- /.checkbox -->
							</div>
							<!-- /.pull-left -->
							<div class="pull-right">
								<button type="button" class="btn btn-flat btn-success button-width-large btn-lg" onclick="verifyEmail()">
									<spring:message code="lbl.submit"/>
								</button>
							</div>
							<!-- /.pull-right -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</form>
				<input type="hidden" id="unsuccess"
					value=<%=request.getAttribute("status") %>>
			</div>
			<input type="hidden" id="success" value="${success}">
			<!-- /.login-box-body -->
		</div>
		<!-- /.login-box -->
	</div>
	
<div class="modal fade" id="passwordsendlinkalert" tabindex="-1" role="dialog" aria-labelledby="passwordsendlinkalert">
            <div class=" col-md-12 col-sm-12 col-xs-12">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">			
			<div class="modal-body" style="text-align: center">
			<h3></h3>
				<p></p>
				<button type="button" class="btn btn-success button-width-large"
					data-dismiss="modal" onclick="closeAlert();"><spring:message code="lbl.ok"/></button>
			</div>
		</div>
	</div>
</div>
</div>
</body>
<script>

 $(document).ready(function(){
	 	var success='${success}';
    	if(success.match("successful"))
    		{ 
    		 $("#passwordsendlinkalert").modal('show');
    		 $("#passwordsendlinkalert .modal-content button").remove();
    		 $("#passwordsendlinkalert p").text('<spring:message code="msg.success.password.link"/>');
    		 setTimeout(function() { window.location="login"; }, 4000);
    		}
 });
 
function validate(){
	  if( document.myForm.email.value.trim() == "" ){
		  		
		  		$("#email").closest(".form-row").addClass("form-error");
				$("#emailerror").fadeIn();
		  	  	document.myForm.email.focus();
		  	    return false;
		  	}else{
		  		var email = document.getElementById('email').value;
		  		var MN = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
					if (!MN.test(email)) {
					    $("#email").closest(".form-row").addClass("form-error");
						$("#emailerror1").fadeIn();
				  	  	document.myForm.email.focus();
					    return false;
				    }
		  	} 
	  return true;
}
function catValidate()
	  {	
			
				if((document.myForm.email.value.trim()).length>0){
					
				      $("#email").closest(".form-row").removeClass("form-error");
					  $("#emailerror").fadeOut();
					  $("#emailerror1").fadeOut();
					  $("#emailerror2").fadeOut();
					  $("#otpFail").fadeOut();
					  $("#errormsg").fadeOut();
				}
	  }
	  
function closeAlert(){
	  $("#passwordsendlinkalert").modal('hide');
	  window.location="login";
}
	  function verifyEmail(){
		  $("#overlay").show();
		  if(validate()){
			   var email = document.myForm.email.value.trim();
				$.ajax({
					type:'POST',
					url : "checkEmailWithURL",
					data :"userEmail="+encodeURIComponent(email),
					error : function() {
						$("#overlay").hide();
					},
					success : function(data){ 
						$("#overlay").hide();
						if(!data){
							$("#email").closest(".form-row").addClass("form-error");
							$("#emailerror2").fadeIn();
							document.myForm.email.focus();	
							}
					else{
					  $("#resetPass").submit();
						}
					}
				}); 
			}
	  }
    </script>
</html>