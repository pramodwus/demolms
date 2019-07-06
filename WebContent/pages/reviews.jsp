<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.HashMap"%>
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
                  <h3 class="box-title" style="text-transform: capitalize">Settings ${fn:toLowerCase(action)}</h3>
                </div><!-- /.box-header -->

				<input type="hidden" name="action" value="${action}">
                  <div class="box-body">
                   <!--  <div class="form-group col-sm-12">
                      <label for="email" class="col-sm-2 control-label">Email</label>
                      <div class="col-sm-10">
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" onkeyup="keyValidate()">
                        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
						<label class="requireFld" for="form-field-1" id="emailError1">This
							field is required.</label> <label class="requireFld" for="email111"
							id="emailError2">Please provide a valid !</label> <label
							class="requireFld" for="email" id="emailError3">Email
							id is already registered! Please enter new.</label>
                      </div>
                    </div> -->
                      
                   <!--  <div class="form-group col-sm-12">
                      <label for="expire" class="col-sm-2 control-label">Expire</label>
                      <div class="col-sm-10">
                        <input type="expire" class="form-control" id="expire" name="expire" readonly="readonly"  onkeyup="keyValidate()">
                      </div>
                    </div>  -->
                      
                      <table>
			<tbody>
				<!-- <tr><td><h4>Shipping Address</h5></td><td><h4>Billing Address</h4></td></tr> -->
				<% HashMap result = (HashMap) request.getAttribute("result");  %>
			<%-- 	<tr><td><%=result.get("PAYMENTREQUEST_0_SHIPTONAME")%></td><td><%=result.get("PAYMENTREQUEST_0_SHIPTONAME")%></td></tr>
				<tr><td><%=result.get("PAYMENTREQUEST_0_SHIPTOSTREET")%></td><td><%=result.get("PAYMENTREQUEST_0_SHIPTOSTREET")%></td></tr>
				<tr><td><%=result.get("PAYMENTREQUEST_0_SHIPTOCITY")%></td><td><%=result.get("PAYMENTREQUEST_0_SHIPTOCITY")%></td></tr>
				<tr><td><%=result.get("PAYMENTREQUEST_0_SHIPTOSTATE")%></td><td><%=result.get("PAYMENTREQUEST_0_SHIPTOSTATE")%></td></tr>
				<tr><td><%=result.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE")%></td><td><%=result.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE")%></td></tr>
				<tr><td><%=result.get("PAYMENTREQUEST_0_SHIPTOZIP")%></td><td><%=result.get("PAYMENTREQUEST_0_SHIPTOZIP")%></td></tr> --%>

				<!-- <tr><td colspan="2">&nbsp;</td></tr>
				<tr><td colspan="2">&nbsp;</td></tr> -->
				<tr><td>Total Amount:</td><td id='amount'><%=result.get("PAYMENTREQUEST_0_AMT")%></td></tr>
				<tr><td>Currency Code:</td><td><%=result.get("CURRENCYCODE")%></td></tr>
				<tr><td>&nbsp;</td></tr>
				<script >
				var origAmt=<%=result.get("PAYMENTREQUEST_0_AMT")%>;
				var oldshipAmt=<%=session.getAttribute("PAYMENTREQUEST_0_SHIPPINGAMT")%>;
				function updateAmount(){
					var e = document.getElementById("shipping_method");
					var shipVal = e.options[e.selectedIndex].value;
				if(shipVal == 'ups_XPD')
						shipAmt=25.43;
					else if(shipVal == 'ups_WXS')
						shipAmt=15.67;
					else 
						shipAmt=5.00;
					
					var newAmt = shipAmt+origAmt-oldshipAmt;
					document.getElementById("amount").innerHTML=newAmt;
				}
				</script>
				<tr><td><h3>Shipping Method</h3></td></tr>
					<tr><td>
					<form action="Return?page=return" name="order_confirm" method="POST">
			<!-- 	Shipping methods: <select onchange="updateAmount();" name="shipping_method" id="shipping_method" style="width: 250px;" class="required-entry">
					<optgroup label="United Parcel Service" style="font-style:normal;">
					<option value="ups_XPD">
					Worldwide Expedited - 25.43</option>
					<option value="ups_WXS">
					Worldwide Express Saver - 15.67</option>
					</optgroup>
					<optgroup label="Flat Rate" style="font-style:normal;">
					<option selected value="flatrate_flatrate">
					Fixed - 5.00</option>
					</optgroup>
					</select><br> -->
				<input type="submit" class ="btn btn-primary btn-large" value="Place Order"></input>
			</form>
					
					</td></tr>
					<tr><td></td></tr>
				
			</tbody>
		</table>
                  </div><!-- /.box-body -->
                 <!--  <div class="box-footer">
                    <div class="col-sm-12">
                    <div class="col-sm-7">
                    <button type="button" class="btn btn-success btn-flat button-width-large pull-right" onclick="submitForm()" style="text-transform: capitalize">Upgrate Now</button>
                    </div>
                    </div>
                  </div> --><!-- /.box-footer -->
               
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

<script>

</script>
</html>