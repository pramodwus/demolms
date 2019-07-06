<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
</head>
<style>
.inner{
  background: transparent !important;
}
</style>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<div class="col-sm-12">
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
                  <h3 class="box-title" style="text-transform: capitalize">${fn:toLowerCase(action)}</h3>
                </div><!-- /.box-header -->
				<!--  <form class="form" action="ExpressCheckout" method="POST"> -->
				 <%	String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
					 
				<input type="hidden" name="action" value="${action}">
                    <br>
                      <div class="form-group col-sm-12">
                      <label for="currentPlan" class="col-sm-2 control-label">Current Plan</label>
                      <div class="col-sm-10">
                       <input type="expire" class="form-control" id="expire" name="expire" value="${licenseDeatils.licenseName}" readonly="readonly"  onkeyup="keyValidate()">
                      </div>
                      </div>
                    <div class="form-group col-sm-12">
                      <label for="expire" class="col-sm-2 control-label">Expire</label>
                      <div class="col-sm-10">
                        <input type="expire" class="form-control" id="expire" name="expire" value="${licenseDeatils.licenseExpiryDate}" readonly="readonly"  onkeyup="keyValidate()">
                      </div>
                    </div> 
                    <div class="form-group col-sm-12">
                    
                      <div class="form-group  col-sm-12">
                       <%--  <c:forEach items="${licenseList}" var="licenseList">   
                    <form class="form" action="ExpressCheckout" method="POST">
                     <input type="hidden" name="LOGOIMG" value="<%=basePath %>img/logo.jpg"></input>
					  <input type="hidden" id="currencyCodeType" name="currencyCodeType" value="USD">
					   <input type="hidden" id="paymentType" name="paymentType" value="Sale">
					    <input type="hidden" id="PAYMENTREQUEST_0_AMT" name="PAYMENTREQUEST_0_AMT" value="${licenseList.cost}">
					    <input type="hidden" name="L_PAYMENTREQUEST_0_NUMBER0" value="${licenseList.licenseId}"></input>
				        <div class="col-lg-4 col-xs-6 ">
				          <!-- small box -->
				             <div class="small-box bg-green">
				                 <div class="inner">
				                 
				                    <h3 class="fa fa-fw fa-usd">${licenseList.cost}</h3>
				                 <p>${licenseList.licenseName}</p>
				            </div>
				            <div class="icon">
				              <i class="fa fa-cc-paypal"></i>
				            </div>
				            <a href="#" class="small-box-footer ">
				            <c:choose>
									  <c:when test="${licenseList.licenseId==licenseDeatils.licenseId}">
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
                       </c:forEach>  --%>
                          <a href="upgrade"><button type="button"  class="btn  btn-success btn-lg pull-right">Upgrade</button></a>
                  </div><!-- /.box-body -->
                  </div>
                  <div class="box-footer" style="border:0px">
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
			<div class="control-sidebar-bg"></div>
		</div>
		<!-- ./wrapper -->
	</div>
</body>
<!-- Select2 -->
<script src="resources/adminlte/plugins/select2/select2.full.min.js"></script>
<script>
$(document).ready(function(){
	$("#settings").addClass('active');
});
function submitForm()
{
	location.href='upgrade';
}
</script>
</html>