/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    
    $(window).scroll(function () {
        var _t = $(document).scrollTop(),
             nav = $("#nav");

        if (_t > 10) {
            nav.addClass("navTheme");
        } else {
            nav.removeClass("navTheme");
        }
    });
    
	
	
	$('a[href^="#"]').on('click', function(event) {
    	var target = $(this.getAttribute('href'));
    	if( target.length ) {
       		event.preventDefault();
        	$('html, body').stop().animate({
            	scrollTop: target.offset().top
        	}, 1000);
    	}
	});
	
	
});

var showMoreFeature = function(){
	$("#features_section").toggle();
}

$( document ).ready(function() {
	clearInputText();
	
	});
    
/*    	function requestDemo(id){
    		clearInputText();
    		$("#loginDiv").hide();
    		$("#forgetPassword").hide();
    		$("#Modal2").hide();
    		$("#registerNewUser").hide();
    		if(id==0)
    			$("#Modal2").show();
    	}
    	
    	function login(id){
    		clearInputText();
    		$("#loginDiv").hide();
    		$("#forgetPassword").hide();
    		$("#Modal2").hide();
    		$("#registerNewUser").hide();
    		if(id==0)
    			$("#loginDiv").show();
    	}
    	
    	function forgetPassword(id){
    		clearInputText();
    		$("#loginDiv").hide();
    		$("#forgetPassword").hide();
    		$("#Modal2").hide();
    		$("#registerNewUser").hide();
    		if(id==0)
    			$("#forgetPassword").show();
    	}*/
    	
    	function demoValidate(){
  		  	var emailFilter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  		  	var flag=true;
  		  	if($.trim($("#demoname").val())==""){
  		  		$("#demoname").closest(".form-row").addClass("form-error");
  				$("#demonameerror").fadeIn();
  				$("#demoname").focus();
  				flag=false;
		  		}
  		  	else if($.trim($("#demoemail").val())==""){
  		  		$("#demoemail").closest(".form-row").addClass("form-error");
  				$("#demoemailerror").fadeIn();
  				$("#demoemail").focus();
  				flag=false;
	  		}else if(!emailFilter.test($("#demoemail").val())){
  			  	$("#demoemail").closest(".form-row").addClass("form-error");
  				$("#demoemailerror1").fadeIn();
  				$("#demoemail").focus();
  				flag=false;
  		  	}
  			return(flag);
  	  	}

		function demoCalValidate(){
  		  	if($.trim($("#demoname").val())!=""){
  		  		$("#demoname").closest(".form-row").removeClass("form-error");
  				$("#demonameerror").fadeOut();
		  		}
  		  	if($.trim($("#demoemail").val())!=""){
  		  		$("#demoemail").closest(".form-row").removeClass("form-error");
  				$("#demoemailerror").fadeOut();
  				$("#demoemailerror1").fadeOut();
	  		}
  	  	}
		
		function loginValidate(){
  		  	var emailFilter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  		  	if($.trim($("#loginsubdomain").val())==""){
  		  	    $("#loginsubdomain").closest(".form-row").addClass("form-error");
  				$("#loginsubdomainerror").fadeIn();
                $("#loginsubdomain").focus();
                return false;
		  		}
  		  	if($.trim($("#loginemail").val())==""){
  		  		$("#loginemail").closest(".form-row").addClass("form-error");
  				$("#loginemailerror").fadeIn();
  				$("#loginemail").focus();
  				return false;
	  		}
  		  	if(!emailFilter.test($("#loginemail").val())){
  			  	$("#loginemail").closest(".form-row").addClass("form-error");
  				$("#loginemailerror1").fadeIn();
  				$("#loginemail").focus();
  				return false;
  		  	}
  		  	if($.trim($("#loginpassword").val())==""){
  		  		$("#loginpassword").closest(".form-row").addClass("form-error");
  				$("#loginpwderror").fadeIn();
  		  	    $("#loginpassword").focus();
  				return false;
	  		}/* else if(!emailFilter.test($("#loginpassword").val())){
  			  	$("#loginpassword").css("border-color","#c95b5b");
  				$("#loginpwderror1").fadeIn();
  				flag=false;
  		  	} */
  		  	return true;
  	  	}
		
		function submitLoginForm(){
  			if(loginValidate()){
  				$("#overlay").show();
  				$.ajax({
  				    url:"logincheck",
  				    data:{'loginsubdomain' : $.trim($("#loginsubdomain").val()), 'loginemail' : $.trim($("#loginemail").val()), 'loginpassword' : $.trim($("#loginpassword").val())},
  				    type: "POST",
  				    success:function(str){
  				    	if(str=="0"){
  	  				    	$("#overlay").hide();
  	  				        $("#licenceerror").hide();
  				    		$("#verifyerror").addClass('hide');
  				    		$("#loginerror").fadeIn();
  				    	}else if(str=="1"){
  	  				    	$("#overlay").hide();
  	  				        $("#licenceerror").hide();
  				    		$("#loginerror").fadeOut();
  				    		$("#verifyerror").removeClass('hide');
  				    		$("#mailid").val($.trim($("#loginemail").val()));
  				    	}else if(str=="3"){
  	  				    	$("#overlay").hide();
  	  				        $("#loginerror").fadeOut();
  				    		$("#licenceerror").show();
  				    		$("#mailid").val($.trim($("#loginemail").val()));
  				    	}else{
  				    		var loc = getAbsolutePath();
  							loc = loc.replace(window.location.hostname, $.trim($("#loginsubdomain").val())
  									+ "." + domainName);
  							location.replace(loc + "authuseratrequestdemo?loginemail="+encodeURIComponent($.trim($("#loginemail").val())));
  				    		//location.replace("//"+$("#loginsubdomain").val()+"."+window.location.hostname+"/authuseratrequestdemo?loginemail="+encodeURIComponent($.trim($("#loginemail").val())));
  				    	}
  				    }
					});
  			}
		}
		
		
		function loginCalValidate(){
  		  	if($.trim($("#loginsubdomain").val())!=""){
  		  		$("#loginsubdomain").closest(".form-row").removeClass("form-error");
  				$("#loginsubdomainerror").fadeOut();
		  		}
  		  	if($.trim($("#loginemail").val())!=""){
  		  		$("#loginemail").closest(".form-row").removeClass("form-error");
  				$("#loginemailerror").fadeOut();
  				$("#loginemailerror1").fadeOut();
	  		}
  		  	if($.trim($("#loginpassword").val())!=""){
  		  		$("#loginpassword").closest(".form-row").removeClass("form-error");
  				$("#loginpwderror").fadeOut();
  				$("#loginpwderror1").fadeOut();
	  		}
  	  	}
		
    	function forgetPassDataValidate(){
  		  	var emailFilter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  		  	var flag=true;
  		  	if($.trim($("#forgetpasssubdomain").val())==""){
  		  		$("#forgetpasssubdomain").closest(".form-row").addClass("form-error");
  				$("#forgetpasssubdomainerror1").fadeIn();
  				$("#forgetpasssubdomain").focus();
  				flag=false;
		  		}
  		  	else if($.trim($("#forgetpassemail").val())==""){
  		  		$("#forgetpassemail").closest(".form-row").addClass("form-error");
  				$("#forgetpassemailerror1").fadeIn();
  				$("#forgetpassemail").focus();
  				flag=false;
	  		}else if(!emailFilter.test($("#forgetpassemail").val())){
  			  	$("#forgetpassemail").closest(".form-row").addClass("form-error");
  				$("#forgetpassemailerror2").fadeIn();
  				$("#forgetpassemail").focus();
  				flag=false;
  		  	}
  			return(flag);
  	  	}
    	
    	function forgetPassKeyValidate(){
  		  	if($.trim($("#forgetpasssubdomain").val()!="")){
  		  		$("#forgetpasssubdomain").closest(".form-row").removeClass("form-error");
  				$("#forgetpasssubdomainerror1").fadeOut();
  				$("#forgetpasssubdomainerror2").fadeOut();
		  		}
  		  	if($.trim($("#forgetpassemail").val())!=""){
  		  		$("#forgetpassemail").closest(".form-row").removeClass("form-error");
  				$("#forgetpassemailerror1").fadeOut();
  				$("#forgetpassemailerror2").fadeOut();
  				$("#forgetpassemailerror3").fadeOut();
  		  	}
  	  	}
    	
		function resetPassword(){
			if(forgetPassDataValidate()){
				$.ajax({
					url:"checkEmailWithOrg",
					type:"GET",
					data: {'subdomain' : $.trim($("#forgetpasssubdomain").val()), 'email' : $.trim($("#forgetpassemail").val())},
					error : function(data){},
					success : function(data){
						if(!data.subdomain){
							$("#forgetpasssubdomain").closest(".form-row").addClass("form-error");
							$("#forgetpasssubdomainerror2").fadeIn();
							$("#forgetpasssubdomain").focus();
						}
						else if (!data.email){
							$("#forgetpassemail").closest(".form-row").addClass("form-error");
							$("#forgetpassemailerror3").fadeIn();
							$("#forgetpassemail").focus();
						}
						else if(data.passLink){
							$("#loginForm").show();
							$("#forgetpwdform").hide();
				    		$("#passwordResetAlert p").text(messages['msg.success.password.link']);
							$("#passwordResetAlert").modal('show');
							setTimeout(function() {$('#passwordResetAlert').modal('hide');}, 4000);
						}
					}
				});
			}
		}
		
		var resendverificationlink = function(){
			
			var email=$("#mailid").val();
			if(email==""){									
			}else{
				$("#overlay").show();
				$.ajax({
		    		  url : "resendVerifyEmailfromHome?email="+email,
						type : 'POST',
						//async : false,		
						error : (function() {
							$("#overlay").hide();
							alert("server error");
						}),
						success : function(data) {
							$("#overlay").hide();
							if(data){						
								$(".callout").addClass('hide');
								$("#loginDiv").hide();
								$("#verifyerror").addClass('hide');
								$("#passwordResetAlert p").text(messages['msg.success.verification.mail']);
								$("#passwordResetAlert").modal('show') ;
								setTimeout(function() {$('#passwordResetAlert').modal('hide');}, 4000);
							}
						}
		  	  });					
			}
	    	  
	      }
	  	
	  	var clearInputText=function(){
	  		$("#loginsubdomain").val('');
	  		$("#loginemail").val('');
	  		$("#loginpassword").val('');
	  		$("#demoorganization").val('');
	  		$("#demoname").val('');
	  		$("#demoemail").val('');
	  		$("#democomment").val('');
	  		$("#forgetpasssubdomain").val('');
	  		$("#forgetpassemail").val('');
	  		$("#compLoginPwd").val('');
	  		$("#compLoginPwd2").val('');
	  		$("#email").val('');
	  		$("#orgName").val('');
	  		$("#subdomain").val('');
	  	}
	  	
	  	var showloginPage = function(){
	  		$("#registerNewUser").hide();
	  		$("#loginDiv").show();
	  	}
	  	
	  	var requestADemo = function(){
	  		if(demoValidate()){
		  		var data = {
		  				"name" : $("#demoname").val(),	  		
		  				"email": $("#demoemail").val(),
		  				"orgName" : $("#demoorganization").val(),
		  				"about" : $("#democomment").val(),
		  		};
	  			$.ajax({
		    		  url : "sendDemoRequest",
						type : 'POST',						
						data : data,
						datatype:"String",
						error : (function() {
							alert("server error");
						}),
						success : function(data) {
							if(data){
								$("#homeAlert p").text(messages['msg.success.requestdemo']);
								 $("#homeAlert").modal('show');
								 setTimeout(function() {
								 location.href="requestatdemo";
								 }, 4000);
							}
						}
	  			});	  			
	  		}	  		
	  	}
	  	function forgetPasswordV2(id){
    		clearInputText();    		
    		$("#loginForm").hide();    		
    		$("#forgetpwdform").show();    		
    	}