/**
 * @summary This file would be used for performing multiple choice type question operation in test.
 * @author ankur
 * @date 3 sep 2016
 */

/**
 * @summary This is used add multiple choice type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addMultipleChoiceTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * get section id
		 */
		var sectionId = test.sectionlist[secNo].sectionId;
		/**
		 * get question id
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * question type
		 */
		var questionType = test.sectionlist[secNo].questionList[quesNo].questionType;
		/**
		 * append question in question div.
		 */
		$("#allquestion").append(
				createMultipleChoiceQuestionList(secNo + 1, quesNo + 1));
		/**
		 * set question title.
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * iterate on option list.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].option.length; optn++) {
			/**
			 * get option id
			 */
			var optionid = test.sectionlist[secNo].questionList[quesNo].option[optn].optionId;
			/**
			 * add option in option list of question.
			 */
			$("#" + (secNo + 1) + "optionlist" + (quesNo + 1)).append(
					addMultipleChoiceOptions(sectionId, quesId, optionid,
							questionType));
			/**
			 * set option title.
			 */
			$("#" + sectionId + "option" + quesId + "title" + optionid)
					.html(
							test.sectionlist[secNo].questionList[quesNo].option[optn].optionName);
			/**
			 * add 64 in option order for making equal to ascii value.
			 */
			var optn1 = (64 + parseInt(optn + 1));
			/**
			 * making html .
			 */
			var optionOrder = "&#" + optn1 + ";";
			/**
			 * set option order.
			 */
			$("#" + sectionId + "option" + quesId + "Order" + optionid).html(
					optionOrder);
		}
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary Function for creating question html for appending in test
 *          dynamically.
 * @param secNo
 * @param quesNo
 * @returns {String}
 */
function createMultipleChoiceQuestionList(secNo, quesNo) {
	var questnString = '<div class="box-body questionAllDiv" style="display:none" id="'
			+ secNo
			+ 'questionDiv'
			+ quesNo
			+ '">'
			+ '<div style="border-bottom:0px solid #F0F0F0">'
			+ '<div id="'
			+ secNo
			+ 'questiontitle'
			+ quesNo
			+ '" class="imgset pull-left"></div>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div id="'
			+ secNo
			+ 'optionlist'
			+ quesNo
			+ '" class="singleMultiChoice">'
			+ '</div>'
			+ '</div>';
	return questnString;
}

/**
 * @summary function for adding option div in question.
 * @param secNo
 * @param quesNo
 * @returns {String}
 */
function addMultipleChoiceOptions(sectionId, quesId, optionid, questionType) {
	var optionString = '<div class="option-click box tools pull-left input-group" style="border-top:0px">'
			+ '<p class="input-group-addon" onclick="clickAnswer('
			+ sectionId
			+ ','
			+ quesId
			+ ','
			+ optionid
			+ ','
			+ questionType
			+ ');" style="cursor:pointer"> <span class="badge button-color-blue bagde-style" id="'
			+ sectionId
			+ 'option'
			+ quesId
			+ 'Order'
			+ optionid
			+ '"></span></p>'
			+ '<div class="pull-left imgset" id="'
			+ sectionId
			+ 'option'
			+ quesId
			+ 'title'
			+ optionid
			+ '"></div>'
			+ '<div class="clearfix"></div>' + '</div>';
	return optionString;
}

/**
 * @summary function for selecting or un selecting the option by user.
 * @param sectionId
 * @param quesId
 * @param optionid
 * @returns no.
 */
function clickAnswer(sectionId, quesId, optionid, questionType) {
	if (questionType == SINGLE_CHOICE_TYPE) {
		if ($('#' + sectionId + 'option' + quesId + 'Order' + optionid)
				.hasClass("button-color-blue")) {
			var object = $(
					'#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.closest('.singleMultiChoice');
			$(object).find('.button-green-color').removeClass(
					"button-green-color").addClass('button-color-blue');
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.removeClass("button-color-blue");
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.addClass("button-green-color");
			saveSingleChoiceUserResponseInJson(sectionId, quesId, optionid);
		} else {
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.addClass("button-color-blue");
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.removeClass("button-green-color");
			clearUserResponsefromJson(sectionId, quesId, optionid);
		}
	} else {
		if ($('#' + sectionId + 'option' + quesId + 'Order' + optionid)
				.hasClass("button-color-blue")) {
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.removeClass("button-color-blue");
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.addClass("button-green-color");
			saveUserResponseInJson(sectionId, quesId, optionid);
		} else {
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.addClass("button-color-blue");
			$('#' + sectionId + 'option' + quesId + 'Order' + optionid)
					.removeClass("button-green-color");
			clearUserResponsefromJson(sectionId, quesId, optionid);
		}
	}
}

/**
 * @summary function for saving the user clicking answer in json
 * @param sectionId
 * @param quesId
 * @param optionid
 * @returns no.
 */
function saveUserResponseInJson(sectionId, quesId, optionid) {
	var quesPosition = 0;
	var isExist = false;
	var secPosition = findSectionIdInList(sectionId);
	for (var ques = 0; ques < submitTestJson.sectionlist[secPosition].questionList.length; ques++) {
		if (quesId == submitTestJson.sectionlist[secPosition].questionList[ques].questionId) {
			isExist = true;
			quesPosition = ques;
		}
	}
	if (isExist) {
		submitTestJson.sectionlist[secPosition].questionList[quesPosition].givenAnswer
				.push(optionid);
	} else {
		var response = {
			"questionId" : quesId,
			"givenAnswer" : [ optionid ]
		};
		submitTestJson.sectionlist[secPosition].questionList.push(response);
	}
}

/**
 * @summary function for saving the user clicking answer in json
 * @param sectionId
 * @param quesId
 * @param optionid
 * @returns no.
 */
function saveSingleChoiceUserResponseInJson(sectionId, quesId, optionid) {
	var quesPosition = 0;
	var isExist = false;
	var secPosition = findSectionIdInList(sectionId);
	for (var ques = 0; ques < submitTestJson.sectionlist[secPosition].questionList.length; ques++) {
		if (quesId == submitTestJson.sectionlist[secPosition].questionList[ques].questionId) {
			isExist = true;
			quesPosition = ques;
		}
	}
	if (isExist) {
		submitTestJson.sectionlist[secPosition].questionList[quesPosition].givenAnswer = [ optionid ];
	} else {
		var response = {
			"questionId" : quesId,
			"givenAnswer" : [ optionid ]
		};
		submitTestJson.sectionlist[secPosition].questionList.push(response);
	}
}
/**
 * @summary function for clear unselect option by user from json object.
 * @param sectionId
 * @param quesId
 * @param optionid
 * @returns no.
 */
function clearUserResponsefromJson(sectionId, quesId, optionid) {
	var isExist = false;
	var quesPosition = 0;
	var secPosition = findSectionIdInList(sectionId);
	for (var ques = 0; ques < submitTestJson.sectionlist[secPosition].questionList.length; ques++) {
		if (quesId == submitTestJson.sectionlist[secPosition].questionList[ques].questionId) {
			isExist = true;
			quesPosition = ques;
		}
	}
	if (isExist) {
		for (var opt = 0; opt < submitTestJson.sectionlist[secPosition].questionList[quesPosition].givenAnswer.length; opt++) {

			if (optionid == submitTestJson.sectionlist[secPosition].questionList[quesPosition].givenAnswer[opt]) {
				submitTestJson.sectionlist[secPosition].questionList[quesPosition].givenAnswer
						.splice(opt, 1);
			}
		}
	}
}