/**
 * @summary This is used for common operations for created question in test.
 * @author ankur
 * @date 03 Sep 2016
 */

/**
 * @summary This is used for redirect on question page based on question type.
 * @param sectionId
 * @param questionId
 * @returns no.
 */
function editQuestionData(sectionId, questionId) {
	var position = findSectionIdInList(sectionId);
	for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
		if (questionSectionList[position].questionList[q].questionId == questionId) {
			switch (questionSectionList[position].questionList[q].questionType) {
			case MULTIPLE_CHOICE_TYPE:
				addNextQuestion(questionSectionList[position].questionList[q].questionNo);
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.multiplechoice']);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillQuestionData(position, questionId);
				break;
			case SINGLE_CHOICE_TYPE:
				addNextQuestion(questionSectionList[position].questionList[q].questionNo);
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.singlechoice']);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillQuestionData(position, questionId);
				break;
			case SORT_LIST_TYPE:
				addSortListTypeQuestion();
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.sortlist']);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillSortListTypeQuestionData(position, questionId);
				break;
			case CHOICE_MATRIX_TYPE:
				addChoiceMatrixTypeQuestion();
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.choicematrix']);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillchoiceMatrixTypeQuestionData(position, questionId);
				break;
			case CLASSIFICATION_TYPE:
				addClassificationTypeQuestion();
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.classification']);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillClassificationTypeQuestionData(position, questionId);
				break;
			case MATCH_LIST:
				addMatchListTypeQuestion();
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#question-type-header").text(messages['lbl.matchlist']);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillMatchListTypeQuestionData(position, questionId);
				break;
			}
		}
	}
}

/**
 * @summary This is used for when any click event is fired on element by user
 *          which has sectionDelete class.
 * 
 * @return no.
 */
$(document).on('click', '.sectionDelete', function() {
	$("#clearquestionAlert p").text(messages['msg.deletesection']);
	currentObject = this;
	$("#dId").attr('onclick', 'deleteSection()');
	$("#clearquestionAlert").modal('show');
});

/**
 * @summary This is used for removing the delete section from the list.
 * @returns no.
 */
function deleteSection() {
	var sectiondivId = $(currentObject).attr("id").split('sectionDelete');
	var sectionId = sectiondivId[1];
	var position = findSectionIdInList(sectionId);
	questionSectionList[position].isDelete = 1;
	$(currentObject).closest("div.section").remove();
	$("#clearquestionAlert").modal('hide');
	var length = $("#totalTestSections .section").length;
	var isUpdate = 0;
	for (var sec = 0; sec < questionSectionList.length; sec++) {
		if (questionSectionList[sec].isUpdate == 1) {
			isUpdate = 1;
			break;
		}
	}
	if (length != null && length == 0 && (isUpdate == 0)) {
		$("#submitAllData").prop('disabled', true);
	}
}

/**
 * @summary Function is used for find out that section is already is created or
 *          not.
 * 
 * @returns {Boolean}
 */
function sectionIsCreated() {
	var status = false;
	if (questionSectionList.length > 0) {
		status = true;
	}
	return status;
}

/**
 * @summary Function is used for removing a particular question from json object
 *          based on section id and question id.
 * 
 * @param sectionId
 * @param questionId
 * @returns no.
 */
function deleteQuestionData(sectionId, questionId) {
	$("#clearquestionAlert").modal('hide');
	var position = findSectionIdInList(sectionId);
	for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
		if (questionSectionList[position].questionList[q].questionId == questionId) {
			questionSectionList[position].sectionScore = questionSectionList[position].sectionScore
					- questionSectionList[position].questionList[q].questionMark;
			questionSectionList[position].questionList.splice(q, 1);
			for (var j = q; j < questionSectionList[position].questionList.length; j++) {
				questionSectionList[position].questionList[j].questionNo = j + 1;
			}
			$(currentObject).closest("div.sectionQuestion").remove();
			$("#totalSectionQuestion" + sectionId).text(
					questionSectionList[position].questionList.length);
			$("#totalSectionMarks" + sectionId).text(
					questionSectionList[position].sectionScore);
			if (questionSectionList[position].questionList.length == 0) {
				$("#questionDivOfSection" + sectionId).next('div').remove();
			}
			totalQuestions--;
			break;
		}
	}
}

/**
 * @summary This is used for open the pop up for submit the questions.
 * @returns no.
 */
function submitAllData() {
	if (jsonValidation()) {
		$("#clearquestionAlert p").text(messages['msg.submitdetail']);
		$("#dId").attr('onclick', 'submitSectionDetails()');
		$("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary This is used of submit the all sections details using ajax.
 * @returns no.
 */
function submitSectionDetails() {
	var data = JSON.stringify(questionSectionList);
	$("#clearquestionAlert").modal('hide');
	$("#overlay").show();
	console.log("data " + data);
	$.ajax({
		type : 'POST',
		url : 'saveQuestions',
		data : 'sectionData=' + encodeURIComponent(data),
		success : function(status) {
			if (status) {
				// disable warning
				$(window).off('beforeunload');
				if (courseId != null && courseSectionId != null) {
					var url = 'testreview?testId=' + testId + '&courseId='
							+ courseId + '&sectionId=' + courseSectionId;
					window.location.href = contentId != null ? url
							+ '&contentId=' + contentId : url;
				} else {
					window.location.href = 'testreview?testId=' + testId;
				}
			} else {
				$("#overlay").hide();
				$('#testAlert').modal('show');
				$("#testAlert p").text(messages['msg.somethingwentwrong']);
			}
		}
	});
}

/**
 * @summary this function is used for converting 3d array data into 2d array
 *          data of columns of data tables.
 * @param data
 * @returns no.
 */
function planify(data) {
	for (var i = 0; i < data.columns.length; i++) {
		column = data.columns[i];
		column.searchRegex = column.search.regex;
		column.searchValue = column.search.value;
		delete (column.search);
	}
}

/**
 * @summary This is used for getting test list using ajax from where user can
 *          choose any test.
 * @param sectionId
 * @return no.
 */
var questionBankTableVar;
var selectedQuestionFromQuestionBank = [];
function getQuestionBank(sectionId) {
	$("#overlay").show();
	var position = findSectionIdInList(sectionId);
	var alreadyImportedQuestion = [];
	selectedQuestionFromQuestionBank = [];
	for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
		/**
		 * push ids of question if question is already save in test.
		 */
		if (questionSectionList[position].questionList[q].isNew == 0) {
			alreadyImportedQuestion
					.push(parseInt(questionSectionList[position].questionList[q].questionId));
		}
	}
	$('#questionBankTable').DataTable().destroy();
	$("#questionBankBody").empty();
	/**
	 * calling ajax function for getting test list dynamically
	 */
	questionBankTableVar = $('#questionBankTable')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						'aaSorting' : [],
						"pagingType" : "full_numbers",
						"pages" : 10,
						"language" : datatablelanguagejson,
						"ajax" : $.fn.dataTable.pipeline({
							"url" : "getQuestionListData?action=questionbank",
							"type" : "GET",
							"data" : function(data) {
								planify(data);
							}
						}),
						"columns" : [ {
							"data" : "createdDate"
						}, {
							"data" : "questionName"
						}, {
							"data" : "questionTypeName"
						} ],
						"columnDefs" : [
								{
									// The `data` parameter refers to the data
									// for the cell (defined by the
									// `data` option, which defaults to the
									// column being worked with, in
									// this case `data: 0`.
									"render" : function(data, type, row) {
										var argument = "'" + row.questionId
												+ "'";
										/**
										 * checkbox input
										 */
										var inputCheckbox = '';
										/**
										 * check question is already imported.
										 */
										if (contains(alreadyImportedQuestion,
												row.questionId)) {
											inputCheckbox = '<input type="checkbox" name="selectedQuestionIds" class="selectedQuestionIds" value="'
													+ row.questionId
													+ '" disabled>';
										} else if (contains(
												selectedQuestionFromQuestionBank,
												row.questionId)) {
											inputCheckbox = '<input type="checkbox" name="selectedQuestionIds" class="selectedQuestionIds" value="'
													+ row.questionId
													+ '" checked>';
										} else {
											inputCheckbox = '<input type="checkbox" name="selectedQuestionIds" class="selectedQuestionIds" value="'
													+ row.questionId + '">';
										}
										return inputCheckbox;
									},
									"targets" : 3,
									"orderable" : false
								}, {
									// The `data` parameter refers to the data
									// for the cell (defined by the
									// `data` option, which defaults to the
									// column being worked with, in
									// this case `data: 0`.
									"render" : function(data, type, row) {
										return data == null ? "" : data;
									},
									"targets" : 2
								}

						],
						"drawCallback" : function() {
							$('input[type="checkbox"].selectedQuestionIds')
									.iCheck(
											{
												checkboxClass : 'icheckbox_square-green'
											});
						},
						"initComplete" : function() {
							selectedQuestionFromQuestionBank = [];
							$('input[name=selectedQuestionIds]').prop(
									'checked', false);
							/**
							 * Save action method on import button.
							 */
							$("#importQuestionButtonInTable").attr('onclick',
									'importQuestion(' + sectionId + ')');
							$("#importQuestionButtonInTable").prop('disabled',
									true);
							$("#overlay").hide();
							$("#questionBankPopup").modal('show');
						}
					});
}

/**
 * listener on selectedQuestionIds class for making enable and disable the import question button.
 */
$(document).on(
		'ifChecked',
		'.selectedQuestionIds',
		function() {
			var index = getIndex(selectedQuestionFromQuestionBank, parseInt($(
					this).val()));
			if (index == null) {
				selectedQuestionFromQuestionBank.push(parseInt($(this).val()));
			}
			var boxes = $('.selectedQuestionIds');
			$('#importQuestionButtonInTable').prop('disabled',
					!boxes.filter(':checked').length);
		}).trigger('change');

/**
 * listener on selectedQuestionIds class for making enable and disable the import question button.
 */
$(document).on(
		'ifUnchecked',
		'.selectedQuestionIds',
		function() {
			/**
			 * getting the index value of this unchecked email.
			 */
			var index = getIndex(selectedQuestionFromQuestionBank, parseInt($(
					this).val()));
			/**
			 * when index is not null.
			 */
			if (index != null) {
				/**
				 * removing this email from email list array.
				 */
				selectedQuestionFromQuestionBank.splice(index, 1);
			}
			var boxes = $('.selectedQuestionIds');
			$('#importQuestionButtonInTable').prop('disabled',
					selectedQuestionFromQuestionBank.length == 0);
		}).trigger('change');

/**
 * @summary this is used for getting item index from array;
 * @param array
 * @param item
 * @returns index
 */
function getIndex(array, item) {
	var index;
	for (var i = 0; i < array.length; i++) {
		if (array[i] === item) {
			index = i;
			break;
		}
	}
	return index;
}

/**
 * @summary This function is used reduced the ajax calling on server if data is already in cache.
 * @param opts
 */
$.fn.dataTable.pipeline = function(opts) {
	// Configuration options
	var conf = $.extend({
		pages : 10, // number of pages to cache
		url : '', // script url
		data : null, // function or object with parameters to send to the server
		// matching how `ajax.data` works in DataTables
		method : 'GET' // Ajax HTTP method
	}, opts);

	// Private variables for storing the cache
	var cacheLower = -1;
	var cacheUpper = null;
	var cacheLastRequest = null;
	var cacheLastJson = null;

	return function(request, drawCallback, settings) {
		var ajax = false;
		var requestStart = request.start;
		var drawStart = request.start;
		var requestLength = request.length;
		var requestEnd = requestStart + requestLength;

		if (settings.clearCache) {
			// API requested that the cache be cleared
			ajax = true;
			settings.clearCache = false;
		} else if (cacheLower < 0 || requestStart < cacheLower
				|| requestEnd > cacheUpper) {
			// outside cached data - need to make a request
			ajax = true;
		} else if (JSON.stringify(request.order) !== JSON
				.stringify(cacheLastRequest.order)
				|| JSON.stringify(request.columns) !== JSON
						.stringify(cacheLastRequest.columns)
				|| JSON.stringify(request.search) !== JSON
						.stringify(cacheLastRequest.search)) {
			// properties changed (ordering, columns, searching)
			ajax = true;
		}

		// Store the request for checking next time around
		cacheLastRequest = $.extend(true, {}, request);

		if (ajax) {
			// Need data from the server
			if (requestStart < cacheLower) {
				requestStart = requestStart
						- (requestLength * (conf.pages - 1));

				if (requestStart < 0) {
					requestStart = 0;
				}
			}

			cacheLower = requestStart;
			cacheUpper = requestStart + (requestLength * conf.pages);

			request.start = requestStart;
			request.length = requestLength * conf.pages;

			// Provide the same `data` options as DataTables.
			if ($.isFunction(conf.data)) {
				// As a function it is executed with the data object as an arg
				// for manipulation. If an object is returned, it is used as the
				// data object to submit
				var d = conf.data(request);
				if (d) {
					$.extend(request, d);
				}
			} else if ($.isPlainObject(conf.data)) {
				// As an object, the data given extends the default
				$.extend(request, conf.data);
			}

			settings.jqXHR = $.ajax({
				"type" : conf.method,
				"url" : conf.url,
				"data" : request,
				"dataType" : "json",
				"cache" : false,
				"success" : function(json) {
					cacheLastJson = $.extend(true, {}, json);

					if (cacheLower != drawStart) {
						json.data.splice(0, drawStart - cacheLower);
					}
					if (requestLength >= -1) {
						json.data.splice(requestLength, json.data.length);
					}

					drawCallback(json);
				}
			});
		} else {
			json = $.extend(true, {}, cacheLastJson);
			json.draw = request.draw; // Update the echo for each response
			json.data.splice(0, requestStart - cacheLower);
			json.data.splice(requestLength, json.data.length);

			drawCallback(json);
		}
	}
};

// Register an API method that will empty the pipelined data, forcing an Ajax
// fetch on the next draw (i.e. `table.clearPipeline().draw()`)
$.fn.dataTable.Api.register('clearPipeline()', function() {
	return this.iterator('table', function(settings) {
		settings.clearCache = true;
	});
});

/**
 * @summary This is used for extracting test details from test list json and
 *          adding in table.
 * @param testData
 *            This is only parameter which has details about all tests.
 * @return no.
 */
/*
 * function extractQuestionBank(questionBank, sectionId) { var position =
 * findSectionIdInList(sectionId); var alreadyImportedQuestion = [];
 *//**
 * This will take all imported ids of question in a particular section and
 * these ids would be used for removing the duplicacy of same question in
 * same section.
 */
/*
 * for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
 *//**
 * push ids of question if question is already save in test.
 */
/*
 * if (questionSectionList[position].questionList[q].isNew == 0) {
 * alreadyImportedQuestion
 * .push(parseInt(questionSectionList[position].questionList[q].questionId)); } }
 * 
 * $('#questionBankTable').DataTable().destroy();
 *//**
 * This is a empty object which would be used adding html data as row inside
 * table body.
 */
/*
 * var row = '';
 *//**
 * before adding any data in table body, make it empty for no duplicate data
 * if operation performs again.
 */
/*
 * $("#questionBankBody").empty();
 *//**
 * iterate on test list data for extract test details for a particular test.
 */
/*
 * for (var i = 0; i < questionBank.length; i++) {
 *//**
 * making an argument what would be passed inside a function for selecting a
 * test.
 */
/*
 * var argument = "'" + questionBank[i].questionId + "'";
 *//**
 * checkbox input
 */
/*
 * var inputCheckbox = '';
 *//**
 * check question is already imported.
 */
/*
 * if (contains(alreadyImportedQuestion, questionBank[i].questionId)) {
 * inputCheckbox = '<input type="checkbox" name="selectedQuestionIds"
 * class="selectedQuestionIds" value="' + questionBank[i].questionId + '"
 * disabled>'; } else { inputCheckbox = '<input type="checkbox"
 * name="selectedQuestionIds" class="selectedQuestionIds" value="' +
 * questionBank[i].questionId + '">'; }
 *//**
 * assign a html element for adding a row in table body.
 */
/*
 * row = row + '<tr><td>' + questionBank[i].createdDate + '</td>' + '<td class="imgset">' +
 * questionBank[i].questionName + '</td>' + '<td>' +
 * questionBank[i].questionTypeName + '</td>' + '<td>' + inputCheckbox + '</td></tr>'; }
 *//**
 * append a table row inside table body.
 */
/*
 * $("#questionBankBody").append(row);
 *//**
 * using icheck for checkbox.
 */
/*
 * $('input[type="checkbox"].selectedQuestionIds').iCheck({ checkboxClass :
 * 'icheckbox_square-green' }); $('.imgset img').css({ 'display' : 'block',
 * 'width' : '100%', 'max-width' : '100%', 'height' : 'auto' });
 *//**
 * initialize a table into data table
 */
/*
 * questionBankTableVar = $("#questionBankTable").dataTable({ 'columnDefs' : [ {
 * 'orderable' : false, 'targets' : [ 3 ] } ], // hide sort icon on action
 * 'aaSorting' : [], 'language': datatablelanguagejson, "destroy" : true
 * 
 * });
 *//**
 * clear the selected checkbox.
 */
/*
 * $('input[name=selectedQuestionIds]').prop('checked', false);
 *//**
 * Save action method on import button.
 */
/*
 * $("#importQuestionButtonInTable").attr('onclick', 'importQuestion(' +
 * sectionId + ')'); $("#importQuestionButtonInTable").prop('disabled', true);
 *//**
 * hide the loader
 */
/*
 * $("#overlay").hide();
 *//**
 * after loading all data into table , show pop up for choosing a test.
 */
/*
 * $("#questionBankPopup").modal('show'); }
 */

/**
 * @summary function for importing questions from question bank.
 * @param sectionId
 * @returns no.
 */
var importQuestion = function(sectionId) {
	var form = new FormData();
	var everyQuesMark = equalMarkTest == 1 ? everyQuestionMark : 0;
	var position = findSectionIdInList(sectionId);
	/**
	 * This would be call when user wants import questions from Question Bank.
	 */
	/*var checkValues = [];
	$(":checkbox:checked", questionBankTableVar.rows().nodes()).each(
			function(i) {
				checkValues[i] = $(this).val();
			});*/
	form.append("selectedQuestionIds", selectedQuestionFromQuestionBank);
	form.append("totalQuestion",
			questionSectionList[position].questionList.length);
	form.append("everyQuestionMark", everyQuesMark);
	form.append("negMark", negMark);
	form.append("equalMarkTest", equalMarkTest);
	$("#questionBankPopup").modal('hide');
	$("#overlay").show();
	$
			.ajax({
				type : "POST",
				url : "importQuestion",
				data : form,
				processData : false,
				contentType : false,
				error : function() {
					$("#overlay").hide();
					$("body").css("overflow", "hidden");
					$("#testAlert p").text(messages['msg.somethingwentwrong']);
					$('#testAlert').modal('show');
				},
				success : function(questionJsonList) {
					if (questionJsonList.length > 0) {
						if (questionSectionList[position].questionList.length == 0) {
							appendSectionDetailsDiv(sectionId);
						}
						for (var q = 0; q < questionJsonList.length; q++) {
							questionSectionList[position].questionList
									.push(questionJsonList[q]);
							questionSectionList[position].sectionScore = questionSectionList[position].sectionScore
									+ questionJsonList[q].questionMark;
							var quesData = [];
							quesData.push(questionJsonList[q].questionId);
							quesData.push(questionJsonList[q].questionName);
							quesData.push(questionJsonList[q].questionMark);
							appendQuestionInsection(quesData, sectionId);
						}
						$("#totalSectionQuestion" + sectionId)
								.text(
										questionSectionList[position].questionList.length);
						$("#totalSectionMarks" + sectionId).text(
								questionSectionList[position].sectionScore);
						$("#overlay").hide();
					} else {
						$("#overlay").hide();
						$("body").css("overflow", "hidden");
						$("#testAlert p").text(
								messages['msg.somethingwentwrong']);
						$('#testAlert').modal('show');
					}
				}
			});
}

/**
 * @summary this function is used for validate the all required field for
 *          question.
 * @returns {Boolean}
 */
var jsonValidation = function() {
	var status = true;
	try {
		/**
		 * traversing on section.
		 */
		for (var sec = 0; sec < questionSectionList.length; sec++) {
			/**
			 * traversing on question of every section.
			 */
			for (var q = 0; q < questionSectionList[sec].questionList.length; q++) {
				if (questionSectionList[sec].questionList[q].questionMark < 1
						|| questionSectionList[sec].questionList[q].questionMark == '') {
					throw new expectationFailedException(
							"Question Score is required for Question Number "
									+ (q + 1) + " of Section " + (sec + 1));
				}
			}
		}
	} catch (err) {
		if (err instanceof expectationFailedException) {
			status = false;
			$("#testAlert p").text(err.message);
			$('#testAlert').modal('show');
		}
	}
	return status;
}

/**
 * @summary custom exception for json validation.
 * @param message
 */
function expectationFailedException(message) {
	this.message = message;
}

/**
 * @summary This is used for searching an element in array.
 * @param array
 * @param item
 * @returns {Boolean}
 */
function contains(array, item) {
	var status = false;
	for (var i = 0; i < array.length; i++) {
		if (array[i] === item) {
			status = true;
			break;
		}
	}
	return status;
}

/**
 * @summary show add question page.
 * @param sectionId
 * @returns no.
 */
var showCreateQuestionPage = function(sectionId) {

	$("#sectionIdForAddedQues").val(sectionId);

	var selectedQuestionType = Number($.trim($("#selectedQuestionType").val()));
	/**
	 * make cases on question type.
	 */
	switch (selectedQuestionType) {
	/**
	 * if question type is multiple choice.
	 */
	case MULTIPLE_CHOICE_TYPE:
		$("#question-type-header").text(messages['lbl.multiplechoice']);
		addNextQuestion(totalQuestions + 1);
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveQuestionInJson()');
		break;
	/**
	 * if question type is single choice type.
	 */
	case SINGLE_CHOICE_TYPE:
		$("#question-type-header").text(messages['lbl.singlechoice']);
		addNextQuestion(totalQuestions + 1);
		$("#ansTypeButton").prop('checked', false);
		changeAnswerType();
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveQuestionInJson()');
		break;
	/**
	 * if question type is sort list type.
	 */
	case SORT_LIST_TYPE:
		$("#question-type-header").text(messages['lbl.sortlist']);
		addSortListTypeQuestion();
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion")
				.attr('onclick', 'saveSortListTypeQuestionInJson()');
		break;
	/**
	 * if question type is choice matrix type.
	 */
	case CHOICE_MATRIX_TYPE:
		$("#question-type-header").text(messages['lbl.choicematrix']);
		addChoiceMatrixTypeQuestion();
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick',
				'savechoiceMatrixTypeQuestionInJson()');
		break;
	/**
	 * if question type is classification type.
	 */
	case CLASSIFICATION_TYPE:
		$("#question-type-header").text(messages['lbl.classification']);
		addClassificationTypeQuestion();
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick',
				'saveClassificationTypeQuestionInJson()');
		break;
	/**
	 * if question type is match list type.
	 */
	case MATCH_LIST:
		$("#question-type-header").text(messages['lbl.matchlist']);
		addMatchListTypeQuestion();
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick',
				'saveMatchListTypeQuestionInJson()');
		break;
	default:
		$("#question-type-header").text(messages['lbl.multiplechoice']);
		addNextQuestion(totalQuestions + 1);
		$("#submitQuestion").text(messages['lbl.save']);
		$("#submitQuestion").attr('onclick', 'saveQuestionInJson()');
	}
	$(window).scrollTop(0);
	$("#createsectiontab").hide();
	$("#questionPageDiv").show();
}

/**
 * @summary back on section page from creating new question page.
 * @returns no.
 */
var backonSectionPage = function() {
	$("#createsectiontab").show();
	$("#questionPageDiv").hide();
}

/**
 * @summary redirect on test info tab from question tab.
 * @returns no.
 */
var testInfoTabFromQuestionTab = function() {
	if (courseId != null && courseSectionId != null) {
		var url = 'addEditTest?testId=' + testId + '&courseId=' + courseId
				+ '&sectionId=' + courseSectionId;
		window.location.href = contentId != null ? url + '&contentId='
				+ contentId : url;
	} else {
		window.location.href = 'addEditTest?testId=' + testId;
	}
}

/**
 * @summary This is used for converting the html text into plain text.
 * 
 * @param strHtmlText
 * @returns {String}
 */
function ConvertHtmlToPlainTest(strHtmlText) {
	if (strHtmlText.match("img")) {
		strHtmlText;
	} else if (strHtmlText != undefined) {
		strHtmlText = strHtmlText.replace(/<(?:.|\n)*?>/gm, '');
		var temp = document.createElement("div");
		temp.innerHTML = strHtmlText;
		strHtmlText = temp.textContent || temp.innerText || "";
	} else {
		strHtmlText = "";
	}
	return strHtmlText.trim();
}

/**
 * @summary This is used for showing popup form for fill up section details.
 * 
 * @returns no.
 */
function sectionCreatePopup() {
	$("#sectionHeading").text(messages['lbl.createnewsection']);
	$("#newSectionName").css({
		"border-color" : ""
	});
	$("#newSectionNameError1").fadeOut();
	$("#newSectionNameError2").fadeOut();
	$("#newSectionNameError3").fadeOut();
	$("#saveSectionButton").text(messages['lbl.save']);
	$("#newSectionName").val("");
	$("#saveSectionButton").attr('onclick', 'saveSection()');
	$("#sectionPop").modal('show');

}

/**
 * @summary This is used for showing popup for updating section name.
 * @param sectionId
 * @returns no.
 */
function updateSectionName(sectionId) {
	$("#sectionHeading").text(messages['lbl.editsectionname']);
	$("#newSectionName").css({
		"border-color" : ""
	});
	$("#newSectionNameError1").fadeOut();
	$("#newSectionNameError2").fadeOut();
	$("#newSectionNameError3").fadeOut();
	$("#saveSectionButton").text(messages['lbl.update']);
	$("#newSectionName").val($("#sectionNameText" + sectionId).text());
	$("#saveSectionButton").attr('onclick', 'updateSection(' + sectionId + ')');
	$("#sectionPop").modal('show');
}

/**
 * @summary function is used for changing the name of section in json.
 * @param sectionId
 * @returns no.
 */
var updateSection = function(sectionId) {
	var sectionName = $("#newSectionName").val().trim();
	if (sectionNameValidation()) {
		var position = findSectionIdInList(sectionId);
		questionSectionList[position].sectionName = sectionName;
		$("#sectionNameText" + sectionId).text(sectionName);
		$("#sectionPop").modal('hide');
	}
}

/**
 * @summary This is used for checking that section name provided by user is
 *          valid or not.
 * 
 * @return {boolean type}true/false This returns true if section name is valid
 *         after performing all condition like length,alphanumeric value e.t.c.
 *         otherwise false;
 */
function sectionNameValidation() {

	$("#newSectionName").css({
		"border-color" : ""
	});
	$("#newSectionName").next('span').remove();

	/**
	 * regrex condtion for aluphanumeric value.
	 */
	var regrex = /^[a-zA-Z0-9 ]+$/;
	/**
	 * getting section's name
	 */
	var sectionName = $("#newSectionName").val().trim();
	/**
	 * checking section's name empty.
	 */
	if (sectionName.length == 0) {
		$("#newSectionName").css("border-color", "#c95b5b");
		$("#newSectionNameError1").fadeIn();
		document.sectionForm.newSectionName.focus();
		return false;
	}

	if (sectionName.length > 50) {
		$("#newSectionName").css("border-color", "#c95b5b");
		$("#newSectionNameError3").fadeIn();
		document.sectionForm.newSectionName.focus();
		return false;
	}

	/**
	 * checking section's name is alphanumeric.
	 */
	if (!regrex.test(sectionName)) {
		$("#newSectionName").css("border-color", "#c95b5b");
		$("#newSectionNameError2").fadeIn();
		document.sectionForm.newSectionName.focus();
		return false;
	}

	return (true);
}

/**
 * @summary function is used for saving the new creating section in json.
 * @returns no.
 */
function saveSection() {
	var sectionName = $("#newSectionName").val().trim();
	if (sectionNameValidation()) {
		createNewSection(sectionName);
	}
}

/**
 * @summary This is used for fade out showing validation's error on front end on
 *          key up or key down by user.
 * 
 * @returns no
 */
function sectionNameKey() {
	var sectionName = $("#newSectionName").val().trim();
	if (sectionName.length > 0) {
		$("#newSectionName").css("border-color", "");
		$("#newSectionNameError1").fadeOut();
		$("#newSectionNameError2").fadeOut();
		$("#newSectionNameError3").fadeOut();
	}
}

/**
 * @summary This function is used for creating the new section in test.
 * @param sectionName
 * @returns no.
 */
var createNewSection = function(sectionName) {
	var sectionOrder = questionSectionList.length;
	sectionOrder++;
	var sectionId = parseInt(testId + '' + sectionOrder);
	var section = {
		"testId" : testId,
		"sectionName" : sectionName,
		"sectionId" : sectionId,
		"sectionSortOrder" : sectionOrder,
		"isUpdate" : 0, // used in identify for new created section on server
		// side. if section is already created then isUpdate
		// would be 1.
		"isDelete" : 0, // used in identify for deleted section on server side.
		// if section is deleted then isDelete would be 1.
		"sectionScore" : 0,
		"questionList" : [],
	};
	questionSectionList.push(section);
	appendSectionDivHtml(sectionId, sectionName);
	$("#sectionPop").modal('hide');
}

/**
 * @summary This is used appending section html in page.
 * @param sectionId
 * @param sectionName
 * @returns no.
 */
function appendSectionDivHtml(sectionId, sectionName) {
	var section = '<div class="section">' + '<div class="row">'
			+ '<div class="col-sm-12" style="padding:14px">'
			+ '<div class="input-group">'
			+ '<div  class="section_name_div"><p id="sectionNameText'
			+ sectionId
			+ '">'
			+ sectionName
			+ '</p></div>'
			+ '<div class="input-group-addon">'
			+ '<div class="pull-right">'
			+ '<div class="dropdown">'
			+ '<a style="color:#BFBFBF" id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="icon-dropdown"><i class="fa fa-gears"></i></a>'
			+ '<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dlabel">'
			+ '<li><a onclick="updateSectionName('
			+ sectionId
			+ ');" class="cursor-pointer">'
			+ messages['lbl.editname']
			+ '</a></li>'
			+ '<li class="divider"></li>'
			+ '<li><a class="cursor-pointer sectionDelete" id="sectionDelete'
			+ sectionId
			+ '">'
			+ messages['lbl.delete']
			+ '</a></li>'
			+ '</ul>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '<div class="row">'
			+ '<div id="questionDivOfSection'
			+ sectionId
			+ '">'
			+ '</div>'
			+ '<div class="col-sm-12">'
			+ '<div class="col-sm-4"></div>'
			+ '<div class="col-sm-4 hide" style="border-right: 1px solid #eee;">'
			+ '<h3 style="text-align: center;">'
			+ '<img src="resources/adminlte/dist/img/coursecontent.png" onclick="showCreateQuestionPagePopUp('
			+ sectionId
			+ ');" style="cursor:pointer"/>'
			+ '</h3>'
			+ '<h4 style="text-align: center;">'
			+ messages['lbl.createquestion']
			+ '</h4>'
			+ '</div>'
			+ '<div class="col-sm-4">'
			+ '<h3 style="text-align: center;">'
			+ '<img onclick="getQuestionBank('
			+ sectionId
			+ ');" src="resources/adminlte/dist/img/coursecontent.png" style="cursor:pointer"/>'
			+ '</h3>'
			+ '<h4 style="text-align: center;">'
			+ messages['lbl.addquestionfromquestionbank']
			+ '</h4>'
			+ '<div class="col-sm-2"></div>'
			+ '	</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>';
	$("#totalTestSections").append(section);
	var length = $("#totalTestSections .section").length;
	if (length != null && length > 0) {
		$("#submitAllData").prop('disabled', false);
	}
}

/**
 * @summary function is used append section details div into section (it is all
 *          about how much marks a section has and how much question).
 * @param sectionId
 * @returns no.
 */
function appendSectionDetailsDiv(sectionId) {
	var str = '<div class="well2 well-sm">' + '<div class="input-group">'
			+ '<div class="input-group-addon imgset pull-left"><b>'
			+ messages['lbl.totalquestionadded']
			+ ' :&nbsp;<span id="totalSectionQuestion' + sectionId
			+ '"></span>' + messages['lbl.questions'] + '</b></div>'
			+ '<div class="input-group-addon">' + '<b>'
			+ messages['lbl.totalmarkscontain']
			+ ' :&nbsp;<span id="totalSectionMarks' + sectionId
			+ '"></span></b>' + '</div>' + '</div>' + '</div>';
	$("#questionDivOfSection" + sectionId).after(str);
}

/**
 * @summary This is used for appending new created question in list of section.
 * 
 * @param quesData
 * @param sectionId
 * @returns no.
 */
function appendQuestionInsection(quesData, sectionId) {

	var ques = '<div class="well1 well-sm sectionQuestion" id="sortable_1">'
			+ '<div class="input-group">'
			+ '<div class="imgset pull-left" id="questionText'
			+ sectionId
			+ '##'
			+ quesData[0]
			+ '">'
			+ quesData[1]
			+ '</div>'
			+ '<div class="input-group-addon"><button type="button" id="questionMark'
			+ sectionId
			+ '##'
			+ quesData[0]
			+ '" class="btn nohover btn-flat btn-default" style="cursor:default" disabled>'
			+ quesData[2]
			+ '</button>&nbsp;&nbsp;&nbsp;Marks</div>'
			+ '<div class="input-group-addon">'
			+ '<div class="dropdown pull-right">'
			+ '<a id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="content-dropdown icon-dropdown"> <img src="resources/adminlte/dist/img/ellipsis-v.png" class="shape" /></a>'
			+ '<ul class="dropdown-menu dropdown-menu-right" aria-labelledby=" dlabel">'
			+ '<li><a id="questionEdit'
			+ sectionId
			+ '##'
			+ quesData[0]
			+ '" class="cursor-pointer questionEdit">'
			+ messages['lbl.edit']
			+ '</a></li>'
			+ '<li class="divider"></li>'
			+ '<li><a id="questionDelete'
			+ sectionId
			+ '##'
			+ quesData[0]
			+ '" class="questionDelete cursor-pointer">'
			+ messages['lbl.delete']
			+ '</a></li>'
			+ '</ul>'
			+ '</div>'
			+ '</div>' + '</div>' + '</div>';

	$("#questionDivOfSection" + sectionId).append(ques);
	$('.imgset img').css({
		'max-width' : '400px',
		'height' : 'auto'
	});
	$("#createsectiontab").show();
	$("#questionPageDiv").hide();
}

/**
 * @summary This is used for edit the question when any click event is fired on
 *          element by user which has questionEdit class.
 * 
 * @return no.
 */
$(document).on('click', '.questionEdit', function() {
	var linkId = $(this).attr('id');
	var ids = linkId.split('questionEdit');
	var secandquesid = ids[1].split("##");
	var sectionId = Number($.trim(secandquesid[0]));
	var questionId = Number($.trim(secandquesid[1]));
	currentObject = this;
	editQuestionData(sectionId, questionId);
});

/**
 * @summary This is used for delete the question when any click event is fired
 *          on element by user which has questionDelete class.
 * 
 * @return no.
 */
$(document).on(
		'click',
		'.questionDelete',
		function() {
			$("#clearquestionAlert p").text(messages['msg.deletequestion']);
			var linkId = $(this).attr('id');
			var ids = linkId.split('questionDelete');
			var secandquesid = ids[1].split("##");
			var sectionId = $.trim(secandquesid[0]);
			var questionId = $.trim(secandquesid[1]);
			currentObject = this;
			var clickAction = 'deleteQuestionData("' + sectionId + '","'
					+ questionId + '")';
			$("#dId").attr('onclick', clickAction);
			$("#clearquestionAlert").modal('show');
		});

/**
 * @summary This is used findout the position in json array of a particular
 *          section based on section id.
 * 
 * @param sectionId
 * @return position.
 */
var findSectionIdInList = function(sectionId) {
	var position = 0;
	for (var s = 0; s < questionSectionList.length; s++) {
		if (questionSectionList[s].sectionId == sectionId) {
			position = s;
			break;
		}
	}
	return position;
}

/**
 * @summary This is used for calling fetching all section if user clicks on this
 *          button.
 * @returns no.
 */
$("#importExistSections").click(function() {
	getAllSectionList();
});

/**
 * @summary This is used for fecthing the Section list.
 * 
 * @param action
 * @returns no.
 */
function getAllSectionList() {
	$("#overlay").show();
	$.ajax({
		url : 'gettestsectionlistajax?testId=' + testId,
		type : 'GET',
		error : function() {
			$("#overlay").hide();
			$("#testAlert p").text(messages['msg.somethingwentwrong']);
			$('#testAlert').modal('show');
		},
		success : function(sectionlist) {
			/**
			 * calling function for extracting section details from section list
			 * json and adding in table.
			 */
			extractSectionListData(sectionlist);
		}

	});
}

/**
 * @summary Instance of section list table.
 */
var secListtable;

/**
 * @summary This is used for extracting section details from list json and
 *          adding in table.
 * @param sectionlist
 *            This is only parameter which has details about all section.
 * @return no.
 */
function extractSectionListData(sectionlist) {
	try {
		var alreadyImportedSection = [];
		/**
		 * This will take all imported ids of question in a particular section
		 * and these ids would be used for removing the duplicacy of same
		 * question in same section.
		 */
		for (var sec = 0; sec < questionSectionList.length; sec++) {
			/**
			 * push ids of question if question is already save in test.
			 */
			alreadyImportedSection
					.push(parseInt(questionSectionList[sec].sectionId));
		}

		if (secListtable != null) {
			$('#sectionListTable').DataTable().destroy();
		}
		/**
		 * This is a empty object which would be used adding html data as row
		 * inside table body.
		 */
		var row = '';
		/**
		 * before adding any data in table body, make it empty for no duplicate
		 * data if operation performs again.
		 */
		$("#sectonListbody").empty();
		/**
		 * a lopping on section list data for extract section details.
		 */
		for (var i = 0; i < sectionlist.length; i++) {
			/**
			 * making an argument what would be passed inside a function for
			 * selecting a test.
			 */
			var argument = "'" + sectionlist[i].sectionId + "'";
			/**
			 * checkbox input
			 */
			var inputCheckbox = '';
			/**
			 * check section is already imported.
			 */
			if (contains(alreadyImportedSection, sectionlist[i].sectionId)) {
				inputCheckbox = '<input type="checkbox" name="selectedSectionIds" class="selectedSectionIds" value="'
						+ sectionlist[i].sectionId + '" disabled>';
			} else {
				inputCheckbox = '<input type="checkbox" name="selectedSectionIds" class="selectedSectionIds" value="'
						+ sectionlist[i].sectionId + '">';
			}
			/**
			 * assing a html element for adding a row in table body.
			 */
			row = row + '<tr><td>' + sectionlist[i].sectionName + '</td>'
					+ '<td>' + sectionlist[i].testName + '</td>' + '<td>'
					+ sectionlist[i].totalQuestions + '</td>' + '<td>'
					+ inputCheckbox + '</td></tr>';
		}
		/**
		 * append a table row inside table body.
		 */
		$("#sectonListbody").append(row);
		/**
		 * using icheck for checkbox.
		 */
		$('input[type="checkbox"].selectedSectionIds').iCheck({
			checkboxClass : 'icheckbox_square-green'
		});
		/**
		 * initialize a table into data table
		 */
		secListtable = $("#sectionListTable").dataTable({
			'columnDefs' : [ {
				'orderable' : false,
				'targets' : [ 3 ]
			} ], // hide sort icon on action
			'aaSorting' : [],
			'language' : datatablelanguagejson,
			"destroy" : true
		});
		/**
		 * hide the loader
		 */
		$("#overlay").hide();
		/**
		 * Set calling function on import section button.
		 */
		$("#importSectionButtonInTable").attr('onclick', 'importSection()');
		/**
		 * Disable import button
		 */
		$("#importSectionButtonInTable").prop('disabled', true);
		/**
		 * after loading all data into table , show pop up for choosing a test.
		 */
		$("#sectionListtablePopup").modal('show');
	} catch (err) {
		$("#overlay").hide();
		console.log(err.message)
	}
}

/**
 * @summary function for importing sections in Test.
 * @returns no.
 */
var importSection = function() {
	$("#overlay").show();
	var form = new FormData();
	var everyQuesMark = equalMarkTest == 1 ? everyQuestionMark : 0;
	/**
	 * This would be call when user wants import questions from Question Bank.
	 */
	var checkValues = [];
	$(":checkbox:checked", secListtable.fnGetNodes()).each(function(i) {
		checkValues[i] = $(this).val();
	});
	form.append("selectedSectionIds", checkValues);
	form.append("everyQuestionMark", everyQuesMark);
	form.append("negMark", negMark);
	form.append("equalMarkTest", equalMarkTest);
	form.append("testId", testId);
	$("#sectionListtablePopup").modal('hide');
	$.ajax({
		type : "POST",
		url : "importSectionInTest",
		data : form,
		processData : false,
		contentType : false,
		error : function() {
			$("#overlay").hide();
			$("body").css("overflow", "hidden");
			$("#testAlert p").text(messages['msg.somethingwentwrong']);
			$('#testAlert').modal('show');
		},
		success : function(sectionList) {
			// console.log(JSON.stringify(sectionList));
			if (sectionList.length > 0) {
				performSectionAddingOperation(sectionList);
				$("#overlay").hide();
			} else {
				$("#overlay").hide();
				$("body").css("overflow", "hidden");
				$("#testAlert p").text(messages['msg.somethingwentwrong']);
				$('#testAlert').modal('show');
			}
		}
	});
}

/**
 * @summary This is used adding importing section in test.
 * @param sectionList
 * @returns no.
 */
function performSectionAddingOperation(sectionList) {
	try {
		for (var sec = 0; sec < sectionList.length; sec++) {
			questionSectionList.push(sectionList[sec]);
			appendSectionDivHtml(sectionList[sec].sectionId,
					sectionList[sec].sectionName);
			for (var q = 0; q < sectionList[sec].questionList.length; q++) {
				var quesData = [];
				quesData.push(sectionList[sec].questionList[q].questionId);
				quesData.push(sectionList[sec].questionList[q].questionName);
				quesData.push(sectionList[sec].questionList[q].questionMark);
				appendQuestionInsection(quesData, sectionList[sec].sectionId);
			}
			if (sectionList[sec].questionList.length >= 1) {
				appendSectionDetailsDiv(sectionList[sec].sectionId);
			}
			$("#totalSectionQuestion" + sectionList[sec].sectionId).text(
					sectionList[sec].questionList.length);
			$("#totalSectionMarks" + sectionList[sec].sectionId).text(
					sectionList[sec].sectionScore);
		}
	} catch (err) {
		$("#overlay").hide();
		console.log(err.message);
	}
}

/**
 * @summary This is used showing list for selecting the question type.
 * @param sectionId
 * @returns no.
 */
function showCreateQuestionPagePopUp(sectionId) {
	$("#selectedQuestionType").val(MULTIPLE_CHOICE_TYPE);
	$("#createQuestionType").attr('onclick',
			'showCreateQuestionPage(' + sectionId + ')');
	$("#CreateQuestionPagePopUp").modal('show');
}

function redirectOnTestPreviewPage() {
	if (courseId != null && courseSectionId != null) {
		var url = 'testreview?testId=' + testId + '&courseId='
				+ courseId + '&sectionId=' + courseSectionId;
		window.location.href = contentId != null ? url
				+ '&contentId=' + contentId : url;
	} else {
		window.location.href = 'testreview?testId=' + testId;
	}
}