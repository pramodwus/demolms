/**
 * @summary This file is used for test creation and updation.
 * @author ankur
 * @date 04 Sep 2016  
 */

/**
 * @summary function is used for submitting test information on server using
 *          ajax.
 * @returns no.
 */
function submitTest() {
	if (testValidate()) {
		var testName = $("#testName").val();
		var scoreType = $("#scoreType").val();
		var formData = new FormData($("#testForm")[0]);
		$("#overlay").show();
		$.ajax({
			type : 'POST',
			url : 'testSubmit',
			processData : false,
			contentType : false,
			data : formData,
			success : function(testId) {
				$("#overlay").hide();
				if ($.trim(testId) > 0) {
					if(updatedTestId === ""){
						ga('send', {
							  hitType: 'event',
							  eventCategory: 'Create Quiz',
							  eventAction: testName,
							  eventLabel: 'New quiz created'
							});
					}
					$(window).off('beforeunload');
					if (courseId != null && courseSectionId != null) {
						var url = 'prepareTestQuestions?testId=' + testId
								+ '&courseId=' + courseId + '&sectionId='
								+ courseSectionId;
						window.location.href = contentId != null ? url
								+ '&contentId=' + contentId : url;
					} else {
						window.location.href = 'prepareTestQuestions?testId='
								+ testId;
					}
				} else {
					$("#testAlert p").text(messages['msg.somethingwentwrong']);
					$('#testAlert').modal('show');
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$("#overlay").hide();
				$("#testAlert p").text(messages['msg.refreshpage']);
				$('#testAlert').modal('show');
			}
		});
	}
}

/**
 * @summary This function is used for changing the behavior on click of toggle
 *          button.
 * @param Data
 * @returns no.
 */
function changeState(Data) {
	if (Data.trim() == 'view') {
		if ($("#view2").val().trim() == 'Off') {
			$("#view2").val('On');
			$("#view1").val('0');
		} else {
			$("#view2").val('Off');
			$("#view1").val('1');
		}
	}

	if (Data.trim() == 'scoreType') {
		if ($("#scoreType").val().trim() == '0') {
			$("#scoreType").val('1');
			$("#scoreTypeOnOff").show();
			$("#scoreMark").val('');
		} else {
			$("#scoreType").val('0');
			$("#scoreMark").val('');
			$("#scoreTypeOnOff").hide();
			$("#scoreMark").css("border-color", "#7ac17d");
			$("#scoreMarkError").fadeOut();
			$("#scoreMarkError1").fadeOut();
		}
	}
	if (Data.trim() == 'limitAttempts') {
		if ($("#limitAttempts").val().trim() == 'Off') {
			$("#limitAttempts").val('On');
			$("#limitAttemptsOnOff").show();
			$("#maxAttempts").val('');
		} else {
			$("#limitAttempts").val('Off');
			$("#limitAttemptsOnOff").hide();
			$("#maxAttempts").css("border-color", "#7ac17d");
			$("#maxAttemptsError").fadeOut();
			$("#maxAttemptsError1").fadeOut();
		}
	}
	if (Data.trim() == 'isReview') {
		if ($("#isReview").val().trim() == 0) {
			$("#isReview").val('1');
			$("#isReviewOnOff").show();
		} else {
			$("#isReview").val('0');
			$("#isReviewOnOff").hide();
		}
	}
	if (Data.trim() == 'negType') {
		if ($("#negType").val().trim() == 'Off') {
			$("#negType").val('On');
			$("#negMark").val('');
			$("#negTypeOnOff").show();
		} else {
			$("#negType").val('Off');
			$("#negMark").val('0');
			$("#negTypeOnOff").hide();
			$("#negMark").css("border-color", "#7ac17d");
			$("#negMarkError").fadeOut();
			$("#negMarkError1").fadeOut();
			$("#negMarkError2").fadeOut();
		}
	}
	if (Data.trim() == 'time') {
		if ($("#time").val().trim() == 'Off') {
			$("#time").val('On');
			$("#testTime").show();
			$("#timeMin").val('');
		} else {
			$("#time").val('Off');
			$("#timeMin").val('');
			$("#testTime").hide();
			$("#timeError").fadeOut();
			$("#timeError1").fadeOut();
			$("#timeError2").fadeOut();
		}
	}
	if (Data.trim() == 'testPause') {
		if ($("#testPause2").val().trim() == 'Off') {
			$("#testPause2").val('On');
			$("#testPause1").val('1');
		} else {
			$("#testPause2").val('Off');
			$("#testPause1").val('0');
		}
	}
	if (Data.trim() == 'testAdaptive') {
		if ($("#testAdaptive2").val().trim() == 'Off') {
			$("#testAdaptive2").val('On');
			$("#testAdaptive1").val('1');
		} else {
			$("#testAdaptive2").val('Off');
			$("#testAdaptive1").val('0');
		}
	}

	if (Data.trim() == 'shuffleSection') {
		if ($("#shuffleSection").val().trim() == '0') {
			$("#shuffleSection").val('1');
		} else {
			$("#shuffleSection").val('0');
		}
	}

	if (Data.trim() == 'shuffleQuestion') {
		if ($("#shuffleQuestion").val().trim() == '0') {
			$("#shuffleQuestion").val('1');
		} else {
			$("#shuffleQuestion").val('0');
		}
	}
	
	if (Data.trim() == 'quizRandom') {
		if ($("#quizRandom").val() == '0') {
			$("#quizRandom").val('1');
			$("#limitMaxQuestionsOnOff").show();
			$("#maxQuestions").val('');
		} else {
			$("#quizRandom").val('0');
			$("#limitMaxQuestionsOnOff").hide();
			$("#maxQuestions").css("border-color", "#7ac17d");
			$("#maxQuestionsError").fadeOut();
			$("#maxQuestionsError1").fadeOut();
		}
	}

	if (Data.trim() == 'shuffleOption') {
		if ($("#shuffleOption").val().trim() == '0') {
			$("#shuffleOption").val('1');
		} else {
			$("#shuffleOption").val('0');
		}
	}

	/*if (Data.trim() == 'isSchedule') {
		if ($("#isSchedule").val().trim() == '0') {
			$("#isSchedule").val('1');
			$("#schedulePublishDateDiv").show();
		} else {
			$("#isSchedule").val('0');
			$("#schedulePublishDateDiv").hide();
		}
	}*/

}

/**
 * @summary This listener is used for calling the getTestList function as user
 *          click on button which has test Setting id.
 * @returns no.
 */
$("#testSetting").click(function() {
	getTestList();
});

/**
 * @summary Function for getting all test list using ajax.
 * @returns no.
 */
function getTestList() {
	$("#overlay").show();
	$.ajax({
		url : 'gettestlistajax',
		type : 'GET',
		error : function() {
			$("#overlay").hide();
			$("#testAlert p").text(messages['msg.somethingwentwrong']);
			$('#testAlert').modal('show');
		},
		success : function(testlist) {
			/**
			 * after successful getting data as string , parsing it into a json.
			 */
			var data = JSON.parse(testlist);
			/**
			 * calling function for extracting test details from test list json
			 * and adding in table.
			 */
			extractTestData(data);
		}

	});
}

/**
 * @summary Instance of test list table.
 */
var table;

/**
 * @summary This is used for extracting test details from test list json and
 *          adding in table.
 * @param testData
 *            This is only parameter which has details about all tests.
 * @returns no.
 */
function extractTestData(testData) {
	if (table != null) {
		$('#testTable').DataTable().destroy();
		// bDestroy = true; // trying out with bDestroy
	}
	/**
	 * This is a empty object which would be used adding html data as row inside
	 * table body.
	 */
	var row = '';
	/**
	 * before adding any data in table body, make it empty for no duplicate data
	 * if operation performs again.
	 */
	$("#tbody").empty();
	/**
	 * a lopping on test list data for extract test details for a particular
	 * test.
	 */
	for (var i = 0; i < testData.length; i++) {
		/**
		 * assign test is published or drafted based on test published status,
		 * if test is published then its published status would be 1.
		 */
		var testStatus = (testData[i].testPublishStatus == 1 ? messages['lbl.published'] : messages['lbl.drafted']);
		/**
		 * assign a html element for adding a row in table body.
		 */
		var hrefLink = '';
		var testId = $("#testId").val();
		if (updatedTestId == testData[i].testId) {
			hrefLink = '<a class="btn btn-success btn-flat button-width-large" disabled >'+messages['lbl.selectassessment']+'</a>';
		} else {
			var onclickAction = 'importTestSetting(' + testData[i].testId + ')';
			hrefLink = '<a class="btn btn-success btn-flat button-width-large" class="close" data-dismiss="modal" onclick="'
					+ onclickAction + '">'+messages['lbl.selectassessment']+'</a>';
		}
		row = row + '<tr><td>' + testData[i].testCreatedDate + '</td>' + '<td>'
				+ testData[i].testName + '</td>' + '<td>' + testStatus
				+ '</td>' + '<td>' + hrefLink + '</td></tr>';
	}
	/**
	 * append a table row inside table body.
	 */
	$("#tbody").append(row);
	/**
	 * initialize a table into data table
	 */
	table = $("#testTable").dataTable({
		'columnDefs' : [ {
			'orderable' : false,
			'targets' : [ 3 ]
		} ], // hide sort icon on action
		'aaSorting' : [],
		'language': datatablelanguagejson,
		"destroy" : true
	});
	/**
	 * hide the loader
	 */
	$("#overlay").hide();
	/**
	 * after loading all data into table , show pop up for choosing a test.
	 */
	$("#testtablePopup").modal('show');
}

/**
 * @summary fetch test setting based on test id.
 * @param testId
 * @returns no.
 */
function importTestSetting(testId) {
	if (testId > 0) {
		$("#overlay").show();
		$
				.ajax({
					url : 'importTestSetting?testId=' + testId,
					type : 'GET',
					error : function() {
						$("#overlay").hide();
						$("#testAlert p").text(messages['msg.somethingwentwrong']);
						$('#testAlert').modal('show');
					},
					success : function(test) {
						extractTestSetting(test);
						$("#overlay").hide();
					}
				});
	}
}

/**
 * @summary import old test setting.
 * @param test
 * @returns no.
 */
function extractTestSetting(test) {
	try {
		$("#testName").val(test.testName);
		CKEDITOR.instances['testInstruct'].setData(test.testInstruct);
		$("#testDesc").html(test.testDesc);
		if (test.view == 1) {
			$("#view2").val('Off');
			$("#view1").val('1');
			$("#view2").prop('checked', false);
		} else {
			$("#view2").val('On');
			$("#view1").val('0');
			$("#view2").prop('checked', true);
		}
		if (test.negMark == '0') {
			$("#negType").val('Off');
			$("#negType").prop('checked', false);
			$("#negMark").val('0');
			$("#negTypeOnOff").hide();
		}
		if (test.negMark != 0) {
			$("#negType").val('On');
			$("#negType").prop('checked', true);
			$("#negMark").val(test.negMark);
			$("#negTypeOnOff").show();
		}

		if (test.testTime == '0') {
			$("#time").val('Off');
			$("#testTime").hide();
			$("#time").prop('checked', false);
		}
		if (test.testTime == '1') {
			$("#time").val('On');
			$("#testTime").show();
			$("#timeMin").val(test.testTime);
			$("#time").prop('checked', true);
		}
		if (test.testPause == 1) {
			$("#testPause2").val('On');
			$("#testPause1").val('1');
			$("#testPause2").prop('checked', true);
		} else {
			$("#testPause2").val('Off');
			$("#testPause1").val('0');
			$("#testPause2").prop('checked', false);
		}
		if (test.testAdaptive == 1) {
			$("#testAdaptive2").val('On');
			$("#testAdaptive1").val('1');
			$("#testAdaptive2").prop('checked', true);
		} else {
			$("#testAdaptive2").val('Off');
			$("#testAdaptive1").val('0');
			$("#testAdaptive2").prop('checked', false);
		}

		if (test.equalMarkTest == 1) {
			$("#scoreType").prop('checked', true);
			$("#scoreType").val('1');
			$("#scoreTypeOnOff").show();
			$("#scoreMark").val(test.everyQuestionMark);
		} else {
			$("#scoreType").prop('checked', false);
			$("#scoreType").val('0');
			$("#scoreTypeOnOff").hide();
		}

		if (test.maxAttempts != 0) {
			$("#maxAttempts").val(test.maxAttempts);
			$("#limitAttempts").prop('checked', true);
			$("#limitAttempts").val('On');
			$("#limitAttemptsOnOff").show();
		} else {
			$("#limitAttempts").prop('checked', false);
			$("#limitAttempts").val('Off');
			$("#limitAttemptsOnOff").hide();
		}
		if (test.isReview == 1) {
			$("#isReview").val('1');
			$("#isReviewOnOff").show();
			$("#isReview").prop('checked', true);
			if (test.reviewWithCorrect == 0) {
				$("#withoutCorrect").prop('checked', true);
			} else {
				$("#withCorrect").prop('checked', true);
			}
		} else {
			$("#isReview").val('0');
			$("#isReview").prop('checked', false);
			$("#isReviewOnOff").hide();
		}
		if (test.shuffleSection == 1) {
			$("#shuffleSection").prop('checked', true);
			$("#shuffleSection").val('1');
		} else {
			$("#shuffleSection").prop('checked', false);
			$("#shuffleSection").val('0');
		}
		if (test.shuffleQuestion == 1) {
			$("#shuffleQuestion").prop('checked', true);
			$("#shuffleQuestion").val('1');
		} else {
			$("#shuffleQuestion").prop('checked', false);
			$("#shuffleQuestion").val('0');
		}
		if (test.shuffleOption == 1) {
			$("#shuffleOption").prop('checked', true);
			$("#shuffleOption").val('1');
		} else {
			$("#shuffleOption").prop('checked', false);
			$("#shuffleOption").val('0');
		}
		var tag = test.testTag;
		var tagData = [];
		if (tag != '') {
			var tagArray = tag.split(',');
			var tagIds = [];
			for (var i = 0; i < tagArray.length; i++) {
				var arr = {
					id : tagArray[i],
					text : tagArray[i]
				};
				tagData.push(arr);
				tagIds.push(tagArray[i]);
			}
			$(".select2").select2({
				data : tagData,
				tags : true,
				tokenSeparators : [ ',', ' ' ]
			});
			$("#multiSelectTag").select2('val', tagIds);

		} else {
			$(".select2").select2({
				tags : true,
				tokenSeparators : [ ',', ' ' ]
			});
		}
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary This is used to findout that array contains an element or not.
 * @param array
 * @param item
 * @returns {Boolean}
 */
function contains(array, item) {
	var status = false;
	for (var i = 0; i < array.length; i++) {
		if (array[i] === item) {
			status = true;
			break;
		}
	}
	return status;
}

/**
 * @summary function for showing image preview after selecting image as course
 *          icon.
 * @param fileInput
 * @returns no.
 */
function showMyImage(fileInput) {
	$("#testImageError1").fadeOut();
	var files = fileInput.files;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		var imageType = /image.*/;
		if (!file.type.match(imageType)) {
			$("#testImage").val("");
			$("#testImageError").fadeIn();
		} else if (files[i].size > 350000) {
			$("#testImage").val("");
			$("#testImageError1").fadeIn();
		} else {
			if (!file.type.match(imageType)) {
				// $("#courseImagePreview").attr('src','');
				continue;
			}
			var img = document.getElementById("TestImagePreview");
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
 * @summary event listener for fade out the modal on click of okk button.
 * @returns no.
 */
$(document).ready(function() {
	$("#okk").click(function() {
		$("#testAlert,.modal-backdrop").fadeOut();
	});
});

/**
 * @summary This function is used to validate the all field related to test
 *          information form.
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
	 * validate that if score type is on then question score for every question
	 * should be numeric.
	 */
	if ($("#scoreType").val().trim() == '1') {
		var scoreVal = $("#scoreMark").val();
		if (document.testForm.scoreMark.value.trim() == "") {
			$("#scoreMark").css("border-color", "#c95b5b");
			$("#scoreMarkError").fadeIn();
			document.testForm.scoreMark.focus();
			return false;
		}
		else if($.trim(scoreVal)==0){
			$("#scoreMark").css("border-color", "#c95b5b");
			$("#scoreMarkError1").fadeIn();
			document.testForm.scoreMark.focus();
			return false;
		}
		else if (!scoreVal.match(/^\d*$/)) {
			$("#scoreMark").css("border-color", "#c95b5b");
			$("#scoreMarkError1").fadeIn();
			document.testForm.scoreMark.focus();
			return false;

		}
	}
	/**
	 * validate that if limitAttempts is on then maxAttempts for test should be
	 * numeric.
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
		} 
		else if($.trim(scoreVal)==0){
			$("#maxAttempts").css("border-color", "#c95b5b");
			$("#maxAttemptsError1").fadeIn();
			document.testForm.maxAttempts.focus();
			return false;
		}else if (!scoreVal.match(/^\d*$/)) {
			$("#maxAttempts").css("border-color", "#c95b5b");
			$("#maxAttemptsError1").fadeIn();
			document.testForm.scoreMark.focus();
			return false;

		}
	}
	/**
	 * validate that if negative Type is on then negative mark for test should
	 * be numeric.
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
	 * validate that if time bound is on then time minutes should be numeric and
	 * maximum than 5 and also multiple of 5.
	 */
	if ($("#time").val().trim() == 'On') {
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
	 * validate that if Random Quiz is on then maxAttempts for test should be
	 * numeric.
	 */
	if ($("#quizRandom").val() == '1') {
		$("#maxQuestionsError").fadeOut();
		$("#maxQuestionsError1").fadeOut();
		var maxQuestionsValue = $("#maxQuestions").val();
		if (document.testForm.maxQuestions.value.trim() == "") {
			$("#maxQuestions").css("border-color", "#c95b5b");
			$("#maxQuestionsError").fadeIn();
			document.testForm.maxQuestions.focus();
			return false;
		} 
		else if($.trim(maxQuestionsValue)==0){
			$("#maxQuestions").css("border-color", "#c95b5b");
			$("#maxQuestionsError").fadeIn();
			document.testForm.maxQuestions.focus();
			return false;
		}else if (!maxQuestionsValue.match(/^\d*$/)) {
			$("#maxQuestions").css("border-color", "#c95b5b");
			$("#maxQuestionsError1").fadeIn();
			document.testForm.maxQuestions.focus();
			return false;

		}
	}
	
	/**
	 * test tags validation.
	 */
	var isError = customTestTagValidate();
	if(isError) {
		return false;
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
 * @summary This method is used for validate the custom test tag.
 * @returns
 */
function customTestTagValidate() {
	var isError = false;
	$('#createTest').find('.test-tags').each(function() {
		var value = $(this).find('.test-tags-input').val();
		if(value == null || $.trim(value).length == 0) {
			$(this).find('.test-tags-input').css("border-color", "#c95b5b");
			$(this).find('.test-tags-input-error').fadeIn();
			isError = true;
		}
	});
	return isError;
}

/**
 * @summary This function is used for fading out all showing error from test
 *          information fields.
 * @returns no.
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
	if (document.testForm.maxAttempts.value.trim().length > 0) {
		$("#maxAttempts").css("border-color", "");
		$("#maxAttemptsError").fadeOut();
		$("#maxAttemptsError1").fadeOut();
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
	if (document.testForm.maxQuestions.value.trim().length > 0) {
		$("#maxQuestions").css("border-color", "");
		$("#maxQuestionsError").fadeOut();
		$("#maxQuestionsError1").fadeOut();
	}
	
	/**
	 * test tags validation.
	 */
	$('#createTest').find('.test-tags').each(function() {
		var value = $(this).find('.test-tags-input').val();
		console.log("value " + value);
		if(value != null && $.trim(value).length > 0) {
			$(this).find('.test-tags-input').css("border-color", "#d0d0d0");
			$(this).find('.test-tags-input-error').fadeOut();
		}
	});
	
	if (document.testForm.testTag.value.trim().length > 0) {
		$("#testTag").css("border-color", "white");
		$("#testTagError").fadeOut();
	}
	$("#testImageError").fadeOut();
	$("#testImageError1").fadeOut();
}

/**
 * @summary This is used for converting the html text into plain text.
 * 
 * @param strHtmlText
 * @returns {String}
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

/**
 * This function is used for changing the behavior on click of publish schedule date button. 
 */
$(document).on('ifClicked','input[name="isSchedule"]',function(){
	  var _isSchedule = parseInt($.trim($(this).val()));
	  if(_isSchedule==1){
		  $("#schedulePublishDateDiv").show();
	  }
	  else
		  {
		  $("#schedulePublishDateDiv").hide();
		  }
});



