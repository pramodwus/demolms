<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<%@ include file="/pages/include.jsp"%>
<style>
fieldset.course-result-border {
    border: 1px groove #ddd !important;
    padding: 0 1.4em 1.4em 1.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}

    legend.course-result-border {
        font-size: 1.2em !important;
        font-weight: bold !important;
        text-align: left !important;
        width:auto;
        padding:0 10px;
        border-bottom:none;
    }
    
    
    .course-result-border .box-widget {
     padding-top: 0px !important;
}

 .course-result-border .widget-user-2 .widget-user-image>img {
  width: 50px;
  }
  .course-result-border .widget-user-2 .box-footer {
  background-color: transparent;
  }
  
  .course-result-border .box {
  background: transparent;
  }
</style>
</head>
<body>
<div class="wrapper">
        <div class="container">
          <!-- Content Header (Page header) -->
          <section class="content" style="margin-top:50px;">
          <div class="col-md-3"></div>
          <div class="col-md-6">
<fieldset class="course-result-border">
    <legend class="course-result-border" id="course_result_header">Course Result</legend>
    <div class="control-group">
    
    <div class="col-md-12">
              <!-- Widget: user widget style 1 -->
              <div class="box box-widget widget-user-2">
                <!-- Add the bg color to the header using any of the bg-* classes -->
                <div class="widget-user-header hide">
                  <div class="widget-user-image">
                    <!-- <img class="img-circle" src="resources/images/check-mark-1292787_960_720.png" alt="User Avatar"> -->
                  </div><!-- /.widget-user-image -->
                  <h3 class="widget-user-username">Passed</h3>
                </div>
                <div class="box-footer no-padding">
                  <ul class="nav nav-stacked">
                    <li><a href="#">Completion <span class="pull-right badge bg-blue">${cmi.completion_status}</span></a></li>
                    
                    <c:if test="${cmi.success_status == 'failed'}">
                    <li><a href="#">Status <span class="pull-right badge bg-red">${cmi.success_status}</span></a></li>
                    </c:if>
                    
                    <c:if test="${cmi.success_status == 'passed'}">
                    <li><a href="#">Status <span class="pull-right badge bg-green">${cmi.success_status}</span></a></li>
                    </c:if>
                    
                    <li><a href="#">Session Time <span class="pull-right badge bg-aqua">${cmi.session_time}</span></a></li>
                    
                    <c:if test="${cmi.score.scaled == 'NA'}">
                    <li><a href="#">Score <span class="pull-right badge bg-blue">${cmi.score.scaled}</span></a></li>
                    </c:if>
                    
                    <c:if test="${cmi.score.scaled != 'NA'}">
                    <li><a href="#">Score <span class="pull-right badge bg-blue">${cmi.score.scaled} %</span></a></li>
                    </c:if>
                    
                    
                  </ul>
                </div>
              </div><!-- /.widget-user -->
              <div class="col-sm-12" style="text-align:center">
                 <button type="button" class="btn btn-flat btn-success" onclick="location.href='studentCourse?action=courseList'">Go Course List</button>
              </div>
            </div>
    </div>
</fieldset>
</div>
 <div class="col-md-3"></div>
          </section>

        </div><!-- /.container -->
      </div><!-- /.wrapper -->
<script>
$(document).ready(function(){
});
</script>
</body>
</html>