/**
 * @summary This file would be used for performing sort list type question operation in test.
 * @author ankur
 * @date 03 Sep 2016
 */

/**
 * @summary This is used add sort list type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addSortListTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * append question in section.
		 */
		$("#allquestion").append(
				createSortListTypeQuestion(secNo + 1, quesNo + 1));
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
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].option.length; optn++) {
			/**
			 * append option in source of question.
			 */
			$("#" + (secNo + 1) + "optionlist" + (quesNo + 1))
					.append(
							addSortListQuestionOptions(secNo + 1, quesNo + 1,
									optn + 1));
			/**
			 * append option in target of question.
			 */
			$("#" + (secNo + 1) + "usertargetoptionlist" + (quesNo + 1))
					.append(
							addSortListTypeTargetOptions(secNo + 1, quesNo + 1,
									answerValue[optn], optn + 1));
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
function createSortListTypeQuestion(secNo, quesNo) {
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
			+ '<div class="col-sm-12">'
			+ '<div class="col-sm-5 source_table">'
			+ '<table class="table" id="'
			+ secNo
			+ 'source_table'
			+ quesNo
			+ '">'
			+ '<thead><tr><th style="text-align:center;">'
			+ messages['lbl.source']
			+ '</th></tr></thead>'
			+ '<tbody id="'
			+ secNo
			+ 'optionlist'
			+ quesNo
			+ '"></tbody>'
			+ '</table>'
			+ '</div>'
			+ '<div class="col-sm-2"></div>'
			+ '<div  class="col-sm-5 target_table">'
			+ '<table class="table" id="'
			+ secNo
			+ 'target_table'
			+ quesNo
			+ '">'
			+ '<thead><tr><th style="text-align:center;">'
			+ messages['lbl.target']
			+ '</th></tr></thead>'
			+ '<tbody id="'
			+ secNo
			+ 'usertargetoptionlist'
			+ quesNo
			+ '"></tbody>'
			+ '</table>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" id="'
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

/**
 * @summary This is used for appending row into source table.
 * @param secNo
 * @param quesNo
 * @param optn
 * @returns {String}
 */
function addSortListQuestionOptions(secNo, quesNo, optn) {
	var opnHTML = '<tr><td class="source_drag"><div class="col-sm-12 lrn_btn_sort" id="optionOrder'
			+ optn
			+ 'InQuestion'
			+ quesNo
			+ 'InSection'
			+ secNo
			+ '">'
			+ '<div class="input-group">'
			+ '<div class="input-group-addon">'
			+ optn
			+ '</div>'
			+ '<div class="imgset pull-left padding-left" id="">'
			+ test.sectionlist[secNo - 1].questionList[quesNo - 1].option[optn - 1].optionName
			+ '</div>' + '</div>' + '</div></td></tr>';
	return opnHTML;
}

/**
 * @summary This is used for appending row into target table.
 * @param secNo
 * @param quesNo
 * @param optn
 * @returns {String}
 */
function addSortListTypeTargetOptions(secNo, quesNo, optn, order) {
	var opnHTML = '<tr><td class="target_drag"><div class="col-sm-12 lrn_btn_sort" id="optionOrder'
			+ optn
			+ 'InQuestion'
			+ quesNo
			+ 'InSection'
			+ secNo
			+ '">'
			+ '<div class="input-group">'
			+ '<div class="input-group-addon">'
			+ order
			+ '</div>'
			+ '<div class="imgset pull-left padding-left" id="">'
			+ test.sectionlist[secNo - 1].questionList[quesNo - 1].option[optn - 1].optionName
			+ '</div>' + '</div>' + '</div></td></tr>';
	return opnHTML;
}