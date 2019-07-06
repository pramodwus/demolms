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
			+ '<div class="col-xs-12 question"><label id="questiontitle"><sup><font color="red" size="3px">*</font></sup>Question : </label>'
			+ '<div id="questionEditor"><textarea name="question" id="question" class="form-control myTextEditor" placeholder="Enter your question here."></textarea></div>'
			+ '<label class="requireFld" id="questionError">This Field is required.</label>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody"><label><sup><font color="red" size="3px">*</font></sup>Answers (click check box for right answer)</label></div>'
			+ '<div class="col-xs-12 "><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="selectError">Please click for right answer</label></div></div>'
			+ '<div id="ansType">'
			+ '<!-- Ans Div -->'
			+ '<div id="option1">'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="">'
			+ '<div class="input-group">'
			+ '<span class="input-group-addon" id="answerOrder1"><span class="badge bagde-style">A</span></span>'
			+ '<span class="input-group-addon">'
			+ '<input type="checkbox" class="flat-red questionType"  name="answerStatus" id="answerStatus1" value="1" onclick="questionKeyValidate();">'
			+ '</span>'
			+ '<div id="answer1div" class="text_editor_margin"><textarea name="answer1" id="answer1"  class="form-control myTextEditor" placeholder="Answer No. 1"></textarea></div>'
			+ '<span class="input-group-addon" id="deleteOption1" onclick="deleteOption(this)" style="visibility:hidden"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle" title="Remove Answer"></i></span>'
			+ '</div><!-- /input-group -->'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer1Error">This Field is required.</label></div></div>'
			+ '</div>'
			+

			'<div id="option2">'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="">'
			+ '<div class="input-group">'
			+ '<span class="input-group-addon" id="answerOrder2"><span class="badge bagde-style">B</span></span>'
			+ '<span class="input-group-addon">'
			+ '<input type="checkbox" class="flat-red questionType" name="answerStatus" id="answerStatus2" value="2" onclick="questionKeyValidate();">'
			+ '</span>'
			+ '<div id="answer2div" class="text_editor_margin"><textarea name="answer2" id="answer2"  class="form-control myTextEditor" placeholder="Answer No. 2"></textarea></div>'
			+ '<span class="input-group-addon" id="deleteOption2" onclick="deleteOption(this)" style="visibility:hidden"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle" title="Remove Answer"></i></span>'
			+ '</div><!-- /input-group -->'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer2Error">This Field is required.</label></div></div>'
			+ '</div>'
			+

			'<input type="hidden" id="totalOptions" name="totalOptions" value="2">'
			+ '</div><!-- /.ansType -->'
			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-11">'
			+ '<button type="button" class="margin-left btn btn-flat btn-success button-width-large" onclick="addAnswers();"><span><i class="glyphicon glyphicon-plus-sign"></i> Add Answer</span></button>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-2 margin-left"><label class="pull-left">Multiple Answer</label></div>'
			+ '<div class="col-xs-3">'
			+ '<input type="hidden" value="1" id="testType" name="testType">'
			+ '<input type="checkbox" value="On" id="ansTypeButton" style="background-color: #DD2163;color: white" onclick="changeAnswerType();" checked>'
			+ '</div>'
			+ '<div class="col-xs-3"><label class="pull-left">Mathematical Formula</label></div>'
			+ '<div class="col-xs-2"><input type="checkbox" value="0" id="isFormula" style="background-color: #DD2163; color: white" onclick="changeMathFormula();"></div>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody questionMark hide" id="questionmarkdiv">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-11"><label class="margin-left"><input type="text" style="width: 30px" name="questionmark" id="questionmark" onkeyup="questionKeyValidate();"> <sup><font color="red" size="3px">&nbsp;*</font></sup>Mark </label><span> (Enter the Mark for this question.)</span></div>'
			+ '<div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="questionmarkError">This Field is required.</label></div>'
			+ '<div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="questionmarkError1">Please Enter only numeric value.</label></div>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody">'
			+ '<div class="col-xs-1"></div>'
			+ '<div class="col-xs-11">'
			+ '<button type="button" class="btn btn-danger btn-flat button-width-large margin-left" onclick="clearQuestion();"><span><i class="fa fa-trash-o"> Clear Question</i></span></button>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody"></div>'
			+ '<div class="col-xs-12 formBody form-group" id="mathformuladiv" style="display:none">'
			+ '<label>Math Formula:</label>'
			+ '<div id="mathformulaEditor">'
			+ '<textarea name="mathformula" id="mathformula" class="form-control myTextEditor"></textarea>'
			+ '</div>'
			+ '<label class="requireFld" id="mathformulaerror">This Field is required.</label>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody">'
			+ '<label>Answer Explanation:</label>'
			+ '<textarea class="form-control textAreaControl"  name="answerexplanation" id="answerexplanation" placeholder="Answer Explanation" onkeyup="questionKeyValidate();"></textarea>'
			+ '<label class="requireFld" id="answerexplanationerror" >This Field is required.</label>'
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
	          checkboxClass: 'icheckbox_square-green',
	          radioClass: 'iradio_square-green'
	     });
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
		$("#deleteOption1").css("visibility","visible");
		$("#deleteOption2").css("visibility","visible");
		if (val == "On") {
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
					+ '" onclick="questionKeyValidate();">'
					+ '</span>'
					+ '<div id="answer'
					+ optionNo
					+ 'div" class="text_editor_margin"><textarea name="answer'
					+ optionNo
					+ '" id="answer'
					+ optionNo
					+ '"  placeholder="Answer No. '
					+ optionNo
					+ '" class="form-control myTextEditor"></textarea></div>'
					+ '<span class="input-group-addon" id="deleteOption'
					+ optionNo
					+ '" onclick="deleteOption(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="Remove Answer"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'Error">This Field is required.</label></div></div>'
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
					+ '" onclick="questionKeyValidate();">'
					+ '</span>'
					+ '<div id="answer'
					+ optionNo
					+ 'div" class="text_editor_margin"><textarea name="answer'
					+ optionNo
					+ '" id="answer'
					+ optionNo
					+ '"  placeholder="Answer No. '
					+ optionNo
					+ '" class="form-control myTextEditor"></textarea></div>'
					+ '<span class="input-group-addon" id="deleteOption'
					+ optionNo
					+ '" onclick="deleteOption(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="Remove Answer"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12" ><div class="col-xs-1"></div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'Error">This Field is required.</label></div></div>'
			'</div>';
		}
		$("#ansType").append(str);
		$("#totalOptions").val(optionNo);
		CKEDITOR.inline('answer' + optionNo, {
			height : 40
		}).on('key', function(e) {
			questionKeyValidate();
		});
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		       checkboxClass: 'icheckbox_square-green',
		          radioClass: 'iradio_square-green'
		     });
	}
}

/**
 * @summary This is used change the single to multi choice and multi to single choice question.
 * @returns no
 */
function changeAnswerType() {
	var val = $("#ansTypeButton").val();
	if (val == 'On') {
		$(".questionType").attr('type', 'radio');
		$(".questionType").prop('checked', false);
		$("#ansTypeButton").val('Off');
		$("#testType").val('2');
	}
	if (val == 'Off') {
		$(".questionType").attr('type', 'checkbox');
		$(".questionType").prop('checked', false);
		$("#ansTypeButton").val('On');
		$("#testType").val('1');
	}
	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
	       checkboxClass: 'icheckbox_square-green',
	          radioClass: 'iradio_square-green'
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
	if(isFormula=='1'){
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
	return true;
}

/**
 * @summary This is used to fadeout the validation error  on create question page on key event.
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
	
	if(mathformula!=""){
		$("#mathformulaEditor").css("border-color", "#7ac17d");
		$("#mathformulaerror").fadeOut();
	}
	if ($("#answerexplanation").val().length > 0) {
		$("#answerexplanation").css("border-color", "#7ac17d");
		$("#answerexplanationerror").fadeOut();
	}
}

/**
 * @summary This is used to show and hide the text area field for math formula.
 * @returns no
 */
function changeMathFormula(){
	var isFormula = $("#isFormula").val().trim();
	if(isFormula=='0'){
		$("#isFormula").val('1');
		$("#mathformuladiv").show();
	}
	else
		{
		$("#isFormula").val('0');
		$("#mathformuladiv").hide();
		}
}

/**
 * @summary This is used for show the pop up for clear question data.
 * @returns no
 */
function clearQuestion() {
	$("#clearquestionAlert p").text("Are you sure to clear this Question?");
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
	var totalOptions=$("#totalOptions").val();
	CKEDITOR.instances['question'].setData('');
	$('.flat-red').iCheck('uncheck');
	for(var op=1;op<=totalOptions;op++){
		CKEDITOR.instances['answer'+op].setData('');
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
			$("#answerOrder" + i).attr('id', "answerOrder" + (i - 1));
			$("#answerOrder" + (i - 1)).html("<span class='badge bagde-style'>&#" + (64 + i - 1) + ";</span>");
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
				questionKeyValidate();
			});
		}
		totalOption--;
		$("#totalOptions").val(totalOption);
	}
	if(totalOption == 2){
		$("#deleteOption1").css("visibility","hidden");
		$("#deleteOption2").css("visibility","hidden");
	}
}

/**
 * @summary This is used for confirm the user for submit the details of multiple choice type question.
 * @returns no.
 */
function saveMultiQuesPopUp(){
	if(questionValidate()){
	$("#clearquestionAlert p").text(
		"Are you sure for submit the details?");
	$("#dId").attr('onclick', 'saveMultiTypeQuestionInJson()');
	$("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary function is used for save the new data of multi choice question in json.
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
	var quesData=[];
	var optionsList = [];
	var questionContent = CKEDITOR.instances['question'].getData();
	var question = {
		"questionId" : dynamicId,
		"questionName" : questionContent,
		"questionType" : parseInt($("#testType").val()),
		"option" : optionsList,
		"isFormula" : $("#isFormula").val(),
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
	/*console.log(JSON.stringify(questionSectionList));*/
	return quesData;
}

/** 
 * @summary This is used for showing the pop up for confirm the user for update the multiple choice type question.
 * @param questionId
 * @returns no
 */
function updateMultiTypeQuestionPopUp(questionId){
	if(questionValidate()){
       $("#clearquestionAlert p").text(
	         "Are you sure for update this question?");
       $("#dId").attr('onclick', 'updateMultiTypeQuestion('+questionId+')');
       $("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary This is used for performing opertion for update the multi choice type question.
 * @param questionId
 * @retruns no
 */
function updateMultiTypeQuestion(questionId){
    if(questionValidate()){
	var quesData = updateQuestionInJson(questionId);
	submitQuestionDetails();
    }
}

/**
 * @summary This is used for updating the json of this question.
 * @param questionId
 * @returns {Array}
 */
function updateQuestionInJson(questionId){
    	for(var q=0;q<questionSectionList[0].questionList.length;q++){
    		if(questionSectionList[0].questionList[q].questionId==questionId){
    			var optionsList = [];
    			var questionContent = CKEDITOR.instances['question'].getData();
    			var question = {
    				"questionId" : questionId,
    				"questionName" : questionContent,
    				"questionNo" : (q+1),
    				"questionType" : parseInt($("#testType").val()),
    				"option" : optionsList,
    				"isFormula" : $("#isFormula").val(),
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
    					"optionId" : parseInt(questionId +""+ op),
    					"optionName" : optionContent,
    					"optionOrder" : op,
    					"answerStatus" : optionStatus
    				};
    				optionsList.push(options);
    			}
          	questionSectionList[0].questionList[q]=question;
    		 }
    	   /*console.log(JSON.stringify(questionSectionList));*/
    	}
    	var quesData = [];
    	quesData.push(question.questionId);
    	quesData.push(question.questionName);
    	return quesData;
}

/**
 * @summary This is used for filling multi type question data from question json object.
 * @returns no
 */
function fillQuestionData(questionId){
    	for(var i=0;i<questionSectionList[0].questionList.length;i++){ 
    		if(questionSectionList[0].questionList[i].questionId==questionId){
    			CKEDITOR.instances['question'].setData(questionSectionList[0].questionList[i].questionName);
    			if(questionSectionList[0].questionList[i].questionType==2){
        			  $("#ansTypeButton").prop('checked',false);
        			  changeAnswerType();
        			  }
    			for(var j=0;j<questionSectionList[0].questionList[i].option.length;j++){
    			      if($("#totalOptions").val()==j){
    					addAnswers();
    				       }
    				CKEDITOR.instances['answer'+(j+1)].setData(questionSectionList[0].questionList[i].option[j].optionName);
    				if(questionSectionList[0].questionList[i].option[j].answerStatus==1){
    					$("#answerStatus"+(j+1)).iCheck('check');
    				    }
    			}
    			if(questionSectionList[0].questionList[i].isFormula==1){
    				$("#isFormula").val(1);
    				$("#isFormula").prop('checked',true);
    				CKEDITOR.instances['mathformula'].setData(questionSectionList[0].questionList[i].mathFormula);
    				$("#mathformuladiv").show();
    			}
    			$("#answerexplanation").val(questionSectionList[0].questionList[i].explanation);
    		}
    	}
   }