/**
 * @summary This is used for created match list type question for preview a particular question.
 * @author ankur
 * @date 14 Sep 2016.
 * 
 */

/**
 * @summary This is used add match list type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addMatchListTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * append question in section.
		 */
		$("#allquestion").append(
				createMatchListTypeQuestion(secNo + 1, quesNo + 1));
		/**
		 * set question title
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * get list item length
		 */
		var totalListItem = test.sectionlist[secNo].questionList[quesNo].questionSetting.questionUiStyle.listItemTitles.length;

		/**
		 * iterate on list
		 */
		for (var listItemNo = 0; listItemNo < totalListItem; listItemNo++) {
			/**
			 * append matching list
			 */
			$("#" + (secNo + 1) + "matchListTypeOptionList" + (quesNo + 1))
					.append(
							addListItemInMatchingList(secNo + 1, quesNo + 1,
									listItemNo + 1));
			/**
			 * set html in list item.
			 */
			$(
					'#' + (secNo + 1) + '_match_list_type_' + (quesNo + 1)
							+ '_question_' + (listItemNo + 1))
					.html(
							test.sectionlist[secNo].questionList[quesNo].questionSetting.questionUiStyle.listItemTitles[listItemNo]);
		}

		/**
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].option.length; optn++) {
			var dragAnswer = '<div class="answerDivInMatchListTypeQuestion" id="'
					+ (secNo + 1)
					+ 'draggedOption'
					+ (optn + 1)
					+ 'InMatchListTypeQuestion'
					+ (quesNo + 1)
					+ '" draggable="true" ondragstart="dragInMatchListQuestionType(event)"><div class="input-group"><div class="input-group-addon"><i class="fa fa-align-justify"></i></div><div class="pull-left" id="'
					+ (secNo + 1)
					+ 'optionDrag'
					+ (optn + 1)
					+ 'InMatchListTypeQuestion'
					+ (quesNo + 1)
					+ '">'
					+ test.sectionlist[secNo].questionList[quesNo].option[optn].optionName
					+ '</div></div></div>';
			$("#" + (secNo + 1) + "matchListOptionsDivForDrag" + (quesNo + 1))
					.append(dragAnswer);
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
function createMatchListTypeQuestion(secNo, quesNo) {
	var questnString = '<div class="box-body questionAllDiv" style="display:none" id="'
			+ secNo
			+ 'questionDiv'
			+ quesNo
			+ '">'
			+ '<div>'
			+ '<div id="'
			+ secNo
			+ 'questiontitle'
			+ quesNo
			+ '" class="imgset pull-left"></div>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'

			+ '<div class="row match_list_assoc_table form-group" id="'
			+ secNo
			+ 'matchListTypeOptionList'
			+ quesNo
			+ '">'
			+ '</div>'

			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group">'
			+ '<div class="row triangle"></div>'
			+ '<div class="row matchList_drag" style="min-height:100px;background-color:#ccc;padding:10px" id="'
			+ secNo
			+ 'matchListOptionsDivForDrag'
			+ quesNo
			+ '" ondrop="dropInMatchListQuestionType(event)" ondragover="allowDropInMatchListQuestionType(event)"></div>'
			+ '</div>' + '</div>';
	return questnString;
}

/**
 * @summary This is used to create matching list.
 * @param secNo
 * @param quesNo
 * @param optionNo
 * @returns {String}
 */
function addListItemInMatchingList(secNo, quesNo, optionNo) {

	var matchListItem = '<div class="match_list_type_ques_row" id="'
			+ secNo
			+ '_match_list_type_'
			+ quesNo
			+ '_ques_row_'
			+ optionNo
			+ '">'
			+ '<div class="match_list_type_ques_col2">'
			+ '<div class="match_list_type_question" id="'
			+ secNo
			+ '_match_list_type_'
			+ quesNo
			+ '_question_'
			+ optionNo
			+ '"></div>'
			+ '</div>'
			+ '<div class="match_list_type_ques_col1 match_list_type_ques_arrows">'
			+ '<div class="match_list_type_ques_arrow"></div>'
			+ '</div>'
			+ '<div class="match_list_type_ques_col2 match_list_type_question_res_container match_list_type_ques_dropzone" id="'
			+ secNo
			+ '_match_list_type_ques_'
			+ quesNo
			+ '_dropzone_'
			+ optionNo
			+ '" ondrop="dropInMatchListQuestionType(event)" ondragover="allowDropInMatchListQuestionType(event)">'
			+ '</div>' + '</div>'
	return matchListItem;
}

/**
 * @summary This function allow and element for drop.
 * @param ev
 * @returns no.
 */
function allowDropInMatchListQuestionType(ev) {
	ev.preventDefault();
}

/**
 * @summary This function is used get the activity when user start drag an
 *          element.
 * @param ev
 * @returns no.
 */
function dragInMatchListQuestionType(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

/**
 * @summary This function is used for performing actual action when user drops
 *          an element.
 * @param ev
 * @returns no.
 */
function dropInMatchListQuestionType(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");

	if ($(ev.target).hasClass('match_list_type_ques_dropzone')) {
		var droppedElement = $(ev.target).find(
				'.answerDivInMatchListTypeQuestion').length;
		if (droppedElement == 0) {
			ev.target.appendChild(document.getElementById(data));
			/**
			 * get id of target div.
			 */
			var targetTableId = $(ev.target).closest(".match_list_assoc_table")
					.attr('id');
			var arr = targetTableId.split('matchListTypeOptionList');
			var secNo = parseInt(arr[0]);
			var quesNo = parseInt(arr[1]);
			/**
			 * perform required action now.
			 */
			performedActionInMatchListTypeQuestion(secNo, quesNo);
		}
	}
	/**
	 * checking that in target element is same or different.
	 */
	else if ($(ev.target).hasClass('matchList_drag')) {
		/**
		 * append dragged element inside target element.
		 */
		ev.target.appendChild(document.getElementById(data));
		/**
		 * get id of source div.
		 */
		var sourceId = $(ev.target).closest(".matchList_drag").attr('id');
		var arr = sourceId.split('matchListOptionsDivForDrag');
		var secNo = parseInt(arr[0]);
		var quesNo = parseInt(arr[1]);
		/**
		 * perform required action now.
		 */
		performedActionInMatchListTypeQuestion(secNo, quesNo);
	}

}

/**
 * @summary This function is used to extract information about dropped element
 *          and target element.
 * @param tableId
 * @returns no.
 */
function performedActionInMatchListTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * get total list item
		 */
		var totalListItem = test.sectionlist[secNo - 1].questionList[quesNo - 1].questionSetting.questionUiStyle.listItemTitles.length;
		var answerValue = [];
		for (var listItemNo = 1; listItemNo <= totalListItem; listItemNo++) {
			var itemLength = $(
					"#" + secNo + "_match_list_type_ques_" + quesNo
							+ "_dropzone_" + listItemNo).find(
					'.answerDivInMatchListTypeQuestion').length;
			if (itemLength > 0) {
				$(
						"#" + secNo + "_match_list_type_ques_" + quesNo
								+ "_dropzone_" + listItemNo).find(
						'.answerDivInMatchListTypeQuestion').each(function() {
					var id = $(this).attr('id');
					var arr1 = id.split("draggedOption");
					var arr2 = arr1[1].split("InMatchListTypeQuestion");
					var correctOrder = parseInt(arr2[0]);
					answerValue.push(correctOrder);
				});
			} else {
				answerValue.push(0);
			}
		}
		/**
		 * save response of user.
		 */
		saveMatchListTypeQuestionUserResponseInJson(secNo, quesNo, answerValue);
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary This is used save the response into json of match list type
 *          question..
 * @param secNo
 * @param quesNo
 * @param answerValue
 * @returns no.
 */
function saveMatchListTypeQuestionUserResponseInJson(secNo, quesNo, answerValue) {
	try {
		var quesPosition = 0;
		var isExist = false;
		var sectionId = test.sectionlist[secNo - 1].sectionId;
		var quesId = test.sectionlist[secNo - 1].questionList[quesNo - 1].questionId;
		var secPosition = findSectionIdInList(sectionId);
		for (var ques = 0; ques < submitTestJson.sectionlist[secPosition].questionList.length; ques++) {
			if (quesId == submitTestJson.sectionlist[secPosition].questionList[ques].questionId) {
				isExist = true;
				quesPosition = ques;
			}
		}
		if (isExist) {
			submitTestJson.sectionlist[secPosition].questionList[quesPosition].userGivenAnswer = answerValue;
		} else {
			var response = {
				"questionId" : quesId,
				"userGivenAnswer" : answerValue
			};
			submitTestJson.sectionlist[secPosition].questionList.push(response);
		}
	} catch (err) {
		console.log(err.message);
	}
}