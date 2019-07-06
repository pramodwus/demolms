/**
 * @summary This is used for created sort list type question for review page.
 * @author ankur
 * @date 19 Aug 2016.
 * 
 */

/**
 * @summary This is used for adding sort list type question.
 * @returns no.
 */
function addSortListTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * Add question html in question list.
		 */
		$("#allquestion").append(
				createSortListTypeQuestionList(secNo + 1, quesNo + 1));
		/**
		 * set question html.
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * correct answer value
		 */
		var answerValue = JSON
				.parse(test.sectionlist[secNo].questionList[quesNo].answerValue);
		/**
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].optionList.length; optn++) {
			/**
			 * append given option in given option table body.
			 */
			$("#" + (secNo + 1) + "usergivenoptionlist" + (quesNo + 1))
					.append(
							addSortListTypeGivenOptions(secNo + 1, quesNo + 1,
									optn + 1));
			/**
			 * append correct option in correct option table body.
			 */
			$("#" + (secNo + 1) + "usercorrectoptionlist" + (quesNo + 1))
					.append(
							addSortListTypeCorrectOptions(secNo + 1,
									quesNo + 1, answerValue[optn], optn + 1));
			/**
			 * If user has attempted this question.
			 */
			if (test.sectionlist[secNo].questionList[quesNo].givenAnswer.length > 0) {
				/**
				 * get the given order from given answer of this option.
				 */
				var givenOrder = test.sectionlist[secNo].questionList[quesNo].givenAnswer[optn];
				/**
				 * get the correct order from correct answer value of this
				 * option.
				 */
				var correctOrder = answerValue[optn];
				/**
				 * If user has given the order for this option.
				 */
				if (givenOrder != 0) {
					/**
					 * generate html for appending in given option table.
					 */
					var html = '<div class="col-sm-12 lrn_btn_sort" id="optionOrder'
							+ (optn + 1)
							+ 'InQuestion'
							+ (quesNo + 1)
							+ 'InSection'
							+ (secNo + 1)
							+ '">'
							+ '<div class="input-group" id="'
							+ (secNo + 1)
							+ 'input-'
							+ (quesNo + 1)
							+ '-group'
							+ (optn + 1)
							+ '">'
							+ '<div class="input-group-addon">'
							+ (optn + 1)
							+ '</div>'
							+ '<div class="imgset pull-left padding-left" id="">'
							+ test.sectionlist[secNo].questionList[quesNo].optionList[givenOrder - 1].optionName
							+ '</div>' + '</div>' + '</div>';
					/**
					 * append html in cell of given option table.
					 */
					$(
							'#' + (secNo + 1) + 'given' + (quesNo + 1) + 'row'
									+ (optn + 1)).html(html);
					/**
					 * if review with correct answer is enable.
					 */
					if (test.reviewWithCorrect == 1) {
						/**
						 * if user has submitted this option on correct order.
						 */
						if (givenOrder == correctOrder) {
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)).addClass(
									"border-green");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)
											+ ' .input-group-addon:first')
									.addClass("bg-green");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1))
									.append(
											'<div class="input-group-addon"><i class="fa fa-check" style="color:green" ></i></div>');
						}
						/**
						 * if user has submitted this option on wrong order.
						 */
						else {
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)).addClass(
									"border-red");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)
											+ ' .input-group-addon:first')
									.addClass("bg-red");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1))
									.append(
											'<div class="input-group-addon"><i class="fa fa-close" style="color:#dd4b39" ></i></div>');
						}
					}
					/**
					 * if review with correct answer is not enable.
					 */
					else {
						$(
								'#' + (secNo + 1) + 'input-' + (quesNo + 1)
										+ '-group' + (optn + 1)).addClass(
								"border-blue");
						$(
								'#' + (secNo + 1) + 'input-' + (quesNo + 1)
										+ '-group' + (optn + 1)
										+ ' .input-group-addon:first')
								.addClass("bg-blue");
					}
				} else {
					/**
					 * generate html for appending in correct option table.
					 */
					var html = '<div class="col-sm-12 lrn_btn_sort" id="optionOrder'
							+ (optn + 1)
							+ 'InQuestion'
							+ (quesNo + 1)
							+ 'InSection'
							+ (secNo + 1)
							+ '">'
							+ '<div class="input-group input-group-first" id="'
							+ (secNo + 1)
							+ 'input-'
							+ (quesNo + 1)
							+ '-group'
							+ (optn + 1)
							+ '">'
							+ '<div class="input-group-addon">'
							+ (optn + 1)
							+ '</div>'
							+ '<div class="imgset pull-left padding-left" id="">'
							+ '</div>' + '</div>' + '</div>';
					/**
					 * append html in correct option table.
					 */
					$(
							'#' + (secNo + 1) + 'given' + (quesNo + 1) + 'row'
									+ (optn + 1)).html(html);
				}
			}
		}
		/**
		 * checking review with correct answer is enabled.
		 */
		if (test.reviewWithCorrect == 1) {
			/**
			 * if user has skipped this question.
			 */
			if (test.sectionlist[secNo].questionList[quesNo].isCorrect == -1) {
			}
			/**
			 * if user has submitted correct answers for this question.
			 */
			else if (test.sectionlist[secNo].questionList[quesNo].isCorrect == 1) {
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1))
						.html(
								'<a class="btn btn-social-icon bg-green btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-check" style="margin-left: 4px;"  title="'+messages['lbl.correctanswer']+'"></i></a>');
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1)).css(
						'border-bottom', '3px solid green');
				$('#' + (secNo + 1) + 'optionlist' + (quesNo + 1)).css(
						'border', '1px solid green');
			}
			/**
			 * if user has submitted wrong answer.
			 */
			else {
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1))
						.html(
								'<span class="btn btn-social-icon bg-red btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-close"  title="'+messages['lbl.wronganswer']+'" style="margin-left: 4px;"></i></span>');
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1)).css(
						'border-bottom', '3px solid #dd4b39');
				$('#' + (secNo + 1) + 'optionlist' + (quesNo + 1)).css(
						'border', '1px solid #dd4b39');
			}
		}
		/**
		 * if review with correct answer is not enabled.
		 */
		else {
			/**
			 * remove table of correct answer from question.
			 */
			$("#" + (secNo + 1) + "correct_answer_table" + (quesNo + 1))
					.remove();
		}
	} catch (err) {
		console.log(err.message)
	}
}

/**
 * @summary Function for creating question html for appending in test
 *          dynamically.
 * @returns no.
 */
function createSortListTypeQuestionList(secNo, quesNo) {
	var questnString = '<div class="box-body box-left-margin questionAllDiv" style="display:none" id="'
			+ secNo
			+ 'questionDiv'
			+ quesNo
			+ '">'
			+ '<div>'
			+ '<h5 id="'
			+ secNo
			+ 'questiontitle'
			+ quesNo
			+ '" class="imgset"></h5>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div class="row" style="text-align:center;margin:0px !important" id="'
			+ secNo
			+ 'answerStatus'
			+ quesNo
			+ '"></div>'
			+ '<div id="'
			+ secNo
			+ 'optionlist'
			+ quesNo
			+ '" class="row" style="margin:0px !important">'

			+ '<div class="col-sm-5 given_answer_table">'
			+ '<table class="table" id="'
			+ secNo
			+ 'given_answer_table'
			+ quesNo
			+ '">'
			+ '<thead><tr><th style="text-align:center;">'+messages['lbl.givenorder']+'</th></tr></thead>'
			+ '<tbody id="'
			+ secNo
			+ 'usergivenoptionlist'
			+ quesNo
			+ '"></tbody>'
			+ '</table>'
			+ '</div>'

			+ '<div class="col-sm-2"></div>'

			+ '<div  class="col-sm-5 correct_answer_table">'
			+ '<table class="table" id="'
			+ secNo
			+ 'correct_answer_table'
			+ quesNo
			+ '">'
			+ '<thead><tr><th style="text-align:center;">'+messages['lbl.correctorder']+'</th></tr></thead>'
			+ '<tbody id="'
			+ secNo
			+ 'usercorrectoptionlist'
			+ quesNo
			+ '"></tbody>'
			+ '</table>'
			+ '</div>'

			+ '</div>'
			+ '<div class="col-xs-12" id="'
			+ secNo
			+ 'explaindiv'
			+ quesNo
			+ '" style="display:none;border-color: green;border-style: solid;border-width: 1px;margin-top:10px">'
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
			+

			'<li style="padding-right:12px;"><button type="button" class="btn btn-success btn-flat button-width" id="'
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
 * @summary This is used for appending row into Given option table.
 * 
 * @param secNo
 * @param quesNo
 * @param optn
 * @returns {String}
 */
function addSortListTypeGivenOptions(secNo, quesNo, optn) {
	var opnHTML = '<tr><td id="' + secNo + 'given' + quesNo + 'row' + optn
			+ '"></td></tr>';
	return opnHTML;
}

/**
 * @summary This is used for appending row into correct option table.
 * 
 * @param secNo
 * @param quesNo
 * @param optn
 * @returns {String}
 */
function addSortListTypeCorrectOptions(secNo, quesNo, optn, order) {
	var opnHTML = '<tr><td class="correct_drag"><div class="col-sm-12 lrn_btn_sort" id="optionOrder'
			+ optn
			+ 'InQuestion'
			+ quesNo
			+ 'InSection'
			+ secNo
			+ '">'
			+ '<div class="input-group">'
			+ '<div class="input-group-addon">'
			+ order
			+ '</div>&nbsp;'
			+ '<div class="imgset pull-left padding-left" id="">'
			+ test.sectionlist[secNo - 1].questionList[quesNo - 1].optionList[optn - 1].optionName
			+ '</div>' + '</div>' + '</div></td></tr>';
	return opnHTML;
}