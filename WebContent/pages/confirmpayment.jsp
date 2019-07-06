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
<link rel="stylesheet" href="<spring:url value='/resources/css/confirmpayment.css?v=1'/>">	
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
                </div>
        </nav>
        <div class="signup-confirm-payment-main">
            <section class="licence-selection">
  <div class="wrapper">
    <div class="licences">
    <div class="col-sm-7">
      <div class="box box-solid">
                <div class="box-header">
                  <h4 class="box-title">Selected Licence</h4>
                </div><!-- /.box-header -->
                <div class="box-body" style="">
      <div class="licence licence--free">
        <input type="radio" name="licenseId" id="free" value="1" class="js-input_licence" checked/>

        <label for="free">
          <div class="checkbox"><div class="check"></div></div>
          <h5>${licensedetails.licenseName}</h5>
          <h6><strong>${licensedetails.licenseCost}</strong> <span>Price</span></h6>

          <ul>
          <li><strong>${licensedetails.validityDays} Days</strong> <span>validity</span></li>
          <li><strong>${licensedetails.usedSpace}</strong> <span>Cloud Disk Storage</span></li>
          <li><strong>${licensedetails.maxUsers}</strong> <span>users</span></li>
          </ul>
        </label>
      </div>
      </div>
      </div>
      
      
</div>
<div class="col-sm-5">
      
     <div class="box box-solid">
                <div class="box-header">
                  <h4 class="box-title">Price Details</h4>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <dl class="dl-horizontal price-details">
                    <dt><span>Transaction Id :</span></dt>
                    <dd><span><c:out value="${checkoutDetails.PAYERID}"/></span></dd>
                    <dt><span> Amount :</span></dt>
                    <dd>$<span><c:out value="${checkoutDetails.AMT}"/></span></dd>
                    <dt><span>Tax Amount :</span></dt>
                    <dd>$<span><c:out value="${checkoutDetails.TAXAMT}"/></span></dd>
                    <dt><span>Handling Amount :</dt>
                    <dd>$<span><c:out value="${checkoutDetails.HANDLINGAMT}"/></span></dd>
                    <dt><span>Discount Amount</span></dt>
                    <dd>$<span>0.00</span></dd>
                  </dl>
                  <dl class="dl-horizontal">
                  <c:set var="totalAmount" value="${checkoutDetails.AMT + checkoutDetails.TAXAMT + checkoutDetails.HANDLINGAMT}"/>
                    <dt><strong><span>Total Amount</span></strong></dt>
                    <dd><strong>$<span><c:out value="${totalAmount}"/></span></strong></dd>
                  </dl>
                  <form action="doExpressCheckoutDetails" method="POST">
                  <input type="hidden" name="PayerID" value="${PayerID}">
                  <input type="hidden" name="token" value="${token}">
                  <button type="submit" class="btn btn-flat btn-success btn-lg pay-now btn-block">Pay Now</button>
                  </form>
                </div><!-- /.box-body -->
             
      </div>
      </div>
      
    </div>
  </div>
</section>
         </div>
</body>
<!-- jQuery 2.1.4 js library -->
<script src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<!-- Bootstrap 3.3.5 js library -->
<script src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap.min.js'/>"></script>
</html>
