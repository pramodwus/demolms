<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.qbis.model.User" %>
<%@page import="com.qbis.common.ConstantUtil" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div class="col-sm-12">
			<div class="content">
				<section class="content-header">
					<div class="pull-left">
						
					</div>
					<br />
				</section>
						<section class="content">
			<div class="row">			 
				<div class="col-md-12">
				<div class="box no-border">
				<div class="box-header with-border">
                  <h3 class="box-title" style="text-transform: capitalize">Settings</h3>
                </div><!-- /.box-header -->
				
				<% 
		
		if(request.getAttribute("ack").equals("SUCCESS") || request.getAttribute("ack").equals("SUCCESSWITHWARNING")) { 
 HashMap result = (HashMap) request.getAttribute("result");
   
%>
                  <div class="box-body">   
                    <div class="form-group col-sm-12">
                      <label for="expire" class="col-sm-2 control-label">Transaction ID:</label>
                      <div class="col-sm-10">
                        <%=result.get("PAYMENTINFO_0_TRANSACTIONID")%>
                      </div>
                      
                    </div> 
                  </div><!-- /.box-body -->
                  <div class="box-body">   
                    <div class="form-group col-sm-12">
                      <label for="expire" class="col-sm-2 control-label">Transaction Type:</label>
                      <div class="col-sm-10">
                        <%=result.get("PAYMENTINFO_0_TRANSACTIONTYPE")%>
                      </div>
                      
                    </div> 
                  </div><!-- /.box-body -->
                  <div class="box-body">   
                    <div class="form-group col-sm-12">
                      <label for="expire" class="col-sm-2 control-label">Payment Total Amount:</label>
                      <div class="col-sm-10">
                        <%=result.get("PAYMENTINFO_0_AMT")%>
                      </div>
                      
                    </div> 
                  </div><!-- /.box-body -->
                   <div class="box-body">   
                    <div class="form-group col-sm-12">
                      <label for="expire" class="col-sm-2 control-label">Payment Status:</label>
                      <div class="col-sm-5">
                        <%=result.get("PAYMENTINFO_0_PAYMENTSTATUS")%>
                      </div>
                      <div class="col-sm-5">
                         <input type="submit" onclick="goToLogin()" class="btn btn-primary btn-large" value="Place Order">
                      </div>
                    </div> 
                  </div><!-- /.box-body -->
<% } else { %>

<div class="hero-unit">
    			<!-- Display the Transaction Details-->
    			<h4> There is a Funding Failure in your account. You can modify your funding sources to fix it and make purchase later. </h4>
    			Payment Status:
    			PAYMENTINFO_0_PAYMENTSTATUS
    			
    			<h3> Click <a href='https://www.sandbox.paypal.com/'>here </a> to go to PayPal site.</h3> <!--Change to live PayPal site for production-->
    			</div>

<% } %>	     
                </div>
				</div>
				<!-- /.col -->
			</div>
			</section>
			</div>
			<div class="control-sidebar-bg"></div>
				
		</div>
		<!-- ./wrapper -->
	</div>
</body>
<%User user=(User) request.getSession().getAttribute("userSession"); %>
<input type="hidden" id="orgName" value="<%=user.getSubdomain()%>">
<input type="hidden" id="domain" value="<%=ConstantUtil.DOMAIN_NAME%>"> 
<!-- Select2 -->
<script src="resources/adminlte/plugins/select2/select2.full.min.js"></script>
<script>
var domainName = "${userReg.domainName}";


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
/*
 *@ GO to Login Page 
 */
function goToLogin()
{
	var loc = getAbsolutePath();
	loc = loc.replace(window.location.hostname, $.trim($(
			"#orgName").val())
			+ "." + $("#domain").val());
	console.log(loc);
	location.replace(loc + "regsuccess");
}
document.onkeydown = function() {    
    switch (event.keyCode) { 
        case 116 : //F5 button
            event.returnValue = false;
            event.keyCode = 0;
            return false; 
        case 82 : //R button
            if (event.ctrlKey) { 
                event.returnValue = false; 
                event.keyCode = 0;  
                return false; 
            } 
    }
}
</script>
</html>