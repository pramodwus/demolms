/**
 * @summary This is used for created sort list type question for preview a particular question.
 * @author ankur
 * @date 22 Aug 2016.
 * 
 */

/**
 * @summary This is used for adding sort list type question.
 * @returns no.
 */
function sortListTypeQuestionPreview(quesData) {

	try {
		$("#allquestion").append(createSortListTypeQuestionList());
		$("#questiontitle").html(quesData.questionName);
		var answerValue = JSON.parse(quesData.answerValue);
		for (var optn = 0; optn < quesData.option.length; optn++) {
			var optionid = quesData.option[optn].optionId;
			$("#usersourceoptionlist").append(
					addSortListQuestionOptions(optn + 1));
			$("#usertargetoptionlist").append(
					addSortListTypeTargetOptions(optn + 1));
			$("#sourceOptionName" + (optn + 1)).html(
					quesData.option[optn].optionName);
			$("#targetOptionName" + (optn + 1)).html(
					quesData.option[answerValue[optn] - 1].optionName);
		}
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
function createSortListTypeQuestionList() {
	var questnString = '<div class="box-body box-left-margin questionAllDiv" id="questionDiv">'
			+ '<div>'
			+ '<h5 id="questiontitle" class="imgset"></h5>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div id="optionlist">'

			+ '<div class="col-sm-5 source_table">'
			+ '<table class="table" id="source_table">'
			+ '<thead><tr><th style="text-align:center;">'+messages['lbl.source']+'</th></tr></thead>'
			+ '<tbody id="usersourceoptionlist"></tbody>'
			+ '</table>'
			+ '</div>'

			+ '<div class="col-sm-2"></div>'

			+ '<div  class="col-sm-5 target_table">'
			+ '<table class="table" id="target_table">'
			+ '<thead><tr><th style="text-align:center;">'+messages['lbl.target']+'</th></tr></thead>'
			+ '<tbody id="usertargetoptionlist"></tbody>'
			+ '</table>'
			+ '</div>'

			+ '</div>'
			+ '<div class="col-xs-12" id="description" style="border-color: green;border-style: solid;border-width: 1px;">'
			+ '<h3>'+messages['lbl.explanation']+'</h3><p id="ansexplain"></p></div>' + '</div>';

	return questnString;

}

/**
 * @summary This is used for appending row into source table.
 * 
 * @param secNo
 * @param quesNo
 * @param optn
 * @returns {String}
 */
function addSortListQuestionOptions(optn) {
	var opnHTML = '<tr><td class="source_drag"><div class="col-sm-12 lrn_btn_sort" id="sourceOptionOrder'
			+ optn
			+ '">'
			+ '<div class="input-group">'
			+ '<div class="input-group-addon">'
			+ optn
			+ '</div>&nbsp;'
			+ '<div class="imgset pull-left padding-left" id="sourceOptionName'
			+ optn + '">' + '</div>' + '</div>' + '</div></td></tr>';
	return opnHTML;
}

/**
 * @summary This is used for appending row into target table.
 * 
 * @param secNo
 * @param quesNo
 * @param optn
 * @returns {String}
 */
function addSortListTypeTargetOptions(optn) {
	var opnHTML = '<tr><td class="target_drag"><div class="col-sm-12 lrn_btn_sort" id="targetOptionOrder'
			+ optn
			+ '">'
			+ '<div class="input-group">'
			+ '<div class="input-group-addon">'
			+ optn
			+ '</div>&nbsp;'
			+ '<div class="imgset pull-left padding-left" id="targetOptionName'
			+ optn + '">' + '</div>' + '</div>' + '</div></td></tr>';
	return opnHTML;
}