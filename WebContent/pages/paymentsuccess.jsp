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
	href="<spring:url value='/resources/adminlte/dist/css/extra.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css?v=1'/>">	
<link rel="stylesheet" type="text/css" href="<spring:url value='/resources/css/paymentsuccess.css?v=1'/>">
</head>
<body class="signup--body">
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
                                <li><a href="javascript:void(0)" onclick="goHomePage()"><spring:message code="lbl.home"/></a></li>
                                <li><a href="javascript:void(0)" onclick="gotHomeFeature()"><spring:message code="lbl.features"/></a></li>   		
                                <li><a  href="javascript:void(0)" class="nav__link"><span class="btn btn-primary"><spring:message code="lbl.singup"/></span></a></li>
                                <li><a  href="login"><spring:message code="lbl.login"/></a></li>
                                <li><a href="javascript:void(0)" onclick="requestDemo()"><spring:message code="lbl.req.demo"/></a></li>
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
		<h1 class="pf-green">Payment Successful</h1>
		<p class="pf-processed-msg">Your payment has been processed! Details of the transaction are included below:</p>
		<div class="pf-total-col">
			<span class="pf-total-paid">Amount Paid</span>
			<div class="pf-total-wrapper">
				<span class="pf-total-label">Total:</span>
				<span class="pf-total-amount pf-green" id="pf-total-amount">$<c:out value="${amount}"></c:out></span>
			</div>
		</div>
		<div class="pf-success-details">
			<p>Thank you for your purchase. We have sent an account verification link to your registered email address. Please verify and login.</p>
			<div class="pf-success-info">
				<ul>
					<li>
						<span class="pf-value-name">Email Address:</span>
						<span id="pf-full-name" class="pf-value"><c:out value="${useremail}"></c:out></span>
					</li>
					<li>
						<span class="pf-value-name">License Name:</span>
						<span id="pf-auth-code" class="pf-value"><c:out value="${licenseName}"></c:out></span>
					</li>
					<li>
						<span class="pf-value-name">Transaction Id:</span>
						<span id="pf-auth-code" class="pf-value"><c:out value="${transactionId}"></c:out></span>
					</li>
				</ul>
			</div>
		</div>
		<div class="pf-payment-buttons">
		    <button onclick="printPaymentReceipt()" class="payment_print">Print</button>
            <!-- | PUT YOUR LINK HERE | -->
			<button class="redirect_login" onclick="location.href='login'">Go to Login</button>
            <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
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
var subdomainname = '${subdomainname}';

/**
 * @summary This is used for print the receipt.
 */
function printPaymentReceipt(){
    var printContents = document.getElementById("payment_success").innerHTML;
    var originalContents = document.body.innerHTML;      
    if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
        var popupWin = window.open('', '_blank', 'width=600,height=600,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
        popupWin.window.focus();
        popupWin.document.write('<!DOCTYPE html><html><head><meta charset="utf-8">'
      		  +'<meta name="description" content="QBis is Smart Learning Platform (SLP) that can be used by enterprises and educational organizations to train internal as well as external audience. It allows you to create multimedia content with powerful assessment that supports several different types of questions such as essay type, objective, match the following etc."/>'
      		  +'<meta name="viewport" content="width=device-width, initial-scale=1">'
      		  +'<link rel="shortcut icon" type="image/png" href="resources/images/qbisfavicon.png">'
      		  + '<link rel="stylesheet" href="resources/css/signup.css">'
      		  +'<link rel="stylesheet" type="text/css" href="resources/css/paymentsuccess.css"></head><body onload="window.print()">'+printContents+'</body></html>');
        popupWin.onabort = function (event) {
            popupWin.document.close();
            popupWin.close();
        }
        setTimeout(function(){popupWin.close();},1000);
    } else {
        var popupWin = window.open('', '_blank', 'width=800,height=600');
        popupWin.document.open();
        popupWin.document.write('<html><head><link rel="stylesheet" href="resources/css/signup.css"><link rel="stylesheet" type="text/css" href="resources/css/paymentsuccess.css" /></head><body onload="window.print()">' + printContents + '</html>');
        popupWin.document.close();
        setTimeout(function(){popupWin.close();},1000);
    }
    popupWin.document.close();
    
    return true;
}

/**
 * @summary This is used for redirect on request demo page.
 */
function requestDemo(){
	var loc = getAbsolutePath();
	if(loc.indexOf(subdomainname)>0){
		var newloc = loc.replace(subdomainname+'.','');
		location.href = newloc + 'requestatdemo' ;
	}
	else
		{
		location.href = loc + 'requestatdemo';
		}
}

/**
 * @summary This is used for redirect on home page.
 */
function goHomePage(){
	var loc = getAbsolutePath();
	if(loc.indexOf(subdomainname)>0){
		var newloc = loc.replace(subdomainname+'.','');
		location.href = newloc;
	}
	else
		{
		location.href = loc;
		}
}

/**
 * @summary This is used for redirect on features page.
 */
function gotHomeFeature(){
	var loc = getAbsolutePath();
	if(loc.indexOf(subdomainname)>0){
		var newloc = loc.replace(subdomainname+'.','');
		location.href = newloc + 'homefeatures';
	}
	else
		{
		location.href = loc + 'homefeatures';
		}
}

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
