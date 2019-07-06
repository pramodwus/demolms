/**
 * @summary This file would be used for performing sort list type question operation in test.
 * @author ankur
 * @date 04 Sep 2016
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
		 * set question title
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
									optn + 1));
		}
		/**
		 * enable drag property on all options in source of question.
		 */
		$("#" + (secNo + 1) + "source_table" + (quesNo + 1) + " .lrn_btn_sort")
				.draggable(
						{
							connectWith : "#" + (secNo + 1) + "target_table"
									+ (quesNo + 1) + " .target_drop",
							revert : "invalid",
							start : function(event, ui) {
								ui.helper.addClass('active');
							},
							stop : actionPerformed
						});
		/**
		 * enable drop property for all options in target of question.
		 */
		$("#" + (secNo + 1) + "target_table" + (quesNo + 1) + " tr td")
				.droppable(
						{
							accept : "#" + (secNo + 1) + "source_table"
									+ (quesNo + 1) + " .lrn_btn_sort",
							connectWith : "#" + (secNo + 1) + "source_table"
									+ (quesNo + 1) + " .source_drop",
							drop : function(event, ui) {
							}
						});
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary This function would be performed when user drop a option from source
 *          to target for making right order of options.
 * @param ev
 * @param ui
 * @returns no.
 */
function actionPerformed(ev, ui) {
	/**
	 * remove active class from div.
	 */
	ui.helper.removeClass('active');
	/**
	 * if event occurs on drag.
	 */
	if (ev.originalEvent != null) {
		/**
		 * get id of source table in which this option exists.
		 */
		var sourcetableId = $(this).closest("table").attr('id');
		/**
		 * split source table id on source_table
		 */
		var arr = sourcetableId.split('source_table');
		/**
		 * assign section order.
		 */
		var secNo = arr[0];
		/**
		 * assign question order in section list.
		 */
		var quesNo = arr[1];
		/**
		 * get id of target table in which user dropped this option.
		 */
		var targetTableId = secNo + "target_table" + quesNo;
		/**
		 * get cell id of target table.
		 */
		var targetId = $(ev.originalEvent.target).attr('id');
		/**
		 * get cell id of source table
		 */
		var sourceId = $(ev.target).attr('id');
		/**
		 * if target table cell is empty. means there is no dropped option
		 * inside cell.
		 */
		if ($('#' + targetId).is(':empty')) {
			/**
			 * make html data for dropping in target cell of target table.
			 */
			var appendHtml = '<div class="col-sm-12 lrn_btn_sort" id="'
					+ sourceId + '">' + $(this).html() + '</div>';
			/**
			 * remove dragged div from source table.
			 */
			$(this).closest("div.lrn_btn_sort").remove();
			/**
			 * append html data into target cell of target table.
			 */
			$("#" + targetId).html(appendHtml);
			/**
			 * make re-enable dropable on target table.
			 */
			$("#" + targetTableId + " tr td").droppable({
				accept : "#" + sourcetableId + " .lrn_btn_sort",
				connectWith : "#" + sourcetableId + " .source_drop",
				start : function(event, ui) {
					ui.helper.addClass('active');
				},
				drop : function(event, ui) {
				}
			});
			/**
			 * enable sort property on row of target table.
			 */
			$("#" + targetTableId + " tbody").sortable({
				update : updateDataOnSort
			});
			/**
			 * iterate on row of target table for getting order of dropped
			 * options inside cell.
			 */
			iterateTableCell(secNo, quesNo, targetTableId);
		}
		/**
		 * if target table cell is not empty. means there is already dropped
		 * option inside cell.
		 */
		else {
			/**
			 * make html of drag div.
			 */
			var appendHtml = '<div class="col-sm-12 lrn_btn_sort" id="'
					+ sourceId + '">' + $(this).html() + '</div>';
			/**
			 * append on drag data into dragged cell of source item means no
			 * changes would be peroformed if target cell already contains
			 * dropped data.
			 */
			$(this).closest("td").html(appendHtml);
			/**
			 * re enable the drag property on source table of this question.
			 */
			$("#" + sourcetableId + " .lrn_btn_sort").draggable({
				connectWith : "#" + targetTableId + " .target_drop",
				revert : "invalid",
				start : function(event, ui) {
					ui.helper.addClass('active');
				},
				stop : actionPerformed
			});
		}
	}
}

/**
 * @summary This would be called when user update order of taget row using sort
 *          property.
 * @param event
 * @param ui
 * @returns no.
 */
function updateDataOnSort(event, ui) {
	/**
	 * get id of target table on which sort has been peroformed.
	 */
	var targetTableId = $(this).closest("table").attr('id');
	/**
	 * split id on target_table'.
	 */
	var arr = targetTableId.split('target_table');
	/**
	 * assign section order of this target table.
	 */
	var secNo = arr[0];
	/**
	 * assign question order of this target table inside section.
	 */
	var quesNo = arr[1];
	/**
	 * iterate on row of target table for getting order of sort options inside
	 * cell.
	 */
	iterateTableCell(secNo, quesNo, targetTableId);
}

/**
 * @summary This function is used for getting the order of options set by user
 *          as target options.
 * @param secNo
 * @param quesNo
 * @param targetTableId
 * @returns no.
 */
function iterateTableCell(secNo, quesNo, targetTableId) {
	/**
	 * initialize answerValue of target options.
	 */
	var answerValue = []
	/**
	 * iterate on every row of taget table on a particular question.
	 */
	$("#" + targetTableId + " tbody tr").each(function() {
		/**
		 * get cell id.
		 */
		var cellId = $(this).find("div.lrn_btn_sort").attr('id');
		/**
		 * if cell id is null means there is no dropped options.
		 */
		if (cellId != null) {
			/**
			 * split on optionOrder.
			 */
			var cellIdArray = cellId.split('optionOrder');
			/**
			 * get order of this cell in source options.
			 */
			var order = cellIdArray[1].substring(0, 1);
			/**
			 * push order value of this cell.
			 */
			answerValue.push(Number(order));
		}
		/**
		 * if cell id is null.
		 */
		else {
			/**
			 * so there is no dropped options . in this case take order as 0 of
			 * not dragged options.
			 */
			answerValue.push(0);
		}
	});
	/**
	 * Save this response of user into json.
	 */
	saveSortListTypeQuestionUserResponseInJson(secNo, quesNo, answerValue);
	// console.log(JSON.stringify(answerValue));
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
			+ '<thead><tr><th style="text-align:center;">'+messages['lbl.source']+'</th></tr></thead>'
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
			+ '<thead><tr><th style="text-align:center;">'+messages['lbl.target']+'</th></tr></thead>'
			+ '<tbody id="'
			+ secNo
			+ 'usertargetoptionlist'
			+ quesNo
			+ '"></tbody>' + '</table>' + '</div>' + '</div>' + '</div>';
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
			+ '<div class="input-group-addon" style="background-color: transparent;">'
			+ '<i class="fa fa-align-justify"></i>'
			+ '</div>'
			+ '<div class="imgset pull-left" id="">'
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
function addSortListTypeTargetOptions(secNo, quesNo, optn) {
	var opnHTML = '<tr><td id="' + secNo + 'target' + quesNo + 'row' + optn
			+ '"></td></tr>';
	return opnHTML;
}

/**
 * @summary This is used save the response into json of sort list type
 *          question..
 * @param secNo
 * @param quesNo
 * @param answerValue
 */
function saveSortListTypeQuestionUserResponseInJson(secNo, quesNo, answerValue) {
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
			submitTestJson.sectionlist[secPosition].questionList[quesPosition].givenAnswer = answerValue;
		} else {
			var response = {
				"questionId" : quesId,
				"givenAnswer" : answerValue
			};
			submitTestJson.sectionlist[secPosition].questionList.push(response);
		}
	} catch (err) {
		console.log(err.message);
	}
}