/**
 * @summary This is used for created multiple choice type question for preview a particular question.
 * @author ankur
 * @date 03 Sep 2016.
 */

/**
 * @summary This function is used for adding multi and single type question for
 *          preview.
 * @param quesData
 * @return no.
 */
function previewMultiChoiceQuestion(quesData) {
	try {
		$("#allquestion").append(
				createMultipleChoiceQuestionList(quesData.questionName));
		$("#questiontitle").html(quesData.questionName);
		for (var optn = 0; optn < quesData.option.length; optn++) {
			var optionid = quesData.option[optn].optionId;
			$("#optionlist").append(addMultipleChoiceOptions(optionid));
			$("#optiontitle" + optionid).html(quesData.option[optn].optionName);
			var optn1 = (64 + parseInt(quesData.option[optn].optionOrder));
			var optionOrder = "&#" + optn1 + ";";
			$("#optionOrder" + optionid).html(optionOrder);
			if (quesData.option[optn].answerStatus != 1) {
				$("#optionOrder" + optionid).removeClass();
				$("#optionOrder" + optionid).addClass(
						"badge button-color-blue bagde-style");
			}
		}
		quesData.explanation == null ? $("#description").hide()
				: (quesData.explanation.trim().length == 0 ? $("#description")
						.hide() : $("#ansexplain").html(quesData.explanation));
		if (quesData.isFormula == 1) {
			$("#mathformula").html(quesData.mathFormula);
			$("#mathformuladiv").show();
		}
		$("#formula").html(quesData.mathFormula);
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
 * @summary Function for creating question html.
 * @returns {String}
 */
function createMultipleChoiceQuestionList() {
	var questnString = '<div class="box-body" id="questionDiv">'
			+ '<div>'
			+ '<div id="questiontitle" class="imgset pull-left"></div>'
			+ '</div>'
			+ '<div>&nbsp;</div><div>&nbsp;</div>'
			+ '<div id="optionlist">'
			+ '</div>'
			+ '<div class="col-xs-12" id="mathformuladiv" style="display:none">'
			+ '<h3>'+messages['lbl.mathematicalformula']+' :</h3><p id="mathformula" class="imgset"> </p></div>'
			+ '<div class="col-xs-12" id="description" style="border-color: green;border-style: solid;border-width: 2px;">'
			+ '<h3>'+ messages['lbl.explanation'] +'</h3><p id="ansexplain"> </p></div>' + '</div>';
	return questnString;
}

/**
 * @summary function for adding option div in question.
 * @returns {String}
 */
function addMultipleChoiceOptions(optionid) {
	var optionString = '<div class="option-click box tools pull-left input-group" style="border-top:0px">'
			+ '<p class="input-group-addon" onclick="clickAnswer('
			+ optionid
			+ ');" style="cursor:pointer"> <span class="badge bagde-style button-green-color button-color-blue" id="optionOrder'
			+ optionid
			+ '"></span></p>'
			+ '<div class="pull-left imgset" id="optiontitle'
			+ optionid
			+ '"></div>' + '<div class="clearfix"></div>' + '</div>';
	return optionString;
}