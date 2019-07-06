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
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/font-awesome-4.4.0/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/ionicons-2.0.1/css/ionicons.min.css'/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="resources/adminlte/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="resources/adminlte/plugins/iCheck/square/blue.css">
    <link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/extra.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css'/>">	
<link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/paymentsuccess.css'/>">
<style>
.licence input[type=radio]:checked+label .checkbox {
    border: 1px solid red;
    background-color: red;
    
}

.licence .checkbox .check {
    background-image: url(resources/images/error-crossmark.png);
    -ms-transform: rotate(50deg); /* IE 9 */
    -webkit-transform: rotate(50deg); /* Chrome, Safari, Opera */
    transform: rotate(50deg);
}

.pf-checkout-container .pf-green {
    color: red;
}

.pf-payment-buttons .redirect_login {
    background-color: red;
    }
    .pf-payment-buttons .redirect_login:hover{
	background-color:red;
}
</style>
</head>
<body class="signup--body">
<div id="overlay" class="signup-form-overlay">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>">
		</div>
 <nav class="navbar navbar-inverse navbar-fixed-top navTheme" id="nav">
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
                                <li><a href="javascript:void(0)"><spring:message code="lbl.home"/></a></li>
                                <li><a href="javascript:void(0)"><spring:message code="lbl.features"/></a></li>   		
                                <li><a  href="javascript:void(0)"><span class="btn btn-primary"><spring:message code="lbl.singup"/></span></a></li>
                                <li><a  href="javascript:void(0)"><spring:message code="lbl.login"/></a></li>
                                <li><a href="javascript:void(0)"><spring:message code="lbl.req.demo"/></a></li>
                        </ul>
                </div>
                
                </div>
        </nav>
      <div class="login-logo payment_success_logo">
      <div>
         <div class="licence">
        <input type="radio" id="free" class="js-input_licence" checked/>

        <label for="free">
          <div class="checkbox"><div class="check"></div></div>
          </label>
        </div>
        </div>
   
      </div><!-- /.login-logo -->
     
       <div class="payment_success"  id="payment_success">
       <div class="pf-checkout-container pf-success-page">
		<h1 class="pf-green">Payment <c:out value="${sale.state}"></c:out></h1>
		<c:if test="${cancelmsg!=null}"><p class="pf-processed-msg">Your payment has been cancelled by you.</p></c:if>
		<c:if test="${cancelmsg==null}"><p class="pf-processed-msg">Your payment has been failed!</p></c:if>
		<div class="pf-payment-buttons">
		    
            <!-- | PUT YOUR LINK HERE | -->
			<button class="redirect_login" onclick="processPaypalPaymentAgain()">Try Again</button>
		</div>
	</div>
	 <%@ include file="footer.jsp"%>
         </div>
</body>
<!-- jQuery 2.1.4 js library -->
<script src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<!-- Bootstrap 3.3.5 js library -->
<script src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap.min.js'/>"></script>
<script>
function processPaypalPaymentAgain(){
	try{
		$("#overlay").show();
	    location.href = 'processPaymentRequest';
	}catch(err)
	{
		
	}
}
</script>
</html>
