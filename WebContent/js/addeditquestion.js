/**
 * @summary This js file is used for performing all common operation related to creating and editing any type of question in question bank.
 * @author ankur
 * @date 23 Aug 2016.
 */


/**
 * @summary This is used creating empty question array.
 * @returns no
 */
function createQuestionArray() {
		var question = {
			"questionList" : [],
		};
		questionSectionList.push(question);
	}

/**
 * @summary This is used for submitting the json of all created questions or updated question.
 * @returns no
 */
function submitQuestionDetails() {
	$("#clearquestionAlert").modal('hide');	
	var callingURL='saveQuestion';
	if(updatedquestionId.length>0 && updatedquestionId>0){
		callingURL='saveQuestion?questionId='+updatedquestionId;
	}
	var data = JSON.stringify(questionSectionList);
	$("#overlay").show();
	$.ajax({
				type : 'POST',
				url : callingURL,
				data : 'sectionData=' + encodeURIComponent(data),
				success : function(status) {
					$("#overlay").hide();
					if (status) {
					$(window).off('beforeunload');	
					window.location.href='questionList';
					} else {
						$('#questionErrorAlert').modal('show');
						$("#questionErrorAlert p").text(
								"Something went wrong,Please try again");
					}
				},
			   error : function(){
				   $("#overlay").hide();
				   $('#questionErrorAlert').modal('show');
					$("#questionErrorAlert p").text(
							"Something went wrong,Please try again");
			}
			});
}


/**
 * @summary This is used for redirecting on editing page of question on based on question type but this would be used when user perform edit opertion from question bank list. So In this case user wants update only a particular question.
 * @param questionId
 * @returns no
 */
function editParticularQuestion(questionId) {
	for (var q = 0; q < questionSectionList[0].questionList.length; q++) {
		if (questionSectionList[0].questionList[q].questionId == questionId) {
			switch(questionSectionList[0].questionList[q].questionType){
			case 1:
			    addNextQuestion();
			    fillQuestionData(questionId);
			    $(window).scrollTop(0);
			    $("#cancelQuestion").attr('onclick','location.href="questionList"');
			    $("#submitQuestion").text('Update');
			    $("#submitQuestion").attr('onclick', 'updateMultiTypeQuestionPopUp("'+questionId+'")');
			    break;
			case 2:
				addNextQuestion();
				fillQuestionData(questionId);
				$(window).scrollTop(0);
				$("#cancelQuestion").attr('onclick','location.href="questionList"');
				$("#submitQuestion").text('Update');
				$("#submitQuestion").attr('onclick', 'updateMultiTypeQuestionPopUp("'+questionId+'")');
				break;
			case 3:
				addSortListTypeQuestion();
				fillSortListTypeQuestionData(questionId);
				$(window).scrollTop(0);
				$("#cancelQuestion").attr('onclick','location.href="questionList"');
				$("#submitQuestion").text('Update');
				$("#submitQuestion").attr('onclick', 'updateSortListTypeQuestionPopUp("'+questionId+'")');
				break;
			        }
		}
	}
}

/**
 * @summary This is used for redirect on create question page based on question type when user comes first time from question bank for creating the question based on chosed question type. 
 * @param selectedQuestionType
 * @returns no
 */
function showCreateQuestionPage(selectedQuestionType) {
	/**
	 * if question type is multiple choice.
	 */
	if(selectedQuestionType==1){
		
	addNextQuestion();
	$("#cancelQuestion").attr('onclick','location.href="questionList"');
	$("#submitQuestion").text('Save');
	$("#submitQuestion").attr('onclick', 'saveMultiQuesPopUp()');
	}
	/**
	 * if question type is multiple choice.
	 */
	else if(selectedQuestionType==3){
		addSortListTypeQuestion();
		$("#cancelQuestion").attr('onclick','location.href="questionList"');
		$("#submitQuestion").text('Save');
		$("#submitQuestion").attr('onclick', 'saveSortListQuesPopUp()');
	}
	else {
		
		addNextQuestion();
		$("#cancelQuestion").attr('onclick','location.href="questionList"');
		$("#submitQuestion").text('Save');
		$("#submitQuestion").attr('onclick', 'saveMultiQuesPopUp()');
	}
	$(window).scrollTop(0);
	$("#questionlistpage").hide();
	$("#createQuestionPage").show();
}

/**
 * @summary This is used to convert the html into simple text.
 * @param strHtmlText
 * @returns strHtmlText
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