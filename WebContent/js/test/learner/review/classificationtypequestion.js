/**
 * @summary This is used for created classification type question for review a particular question.
 * @author ankur
 * @date 09 Sep 2016.
 * 
 */

/**
 * @summary This is used add classification type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addClassificationTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * append question in section.
		 */
		$("#allquestion").append(
				createClassificationTypeQuestion(secNo + 1, quesNo + 1));
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
		 * get total column
		 */
		var totalColumn = test.sectionlist[secNo].questionList[quesNo].questionSetting.questionUiStyle.columnTitles.length;
		/**
		 * get total row
		 */
		var totalRow = test.sectionlist[secNo].questionList[quesNo].questionSetting.questionUiStyle.rowTitles.length;
		/**
		 * iterate on column
		 */
		for (var columnNo = 0; columnNo < totalColumn; columnNo++) {
			/**
			 * append header name in table column
			 */
			$(
					'#' + (secNo + 1) + 'classificationtable' + (quesNo + 1)
							+ ' thead>tr')
					.append(
							"<th class='imgset text-align'>"
									+ test.sectionlist[secNo].questionList[quesNo].questionSetting.questionUiStyle.columnTitles[columnNo]
									+ "</th>");
		}
		var orderNo = 1;
		/**
		 * iterate on row
		 */
		for (var rowNo = 0; rowNo < totalRow; rowNo++) {
			/**
			 * generate row for table body
			 */
			var tableRow = "<tr><td class='imgset'>"
					+ test.sectionlist[secNo].questionList[quesNo].questionSetting.questionUiStyle.rowTitles[rowNo]
					+ "</td>";
			/**
			 * iterate on column
			 */
			for (var columnNo = 0; columnNo < totalColumn; columnNo++) {
				tableRow = tableRow
						+ "<td class='imgset text-align'><div class='cellOrderNumberInClassificationTypeQuestion'>"
						+ orderNo + "</div></td>";
				orderNo++;
			}
			tableRow = tableRow + "</tr>"
			/**
			 * append in table body.
			 */
			$(
					'#' + (secNo + 1) + 'classificationtable' + (quesNo + 1)
							+ ' tbody').append(tableRow);
		}
		/**
		 * get Instance of table
		 */
		var table = document.getElementById((secNo + 1) + 'classificationtable'
				+ (quesNo + 1));

		/**
		 * Iterate on answer value.
		 */
		for (var x = 0; x < userGivenAnswer.length; x++) {
			for (var y = 0; y < userGivenAnswer[x].length; y++) {
				for (var z = 0; z < userGivenAnswer[x][y].length; z++) {
					/**
					 * get option order.
					 */
					var optionNumber = userGivenAnswer[x][y][z];
					/**
					 * generate html for table cell according to answer value.
					 */
					var dragDiv = '<div class="answerDivInClassificationTypeQuestion bg-light-gray" id="'
							+ secNo
							+ 'answerDiv'
							+ (x + 1)
							+ 'In'
							+ optionNumber
							+ 'ClassificationType'
							+ (y + 1)
							+ 'Question'
							+ quesNo
							+ '"><div class="input-group"><div class="pull-left">'
							+ test.sectionlist[secNo].questionList[quesNo].optionList[optionNumber - 1].optionName
							+ '</div><div class="input-group-addon bg-light-gray"></div></div></div>';
					/**
					 * append in table cell.
					 */
					$(table.rows[x + 1].cells[y + 1]).append(dragDiv);
				}
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
				appendCorrectAnswerInClassificationTypeQuestion(secNo + 1,
						quesNo + 1);
			}
			/**
			 * checking that user's attempted question is correct.
			 */
			else if (test.sectionlist[secNo].questionList[quesNo].isCorrect == 1) {
				$(
						'#' + (secNo + 1) + 'answerStatusInclassificationtable'
								+ (quesNo + 1))
						.html(
								'<a class="btn btn-social-icon bg-green btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-check" style="margin-left: 4px;"  title="'+messages['lbl.correctanswer']+'"></i></a>');
				$(
						'#' + (secNo + 1) + 'answerStatusInclassificationtable'
								+ (quesNo + 1)).css('border-bottom',
						'3px solid green');
				$(table).closest("div.row").css('border', '1px solid green');
			} else {
				$(
						'#' + (secNo + 1) + 'answerStatusInclassificationtable'
								+ (quesNo + 1))
						.html(
								'<span class="btn btn-social-icon bg-red btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-close"  title="'+messages['lbl.wronganswer']+'" style="margin-left: 4px;"></i></span>');
				$(
						'#' + (secNo + 1) + 'answerStatusInclassificationtable'
								+ (quesNo + 1)).css('border-bottom',
						'3px solid #dd4b39');
				$(table).closest("div.row").css('border', '1px solid #dd4b39');
				appendCorrectAnswerInClassificationTypeQuestion(secNo + 1,
						quesNo + 1);
			}
			for (var x = 0; x < userGivenAnswer.length; x++) {
				for (var y = 0; y < userGivenAnswer[x].length; y++) {
					for (var z = 0; z < userGivenAnswer[x][y].length; z++) {
						var givenAnswer = userGivenAnswer[x][y][z];
						$(
								'#' + secNo + 'answerDiv' + (x + 1) + 'In'
										+ givenAnswer + 'ClassificationType'
										+ (y + 1) + 'Question' + quesNo)
								.removeClass("bg-light-gray");
						$(
								'#' + secNo + 'answerDiv' + (x + 1) + 'In'
										+ givenAnswer + 'ClassificationType'
										+ (y + 1) + 'Question' + quesNo
										+ ' .input-group .input-group-addon')
								.removeClass("bg-light-gray");
						if (answerValue[x][y].indexOf(givenAnswer) == -1) {
							$(
									'#' + secNo + 'answerDiv' + (x + 1) + 'In'
											+ givenAnswer
											+ 'ClassificationType' + (y + 1)
											+ 'Question' + quesNo).addClass(
									"bg-light-red");
							$(
									'#'
											+ secNo
											+ 'answerDiv'
											+ (x + 1)
											+ 'In'
											+ givenAnswer
											+ 'ClassificationType'
											+ (y + 1)
											+ 'Question'
											+ quesNo
											+ ' .input-group .input-group-addon')
									.addClass("bg-light-red");
							$(
									'#'
											+ secNo
											+ 'answerDiv'
											+ (x + 1)
											+ 'In'
											+ givenAnswer
											+ 'ClassificationType'
											+ (y + 1)
											+ 'Question'
											+ quesNo
											+ ' .input-group .input-group-addon')
									.html(
											"<i class='fa fa-close' style='color:#dd4b39'></i>");
						} else {
							$(
									'#' + secNo + 'answerDiv' + (x + 1) + 'In'
											+ givenAnswer
											+ 'ClassificationType' + (y + 1)
											+ 'Question' + quesNo).addClass(
									"bg-light-green");
							$(
									'#'
											+ secNo
											+ 'answerDiv'
											+ (x + 1)
											+ 'In'
											+ givenAnswer
											+ 'ClassificationType'
											+ (y + 1)
											+ 'Question'
											+ quesNo
											+ ' .input-group .input-group-addon')
									.addClass("bg-light-green");
							$(
									'#'
											+ secNo
											+ 'answerDiv'
											+ (x + 1)
											+ 'In'
											+ givenAnswer
											+ 'ClassificationType'
											+ (y + 1)
											+ 'Question'
											+ quesNo
											+ ' .input-group .input-group-addon')
									.html(
											"<i class='fa fa-check' style='color:green'></i>");
						}
					}
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
function createClassificationTypeQuestion(secNo, quesNo) {
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
			+ '<div class="row" style="text-align:center;margin:0px !important" id="'
			+ secNo
			+ 'answerStatusInclassificationtable'
			+ quesNo
			+ '"></div>'
			+ '<div class="row table-responsive" style="margin:0px;padding-right:15px;padding-bottom:10px">'
			+ '<table class="table classificationtable" id="'
			+ secNo
			+ 'classificationtable'
			+ quesNo
			+ '">'
			+ '<thead><tr><th></th></tr></thead>'
			+ '<tbody></tbody>'
			+ '</table>'
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
 * @summary This is used for appending correct answer in correct answer div.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function appendCorrectAnswerInClassificationTypeQuestion(secNo, quesNo) {
	/**
	 * get Instance of table
	 */
	var table = document.getElementById(secNo + 'classificationtable' + quesNo);
	var correctAnswerDiv = '<div class="correctAnswerDivInClassificationQuestion row" id="'
			+ secNo
			+ 'correctAnswerListInClassificationQuestion'
			+ quesNo
			+ '"><h4>'+messages['lbl.correctanswers']+':</h4></div>';
	$(table).closest("div.row").after(correctAnswerDiv);
	/**
	 * get answer value.
	 */
	var answerValue = JSON
			.parse(test.sectionlist[secNo - 1].questionList[quesNo - 1].answerValue);
	/**
	 * Iterate on answer value.
	 */
	var answerDivOrder = 1;
	for (var x = 0; x < answerValue.length; x++) {
		for (var y = 0; y < answerValue[x].length; y++) {
			var firstMainDiv = "<div class='correctAnswerContainDivInClassificationQuestion' id='"
					+ secNo
					+ "correctAnswerContainDiv"
					+ answerDivOrder
					+ "InClassificationQuestion"
					+ quesNo
					+ "'><div class='cellOrderNumberInClassificationTypeQuestion'>"
					+ answerDivOrder + "</div></div>";
			$(
					'#' + secNo + 'correctAnswerListInClassificationQuestion'
							+ quesNo).append(firstMainDiv);
			for (var z = 0; z < answerValue[x][y].length; z++) {
				/**
				 * get option order.
				 */
				var optionNumber = answerValue[x][y][z];
				/**
				 * generate html for table cell according to answer value.
				 */
				var dragDiv = '<div class="answerCellInClassificationTypeQuestion"><div class="input-group"><div class="pull-left">'
						+ test.sectionlist[secNo - 1].questionList[quesNo - 1].optionList[optionNumber - 1].optionName
						+ '</div></div></div>';
				/**
				 * append in table cell.
				 */
				$(
						"#" + secNo + "correctAnswerContainDiv"
								+ answerDivOrder + "InClassificationQuestion"
								+ quesNo).append(dragDiv);
			}
			answerDivOrder++;
		}
	}

}