/**
 * @summary This file would be used for adding and editing sort list type question.
 * @author ankur
 * @date 24 Aug 2016
 */
var sortListTypeOptions = [];

/**
 * @summary function is used to create the sort list type question.
 * @returns no.
 */
function addSortListTypeQuestion(){
	$("#questiondiv").empty();
	sortListTypeOptions = [];
	var str = '<div class="col-xs-12">'
		
			+ '<div class="col-xs-12 question"><label id="sortListTypeQuestionTitle"><sup><font color="red" size="3px">*</font></sup>Question : </label>'
			+ '<div id="sortListTypeQuestionEditor" class="text_editor_margin"><textarea name="sortListTypeQuestion" id="sortListTypeQuestion" class="form-control myTextEditor" placeholder="Enter your question here."></textarea></div>'
			+ '<label class="requireFld" id="sortListTypeQuestionTitleError">This Field is required.</label>'
			+ '</div>'
			+ '<div class="col-xs-12 formBody"></div>'
			+ '<div class="col-xs-12 formBody"><label><sup><font color="red" size="3px">*</font></sup>Answers</label></div>'
			+ '<div id="answerDivInSortListTypeQuestion">'
			+'<input type="hidden" id="totalOptionsInSortListTypeQuestion" name="totalOptionsInSortListTypeQuestion" value="0">'
			+ '</div><!-- /.totalOptionsInSortListTypeQuestion -->'
			
			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" onclick="addOptionInSortListTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> Add Answer</span></button>'
			+ '&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-flat button-width-large" onclick="clearDataInSortListTypeQuestionPopup();"><span><i class="fa fa-trash-o"> Clear Question</i></span></button>'
			+ '</div>'
			
			+ '<div class="col-xs-12 form-group">'
            + '<div class="row" id="correctOrderListInSortListTypeQuestion"></div>'
			+ '</div>'
			
			+ '<div class="col-xs-12 form-group">'
			+ '<label>Answer Explanation:</label>'
			+ '<textarea class="form-control textAreaControl"  name="sortListTypeQuestionAnswerExplanation" id="sortListTypeQuestionAnswerExplanation" placeholder="Answer Explanation" onkeyup="sortListTypeQuestionListner();"></textarea>'
			+ '<label class="requireFld" id="sortListTypeQuestionAnswerExplanationError" >This Field is required.</label>'
			+ '</div>' 
			
			+ '</div>';
	$("#questiondiv").append(str);
    for(var option=0;option<2;option++){
		addOptionInSortListTypeQuestion(option+1);
	}
	CKEDITOR.inline('sortListTypeQuestion').on('change', function(e) {
		sortListTypeQuestionListner();
	});
 $("#correctOrderListInSortListTypeQuestion").sortable({
		appendTo: "body",
		axis : "y",
		revert : true,
		//cursor : "move", //Defines the cursor that is being shown while sorting.
		//distance : 5, //Tolerance, in pixels, for when sorting should start. If specified, sorting will not start until after mouse is dragged beyond distance. Can be used to allow for clicks on elements within a handle.
		//containment: "parent", // Defines a bounding box that the sortable items are constrained to while dragging.
		opacity : 0.5, //Defines the opacity of the helper while sorting. From 0.01 to 1.
		update : function(event, ui) {
			sortListTypeOptions = $(this).sortable("toArray");
		}
	});
	$("#correctOrderListInSortListTypeQuestion").disableSelection();
}

/**
 * @summary This is used to adding more answer for sort list type question.
 * @returns no.
 */
function addOptionInSortListTypeQuestion() {
	var optionNo = $("#totalOptionsInSortListTypeQuestion").val();
	var optn = 65 + parseInt(optionNo);
	var optionOrder = "&#" + optn + ";";
	var str = "";
	if (optionNo < 10) {
		optionNo++;		
			str = '<div id="option'
					+ optionNo
					+ 'InSortListTypeQuestion">'
					+ '<div class="col-xs-12 formBody">'
					+ '<div class="">'
					+ '<div class="input-group">'
					+ '<span class="input-group-addon" id="answerOrder'
					+ optionNo
					+ 'InSortListTypeQuestion"><span class="badge bagde-style">'
					+ optionOrder
					+ '</span></span>'
					+ '<div id="answer'
					+ optionNo
					+ 'divInSortListTypeQuestion" class="text_editor_margin"><textarea name="answer'
					+ optionNo
					+ 'InSortListTypeQuestion" id="answer'
					+ optionNo
					+ 'InSortListTypeQuestion"  placeholder="Answer No. '
					+ optionNo
					+ '" class="form-control myTextEditor"></textarea></div>'
					+ '<span class="input-group-addon" id="deleteOption'
					+ optionNo
					+ 'InSortListTypeQuestion" onclick="deleteOptionInSortListTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="Remove Answer"></i></span>'
					+ '</div><!-- /input-group -->'
					+ '</div>'
					+ '</div>'
					+ '<div class="col-xs-12 input-group" ><div class="input-group-addon" style="visibility:hidden">AAA</div><div class="col-xs-11"><label class="requireFld" id="answer'
					+ optionNo
					+ 'ErrorInSortListTypeQuestion">This Field is required.</label></div></div>'
			        + '</div>';

		$("#answerDivInSortListTypeQuestion").append(str);
              if(optionNo<=2){
          		$("#deleteOption1InSortListTypeQuestion").css("visibility", "hidden");
        		$("#deleteOption2InSortListTypeQuestion").css("visibility", "hidden");
		         }
		        else
			        {
		        	$("#deleteOption1InSortListTypeQuestion").css("visibility", "visible");
	        		$("#deleteOption2InSortListTypeQuestion").css("visibility", "visible");
			        }
		$("#totalOptionsInSortListTypeQuestion").val(optionNo);
		CKEDITOR.inline('answer'+optionNo+'InSortListTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			sortListTypeQuestionListner();
			changeOptionTextInSortListTypeQuestion(e.listenerData);
		},null,optionNo);
		createOptionOrderListInSortListTypeQuestion(optionNo);
	}
}

/**
 * @summary This is used creating option list for sorting.
 * @param opnNo
 * @returns no.
 */
function createOptionOrderListInSortListTypeQuestion(opnNo){
	var optn = 64 + parseInt(opnNo);
	var optionOrder = "&#" + optn + ";"; 
	var opnList = '<div class="well well-sm" id="optionOrder'+opnNo+'InSortListTypeQuestion">'
		+ '<div class="input-group">'
		+ '<div class="input-group-addon">'
		+ '<i class="fa fa-align-justify"></i>'
		+ '</div>' 
		+ '<div class="imgset pull-left" id="optionText'+opnNo+'InSortListTypeQuestion">[ '+optionOrder
		+ ' ]</div>'
		+ '</div>' 
		+ '</div>';
	$("#correctOrderListInSortListTypeQuestion").append(opnList);
	sortListTypeOptions.push('optionOrder'+opnNo+'InSortListTypeQuestion');
}

/**
 * @summary This is used changing the text in list which is showing for order.
 * @param opn
 * @returns no.
 */
function changeOptionTextInSortListTypeQuestion(opn){
  $("#optionText"+opn+"InSortListTypeQuestion").html(CKEDITOR.instances['answer' + opn + 'InSortListTypeQuestion'].getData());
	$('.imgset img').css({
		'max-width' : '400px',
		'height' : 'auto'
	});
}

/**
 * @summary This is used for deleting the option of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteOptionInSortListTypeQuestion(object) {
	var optionId = object.id;
	var arr = optionId.split('deleteOption');
	var optionNo = parseInt(arr[1].substr(0,1));
	var totalOption = $("#totalOptionsInSortListTypeQuestion").val();
	if (totalOption <= 2) {
	} else {
		$("#option" + optionNo + "InSortListTypeQuestion").remove();
		$("#optionOrder"+optionNo+"InSortListTypeQuestion").remove();
		var isSplice=0;
		for(var order =0;order<sortListTypeOptions.length;order++){
			if(sortListTypeOptions[order]=="optionOrder"+optionNo+"InSortListTypeQuestion"){
				sortListTypeOptions.splice(order,1);
				isSplice = 1;
				break;
			}
		}
		if(isSplice==1){
			for(var order =0;order<sortListTypeOptions.length;order++){
				var arr = sortListTypeOptions[order].split("optionOrder");
				if(Number(arr[1].substring(0,1))>optionNo){
					sortListTypeOptions[order] = "optionOrder"+(arr[1]-1)+"InSortListTypeQuestion";
				}
			}
		}
		optionNo++;
		for (var i = optionNo; i <= totalOption; i++) {
			$("#option" + i + "InSortListTypeQuestion").attr('id', "option" + (i - 1) + "InSortListTypeQuestion");
			$("#answerOrder" + i + "InSortListTypeQuestion").attr('id', "answerOrder" + (i - 1) + "InSortListTypeQuestion");
			$("#answerOrder" + (i - 1) + "InSortListTypeQuestion").html(
					"<span class='badge bagde-style'>&#" + (64 + i - 1)
							+ ";</span>");
			$("#answer" + i + "divInSortListTypeQuestion").attr('id', "answer" + (i - 1) + "divInSortListTypeQuestion");
			$("#answer" + i + "InSortListTypeQuestion").attr({
				id : "answer" + (i - 1) + "InSortListTypeQuestion",
				name : "answer" + (i - 1) + "InSortListTypeQuestion",
				placeholder : "Answer No. " + (i - 1)
			});
			$("#deleteOption" + i + "InSortListTypeQuestion").attr('id', "deleteOption" + (i - 1) + "InSortListTypeQuestion");
			$("#answer" + i + "ErrorInSortListTypeQuestion").attr('id', "answer" + (i - 1) + "ErrorInSortListTypeQuestion");
			$("#optionOrder"+ i +"InSortListTypeQuestion").attr('id', "optionOrder" + (i - 1) +"InSortListTypeQuestion");
			$("#optionText"+ i +"InSortListTypeQuestion").attr('id', "optionText" + (i - 1) +"InSortListTypeQuestion");
			CKEDITOR.instances['answer' + i + 'InSortListTypeQuestion'].destroy();
			CKEDITOR.inline('answer'+(i - 1)+'InSortListTypeQuestion', {
				height : 40
			}).on('change', function(e) {
				sortListTypeQuestionListner();
				changeOptionTextInSortListTypeQuestion(e.listenerData);
			},null,(i-1));
		}
		totalOption--;
		$("#totalOptionsInSortListTypeQuestion").val(totalOption);
	}
	if (totalOption == 2) {
		$("#deleteOption1InSortListTypeQuestion").css("visibility", "hidden");
		$("#deleteOption2InSortListTypeQuestion").css("visibility", "hidden");
	}
}

/**
 * @summary This is used to validate sort list type question data.
 * @returns {Boolean}
 */
 function sortListTypeQuestionValidate(){
		for ( var instanceName in CKEDITOR.instances) {
			CKEDITOR.instances[instanceName].updateElement();
		}
		var totaloptions = $("#totalOptionsInSortListTypeQuestion").val();
		var questionContent = CKEDITOR.instances['sortListTypeQuestion'].getData();
		var questionContentData = ConvertHtmlToPlainTest(questionContent);
		if (questionContentData == "") {
			$("#sortListTypeQuestionEditor").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#sortListTypeQuestionTitleError").fadeIn();
			$(window).scrollTop($("#sortListTypeQuestionEditor").offset().top);
			return false;
		}
		for (var j = 1; j <= totaloptions; j++) {
			var optionContent = CKEDITOR.instances['answer' + j+ 'InSortListTypeQuestion'].getData();
			var optionContentData = ConvertHtmlToPlainTest(optionContent);
			if (optionContentData == "") {
				$("#answer" + j + "divInSortListTypeQuestion").css({
					"border-color" : "#c95b5b",
					"border-style" : "solid",
					"border-width" : "1px"
				});
				$("#answer" + j + "ErrorInSortListTypeQuestion").fadeIn();
				$(window).scrollTop($("#answer" + j + "divInSortListTypeQuestion").offset().top);
				return false;
			}

		}	
		/*
		 * if($("#sortListTypeQuestionAnswerExplanation").val()=="") {
		 * $("#sortListTypeQuestionAnswerExplanation").css("border-color","#c95b5b");
		 * $("#sortListTypeQuestionAnswerExplanationError").fadeIn(); return false; }
		 */
		return true;
 }
 
 /**
  * @summary This is used fadeout the validation errors from sort list type question page.
  * @returns no.
  */
 function sortListTypeQuestionListner(){
		for ( var instanceName in CKEDITOR.instances) {
			CKEDITOR.instances[instanceName].updateElement();
		}
		var totaloptions = $("#totalOptionsInSortListTypeQuestion").val();
		var questionContent = CKEDITOR.instances['sortListTypeQuestion'].getData();
		if (questionContent != "") {
			$("#sortListTypeQuestionEditor").css("border-color", "#7ac17d");
			$("#sortListTypeQuestionTitleError").fadeOut();
		}
		for (var j = 1; j <= totaloptions; j++) {
			var optionContent = CKEDITOR.instances['answer' + j + 'InSortListTypeQuestion'].getData();
			if (optionContent != "") {
				$("#answer" + j + "divInSortListTypeQuestion").css("border-color", "#7ac17d");
				$("#answer" + j + "ErrorInSortListTypeQuestion").fadeOut();
			}
		}
		
		if ($("#sortListTypeQuestionAnswerExplanation").val().length > 0) {
			$("#sortListTypeQuestionAnswerExplanation").css("border-color", "#7ac17d");
			$("#sortListTypeQuestionAnswerExplanationError").fadeOut();
		}
 }
 
 /**
  * @summary This is used for showing the pop up for clear data from sort list type question.
  * @returns no.
  */
 function clearDataInSortListTypeQuestionPopup(){
		$("#clearquestionAlert p").text("Are you sure to clear this Question?");
		$("#dId").attr('onclick', 'clearQuestionDataInSortListTypeQuestion()');
		$('#clearquestionAlert').modal('show');
 }
 
 /**
  * @summary This is used for clear the all data from sort list type question.
  * @returns no.
  */
 function clearQuestionDataInSortListTypeQuestion(){
		$('#clearquestionAlert').modal('hide');
		var totalOptions = $("#totalOptionsInSortListTypeQuestion").val();
		CKEDITOR.instances['sortListTypeQuestion'].setData('');
		for (var op = 1; op <= totalOptions; op++) {
			CKEDITOR.instances['answer' + op + 'InSortListTypeQuestion'].setData('');
			$("#optionText" + op + "InSortListTypeQuestion").html('');
		}
		$("#sortListTypeQuestionAnswerExplanation").val('');
		
		$(window).scrollTop(0);
 }
 
 /**
  * @summary This is used for confirm the user for submit the details of sort list type question.
  * @returns no.
  */
 function saveSortListQuesPopUp(){
		if(sortListTypeQuestionValidate()){
		$("#clearquestionAlert p").text(
			"Are you sure for submit the details?");
		$("#dId").attr('onclick', 'saveSortListTypeQuestionInJson()');
		$("#clearquestionAlert").modal('show');
		}
	}
 
/**
 * @summary function is used for save the new question data in json.
 * @returns no.
 */
function saveSortListTypeQuestionInJson() {		
	if (sortListTypeQuestionValidate()) {
		var quesData = createSortListTypeQuestionJson();
		submitQuestionDetails();
	}
}

/**
 * @summary This is used for creating the json for new question.
 * 
 * @param position
 * @returns {Array}
 */
function createSortListTypeQuestionJson() {
var quesData = [];
var optionsList = [];
var questionContent = CKEDITOR.instances['sortListTypeQuestion'].getData();
var question = {
	"questionId" : dynamicId,
	"questionName" : questionContent,
	"questionType" : 3,
	"option" : optionsList,
	"explanation" : $("#sortListTypeQuestionAnswerExplanation").val(),
	"isFormula" : 0,
	"answerValue" :""
};
var totalOption = $("#totalOptionsInSortListTypeQuestion").val();
 var answerValue=[];
for(var op=0;op<sortListTypeOptions.length;op++){
	var options = sortListTypeOptions[op].split('optionOrder');
	answerValue.push(Number(options[1].substring(0,1)));
}
question.answerValue = answerValue.toString();
for (var op = 1; op <= totalOption; op++) {
	var optionContent = CKEDITOR.instances['answer' + op + 'InSortListTypeQuestion'].getData();
	var optionStatus = 0;

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
 * @summary This is used for fill up the question's data from json object when user enters in edit mode of sort list type question.
 * @param position
 * @param questionId
 * @returns no.
 */
function fillSortListTypeQuestionData(questionId){
	for (var i = 0; i < questionSectionList[0].questionList.length; i++) {
		if (questionSectionList[0].questionList[i].questionId == questionId) {
			CKEDITOR.instances['sortListTypeQuestion'].setData(questionSectionList[0].questionList[i].questionName);		
			for (var j = 0; j < questionSectionList[0].questionList[i].option.length; j++) {
				if ($("#totalOptionsInSortListTypeQuestion").val() == j) {
				addOptionInSortListTypeQuestion();
				}
				CKEDITOR.instances['answer' + (j + 1) + 'InSortListTypeQuestion'].setData(questionSectionList[0].questionList[i].option[j].optionName);
				/**
				 * for sort list
				 */
				$("#optionText" + (j+1) + "InSortListTypeQuestion").html(questionSectionList[0].questionList[i].option[j].optionName);
			}
			
			var $divs = $("#correctOrderListInSortListTypeQuestion > .well");
			var sortOrderOfAnswer = JSON.parse("[" + questionSectionList[0].questionList[i].answerValue + "]");
			sortListTypeOptions = [];
			for(var opOrder =0;opOrder<sortOrderOfAnswer.length;opOrder++){
				sortListTypeOptions.push("optionOrder" + sortOrderOfAnswer[opOrder] + "InSortListTypeQuestion")
			}
			var alphabeticallyOrderedDivs = $divs.sort(function (a, b) {
				var firstDivId = $(a).attr('id').split('optionOrder');
				var secondDivId = $(b).attr('id').split('optionOrder');
				var fstIdOrder = findPositionInArray(sortOrderOfAnswer,Number(firstDivId[1].substring(0,1)));  
				var secIdOrder = findPositionInArray(sortOrderOfAnswer,Number(secondDivId[1].substring(0,1)));	
				return  fstIdOrder > secIdOrder;
		    });
		    $("#correctOrderListInSortListTypeQuestion").html(alphabeticallyOrderedDivs);
		    
			$("#sortListTypeQuestionAnswerExplanation").val(
					questionSectionList[0].questionList[i].explanation);
		}
	}
	$('.imgset img').css({
		'max-width' : '400px',
		'height' : 'auto'
	});
}

/**
 * @summary This is used for finding the postion of a item in a particular array.
 * @param array
 * @param item
 * @returns {Number}
 */
function findPositionInArray(array, item) {
	var position;
	for (var i = 0; i < array.length; i++) {
		if (array[i] === item) {
			position = i;
			break;
		}
	}
	return position;
}

/**
 * @summary This is used for showing the pop up for confirm the update a sort list type question.
 * @param questionId
 * @returns no
 */
function updateSortListTypeQuestionPopUp(questionId){
	if(sortListTypeQuestionValidate()){
       $("#clearquestionAlert p").text(
	         "Are you sure for update this question?");
       $("#dId").attr('onclick', 'updateSortListQuestion('+questionId+')');
       $("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary This is used for performing opertion for update the sort list type question.
 * @param questionId
 * @retruns no
 */
function updateSortListQuestion(questionId){
	if(sortListTypeQuestionValidate()){
	var quesData = updateSortListQuestionTypeJson(questionId);
	submitQuestionDetails();
	}
}

/**
 * @summary This is used for update the json for sort list question type after update the user..
 * @param position
 * @param questionId
 * @returns no.
 */
function updateSortListQuestionTypeJson(questionId) {
		for (var q = 0; q < questionSectionList[0].questionList.length; q++) {
			if (questionSectionList[0].questionList[q].questionId == questionId) {
				var optionsList = [];
				var questionContent = CKEDITOR.instances['sortListTypeQuestion'].getData();
				var question = {
					"questionId" : questionId,
					"questionName" : questionContent,
					"questionType" : 3,
					"option" : optionsList,
					"explanation" : $("#sortListTypeQuestionAnswerExplanation").val(),
					"isFormula" : 0,
					"answerValue" :""
				};
				var totalOption = $("#totalOptionsInSortListTypeQuestion").val();
				
				 var answerValue=[];
				 for(var op=0;op<sortListTypeOptions.length;op++){
				 	var options = sortListTypeOptions[op].split('optionOrder');
				 	answerValue.push(Number(options[1].substring(0,1)));
				 }
				 question.answerValue = answerValue.toString();
				 
				for (var op = 1; op <= totalOption; op++) {
					var optionContent = CKEDITOR.instances['answer' + op + 'InSortListTypeQuestion']
							.getData();
					var optionStatus = 0;
					$('.imgset img').css({
						'max-width' : '400px',
						'height' : 'auto'
					});
					var options = {
						"optionId" : parseInt(questionId +""+ op),
						"optionName" : optionContent,
						"optionOrder" : op,
						"answerStatus" : optionStatus
					};
					optionsList.push(options);
				} 
				questionSectionList[0].questionList[q] = question;
			}
		  /*console.log(JSON.stringify(questionSectionList));*/
		}
		sortListTypeOptions = [];
		var quesData = [];
    	quesData.push(question.questionId);
    	quesData.push(question.questionName);
    	return quesData;
		$('.imgset img').css({
			'max-width' : '400px',
			'height' : 'auto'
		});
}