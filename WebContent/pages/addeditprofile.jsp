<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
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
.daterangepicker td.active, .daterangepicker td.active:hover {
background-color:#00B06C;
}
.input-group-addon {
    border: 1px solid #ccc !important;
}
.input-group-addon:last-child {
    border-left: 0 !important;
}
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-10">
			<div class="content-wrapper">
			<section class="content">
			<div class="row" style="text-align:center">
			<div class="col-md-3">
              <!-- Profile Image -->
              <div class="box box-success">
                <div class="box-body box-profile">
                  <img   class="profile-user-img img-responsive img-circle" id="imgpreview"
                   onerror="this.src='resources/images/icon-admin.png'" src="${profile.image}">
                   <span id="editimg" class="pull-right"><i class="fa fa-lg fa-upload"></i></span>                   
                  <h3 class="profile-username text-center">${profile.firstName} ${profile.lastName}</h3>
                  <p class="text-muted text-center"></p>
                </div><!-- /.box-body -->
              </div><!-- /.box -->       
            </div>
			<div class="col-sm-9">
			              <!-- Horizontal Form -->
              <div class="box no-border">
                <div class="box-header no-border">
                  <h2 class="box-title"><spring:message code="lbl.userprofile" text="User Profile"/></h2>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form:form class="form-horizontal" action="updateProfile" commandName="profile" id="profileForm" name="profileForm" enctype="multipart/form-data"  onsubmit="return validateProfileData()">
                  <div class="box-body">
                  <div class="form-group">
                      <label for="firstName" class="col-sm-3 control-label"><spring:message code="lbl.firstname" text="First Name"/></label>
                      <div class="col-sm-9">
                        <spring:message code="lbl.firstname" text="First Name" var="firstname"/>
                        <form:input type="text" class="form-control" id="firstName" name="firstName" maxlength="64" placeholder="${firstname}" path="firstName" onkeyup="keyProfileData()"/>
                       <p class="requireFld" style="text-align:left" id="firstNameError"><spring:message code="msg.empty" text="This field is required."/></p>
                      </div>
                      
                    </div>
                    <div class="form-group">
                      <label for="lastName" class="col-sm-3 control-label"><spring:message code="lbl.lastname" text="Last Name"/></label>
                      <div class="col-sm-9">
                        <spring:message code="lbl.lastname" text="Last Name" var="lastname"/>
                        <form:input type="text" class="form-control" id="lastName" name="lastName" maxlength="64" placeholder="${lastname}" path="lastName"/>
                      </div>
                    </div>
                     <div class="form-group">
                      <label for="mobile" class="col-sm-3 control-label"><spring:message code="lbl.mobilenumber" text="Mobile Number"/></label>
                      <div class="col-sm-9">
                        <spring:message code="lbl.mobilenumber" text="Mobile Number" var="mobilenumber"/>
                        <form:input type="text" class="form-control" id="mobile" name="mobile" placeholder="${mobilenumber}" maxlength="10" path="mobile" onkeyup="keyProfileData()"/>
                        <p class="requireFld" style="text-align:left" id="mobileError"><spring:message code="msg.empty" text="This field is required."/></p>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="about" class="col-sm-3 control-label"><spring:message code="lbl.bio" text="Bio"/></label>
                      <div class="col-sm-9">
                      <form:textarea class="form-control textAreaControl" id="about" name="about" path="about" maxlength="1024"></form:textarea>
                      </div>
                    </div>
                     <div class="form-group">
                      <label for="dob" class="col-sm-3 control-label"><spring:message code="lbl.dob" text="DOB"/></label>
                      <div class="col-sm-9 ">
                      <div class="input-group">
                        <spring:message code="lbl.dateofbirth" text="Date of Birth" var="dateofbirth"/>
                        <form:input type="text" class="form-control" id="dob" name="dob" path="dob" placeholder="${dateofbirth}"/>
                        <div class="input-group-addon">
						<i class="fa fa-calendar"></i>
						</div>
						</div>
                        <form:input type="file" class="form-control hide" name="image"  path="image"  onchange="showMyImage(this)"/>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="systemLanguage" class="col-sm-3 control-label"><spring:message code="lbl.systemlanguage" text="System Language"/></label>
                      <div class="col-sm-9">
                      <spring:message code="systemlanguge.english" text="English" var="english"/>
                      <spring:message code="systemlanguge.hindi" text="Hindi" var="hindi"/>
                      <form:select id="systemLanguage" name="systemLanguage"
								class="form-control" path="systemLanguage">
								<form:option value="en" label="${english}"/>
                                <form:option value="hi" label="${hindi}"/>
							</form:select>
                      </div>
                    </div>
                  </div><!-- /.box-body -->
                  <div class="box-footer">
                    <button type="button" class="btn btn-default pull-left btn-flat button-width-large" onclick="location.href='${backlocation}'"><spring:message code="lbl.cancel" text="Cancel"/></button>
                    <button type="submit" class="btn btn-success pull-right btn-flat button-width-large"><spring:message code="lbl.updateprofile" text="Update Profile"/></button>
                  </div><!-- /.box-footer -->
                </form:form>
              </div><!-- /.box -->
			</div>
			</div>
			</section>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['msg.images.required'] = "<spring:message code='msg.images.required' text='Only images are supported.' javaScriptEscape='true' />";
        messages['msg.maximgsize'] = "<spring:message code='msg.maximgsize' text='Maximum image size is #maxsizeKB.' arguments='#maxsize;' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        </script>
<script>
var maxDate =  '<%
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
	out.print(dateFormat.format(new Date()));
	%>';
$(document).ready(function(){
	  $('#dob').daterangepicker({
		  locale: {
		    	  "format": "YYYY-MM-DD",
		          "separator": " - ",
		          "applyLabel": paggingmessages['lbl.apply'],
		          "cancelLabel": paggingmessages['lbl.cancel'],
		          "fromLabel": paggingmessages['lbl.from'],
		          "toLabel": paggingmessages['lbl.to'],
		          "customRangeLabel": "Custom",
		          "daysOfWeek": [
		              paggingmessages['lbl.sunday'],
		              paggingmessages['lbl.monday'],
		              paggingmessages['lbl.tuesday'],
		              paggingmessages['lbl.wednesday'],
		              paggingmessages['lbl.thursday'],
		              paggingmessages['lbl.friday'],
		              paggingmessages['lbl.saturday']
		          ],
		          "monthNames": [
		              paggingmessages['lbl.jan'],
		              paggingmessages['lbl.feb'],
		              paggingmessages['lbl.mar'],
		              paggingmessages['lbl.apr'],
		              paggingmessages['lbl.may'],
		              paggingmessages['lbl.jun'],
		              paggingmessages['lbl.jul'],
		              paggingmessages['lbl.aug'],
		              paggingmessages['lbl.sep'],
		              paggingmessages['lbl.oct'],
		              paggingmessages['lbl.nov'],
		              paggingmessages['lbl.dec']
		          ],
		    },
	        "singleDatePicker": true,
	        "showDropdowns": true,
	        "showWeekNumbers": false,
	        "timePicker": false,
	        "maxDate": maxDate,
	        "drops": "up"
	    });
	$("#editimg").click(function() {
		$("#image").click();
	})
});

/**
 * @summary function for showing image preview after selecting image as course icon. 
 * @param fileInput
 */
function showMyImage(fileInput) {		
	var files = fileInput.files;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		var imageType = /image.*/;
		var size = parseFloat(files[i].size / 1024)
		.toFixed(2);
		if(!file.type.match(imageType))
		{
		 $("#image").val("");
		 $("#imgpreview").css("border-color", "#c95b5b");
		 $('#editimg').after("<span class='text-red'>"+messages['msg.images.required']+"</span>");
		}
		else if(size>150){
			$("#image").val("");
			$("#imgpreview").css("border-color", "#c95b5b");
			$('#editimg').after("<span class='text-red'>"+messages['msg.maximgsize'].replace('#maxsize',150)+"</span>");
		}
		else{
		if (!file.type.match(imageType)) {			
			continue;
		}
		var img = document.getElementById("imgpreview");
		img.file = file;
		
		var reader = new FileReader();
		reader.onload = (function(aImg) {
			return function(e) {
				aImg.src = e.target.result;
			};
		})(img);
		reader.readAsDataURL(file);
	}
	}
}

/**
 * @summary This is used validate the form data.
 * @returns {Boolean}
 */
 function validateProfileData(){
	 if(document.profileForm.firstName.value.trim()==""){
		 $("#firstName").css("border-color","#c95b5b");
		 $("#firstNameError").fadeIn();
		return false;
	}
	if(document.profileForm.mobile.value.trim()==""){
		$("#mobile").css("border-color","#c95b5b");
		 $("#mobileError").fadeIn();
		return false;
	} 
	return true;
  }
  
 /**
  * @summary This is used validate the form data.
  */
 function keyProfileData(){
 	 if(document.profileForm.firstName.value.trim().length>0){
 		 $("#firstName").css("border-color","");
 		 $("#firstNameError").fadeOut();
 	}
    if(document.profileForm.mobile.value.trim().length>0){
    	 $("#mobile").css("border-color","");
		 $("#mobileError").fadeOut();
	}
   }
</script>
</html>