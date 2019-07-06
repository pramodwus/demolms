/**
 * @summary This js file is used for performing all common operation related to creating and editing any type of question in question bank.
 * @author ankur
 * @date 23 Aug 2016.
 */

/**
 * @summary This is used creating empty question array.
 * @returns no
 */
function createQuestionArray() {
	var question = {
		"questionList" : [],
	};
	questionSectionList.push(question);
}

/**
 * @summary This is used for submitting the json of all created questions or
 *          updated question.
 * @returns no
 */
function submitQuestionDetails() {
	$("#clearquestionAlert").modal('hide');
	var callingURL = 'saveQuestion';
	if (updatedquestionId.length > 0 && updatedquestionId > 0) {
		callingURL = 'saveQuestion?questionId=' + updatedquestionId;
	}
	var data = JSON.stringify(questionSectionList);
	$("#overlay").show();
	$.ajax({
		type : 'POST',
		url : callingURL,
		data : 'sectionData=' + encodeURIComponent(data),
		success : function(status) {
			$("#overlay").hide();
			if (status) {
				$(window).off('beforeunload');
				window.location.href = 'questionList';
			} else {
				$('#questionErrorAlert').modal('show');
				$("#questionErrorAlert p").text(messages['msg.somethingwentwrong']);
			}
		},
		error : function() {
			$("#overlay").hide();
			$('#questionErrorAlert').modal('show');
			$("#questionErrorAlert p").text(messages['msg.somethingwentwrong']);
		}
	});
}

/**
 * @summary This is used for redirecting on editing page of question on based on
 *          question type but this would be used when user perform edit opertion
 *          from question bank list. So In this case user wants update only a
 *          particular question.
 * @param questionId
 * @returns no
 */
function editParticularQuestion(questionId) {
	for (var q = 0; q < questionSectionList[0].questionList.length; q++) {
		if (questionSectionList[0].questionList[q].questionId == questionId) {
			switch (questionSectionList[0].questionList[q].questionType) {
			case MULTIPLE_CHOICE_TYPE:
				addNextQuestion();
				fillQuestionData(questionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.multiplechoice']);
				$("#cancelQuestion").attr('onclick',
						'location.href="questionList"');
				$("#submitQuestion").text(messages['lbl.update']);
				$("#submitQuestion").attr('onclick',
						'updateMultiTypeQuestionPopUp("' + questionId + '")');
				break;
			case SINGLE_CHOICE_TYPE:
				addNextQuestion();
				fillQuestionData(questionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.singlechoice']);
				$("#cancelQuestion").attr('onclick',
						'location.href="questionList"');
				$("#submitQuestion").text(messages['lbl.update']);
				$("#submitQuestion").attr('onclick',
						'updateMultiTypeQuestionPopUp("' + questionId + '")');
				break;
			case SORT_LIST_TYPE:
				addSortListTypeQuestion();
				fillSortListTypeQuestionData(questionId);
				$("#question-type-header").text(messages['lbl.sortlist']);
				$(window).scrollTop(0);
				$("#cancelQuestion").attr('onclick',
						'location.href="questionList"');
				$("#submitQuestion").text(messages['lbl.update']);
				$("#submitQuestion")
						.attr(
								'onclick',
								'updateSortListTypeQuestionPopUp("'
										+ questionId + '")');
				break;
			case CHOICE_MATRIX_TYPE:
				addChoiceMatrixTypeQuestion();
				fillchoiceMatrixTypeQuestionData(questionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.choicematrix']);
				$("#cancelQuestion").attr('onclick',
						'location.href="questionList"');
				$("#submitQuestion").text(messages['lbl.update']);
				$("#submitQuestion").attr(
						'onclick',
						'updateChoiceMatrixTypeQuestionPopUp("' + questionId
								+ '")');
				break;
			case CLASSIFICATION_TYPE:
				addClassificationTypeQuestion();
				fillClassificationTypeQuestionData(questionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.classification']);
				$("#cancelQuestion").attr('onclick',
						'location.href="questionList"');
				$("#submitQuestion").text(messages['lbl.update']);
				$("#submitQuestion").attr(
						'onclick',
						'updateClassificationTypeQuestionPopUp("' + questionId
								+ '")');
				break;
			case MATCH_LIST:
				addMatchListTypeQuestion();
				fillMatchListTypeQuestionData(questionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.matchlist']);
				$("#cancelQuestion").attr('onclick',
						'location.href="questionList"');
				$("#submitQuestion").text(messages['lbl.update']);
				$("#submitQuestion").attr(
						'onclick',
						'updateMatchListTypeQuestionPopUp("' + questionId
								+ '")');
				break;
			}
		}
	}
}

/**
 * @summary This is used for redirect on create question page based on question
 *          type when user comes first time from question bank for creating the
 *          question based on chose question type.
 * @param selectedQuestionType
 * @returns no
 */
function showCreateQuestionPage(selectedQuestionType) {
	/**
	 * convert string into integer
	 */
	selectedQuestionType = parseInt(selectedQuestionType);
	/**
	 * make cases on question type.
	 */
	switch (selectedQuestionType) {
	/**
	 * if question type is multiple choice.
	 */
	case MULTIPLE_CHOICE_TYPE:
		$("#question-type-header").text(messages['lbl.multiplechoice']);
		addNextQuestion();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveMultiQuesPopUp()');
		break;
	/**
	 * if question type is single choice type.
	 */
	case SINGLE_CHOICE_TYPE:
		$("#question-type-header").text(messages['lbl.singlechoice']);
		addNextQuestion();
		$("#ansTypeButton").prop('checked', false);
		changeAnswerType();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveMultiQuesPopUp()');
		break;
	/**
	 * if question type is sort list type.
	 */
	case SORT_LIST_TYPE:
		$("#question-type-header").text(messages['lbl.sortlist']);
		addSortListTypeQuestion();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveSortListQuesPopUp()');
		break;
	/**
	 * if question type is choice matrix type.
	 */
	case CHOICE_MATRIX_TYPE:
		$("#question-type-header").text(messages['lbl.choicematrix']);
		addChoiceMatrixTypeQuestion();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveChoiceMatrixQuesPopUp()');
		break;
	/**
	 * if question type is classification type.
	 */
	case CLASSIFICATION_TYPE:
		$("#question-type-header").text(messages['lbl.classification']);
		addClassificationTypeQuestion();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveClassificationQuesPopUp()');
		break;
	/**
	 * if question type is match list type.
	 */
	case MATCH_LIST:
		$("#question-type-header").text(messages['lbl.matchlist']);
		addMatchListTypeQuestion();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveMatchListQuesPopUp()');
		break;
	default:
		$("#question-type-header").text(messages['lbl.multiplechoice']);
		addNextQuestion();
		$("#cancelQuestion").attr('onclick', 'location.href="questionList"');
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveMultiQuesPopUp()');

	}
	$(window).scrollTop(0);
}

/**
 * @summary This is used to convert the html into simple text.
 * @param strHtmlText
 * @returns strHtmlText
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