/**
 * @summary This is used for common operation for review for test.
 * @author ankur
 * @date 03 Sep 2016.
 * 
 */

/**
 * @summary This is used getting question list using ajax calling.
 * @returns no.
 */
var getreviewdata = function() {
	$("#allquestion").empty();
	$("#next").show();
	$("#previous").hide();
	$("#overlay").show();
	$.ajax({
		url : "api/getreviewattemptedtest/" + $("#idd").val() + "/"
				+ $("#userid").val(),
		type : "GET",
		beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', 'Browser');
		},
		error : function() {
			$("#overlay").hide();
			alert("error");
		},
		success : function(data) {
			$("#questionlist").show();
			test = data.review;
			$("#time").html(test.testTime);
			if (test.testTime == "") {
				$("#divtime").hide();
			}
			$("#testname").html(test.testName);
			addQuestionList();
			$('.imgset img').css({
				'display' : 'block',
				'max-width' : '100%',
				'height' : 'auto'
			});
			$("#overlay").hide();
		}
	});
}

/**
 * @summary function for extracting question data from json object.
 * @returns no.
 */

function addQuestionList() {
	try {
		for (var sec = 0; sec < test.sectionlist.length; sec++) {
			if (sec == 0) {
				$("#sectionSelectList").append(
						'<option value="' + (sec + 1) + '" selected>'+messages['lbl.section']+' '
								+ (sec + 1) + '</option>');
			} else {
				$("#sectionSelectList").append(
						'<option value="' + (sec + 1) + '">'+messages['lbl.section']+' '
								+ (sec + 1) + '</option>');
			}
			for (var ques = 0; ques < test.sectionlist[sec].questionList.length; ques++) {
				/**
				 * get the type of question.
				 */
				var questionType = test.sectionlist[sec].questionList[ques].questionType;
				/**
				 * make case on question type.
				 */
				switch (questionType) {
				/**
				 * if question is multiple choice type.
				 */
				case MULTIPLE_CHOICE_TYPE:
					addMultipleChoiceTypeQuestion(sec, ques)
					break;
				/**
				 * if question is single choice type.
				 */
				case SINGLE_CHOICE_TYPE:
					addMultipleChoiceTypeQuestion(sec, ques)
					break;
				/**
				 * if question is sort list type.
				 */
				case SORT_LIST_TYPE:
					addSortListTypeQuestion(sec, ques);
					break;
				/**
				 * if question is choice matrix type.
				 */
				case CHOICE_MATRIX_TYPE:
					addChoiceMatrixTypeQuestion(sec, ques);
					break;
				/**
				 * if question is choice matrix type.
				 */
				case CLASSIFICATION_TYPE:
					addClassificationTypeQuestion(sec, ques);
					break;
				/**
				 * if question is match list type.
				 */
				case MATCH_LIST:
					addMatchListTypeQuestion(sec, ques);
					break;
				}
				if (sec == 0 && ques == 0) {
					$("#" + (sec + 1) + "questionDiv" + (ques + 1)).show();
					$("#questionNo").text(ques + 1);
					$("#totalQuestions").text(
							test.sectionlist[sec].questionList.length);
				}
				if (test.sectionlist[sec].questionList[ques].explanation == null
						|| test.sectionlist[sec].questionList[ques].explanation == "") {
					$("#" + (sec + 1) + "explain" + (ques + 1)).prop(
							"disabled", true);
				} else {
					$("#" + (sec + 1) + "ansexplain" + (ques + 1))
							.html(
									test.sectionlist[sec].questionList[ques].explanation);
				}

			}
			if (test.sectionlist[sec].questionList.length == 1) {
				$("#" + (sec + 1) + "next1").prop("disabled", true);
				$("#" + (sec + 1) + "previous1").prop("disabled", true);
			}
		}
	} catch (err) {
		console.log(err.message);
	}
}

/**
 * function for providing functionality for next button on page.
 * 
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function next(secNo, quesNo) {
	$("#" + secNo + "questionDiv" + quesNo).hide();
	$("#" + secNo + "questionDiv" + (quesNo + 1)).show();
	$("#questionNo").text((quesNo + 1));
	if (quesNo == test.sectionlist[secNo - 1].questionList.length - 1) {
		$("#" + secNo + "next" + (quesNo + 1)).prop("disabled", true);
	}
	$("#" + secNo + "previous" + (quesNo + 1)).prop("disabled", false);
}

/**
 * @summary function for providing functionality for previous button on page.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function previous(secNo, quesNo) {
	$("#" + secNo + "questionDiv" + (quesNo)).hide();
	$("#" + secNo + "questionDiv" + (quesNo - 1)).show();
	$("#questionNo").text(quesNo - 1);
	if (quesNo == 2) {
		$("#" + secNo + "previous1").prop("disabled", true);
	}
	$("#" + secNo + "next" + (quesNo - 1)).prop("disabled", false);
}

/**
 * @summary This is used show the explanation for question.
 * @param secNo
 * @param quesNo
 * @returns no.
 */
function showexplaination(secNo, quesNo) {
	$("#" + secNo + "explaindiv" + quesNo).show();
}

/**
 * @summary This is using quit the review page.
 * @returns no.
 */
function quit() {
	location.href = 'viewTestResult?id=' + $("#idd").val();
}

/**
 * @summary This function is used for choosing randomly section.
 * @returns no.
 */
var changeSection = function() {
	var secNo = $("#sectionSelectList").val();
	$(".questionAllDiv").hide();
	$("#questionNo").text("1");
	$("#totalQuestions").text(test.sectionlist[secNo - 1].questionList.length);
	$("#" + secNo + "questionDiv1").show();
}