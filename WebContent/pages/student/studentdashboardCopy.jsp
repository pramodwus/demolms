<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<script>
var currentpagemenuid='dashboardstu';
</script>
<%@ include file="/pages/include.jsp"%>
<link rel="stylesheet" href="<spring:url value='/resources/css/custom.css'/>">
<style>

.chart-legend>li {
	display: block;
	float: left;
}

.chart-legend>li {
	margin-left: 10px;
}

.canvas-legends {
	margin-left: 40%;
	margin-top: 50px;
}
.content-wrapper {
margin: auto;
margin-left:230px;
}


@media only screen and (min-width: 767px) {
    .content-wrapper {
        /* background-color: yellow; */
        margin-left:230px;
    }
}
@media only screen and (max-width: 760px) {
  .content-wrapper {
       /*  background-color: pink; */
        margin-left:0px;
    }
}  

</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<%@ include file="/pages/header.jsp"%>
		<%@ include file="/pages/leftmenu.jsp"%>
		<div class="col-sm-12" >
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1><spring:message code="lbl.dashboard" text="Dashboard"/></h1>
								<div class="callout callout-danger hide"
							style="margin-bottom: 5px;">
							<label><spring:message code="msg.account.not.verified" text="Verify your email address!"/></label> <label
								class="pull-right"> <a style="cursor:pointer"
								onclick="resendverificationlink()"><spring:message code="msg.resend.verification.mail" text="Resend verification email"/>
							</a>
							</label>
						</div>
			</section>
			<!-- Main content -->
			<section class="content">
			<div class="row">
             <div class="col-md-4 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="fa fa-clone" style="margin-top: 22px;"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text" class="h4"><spring:message code="lbl.assessments" text="Assessments"/></span>
                  <span class="info-box-number">${totaltest}</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->
            <div class="col-md-4 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-red"><i class="fa fa-language" style="margin-top: 22px;"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text" class="h4"><spring:message code="lbl.courses" text="Courses"/></span>
                  <span class="info-box-number">${totalcourse}</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->

            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>
                        
            <div class="col-md-4 col-sm-6 col-xs-12">
              <div class="info-box">
                <span class="info-box-icon bg-yellow"><i class="fa fa-users" style="margin-top: 22px;"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text" class="h4"><spring:message code="lbl.noofattempts" text="No. of Attempts"/></span>
                  <span class="info-box-number">${allAttemptCount}</span>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
            </div><!-- /.col -->
          </div>
            
            <div class="row">
            
            <div class="col-lg-6">
             <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message code="lbl.overallresult" text="Overall Result"/></h3>
                  <div class="box-tools pull-right">
                    <!-- <button data-widget="collapse" class="btn btn-box-tool"><i class="fa fa-minus"></i></button>  -->                   
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body" style="text-align:center;height: 271px;">
					 <div class="chart-responsive">
						<canvas id="canvas" style="width: 400px; height: 200px;"></canvas>
					 </div>					 
				</div>
				<div class="box-footer">
				<ul class="chart-legend clearfix">
										<li><i class="fa fa-circle-o text-green"></i> <spring:message code="lbl.pass" text="Pass"/>: ${countpass}</li>
										<li><i class="fa fa-circle-o text-red"></i> <spring:message code="lbl.fail" text="Fail"/>: ${countfail}</li>
										<li><i class="fa fa-circle-o text-gray"></i> <spring:message code="lbl.totalAttempts" text="=Total Attempts"/>: ${allAttemptCount}</li>										
									</ul>
					</div>
			 </div>
			 </div>          
              <div class="col-lg-6">
              <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message code="lbl.latestattemptedassessment" text="Latest Attempted Assessment"/></h3>
                  <div class="box-tools pull-right">
                    <!-- <button data-widget="collapse" class="btn btn-box-tool"><i class="fa fa-minus"></i></button>  -->                   
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <div class="table-responsive">
                  <table class="table">
                      <thead>
                        <tr>
                          <th><spring:message code="lbl.assessmenttitle" text="Assessment Title"/></th>
                          <th><spring:message code="lbl.attempteddate" text="Attempted Date"/></th>                          
                          <th><spring:message code="lbl.score" text="Score"/></th>
                          <th><spring:message code="lbl.result" text="Result"/></th>                          
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${allAttempt}" var="list" begin="0"  end="4">
                        <tr>
                          <td><c:if test="${fn:length(list.testName)>20}">${fn:substring(list.testName,0,19)}....</c:if><c:if test="${fn:length(list.testName)<=20}">${list.testName}</c:if></td>
                          <td>${list.testGivenTime}</td>
                          <%-- <td>${testlist.testMark}</td> --%>
                          <td>${list.obtainMarks}/${list.totalMarks}</td>
                          <td>${list.grade}</td>                          
                          </tr>
                          </c:forEach>
                          </tbody>
                          </table>
                    
                  </div><!-- /.table-responsive -->
                </div><!-- /.box-body -->
                <div class="box-footer clearfix">                  
                  <a class="btn btn-sm btn-default btn-flat pull-right" href="myLibrary?action=list"><spring:message code="lbl.viewall" text="View All"/></a>
                </div>
              </div>
           </div>           
          </div>
          <input type="hidden" id="countpass" value="${countpass}">
          <input type="hidden" id="countfail" value="${countfail}">
          
          <!-- BAR Chart for Attempted Bar chart Report -->
		<div class="row col-lg-12 col-xs-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">
						<spring:message code="lbl.attemptsstatistics" text="Attempts Statistics"/>
					</h3>
					<div class="box-tools pull-right">
						<button class="btn btn-box-tool" data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button class="btn btn-box-tool" data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body dashboard-box">
					<div class="form-group-sm pull-right">
                      <label><spring:message code="lbl.last" text="Last"/></label>
                      <select class="form-control" style="display: inline; width: 70px;" id="articlelastNMonths">
                      	<option value="3" >3</option>
                      	<option value="6">6</option>
                      	<option value="9">9</option>
                      	<option value="12" selected>12</option>                      	
                      </select>
                      <label><spring:message code="lbl.months" text="Months"/></label>
                    </div>
					<div class="chart" id="attemptStatsChart">
					</div>
					<div>
						<ul class="chart-legend clearfix canvas-legends">
							<li><i
								class="fa fa-circle-o" id="pass_legend"></i> <spring:message code="lbl.pass" text="Pass"/></li>
							<li><i
								class="fa fa-circle-o" id="fail_legend"></i> <spring:message code="lbl.fail" text="Fail"/></li>
						</ul>
					</div>
					<div class="overlay" id="loaderArticleStats" style="display: none">
						<i class="fa fa-spinner fa-spin"></i>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
           <!-- BAR Chart for Attempted Bar chart Report end-->
		</section>
	 </div>
		<!-- content-wrapper -->
	</div>
	</div>
	
<div class="modal fade" id="successdialog" tabindex="-1"
		role="dialog" aria-labelledby="successdialog">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p><spring:message code="msg.success.verification.mail" text="Email verification link has been sent successfully! Verify your email address and login again."/></p>
						<button type="button" class="btn btn-success button-width-large btn-flat"
						data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script>
var messages = new Array();
    messages['lbl.jan']="<spring:message code='lbl.jan' text='Jan' javaScriptEscape='true'/>";
    messages['lbl.feb']="<spring:message code='lbl.feb' text='Feb' javaScriptEscape='true'/>";
    messages['lbl.mar']="<spring:message code='lbl.mar' text='Mar' javaScriptEscape='true'/>";
    messages['lbl.apr']="<spring:message code='lbl.apr' text='Apr' javaScriptEscape='true'/>";
    messages['lbl.may']="<spring:message code='lbl.may' text='May' javaScriptEscape='true'/>";
    messages['lbl.jun']="<spring:message code='lbl.jun' text='Jun' javaScriptEscape='true'/>";
    messages['lbl.jul']="<spring:message code='lbl.jul' text='Jul' javaScriptEscape='true'/>";
    messages['lbl.aug']="<spring:message code='lbl.aug' text='Aug' javaScriptEscape='true'/>";
    messages['lbl.sep']="<spring:message code='lbl.sep' text='Sep' javaScriptEscape='true'/>";
    messages['lbl.oct']="<spring:message code='lbl.oct' text='Oct' javaScriptEscape='true'/>";
    messages['lbl.nov']="<spring:message code='lbl.nov' text='Nov' javaScriptEscape='true'/>";
    messages['lbl.dec']="<spring:message code='lbl.dec' text='Dec' javaScriptEscape='true'/>";
</script>
<script src="<spring:url value='/resources/adminlte/plugins/chartjs/Chart.js'/>"></script>
<script>
	$(function() {
		var userStatus='${userStatus==0}'; 
    	if(userStatus=='true'){    		  
    		  $("#addnew").attr("disabled","disabled");    		  
          	  $(".callout").removeClass('hide');
    	  }
		$(".treeview").removeClass("active");
		$("#dashboard").addClass("active");
		$("#dashboardstu").addClass("active");
		createGraph();
		loadAttemptedTestStatistics($("#articlelastNMonths").val());
		
		$("#articlelastNMonths").change(function(){
			loadAttemptedTestStatistics($("#articlelastNMonths").val());
		});
		
	});
	
	   function createGraph(){
		    var str = '250,88,130~130,250,88~128,128,128~247,159,129~159,129,247';
			var colors = str.split("~");
			var noattempt = $("#countfail").val()==0 && $("#countpass").val()==0 ?'100':0;
			//console.log(colors);
			var myArray = [
			               {"Fail"   : $("#countfail").val()},
			               {"Pass"   : $("#countpass").val()},
			               {"No Attempt (%)":noattempt}
			              ];
			var ctx = document.getElementById("canvas").getContext("2d");
			var myDoughnutChart = new Chart(ctx).Doughnut(DonutChart(myArray,colors));	
			
	   }
	 	
	   
	   var DonutChart = function(myArray, colors){
			var data = new Array();
			for(var i=0;i<myArray.length;i++){
				$.each(myArray[i], function(key, val) {
					data[i]= { value: val, color: "rgba("+colors[i]+",0.75)",
							highlight: "rgba("+colors[i]+",0.5)", label: key};				
				});
				
			}		
			return data;
		
	   }
	   
	    var resendverificationlink = function(){
	    	  var email='<%=((User)request.getSession().getAttribute("userSession")).getEmail() %>';
	    	  $("#successdialog").modal('show');
	    	  $.ajax({
	  				url : "sendVerifyMail",
	  				type : 'POST',
	  				//async : false,		
	  				error : (function() {
	  					alert("server error");
	  				}),
	  				success : function(data) {
	  					if(data){						
	  						$(".callout").addClass('hide');
	  						$("#successdialog").modal('show');
	  					}
	  				}
	  	  });
	      }
	    
	    
	    /**
	     * Function to load Article Statistics Data and Paint Bar Chart.
	     */
	    var loadAttemptedTestStatistics = function(lastNMonths){
	    	$("#loaderArticleStats").show();
	    	
	    	$("#canvaschart").remove();
	    	$("#attemptStatsChart").append('<canvas id="canvaschart"></canvas>');
	    	
	    	submitAjaxRequest('attemptedTestStatistics/'+lastNMonths, 'GET', '', function(response, status, xhr){
	    		
	    		var ctx = document.getElementById("canvaschart").getContext("2d");
	    		var rgbColor = response.result[1].split("~");
	    		
	    		window.myBar = new Chart(ctx).Bar(BarChart(response.result[0], rgbColor[0], rgbColor[1]), {
	    			responsive : true
	    		});
	    		$("#pass_legend").css("color", "rgb("+rgbColor[0]+")");
	    		$("#fail_legend").css("color", "rgb("+rgbColor[1]+")");
	    		
	    		$("#loaderArticleStats").hide();
	    	});
	    }
	    
	    
	    var submitAjaxRequest = function(p_url, p_method, p_data, p_successfunc){
	    	
	    	$.ajax({
	    		url : p_url,
	    		method : p_method,
	    		dataType : 'json',
	    		contentType : "application/json",
	    		data : p_data,
	    		statusCode: {
	    			403: function() {
	    				location.href = "logout";
	    		    }
	    		},
	    		success : p_successfunc
	    		
	    	});
	    	
	    }
	    
	    
	    /**
	     * Object for bar charts.
	     */
	    var BarChart = function(data, rgbColor1, rgbColor2){
	    	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
	    	var k=data;
	    	var label1=[];
	    	var data1=[];
	    	var data2=[];
	    	for(var i=0;i<k.length;i++){
	    	try{ 
	    		label1[i]=messages['lbl.'+(k[i].split("#")[0]).toLowerCase()];
	    		}catch(err)
	    		{
	    			console.log(err.messages);
	    		}
	    		data1[i]=k[i].split("#")[1];
	    		data2[i]=k[i].split("#")[2];
	    	}
	    	var barChartData = {
	    		labels : label1,
	    		datasets : [
	    			{
	    				fillColor : "rgba("+rgbColor1+",0.5)",
	    				strokeColor : "rgba("+rgbColor1+",0.8)",
	    				highlightFill: "rgba("+rgbColor1+",0.75)",
	    				highlightStroke: "rgba("+rgbColor1+",1)",
	    				data : data1
	    			},
	    			{
	    				fillColor : "rgba("+rgbColor2+",0.5)",
	    				strokeColor : "rgba("+rgbColor2+",0.8)",
	    				highlightFill : "rgba("+rgbColor2+",0.75)",
	    				highlightStroke : "rgba("+rgbColor2+",1)",
	    				data : data2
	    			}
	    		]
	    	}
	    	return barChartData;
	    }
	    

</script>
</html>