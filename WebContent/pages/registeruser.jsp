<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>QBis: Smart Learning Platform</title>
<meta charset="utf-8">
<meta name="description" content="QBis is Smart Learning Platform (SLP) that can be used by enterprises and educational organizations to train internal as well as external audience. It allows you to create multimedia content with powerful assessment that supports several different types of questions such as essay type, objective, match the following etc."/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="<spring:url value='/resources/images/qbisfavicon.png' />">
<link rel="canonical" href="http://www.bellurbis.com/qbis.html">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/font-awesome-4.4.0/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/ionicons-2.0.1/css/ionicons.min.css'/>">
<link rel="stylesheet"	href="resources/adminlte/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/extra.css?v=1'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css?v=1'/>">
<style>
span {
	font-family: 'Century Gothic' !important;
}

.system_lan_desc{
font-family: 'Century Gothic' !important;
}
</style>
</head>
<body class="signup--body signup-v3--body">
	<div class="page">
<div id="overlay" class="signup-form-overlay">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>">
		</div>
 <nav class="navbar navbar-inverse navbar-fixed-top navTheme">
                <div class="container sp__width">
                <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                        </button>
                    <a class="navbar-brand" href="#myCarousel">
                            <img src="resources/adminlte/dist/img/imageshome/logo.png" height="50">
                        </a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav navbar-right">
                                <li><a href="/"><spring:message code="lbl.home"/></a></li>
                                <li><a href="homefeatures"><spring:message code="lbl.features"/></a></li>   		
                                <li><a  href="#reguser" class="nav__link"><span class="btn btn-primary"><spring:message code="lbl.singup"/></span></a></li>
                                <li><a  href="loginathome"><spring:message code="lbl.login"/></a></li>
                                <li><a href="requestatdemo"><spring:message code="lbl.req.demo"/></a></li>
                        </ul>
                </div>
                </div>
        </nav>

        <div class="signup-form-main-div">
        <header class="signup-header">
        <div class="wrapper">
        <h1 class="qbis-signup-title signup-header-title-with-selected-licence">Start your 14 day free trial</h1>
        </div>
        </header>
<form name="myForm" id="myForm" class="js-signup-form">
        <section class="signup-main">
        <input type="password" class="form-filed-hide">
  <div class="wrapper signup-form">
    <fieldset>
      <legend>Your info</legend>
      
      <div class="form-row">
        <label for="email">Email address</label>
        <div class="form-field">
          <input id="email" name="email"  class="form-control input-lg" placeholder="<spring:message code="placeholder.email"/>" onkeyup="catValidate();">
          
        </div>
        					<p class="requireFld error-msg validation--error" id="emailError"><spring:message code="msg.empty"/></p> 
							<p class="requireFld error-msg validation--error" id="emailError1"><spring:message code="msg.email.invalid"/></p>
							<p class="requireFld error-msg validation--error" id="emailError2"><spring:message code="msg.email.already.exist"/></p>
      </div>
       
      <div class="form-row ">
        <label for="password">Password</label>
        <div class="form-field">
          <input type="password" class="form-control input-lg tooltip-field"
								name="compLoginPwd" id="compLoginPwd" placeholder="<spring:message code="placeholder.password"/>"
								onkeydown="checkPasswordStrength();" onkeyup="catValidate();checkPasswordStrength();" maxlength="50" onchange="checkPasswordStrength();">
          <div class="input-postpend signup-show-password">Show Password</div>
          <div class="signup-password-strength">
            <strong id="passstrength">Strength</strong>
            <div class="meter"><div class="meter-fill"></div></div>
          </div>      
        </div>
        
        <p class="requireFld error-msg validation--error"  id="pwderror"><spring:message code="msg.empty"/></p>
		<p class="requireFld error-msg validation--error" id="pwderror1"><spring:message code="msg.password.min.length"/></p>
		<p class="requireFld error-msg validation--error"  id="pwderror11"><spring:message code="msg.password.invalid"/></p>
		<p class="requireFld error-msg validation--error"  id="pwderror111"><spring:message code="msg.password.max.length"/></p>
					
      </div>
       
      <div class="form-row">
        <label for="password">Retype password</label>
        <div class="form-field">
          <input type="password" class="form-control input-lg"
								name="compLoginPwd2" id="compLoginPwd2"
								placeholder="<spring:message code="placeholder.retypepassword"/>" onkeyup="catValidate();"
								maxlength="50">
        </div>
        <p class="requireFld error-msg validation--error" id="pwderror2"><spring:message code="msg.empty"/></p>
		<p class="requireFld error-msg validation--error" id="pwderror123"><spring:message code="msg.password.matching"/></p>
      </div>


      
    </fieldset>

    <fieldset data-ng-app="myApp" data-ng-controller="myCtrl">
      <legend>Organization</legend>

             <div class="form-row ">
        <label for="orgName">Organization name</label>
        <div class="form-field">
          <input id="orgName" type="text" class="form-control input-lg"
								name="orgName" placeholder="<spring:message code="placeholder.organization"/>"
								 maxlength="50" data-ng-model="subdomain" data-ng-keyup="catValidate()"  data-ng-change="changeSubDomain()">
          
        </div>
        <p class="requireFld error-msg validation--error" id="orgNameError"><spring:message code="msg.empty"/></p>
      </div>

      <div class="form-row">
        <label>Subdomain</label>
        <div class="form-field">
          <input id="subdomain" data-ng-value="changesubdomain" type="text" class="form-control input-lg tooltip-field"
								name="subdomain" placeholder="<spring:message code="placeholder.subdomain"/>"
								 maxlength="50" data-ng-keyup="catValidate()">
          <div class="input-prepend"><span>http://</span></div>
          <div class="input-postpend"><span>.qbis.in</span></div>
        </div>
			    <p class="requireFld error-msg validation--error"  id="subdomainError"><spring:message code="msg.empty"/></p>
				<p class="requireFld error-msg validation--error"  id="subdomainError1"><spring:message code="msg.subdomain.invalid"/></p>
				<p class="requireFld error-msg validation--error"  id="subdomainError2"><spring:message code="msg.subdomain.already.exist"/></p>
      
      </div>
      
            
      <div class="form-row">
        <label>System default language <span class="badge bg-gray tooltip-field" id="system-language-tooltip-title"><i class="fa fa-question"></i></span></label>
        <div class="form-field">
          <select id="systemLanguage" name="systemLanguage"
								class="form-control input-lg">
								<option value="en" selected><spring:message code="systemlanguge.english" text="English"/></option>
                                <option value="hi"><spring:message code="systemlanguge.hindi" text="Hindi"/></option>
		</select>
          
        </div>
      </div>
      

    </fieldset>
    
     </div> 
</section>
<section class="licence-selection">
  <div class="wrapper">

    <h3 style="margin-bottom: 30px;">Select the licence that fits you best</h3>

    <div class="licences">
      <c:forEach items="${licenselist}" var="license">
      <c:if test="${license.licenseId==1}">
      <div class="licence licence--free">
        <input type="radio" name="licenseId" id="free" value="${license.licenseId}" class="js-input_licence" checked/>

        <label for="free">
          <div class="checkbox"><div class="check"></div></div>
          <h5>${license.licenseName}</h5>
          <h6><strong><span>No Price</span></strong></h6>

          <ul>
            <li><strong>${license.availableSpace} GB</strong><span> Cloud Disk Storage</span></li>
            <li><strong>${license.validityDays} Days</strong><span> validity</span></li>
            <li><strong>${license.maxAllowedUser}</strong><span> users</span></li>
            <li class="li-height"></li>
          </ul>
        </label>
      </div>
      </c:if>
      <c:if test="${license.licenseId==2}">
      <div class="licence licence--advance">
        <input type="radio" name="licenseId" id="advance" value="${license.licenseId}" class="js-input_licence" />

        <label for="advance">
          <div class="checkbox"><div class="check"></div></div>
          <h5>${license.licenseName}</h5>
          <h6><strong>$<c:out value="${license.cost}"></c:out></strong><span> / month</span></h6>

          <ul>
            <li><strong>${license.availableSpace} GB</strong><span> Cloud Disk Storage</span></li>
            <li><strong>${license.validityDays} Days</strong><span> validity</span></li>
            <li><strong>${license.maxAllowedUser}</strong><span> users</span></li>
            <li><span class="paypal-logo"><img
			src="<spring:url value='/resources/images/paypal-logo.png'/>" /></span></li>
          </ul>
        </label>
      </div>
      </c:if>
      </c:forEach>
    </div>
    
          
      <div class="custom--licence">
      <a href="requestatdemo" target="_blank">
        <span><b></b></span>
        You can also request for a <em>custom licence</em> as per your requirement.
      </a>
    </div>
    
  </div>
</section>
<section class="signup-main">
<div class="wrapper signup-form recapcha-form-row">
 <fieldset>
      <div class="form-row">
							<div class="g-recaptcha"
								data-sitekey="6LflDggUAAAAADFymyuy1yC4w7QNALFP0_OEn28P"
								data-callback="verifyCallback"
								data-expired-callback="expireCallback"></div>
							<p class="requireFld error-msg validation--error" id="g-recaptchaError"><spring:message code="msg.recaptcha.invalid"/></p>					
        </div>
      </fieldset>
             <fieldset>
             <div class="form-row">
           <label for="terms">
          <!-- <input name="account[terms_of_service]" type="hidden" value="0" /> --><input class="js-input_terms" id="terms" type="checkbox" value="1" checked/>
          <span><b></b></span>
          I agree to the <a target="_blank" href="resources/adminlte/termsofuse.html">terms of service</a> and <a target="_blank" href="resources/adminlte/privacypolicy.html">privacy policy</a>
        </label>
      </div>
       </fieldset>
</div>
</section>

  <section class="signup-submit">
    <div class="wrapper">
      <fieldset class="terms-container">
        <button type="button" class="btn btn-submit" id="submitButton" onclick="submitForm()" disabled>Sign Up</button>
      </fieldset>

    </div>
  </section>					
</form>

        </div>
        <%@ include file="footer.jsp"%>
	</div>
<!-- Fresh chat -->   
<script	src="<spring:url value='/resources/adminlte/dist/js/freshchat.js'/>"></script> 	
</body>
<script src="https://www.google.com/recaptcha/api.js?hl=<%=request.getParameter("locale")%>"></script>
<!-- jQuery 2.1.4 js library -->
<script src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<!-- Bootstrap 3.3.5 js library -->
<script src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap.min.js'/>"></script>
<!-- angular 1.5.8 js library -->
<script src="<spring:url value='/resources/js/angular.min.js'/>"></script>  
<script>
	var domainName = "${userReg.domainName}";
	var submitform = true;
	/**
	 * @summary This method would be called when recaptcha response is generated.
	 */
	function verifyCallback() {
		$("#g-recaptchaError").fadeOut();
		$("#submitButton").prop('disabled', false);
	}

	/**
	 * @summary This method would be called when recaptcha session is expired.
	 */
	function expireCallback() {
		grecaptcha.reset();
		$("#submitButton").prop('disabled', true);
	}

	/**
	 * @summary This method would be called when body has been loaded.
	 */
	$(document).ready(function() {
	  $('#subdomain').tooltip({title: "You'll use this URL to access your account", trigger: "focus",placement:"bottom"});
	  $('#compLoginPwd').tooltip({title: "Password must be between 6 and 50 characters", trigger: "focus", placement:"bottom"});
	  $('#system-language-tooltip-title').tooltip({title: "This will be your default language for system. You can change it later from setting.", trigger: "hover", placement:"top"});
		$('#uItem li').click(function() {
			var text = $(this).text();
			catValidate();
		});
	});

	/**
	 * @summary This is used for getting absolute path from url. 
	 */
	function getAbsolutePath() {
		var loc = window.location;
		var pathName = loc.pathname.substring(0,
				loc.pathname.lastIndexOf('/') + 1);
		return loc.href
				.substring(
						0,
						loc.href.length
								- ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	}
	
	/**
	 * @summary This is used for validating the form input field.
	 */
	function validate() {
		var pwd1 = document.getElementById('compLoginPwd').value;
		var pwd2 = document.getElementById('compLoginPwd2').value;
		//var regularExpression = /^(?=[^\d_].*?\d)(?=.*[!@#$%^&*~{}<>?:+_|()])\w(\w|[!@#$%^&*~{}<>?:+_|()]){6,49}$/;
		var regularExpression = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*~{}<>?:+_|()])([a-zA-Z0-9$!@#$%^&*~{}<>?:+_|()]).{6,49}$");
		if (document.myForm.email.value.trim() == "") {

			$("#email").closest(".form-row").addClass("form-error");
			$("#emailError").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#email").offset().top-200
	        }, 1000);
			document.myForm.email.focus();
			return false;
		}
		var email = document.getElementById('email').value;
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!filter.test(email)) {
			$("#email").closest(".form-row").addClass("form-error");
			$("#emailError1").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#email").offset().top-200
	        }, 1000);
			document.myForm.email.focus();

		
			return false;
		}

		if (document.myForm.compLoginPwd.value.trim() == "") {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#compLoginPwd").offset().top-200
	        }, 1000);
			document.myForm.compLoginPwd.focus();
			return false;
		}
		if (pwd1.length < 6) {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror1").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#compLoginPwd").offset().top-200
	        }, 1000);
			document.myForm.compLoginPwd.focus();
			return false;
		}
		if (pwd1.length > 50) {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror111").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#compLoginPwd").offset().top-200
	        }, 1000);
			document.myForm.compLoginPwd.focus();
			return false;
		}
		/* if (!regularExpression.test(pwd1)) {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror11").fadeIn();
			document.myForm.compLoginPwd.focus();
			return false;
		} */
		if (document.myForm.compLoginPwd2.value.trim() == "") {
			$("#compLoginPwd2").closest(".form-row").addClass("form-error");
			$("#pwderror2").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#compLoginPwd2").offset().top-200
	        }, 1000);
			document.myForm.compLoginPwd2.focus();
			return false;
		} 
		if (pwd1 != pwd2) {
			$("#compLoginPwd2").closest(".form-row").addClass("form-error");
			$("#pwderror123").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#compLoginPwd2").offset().top-200
	        }, 1000);
			document.myForm.compLoginPwd2.focus();
			return false;
		}
		
		if (document.myForm.orgName.value.trim() == "") {
			$("#orgName").closest(".form-row").addClass("form-error");
			$("#orgNameError").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#orgName").offset().top-200
	        }, 1000);
			document.myForm.orgName.focus();
			return false;
		}

		var subdomain = $.trim($("#subdomain").val());
		if (subdomain == "") {
			$("#subdomain").closest(".form-row").addClass("form-error");
			$("#subdomainError").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#subdomain").offset().top-200
	        }, 1000);
			return false;
		}
		var alphanumval = /^[0-9a-zA-Z-]+$/;
		if (!subdomain.match(alphanumval)) {
			$("#subdomain").closest(".form-row").addClass("form-error");
			$("#subdomainError1").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#subdomain").offset().top-200
	        }, 1000);
			return false;
		}

		if ((subdomain.indexOf('-') == 0)
				|| (subdomain.lastIndexOf('-') == (subdomain.length - 1))) {
			$("#subdomain").closest(".form-row").addClass("form-error");
			$("#subdomainError1").fadeIn();
			$('html, body').animate({
	            scrollTop: $("#subdomain").offset().top-200
	        }, 1000);
			return false;
		}
		
		return (true);
	}

	/**
	 * @summary This is used that subdomain is already exist or not.
	 */
	function submitForm() {
		if (validate()) {
			$("#submitButton").prop('disabled', true);
			var orgName = document.myForm.subdomain.value.trim();
			var email = document.myForm.email.value.trim();
			$("#overlay").show();
			$.ajax({
				type : 'GET',
				url : "checkOrganization",
				data : "orgName=" + encodeURIComponent(orgName) + "&email="
						+ encodeURIComponent(email),
				error : function() {
					$("#overlay").hide();
					expireCallback();
				},
				success : function(result) {
					$("#overlay").hide();
					if (result.success == true) {
						submitRegistrationForm();
					} else {
						if (result.email == true) {
							$("#email").closest(".form-row").addClass("form-error");
							$("#emailError2").fadeIn();
							$('html, body').animate({
					            scrollTop: $("#email").offset().top-200
					        }, 1000);
							document.myForm.email.focus();
						}
						if (result.subdomain == true) {
							$("#subdomain").closest(".form-row").addClass("form-error");
							$("#subdomainError2").fadeIn();
							$('html, body').animate({
					            scrollTop: $("#subdomain").offset().top-200
					        }, 1000);
						}
						expireCallback();
					}
				}
			});
		} else {
			expireCallback();
		}
	}

	/**
	 * @summary This is used submit the data of user after validate.
	 */
	function submitRegistrationForm() {
		$("#overlay").show();
		$.ajax({
			type : 'POST',
			url : "newUserRegister",
			data : {
				'email' : $.trim($("#email").val()),
				'orgName' : $.trim($("#orgName").val()),
				'subdomain' : $.trim($("#subdomain").val()),
				'password' : $.trim($("#compLoginPwd").val()),
				'licenseId' : $.trim($('input[name=licenseId]:checked', '#myForm').val()),
				'gRecaptchaResponse' : grecaptcha.getResponse(),
				'systemLanguage' : $.trim($("#systemLanguage").val()),
			},
			datatype : "String",
			error : function() {
				$("#overlay").hide();
				expireCallback();
			},
			success : function(data) {
				if ($.trim(data).length > 0) {
					$("#overlay").hide();
					$("#g-recaptchaError").fadeIn();
					expireCallback();
				} else {
					var loc = getAbsolutePath();
					loc = loc.replace(window.location.hostname, $.trim($(
							"#subdomain").val())
							+ "." + domainName);
					if($.trim($('input[name=licenseId]:checked', '#myForm').val())==1)
						{
						location.replace(loc + "regsuccess");
						}
					else
						{
						location.replace(loc + "makePayment");
						}
				}
			}
		});
	}

	/**
	 * @summary This is used fade out the error on key up event.
	 */
	function catValidate() {

		if ((document.myForm.email.value.trim()).length > 0) {

			$("#email").closest(".form-row").removeClass("form-error");
			;
			$("#emailError").fadeOut();
			$("#emailError1").fadeOut();
			$("#emailError2").fadeOut();
		}
		
		if ((document.myForm.orgName.value.trim()).length > 0) {
			$("#orgName").closest(".form-row").removeClass("form-error");
			$("#orgNameError").fadeOut();
		}

		if ($.trim($("#subdomain").val()).length > 0) {
			$("#subdomain").closest(".form-row").removeClass("form-error");
			$("#subdomainError1").fadeOut();
			$("#subdomainError2").fadeOut();
			$("#subdomainError").fadeOut();
		}
		if ((document.myForm.compLoginPwd.value.trim()).length > 0) {

			$("#compLoginPwd").closest(".form-row").removeClass("form-error");
			$("#pwderror").fadeOut();
			$("#pwderror1").fadeOut();
			$("#pwderror11").fadeOut();
			$("#pwderror111").fadeOut();
		}
		if ((document.myForm.compLoginPwd2.value.trim()).length > 0) {

			$("#compLoginPwd2").closest(".form-row").removeClass("form-error");
			$("#pwderror2").fadeOut();
			$("#pwderror123").fadeOut();
		}
	}
	
	/**
	 * @summary This is used for showing and hide the password text.
	 */
	$(document).on('click','.signup-show-password',function(){
		if($(this).hasClass('is-showing')){
			$(this).removeClass('is-showing');
			$("#compLoginPwd").attr('type','password');
		}
		else
			{
			$(this).addClass('is-showing');
			$("#compLoginPwd").attr('type','text');
			}
	});
	
	/**
	 * @summary This function is used for checking password length.
	 */
    function checkPasswordStrength() {
    	var password = $("#compLoginPwd").val();
 
        //TextBox left blank.
        if (password.length == 0) {
            $(".signup-password-strength").removeClass("is-veryweak");
            $(".signup-password-strength").removeClass("is-weak");
            $(".signup-password-strength").removeClass("is-good");
            $(".signup-password-strength").removeClass("is-strong");
            $("#passstrength").text("Strength");
            return;
        }
 
        //Regular Expressions.
        var regex = new Array();
        regex.push("[A-Z]"); //Uppercase Alphabet.
        regex.push("[a-z]"); //Lowercase Alphabet.
        regex.push("[0-9]"); //Digit.
        regex.push("[$@$!%*#?&]"); //Special Character.
 
        var passed = 0;
 
        //Validate for each Regular Expression.
        for (var i = 0; i < regex.length; i++) {
            if (new RegExp(regex[i]).test(password)) {
                passed++;
            }
        }
 
        //Validate for length of Password.
        if (passed > 2 && password.length > 8) {
            passed++;
        }
 
        //Display status.
        var color = "";
        var strength = "";
        switch (passed) {
            case 0:
            case 1:
                strength = "Very Weak";
                color = "red";
                $(".signup-password-strength").removeClass("is-veryweak");
                $(".signup-password-strength").removeClass("is-weak");
                $(".signup-password-strength").removeClass("is-good");
                $(".signup-password-strength").removeClass("is-strong");
                $(".signup-password-strength #passstrength").text("Very Weak");
                $(".signup-password-strength").addClass("is-veryweak");//is-weak is-good is-strong
                break;
            case 2:
                strength = "Weak";
                color = "darkorange";
                $(".signup-password-strength").removeClass("is-veryweak");
                $(".signup-password-strength").removeClass("is-weak");
                $(".signup-password-strength").removeClass("is-good");
                $(".signup-password-strength").removeClass("is-strong");
                $(".signup-password-strength #passstrength").text("Weak");
                $(".signup-password-strength").addClass("is-weak");
                break;
            case 3:
            case 4:
                strength = "Good";
                color = "green";
                $(".signup-password-strength").removeClass("is-veryweak");
                $(".signup-password-strength").removeClass("is-weak");
                $(".signup-password-strength").removeClass("is-good");
                $(".signup-password-strength").removeClass("is-strong");
                $(".signup-password-strength #passstrength").text("Good");
                $(".signup-password-strength").addClass("is-good");
                break;
            case 5:
                strength = "Strong";
                color = "darkgreen";
                $(".signup-password-strength").removeClass("is-veryweak");
                $(".signup-password-strength").removeClass("is-weak");
                $(".signup-password-strength").removeClass("is-good");
                $(".signup-password-strength").removeClass("is-strong");
                $(".signup-password-strength #passstrength").text("Strong");
                $(".signup-password-strength").addClass("is-strong");
                break;
        	}
    }    
	
	/**
	 * @summary This function is used for changing the text of sign up form header with selected licence by user.
	 */
	$(document).on('change','input[name="licenseId"]',function(){
		var licenseId =  parseInt($(this).val());
		switch(licenseId){
		case 1:
			$(".signup-header-title-with-selected-licence").text("Start your 14 day free trial");
			break;
		case 2:
			$(".signup-header-title-with-selected-licence").text("Complete your registration");
			break;
		}
	});
</script>

<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', myCtrl);
function myCtrl($scope, $http) {
	$scope.catValidate = catValidate;
	$scope.changeSubDomain = function(){
		$scope.changesubdomain = $scope.subdomain.toLowerCase().replace(/[^\w ]+/g,"").replace(/ +/g,"-");
	};
	}
</script>
</html>
