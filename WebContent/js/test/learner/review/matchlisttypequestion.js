/**
 * @summary This is used for created match List type question for review a particular question.
 * @author ankur
 * @date 14 Sep 2016.
 * 
 */

/**
 * @summary This is used add match List type question.
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
		 * set title of question.
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
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
		 * get user given answer value.
		 */
		var userGivenAnswer = test.sectionlist[secNo].questionList[quesNo].userGivenAnswer;
		/**
		 * get total list item
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
		var orderNo = 1;

		/**
		 * Iterate on given answer value.
		 */
		for (var x = 0; x < userGivenAnswer.length; x++) {
			var optionNumber = userGivenAnswer[x];
			if (optionNumber != 0) {
				$(
						'#' + (secNo + 1) + '_match_list_type_ques_'
								+ (quesNo + 1) + '_dropzone_' + (x + 1)
								+ ' .match_list_option_text')
						.html(
								test.sectionlist[secNo].questionList[quesNo].optionList[optionNumber - 1].optionName);
			}
		}
		/**
		 * check review with correct answer is enabled.
		 */
		if (test.reviewWithCorrect == 1) {
			/**
			 * checking that user has skipped this question.
			 */
			if (test.sectionlist[secNo].questionList[quesNo].isCorrect == -1) {
				appendCorrectAnswerInMatchListTypeQuestion(secNo + 1,
						quesNo + 1);
			}
			/**
			 * checking that user's attempted question is correct.
			 */
			else if (test.sectionlist[secNo].questionList[quesNo].isCorrect == 1) {
				$(
						'#' + (secNo + 1) + 'answerStatusInMatchListtable'
								+ (quesNo + 1))
						.html(
								'<a class="btn btn-social-icon bg-green btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-check" style="margin-left: 4px;"  title="'+messages['lbl.correctanswer']+'"></i></a>');
				$(
						'#' + (secNo + 1) + 'answerStatusInMatchListtable'
								+ (quesNo + 1)).css('border-bottom',
						'3px solid green');
				$('#' + (secNo + 1) + 'matchListTypeOptionList' + (quesNo + 1))
						.css('border', '1px solid green');
			} else {
				$(
						'#' + (secNo + 1) + 'answerStatusInMatchListtable'
								+ (quesNo + 1))
						.html(
								'<span class="btn btn-social-icon bg-red btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-close"  title="'+messages['lbl.wronganswer']+'" style="margin-left: 4px;"></i></span>');
				$(
						'#' + (secNo + 1) + 'answerStatusInMatchListtable'
								+ (quesNo + 1)).css('border-bottom',
						'3px solid #dd4b39');
				$('#' + (secNo + 1) + 'matchListTypeOptionList' + (quesNo + 1))
						.css('border', '1px solid #dd4b39');
				appendCorrectAnswerInMatchListTypeQuestion(secNo + 1,
						quesNo + 1);
			}

			for (var x = 0; x < userGivenAnswer.length; x++) {
				var givenAnswer = userGivenAnswer[x];
				var correctAnswer = answerValue[x];
				$(
						'#' + (secNo + 1) + '_match_list_type_ques_'
								+ (quesNo + 1) + '_dropzone_' + (x + 1))
						.removeClass('border-blue');
				$(
						'#' + (secNo + 1) + '_match_list_type_ques_'
								+ (quesNo + 1) + '_dropzone_' + (x + 1)
								+ ' .match_type_ques_responseIndex')
						.removeClass('bg-blue');
				if (givenAnswer == 0) {
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ ' .match_type_ques_responseIndex')
							.addClass('bg-dark-gray');
				} else if (givenAnswer == correctAnswer) {
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1))
							.addClass('border-green bg-light-green');
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ ' .match_type_ques_responseIndex')
							.addClass('bg-green');
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ " .input-group-addon").addClass(
							'bg-light-green');
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ " .input-group-addon").html(
							'<i class="fa fa-check" style="color:green" ></i>');
				} else {
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1))
							.addClass('border-red bg-light-red');
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ ' .match_type_ques_responseIndex')
							.addClass('bg-red');
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ " .input-group-addon").addClass(
							'bg-light-red');
					$(
							'#' + (secNo + 1) + '_match_list_type_ques_'
									+ (quesNo + 1) + '_dropzone_' + (x + 1)
									+ " .input-group-addon")
							.html(
									'<i class="fa fa-close" style="color:#dd4b39" ></i>');
				}

			}
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
			+ '<div class="row no-margin">'
			+ '<div id="'
			+ secNo
			+ 'questiontitle'
			+ quesNo
			+ '" class="imgset pull-left"></div>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div class="row" style="text-align:center;margin:0px !important" id="'
			+ secNo
			+ 'answerStatusInMatchListtable'
			+ quesNo
			+ '"></div>'

			+ '<div class="row match_list_assoc_table form-group" id="'
			+ secNo
			+ 'matchListTypeOptionList'
			+ quesNo
			+ '">'
			+ '</div>'

			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group" id="'
			+ secNo
			+ 'explaindiv'
			+ quesNo
			+ '" style="display:none;border-color: green;border-style: solid;border-width: 2px;">'
			+ '<h3>'+messages['lbl.explanation']+'</h3><p id="'
			+ secNo
			+ 'ansexplain'
			+ quesNo
			+ '"> </p></div>'
			+ '<div>'
			+ '<nav>'
			+ '<ul class="pager pull-left">'
			+ '<li style=""><button type="button" onclick="quit()" class="btn btn-flat btn-success button-width">'
			+ '<i class="fa fa-close">&nbsp;&nbsp;&nbsp;</i>'+messages['lbl.quit']+'</button></li>'
			+ '</ul>'
			+ '<ul class="pager pull-right" style="">'
			+ '<li style="padding-right:12px;">'
			+ '<button id="'
			+ secNo
			+ 'explain'
			+ quesNo
			+ '" class="btn btn-success btn-flat  button-width" onclick="showexplaination('
			+ secNo
			+ ','
			+ quesNo
			+ ')" >'+messages['lbl.explanation']+'</button>'
			+ '<li style="padding-right:12px;"><button type="button" id="'
			+ secNo
			+ 'previous'
			+ quesNo
			+ '" class="btn btn-flat btn-success button-width"	 onclick="previous('
			+ secNo
			+ ','
			+ quesNo
			+ ')" disabled>'
			+ '<i class="fa fa-backward"></i>&nbsp;'+messages['lbl.previous']+''
			+ '</button></li>'
			+ '<li style="padding-right:12px;"><button type="button" class="btn btn-success btn-flat button-width" id="'
			+ secNo
			+ 'next'
			+ quesNo
			+ '" onclick="next('
			+ secNo
			+ ','
			+ quesNo
			+ ')">'
			+ '<i class="fa fa-forward"></i>&nbsp;'+messages['lbl.next']+'</button></li>'
			+ '</ul>'
			+ '</nav>' + '</div>' + '</div>';
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

			+ '<div class="input-group match_list_type_ques_col2 match_list_type_question_res_container match_list_type_ques_dropzone border-blue" id="'
			+ secNo + '_match_list_type_ques_' + quesNo + '_dropzone_'
			+ optionNo + '">'
			+ '<div class="match_type_ques_responseIndex bg-blue">' + optionNo
			+ '</div>' + '<div class="match_list_option_text"></div>'
			+ '<div class="input-group-addon"></div>' + '</div>'

			+ '</div>'
	return matchListItem;
}

/**
 * @summary This is used for appending correct answer in correct answer div.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function appendCorrectAnswerInMatchListTypeQuestion(secNo, quesNo) {
	var correctAnswerDiv = '<div class="correctAnswerDivInMatchListQuestion row" id="'
			+ secNo
			+ 'correctAnswerListInMatchListQuestion'
			+ quesNo
			+ '"><h4>'+messages['lbl.correctanswers']+':</h4></div>';
	$('#' + secNo + 'matchListTypeOptionList' + quesNo).after(correctAnswerDiv);
	/**
	 * get answer value.
	 */
	var answerValue = JSON
			.parse(test.sectionlist[secNo - 1].questionList[quesNo - 1].answerValue);
	/**
	 * Iterate on answer value.
	 */
	for (var x = 0; x < answerValue.length; x++) {
		/**
		 * get option order.
		 */
		var optionNumber = answerValue[x];

		var firstMainDiv = "<div class='correctAnswerContainDivInMatchListQuestion'>"

				+ "<div class='cellOrderNumberInMatchListTypeQuestion'>"
				+ (x + 1)
				+ "</div>"

				+ "<div class='answerCellInMatchListTypeQuestion'>"
				+ "<div class='input-group'>"
				+ "<div class='pull-left'>"
				+ test.sectionlist[secNo - 1].questionList[quesNo - 1].optionList[optionNumber - 1].optionName
				+ "</div>" + "</div>" + "</div>"

				+ "</div>";

		$('#' + secNo + 'correctAnswerListInMatchListQuestion' + quesNo)
				.append(firstMainDiv);
	}

}