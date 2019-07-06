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
</head>
<body class="signup--body signup-v3--body">
	<div class="page">
 <nav class="navbar navbar-inverse navbar-nav navbar-fixed-top navTheme" >
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
                                <li><a href="loginathome"><spring:message code="lbl.login"/></a></li>
                                <li><a href="requestatdemo" class="nav__link"><span class="btn btn-primary"><spring:message code="lbl.req.demo"/></span></a></li>
                        </ul>
                </div>
                </div>
        </nav>

        <div class="request-demo-form-div">
        <header class="signup-header">        
        </header>        
                <form id="demoForm">
                   <section class="signup-main">
                   <div class="col-sm-3"></div>
                     <div class="wrapper signup-form col-sm-6">
                        <h2 class="text-align-center"><spring:message code="header.home.request.demo.form"/></h2>  
    		             <p class="text-align-center"><spring:message code="lbl.home.instruction.request.form"/></p>
    		         
                   				<div class="form-group has-feedback"></div>
                   				<div class="form-group has-feedback form-row">
						            <input id="demoname" type="text" class="form-control input-lg" onkeyup="demoCalValidate();" name="demoname" placeholder="<spring:message code="placeholder.name"/>">
						            <span class="glyphicon glyphicon-user form-control-feedback"></span>
						            <p class="requireFld error-msg validation--error" id="demonameerror"><spring:message code="msg.empty"/></p> 
					          	</div>
					          	<div class="form-group has-feedback form-row">
						            <input id="demoorganization" type="text" class="form-control input-lg" name="demoorganization" placeholder="<spring:message code="placeholder.organization"/>">
						            <span class="glyphicon glyphicon-globe form-control-feedback"></span>
					          	</div>
					          	<div class="form-group has-feedback form-row">
						            <input id="demoemail" type="text" class="form-control input-lg" onkeyup="demoCalValidate();" name="demoemail" placeholder="<spring:message code="placeholder.email"/>">
						            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
						            <p class="requireFld error-msg validation--error" id="demoemailerror"><spring:message code="msg.empty"/></p> 
						            <p class="requireFld error-msg validation--error" id="demoemailerror1"><spring:message code="msg.email.invalid"/></p>
					          	</div>
					          	<div class="form-group has-feedback form-row">
						            <textarea id="democomment" class="form-control input-lg area textAreaControl" name="democomment" placeholder="<spring:message code="placeholder.comment"/>" maxlength="1024"></textarea>
						            <span class="glyphicon glyphicon-comment form-control-feedback"></span>
					          	</div>
					          	<div class="row">
					            		<div class="col-xs-12">					            		    
					              			<button type="button" id="demoForm" onclick="requestADemo()" class="btn btn-flat btn-success btn-lg pull-right submit-request-demo-button-home"><spring:message code="lbl.submit"/></button>
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
						</div>
					</div>
				</div>
			</div>
		</div>       
<%@ include file="footer.jsp"%>
	</div>
</body>
<!-- jQuery 2.1.4 js library -->
<script src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<!-- Bootstrap 3.3.5 js library -->
<script src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap.min.js'/>"></script>
<script	src="<spring:url value='/resources/adminlte/dist/js/main.js'/>"></script>
<!-- Fresh chat -->   
<script	src="<spring:url value='/resources/adminlte/dist/js/freshchat.js'/>"></script>   
<script type="text/javascript">
        var messages = new Array();
        messages['msg.success.password.link'] = "<spring:message code='msg.success.password.link' javaScriptEscape='true' />";
        messages['msg.success.verification.mail'] = "<spring:message code='msg.success.verification.mail' javaScriptEscape='true' />";
        messages['msg.success.requestdemo'] = "<spring:message code='msg.success.requestdemo' javaScriptEscape='true' />";
</script>
</html>
