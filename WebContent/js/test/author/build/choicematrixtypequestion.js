/**
 * @summary This file would be used for adding and editing choice matrix type question.
 * @author ankur
 * @date 30 Aug 2016
 */

/**
 * @summary function is used to create the choice matrix type question.
 * @returns no.
 */
function addChoiceMatrixTypeQuestion() {
	$("#questiondiv").empty();
	var str = '<div class="col-xs-12">'

			+ '<div class="col-xs-12 question form-group"><label id="choiceMatrixTypeQuestionTitle"><sup><font color="red" size="3px">*</font></sup>'+ messages['lbl.question'] +' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-title-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="choiceMatrixTypeQuestionEditor" class="text_editor_margin"><textarea name="choiceMatrixTypeQuestion" id="choiceMatrixTypeQuestion" class="form-control myTextEditor" placeholder="'+messages['placeholder.question']+'"></textarea></div>'
			+ '<label class="requireFld" id="choiceMatrixTypeQuestionTitleError">'+messages['msg.empty']+'</label>'
			+ '</div>'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.answer']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-answer-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="answerDivInchoiceMatrixTypeQuestion">'
			+ '<input type="hidden" id="totalOptionsInchoiceMatrixTypeQuestion" name="totalOptionsInchoiceMatrixTypeQuestion" value="0">'
			+ '<input type="hidden" id="totalSubOptionsInchoiceMatrixTypeQuestion" name="totalSubOptionsInchoiceMatrixTypeQuestion" value="0">'
			+ '</div><!-- /.totalOptionsInchoiceMatrixTypeQuestion -->'

			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="addMoreOptionsInchoiceMatrixTypeQuestion" onclick="addOptionInchoiceMatrixTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addanswer']+'</span></button>'
			+ '&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-flat button-width-large" id="clearDataInchoiceMatrixTypeQuestion" onclick="clearDataInchoiceMatrixTypeQuestionPopup();"><span><i class="fa fa-trash-o"></i> '+ messages['lbl.clearquestion']+'</span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.suboptions']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-suboption-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="subOptionsListInchoiceMatrixTypeQuestion"></div>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="addMoreSubOptionsInchoiceMatrixTypeQuestion" onclick="addSubOptionInchoiceMatrixTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addsuboption']+'</span></button>'
			+ '</div>'

			+ '<div class="col-sm-12 form-group"><label>'+messages['lbl.choicematrixtable']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-choice-matrix-table-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div class="col-sm-12 form-group">'
			+ '<table class="table" id="choiceMatrixOptionTable">'
			+ '<thead>'
			+ '<tr>'
			+ '<th></th>'
			+ '</tr>'
			+ '</thead>'
			+ '<tbody>'
			+ '</tbody>'
			+ '</table>'
			+ '<label class="requireFld" id="choiceMatrixTypeQuestionSelectedButtonError">'+messages['msg.empty']+'</label>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<label class="form-group">'+messages['lbl.answerexplanation']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-explanation-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<textarea class="form-control textAreaControl"  name="choiceMatrixTypeQuestionAnswerExplanation" id="choiceMatrixTypeQuestionAnswerExplanation" placeholder="'+messages['lbl.answerexplanation']+'" onkeyup="choiceMatrixTypeQuestionListner();"></textarea>'
			+ '<label class="requireFld" id="choiceMatrixTypeQuestionAnswerExplanationError">'+messages['msg.empty']+'</label>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group"></div>'
			+ '<div class="col-xs-12 form-group questionMark" id="choiceMatrixTypeQuestionMarkDiv">'
			+ '<label class="form-group"><sup><font color="red" size="3px">&nbsp;*</font></sup><span>'+messages['lbl.enterthemarkforthisquestion']+'</label>'
			+ '<input type="text" style="width: 50px" name="choiceMatrixTypeQuestionMark" class="form-control" id="choiceMatrixTypeQuestionMark" onkeyup="choiceMatrixTypeQuestionListner();" maxlength="2">'
			+ '<label class="requireFld" id="choiceMatrixTypeQuestionMarkError1">'+messages['msg.empty']+'</label><label class="requireFld" id="choiceMatrixTypeQuestionMarkError2">'+messages['msg.allowednumericvalue']+'</label>'
			+ '</div>'

			+ '</div>';
	$("#questiondiv").append(str);
	for (var option = 0; option < 2; option++) {
		addOptionInchoiceMatrixTypeQuestion(option + 1);
		addSubOptionInchoiceMatrixTypeQuestion(option + 1);
		var table = document.getElementById('choiceMatrixOptionTable');
		$(table.rows[option + 1].cells[0])
				.html('['+ messages['lbl.option']+' ' + (option + 1) + ']');
		$(table.rows[0].cells[option + 1]).html(
				'['+ messages['lbl.suboption']+' ' + (option + 1) + ']');
	}
	CKEDITOR.inline('choiceMatrixTypeQuestion').on('change', function(e) {
		choiceMatrixTypeQuestionListner();
	});

	if (equalMarkTest == 1) {
		$("#choiceMatrixTypeQuestionMark").val(everyQuestionMark);
		$("#choiceMatrixTypeQuestionMarkDiv").hide();
	}
	
	$('#question-title-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('#question-answer-tooltip-title').tooltip({title: messages['lbl.listofmatrixitems'], trigger: "hover",placement:"right"});
	$('#question-suboption-tooltip-title').tooltip({title: messages['lbl.choiematrixsuboptiondesc'], trigger: "hover",placement:"right"});
	$('#question-choice-matrix-table-tooltip-title').tooltip({title: messages['lbl.choicematrixtabledescriptionforselectedoption'], trigger: "hover",placement:"right"});
	$('#question-explanation-tooltip-title').tooltip({title: messages['lbl.questionexpdescriptionintooltip'], trigger: "hover",placement:"right"});
	
}

/**
 * @summary This is used to adding more answer for choice matrix type question.
 * @returns no.
 */
function addOptionInchoiceMatrixTypeQuestion() {
	var optionNo = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		str = '<div id="option'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'
				+ '<div id="answer'
				+ optionNo
				+ 'divInchoiceMatrixTypeQuestion" class="text_editor_margin"><textarea name="answer'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion" id="answer'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ optionNo
				+ '" class="form-control myTextEditor"></textarea></div>'
				+ '<span class="input-group-addon" id="deleteOption'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion" onclick="deleteOptionInchoiceMatrixTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="answer'
				+ optionNo
				+ 'ErrorInchoiceMatrixTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#answerDivInchoiceMatrixTypeQuestion").append(str);
		var headerCount = $('#choiceMatrixOptionTable >thead>tr>th').length;
		var tableData = "";
		for (var th = 1; th < headerCount; th++) {
			tableData = tableData
					+ '<td class="text-align"><input class="choiceMatrixButton" type="radio" id="optionChoice'
					+ optionNo
					+ 'InChoiceMatrixTypeQuestion'
					+ th
					+ '" name="optionChoice'
					+ optionNo
					+ 'InChoiceMatrixTypeQuestion" onclick="choiceMatrixTypeQuestionListner();"></td>';
		}
		var tbodyRowData = '<tr><td class="imgset"></td>' + tableData + '</tr>'
		$("#choiceMatrixOptionTable>tbody").append(tbodyRowData);
		if (optionNo <= 2) {
			$("#deleteOption1InchoiceMatrixTypeQuestion").css("visibility",
					"hidden");
			$("#deleteOption2InchoiceMatrixTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteOption1InchoiceMatrixTypeQuestion").css("visibility",
					"visible");
			$("#deleteOption2InchoiceMatrixTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalOptionsInchoiceMatrixTypeQuestion").val(optionNo);
		CKEDITOR.inline('answer' + optionNo + 'InchoiceMatrixTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			choiceMatrixTypeQuestionListner();
			changeTextInchoiceMatrixTypeTableBody(e.listenerData);
		}, null, optionNo);
	}
}

/**
 * @summary This is used to adding more answer for choice matrix type question.
 * @returns no.
 */
function addSubOptionInchoiceMatrixTypeQuestion() {
	var optionNo = $("#totalSubOptionsInchoiceMatrixTypeQuestion").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		str = '<div id="subOption'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'
				+ '<div id="subAnswer'
				+ optionNo
				+ 'divInchoiceMatrixTypeQuestion" class="text_editor_margin"><textarea name="subAnswer'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion" id="subAnswer'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ optionNo
				+ '" class="form-control myTextEditor"></textarea></div>'
				+ '<span class="input-group-addon" id="deleteSubOption'
				+ optionNo
				+ 'InchoiceMatrixTypeQuestion" onclick="deleteSubOptionInchoiceMatrixTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="subAnswer'
				+ optionNo
				+ 'ErrorInchoiceMatrixTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#subOptionsListInchoiceMatrixTypeQuestion").append(str);
		var rowCount = $('#choiceMatrixOptionTable>tbody>tr').length;
		var table = document.getElementById('choiceMatrixOptionTable');
		for (var tr = 1; tr <= rowCount; tr++) {
			$(table.rows[tr])
					.append(
							'<td class="text-align"><input type="radio" class="choiceMatrixButton" id="optionChoice'
									+ tr
									+ 'InChoiceMatrixTypeQuestion'
									+ optionNo
									+ '" name="optionChoice'
									+ tr
									+ 'InChoiceMatrixTypeQuestion" onclick="choiceMatrixTypeQuestionListner();"></td>');
		}
		var tbodyHeadData = '<th class="text-align imgset"></th>'
		$("#choiceMatrixOptionTable>thead>tr").append(tbodyHeadData);
		if (optionNo <= 2) {
			$("#deleteSubOption1InchoiceMatrixTypeQuestion").css("visibility",
					"hidden");
			$("#deleteSubOption2InchoiceMatrixTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteSubOption1InchoiceMatrixTypeQuestion").css("visibility",
					"visible");
			$("#deleteSubOption2InchoiceMatrixTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalSubOptionsInchoiceMatrixTypeQuestion").val(optionNo);
		CKEDITOR.inline('subAnswer' + optionNo + 'InchoiceMatrixTypeQuestion',
				{
					height : 40
				}).on('change', function(e) {
			choiceMatrixTypeQuestionListner();
			changeTextInchoiceMatrixTypeTableHead(e.listenerData);
		}, null, optionNo);
	}
}

/**
 * @summary This is used changing the text in list which is showing for order.
 * @param opn
 * @returns no.
 */
function changeTextInchoiceMatrixTypeTableBody(opn) {
	var table = document.getElementById('choiceMatrixOptionTable');
	$(table.rows[opn].cells[0]).html(
			CKEDITOR.instances['answer' + opn + 'InchoiceMatrixTypeQuestion']
					.getData());
	$('.imgset img').css({
		'max-width' : '100%',
		'height' : 'auto'
	});
}

/**
 * @summary This is used changing the text in list which is showing for order.
 * @param opn
 * @returns no.
 */
function changeTextInchoiceMatrixTypeTableHead(opn) {
	var table = document.getElementById('choiceMatrixOptionTable');
	$(table.rows[0].cells[opn])
			.html(
					CKEDITOR.instances['subAnswer' + opn
							+ 'InchoiceMatrixTypeQuestion'].getData());
	$('.imgset img').css({
		'max-width' : '100%',
		'height' : 'auto'
	});
}

/**
 * @summary This is used for deleting the option of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteOptionInchoiceMatrixTypeQuestion(object) {
	var optionId = object.id;
	var arr = optionId.split('deleteOption');
	var optionNo = parseInt(arr[1].substr(0, 1));
	var totalOption = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	if (totalOption <= 2) {
	} else {
		$("#option" + optionNo + "InchoiceMatrixTypeQuestion").remove();
		var table = document.getElementById('choiceMatrixOptionTable');
		$(table.rows[optionNo]).remove();
		optionNo++;
		for (var i = optionNo; i <= totalOption; i++) {
			$("#option" + i + "InchoiceMatrixTypeQuestion").attr('id',
					"option" + (i - 1) + "InchoiceMatrixTypeQuestion");
			$("#answer" + i + "divInchoiceMatrixTypeQuestion").attr('id',
					"answer" + (i - 1) + "divInchoiceMatrixTypeQuestion");
			$("#answer" + i + "InchoiceMatrixTypeQuestion").attr({
				id : "answer" + (i - 1) + "InchoiceMatrixTypeQuestion",
				name : "answer" + (i - 1) + "InchoiceMatrixTypeQuestion",
				placeholder : messages['lbl.answernumber'] + " " + (i - 1)
			});
			$("#deleteOption" + i + "InchoiceMatrixTypeQuestion").attr('id',
					"deleteOption" + (i - 1) + "InchoiceMatrixTypeQuestion");
			$("#answer" + i + "ErrorInchoiceMatrixTypeQuestion").attr('id',
					"answer" + (i - 1) + "ErrorInchoiceMatrixTypeQuestion");
			CKEDITOR.instances['answer' + i + 'InchoiceMatrixTypeQuestion']
					.destroy();
			CKEDITOR.inline('answer' + (i - 1) + 'InchoiceMatrixTypeQuestion',
					{
						height : 40
					}).on('change', function(e) {
				choiceMatrixTypeQuestionListner();
				changeTextInchoiceMatrixTypeTableBody(e.listenerData);
			}, null, (i - 1));
			var headColumnCount = $('#choiceMatrixOptionTable>thead>tr>th').length;
			/**
			 * change id and name according to row order.
			 */
			for (var td = 1; td <= headColumnCount; td++) {
				$('#optionChoice' + i + 'InChoiceMatrixTypeQuestion' + td)
						.attr(
								{
									id : 'optionChoice' + (i - 1)
											+ 'InChoiceMatrixTypeQuestion' + td,
									name : 'optionChoice' + (i - 1)
											+ 'InChoiceMatrixTypeQuestion'
								});
			}
		}
		totalOption--;
		$("#totalOptionsInchoiceMatrixTypeQuestion").val(totalOption);
	}
	if (totalOption == 2) {
		$("#deleteOption1InchoiceMatrixTypeQuestion").css("visibility",
				"hidden");
		$("#deleteOption2InchoiceMatrixTypeQuestion").css("visibility",
				"hidden");
	}
}

/**
 * @summary This is used for deleting the option of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteSubOptionInchoiceMatrixTypeQuestion(object) {
	var optionId = object.id;
	var arr = optionId.split('deleteSubOption');
	var optionNo = parseInt(arr[1].substr(0, 1));
	var totalOption = $("#totalSubOptionsInchoiceMatrixTypeQuestion").val();
	var totalRow = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	if (totalOption <= 2) {
	} else {
		$("#subOption" + optionNo + "InchoiceMatrixTypeQuestion").remove();
		var table = document.getElementById('choiceMatrixOptionTable');
		optionNo++;
		for (var i = optionNo; i <= totalOption; i++) {
			$("#subOption" + i + "InchoiceMatrixTypeQuestion").attr('id',
					"subOption" + (i - 1) + "InchoiceMatrixTypeQuestion");
			$("#subAnswer" + i + "divInchoiceMatrixTypeQuestion").attr('id',
					"subAnswer" + (i - 1) + "divInchoiceMatrixTypeQuestion");
			$("#subAnswer" + i + "InchoiceMatrixTypeQuestion").attr({
				id : "subAnswer" + (i - 1) + "InchoiceMatrixTypeQuestion",
				name : "subAnswer" + (i - 1) + "InchoiceMatrixTypeQuestion",
				placeholder : messages['lbl.answernumber'] + " " + (i - 1)
			});
			$("#deleteSubOption" + i + "InchoiceMatrixTypeQuestion").attr('id',
					"deleteSubOption" + (i - 1) + "InchoiceMatrixTypeQuestion");
			$("#subAnswer" + i + "ErrorInchoiceMatrixTypeQuestion").attr('id',
					"subAnswer" + (i - 1) + "ErrorInchoiceMatrixTypeQuestion");
			CKEDITOR.instances['subAnswer' + i + 'InchoiceMatrixTypeQuestion']
					.destroy();
			CKEDITOR.inline(
					'subAnswer' + (i - 1) + 'InchoiceMatrixTypeQuestion', {
						height : 40
					}).on('change', function(e) {
				choiceMatrixTypeQuestionListner();
				changeTextInchoiceMatrixTypeTableHead(e.listenerData);
			}, null, (i - 1));
		}
		/**
		 * remove cell from row of table header.
		 */
		$(table.rows[0].cells[optionNo - 1]).remove();
		/**
		 * itearte on row of table body.
		 */
		for (var tr = 1; tr <= totalRow; tr++) {
			/**
			 * remove cell from row of table body.
			 */
			$(table.rows[tr].cells[optionNo - 1]).remove();
			/**
			 * iterate on every cell of table row.
			 */
			for (var td = optionNo; td <= totalOption; td++) {
				/**
				 * change id of input type.
				 */
				$('#optionChoice' + tr + 'InChoiceMatrixTypeQuestion' + td)
						.attr(
								{
									id : 'optionChoice' + tr
											+ 'InChoiceMatrixTypeQuestion'
											+ (td - 1)
								});
			}
		}
		totalOption--;
		$("#totalSubOptionsInchoiceMatrixTypeQuestion").val(totalOption);
	}
	if (totalOption == 2) {
		$("#deleteSubOption1InchoiceMatrixTypeQuestion").css("visibility",
				"hidden");
		$("#deleteSubOption2InchoiceMatrixTypeQuestion").css("visibility",
				"hidden");
	}
}

/**
 * @summary This is used to validate choice matrix type question data.
 * @returns {Boolean}
 */
function choiceMatrixTypeQuestionValidate() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	var totalSubOptions = $("#totalSubOptionsInchoiceMatrixTypeQuestion").val();
	var questionContent = CKEDITOR.instances['choiceMatrixTypeQuestion']
			.getData();
	var questionContentData = ConvertHtmlToPlainTest(questionContent);
	if (questionContentData == "") {
		$("#choiceMatrixTypeQuestionEditor").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#choiceMatrixTypeQuestionTitleError").fadeIn();
		$(window).scrollTop($("#choiceMatrixTypeQuestionEditor").offset().top);
		return false;
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j
				+ 'InchoiceMatrixTypeQuestion'].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#answer" + j + "divInchoiceMatrixTypeQuestion").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#answer" + j + "ErrorInchoiceMatrixTypeQuestion").fadeIn();
			$(window)
					.scrollTop(
							$("#answer" + j + "divInchoiceMatrixTypeQuestion")
									.offset().top);
			return false;
		}

	}

	for (var j = 1; j <= totalSubOptions; j++) {
		var optionContent = CKEDITOR.instances['subAnswer' + j
				+ 'InchoiceMatrixTypeQuestion'].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#subAnswer" + j + "divInchoiceMatrixTypeQuestion").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#subAnswer" + j + "ErrorInchoiceMatrixTypeQuestion").fadeIn();
			$(window).scrollTop(
					$("#subAnswer" + j + "divInchoiceMatrixTypeQuestion")
							.offset().top);
			return false;
		}

	}
	var table = document.getElementById('choiceMatrixOptionTable');
	for (var tr = 1; tr <= totaloptions; tr++) {
		var length = $(table.rows[tr]).find('.choiceMatrixButton:checked').length
		if (length == 0) {
			$("#choiceMatrixTypeQuestionSelectedButtonError").text(messages['msg.suboption.required']);
			$("#choiceMatrixTypeQuestionSelectedButtonError").fadeIn();
			return false;
		}
	}
	if (equalMarkTest == 0) {
		if ($("#choiceMatrixTypeQuestionMark").val() == "") {
			$("#choiceMatrixTypeQuestionMark").css("border-color", "#c95b5b");
			$("#choiceMatrixTypeQuestionMarkError1").fadeIn();
			return false;
		} else if (!($("#choiceMatrixTypeQuestionMark").val()).match(/^\d*$/)) {
			$("#choiceMatrixTypeQuestionMark").css("border-color", "#c95b5b");
			$("#choiceMatrixTypeQuestionMarkError2").fadeIn();
			return false;
		}
	}
	/*
	 * if($("#choiceMatrixTypeQuestionAnswerExplanation").val()=="") {
	 * $("#choiceMatrixTypeQuestionAnswerExplanation").css("border-color","#c95b5b");
	 * $("#choiceMatrixTypeQuestionAnswerExplanationError").fadeIn(); return
	 * false; }
	 */
	return true;
}

/**
 * @summary This is used fadeout the validation errors from choice matrix type
 *          question page.
 * @returns no.
 */
function choiceMatrixTypeQuestionListner() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	var totalSubOptions = $("#totalSubOptionsInchoiceMatrixTypeQuestion").val();
	var questionContent = CKEDITOR.instances['choiceMatrixTypeQuestion']
			.getData();
	if (questionContent != "") {
		$("#choiceMatrixTypeQuestionEditor").css("border-color", "#7ac17d");
		$("#choiceMatrixTypeQuestionTitleError").fadeOut();
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j
				+ 'InchoiceMatrixTypeQuestion'].getData();
		if (optionContent != "") {
			$("#answer" + j + "divInchoiceMatrixTypeQuestion").css(
					"border-color", "#7ac17d");
			$("#answer" + j + "ErrorInchoiceMatrixTypeQuestion").fadeOut();
		}
	}
	for (var j = 1; j <= totalSubOptions; j++) {
		var optionContent = CKEDITOR.instances['subAnswer' + j
				+ 'InchoiceMatrixTypeQuestion'].getData();
		if (optionContent != "") {
			$("#subAnswer" + j + "divInchoiceMatrixTypeQuestion").css(
					"border-color", "#7ac17d");
			$("#subAnswer" + j + "ErrorInchoiceMatrixTypeQuestion").fadeOut();
		}
	}
	var length = $('#choiceMatrixOptionTable .choiceMatrixButton:checked').length;
	if (length > 0) {
		$("#choiceMatrixTypeQuestionSelectedButtonError").fadeOut();
	}
	if ($("#choiceMatrixTypeQuestionMark").val().length > 0) {
		$("#choiceMatrixTypeQuestionMark").css("border-color", "#7ac17d");
		$("#choiceMatrixTypeQuestionMarkError1").fadeOut();
		$("#choiceMatrixTypeQuestionMarkError2").fadeOut();
	}

	if ($("#choiceMatrixTypeQuestionAnswerExplanation").val().length > 0) {
		$("#choiceMatrixTypeQuestionAnswerExplanation").css("border-color",
				"#7ac17d");
		$("#choiceMatrixTypeQuestionAnswerExplanationError").fadeOut();
	}
}

/**
 * @summary This is used for showing the pop up for clear data from choice
 *          matrix type question.
 * @returns no.
 */
function clearDataInchoiceMatrixTypeQuestionPopup() {
	$("#clearquestionAlert p").text(messages['msg.clearquestion']);
	$("#dId").attr('onclick', 'clearQuestionDataInchoiceMatrixTypeQuestion()');
	$('#clearquestionAlert').modal('show');
}

/**
 * @summary This is used for clear the all data from choice matrix type
 *          question.
 * @returns no.
 */
function clearQuestionDataInchoiceMatrixTypeQuestion() {
	$('#clearquestionAlert').modal('hide');
	var totalOptions = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	var totalSubOptions = $("#totalSubOptionsInchoiceMatrixTypeQuestion").val();
	CKEDITOR.instances['choiceMatrixTypeQuestion'].setData('');
	for (var op = 1; op <= totalOptions; op++) {
		CKEDITOR.instances['answer' + op + 'InchoiceMatrixTypeQuestion']
				.setData('');
	}
	for (var subOp = 1; subOp <= totalSubOptions; subOp++) {
		CKEDITOR.instances['subAnswer' + subOp + 'InchoiceMatrixTypeQuestion']
				.setData('');
	}
	if (equalMarkTest != 1) {
		$("#choiceMatrixTypeQuestionMark").val('');
	}
	$("#choiceMatrixOptionTable .choiceMatrixButton").prop('checked', false);
	$("#choiceMatrixTypeQuestionAnswerExplanation").val('');
	$(window).scrollTop(0);
}

/**
 * @summary function is used for save the new question data in json.
 * @returns no.
 */
function savechoiceMatrixTypeQuestionInJson() {
	var sectionId = $("#sectionIdForAddedQues").val();
	if (choiceMatrixTypeQuestionValidate()) {
		var position = findSectionIdInList(sectionId);
		var quesData = createchoiceMatrixTypeQuestionJson(position);
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
function createchoiceMatrixTypeQuestionJson(position) {
	var quesData = [];
	var optionsList = [];
	var subOptionsList = [];
	var questionContent = CKEDITOR.instances['choiceMatrixTypeQuestion']
			.getData();
	var question = {
		"questionId" : parseInt(questionSectionList[position].testId + ""
				+ questionSectionList[position].sectionId
				+ (questionSectionList[position].questionList.length + 1)
				+ dynamicId),
		"questionName" : questionContent,
		"questionNo" : questionSectionList[position].questionList.length + 1,
		"questionMark" : parseInt($("#choiceMatrixTypeQuestionMark").val()),
		"negMark" : (parseInt($("#choiceMatrixTypeQuestionMark").val()) * negMark) / 100,
		"questionType" : CHOICE_MATRIX_TYPE,
		"option" : optionsList,
		"subOption" : subOptionsList,
		"isNew" : 1, // defines that question is new created in test.
		"explanation" : $("#choiceMatrixTypeQuestionAnswerExplanation").val(),
		"isFormula" : 0,
		"answerValue" : ""
	};
	var totalOption = $("#totalOptionsInchoiceMatrixTypeQuestion").val();
	var totalSubOption = $("#totalSubOptionsInchoiceMatrixTypeQuestion").val();
	var answerValue = [];
	for (var op = 1; op <= totalOption; op++) {
		var optionContent = CKEDITOR.instances['answer' + op
				+ 'InchoiceMatrixTypeQuestion'].getData();
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
	for (var subop = 1; subop <= totalSubOption; subop++) {
		var subOptionContent = CKEDITOR.instances['subAnswer' + subop
				+ 'InchoiceMatrixTypeQuestion'].getData();
		var subOptions = {
			"optionId" : parseInt(questionSectionList[position].testId + ""
					+ questionSectionList[position].sectionId
					+ (questionSectionList[position].questionList.length + 1)
					+ subop),
			"optionName" : subOptionContent,
			"optionOrder" : subop,
		};
		subOptionsList.push(subOptions);
	}
	for (var tr = 1; tr <= totalOption; tr++) {
		var subOption = [];
		for (var td = 1; td <= totalSubOption; td++) {
			if ($('#optionChoice' + tr + 'InChoiceMatrixTypeQuestion' + td)[0].checked) {
				subOption.push(td);
			}
		}
		answerValue.push(subOption);
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
 *          user enters in edit mode of choice matrix type question.
 * @param position
 * @param questionId
 * @returns no.
 */
function fillchoiceMatrixTypeQuestionData(position, questionId) {
	for (var i = 0; i < questionSectionList[position].questionList.length; i++) {
		if (questionSectionList[position].questionList[i].questionId == questionId) {
			var table = document.getElementById('choiceMatrixOptionTable');
			CKEDITOR.instances['choiceMatrixTypeQuestion']
					.setData(questionSectionList[position].questionList[i].questionName);
			$("#choiceMatrixTypeQuestionMark").val(
					questionSectionList[position].questionList[i].questionMark);

			for (var j = 0; j < questionSectionList[position].questionList[i].option.length; j++) {
				if ($("#totalOptionsInchoiceMatrixTypeQuestion").val() == j) {
					addOptionInchoiceMatrixTypeQuestion();
				}
				CKEDITOR.instances['answer' + (j + 1)
						+ 'InchoiceMatrixTypeQuestion']
						.setData(questionSectionList[position].questionList[i].option[j].optionName);
				$(table.rows[j + 1].cells[0])
						.html(
								questionSectionList[position].questionList[i].option[j].optionName);
			}
			for (var subop = 0; subop < questionSectionList[position].questionList[i].subOption.length; subop++) {
				if ($("#totalSubOptionsInchoiceMatrixTypeQuestion").val() == subop) {
					addSubOptionInchoiceMatrixTypeQuestion();
				}
				CKEDITOR.instances['subAnswer' + (subop + 1)
						+ 'InchoiceMatrixTypeQuestion']
						.setData(questionSectionList[position].questionList[i].subOption[subop].optionName);
				$(table.rows[0].cells[subop + 1])
						.html(
								questionSectionList[position].questionList[i].subOption[subop].optionName);
			}
			var answerValue = JSON
					.parse(questionSectionList[position].questionList[i].answerValue);
			for (var tr = 0; tr < answerValue.length; tr++) {
				for (td = 0; td < answerValue[tr].length; td++) {
					$(
							'#optionChoice' + (tr + 1)
									+ 'InChoiceMatrixTypeQuestion'
									+ answerValue[tr][td])
							.prop('checked', true);
				}
			}
			$("#choiceMatrixTypeQuestionAnswerExplanation").val(
					questionSectionList[position].questionList[i].explanation);
			$("#submitQuestion").text(messages['lbl.update']);
			var updateAction = 'updatechoiceMatrixQuestionTypeJson("'
					+ position + '","' + questionId + '")';
			$("#submitQuestion").attr('onclick', updateAction);
		}
	}
	$('.imgset img').css({
		'max-width' : '400px',
		'height' : 'auto'
	});
}

/**
 * @summary This is used for finding the postion of a item in a particular
 *          array.
 * @param array
 * @param item
 * @returns {Number}
 * @returns no.
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
 * @summary This is used for update the json for choice matrix question type
 *          after update the user..
 * @param position
 * @param questionId
 * @returns no.
 */
function updatechoiceMatrixQuestionTypeJson(position, questionId) {
	if (choiceMatrixTypeQuestionValidate()) {
		for (var q = 0; q < questionSectionList[position].questionList.length; q++) {
			if (questionSectionList[position].questionList[q].questionId == questionId) {
				var optionsList = [];
				var subOptionsList = [];
				var questionContent = CKEDITOR.instances['choiceMatrixTypeQuestion']
						.getData();
				var question = {
					"questionId" : questionId,
					"questionName" : questionContent,
					"questionNo" : (q + 1),
					"questionMark" : parseInt($("#choiceMatrixTypeQuestionMark")
							.val()),
					"negMark" : (parseInt($("#choiceMatrixTypeQuestionMark")
							.val()) * negMark) / 100,
					"questionType" : CHOICE_MATRIX_TYPE,
					"option" : optionsList,
					"subOption" : subOptionsList,
					"isNew" : questionSectionList[position].questionList[q].isNew,
					"explanation" : $(
							"#choiceMatrixTypeQuestionAnswerExplanation").val(),
					"isParent" : questionSectionList[position].questionList[q].isParent,
					"isFormula" : 0,
					"answerValue" : ""
				};
				var totalOption = $("#totalOptionsInchoiceMatrixTypeQuestion")
						.val();
				var totalSubOption = $(
						"#totalSubOptionsInchoiceMatrixTypeQuestion").val();
				var answerValue = [];

				for (var op = 1; op <= totalOption; op++) {
					var optionContent = CKEDITOR.instances['answer' + op
							+ 'InchoiceMatrixTypeQuestion'].getData();
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

				for (var subop = 1; subop <= totalSubOption; subop++) {
					var subOptionContent = CKEDITOR.instances['subAnswer'
							+ subop + 'InchoiceMatrixTypeQuestion'].getData();
					var subOptions = {
						"optionId" : parseInt(questionSectionList[position].testId
								+ ""
								+ questionSectionList[position].sectionId
								+ (questionSectionList[position].questionList.length + 1)
								+ subop),
						"optionName" : subOptionContent,
						"optionOrder" : subop,
					};
					subOptionsList.push(subOptions);
				}
				for (var tr = 1; tr <= totalOption; tr++) {
					var subOption = [];
					for (var td = 1; td <= totalSubOption; td++) {
						if ($('#optionChoice' + tr
								+ 'InChoiceMatrixTypeQuestion' + td)[0].checked) {
							subOption.push(td);
						}
					}
					answerValue.push(subOption);
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
						CKEDITOR.instances['choiceMatrixTypeQuestion']
								.getData());
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