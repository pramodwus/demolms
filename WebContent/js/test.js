/**
 * event listener for fade out the modal on click of okk button.
 */
$(document).ready(function() {
	$("#okk").click(function() {
		$("#testAlert,.modal-backdrop").fadeOut();
	});
});

/**
 * This function is used to validate the all field related to test information form.
 * @returns {Boolean}
 */
function testValidate() {
	/**
	 * getting the instance of ckeditor.
	 */
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	/**
	 * validate test title is empty.
	 */
	if (document.testForm.testName.value.trim() == "") {
		$("#testName").css("border-color", "#c95b5b");
		$("#testNameError").fadeIn();
		document.testForm.testName.focus();
		return false;
	}
	/**
	 * validate the length of test title.
	 */
	if ((document.testForm.testName.value.trim()).length > 50) {
		$("#testName").css("border-color", "#c95b5b");
		$("#testNameError1").fadeIn();
		document.testForm.testName.focus();
		return false;
	}
	var instruct = CKEDITOR.instances['testInstruct'].getData();
	var instructData = ConvertHtmlToPlainTest(instruct);
	/**
	 * validate test instruction is empty.
	 */
	if (instructData == "") {
		$("#testInstructDiv").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#testInstructError").fadeIn();
		$(window).scrollTop($('#testInstructDiv').offset().top);
		return false;
	}
	/**
	 * validate test instruction's length.
	 */
	if (instruct.length > 1024) {
		$("#testInstructDiv").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#testInstructError1").fadeIn();
		$(window).scrollTop($('#testInstructDiv').offset().top);
		return false;
	}
	/**
	 * validate test description is empty.
	 */
	if (document.testForm.testDesc.value.trim() == "") {
		$("#testDesc").css("border-color", "#c95b5b");
		$("#testDescError").fadeIn();
		document.testForm.testDesc.focus();
		return false;
	}
	/**
	 * validate test description's length.
	 */
	if ((document.testForm.testDesc.value.trim()).length > 512) {
		$("#testDesc").css("border-color", "#c95b5b");
		$("#testDescError1").fadeIn();
		document.testForm.testDesc.focus();
		return false;
	}
	/**
	 * validate that if score type is on then question score for every question should be numeric.
	 */
	if ($("#scoreType").val().trim() == '1') {
		var scoreVal = $("#scoreMark").val();
		if (document.testForm.scoreMark.value.trim() == "") {
			$("#scoreMark").css("border-color", "#c95b5b");
			$("#scoreMarkError").fadeIn();
			document.testForm.scoreMark.focus();
			return false;
		} else if (!scoreVal.match(/^\d*$/)) {
			$("#scoreMark").css("border-color", "#c95b5b");
			$("#scoreMarkError1").fadeIn();
			document.testForm.scoreMark.focus();
			return false;

		}
	}
	/**
	 * validate that if limitAttempts is on then maxAttempts for test should be numeric.
	 */
	if ($("#limitAttempts").val().trim() == 'On') {
		$("#maxAttemptsError").fadeOut();
		$("#maxAttemptsError1").fadeOut();
		var scoreVal = $("#maxAttempts").val();
		if (document.testForm.maxAttempts.value.trim() == "") {
			$("#maxAttempts").css("border-color", "#c95b5b");
			$("#maxAttemptsError").fadeIn();
			document.testForm.maxAttempts.focus();
			return false;
		} else if (!scoreVal.match(/^\d*$/)) {
			$("#maxAttempts").css("border-color", "#c95b5b");
			$("#maxAttemptsError1").fadeIn();
			document.testForm.scoreMark.focus();
			return false;

		}
	}
	/**
	 * validate that if negative Type is on then negative mark for test should be numeric.
	 */
	if ($("#negType").val().trim() == 'On') {
		var negMark = $("#negMark").val();
		var scoreVal = $("#scoreMark").val();
		if (document.testForm.negMark.value.trim() == "") {
			$("#negMark").css("border-color", "#c95b5b");
			$("#negMarkError").fadeIn();
			document.testForm.negMark.focus();
			return false;
		} else if (!negMark.match(/^\d*$/)) {
			$("#negMark").css("border-color", "#c95b5b");
			$("#negMarkError1").fadeIn();
			document.testForm.negMark.focus();
			return false;
		}
	}
	/**
	 * validate that if time bound is on then time minutes should be numeric and maximum than 5 and also multiple of 5.
	 */
	if ($("#time").val().trim() == 'On') {
		debugger;
		var timeMin = $("#timeMin").val();
		var timeSec = $("#timeSec").val();
		if (document.testForm.timeMin.value.trim() == "") {
			$("#timeMin").css("border-color", "#c95b5b");
			$("#timeError").fadeIn();
			document.testForm.timeMin.focus();
			return false;
		} else if (document.testForm.timeSec.value.trim() == "") {
			$("#timeSec").css("border-color", "#c95b5b");
			$("#timeError").fadeIn();
			document.testForm.timeSec.focus();
			return false;
		} else if (!timeMin.match(/^\d*$/)) {
			$("#timeMin").css("border-color", "#c95b5b");
			$("#timeError1").fadeIn();
			document.testForm.timeMin.focus();
			return false;
		} else if (timeMin % 5 != 0 || timeMin < 5) {
			$("#timeMin").css("border-color", "#c95b5b");
			$("#timeError2").fadeIn();
			document.testForm.timeMin.focus();
			return false;
		} else if (!timeSec.match(/^\d*$/)) {
			$("#timeSec").css("border-color", "#c95b5b");
			$("#timeError1").fadeIn();
			document.testForm.timeSec.focus();
			return false;
		} else if (timeSec > 59) {
			$("#timeSec").css("border-color", "#c95b5b");
			$("#timeError2").fadeIn();
			document.testForm.timeSec.focus();
			return false;
		}
	}
	/**
	 * validate that if test tag should not be empty.
	 */
	if (document.testForm.testTag.value.trim() == "") {
		$("#testTag").css("border-color", "#c95b5b");
		$("#testTagError").fadeIn();
		document.testForm.testTag.focus();
		return false;
	}
	return true;
}

/**
 * this function is used for fading out all showing error from test information fields. 
 */
function catValidate() {
	if ((document.testForm.testName.value.trim()).length > 0) {
		$("#testName").css("border-color", "#7ac17d");
		$("#testNameError").fadeOut();
		$("#testNameError1").fadeOut();
		$("#testNameError2").fadeOut();
	}
	var instruct = CKEDITOR.instances['testInstruct'].getData();
	if (instruct != "") {
		$("#testInstructDiv").css("border-color", "#7ac17d");
		$("#testInstructError").fadeOut();
		$("#testInstructError1").fadeOut();
	}
	if ((document.testForm.testDesc.value.trim()).length > 0) {
		$("#testDesc").css("border-color", "#7ac17d");
		$("#testDescError").fadeOut();
		$("#testDescError1").fadeOut();
	}
	if ((document.testForm.scoreMark.value.trim()).length > 0) {
		$("#scoreMark").css("border-color", "#7ac17d");
		$("#scoreMarkError").fadeOut();
		$("#scoreMarkError1").fadeOut();
	}
	if ((document.testForm.negMark.value.trim()).length > 0) {
		$("#negMark").css("border-color", "#7ac17d");
		$("#negMarkError").fadeOut();
		$("#negMarkError1").fadeOut();
		$("#negMarkError2").fadeOut();
	}
	if ((document.testForm.timeMin.value.trim()).length > 0) {
		$("#timeMin").css("border-color", "#7ac17d");
		$("#timeError").fadeOut();
		$("#timeError1").fadeOut();
		$("#timeError2").fadeOut();
	}
	if ((document.testForm.timeSec.value.trim()).length > 0) {
		$("#timeSec").css("border-color", "#7ac17d");
		$("#timeError").fadeOut();
		$("#timeError1").fadeOut();
		$("#timeError2").fadeOut();
	}
	if (document.testForm.testTag.value.trim().length > 0) {
		$("#testTag").css("border-color", "white");
		$("#testTagError").fadeOut();
	}
		$("#testImageError").fadeOut();
		$("#testImageError1").fadeOut();
}


/**
 * This is used for converting the html text into plain text.
 * 
 * @param strHtmlText
 * @returns
 */
function ConvertHtmlToPlainTest(strHtmlText) {
	if (strHtmlText.match("img")) {
		strHtmlText;
	} else if (strHtmlText != undefined) {
		strHtmlText = strHtmlText.replace(/<(?:.|\n)*?>/gm, '');
		var temp = document.createElement("div");
		temp.innerHTML = strHtmlText;
		strHtmlText = temp.textContent || temp.innerText || "";
	} else {
		strHtmlText = "";
	}
	return strHtmlText.trim();
}


