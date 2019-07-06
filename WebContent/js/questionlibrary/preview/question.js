/**
 * @summary This is used for common operation for question preview in question library.
 * @author ankur
 * @date 03 Sep 2016.
 * 
 */

/**
 * @summary function for showing pop up if user clicks on delete button.
 * @param questionId
 * @returns no.
 */
function deleteDraftedQuestion(questionId) {
	if (questionId > 0) {
		$("#dId").attr('onclick', 'ajaxDelete(' + questionId + ')');
		$('#deleteQuestionAlert').modal('show');
	}
}

/**
 * @summary function for ajax calling in case of question delete.
 * @param questionId
 * @returns no.
 */
var ajaxDelete = function(questionId) {
	if (questionId > 0) {
		$("#overlay").show();
		$.ajax({
			url : "deleteQuestionData?questionId=" + questionId,
			type : 'POST',
			error : function() {
				$("#overlay").hide();
				$("#questionErrorAlert p").text(messages['msg.somethingwentwrong']);
				$("#questionErrorAlert").modal('show');
			},
			success : function(status) {
				$("#overlay").hide();
				if (status) {
					location.href = 'questionList';
				} else {
					$("#questionErrorAlert p").text(messages['msg.deleteerror']);
					$("#questionErrorAlert").modal('show');
				}
			}
		});
	}

}

/**
 * @summary function for getting question's data through Ajax Calling.
 * @param questionId
 * @returns no.
 */
var questionPreview = function(questionId) {
	if (questionId > 0) {
		$("#overlay").show();
		$.ajax({
			url : 'questionPreview?questionId=' + questionId,
			type : 'GET',
			error : function() {
				$("#overlay").hide();
				$("#questionErrorAlert p").text(messages['msg.somethingwentwrong']);
				$("#questionErrorAlert").modal('show');
			},
			success : function(data) {
				addQuestionList(data);
				$("#overlay").hide();
				$("#questionPreview").modal('show');
			}
		});
	}
}

/**
 * @summary function for extracting data from json object.
 * @param quesData
 * @returns no.
 */
function addQuestionList(quesData) {
	$("#allquestion").empty();
	try {
		switch (quesData.questionType) {
		case MULTIPLE_CHOICE_TYPE:
			$("#questionType").html("Multi Choice");
			previewMultiChoiceQuestion(quesData);
			break;
		case SINGLE_CHOICE_TYPE:
			$("#questionType").html("Single Choice");
			previewMultiChoiceQuestion(quesData);
			break;
		case SORT_LIST_TYPE:
			$("#questionType").html("Sort List");
			sortListTypeQuestionPreview(quesData);
			break;
		case CHOICE_MATRIX_TYPE:
			$("#questionType").html("Choice Matrix");
			choiceMatrixTypeQuestionPreview(quesData);
			break;
		case CLASSIFICATION_TYPE:
			$("#questionType").html("Classification");
			classificationTypeQuestionPreview(quesData);
			break;
		case MATCH_LIST:
			$("#questionType").html("Match List");
			matchListTypeQuestionPreview(quesData);
			break;
		}
	} catch (err) {
		console.log(err.message);
	}

}

/**
 * @summary function for calling on upload file button.
 * @returns no.
 */
$("#uploadQuestionFile").click(function() {
	if (fileValidation()) {
		submitFile();
	}
});

/**
 * @summary function for file validation.
 * @returns {Boolean}
 */
var fileValidation = function() {
	var validExts = new Array(".xls", ".xlsx");
	var quesFile = document.getElementById("questionFile");
	if (($("#questionFile").val().trim()).length == 0) {
		$("#questionFileError").fadeIn();
		return false;
	}
	if (!checkfile(quesFile)) {
		$("#questionFileError").text(messages['msg.invalidfileselected'].replace("#filetype",validExts.toString()));
		$("#questionFileError").fadeIn();
		return false;
	}

	return true;
}

/**
 * @summary function for checking that excel file is valid or not.
 * @param sender
 * @returns {Boolean}
 */
function checkfile(sender) {
	var validExts = new Array(".xls", ".xlsx");
	var fileExt = sender.value;
	fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
	if (validExts.indexOf(fileExt) < 0) {
		return false;
	} else
		return true;
}

/**
 * @summary function for fading out the showing error for excel sheet.
 * @returns no.
 */
var keyValidate = function() {
	if (($("#questionFile").val().trim()).length > 0) {
		$("#questionFileError").fadeOut();
	}
}

/**
 * @summary function for uploading excel sheet through ajax.
 * @returns no.
 */
var submitFile = function() {
	var formData = new FormData();
	formData.append("questionFile", $("#questionFile").prop("files")[0]);
	formData.append("fileName", $("#questionFile").val());
	$("#overlay").show();
	$
			.ajax({
				type : 'POST',
				url : 'uploadQuestionFile',
				processData : false,
				contentType : false,
				data : formData,
				success : function(result) {
					$("#overlay").hide();
					if (result) {
						window.location.href = 'questionList';
					} else {
						$("#questionFileError").text(messages['msg.somethingwentwrong']);
						$("#questionFileError").fadeIn();
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$("#overlay").hide();
					var err = JSON.parse(xhr.responseText);
					$("#questionFileError").text(err.msg);
					$("#questionFileError").fadeIn();
				}
			});
}

/**
 * @summary This is used showing pop up for selecting question type.
 * @returns no.
 */
function showCreateQuestionPopUp() {
	$("#selectedQuestionType").val(MULTIPLE_CHOICE_TYPE);
	$("#createQuestionType").attr('onclick', 'showCreateQuestionPage()');
	$("#CreateQuestionPagePopUp").modal('show');
}

/**
 * @summary This is used for redirect on create question page based on question
 *          type.
 * @returns no.
 */
function showCreateQuestionPage() {
	var selectedQuestionType = $.trim($("#selectedQuestionType").val());
	location.href = 'addeditquestion?type=' + selectedQuestionType;
}