/**
 * @summary This file would be used for adding and editing match list type question.
 * @author ankur
 * @date 13 Sep 2016
 */

/**
 * @summary function is used to create the match list type question.
 * @returns no.
 */
function addMatchListTypeQuestion() {
	$("#questiondiv").empty();
	var str = '<div class="col-xs-12">'

			+ '<div class="col-xs-12 question form-group"><label id="matchListTypeQuestionTitle"><sup><font color="red" size="3px">*</font></sup>'+ messages['lbl.question'] +' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-title-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="matchListTypeQuestionEditor" class="text_editor_margin"><textarea name="matchListTypeQuestion" id="matchListTypeQuestion" class="form-control myTextEditor" placeholder="'+messages['placeholder.question']+'"></textarea></div>'
			+ '<label class="requireFld" id="matchListTypeQuestionTitleError">'+messages['msg.empty']+'</label>'
			+ '</div>'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.answer']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-answer-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="answerDivInMatchListTypeQuestion">'
			+ '<input type="hidden" id="totalOptionsInMatchListTypeQuestion" name="totalOptionsInMatchListTypeQuestion" value="0">'
			+ '<input type="hidden" id="totalListItemsInMatchListTypeQuestion" name="totalListItemsInMatchListTypeQuestion" value="0">'
			+ '</div><!-- /.totalOptionsInMatchListTypeQuestion -->'

			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="addMoreOptionsInMatchListTypeQuestion" onclick="addOptionInMatchListTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addanswer']+'</span></button>'
			+ '&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-flat button-width-large" id="clearDataInMatchListTypeQuestion" onclick="clearDataInMatchListTypeQuestionPopup();"><span><i class="fa fa-trash-o"></i> '+ messages['lbl.clearquestion']+'</span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+ messages['lbl.list']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-list-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="listItemsListInMatchListTypeQuestion"></div>'
			+ '</div>'
            
	
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="addMoreListItemsInMatchListTypeQuestion" onclick="addListItemsInMatchListTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addlistitem']+'</span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group"><label>'+messages['lbl.matchinglistorder']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-matchlist-order-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div class="col-xs-12 form-group" style="border:1px solid #ccc;">'

			+ '<div class="row match_list_assoc_table" id="matchListTypeOptionList">'
			+ '</div>'
            
			+ '<div class="row triangle"></div>'
			+ '<div class="row matchList_drag" style="min-height:100px;background-color:#ccc;padding:10px" id="matchListOptionsDivForDrag" ondrop="dropInMatchListQuestionType(event)" ondragover="allowDropInMatchListQuestionType(event)"></div>'
			+ '<label class="requireFld" id="matchListTypeQuestionSelectedButtonError">'+messages['msg.empty']+'</label>'
			+ '</div>'
            
			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group">'
			+ '<label class="form-group">'+messages['lbl.answerexplanation']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-explanation-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<textarea class="form-control textAreaControl"  name="matchListTypeQuestionAnswerExplanation" id="matchListTypeQuestionAnswerExplanation" placeholder="'+messages['lbl.answerexplanation']+'" onkeyup="matchListTypeQuestionListner();"></textarea>'
			+ '<label class="requireFld" id="matchListTypeQuestionAnswerExplanationError">'+messages['msg.empty']+'</label>'
			+ '</div>'
            
			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group questionMark" id="matchListTypeQuestionMarkDiv">'
			+ '<label class="form-group"><sup><font color="red" size="3px">&nbsp;*</font></sup><span>'+messages['lbl.enterthemarkforthisquestion']+'</label>'
			+ '<input type="text" style="width: 50px" name="matchListTypeQuestionMark" class="form-control" id="matchListTypeQuestionMark" onkeyup="matchListTypeQuestionListner();" maxlength="2">'
			+ '<label class="requireFld" id="matchListTypeQuestionMarkError1">'+messages['msg.empty']+'</label><label class="requireFld" id="matchListTypeQuestionMarkError2">'+messages['msg.allowednumericvalue']+'</label>'
			+ '</div>'

			+ '</div>';
	$("#questiondiv").append(str);
	for (var option = 0; option < 2; option++) {
		addOptionInMatchListTypeQuestion();
	}
	addListItemsInMatchListTypeQuestion();
	CKEDITOR.inline('matchListTypeQuestion').on('change', function(e) {
		matchListTypeQuestionListner();
	});

	if (equalMarkTest == 1) {
		$("#matchListTypeQuestionMark").val(everyQuestionMark);
		$("#matchListTypeQuestionMarkDiv").hide();
	}
	
	$('#question-title-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('#question-answer-tooltip-title').tooltip({title: messages['lbl.valuestobedisplayedinthequestionlist'], trigger: "hover",placement:"right"});
	$('#question-list-tooltip-title').tooltip({title: messages['lbl.listofpossibleresponsevalues'], trigger: "hover",placement:"right"});
	$('#question-matchlist-order-tooltip-title').tooltip({title: messages['lbl.thisismatchinglistanddragtheiteminmatchinglist'], trigger: "hover",placement:"right"});
	$('#question-explanation-tooltip-title').tooltip({title: messages['lbl.questionexpdescriptionintooltip'], trigger: "hover",placement:"right"});
}

/**
 * @summary This is used to adding more answer for match List type question.
 * @returns no.
 */
function addOptionInMatchListTypeQuestion() {
	var optionNo = $("#totalOptionsInMatchListTypeQuestion").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		str = '<div id="option'
				+ optionNo
				+ 'InMatchListTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'
				+ '<div id="answer'
				+ optionNo
				+ 'divInMatchListTypeQuestion" class="text_editor_margin"><textarea name="answer'
				+ optionNo
				+ 'InMatchListTypeQuestion" id="answer'
				+ optionNo
				+ 'InMatchListTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ optionNo
				+ '" class="form-control myTextEditor"></textarea></div>'
				+ '<span class="input-group-addon" id="deleteOption'
				+ optionNo
				+ 'InMatchListTypeQuestion" onclick="deleteOptionInMatchListTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="answer'
				+ optionNo
				+ 'ErrorInMatchListTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#answerDivInMatchListTypeQuestion").append(str);
		var dragDiv = '<div class="answerDivInMatchListTypeQuestion" id="draggedOption'
				+ optionNo
				+ 'InMatchListTypeQuestion" draggable="true" ondragstart="dragInMatchListQuestionType(event)"><div class="input-group"><div class="input-group-addon"><i class="fa fa-align-justify"></i></div><div class="pull-left" id="optionDrag'
				+ optionNo + 'InMatchListTypeQuestion"></div></div></div>';
		$("#matchListOptionsDivForDrag").append(dragDiv);
		if (optionNo <= 2) {
			$("#deleteOption1InMatchListTypeQuestion").css("visibility",
					"hidden");
			$("#deleteOption2InMatchListTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteOption1InMatchListTypeQuestion").css("visibility",
					"visible");
			$("#deleteOption2InMatchListTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalOptionsInMatchListTypeQuestion").val(optionNo);
		CKEDITOR.inline('answer' + optionNo + 'InMatchListTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			matchListTypeQuestionListner();
			changeTextInDragOptionInMatchList(e.listenerData);
		}, null, optionNo);
	}
}

/**
 * @summary This function is used for allowing the drop feature on an element.
 * @param ev
 * @returns no.
 */
function allowDropInMatchListQuestionType(ev) {
	ev.preventDefault();
}

/**
 * @summary This function would be call when user start drag activity on an
 *          element.
 * @param ev
 * @returns no.
 */
function dragInMatchListQuestionType(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

/**
 * @summart This function is used to get action after drop an element.
 * @param ev
 * @returns no.
 */
function dropInMatchListQuestionType(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	if ($(ev.target).hasClass('match_list_type_ques_dropzone')) {
		var droppedElement = $(ev.target).find(
				'.answerDivInMatchListTypeQuestion').length;
		if (droppedElement == 0) {
			ev.target.appendChild(document.getElementById(data));
		}
		$("#matchListTypeQuestionSelectedButtonError").fadeOut();
	}

	else if ($(ev.target).hasClass('matchList_drag')) {
		ev.target.appendChild(document.getElementById(data));
		$("#matchListTypeQuestionSelectedButtonError").fadeOut();
	}
}

/**
 * @summary This is used to adding more list item for match list type question.
 * @returns no.
 */
function addListItemsInMatchListTypeQuestion() {
	var optionNo = $("#totalListItemsInMatchListTypeQuestion").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		str = '<div id="listItemMain'
				+ optionNo
				+ 'InMatchListTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'

				+ '<div id="listItem'
				+ optionNo
				+ 'divInMatchListTypeQuestion" class="text_editor_margin">'
				+ '<textarea name="listItem'
				+ optionNo
				+ 'InMatchListTypeQuestion" id="listItem'
				+ optionNo
				+ 'InMatchListTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ optionNo
				+ '" class="form-control myTextEditor"></textarea>'
				+ '</div>'

				+ '<span class="input-group-addon" id="deleteListItem'
				+ optionNo
				+ 'InMatchListTypeQuestion" onclick="deleteListItemInMatchListTypeQuestion(this)">'
				+ '<i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removelistitem']+'"></i>'
				+ '</span>'

				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="listItem'
				+ optionNo
				+ 'ErrorInMatchListTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#listItemsListInMatchListTypeQuestion").append(str);
		addListItemInMatchingList(optionNo);
		if (optionNo <= 1) {
			$("#deleteListItem1InMatchListTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteListItem1InMatchListTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalListItemsInMatchListTypeQuestion").val(optionNo);
		CKEDITOR.inline('listItem' + optionNo + 'InMatchListTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			matchListTypeQuestionListner();
			changeTextInMatchingList(e.listenerData);
		}, null, optionNo);
	}
}

/**
 * @summary This is used to create matching list.
 * @param optionNo
 * @returns no.
 */
function addListItemInMatchingList(optionNo) {

	var matchListItem = '<div class="match_list_type_ques_row" id="match_list_type_ques_row_'
			+ optionNo
			+ '">'
			+ '<div class="match_list_type_ques_col2">'
			+ '<div class="match_list_type_question" id="match_list_type_question_'
			+ optionNo
			+ '"></div>'
			+ '</div>'
			+ '<div class="match_list_type_ques_col1 match_list_type_ques_arrows">'
			+ '<div class="match_list_type_ques_arrow"></div>'
			+ '</div>'
			+ '<div class="match_list_type_ques_col2 match_list_type_question_res_container match_list_type_ques_dropzone" id="match_list_type_ques_dropzone_'
			+ optionNo
			+ '" ondrop="dropInMatchListQuestionType(event)" ondragover="allowDropInMatchListQuestionType(event)">'
			+ '</div>' + '</div>'
	$("#matchListTypeOptionList").append(matchListItem);
}

/**
 * @summary This is used changing the text in list which is showing for drag.
 * @param opn
 * @returns no.
 */
function changeTextInDragOptionInMatchList(opn) {
	var optionText = CKEDITOR.instances['answer' + opn
			+ 'InMatchListTypeQuestion'].getData();
	$('#optionDrag' + opn + 'InMatchListTypeQuestion').html(optionText);
}

/**
 * @summary This is used changing the text of matching list in left side.
 * @param opn
 * @returns no.
 */
function changeTextInMatchingList(listItemNo) {
	$('#match_list_type_question_' + listItemNo).html(
			CKEDITOR.instances['listItem' + listItemNo
					+ 'InMatchListTypeQuestion'].getData());
}

/**
 * @summary This is used for deleting the option of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteOptionInMatchListTypeQuestion(object) {
	var optionId = object.id;
	var arr = optionId.split('deleteOption');
	var optionNo = parseInt(arr[1].substr(0, 1));
	var totalOption = $("#totalOptionsInMatchListTypeQuestion").val();
	if (totalOption <= 2) {
	} else {
		$("#option" + optionNo + "InMatchListTypeQuestion").remove();
		$('#draggedOption' + optionNo + 'InMatchListTypeQuestion').remove();
		optionNo++;
		for (var i = optionNo; i <= totalOption; i++) {
			$("#option" + i + "InMatchListTypeQuestion").attr('id',
					"option" + (i - 1) + "InMatchListTypeQuestion");
			$("#answer" + i + "divInMatchListTypeQuestion").attr('id',
					"answer" + (i - 1) + "divInMatchListTypeQuestion");
			$("#answer" + i + "InMatchListTypeQuestion").attr({
				id : "answer" + (i - 1) + "InMatchListTypeQuestion",
				name : "answer" + (i - 1) + "InMatchListTypeQuestion",
				placeholder : messages['lbl.answernumber'] + " " + (i - 1)
			});
			$("#deleteOption" + i + "InMatchListTypeQuestion").attr('id',
					"deleteOption" + (i - 1) + "InMatchListTypeQuestion");
			$("#answer" + i + "ErrorInMatchListTypeQuestion").attr('id',
					"answer" + (i - 1) + "ErrorInMatchListTypeQuestion");
			CKEDITOR.instances['answer' + i + 'InMatchListTypeQuestion']
					.destroy();
			CKEDITOR.inline('answer' + (i - 1) + 'InMatchListTypeQuestion', {
				height : 40
			}).on('change', function(e) {
				matchListTypeQuestionListner();
				changeTextInDragOptionInMatchList(e.listenerData);
			}, null, (i - 1));
			$('#draggedOption' + i + 'InMatchListTypeQuestion').attr('id',
					'draggedOption' + (i - 1) + 'InMatchListTypeQuestion')
			$('#optionDrag' + i + 'InMatchListTypeQuestion').attr('id',
					'optionDrag' + (i - 1) + 'InMatchListTypeQuestion');
		}
		totalOption--;
		$("#totalOptionsInMatchListTypeQuestion").val(totalOption);
	}
	if (totalOption == 2) {
		$("#deleteOption1InMatchListTypeQuestion").css("visibility", "hidden");
		$("#deleteOption2InMatchListTypeQuestion").css("visibility", "hidden");
	}
}

/**
 * @summary This is used for deleting the list item of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteListItemInMatchListTypeQuestion(object) {
	var ListItemId = object.id;
	var arr = ListItemId.split('deleteListItem');
	var ListItemNo = parseInt(arr[1].substr(0, 1));
	var totalListItem = $("#totalListItemsInMatchListTypeQuestion").val();
	if (totalListItem <= 1) {
	} else {
		$("#listItemMain" + ListItemNo + "InMatchListTypeQuestion").remove();
		/**
		 * append its html in dragged options div.
		 */
		$("#matchListOptionsDivForDrag").append(
				$('#match_list_type_ques_dropzone_' + ListItemNo).html());
		$("#match_list_type_ques_row_" + ListItemNo).remove();
		ListItemNo++;
		for (var i = ListItemNo; i <= totalListItem; i++) {
			$("#listItemMain" + i + "InMatchListTypeQuestion").attr('id',
					"listItemMain" + (i - 1) + "InMatchListTypeQuestion");
			$("#listItem" + i + "divInMatchListTypeQuestion").attr('id',
					"listItem" + (i - 1) + "divInMatchListTypeQuestion");
			$("#listItem" + i + "InMatchListTypeQuestion").attr({
				id : "listItem" + (i - 1) + "InMatchListTypeQuestion",
				name : "listItem" + (i - 1) + "InMatchListTypeQuestion",
				placeholder : messages['lbl.answernumber']+" " + (i - 1)
			});
			$("#deleteListItem" + i + "InMatchListTypeQuestion").attr('id',
					"deleteListItem" + (i - 1) + "InMatchListTypeQuestion");
			$("#listItem" + i + "ErrorInMatchListTypeQuestion").attr('id',
					"listItem" + (i - 1) + "ErrorInMatchListTypeQuestion");
			CKEDITOR.instances['listItem' + i + 'InMatchListTypeQuestion']
					.destroy();
			CKEDITOR.inline('listItem' + (i - 1) + 'InMatchListTypeQuestion', {
				height : 40
			}).on('change', function(e) {
				matchListTypeQuestionListner();
				changeTextInMatchingList(e.listenerData);
			}, null, (i - 1));
			$("#match_list_type_ques_row_" + i).attr('id',
					"match_list_type_ques_row_" + (i - 1));
			$("#match_list_type_question_" + i).attr('id',
					"match_list_type_question_" + (i - 1));
			$("#match_list_type_ques_dropzone_" + i).attr('id',
					"match_list_type_ques_dropzone_" + (i - 1));
		}

		totalListItem--;
		$("#totalListItemsInMatchListTypeQuestion").val(totalListItem);
	}
	if (totalListItem == 1) {
		$("#deleteListItem1InMatchListTypeQuestion")
				.css("visibility", "hidden");
	}
}

/**
 * @summary This is used to validate match list type question data.
 * @returns {Boolean}
 */
function matchListTypeQuestionValidate() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptionsInMatchListTypeQuestion").val();
	var totalListItem = $("#totalListItemsInMatchListTypeQuestion").val();
	var questionContent = CKEDITOR.instances['matchListTypeQuestion'].getData();
	var questionContentData = ConvertHtmlToPlainTest(questionContent);
	if (questionContentData == "") {
		$("#matchListTypeQuestionEditor").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#matchListTypeQuestionTitleError").fadeIn();
		$(window).scrollTop($("#matchListTypeQuestionEditor").offset().top);
		return false;
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j
				+ 'InMatchListTypeQuestion'].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#answer" + j + "divInMatchListTypeQuestion").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#answer" + j + "ErrorInMatchListTypeQuestion").fadeIn();
			$(window)
					.scrollTop(
							$("#answer" + j + "divInMatchListTypeQuestion")
									.offset().top);
			return false;
		}

	}

	for (var j = 1; j <= totalListItem; j++) {
		var optionContent = CKEDITOR.instances['listItem' + j
				+ 'InMatchListTypeQuestion'].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#listItem" + j + "divInMatchListTypeQuestion").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#listItem" + j + "ErrorInMatchListTypeQuestion").fadeIn();
			$(window)
					.scrollTop(
							$("#listItem" + j + "divInMatchListTypeQuestion")
									.offset().top);
			return false;
		}

	}

	for (var listItem = 1; listItem <= totalListItem; listItem++) {
		var droppedItem = $('#match_list_type_ques_row_' + listItem).find(
				'.answerDivInMatchListTypeQuestion').length;
		if (droppedItem == 0) {
			$("#matchListTypeQuestionSelectedButtonError").text(messages['msg.matchingitemempty']);
			$("#matchListTypeQuestionSelectedButtonError").fadeIn();
			return false;
		}
	}
	if (equalMarkTest == 0) {
		if ($("#matchListTypeQuestionMark").val() == "") {
			$("#matchListTypeQuestionMark").css("border-color", "#c95b5b");
			$("#matchListTypeQuestionMarkError1").fadeIn();
			return false;
		} else if (!($("#matchListTypeQuestionMark").val()).match(/^\d*$/)) {
			$("#matchListTypeQuestionMark").css("border-color", "#c95b5b");
			$("#matchListTypeQuestionMarkError2").fadeIn();
			return false;
		}
	}
	/*
	 * if($("#matchListTypeQuestionAnswerExplanation").val()=="") {
	 * $("#matchListTypeQuestionAnswerExplanation").css("border-color","#c95b5b");
	 * $("#matchListTypeQuestionAnswerExplanationError").fadeIn(); return false; }
	 */
	return true;
}

/**
 * @summary This is used fadeout the validation errors from match list type
 *          question page.
 * @returns no.
 */
function matchListTypeQuestionListner() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptionsInMatchListTypeQuestion").val();
	var totalListItem = $("#totalListItemsInMatchListTypeQuestion").val();
	var questionContent = CKEDITOR.instances['matchListTypeQuestion'].getData();
	if (questionContent != "") {
		$("#matchListTypeQuestionEditor").css("border", "none");
		$("#matchListTypeQuestionTitleError").fadeOut();
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j
				+ 'InMatchListTypeQuestion'].getData();
		if (optionContent != "") {
			$("#answer" + j + "divInMatchListTypeQuestion").css("border",
					"none");
			$("#answer" + j + "ErrorInMatchListTypeQuestion").fadeOut();
		}
	}
	for (var j = 1; j <= totalListItem; j++) {
		var optionContent = CKEDITOR.instances['listItem' + j
				+ 'InMatchListTypeQuestion'].getData();
		if (optionContent != "") {
			$("#listItem" + j + "divInMatchListTypeQuestion").css("border",
					"none");
			$("#listItem" + j + "ErrorInMatchListTypeQuestion").fadeOut();
		}
	}
	if ($("#matchListTypeQuestionMark").val().length > 0) {
		$("#matchListTypeQuestionMark").css("border-color", "");
		$("#matchListTypeQuestionMarkError1").fadeOut();
		$("#matchListTypeQuestionMarkError2").fadeOut();
	}

	if ($("#matchListTypeQuestionAnswerExplanation").val().length > 0) {
		$("#matchListTypeQuestionAnswerExplanation").css("border-color", "");
		$("#matchListTypeQuestionAnswerExplanationError").fadeOut();
	}
}

/**
 * @summary This is used for showing the pop up for clear data from match list
 *          type question.
 * @returns no.
 */
function clearDataInMatchListTypeQuestionPopup() {
	$("#clearquestionAlert p").text(messages['msg.clearquestion']);
	$("#dId").attr('onclick', 'clearQuestionDataInMatchListTypeQuestion()');
	$('#clearquestionAlert').modal('show');
}

/**
 * @summary This is used for clear the all data from match list type question.
 * @returns no.
 */
function clearQuestionDataInMatchListTypeQuestion() {
	$('#clearquestionAlert').modal('hide');
	var totalOptions = $("#totalOptionsInMatchListTypeQuestion").val();
	var totalListItem = $("#totalListItemsInMatchListTypeQuestion").val();
	var totalRow = $("#totalRowsInMatchListTypeQuestion").val();
	CKEDITOR.instances['matchListTypeQuestion'].setData('');
	for (var op = 1; op <= totalOptions; op++) {
		CKEDITOR.instances['answer' + op + 'InMatchListTypeQuestion']
				.setData('');
	}
	for (var ListItemNo = 1; ListItemNo <= totalListItem; ListItemNo++) {
		CKEDITOR.instances['listItem' + ListItemNo + 'InMatchListTypeQuestion']
				.setData('');
	}
	if (equalMarkTest != 1) {
		$("#matchListTypeQuestionMark").val('');
	}
	$("#matchListTypeQuestionAnswerExplanation").val('');
	$(window).scrollTop(0);
}

/**
 * @summary function is used for save the new question data in json.
 * @returns no.
 */
function saveMatchListTypeQuestionInJson() {
	var sectionId = $("#sectionIdForAddedQues").val();
	if (matchListTypeQuestionValidate()) {
		var position = findSectionIdInList(sectionId);
		var quesData = createMatchListTypeQuestionJson(position);
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
function createMatchListTypeQuestionJson(position) {
	var quesData = [];
	var optionsList = [];
	var questionContent = CKEDITOR.instances['matchListTypeQuestion'].getData();
	var question = {
		"questionId" : parseInt(questionSectionList[position].testId + ""
				+ questionSectionList[position].sectionId
				+ (questionSectionList[position].questionList.length + 1)
				+ dynamicId),
		"questionName" : questionContent,
		"questionNo" : questionSectionList[position].questionList.length + 1,
		"questionMark" : parseInt($("#matchListTypeQuestionMark").val()),
		"negMark" : (parseInt($("#matchListTypeQuestionMark").val()) * negMark) / 100,
		"questionType" : MATCH_LIST,
		"option" : optionsList,
		"isNew" : 1, // defines that question is new created in test.
		"explanation" : $("#matchListTypeQuestionAnswerExplanation").val(),
		"isFormula" : 0,
		"answerValue" : "",
		"questionSetting" : {
			"questionUiStyle" : {
				"listItemTitles" : []
			}
		}
	};
	var totalOption = $("#totalOptionsInMatchListTypeQuestion").val();
	var totalListItem = $("#totalListItemsInMatchListTypeQuestion").val();
	var answerValue = [];
	for (var op = 1; op <= totalOption; op++) {
		var optionContent = CKEDITOR.instances['answer' + op
				+ 'InMatchListTypeQuestion'].getData();
		var optionStatus = 0;

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

	for (var listItemNo = 1; listItemNo <= totalListItem; listItemNo++) {
		var listItemTitle = CKEDITOR.instances['listItem' + listItemNo
				+ 'InMatchListTypeQuestion'].getData();
		question.questionSetting.questionUiStyle.listItemTitles
				.push(listItemTitle);
		$("#match_list_type_ques_dropzone_" + listItemNo).find(
				'.answerDivInMatchListTypeQuestion').each(function() {
			var id = $(this).attr('id');
			var arr1 = id.split("draggedOption");
			var arr2 = arr1[1].split("InMatchListTypeQuestion");
			var correctOrder = parseInt(arr2[0]);
			answerValue.push(correctOrder);
		});
	}

	question.answerValue = JSON.stringify(answerValue);
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
 * @summary This is used for fill up the question's data from json object when
 *          user enters in edit mode of match list type question.
 * @param position
 * @param questionId
 * @returns no.
 */
function fillMatchListTypeQuestionData(position, questionId) {
	for (var i = 0; i < questionSectionList[position].questionList.length; i++) {
		if (questionSectionList[position].questionList[i].questionId == questionId) {
			var table = document.getElementById('matchListTypeOptionList');
			CKEDITOR.instances['matchListTypeQuestion']
					.setData(questionSectionList[position].questionList[i].questionName);
			$("#matchListTypeQuestionMark").val(
					questionSectionList[position].questionList[i].questionMark);

			for (var j = 0; j < questionSectionList[position].questionList[i].option.length; j++) {
				if ($("#totalOptionsInMatchListTypeQuestion").val() == j) {
					addOptionInMatchListTypeQuestion();
				}
				CKEDITOR.instances['answer' + (j + 1)
						+ 'InMatchListTypeQuestion']
						.setData(questionSectionList[position].questionList[i].option[j].optionName);
				$('#optionDrag' + (j + 1) + 'InMatchListTypeQuestion')
						.html(
								questionSectionList[position].questionList[i].option[j].optionName);
			}

			var questionSetting = questionSectionList[position].questionList[i].questionSetting;

			for (var listItemNo = 0; listItemNo < questionSetting.questionUiStyle.listItemTitles.length; listItemNo++) {
				if ($("#totalListItemsInMatchListTypeQuestion").val() == listItemNo) {
					addListItemsInMatchListTypeQuestion();
				}
				CKEDITOR.instances['listItem' + (listItemNo + 1)
						+ 'InMatchListTypeQuestion']
						.setData(questionSetting.questionUiStyle.listItemTitles[listItemNo]);
				$("#match_list_type_question_" + (listItemNo + 1))
						.html(
								questionSetting.questionUiStyle.listItemTitles[listItemNo]);

			}

			var answerValue = JSON
					.parse(questionSectionList[position].questionList[i].answerValue);
			for (var x = 0; x < answerValue.length; x++) {
				var answerOrder = answerValue[x];
				$("#match_list_type_ques_dropzone_" + (x + 1)).append(
						document.getElementById("draggedOption" + answerOrder
								+ "InMatchListTypeQuestion"));
			}
			$("#matchListTypeQuestionAnswerExplanation").val(
					questionSectionList[position].questionList[i].explanation);
			$("#submitQuestion").text(messages['lbl.update']);
			var updateAction = 'updateMatchListQuestionTypeJson("' + position
					+ '","' + questionId + '")';
			$("#submitQuestion").attr('onclick', updateAction);
		}
	}
	$('.imgset img').css({
		'max-width' : '400px',
		'height' : 'auto'
	});
}

/**
 * @summary This is used for update the json for match list question type after
 *          update the user.
 * @param position
 * @param questionId
 * @returns no.
 */
function updateMatchListQuestionTypeJson(position, questionId) {
	if (matchListTypeQuestionValidate()) {
		for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
			if (questionSectionList[position].questionList[q].questionId == questionId) {
				var optionsList = [];
				var subOptionsList = [];
				var questionContent = CKEDITOR.instances['matchListTypeQuestion']
						.getData();
				var question = {
					"questionId" : questionId,
					"questionName" : questionContent,
					"questionNo" : (q + 1),
					"questionMark" : parseInt($("#matchListTypeQuestionMark")
							.val()),
					"negMark" : (parseInt($("#matchListTypeQuestionMark").val()) * negMark) / 100,
					"questionType" : questionSectionList[position].questionList[q].questionType,
					"option" : optionsList,
					"isNew" : questionSectionList[position].questionList[q].isNew,
					"explanation" : $("#matchListTypeQuestionAnswerExplanation")
							.val(),
					"isParent" : questionSectionList[position].questionList[q].isParent,
					"isFormula" : 0,
					"answerValue" : "",
					"questionSetting" : {
						"questionUiStyle" : {
							"listItemTitles" : []
						}
					}
				};
				var totalOption = $("#totalOptionsInMatchListTypeQuestion")
						.val();
				var totalListItem = $("#totalListItemsInMatchListTypeQuestion")
						.val();
				var answerValue = [];

				for (var op = 1; op <= totalOption; op++) {
					var optionContent = CKEDITOR.instances['answer' + op
							+ 'InMatchListTypeQuestion'].getData();
					var optionStatus = 0;
					$('.imgset img').css({
						'max-width' : '400px',
						'height' : 'auto'
					});
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

				for (var listItemNo = 1; listItemNo <= totalListItem; listItemNo++) {
					var listItemTitle = CKEDITOR.instances['listItem'
							+ listItemNo + 'InMatchListTypeQuestion'].getData();
					question.questionSetting.questionUiStyle.listItemTitles
							.push(listItemTitle);
					$("#match_list_type_ques_dropzone_" + listItemNo).find(
							'.answerDivInMatchListTypeQuestion').each(
							function() {
								var id = $(this).attr('id');
								var arr1 = id.split("draggedOption");
								var arr2 = arr1[1]
										.split("InMatchListTypeQuestion");
								var correctOrder = parseInt(arr2[0]);
								answerValue.push(correctOrder);
							});
				}

				question.answerValue = JSON.stringify(answerValue);

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
				$(obj).html(
						CKEDITOR.instances['matchListTypeQuestion'].getData());
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