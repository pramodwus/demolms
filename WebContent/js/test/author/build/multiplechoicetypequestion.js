/**
 * @summary This file would be used for adding and editing multiple choice type
 *          question.
 * @author ankur
 * @date 03 Sep 2016
 */

/**
 * @summary function is used for save the new question data in json.
 * @returns no.
 */
function saveQuestionInJson() {
	var sectionId = $("#sectionIdForAddedQues").val();
	if (questionValidate()) {
		var position = findSectionIdInList(sectionId);
		var quesData = createJson(position);
		appendQuestionInsection(quesData, sectionId);
		if (questionSectionList[position].questionList.length == 1) {
			appendSectionDetailsDiv(sectionId);
		}
		$("#totalSectionQuestion" + sectionId).text(
				questionSectionList[position].questionList.length);
		$("#totalSectionMarks" + sectionId).text(
				questionSectionList[position].sectionScore);
		totalQuestions++;
	}
}

/**
 * @summary This is used for creating the json for new question.
 * 
 * @param position
 * @returns {Array}
 */
function createJson(position) {
	var quesData = [];
	var optionsList = [];
	var questionContent = CKEDITOR.instances['question'].getData();
	var question = {
		"questionId" : parseInt(questionSectionList[position].testId + ""
				+ questionSectionList[position].sectionId
				+ (questionSectionList[position].questionList.length + 1)
				+ dynamicId),
		"questionName" : questionContent,
		"questionNo" : questionSectionList[position].questionList.length + 1,
		"questionMark" : parseInt($("#questionmark").val()),
		"negMark" : (parseInt($("#questionmark").val()) * negMark) / 100,
		"questionType" : parseInt($("#testType").val()),
		"option" : optionsList,
		"isFormula" : $("#isFormula").val(),
		"isNew" : 1, // defines that question is new created in test.
		"mathFormula" : $("#mathformula").val(),
		"explanation" : $("#answerexplanation").val()
	};
	var totalOption = $("#totalOptions").val();
	for (var op = 1; op <= totalOption; op++) {
		var optionContent = CKEDITOR.instances['answer' + op].getData();
		var optionStatus = 0;
		if ($("#answerStatus" + op)[0].checked) {
			optionStatus = 1;
		}
		var options = {
			"optionId" : parseInt(questionSectionList[position].testId + ""
					+ questionSectionList[position].sectionId
					+ (questionSectionList[position].questionList.length + 1)
					+ op),
			"optionName" : optionContent,
			"optionOrder" : op,
			"answerStatus" : optionStatus
		};
		optionsList.push(options);
	}
	quesData.push(question.questionId);
	quesData.push(question.questionName);
	quesData.push(question.questionMark);
	questionSectionList[position].sectionScore = questionSectionList[position].sectionScore
			+ question.questionMark;
	questionSectionList[position].questionList.push(question);
	dynamicId++;
	return quesData;
}

/**
 * @summary This is used for updating the question json of multi choice type
 *          question.
 * 
 * @param position
 * @param questionId
 * @returns no.
 */
function updateQuestionJson(position, questionId) {
	if (questionValidate()) {
		for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
			if (questionSectionList[position].questionList[q].questionId == questionId) {
				var optionsList = [];
				var questionContent = CKEDITOR.instances['question'].getData();
				var question = {
					"questionId" : questionId,
					"questionName" : questionContent,
					"questionNo" : (q + 1),
					"questionMark" : parseInt($("#questionmark").val()),
					"negMark" : (parseInt($("#questionmark").val()) * negMark) / 100,
					"questionType" : parseInt($("#testType").val()),
					"option" : optionsList,
					"isFormula" : parseInt($("#isFormula").val()),
					"isNew" : questionSectionList[position].questionList[q].isNew,
					"mathFormula" : $("#mathformula").val(),
					"explanation" : $("#answerexplanation").val(),
					"isParent" : questionSectionList[position].questionList[q].isParent
				};
				var totalOption = $("#totalOptions").val();
				for (var op = 1; op <= totalOption; op++) {
					var optionContent = CKEDITOR.instances['answer' + op]
							.getData();
					var optionStatus = 0;
					$('.imgset img').css({
						'max-width' : '400px',
						'height' : 'auto'
					});
					if ($("#answerStatus" + op)[0].checked) {
						optionStatus = 1;
					}
					var options = {
						"optionId" : parseInt(questionSectionList[position].testId
								+ ""
								+ questionSectionList[position].sectionId
								+ (questionSectionList[position].questionList.length + 1)
								+ op),
						"optionName" : optionContent,
						"optionOrder" : op,
						"answerStatus" : optionStatus
					};
					optionsList.push(options);
				}
				questionSectionList[position].sectionScore = questionSectionList[position].sectionScore
						- questionSectionList[position].questionList[q].questionMark
						+ question.questionMark;
				questionSectionList[position].questionList[q] = question;
				$(
						"#totalSectionQuestion"
								+ questionSectionList[position].sectionId)
						.text(questionSectionList[position].questionList.length);
				$(
						"#totalSectionMarks"
								+ questionSectionList[position].sectionId)
						.text(questionSectionList[position].sectionScore);
				var quesmarkObj = document.getElementById('questionMark'
						+ questionSectionList[position].sectionId + '##'
						+ questionId);
				$(quesmarkObj).text(question.questionMark);
				var obj = document.getElementById('questionText'
						+ questionSectionList[position].sectionId + '##'
						+ questionId);
				$(obj).html(CKEDITOR.instances['question'].getData());
			}
			// console.log(JSON.stringify(questionSectionList));
		}
		$("#createsectiontab").show();
		$("#questionPageDiv").hide();
		$('.imgset img').css({
			'max-width' : '400px',
			'height' : 'auto'
		});
		$("#testAlert p").text(messages['msg.successquestiondetailupdate']);
		$("#testAlert").modal('show');
	}
}

/**
 * @summary This is used for filling the question data in creating question tab
 *          for updating.
 * 
 * @param id @
 *            returns no.
 */
function fillQuestionData(position, questionId) {
	for (var i = 0; i < questionSectionList[position].questionList.length; i++) {
		if (questionSectionList[position].questionList[i].questionId == questionId) {
			CKEDITOR.instances['question']
					.setData(questionSectionList[position].questionList[i].questionName);
			$("#questionmark").val(
					questionSectionList[position].questionList[i].questionMark);
			if (questionSectionList[position].questionList[i].questionType == SINGLE_CHOICE_TYPE) {
				$("#ansTypeButton").prop('checked', false);
				changeAnswerType();
			}
			for (var j = 0; j < questionSectionList[position].questionList[i].option.length; j++) {
				if ($("#totalOptions").val() == j) {
					addAnswers();
				}
				CKEDITOR.instances['answer' + (j + 1)]
						.setData(questionSectionList[position].questionList[i].option[j].optionName);
				if (questionSectionList[position].questionList[i].option[j].answerStatus == 1) {
					$("#answerStatus" + (j + 1)).iCheck('check');
				}
			}
			if (questionSectionList[position].questionList[i].isFormula == 1) {
				console.log("isFormula "+questionSectionList[position].questionList[i].mathFormula )
				$("#isFormula").val(1);
				$("#isFormula").prop('checked', true);
				CKEDITOR.instances['mathformula'].setData(questionSectionList[position].questionList[i].mathFormula);
				$("#mathformuladiv").show();
			}
			$("#answerexplanation").val(
					questionSectionList[position].questionList[i].explanation);
			$("#submitQuestion").text(messages['lbl.update']);
			var updateAction = 'updateQuestionJson("' + position + '","'
					+ questionId + '")';
			$("#submitQuestion").attr('onclick', updateAction);
		}
	}
	$('.imgset img').css({
		'max-width' : '400px',
		'height' : 'auto'
	});
}

/**
 * @summary This is used of adding creating question div.
 * 
 * @param totalQuestions
 * @returns no.
 */
function addNextQuestion(totalQuestions) {
	$("#questiondiv").empty();
	var str = '<div class="col-xs-12">' + '<div class="col-xs-12 hide">'
			+ '<label>'+messages['lbl.multipleanswer']+' :</label>'
			+ '<div class="form-group"><input type="hidden" value="'
			+ MULTIPLE_CHOICE_TYPE
			+ '" id="testType" name="testType"><input type="checkbox" value="On" id="ansTypeButton" style="background-color: #DD2163;color: white" onclick="changeAnswerType();" checked></div>'
			+ '</div>'

			+ '<div class="col-xs-12 question form-group"><label id="questiontitle"><sup><font color="red" size="3px">*</font></sup>'+ messages['lbl.question'] +' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-title-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="questionEditor" class="text_editor_margin"><textarea name="question" id="question" class="form-control myTextEditor" placeholder="'+messages['placeholder.question']+'"></textarea></div>'
			+ '<label class="requireFld" id="questionError">'+messages['msg.empty']+'</label>'
			+ '</div>'
			
			+ '<div class="col-xs-12 form-group"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.answers']+' ('+ messages['msg.multipleanswer']+')</label></div>'
			+ '<div class="col-xs-12 "><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="selectError">'+messages['lbl.clickforrightanswer'] +'</label></div></div>'
			+ '<div id="ansType">'
			
			+ '<!-- Ans Div -->'
			+ '<div id="option1" class="form-group">'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-sm-12"><label>'+messages['lbl.option']+' <span id="option-order-1">1</span> &nbsp;<span class="question-badge bg-gray questiontype-tooltip question-option-title-tooltip"><i class="fa fa-question"></i></span></label></div>'
			+ '<div class="row no-margin">'
			+ '<div class="input-group">'
			+ '<span class="input-group-addon" id="answerOrder1"><span class="badge bagde-style">A</span></span>'
			+ '<span class="input-group-addon">'
			+ '<input type="checkbox" class="flat-red questionType"  name="answerStatus" id="answerStatus1" value="1" onclick="questionKeyValidate();">'
			+ '</span>'
			+ '<div id="answer1div" class="text_editor_margin"><textarea name="answer1" id="answer1"  class="form-control myTextEditor" placeholder="'+messages['lbl.answernumber'] +' 1"></textarea></div>'
			+ '<span class="input-group-addon" id="deleteOption1" onclick="deleteOption(this)" style="visibility:hidden"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle" title="'+messages['lbl.removeanswer'] +'"></i></span>'
			+ '</div><!-- /input-group -->'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer1Error">'+messages['msg.empty']+' </label></div></div>'
			+ '</div>'
			+

			'<div id="option2" class="form-group">'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-sm-12"><label>'+messages['lbl.option']+' <span id="option-order-2">2</span> &nbsp;<span class="question-badge bg-gray questiontype-tooltip question-option-title-tooltip"><i class="fa fa-question"></i></span></label></div>'
			+ '<div class="row no-margin">'
			+ '<div class="input-group">'
			+ '<span class="input-group-addon" id="answerOrder2"><span class="badge bagde-style">B</span></span>'
			+ '<span class="input-group-addon">'
			+ '<input type="checkbox" class="flat-red questionType" name="answerStatus" id="answerStatus2" value="2" onclick="questionKeyValidate();">'
			+ '</span>'
			+ '<div id="answer2div" class="text_editor_margin"><textarea name="answer2" id="answer2"  class="form-control myTextEditor" placeholder="'+messages['lbl.answernumber']+' 2"></textarea></div>'
			+ '<span class="input-group-addon" id="deleteOption2" onclick="deleteOption(this)" style="visibility:hidden"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle" title="'+messages['lbl.removeanswer'] +'"></i></span>'
			+ '</div><!-- /input-group -->'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer2Error">'+messages['msg.empty'] +'</label></div></div>'
			+ '</div>'
			+

			'<input type="hidden" id="totalOptions" name="totalOptions" value="2">'
			+ '</div><!-- /.ansType -->'

			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" onclick="addAnswers();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addanswer'] +'</span></button>'
			+ '&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-flat button-width-large" onclick="clearQuestion();"><span><i class="fa fa-trash-o"></i>  '+messages['lbl.clearquestion']+'</span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<label class="form-group">'+messages['lbl.answerexplanation']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-explanation-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<textarea class="form-control textAreaControl"  name="answerexplanation" id="answerexplanation" placeholder="'+messages['lbl.answerexplanation']+'" onkeyup="questionKeyValidate();"></textarea>'
			+ '<label class="requireFld" id="answerexplanationerror">'+messages['msg.empty']+'</label>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group">'
			+ '<label class="form-group">'+messages['lbl.mathematicalformula']+' </label>'
			+ '<div class="form-group"><input type="checkbox" value="0" id="isFormula" style="background-color: #DD2163; color: white" onclick="changeMathFormula();"></div>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group" id="mathformuladiv" style="display:none">'
			+ '<label class="form-group">'+messages['lbl.mathematicalformula']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-formula-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="mathformulaEditor">'
			+ '<textarea name="mathformula" id="mathformula" class="form-control myTextEditor"></textarea>'
			+ '</div>'
			+ '<label class="requireFld" id="mathformulaerror">'+messages['msg.empty']+'</label>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group questionMark" id="questionmarkdiv">'
			+ '<label class="form-group"><sup><font color="red" size="3px">&nbsp;*</font></sup><span>'+messages['lbl.enterthemarkforthisquestion']+'</label>'
			+ '<input type="text" style="width: 50px" name="questionmark" class="form-control" id="questionmark" onkeyup="questionKeyValidate();" maxlength="2">'
			+ '<label class="requireFld" id="questionmarkError">'+messages['msg.empty']+'</label><label class="requireFld" id="questionmarkError1">'+messages['msg.allowednumericvalue']+'</label>'
			+ '</div>'

			+ '</div>';
	$("#questiondiv").append(str);

	$("#ansTypeButton").toggleSwitch();
	$("#isFormula").toggleSwitch();
	CKEDITOR.inline('question').on('change', function(e) {
		questionKeyValidate();
	});
	CKEDITOR.inline('answer1', {
		height : 40
	}).on('change', function(e) {
		questionKeyValidate();
	});
	CKEDITOR.inline('answer2', {
		height : 40
	}).on('change', function(e) {
		questionKeyValidate();
	});
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green'
	});

	CKEDITOR.inline('mathformula').on('change', function(e) {
		questionKeyValidate();
	});
	if (equalMarkTest == 1) {
		$("#questionmark").val(everyQuestionMark);
		$("#questionmarkdiv").hide();
	}
	
	$('#question-title-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('.question-option-title-tooltip').tooltip({title: messages['lbl.multipletypeoptiondesctooltip'], trigger: "hover",placement:"right"});
	$('#question-formula-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('#question-explanation-tooltip-title').tooltip({title: messages['lbl.questionexpdescriptionintooltip'], trigger: "hover",placement:"right"});
}

/**
 * @summary This is used for changing the question type of question.
 * @returns no.
 */
function changeAnswerType() {
	var val = $("#ansTypeButton").val();
	if (val == 'On') {
		$(".questionType").attr('type', 'radio');
		$(".questionType").prop('checked', false);
		$("#ansTypeButton").val('Off');
		$("#testType").val(SINGLE_CHOICE_TYPE);
	}
	if (val == 'Off') {
		$(".questionType").attr('type', 'checkbox');
		$(".questionType").prop('checked', false);
		$("#ansTypeButton").val('On');
		$("#testType").val(MULTIPLE_CHOICE_TYPE);
	}
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green'
	});
}

/**
 * @summary This is used to adding more answer in question.
 * @returns no.
 */
function addAnswers() {
	var optionNo = $("#totalOptions").val();
	var optn = 65 + parseInt(optionNo);
	var optionOrder = "&#" + optn + ";";
	var val = $("#ansTypeButton").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		$("#deleteOption1").css("visibility", "visible");
		$("#deleteOption2").css("visibility", "visible");
		if (val == "On") {
			str = '<div id="option'
					+ optionNo
					+ '" class="form-group">'
					+ '<div class="col-xs-12 formBody">'
					+ '<div class="col-sm-12"><label>'+messages['lbl.option']+' <span id="option-order-'+optionNo+'">'+optionNo+'</span> &nbsp;<span class="question-badge bg-gray questiontype-tooltip question-option-title-tooltip"><i class="fa fa-question"></i></span></label></div>'
					+ '<div class="row no-margin">'
					+ '<div class="input-group">'
					+ '<span class="input-group-addon" id="answerOrder'
					+ optionNo
					+ '"><span class="badge bagde-style">'
					+ optionOrder
					+ '</span></span>'
					+ '<span class="input-group-addon">'
					+ '<input type="checkbox" class="flat-red questionType" name="answerStatus" id="answerStatus'
					+ optionNo
					+ '" value="'
					+ optionNo
					+ '" onclick="questionKeyValidate();">'
					+ '</span>'
					+ '<div id="answer'
					+ optionNo
					+ 'div" class="text_editor_margin"><textarea name="answer'
					+ optionNo
					+ '" id="answer'
					+ optionNo
					+ '"  placeholder="'+messages['lbl.answernumber']+' '
					+ optionNo
					+ '" class="form-control myTextEditor"></textarea></div>'
					+ '<span class="input-group-addon" id="deleteOption'
					+ optionNo
					+ '" onclick="deleteOption(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'Error">'+messages['msg.empty']+'</label></div></div>'
					+ '</div>';
		} else {
			str = '<div id="option'
					+ optionNo
					+ '" class="form-group">'
					+ '<div class="col-xs-12 formBody">'
					+ '<div class="col-sm-12"><label>'+messages['lbl.option']+' <span id="option-order-'+optionNo+'">'+optionNo+'</span> &nbsp;<span class="question-badge bg-gray questiontype-tooltip question-option-title-tooltip"><i class="fa fa-question"></i></span></label></div>'
					+ '<div class="">'
					+ '<div class="input-group">'
					+ '<span class="input-group-addon" id="answerOrder'
					+ optionNo
					+ '"><span class="badge bagde-style">'
					+ optionOrder
					+ '</span></span>'
					+ '<span class="input-group-addon">'
					+ '<input type="radio" class="flat-red questionType" name="answerStatus" id="answerStatus'
					+ optionNo
					+ '" value="'
					+ optionNo
					+ '" onclick="questionKeyValidate();">'
					+ '</span>'
					+ '<div id="answer'
					+ optionNo
					+ 'div" class="text_editor_margin"><textarea name="answer'
					+ optionNo
					+ '" id="answer'
					+ optionNo
					+ '"  placeholder="'+messages['lbl.answernumber']+' '
					+ optionNo
					+ '" class="form-control myTextEditor"></textarea></div>'
					+ '<span class="input-group-addon" id="deleteOption'
					+ optionNo
					+ '" onclick="deleteOption(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'Error">'+messages['msg.empty']+'</label></div></div>'
					+ '</div>';
		}
		$("#ansType").append(str);
		$("#totalOptions").val(optionNo);
		CKEDITOR.inline('answer' + optionNo, {
			height : 40
		}).on('change', function(e) {
			questionKeyValidate();
		});
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
				.iCheck({
					checkboxClass : 'icheckbox_square-green',
					radioClass : 'iradio_square-green'
				});
		$('.question-option-title-tooltip').tooltip({title: messages['lbl.multipletypeoptiondesctooltip'], trigger: "hover",placement:"right"});
	}
}

/**
 * @summary This is used for validate the question.
 * @returns {Boolean}
 */
function questionValidate() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptions").val();
	var checkboxcondition = true;
	var questionContent = CKEDITOR.instances['question'].getData();
	var questionContentData = ConvertHtmlToPlainTest(questionContent);
	if (questionContentData == "") {
		$("#questionEditor").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#questionError").fadeIn();
		$(window).scrollTop($("#questiondiv").offset().top);
		return false;
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#answer" + j + "div").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#answer" + j + "Error").fadeIn();
			$(window).scrollTop($("#answer" + j + "div").offset().top);
			return false;
		}
		checkboxcondition = checkboxcondition
				&& ($("#answerStatus" + j)[0].checked == false);

	}
	if (checkboxcondition) {
		$("#selectError").fadeIn();
		$(window).scrollTop($("#selectError").offset().top);
		return false;
	}
	var isFormula = $("#isFormula").val().trim();
	if (isFormula == '1') {
		var mathformula = CKEDITOR.instances['mathformula'].getData();
		var mathformulaData = ConvertHtmlToPlainTest(mathformula);
		if (mathformulaData == "") {
			$("#mathformulaEditor").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#mathformulaerror").fadeIn();
			$(window).scrollTop($("#mathformuladiv").offset().top);
			return false;
		}
	}

	if (equalMarkTest == 0) {
		if ($("#questionmark").val() == "") {
			$("#questionmark").css("border-color", "#c95b5b");
			$("#questionmarkError").fadeIn();
			return false;
		} else if (!($("#questionmark").val()).match(/^\d*$/)) {
			$("#questionmark").css("border-color", "#c95b5b");
			$("#questionmarkError1").fadeIn();
			return false;
		}
	}
	/*
	 * if($("#answerexplanation"+quesNo).val()=="") {
	 * $("#answerexplanation"+quesNo).css("border-color","#c95b5b");
	 * $("#answerexplanationerror"+quesNo).fadeIn(); return false; }
	 */
	return true;
}

/**
 * @summary It is used for checking that question text is empty or not.
 * 
 * @returns {Boolean}
 */
function questionIsBlank() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptions").val();

	var questionContent = CKEDITOR.instances['question'].getData();
	var questionContentData = ConvertHtmlToPlainTest(questionContent);
	if (questionContentData != "") {
		return false;
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData != "") {
			return false;
		}
	}
	/*
	 * if($("#questionmark").val() != ""){ return false; }
	 */
	return true;
}

/**
 * @summary This is used for hidding the error on question page.
 * @returns no.
 */
function questionKeyValidate() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var checkboxcondition = false;
	var totaloptions = $("#totalOptions").val();
	var questionContent = CKEDITOR.instances['question'].getData();
	if (questionContent != "") {
		$("#questionEditor").css("border-color", "#7ac17d");
		$("#questionError").fadeOut();
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j].getData();
		if (optionContent != "") {
			$("#answer" + j + "div").css("border-color", "#7ac17d");
			$("#answer" + j + "Error").fadeOut();
		}
		checkboxcondition = checkboxcondition
				|| ($("#answerStatus" + j)[0].checked == true);
	}
	if (checkboxcondition) {
		$("#selectError").fadeOut();
	}
	if ($("#questionmark").val().length > 0) {
		$("#questionmark").css("border-color", "#7ac17d");
		$("#questionmarkError").fadeOut();
		$("#questionmarkError1").fadeOut();
	}
	var mathformula = CKEDITOR.instances['mathformula'].getData();

	if (mathformula != "") {
		$("#mathformulaEditor").css("border-color", "#7ac17d");
		$("#mathformulaerror").fadeOut();
	}
	if ($("#answerexplanation").val().length > 0) {
		$("#answerexplanation").css("border-color", "#7ac17d");
		$("#answerexplanationerror").fadeOut();
	}
}

/**
 * @summary This is used for showing alert if user press clear question button.
 * @returns no.
 */
function clearQuestion() {
	$("#clearquestionAlert p").text(messages['msg.clearquestion']);
	$("#dId").attr('onclick', 'clearQuestionData()');
	$('#clearquestionAlert').modal('show');
}

/**
 * @summary This is used for clear the question data from all fields.
 * @returns no.
 */
function clearQuestionData() {
	$('#clearquestionAlert').modal('hide');
	var totalQuestions = $("#totalQuestions").val();
	var totalOptions = $("#totalOptions").val();
	CKEDITOR.instances['question'].setData('');
	$('.flat-red').iCheck('uncheck');
	for (var op = 1; op <= totalOptions; op++) {
		CKEDITOR.instances['answer' + op].setData('');
	}
	if (equalMarkTest != 1) {
		$("#questionmark").val('');
	}
	CKEDITOR.instances['mathformula'].setData('');
	$("#answerexplanation").val('');
	$(window).scrollTop(0);
}

/**
 * @summary This is used for deleting the option of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteOption(object) {
	var optionId = object.id;
	var arr = optionId.split('deleteOption');
	var optionNo = parseInt(arr[1]);
	var totalOption = $("#totalOptions").val();
	if (totalOption <= 2) {
	} else {
		$("#option" + optionNo).remove();
		optionNo++;
		for (var i = optionNo; i <= totalOption; i++) {
			$("#option" + i).attr('id', "option" + (i - 1));
			$("#option-order-" + i).attr('id',"option-order-" + (i - 1));
			$("#option-order-" + (i-1)).text(i-1);
			$("#answerOrder" + i).attr('id', "answerOrder" + (i - 1));
			$("#answerOrder" + (i - 1)).html(
					"<span class='badge bagde-style'>&#" + (64 + i - 1)
							+ ";</span>");
			$("#answerStatus" + i).attr({
				id : "answerStatus" + (i - 1),
				value : (i - 1)
			});
			$("#answer" + i + "div").attr('id', "answer" + (i - 1) + "div");
			$("#answer" + i).attr({
				id : "answer" + (i - 1),
				name : "answer" + (i - 1),
				placeholder : messages['lbl.answernumber']+" " + (i - 1)
			});
			$("#deleteOption" + i).attr('id', "deleteOption" + (i - 1));
			$("#answer" + i + "Error").attr('id', "answer" + (i - 1) + "Error");
			$("#optionorderlist" + i).attr('id', "optionorderlist" + (i - 1));
			$("#optionText" + i).attr('id', "optionText" + (i - 1));
			CKEDITOR.instances['answer' + i].destroy();
			CKEDITOR.inline('answer' + (i - 1), {
				height : 40
			}).on('change', function(e) {
				questionKeyValidate();
			});
		}
		totalOption--;
		$("#totalOptions").val(totalOption);
		$('.question-option-title-tooltip').tooltip({title: messages['lbl.multipletypeoptiondesctooltip'], trigger: "hover",placement:"right"});
	}
	if (totalOption == 2) {
		$("#deleteOption1").css("visibility", "hidden");
		$("#deleteOption2").css("visibility", "hidden");
	}
}

/**
 * @summary This function is used for show and hide the math formula field.
 * @returns no.
 */
function changeMathFormula() {
	var isFormula = $("#isFormula").val().trim();
	if (isFormula == '0') {
		$("#isFormula").val('1');
		$("#mathformuladiv").show();
	} else {
		$("#isFormula").val('0');
		$("#mathformuladiv").hide();
	}
}