const SINGLE_CHOICE_TYPE = 2;
const MULTI_CHOICE_TYPE = 1;
var sessionId;
var contentId;
var attemptId;
var video;
var  questionCount=1;



video = document.getElementById('video');
function playM3u8(url){
	debugger;
	video = document.getElementById('video');
  if(Hls.isSupported()) {
      var config = {
        debug: true,
        xhrSetup: function (xhr,url) {

          xhr.withCredentials = true;
          xhr.setRequestHeader("Access-Control-Allow-Headers","Content-Type, Accept, X-Requested-With");
          // xhr.setRequestHeader("Access-Control-Allow-Origin","*.eluminate.in");
          xhr.setRequestHeader("Access-Control-Allow-Credentials","true");
          }
        };
      var hls = new Hls(config);
      var m3u8Url = decodeURIComponent(url)
    
      hls.loadSource(m3u8Url);
      hls.attachMedia(video);
      hls.on(Hls.Events.MANIFEST_PARSED,function() {
        video.play();
      });
      //document.title = url
    }
	else if (video.canPlayType('application/vnd.apple.mpegurl')) {
		video.src = url;
		video.addEventListener('canplay',function() {
		  video.play();
		});
	
		//document.title = url;
  	}
    $('#video').on('click', function(){this.paused?this.play():this.pause();});
    Mousetrap.bind('space', playPause);
    Mousetrap.bind('up', volumeUp);
    Mousetrap.bind('down', volumeDown);
    Mousetrap.bind('right', seekRight);
    Mousetrap.bind('left', seekLeft);
    Mousetrap.bind('f', vidFullscreen);

  
}

function playPause() {
    video.paused?video.play():video.pause();
}

function volumeUp() {
    if(video.volume <= 0.9) video.volume+=0.1;
}

function volumeDown() {
    if(video.volume >= 0.1) video.volume-=0.1;
}

function seekRight() {
    video.currentTime+=5;
}

function seekLeft() {
    video.currentTime-=5;
}

function vidFullscreen() {
    if (video.requestFullscreen) {
      video.requestFullscreen();
  } else if (video.mozRequestFullScreen) {
      video.mozRequestFullScreen();
  } else if (video.webkitRequestFullscreen) {
      video.webkitRequestFullscreen();
    }
}
















/**
 * This method is used for loading the content.
 */
$(".chapter-content-box").on('click', '.load-content', function() {
	 debugger;

	var contentId = $(this).data('contentid');
	var attemptId=$(this).data('attemptid');
	var sessionId = $(this).closest('.course-session-menu').data('sessionid');
	               

	//var requiredDataElement = $(this).closest('.chapter-content-box').find('#required-user-data'+sessionId);
	var requiredDataElement = $(this).closest('.chapter-content-box').find('#required-user-data'+sessionId);
	//var activeSessionId=$(requiredDataElement).data('activeid');   
	 //var sessionactiveid=$(requiredDataElement).data('sessionactiveid');   
	var isActActive = $("#act-div-" + sessionId).hasClass('active');
	if(!isActActive) {
		
		$(".act-div").removeClass('active');
		$("#act-div-" + sessionId).addClass('active');
	}
	var courseId = $(requiredDataElement).data('courseid');
	var sectionId = $(requiredDataElement).data('sectionid');
	//var attemptId = $(requiredDataElement).data('attemptid');
//	$("#act-div-"+activeSessionId).addClass("active");
	//$("#act-div-"+sessionactiveid).removeClass("active");
	getContentDetails(courseId,sectionId, attemptId, sessionId, contentId);
});


function reviewClick() {
	debugger;
	     var courseId=$("#courseId").val();
		var contentId=$("#contentId").val();
		var sectionId=$("#sectionId").val();
		var attemptId=$("#attemptId").val();
		var sessionId=$("#sessionId").val();
	getContentDetails(courseId,sectionId, attemptId, sessionId, contentId);
}



/**
 * This method is used for getting content details.
 * @param courseId
 * @param sectionId
 * @param attemptId
 * @param sessionId
 * @param contentId
 * @returns
 */
function getContentDetails(courseId, sectionId, attemptId, sessionId, contentId) {
	debugger;
    var url = "api/course/detail/" + courseId + "/" + sectionId + "/" + attemptId + "/" + sessionId + "/" + contentId;
    submitAjaxRequestCors(url, 'GET', '', loadContentDetails);
}

/**
 * This method is used for handling loading content response.
 * @param response
 * @returns
 */
function loadContentDetails(response) {
	debugger;
	if(response.code == 1 && response.result != null) {
		var content = response.result;
		var contentType = content.contentType
		switch(contentType) {
		case 'TEST':
			startAssessment(content);
			break;
		case 'VIDEO':
			playVideoContent(content)
			break;
		}
	}
}

/**
 * This method is used for loading the assessment type content.
 * @param content
 * @returns
 */
function startAssessment(content) {
	debugger;
	questionCount=1;
	var isReview = content.test.isReview;
	var testId=content.test.testId;
	 contentId=content.contentId;
	 sessionId=content.sessionId;
	 attemptId=content.attemptId;
	console.log(content);
	$("#navBar").remove();
	if(isReview != null && isReview == 1) {
	
		
	var testHTML = $("#reviewtestcontentloadtemplate").tmpl(content);
	    // $("#feedback-div").hide();
		$("#course-content-view-div").html(testHTML);
		var questionlength = $("#course-content-view-div .questionAllDiv.active").prev().length;
		if(questionlength == 0)
			{
			$("#course-content-view-div .previous-question-btn").hide();
			}
		
	} else {
	      
		getTestDetail(testId); 
	
	}
}
/**
 * This method is used for get test detail.
 * @param content
 * @returns
 */
function getTestDetail(testId) {
	debugger;
	   var url = "api/gettestdetails/" + testId;
	    submitAjaxRequestCors(url, 'GET', '', responseTestDetail);
	
}

function responseTestDetail(response) {
	questionCount=1;
  var content=response.test;
	debugger;
	console.log(content);
  if(response.test != null)
{
	var preTest=$("#pretesttemplate").tmpl(content);
	
	 $("#course-content-view-div").html(preTest);
	 $("#feedback-div").hide();

}   
  
}


function loadtesttemplate()
{
	
	debugger;
	  var courseId=$("#courseId").val();
	
		var sectionId=$("#sectionId").val();
	//	var attemptId=$("#attemptId").val();
		
		
	
		   var url = "api/course/detail/" + courseId + "/" + sectionId + "/" + attemptId + "/" + sessionId + "/" + contentId;
		    submitAjaxRequestCors(url, 'GET', '', startTest);
	
}

function startTest(response)
{	
	questionCount=1;
	var content = response.result;
	console.log(response);
	var questionlength = $("#course-content-view-div .questionAllDiv.active").prev().length;
	
	var testHTML = $("#testcontentloadtemplate").tmpl(content);
	$("#course-content-view-div").html(testHTML);
	if(questionlength == 0)
	{
	$("#course-content-view-div .previous-question-btn").hide();
	}
	var timer = new Timer();
	timer.start();
	timer.addEventListener('secondsUpdated', function (e) {
	    $('#basicUsage').html(timer.getTimeValues().toString());
	});
	var getdata=JSON.stringify(createJsonforGet1());
	console.log(getdata);
	$.ajax({
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : getdata,
		contentType : "application/json",
		url : 'api/getuserFeedback',
		headers : {
			 'Authorization': $("#token").val()
		},
		success : function(response) {
			var feed=response.result.feedbackDetails.userFeedback;
			var getBad=response.result.feedbackDetails.isBad;
			var getGood=response.result.feedbackDetails.isGood;
			var getExcellent=response.result.feedbackDetails.isExcellent;
			
			$('#badt').val(response.result.feedbackDetails.badReviewCount);
			$('#goodt').val(response.result.feedbackDetails.goodReviewCount);
			$('#excellentt').val(response.result.feedbackDetails.excellentReviewCount);
			//$('#feedbackData').attr('disabled',true);
			$('#feedbackData').val(response.result.feedbackDetails.userFeedback);
			if(getBad==true){
				isBadr=true;
				isGoodr=false;
				isExcellentr=false; 
				$("#bad2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#bad1').removeAttr("style");
				 $('#good1').prop("style","filter: grayscale(100%)");
				 $('#excellent1').prop("style","filter: grayscale(100%)");
				 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(getGood==true){
				isBadr=false;
				isGoodr=true;
				isExcellentr=false;
				 $("#good2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#good1').removeAttr("style");
				 $('#bad1').prop("style","filter: grayscale(100%)");
				 $('#excellent1').prop("style","filter: grayscale(100%)");
				 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(getExcellent==true){
				isBadr=false;
				isGoodr=false;
				isExcellentr=true;
				$("#excellent2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#excellent1').removeAttr("style");
				 $('#bad1').prop("style","filter: grayscale(100%)");
				 $('#good1').prop("style","filter: grayscale(100%)");
				 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(feed!=null && feed!="")
				{
				$('#feedbackData').prop('value',feed);
				$('#feedbackData').prop('disabled', true);
				$('.themeBtn').hide();
				}
		}
	});
}


function createJsonforGet1(){
	var courseId=$('#courseId').val();
	var sectionId=$('#sectionId').val();
	var sessionId =$('#sessionId').val();
	var userId=$('#userId').val();
	var contentId=$('#contentId').val();
	
	var obj={
			"courseId":courseId,
			"sectionId":sectionId,
			"sessionId":sessionId,
			"contentId":contentId,
	        "userId":userId,
	        
			}
	return obj;
	}


/**
 * This method is used VIDEO type content.
 * @param content
 * @returns
 */
function playVideoContent(content) {
	
	debugger;
	console.log(content);
	$("#navBar").remove();
	console.log(content.streamingUrl);
	
	var videoContentHTML = $("#videocontentloadtemplate").tmpl(content);
	$("#course-content-view-div").html(videoContentHTML);
	playM3u8(content.streamingUrl);
	var getdata=JSON.stringify(createJsonforGet1());
	console.log(getdata);
	$.ajax({
		type : 'POST',
		crossDomain : true,
		dataType : 'json',
		data : getdata,
		contentType : "application/json",
		url : 'api/getuserFeedback',
		headers : {
			 'Authorization': $("#token").val()
		},
		success : function(response) {
			debugger;
			var feed=response.result.feedbackDetails.userFeedback;
			var getBad=response.result.feedbackDetails.isBad;
			var getGood=response.result.feedbackDetails.isGood;
			var getExcellent=response.result.feedbackDetails.isExcellent;
			
			$('#badt').val(response.result.feedbackDetails.badReviewCount);
			$('#goodt').val(response.result.feedbackDetails.goodReviewCount);
			$('#excellentt').val(response.result.feedbackDetails.excellentReviewCount);
			//$('#feedbackData').attr('disabled',true);
			$('#feedbackData').val(response.result.feedbackDetails.userFeedback);
			if(getBad==true){
				isBadr=true;
				isGoodr=false;
				isExcellentr=false; 
				$("#bad2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#bad1').removeAttr("style");
				 $('#good1').prop("style","filter: grayscale(100%)");
				 $('#excellent1').prop("style","filter: grayscale(100%)");
				 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(getGood==true){
				isBadr=false;
				isGoodr=true;
				isExcellentr=false;
				 $("#good2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#good1').removeAttr("style");
				 $('#bad1').prop("style","filter: grayscale(100%)");
				 $('#excellent1').prop("style","filter: grayscale(100%)");
				 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#excellent2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(getExcellent==true){
				isBadr=false;
				isGoodr=false;
				isExcellentr=true;
				$("#excellent2").addClass("_2oeEO").addClass("g4jOE").addClass("_3ctp0");
				 $('#excellent1').removeAttr("style");
				 $('#bad1').prop("style","filter: grayscale(100%)");
				 $('#good1').prop("style","filter: grayscale(100%)");
				 $("#good2").removeClass("_3ctp0 g4jOE _3ctp0");
				 $("#bad2").removeClass("_3ctp0 g4jOE _3ctp0");
			}
			if(feed!=null && feed!="")
				{
				$('#feedbackData').prop('value',feed);
				$('#feedbackData').prop('disabled', true);
				$('.themeBtn').hide();
				}
		}
	});


		
//	bindVideoEvent();
}

/**
 * Take the action when user selects an option as a correct answer.
 */
$("#course-content-view-div").on('click', '.check-answer', function() {
	debugger;
	var type = $(this).closest('.questionAllDiv').data('type');
	if(type == SINGLE_CHOICE_TYPE) {
		if($(this).find('.badge.bagde-style').hasClass("button-color-blue")) {
			var object = $(this).closest('.singleMultiChoice');
			$(object).find('.button-green-color').removeClass("button-green-color").addClass('button-color-blue');
			$(this).find('.badge.bagde-style').removeClass("button-color-blue");
			$(this).find('.badge.bagde-style').addClass("button-green-color");
		} else {
			$(this).find('.badge.bagde-style').removeClass("button-green-color");
			$(this).find('.badge.bagde-style').addClass("button-color-blue");
		}
	} else {
		if($(this).find('.badge.bagde-style').hasClass("button-color-blue")) {
			$(this).find('.badge.bagde-style').removeClass("button-color-blue");
			$(this).find('.badge.bagde-style').addClass("button-green-color");
		} else {
			$(this).find('.badge.bagde-style').removeClass("button-green-color");
			$(this).find('.badge.bagde-style').addClass("button-color-blue");
		}
	}
});

/**
 * Load Next Question when user clicks on next button.
 */
$("#course-content-view-div").on('click', '.next-question-btn', function() {
	debugger;
	questionCount=questionCount+1;
   var size=$("#course-content-view-div .questionAllDiv").length;
   if(size == questionCount)
	   $('#course-content-view-div .next-question-btn').hide();
   
	var nextElement = $("#course-content-view-div .questionAllDiv.active").next();
	var hasNextElement = $(nextElement).hasClass('questionAllDiv');
	if(hasNextElement) {
		$('#course-content-view-div .previous-question-btn').attr("disabled", false);
		$('#course-content-view-div .previous-question-btn').show();
		$("#course-content-view-div .questionAllDiv.active").removeClass('active');
		$(nextElement).addClass('active');
	}
	else
		{
		$("#course-content-view-div .next-question-btn").attr("disabled", true);
		}
	

});

/**
 * Load Previous Question when user clicks on previous button.
 */
$("#course-content-view-div").on('click', '.previous-question-btn', function() {
	debugger;
	questionCount=questionCount-1;
	var preElement = $("#course-content-view-div .questionAllDiv.active").prev();
	var questionlength = $("#course-content-view-div .questionAllDiv.active").prev().length;
	if(questionCount == 1)
		{
		$("#course-content-view-div .previous-question-btn").hide();
		}
	
	var hasPreElement = $(preElement).hasClass('questionAllDiv');
	if(hasPreElement) {
		$('#course-content-view-div .next-question-btn').attr("disabled", false);
		   $('#course-content-view-div .next-question-btn').show();
		$("#course-content-view-div .questionAllDiv.active").removeClass('active');
		$(preElement).addClass('active');
	}
	else
		{
		$("#course-content-view-div .previous-question-btn").attr("disabled", true);
		
		}
	
});

/**
 * Get option order html like A, B, C..
 * @param index
 * @returns
 */
function getOptionIndexHtml(index) {
	return "&#" + (65 + index);
}

/**
 * This method is used for generating the test json for submission.
 * @returns
 */
function getSubmitTestJSON() {
	var sectionList = [];
	var totalQuestion = 0;
	$("#given-test-content .test-section-div").each(function() {
		var secId = $(this).data('sectionid'); 
		var questionList = [];
		$(this).find(".questionAllDiv").each(function() {
			var quesId = $(this).data('questionid');
			var givenAnswer = [];
			$(this).find('.singleMultiChoice .option-click').each(function() {
				var isChecked = $(this).find('.check-answer .badge.bagde-style').hasClass("button-green-color");
				if(isChecked) {
					givenAnswer.push($(this).data('optionid'));
				}
			});
			var question = {
					"questionId" : quesId,
					"givenAnswer" : givenAnswer
			}
			questionList.push(question);
			totalQuestion++;
		});
		var section = {
				"sectionId" : secId,
				"sectionTime": "0",
				"totalQuestions": questionList.length,
				"sectionStatus": 1,
				"questionList": questionList
		};
		sectionList.push(section);
	});
	var result = {
			  "isPractice":$("#practiceId").val(),
			  "testTime": 0,
			  "totalQuestion": totalQuestion,
			  "sectionlist": sectionList
			}
	console.log(JSON.stringify(result));
	return result;
}

/**
 * Submit the test when user clicks on submit button.
 */
$("#course-content-view-div").on('click', '.submit-test-details', function(){

	var viewId = $("#given-test-content").data('viewid');
	if(viewId != null && viewId > 0) {
		var data = JSON.stringify(getSubmitTestJSON());
	    var url = "api/course/savetest/" + viewId;
	    submitAjaxRequestCors(url, 'POST', data, handleTestSubmissionResponse);
	}
	
});

/**
 * Handle the test submission response.
 * @param response
 * @returns
 */
function handleTestSubmissionResponse(response) {
	if(response.code == 1 && response.result != null) {
		questionCount=1;
		 console.log(response);
		var result = response.result;
		
		 var testHTML= $("#submitiontemplate").tmpl(result);
			$("#course-content-view-div").html(testHTML);
		var contentId = result.contentId;
		var sessionId = result.sessionId;
		
		//redirectOnNextContent(sessionId, contentId);
	} else {
		alert("Something went wrong, please try again.");
		
	}
}

/**
 * This method is used for binging the end event with video for identify that video has been finished.
 * @returns
 */
function bindVideoEvent() {
	$("#course-content-view-div video").bind("ended", function() {
	   var sessionId = $(this).data('sessionid');
	   var contentId = $(this).data('contentid');
	   redirectOnNextContent(sessionId, contentId);
	});
}

/**
 * This method is used for redirecting on next content.
 * @param preSessionId
 * @param preContentId
 * @returns
 */
function redirectOnNextContent(preSessionId, preContentId) {
	debugger;
	var orderNum= $("#load-content-" + preSessionId + "-" + preContentId).data('order');
	//var orderNum=$('#value-'+preSessionId+'-'+preContentId).val();
	if(orderNum != null && orderNum > 0) {
		var nextElementId = $(".load-content[data-order='" + (orderNum + 1) + "']").attr('id');
		if(nextElementId != null) {
			nextElementId = "#" + nextElementId;
			var contentId = $(nextElementId).data('contentid');
			var sessionId = $(nextElementId).data('sessionid');
			var isDisplayed = $(nextElementId).closest('.course-session-menu').hasClass('show');
			if(!isDisplayed) {
				$(".course-session-menu").removeClass('show');
				$(nextElementId).closest('.course-session-menu').addClass('show');
			}
			var isActActive = $("#act-div-" + sessionId).hasClass('active');
			if(!isActActive) {
				$(".act-div").removeClass('active');
				$("#act-div-" + sessionId).addClass('active');
			}
			$(nextElementId).click();
		} else {
			/**
			 * when there is no more next content.
			 */
			$("#course-content-view-div").empty();
			if (alert("For viewing further sessions please pre book this course")) {
		        $(this).prev('span.text').remove();
		    	
		    }
			window.location.href = "http://qa.eluminate.in/all-courses";
		}
	}
}



function redirectOnNextContentForLockedTests(preSessionId, preContentId) {
	debugger;
	var orderNum= $("#load-content-" + preSessionId + "-" + preContentId).data('order');
	//var orderNum=$('#value-'+preSessionId+'-'+preContentId).val();
	if(orderNum != null && orderNum > 0) {
		var nextElementId = $(".load-content[data-order='" + (orderNum+2) + "']").attr('id');
		if(nextElementId != null) {
			nextElementId = "#" + nextElementId;
			var contentId = $(nextElementId).data('contentid');
			var sessionId = $(nextElementId).data('sessionid');
			var isDisplayed = $(nextElementId).closest('.course-session-menu').hasClass('show');
			if(!isDisplayed) {
				$(".course-session-menu").removeClass('show');
				$(nextElementId).closest('.course-session-menu').addClass('show');
			}
			var isActActive = $("#act-div-" + sessionId).hasClass('active');
			if(!isActActive) {
				$(".act-div").removeClass('active');
				$("#act-div-" + sessionId).addClass('active');
			}
			$(nextElementId).click();
		} else {
			/**
			 * when there is no more next content.
			 */
			$("#course-content-view-div").empty();
			if (alert("For viewing further sessions please pre book this course")) {
		        $(this).prev('span.text').remove();
		    	
		    }
			window.location.href = "http://qa.eluminate.in/all-courses";
		}
	}
}


/**
 * When user clicks the next button while review the test.
 */
$("#course-content-view-div").on('click', '.go-to-next-content', function(){
	var contentId = $(this).data('contentid');
	var sessionId = $(this).data('sessionid');
	redirectOnNextContent(sessionId, contentId);
});

