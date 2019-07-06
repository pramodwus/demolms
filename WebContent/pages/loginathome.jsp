<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>QBis: Smart Learning Platform</title>
<meta charset="utf-8">
<meta name="description" content="QBis is Smart Learning Platform (SLP) that can be used by enterprises and educational organizations to train internal as well as external audience. It allows you to create multimedia content with powerful assessment that supports several different types of questions such as essay type, objective, match the following etc."/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="<spring:url value='/resources/images/qbisfavicon.png' />">
<link rel="canonical" href="http://www.bellurbis.com/qbis.html">
<link rel="stylesheet"	href="resources/adminlte/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/extra.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css?v=1'/>">
<style>


</style>
</head>
<body class="signup--body signup-v3--body login-at-home--body">
	<div class="page">
	<div id="overlay" class="signup-form-overlay">
			<img id="loading" class="lazy" src="resources/images/loading.gif">
	</div>
 <nav class="navbar navbar-inverse navbar-fixed-top navTheme" >
                <div class="container sp__width">
                <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                        </button>
                    <a class="navbar-brand" href="#myCarousel">
                            <img src="resources/adminlte/dist/img/imageshome/logo.png" height="50">
                        </a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                                <li><a href="/"><spring:message code="lbl.home"/></a></li>
                                <li><a href="homefeatures"><spring:message code="lbl.features"/></a></li>                                    		
                                <li><a href="reguser"><spring:message code="lbl.singup"/></a></li>
                                <li><a href="loginathome" class="nav__link"><span class="btn btn-primary"><spring:message code="lbl.login"/></span></a></li>
                                <li><a href="requestatdemo"><spring:message code="lbl.req.demo"/></a></li>
                        </ul>
                </div>
                </div>
        </nav>

        <div class="login-form-main-div">
        <header class="signup-header">        
        </header>
                   <form id="loginForm" class="js-signup-form login-at-home">
                   <section class="signup-main">
                   <div class="col-sm-3"></div>
                     <div class="wrapper signup-form col-sm-6">
                     <h1 class="text-align-center"><spring:message code="lbl.login"/></h1>
                   				<div class="notifications requireFld" id="loginerror" tabindex="-1">
							    <p class="notification notification-critical" role="alert">
							    <spring:message code="lbl.logininfoincorrect" text="Some of your info isn't correct. Please try again."/>
							    </p>
						        </div>
						         
						        <div class="notifications requireFld" id="licenceerror" tabindex="-1">
							    <p class="notification notification-critical" role="alert">
							   <spring:message code="msg.license.expired"/>
							    </p>
						        </div>
						        
                   				<div class="notifications hide" id="verifyerror" tabindex="-1">
							    <p class="notification notification-critical" role="alert"><spring:message code="msg.account.not.verified"/> <a href="javascript:void(0)"
								onclick="resendverificationlink()"><spring:message code="msg.resend.verification.mail"/>
							    </a>
							    </p>
							    <input type="hidden" id="mailid">
						        </div>
						         
                   				<div class="form-group has-feedback form-row">
						            <input id="loginsubdomain" type="text" class="form-control input-lg" name="loginsubdomain" placeholder="<spring:message code="placeholder.subdomain"/>" onkeyup="loginCalValidate();" style="padding-right: 100px !important;">
						             <div class="input-prepend"><span class="span-font-family">http://</span></div>
                                     <div class="input-postpend"><span class="span-font-family">.qbis.in</span></div>
						            <p class="requireFld error-msg validation--error" id="loginsubdomainerror"><spring:message code="msg.empty"/></p> 
					          	</div>
					   			<div class="form-group has-feedback form-row">
						            <input id="loginemail" type="text" class="form-control input-lg" name="loginemail" placeholder="<spring:message code="placeholder.email"/>" onkeyup="loginCalValidate();">
						           <!--  <div class="input-postpend signup-email-icon">Email Icon</div> -->
						            <div class="input-postpend signup-email-icon"><i class="glyphicon glyphicon-envelope "></i></div>
						            <p class="requireFld error-msg validation--error" id="loginemailerror"><spring:message code="msg.empty"/></p> 
						            <p class="requireFld error-msg validation--error" id="loginemailerror1"><spring:message code="msg.email.invalid"/></p>
					          	</div>
					          	<div class="form-group has-feedback form-row">
						            <input id="loginpassword" type="password" class="form-control input-lg" name="loginpassword" placeholder="<spring:message code="placeholder.password"/>" onkeyup="loginCalValidate();">
						            <div class="input-postpend signup-show-password">Show Password</div>
						            <p class="requireFld error-msg validation--error" id="loginpwderror"><spring:message code="msg.empty"/></p>
									<p class="requireFld error-msg validation--error" id="loginpwderror1"><spring:message code="msg.password.min.length"/></p>
					           	</div>
					           	<div class="row">
					            	<div class="col-xs-7 forget-password-link-home">
				              			<a href="javascript:void(0)" onclick="forgetPasswordV2(0)" class="link"><spring:message code="lbl.forget.password"/></a>
					          		</div>
					   			</div>
					          	<div class="row">
					            		<div class="col-xs-12">
					              			<button type="button" value="Submit" onclick="submitLoginForm()" class="btn btn-flat btn-lg btn-success pull-right submit-login-button-home"><spring:message code="lbl.submit"/></button>
					            		</div><!-- /.col -->
					   			</div>
					   			</div>
					   			</section>
					   		</form>
					   		
					   		<form id="forgetpwdform" method="post" action="" class="js-signup-form" style="display:none">
					   		<section class="signup-main">
					   		<div class="col-sm-3"></div>
                             <div class="wrapper signup-form col-sm-6">
                                <h1 class="text-align-center"><spring:message code="lbl.forget.password"/></h1>
                   				<div class="form-group has-feedback"></div>
                   				<div class="form-group has-feedback form-row">
						            <input id="forgetpasssubdomain" type="text" class="form-control input-lg" name="subdomain" placeholder="<spring:message code="placeholder.subdomain"/>" style="padding-right: 100px !important;" onkeyup="forgetPassKeyValidate();">
						            <div class="input-prepend"><span class="span-font-family">http://</span></div>
                                    <div class="input-postpend"><span class="span-font-family">.qbis.in</span></div>
						            <p class="requireFld error-msg validation--error" id="forgetpasssubdomainerror1"><spring:message code="msg.empty"/></p> 
						            <p class="requireFld error-msg validation--error" id="forgetpasssubdomainerror2"><spring:message code="msg.subdomain.notexist"/></p> 
					          	</div>
					   			<div class="form-group has-feedback form-row">
						            <input id="forgetpassemail" type="text" class="form-control input-lg" name="email" placeholder="<spring:message code="placeholder.email"/>"  onkeyup="forgetPassKeyValidate();">
						            <div class="input-postpend signup-email-icon"><i class="glyphicon glyphicon-envelope "></i></div>
						            <p class="requireFld error-msg validation--error" id="forgetpassemailerror1"><spring:message code="msg.empty"/></p> 
						            <p class="requireFld error-msg validation--error" id="forgetpassemailerror2"><spring:message code="msg.email.invalid"/></p>
						            <p class="requireFld error-msg validation--error" id="forgetpassemailerror3"><spring:message code="msg.email.notexist.with.org"/></p>
					          	</div>
					          	<div class="row">
					            		<div class="col-xs-12">
					              			<button type="button" onclick="resetPassword()" class="btn pull-right btn-flat btn-lg btn-success submit-reset-password-button-home"><spring:message code="lbl.submit"/></button>
					            		</div><!-- /.col -->
					   			  </div>
					   			 </div>
					   			</section>
					   		</form>
					   		

        </div>
        <div class="modal fade" id="homeAlert" tabindex="-1" role="dialog" aria-labelledby="homeAlert">
            <div class=" col-md-12 col-sm-12 col-xs-12">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">			
						<div class="modal-body" style="text-align: center">
						<h3></h3>
							<p></p>
							<button type="button" class="btn btn-success btn-flat" style="width:100px"
								data-dismiss="modal"><spring:message code="lbl.ok"/></button>
						</div>
					</div>
				</div>
			</div>
		</div>	
		
		 <div class="modal fade" id="passwordResetAlert" tabindex="-1" role="dialog" aria-labelledby="passwordResetAlert">
            <div class=" col-md-12 col-sm-12 col-xs-12">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">			
						<div class="modal-body" style="text-align: center">
						<h3></h3>
							<p></p>
						</div>
					</div>
				</div>
			</div>
		</div>	
		
       <%@ include file="footer.jsp"%>
	</div>
	<%@ include file="dialogs.jsp"%>
</body>
<!-- jQuery 2.1.4 js library -->
<script src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<!-- Bootstrap 3.3.5 js library -->
<script src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap.min.js'/>"></script>
<script	src="<spring:url value='/resources/adminlte/dist/js/main.js?v=1'/>"></script>
<!-- Fresh chat -->   
<script	src="<spring:url value='/resources/adminlte/dist/js/freshchat.js'/>"></script>   
<script type="text/javascript">
        var messages = new Array();
        messages['msg.success.password.link'] = "<spring:message code='msg.success.password.link' javaScriptEscape='true' />";
        messages['msg.success.verification.mail'] = "<spring:message code='msg.success.verification.mail' javaScriptEscape='true' />";
        messages['msg.success.requestdemo'] = "<spring:message code='msg.success.requestdemo' javaScriptEscape='true' />";
</script>
<script>
var domainName = "${userLogin.domainName}";
/**
 * @summary This is used for showing and hide the password text.
 */
$(document).on('click','.signup-show-password',function(){
	if($(this).hasClass('is-showing')){
		$(this).removeClass('is-showing');
		$("#loginpassword").attr('type','password');
	}
	else
		{
		$(this).addClass('is-showing');
		$("#loginpassword").attr('type','text');
		}
});

/**
 * @summary This is used for getting absolute path from url. 
 */
function getAbsolutePath() {
	var loc = window.location;
	var pathName = loc.pathname.substring(0,
			loc.pathname.lastIndexOf('/') + 1);
	return loc.href
			.substring(
					0,
					loc.href.length
							- ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}
</script>
</html>
