/**
 * @summary This is used for created classification type question for building a particular question.
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
		 * set question title
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
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
				tableRow = tableRow
						+ "<td class='imgset text-align classification_drag' ondrop='dropInClassificationQuestionType(event)' ondragover='allowDropInClassificationQuestionType(event)'></td>";
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
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].option.length; optn++) {
			var dragAnswer = '<div class="answerDivInClassificationTypeQuestion" id="'
					+ (secNo + 1)
					+ 'draggedOption'
					+ (optn + 1)
					+ 'InClassificationTypeQuestion'
					+ (quesNo + 1)
					+ '" draggable="true" ondragstart="dragInClassificationQuestionType(event)"><div class="input-group"><div class="input-group-addon"><i class="fa fa-align-justify"></i></div><div class="pull-left" id="'
					+ (secNo + 1)
					+ 'optionDrag'
					+ (optn + 1)
					+ 'InClassificationTypeQuestion'
					+ (quesNo + 1)
					+ '">'
					+ test.sectionlist[secNo].questionList[quesNo].option[optn].optionName
					+ '</div></div></div>';
			$(
					"#" + (secNo + 1) + "classificationOptionsDivForDrag"
							+ (quesNo + 1)).append(dragAnswer);
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
			+ '<div class="col-xs-12 form-group">'
			+ '<div class="row triangle"></div>'
			+ '<div class="row classification_drag" style="min-height:100px;background-color:#ccc;padding:10px" id="'
			+ secNo
			+ 'classificationOptionsDivForDrag'
			+ quesNo
			+ '" ondrop="dropInClassificationQuestionType(event)" ondragover="allowDropInClassificationQuestionType(event)"></div>'
			+ '</div>' + '</div>';
	return questnString;

}

/**
 * @summary This function allow and element for drop.
 * @param ev
 * @returns no.
 */
function allowDropInClassificationQuestionType(ev) {
	ev.preventDefault();
}

/**
 * @summary This function is used get the activity when user start drag an
 *          element.
 * @param ev
 * @returns no.
 */
function dragInClassificationQuestionType(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

/**
 * @summary This function is used for performing actual action when user drops
 *          an element.
 * @param ev
 * @returns no.
 */
function dropInClassificationQuestionType(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	/**
	 * checking that in target element is same or different.
	 */
	if ($(ev.target).hasClass('classification_drag')) {
		/**
		 * append dragged element inside target element.
		 */
		ev.target.appendChild(document.getElementById(data));
		/**
		 * get id of target table.
		 */
		var targetTableId = $(ev.target).closest("table").attr('id');
		var sourceDivId = $(ev.target).closest(".classification_drag").attr(
				'id');
		if (targetTableId != null) {
			var arr = targetTableId.split('classificationtable');
			var secNo = parseInt(arr[0]);
			var quesNo = parseInt(arr[1]);
			/**
			 * perform required action now.
			 */
			performedActionInClassificationTypeQuestion(secNo, quesNo);
		} else if (sourceDivId != null) {
			var arr = sourceDivId.split('classificationOptionsDivForDrag');
			var secNo = parseInt(arr[0]);
			var quesNo = parseInt(arr[1]);
			/**
			 * perform required action now.
			 */
			performedActionInClassificationTypeQuestion(secNo, quesNo);
		}

	}

}

/**
 * @summary This function is used to extract information about dropped element
 *          and target element.
 * @param tableId
 * @returns no.
 */
function performedActionInClassificationTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * get instance of table.
		 */
		var table = document.getElementById(secNo + 'classificationtable'
				+ quesNo);
		/**
		 * get total row
		 */
		var totalRow = test.sectionlist[secNo - 1].questionList[quesNo - 1].questionSetting.questionUiStyle.rowTitles.length;
		/**
		 * get total column
		 */
		var totalColumn = test.sectionlist[secNo - 1].questionList[quesNo - 1].questionSetting.questionUiStyle.columnTitles.length;
		var answerValue = [];
		for (var tr = 1; tr <= totalRow; tr++) {
			var columnArray = [];
			for (var td = 1; td <= totalColumn; td++) {
				var correctAnswer = [];
				$(table.rows[tr].cells[td]).find(
						'.answerDivInClassificationTypeQuestion').each(
						function() {
							var id = $(this).attr('id');
							var arr1 = id.split("draggedOption");
							var arr2 = arr1[1]
									.split("InClassificationTypeQuestion");
							var correctOrder = parseInt(arr2[0]);
							correctAnswer.push(correctOrder);
						});
				columnArray.push(correctAnswer);
			}
			answerValue.push(columnArray);
		}
		/**
		 * save response of user.
		 */
		saveClassificationTypeQuestionUserResponseInJson(secNo, quesNo,
				answerValue);
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary This is used save the response into json of classification type
 *          question..
 * @param secNo
 * @param quesNo
 * @param answerValue
 * @returns no.
 */
function saveClassificationTypeQuestionUserResponseInJson(secNo, quesNo,
		answerValue) {
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
			submitTestJson.sectionlist[secPosition].questionList[quesPosition].userGivenAnswer = answerValue;
		} else {
			var response = {
				"questionId" : quesId,
				"userGivenAnswer" : answerValue
			};
			submitTestJson.sectionlist[secPosition].questionList.push(response);
		}
	} catch (err) {
		console.log(err.message);
	}
}