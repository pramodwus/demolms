<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <%@ include file="include.jsp"%>
   <link rel="stylesheet" href="resources/css/custom.css">
   <style>

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

@media (min-width: 768px){
.dl-horizontal dd {
  margin-left: 200px;
}
}

@media (min-width: 768px){
.dl-horizontal dt {
  width: 180px;
}
}

.capitalize {
	text-transform: capitalize;
}
</style>
	</head>
	
	<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
	<div id="overlay" class="overlay1" style="display: none;position: fixed; left: 0;top: 0;  bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity=80); z-index:9999;">
 		<img id="loading" class="lazy" src="resources/images/loading.gif" style="position:fixed;left: 50%; top: 50%;">
	</div>
        <%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>  
	
	<!-- start dataTable----->
	<div class="col-sm-12" >
 <div class="content-wrapper">
 <section class="content">
 <div class="row" id="viewtest">
 <div class="col-md-12">
              <div class="box no-border">
                <div class="box-header with-border">
                  <h3 class="capitalize">${test.testName}</h3>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">   
                  <br/>                     
                  <dl class="dl-horizontal">                             
                    <dt class="form-group"><spring:message code="lbl.timebond"/> (<spring:message code="lbl.minutes"/>) </dt>
                    <dd>
                    <c:choose>
                    <c:when test="${test.testTime!=null}">
                     ${test.timeMinute}
                      </c:when>
                     <c:otherwise>
                       <spring:message code="lbl.nolimit"/>
                     </c:otherwise>
                     </c:choose>
                    </dd>                    
                    <dt class="form-group"><spring:message code="lbl.numberofattempts"/></dt>
                    <dd>
                      <c:if test="${test.maxAttempts==0}">
                       <spring:message code="lbl.unlimited"/>
                      </c:if>
                      <c:if test="${test.maxAttempts!=0}">
                       ${test.maxAttempts}
                      </c:if>
                      </dd>
                      <dt class="form-group"><spring:message code="lbl.visibility"/></dt>
                     <dd>
                      <c:if test="${test.isPublic==0}">
                       <spring:message code="lbl.public"/>
                      </c:if>
                      <c:if test="${test.isPublic!=0}">
                       <spring:message code="lbl.private"/>
                      </c:if>
                      </dd>
                      <dt class="form-group"><spring:message code="lbl.enablereview"/></dt>
                     <dd>
                      <c:if test="${test.isReview==0}">
                      <spring:message code="lbl.no"/>
                      </c:if>
                      <c:if test="${test.isReview!=0}">
                       <spring:message code="lbl.yes"/> (<c:if test="${test.reviewWithCorrect!=0}">
                                          <spring:message code="lbl.withcorrectanswer"/></c:if>
                            <c:if test="${test.reviewWithCorrect==0}"><spring:message code="lbl.withoutcorrectanswer"/></c:if>)
                      </c:if>
                      </dd>                      
                      <dt class="form-group"><spring:message code="lbl.numberofquestions"/></dt>
                      <dd>${test.totalQuestion}</dd> 
                      <dt class="form-group"><spring:message code="lbl.totalmarks"/></dt>
                      <dd>${test.maxMark}</dd>
                      <dt class="form-group"><spring:message code="lbl.negativemarks"/></dt>
                      <dd>${test.negMark}%</dd>
                      <dt class="form-group"><spring:message code="lbl.pausepossible"/></dt>
                     <dd>
                      <c:if test="${test.testPause==0}">
                       <spring:message code="lbl.no"/>
                      </c:if>
                      <c:if test="${test.testPause!=0}">
                       <spring:message code="lbl.yes"/>
                      </c:if>
                      </dd>
                     <dt class="form-group"><spring:message code="lbl.shufflesections"/></dt>
                     <dd>
                      <c:if test="${test.shuffleSection==0}">
                       <spring:message code="lbl.no"/>
                      </c:if>
                      <c:if test="${test.shuffleSection!=0}">
                       <spring:message code="lbl.yes"/>
                      </c:if>
                      </dd>
                     <dt class="form-group"><spring:message code="lbl.shufflequestions"/></dt>
                     <dd>
                      <c:if test="${test.shuffleQuestion==0}">
                       <spring:message code="lbl.no"/>
                      </c:if>
                      <c:if test="${test.shuffleQuestion!=0}">
                        <spring:message code="lbl.yes"/>
                      </c:if>
                     </dd>
                     <dt class="form-group hide"><spring:message code="lbl.shuffleoptions"/></dt>
                     <dd class="hide">
                      <c:if test="${test.shuffleOption==0}">
                        <spring:message code="lbl.no"/>
                      </c:if>
                      <c:if test="${test.shuffleOption!=0}">
                       <spring:message code="lbl.yes"/>
                      </c:if>
                     </dd>
                  </dl>                  
                  <hr style="margin-top: 2px; margin-bottom: 2px;">                
                </div><!-- /.box-body -->                
                 <div class="box-body">
                  <dl>
                    <dt><spring:message code="lbl.instructions"/></dt>
                    <dd>${test.testInstruct}</dd>                 
                  </dl>
                </div>  
                <hr style="margin-top: 2px; margin-bottom: 2px;">    
                <div class="box-body">
                  <dl>
                    <dt><spring:message code="lbl.description"/></dt>
                    <dd>${test.testDesc}</dd>                 
                  </dl>
                </div>                
                <hr style="margin-top: 2px; margin-bottom: 2px;"> 
                <div class="box-body">
                  <dl>
                    <dt><spring:message code="lbl.tags"/></dt>
                    <dd>${test.testTag}</dd>                 
                  </dl>
                </div>
              </div><!-- /. box -->  
              <button class="btn pull-left btn-success btn-flat button-width-large" onclick="location.href='testList'">
              <spring:message code="lbl.back"/>
              </button> 
              <div class="pull-right">           
              <c:if test="${sessionScope.user.roleId!=4}">
                 <c:if test="${test.totalQuestion==0}">
                  <button class="btn btn-success btn-flat button-width-large" disabled="disabled">
                  <spring:message code="lbl.preview"/>
                  </button>
                 </c:if>
                 <c:if test="${test.totalQuestion!=0}">
                  <div class="btn btn-success btn-flat button-width-large"  onclick="window.open('testpreview?testId=${test.testId}', '', ' scrollbars=yes,menubar=no,width=500, resizable=yes,toolbar=no,location=no,status=no')"><span>
                  <spring:message code="lbl.preview"/>
                  </span></div>
                 </c:if>
                
              </c:if>
              &nbsp;&nbsp;&nbsp;&nbsp;
               <c:if test="${test.testPublishStatus==1}">
              <button class="btn pull-right btn-success btn-flat  button-width-large" disabled="disabled">
              <spring:message code="lbl.editassessment"/>
              </button>
               </c:if>
                <c:if test="${test.testPublishStatus==0}">
              <button class="btn pull-right btn-success btn-flat  button-width-large" onclick="location.href='addEditTest?testId=${test.testId}'">
              <spring:message code="lbl.editassessment"/>
              </button>
               </c:if>
               </div>
            </div><!-- /.col -->
            </div>       
    </section>
            
	</div><!-- content-wrapper -->
	</div>
	<!-- End dataTable----->
	<div class="control-sidebar-bg"></div>
    </div><!-- ./wrapper -->
	</body>
   <script>
   $(document).ready(function(){
	      $(".treeview").removeClass("active");
	      $("#test").addClass("active");
	      $("#test .treeview-menu > #test").addClass("active");
   });
    </script>
 </html>