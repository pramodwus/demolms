<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
<%@ include file="include.jsp"%>
    <!-- QBis CSS -->
    <link rel="stylesheet" href="<spring:url value='/resources/css/qbis.css'/>">
	    <style>
    body {
    background-image:    url("<spring:url value='/resources/images/body.jpg'/>");
    background-size:     cover;                   
    background-repeat:   no-repeat;
    background-position: center;          /* optional, center the image */
         }
    </style>
  </head>
  <body>
    <div class="hold-transition login-page" style="padding-right:3em;">
    	<div class="login-box pull-right">
    	<div class="login-box-body">
		<div class="login-logo">
			<img src="<spring:url value='/resources/images/logo.png'/>" />
		</div>
		    <div class="text-center h3"><spring:message code="msg.logout"/></div>
		      <div class="text-center">
        <a id="login" href="#"><spring:message code="lbl.logout.for.login"/></a>
        <div><br/> </div>
      </div>
        </div>
	</div>
	</div>
  </body>

<script>
$("#login").click(function() {	
	window.location = 'login';		 
  });
</script>
</html>