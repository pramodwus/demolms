<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<script>
var currentpagemenuid='dashboard';
</script>
<%@ include file="include.jsp"%>

<link rel="stylesheet" href="<spring:url value='/resources/css/custom.css'/>">
<style>

.content-wrapper {
margin: auto;
margin-left:230px;
}

.chart-legend>li {
	display: block;
	float: left;
	margin-left: 10px;
}


.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-left: 20px;
	margin-right: 20px;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-top-color: #f1f1f1;
	border-bottom-color: #7d7d7d;
}

.nav-tabs-custom>.nav-tabs>li.active>a, .nav-tabs-custom>.nav-tabs>li.active:hover>a
	{
	background-color: #ECF0F5;
	color: #444;	
    padding-top: 5px;
    padding-bottom: 5px;
	
}


.nav-tabs-custom {
	margin-bottom: 20px;
	background: #fff;
	box-shadow: 0 0px 0px rgba(0, 0, 0, 0.1);
	border-radius: 3px;
}

.nav-tabs {
	border-bottom: 0px solid #ddd !important;
}

.small-box:hover {
    text-decoration: none;
    color: black;
}

.removebullet{
    list-style-type: none;
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

@media (min-width: 768px)
{
.dl-horizontal dd {
  margin-left: 150px;
}
}

@media (min-width: 768px)
{
.dl-horizontal dt {
  width: 140px;
}
}

.recent-activity-text {
  border-radius: 5px;
  padding: 5px 10px;
  background: #d2d6de;
  border: 1px solid #d2d6de;
}

.box-comments .box-comment {
  border-bottom:0px;
}

.latest-course_test_list .box-header{
   min-height:55px;
 }
 
 .latest-course_test_list .box-header .box-title {
  line-height: 2;
}

</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper" >
	
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<%@ include file="dialogs.jsp"%>	
		<div class="content-wrapper"> 
			<div class="col-sm-12">
			<!-- Content Header (Page header) -->
			<section class="content-header" >
				<h1><spring:message code="lbl.leftsubmenu.trainerdashboard"/></h1>
						<div class="callout callout-danger hide"
							style="margin-bottom: 5px;">
							<label><spring:message code="msg.account.not.verified"/></label> <label
								class="pull-right"> <a href="#"
								onclick="resendverificationlink()"><spring:message code="msg.resend.verification.mail"/>
							</a>
							</label>
						</div>	
			</section>
			<!-- Main content -->
			<section class="content">			
			<div class="row">
            <div class="col-lg-3 col-xs-6">              
              <div class="small-box">
                <div class="inner" style="background-color: white;">
                  <p><spring:message code="lbl.leftmenu.users"/></p><br/>
                  <dl class="dl-horizontal">
                    <dt><spring:message code="lbl.team"/></dt>
                    <dd>${teamsCount}</dd>
                    <dt><spring:message code="lbl.trainee"/></dt>
                    <dd>${traineeCount}</dd>
                   </dl>
                </div>
                <div class="box-footer clearfix">                  
                    <a href="userlist" class="btn btn-sm btn-default btn-flat pull-right"><spring:message code="lbl.viewmore"/></a>
                  </div>
              </div>               
              
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box">
                <c:set var="coursePercent"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${countPubCourse*100/totalcourse}" /></c:set>                
                <div class="inner" style="background-color: white;">                  
                  <p><spring:message code="lbl.trainee"/></p>
                  <dl class="dl-horizontal">
                    <dt><spring:message code="lbl.published"/></dt>
                    <dd>${countPubCourse}</dd>
                    <dt><spring:message code="lbl.drafted"/></dt>
                    <dd>${draftedCourseCount}</dd>  
                    <dt><spring:message code="lbl.scheduled"/></dt>
                    <dd>${scheduledCourseCount}</dd>  
                   </dl>
                </div>
                <div class="box-footer clearfix">                  
                    <a href="courselist" class="btn btn-sm btn-default btn-flat pull-right"><spring:message code="lbl.viewmore"/></a>
                 </div>               
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box">
                <div class="inner" style="background-color: white;">                  
                  <p><spring:message code="lbl.assessment"/></p>
                  <dl class="dl-horizontal">
                    <dt><spring:message code="lbl.published"/></dt>
                    <dd>${countPubTest}</dd>
                    <dt><spring:message code="lbl.drafted"/></dt>
                    <dd>${draftedTest}</dd>  
                    <dt><spring:message code="lbl.scheduled"/></dt>
                    <dd>${scheduledTest}</dd>  
                   </dl>
                </div>
                <div class="box-footer">                  
                    <a href="testList" class="btn btn-sm btn-default btn-flat pull-right"><spring:message code="lbl.viewmore"/></a>
                  </div>             
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box">
                <div class="inner" style="background-color: white;">                  
                  <p><spring:message code="lbl.remainingdisk"/></p> <br/>                 
                  <dl class="dl-horizontal">
                    <dt><spring:message code="lbl.usedspace"/></dt>
                    <dd>
                    ${usedSpace}
                    <c:if test="${usedSpaceFileSizeType=='GB'}"><spring:message code="lbl.gb" text="GB"/></c:if>
                    <c:if test="${usedSpaceFileSizeType=='MB'}"><spring:message code="lbl.mb" text="MB"/></c:if>  
                    </dd>
                    <dt><spring:message code="lbl.remainingspace"/></dt>
                    <dd>${license.availableSpace} <spring:message code="lbl.gb"/></dd>
                   </dl>
                </div>
                <div class="box-footer clearfix">                  
                    <a href="listuploadcontent" class="btn btn-sm btn-default btn-flat pull-right"><spring:message code="lbl.viewmore"/></a>
                  </div>                
              </div>
            </div><!-- ./col -->
          </div>
                  <div class="row">
		<div class="col-lg-12">
		   <!-- Area Chart for Active uses  graph -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						<spring:message code="header.statistics"/>
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
				<div class="box-body">
				<div>
						<ul class="chart-legend clearfix canvas-legends">
							<li><i class="fa fa-circle-o" style="color:	rgba(247,161,6,1)"></i> <spring:message code="lbl.totalactiveuser"/></li>
							<li><i class="fa fa-circle-o" style="color:	rgba(249,134,139,1)"></i> <spring:message code="lbl.courseactiveusers"/></li>
							<li><i class="fa fa-circle-o" style="color:	rgba(69,75,151,1)"></i> <spring:message code="lbl.assessmentactiveusers"/></li>
						</ul>
					</div>
					<div class="form-group-sm pull-right">
                      <label><spring:message code="lbl.last"/> </label>
                      <select class="form-control" style="display: inline; width: 70px;" id="userlastNMonths" onchange="loadActiveUserStatistics(this.value);">
                      	<option value="3">3</option>
                      	<option value="6">6</option>
                      	<option value="9">9</option>
                      	<option value="12" selected>12</option>                      	
                      </select>
                      <label> <spring:message code="lbl.months"/></label>
                    </div>
					<div class="chart" id="userStatsChart">
					</div>
					<div>						
					</div>
					<div class="overlay" id="loaderUserStats" style="display: none">
						<i class="fa fa-spinner fa-spin"></i>
					</div>
				</div>
								
			</div>
			<!-- Area Chart for Active uses  graph end-->

       </div>    
      		<div class="col-lg-4 hide">
			<div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message code="lbl.chat"/></h3>                  
                </div><!-- /.box-header -->
                <div class="box-footer box-comments" style="background-color: white;">
                  <div class="box-comment" >
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar5.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Dilsher dhanju
                        <span class="text-muted pull-right">8:03 PM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle text-success"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  <div class="box-comment">
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar2.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Ankur kumar
                        <span class="text-muted pull-right">9:03 PM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle text-success"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  <div class="box-comment">
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar04.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Kuldeep kumar
                        <span class="text-muted pull-right">6:03 AM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle text-success"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  <div class="box-comment" >
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar04.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Vinay kumar
                        <span class="text-muted pull-right">9:13 AM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  <div class="box-comment" >
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar04.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Komal Rawat
                        <span class="text-muted pull-right">10:03 PM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle text-success"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment --> 
                  <div class="box-comment" >
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar04.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Rohit Mehra
                        <span class="text-muted pull-right">10:03 PM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  <div class="box-comment" >
                    <!-- User image -->
                    <img alt="user image" src="resources/adminlte/dist/img/avatar04.png" class="img-circle img-sm">
                    <div class="comment-text">
                      <span class="username">
                        Karan Mehra
                        <span class="text-muted pull-right">4:03 PM Today</span>
                      </span><!-- /.username -->
                       <i class="fa fa-circle text-success"></i>                      
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->                     
                </div>
			
			</div>
			<!-- /.box -->
		</div>
	</div>
          
          <div class="row">
            <div class="col-lg-6">
              <div class="box box-solid">
                <div class="box-header">
                  <h3 class="box-title"><spring:message code="lbl.diskmgmt"/></h3>                  
                </div><!-- /.box-header -->
                <div class="box-body ">
                  <div class="sparkpie col-lg-8 text-center" id="sparkpie0">
                    18,20,25,22,15
                  </div>
                  <div class="col-lg-4">
                    <br/><br/><br/>
                    <ul class="removebullet">                    
							<li style="margin-left: -20px;"><i style="color:#FFA000" class="fa fa-circle-o" id="pass_legend"></i> <span id="spanimg"></span>% <spring:message code="lbl.image"/></li>
							<li style="margin-left: -20px;"><i style="color:#334C83" class="fa fa-circle-o" id="fail_legend"></i> <span id="spanvideo"></span>% <spring:message code="lbl.video"/></li>
							<li style="margin-left: -20px;"><i style="color:#34D1C6" class="fa fa-circle-o" id="fail_legend"></i> <span id="spanppt"></span>% <spring:message code="lbl.ppt"/></li>
							<li style="margin-left: -20px;"><i style="color:#F26D7D" class="fa fa-circle-o" id="fail_legend"></i> <span id="spanpdf"></span>% <spring:message code="lbl.pdf"/></li>
							<li style="margin-left: -20px;"><i style="color:#D7E0E5" class="fa fa-circle-o" id="fail_legend"></i> <span id="spanfree"></span>% <spring:message code="lbl.empty"/></li>
						</ul>
                  </div>
                </div><!-- /.box-body -->
                <hr style="margin-top: 10px; margin-bottom: 10px;">               
				 <div class="box-header">                  
                  <h3 class="box-title"><spring:message code="lbl.activefilestatus"/></h3>                  
                </div>
				<div class="box-body" style="text-align:center">
				  
                  <span class="sparkpie col-lg-3" id="sparkpie1">
                    82,18
                  </span>
                  <span class="sparkpie col-lg-3" id="sparkpie2">
                    80,20
                  </span>
                  <span class="sparkpie col-lg-3" id="sparkpie3">
                    75,25
                  </span>
                  <span class="sparkpie col-lg-3" id="sparkpie4">
                    78,22
                  </span>
                </div>
              </div><!-- /.box -->
            </div><!-- /.col -->
          
           <div class="col-lg-6">
              <div class="box latest-course_test_list">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message code="lbl.latestcourseassessment"/></h3>
                  <div class="box-tools pull-right">
                    <div class="nav-tabs-custom">
							<ul class="nav nav-tabs"
								style="border-bottom: 1px solid #dedede;">
								<li class="active" style="margin-right:0px;text-align:center;">
									<a href="#courseListDiv"
									data-toggle="tab"><spring:message code="lbl.course"/></a></li>
								<li class=" button-width" style="margin-right:0px;text-align:center;">
								<a href="#testListDiv"
									data-toggle="tab"><spring:message code="lbl.assessment"/></a></li>
																	
							</ul>
						</div>                   
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body">
                <div class="tab-content">
                 <div id="courseListDiv" class="tab-pane active">
                  <div class="table-responsive">
                  <table class="table no-margin">
                      <thead>
                        <tr>
                          <th><spring:message code="lbl.title"/></th>
                          <th><spring:message code="lbl.status"/></th>
                          <th><spring:message code="lbl.action"/></th>                          
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${courseList}" var="courseList" begin="0"  end="7">
                        <tr>
                          <td>
                          <a href="courseViewController?courseId=${courseList.courseId}&isPublish=${courseList.publish}">
                          <c:if test="${fn:length(courseList.courseName)>20}">${fn:substring(courseList.courseName,0,19)}....</c:if>
                          <c:if test="${fn:length(courseList.courseName)<=20}">${courseList.courseName}</c:if></a>
                          </td>
                          <td>
                            <c:if test="${courseList.publish==1}"><spring:message code="lbl.published"/></c:if>
                            <c:if test="${courseList.publish==0}"><spring:message code="lbl.drafted"/></c:if>
                            <c:if test="${courseList.publish==2}"><spring:message code="lbl.scheduled"/></c:if> 
                          </td>	
                          <td>                            
                            <c:if test="${courseList.publish==0}">
                              <a href="createCourse?courseId=${courseList.courseId}">
                              <font color="#00A65A"><i class="fa fa-lg fa-pencil" title="<spring:message code="lbl.edit"/>"></i></font></a>&nbsp;&nbsp; 
                              <a href="javascript:void(0)" onclick="deletecourse(${courseList.courseId});">
                              <font color="#00A65A"><i class="fa fa-lg fa-trash" title="<spring:message code="lbl.delete"/>"></i></font></a>
                            </c:if>                                                        
                           </td>                                                     
                          </tr>
                          </c:forEach>
                          </tbody>
                          </table>                    
                  </div><!-- /.table-responsive -->
                  <div class="box-footer clearfix">                  
                    <a class="btn btn-sm btn-default btn-flat pull-right" href="courselist"><spring:message code="lbl.viewmore"/></a>
                  </div><!-- /.box-footer -->
                  </div>
                  
                  <div id="testListDiv" class="tab-pane">
                  <div class="table-responsive">
                  <table class="table no-margin">
                      <thead>
                        <tr>
                           <th><spring:message code="lbl.title"/></th>
                          <th><spring:message code="lbl.status"/></th>
                          <th><spring:message code="lbl.action"/></th>                             
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${testList}" var="testList" begin="0"  end="8">
                        <tr>                          
                          <td><a href="javascript:void(0)" onclick="viewtestpage(${testList.testId})">${testList.testName}</a></td>                          
                          <td>
                            <c:if test="${testList.testPublishStatus==1}"><spring:message code="lbl.published"/></c:if>
                            <c:if test="${testList.testPublishStatus==0}"><spring:message code="lbl.drafted"/></c:if>
                            <c:if test="${testList.testPublishStatus==2}"><spring:message code="lbl.scheduled"/></c:if>  
                          </td>
                          <td>                           
                            <c:if test="${testList.testPublishStatus==0}">
                              <a href="javascript:void(0)" onclick="location.href='addEditTest?testId=${testList.testId}'">
                              <font color="#00A65A"><i class="fa fa-lg fa-pencil" title="<spring:message code="lbl.edit"/>"></i></font></a>
                              &nbsp;&nbsp;
                              <a href="javascript:deleteDraftedTest(${testList.testId})">
                              <font color="#00A65A"><i class="fa fa-lg fa-trash" title="<spring:message code="lbl.delete"/>"></i></font></a>
                            </c:if>
                          </td>                                                     
                          </tr>
                          </c:forEach>
                          </tbody>
                          </table>
                    
                  </div><!-- /.table-responsive -->
                  <div class="box-footer clearfix">                  
                   <a class="btn btn-sm btn-default btn-flat pull-right" href="testList"><spring:message code="lbl.viewmore"/></a>
                  </div><!-- /.box-footer -->
                  </div>
                  
                  </div>
                </div><!-- /.box-body -->
                
              </div>
           </div>
          </div>
          
        <div class="row">  
        <!-- BAR Chart for Course Popularity chart Report -->
		<div class="col-lg-8 col-xs-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						<spring:message code="lbl.coursestatistics"/>
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
				  <div>
						<ul class="chart-legend clearfix canvas-legends">
							<li><i class="fa fa-circle-o" style="color:	#32D3C4"></i> <spring:message code="lbl.popularity"/></li>
							<li><i class="fa fa-circle-o" style="color:	#364B80"></i> <spring:message code="lbl.nonpopularity"/></li>							
						</ul>
					</div>
					<div class="form-group-sm pull-right">
                      <label><spring:message code="lbl.last"/> </label>
                      <select class="form-control" style="display: inline; width: 70px;" id="courselastNMonths">
                      	<option value="3" >3</option>
                      	<option value="6">6</option>
                      	<option value="9">9</option>
                      	<option value="12" selected>12</option>                      	
                      </select>
                      <label> <spring:message code="lbl.months"/></label>
                    </div>
					<div class="chart" id="courseStatsChart">
					</div>
					<div>						
					</div>
					<div class="overlay" id="loaderCourseStats" style="display: none">
						<i class="fa fa-spinner fa-spin"></i>
					</div>
				</div>				
			</div>
			<!-- /.box -->
		</div>
           <!-- BAR Chart for Course Bar chart Report end-->
     		<div class="col-lg-4 col-xs-12">
			<div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title"><spring:message code="lbl.recentactivities"/></h3>                  
                </div><!-- /.box-header -->
                <div class="box-footer box-comments" style="background-color: white; max-height: 390px;overflow:auto">
                <c:if test="${fn:length(activityList)==0}">
                <div class="box-comment text-align">
                <span><spring:message code="lbl.norecentactivity" text="No recent activity"/></span>
                </div>
                </c:if>
                <c:if test="${fn:length(activityList)>0}">
                <c:forEach items="${activityList}" var="list">
                  <div class="box-comment">
                    <div class="recent-activity-text">                      
                      ${list}                                  
                    </div><!-- /.comment-text -->
                  </div><!-- /.box-comment -->
                  </c:forEach> 
                </c:if>                               
                </div>
			
			</div>
			<!-- /.box -->
		</div>  
       </div>
          
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
						<p><spring:message code="msg.success.verification.mail"/></p>
						<button type="button" class="btn btn-success button-width-large btn-flat"
						data-dismiss="modal"><spring:message code="lbl.close"/></button>
						</div>
				</div>
			</div>
		</div>
		</div>
			
			<!-- Start of Alert box for delete -->
	<div class="modal fade" id="deleteAlert" tabindex="-1"
		role="dialog" aria-labelledby="deleteAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p></p>
						<button type="button" class="btn btn-default button-width"
							data-dismiss="modal"><spring:message code="lbl.no"/></button>
						<a id="dId"><button type="button"
								class="btn btn-success button-width" data-dismiss="modal"><spring:message code="lbl.yes"/></button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="errorAlert" tabindex="-1"
		role="dialog" aria-labelledby="errorAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p></p>
						<button type="button" class="btn btn-success button-width-large btn-flat"
						data-dismiss="modal"><spring:message code="lbl.ok"/></button>
						</div>
				</div>
			</div>
		</div>
	</div>	
</body>
<script src="<spring:url value='/resources/adminlte/plugins/chartjs/Chart.js'/>"></script>
<script src="<spring:url value='/resources/adminlte/plugins/sparkline/jquery.sparkline.min.js'/>"></script>
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
<script>
	$(function() {
		var userStatus='${userStatus==0}'; 
    	if(userStatus=='true'){    		  
    		  $("#addnew").attr("disabled","disabled");    		  
          	  $(".callout").removeClass('hide');
    	  }
		$(".treeview").removeClass("active");
		$("#dashboard").addClass("active");
		$("#dashboard .treeview-menu > #dashboard").addClass("active");
		$('.imgset img').imgCentering({'forceWidth': true,});
		
		$(".sparkline").each(function () {
	          var $this = $(this);
	          $this.sparkline('html', $this.data());
	        });
		
		var diskmap = ${diskmap};
		var str = (diskmap.image==null?0:diskmap.image)+","+(diskmap.video==null?0:diskmap.video)+","+(diskmap.ppt==null?0:diskmap.ppt)+","+(diskmap.pdf==null?0:diskmap.pdf)+","+(diskmap.free==null?0:diskmap.free);
		
		$('#spanimg').text((diskmap.image==null?0:diskmap.image));
		$('#spanvideo').text((diskmap.video==null?0:diskmap.video));
		$('#spanppt').text((diskmap.ppt==null?0:diskmap.ppt));
		$('#spanpdf').text((diskmap.pdf==null?0:diskmap.pdf));
		$('#spanfree').text((diskmap.free==null?0:diskmap.free));		
		$('#sparkpie0').text(str);
		
		// Pie charts
        $('#sparkpie0').sparkline('html', 
               {type: 'pie', height: '250px',width: '250px',
                sliceColors:['#FFA000','#334C83','#34D1C6','#F26D7D','#D7E0E5'],
                highlightLighten: 1.1,
                tooltipFormat: '{{offset:offset}} ({{percent.2}}%)',
                tooltipValueLookups: {
                    'offset': {
                        0: '<spring:message code="lbl.image"/>',
                        1: '<spring:message code="lbl.video"/>',
                        2: '<spring:message code="lbl.ppt"/>',
                        3: '<spring:message code="lbl.pdf"/>',
                        4: '<spring:message code="lbl.empty"/>'
                    }
                }
        });
		
        var varimg = 100 - Number((diskmap.image==null?0:diskmap.image));
        $('#sparkpie1').text(varimg+","+(diskmap.image==null?0:diskmap.image));
     // Pie charts
        $('#sparkpie1').sparkline('html', 
               {type: 'pie', height: '60px',width: '60px',
                sliceColors:['#FFDCA2','#FFA000'],
                highlightLighten: 1.1,
                tooltipFormat: '{{offset:offset}} ({{percent.2}}%)',
                tooltipValueLookups: {
                    'offset': {
                        1: '<spring:message code="lbl.image"/>'                    
                    }
                }
        });
     
     
        var varvideo = 100 - Number((diskmap.video==null?0:diskmap.video));
        $('#sparkpie2').text(varvideo+","+(diskmap.video==null?0:diskmap.video));
        // Pie charts
        $('#sparkpie2').sparkline('html', 
               {type: 'pie', height: '60px',width: '60px',
                sliceColors:['#BED2FD','#334C83'],
                highlightLighten: 1.1,
                tooltipFormat: '{{offset:offset}} ({{percent.2}}%)',
                tooltipValueLookups: {
                    'offset': {
                        1: '<spring:message code="lbl.video"/>'                    
                    }
                }
        });
        
        var varppt = 100 - Number((diskmap.ppt==null?0:diskmap.ppt));
        $('#sparkpie3').text(varppt+","+(diskmap.ppt==null?0:diskmap.ppt));        
        // Pie charts
        $('#sparkpie3').sparkline('html', 
               {type: 'pie', height: '60px',width: '60px',
                sliceColors:['#C0F8F4','#34D1C6'],
                highlightLighten: 1.1,
                tooltipFormat: '{{offset:offset}} ({{percent.2}}%)',
                tooltipValueLookups: {
                    'offset': {
                        1: '<spring:message code="lbl.ppt"/>'                    
                    }
                }
        });
        
        var varpdf = 100 - Number((diskmap.pdf==null?0:diskmap.pdf));
        $('#sparkpie4').text(varpdf+","+(diskmap.pdf==null?0:diskmap.pdf));
        // Pie charts
        $('#sparkpie4').sparkline('html', 
               {type: 'pie', height: '60px',width: '60px',
                sliceColors:['#FEC3CA','#F26D7D'],
                highlightLighten: 1.1,
                tooltipFormat: '{{offset:offset}} ({{percent.2}}%)',
                tooltipValueLookups: {
                    'offset': {
                        1: '<spring:message code="lbl.pdf"/>'                    
                    }
                }
        });
	});
		
	var viewtestpage = function(testId){    	 
  	  location.href='viewTestDetail?testId='+testId;
    }
    var resendverificationlink = function(){
  	  var email='<%=((User)request.getSession().getAttribute("userSession")).getEmail() %>';
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
    $(document).ready(function(){
        $("button").click(function(){
            $("#button").toggleClass("main");
        });                
        loadCoursePopularStatistics(12);
        loadActiveUserStatistics(12); 
    });
     
 var deletecourse = function(id){  
	 $('#deleteAlert p').text('<spring:message code="msg.course.delete"/>');
     $("#dId").attr('onclick','ajaxDelete('+id+')');
     $('#deleteAlert').modal('show');     
 }


var ajaxDelete = function(id){
   
      $.ajax({
          url:"deletedraftedcourse?courseId="+id,
         type:'POST',
         error : function(){
             $("#overlay").hide();
             $("#errorAlert p").text('<spring:message code="msg.error.course.delete"/>');
              $("#errorAlert").modal('show');
         },
         success : function(status){
             //$("#overlay").hide();
             if(status){
                 location.href='dashboardcontroller';
             }                    
         }
      });
}
	

function deleteDraftedTest(testId){
	   if(testId>0){
		   $("#dId").attr('onclick', 'ajaxTestDelete('+testId+')');
		   $('#deleteAlert p').text('<spring:message code="msg.assessment.delete"/>');
		   $('#deleteAlert').modal('show');
	   }
}

var ajaxTestDelete = function(id){
	$("#overlay").show(); 
    $.ajax({
        url:"deletedraftedtest?testId="+id,
       type:'GET',
       error : function(){
           $("#overlay").hide();
           $("#errorAlert p").text('<spring:message code="msg.error.assessment.delete"/>');
            $("#errorAlert").modal('show');
       },
       success : function(status){
           $("#overlay").hide(); 
           if(status){
               location.href='dashboardcontroller';
           }  
       }
    });
}
    	
    	/**
	     * Function to load course Statistics Data and Paint Bar Chart.
	     */
	    var loadCoursePopularStatistics = function(lastNMonths){
	    	$("#loaderCourseStats").show();
	    	
	    	$("#canvaschart").remove();
	    	$("#courseStatsChart").append('<canvas id="canvaschart"></canvas>');	    	
	    	/* submitAjaxRequest('attemptedTestStatistics/'+lastNMonths, 'GET', '', function(response, status, xhr){	    		
	    		var ctx = document.getElementById("canvaschart").getContext("2d");
	    		var rgbColor = response.result[1].split("~");	    		
	    		window.myBar = new Chart(ctx).Bar(BarChart(response.result[0], rgbColor[0], rgbColor[1]), {
	    			responsive : true
	    		});
	    		$("#pass_legend").css("color", "rgb("+rgbColor[0]+")");
	    		$("#fail_legend").css("color", "rgb("+rgbColor[1]+")");	    		
	    		
	    	}); */
	    	var ctx = document.getElementById("canvaschart").getContext("2d");
	    	var rgbColor = [
                             '50, 211, 196',
                             '54, 75, 128'             
                           ];
	    	
	    	var data =  ['<spring:message code="lbl.september"/>#5#1', '<spring:message code="lbl.october"/>#1#2', '<spring:message code="lbl.november"/>#6#3', '<spring:message code="lbl.december"/>#7#5', '<spring:message code="lbl.january"/>#0#0', '<spring:message code="lbl.february"/>#0#0', '<spring:message code="lbl.march"/>#4#2', '<spring:message code="lbl.april"/>#3#1', '<spring:message code="lbl.may"/>#0#0', '<spring:message code="lbl.june"/>#5#3', '<spring:message code="lbl.july"/>#2#1', '<spring:message code="lbl.august"/>#0#1'];
	    	
	    	window.myBar = new Chart(ctx).Bar(BarChart(data, rgbColor[0], rgbColor[1]), {
    			responsive : true
    		});
	    	$("#loaderCourseStats").hide();
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
	    	//var lbl=["User enrolled","User Viewed",];
	    	var lbl1="Enrolled";
	    	var lbl2="Viewed";
	    	for(var i=0;i<k.length;i++){
	    		label1[i]=k[i].split("#")[0];
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
	    
	    /**
	     * Function to load active users Statistics Data and Paint Bar Chart.
	     */
	    var loadActiveUserStatistics = function(lastNMonths){
	    	var userArr = [];
	    	var courseArr = [];
	    	var testArr = [];
	    	var monthsArr = [];
	    	submitAjaxRequest('activeUserStatistics/'+lastNMonths, 'GET', '', function(response, status, xhr){
	    		var k = response.activeUser;
	    		for(var i=0;i<k.length;i++){
	    	    	try{ 
	    	    		monthsArr[i]=messages['lbl.'+(k[i].split("#")[0]).toLowerCase()];	    	    		
	    	    		userArr[i]=parseInt(k[i].split("#")[1]);
	    	    		courseArr[i]=parseInt(response.courseActiveUser[i]);
	    	    		testArr[i]=parseInt(response.testActiveUser[i])
	    	    		}catch(err)
	    	    		{
	    	    			console.log(err.messages);
	    	    		}
	    	    		
	    	    	}	    		
	    	});
	    	
	    	$("#loaderUserStats").show();	    	
	    	$("#canvasuserchart").remove();
	    	$("#userStatsChart").append('<canvas id="canvasuserchart"></canvas>');
	    	var ctx = document.getElementById("canvasuserchart").getContext("2d");
	        var data4 = {
	          labels: monthsArr,
	          datasets: [{
	            label: "My First dataset",
	            fillColor: "rgba(247,161,6,0.2)",
	            strokeColor: "rgba(247,161,6,1)",
	            pointColor: "rgba(247,161,6,1)",
	            pointStrokeColor: "#F7A106",
	            pointHighlightFill: "#F7A106",
	            pointHighlightStroke: "rgba(247,161,6,1)",
	            data: userArr
	          } , {
	            label: "My Second dataset",
	            fillColor: "rgba(249,134,139,0.2)",
	            strokeColor: "rgba(249,134,139,1)",
	            pointColor: "rgba(249,134,139,1)",
	            pointStrokeColor: "#FB8D93",
	            pointHighlightFill: "#FB8D93",
	            pointHighlightStroke: "rgba(249,134,139,1)",
	            data: courseArr
	          },
	          {
		            label: "My third dataset",
		            fillColor: "rgba(69,75,151,0.2)",
		            strokeColor: "rgba(69,75,151,1)",
		            pointColor: "rgba(69,75,151,1)",
		            pointStrokeColor: "#454B97",
		            pointHighlightFill: "#454B97",
		            pointHighlightStroke: "rgba(69,75,151,1)",
		            data: testArr
		          }
	          ]
	        };
	              
	        var MyNewChart = new Chart(ctx).Line(data4, {
	                scaleSteps : 10,
	                scaleStepWidth : 1,
	                scaleStartValue : 0, 
	                });	        
	    	$("#loaderUserStats").hide();
	    }
	    
	        
	    
	    var submitAjaxRequest = function(p_url, p_method, p_data, p_successfunc){
	    	
	    	$.ajax({
	    		url : p_url,
	    		method : p_method,
	    		dataType : 'json',
	    		contentType : "application/json",
	    		data : p_data,
	    		async:false,
	    		statusCode: {
	    			403: function() {
	    				location.href = "logout";
	    		    }
	    		},
	    		success : p_successfunc
	    		
	    	});
	    	
	    }
	      
</script>
</html>