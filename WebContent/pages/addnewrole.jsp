<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>	
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>
.body-color {
background-color:#F0F0F0;
}
.color-mainblue{
color:#05B26F;
}
.button-color-blue{
background-color:white;
color:#4B5960;
border:0.1em solid #4B5960;
}
.button-green-color{
border-width: 0.1em;
color:white;
border:1px solid #05B26F;
background-color:#05B26F;
}

.bottom-border{
border:1px solid #F0F0F0;
margin:0px;
height:100%
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
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position:fixed;left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>        
		<!-- start dataTable----->
		<div class="col-sm-12" >
		<div class="content-wrapper">
			<section class="content-header">
		<h3 style="margin:0">
				<spring:message code="lbl.addnewrole" text="Add New Role"/>
				</h3>
			</section>
			<section class="content">
			     <div class="col-lg-10 form-group">
	                    <label>
	                        <sup><font color="red" size="3px">*</font></sup>
	                        <spring:message code="lbl.rolename" text="Role Name"/>
	                    </label>
						<input type="text" name="roleName" id="roleName" class="form-control"  value="" maxlength="50" onkeyup="catValidate()">
					    <label class="requireFld" id="roleNameError"><spring:message code="msg.empty" text="This Field is required."/></label>
					    <label class="requireFld" id="roleNameError1"><spring:message code="msg.maxlength50" text="Maximum 50 characters allowed."/></label> 
				  </div>
				
				  <div class="col-lg-10 form-group">
	                    <label>
	                        <sup><font color="red" size="3px">*</font></sup>
	                        <spring:message code="lbl.roledescription" text="Role Description"/>
	                    </label>
						<textarea name="roleDesc" id="roleDesc" class="form-control textAreaControl" maxlength="50" onkeyup="catValidate()"></textarea>
					    <label class="requireFld" id="roleDescError"><spring:message code="msg.empty" text="This Field is required."/></label>
					    <label class="requireFld" id="roleDescError1"><spring:message code="msg.maxlength50" text="Maximum 50 characters allowed."/></label> 
				  </div>				
				<div class="col-lg-10 form-group">
						<label >
							<sup><font color="red" size="3px">*</font></sup>
							<spring:message code="lbl.applicationfunction" text="Application Function"/>
					   </label>		
					   <c:set var="count" value="0"></c:set>		
					   <div id="appFunction" class="form-control form-group"  style="overflow: auto; height: 30%">
								<ul class="checkbox-grid">	
								<c:forEach items="${appfunctions}" var="rolefunction">
								<li class="form-group">
								          <span><spring:message code="${rolefunction.functionName}"/></span>
							             <input type="hidden" id="functionId${count}" value="${rolefunction.functionId}"/>
							             <br>
							              <input type="hidden" value="${fn:length(rolefunction.subAppFuntion)}" id="subfunctionId${count}">
							              <c:set var="count1" value="1"></c:set>
							               <c:forEach items="${rolefunction.subAppFuntion}" var="rolesubfunction">
															   
																 <input type="checkbox"  class="selectedEmail" id="${rolefunction.functionId}${count1}"
																	value="${rolesubfunction.subFunctionId}"/>
																<label for="${rolefunction.functionId}${count1}">
																	<spring:message code="${rolesubfunction.subFunctionName}"/>
																</label>&nbsp;&nbsp;&nbsp;
																<c:set var="count1" value="${count1+1}"></c:set>	
											</c:forEach> 
										<c:set var="count" value="${count+1}"></c:set>		
								</li>					
								</c:forEach>							
								</ul>	
								<input type="hidden" id="size" value="${fn:length(appfunctions)}">	
					   </div>
					   <label class="requireFld" id="appFunctionError"><spring:message code="msg.oneappfunction.required" text="Please select at least one application function."/></label>
				</div>									
						<div class="col-sm-10 form-group">
									<button type="submit" id="savebutton" onclick="saveRole()" class="btn btn-success btn-flat pull-left button-width-large">
										<spring:message code="lbl.save" text="Save"/>
									</button>
									<button type="button" id="cancelbutton" class="btn pull-right btn-flat btn-default add-Right-Margin button-width-large" onclick="backButtonPage()">
										<spring:message code="lbl.cancel" text="Cancel"/>
									</button>
									<%User user=(User)session.getAttribute("userSession"); %>
									<input type="hidden" id="userId" value="<%=user.getUserId() %>">
			</div>					
			</section>
			<!-- Footer Button -->
								
			</div>
		
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->


</body>

<script type="text/javascript">
/**
 * Method to save Role details.
 */
 var flag=false;
 $( document ).ready(function() {
	 $(".treeview").removeClass("active");
     $("#rolemanagement").addClass("active");
     $("#rolemanagement .treeview-menu > #rolemanagement").addClass("active");
	 $('input[type="checkbox"].selectedEmail, input[type="radio"].selectedEmail').iCheck({
	     checkboxClass: 'icheckbox_square-green',
	        radioClass: 'iradio_square-green'
	   });
    /**
		 * @summary This is used for showing the data lost warning when user wants leave the page.
		 */
		$(window).on('beforeunload',
						function() {
							return '<spring:message code="msg.datalostwarning"/>';
						});
	 $(document).on('ifClicked', '.selectedEmail', function() {
		 if(flag){
				$("#appFunction").css("border-color","#7ac17d"); 
			  	$("#appFunctionError").fadeOut();
			  	flag=false;
			}
	 });
});
function saveRole(){
	var allVals =new Array();
	/*Get All function Id */
	for(var appIndex=0;appIndex<$("#size").val();appIndex++){
	    var subfunctionId=new Array();/*SubFunction Array for every function*/
		for(var functionIndex=1;functionIndex<=$("#subfunctionId"+appIndex).val();functionIndex++){
			if($('#'+$("#functionId"+appIndex).val()+""+functionIndex).is(":checked")){/* Check subfunction is checked Or Not*/
				subfunctionId.push({subFunctionId:parseInt($("#"+$("#functionId"+appIndex).val()+""+functionIndex).val())});/*Add subfunction Id */
			}	
		}
		if(subfunctionId.length>0){
			allVals.push({functionId:parseInt($("#functionId"+appIndex).val()),subAppFuntion:subfunctionId})
		}			
	}
	if(allVals.length>0){
		var data = JSON.stringify({
			roleName  			: $('#roleName').val(),
			roleDesc        	: $('#roleDesc').val(),
			appfunctions   	: allVals,
			roleCreatorId:$("#userId").val()
		});
		if(validate()){
			$.ajax({
	       		   url:"addrole",
	       		   type:'POST',
	       		   dataType : 'json',
	    		   contentType : "application/json",
	       		   data : data,
	       		   error : function(response){
	       			//location.href="rolelist";
	       		   },
	       		   success : function(response){
                    $(window).off('beforeunload');
	       			location.href="rolelist";	
	       		   }
       		});
		}
	}else{
		$("#appFunction").css("border-color","#c95b5b"); 
	  	$("#appFunctionError").fadeIn();
	  	flag=true;
	}
}
function validate(){
	if($("#roleName").val()==""){
	      $("#roleName").css("border-color","#c95b5b"); 
		  $("#roleNameError").fadeIn();
	    return false;	
	}
	if($("#roleDesc").val().trim()==""){
	      $("#roleDesc").css("border-color","#c95b5b"); 
		  $("#roleDescError").fadeIn();
	    return false;	
	}
  return true;	
}
function catValidate(){	
	if(($("#roleName").val()!='undefined')&($("#roleName").val().trim()).length>0){
	      $("#roleName").css("border-color","#7ac17d");; 
		  $("#roleNameError").fadeOut();
	}
	if(($("#roleDesc").val()!='undefined')&($("#roleDesc").val().trim()).length>0){
	      $("#roleDesc").css("border-color","#7ac17d");
		  $("#roleDescError").fadeOut();
	}	
}

function backButtonPage(){
	location.href="rolelist";
}
</script>
</html>