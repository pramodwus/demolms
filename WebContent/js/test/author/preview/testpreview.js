/**
 * @summary This file would be used for performing common operation in test preview.
 * @author ankur
 * @date 03 Sep 2016
 */

/**
 * @summary function is used getting value from url.
 * @returns vars.
 */
function getUrlVars() {
	var vars = {};
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
			function(m, key, value) {
				vars[key] = value;
			});
	return vars;
}

/**
 * @summary function for extracting data from json object.
 * @returns no.
 */
function addQuestionList() {
	try {
		for (var sec = 0; sec < test.sectionlist.length; sec++) {
			var sectionId = test.sectionlist[sec].sectionId;
			$("#allQuestionListDiv")
					.append(
							'<div id="sectionQuesList'
									+ (sec + 1)
									+ '" class="sectionQuesList" style="display:none"></div>');
			qlist.push(0);
			if (sec == 0) {
				$("#sectionSelectList").append(
						'<option value="' + (sec + 1) + '" selected>' + messages['lbl.section'] + ' '
								+ (sec + 1) + '</option>');
				$("#sectiontitle").text(test.sectionlist[sec].sectionName);
				$("#selectedSectionForCurrentQues").text(
						"( " +messages['lbl.section']+ " " + (sec + 1) + " )");
			} else {
				$("#sectionSelectList").append(
						'<option value="' + (sec + 1) + '">' + messages['lbl.section'] + ' '
								+ (sec + 1) + '</option>');
			}
			for (var ques = 0; ques < test.sectionlist[sec].questionList.length; ques++) {
				/**
				 * get question type.
				 */
				var questionType = test.sectionlist[sec].questionList[ques].questionType;
				/**
				 * making cases based on question type.
				 */
				switch (questionType) {
				/**
				 * if question type is multiple choice type.
				 */
				case MULTIPLE_CHOICE_TYPE:
					addMultipleChoiceTypeQuestion(sec, ques)
					break;
				/**
				 * if question type is single choice type.
				 */
				case SINGLE_CHOICE_TYPE:
					addMultipleChoiceTypeQuestion(sec, ques)
					break;
				/**
				 * if question type is sort list type.
				 */
				case SORT_LIST_TYPE:
					addSortListTypeQuestion(sec, ques);
					break;
				/**
				 * if question type is choice matrix type.
				 */
				case CHOICE_MATRIX_TYPE:
					addChoiceMatrixTypeQuestion(sec, ques);
					break;
				/**
				 * if question type is classification type.
				 */
				case CLASSIFICATION_TYPE:
					addClassificationTypeQuestion(sec, ques);
					break;
				/**
				 * if question type is match list type.
				 */
				case MATCH_LIST:
					addMatchListTypeQuestion(sec, ques);
					break;
				}

				$("#" + (sec + 1) + "questiontitle" + (ques + 1)).html(
						test.sectionlist[sec].questionList[ques].questionName);

				if (ques % listlimitsize == 0) {
					qlist[sec] = ques / listlimitsize + 1;
					$("#sectionQuesList" + (sec + 1))
							.append(
									'<ul class="sidebar-menu allQuestionList'
											+ (sec + 1)
											+ '" style="display:none;background-color: white;" id="'
											+ (sec + 1) + 'allQuestionList'
											+ qlist[sec] + '"></ul>');
				}

				$("#" + (sec + 1) + "allQuestionList" + qlist[sec]).append(
						'<li onclick="showRandomQuestion(' + (sec + 1) + ','
								+ (ques + 1)
								+ ')" class="treeview cursor" id="' + (sec + 1)
								+ 'questionListNumber' + (ques + 1)
								+ '"><a><span>' +messages['lbl.question']+ ' ' + (ques + 1)
								+ '</span></a></li>');
				if (sec == 0 && ques == 0) {
					$("#" + (sec + 1) + "questionDiv" + (ques + 1)).show();
					$("#questionNo").text(ques + 1);
					$("#question_mark")
							.text(
									test.sectionlist[sec].questionList[ques].questionMark);
					$("#totalQuestions").text(
							test.sectionlist[sec].questionList.length);
					fetchFormulaDetails(sec + 1, ques + 1);
					$("#" + (sec + 1) + "questionListNumber" + (ques + 1))
							.addClass('active');
					$("#" + (sec + 1) + "allQuestionList" + qlist).show();
					$("#previousSection").attr('onclick',
							'previousSection(' + (sec + 1) + ')');
					$("#nextSection").attr('onclick',
							'nextSection(' + (sec + 1) + ')');
					$("#sectionQuesList" + (sec + 1)).show();
				}
				if (ques == test.sectionlist[sec].questionList.length - 1) {
					$("#sectionQuesList" + (sec + 1))
							.append(
									'<ul class="pagination"><li><button type="button" onclick="showPreviousQuestonList('
											+ (sec + 1)
											+ ',1)" id="listPrevious'
											+ (sec + 1)
											+ '" class="btn btn-flat btn-default cursor" disabled>«</button></li><li><button type="button" class="btn btn-flat btn-default cursor"  onclick="showNextQuestonList('
											+ (sec + 1)
											+ ',1)" id="listNext'
											+ (sec + 1)
											+ '">»</button></li></ul>');
				}
				if (test.sectionlist[sec].questionList.length <= listlimitsize) {
					$("#listPrevious" + (sec + 1)).attr("disabled", true);
					$("#listNext" + (sec + 1)).attr("disabled", true);
				}
				test.sectionlist[sec].questionList[ques].explanation == null ? $(
						"#" + (sec + 1) + "description" + (ques + 1)).hide()
						: (test.sectionlist[sec].questionList[ques].explanation
								.trim().length == 0 ? $(
								"#" + (sec + 1) + "description" + (ques + 1))
								.hide()
								: $("#" + (sec + 1) + "ansexplain" + (ques + 1))
										.html(
												test.sectionlist[sec].questionList[ques].explanation));
			}

			if (test.sectionlist[sec].questionList.length == 1) {
				$("#next").prop("disabled", true);
				$("#previous").prop("disabled", true);
			}
		}
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * @summary function for providing functionality for next button on page.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function next(secNo, quesNo) {
	$("#" + secNo + "questionDiv" + quesNo).hide();
	$("#" + secNo + "questionDiv" + (quesNo + 1)).show();
	$("#next").attr('onclick', 'next(' + secNo + ',' + (quesNo + 1) + ')');
	$("#previous").attr('onclick', 'previous(' + secNo + ',' + (quesNo) + ')');
	$("#questionNo").text((quesNo + 1));
	$("#question_mark").text(
			test.sectionlist[secNo - 1].questionList[quesNo].questionMark);
	$("#next").html(messages['lbl.next']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	$("#previous")
			.html(
					'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previous']);
	if ((quesNo == test.sectionlist[secNo - 1].questionList.length - 1)
			&& (secNo < test.sectionlist.length)) {
		$("#next").attr('onclick',
				'moveNextPreviousSection(' + (secNo + 1) + ')');
		$("#next")
				.html(messages['lbl.nextsection']
						+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	} else if (quesNo == test.sectionlist[secNo - 1].questionList.length - 1) {
		$("#next").prop("disabled", true);
	}
	$("#previous").prop("disabled", false);
	$(".treeview").removeClass("active");
	$("#" + secNo + "questionListNumber" + (quesNo + 1)).addClass('active');
	if (quesNo % listlimitsize == 0) {
		var listNumber = quesNo / listlimitsize;
		(listNumber + 1) == qlist[secNo - 1] ? $("#listNext" + secNo).attr(
				"disabled", true) : $("#listNext" + secNo).attr("disabled",
				false);
		$("#listPrevious" + secNo).attr("disabled", false);
		$("#listNext" + secNo).attr("onclick",
				'showNextQuestonList(' + secNo + ',' + (listNumber + 1) + ')');
		$("#listPrevious" + secNo).attr(
				"onclick",
				'showPreviousQuestonList(' + secNo + ',' + (listNumber + 1)
						+ ')');
		$(".sectionQuesList").hide();
		$(".allQuestionList" + secNo).hide();
		$("#sectionQuesList" + secNo).show();
		$("#" + secNo + "allQuestionList" + (listNumber + 1)).show();
	} else {
		var listNumber = Math.ceil(quesNo / listlimitsize);
		listNumber == qlist[secNo - 1] ? $("#listNext" + secNo).attr(
				"disabled", true) : $("#listNext" + secNo).attr("disabled",
				false);
		listNumber == 1 ? $("#listPrevious" + secNo).attr("disabled", true)
				: $("#listPrevious" + secNo).attr("disabled", false);
		$("#listNext" + secNo).attr("onclick",
				'showNextQuestonList(' + secNo + ',' + listNumber + ')');
		$("#listPrevious" + secNo).attr("onclick",
				'showPreviousQuestonList(' + secNo + ',' + listNumber + ')');
		$(".sectionQuesList").hide();
		$(".allQuestionList" + secNo).hide();
		$("#sectionQuesList" + secNo).show();
		$("#" + secNo + "allQuestionList" + listNumber).show();
	}
	fetchFormulaDetails(secNo, quesNo + 1);
}

/**
 * @summary function for providing functionality for previous button on page.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function previous(secNo, quesNo) {
	$("#" + secNo + "questionDiv" + (quesNo + 1)).hide();
	$("#" + secNo + "questionDiv" + (quesNo)).show();
	$("#previous").attr('onclick',
			'previous(' + secNo + ',' + (quesNo - 1) + ')');
	$("#next").attr('onclick', 'next(' + secNo + ',' + (quesNo) + ')');
	$("#questionNo").text(quesNo);
	$("#question_mark").text(
			test.sectionlist[secNo - 1].questionList[quesNo - 1].questionMark);
	$("#next")
			.html(messages['lbl.next']
					+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	$("#previous")
			.html(
					'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previous']);
	if ((quesNo == 1) && (secNo > 1)) {
		$("#previous").attr('onclick',
				'moveNextPreviousSection(' + (secNo - 1) + ')');
		$("#previous")
				.html(
						'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previoussection']);
	} else if (quesNo == 1) {
		$("#previous").prop("disabled", true);
	}
	$("#next").prop("disabled", false);
	$(".treeview").removeClass("active");
	$("#" + secNo + "questionListNumber" + (quesNo)).addClass('active');
	if (quesNo % listlimitsize == 0) {
		var listNumber = quesNo / listlimitsize;
		listNumber == 1 ? $("#listPrevious" + secNo).attr("disabled", true)
				: $("#listPrevious" + secNo).attr("disabled", false);
		$("#listNext" + secNo).attr("disabled", false);
		$("#listNext" + secNo).attr("onclick",
				'showNextQuestonList(' + secNo + ',' + listNumber + ')');
		$("#listPrevious" + secNo).attr("onclick",
				'showPreviousQuestonList(' + secNo + ',' + listNumber + ')');

		$(".sectionQuesList").hide();
		$(".allQuestionList" + secNo).hide();
		$("#sectionQuesList" + secNo).show();
		$("#" + secNo + "allQuestionList" + listNumber).show();
	} else {
		var listNumber = Math.ceil(quesNo / listlimitsize);
		listNumber == qlist[secNo - 1] ? $("#listNext" + secNo).attr(
				"disabled", true) : $("#listNext" + secNo).attr("disabled",
				false);
		listNumber == 1 ? $("#listPrevious" + secNo).attr("disabled", true)
				: $("#listPrevious" + secNo).attr("disabled", false);
		$("#listNext" + secNo).attr("onclick",
				'showNextQuestonList(' + secNo + ',' + listNumber + ')');
		$("#listPrevious" + secNo).attr("onclick",
				'showPreviousQuestonList(' + secNo + ',' + listNumber + ')');
		$(".sectionQuesList").hide();
		$(".allQuestionList" + secNo).hide();
		$("#sectionQuesList" + secNo).show();
		$("#" + secNo + "allQuestionList" + listNumber).show();
	}

	fetchFormulaDetails(secNo, quesNo);
}

/**
 * @summary function for quit the test preview.
 * @returns no.
 */
function quit() {
	window.close();
}

/**
 * @summary function for showing math's formula pop up.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function fetchFormulaDetails(secNo, quesNo) {
	$("#formulaContent").empty();
	if (test.sectionlist[secNo - 1].questionList[quesNo - 1].isFormula == 1) {
		$("#formulaContent")
				.html(
						test.sectionlist[secNo - 1].questionList[quesNo - 1].mathFormula);
		$("#mathformula").show();
	} else {
		$("#mathformula").hide();
	}

}

/**
 * @summary function for showing the question if user click on a question in
 *          list.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function showRandomQuestion(secNo, quesNo) {
	$(".questionAllDiv").hide();
	$("#" + secNo + "questionDiv" + quesNo).show();
	$("#next").attr('onclick', 'next(' + secNo + ',' + quesNo + ')');
	$("#previous").attr('onclick',
			'previous(' + secNo + ',' + (quesNo - 1) + ')');
	$("#questionNo").text(quesNo);
	$("#question_mark").text(
			test.sectionlist[secNo - 1].questionList[quesNo - 1].questionMark);
	$("#totalQuestions").text(test.sectionlist[secNo - 1].questionList.length);
	$("#next")
			.html(messages['lbl.next']
					+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	if ((quesNo == test.sectionlist[secNo - 1].questionList.length)
			&& (secNo < test.sectionlist.length)) {
		$("#next").attr('onclick',
				'moveNextPreviousSection(' + (secNo + 1) + ')');
		$("#next")
				.html(messages['lbl.nextsection']
						+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	} else if (quesNo == test.sectionlist[secNo - 1].questionList.length) {
		$("#next").prop("disabled", true);
	} else {
		$("#next").prop("disabled", false);
	}
	$("#previous").prop("disabled", false);
	$("#previous")
			.html(
					'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previous']);
	if ((quesNo == 1) && (secNo > 1)) {
		$("#previous").attr('onclick',
				'moveNextPreviousSection(' + (secNo - 1) + ')');
		$("#previous")
				.html(
						'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previoussection']);
		$("#previous").prop("disabled", false);
	} else if (quesNo == 1) {
		$("#previous").prop("disabled", true);
	}
	$(".treeview").removeClass("active");
	$("#" + secNo + "questionListNumber" + quesNo).addClass('active');
	fetchFormulaDetails(secNo, quesNo);
}

/**
 * @summary function for providing functionality for showing the next question
 *          list on next button.
 * @param secNo
 * @param listNo
 * @returns no.
 */
function showNextQuestonList(secNo, listNo) {
	$("#" + secNo + "allQuestionList" + listNo).hide();
	$("#" + secNo + "allQuestionList" + (listNo + 1)).show();
	$("#listNext" + secNo).attr("onclick",
			'showNextQuestonList(' + secNo + ',' + (listNo + 1) + ')');
	$("#listPrevious" + secNo).attr("onclick",
			'showPreviousQuestonList(' + secNo + ',' + (listNo + 1) + ')');
	if (qlist[secNo - 1] == (listNo + 1)) {
		$("#listNext" + secNo).attr("disabled", true);
	}
	$("#listPrevious" + secNo).attr("disabled", false);
}

/**
 * @summary function for providing functionality for showing the previous
 *          question list on previous button.
 * @param secNo
 * @param listNo
 * @returns no.
 */
function showPreviousQuestonList(secNo, listNo) {
	$("#" + secNo + "allQuestionList" + listNo).hide();
	$("#" + secNo + "allQuestionList" + (listNo - 1)).show();
	$("#listNext" + secNo).attr("onclick",
			'showNextQuestonList(' + secNo + ',' + (listNo - 1) + ')');
	$("#listPrevious" + secNo).attr("onclick",
			'showPreviousQuestonList(' + secNo + ',' + (listNo - 1) + ')');
	if (listNo == 2) {
		$("#listPrevious" + secNo).attr("disabled", true);
	}
	$("#listNext" + secNo).attr("disabled", false);
}

/**
 * @summary This function is used for choosing randomly section.
 * @returns no.
 */
function changeSection() {
	var secNo = $("#sectionSelectList").val();
	moveNextPreviousSection(secNo);
}

/**
 * @summary This function is used for changing section.
 * @param secNo
 * @returns no.
 */
function moveNextPreviousSection(secNo) {
	$("#sectiontitle").text(test.sectionlist[secNo - 1].sectionName);
	$("#selectedSectionForCurrentQues").text("( " + messages['lbl.section'] + " " + secNo + " )");
	$("#sectionSelectList").val(secNo);
	$("#listNext" + secNo).attr("onclick",
			'showNextQuestonList(' + secNo + ',1)');
	$("#listPrevious" + secNo).attr("onclick",
			'showPreviousQuestonList(' + secNo + ',1)');
	$("#listPrevious" + secNo).prop('disabled', true);
	if (test.sectionlist[secNo - 1].questionList.length <= listlimitsize) {
		$("#listNext" + secNo).attr("disabled", true);
	}
	$(".treeview").removeClass("active");
	$("#" + secNo + "questionListNumber1").addClass('active');
	$("#previous").attr('onclick', 'previous(' + secNo + ',1)');
	$("#previous").prop('disabled', true);
	$("#next").prop('disabled', false);
	$("#next").attr('onclick', 'next(' + secNo + ',1)');
	$("#next")
			.html(messages['lbl.next']
					+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	if ((secNo < test.sectionlist.length)
			&& (test.sectionlist[secNo - 1].questionList.length < 2)) {
		$("#next").attr('onclick',
				'moveNextPreviousSection(' + (secNo + 1) + ')');
		$("#next")
				.html(messages['lbl.nextsection']
						+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	} else if (test.sectionlist[secNo - 1].questionList.length < 2) {
		$("#next").prop('disabled', true);
	}
	$("#previous")
			.html(
					'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previous']);
	if (secNo > 1) {
		$("#previous").attr('onclick',
				'moveNextPreviousSection(' + (secNo - 1) + ')');
		$("#previous")
				.html(
						'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previoussection']);
		$("#previous").prop('disabled', false);
	}
	$(".questionAllDiv").hide();
	$(".sectionQuesList").hide();
	$(".allQuestionList" + secNo).hide();

	$("#questionNo").text(1);
	$("#question_mark").text(
			test.sectionlist[secNo - 1].questionList[0].questionMark);
	$("#totalQuestions").text(test.sectionlist[secNo - 1].questionList.length);
	$("#" + secNo + "allQuestionList1").show();
	$("#" + secNo + "questionDiv1").show();
	$("#sectionQuesList" + secNo).show();
}

/**
 * function is used for showing the next section.
 * 
 * 
 * function nextSection(secNo){
 * $("#previousSection").attr('onclick','previousSection('+(secNo+1)+')');
 * $("#nextSection").attr('onclick','nextSection('+(secNo+1)+')');
 * $("#previous").attr('onclick','previous('+(secNo+1)+',1)');
 * $("#previous").prop('disabled',true); $("#next").prop('disabled',false);
 * $("#next").attr('onclick','next('+(secNo+1)+',1)');
 * if(test.sectionlist[secNo].questionList.length<2){
 * $("#next").prop('disabled',true); }
 * 
 * $(".questionAllDiv").hide(); $("#sectionQuesList"+secNo).hide();
 * $(".allQuestionList"+(secNo+1)).hide();
 * if(test.sectionlist.length==(secNo+1)){
 * $("#nextSection").prop('disabled',true); } $("#previousSection").prop(
 * "disabled", false); $("#questionNo").text(1);
 * $("#totalQuestions").text(test.sectionlist[secNo].questionList.length);
 * $("#"+(secNo+1)+"allQuestionList1").show();
 * $("#"+(secNo+1)+"questionDiv1").show();
 * $("#sectionQuesList"+(secNo+1)).show(); } /** function is used for showing
 * the previous section.
 * 
 * 
 * function previousSection(secNo){
 * $("#previousSection").attr('onclick','previousSection('+(secNo-1)+')');
 * $("#nextSection").attr('onclick','nextSection('+(secNo-1)+')');
 * $("#previous").attr('onclick','previous('+(secNo-1)+',1)');
 * $("#previous").prop('disabled',true); $("#next").prop('disabled',false);
 * $("#next").attr('onclick','next('+(secNo-1)+',1)');
 * if(test.sectionlist[secNo-1].questionList.length<2){
 * $("#next").prop('disabled',true); } $(".questionAllDiv").hide();
 * $("#sectionQuesList"+secNo).hide(); $(".allQuestionList"+(secNo-1)).hide();
 * if(secNo==2){ $("#previousSection").prop('disabled',true); }
 * $("#nextSection").prop( "disabled", false);
 * $("#totalQuestions").text(test.sectionlist[secNo-2].questionList.length);
 * $("#"+(secNo-1)+"allQuestionList1").show();
 * $("#"+(secNo-1)+"questionDiv1").show();
 * $("#sectionQuesList"+(secNo-1)).show(); }
 * 
 */
