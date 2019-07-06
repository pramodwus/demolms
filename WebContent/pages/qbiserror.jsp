<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <body >
  <div class="hold-transition login-page" style="padding-right:3em;">
    <div class="login-box pull-right">
      <div class="login-box-body">
      <div class="login-logo">
        <p style="color:#231252;"><b>QB<span style="color:#DD2163">i</span>s</b></p>
        <p>Organization Is InValid</p>
      </div><!-- /.login-logo -->
        
      
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    </div>
  </body>
   <script>
    $(document).ready(function(){
    	
    	var status=$("#unsuccess").val();
    	if(status.match("fail"))
    		{ 
    		$("#email").css("border-color","#c95b5b");
			$("#unsuccesserror").fadeIn();
    		}
    });
  
   
   
    </script>
</html>