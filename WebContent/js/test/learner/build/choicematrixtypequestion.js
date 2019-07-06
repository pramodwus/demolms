/**
 * @summary This is used for created choice matrix type question for building in given test.
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
		 * set question title
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * iterate on sub option array of this question.
		 */
		for (var suboptn = 0; suboptn < test.sectionlist[secNo].questionList[quesNo].subOption.length; suboptn++) {
			$('#' + (secNo + 1) + 'choicematrix' + (quesNo + 1) + ' thead>tr')
					.append(
							"<th class='imgset text-align'>"
									+ test.sectionlist[secNo].questionList[quesNo].subOption[suboptn].optionName
									+ "</th>");
		}
		/**
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].option.length; optn++) {
			var tableRow = "<tr><td class='imgset'>"
					+ test.sectionlist[secNo].questionList[quesNo].option[optn].optionName
					+ "</td>";
			/**
			 * iterate on sub option array of this question.
			 */
			for (var suboptn = 0; suboptn < test.sectionlist[secNo].questionList[quesNo].subOption.length; suboptn++) {
				tableRow = tableRow
						+ "<td class='imgset text-align'><input type='radio' class='choiceMatrixRadioButton' id='"
						+ (secNo + 1) + "choice" + (optn + 1) + "matrix"
						+ (suboptn + 1) + "Button" + (quesNo + 1) + "' name='"
						+ (secNo + 1) + "choice" + (optn + 1) + "matrixButton"
						+ (quesNo + 1) + "'></td>";
			}
			tableRow = tableRow + "</tr>";
			$('#' + (secNo + 1) + 'choicematrix' + (quesNo + 1) + ' tbody')
					.append(tableRow);
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
			+ '<table class="table choicematrixtable" id="'
			+ secNo
			+ 'choicematrix'
			+ quesNo
			+ '">'
			+ '<thead><tr><th></th></tr></thead>'
			+ '<tbody></tbody>'
			+ '</table>' + '</div>';
	return questnString;

}

/**
 * @summary Function is used to get data for saving the response when user click
 *          on radio button in choice matrix type question.
 * @returns no.
 */
$(document).on(
		'click',
		'.choiceMatrixRadioButton',
		function() {
			var buttonId1 = $(this).attr('id');
			var buttonId2 = buttonId1.split('choice');
			var secNo = buttonId2[0];
			var buttonId3 = buttonId2[1].split('matrix');
			var opnNo = buttonId3[0];
			var buttonId4 = buttonId3[1].split('Button');
			var subOpnNo = buttonId4[0];
			var quesNo = buttonId4[1];
			saveChoiceMatrixTypeQuestionUserResponseInJsonOnRadioClick(secNo,
					quesNo, opnNo, subOpnNo);
			// alert("secNo= " + secNo + " ,quesNo= " + quesNo + " ,opnNo= " +
			// opnNo + " ,subOpnNo=" + subOpnNo);
		});

/**
 * @summary This is used save the response into json of choice matrix type
 *          question..
 * @param secNo
 * @param quesNo
 * @param opnNo
 * @param subOpnNo
 * @returns no.
 */
function saveChoiceMatrixTypeQuestionUserResponseInJsonOnRadioClick(secNo,
		quesNo, opnNo, subOpnNo) {
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
			var answerValue = [];
			answerValue.push(Number(subOpnNo));
			submitTestJson.sectionlist[secPosition].questionList[quesPosition].choiceMatrixGivenAnswer[opnNo - 1] = answerValue;
		} else {
			var answerValue = [];
			for (var tr = 0; tr < test.sectionlist[secNo - 1].questionList[quesNo - 1].option.length; tr++) {
				var emptyRow = [];
				if (tr == (opnNo - 1)) {
					emptyRow.push(Number(subOpnNo));
				}
				answerValue.push(emptyRow);
			}
			var response = {
				"questionId" : quesId,
				"choiceMatrixGivenAnswer" : answerValue
			};
			submitTestJson.sectionlist[secPosition].questionList.push(response);
		}
		// console.log(JSON.stringify(submitTestJson));
	} catch (err) {
		console.log(err.message);
	}
}