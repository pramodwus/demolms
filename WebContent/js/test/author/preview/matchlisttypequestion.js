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
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * get answer value.
		 */
		var answerValue = JSON
				.parse(test.sectionlist[secNo].questionList[quesNo].answerValue);
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
		 * Iterate on answer value.
		 */
		for (var x = 0; x < answerValue.length; x++) {
			var answerOrder = answerValue[x];
			$(
					'#' + (secNo + 1) + '_match_list_type_ques_' + (quesNo + 1)
							+ '_dropzone_' + (x + 1))
					.html(
							test.sectionlist[secNo].questionList[quesNo].option[answerOrder - 1].optionName);
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
			+ '<div class="col-xs-12 form-group" id="'
			+ secNo
			+ 'description'
			+ quesNo
			+ '" style="border-color: green;border-style: solid;border-width: 2px;">'
			+ '<h3>'
			+ messages['lbl.explanation']
			+ '</h3><p id="'
			+ secNo
			+ 'ansexplain'
			+ quesNo
			+ '"> </p></div>' + '</div>';
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
			+ secNo + '_match_list_type_ques_' + quesNo + '_dropzone_'
			+ optionNo + '">' + '</div>' + '</div>'
	return matchListItem;
}