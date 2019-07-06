<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<meta name="description"
	content="HTML5 Admin is an HTML5 Administration template easy to modify and maintain based on Bootstrap 3">
<meta name="author" content="HTML5 Admin">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/png"
	href="<spring:url value='/resources/images/qbisfavicon.png' />">
<title>QBis</title>
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/bootstrap/css/bootstrap.min.css'/>">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<spring:url value='/resources/css/font-awesome.css'/>">
<!-- Theme style -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/AdminLTE.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/qbis.css'/>">
<style>
body {
	background-image: url('<spring:url value='/ resources/ images/ body.jpg '/>');
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center; /* optional, center the image */
}

.register-box {
	Border: 1px solid #5E5A4B;
	/* max-width:360px */
}

@font-face {
	font-family: 'Century Gothic';
	src: url('resources/fonts/CenturyGothic.ttf');
}
h1, h2, h3, h4, h5,h6,div,select,input, p, button ,label, textarea,ul ,span, a{
	font-family: 'Century Gothic' !important;
}
</style>
</head>
<body>
	<div class="hold-transition login-page" style="padding-right: 3em;">
		<div class="login-box pull-right">
			<div class="login-box-body">
				<div class="login-logo">
					<img src="<spring:url value='/resources/images/logo.png'/>" />
				</div>
				<c:if test="${password!=null}">
				<c:if test="${setUpPass==null}"><p class="login-box-msg"><spring:message code="header.success.password.change"/>!</p></c:if>
				<c:if test="${setUpPass!=null}"><p class="login-box-msg"><spring:message code="header.password.setup"/>!</p></c:if>
				<div style="margin: 10px auto; text-align: center; width: 90%;">
					<c:if test="${setUpPass!=null}"><p class="login-box-msg"><spring:message code="msg.success.pasword.setup"/></p></c:if>
					<c:if test="${setUpPass==null}"><p class="login-box-msg"><spring:message code="msg.success.pasword.change"/></p></c:if>
					<p>
						<span><spring:message code="msg.please"/> </span><a href="login"><span>
						<spring:message code="msg.login.again"/>
						</span></a>
					</p>
				</div>
				</c:if>
				<!-- /.login-logo -->
				<c:if test="${password==null}">
				<p class="login-box-msg"><spring:message code="header.email.verified"/></p>
				<div style="margin: 10px auto; text-align: center; width: 90%;">
					<p><spring:message code="msg.email.verified"/> </p>
					<p>
						<span><spring:message code="msg.please"/> </span><a href="login"><span><spring:message code="lbl.login"/>!</span></a>
					</p>
				</div>
				</c:if>
			</div>
			<!-- /.login-box-body -->
		</div>
		<!-- /.login-box -->
	</div>
</body>
</html>