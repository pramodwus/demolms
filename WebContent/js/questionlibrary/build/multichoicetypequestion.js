/**
 * @summary This js file is used for creating and editing multiple choice type question.
 * @author ankur
 * @date 23 Aug 2016.
 */

/**
 * @summary This is used to create the multiple type question.
 * @returns no
 */
function addNextQuestion() {
	$("#questiondiv").empty();
	var str = '<div class="col-xs-12">'
			+ '<div class="col-xs-12 question form-group"><label id="questiontitle"><sup><font color="red" size="3px">*</font></sup>'+ messages['lbl.question'] +' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-title-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="questionEditor" class="text_editor_margin"><textarea name="question" id="question" class="form-control myTextEditor"  placeholder="'+messages['placeholder.question']+'"></textarea></div>'
			+ '<label class="requireFld" id="questionError">'+messages['msg.empty']+'</label>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.answers']+' ('+ messages['msg.multipleanswer']+')</label></div>'
			+ '<div class="col-xs-12 "><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="selectError">'+messages['lbl.clickforrightanswer'] +'</label></div></div>'
			+ '<div id="ansType">'
			
			+ '<!-- Ans Div -->'
			+ '<div id="option1">'
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
			+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer1Error">'+messages['msg.empty'] +'</label></div></div>'
			+ '</div>'
			+

			'<div id="option2">'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-sm-12"><label>'+messages['lbl.option']+' <span id="option-order-2">2</span> &nbsp;<span class="question-badge bg-gray questiontype-tooltip question-option-title-tooltip"><i class="fa fa-question"></i></span></label></div>'
			+ '<div class="row no-margin">'
			+ '<div class="input-group">'
			+ '<span class="input-group-addon" id="answerOrder2"><span class="badge bagde-style">B</span></span>'
			+ '<span class="input-group-addon">'
			+ '<input type="checkbox" class="flat-red questionType" name="answerStatus" id="answerStatus2" value="2" onclick="questionKeyValidate();">'
			+ '</span>'
			+ '<div id="answer2div" class="text_editor_margin"><textarea name="answer2" id="answer2"  class="form-control myTextEditor" placeholder="'+messages['lbl.answernumber'] +' 2"></textarea></div>'
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
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-11">'
			+ '<button type="button" class="margin-left btn btn-flat btn-success button-width-large" onclick="addAnswers();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addanswer'] +'</span></button>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" style="min-height: 10px"></div>'

			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-2 margin-left hide"><label class="pull-left">'+messages['lbl.multipleanswer'] +'</label></div>'
			
			+ '<div class="col-xs-3 hide">' + '<input type="hidden" value="'
			+ MULTIPLE_CHOICE_TYPE
			+ '" id="testType" name="testType">'
			+ '<input type="checkbox" value="On" id="ansTypeButton" style="background-color: #DD2163;color: white" onclick="changeAnswerType();" checked>'
			+ '</div>'
			
			+ '<div class="col-xs-3 hide" style="min-width:200px;">&nbsp;&nbsp;&nbsp;<label>'+messages['lbl.mathematicalformula']+'</label></div>'
			+ '<div class="col-xs-2 hide"><input type="checkbox" value="0" id="isFormula" style="background-color: #DD2163; color: white" onclick="changeMathFormula();"></div>'
			+ '</div>'
			
			+ '<div class="col-xs-12 form-group">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-11">'
			+ '<button type="button" class="btn btn-danger btn-flat button-width-large margin-left hide" onclick="clearQuestion();"><span><i class="fa fa-trash-o"></i> '+messages['lbl.clearquestion']+'</span></button>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group hide" id="mathformuladiv" style="display:none">'
			+ '<label class="form-group">'+messages['lbl.mathematicalformula']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-formula-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="mathformulaEditor">'
			+ '<textarea name="mathformula" id="mathformula" class="form-control myTextEditor"></textarea>'
			+ '</div>'
			+ '<label class="requireFld" id="mathformulaerror">'+messages['msg.empty']+'</label>'
			+ '</div>'
			+ '<div class="col-xs-12 form-group">'
			+ '<label class="form-group">'+messages['lbl.answerexplanation']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-explanation-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<textarea class="form-control textAreaControl"  name="answerexplanation" id="answerexplanation" placeholder="'+messages['lbl.answerexplanation']+'" onkeyup="questionKeyValidate();"></textarea>'
			+ '<label class="requireFld" id="answerexplanationerror">'+messages['msg.empty']+'</label>'
			+ '</div>' + '</div>' + '<div class="col-xs-12 formBody"></div>';
	$("#questiondiv").append(str);
	$("#ansTypeButton").toggleSwitch();
	$("#isFormula").toggleSwitch();
	CKEDITOR.inline('question').on('key', function(e) {
		questionKeyValidate();
	});
	CKEDITOR.inline('answer1', {
		height : 40
	}).on('key', function(e) {
		questionKeyValidate();
	});
	CKEDITOR.inline('answer2', {
		height : 40
	}).on('key', function(e) {
		questionKeyValidate();
	});
	CKEDITOR.inline('mathformula').on('key', function(e) {
		questionKeyValidate();
	});
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green'
	});
	$('#question-title-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('.question-option-title-tooltip').tooltip({title: messages['lbl.multipletypeoptiondesctooltip'], trigger: "hover",placement:"right"});
	$('#question-formula-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('#question-explanation-tooltip-title').tooltip({title: messages['lbl.questionexpdescriptionintooltip'], trigger: "hover",placement:"right"});
}

/**
 * @summary This is used adding options in multi type question.
 * @returns no
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
					+ '">'
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
			'</div>';
		} else {
			str = '<div id="option'
					+ optionNo
					+ '">'
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
			'</div>';
		}
		$("#ansType").append(str);
		$("#totalOptions").val(optionNo);
		CKEDITOR.inline('answer' + optionNo, {
			height : 40
		}).on('key', function(e) {
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
 * @summary This is used change the single to multi choice and multi to single
 *          choice question.
 * @returns no
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
 * @summary This is used validate the data of multi type question.
 * @returns Boolean
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
	return customQuestionTagValidate();
	//return questionKeyValidate();
}




/**
 * @summary This is used to fadeout the validation error on create question page
 *          on key event.
 * @returns no
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


function customQuestionTagValidate() {
	var isValid = true;
	$('#createQuestion').find('.question-tags').each(function() {
		var value = $(this).find('.question-tags-input').val();
		
		if(value == null || $.trim(value).length == 0) {
			$(this).find('.question-tags-input').css("border-color", "#c95b5b");
			$(this).find('.question-tags-input-error').fadeIn();
			isValid = false;
		}/*
		else {
			
		 
			
			$(this).find('.question-tags-input').css("border-color", "#7ac17d");
			$(this).find('.question-tags-input-error').fadeOut();
			isValid = true;
			
		}*/
	});
	return isValid;
}

function keyCustomQuestionTagValidate() {
	$('#createQuestion').find('.question-tags').each(function() {
		var value = $(this).find('.question-tags-input').val();
		if(value != null && $.trim(value).length>0) {
			$(this).find('.question-tags-input').css("border-color", "#7ac17d");
			$(this).find('.question-tags-input-error').fadeOut();
		}
	});
}


/**
 * @summary This is used to show and hide the text area field for math formula.
 * @returns no
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

/**
 * @summary This is used for show the pop up for clear question data.
 * @returns no
 */
function clearQuestion() {
	$("#clearquestionAlert p").text(messages['msg.clearquestion']);
	$("#dId").attr('onclick', 'clearQuestionData()');
	$('#clearquestionAlert').modal('show');
}

/**
 * @summary This is used to clear the multi type question data.
 * @returns no
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
	CKEDITOR.instances['mathformula'].setData('');
	$("#answerexplanation").val('');
	$(window).scrollTop(0);
}

/**
 * @summary This is used for delete the option from question.
 * @param object
 * @returns no
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
			CKEDITOR.instances['answer' + i].destroy();
			CKEDITOR.inline('answer' + (i - 1), {
				height : 40
			}).on('key', function(e) {
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
 * @summary This is used for confirm the user for submit the details of multiple
 *          choice type question.
 * @returns no.
 */
function saveMultiQuesPopUp() {
	if (questionValidate()) {
		$("#clearquestionAlert p").text(messages['msg.submitdetail']);
		$("#dId").attr('onclick', 'saveMultiTypeQuestionInJson()');
		$("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary function is used for save the new data of multi choice question in
 *          json.
 * @retruns no
 */
function saveMultiTypeQuestionInJson() {
	if (questionValidate()) {
		var quesData = createJson();
		submitQuestionDetails();
	}
}

/**
 * @summary This is used to create the json for multi choice type question.
 * @returns {Array}
 */
function createJson() {
	var quesData = [];
	var optionsList = [];
	var questionContent = CKEDITOR.instances['question'].getData();
	var question = {
		"questionId" : dynamicId,
		"questionName" : questionContent,
		"questionType" : parseInt($("#testType").val()),
		"option" : optionsList,
		"isFormula" : $("#isFormula").val(),
		"mathFormula" : $("#mathformula").val(),
		"explanation" : $("#answerexplanation").val(),
		"tagList" : createJsonForTags()
	};
	console.log("createJsonForTags " + JSON.stringify(createJsonForTags()));
	
	var totalOption = $("#totalOptions").val();
	for (var op = 1; op <= totalOption; op++) {
		var optionContent = CKEDITOR.instances['answer' + op].getData();
		var optionStatus = 0;
		if ($("#answerStatus" + op)[0].checked) {
			optionStatus = 1;
		}
		var options = {
			"optionId" : dynamicId + "" + op,
			"optionName" : optionContent,
			"optionOrder" : op,
			"answerStatus" : optionStatus
		};
		optionsList.push(options);
	}
	quesData.push(question.questionId);
	quesData.push(question.questionName);
	questionSectionList[0].questionList.push(question);
	dynamicId++;
	 console.log(JSON.stringify(questionSectionList)); 
	return quesData;
}



function createJsonForTags(){
	var tagList = [];
	$(".question-tags").each(function(){
		var tag = {
				"id":$(this).data('id'),
				"value": $(this).find('.question-tags-input').val()
		}
		tagList.push(tag);
	});
	return tagList;
	
}

/**
 * @summary This is used for showing the pop up for confirm the user for update
 *          the multiple choice type question.
 * @param questionId
 * @returns no
 */
function updateMultiTypeQuestionPopUp(questionId) {
	if (questionValidate()) {
		$("#clearquestionAlert p").text(messages['msg.sureforquestionupdate']);
		$("#dId")
				.attr('onclick', 'updateMultiTypeQuestion(' + questionId + ')');
		$("#clearquestionAlert").modal('show');
	}
	
}

/**
 * @summary This is used for performing opertion for update the multi choice
 *          type question.
 * @param questionId
 * @retruns no
 */
function updateMultiTypeQuestion(questionId) {
	if (questionValidate()) {
		var quesData = updateQuestionInJson(questionId);
		submitQuestionDetails();

	}
}

/**
 * @summary This is used for updating the json of this question.
 * @param questionId
 * @returns {Array}
 */
function updateQuestionInJson(questionId) {
	for (var q = 0; q < questionSectionList[0].questionList.length; q++) {
		if (questionSectionList[0].questionList[q].questionId == questionId) {
			var optionsList = [];
			var questionContent = CKEDITOR.instances['question'].getData();
			var question = {
				"questionId" : questionId,
				"questionName" : questionContent,
				"questionNo" : (q + 1),
				"questionType" : parseInt($("#testType").val()),
				"option" : optionsList,
				"isFormula" : $("#isFormula").val(),
				"mathFormula" : $("#mathformula").val(),
				"explanation" : $("#answerexplanation").val(),
				"tagList" :UpdateJsonForTags(questionId)
			};
			var totalOption = $("#totalOptions").val();
			for (var op = 1; op <= totalOption; op++) {
				var optionContent = CKEDITOR.instances['answer' + op].getData();
				var optionStatus = 0;
				if ($("#answerStatus" + op)[0].checked) {
					optionStatus = 1;
				}
				var options = {
					"optionId" : parseInt(questionId + "" + op),
					"optionName" : optionContent,
					"optionOrder" : op,
					"answerStatus" : optionStatus
				};
				optionsList.push(options);
			}
			questionSectionList[0].questionList[q] = question;
		}
		/* console.log(JSON.stringify(questionSectionList)); */
	}
	
	function UpdateJsonForTags(){
		var tagList = [];
		$(".question-tags").each(function(){
			var tag = {
					"id":$(this).data('id'),
					"value": $(this).find('.question-tags-input').val()
			}
			tagList.push(tag);
		});
		return tagList;
		
	}
	
	
	
	var quesData = [];
	quesData.push(question.questionId);
	quesData.push(question.questionName);
	return quesData;
}

/**
 * @summary This is used for filling multi type question data from question json
 *          object.
 * @returns no
 */
function fillQuestionData(questionId) {
	for (var i = 0; i < questionSectionList[0].questionList.length; i++) {
		if (questionSectionList[0].questionList[i].questionId == questionId) {
			CKEDITOR.instances['question']
					.setData(questionSectionList[0].questionList[i].questionName);
			if (questionSectionList[0].questionList[i].questionType == SINGLE_CHOICE_TYPE) {
				$("#ansTypeButton").prop('checked', false);
				changeAnswerType();
			}
			for (var j = 0; j < questionSectionList[0].questionList[i].option.length; j++) {
				if ($("#totalOptions").val() == j) {
					addAnswers();
				}
				CKEDITOR.instances['answer' + (j + 1)]
						.setData(questionSectionList[0].questionList[i].option[j].optionName);
				if (questionSectionList[0].questionList[i].option[j].answerStatus == 1) {
					$("#answerStatus" + (j + 1)).iCheck('check');
				}
			}
			if (questionSectionList[0].questionList[i].isFormula == 1) {
				$("#isFormula").val(1);
				$("#isFormula").prop('checked', true);
				CKEDITOR.instances['mathformula']
						.setData(questionSectionList[0].questionList[i].mathFormula);
				$("#mathformuladiv").show();
			}
			$("#answerexplanation").val(
					questionSectionList[0].questionList[i].explanation);
		}
	}
}





/** Videos Method */
/**
 * @summary This is used adding options in multi type question.
 * @returns no
 */
function addOptions() {
	var optionNo = $("#totalOptions").val();
	var optn = 65 + parseInt(optionNo);
	var optionOrder = "&#" + optn + ";";
	var val = $("#selectedQuestionType").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		if (val == "1") {
			str = '<div id="option'
					+ optionNo
					+ '">'
					+ '<div class="col-xs-12 formBody">'
					+ '<div class="">'
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
					+ '" onclick="videoquestionKeyValidate();">'
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
					+ '" onclick="deleteVideoOption(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'Error">'+messages['msg.empty']+'</label></div></div>'
			'</div>';
		} else {
			str = '<div id="option'
					+ optionNo
					+ '">'
					+ '<div class="col-xs-12 formBody">'
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
					+ '" onclick="videoquestionKeyValidate();">'
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
					+ '" onclick="deleteVideoOption(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'Error">'+messages['msg.empty']+'</label></div></div>'
			'</div>';
		}
		$("#ansType").append(str);
		$("#totalOptions").val(optionNo);
		CKEDITOR.inline('answer' + optionNo, {
			height : 40
		}).on('key', function(e) {
			videoquestionKeyValidate();
		});
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
				.iCheck({
					checkboxClass : 'icheckbox_square-green',
					radioClass : 'iradio_square-green'
				});
	}
	if (optionNo <= 2) {
		$("#deleteOption1").css("visibility", "hidden");
		$("#deleteOption2").css("visibility", "hidden");
	}else{
		$("#deleteOption1").css("visibility", "visible");
		$("#deleteOption2").css("visibility", "visible");
	}
}

function changeAnswerTypeForVideo() {
	var val = $("#selectedQuestionType").val();
	if (val == '1') {
		$(".questionType").attr('type', 'checkbox');
		$(".questionType").prop('checked', false);
	}
	if (val == '2') {
		$(".questionType").attr('type', 'radio');
		$(".questionType").prop('checked', false);
	}
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green'
	});
}

function videoquestionValidate(isQuestionUpdate) {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptions").val();
	var checkboxcondition = true;
	var questionContent = CKEDITOR.instances['question'].getData();
	var questionContentData = ConvertHtmlToPlainTestForVideo(questionContent);
	if (questionContentData == "") {
		$("#questionEditor").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#questionError").fadeIn();
		return false;
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j].getData();
		var optionContentData = ConvertHtmlToPlainTestForVideo(optionContent);
		if (optionContentData == "") {
			$("#answer" + j + "div").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#answer" + j + "Error").fadeIn();
			return false;
		}
		checkboxcondition = checkboxcondition
				&& ($("#answerStatus" + j)[0].checked == false);

	}
	if (checkboxcondition) {
		$("#selectError").fadeIn();
		return false;
	}
	if (timeList.length>0 && isQuestionUpdate == null) {
		if($.inArray(parseInt($("#time").val()), timeList)!=-1){			
			$("#contentErrorAlert p").text(messages['msg.questionsaverrorforvideo'].replace('#currenttime',getTimeInFormat($("#time").val())));
		   	$("#contentErrorAlert").modal('show');
			return false;
		}
	}
	return true;
}

function saveVideoQuestionInJson() {
	if (videoquestionValidate()) {
		createVideoQuestionJson();
		showAddedQuestion();
		if($("#mode").val()==0){
			backToVideo();
		}else{
			backToPDF();
		}
	}
}

function createVideoQuestionJson() {
	var optionsList = [];
	var questionContent = CKEDITOR.instances['question'].getData();
	var question = {
		"questionId" : dynamicId,
		"questionName" : questionContent,
		"questionType" : parseInt($("#selectedQuestionType").val()),
		"time" : $("#time").val(),
		"contentTypeId" : $("#contentId").val(),
		"option" : optionsList
	};
	var totalOption = $("#totalOptions").val();
	for (var op = 1; op <= totalOption; op++) {
		var optionContent = CKEDITOR.instances['answer' + op].getData();
		var optionStatus = 0;
		if ($("#answerStatus" + op)[0].checked) {
			optionStatus = 1;
		}
		var options = {
			"optionId" : dynamicId + "" + op,
			"optionName" : optionContent,
			"optionOrder" : op,
			"answerStatus" : optionStatus
		};
		optionsList.push(options);
	}
	questionList.push(question);
	dynamicId++;
	timeList.push(parseInt($("#time").val()));
	// console.log(JSON.stringify(timeList));
	return true;
}

function videoquestionKeyValidate() {
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
}

function ConvertHtmlToPlainTestForVideo(strHtmlText) {
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

function videoQuestionDataSubmit() {
	if (questionList.length < 1) {
		$("#contentErrorAlert p").text(messages['msg.atleastonequestionisrequired']);
		$("#contentErrorAlert").modal('show');
	} else {
		var data = encodeURIComponent(JSON.stringify(questionList));
		$("#overlay").show();
		$.ajax({
			url : "videoQuestionDataSubmit",
			type : "POST",
			data : "questionList=" + data,
			success : function(str) {
				$("#overlay").hide();
				window.location.href = "listuploadcontent";
			},
			error : function(){
				$("#overlay").hide();
			}
		
		});
	}
}

function clearVideoQuestionData() {
	CKEDITOR.instances['question'].setData('');
}

function showAddedQuestion() {
	var totalOptions = $("#totalOptions").val();
	var str = '<div class="showquestion" id="showQuestion'
			+ (dynamicId - 1)
			+ '"><div class="form-group"><label>';
			if($("#mode").val()==0){
				str+=messages['lbl.questiontime']+': &nbsp;</label><span>'+messages['lbl.questionat'].replace('#time',getTimeInFormat($("#time").val()));
			}else{
				str+=messages['lbl.questionpage']+': &nbsp;</label><span>'+messages['lbl.questionatpage'].replace('#time',$("#time").val());
			}
			str+= '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '<span><a href="#" onclick="editaddedquestion('
			+ (dynamicId - 1)
			+ ')"><font color="#00A65A"><i class="fa fa-edit"></i></font></a>&nbsp;&nbsp;<a href="#" onclick="deleteaddedquestion('
			+ (dynamicId - 1)
			+ ')"><font color="#00A65A"><i class="fa fa-trash"></i></font></a> </br>'
			+ '<label>'+messages['lbl.questiontype']+': &nbsp;</label><span>'
			+ $("#selectedQuestionType  option:selected").text()
			+ '</span></br>'
			+ '<div class="input-group" style="float: left"><label>'+messages['lbl.question']+': &nbsp;</label></div><div style="padding-left:40px"><span>'
			+ CKEDITOR.instances['question'].getData() + '</span></div>'
			+ '<label>'+messages['lbl.answers']+'</label></br>';
	for (var op = 1; op <= totalOptions; op++) {
		var optn = 65 + parseInt(op) - 1;
		var optionOrder = "&#" + optn + ";";
		var selectedCss = "";
		if ($("#answerStatus" + op)[0].checked) {
			selectedCss = 'style="color: green"';
		}
		str += '<div class="input-group" style="float: left"><span class="badge bagde-style">'
				+ optionOrder
				+ '</span></div><div style="padding-left:40px"><span '
				+ selectedCss
				+ '>'
				+ CKEDITOR.instances['answer' + op].getData() + '</span></div>';
	}
	str += "</div>";
	$("#videoQuestionAdd").append(str);
}

function getTimeInFormat(curTime) {
	var time = "00:00";
	var currTime = parseInt(curTime);
	if (currTime < 10) {
		time = "00:0" + currTime;
	} else if (currTime > 9 && currTime < 60) {
		time = "00:" + currTime;
	} else {
		var curr = "00";
		var perNumb = parseInt(currTime % 60);
		if (perNumb < 10) {
			curr = "0" + perNumb;
		} else {
			curr = perNumb;
		}
		var curr1 = "00";
		var perNumb1 = parseInt(currTime / 60);
		if (perNumb1 < 10) {
			curr1 = "0" + perNumb1;
		} else {
			curr1 = perNumb1;
		}
		time = curr1 + ":" + curr;
	}
	return time;
}

function editaddedquestion(questionId) {
	var mod=parseInt($("#mode").val());
	if(mod==0)
		vid.pause();
	$("#videoQuestion").hide();
	$("#videoQuestionAdd").hide();
	var currTime = "00:00";
	for (var i = 0; i < questionList.length; i++) {
		if (questionList[i].questionId == questionId) {
			$("#selectedQuestionType").val(questionList[i].questionType);
			CKEDITOR.instances['question']
					.setData(questionList[i].questionName);
			changeVideoAnswerType();
			for (var j = 0; j < questionList[i].option.length; j++) {
				if ($("#totalOptions").val() == j) {
					addOptions();
				}
				CKEDITOR.instances['answer' + (j + 1)]
						.setData(questionList[i].option[j].optionName);
				if (questionList[i].option[j].answerStatus == 1) {
					$("#answerStatus" + (j + 1)).iCheck('check');
				}
			}
			if(mod==0){
				currTime = getTimeInFormat(questionList[i].time);
				$("#time").val(parseInt(questionList[i].time));
			}else{
				currTime = questionList[i].time;
				$("#currtime").val(parseInt(questionList[i].time));
			}
		}
	}
	
	$("#play").attr("disabled", true);
	if(mod==0){
		$("#savequestionbutton").text(messages['lbl.saveat'].replace('#time',currTime));
		$("#myVideo").removeAttr("controls");
	}else{
		$("#savequestionbutton").text(messages['lbl.saveatpage'].replace('#time',currTime));
	}
		
		
	$("#videoQuestion").show();
	$("#savequestionbutton").removeAttr("onClick");
	$("#savequestionbutton").attr("onClick","updateVideoQuestionInJson(" + questionId + ")");
}

function deleteaddedquestion(questionId) {
	$("#showQuestion" + questionId).remove();
	for (var q = 0; q < questionList.length; q++) {
		if (questionList[q].questionId == questionId) {
			for (var i = 0; i < timeList.length; i++) {
				if (parseInt(questionList[q].time) == timeList[i]) {
					timeList.splice(i, 1);
					break;
				}
			}
			questionList.splice(q, 1);
			break;
		}
	}
	console.log(JSON.stringify(questionList));
	console.log(JSON.stringify(timeList));
}

function updateVideoQuestionInJson(questionId) {
	if (videoquestionValidate('questionUpdate')) {
		updateVideoQuestionJson(questionId);
		showUpdatedQuestion(questionId);
		if($("#mode").val()==0){
			backToVideo();
		}else{
			backToPDF();
		}
	}
}

function updateVideoQuestionJson(questionId) {
	var tim=$("#time").val();
	if(parseInt($("#mode").val())==0){
		tim=$("#time").val();
	}else{
		tim=$("#currtime").val();
	}
	for (var q = 0; q < questionList.length; q++) {
		if (questionList[q].questionId == questionId) {
			var optionsList = [];
			var questionContent = CKEDITOR.instances['question'].getData();
			var question = {
				"questionId" : questionId,
				"questionName" : questionContent,
				"questionType" : parseInt($("#selectedQuestionType").val()),
				"time" : tim,
				"contentTypeId" : $("#contentId").val(),
				"option" : optionsList
			};
			var totalOption = $("#totalOptions").val();
			for (var op = 1; op <= totalOption; op++) {
				var optionContent = CKEDITOR.instances['answer' + op].getData();
				var optionStatus = 0;
				if ($("#answerStatus" + op)[0].checked) {
					optionStatus = 1;
				}
				var options = {
					"optionId" : parseInt(questionId + "" + op),
					"optionName" : optionContent,
					"optionOrder" : op,
					"answerStatus" : optionStatus
				};
				optionsList.push(options);
			}
			questionList[q] = question;
			break;
		}
	}
	return true;
}

function showUpdatedQuestion(questionId) {
	var totalOptions = $("#totalOptions").val();
	$("#showQuestion" + questionId).empty();
	var str = '<div class="form-group"><label>';
			if($("#mode").val()==0){
				str+=messages['lbl.questiontime']+': &nbsp;</label><span>'+messages['lbl.questionat'].replace('#time',getTimeInFormat($("#time").val()));
			}else{
				str+=messages['lbl.questionpage']+': &nbsp;</label><span>'+messages['lbl.questionatpage'].replace('#time',$("#time").val());
			}
			str+= '</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+ '<span><a href="#" onclick="editaddedquestion('
			+ (dynamicId - 1)
			+ ')"><font color="#00A65A"><i class="fa fa-edit"></i></font></a>&nbsp;&nbsp;<a href="#" onclick="deleteaddedquestion('
			+ (dynamicId - 1)
			+ ')"><font color="#00A65A"><i class="fa fa-trash"></i></font></a> </br>'
			+ '<label>'+messages['lbl.questiontype']+': &nbsp;</label><span>'
			+ $("#selectedQuestionType  option:selected").text()
			+ '</span></br>'
			+ '<div class="input-group" style="float: left"><label>'+messages['lbl.question']+': &nbsp;</label></div><div style="padding-left:40px"><span>'
			+ CKEDITOR.instances['question'].getData()
			+ '</span></div>'
			+ '<label>'+messages['lbl.answers']+'</label></br>';
	for (var op = 1; op <= totalOptions; op++) {
		var optn = 65 + parseInt(op) - 1;
		var optionOrder = "&#" + optn + ";";
		var selectedCss = "";
		if ($("#answerStatus" + op)[0].checked) {
			selectedCss = 'style="color: green"';
		}
		str += '<div class="input-group" style="float: left"><span class="badge bagde-style">'
				+ optionOrder
				+ '</span></div><div style="padding-left:40px"><span '
				+ selectedCss
				+ '>'
				+ CKEDITOR.instances['answer' + op].getData() + '</span></div>';
	}
	$("#showQuestion" + questionId).append(str);
}

function changeVideoAnswerType() {
	var val = parseInt($("#selectedQuestionType").val());
	if (val == SINGLE_CHOICE_TYPE) {
		$(".questionType").attr('type', 'radio');
		$(".questionType").prop('checked', false);
	}
	if (val == MULTIPLE_CHOICE_TYPE) {
		$(".questionType").attr('type', 'checkbox');
		$(".questionType").prop('checked', false);
	}
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green'
	});
}


function deleteVideoOption(object) {
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
				placeholder : "Answer No. " + (i - 1)
			});
			$("#deleteOption" + i).attr('id', "deleteOption" + (i - 1));
			$("#answer" + i + "Error").attr('id', "answer" + (i - 1) + "Error");
			CKEDITOR.instances['answer' + i].destroy();
			CKEDITOR.inline('answer' + (i - 1), {
				height : 40
			}).on('key', function(e) {
				videoquestionKeyValidate();
			});
		}
		totalOption--;
		$("#totalOptions").val(totalOption);
	}
	if (totalOption == 2) {
		$("#deleteOption1").css("visibility", "hidden");
		$("#deleteOption2").css("visibility", "hidden");
	}
}

function pageCall(sign,type){
	var currPage=$("#time").val();
	$("#pafContent").removeAttr("src");
	if(sign==0){
		currPage--;
		if($.trim(type)=='pdf'){
		$("#pafContent").attr("data",$("#contentUrl").val()+"/page_"+currPage+"."+type);
		    }
		else
			{
			$("#pafContent").attr("src",$("#contentUrl").val()+"/page_"+currPage+"."+type);
			}
	}else{
		currPage++;
		    if($.trim(type)=='pdf'){
			$("#pafContent").attr("data",$("#contentUrl").val()+"/page_"+currPage+"."+type);
			    }
			else
				{
				$("#pafContent").attr("src",$("#contentUrl").val()+"/page_"+currPage+"."+type);
				}
		$("#next").removeAttr("disabled");
	}
	if(currPage==parseInt($("#contentPages").val())){
		$("#next").attr("disabled","disabled");
	}else{
		$("#next").removeAttr("disabled");
	}
	if(currPage==1){
		$("#previous").attr("disabled","disabled");
	}else{
		$("#previous").removeAttr("disabled");
	}
	$("#pageTextCount").text(currPage);
	$("#time").val(currPage);
}

//Function for PPT
function slideCall(sign){
	var currPage=$("#time").val();
	$("#pafContent").removeAttr("src");
	showAudioAddedDiv(sign,currPage);
	if(sign==0){
		currPage--;
		$("#pafContent").attr("src",$("#contentUrl").val()+"/page_"+currPage+".png");
	}else{
		currPage++;
		$("#pafContent").attr("src",$("#contentUrl").val()+"/page_"+currPage+".png");
		/*if(questionList.length==currPage){
			$("#next").attr("disabled","disabled");
			$("#pafContent").attr("src",$("#contentUrl").val()+"/page_"+currPage+".png");
		}else{
			currPage++;
			$("#pafContent").attr("src",$("#contentUrl").val()+"/page_"+currPage+".png");
		}*/
		
	}
    var contentPages  = parseInt($.trim($("#contentPages").val()));
	if(questionList.length<currPage || contentPages == currPage){
		$("#reachtime").val(currPage);
		$("#next").attr("disabled","disabled");
	}else{
		$("#next").removeAttr("disabled");
	}
	if(currPage==1){
		$("#previous").attr("disabled","disabled");
	}else{
		$("#previous").removeAttr("disabled");
	}
	$("#pageTextCount").text(currPage);
	$("#time").val(currPage);
	
}

var uploadAudioFileSize=parseFloat(0);
var fileSizeArr = [];
function savePPTSlideAudio(id){
	var obj=new FormData();
	var flag=false;
	var _audioFileSize;
	if($("#answerStatus"+id+"1").is(":checked")){
		var inp = document.getElementById('uploadfile'+id); 
		if(inp.files.length>0){
			var extArray = ['mp3'];
			if (extArray.indexOf(inp.files.item(0).name.split('.').pop()) > -1) {
				var size = inp.files.item(0).size;
				uploadAudioFileSize = uploadAudioFileSize+parseFloat((size / (1024*1024)).toFixed(2));
   			 	if(uploadAudioFileSize>parseInt($("#availableSpace").val())){
   			 		uploadAudioFileSize = uploadAudioFileSize-parseFloat((size / (1024*1024)).toFixed(2));
   			 		$("#fileUploadSpaceError"+id).fadeIn();
   			 	}else{
   					obj.append("file", inp.files.item(0));
   					_audioFileSize = parseFloat((size / (1024*1024)).toFixed(2));
   					obj.append("fileSize", parseFloat((size / (1024*1024)).toFixed(2)));
   					flag=true;
   			 	}
			} else {
				$("#fileUploadExtError"+id).fadeIn();
			}
		}else{
			$("#fileUploadError"+id).fadeIn();
		}
	}else if($("#answerStatus"+id+"3").is(":checked")){
		if($.trim($("#pptHoldNumber"+id).val())!=""){
			obj.append("fileHoldTime", $("#pptHoldNumber"+id).val());
			obj.append("fileSize", 0);
			flag=true;
		}else{
			$("#requiredError"+id).fadeIn();
		}
	}
	if(flag){
		$("#savequestionbutton"+id).hide();
		$("#editButton"+id).show();
		obj.append("pageNum", id);
		obj.append("contentId", $("#contentId").val());
		questionList.push(obj);
		fileSizeArr.push(_audioFileSize);
		enabelDisableFields(id,false);
		if(id==$("#contentPages").val()){
			$("#savebtn").removeAttr("disabled");
		}else{
			$("#addVoice"+id).hide();
			slideCall(id);
			id++;
			var str='<div id="addVoice'+id+'"><div class="form-group"><div class="input-group" style="float: left">'
					+'<input type="radio" checked="checked" class="flat-red questionType"  name="answerStatus'+id+'" id="answerStatus'+id+'1"/>&nbsp;&nbsp;</div>'
					+'<div style="padding:2px;float: left;"><label>Upload Voice File (Only mp3 allow)</label></div>'
					+'<div style="padding:2px;float: right;"><a href="#" id="editButton'+id+'" style="display: none" onclick="editaddedAudio('+id+')"><font color="#00A65A"><i class="fa fa-edit"></i></font></a>&nbsp;&nbsp;</div>'
					+'<div style="padding:2px; clear: both;"><input type="file" id="uploadfile'+id+'" name="uploadfile'+id+'" onclick="hideRequiredError()"></div>'
					+'<div class="col-xs-12" ><label class="requireFld" id="fileUploadError'+id+'">'+messages['msg.pleaseuploadfile']+'</label>'
					+'<label class="requireFld" id="fileUploadSpaceError'+id+'">'+messages['msg.pleaseuploadspacefile']+'</label>'
					+'<label class="requireFld" id="fileUploadExtError'+id+'">'+messages['msg.pleaseuploadextfile']+'</label></div></div>'
					+'<div class="form-group" style="display:none">OR</div><div class="form-group" style="display:none"><div class="input-group" style="float: left">'
					+'<input type="radio" class="flat-red questionType"  name="answerStatus'+id+'" id="answerStatus'+id+'2"/>&nbsp;&nbsp;</div>'
					+'<div style="padding:2px"><label>Record Voice</label></div><input type="file" id="recordfile'+id+'" name="recordfile'+id+'" disabled="disabled"></div>'
					+'<div class="form-group">OR</div><div class="form-group">	<div class="input-group" style="float: left">'
					+'<input type="radio" class="flat-red questionType"  name="answerStatus'+id+'" id="answerStatus'+id+'3"/>&nbsp;&nbsp;</div>'
					+'<div style="padding:2px"><label>Provide time to hold slide. (In Second)</label></div>'
					+'<input type="text" class="form-control eOnlyNum" id="pptHoldNumber'+id+'" name="pptHoldNumber'+id+'" maxlength="3">'
					+'<div class="col-xs-12" ><label class="requireFld" id="requiredError'+id+'">'+messages['msg.empty']+'</label></div></div>'
					+'<div class="col-xs-12" style="min-height: 10px"></div><div class="col-xs-12 formBody"><div class="col-xs-12">'
					+'<button type="button" class="margin-left btn btn-flat btn-success button-width-large" id="savequestionbutton'+id+'" onclick="savePPTSlideAudio('+id+')">'+messages['lbl.saveatslide'].replace("#slidenum",id)+'</button>'
					+'</div></div><div class="col-xs-12 formBody" id="editButtons'+id+'" style="display: none;"><div class="col-xs-6">'
					+'<button type="button" class="btn btn-default btn-flat button-width-large" onclick="backToPPT('+id+');"><span>'+messages['lbl.cancel']+'</span></button></div>'
					+'<div class="col-xs-6"><button type="button" class="margin-left btn btn-flat btn-success button-width-large" id="savequestionbutton" onclick="updatePPTAudio('+id+');"><span>'+messages['lbl.update']+'</span></button></div>'
					+'</div></div>';
			$("#pptVoiceDiv").append(str);
			$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
			.iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green'
			});
		}
	}
}

function showAudioAddedDiv(sign,id){
	$("#addVoice"+id).hide();
	if(sign==0){
		$("#addVoice"+(parseInt(id)-1)).show();
	}else{
		$("#addVoice"+(parseInt(id)+1)).show();
		if(questionList.length==parseInt($("#contentPages").val())){
			$("#savebtn").removeAttr("disabled");
		}
	}
}

function pptAudioDataSubmit(){
	$("#savebtn").attr("disabled","disabled");
	$("#overlay").show();
	var responseCompleted = 0; 
	for(i=0;i<questionList.length;i++){
		$.ajax({
            url : 'savePPTAudio',
            method : 'POST',
            data : questionList[i],
            processData : false,
            contentType : false,
            error: function(){
            	if((i+1)==questionList.length){
            		$("#overlay").hide();
            	}
            },
            success : function(response, status,xhr) {
            	responseCompleted++;
            	if(responseCompleted==questionList.length){
            		window.location.href = "listuploadcontent";
            	}
            }
        });
	}
}

function editaddedAudio(id){
	$("#next").attr("disabled","disabled");
	$("#previous").attr("disabled","disabled");
	$("#savebtn").attr("disabled","disabled");
	$("#editButton"+id).hide();
	$("#editButtons"+id).show();
	enabelDisableFields(id,true);
}

function backToPPT(id){
	$("#next").removeAttr("disabled");
	if(id!=1){
		$("#previous").removeAttr("disabled");
	}
	
	if(id == parseInt($("#contentPages").val())){
		$("#next").prop("disabled",true);
	}
	if(questionList.length==parseInt($("#contentPages").val())){
		$("#savebtn").removeAttr("disabled");
	}
	$("#editButton"+id).show();
	$("#editButtons"+id).hide();
	enabelDisableFields(id,false);
}

function updatePPTAudio(id){
	var obj=new FormData();
	var flag=false;
	var _audioFileSize;
	uploadAudioFileSize=uploadAudioFileSize-fileSizeArr[id-1];
	if($("#answerStatus"+id+"1").is(":checked")){
		var inp = document.getElementById('uploadfile'+id); 
		if(inp.files.length>0){
			var extArray = ['mp3'];
			if (extArray.indexOf(inp.files.item(0).name.split('.').pop()) > -1) {
				var size = inp.files.item(0).size;
				uploadAudioFileSize = uploadAudioFileSize+parseFloat((size / (1024*1024)).toFixed(2));
   			 	if(uploadAudioFileSize>parseInt($("#availableSpace").val())){
   			 		$("#fileUploadSpaceError"+id).fadeIn();
   			 	}else{
   					obj.append("file", inp.files.item(0));
   					_audioFileSize = parseFloat((size / (1024*1024)).toFixed(2));
   					obj.append("fileSize", parseFloat((size / (1024*1024)).toFixed(2)));
   					flag=true;
   			 	} 
			} else {
				$("#fileUploadExtError"+id).fadeIn();
			}
		}else{
			$("#fileUploadError"+id).fadeIn();
		}
	}else if($("#answerStatus"+id+"3").is(":checked")){
		if($.trim($("#pptHoldNumber"+id).val())!=""){
			obj.append("fileHoldTime", $("#pptHoldNumber"+id).val());
			flag=true;
		}else{
			$("#requiredError"+id).fadeIn();
		}
	}
	if(flag){
		obj.append("pageNum", id);
		obj.append("contentId", $("#contentId").val());
		questionList[id-1]=obj;
		fileSizeArr[id-1]=_audioFileSize;
		backToPPT(id);
	}
}

function enabelDisableFields(id,flag){
	if(flag){
		$("#answerStatus"+id+"1").removeAttr("disabled");
		$("#answerStatus"+id+"3").removeAttr("disabled");
		$("#uploadfile"+id).removeAttr("disabled");
		$("#pptHoldNumber"+id).removeAttr("disabled");
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
		.iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green'
		});
	}else{
		$("#answerStatus"+id+"1").attr("disabled",true);
		$("#answerStatus"+id+"3").attr("disabled",true);
		$("#uploadfile"+id).attr("disabled",true);
		$("#pptHoldNumber"+id).attr("disabled",true);
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
		.iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green'
		});
	}
}

function hideRequiredError(){
	$(".requireFld").fadeOut();
}