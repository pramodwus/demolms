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
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<div class="col-sm-12">
			<div class="content-wrapper">
				<section class="content-header">
					<div class="pull-left">
						<h3 style="margin: 0"><spring:message code="lbl.createnotification" text="Create Notification"/></h3>
					</div>
					<br />
				</section>
				<section class="content">
					<div class="row">
						<div class="col-xs-12">
							<div class="box no-border">
								<div class="box-body">
									<div id="overlay" class="overlay1"
										style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
										<img id="loading" class="lazy"
											src="<spring:url value='/resources/images/loading.gif'/>"
											style="position: fixed; left: 50%; top: 50%;">
									</div>
									<div class="col-sm-12 form-group" id="lbluser" >
										        <label for="multiusers"><spring:message code="lbl.selectusers" text="Select Users"/></label>	
										        <div class="form-group">											
												 <select class="form-control select2"
													id="multiusers" multiple="multiple"
													data-placeholder="<spring:message code="lbl.selectuser" text="Select User"/>" style="width: 100%;">
													<c:forEach items="${users}" var="users">
														<c:if test="${users.firstName!=null}">
															<option value="${users.userId}">
																${users.userId}&nbsp;${users.firstName}&nbsp;${users.lastName}
															</option>
														</c:if>
														<c:if test="${users.firstName==null}">
															<option value="${users.userId}">${users.userId}&nbsp;
															<spring:message code="lbl.guestuser" text="Guest User"/>
															</option>
														</c:if>
													</c:forEach>
												</select>
												</div>
									<label><input type="checkbox" id="checkBox12"></label>&nbsp;<spring:message code="lbl.selectall" text="Select All"/>											
									</div>
									<div class="col-sm-12 form-group" id="textmsg" style="padding-right:15px;">
										<label><spring:message code="lbl.message" text="Message"/> </label>										
											<textarea class="form-control textAreaControl" id="notificationMessage"
												name="notificationMessage"></textarea>										
									</div>
                                    
                                    <div class="col-sm-12 form-group">
                                      <label><spring:message code="lbl.uploadimage" text="Upload Image"/></label>
                                    </div>                                    
									<div id="image" class="col-sm-12 form-group">
											<img
												onError="this.src='<spring:url value='/resources/images/noimage.jpeg' />';"
												src="src" width=100, height="100" id="previewimg"
												name="previewimg" /> &nbsp;&nbsp;&nbsp;&nbsp;<label><input
												type="file" id="uploadimg" name="uploadimg"
												onchange="Validate(this);" /></label>
									</div>
								</div>

								<div class="box-footer ">
									<div class="pull-right">
										<button type="button" id="save"
											class="btn btn-success btn-flat "><i class="fa fa-send"></i> <spring:message code="lbl.send" text="Send"/></button>
										<button type="button" id="cancel"
											class="btn btn-default btn-flat"><spring:message code="lbl.cancel" text="Cancel"/></button>
									</div>
								</div>

							</div>
							<!-- /.box -->

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
<script type="text/javascript">
        var messages = new Array();
        messages['msg.images.size150'] = "<spring:message code='msg.images.size150' text='Maximum Image size is 150KB.' javaScriptEscape='true'/>";
        messages['msg.allowedimageextension'] = "<spring:message code='msg.allowedimageextension' text='Invalid image type, allowed extensions are: #filetype' arguments='#filetype' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
        messages['msg.notificationmsg.required']= "<spring:message code='msg.notificationmsg.required' text='Notification message is mandatory.' javaScriptEscape='true'/>"
        messages['msg.browserdoesnotsupporthtml5'] = "<spring:message code='msg.browserdoesnotsupporthtml5' text='This browser does not support HTML5.' javaScriptEscape='true'/>";
        messages['msg.selectminoneuser'] = "<spring:message code='msg.selectminoneuser' text='Please select at least one user.' javaScriptEscape='true'/>"
        </script>
<!-- Select2 -->
<script src="resources/adminlte/plugins/select2/select2.full.min.js"></script>
<script type="text/javascript">
$(function () {	
	$(".treeview").removeClass("active");
	$("#notification").addClass("active");
	$("#notification .treeview-menu > #notification").addClass("active");
	 /* $('input').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%' // optional
	});  */
	 
	$(".select2").select2();
	
	$(document).on('change','.select2',function(){
			$('#checkBox12').iCheck('uncheck');
	});
	$('#checkBox12').iCheck({
        checkboxClass: 'icheckbox_square-green'
      });
	    /* $("#checkBox12").click(function(){	 */
	    	$(document).on('ifClicked', '#checkBox12', function() {
		     if($("#checkBox12").is(':checked') ){
		    	 $("#multiusers > option").removeAttr("selected");
		         $("#multiusers").trigger("change");
		        
		    }else{		    	
		    	$("#multiusers > option").prop("selected","selected");
		        $("#multiusers").trigger("change");
		     } 
		}); 	
	 
	  
	  $(".notificationMessage").val('');
	  $(".multiusers").val('');
	  $("#uploadimg").val("");
	  
	  $('#save').click(
			 
				function() {
									
					var formdata = new FormData();
					formdata.append("categimg",
							$("#uploadimg").prop("files")[0]);					
					console.log($('.select2').select2("val"));
					
					console.log($('#notificationMessage').val());
					var blob = new Blob([ JSON.stringify({
						users : $('.select2').select2("val"),
						notificationMessage : $("#notificationMessage").val()
					}) ], {
						type : 'application/json'
					});
					formdata.append("categ", blob);
					 if(validateForm()){
						 $("#overlay").show();	
						 $.ajax({
								url : 'sendNotificationCreatedByAdmin',						
								method : 'POST',
								data : formdata,
								processData : false,
								contentType : false,
								error:function(response, status, xhr)
						        {
						        	if(response.status == 403)
						        	location.href = "logout";
						        }, 
								success : function(response, status, xhr)
								{     $("#overlay").hide();
									  location.href="dashboardcontroller";
								}
							});
					 }
					 
				});
	  
	  $('#cancel').click(function() {
		  location.href="dashboardcontroller";
        });
	  	 		
});


/**
 *Display Image 
 */
var displayimg = function() {
	if (checkFileSize()) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#previewimg').attr('src', e.target.result);
		}, reader.readAsDataURL($("#uploadimg").prop("files")[0]);
	} else {
		$("#uploadimg").val("");
	}
};
/**
 * Check file Size 
 **/
function checkFileSize() {
	$("#image").css({"border-color" : ""});	
	$("#image").next('span').remove();
	var reader = new FileReader();
	reader.readAsDataURL($("#uploadimg").prop("files")[0]);
	if (typeof ($("#uploadimg")[0].files) != "undefined") {
		var size = parseFloat($("#uploadimg")[0].files[0].size / 1024)
				.toFixed(2);
		if (size < 150) {
			return true;
		} else {
			$('#image').css({"border-color" : "red"});
			$('#image').after('<span class="text-red">'+ messages['msg.images.size150'] +'</span>');			
			return false;
		}
	} else {
		alert(messages['msg.browserdoesnotsupporthtml5']);
		return false;
	}
}


var _validFileExtensions = [ ".jpg", ".jpeg", ".bmp", ".gif", ".png" ];

/**
 * function for validate image type 
 **/
function Validate(oInput) {
	$("#image").css({"border-color" : ""});	
	$("#image").next('span').remove();
	if (oInput.type == "file") {
		var sFileName = oInput.value;
		if (sFileName.length > 0) {
			var blnValid = false;
			for (var j = 0; j < _validFileExtensions.length; j++) {
				var sCurExtension = _validFileExtensions[j];
				if (sFileName.substr(
						sFileName.length - sCurExtension.length,
						sCurExtension.length).toLowerCase() == sCurExtension
						.toLowerCase()) {
					blnValid = true;
					break;
				}
			}

			if (!blnValid) {				
				$('#image').css({"border-color" : "red"});
				$('#image').after('<span class="text-red">'+messages['msg.allowedimageextension'].replace('#filetype', _validFileExtensions.join(", "))+'</span>');
				oInput.value = "";
				return false;
			} else {
				displayimg();
			}
		}
	}
	return true;
}


/**
 * function for validate form 
 **/
var validateForm = function(){
	var flag = 0;
	$("#lbluser").css({"border-color" : ""});	
	$("#lbluser").next('span').remove();
	$("#textmsg").css({"border-color" : ""});	
	$("#textmsg").next('span').remove();
	
	if($('.select2').select2("val")==null){
		$('#lbluser').css({"border-color" : "red"});
		$('#lbluser').after('<span class="text-red">'+messages['msg.selectminoneuser']+'</span>');
		return false;
	}
	
	if($('#notificationMessage').val()==""){
		$('#textmsg').css({"border-color" : "red"});
		$('#textmsg').after('<span class="text-red">'+messages['msg.notificationmsg.required']+'</span>');
		return false;
	}
	
		return true;
}


</script>
</html>