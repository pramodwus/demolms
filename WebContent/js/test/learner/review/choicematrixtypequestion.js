/**
 * @summary This is used for created choice matrix type question for review a particular question.
 * @author ankur
 * @date 31 Aug 2016.
 * 
 */

/**
 * @summary This is used add choice matrix type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addChoiceMatrixTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * append question in section.
		 */
		$("#allquestion").append(
				createChoiceMatrixTypeQuestion(secNo + 1, quesNo + 1));
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
		 * get correct answer value.
		 */
		var answerValue = JSON
				.parse(test.sectionlist[secNo].questionList[quesNo].answerValue);
		/**
		 * get given answer value.
		 */
		var choiceMatrixGivenAnswer = test.sectionlist[secNo].questionList[quesNo].choiceMatrixGivenAnswer;
		/**
		 * iterate on sub option array of this question.
		 */
		for (var suboptn = 0; suboptn < test.sectionlist[secNo].questionList[quesNo].subOptionList.length; suboptn++) {
			$('#' + (secNo + 1) + 'choicematrix' + (quesNo + 1) + ' thead>tr')
					.append(
							"<th class='imgset text-align'>"
									+ test.sectionlist[secNo].questionList[quesNo].subOptionList[suboptn].optionName
									+ "</th>");
		}
		/**
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].optionList.length; optn++) {
			var tableRow = "<tr><td class='imgset'>"
					+ test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionName
					+ "</td>";
			/**
			 * iterate on sub option array of this question.
			 */
			for (var suboptn = 0; suboptn < test.sectionlist[secNo].questionList[quesNo].subOptionList.length; suboptn++) {
				tableRow = tableRow
						+ "<td class='imgset text-align'><input type='radio' class='choiceMatrixButton' id='"
						+ (secNo + 1) + "choice" + (optn + 1) + "matrix"
						+ (suboptn + 1) + "Button" + (quesNo + 1) + "' name='"
						+ (secNo + 1) + "choice" + (optn + 1) + "matrixButton"
						+ (quesNo + 1) + "'></td>";
			}
			tableRow = tableRow + "</tr>";
			$('#' + (secNo + 1) + 'choicematrix' + (quesNo + 1) + ' tbody')
					.append(tableRow);
		}
		/**
		 * iterate on given answer value.
		 */
		for (var tr = 0; tr < choiceMatrixGivenAnswer.length; tr++) {

			for (var td = 0; td < choiceMatrixGivenAnswer[tr].length; td++) {

				$(
						"#" + (secNo + 1) + "choice" + (tr + 1) + "matrix"
								+ choiceMatrixGivenAnswer[tr][td] + "Button"
								+ (quesNo + 1)).prop('checked', true);
			}
		}
		/**
		 * get table id of choice matrix option.
		 */
		var table = document.getElementById((secNo + 1) + 'choicematrix'
				+ (quesNo + 1));
		/**
		 * check review with correct answer is enabled.
		 */
		if (test.reviewWithCorrect == 1) {
			/**
			 * checking that user has skipped this question.
			 */
			if (test.sectionlist[secNo].questionList[quesNo].isCorrect == -1) {
			}
			/**
			 * checking that user's attempted question is correct.
			 */
			else if (test.sectionlist[secNo].questionList[quesNo].isCorrect == 1) {
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1))
						.html(
								'<a class="btn btn-social-icon bg-green btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-check" style="margin-left: 4px;"  title="'+messages['lbl.correctanswer']+'"></i></a>');
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1)).css(
						'border-bottom', '3px solid green');
				$(table).closest("div.row").css('border', '1px solid green');
			} else {
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1))
						.html(
								'<span class="btn btn-social-icon bg-red btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-close"  title="'+messages['lbl.wronganswer']+'" style="margin-left: 4px;"></i></span>');
				$('#' + (secNo + 1) + 'answerStatus' + (quesNo + 1)).css(
						'border-bottom', '3px solid #dd4b39');
				$(table).closest("div.row").css('border', '1px solid #dd4b39');
			}

			/**
			 * iterate on given answer
			 */
			for (var tr = 0; tr < choiceMatrixGivenAnswer.length; tr++) {

				for (var td = 0; td < choiceMatrixGivenAnswer[tr].length; td++) {

					var givenAnswer = choiceMatrixGivenAnswer[tr][td];
					/**
					 * checking given answer for this option is wrong.
					 */
					if ((answerValue[tr].indexOf(givenAnswer)) == -1) {
						/**
						 * append wrong mark for identify to user that this you
						 * have submitted wrong answer for this option.
						 */
						$(
								"#" + (secNo + 1) + "choice" + (tr + 1)
										+ "matrix" + (givenAnswer) + "Button"
										+ (quesNo + 1))
								.after(
										'<i class="fa fa-close" style="color:#dd4b39" ></i>');
					}
					/**
					 * given answer for this option is correct.
					 */
					else {
						/**
						 * append check mark for identify to user that this you
						 * have submitted right answer for this option.
						 */
						$(
								"#" + (secNo + 1) + "choice" + (tr + 1)
										+ "matrix" + (givenAnswer) + "Button"
										+ (quesNo + 1))
								.after(
										'<i class="fa fa-check" style="color:green"></i>');
					}
				}
			}
			/**
			 * iterate on given answer value.
			 */
			for (var tr = 0; tr < answerValue.length; tr++) {

				for (var td = 0; td < answerValue[tr].length; td++) {

					var correctAnswer = answerValue[tr][td];
					/**
					 * checking that user has not skipped this question.
					 */
					if (choiceMatrixGivenAnswer[tr] != null) {
						/**
						 * checking that user has not submitted this answer for
						 * this option.
						 */
						if ((choiceMatrixGivenAnswer[tr].indexOf(correctAnswer)) == -1) {
							/**
							 * append check mark for identify to user that this
							 * is one from correct answer for this option.
							 */
							$(
									"#" + (secNo + 1) + "choice" + (tr + 1)
											+ "matrix" + (correctAnswer)
											+ "Button" + (quesNo + 1))
									.after(
											'<i class="fa fa-check" style="color:#888888" ></i>');
						}
					}
					/**
					 * when user has skipped this question.
					 */
					else {
						/**
						 * append check mark for identify to user that this is
						 * one from correct answer for this option.
						 */
						$(
								"#" + (secNo + 1) + "choice" + (tr + 1)
										+ "matrix" + (correctAnswer) + "Button"
										+ (quesNo + 1))
								.after(
										'<i class="fa fa-check" style="color:#888888" ></i>');
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
function createChoiceMatrixTypeQuestion(secNo, quesNo) {
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
			+ 'answerStatus'
			+ quesNo
			+ '"></div>'
			+ '<div class="row table-responsive" style="margin:0px;">'
			+ '<table class="table choicematrixtable" id="'
			+ secNo
			+ 'choicematrix'
			+ quesNo
			+ '" style="margin-bottom: 0px;">'
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
			+ ')">'+messages['lbl.explanation']+'</button>'
			+ '<li style="padding-right:12px;"><button type="button" id="'
			+ secNo
			+ 'previous'
			+ quesNo
			+ '" class="btn btn-flat btn-success button-width" onclick="previous('
			+ secNo
			+ ','
			+ quesNo
			+ ')" disabled>'
			+ '<i class="fa fa-backward"></i> '+messages['lbl.previous']+''
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
			+ '<i class="fa fa-forward"></i> '+messages['lbl.next']+'</button></li>'
			+ '</ul>'
			+ '</nav>' + '</div>' + '</div>';
	return questnString;

}