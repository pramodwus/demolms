<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css?v=1'/>">
    <!-- QBis CSS -->
<link rel="stylesheet" href="<spring:url value='/resources/css/qbis.css?v=2'/>">
  </head>
  <body class="signup--body qbis--body login-on-domain--body">
  <div class="hold-transition login-page user-login-page-with-subdomain">
    <div class="login-box pull-right login-box-media-query">
      <div class="login-box-body">
      <div class="login-logo">
       <img src="<spring:url value='/resources/images/logo.png'/>" />
      </div><!-- /.login-logo -->
      <div id="overlay" class="user-login-page-overlay">
						<img id="loading" class="lazy"
							src="<spring:url value='/resources/images/loading.gif'/>">
	 </div>
      <form action="auth" role="form" method="post"  id="loginForm" name="myForm" onsubmit="return validate();" class="signup-form">
       <div class="form-group has-feedback">
        <p class="requireFld user-login-error" id="regSuccess"><spring:message code="msg.success.verification.mail"/></p>
       </div>
     
           <div class="notifications hide" id="verifyerror" tabindex="-1">
			    <p class="notification notification-critical" role="alert"><spring:message code="msg.account.not.verified"/> <a href="javascript:void(0)"
				onclick="resendverificationlink()"><spring:message code="msg.resend.verification.mail"/>
			    </a>
			    </p>
	        </div>
			
			<div class="notifications requireFld" id="unsuccesserror" tabindex="-1">
			    <p class="notification notification-critical" role="alert">
			    <spring:message code="msg.login.failed"/>
			    </p>
	        </div>
						        			           						     
          <c:if test="${expired!=null}">
          <div class="notifications" tabindex="-1">
			    <p class="notification notification-critical" role="alert">
			    <spring:message code="msg.license.expired"/>
			    </p>
	        </div>
          </c:if>
          
          <div class="form-group has-feedback form-row">
            <input id="email" type="text" class="form-control input-lg" name="email" placeholder="<spring:message code="placeholder.email"/>"  onkeyup="catValidate();">
            <div class="input-postpend signup-email-icon"><i class="glyphicon glyphicon-envelope"></i></div>
            <p class="requireFld error-msg validation--error" id="emailerror"><spring:message code="msg.empty"/></p> 
            <p class="requireFld error-msg validation--error" id="emailerror1"><spring:message code="msg.email.invalid"/></p>
          </div>
          <div class="form-group has-feedback form-row">
            <input id="exampleInputPassword2" type="password" class="form-control input-lg" name="password" placeholder="<spring:message code="placeholder.password"/>" onkeyup="catValidate();">
            <div class="input-postpend signup-show-password">Show Password</div>
             <p class="requireFld error-msg validation--error" id="pwderror"><spring:message code="msg.empty"/></p>
			<p class="requireFld error-msg validation--error" id="pwderror1"><spring:message code="msg.password.min.length"/></p>
            </div>
           <a href="forgetPass"><spring:message code="lbl.forget.password"/></a><br>
          <div class="row">
            <div class="col-xs-12">
              <button type="submit" class="btn btn-block pull-right btn-flat btn-success btn-lg submit-login-button"><spring:message code="lbl.submit"/></button>
            </div><!-- /.col -->
          </div>
          <input type="hidden" id="orgId" name="orgId" value="<%=session.getAttribute("orgId")%>">
        </form>
        <input type="hidden" id="unsuccess" value="${loginStatus}">
        <input type="hidden" id="mailid" value="${mailid}">
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    </div>
    
<%@ include file="dialogs.jsp"%>
  </body>
   <script>
   var regSuccess = '${regSuccess}';
   <%request.getSession().removeAttribute("regSuccess");%> 
    $(document).ready(function(){
    	
    	var status=$("#unsuccess").val();
    	if(status.match("fail"))
    		{ 
    		 $("#email").closest(".form-row").addClass("form-error");
			 $("#unsuccesserror").fadeIn();
    		}
    	//alert(status);
    	if(status=="unverified")
		{     		
		  $("#verifyerror").removeClass("hide");		
		}   	
     if(regSuccess=='regSuccess'){
    		   $("#regSuccess").fadeIn();
    	 }
    	
    });
  
    function validate(){
		  var pwd1 = document.getElementById('exampleInputPassword2').value;
		  var emailFilter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  var email=$("#email").val();
		  if( document.myForm.email.value.trim() == "" ){
		  		
		  		$("#email").closest(".form-row").addClass("form-error");
				$("#emailerror").fadeIn();
		  	  	document.myForm.email.focus();
		  	    return false;
		  	}
		  else if(!emailFilter.test(email))
		  {
			  $("#email").closest(".form-row").addClass("form-error");
				$("#emailerror1").fadeIn();
		  	  	document.myForm.email.focus();
		  	    return false;
		  }
			if( document.myForm.exampleInputPassword2.value.trim() == "" ){
					$("#exampleInputPassword2").closest(".form-row").addClass("form-error");
					$("#pwderror").fadeIn();
			  	  	document.myForm.exampleInputPassword2.focus();
			  	    return false;
			 }else{
				 
				 if(pwd1.length <6){
					$("#exampleInputPassword2").closest(".form-row").addClass("form-error");
					$("#pwderror1").fadeIn();
			  	  	document.myForm.exampleInputPassword2.focus();
				    return false;
				 }
			 }
			
			   
		return(true);
	  }
   
    function catValidate()
	  {	
			
				if((document.myForm.email.value.trim()).length>0){
					
				      $("#email").closest(".form-row").removeClass("form-error");
					  $("#emailerror").fadeOut();
					  $("#emailerror1").fadeOut();
					  $("#unsuccesserror").fadeOut();
					  
				}
				if((document.myForm.exampleInputPassword2.value.trim()).length>0){
					
				      $("#exampleInputPassword2").closest(".form-row").removeClass("form-error");
					  $("#pwderror").fadeOut();
					  $("#pwderror1").fadeOut();
					  $("#unsuccesserror").fadeOut();
				}
			}
    
    var resendverificationlink = function(){
    	 $("#overlay").show();
		var email=$.trim($("#mailid").val());
		if(email==""){
			$("#email").css("border-color","#c95b5b");
			$("#emailerror").fadeIn();
	  	  	document.myForm.email.focus();				
		}else{
			$.ajax({
	    		  url : "resendVerifyEmail?email="+email,
					type : 'POST',
					//async : false,		
					error : (function() {
						alert("server error");
					}),
					success : function(data) {
						if(data){
							$("#verifyerror").addClass('hide');
							$("#successdialog p").text('<spring:message code="msg.success.verification.mail"/>');
							$("#successdialog").modal('show') ;
							$("#overlay").hide();
						}
					}
	  	  });					
		}
    	  
      }
    
    /**
     * @summary This is used for showing and hide the password text.
     */
    $(document).on('click','.signup-show-password',function(){
    	if($(this).hasClass('is-showing')){
    		$(this).removeClass('is-showing');
    		$("#exampleInputPassword2").attr('type','password');
    	}
    	else
    		{
    		$(this).addClass('is-showing');
    		$("#exampleInputPassword2").attr('type','text');
    		}
    });
    </script>
</html>