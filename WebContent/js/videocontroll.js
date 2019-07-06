function showQuestionDiv(vidtime) {
	clearInterval(setTimeoutId);
	vid.pause();
	$("#continue").attr("onClick", "continueVideo(" + vidtime + ")");
	$("#content-show-body").animate({width: '60%'});
	$("#questionListDiv").show(500);
	$("#showQuestionDiv" + vidtime).show();
	$("#myVideo").prop("controls", false);
}

function continueVideo(vidtime) {
	vid.currentTime = parseInt(vid.currentTime) + 2;
	$("#questionListDiv").hide();
	$("#showQuestionDiv" + vidtime).hide();
	setTimeForQuestion();
	$("#myVideo").prop("controls", false);
	$("#myVideo").prop("controls", true);
	$("#content-show-body").animate({width: '100%'});
	vid.play();
}

function setTimeForQuestion() {
	var timeslot = 0;
	for (var i = 0; i < timeList.length; i++) {
		if (timeList[i] >= parseInt(vid.currentTime)) {
			timeslot = timeList[i] - parseInt(vid.currentTime);
			setTimeoutId = setTimeout(function() {
				showQuestionDiv(timeList[i]);
			}, ((timeslot) * 1000));
			break;
		}
	}
}

function submitVideoQuestion(vidtime) {
	var queNum = $("#questionNumber" + vidtime).val();
	if ($(".answerOption" + queNum).is(":checked")) {
		$("#questionError" + queNum).fadeOut();
		var currectAnswer = [];
		for (var i = 0; i < parseInt($("#optionCount" + queNum).val()); i++) {
			if ($("#answerStatus" + queNum + "" + i).is(":checked")) {
				currectAnswer
						.push(parseInt($("#answerStatus" + queNum + "" + i)
								.val()));
			}
		}
		$.ajax({
			url : "saveAttemptContentQuestion",
			data : {
				"sectionId" : $("#courseSectionId").val(),
				"questionId" : $("#questionId" + queNum).val(),
				"attemptedAnswer" : JSON.stringify(currectAnswer),
				"attemptCounter" : $("#attemptCount").val()
			},
			type : 'POST',
			success : function(response) {
				if (response != null && response != 0) {
					$("#attemptCount").val(response);
					vid.currentTime = parseInt(vid.currentTime) + 2;
					$("#questionListDiv").hide();
					$("#showQuestionDiv" + vidtime).hide();
					$(".answerOption" + queNum).remove();
					$("#submit" + queNum).remove();
					// showQuestionDivAtCourse();
					$("#myVideo").prop("controls", false);
					$("#myVideo").prop("controls", true);
					$("#content-show-body").animate({width: '100%'});
					vid.play();
				} else {
					$("#contentErrorAlert p")
							.text(messages['msg.datanotsaved']);
					$("#contentErrorAlert").modal('show');
				}
			}
		});
	} else {
		$("#questionError" + queNum).fadeIn();
	}
}

function showQuestionDivAtCourse(vidtime) {
	var queNum = $("#questionNumber" + vidtime).val();
	clearInterval(setTimeoutId);
	vid.pause();
	$("#content-show-body").animate({width: '60%'});
	$("#questionListDiv").show(500);
	$("#showQuestionDiv" + vidtime).show();
	$("#myVideo").prop("controls", false);
}

function setTimeForQuestionAtCourse() {
	var timeslot = 0;
	for (var i = 0; i < timeList.length; i++) {
		if (timeList[i] >= parseInt(vid.currentTime)) {
			timeslot = timeList[i] - parseInt(vid.currentTime);
			setTimeoutId = setTimeout(function() {
				showQuestionDivAtCourse(timeList[i]);
			}, ((timeslot) * 1000));
			break;
		}
	}
}

function continueVideoAtCourse(vidtime) {
	vid.currentTime = parseInt(vid.currentTime) + 2;
	$("#questionListDiv").hide();
	$("#showQuestionDiv" + vidtime).hide();
	setTimeForQuestionAtCourse();
	$("#myVideo").prop("controls", false);
	$("#myVideo").prop("controls", true);
	$("#content-show-body").animate({width: '100%'});
	vid.play();
}

function createVideoQuestionUI(videoContentList, idFrom) {
	var str = '<div class="box-body" style="width: 100%;float: left;" id="content-show-body"><div class="form-group" style="text-align:left"><label>'
			+ messages['lbl.title']
			+ ': &nbsp;</label> <span class="capitalize">'
			+ videoContentList.contentName
			+ '</span></div>'
			+ '<div class=""><video id="myVideo" style="object-fit: initial;width: 98%;max-height:550px" controls><source src="'
			+ videoContentList.contentPath
			+ '"></video></div>'
			+ '<input type="hidden" id="contentId" name="contentId" value="'
			+ videoContentList.contentId
			+ '"><input type="hidden" id="attemptCount" name="attemptCount" value=""></div>'
			+ '<div id="questionListDiv" style="width: 40%;float: right; display: none;"><div class="form-group">';
	var upperLoopCnt = 0;
	$.each(videoContentList.questionList,
					function(i, questionObj) {
						str += '<div class="form-group" style="display: none;margin-top:45px" id="showQuestionDiv'
								+ questionObj.time
								+ '"><input type="hidden" id="questionNumber'
								+ questionObj.time
								+ '" value="'
								+ upperLoopCnt
								+ '"><input type="hidden" id="questionId'
								+ upperLoopCnt
								+ '" name="questionId'
								+ upperLoopCnt
								+ '" value="'
								+ questionObj.questionId
								+ '"><input type="hidden" id="timeslot'
								+ upperLoopCnt
								+ '" name="timeslot'
								+ upperLoopCnt
								+ '" value="'
								+ questionObj.time
								+ '">'
								+ '<label>'
								+ messages['lbl.questiontype']
								+ ': &nbsp;</label><span>'
								+ questionObj.questionTypeName
								+ '</span></br>'
								+ '<div class="input-group" style="float: left"><label>'
								+ messages['lbl.question']
								+ ': &nbsp;</label></div><div style="padding-left:40px"><span>'
								+ questionObj.questionName
								+ '</span></div>'
								+ '<label>'
								+ messages['lbl.answers']
								+ '</label></br>';
						var innerLoopCnt = 0;
						$.each(questionObj.option,
										function(j, optionObj) {
											str += '<div class="input-group" style="float: left"><span class="badge bagde-style">&#'
													+ (innerLoopCnt + 65)
													+ ';</span>&nbsp;&nbsp;&nbsp;&nbsp;';
											if (questionObj.questionType == 1) {
												str += '<input type="checkbox" class="flat-red questionType answerOption'
														+ upperLoopCnt
														+ '"  name="answerStatus'
														+ upperLoopCnt
														+ ''
														+ innerLoopCnt
														+ '" id="answerStatus'
														+ upperLoopCnt
														+ ''
														+ innerLoopCnt
														+ '" value="'
														+ optionObj.optionId
														+ '"/>&nbsp;&nbsp;&nbsp;&nbsp;';
											} else {
												str += '<input type="radio" class="flat-red questionType answerOption'
														+ upperLoopCnt
														+ '"  name="answerStatus'
														+ upperLoopCnt
														+ '" id="answerStatus'
														+ upperLoopCnt
														+ ''
														+ innerLoopCnt
														+ '" value="'
														+ optionObj.optionId
														+ '"/>&nbsp;&nbsp;&nbsp;&nbsp;';
											}
											str += '</div><div style="padding-left:40px"><span>'
													+ optionObj.optionName
													+ '</span></div>';
											innerLoopCnt++;
										});

						if (idFrom == 0) {
							str += '<input type="hidden" id="optionCount'
									+ upperLoopCnt
									+ '" value="'
									+ innerLoopCnt
									+ '">'
									+ '<div class="col-xs-12" ><label class="requireFld" id="questionError'
									+ upperLoopCnt
									+ '">'
									+ messages['msg.attemptquestionforsubmit']
									+ '</label></div><button class="btn btn-default btn-flat button-width-large" id="continue'
									+ upperLoopCnt
									+ '" onclick="continueVideoAtCourse('
									+ questionObj.time
									+ ')" type="button">'
									+ messages['lbl.continue']
									+ '</button>&nbsp;&nbsp;<button class="btn btn-success btn-flat button-width-large" onclick="submitVideoQuestion('
									+ questionObj.time + ')" id="submit'
									+ upperLoopCnt + '" type="button">'
									+ messages['lbl.submit']
									+ '</button></div>';
						} else {
							str += '<input type="hidden" id="optionCount'
									+ upperLoopCnt
									+ '" value="'
									+ innerLoopCnt
									+ '">'
									+ '<div class="col-xs-12" style="display:none;"><label class="requireFld" id="questionError'
									+ upperLoopCnt
									+ '">'
									+ messages['msg.attemptquestionforsubmit']
									+ '</label></div><button class="btn btn-default btn-flat button-width-large" id="continue'
									+ upperLoopCnt
									+ '" onclick="continueVideoAtCourse('
									+ questionObj.time + ')" type="button">'
									+ messages['lbl.continue']
									+ '</button></div>';
						}
						upperLoopCnt++;
					});

	str += '<input type="hidden" id="questionCount" value="' + upperLoopCnt
			+ '"/></div></div>';
	return str;
}

var currTime = 0;
/**
 * @summary if sign is 0 then user clicked previous button and if sign is 1 then user clicked next button.
 * 
 * @param sign
 */
function pageCallView(sign) {
	/**
	 * @summary This will indicate there is an added question on current page whether user have clicked previous button. 
	 */
	var backFlag = false;
	var currPage = $("#time").val();
	$("#pafContent").removeAttr("data");
	/**
	 * @summary This will indicate there is an added question on current page whether user have clicked previous or next button. 
	 */
	var flag = false;
	if (sign == 0) {
		currPage--;
		/**
		 * @checking that there is any added question on current page when user have clicked previous button.
		 */
		for (var i = 0; i < timeList.length; i++) {
			if (timeList[i] == currPage) {
				flag = true;
				backFlag = true;
				break;
			}
		}
		if(backFlag){
		$("#content-show-body").animate({width: '60%'});
		setTimeout(function(){$("#pafContent").attr("data",
				$("#contentUrl").val() + "/page_" + currPage + ".pdf");}, 500);
		}
		else
			{
			$("#content-show-body").animate({width: '100%'});
			$("#pafContent").attr("data",
					$("#contentUrl").val() + "/page_" + currPage + ".pdf");
			}
	} else {
		/**
		 * @summary if sign is equal to 1 then content is pdf type and timeList contains the list of pages where questions are added on particular pages.
		 */
		currPage++;
		
		/**
		 * @checking that there is any added question on current page when user have clicked next button.
		 */
		for (var i = 0; i < timeList.length; i++) {
			/**
			 * @summary checking that current page is in list of question added pages.
			 */
			if ((timeList[i] + 1) == currPage && currTime != currPage) {
				flag = true;
				currTime = currPage;
				break;
			}
		}
		if (!flag) {
			$("#content-show-body").animate({width: '100%'});
			$("#pafContent").attr("data",
					$("#contentUrl").val() + "/page_" + currPage + ".pdf");
			$("#next").removeAttr("disabled", "disabled");
		} else {
			currPage--;
			$("#content-show-body").animate({width: '60%'});
			setTimeout(function(){$("#pafContent").attr("data",
					$("#contentUrl").val() + "/page_" + currPage + ".pdf");}, 500);
		}
	}

	if (currPage == parseInt($("#contentPages").val())) {
		/**
		 * length 
		 */
		$("#next").attr("disabled", "disabled");
	} else {
		$("#next").removeAttr("disabled");
	}
	if (currPage == 1) {
		$("#previous").attr("disabled", "disabled");
	} else {
		$("#previous").removeAttr("disabled");
	}
	$("#pageTextCount").text(currPage);
	$("#time").val(currPage);
	if (flag) {
		$("#continue").attr("onClick", "continuePDF(" + currPage + ")");
		$("#questionListDiv").show(1000);
		$("#showQuestionDiv" + currPage).show(1000);
		$("#next").attr("disabled", "disabled");
		$("#previous").attr("disabled", "disabled");
	} else {
		if (!backFlag){
			currTime = 0;
		}
	}
}

function continuePDF(currPage) {
	$("#questionListDiv").hide();
	$("#showQuestionDiv" + currPage).hide();
	if (currPage == parseInt($("#contentPages").val())) {
		$("#next").attr("disabled", "disabled");
	} else {
		$("#next").removeAttr("disabled");
	}
	if (currPage == 1) {
		$("#previous").attr("disabled", "disabled");
	} else {
		$("#previous").removeAttr("disabled");
	}
	$("#content-show-body").animate({width: '100%'});
}

function createPDFQuestionUI(videoContentList, idFrom) {
	var currentshowingpage = messages['lbl.showingpage']
			.replace('#currectpage',
					'<span id="pageTextCount" class="btn" style="padding: 6px 0px;">1</span>')
			.replace('#totalpage', videoContentList.numPages);
/*	var _pdfsrc = videoContentList.contentPath.split('/');
	var _courseDir = $.trim(_pdfsrc[3]);
	var _pdfDir = $.trim(_pdfsrc[4]);*/
	var _objectDataPath = 'https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc='+videoContentList.contentPath;
	var str = '<div class="box-body" style="width: 100%;float: left;" id="content-show-body"><div class="form-group" style="text-align:left"><label>'
			+ messages['lbl.title']
			+ ': &nbsp;</label> <span class="capitalize">'
			+ videoContentList.contentName
			+ '</span></div>'
			+ '<div class=""><object type="text/html" id="pafContent" style="width: 98%; height: 450px;" data="'+_objectDataPath+'/page_1.pdf">'
			+ '</object></div>'
			+ '<div style="float: left; width: 30%; padding: 2%;"><button disabled="disabled" type="button" id="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCallView(0);"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp'
			+ messages['lbl.previous']
			+ '</span></button></div>'
			+ '<div style="float: left; width: 40%; padding: 2%; text-align: center;">'
			+ currentshowingpage
			+ '</div>'
			+ '<div style="float: right; width: 30%; padding: 2%;"><button type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCallView(1);"><span>'
			+ messages['lbl.next']
			+ '&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button></div>'
			+ '<input type="hidden" id="contentId" name="contentId" value="'
			+ videoContentList.contentId
			+ '"><input type="hidden" id="contentUrl" name="contentUrl" value="'
			+ _objectDataPath
			+ '">'
			+ '<input type="hidden" id="contentPages" name="contentPages" value="'
			+ videoContentList.numPages
			+ '"><input type="hidden" id="contentType" name="contentType" value="'
			+ videoContentList.contentType
			+ '">'
			+ '<input type="hidden" id="time" name="time" value="1"><input type="hidden" id="attemptCount" name="attemptCount" value=""></div>'
			+ '<div id="questionListDiv" style="width: 40%;float: right; display: none;margin-top:45px"><div class="form-group">';
	var upperLoopCnt = 0;
	$
			.each(
					videoContentList.questionList,
					function(i, questionObj) {
						str += '<div class="form-group" style="display: none;" id="showQuestionDiv'
								+ questionObj.time
								+ '"><input type="hidden" id="questionNumber'
								+ questionObj.time
								+ '" value="'
								+ upperLoopCnt
								+ '"><input type="hidden" id="questionId'
								+ upperLoopCnt
								+ '" name="questionId'
								+ upperLoopCnt
								+ '" value="'
								+ questionObj.questionId
								+ '"><input type="hidden" id="timeslot'
								+ upperLoopCnt
								+ '" name="timeslot'
								+ upperLoopCnt
								+ '" value="'
								+ questionObj.time
								+ '">'
								+ '<label>'
								+ messages['lbl.questiontype']
								+ ': &nbsp;</label><span>'
								+ questionObj.questionTypeName
								+ '</span></br>'
								+ '<div class="input-group" style="float: left"><label>'
								+ messages['lbl.question']
								+ ': &nbsp;</label></div><div style="padding-left:40px"><span>'
								+ questionObj.questionName
								+ '</span></div>'
								+ '<label>'
								+ messages['lbl.answers']
								+ '</label></br>';
						var innerLoopCnt = 0;
						$
								.each(
										questionObj.option,
										function(j, optionObj) {
											str += '<div class="input-group" style="float: left"><span class="badge bagde-style">&#'
													+ (innerLoopCnt + 65)
													+ ';</span>&nbsp;&nbsp;&nbsp;&nbsp;';
											if (questionObj.questionType == 1) {
												str += '<input type="checkbox" class="flat-red questionType answerOption'
														+ upperLoopCnt
														+ '"  name="answerStatus'
														+ upperLoopCnt
														+ ''
														+ innerLoopCnt
														+ '" id="answerStatus'
														+ upperLoopCnt
														+ ''
														+ innerLoopCnt
														+ '" value="'
														+ optionObj.optionId
														+ '"/>&nbsp;&nbsp;&nbsp;&nbsp;';
											} else {
												str += '<input type="radio" class="flat-red questionType answerOption'
														+ upperLoopCnt
														+ '"  name="answerStatus'
														+ upperLoopCnt
														+ '" id="answerStatus'
														+ upperLoopCnt
														+ ''
														+ innerLoopCnt
														+ '" value="'
														+ optionObj.optionId
														+ '"/>&nbsp;&nbsp;&nbsp;&nbsp;';
											}
											str += '</div><div style="padding-left:40px"><span>'
													+ optionObj.optionName
													+ '</span></div>';
											innerLoopCnt++;
										});

						if (idFrom == 0) {
							str += '<input type="hidden" id="optionCount'
									+ upperLoopCnt
									+ '" value="'
									+ innerLoopCnt
									+ '">'
									+ '<div class="col-xs-12" ><label class="requireFld" id="questionError'
									+ upperLoopCnt
									+ '">'
									+ messages['msg.attemptquestionforsubmit']
									+ '</label></div><button class="btn btn-default btn-flat button-width-large" id="continue'
									+ upperLoopCnt
									+ '" onclick="continuePDF('
									+ questionObj.time
									+ ')" type="button">'
									+ messages['lbl.continue']
									+ '</button>&nbsp;&nbsp;<button class="btn btn-success btn-flat button-width-large" onclick="submitPDFQuestion('
									+ questionObj.time + ')" id="submit'
									+ upperLoopCnt + '" type="button">'
									+ messages['lbl.submit']
									+ '</button></div>';
						} else {
							str += '<input type="hidden" id="optionCount'
									+ upperLoopCnt
									+ '" value="'
									+ innerLoopCnt
									+ '">'
									+ '<div class="col-xs-12" style="display:none;"><label class="requireFld" id="questionError'
									+ upperLoopCnt
									+ '">'
									+ messages['msg.attemptquestionforsubmit']
									+ '</label></div><button class="btn btn-default btn-flat button-width-large" id="continue'
									+ upperLoopCnt + '" onclick="continuePDF('
									+ questionObj.time + ')" type="button">'
									+ messages['lbl.continue']
									+ '</button></div>';
						}
						upperLoopCnt++;
					});

	str += '<input type="hidden" id="questionCount" value="' + upperLoopCnt
			+ '"/></div></div>';
	return str;
}

function submitPDFQuestion(vidtime) {
	var queNum = $("#questionNumber" + vidtime).val();
	if ($(".answerOption" + queNum).is(":checked")) {
		$("#questionError" + queNum).fadeOut();
		var currectAnswer = [];
		for (var i = 0; i < parseInt($("#optionCount" + queNum).val()); i++) {
			if ($("#answerStatus" + queNum + "" + i).is(":checked")) {
				currectAnswer
						.push(parseInt($("#answerStatus" + queNum + "" + i)
								.val()));
			}
		}
		$.ajax({
			url : "saveAttemptContentQuestion",
			data : {
				"sectionId" : $("#courseSectionId").val(),
				"questionId" : $("#questionId" + queNum).val(),
				"attemptedAnswer" : JSON.stringify(currectAnswer),
				"attemptCounter" : $("#attemptCount").val()
			},
			type : 'POST',
			success : function(response) {
				if (response != null && response != 0) {
					$("#attemptCount").val(response);
					$(".answerOption" + queNum).remove();
					$("#submit" + queNum).remove();
					continuePDF(vidtime);
				} else {
					$("#contentErrorAlert p")
							.text(messages['msg.datanotsaved']);
					$("#contentErrorAlert").modal('show');
				}
			}
		});
	} else {
		$("#questionError" + queNum).fadeIn();
	}
}

var loadinstance;
function showNewSlide(sign) {
	var aud = document.getElementById('myaudio');
	if (aud != null) {
		aud.pause();
	}
	var currPage = parseInt($("#time").val());
	var actionPerformed = false;
	if (sign == 0 || (currPage + 1) <= parseInt($("#contentPages").val())) {
		clearInterval(interval);
		actionPerformed = true;
		//$("#pafContent").removeAttr("src");
		$("#lazy-image").empty();
		var id = currPage - 1;
		if (sign == 0) {
			currPage--;
			var datasrc = $("#contentUrl").val() + "/page_" + currPage + ".png";
			var img = '<img id="pafContent" data-sign="'+sign+'" data-id="'+(id-1)+'" class="lazy loading" style="width: 98%; max-height: 450px; display: inline-block;" data-src="'+datasrc+'">'
			$("#lazy-image").append(img);
			//addAudioAndTime((id - 1));
		} else {
			//$("#lazy-image").empty();
			currPage++;
			var datasrc = $("#contentUrl").val() + "/page_" + currPage + ".png";
			var img = '<img id="pafContent" data-sign="'+sign+'" data-id="'+(id+1)+'" class="lazy loading" style="width: 98%; max-height: 450px; display: inline-block;" data-src="'+datasrc+'">'
			$("#lazy-image").append(img);
			/*$("#pafContent").attr("data-src",
					$("#contentUrl").val() + "/page_" + currPage + ".png");*/
			//addAudioAndTime((id + 1));
		}
		if (currPage == parseInt($("#contentPages").val())) {
			$("#next").attr("disabled", "disabled");
		} else {
			$("#next").removeAttr("disabled");
		}
		if (currPage == 1) {
			$("#previous").attr("disabled", "disabled");
		} else {
			$("#previous").removeAttr("disabled");
		}
		$("#pageTextCount").text(currPage);
		$("#time").val(currPage);
	}
	if(loadinstance!=null){
		loadinstance.destroy();
	}
	loadinstance = $('.lazy').Lazy({
	    bind:'event',
	    chainable: false,
    	beforeLoad: function(element) {
        },
        afterLoad: function(element) {
        	var actionPerformedSign = $(element).data('sign');
        	var actionPerformedId = $.trim($(element).data('id'));
        	var actionImageId = $.trim($("#pafContent").data('id'));
               if(actionPerformedSign!=null && actionPerformedId!=null){
            	   if(actionImageId == actionPerformedId){
	                 addAudioAndTime(parseInt(actionPerformedId));
            	   }

        	}
        },
        onError: function(element) {
        },
        onFinishedAll: function(element) {
        }
});
	//loadinstance.update();
}


var interval = "";
function addAudioAndTime(id) {
	$("#myaudio").removeAttr("src");
	$("#myaudio").hide();
	$("#timeholdlabel").hide();
	clearInterval(interval);
	if (audioList.length > id) {
		if (audioList[id].slideHoldTime != 0) {
			$("#timeholdlabel").show();
			var display = document.getElementById("timehold");
			startTimer(audioList[id].slideHoldTime, display);
		} else if (audioList[id].fileName != null
				&& audioList[id].fileName != "") {
			$("#myaudio").show();
			$("#myaudio").attr(
					"src",
					audioList[id].fileName + '/audio_' + audioList[id].pageNum
							+ '.mp3');
			aud.play();
		}
	}
}

function startTimer(duration, display) {
	var start = Date.now(), diff, minutes, seconds;
	function timer() {
		// get the number of seconds that have elapsed since
		// startTimer() was called
		diff = duration - (((Date.now() - start) / 1000) | 0);

		// does the same job as parseInt truncates the float
		minutes = (diff / 60) | 0;
		seconds = (diff % 60) | 0;

		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		display.textContent = minutes + ":" + seconds;

		if (minutes == 0 && seconds == 0) {
			showNewSlide(1);
			clearInterval(interval);
		}

		if (diff <= 0) {
			// add one second so that the count down starts at the full duration
			// example 05:00 not 04:59
			start = Date.now() + 1000;
		}
	}
	;
	// we don't want to wait a full second before the timer starts
	timer();
	interval = setInterval(timer, 1000);
	// console.log(interval);
}

function createPPTVoiceUI(videoContentList, idFrom) {
	var currentshowingpage = messages['lbl.showingpage']
			.replace('#currectpage',
					'<span id="pageTextCount" class="btn" style="padding: 6px 0px;">1</span>')
			.replace('#totalpage', videoContentList.numPages);
	var str = '<div class="box-body" style="width: 100%;float: left;"><div class="form-group" style="text-align:left"><label>'
			+ messages['lbl.title']
			+ ': &nbsp;</label> <span class="capitalize">'
			+ videoContentList.contentName
			+ '</span></div>'
			+ '<div id="lazy-image" class="form-group"><img id="pafContent" data-src="'
			+ videoContentList.contentPath
			+ '/page_1.png" style="width: 98%; max-height: 450px" class="lazy-first_ppt loading"></div>'
			+ '<div><audio id="myaudio" style="width: 98%; height: 30px;" controls></audio></div>'
			+ '<div><label id="timeholdlabel">'
			+ messages['lbl.slideholdtill']
			+ '&nbsp;&nbsp;<span id="timehold"></span></label></div>'
			+ '<div><div style="float: left; width: 30%; padding: 2%;"><button disabled="disabled" type="button" id="previous" name="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="showNewSlide(0);"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp;'
			+ messages['lbl.previous']
			+ '</span></button></div>'
			+ '<div style="float: left; width: 40%; padding: 2%; text-align: center;">'
			+ currentshowingpage
			+ '</div>'
			+ '<div style="float: right; width: 30%; padding: 2%;"><button type="button" style="float: right;" id="next" name="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="showNewSlide(1);"><span>'
			+ messages['lbl.next']
			+ '&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button></div>'
			+ '<input type="hidden" id="contentUrl" name="contentUrl" value="'
			+ videoContentList.contentPath
			+ '"><input type="hidden" id="contentPages" name="contentPages" value="'
			+ videoContentList.numPages
			+ '">'
			+ '<input type="hidden" id="contentType" name="contentType" value="'
			+ videoContentList.contentType
			+ '"><input type="hidden" id="time" name="time" value="1"></div>';
	return str;
}

/**
 * @summart This is used for checking that course has more sections.
 * @param contentId
 * @param sectionId
 */
var hasMoreContentInSection = function(contentId,sectionId){
    for(var section = 0 ; section<sectionJson.length;section++){
    		if(sectionJson[section].sectionId==sectionId){
    			for(var con = 0;con<sectionJson[section].sectionContent.length;con++){
    				if(sectionJson[section].sectionContent[con].contentId == contentId){
    					if(con < (sectionJson[section].sectionContent.length-1)){
    						$("#next").text("Next Content");
    						$("#next").attr("onclick","showContent('"+sectionId+"','"+sectionJson[section].sectionContent[con + 1].contentId+"')");
    						$("#next").prop("disabled",false);
    					}
    				}
    			}
    	}
    	 
     }
}