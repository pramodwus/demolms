/**
 * @summary This is used for created match list type question for preview a particular question.
 * @author ankur
 * @date 14 Sep 2016.
 * 
 */

/**
 * @summary This is used for adding match list type question.
 * @returns no.
 */
function matchListTypeQuestionPreview(quesData) {

	try {
		/**
		 * append question in question list
		 */
		$("#allquestion").append(createMatchListTypeQuestionList());
		/**
		 * set title of question.
		 */
		$("#questiontitle").html(quesData.questionName);
		/**
		 * get answer value.
		 */
		var answerValue = JSON.parse(quesData.answerValue);
		/**
		 * get total items in match list answer list.
		 */
		var totalListItem = quesData.questionSetting.questionUiStyle.listItemTitles.length;

		/**
		 * iterate on list
		 */
		for (var listItemNo = 0; listItemNo < totalListItem; listItemNo++) {
			/**
			 * append matching list
			 */
			$("#optionlist").append(addListItemInMatchingList(listItemNo + 1));
			/**
			 * set html in list item.
			 */
			$('#match_list_type_question_' + (listItemNo + 1))
					.html(
							quesData.questionSetting.questionUiStyle.listItemTitles[listItemNo]);
		}

		/**
		 * iterate on correct answer value.
		 */
		for (var x = 0; x < answerValue.length; x++) {
			var answerOrder = answerValue[x];
			$('#match_list_type_ques_dropzone_' + (x + 1)).html(
					quesData.option[answerOrder - 1].optionName);
		}
		/**
		 * checking question's explanation is not null.
		 */
		quesData.explanation == null ? $("#description").hide()
				: (quesData.explanation.trim().length == 0 ? $("#description")
						.hide() : $("#ansexplain").html(quesData.explanation));
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary Function for creating question html for appending in test
 *          dynamically.
 * @returns {String}.
 */
function createMatchListTypeQuestionList() {
	var questnString = '<div class="box-body box-left-margin questionAllDiv" id="questionDiv">'
			+ '<div>'
			+ '<h5 id="questiontitle" class="imgset"></h5>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div class="row match_list_assoc_table form-group" id="optionlist">'
			+ '</div>'
			+ '<div class="col-xs-12" id="description" style="border-color: green;border-style: solid;border-width: 1px;">'
			+ '<h3>'+ messages['lbl.explanation']+'</h3><p id="ansexplain"></p></div>' + '</div>';

	return questnString;

}

/**
 * @summary This is used to create matching list.
 * @param optionNo
 * @returns {String}
 */
function addListItemInMatchingList(optionNo) {

	var matchListItem = '<div class="match_list_type_ques_row" id="match_list_type_ques_row_'
			+ optionNo
			+ '">'
			+ '<div class="match_list_type_ques_col2">'
			+ '<div class="match_list_type_question" id="match_list_type_question_'
			+ optionNo
			+ '"></div>'
			+ '</div>'
			+ '<div class="match_list_type_ques_col1 match_list_type_ques_arrows">'
			+ '<div class="match_list_type_ques_arrow"></div>'
			+ '</div>'
			+ '<div class="match_list_type_ques_col2 match_list_type_question_res_container match_list_type_ques_dropzone" id="match_list_type_ques_dropzone_'
			+ optionNo + '">' + '</div>' + '</div>'
	return matchListItem;
}