/**
 * @summary This is used for created multiple choice type question for review page.
 * @author ankur
 * @date 03 Sep 2016.
 * 
 */

/**
 * @summary This function is used for adding multiple choice type question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function addMultipleChoiceTypeQuestion(secNo, quesNo) {
	try {
		/**
		 * get section id.
		 */
		var sectionId = test.sectionlist[secNo].sectionId;
		/**
		 * get question id.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * append question after created its html in question div.
		 */
		$("#allquestion").append(
				createMultiChoiceQuestionList(secNo + 1, quesNo + 1));
		/**
		 * set title of question.
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * iterate on option list.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].optionList.length; optn++) {
			/**
			 * get option id.
			 */
			var optionid = test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionId;
			/**
			 * append option list for question.
			 */
			$("#" + (secNo + 1) + "optionlist" + (quesNo + 1)).append(
					addOptions(sectionId, quesId, optionid));
			/**
			 * set title of option.
			 */
			$("#" + sectionId + "option" + quesId + "title" + optionid)
					.html(
							test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionName);
			/**
			 * set order of option.
			 */
			$("#" + sectionId + "option" + quesId + "Order" + optionid)
					.html(
							test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionPosition);
			/**
			 * checking If review is not enabled with correct answer.
			 */
			if (test.reviewWithCorrect == 0) {
				/**
				 * hide visibility for showing to user that this is correct
				 * answer for this question.
				 */
				$("#" + sectionId + "check" + quesId + "Answer" + optionid)
						.css("visibility", "hidden");
				/**
				 * checking option id is same with attempted option pk.
				 */
				if (test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionId == test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionPk) {
					$("#" + sectionId + "option" + quesId + "Order" + optionid)
							.removeClass('button-color-white');
					$("#" + sectionId + "option" + quesId + "Order" + optionid)
							.addClass('button-color-blue');
				}

			} else {
				if (test.sectionlist[secNo].questionList[quesNo].optionList[optn].answerStatus != 1) {
					$("#" + sectionId + "check" + quesId + "Answer" + optionid)
							.css("visibility", "hidden");
				}
				/**
				 * checking that question is skipped.
				 */
				if (test.sectionlist[secNo].questionList[quesNo].isCorrect == -1) {
				}
				/**
				 * checking question is attempted correctly by user.
				 */
				else if (test.sectionlist[secNo].questionList[quesNo].isCorrect == 1) {
					/**
					 * checking option id is same with attempted option pk.
					 */
					if (test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionId == test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionPk) {
						$(
								"#" + sectionId + "option" + quesId + "Order"
										+ optionid).removeClass(
								'button-color-white');
						$(
								"#" + sectionId + "option" + quesId + "Order"
										+ optionid).addClass(
								'button-color-green');
					}
				} else {
					/**
					 * get answer status.
					 */
					var ansStatus = test.sectionlist[secNo].questionList[quesNo].optionList[optn].answerStatus;
					/**
					 * checking option id is same with attempted option pk.
					 */
					var isSame = test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionId == test.sectionlist[secNo].questionList[quesNo].optionList[optn].optionPk;
					/**
					 * cheking this answer is correct and user also sumitted
					 * this answer.
					 */
					if ((ansStatus == 1) && isSame) {
						$(
								"#" + sectionId + "option" + quesId + "Order"
										+ optionid).removeClass(
								'button-color-white');
						$(
								"#" + sectionId + "option" + quesId + "Order"
										+ optionid).addClass(
								'button-color-green');
					} else if (isSame) {
						$(
								"#" + sectionId + "option" + quesId + "Order"
										+ optionid).removeClass(
								'button-color-white');
						$(
								"#" + sectionId + "option" + quesId + "Order"
										+ optionid).addClass('bg-red');
					}
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
function createMultiChoiceQuestionList(secNo, quesNo) {
	var questnString = '<div class="box-body box-left-margin questionAllDiv" style="display:none" id="'
			+ secNo
			+ 'questionDiv'
			+ quesNo
			+ '">'
			+ '<div>'
			+ '<h5 id="'
			+ secNo
			+ 'questiontitle'
			+ quesNo
			+ '" class="imgset"></h5>'
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
			+ 'explaindiv'
			+ quesNo
			+ '" style="display:none;border-color: green;border-style: solid;border-width: 1px;">'
			+ '<h3>'+messages['lbl.explanation']+'</h3><p id="'
			+ secNo
			+ 'ansexplain'
			+ quesNo
			+ '"> </p></div>'
			+ '<div>'
			+ '<nav>'
			+ '<ul class="pager pull-left">'
			+ '<li style=""><button type="button" onclick="quit()" class="btn btn-flat btn-success button-width">'
			+ '<i class="fa fa-close">&nbsp;&nbsp;&nbsp;</i>'+messages['lbl.quit']+'</button></li>'
			+ '</ul>'
			+ '<ul class="pager pull-right" style="">'
			+ '<li style="padding-right:12px;">'
			+ '<button id="'
			+ secNo
			+ 'explain'
			+ quesNo
			+ '" class="btn btn-success btn-flat  button-width" onclick="showexplaination('
			+ secNo
			+ ','
			+ quesNo
			+ ')" >'+messages['lbl.explanation']+'</button>'
			+ '<li style="padding-right:12px;"><button type="button" id="'
			+ secNo
			+ 'previous'
			+ quesNo
			+ '" class="btn btn-flat btn-success button-width"	 onclick="previous('
			+ secNo
			+ ','
			+ quesNo
			+ ')" disabled>'
			+ '<i class="fa fa-backward"></i>&nbsp;'+messages['lbl.previous']+''
			+ '</button></li>'
			+

			'<li style="padding-right:12px;"><button type="button" class="btn btn-success btn-flat button-width" id="'
			+ secNo
			+ 'next'
			+ quesNo
			+ '" onclick="next('
			+ secNo
			+ ','
			+ quesNo
			+ ')">'
			+ '<i class="fa fa-forward"></i>&nbsp;'+messages['lbl.next']+'</button></li>'
			+ '</ul>'
			+ '</nav>' + '</div>' + '</div>';

	return questnString;

}

/**
 * @summary function for adding option div in question.
 * @param sectionId
 * @param quesId
 * @param optionid
 * @returns {String}
 */
function addOptions(sectionId, quesId, optionid) {
	var optionString = '<div class="option-click box tools pull-left input-group" style="border-top:0px">'
			+ '<div class="image input-group-addon" id="'
			+ sectionId
			+ 'check'
			+ quesId
			+ 'Answer'
			+ optionid
			+ '" style="padding:0px">'
			+ '<img src="resources/images/double-check-mark-hi.png" style="height:10px">'
			+ '</div>'
			+ '<p class="input-group-addon" style="cursor:pointer;padding-left:0px"> <span class="badge  button-color-white  bagde-style" id="'
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