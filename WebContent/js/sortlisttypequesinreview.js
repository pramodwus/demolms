/**
 * @summary This is used for created sort list type question for review page.
 * @author ankur
 * @date 19 Aug 2016.
 * 
 */

/**
 * @summary This is used for adding sortlist type question.
 * @returns no.
 */
function addSortListTypeQuestion(secNo, quesNo) {

	try {
		/**
		 * describes that question is correct or not.
		 */
		var isCorrect =0;
		/**
		 * Add question html in question list.
		 */
		$("#allquestion").append(
				createSortListTypeQuestionList(secNo + 1, quesNo + 1));
		/**
		 * set question html.
		 */
		$("#" + (secNo + 1) + "questiontitle" + (quesNo + 1)).html(
				test.sectionlist[secNo].questionList[quesNo].questionName);
		/**
		 * get id of this question.
		 */
		var quesId = test.sectionlist[secNo].questionList[quesNo].questionId;
		/**
		 * iterate on option array of this question.
		 */
		for (var optn = 0; optn < test.sectionlist[secNo].questionList[quesNo].optionList.length; optn++) {

			/**
			 * append option in source of question.
			 */
			$("#" + (secNo + 1) + "usersourceoptionlist" + (quesNo + 1))
					.append(
							addSortListQuestionOptions(secNo + 1, quesNo + 1,
									optn + 1));
			/**
			 * append option in target of question.
			 */
			$("#" + (secNo + 1) + "usertargetoptionlist" + (quesNo + 1))
					.append(
							addSortListTypeTargetOptions(secNo + 1, quesNo + 1,
									optn + 1));
			/**
			 * If user has attempted this question.
			 */
			if (test.sectionlist[secNo].questionList[quesNo].givenAnswer.length > 0) {
				/**
				 * get the given order from given answer of this option.
				 */
				var givenOrder = test.sectionlist[secNo].questionList[quesNo].givenAnswer[optn];
				/**
				 * get the correct order from answer value of this option.
				 */
				var correctOrder = test.sectionlist[secNo].questionList[quesNo].correctAnswer[optn];
				/**
				 * If user has given the order for this option.
				 */
				if (givenOrder != 0) {
					/**
					 * generate html for appending in target table.
					 */
					var html = '<div class="col-sm-12 lrn_btn_sort" id="optionOrder'
							+ (optn + 1)
							+ 'InQuestion'
							+ (quesNo + 1)
							+ 'InSection'
							+ (secNo + 1)
							+ '">'
							+ '<div class="input-group" id="'
							+ (secNo + 1)
							+ 'input-'
							+ (quesNo + 1)
							+ '-group'
							+ (optn + 1)
							+ '">'
							+ '<div class="input-group-addon">'
							+ (optn + 1)
							+ '</div>'
							+ '<div class="imgset pull-left padding-left" id="">'
							+ test.sectionlist[secNo].questionList[quesNo].optionList[givenOrder - 1].optionName
							+ '</div>' + '</div>' + '</div>';
					/**
					 * append html in target table.
					 */
					$(
							'#' + (secNo + 1) + 'target' + (quesNo + 1) + 'row'
									+ (optn + 1)).html(html);
					/**
					 * if review with correct answer is enable.
					 */
					if (test.reviewWithCorrect == 1) {
						/**
						 * if user has submitted this option on correct order.
						 */
						if (givenOrder == correctOrder) {
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)).addClass(
									"border-green");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)
											+ ' .input-group-addon:first')
									.addClass("bg-green");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1))
									.append(
											'<div class="input-group-addon"><i class="fa fa-check" style="color:green" ></i></div>');
							isCorrect++;
						}
						/**
						 * if user has submitted this option on wrong order.
						 */
						else {
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)).addClass(
									"border-red");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1)
											+ ' .input-group-addon:first')
									.addClass("bg-red");
							$(
									'#' + (secNo + 1) + 'input-' + (quesNo + 1)
											+ '-group' + (optn + 1))
									.append(
											'<div class="input-group-addon"><i class="fa fa-close" style="color:#dd4b39" ></i></div>');
						}
					}
					/**
					 * if review with correct answer is not enable.
					 */
					else {
						$(
								'#' + (secNo + 1) + 'input-' + (quesNo + 1)
										+ '-group' + (optn + 1)).addClass(
								"border-blue");
						$(
								'#' + (secNo + 1) + 'input-' + (quesNo + 1)
										+ '-group' + (optn + 1)
										+ ' .input-group-addon:first')
								.addClass("bg-blue");
					}
				}
				else
					{
					/**
					 * generate html for appending in target table.
					 */
					var html = '<div class="col-sm-12 lrn_btn_sort" id="optionOrder'
							+ (optn + 1)
							+ 'InQuestion'
							+ (quesNo + 1)
							+ 'InSection'
							+ (secNo + 1)
							+ '">'
							+ '<div class="input-group input-group-first" id="'
							+ (secNo + 1)
							+ 'input-'
							+ (quesNo + 1)
							+ '-group'
							+ (optn + 1)
							+ '">'
							+ '<div class="input-group-addon">'
							+ (optn + 1)
							+ '</div>'
							+ '<div class="imgset pull-left padding-left" id="">'
							+ '</div>' + '</div>' + '</div>';
					/**
					 * append html in target table.
					 */
					$(
							'#' + (secNo + 1) + 'target' + (quesNo + 1) + 'row'
									+ (optn + 1)).html(html);
					}
			}
		}
		if (test.reviewWithCorrect == 1) {
		if(isCorrect==test.sectionlist[secNo].questionList[quesNo].optionList.length){
			$('#'+(secNo+1)+'answerStatus'+(quesNo+1)).html('<a class="btn btn-social-icon bg-green btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-check" style="margin-left: 4px;"  title="correct answer"></i></a>');
			$('#'+(secNo+1)+'answerStatus'+(quesNo+1)).css('border-bottom','3px solid green');
			$('#'+(secNo+1)+'optionlist'+(quesNo+1)).css('border','1px solid green');
		}
		else
			{
			$('#'+(secNo+1)+'answerStatus'+(quesNo+1)).html('<span class="btn btn-social-icon bg-red btn-xs btn-flat" style="margin-bottom:-13px;cursor: default;"><i class="fa fa-close"  title="wrong answer" style="margin-left: 4px;"></i></span>');
			$('#'+(secNo+1)+'answerStatus'+(quesNo+1)).css('border-bottom','3px solid #dd4b39');
			$('#'+(secNo+1)+'optionlist'+(quesNo+1)).css('border','1px solid #dd4b39');
			}
		}
	} catch (err) {
		console.log(err.message)
	}
}

/**
 * @summary Function for creating question html for appending in test dynamically.
 * @returns no.
 */
function createSortListTypeQuestionList(secNo, quesNo) {
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
			+ '<div class="row" style="text-align:center;margin:0px !important" id="'+secNo+'answerStatus'+quesNo+'"></div>'
			+ '<div id="'
			+ secNo
			+ 'optionlist'
			+ quesNo
			+ '" class="row" style="margin:0px !important">'

			+ '<div class="col-sm-5 source_table">'
			+ '<table class="table" id="'
			+ secNo
			+ 'source_table'
			+ quesNo
			+ '">'
			+ '<thead><tr><th style="text-align:center;">Source</th></tr></thead>'
			+ '<tbody id="'
			+ secNo
			+ 'usersourceoptionlist'
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
			+ '<thead><tr><th style="text-align:center;">Target</th></tr></thead>'
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
			+ 'explaindiv'
			+ quesNo
			+ '" style="display:none;border-color: green;border-style: solid;border-width: 1px;margin-top:10px">'
			+ '<h3>Explanation</h3><p id="'
			+ secNo
			+ 'ansexplain'
			+ quesNo
			+ '"> </p></div>'
			+ '<div>'
			+ '<nav>'
			+ '<ul class="pager pull-left">'
			+ '<li style=""><button type="button" onclick="quit()" class="btn btn-flat btn-success button-width">'
			+ '<i class="fa fa-close">&nbsp;&nbsp;&nbsp;</i>Quit</button></li>'
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
			+ ')" >Explanation</button>'
			+ '<li style="padding-right:12px;"><button type="button" id="'
			+ secNo
			+ 'previous'
			+ quesNo
			+ '" class="btn btn-flat btn-success button-width"	 onclick="previous('
			+ secNo
			+ ','
			+ quesNo
			+ ')" disabled>'
			+ '<i class="fa fa-backward"></i> Previous'
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
			+ '<i class="fa fa-forward"></i> Next</button></li>'
			+ '</ul>'
			+ '</nav>' + '</div>' + '</div>';

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
			+ '</div>&nbsp;'
			+ '<div class="imgset pull-left padding-left" id="">'
			+ test.sectionlist[secNo - 1].questionList[quesNo - 1].optionList[optn - 1].optionName
			+ '</div>' + '</div>' + '</div></td></tr>';
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
function addSortListTypeTargetOptions(secNo, quesNo, optn) {
	var opnHTML = '<tr><td id="' + secNo + 'target' + quesNo + 'row' + optn
			+ '"></td></tr>';
	return opnHTML;
}