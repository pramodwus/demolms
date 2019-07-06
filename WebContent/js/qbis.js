/**
 * functon is used for sumitting test test information on server using ajax.
 */
function submitTest() {
	if (testValidate()) {
		var testName = $("#testName").val();
		var scoreType = $("#scoreType").val();
		var formData = new FormData($("#testForm")[0]);
		$("#overlay").show();
		$.ajax({
			type : 'POST',
			url : 'testSubmit',
			processData : false,
			contentType: false,
			data : formData,
			success : function(testId) {
				$("#overlay").hide();
				if ($.trim(testId) > 0) {
					$(window).off('beforeunload');
					if(courseId!=null && courseSectionId!=null){
						var url='prepareTestQuestions?testId='+testId+'&courseId='+courseId+'&sectionId='+courseSectionId;
						window.location.href=contentId!=null?url+'&contentId='+contentId:url;	 
					}
					else{
					window.location.href='prepareTestQuestions?testId='+testId;
					    }
				} else {
					$("#testAlert p").text(
					"Something went wrong,Please try again");
					$('#testAlert').modal('show');
				}
			},
			error :function(xhr, ajaxOptions, thrownError){
				$("#overlay").hide();
				$("#testAlert p").text(
						"Refresh the page");
				$('#testAlert').modal('show');
			}
		});
	}
}
/**
 * This function is used for changing the behavior on click of toggle button. 
 * @param Data
 */
function changeState(Data) {
	if (Data.trim() == 'view') {
		if ($("#view2").val().trim() == 'Off') {
			$("#view2").val('On');
			$("#view1").val('0');
		} else {
			$("#view2").val('Off');
			$("#view1").val('1');
		}
	}

	if (Data.trim() == 'scoreType') {
		if ($("#scoreType").val().trim() == '0') {
			$("#scoreType").val('1');
			$("#scoreTypeOnOff").show();
			$("#scoreMark").val('');
		} else {
			$("#scoreType").val('0');
			$("#scoreMark").val('');
			$("#scoreTypeOnOff").hide();
			$("#scoreMark").css("border-color", "#7ac17d");
			$("#scoreMarkError").fadeOut();
			$("#scoreMarkError1").fadeOut();
		}
	}
	if (Data.trim() == 'limitAttempts') {
		if ($("#limitAttempts").val().trim() == 'Off') {
			$("#limitAttempts").val('On');
			$("#limitAttemptsOnOff").show();
			$("#maxAttempts").val('');			
		} else {			
			$("#limitAttempts").val('Off');
			$("#limitAttemptsOnOff").hide();
			$("#maxAttempts").css("border-color", "#7ac17d");
			$("#maxAttemptsError").fadeOut();
			$("#maxAttemptsError1").fadeOut();
		}
	}
	if(Data.trim() == 'isReview'){
		if ($("#isReview").val().trim() == 0) {
			$("#isReview").val('1');
			$("#isReviewOnOff").show();						
		} else {			
			$("#isReview").val('0');
			$("#isReviewOnOff").hide();			
		}
	}
	if (Data.trim() == 'negType') {
		if ($("#negType").val().trim() == 'Off') {
			$("#negType").val('On');
			$("#negMark").val('');
			$("#negTypeOnOff").show();
		} else {
			$("#negType").val('Off');
			$("#negMark").val('0');
			$("#negTypeOnOff").hide();
			$("#negMark").css("border-color", "#7ac17d");
			$("#negMarkError").fadeOut();
			$("#negMarkError1").fadeOut();
			$("#negMarkError2").fadeOut();
		}
	}
	if (Data.trim() == 'time') {
		if ($("#time").val().trim() == 'Off') {
			$("#time").val('On');
			$("#testTime").show();
			$("#timeMin").val('');
		} else {
			$("#time").val('Off');
			$("#timeMin").val('');
			$("#testTime").hide();
			$("#timeError").fadeOut();
			$("#timeError1").fadeOut();
			$("#timeError2").fadeOut();
		}
	}
	if (Data.trim() == 'testPause') {
		if ($("#testPause2").val().trim() == 'Off') {
			$("#testPause2").val('On');
			$("#testPause1").val('1');
		} else {
			$("#testPause2").val('Off');
			$("#testPause1").val('0');
		}
	}
	if (Data.trim() == 'testAdaptive') {
		if ($("#testAdaptive2").val().trim() == 'Off') {
			$("#testAdaptive2").val('On');
			$("#testAdaptive1").val('1');
		} else {
			$("#testAdaptive2").val('Off');
			$("#testAdaptive1").val('0');
		}
	}
	
	if (Data.trim() == 'shuffleSection') {
		if ($("#shuffleSection").val().trim() == '0') {
			$("#shuffleSection").val('1');
		} else {
			$("#shuffleSection").val('0');
		}
	}
	
	if (Data.trim() == 'shuffleQuestion') {
		if ($("#shuffleQuestion").val().trim() == '0') {
			$("#shuffleQuestion").val('1');
		} else {
			$("#shuffleQuestion").val('0');
		}
	}
	
	if (Data.trim() == 'shuffleOption') {
		if ($("#shuffleOption").val().trim() == '0') {
			$("#shuffleOption").val('1');
		} else {
			$("#shuffleOption").val('0');
		}
	}
	
	if (Data.trim() == 'isSchedule') {
		if ($("#isSchedule").val().trim() == '0') {
			$("#isSchedule").val('1');
			$("#schedulePublishDateDiv").show();
		} else {
			$("#isSchedule").val('0');
			$("#schedulePublishDateDiv").hide();
		}
	}
	
}

/**
 * This listener is used for calling the getTestList function as user click on button which has testSetting id.
 */
$("#testSetting").click(function(){
	getTestList();
});

/**
 * Function for getting all test list using ajax.
 */
function getTestList(){
	$("#overlay").show();
	$.ajax({
		url:'gettestlistajax',
		type:'GET',
		error: function(){
			$("#overlay").hide();
			$("#testAlert p").text(
			"Something went wrong,Please try again");
			$('#testAlert').modal('show');
		},
		success: function(testlist){
			/**
			 * after successful getting data as string , parsing it into a json.
			 */
			var data = JSON.parse(testlist);
			/**
			 * calling function for extracting test details from test list json and adding in table.
			 */
			extractTestData(data);
		}
		
	});
}
/**
 * @summary This is used for extracting test details from test list json and adding in table.
 * @param testData This is only parameter which has details about all tests.
 * @return no.
 */
var table;
function extractTestData(testData) {
	if (table != null) {
		$('#testTable').DataTable().destroy();
		//bDestroy = true; // trying out with bDestroy
		}
	/**
	 * This is a empty object which would be used adding  html data as row inside table body. 
	 */
	var row = '';
	/**
	 * before adding any data in table body, make it empty for no duplicate data if operation performs again.
	 */
	$("#tbody").empty();
	/**
	 * a lopping on test list data for extract test details for a particular test.
	 */
	for (var i = 0; i < testData.length; i++) {
		/**
		 * assign test is published or drafted based on test published status, if test is published then its published status would be 1.
		 */
		var testStatus = (testData[i].testPublishStatus == 1 ? "Published"
				: "Drafted");
		/**
		 * assing a html element for adding a row in table body.
		 */
		var hrefLink = '';
	    var testId = $("#testId").val();
		if(updatedTestId == testData[i].testId){
			hrefLink = '<a class="btn btn-success btn-flat button-width-large" disabled >Select Test</a>';
		}
		else
			{
			var onclickAction ='importTestSetting('+ testData[i].testId  + ')';
			hrefLink = '<a class="btn btn-success btn-flat button-width-large" class="close" data-dismiss="modal" onclick="'+onclickAction+'">Select Test</a>';
			}
	       row = row
				+ '<tr><td>'
				+ testData[i].testCreatedDate
				+ '</td>'
				+ '<td>'
				+ testData[i].testName
				+ '</td>'
				+ '<td>'
				+ testStatus
				+ '</td>'
				+ '<td>'+ hrefLink+'</td></tr>';
	}
	/**
	 * append a table row inside table body.
	 */
	$("#tbody").append(row);
	/**
	 * initialize a table into data table
	 */
	table = $("#testTable").dataTable({
		'columnDefs': [{ 'orderable': false, 'targets': [3] }], // hide sort icon on action
 	    'aaSorting': [],
 	    "destroy" : true
	});
	/**
	 * hide the loader
	 */
	$("#overlay").hide();
	/**
	 * after loading all data into table , show pop up for choosing a test.
	 */
	$("#testtablePopup").modal('show');
}

/**
 * @summary fetch test setting based on test id.
 */
function importTestSetting(testId){
	if(testId>0){
		$("#overlay").show();
		$.ajax({
			url:'importTestSetting?testId='+testId,
			type:'GET',
			error:function(){
				$("#overlay").hide();
				$("#testAlert p").text(
				"Something went wrong,Please try again");
				$('#testAlert').modal('show');
			},
			success : function (test){
				extractTestSetting(test);
				$("#overlay").hide();
			}
		});
	}
}

/**
 * @summary import old test setting.
 */
function extractTestSetting(test){
	try {
		$("#testName").val(test.testName);
		CKEDITOR.instances['testInstruct'].setData(test.testInstruct);
		$("#testDesc").html(test.testDesc);
		if (test.view == 1) {
			$("#view2").val('Off');
			$("#view1").val('1');
			$("#view2").prop('checked', false);
		}
		else
			{
			$("#view2").val('On');
			$("#view1").val('0');
			$("#view2").prop('checked', true);
			}
		if (test.negMark == '0') {
			$("#negType").val('Off');
			$("#negType").prop('checked', false);
			$("#negMark").val('0');
			$("#negTypeOnOff").hide();
		}
		if (test.negMark!=0) {
			$("#negType").val('On');
			$("#negType").prop('checked', true);
			$("#negMark").val(test.negMark);
			$("#negTypeOnOff").show();
		}
		
		if (test.testTime == '0') {
			$("#time").val('Off');
			$("#testTime").hide();
			$("#time").prop('checked', false);
		}
		if (test.testTime == '1') {
			$("#time").val('On');
			$("#testTime").show();
			$("#timeMin").val(test.testTime);
			$("#time").prop('checked', true);
		}
		if (test.testPause == 1) {
			$("#testPause2").val('On');
			$("#testPause1").val('1');
			$("#testPause2").prop('checked', true);
		}
		else
			{
			$("#testPause2").val('Off');
			$("#testPause1").val('0');
			$("#testPause2").prop('checked', false);
			}
		if (test.testAdaptive == 1) {
			$("#testAdaptive2").val('On');
			$("#testAdaptive1").val('1');
			$("#testAdaptive2").prop('checked', true);
		}
		else
			{
			$("#testAdaptive2").val('Off');
			$("#testAdaptive1").val('0');
			$("#testAdaptive2").prop('checked', false);
			}
		
		if (test.equalMarkTest == 1) {
			$("#scoreType").prop('checked', true);
			$("#scoreType").val('1');
			$("#scoreTypeOnOff").show();
			$("#scoreMark").val(test.everyQuestionMark);
		} else {
			$("#scoreType").prop('checked', false);
			$("#scoreType").val('0');
			$("#scoreTypeOnOff").hide();
		}

		if (test.maxAttempts != 0) {
			$("#maxAttempts").val(test.maxAttempts);
			$("#limitAttempts").prop('checked', true);
			$("#limitAttempts").val('On');
			$("#limitAttemptsOnOff").show();
		}
		else
			{
			$("#limitAttempts").prop('checked', false);
			$("#limitAttempts").val('Off');
			$("#limitAttemptsOnOff").hide();
			}
		if (test.isReview == 1) {
			$("#isReview").val('1');
			$("#isReviewOnOff").show();
			$("#isReview").prop('checked', true);
			if (test.reviewWithCorrect == 0) {
				$("#withoutCorrect").prop('checked', true);
			} else {
				$("#withCorrect").prop('checked', true);
			}
		}
		else
			{
			$("#isReview").val('0');
			$("#isReview").prop('checked', false);
			$("#isReviewOnOff").hide();
			}
		 if(test.shuffleSection==1){
			 $("#shuffleSection").prop('checked', true);
			 $("#shuffleSection").val('1');
		 }
		 else{
			 $("#shuffleSection").prop('checked', false);
			 $("#shuffleSection").val('0');
		 }
		 if(test.shuffleQuestion==1){
			 $("#shuffleQuestion").prop('checked', true);
			 $("#shuffleQuestion").val('1');
		 }
		 else
			 {
			 $("#shuffleQuestion").prop('checked', false);
			 $("#shuffleQuestion").val('0');
			 }
		 if(test.shuffleOption==1){
			 $("#shuffleOption").prop('checked', true);
			 $("#shuffleOption").val('1');
		 }
		 else
			 {
			 $("#shuffleOption").prop('checked', false);
			 $("#shuffleOption").val('0');
			 }
		var tag = test.testTag;
		var tagData = [];
		if (tag != '') {
			var tagArray = tag.split(',');
			var tagIds = [];
			for (var i = 0; i < tagArray.length; i++) {
				var arr = {
					id : tagArray[i],
					text : tagArray[i]
				};
				tagData.push(arr);
				tagIds.push(tagArray[i]);
			}
			$(".select2").select2({
				data : tagData,
				tags : true,
				tokenSeparators : [ ',', ' ' ]
			});
			$("#multiSelectTag").select2('val', tagIds);

		} else {
			$(".select2").select2({
				tags : true,
				tokenSeparators : [ ',', ' ' ]
			});
		}
	} catch (err) {
		console.log(err.message);
	}
}


/**
 * 
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
 * function for showing image preview after selecting image as course icon. 
 */
function showMyImage(fileInput) {
	$("#testImageError1").fadeOut();	
	var files = fileInput.files;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		var imageType = /image.*/;
		if(!file.type.match(imageType))
		{
		$("#testImage").val("");
		$("#testImageError").fadeIn();
		}
		else if(files[i].size>350000){
			$("#testImage").val("");
			$("#testImageError1").fadeIn();
		}
		else{
		if (!file.type.match(imageType)) {
			//$("#courseImagePreview").attr('src','');
			continue;
		}
		var img = document.getElementById("TestImagePreview");
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
