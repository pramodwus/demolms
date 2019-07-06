/**
 * function on page load for getting language list and level list for creating new course.
 */
$(document).ready(
		function() {
			$.ajax({
				type : 'GET',
				url : 'getlevelandlanguage?action=levelList',
				success : function(levellist) {
					var list = levellist.split("####");
					for (var i = 0; i < list.length - 1; i++) {
						var levelInfo = list[i].split("@@@@");
						var htmlstr = "<option value='"+levelInfo[0].trim()+"'>"+levelInfo[1].trim()+"</option>"
						$("#courselevel").append(htmlstr);
					}
					if($("#courseId").val()>0){$("#courselevel").val(levelId);}
				}
			});
			$.ajax({
				type : 'GET',
				url : 'getlevelandlanguage?action=languageList',
				success : function(lanlist) {
					var list = lanlist.split("####");
					for (var i = 0; i < list.length - 1; i++) {
						var languageInfo = list[i].split("@@@@");
						var htmlstr = "<option value='"
								+ languageInfo[0].trim() + "'>"
								+ languageInfo[1].trim() + "</option>"
						$("#courselanguage").append(htmlstr);
					}
					if($("#courseId").val()>0){$("#courselanguage").val(languageId);}
				}
			});
			

		});

/**
 * function for submitting the course form after valid validation. 
 */
function submitCourseForm() {
	if (courseadvanceValidate()) {		
		if($("#courseId").val()==""){
			var str = $("#courseName").val();			
			ga('send', {
				  hitType: 'event',
				  eventCategory: 'Create Course',
				  eventAction: str,
				  eventLabel: 'New course created'
				});		
		}
		
		$("#courseform").submit();
	}else{		
		//$('.nav-tabs a[href="#basic_info"]').tab('show');		
		$("#li1").addClass("active");
		$("#li2").removeClass("active");				
	}
}
/**
 * function for checking required fields of basic course have content. 
 */
function coursebasicValidate() {
	if (document.courseform.courseName.value.trim() == "") {
		$("#courseName").css("border-color", "#c95b5b");
		$("#courseNameError").fadeIn();
		document.courseform.courseName.focus();
		return false;
	}
	if ((document.courseform.courseName.value.trim()).length > 50) {
		$("#courseName").css("border-color", "#c95b5b");
		$("#courseNameError1").fadeIn();
		document.courseform.courseName.focus();
		return false;
	}
	if (document.courseform.courseDesc.value.trim() == "") {
		$("#courseDesc").css("border-color", "#c95b5b");
		$("#courseDescError").fadeIn();
		document.courseform.courseDesc.focus();
		return false;
	}

	if ((document.courseform.courseDesc.value.trim()).length > 2000) {
		$("#courseDesc").css("border-color", "#c95b5b");
		$("#courseDescError1").fadeIn();
		document.courseform.courseDesc.focus();
		return false;
	}
	return true;
}
/**
 * function for checking required fields of advance course have content. 
 */
function courseadvanceValidate() {
	var promoVideoUrl = document.courseform.promoVideoUrl.value.trim();
	if (document.courseform.courseName.value.trim() == "") {
		$("#courseName").css("border-color", "#c95b5b");
		$("#courseNameError").fadeIn();
		document.courseform.courseName.focus();
		return false;
	}
	if ((document.courseform.courseName.value.trim()).length > 50) {
		$("#courseName").css("border-color", "#c95b5b");
		$("#courseNameError1").fadeIn();
		document.courseform.courseName.focus();
		return false;
	}

	if (promoVideoUrl!="" && !checkPromoURL(promoVideoUrl)) {
		$("#promoVideoUrl").css("border-color", "#c95b5b");
		$("#promoVideoUrlError").fadeIn();
		document.courseform.promoVideoUrl.focus();
		return false;
	}
	if (document.courseform.courseTag.value.trim() == "") {
		$("#courseTag").css("border-color", "#c95b5b");
		$("#courseTagError").fadeIn();
		document.courseform.courseTag.focus();
		return false;
	}
    
	if (document.courseform.courseDesc.value.trim() == "") {
		$("#courseDesc").css("border-color", "#c95b5b");
		$("#courseDescError").fadeIn();
		document.courseform.courseDesc.focus();
		return false;
	}

	if ((document.courseform.courseDesc.value.trim()).length > 2000) {
		$("#courseDesc").css("border-color", "#c95b5b");
		$("#courseDescError1").fadeIn();
		document.courseform.courseDesc.focus();
		return false;
	}
	return true;
}
/**
 * @summary function for checking video url is valid or not.
 * @param promoUrl This is only parameter giving by user for checking.
 * @returns {Boolean}urlStatus returns true in case of valid otherwise false;
 */
function checkPromoURL(promoUrl){
	var urlStatus=false;
	//var myRegExp =/^(?:(?:https?|ftp):\/\/)(?:\S+(?::\S*)?@)?(?:(?!10(?:\.\d{1,3}){3})(?!127(?:\.\d{1,3}){3})(?!169\.254(?:\.\d{1,3}){2})(?!192\.168(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]+-?)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:\/[^\s]*)?$/i;
	var myRegExp=/^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
	if (myRegExp.test(promoUrl)) {
		urlStatus=true;
	}
	return urlStatus;
}

/**
 * function for fide out to error on keyup event.
 */
function coursekeyValidate() {

	if ((document.courseform.courseName.value.trim()).length > 0) {
		$("#courseName").css("border-color", "#7ac17d");
		$("#courseNameError").fadeOut();
		$("#courseNameError1").fadeOut();
	}

	if ((document.courseform.courseDesc.value.trim()).length > 0) {
		$("#courseDesc").css("border-color", "#7ac17d");
		$("#courseDescError").fadeOut();
		$("#courseDescError1").fadeOut();
	}

	if ((document.courseform.promoVideoUrl.value.trim()).length > 0) {
		$("#promoVideoUrl").css("border-color", "#7ac17d");
		$("#promoVideoUrlError").fadeOut();
	}
	if ((document.courseform.courseTag.value.trim()).length > 0) {
		$("#courseTag").css("border-color", "#7ac17d");
		$("#courseTagError").fadeOut();
	}
	/*if ((document.courseform.courseImage.value.trim()).length > 0) {
		$("#courseImageError").fadeOut();
		$("#courseImageError1").fadeOut();
	}*/
	
}
/**
 * function for showing image preview after selecting image as course icon. 
 */
function showMyImage(fileInput) {
	$("#courseImageError1").fadeOut();	
	var files = fileInput.files;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		var imageType = /image.*/;
		if(!file.type.match(imageType))
		{
		$("#courseImage").val("");
		$("#courseImageError").fadeIn();
		}
		else if(files[i].size>350000){
			$("#courseImage").val("");
			$("#courseImageError1").fadeIn();
		}
		else{
		if (!file.type.match(imageType)) {
			//$("#courseImagePreview").attr('src','');
			continue;
		}
		var img = document.getElementById("courseImagePreview");
		img.file = file;
		
		var reader = new FileReader();
		reader.onload = (function(aImg) {
			return function(e) {
				aImg.src = e.target.result;
			};
		})(img);
		reader.readAsDataURL(file);
	}
	}
}


var tag;
var videoId;
// 1. function for playing video.
function playVideo(){ 
	var promoVideoUrl = document.courseform.promoVideoUrl.value.trim();

	if(checkPromoURL(promoVideoUrl)){
	    // 2. This code loads the IFrame Player API code asynchronously.
		    tag = document.createElement('script');
		    tag.src = "https://www.youtube.com/iframe_api";
		    var firstScriptTag = document.getElementsByTagName('script')[0];
		    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
                    var isV=promoVideoUrl.indexOf("v=");
                    videoId = (isV!= -1)?(promoVideoUrl.split('v=')[1]):(promoVideoUrl.substring(promoVideoUrl.lastIndexOf("/")+1));
                    var indexofamp = videoId.indexOf("&");
                    if(indexofamp!=-1){
        				videoId = videoId.substring(0,videoId.indexOf("&"));
        			}
                   //alert(videoId);
	    }
}
    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    var player;
    function onYouTubeIframeAPIReady() {
      player = new YT.Player('promoVideo', {
        height: '250',
        width: '400',
        videoId: videoId,
        events: {
          'onReady': onPlayerReady,
          'onStateChange': onPlayerStateChange
        }
      });
    }
    
    var done = false;
    // 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
      //event.target.playVideo();
    	event.target.stopVideo();
		done = true;
    }

    // 5. The API calls this function when the player's state changes.
    //    The function indicates that when playing a video (state=1),
    //    the player should play for six seconds and then stop.
    function onPlayerStateChange(event) {
      if (event.data == YT.PlayerState.PLAYING && !done) {
        setTimeout(stopVideo, 6000);
        done = true;
      }
    }
    function stopVideo() {
      player.stopVideo();
    }
    
    var ontabsclick = function(type){    	
    	if(type=="2"){    		
    		submitCourseForm();
    	}/*else if(type=="3"){
    		if (courseadvanceValidate()) {
    			if()
    			location.href="courseViewController?courseId="+$("#courseId").val()+"&isPublish=0";
    		}    		
    	}*/
    }
    /**
     * This function is used for changing the behavior on click of publish schedule date button. 
     */
    $(document).on('ifClicked','input[name="isSchedule"]',function(){
		  var _isSchedule = parseInt($.trim($(this).val()));
		  if(_isSchedule==1){
			  $("#schedulePublishDateDiv").show();
		  }
		  else
			  {
			  $("#schedulePublishDateDiv").hide();
			  }
	  });
 /*   function changeState(Data) {
    	if (Data.trim() == 'isSchedule') {
    		if ($("#isSchedule").val().trim() == '0') {
    			$("#isSchedule").val('1');
    			$("#schedulePublishDateDiv").show();
    		} else {
    			$("#isSchedule").val('0');
    			$("#schedulePublishDateDiv").hide();
    		}
    	}
    }*/