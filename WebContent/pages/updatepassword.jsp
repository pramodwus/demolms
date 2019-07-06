<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="<spring:url value='/resources/css/signup.css?v=1'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/qbis.css?v=1'/>">
</head>
<body class="signup--body qbis--body">
	<div class="hold-transition login-page user-login-page-with-subdomain">
		<div class="login-box pull-right">
			<div class="login-box-body">
				<div class="login-logo">
					<img src="<spring:url value='/resources/images/logo.png'/>" />
				</div>
				<c:if test="${setUpPass!=null}">
					<p class="login-box-msg"><spring:message code="header.password.setup"/></p>
				</c:if>
				<c:if test="${setUpPass==null}">
					<p class="login-box-msg"><spring:message code="header.new.password.enter"/></p>
				</c:if>
				<form name="myForm" action="updatePassword" id="passForm"
					method="post" onsubmit="return validate();" class="signup-form">
                    <input type="password" class="form-filed-hide">
					<div class="form-group has-feedback form-row">
						<input type="password" class="form-control input-lg" name="password"
							id="compLoginPwd" placeholder="<spring:message code="placeholder.password"/>" onkeyup="catValidate();checkPasswordStrength();"
							onkeydown="checkPasswordStrength();" onchange="checkPasswordStrength();" maxlength="50"> 
							<div class="input-postpend signup-show-password">Show Password</div>
                                <div class="signup-password-strength">
                                  <strong id="passstrength">Strength</strong>
                                  <div class="meter"><div class="meter-fill"></div></div>
                               </div>
							<p class="requireFld error-msg validation--error" id="pwderror"><spring:message code="msg.empty"/></p>
							<p class="requireFld error-msg validation--error" id="pwderror1"><spring:message code="msg.password.min.length"/></p>
							<p class="requireFld error-msg validation--error" id="pwderror11"><spring:message code="msg.password.invalid"/></p>
							<p class="requireFld error-msg validation--error" id="pwderror111"><spring:message code="msg.password.max.length"/></p>
					</div>
					<div class="form-group has-feedback form-row">
						<input type="password" class="form-control input-lg" name="compLoginPwd2"
							id="compLoginPwd2" placeholder="<spring:message code="placeholder.retypepassword"/>"
							onkeyup="catValidate();" maxlength="50">
							<div class="input-postpend glyphicon-log-in-icon"><i class="glyphicon glyphicon-log-in"></i></div>
						<p class="requireFld error-msg validation--error" id="pwderror2"><spring:message code="msg.empty"/></p>
						<p class="requireFld error-msg validation--error" id="pwderror123"><spring:message code="msg.password.matching"/></p>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<button type="submit"
								class="btn btn-block pull-right btn-flat btn-success btn-lg submit-login-button">
                              <spring:message code="lbl.submit"/>
							</button>
						</div>
						<!--/.col -->
					</div>
				</form>
			</div>
			<!-- /.form-box -->
		</div>
		<!-- /.register-box -->
	</div>
</body>
<script>
	/**
	 * @summary validation for form input field.
	 * @returns {Boolean}
	 */
	function validate() {
		var pwd1 = document.getElementById('compLoginPwd').value;
		var pwd2 = document.getElementById('compLoginPwd2').value;
		var regularExpression = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*~{}<>?:+_|()])([a-zA-Z0-9$!@#$%^&*~{}<>?:+_|()]).{6,49}$");

		if (document.myForm.compLoginPwd.value.trim() == "") {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror").fadeIn();
			document.myForm.compLoginPwd.focus();
			return false;
		}
		if (pwd1.length < 6) {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror1").fadeIn();
			document.myForm.compLoginPwd.focus();
			return false;
		}
		if (pwd1.length > 50) {
			$("#compLoginPwd").closest(".form-row").addClass("form-error");
			$("#pwderror111").fadeIn();
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
			document.myForm.compLoginPwd2.focus();
			return false;
		}
		
		if (pwd1 != pwd2) {
			$("#compLoginPwd2").closest(".form-row").addClass("form-error");
			$("#pwderror123").fadeIn();
			document.myForm.compLoginPwd2.focus();
			return false;
		}
		return (true);
	}

	/**
	 * @summary This is used for fade out the error on key up event.
	 * @returns no.
	 */
	function catValidate() {
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
    
</script>
</html>