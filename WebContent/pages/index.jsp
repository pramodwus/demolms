<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<%-- <%@ include file="header1.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>
.inner{
  background: transparent !important;
}
.glowing-border {
    border: 2px solid #dadada;
    border-radius: 7px;
}

.glowing-border1{ 
    outline: none;
    border-color:#3AA716;
    box-shadow: 0 0 10px #9ecaed;
}
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
<div class="wrapper">
<%@ include file="header.jsp"%>
<%@ include file="leftmenu.jsp"%>

<div class="col-sm-12">
<script type="text/javascript">
$( document ).ready(function() {
	$('#yearly').removeClass('glowing-border1');
	$('#month').addClass('glowing-border1');
});

var monthlyCostPerUser=[];
var yearlyCostPerUser=[];
var monthlyTotalCost=[];
var yearlyTotalCost=[];
var licenseId=[];
function yearly()
{
	$('#month').removeClass('glowing-border1');
	$('#yearly').addClass('glowing-border1');
	for(var i=0;i<licenseId.length;i++)
		{
		 $('#PAYMENTREQUEST_0_AMT'+licenseId[i]+'').val(yearlyTotalCost[i]);
		 $('#totalAmount'+licenseId[i]+'').text(yearlyTotalCost[i]);
		 $('#cost'+licenseId[i]+'').text(yearlyCostPerUser[i]);
		}
}
function monthly()
{
	$('#yearly').removeClass('glowing-border1');
	$('#month').addClass('glowing-border1');
		for(var i=0;i<licenseId.length;i++)
		{
		 $('#PAYMENTREQUEST_0_AMT'+licenseId[i]+'').val(monthlyTotalCost[i]);
		 $('#totalAmount'+licenseId[i]+'').text(monthlyTotalCost[i]);
		 $('#cost'+licenseId[i]+'').text(monthlyCostPerUser[i]);
		}
}
</script>
  <div class="content-wrapper">
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
                  <h3 class="box-title" style="text-transform: capitalize">License ${fn:toLowerCase(action)}</h3>
                </div><!-- /.box-header -->
				<!--  <form class="form" action="ExpressCheckout" method="POST"> -->
				 <%	String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
				<input type="hidden" name="action" value="${action}">
                    <div class="box-body">
                     <div class="form-group  col-sm-12  text-center">
		                    <div class="btn-group ">
		                      <button type="button" id="month" onclick="monthly()" class="btn btn-info glowing-border" style="padding: 10px 25px ">Monthly</button>
		                      <button type="button" id="yearly" onclick="yearly()" class="btn btn-info glowing-border" style="padding: 10px 25px">Yearly</button>
		                    </div>
                     </div>
                      <div class="form-group  col-sm-12">
                    <c:forEach items="${licenseList}" var="licenseList">   
                    <form class="form" action="ExpressCheckout" method="POST">
                     <input type="hidden" name="LOGOIMG" value="<%=basePath %>img/logo.jpg"></input>
					  <input type="hidden" id="currencyCodeType" name="currencyCodeType" value="USD">
					   <input type="hidden" id="paymentType" name="paymentType" value="Sale">
					   <input type="hidden" id="action" name="action" value="settings">
					    <input type="hidden" id="PAYMENTREQUEST_0_AMT${licenseList.licenseId}" name="PAYMENTREQUEST_0_AMT" value="${licenseList.monthTotalAmount}">
					    <input type="hidden" name="L_PAYMENTREQUEST_0_NUMBER0" value="${licenseList.licenseId}"></input>
				        <div class="col-lg-4 col-xs-6 ">
				          <!-- small box -->
				          <script>
				          monthlyCostPerUser.push('${licenseList.cost}');yearlyCostPerUser.push('${licenseList.yearlyCostPerUser}');
				          monthlyTotalCost.push('${licenseList.monthTotalAmount}');yearlyTotalCost.push('${licenseList.yearlyTotalAmount}');
				          licenseId.push('${licenseList.licenseId}');
				          </script>
				             <div class="small-box bg-green">
				                 <div class="inner">
				                 
				                    <h3 class="fa fa-fw fa-usd"><span id="cost${licenseList.licenseId}">${licenseList.cost} </span> /User</h3>
				                 <p>${licenseList.licenseName}</p>
				                  <p>Student : ${licenseList.totalStudent}</p>
				                   <p>Teacher : ${licenseList.totalTeacher}</p>
				                   <p>Available Space : ${licenseList.availableSpace} GB</p>
				                    <div >Amount : <span id="totalAmount${licenseList.licenseId}">${licenseList.monthTotalAmount}</span></div>
				            </div>
				            <div class="icon">
				              <i class="fa fa-cc-paypal"></i>
				            </div>
				            <a href="#" class="small-box-footer ">
				            <c:choose>
									  <c:when test="${licenseList.licenseId==licenseDeatils.licenseId}">
									   <img src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/ppcredit-logo-large.png">
									   
									  </c:when>
									  <c:when test="${licenseList.licenseId==1}">
									   <img src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/ppcredit-logo-large.png">
									   
									  </c:when>
									  <c:otherwise>
									   <input type='image' src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/ppcredit-logo-large.png" alt="PayPal Credit"/>
									  </c:otherwise>
                           </c:choose>
				           
				            <!--  <input type='image' src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/ppcredit-logo-large.png" alt="PayPal Credit"/> -->
				            </a>
				          </div>
				        </div>
                       </form>
                       </c:forEach>  
                          
                  </div><!-- /.box-body -->
                  </div>
                  <div class="box-footer">
                   <!--  <div class="col-sm-12">
                    <div class="col-sm-7">
                    <button type="button" class="btn btn-success btn-flat button-width-large pull-right" onclick="submitForm()" style="text-transform: capitalize">Upgrate Now</button>
                    </div>
                    </div> -->
                  </div><!-- /.box-footer -->
               <!--  </form> -->
                </div>
				</div>
				<!-- /.col -->
			</div>
			</section>
			</div>
</div>
</div>
</body></html>