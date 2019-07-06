/**
 * @summary This is used for created choice matrix type question for preview a particular question.
 * @author ankur
 * @date 30 Aug 2016.
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
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * get answer value.
		 */
		var answerValue = JSON
				.parse(test.sectionlist[secNo].questionList[quesNo].answerValue);
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
		 * iterate on answer value.
		 */
		for (var tr = 0; tr < answerValue.length; tr++) {
			for (var td = 0; td < answerValue[tr].length; td++) {
				$(
						"#" + (secNo + 1) + "choice" + (tr + 1) + "matrix"
								+ answerValue[tr][td] + "Button" + (quesNo + 1))
						.prop('checked', true);
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
			+ '<table class="table choicematrixtable" id="'
			+ secNo
			+ 'choicematrix'
			+ quesNo
			+ '">'
			+ '<thead><tr><th></th></tr></thead>'
			+ '<tbody></tbody>'
			+ '</table>'
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