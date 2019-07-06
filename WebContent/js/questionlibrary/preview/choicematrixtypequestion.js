/**
 * @summary This is used for created choice matrix type question for preview a particular question.
 * @author ankur
 * @date 30 Aug 2016.
 * 
 */

/**
 * @summary This is used for adding choice matrix type question.
 * @returns no.
 */
function choiceMatrixTypeQuestionPreview(quesData) {

	try {
		$("#allquestion").append(createChoiceMatrixTypeQuestionList());
		$("#questiontitle").html(quesData.questionName);
		/**
		 * get answer value.
		 */
		var answerValue = JSON.parse(quesData.answerValue);
		/**
		 * iterate on sub option array of this question.
		 */
		for (var suboptn = 0; suboptn < quesData.subOption.length; suboptn++) {
			$('#choicematrixtable thead>tr').append(
					"<th class='imgset text-align'>"
							+ quesData.subOption[suboptn].optionName + "</th>");
		}
		/**
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < quesData.option.length; optn++) {
			var tableRow = "<tr><td class='imgset'>"
					+ quesData.option[optn].optionName + "</td>";
			/**
			 * iterate on sub option array of this question.
			 */
			for (var suboptn = 0; suboptn < quesData.subOption.length; suboptn++) {
				tableRow = tableRow
						+ "<td class='imgset text-align'><input type='radio' class='choiceMatrixButton' id='choice"
						+ (optn + 1) + "matrix" + (suboptn + 1)
						+ "Button' name='choice" + (optn + 1)
						+ "matrixButton'></td>";
			}
			tableRow = tableRow + "</tr>";
			$('#choicematrixtable tbody').append(tableRow);
		}
		/**
		 * iterate on answer value.
		 */
		for (var tr = 0; tr < answerValue.length; tr++) {
			for (var td = 0; td < answerValue[tr].length; td++) {
				$(
						"#choice" + (tr + 1) + "matrix" + answerValue[tr][td]
								+ "Button").prop('checked', true);
			}
		}
		quesData.explanation == null ? $("#description").hide()
				: (quesData.explanation.trim().length == 0 ? $("#description")
						.hide() : $("#ansexplain").html(quesData.explanation));
		$('.imgset img').css({
			'display' : 'block',
			'max-width' : '100%',
			'height' : 'auto'
		});
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary Function for creating question html for appending in test
 *          dynamically.
 * @returns {String}.
 */
function createChoiceMatrixTypeQuestionList() {
	var questnString = '<div class="box-body box-left-margin questionAllDiv" id="questionDiv">'
			+ '<div>'
			+ '<h5 id="questiontitle" class="imgset"></h5>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div id="optionlist" class="form-group">'
			+ '<table class="table" id="choicematrixtable">'
			+ '<thead><tr><th></th></tr></thead>'
			+ '<tbody></tbody>'
			+ '</table>'
			+ '</div>'
			+ '<div class="col-xs-12" id="description" style="border-color: green;border-style: solid;border-width: 1px;">'
			+ '<h3>'+ messages['lbl.explanation'] +'</h3><p id="ansexplain"></p></div>' + '</div>';

	return questnString;

}