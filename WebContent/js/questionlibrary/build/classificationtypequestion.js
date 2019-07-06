/**
 * @summary This file would be used for adding and editing classification type question.
 * @author ankur
 * @date 10 Sep 2016
 */

/**
 * @summary function is used to create the classification type question.
 * @returns no.
 */
function addClassificationTypeQuestion() {
	$("#questiondiv").empty();
	var str = '<div class="col-xs-12">'

			+ '<div class="col-xs-12 question form-group"><label id="classificationTypeQuestionTitle"><sup><font color="red" size="3px">*</font></sup>'+ messages['lbl.question'] +' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-title-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div id="classificationTypeQuestionEditor" class="text_editor_margin"><textarea name="classificationTypeQuestion" id="classificationTypeQuestion" class="form-control myTextEditor" placeholder="'+messages['placeholder.question']+'"></textarea></div>'
			+ '<label class="requireFld" id="classificationTypeQuestionTitleError">'+messages['msg.empty']+'</label>'
			+ '</div>'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.answer']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-answer-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="answerDivInClassificationTypeQuestion">'
			+ '<input type="hidden" id="totalOptionsInClassificationTypeQuestion" name="totalOptionsInClassificationTypeQuestion" value="0">'
			+ '<input type="hidden" id="totalColumnsInClassificationTypeQuestion" name="totalColumnsInClassificationTypeQuestion" value="0">'
			+ '<input type="hidden" id="totalRowsInClassificationTypeQuestion" name="totalRowsInClassificationTypeQuestion" value="0">'
			+ '</div><!-- /.totalOptionsInClassificationTypeQuestion -->'

			+ '<div class="col-xs-12" style="min-height: 10px"></div>'
			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="addMoreOptionsInClassificationTypeQuestion" onclick="addOptionInClassificationTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addanswer']+'</span></button>'
			+ '&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-danger btn-flat button-width-large" id="clearDataInClassificationTypeQuestion" onclick="clearDataInClassificationTypeQuestionPopup();"><span><i class="fa fa-trash-o"></i> '+messages['lbl.clearquestion']+'</span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.columns']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-column-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="columnsListInClassificationTypeQuestion"></div>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="addMoreColumnsInClassificationTypeQuestion" onclick="addColumnsInClassificationTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addcolumn']+'</span></button>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<div class="col-xs-12"><label><sup><font color="red" size="3px">*</font></sup>'+messages['lbl.rows']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-row-tooltip-title"><i class="fa fa-question"></i></span></label></div>'
			+ '<div id="rowsListInClassificationTypeQuestion"></div>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group" style="text-align: center;">'
			+ '<button type="button" class="btn btn-flat btn-success button-width-large" id="rowsInClassificationTypeQuestion" onclick="addRowInClassificationTypeQuestion();"><span><i class="glyphicon glyphicon-plus-sign"></i> '+messages['lbl.addrow']+'</span></button>'
			+ '</div>'
            
			+ '<div class="col-xs-12 form-group"><label class="form-group">'+messages['lbl.classificationtable']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-classification-table-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<div class="col-sm-12 form-group" style="border:1px solid #ccc">'
			+ '<table class="table" id="classificationOptionTable">'
			+ '<thead>'
			+ '<tr>'
			+ '<th></th>'
			+ '</tr>'
			+ '</thead>'
			+ '<tbody>'
			+ '</tbody>'
			+ '</table>'
			
			+ '<div class="row triangle"></div>'
			+ '<div class="row classification_drag" style="min-height:100px;background-color:#ccc;padding:10px" id="classificationOptionsDivForDrag" ondrop="dropInClassificationQuestionType(event)" ondragover="allowDropInClassificationQuestionType(event)"></div>'
			+ '<label class="requireFld" id="classificationTypeQuestionSelectedButtonError">'+messages['msg.empty']+'</label>'
			+ '</div>'
			+ '</div>'

			+ '<div class="col-xs-12 form-group">'
			+ '<label class="form-group">'+messages['lbl.answerexplanation']+' &nbsp;<span class="question-badge bg-gray questiontype-tooltip" id="question-explanation-tooltip-title"><i class="fa fa-question"></i></span></label>'
			+ '<textarea class="form-control textAreaControl"  name="classificationTypeQuestionAnswerExplanation" id="classificationTypeQuestionAnswerExplanation" placeholder="'+messages['lbl.answerexplanation']+'" onkeyup="classificationTypeQuestionListner();"></textarea>'
			+ '<label class="requireFld" id="classificationTypeQuestionAnswerExplanationError">'+messages['msg.empty']+'</label>'
			+ '</div>'

			+ '</div>';
	$("#questiondiv").append(str);
	/**
	 * Add One Row in Question.
	 */
	addRowInClassificationTypeQuestion();
	/**
	 * Instance of table.
	 */
	var table = document.getElementById('classificationOptionTable');
	$(table.rows[1].cells[0]).html('['+messages['lbl.row']+' 1]');
	for (var option = 0; option < 2; option++) {
		addOptionInClassificationTypeQuestion(option + 1);
		addColumnsInClassificationTypeQuestion();
		$(table.rows[0].cells[option + 1])
				.html('['+messages['lbl.column']+' ' + (option + 1) + ']');
	}
	CKEDITOR.inline('classificationTypeQuestion').on('change', function(e) {
		classificationTypeQuestionListner();
	});
	$('#question-title-tooltip-title').tooltip({title: messages['lbl.questiondescriptionintooltip'], trigger: "hover",placement:"right"});
	$('#question-answer-tooltip-title').tooltip({title: messages['lbl.listofpossibleresponsevalues'], trigger: "hover",placement:"right"});
	$('#question-column-tooltip-title').tooltip({title: messages['lbl.columntitlesforclassificationtable'], trigger: "hover",placement:"right"});
	$('#question-row-tooltip-title').tooltip({title: messages['lbl.rowtitlesclassificationtable'], trigger: "hover",placement:"right"});
	$('#question-classification-table-tooltip-title').tooltip({title: messages['lbl.classificationtypeselectedoptiondescription'], trigger: "hover",placement:"right"});
	$('#question-explanation-tooltip-title').tooltip({title: messages['lbl.questionexpdescriptionintooltip'], trigger: "hover",placement:"right"});
}

/**
 * @summary This is used to adding more answer for classification type question.
 * @returns no.
 */
function addOptionInClassificationTypeQuestion() {
	var optionNo = $("#totalOptionsInClassificationTypeQuestion").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		str = '<div id="option'
				+ optionNo
				+ 'InClassificationTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'
				+ '<div id="answer'
				+ optionNo
				+ 'divInClassificationTypeQuestion" class="text_editor_margin"><textarea name="answer'
				+ optionNo
				+ 'InClassificationTypeQuestion" id="answer'
				+ optionNo
				+ 'InClassificationTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ optionNo
				+ '" class="form-control myTextEditor"></textarea></div>'
				+ '<span class="input-group-addon" id="deleteOption'
				+ optionNo
				+ 'InClassificationTypeQuestion" onclick="deleteOptionInClassificationTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removeanswer']+'"></i></span>'
				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="answer'
				+ optionNo
				+ 'ErrorInClassificationTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#answerDivInClassificationTypeQuestion").append(str);
		var dragDiv = '<div class="answerDivInClassificationTypeQuestion" id="draggedOption'
				+ optionNo
				+ 'InClassificationTypeQuestion" draggable="true" ondragstart="dragInClassificationQuestionType(event)"><div class="input-group"><div class="input-group-addon"><i class="fa fa-align-justify"></i></div><div class="pull-left" id="optionDrag'
				+ optionNo + 'InClassificationTypeQuestion"></div></div></div>';
		$("#classificationOptionsDivForDrag").append(dragDiv);
		if (optionNo <= 2) {
			$("#deleteOption1InClassificationTypeQuestion").css("visibility",
					"hidden");
			$("#deleteOption2InClassificationTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteOption1InClassificationTypeQuestion").css("visibility",
					"visible");
			$("#deleteOption2InClassificationTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalOptionsInClassificationTypeQuestion").val(optionNo);
		CKEDITOR.inline('answer' + optionNo + 'InClassificationTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			classificationTypeQuestionListner();
			changeTextInDragOptionInClassification(e.listenerData);
		}, null, optionNo);
	}
}

/**
 * @summary This function is used for allowing the drop feature on an element.
 * @param ev
 * @returns no.
 */
function allowDropInClassificationQuestionType(ev) {
	ev.preventDefault();
}

/**
 * @summary This function would be call when user start drag activity on an
 *          element.
 * @param ev
 * @returns no.
 */
function dragInClassificationQuestionType(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

/**
 * @summart This function is used to get action after drop an element.
 * @param ev
 * @returns no.
 */
function dropInClassificationQuestionType(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	if ($(ev.target).hasClass('classification_drag')) {
		ev.target.appendChild(document.getElementById(data));
		$("#classificationTypeQuestionSelectedButtonError").fadeOut();
	}
}
/**
 * @summary This is used to adding more column for classification type question.
 * @returns no.
 */
function addColumnsInClassificationTypeQuestion() {
	var optionNo = $("#totalColumnsInClassificationTypeQuestion").val();
	var str = "";
	if (optionNo < 10) {
		optionNo++;
		str = '<div id="columnMain'
				+ optionNo
				+ 'InClassificationTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'
				+ '<div id="column'
				+ optionNo
				+ 'divInClassificationTypeQuestion" class="text_editor_margin"><textarea name="column'
				+ optionNo
				+ 'InClassificationTypeQuestion" id="column'
				+ optionNo
				+ 'InClassificationTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ optionNo
				+ '" class="form-control myTextEditor"></textarea></div>'
				+ '<span class="input-group-addon" id="deleteColumn'
				+ optionNo
				+ 'InClassificationTypeQuestion" onclick="deleteColumnInClassificationTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removecolumn']+'"></i></span>'
				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="column'
				+ optionNo
				+ 'ErrorInClassificationTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#columnsListInClassificationTypeQuestion").append(str);
		var rowCount = $('#classificationOptionTable>tbody>tr').length;
		var table = document.getElementById('classificationOptionTable');
		for (var tr = 1; tr <= rowCount; tr++) {
			$(table.rows[tr])
					.append(
							'<td class="text-align classification_drag" ondrop="dropInClassificationQuestionType(event)" ondragover="allowDropInClassificationQuestionType(event)"></td>');
		}
		var tbodyHeadData = '<th class="text-align imgset"></th>';
		$("#classificationOptionTable>thead>tr").append(tbodyHeadData);
		if (optionNo <= 2) {
			$("#deleteColumn1InClassificationTypeQuestion").css("visibility",
					"hidden");
			$("#deleteColumn2InClassificationTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteColumn1InClassificationTypeQuestion").css("visibility",
					"visible");
			$("#deleteColumn2InClassificationTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalColumnsInClassificationTypeQuestion").val(optionNo);
		CKEDITOR.inline('column' + optionNo + 'InClassificationTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			classificationTypeQuestionListner();
			changeTextInClassificationTypeTableHead(e.listenerData);
		}, null, optionNo);
	}
}

/**
 * @summary This is used to adding more column for classification type question.
 * @returns no.
 */
function addRowInClassificationTypeQuestion() {
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	var str = "";
	if (totalRow < 10) {
		totalRow++;
		str = '<div id="rowMain'
				+ totalRow
				+ 'InClassificationTypeQuestion">'
				+ '<div class="col-xs-12">'
				+ '<div class="">'
				+ '<div class="input-group">'
				+ '<div id="row'
				+ totalRow
				+ 'divInClassificationTypeQuestion" class="text_editor_margin"><textarea name="row'
				+ totalRow
				+ 'InClassificationTypeQuestion" id="row'
				+ totalRow
				+ 'InClassificationTypeQuestion"  placeholder="'+messages['lbl.answernumber']+' '
				+ totalRow
				+ '" class="form-control myTextEditor"></textarea></div>'
				+ '<span class="input-group-addon" id="deleteRow'
				+ totalRow
				+ 'InClassificationTypeQuestion" onclick="deleteRowInClassificationTypeQuestion(this)"><i class="btn btn-danger btn-xs glyphicon glyphicon-remove-circle"  title="'+messages['lbl.removerow']+'"></i></span>'
				+ '</div><!-- /input-group -->'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-xs-12"><label class="requireFld" id="row'
				+ totalRow
				+ 'ErrorInClassificationTypeQuestion">'+messages['msg.empty']+'</label></div>'
				+ '</div>';

		$("#rowsListInClassificationTypeQuestion").append(str);
		var headerCount = $('#classificationOptionTable>thead>tr>th').length;
		var tableData = "";
		for (var th = 1; th < headerCount; th++) {
			tableData = tableData
					+ '<td class="text-align classification_drag" ondrop="dropInClassificationQuestionType(event)" ondragover="allowDropInClassificationQuestionType(event)"></td>';
		}
		var tbodyRowData = '<tr><td class="imgset"></td>' + tableData + '</tr>'
		$("#classificationOptionTable>tbody").append(tbodyRowData);
		if (totalRow <= 1) {
			$("#deleteRow1InClassificationTypeQuestion").css("visibility",
					"hidden");
		} else {
			$("#deleteRow1InClassificationTypeQuestion").css("visibility",
					"visible");
		}
		$("#totalRowsInClassificationTypeQuestion").val(totalRow);
		CKEDITOR.inline('row' + totalRow + 'InClassificationTypeQuestion', {
			height : 40
		}).on('change', function(e) {
			classificationTypeQuestionListner();
			changeTextInClassificationTypeTableBody(e.listenerData);
		}, null, totalRow);
	}
}

/**
 * @summary This is used changing the text in list which is showing for drag.
 * @param opn
 * @returns no.
 */
function changeTextInDragOptionInClassification(opn) {
	var optionText = CKEDITOR.instances['answer' + opn
			+ 'InClassificationTypeQuestion'].getData();
	$('#optionDrag' + opn + 'InClassificationTypeQuestion').html(optionText);
}

/**
 * @summary This is used changing the text of cell in table head.
 * @param opn
 * @returns no.
 */
function changeTextInClassificationTypeTableHead(columnNo) {
	var table = document.getElementById('classificationOptionTable');
	$(table.rows[0].cells[columnNo]).html(
			CKEDITOR.instances['column' + columnNo
					+ 'InClassificationTypeQuestion'].getData());
	$('.imgset img').css({
		'max-width' : '100%',
		'height' : 'auto'
	});
}

/**
 * @summary This is used changing the text of first cell in table row.
 * @param opn
 * @returns no.
 */
function changeTextInClassificationTypeTableBody(rowNo) {
	var table = document.getElementById('classificationOptionTable');
	$(table.rows[rowNo].cells[0]).html(
			CKEDITOR.instances['row' + rowNo + 'InClassificationTypeQuestion']
					.getData());
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
function deleteOptionInClassificationTypeQuestion(object) {
	var optionId = object.id;
	var arr = optionId.split('deleteOption');
	var optionNo = parseInt(arr[1].substr(0, 1));
	var totalOption = $("#totalOptionsInClassificationTypeQuestion").val();
	if (totalOption <= 2) {
	} else {
		$("#option" + optionNo + "InClassificationTypeQuestion").remove();
		$('#draggedOption' + optionNo + 'InClassificationTypeQuestion')
				.remove();
		optionNo++;
		for (var i = optionNo; i <= totalOption; i++) {
			$("#option" + i + "InClassificationTypeQuestion").attr('id',
					"option" + (i - 1) + "InClassificationTypeQuestion");
			$("#answer" + i + "divInClassificationTypeQuestion").attr('id',
					"answer" + (i - 1) + "divInClassificationTypeQuestion");
			$("#answer" + i + "InClassificationTypeQuestion").attr({
				id : "answer" + (i - 1) + "InClassificationTypeQuestion",
				name : "answer" + (i - 1) + "InClassificationTypeQuestion",
				placeholder : messages['lbl.answernumber']+" " + (i - 1)
			});
			$("#deleteOption" + i + "InClassificationTypeQuestion").attr('id',
					"deleteOption" + (i - 1) + "InClassificationTypeQuestion");
			$("#answer" + i + "ErrorInClassificationTypeQuestion").attr('id',
					"answer" + (i - 1) + "ErrorInClassificationTypeQuestion");
			CKEDITOR.instances['answer' + i + 'InClassificationTypeQuestion']
					.destroy();
			CKEDITOR.inline(
					'answer' + (i - 1) + 'InClassificationTypeQuestion', {
						height : 40
					}).on('change', function(e) {
				classificationTypeQuestionListner();
				changeTextInDragOptionInClassification(e.listenerData);
			}, null, (i - 1));
			$('#draggedOption' + i + 'InClassificationTypeQuestion').attr('id',
					'draggedOption' + (i - 1) + 'InClassificationTypeQuestion')
			$('#optionDrag' + i + 'InClassificationTypeQuestion').attr('id',
					'optionDrag' + (i - 1) + 'InClassificationTypeQuestion');
		}
		totalOption--;
		$("#totalOptionsInClassificationTypeQuestion").val(totalOption);
	}
	if (totalOption == 2) {
		$("#deleteOption1InClassificationTypeQuestion").css("visibility",
				"hidden");
		$("#deleteOption2InClassificationTypeQuestion").css("visibility",
				"hidden");
	}
}

/**
 * @summary This is used for deleting the column of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteColumnInClassificationTypeQuestion(object) {
	var columnId = object.id;
	var arr = columnId.split('deleteColumn');
	var columnNo = parseInt(arr[1].substr(0, 1));
	var totalColumn = $("#totalColumnsInClassificationTypeQuestion").val();
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	if (totalColumn <= 2) {
	} else {
		$("#columnMain" + columnNo + "InClassificationTypeQuestion").remove();
		/**
		 * get instance of table.
		 */
		var table = document.getElementById('classificationOptionTable');
		/**
		 * remove cell from row of table header.
		 */
		$(table.rows[0].cells[columnNo]).remove();
		/**
		 * itearte on row of table body.
		 */
		for (var tr = 1; tr <= totalRow; tr++) {
			/**
			 * append its html in dragged options div.
			 */
			$("#classificationOptionsDivForDrag").append(
					$(table.rows[tr].cells[columnNo]).html());
			/**
			 * remove cell from row of table body.
			 */
			$(table.rows[tr].cells[columnNo]).remove();
		}
		columnNo++;
		for (var i = columnNo; i <= totalColumn; i++) {
			$("#columnMain" + i + "InClassificationTypeQuestion").attr('id',
					"columnMain" + (i - 1) + "InClassificationTypeQuestion");
			$("#column" + i + "divInClassificationTypeQuestion").attr('id',
					"column" + (i - 1) + "divInClassificationTypeQuestion");
			$("#column" + i + "InClassificationTypeQuestion").attr({
				id : "column" + (i - 1) + "InClassificationTypeQuestion",
				name : "column" + (i - 1) + "InClassificationTypeQuestion",
				placeholder : messages['lbl.answernumber']+" " + (i - 1)
			});
			$("#deleteColumn" + i + "InClassificationTypeQuestion").attr('id',
					"deleteColumn" + (i - 1) + "InClassificationTypeQuestion");
			$("#column" + i + "ErrorInClassificationTypeQuestion").attr('id',
					"column" + (i - 1) + "ErrorInClassificationTypeQuestion");
			CKEDITOR.instances['column' + i + 'InClassificationTypeQuestion']
					.destroy();
			CKEDITOR.inline(
					'column' + (i - 1) + 'InClassificationTypeQuestion', {
						height : 40
					}).on('change', function(e) {
				classificationTypeQuestionListner();
				changeTextInClassificationTypeTableHead(e.listenerData);
			}, null, (i - 1));
		}
		totalColumn--;
		$("#totalColumnsInClassificationTypeQuestion").val(totalColumn);
	}
	if (totalColumn == 2) {
		$("#deleteColumn1InClassificationTypeQuestion").css("visibility",
				"hidden");
		$("#deleteColumn2InClassificationTypeQuestion").css("visibility",
				"hidden");
	}
}

/**
 * @summary This is used for deleting the row of a particular question.
 * 
 * @param object
 * @returns no.
 */
function deleteRowInClassificationTypeQuestion(object) {
	var rowId = object.id;
	var arr = rowId.split('deleteRow');
	var rowNo = parseInt(arr[1].substr(0, 1));
	var totalColumn = $("#totalColumnsInClassificationTypeQuestion").val();
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	if (totalRow <= 1) {
	} else {
		$("#rowMain" + rowNo + "InClassificationTypeQuestion").remove();
		/**
		 * get instance of table.
		 */
		var table = document.getElementById('classificationOptionTable');
		/**
		 * get column count in count in table head.
		 */
		var headColumnCount = $('#classificationOptionTable>thead>tr>th').length;
		/**
		 * iterate on table head cell.
		 */
		for (var td = 1; td <= headColumnCount; td++) {
			/**
			 * append html into dragged div.
			 */
			$("#classificationOptionsDivForDrag").append(
					$(table.rows[rowNo].cells[td]).html());
		}
		/**
		 * remove row from table.
		 */
		$(table.rows[rowNo]).remove();
		rowNo++;
		for (var i = rowNo; i <= totalRow; i++) {
			$("#rowMain" + i + "InClassificationTypeQuestion").attr('id',
					"rowMain" + (i - 1) + "InClassificationTypeQuestion");
			$("#row" + i + "divInClassificationTypeQuestion").attr('id',
					"row" + (i - 1) + "divInClassificationTypeQuestion");
			$("#row" + i + "InClassificationTypeQuestion").attr({
				id : "row" + (i - 1) + "InClassificationTypeQuestion",
				name : "row" + (i - 1) + "InClassificationTypeQuestion",
				placeholder : messages['lbl.answernumber']+" " + (i - 1)
			});
			$("#deleteRow" + i + "InClassificationTypeQuestion").attr('id',
					"deleteRow" + (i - 1) + "InClassificationTypeQuestion");
			$("#row" + i + "ErrorInClassificationTypeQuestion").attr('id',
					"row" + (i - 1) + "ErrorInClassificationTypeQuestion");
			CKEDITOR.instances['row' + i + 'InClassificationTypeQuestion']
					.destroy();
			CKEDITOR.inline('row' + (i - 1) + 'InClassificationTypeQuestion', {
				height : 40
			}).on('change', function(e) {
				classificationTypeQuestionListner();
				changeTextInClassificationTypeTableHead(e.listenerData);
			}, null, (i - 1));
		}
		totalRow--;
		$("#totalRowsInClassificationTypeQuestion").val(totalRow);
	}
	if (totalRow == 1) {
		$("#deleteRow1InClassificationTypeQuestion")
				.css("visibility", "hidden");
	}
}

/**
 * @summary This is used to validate classification type question data.
 * @returns {Boolean}
 */
function classificationTypeQuestionValidate() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptionsInClassificationTypeQuestion").val();
	var totalColumn = $("#totalColumnsInClassificationTypeQuestion").val();
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	var questionContent = CKEDITOR.instances['classificationTypeQuestion']
			.getData();
	var questionContentData = ConvertHtmlToPlainTest(questionContent);
	if (questionContentData == "") {
		$("#classificationTypeQuestionEditor").css({
			"border-color" : "#c95b5b",
			"border-style" : "solid",
			"border-width" : "1px"
		});
		$("#classificationTypeQuestionTitleError").fadeIn();
		$(window)
				.scrollTop($("#classificationTypeQuestionEditor").offset().top);
		return false;
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j
				+ 'InClassificationTypeQuestion'].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#answer" + j + "divInClassificationTypeQuestion").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#answer" + j + "ErrorInClassificationTypeQuestion").fadeIn();
			$(window).scrollTop(
					$("#answer" + j + "divInClassificationTypeQuestion")
							.offset().top);
			return false;
		}

	}

	for (var j = 1; j <= totalColumn; j++) {
		var optionContent = CKEDITOR.instances['column' + j
				+ 'InClassificationTypeQuestion'].getData();
		var optionContentData = ConvertHtmlToPlainTest(optionContent);
		if (optionContentData == "") {
			$("#column" + j + "divInClassificationTypeQuestion").css({
				"border-color" : "#c95b5b",
				"border-style" : "solid",
				"border-width" : "1px"
			});
			$("#column" + j + "ErrorInClassificationTypeQuestion").fadeIn();
			$(window).scrollTop(
					$("#column" + j + "divInClassificationTypeQuestion")
							.offset().top);
			return false;
		}

	}
	if (totalRow > 1) {
		for (var j = 1; j <= totalRow; j++) {
			var optionContent = CKEDITOR.instances['row' + j
					+ 'InClassificationTypeQuestion'].getData();
			var optionContentData = ConvertHtmlToPlainTest(optionContent);
			if (optionContentData == "") {
				$("#row" + j + "divInClassificationTypeQuestion").css({
					"border-color" : "#c95b5b",
					"border-style" : "solid",
					"border-width" : "1px"
				});
				$("#row" + j + "ErrorInClassificationTypeQuestion").fadeIn();
				$(window).scrollTop(
						$("#row" + j + "divInClassificationTypeQuestion")
								.offset().top);
				return false;
			}
		}
	}
	var table = document.getElementById('classificationOptionTable');
	var correctOptionLength = $(table).find(
			'.answerDivInClassificationTypeQuestion').length;
	if (correctOptionLength == 0) {
		$("#classificationTypeQuestionSelectedButtonError").text(
				"Minimum One Option should be dragged in Table.");
		$("#classificationTypeQuestionSelectedButtonError").fadeIn();
		return false;
	}
	/*
	 * if($("#ClassificationTypeQuestionAnswerExplanation").val()=="") {
	 * $("#ClassificationTypeQuestionAnswerExplanation").css("border-color","#c95b5b");
	 * $("#ClassificationTypeQuestionAnswerExplanationError").fadeIn(); return
	 * false; }
	 */
	return true;
}

/**
 * @summary This is used fadeout the validation errors from classification type
 *          question page.
 * @returns no.
 */
function classificationTypeQuestionListner() {
	for ( var instanceName in CKEDITOR.instances) {
		CKEDITOR.instances[instanceName].updateElement();
	}
	var totaloptions = $("#totalOptionsInClassificationTypeQuestion").val();
	var totalColumn = $("#totalColumnsInClassificationTypeQuestion").val();
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	var questionContent = CKEDITOR.instances['classificationTypeQuestion']
			.getData();
	if (questionContent != "") {
		$("#classificationTypeQuestionEditor").css("border", "none");
		$("#classificationTypeQuestionTitleError").fadeOut();
	}
	for (var j = 1; j <= totaloptions; j++) {
		var optionContent = CKEDITOR.instances['answer' + j
				+ 'InClassificationTypeQuestion'].getData();
		if (optionContent != "") {
			$("#answer" + j + "divInClassificationTypeQuestion").css("border",
					"none");
			$("#answer" + j + "ErrorInClassificationTypeQuestion").fadeOut();
		}
	}
	for (var j = 1; j <= totalColumn; j++) {
		var optionContent = CKEDITOR.instances['column' + j
				+ 'InClassificationTypeQuestion'].getData();
		if (optionContent != "") {
			$("#column" + j + "divInClassificationTypeQuestion").css("border",
					"none");
			$("#column" + j + "ErrorInClassificationTypeQuestion").fadeOut();
		}
	}
	for (var j = 1; j <= totalRow; j++) {
		var optionContent = CKEDITOR.instances['row' + j
				+ 'InClassificationTypeQuestion'].getData();
		if (optionContent != "") {
			$("#row" + j + "divInClassificationTypeQuestion").css("border",
					"none");
			$("#row" + j + "ErrorInClassificationTypeQuestion").fadeOut();
		}
	}

	if ($("#classificationTypeQuestionAnswerExplanation").val().length > 0) {
		$("#classificationTypeQuestionAnswerExplanation").css("border-color",
				"");
		$("#classificationTypeQuestionAnswerExplanationError").fadeOut();
	}
}

/**
 * @summary This is used for showing the pop up for clear data from
 *          classification type question.
 * @returns no.
 */
function clearDataInClassificationTypeQuestionPopup() {
	$("#clearquestionAlert p").text(messages['msg.clearquestion']);
	$("#dId")
			.attr('onclick', 'clearQuestionDataInClassificationTypeQuestion()');
	$('#clearquestionAlert').modal('show');
}

/**
 * @summary This is used for clear the all data from classification type
 *          question.
 * @returns no.
 */
function clearQuestionDataInClassificationTypeQuestion() {
	$('#clearquestionAlert').modal('hide');
	var totalOptions = $("#totalOptionsInClassificationTypeQuestion").val();
	var totalColumn = $("#totalColumnsInClassificationTypeQuestion").val();
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	CKEDITOR.instances['classificationTypeQuestion'].setData('');
	for (var op = 1; op <= totalOptions; op++) {
		CKEDITOR.instances['answer' + op + 'InClassificationTypeQuestion']
				.setData('');
	}
	for (var columnNo = 1; columnNo <= totalColumn; columnNo++) {
		CKEDITOR.instances['column' + columnNo + 'InClassificationTypeQuestion']
				.setData('');
	}
	for (var rowNo = 1; rowNo <= totalRow; rowNo++) {
		CKEDITOR.instances['row' + rowNo + 'InClassificationTypeQuestion']
				.setData('');
	}

	$("#classificationTypeQuestionAnswerExplanation").val('');
	$(window).scrollTop(0);
}

/**
 * @summary This is used for confirm the user for submit the details of
 *          classification type question.
 * @returns no.
 */
function saveClassificationQuesPopUp() {
	if (classificationTypeQuestionValidate()) {
		$("#clearquestionAlert p").text(messages['msg.submitdetail']);
		$("#dId").attr('onclick', 'saveClassificationTypeQuestionInJson()');
		$("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary function is used for save the new question data in json.
 * @returns no.
 */
function saveClassificationTypeQuestionInJson() {
	if (classificationTypeQuestionValidate()) {
		var quesData = createClassificationTypeQuestionJson();
		submitQuestionDetails();
	}
}

/**
 * @summary This is used for creating the json for new question.
 * 
 * @returns {Array}
 */
function createClassificationTypeQuestionJson() {
	var quesData = [];
	var optionsList = [];
	var questionContent = CKEDITOR.instances['classificationTypeQuestion']
			.getData();
	var question = {
		"questionId" : parseInt(questionSectionList[0].testId + ""
				+ questionSectionList[0].sectionId
				+ (questionSectionList[0].questionList.length + 1) + dynamicId),
		"questionName" : questionContent,
		"questionNo" : questionSectionList[0].questionList.length + 1,
		"questionType" : CLASSIFICATION_TYPE,
		"option" : optionsList,
		"isNew" : 1, // defines that question is new created in test.
		"explanation" : $("#classificationTypeQuestionAnswerExplanation").val(),
		"isFormula" : 0,
		"answerValue" : "",
		"questionSetting" : {
			"questionUiStyle" : {
				"columnTitles" : [],
				"rowTitles" : []
			}
		}
	};
	var totalOption = $("#totalOptionsInClassificationTypeQuestion").val();
	var totalColumn = $("#totalColumnsInClassificationTypeQuestion").val();
	var totalRow = $("#totalRowsInClassificationTypeQuestion").val();
	var answerValue = [];
	for (var op = 1; op <= totalOption; op++) {
		var optionContent = CKEDITOR.instances['answer' + op
				+ 'InClassificationTypeQuestion'].getData();
		var optionStatus = 0;

		var options = {
			"optionId" : parseInt(questionSectionList[0].testId + ""
					+ questionSectionList[0].sectionId
					+ (questionSectionList[0].questionList.length + 1) + op),
			"optionName" : optionContent,
			"optionOrder" : op,
			"answerStatus" : optionStatus
		};
		optionsList.push(options);
	}
	for (var columnNo = 1; columnNo <= totalColumn; columnNo++) {
		var columnTitle = CKEDITOR.instances['column' + columnNo
				+ 'InClassificationTypeQuestion'].getData();
		question.questionSetting.questionUiStyle.columnTitles.push(columnTitle);
	}
	for (var rowNo = 1; rowNo <= totalRow; rowNo++) {
		var rowTitle = CKEDITOR.instances['row' + rowNo
				+ 'InClassificationTypeQuestion'].getData();
		question.questionSetting.questionUiStyle.rowTitles.push(rowTitle);
	}
	/**
	 * get instance of table.
	 */
	var table = document.getElementById('classificationOptionTable');

	for (var tr = 1; tr <= totalRow; tr++) {
		var columnArray = [];
		for (var td = 1; td <= totalColumn; td++) {
			var correctAnswer = [];
			$(table.rows[tr].cells[td]).find(
					'.answerDivInClassificationTypeQuestion').each(function() {
				var id = $(this).attr('id');
				var arr1 = id.split("draggedOption");
				var arr2 = arr1[1].split("InClassificationTypeQuestion");
				var correctOrder = parseInt(arr2[0]);
				correctAnswer.push(correctOrder);
			});
			columnArray.push(correctAnswer);
		}
		answerValue.push(columnArray);
	}
	question.answerValue = JSON.stringify(answerValue);
	quesData.push(question.questionId);
	quesData.push(question.questionName);
	questionSectionList[0].questionList.push(question);
	dynamicId++;
	return quesData;
}

/**
 * @summary This is used for fill up the question's data from json object when
 *          user enters in edit mode of classification type question.
 * @param questionId
 * @returns no.
 */
function fillClassificationTypeQuestionData(questionId) {
	for (var i = 0; i < questionSectionList[0].questionList.length; i++) {
		if (questionSectionList[0].questionList[i].questionId == questionId) {
			var table = document.getElementById('classificationOptionTable');
			CKEDITOR.instances['classificationTypeQuestion']
					.setData(questionSectionList[0].questionList[i].questionName);
			for (var j = 0; j < questionSectionList[0].questionList[i].option.length; j++) {
				if ($("#totalOptionsInClassificationTypeQuestion").val() == j) {
					addOptionInClassificationTypeQuestion();
				}
				CKEDITOR.instances['answer' + (j + 1)
						+ 'InClassificationTypeQuestion']
						.setData(questionSectionList[0].questionList[i].option[j].optionName);
				$('#optionDrag' + (j + 1) + 'InClassificationTypeQuestion')
						.html(
								questionSectionList[0].questionList[i].option[j].optionName);
			}

			var questionSetting = questionSectionList[0].questionList[i].questionSetting;
			for (var columnNo = 0; columnNo < questionSetting.questionUiStyle.columnTitles.length; columnNo++) {
				if ($("#totalColumnsInClassificationTypeQuestion").val() == columnNo) {
					addColumnsInClassificationTypeQuestion();
				}
				CKEDITOR.instances['column' + (columnNo + 1)
						+ 'InClassificationTypeQuestion']
						.setData(questionSetting.questionUiStyle.columnTitles[columnNo]);
				$(table.rows[0].cells[columnNo + 1]).html(
						questionSetting.questionUiStyle.columnTitles[columnNo]);

			}

			for (var rowNo = 0; rowNo < questionSetting.questionUiStyle.rowTitles.length; rowNo++) {
				if ($("#totalRowsInClassificationTypeQuestion").val() == rowNo) {
					addRowInClassificationTypeQuestion();
				}
				CKEDITOR.instances['row' + (rowNo + 1)
						+ 'InClassificationTypeQuestion']
						.setData(questionSetting.questionUiStyle.rowTitles[rowNo]);
				$(table.rows[rowNo + 1].cells[0]).html(
						questionSetting.questionUiStyle.rowTitles[rowNo]);

			}

			var answerValue = JSON
					.parse(questionSectionList[0].questionList[i].answerValue);
			for (var x = 0; x < answerValue.length; x++) {
				for (var y = 0; y < answerValue[x].length; y++) {
					for (var z = 0; z < answerValue[x][y].length; z++) {
						var answerOrder = answerValue[x][y][z];
						$(table.rows[x + 1].cells[y + 1]).append(
								document.getElementById("draggedOption"
										+ answerOrder
										+ "InClassificationTypeQuestion"));
					}
				}
			}
			$("#classificationTypeQuestionAnswerExplanation").val(
					questionSectionList[0].questionList[i].explanation);
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
 * @summary This is used for showing the pop up for confirm the update a
 *          classification type question.
 * @param questionId
 * @returns no
 */
function updateClassificationTypeQuestionPopUp(questionId) {
	if (classificationTypeQuestionValidate()) {
		$("#clearquestionAlert p").text(messages['msg.sureforquestionupdate']);
		$("#dId").attr('onclick',
				'updateClassificationQuestion(' + questionId + ')');
		$("#clearquestionAlert").modal('show');
	}
}

/**
 * @summary This is used for performing opertion for update the classification
 *          type question.
 * @param questionId
 * @retruns no
 */
function updateClassificationQuestion(questionId) {
	if (classificationTypeQuestionValidate()) {
		var quesData = updateClassificationQuestionTypeJson(questionId);
		submitQuestionDetails();
	}
}

/**
 * @summary This is used for update the json for classification question type
 *          after update the user.
 * @param questionId
 * @returns no.
 */
function updateClassificationQuestionTypeJson(questionId) {
	if (classificationTypeQuestionValidate()) {
		for (var q = 0; q < questionSectionList[0].questionList.length; q++) {
			if (questionSectionList[0].questionList[q].questionId == questionId) {
				var optionsList = [];
				var subOptionsList = [];
				var questionContent = CKEDITOR.instances['classificationTypeQuestion']
						.getData();
				var question = {
					"questionId" : questionId,
					"questionName" : questionContent,
					"questionNo" : (q + 1),
					"questionType" : questionSectionList[0].questionList[q].questionType,
					"option" : optionsList,
					"isNew" : questionSectionList[0].questionList[q].isNew,
					"explanation" : $(
							"#classificationTypeQuestionAnswerExplanation")
							.val(),
					"isParent" : questionSectionList[0].questionList[q].isParent,
					"isFormula" : 0,
					"answerValue" : "",
					"questionSetting" : {
						"questionUiStyle" : {
							"columnTitles" : [],
							"rowTitles" : []
						}
					}
				};
				var totalOption = $("#totalOptionsInClassificationTypeQuestion")
						.val();
				var totalColumn = $("#totalColumnsInClassificationTypeQuestion")
						.val();
				var totalRow = $("#totalRowsInClassificationTypeQuestion")
						.val();
				var answerValue = [];

				for (var op = 1; op <= totalOption; op++) {
					var optionContent = CKEDITOR.instances['answer' + op
							+ 'InClassificationTypeQuestion'].getData();
					var optionStatus = 0;
					$('.imgset img').css({
						'max-width' : '400px',
						'height' : 'auto'
					});
					var options = {
						"optionId" : parseInt(questionSectionList[0].testId
								+ ""
								+ questionSectionList[0].sectionId
								+ (questionSectionList[0].questionList.length + 1)
								+ op),
						"optionName" : optionContent,
						"optionOrder" : op,
						"answerStatus" : optionStatus
					};
					optionsList.push(options);
				}
				for (var columnNo = 1; columnNo <= totalColumn; columnNo++) {
					var columnTitle = CKEDITOR.instances['column' + columnNo
							+ 'InClassificationTypeQuestion'].getData();
					question.questionSetting.questionUiStyle.columnTitles
							.push(columnTitle);
				}
				for (var rowNo = 1; rowNo <= totalRow; rowNo++) {
					var rowTitle = CKEDITOR.instances['row' + rowNo
							+ 'InClassificationTypeQuestion'].getData();
					question.questionSetting.questionUiStyle.rowTitles
							.push(rowTitle);
				}
				/**
				 * get instance of table.
				 */
				var table = document
						.getElementById('classificationOptionTable');

				for (var tr = 1; tr <= totalRow; tr++) {
					var columnArray = [];
					for (var td = 1; td <= totalColumn; td++) {
						var correctAnswer = [];
						$(table.rows[tr].cells[td])
								.find('.answerDivInClassificationTypeQuestion')
								.each(
										function() {
											var id = $(this).attr('id');
											var arr1 = id
													.split("draggedOption");
											var arr2 = arr1[1]
													.split("InClassificationTypeQuestion");
											var correctOrder = parseInt(arr2[0]);
											correctAnswer.push(correctOrder);
										});
						columnArray.push(correctAnswer);
					}
					answerValue.push(columnArray);
				}

				question.answerValue = JSON.stringify(answerValue);

				questionSectionList[0].questionList[q] = question;
			}
			// console.log(JSON.stringify(questionSectionList));
		}
		$('.imgset img').css({
			'max-width' : '400px',
			'height' : 'auto'
		});
	}
}