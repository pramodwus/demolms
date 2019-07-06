/**
 * @summary This is used for redirect on question page based on question type.
 * 
 * @param sectionId
 * @param questionId
 * @returns no.
 */
function editQuestionData(sectionId, questionId) {
	var position = findSectionIdInList(sectionId);
	for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
		if (questionSectionList[position].questionList[q].questionId == questionId) {
			switch(questionSectionList[position].questionList[q].questionType){
			case 1:
			    addNextQuestion(questionSectionList[position].questionList[q].questionNo);
			    $("#sectionIdForAddedQues").val(sectionId);
			    $(window).scrollTop(0);
			    $("#createsectiontab").hide();
			    $("#questionPageDiv").show();
			    fillQuestionData(position, questionId);
			    break;
			case 2:
				addNextQuestion(questionSectionList[position].questionList[q].questionNo);
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillQuestionData(position, questionId);
				break;
			case 3:
				addSortListTypeQuestion();
				$("#sectionIdForAddedQues").val(sectionId);
				$(window).scrollTop(0);
				$("#createsectiontab").hide();
				$("#questionPageDiv").show();
				fillSortListTypeQuestionData(position, questionId);
				break;
			        }
		}
	}
}

    /**
	 * @summary This is used for filling the question data in creating question tab for
	 * updating.
	 * 
	 * @param id
	 * @ returns no.
	 */
function fillQuestionData(position, questionId) {
	for (var i = 0; i < questionSectionList[position].questionList.length; i++) {
		if (questionSectionList[position].questionList[i].questionId == questionId) {
			CKEDITOR.instances['question'].setData(questionSectionList[position].questionList[i].questionName);
			$("#questionmark").val(
					questionSectionList[position].questionList[i].questionMark);
			if (questionSectionList[position].questionList[i].questionType == 2) {
				$("#ansTypeButton").prop('checked', false);
				changeAnswerType();
			}
			for (var j = 0; j < questionSectionList[position].questionList[i].option.length; j++) {
				if ($("#totalOptions").val() == j) {
					addAnswers();
				}
				CKEDITOR.instances['answer' + (j + 1)].setData(questionSectionList[position].questionList[i].option[j].optionName);
				if (questionSectionList[position].questionList[i].option[j].answerStatus == 1) {
					$("#answerStatus"+ (j + 1)).iCheck('check');
				}
			}
			if (questionSectionList[position].questionList[i].isFormula == 1) {
				$("#isFormula").val(1);
				$("#isFormula").prop('checked', true);
				$("#mathformula")
						.html(
								questionSectionList[position].questionList[i].mathFormula);
				$("#mathformuladiv").show();
			}
			$("#answerexplanation").val(
					questionSectionList[position].questionList[i].explanation);
			$("#submitQuestion").text('Update');
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
	 * @summary This is used for when any click event is fired on element by
	 *          user which has sectionDelete class.
	 * 
	 * @return no.
	 */
$(document).on('click', '.sectionDelete', function() {
	$("#clearquestionAlert p").text("Are you sure for delete this section?");
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
				+ (questionSectionList[position].questionList.length + 1)),
		"questionName" : questionContent,
		"questionNo" : questionSectionList[position].questionList.length + 1,
		"questionMark" : parseInt($("#questionmark").val()),
		"negMark" : (parseInt($("#questionmark").val()) * negMark) / 100,
		"questionType" : parseInt($("#testType").val()),
		"option" : optionsList,
		"isFormula" : $("#isFormula").val(),
		"isNew" :1, // defines that question is new created in test.
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
	questionSectionList[position].sectionScore = questionSectionList[position].sectionScore + question.questionMark;
	questionSectionList[position].questionList.push(question);
	return quesData;
}

/**
 * @summary This is used for updating the question json of multi choice type question.
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
					"isFormula" : $("#isFormula").val(),
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
				questionSectionList[position].sectionScore = questionSectionList[position].sectionScore - questionSectionList[position].questionList[q].questionMark + question.questionMark; 
				questionSectionList[position].questionList[q] = question;
				$("#totalSectionQuestion"+questionSectionList[position].sectionId).text(questionSectionList[position].questionList.length);
				$("#totalSectionMarks"+questionSectionList[position].sectionId).text(questionSectionList[position].sectionScore);
				var quesmarkObj = document.getElementById('questionMark'
						+ questionSectionList[position].sectionId + '##' + questionId);
				$(quesmarkObj).text(question.questionMark);
				var obj = document.getElementById('questionText'
						+ questionSectionList[position].sectionId + '##' + questionId);
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
		$("#testAlert p").text("Your Question details has been updated.");
		$("#testAlert").modal('show');
	}
}

   /**
	 * @summary Function is used for find out that section is already is created or not.
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
	 * based on section id and question id.
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
			questionSectionList[position].sectionScore = questionSectionList[position].sectionScore - questionSectionList[position].questionList[q].questionMark;
			questionSectionList[position].questionList.splice(q, 1);
			for (var j = q; j < questionSectionList[position].questionList.length; j++) {
				questionSectionList[position].questionList[j].questionNo = j + 1;
			}
			$(currentObject).closest("div.sectionQuestion").remove();
			$("#totalSectionQuestion"+sectionId).text(questionSectionList[position].questionList.length);
			$("#totalSectionMarks"+sectionId).text(questionSectionList[position].sectionScore);
			if(questionSectionList[position].questionList.length==0){
				$("#questionDivOfSection"+sectionId).next('div').remove();
			}
			totalQuestions--;
			break;
		}
	}
}
   

/**
 * @summary This is used of adding creating question div.
 * 
 * @param totalQuestions
 * @returns no.
 */
function addNextQuestion(totalQuestions) {
	$("#questiondiv").empty();
	var str = '<div class="col-xs-12">'
		+ '<div class="col-xs-12">'
		+ '<label>Multiple Answer :</label>'
		+ '<div class="form-group"><input type="hidden" value="1" id="testType" name="testType"><input type="checkbox" value="On" id="ansTypeButton" style="background-color: #DD2163;color: white" onclick="changeAnswerType();" checked></div>'
		+ '</div>'
		
			+ '<div class="col-xs-12 question"><label id="questiontitle"><sup><font color="red" size="3px">*</font></sup>Question : </label>'
			+ '<div id="questionEditor" class="text_editor_margin"><textarea name="question" id="question" class="form-control myTextEditor" placeholder="Enter your question here."></textarea></div>'
			+ '<label class="requireFld" id="questionError">This Field is required.</label>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody"></div>'
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
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" onclick="addAnswers();"><span><i class="glyphicon glyphicon-plus-sign"></i> Add Answer</span></button>'
			+ '&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-flat button-width-large" onclick="clearQuestion();"><span><i class="fa fa-trash-o"> Clear Question</i></span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<label>Answer Explanation:</label>'
			+ '<textarea class="form-control textAreaControl"  name="answerexplanation" id="answerexplanation" placeholder="Answer Explanation" onkeyup="questionKeyValidate();"></textarea>'
			+ '<label class="requireFld" id="answerexplanationerror" >This Field is required.</label>'
			+ '</div>' 
			
			+ '<div class="col-xs-12 form-group">'
			+ '<label>Mathematical Formula: </label>'
			+ '<div class="form-group"><input type="checkbox" value="0" id="isFormula" style="background-color: #DD2163; color: white" onclick="changeMathFormula();"></div>'
			+ '</div>'
			
			+ '<div class="col-xs-12 formBody form-group" id="mathformuladiv" style="display:none">'
			+ '<label>Math Formula:</label>'
			+ '<div id="mathformulaEditor">'
			+ '<textarea name="mathformula" id="mathformula" class="form-control myTextEditor"></textarea>'
			+ '</div>'
			+ '<label class="requireFld" id="mathformulaerror">This Field is required.</label>'
			+ '</div>'
			
			+ '<div class="col-xs-12 formBody questionMark" id="questionmarkdiv">'
			+ '<label><sup><font color="red" size="3px">&nbsp;*</font></sup><span>Enter the Mark for this question.</label>'
			+ '<input type="text" style="width: 50px" name="questionmark" class="form-control" id="questionmark" onkeyup="questionKeyValidate();" maxlength="2">'
			+ '<label class="requireFld" id="questionmarkError">This Field is required.</label><label class="requireFld" id="questionmarkError1">Please Enter only numeric value.</label>'
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
	       checkboxClass: 'icheckbox_square-green',
	          radioClass: 'iradio_square-green'
	     });
	
	CKEDITOR.inline('mathformula').on('change', function(e) {
		questionKeyValidate();
	});
	if (equalMarkTest == 1) {
		$("#questionmark").val(everyQuestionMark);
		$("#questionmarkdiv").hide();
	}
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
  * @summary This is used for open the popup for submit the questions.
  * @returns no.
  */
    function submitAllData() {
    	if (jsonValidation()) {
    		$("#clearquestionAlert p").text('Are you sure for submit the details?');
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
	$
			.ajax({
				type : 'POST',
				url : 'saveQuestions',
				data : 'sectionData=' + encodeURIComponent(data),
				success : function(status) {
					if (status) {
						// disable warning
				        $(window).off('beforeunload');
				        if(courseId!=null && courseSectionId!=null){
							var url='testreview?testId='+testId+'&courseId='+courseId+'&sectionId='+courseSectionId;
							window.location.href=contentId!=null?url+'&contentId='+contentId:url;	 
						}
				        else
				        	{
						window.location.href = 'testreview?testId=' + testId;
				        	}
					} else {
						$("#overlay").hide();
						$('#testAlert').modal('show');
						$("#testAlert p").text(
								"Something went wrong,Please try again");
					}
				}
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
			        + '</div>';
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
			        + '</div>';
		}
		$("#ansType").append(str);
		$("#totalOptions").val(optionNo);
		CKEDITOR.inline('answer' + optionNo, {
			height : 40
		}).on('change', function(e) {
			questionKeyValidate();
		});
		$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
		       checkboxClass: 'icheckbox_square-green',
		          radioClass: 'iradio_square-green'
		     });
	}
}

    /**
	 * @summary This is used for getting test list using ajax from where user
	 *          can choose any test.
	 * @param sectionId
	 * @return no.
	 */
function getQuestionBank(sectionId) {
	$("#overlay").show();
	/**
	 * calling ajax function for getting test list dynamically
	 */
	$.ajax({
		type : 'GET',
		url : 'getQuestionBank',
		success : function(questionBank) {
			/**
			 * calling function for extracting test details from test list json
			 * and adding in table.
			 */
			extractQuestionBank(questionBank,sectionId);
		}
	});
}

/**
 * @summary This is used for extracting test details from test list json and
 *          adding in table.
 * @param testData
 *            This is only parameter which has details about all tests.
 * @return no.
 */
    var questionBankTableVar;
    function extractQuestionBank(questionBank,sectionId) {
    	var position = findSectionIdInList(sectionId);
    	var alreadyImportedQuestion=[];
    	/**
    	 * This will take all imported ids of question in a particular section and these ids would be used for removing the duplicacy of same question in same section.
    	 */
    	for(var q=0;q<questionSectionList[position].questionList.length;q++){
    		/**
    		 * push ids of question if question is already save in test.
    		 */
    		if(questionSectionList[position].questionList[q].isNew==0){
    		alreadyImportedQuestion.push(questionSectionList[position].questionList[q].questionId);
    		    }
    	   }
    	
    	
    	$('#questionBankTable').DataTable().destroy();
    	/**
    	 * This is a empty object which would be used adding  html data as row inside table body. 
    	 */
    	var row = '';
    	/**
    	 * before adding any data in table body, make it empty for no duplicate data if operation performs again.
    	 */
       $("#questionBankBody").empty();
    	/**
    	 * a lopping on test list data for extract test details for a particular test.
    	 */
    	for (var i = 0; i < questionBank.length; i++) {
    		/**
    		 * making an argument what would be passed inside a function for selecting a test.
    		 */
    		var argument = "'" + questionBank[i].questionId +"'";
    		/**
    		 * checkbox input 
    		 */
    		var inputCheckbox ='';
    		/**
    		 * check question is already imported.
    		 */
    		if(contains(alreadyImportedQuestion,questionBank[i].questionId)){
    			inputCheckbox = '<input type="checkbox" name="selectedQuestionIds" class="selectedQuestionIds" value="'+questionBank[i].questionId+'" disabled>';
    		}
    		else
    			{
    			inputCheckbox = '<input type="checkbox" name="selectedQuestionIds" class="selectedQuestionIds" value="'+questionBank[i].questionId+'">';
    			}
    		/**
    		 * assing a html element for adding a row in table body.
    		 */
    		row = row
    				+ '<tr><td>'
    				+ questionBank[i].createdDate
    				+ '</td>'
    				+ '<td class="imgset">'
    				+ questionBank[i].questionName
    				+ '</td>'
    				+ '<td>'
    				+ questionBank[i].questionTypeName
    				+ '</td>'
    				+ '<td>'
    				+ inputCheckbox
    				+ '</td></tr>';
    	}
    	/**
    	 * append a table row inside table body.
    	 */
    	$("#questionBankBody").append(row);
    	/**
    	 * using icheck for checkbox.
    	 */
    	$('input[type="checkbox"].selectedQuestionIds').iCheck({
    	       checkboxClass: 'icheckbox_square-green'
    	     });
    	/**
    	 * initialize a table into data table
    	 */
    	questionBankTableVar=$("#questionBankTable").dataTable(
    			          {
    	        	  'columnDefs': [{ 'orderable': false, 'targets': [3] }], // hide sort icon on action
    	        	    'aaSorting': [],
    	        	    "destroy" : true
    	        	    
    	                   });
    	/**
    	 * clear the selected checkbox.
    	 */
    	$('input[name=selectedQuestionIds]').prop('checked', false)
        $('.imgset img').css({'max-width':'100%','height': 'auto'});
    	/**
    	 * Save action method on import button.
    	 */
    	$("#importQuestionButtonInTable").attr('onclick','importQuestion('+sectionId+')');
    	$("#importQuestionButtonInTable").prop('disabled',true);
    	/**
    	 * hide the loader
    	 */
    	 $("#overlay").hide();
    	/**
    	 * after loading all data into table , show pop up for choosing a test.
    	 */
    	$("#questionBankPopup").modal('show');
    }

    
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
		var checkValues = []; 
		$(":checkbox:checked", questionBankTableVar.fnGetNodes()).each(
				function(i) {
					checkValues[i] = $(this).val();
				});
		form.append("selectedQuestionIds", checkValues);
	form.append("totalQuestion", questionSectionList[position].questionList.length);
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
					$("#testAlert p").text(
							"Something went wrong,Please try again");
					$('#testAlert').modal('show');
				},
				success : function(questionJsonList) {
					if (questionJsonList.length > 0) {
						if(questionSectionList[position].questionList.length==0){
							appendSectionDetailsDiv(sectionId);
					           }
						for (var q = 0; q < questionJsonList.length; q++) {
								questionSectionList[position].questionList
										.push(questionJsonList[q]);
								questionSectionList[position].sectionScore = questionSectionList[position].sectionScore + questionJsonList[q].questionMark;
								var quesData = [];
								quesData.push(questionJsonList[q].questionId);
								quesData.push(questionJsonList[q].questionName);
								quesData.push(questionJsonList[q].questionMark);
								appendQuestionInsection(quesData,sectionId);
						}
						$("#totalSectionQuestion"+sectionId).text(questionSectionList[position].questionList.length);
						$("#totalSectionMarks"+sectionId).text(questionSectionList[position].sectionScore);
						$("#overlay").hide();
					} else {
						$("#overlay").hide();
						$("body").css("overflow", "hidden");
						$("#testAlert p").text(
								"Something went wrong,Please try again");
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
		for(var sec=0;sec<questionSectionList.length;sec++){
			/**
			 * traversing on question of every section.
			 */
		for (var q = 0; q < questionSectionList[sec].questionList.length; q++) {
			if (questionSectionList[sec].questionList[q].questionMark < 1
					|| questionSectionList[sec].questionList[q].questionMark == '') {
				throw new expectationFailedException(
						"Question Score is required for Question Number "
								+ (q + 1) + " of Section " +(sec+1) );
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
 * @summary custom exception for json validatation.
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
	var selectedQuestionType = $.trim($("#selectedQuestionType").val());
	/**
	 * if question type is multiple choice.
	 */
	if(selectedQuestionType==1){
	addNextQuestion(totalQuestions + 1);
	$("#submitQuestion").text('Save');
	$("#submitQuestion").attr('onclick', 'saveQuestionInJson()');
	}
	/**
	 * if question type is multiple choice.
	 */
	else if(selectedQuestionType==3){
		addSortListTypeQuestion();
		$("#submitQuestion").text('Save');
		$("#submitQuestion").attr('onclick', 'saveSortListTypeQuestionInJson()');
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
	 if(courseId!=null && courseSectionId!=null){
			var url='addEditTest?testId='+testId+'&courseId='+courseId+'&sectionId='+courseSectionId;
			window.location.href=contentId!=null?url+'&contentId='+contentId:url;	 
		}
	 else
		 {
	window.location.href='addEditTest?testId='+testId;
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
 * @summary This is used for showing alert if user press clear question button.
 * @returns no.
 */
function clearQuestion() {
	$("#clearquestionAlert p").text("Are you sure to clear this Question?");
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

/**
 * @summary This is used for showing popup form for fill up section details.
 * 
 * @returns no.
 */
function sectionCreatePopup() {
	$("#sectionHeading").text("Create New Section");
	$("#newSectionName").css({
		"border-color" : ""
	});
	$("#newSectionNameError1").fadeOut();
	$("#newSectionNameError2").fadeOut();
	$("#newSectionNameError3").fadeOut();
	$("#saveSectionButton").text("Save");
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
	$("#sectionHeading").text("Edit Section Name");
	$("#newSectionName").css({
		"border-color" : ""
	});
	$("#newSectionNameError1").fadeOut();
	$("#newSectionNameError2").fadeOut();
	$("#newSectionNameError3").fadeOut();
	$("#saveSectionButton").text("Update");
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
		"sectionScore":0,
		"questionList" : [],
	};
	questionSectionList.push(section);
	appendSectionDivHtml(sectionId,sectionName);
	$("#sectionPop").modal('hide');
} 

/**
 * @summary This is used appending section html in page.
 * @param sectionId
 * @param sectionName
 * @returns no.
 */
function appendSectionDivHtml(sectionId, sectionName) {
	var section = '<div class="section">'
		    + '<div class="row">' 
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
			+ '<a id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="icon-dropdown"><img src="resources/adminlte/dist/img/ellipsis-h.png"></a>'
			+ '<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dlabel">'
			+ '<li><a onclick="updateSectionName('
			+ sectionId
			+ ');" class="cursor-pointer">Edit Name</a></li>'
			+ '<li class="divider"></li>'
			+ '<li><a class="cursor-pointer sectionDelete" id="sectionDelete'
			+ sectionId
			+ '">Delete</a></li>'
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
			+ '<div class="col-sm-2"></div>'
			+ '<div class="col-sm-4" style="border-right: 1px solid #eee;">'
			+ '<h3 style="text-align: center;">'
			+ '<img src="resources/adminlte/dist/img/coursecontent.png" onclick="showCreateQuestionPagePopUp('
			+ sectionId
			+ ');" style="cursor:pointer"/>'
			+ '</h3>'
			+ '<h4 style="text-align: center;">Create Question</h4>'
			+ '</div>'
			+ '<div class="col-sm-4">'
			+ '<h3 style="text-align: center;">'
			+ '<img onclick="getQuestionBank('
			+ sectionId
			+ ');" src="resources/adminlte/dist/img/coursecontent.png" style="cursor:pointer"/>'
			+ '</h3>'
			+ '<h4 style="text-align: center;">Add Question from <br>Question Bank</h4>'
			+ '<div class="col-sm-2"></div>'
			+ '	</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>';
	$("#totalTestSections").append(section);
}


/**
 * @summary function is used for save the new question data in json.
 * @returns no.
 */
function saveQuestionInJson() {		
	var sectionId = $("#sectionIdForAddedQues").val();
	if (questionValidate()) {
		var position = findSectionIdInList(sectionId);
		var quesData = createJson(position);
		appendQuestionInsection(quesData,sectionId);
		if(questionSectionList[position].questionList.length==1){
			appendSectionDetailsDiv(sectionId);
		   }
		$("#totalSectionQuestion"+sectionId).text(questionSectionList[position].questionList.length);
		$("#totalSectionMarks"+sectionId).text(questionSectionList[position].sectionScore);
		totalQuestions++;
	}
}

/**
 * @summary function is used append section details div into section (it is all about how much marks a section has and how much question).
 * @param sectionId
 * @returns no.
 */
function appendSectionDetailsDiv(sectionId){
	  var str = '<div class="well2 well-sm">'+
		'<div class="input-group">'+
			'<div class="input-group-addon imgset pull-left"><b>Total Question Added :&nbsp;<span id="totalSectionQuestion'+sectionId+'"></span> Questions</b></div>'+
			'<div class="input-group-addon">'+
                 '<b>Total Marks Contain :&nbsp;<span id="totalSectionMarks'+sectionId+'"></span></b>'+  
			'</div>'+
		'</div>'+
	'</div>';
$("#questionDivOfSection"+sectionId).after(str);
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
			+ '<div class="input-group-addon"><button type="button" id="questionMark'+sectionId+'##'+quesData[0]+'" class="btn nohover btn-flat btn-default" style="cursor:default" disabled>'+quesData[2]+'</button>&nbsp;&nbsp;&nbsp;Marks</div>'
			+ '<div class="input-group-addon">'
			+ '<div class="dropdown pull-right">'
			+ '<a id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="content-dropdown icon-dropdown"> <img src="resources/adminlte/dist/img/ellipsis-v.png" class="shape" /></a>'
			+ '<ul class="dropdown-menu dropdown-menu-right" aria-labelledby=" dlabel">'
			+ '<li><a id="questionEdit'
			+ sectionId
			+ '##'
			+ quesData[0]
			+ '" class="cursor-pointer questionEdit">Edit</a></li>'
			+ '<li class="divider"></li>'
			+ '<li><a id="questionDelete'
			+ sectionId
			+ '##'
			+ quesData[0]
			+ '" class="questionDelete cursor-pointer">Delete</a></li>'
			+ '</ul>' + '</div>' + '</div>' + '</div>' + '</div>';

	$("#questionDivOfSection" + sectionId).append(ques);
	$('.imgset img').css({'max-width':'400px','height': 'auto'});
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
			$("#clearquestionAlert p").text(
					"Are you sure for delete this question?");
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
 * @summary This is used findout the position in json array of a particular section based
 * on section id.
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
 * @summary This is used for calling fetching all section if user clicks on
 * this button.
 * @returns no.
 */
    $("#importExistSections").click(function (){
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
		url : 'gettestsectionlistajax?testId='+testId,
		type : 'GET',
		error : function() {
			$("#overlay").hide();
			$("#testAlert p").text("Something went wrong,Please try again");
			$('#testAlert').modal('show');
		},
		success : function(sectionlist) {
			/**
			 * calling function for extracting section details from section list json
			 * and adding in table.
			 */
			extractSectionListData(sectionlist);
		}

	});
}

/**
 * @summary This is used for extracting section details from  list json and
 *          adding in table.
 * @param sectionlist
 *            This is only parameter which has details about all section.
 * @return no.
 */
var secListtable;
function extractSectionListData(sectionlist) {
	try{
	var alreadyImportedSection=[];
	/**
	 * This will take all imported ids of question in a particular section and these ids would be used for removing the duplicacy of same question in same section.
	 */
	for(var sec=0;sec<questionSectionList.length;sec++){
		/**
		 * push ids of question if question is already save in test.
		 */
		alreadyImportedSection.push(questionSectionList[sec].sectionId);
	   }
	
	if (secListtable != null) {
		$('#sectionListTable').DataTable().destroy();
		}
	/**
	 * This is a empty object which would be used adding  html data as row inside table body. 
	 */
	var row = '';
	/**
	 * before adding any data in table body, make it empty for no duplicate data if operation performs again.
	 */
	$("#sectonListbody").empty();
	/**
	 * a lopping on section list data for extract section details.
	 */
	for (var i = 0; i < sectionlist.length; i++) {
		/**
		 * making an argument what would be passed inside a function for selecting a test.
		 */
		var argument = "'" + sectionlist[i].sectionId +"'";
		/**
		 * checkbox input 
		 */
		var inputCheckbox ='';
		/**
		 * check section is already imported.
		 */
		if(contains(alreadyImportedSection,sectionlist[i].sectionId)){
			inputCheckbox = '<input type="checkbox" name="selectedSectionIds" class="selectedSectionIds" value="'+sectionlist[i].sectionId+'" disabled>';
		}
		else
			{
			inputCheckbox = '<input type="checkbox" name="selectedSectionIds" class="selectedSectionIds" value="'+sectionlist[i].sectionId+'">';
			}
		/**
		 * assing a html element for adding a row in table body.
		 */
		row = row
				+ '<tr><td>'
				+ sectionlist[i].sectionName
				+ '</td>'
				+ '<td>'
				+ sectionlist[i].testName
				+ '</td>'
				+ '<td>'
				+ sectionlist[i].totalQuestions
				+ '</td>'
				+ '<td>'
				+ inputCheckbox
				+ '</td></tr>';
	}
	/**
	 * append a table row inside table body.
	 */
	$("#sectonListbody").append(row);
	/**
	 * using icheck for checkbox.
	 */
	$('input[type="checkbox"].selectedSectionIds').iCheck({
	       checkboxClass: 'icheckbox_square-green'
	     });
	/**
	 * initialize a table into data table
	 */
	secListtable = $("#sectionListTable").dataTable({
		'columnDefs': [{ 'orderable': false, 'targets': [3] }], // hide sort icon on action
 	    'aaSorting': [],
 	    "destroy" : true
	});
	/**
	 * hide the loader
	 */
	$("#overlay").hide();
	/**
	 * Set calling function on import section button.
	 */
	$("#importSectionButtonInTable").attr('onclick','importSection()');
	/**
	 * Disable import button 
	 */
	$("#importSectionButtonInTable").prop('disabled',true);
	/**
	 * after loading all data into table , show pop up for choosing a test.
	 */
	$("#sectionListtablePopup").modal('show');
	}catch(err)
	{$("#overlay").hide();console.log(err.message)}
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
	$(":checkbox:checked", secListtable.fnGetNodes()).each(
			function(i) {
				checkValues[i] = $(this).val();
			});
    form.append("selectedSectionIds", checkValues);
	form.append("everyQuestionMark", everyQuesMark);
	form.append("negMark", negMark);
	form.append("equalMarkTest", equalMarkTest);
	form.append("testId", testId);
	$("#sectionListtablePopup").modal('hide');
$
		.ajax({
			type : "POST",
			url : "importSectionInTest",
			data : form,
			processData : false,
			contentType : false,
			error : function() {
				$("#overlay").hide();
				$("body").css("overflow", "hidden");
				$("#testAlert p").text(
						"Something went wrong,Please try again");
				$('#testAlert').modal('show');
			},
			success : function(sectionList) {
				//console.log(JSON.stringify(sectionList));
				if (sectionList.length > 0) {
					performSectionAddingOperation(sectionList);
					$("#overlay").hide();
				} else {
					$("#overlay").hide();
					$("body").css("overflow", "hidden");
					$("#testAlert p").text(
							"Something went wrong,Please try again");
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
function performSectionAddingOperation(sectionList){
	try{
	for(var sec=0;sec<sectionList.length;sec++){
	questionSectionList.push(sectionList[sec]);
	appendSectionDivHtml(sectionList[sec].sectionId,sectionList[sec].sectionName);
	   for(var q=0;q<sectionList[sec].questionList.length;q++){
		   var quesData=[];
		   quesData.push(sectionList[sec].questionList[q].questionId);
		   quesData.push(sectionList[sec].questionList[q].questionName);
		   quesData.push(sectionList[sec].questionList[q].questionMark);
			appendQuestionInsection(quesData,sectionList[sec].sectionId);
	                   }
		if(sectionList[sec].questionList.length>=1){
			appendSectionDetailsDiv(sectionList[sec].sectionId);
		   }
		$("#totalSectionQuestion"+sectionList[sec].sectionId).text(sectionList[sec].questionList.length);
		$("#totalSectionMarks"+sectionList[sec].sectionId).text(sectionList[sec].sectionScore);
	     }
	}catch(err)
	{
		$("#overlay").hide();
		console.log(err.message);
		}
}

/**
 * @summary This is used showing list for selecting the question type.
 * @param sectionId
 * @returns no.
 */
function showCreateQuestionPagePopUp(sectionId){
	$("#selectedQuestionType option[value=1]").attr("selected","selected");
	$("#createQuestionType").attr('onclick','showCreateQuestionPage('+sectionId+')');
	$("#CreateQuestionPagePopUp").modal('show');
}