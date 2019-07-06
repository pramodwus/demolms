/**
 * @summary This file would be used for performing multiple choice type question operation in test.
 * @author ankur
 * @date 03 Sep 2016
 */

/**
 * @summary This is used add multiple choice type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addMultipleChoiceTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * get question id.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * get section id.
		 */
		var sectionId = test.sectionlist[secNo].sectionId;
		/**
		 * append question in question div.
		 */
		$("#allquestion").append(
				createMultipleChoiceTypeQuestionList(secNo + 1, quesNo + 1));
		/**
		 * iterate on option list.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].option.length; optn++) {
			/**
			 * get option id.
			 */
			var optionid = test.sectionlist[secNo].questionList[quesNo].option[optn].optionId;
			/**
			 * append option in option list of question.
			 */
			$("#" + (secNo + 1) + "optionlist" + (quesNo + 1)).append(
					addMultipleChoiceTypeOptions(sectionId, quesId, optionid));
			/**
			 * set title of option.
			 */
			$("#" + sectionId + "option" + quesId + "title" + optionid)
					.html(
							test.sectionlist[secNo].questionList[quesNo].option[optn].optionName);
			/**
			 * add 64 for making ascii value of option order.
			 */
			var optn1 = (64 + parseInt(test.sectionlist[secNo].questionList[quesNo].option[optn].optionOrder));
			/**
			 * make html element.
			 */
			var optionOrder = "&#" + optn1 + ";";
			/**
			 * set option order.
			 */
			$("#" + sectionId + "option" + quesId + "Order" + optionid).html(
					optionOrder);
			/**
			 * check that this is not correct option of this question.
			 */
			if (test.sectionlist[secNo].questionList[quesNo].option[optn].answerStatus != 1) {

				$("#" + sectionId + "option" + quesId + "Order" + optionid)
						.removeClass();
				$("#" + sectionId + "option" + quesId + "Order" + optionid)
						.addClass("badge button-color-blue bagde-style");
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
function createMultipleChoiceTypeQuestionList(secNo, quesNo) {
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
			+ '<div id="'
			+ secNo
			+ 'optionlist'
			+ quesNo
			+ '">'
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
 * @summary Function for adding option div in question.
 * @param secNo
 * @param quesNo
 * @returns {String}
 */
function addMultipleChoiceTypeOptions(sectionId, quesId, optionid) {
	var optionString = '<div class="option-click box tools pull-left input-group" style="border-top:0px">'
			+ '<p class="input-group-addon" onclick="clickAnswer('
			+ sectionId
			+ ','
			+ quesId
			+ ','
			+ optionid
			+ ');" style="cursor:pointer"> <span class="badge bagde-style button-green-color button-color-blue" id="'
			+ sectionId
			+ 'option'
			+ quesId
			+ 'Order'
			+ optionid
			+ '"></span></p>'
			+ '<div class="pull-left imgset" id="'
			+ sectionId
			+ 'option'
			+ quesId
			+ 'title'
			+ optionid
			+ '"></div>'
			+ '<div class="clearfix"></div>' + '</div>';
	return optionString;
}