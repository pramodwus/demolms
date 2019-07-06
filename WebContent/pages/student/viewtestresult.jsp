<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ include file="/pages/include.jsp"%>
<link rel="stylesheet" href="<spring:url value='/resources/css/resultcustom.css'/>">
    <!-- <link rel="stylesheet" href="adminlte/plugins/morris/morris.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="adminlte/plugins/morris/morris.min.js"></script> -->
    <script src="<spring:url value='/resources/adminlte/plugins/chartjs/Chart.js'/>"></script>
        
	</head>
	
	<body class="hold-transition skin-blue sidebar-mini">
	<div id="overlay" class="overlay1" style="display: none;position: fixed; left: 0;top: 0;  bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity=80); z-index:9999;">
 		<img id="loading" class="lazy" src="<spring:url value='/resources/images/loading.gif'/>" style="position:fixed;left: 50%; top: 50%;">
	</div>
	<!-- start dataTable----->
 <section class="content-header">
         <input type="hidden" id="userid" value='${userId}'>
         <input type="hidden" id="test_id">
 </section> 
     <section class="content">
     <div class="container" style="background: white">
        <div class="row">         
                <div class="box-header with-border">
                <div class="col-sm-6">
                <h2 class="box-title pull-left"><spring:message code="lbl.resultdetails" text="Result Details"/></h2>
                </div>
                <div class="col-sm-6">
                <h2 class="box-title pull-right"><span id="assessmentName"></span> (<spring:message code="lbl.assessment" text="Assessment"/>)</h2>
                </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                   <div class="row">
                    <div class="col-md-6 col-sm-12 col-xs-12">
					 <div class="chart-responsive">
						<canvas id="canvas" style="width: 500px; height: 250px;"></canvas>
					 </div>
					</div>
					<div class="col-md-6 col-sm-12 col-xs-12">
					  <div id="spanpass" class="text-green hide">
						<h3><spring:message code="lbl.congratulation" text="Congratulation"/>!</h3>
                        <h3><spring:message code="lbl.result" text="Result"/> : <spring:message code="lbl.pass" text="Pass"/></h3></div>
                        <div id="spanfail" class="text-red hide">
						<h3><spring:message code="lbl.oops" text="OOPS"/>!</h3>
                        <h3><spring:message code="lbl.result" text="Result"/> : <spring:message code="lbl.fail" text="Fail"/></h3></div>
                        <div class="col-lg-8" style="padding-top: 15px;">
                        <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-clock-o"></i></span>
                        <div class="info-box-content">
                        <div class="info-box-text"><h3>
                        <span id="spanmin0"></span>
                        <span id="spanmin1"></span></h3>
                        </div>                  
                      </div>
                     </div>
					</div>
					<div class="col-lg-8" style="padding-top: 15px;">
                        <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-circle"></i></span>
                        <div class="info-box-content">
                        <div class="info-box-text"><h3>
                        <span id="spanmark0"></span>
                        <span id="spanmark1"></span></h3>
                        </div>                  
                      </div>
                     </div>
					</div>
					</div>
				</div>
				<div class="col-xs-12" style="min-height:70px"></div>
				<div class="container">
                        <div class="col-sm-12" style="border-top-width:1px;border-top-style:solid;border-top-color:lightgray;min-height:10px"></div>
                        <div class="col-sm-12">
                            <div class="row">                                
                                <div class="col-md-3 col-sm-6 col-xs-12">
                                    <div class="info-box info-box-result">
                                        <span class="info-box-icon "><i class="fa  fa-circle" style="color:aqua"></i></span>
                                        <div class="info-box-content">
                                            <span id="span1" class="info-box-text"></span>
                                        </div>
                                        <!-- /.info-box-content -->
                                    </div>
                                    <!-- /.info-box -->
                                </div>
                                <div class="col-md-3 col-sm-6 col-xs-12">
                                    <div class="info-box info-box-result">
                                        <span class="info-box-icon "><i class="fa  fa-circle" style="color:rgba(130,250,88,0.75)"></i></span>
                                        <div class="info-box-content">
                                            <span id="span2" class="info-box-text"></span>
                                        </div>
                                        <!-- /.info-box-content -->
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-6 col-xs-12">
                                    <div class="info-box info-box-result">
                                        <span class="info-box-icon"><i class="fa fa-circle" style="color:red"></i></span>
                                        <div class="info-box-content">
                                            <span id="span3" class="info-box-text"></span>
                                        </div>
                                        <!-- /.info-box-content -->
                                    </div>
                                </div>
                                <div class= "col-md-3 col-sm-6 col-xs-12">
                                    <div class="info-box info-box-result">
                                        <span class="info-box-icon "><i class="fa  fa-circle" style="color:rgba(46,100,254,0.75)"></i></span>
                                        <div class="info-box-content">
                                            <span id="span4" class="info-box-text"></span>
                                        </div>
                                        <!-- /.info-box-content -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>				     
              </div>            
         </div>
         <div class="container">
          <div class="col-xs-12" style="border-top-width:1px;border-top-style:solid;border-top-color:lightgray;min-height:10px"></div>
                        <h3 style="text-align:center"><spring:message code="lbl.assessmenthistory" text="Assessment History"/></h3>
                        <table class="table table-striped">
                            <thead>                        
                            <tr>
                                <th><spring:message code="lbl.date" text="Date"/></th>
                                <th><spring:message code="lbl.result" text="Result"/></th>
                                <th><spring:message code="lbl.question" text="Question"/></th>
                                <th><spring:message code="lbl.action" text="Action"/></th>
                            </tr>
                            </thead>
                            <tbody id="historybody">
                            </tbody>
                        </table>
          </div>
          
          <div class="container" id="summarydiv">
         </div>
         <div class="container" id="summarygraph"></div>
          
         </div>
         <div>
            <nav>
                <ul class="pager pull-left">
                    <li style="margin:30px"><button class="btn btn-success btn-flat  button-width-large" type="button" onclick="quit()"><spring:message code="lbl.close" text="Close"/></button></li>
                </ul>
                <ul style="margin-right:40px" class="pager pull-right">
                    <li><button id="reviewbtn" class="btn btn-success btn-flat  button-width-large" type="submit"><spring:message code="lbl.review" text="Review"/></button></li>
                    <li>&nbsp;&nbsp;</li>
                    <li><button id="improvebtn" class="btn btn-success btn-flat"
                     type="button" onclick="improvetest()">
                    <spring:message code="lbl.improveassessment" text="Improve Assessment"/>&emsp;<i class="fa  fa-long-arrow-right"></i>
                    </button></li>

                </ul>
            </nav>

        </div>
       </section>
	</body>
<script>
var messages = new Array();
messages['lbl.attempt'] = "<spring:message code='lbl.attempt' text='Attempt' javaScriptEscape='true'/>";
messages['lbl.correct'] = "<spring:message code='lbl.correct' text='Correct' javaScriptEscape='true'/>";
messages['lbl.wrong'] = "<spring:message code='lbl.wrong' text='Wrong' javaScriptEscape='true'/>";
messages['lbl.skipped'] = "<spring:message code='lbl.skipped' text='Skipped' javaScriptEscape='true'/>";
messages['lbl.question'] = "<spring:message code='lbl.question' text='Question' javaScriptEscape='true'/>";
messages['lbl.review'] = "<spring:message code='lbl.review' text='Review' javaScriptEscape='true'/>";
messages['lbl.assessmentsummary'] = "<spring:message code='lbl.assessmentsummary' text='Assessment Summary' javaScriptEscape='true'/>";
messages['lbl.taken'] = "<spring:message code='lbl.taken' text='Taken' javaScriptEscape='true'/>";
messages['lbl.passed'] = "<spring:message code='lbl.passed' text='Passed' javaScriptEscape='true'/>";
messages['lbl.failed'] = "<spring:message code='lbl.failed' text='Failed' javaScriptEscape='true'/>";
messages['lbl.times'] = "<spring:message code='lbl.times' text='Times' javaScriptEscape='true'/>";
messages['lbl.averagescore'] = "<spring:message code='lbl.averagescore' text='Average Score' javaScriptEscape='true'/>";
messages['lbl.skip'] = "<spring:message code='lbl.skip' text='Skip' javaScriptEscape='true'/>";
messages['lbl.pass'] = "<spring:message code='lbl.pass' text='Pass' javaScriptEscape='true'/>";
messages['lbl.fail'] = "<spring:message code='lbl.fail' text='Fail' javaScriptEscape='true'/>";
</script>
   <script>
   var usertestattempt={};
   
   function getUrlVars() {
   var vars = {};
   var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
   vars[key] = value;
   });
   return vars;
   }
   $( document ).ready(function() {
	   var id = getUrlVars()["id"];
	   var totalquestion = getUrlVars()["totalquestion"];
	   $("#totalQuestions").text(totalquestion);
       $("#overlay").show();
	   $.ajax({
			url : "api/getattemptedtestresult/"+id+"/"+$("#userid").val(),
			type: "GET",
			beforeSend: function(xhr){xhr.setRequestHeader('authorization', 'Browser');},
			error : function() {
				$("#overlay").hide();
				alert("error");
			},
			success : function(data){ 
				$("#instructions").hide();
				$("#questionlist").show();
				usertestattempt=data.userTestAttempt;
				setTestHistory(data.testHistory);
				setTestSummary(data.testSummary);
				createGraph();
				$("#assessmentName").html(usertestattempt.testName);
				$("#span1").html("<h4>"+(usertestattempt.correctQueAttempt+usertestattempt.wrongQueAttempt)+"/"+usertestattempt.totalQuestion+"<br/>"+messages['lbl.attempt']+"</h4>")
				$("#span2").html("<h4>"+usertestattempt.correctQueAttempt+"/"+usertestattempt.totalQuestion+"<br/>"+messages['lbl.correct']+"</h4>");
				$("#span3").html("<h4>"+usertestattempt.wrongQueAttempt+"/"+usertestattempt.totalQuestion+"<br/>"+messages['lbl.wrong']+"</h4>");
				$("#span4").html("<h4>"+usertestattempt.unAttemptQue+"/"+usertestattempt.totalQuestion+"<br/>"+messages['lbl.skipped']+"</h4>");
				$("#spanmark0").html(usertestattempt.obtainMarks);
				$("#spanmark1").html("/"+usertestattempt.totalMarks);
				if(usertestattempt.grade=="Pass")
					$("#spanpass").removeClass('hide');
				else
					$("#spanfail").removeClass('hide');
					
				var timetaken = usertestattempt.timeTaken;
				$("#spanmin0").html(usertestattempt.timeTaken);				
                var timegiven = usertestattempt.testGivenTime;
				if(usertestattempt.testGivenTime==""){					
					$("#spanmin1").hide();
				}else{
					$("#spanmin1").html("/"+usertestattempt.testGivenTime);
				}
				if(usertestattempt.isReview==0){					
					$("#reviewbtn").hide();
				}else{
					$("#reviewbtn").attr('onclick','reviewattemptedtest('+usertestattempt.userTestAttemptId+')');
				}
				$("#test_id").val(usertestattempt.testId);			
				$("#overlay").hide();
			}
		}); 
   });
   function createGraph(){
	    var str = '250,88,130~130,250,88~46,100,254~247,159,129~159,129,247';
		var colors = str.split("~");
		var wrong=messages['lbl.wrong'];
		var correct=messages['lbl.correct'];
		var skip=messages['lbl.skip'];
		var myArray = [
		               {"wrong"   :usertestattempt.wrongQueAttempt},
		               {"correct" : usertestattempt.correctQueAttempt},
		               {"skip"    :usertestattempt.unAttemptQue}		               
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
   
   var setTestHistory = function(data){
	   var str='';
	   for(var i=0;i<data.length;i++){
		   var attemresultstatus = data[i].grade=='Pass'?messages['lbl.pass']:messages['lbl.fail'];
		   str+='<tr><td>'+data[i].testGivenTime+'</td><td>'+attemresultstatus+'</td><td>'+messages['lbl.question']+'('+data[i].correctQueAttempt+' '+messages['lbl.correct']+')</td>'
		   if(usertestattempt.isReview=='0')
		      str+='<td>--</td>';
		   else
			   str+='<td><a href="#" onclick="reviewattemptedtest('+data[i].userTestAttemptId+')">'+messages['lbl.review']+'</a></td>';
		   str+='</tr>';
	   }
	   $("#historybody").append(str);
   }
   
   var reviewattemptedtest = function(userTestAttemptId){	   
	   //window.open('testReview?userTestAttemptId='+userTestAttemptId, '', ' scrollbars=yes,menubar=no,width=800, resizable=yes,toolbar=no,location=no,status=no')
	   location.href = "studentTest?action=review&userTestAttemptId="+userTestAttemptId;
   }
   
   var improvetest = function(){
	   //window.open('student?action=instruct&testId='+$("#test_id").val(), '', ' scrollbars=yes,menubar=no,width=800, resizable=yes,toolbar=no,location=no,status=no')
	   location.href = "studentGivenTest?testId="+$("#test_id").val();
   }
   
   var quit = function(){
		 window.close();
	 }
   
   
   var setTestSummary = function(data){
	   var str='';	   
	   str ='<div style="border-top-width:1px;border-top-style:solid;border-top-color:lightgray;min-height:10px" class="col-xs-12"></div>'+
           '<h3 style="text-align:center">'+messages['lbl.assessmentsummary']+'</h3>'+
           '<dl class="dl-horizontal"><dt>'+messages['lbl.taken']+':</dt><dd>'+data.countattempt+'&nbsp;'+messages['lbl.times']+'</dd>'+
           '<dt>'+messages['lbl.passed']+':</dt><dd>'+data.countpass+'&nbsp;'+messages['lbl.times']+'</dd>'+
           '<dt>'+messages['lbl.failed']+':</dt><dd>'+data.countfail+'&nbsp;'+messages['lbl.times']+'</dd>'+
           '<dt>'+messages['lbl.averagescore']+':</dt><dd>'+Number(data.average).toFixed(2)+'%</dd> </dl></div>';
           $("#summarydiv").append(str);
           
           /* var graph = new Array();           
           for(var i=0;i<data.attemptList.length;i++){
        	   graph[i]= { year: i, value: data.attemptList[i].percentage};
           }  
	   
	   new Morris.Line({		   
		   element: 'summarygraph',
		   data: graph,
		   xkey: 'year',
		   ykeys: ['value'],
		   labels: ['Value']
		 }); */
   }
    </script> 
 </html>