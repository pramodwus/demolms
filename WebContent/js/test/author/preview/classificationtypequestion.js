/**
 * @summary This is used for created classification type question for preview a particular question.
 * @author ankur
 * @date 08 Sep 2016.
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
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * get answer value.
		 */
		var answerValue = JSON
				.parse(test.sectionlist[secNo].questionList[quesNo].answerValue);
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
				tableRow = tableRow + "<td class='imgset text-align'></td>";
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
		for (var x = 0; x < answerValue.length; x++) {
			for (var y = 0; y < answerValue[x].length; y++) {
				for (var z = 0; z < answerValue[x][y].length; z++) {
					/**
					 * get option order.
					 */
					var optionNumber = answerValue[x][y][z];
					/**
					 * generate html for table cell according to answer value.
					 */
					var dragDiv = '<div class="answerDivInClassificationTypeQuestion"><div class="input-group"><div class="input-group-addon"><i class="fa fa-align-justify"></i></div><div class="pull-left">'
							+ test.sectionlist[secNo].questionList[quesNo].option[optionNumber - 1].optionName
							+ '</div></div></div>';
					/**
					 * append in table cell.
					 */
					$(table.rows[x + 1].cells[y + 1]).append(dragDiv);
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
			+ '<table class="table classificationtable" id="'
			+ secNo
			+ 'classificationtable'
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
			+'</h3><p id="'
			+ secNo
			+ 'ansexplain'
			+ quesNo
			+ '"> </p></div>' + '</div>';
	return questnString;

}