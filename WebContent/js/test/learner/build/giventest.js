/**
 * @summary This file would be used for performing common operation for all type question in given test.
 * @author ankur
 * @date 3 sep 2016
 */

/**
 * @summary Get Test Details.
 * @param testId
 * @returns no.
 */
var testDeatils = function(testId, userId) {
	$("#overlay").show();
	$.ajax({
		url : "api/testdetailsbyid/" + testId,
		type : "GET", // @summary request type is get.
		contentType : 'application/json',
		beforeSend : function(xhr) { // @summary appending data in header
			// before sending request.
			xhr.setRequestHeader('authorization', 'Browser');
			xhr.setRequestHeader('timestamp', 'Browser');
		},

		error : function() { // @summary would be execute if any error
			// occurs.
			$("#overlay").hide();
			alert("error");
		},
		success : function(data) { // @summary would be execute on successful
			if (data.test.testId != null) {
				/**
				 * @summary calling function for extracting course details from
				 *          list.
				 */
				extractTestData(data);
				/**
				 * if created user and given test user is same.
				 */
				if (Number(userId) == data.test.userId) {
					$("#starttest").attr('onclick',
							'callajax(' + testId + ',' + userId + ')');
				}
			} else {
				$("#starttest").prop('disabled', true);
				// window.close();
			}
			$("#overlay").hide();
		}
	});
}

/**
 * @summary Extract Test details from json object.
 * @param data
 * @returns no.
 */
var extractTestData = function(data) {
	$("#testName").text(data.test.testName);
	$("#numAttempt").val(data.test.maxAttempts);
	$("#testTime").text(
			data.test.timeMinute != null ? data.test.timeMinute : messages['lbl.nolimit']);
	$("#maxAttempts").text(
			data.test.maxAttempts == 0 ? messages['lbl.unlimited'] : data.test.maxAttempts);
	$("#isPublic").text(data.test.isPublic == 0 ? messages['lbl.public'] : messages['lbl.private']);
	$("#isReview")
			.html(
					data.test.isReview == 0 ? messages['lbl.no']
							: messages['lbl.yes']+"&nbsp;"
									+ (data.test.reviewWithCorrect == 0 ? "("+messages['lbl.withoutcorrectanswer']+")"
											: "("+messages['lbl.withcorrectanswer']+")"));
	$("#totalQuestion").text(data.test.totalQuestion);
	$("#maxMark").text(data.test.maxMark);
	$("#negMark").text(
			data.test.negMark != 0 ? data.test.negMark + "%"
					: messages['lbl.nonegativemarking']);
	$("#testPause").text(data.test.testPause == 0 ? messages['lbl.no'] : messages['lbl.yes']);
	$("#testDesc").text(data.test.testDesc);
	$("#testInstruct").html(data.test.testInstruct);
	$("#testNameInQuestionList").text(data.test.testName);

}

/**
 * @summary function for getting json data after clicking "given test " button
 *          by User.
 * @param testId
 * @param userId
 * @returns no.
 */
var givetestpage = function(testId, userId) {
	var flag = 0;
	if (Number($("#numAttempt").val()) == 0) {
		callajax(testId, userId);
	} else if (Number($("#countAttempt").val()) < Number($("#numAttempt").val())) {
		$("#overlay").show();
		callajax(testId, userId);
	} else {
		$("#attemptModal p").text(messages['msg.maximumassessmentattemptlimit'].replace('#maxattempt',$("#numAttempt").val()));
		$("#attemptModal").modal('show');
		$("#starttest").attr("disabled", "disabled");
	}
}

/**
 * @summary This Function is used getting test detail using ajax calling.
 * @param testId
 * @param userId
 * @returns no.
 */
var callajax = function(testId, userId) {
	$("#overlay").show();
	$.ajax({
		url : "api/givetest/" + Number(testId) + "/" + Number(userId),
		type : "GET",
		contentType : 'application/json',
		beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', 'Browser');
		},
		error : function() {
			$("#overlay").hide();
			alert("error");
		},
		success : function(data) {
			$("#instructions").hide();
			$("#questionlist").show();
			test = data;
			addQuestionList();
			$('.imgset img').css({
				'display' : 'block',
				'width' : '100%',
				'max-width' : '100%',
				'height' : 'auto'
			});
			$("#overlay").hide();
			if (test.testTime != null) {
				loadTimer(test.testTime);
			} else {
				loadTimer("0:1");
			}
			if(contentActivityId!=null && contentActivityId>0){
			    updateattemptedtestidincontentactivity(data.userTestAttemptId);
			 }
		}
	});
}

/**
 * @summary function for extracting data from json object.
 * @returns no.
 */
function addQuestionList() {
	try {
		submitTestJson.userTestAttemptId = test.userTestAttemptId;
		submitTestJson.testId = test.testId;
		var totalTestQuestions = 0;
		if (test.testPause == 1) {
			$("#pausebutton").show();
		}else{
			$("#pausebutton").hide();
		}
		
		if (test.testTime == null) {
		$("#timeType").text(messages['lbl.timetaken']+":");	
		}
		
		for (var sec = 0; sec < test.sectionlist.length; sec++) {
			var sectionJson = {
				"sectionId" : test.sectionlist[sec].sectionId,
				"sectionTime" : "0",
				"totalQuestions" : test.sectionlist[sec].questionList.length,
				"sectionStatus" : 0,
				"questionList" : []
			};
			submitTestJson.sectionlist.push(sectionJson);
			totalTestQuestions = totalTestQuestions
					+ test.sectionlist[sec].questionList.length;
			var sectionId = test.sectionlist[sec].sectionId;
			$("#allQuestionListDiv")
					.append(
							'<div id="sectionQuesList'
									+ (sec + 1)
									+ '" class="sectionQuesList" style="display:none"></div>');
			qlist.push(0);
			if (sec == 0) {
				$("#sectionSelectList").append(
						'<option value="' + (sec + 1) + '" selected>'+messages['lbl.section']+' '
								+ (sec + 1) + '</option>');
				$("#sectiontitle").text(test.sectionlist[sec].sectionName);
				$("#selectedSectionForCurrentQues").text(
						"( "+messages['lbl.section']+" " + (sec + 1) + " )");
			} else {
				$("#sectionSelectList").append(
						'<option value="' + (sec + 1) + '">'+messages['lbl.section']+' '
								+ (sec + 1) + '</option>');
			}
			for (var ques = 0; ques < test.sectionlist[sec].questionList.length; ques++) {
				/**
				 * get question id.
				 */
				var quesId = test.sectionlist[sec].questionList[ques].questionId;
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
								+ '"><a><span>'+messages['lbl.question']+' ' + (ques + 1)
								+ '</span></a></li>');
				var favid = test.sectionlist[sec].questionList[ques].favoriateId;
				var strnote = '<li style="display:none;cursor:pointer;"  class="navlist noteli" id="'
						+ (sec + 1)
						+ 'noteli'
						+ (ques + 1)
						+ '" onclick="opennotes('
						+ quesId
						+ ')"><i class="fa fa-pencil color-mainblue"></i></li>';
				if (test.sectionlist[sec].questionList[ques].favorites == 1) {
					strnote += '<li style="display:none;cursor:pointer;" class="navlist favli" id="'
							+ (sec + 1)
							+ 'favli'
							+ (ques + 1)
							+ '" onclick="setresetfavoriate('
							+ quesId
							+ ','
							+ 0
							+ ','
							+ (ques + 1)
							+ ','
							+ favid
							+ ','
							+ (sec + 1)
							+ ')"><i id="'
							+ (sec + 1)
							+ 'favicon'
							+ (ques + 1)
							+ '" class="fa fa-heart color-mainblue"></i></li>';
				} else {
					strnote += '<li style="display:none;cursor:pointer;"  class="navlist favli" id="'
							+ (sec + 1)
							+ 'favli'
							+ (ques + 1)
							+ '" onclick="setresetfavoriate('
							+ quesId
							+ ','
							+ 1
							+ ','
							+ (ques + 1)
							+ ','
							+ favid
							+ ','
							+ (sec + 1)
							+ ')"><i id="'
							+ (sec + 1)
							+ 'favicon'
							+ (ques + 1)
							+ '" class="fa fa-heart-o color-mainblue"></i></li>';
				}

				$("#noteul").append(strnote);
				if (sec == 0 && ques == 0) {
					$("#" + (sec + 1) + "questionDiv" + (ques + 1)).show();
					$("#questionNo").text("1");
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
					$("#" + (sec + 1) + "noteli" + (ques + 1)).show();
					$("#" + (sec + 1) + "favli" + (ques + 1)).show();
				}
				if (ques == test.sectionlist[sec].questionList.length - 1) {
					$("#sectionQuesList" + (sec + 1))
							.append(
									'<ul class="pagination"><li><button type="button" onclick="showPreviousQuestonList('
											+ (sec + 1)
											+ ',1)" id="listPrevious'
											+ (sec + 1)
											+ '" class="btn btn-flat btn-default cursor" disabled><i class="fa fa-angle-double-left"></i></button></li><li><button type="button" class="btn btn-flat btn-default cursor"  onclick="showNextQuestonList('
											+ (sec + 1)
											+ ',1)" id="listNext'
											+ (sec + 1)
											+ '"><i class="fa fa-angle-double-right"></i></button></li></ul>');
				}
				if (test.sectionlist[sec].questionList.length <= listlimitsize) {
					$("#listPrevious" + (sec + 1)).attr("disabled", true);
					$("#listNext" + (sec + 1)).attr("disabled", true);
				}
			}
			if (test.sectionlist[sec].questionList.length == 1) {
				$("#next").prop("disabled", true);
				$("#previous").prop("disabled", true);
			}
		}

		submitTestJson.totalQuestion = totalTestQuestions;
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
	$("#next").removeClass('btn-danger');
	$("#question_mark").text(
			test.sectionlist[secNo - 1].questionList[quesNo].questionMark);
	$("#next")
			.html(
					messages['lbl.next']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	$("#previous")
			.html(
					'<font size="4px"><i class="fa fa-angle-double-left"></i></font>&nbsp;&nbsp;&nbsp;'+messages['lbl.previous']);
	if ((quesNo == test.sectionlist[secNo - 1].questionList.length - 1)
			&& (secNo < test.sectionlist.length)) {
		$("#next").attr('onclick',
				'moveNextPreviousSection(' + (secNo + 1) + ')');
		$("#next")
				.html(
						messages['lbl.nextsection']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	} else if (quesNo == test.sectionlist[secNo - 1].questionList.length - 1) {
		$("#next").prop("disabled", true);
	}
	
	/**
	 * When user is on last question of last section then show submit button to user.
	 */
	if((quesNo == test.sectionlist[secNo - 1].questionList.length - 1)
			&& (secNo == test.sectionlist.length)){
		$("#next").addClass('btn-danger');
		$("#next").prop("disabled", false);
		$("#next").attr('onclick','eventOnSubmit()');
		$("#next").html('Submit');
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
	/**
	 * note and hide button
	 */
	$("#" + secNo + "noteli" + quesNo).hide();
	$("#" + secNo + "noteli" + (quesNo + 1)).show();
	$("#" + secNo + "favli" + quesNo).hide();
	$("#" + secNo + "favli" + (quesNo + 1)).show();
}

/**
 * @summary function for providing functionality for previous button on page.
 * @param secNo
 * @param quesNo
 * @returns {String}
 */
function previous(secNo, quesNo) {
	$("#" + secNo + "questionDiv" + (quesNo + 1)).hide();
	$("#" + secNo + "questionDiv" + (quesNo)).show();
	$("#previous").attr('onclick',
			'previous(' + secNo + ',' + (quesNo - 1) + ')');
	$("#next").attr('onclick', 'next(' + secNo + ',' + (quesNo) + ')');
	$("#next").removeClass('btn-danger');
	$("#questionNo").text(quesNo);
	$("#question_mark").text(
			test.sectionlist[secNo - 1].questionList[quesNo - 1].questionMark);
	$("#next")
			.html(
					messages['lbl.next']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
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
	/**
	 * note and hide button
	 */
	$("#" + secNo + "noteli" + (quesNo + 1)).hide();
	$("#" + secNo + "noteli" + quesNo).show();
	$("#" + secNo + "favli" + (quesNo + 1)).hide();
	$("#" + secNo + "favli" + quesNo).show();
}

/**
 * @summary This is used to calling ajax for submit the test.
 * @param flag.
 * @returns no.
 */
function submitTestProcess(flag) {
	$("#overlay").show();
	if (flag == "2") {
		submitTestJson.testStatus = 2;
	} else if (flag == "1") {
		submitTestJson.testStatus = 1;
	} else {
		submitTestJson.testStatus = 0;
	}
	var status = submitTest();
	$("#overlay").hide();
	if (true) {
		$("#TimeOutModal h3").html("<strong>"+messages['lbl.assessmentsubmitted']+"!</strong>");
		$("#TimeOutModal p").text(messages['msg.afterassessmentsubmit']);
		$("#TimeOutModal").fadeIn();
		$("#viewResult").attr(
				'onclick',
				"location.href='viewTestResult?id=" + test.userTestAttemptId
						+ "'");
	}
}

/**
 * @summary function for submitting the user given test
 * @returns no.
 */
function submitTest() {
	var status = false;
	if (test.testTime != null) {
		var allotedTime = convertSecond(test.testTime);
		var leftTime = convertSecond($("#time").text());
		submitTestJson.testTime = allotedTime - leftTime;
		clearInterval(interval);
		clearInterval(interval1);
	} else {
		submitTestJson.testTime = convertSecond($("#time").text());
		clearInterval(interval1);
		clearInterval(interval2);
	}
	$.ajax({
		url : "api/submitGivenTest",
		type : "POST",
		data : JSON.stringify(submitTestJson),
		contentType : "application/json",
		beforeSend : function(xhr) {
			xhr.setRequestHeader('authorization', 'Browser');
		},
		error : function() {
		},
		success : function(data) {
			status = true;
		}
	});
	return status;
}

/**
 * @summary function for saving json of user's attempted questions.
 * @param returns
 *            no.
 */
function saveJson() {
	if (test.testTime != null) {
		var allotedTime = convertSecond(test.testTime);
		var leftTime = convertSecond($("#time").text());
		submitTestJson.testTime = allotedTime - leftTime;
	} else {
		submitTestJson.testTime = convertSecond($("#time").text());
	}
	var jsonData = {
		"userTestAttemptId" : test.userTestAttemptId,
		"answerJson" : submitTestJson
	};
	$.ajax({
		url : "api/saveAnswerJson",
		type : "POST",
		data : JSON.stringify(jsonData),
		contentType : "application/json",
		error : function() {
		},
		success : function(data) {
		}
	});
}

var t = 0;
/**
 * @summary function for starting the timer.
 * @param duration
 * @param display
 * @returns no.
 */
function startTimer(duration, display) {
	var start = Date.now(), diff, minutes, seconds;
	function timer() {
		// get the number of seconds that have elapsed since
		// startTimer() was called
		diff = duration - (((Date.now() - start) / 1000) | 0);

		// does the same job as parseInt truncates the float
		minutes = (diff / 60) | 0;
		seconds = (diff % 60) | 0;

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		display.textContent = minutes + ":" + seconds;

		if (minutes == 0 && seconds == 0) {
			submitTestJson.testStatus = 1;
			submitTest();
			$('#TimeOutModal').fadeIn();
			$("#viewResult").attr(
					'onclick',
					"location.href='viewTestResult?id="
							+ test.userTestAttemptId + "'");
			clearInterval(interval);
		}

		if (diff <= 0) {
			// add one second so that the count down starts at the full duration
			// example 05:00 not 04:59
			start = Date.now() + 1000;
		}
	}
	;
	// we don't want to wait a full second before the timer starts
	timer();
	interval = setInterval(timer, 1000);
	// console.log(interval);
}

/**
 * @summary function for saving the user json after 20 sec.
 * @param duration
 * @returns no.
 */
function startTimerForSavingJson(duration) {
	var start = Date.now(), diff, minutes, seconds;
	function timer() {
		// get the number of seconds that have elapsed since
		// startTimer() was called
		diff = test.testTime != null ? (duration - (((Date.now() - start) / 1000) | 0))
				: (duration + (((Date.now() - start) / 1000) | 0));

		// does the same job as parseInt truncates the float
		minutes = (diff / 60) | 0;
		seconds = (diff % 60) | 0;

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		if (seconds % 20 == 0) {
			saveJson();
		}
		/*
		 * if(minutes==0 && seconds==0){ clearInterval(interval1); }
		 */
		if (diff <= 0) {
			// add one second so that the count down starts at the full duration
			// example 05:00 not 04:59
			start = Date.now() + 1000;
		}
	}
	;
	// we don't want to wait a full second before the timer starts
	timer();
	interval1 = setInterval(timer, 1000);
	/* console.log(interval1); */
}

/**
 * @summary Function for starting timer from 00 in case of no time bound.
 * @param duration
 * @param display
 * @returns no.
 */
function startTimerForNoTimeBound(duration, display) {
	var start = Date.now(), diff, minutes, seconds;
	function timer() {
		// get the number of seconds that have elapsed since
		// startTimer() was called
		diff = duration + (((Date.now() - start) / 1000) | 0);
		// does the same job as parseInt truncates the float
		minutes = (diff / 60) | 0;
		seconds = (diff % 60) | 0;

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		display.textContent = minutes + ":" + seconds;

		if (diff <= 0) {
			// add one second so that the count down starts at the full duration
			// example 05:00 not 04:59
			start = Date.now() + 1000;
		}
	}
	;
	// we don't want to wait a full second before the timer starts
	timer();
	interval2 = setInterval(timer, 1000);
}

/**
 * @summary Function to start timer on page load
 * @param getTime
 * @returns no.
 */
function loadTimer(getTime) {
	var hms = getTime; // your input string
	// minutes are worth 60 seconds. Hours are worth 60 minutes.
	var seconds = convertSecond(hms);
	var fiveMinutes = seconds, display = document.querySelector('#time');
	if (test.testTime != null) {
		startTimer(fiveMinutes, display);
		startTimerForSavingJson(fiveMinutes);
	} else {
		startTimerForSavingJson(fiveMinutes);
		startTimerForNoTimeBound(fiveMinutes, display);
	}
}

/**
 * @summary function for converting time to seconds.
 * @param hms
 * @returns Number as seconds.
 */
function convertSecond(hms) {
	var a = hms.split(':'); // split it at the colons
	// minutes are worth 60 seconds. Hours are worth 60 minutes.
	var seconds = 0;
	if (a.length > 2) {
		seconds = (+a[0]) * 60 * 60 + (+a[1]) * 60 + (+a[2]);
	} else {
		seconds = (+a[0]) * 60 + (+a[1]);
	}
	return Number(seconds);
}

/**
 * @summary function for pause the timer.
 * @returns no.
 */
function pause() {
	$("#pause").removeClass("fa fa-pause");
	$("#pause").addClass("fa fa-play");
	$("#testResume").show();
	if (test.testTime != null) {
		clearInterval(interval);
		clearInterval(interval1);
	} else {
		clearInterval(interval2);
		clearInterval(interval1);
	}
}

/**
 * @summary function for resume the timer.
 * @returns no.
 */
function resume() {
	$("#pause").removeClass("fa fa-play");
	$("#pause").addClass("fa fa-pause");
	$("#testResume").hide();
	loadTimer($("#time").text());
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
	$("#next").removeClass('btn-danger');
	$("#next")
			.html(
					messages['lbl.next']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	if ((quesNo == test.sectionlist[secNo - 1].questionList.length)
			&& (secNo < test.sectionlist.length)) {
		$("#next").attr('onclick',
				'moveNextPreviousSection(' + (secNo + 1) + ')');
		$("#next")
				.html(
						messages['lbl.nextsection']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	} else if (quesNo == test.sectionlist[secNo - 1].questionList.length) {
		$("#next").prop("disabled", true);
	} else {
		$("#next").prop("disabled", false);
	}
	
	/**
	 * When user is on last question of last section then show submit button to user.
	 */
	if((quesNo == test.sectionlist[secNo - 1].questionList.length)
			&& (secNo == test.sectionlist.length)){
		$("#next").addClass('btn-danger');
		$("#next").prop("disabled", false);
		$("#next").attr('onclick','eventOnSubmit()');
		$("#next").html('Submit');
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
	/**
	 * note and hide button
	 */
	$(".noteli").hide();
	$(".favli").hide();
	$("#" + secNo + "noteli" + quesNo).show();
	$("#" + secNo + "favli" + quesNo).show();
}

/**
 * @summary function for providing functionality for showing the next question
 *          list on next button.
 * @param secNo
 * @param listNo
 * @returns no.
 */
var showNextQuestonList = function(secNo, listNo) {
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
var showPreviousQuestonList = function(secNo, listNo) {
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
 * @param returns
 *            no.
 */
function moveNextPreviousSection(secNo) {
	$("#sectiontitle").text(test.sectionlist[secNo - 1].sectionName);
	$("#selectedSectionForCurrentQues").text("( "+messages['lbl.section']+" " + secNo + " )");
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
	$("#next").removeClass('btn-danger');
	$("#next")
			.html(
					messages['lbl.next']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
	if ((secNo < test.sectionlist.length)
			&& (test.sectionlist[secNo - 1].questionList.length < 2)) {
		$("#next").attr('onclick',
				'moveNextPreviousSection(' + (secNo + 1) + ')');
		$("#next")
				.html(
						messages['lbl.nextsection']+'&nbsp;&nbsp;&nbsp;<font size="4px"><i class="fa fa-angle-double-right"></i></font>');
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
	
	/**
	 * When user is on last question of last section then show submit button to user.
	 */
	if((test.sectionlist[secNo - 1].questionList.length == 1)
			&& (secNo == test.sectionlist.length)){
		$("#next").addClass('btn-danger');
		$("#next").prop("disabled", false);
		$("#next").attr('onclick','eventOnSubmit()');
		$("#next").html('Submit');
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

	/**
	 * note and hide button
	 */
	$(".noteli").hide();
	$(".favli").hide();
	$("#" + secNo + "noteli1").show();
	$("#" + secNo + "favli1").show();

	$("#sectionQuesList" + secNo).show();
}

/**
 * @summary function for open add notes modal.
 * @param q_id
 * @returns no.
 */
var opennotes = function(q_id) {
	$("#notes").val('');
	$('#noteId').val('');
	getpreviousnote(q_id);
	$("#notes").css({
		"border-color" : ""
	});
	$("#notes").next('span').remove();
	$("#q_id").val(q_id);
	$("#notesModal").modal('show');
}

/**
 * @summary function for save question note.
 * @returns no.
 */
var savenotes = function() {
	var data = JSON.stringify({
		'userTestAttemptId' : submitTestJson.userTestAttemptId,
		'questionId' : $("#q_id").val(),
		'userId' : Number(userId),
		'notes' : $("#notes").val(),
		'noteId' : $("#noteId").val()
	});
	if (validateNotes()) {
		$.ajax({
			url : "api/savenotestoquestion",
			beforeSend : function(xhr) { // @summary appending data in header
				// before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', "Browser");
			},
			type : 'POST',
			dataType : 'json',
			contentType : "application/json",
			data : data,
			error : function() {
				alert("error");
			},
			success : function(response) {
				// console.log(response);
				if (response.status == 200) {
					$("#notebtnclose").trigger('click');
					$("#successdialog p").text(messages['msg.aftersavednotesuccess']);
					$('#successdialog').modal('show');
				} else {
					$("#notebtnclose").trigger('click');
					$("#errordialog p").text(messages['msg.somethingwentwrong']);
					$("#errordialog").modal('show');
				}
			}
		});
	}

}

/**
 * @summary function for validate question note.
 * @returns no.
 */
var validateNotes = function() {
	if ($("#notes").val() == "") {
		$('#notes').css({
			"border-color" : "red"
		});
		$('#notes').after("<span class='text-red'>"+messages['msg.empty']+"</span>");
		return false;
	}

	if ($("#notes").val().length > 2000) {
		$('#notes').css({
			"border-color" : "red"
		});
		$('#notes')
				.after(
						"<span class='text-red'>"+messages['msg.maxcharacterlength'].replace('#maxlength','2000')+"</span>");
		return false;
	}

	return true;
}

/**
 * @summary function for get note text by questionId,userId,attemptId
 * @param q_id
 * @returns no.
 */
var getpreviousnote = function(q_id) {

	$.ajax({
		url : "api/getnoteofquestion/" + q_id + "/" + Number(userId) + "/"
				+ submitTestJson.userTestAttemptId,
		beforeSend : function(xhr) { // @summary appending data in header
			// before sending request.
			xhr.setRequestHeader('authorization', 'Browser');
			xhr.setRequestHeader('timestamp', "Browser");
		},
		type : 'GET',
		dataType : 'json',
		contentType : "application/json",
		async : false,
		error : function() {
			alert("error");
		},
		success : function(response) {
			// console.log(response);
			if (response.note != "") {
				$('#notes').val(response.note);
				$('#noteId').val(response.noteId);
			}
		}
	});

}

/**
 * @summary function for set/reset favoriate to question by user.
 * @param qid
 * @param flag
 * @param i
 * @param favid
 * @param secNo
 * @returns no.
 */
var setresetfavoriate = function(qid, flag, i, favid, secNo) {
	var data = JSON.stringify({
		'questionId' : qid,
		'userId' : Number(userId),
		'favorites' : flag,
		'favoriateId' : favid
	});
	$.ajax({
		url : "api/setresetfavoriatequestion",
		beforeSend : function(xhr) { // @summary appending data in header
			// before sending request.
			xhr.setRequestHeader('authorization', 'Browser');
			xhr.setRequestHeader('timestamp', "Browser");
		},
		type : 'POST',
		dataType : 'json',
		contentType : "application/json",
		data : data,
		error : function() {
			alert("error");
		},
		success : function(response) {
			// console.log(response);
			if (response.status == 200) {
				if (response.flag == 1) {
					$("#" + secNo + "favicon" + i).removeClass(
							"fa fa-heart-o color-mainblue");
					$("#" + secNo + "favicon" + i).addClass(
							"fa fa-heart color-mainblue");
					$("#" + secNo + "favli" + i).attr("onclick", "");
					$("#" + secNo + "favli" + i).attr(
							"onclick",
							"setresetfavoriate(" + qid + "," + 0 + "," + i
									+ "," + favid + "," + secNo + ")");
				} else {
					$("#" + secNo + "favicon" + i).removeClass(
							"fa fa-heart color-mainblue");
					$("#" + secNo + "favicon" + i).addClass(
							"fa fa-heart-o color-mainblue");
					$("#" + secNo + "favli" + i).attr("onclick", "");
					$("#" + secNo + "favli" + i).attr(
							"onclick",
							"setresetfavoriate(" + qid + "," + 1 + "," + i
									+ "," + favid + "," + secNo + ")");
				}

			} else {
				$("#errordialog p").text(messages['msg.somethingwentwrong']);
				$("#errordialog").modal('show');
			}
		}
	});

}

/**
 * @summary This is used findout the position in json array of a particular
 *          section based on section id.
 * 
 * @param sectionId
 * @return position.
 */
var findSectionIdInList = function(sectionId) {
	var position = 0;
	for (var s = 0; s < submitTestJson.sectionlist.length; s++) {
		if (submitTestJson.sectionlist[s].sectionId == sectionId) {
			position = s;
			break;
		}
	}
	return position;
}

var eventOnSubmit = function(){
	$("#testSubmit").modal('show');
}

/**
 * @summary This is used updating the view end date for course content.
 */
var updateattemptedtestidincontentactivity = function(attemptedTestId){
	if(contentActivityId!=null && contentActivityId>0){
	var viewContentInfo = JSON.stringify({
		'contentActivityId' : parseInt($.trim(contentActivityId)),
		'attemptedTestId' : parseInt($.trim(attemptedTestId))
	    });
		$.ajax({
			url:'api/updateattemptedtestidincontentactivity',
			beforeSend : function(xhr) { // @summary appending data in header before sending request.
				xhr.setRequestHeader('authorization', 'Browser');
				xhr.setRequestHeader('timestamp', "Browser");
			},
			type:'POST',
			dataType : 'json',
			contentType : "application/json",
			data:viewContentInfo,
			error:function(){
			},
			success: function(response){	
			}
			});
	}
	}