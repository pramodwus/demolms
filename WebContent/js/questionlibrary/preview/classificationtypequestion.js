/**
 * @summary This is used for created classification type question for preview a particular question.
 * @author ankur
 * @date 08 Sep 2016.
 * 
 */

/**
 * @summary This is used for adding classification type question.
 * @returns no.
 */
function classificationTypeQuestionPreview(quesData) {

	try {
		/**
		 * append question in question list
		 */
		$("#allquestion").append(createClassificationTypeQuestionList());
		/**
		 * set title of question.
		 */
		$("#questiontitle").html(quesData.questionName);
		/**
		 * get answer value.
		 */
		var answerValue = JSON.parse(quesData.answerValue);
		/**
		 * get total columns in classification answer table.
		 */
		var totalColumn = quesData.questionSetting.questionUiStyle.columnTitles.length;
		/**
		 * get total rows in classification table.
		 */
		var totalRow = quesData.questionSetting.questionUiStyle.rowTitles.length;
		/**
		 * iterate on classification column.
		 */
		for (var columnNo = 0; columnNo < totalColumn; columnNo++) {
			/**
			 * append cell in table head.
			 */
			$('#classificationtable thead>tr')
					.append(
							"<th class='imgset text-align'>"
									+ quesData.questionSetting.questionUiStyle.columnTitles[columnNo]
									+ "</th>");
		}
		/**
		 * iterate on table row.
		 */
		for (var rowNo = 0; rowNo < totalRow; rowNo++) {
			/**
			 * create html for row of table body.
			 */
			var tableRow = "<tr><td class='imgset'>"
					+ quesData.questionSetting.questionUiStyle.rowTitles[rowNo]
					+ "</td>";
			/**
			 * iterate on table column.
			 */
			for (var columnNo = 0; columnNo < totalColumn; columnNo++) {
				/**
				 * append cell html in row html.
				 */
				tableRow = tableRow + "<td class='imgset text-align'></td>";
			}
			/**
			 * added html in table row.
			 */
			tableRow = tableRow + "</tr>"
			/**
			 * append table row data in table body.
			 */
			$('#classificationtable tbody').append(tableRow);
		}
		/**
		 * get instance of table.
		 */
		var table = document.getElementById('classificationtable');
		/**
		 * iterate on correct answer value.
		 */
		for (var x = 0; x < answerValue.length; x++) {
			for (var y = 0; y < answerValue[x].length; y++) {
				for (var z = 0; z < answerValue[x][y].length; z++) {
					var optionNumber = answerValue[x][y][z];
					var dragDiv = '<div class="answerDivInClassificationTypeQuestion"><div class="input-group"><div class="input-group-addon"><i class="fa fa-align-justify"></i></div><div class="pull-left">'
							+ quesData.option[optionNumber - 1].optionName
							+ '</div></div></div>';
					$(table.rows[x + 1].cells[y + 1]).append(dragDiv);
				}
			}
		}
		/**
		 * checking question's explanation is not null.
		 */
		quesData.explanation == null ? $("#description").hide()
				: (quesData.explanation.trim().length == 0 ? $("#description")
						.hide() : $("#ansexplain").html(quesData.explanation));
		$('.imgset img').css({
			'display' : 'block',
			'width' : '100%',
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
function createClassificationTypeQuestionList() {
	var questnString = '<div class="box-body box-left-margin questionAllDiv" id="questionDiv">'
			+ '<div>'
			+ '<h5 id="questiontitle" class="imgset"></h5>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div id="optionlist" class="form-group">'
			+ '<table class="table" id="classificationtable">'
			+ '<thead><tr><th></th></tr></thead>'
			+ '<tbody></tbody>'
			+ '</table>'
			+ '</div>'
			+ '<div class="col-xs-12" id="description" style="border-color: green;border-style: solid;border-width: 1px;">'
			+ '<h3>'+ messages['lbl.explanation']+'</h3><p id="ansexplain"></p></div>' + '</div>';

	return questnString;

}