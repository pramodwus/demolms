/**
 * array of emails of all trainer.
 */
var trainerList = [];
/**
 * array of emails for sharing the course.
 */
var emailList = [];
var map = {};

/**
 * array of Groups for sharing the course.
 */
var groupList = [];
/**
 * Instance of data table.
 */
var sharedCourseTable;
var sharedGroupTable;

/**
 * listener on selectedEmail class
 */
$(document)
		.on(
				'ifClicked',
				'.selectedEmail1',
				function() {
					/**
					 * checking that user has clicked for select the checkobox.
					 */
					if (!$(this).is(':checked')) {
						/**
						 * checking email is already exist in email list array.
						 */
						if (!isAlreadyExist($(this).val())) {
							/**
							 * appending email in emaillistgroup.
							 */
							$("#emaillistgroup")
									.append(
											'<li class="list-margin" id="'
													+ $(this).val()
													+ '"><span class="select-email-list1"><i class="fa fa-close cursor-pointer"></i>&nbsp;'
													+ $(this).val()
													+ '</span></li>');
							/**
							 * push the email value in email list array.
							 */
							groupList.push($(this).val());

						}
					} else {
						/**
						 * when user unchecked the checkbox of email list.
						 */
						var id = document.getElementById($(this).val());
						/**
						 * getting the index value of this unchecked email.
						 */
						var index = getIndex(groupList, $(this).val());
						/**
						 * when index is not null.
						 */
						if (index != null) {
							/**
							 * removing this email from email list array.
							 */
							groupList.splice(index, 1);
							//alert(JSON.stringify(emailList));
							/**
							 * removing div of this email.
							 */
							$(id).remove();
						}
					}
					/**
					 * checking the length of email list and according to it changing the behavior of shared button.
					 */
					if ((groupList.length == 0) && (emailList.length == 0)) {
						$('#sharedCourseButton').prop('disabled', true);
					} else {
						$('#sharedCourseButton').prop('disabled', false);
					}

				}).trigger('change');

/**
 * listener on selectedEmail class
 */
$(document)
		.on(
				'ifClicked',
				'.selectedEmail',
				function() {
					/**
					 * checking that user has clicked for select the checkobox.
					 */
					if (!$(this).is(':checked')) {
						/**
						 * checking email is already exist in email list array.
						 */
						if (!isAlreadyExist($(this).val())) {
							/**
							 * appending email in emaillistgroup.
							 */
							$("#emaillistgroup")
									.append(
											'<li class="list-margin" id="'
													+ $(this).val()
													+ '"><span class="select-email-list"><i class="fa fa-close cursor-pointer"></i>&nbsp;'
													+ $(this).val()
													+ '</span></li>');
							/**
							 * push the email value in email list array.
							 */
							emailList.push($(this).val());
							var arr = $(this).val().split("@");
							map[$(this).val()] = $(
									"#" + arr[0].replace(".", "_")).val();
						}
					} else {
						delete map[$(this).val()];
						/*
						 * when user unchecked the checkbox of email list.
						 */
						var id = document.getElementById($(this).val());
						/**
						 * getting the index value of this unchecked email.
						 */
						var index = getIndex(emailList, $(this).val());
						/**
						 * when index is not null.
						 */
						if (index != null) {
							/**
							 * removing this email from email list array.
							 */
							emailList.splice(index, 1);
							//alert(JSON.stringify(emailList));
							/**
							 * removing div of this email.
							 */
							$(id).remove();
						}
					}
					/**
					 * checking the length of email list and according to it changing the behaviour of shared button.
					 */
					if ((groupList.length == 0) && (emailList.length == 0)) {
						$('#sharedCourseButton').prop('disabled', true);
					} else {
						$('#sharedCourseButton').prop('disabled', false);
					}

				}).trigger('change');

$(document).on('ifChecked', '.flat-red', function() {
	if ($("#alluser").is(':checked')) {
		$("#users1").show();
		$("#grouptable").hide();
	}
	if ($("#groups").is(':checked')) {
		$("#users1").hide();
		$("#grouptable").show();
	}
});

/**
 * listener for performing function  on list item for sharing email when user removes the any email from list.
 */
$(document).on('click', '.select-email-list > .fa-close ', function() {
	/**
	 * get the list id of this close button.
	 */
	var id = $(this).closest(".list-margin").attr("id");

	/**
	 * getting all checked checkbox value from data table.
	 */
	$(":checkbox:checked", sharedCourseTable.fnGetNodes()).each(function(i) {
		/**
		 * checking that removing email from list exist in email list data table.
		 */

		if (id == $(this).val()) {
			/**
			 * uncheck the that email checkbox from data table.
			 */
			$(this).iCheck('uncheck');
		}
	});

	/**
	 * getting index of removing email from email list.
	 */
	var index = getIndex(emailList, id);
	/**
	 * if email exists in email list
	 */
	if (index != null) {
		/**
		 * remove the email from email list array.
		 */
		emailList.splice(index, 1);
		/**
		 * get current object.
		 */
		var currObject = document.getElementById(id);

		/**
		 * remove email also from showing list on page.
		 */
		$(currObject).remove()
	}
	/**
	 * according to email array length perform operations on shared button.
	 */
	if ((groupList.length == 0) && (emailList.length == 0)) {
		$('#sharedCourseButton').prop('disabled', true);
	} else {
		$('#sharedCourseButton').prop('disabled', false);
	}

});

/**
 * listner for performing functin  on list item for sharing email when user removes the any email from list.
 */
$(document).on('click', '.select-email-list1 > .fa-close ', function() {
	/**
	 * get the list id of this close button.
	 */
	var id = $(this).closest(".list-margin").attr("id");
	/**
	 * getting all checked checkbox value from data table.
	 */
	$(":checkbox:checked", sharedGroupTable.fnGetNodes()).each(function(i) {
		/**
		 * checking that removing email from list exist in email list data table.
		 */
		if (id == $(this).val()) {
			/**
			 * un check the that email checkbox from data table.
			 */

			$(this).iCheck('uncheck');
		}
	});

	/**
	 * getting index of removing email from email list.
	 */
	var index = getIndex(groupList, id);
	/**
	 * if email exists in email list
	 */
	if (index != null) {
		/**
		 * remove the email from email list array.
		 */
		groupList.splice(index, 1);
		/**
		 * get current object.
		 */
		var currObject = document.getElementById(id);

		/**
		 * remove email also from showing list on page.
		 */
		$(currObject).remove()
	}
	/**
	 * according to email array length perform operations on shared button.
	 */
	if ((groupList.length == 0) && (emailList.length == 0)) {
		$('#sharedCourseButton').prop('disabled', true);
	} else {
		$('#sharedCourseButton').prop('disabled', false);
	}

});

/**
 * @summary This is used for adding email in table and also in email array for
 *          shared the course..
 * 
 * @return no.
 */
function addSharedList() {
	/**
	 * email is valid as per requirement.
	 */
	if (emailValidation()) {
		/**
		 * get value of email.
		 */
		var email = $("#sharedEmail").val().trim();
		//var totalSharedEmail = $('#sharedEmailList > tr').length;
		/**
		 * appending email in email list on page.
		 */
		$("#emaillistgroup")
				.append(
						'<li class="list-margin" id="'
								+ email
								+ '"><span class="select-email-list"><i class="fa fa-close cursor-pointer"></i>&nbsp;'
								+ email + '</span></li>');
		/**
		 * adding email in email list array.
		 */
		emailList.push(email);
		/**
		 * get value of all un selected checkbox's value from data table.
		 */
		/*  $('input[type="checkbox"].selectedEmail, input[type="radio"].selectedEmail').iCheck({
		     checkboxClass: 'icheckbox_square-green',
		        radioClass: 'iradio_square-green'
		   }); */

		map[email] = '0';
		$(":checkbox:not(:checked)", sharedCourseTable.fnGetNodes()).each(
				function(i) {
					/**
					 * if adding email exists also in data table.
					 */
					/* 	var arr= email.split("@");
						map[email]=$("#"+arr[0]).val(); */
					if (email == $(this).val()) {
						/**
						 * make checked to that email also in data table.
						 */
						$(this).iCheck('check');
						var arr = email.split("@");
						map[email] = $("#" + arr[0]).val();
						//$(this).prop('checked', true);
					}
				});
		/**
		 * make disable to shared button.
		 */
		$('#sharedCourseButton').prop('disabled', false);
		$("#sharedEmail").val('');
		$("#sharedEmail").removeClass("errorBorder");
	}
}

function checkPromoURL(promoUrl) {
	var urlStatus = false;
	var myRegExp = /^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
	if (myRegExp.test(promoUrl)) {
		urlStatus = true;
	}
	return urlStatus;
}

/**
 * function is used for showing content.
 */
function showContent(contentURL) {
	$('.modal-lg').css('width', '90%');
	$("#frame").empty();
	var frameHTML = '<iframe src="'
			+ contentURL
			+ '" style="width:100%;height:550px" allowfullscreen webkitallowfullscreen></iframe>';
	$("#frame").append(frameHTML);
	$("#showContent").modal('show');
}

function editCorseDetail(action){
	debugger;
	$("#selectedSection").empty();
	var checkboxes = document.getElementsByName("sectionSelect");
	// loop over them all
	for (var i = 0; i < checkboxes.length; i++) {
		// And stick the checked ones onto an array...
		if (checkboxes[i].checked) {
			var str = '<input type="hidden" name="selectedSection" value="'
					+ checkboxes[i].value + '">';
			$("#selectedSection").append(str);
		}
	}
	if (action == 'sharewithall') {
		$("#coursePublished").modal('show');
	} else if (action == 'sharewithusersgroups')
		getUsersList();
	/*$("#publishAlert p").text(messages['msg.coursescheduledateexpire']);
	$("#dId").text(messages['lbl.edit']);
	$("#dId").attr('onclick',
			'location.href="createCourse?courseId=${course.courseId}"');
	$("#publishAlert").modal('show');*/
}

/**
 * @summary This is used for showing popup after clicking the publish button.
 * 
 * @return no.
 */
function publishPopup(action) {
	/**
	 * @summary if there is no section inside course then show error.
	 */
	if (!coursehasSection()) {
		$("#emptySection p").text(
				messages['msg.minonesectionrequiredforpublishcourse']);
		$("#emptySection").modal('show');

	}

	/**
	 * @summary If there is not selected section for publish then show error.
	 */
	else if (!selectedSectionValidation()) {
		$("#emptySection p").text(messages['msg.selectedsectioncontent.empty']);
		$("#emptySection").modal('show');
	}

	/**
	 * @summary If there is drafted assessment inside course then show error.
	 */
	else if (!validateDraftedTest()) {
		$("#emptySection p")
				.text(messages['msg.coursecotaindraftedassessment']);
		$("#emptySection").modal('show');
	}
	/**
	 * @summary checking that user has provied all important information about course.
	 */
	else if (!checkCourseBasicInfo()) {
		$("#emptySection p").text(messages['msg.coursemandatoryinfo.required']);
		$("#emptySection").modal('show');
	}

	/**
	 * @summary if there is no selected section for publishing then show error.
	 */
/*	else if (checkedSectionCount() == 0) {
		$("#emptySection p").text(
				messages['msg.nosectionselectedforpublishcourse']);
		$("#emptySection").modal('show');
	}
*/
	/**
	 * @summary if course is scheduled then scheduled date has been expired then show error message.
	 */
	else if (checkSchedule()) {
		$("#publishAlert p").text(messages['msg.coursescheduledateexpire']);
		$("#dId").text(messages['lbl.edit']);
		$("#dId").attr('onclick',
				'location.href="createCourse?courseId=${course.courseId}"');
		$("#publishAlert").modal('show');
	}
	/**
	 * @summary check section has content accoridng to its setting means validate the sction's setting.
	 */
	else if (!validateSectionSetting()) {
	}
	/**
	 * @summary publish or share the course.
	 */
	else {
		$("#selectedSection").empty();
		var checkboxes = document.getElementsByName("sectionSelect");
		// loop over them all
		for (var i = 0; i < checkboxes.length; i++) {
			// And stick the checked ones onto an array...
			if (checkboxes[i].checked) {
				var str = '<input type="hidden" name="selectedSection" value="'
						+ checkboxes[i].value + '">';
				$("#selectedSection").append(str);
			}
		}
		if (action == 'sharewithall') {
			$("#coursePublished").modal('show');
		} else if (action == 'sharewithusersgroups')
			getUsersList();
	}
}

/**
 * @summary This is used for publishing the course.
 * 
 * @return no.
 */
function coursePublished() {
	$("#coursePublished").modal('hide');
	$("#overlay").show();

	var data = $("#publishForm").serialize();
	$.ajax({
		type : 'POST',
		url : 'publishedCourse?action=publish',
		data : data,
		error : function() {
			$("#overlay").hide();
		},
		success : function(courseStatus) {
			$("#overlay").hide();
			if (courseStatus) {
				$("#sharedPopUp").fadeIn();
			} else {
				$("#overlay").hide();
				$("#emptySection p").text(messages['lbl.somethingwentwrong']);
				$("#emptySection").modal('show');
			}
		}

	});
}

/**
 * @summary function is used trainee list for selected for sharing the course.
 */
var getTraineeList = function() {
	$.ajax({
		url : 'traineelist',
		typel : 'GET',
		error : function() {
			$("#overlay").hide();
		},
		success : function(traineelist) {
			if (traineelist.length > 0) {
				appendInSharedTable(traineelist);
				$("#overlay").hide();
				$("body").css("overflow", "hidden");
				$("#success").css("overflow-y", "auto");
				$("#sharedEmail").val('');
				$("#shareCourseMessage").val('');
				$("#sharedEmail").removeClass("errorBorder");
				$(".modal-lg").css('width', '80%');
				$("#success").modal({
					backdrop : 'static',
					keyboard : false
				});
			} else {
				$("#overlay").hide();
			}
		}
	});
}

/**
 * @summary function is used get All Users
 */
var getUsersList = function() {
	$.ajax({
		url : 'allUsers?action=' + 'course',
		typel : 'GET',
		error : function() {
			$("#overlay").hide();
		},
		success : function(traineelist) {
			$("#overlay").hide();
			$("#sharedEmail").val('');
			$("#shareCourseMessage").val('');
			$("#emaillistgroup").empty();
			$("#sharedCourseButton").prop('disabled', true);
			appendInSharedTable1(traineelist.groupList);
			appendInSharedTable(traineelist.userList);
			$("#overlay").hide();
			/* $("#success").css("overflow-y","auto"); */
			$("#sharedEmail").removeClass("errorBorder");
			$(".modal-lg").css('width', '80%');
			$("#success").modal('show');
		}
	});
}

function getUsers() {

	$("#sharedGroupTable").hide();
	$("#sharedCourseTable").show();
}
function getGroups() {
	$("#sharedGroupTable").show();
	$("#sharedCourseTable").hide();
}
/**
 * function is used for appending emails of trainee in table.
 */
var appendInSharedTable = function(user) {

	if (sharedCourseTable != null) {
		$("#sharedCourseTable").DataTable().destroy();
	}
	$("#sharedCourseTable tbody").empty();
	/**
	 * make empty to trainer list.
	 */
	trainerList = [];
	emailList = [];
	/**
	 * traversing on user list.
	 */
	for (var i = 0; i < user.length; i++) {
		/**
		 * check that email is of trainee.
		 */
		var arr = user[i].email.split("@");
		var row = '<tr>'
				+ '<td><input type="checkbox" name="selectedEmail" class="selectedEmail" value="'
				+ user[i].email + '"><input type="hidden" id="'
				+ arr[0].replace(".", "_") + '" value="' + user[i].userId
				+ '"></td>' + '<td>' + user[i].email + '</td>' + '</tr>';
		$("#sharedEmailList").append(row);

	}
	$('input[type="checkbox"].selectedEmail, input[type="radio"].selectedEmail')
			.iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green'
			});
	sharedCourseTable = $("#sharedCourseTable").dataTable({
		'columnDefs' : [ {
			'orderable' : false,
			'pageLength' : 8,
			'targets' : [ 0 ]
		} ], // hide sort icon on action
		'aaSorting' : [],
		'language' : datatablelanguagejson,
		"destroy" : true,
	/*  "bLengthChange" : false,
	"bInfo":false,  */
	});
}
/**
 * function is used for appending GroupName .
 */
var appendInSharedTable1 = function(user) {
	if (sharedGroupTable != null) {
		$("#sharedGroupTable").DataTable().destroy();
	}
	$("#sharedGroupTable tbody").empty();
	/**
	 * make empty to trainer list.
	 */
	trainerList = [];
	groupList = [];
	$("#sharedGroupList").empty();
	/**
	 * traversing on user list.
	 */

	for (var i = 0; i < user.length; i++) {
		/**
		 * check that email is of trainee.
		 */
		var row = '<tr>'
				+ '<td><input type="checkbox" name="selectedEmail1" class="selectedEmail1" value="'
				+ user[i].groupName + '"></td>' + '<td>' + user[i].groupName
				+ '</td>' + '</tr>';
		$("#sharedGroupList").append(row);

	}
	$(
			'input[type="checkbox"].selectedEmail1, input[type="radio"].selectedEmail1')
			.iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green'
			});
	sharedGroupTable = $("#sharedGroupTable").dataTable({
		'columnDefs' : [ {
			'orderable' : false,
			'pageLength' : 8,
			'targets' : [ 0 ]
		} ], // hide sort icon on action
		'aaSorting' : [],
		"destroy" : true,
		'language' : datatablelanguagejson
	/*  "bLengthChange" : false,
	"bInfo":false,  */
	});
}
/**
 * @summary This is used for checking that course has minimum one section or
 *          not.
 * 
 * @return {boolean type}valid Return true if course has minimum one section
 *         otherwise false.
 */
function coursehasSection() {
	var valid = true;
	var totalSections = $(".totalSections").length;
	if (totalSections == 0) {
		valid = false;
	}
	return valid;
}

/**
 * @summary This is used for checking that only selected sections have contents
 *          when user click on publish.
 * 
 * @return {boolean type}valid Return true if all selected section's has minimum
 *         one content otherwise false;
 */
function selectedSectionValidation() {
	var loopcount = 0;
	var valid = true;
	var totalSections = $(".totalSections").length;
	var checkboxes = document.getElementsByName("sectionSelect");
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			var totalSectionContentDiv = $('#sectionContent'
					+ checkboxes[i].value + ' > .countSectionContent').length;
			if (totalSectionContentDiv == 0 && loopcount == 0) {
				valid = false;
				loopcount++;
			}
		}
	}
	return valid;
}

/**
 * @summary This is used for checking that user has checked minimum one section
 *          for publishing with course.
 * 
 * @return {boolean type}totalcheckedSection returns true if user has choosed to
 *         minimum one section for publishing otherwise false.
 */
function checkedSectionCount() {
	var totalcheckedSection = 0;
	var checkboxes = document.getElementsByName("sectionSelect");
	// loop over them all
	for (var i = 0; i < checkboxes.length; i++) {
		// And stick the checked ones onto an array...
		if (checkboxes[i].checked) {
			totalcheckedSection++;
		}
	}
	return totalcheckedSection;
}

/**
 * @summary This is used for check email validation.
 * 
 * @return true if it is valid otherwise false.
 */
function emailValidation() {
	var email = $("#sharedEmail").val().trim();
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (email.length == 0) {
		$("#sharedEmail").addClass('errorBorder');
		$("#sharedEmailError1").fadeIn();
		// $("#sharedEmail").focus();
		return false;
	}
	if (!filter.test(email)) {
		$("#sharedEmail").addClass('errorBorder');
		$("#sharedEmailError2").fadeIn();
		// $("#sharedEmail").focus();
		return false;
	}
	if (isAlreadyExist(email)) {
		$("#sharedEmail").addClass('errorBorder');
		$("#sharedEmailError3").fadeIn();
		// $("#sharedEmail").focus();
		return false;
	}
	if (isAlreadyExistAsTrainer(email)) {
		$("#sharedEmail").addClass('errorBorder');
		$("#sharedEmailError4").fadeIn();
		return false;
	}
	return (true);
}

/**
 * @summary This is used for fade out the error related to email validation.
 * 
 * @return no.
 */
function emailKeyUp() {
	var email = $("#sharedEmail").val().trim();
	if (email.length > 0) {
		$("#sharedEmail").removeClass("errorBorder");
		$("#sharedEmailError1").fadeOut();
		$("#sharedEmailError2").fadeOut();
		$("#sharedEmailError3").fadeOut();
		$("#sharedEmailError4").fadeOut();

	}
	if ($("#shareCourseMessage").val().trim().length > 0) {
		$("#shareCourseMessage").removeClass("errorBorder");
		$("#shareCourseMessageError").fadeOut();
	}
}

/**
 * @summary This is used for checking email is already exist in table or not.
 * 
 * @return true if exist otherwise not.
 */
function isAlreadyExist(email) {
	var isExist = false;
	for (var i = 0; i < emailList.length; i++) {
		if (emailList[i] == email) {
			isExist = true;
		}
	}
	return isExist;
}
/**
 * @summary This is used for checking email is already exist as trainer.
 * 
 * @return true if exist otherwise not.
 */
function isAlreadyExistAsTrainer(email) {
	var isExist = false;
	for (var i = 0; i < trainerList.length; i++) {
		if (trainerList[i] == email) {
			isExist = true;
		}
	}
	return isExist;
}

/**
 * @summary This is used for creating mapping of all users with course for
 *          sharing the course.
 * 
 * @return no.
 */
function shared() {
	console.log("shared");
	var message = $("#shareCourseMessage").val().trim();
	if (message.length == 0) {
		$("#shareCourseMessage").addClass('errorBorder');
		$("#shareCourseMessageError").fadeIn();
	} else {
		$("#overlay").show();
		var data = $("#publishForm").serialize();
		$.ajax({
			type : 'POST',
			url : 'publishedCourse?action=publish',
			data : data,
			error : function() {
				$("#overlay").hide();
			},
			success : function(courseStatus) {
				if (courseStatus) {
					shareCourse();
				} else {
					$("#overlay").hide();
					$("#emptySection p").text(
							messages['lbl.somethingwentwrong']);
					$("#emptySection").modal('show');
				}
			}

		});
	}
}

function shareCourse() {
	var message = $("#shareCourseMessage").val().trim();
	var courseId = $("#courseid").val();
	$.ajax({
		type : 'POST',
		url : 'publishedCourse?action=share',
		dataType : 'json',
		data : "emailList=" + emailList + "&courseId=" + courseId
				+ "&groupList=" + groupList + "&emailMap="
				+ JSON.stringify(map) + "&message="
				+ encodeURIComponent(message),
		success : function(courseStatus) {
			$("#success").modal('hide');
			$("#overlay").hide();
			if (courseStatus) {
				$("#sharedPopUp").fadeIn();
			} else {
				$("#emptySection p").text(messages['msg.shared.error']);
				$("#emptySection").modal('show');
			}
		},
		onerror : function(e) {
			$("#overlay").hide();
			$("#emptySection p").text(messages['msg.shared.error']);
			$("#emptySection").modal('show');
		}
	});
}

/**
 * @summary function for validate basic info of course mandatory for publish.
 */
var checkCourseBasicInfo = function() {
	if ($("#cname").val() != "" && $("#ctags").val() != ""
			&& $("#cdesc").val() != "") {
		return true;
	} else {
		return false;
	}

}

/**
 *  @summary function for check content (test) drafted or not. if drafted then return false else true 
 */
var validateDraftedTest = function() {
	var valid = true;
	$.ajax({
		type : 'GET',
		url : 'getdraftedtestcountbycourseid?id=' + $("#courseid").val(),
		async : false,
		success : function(res) {
			if (!res) {
				valid = false;
			}
		}
	});
	return valid;
}

/**
 * function is used for checking that if test is schedued then its publish time is expire or not.
 */
var checkSchedule = function() {
	var isCorrect = false;
	if (isSchedule == 1) {
		$.ajax({
			url : 'checkCourseScheduleTime?courseId=' + courseId,
			type : 'GET',
			async : false,
			error : function(err) {

			},
			success : function(status) {
				if (status) {
					isCorrect = true;
				}
			},
		});
	}
	return isCorrect;
}
/**
 * 
 * @param array
 * @param item
 * @returns {Boolean}
 */
function getIndex(array, item) {
	var index;
	for (var i = 0; i < array.length; i++) {
		if (array[i] === item) {
			index = i;
			break;
		}
	}
	return index;
}

/**
 * @summary This is used for validation the section setting.
 */
var validateSectionSetting = function() {
	var isSectionSettingValidate = false;
	$("#overlay").show();
	$.ajax({
		url : 'checksectionsettingvalidation?courseId='
				+ parseInt($.trim(courseId)),
		method : 'GET',
		async : false,
		error : function() {
			$("#overlay").hide();
			$("#emptySection p").text(messages['msg.somethingwentwrong']);
			$("#emptySection").modal('show');
		},
		success : function(response) {
			if (response.status == 200) {
				$("#overlay").hide();
				isSectionSettingValidate = true;
			} else {
				isSectionSettingValidate = false;
				$("#setting_validation_failed_section_list").empty();
				for (var i = 0; i < response.errorSectionList.length; i++) {
					$("#setting_validation_failed_section_list").append(
							"<li>" + response.errorSectionList[i].sectionName
									+ "</li>");
					$(
							"#sectionContent"
									+ response.errorSectionList[i].sectionId)
							.closest('.totalSections').addClass(
									"section_setting_validation_failed");
				}
				$("#overlay").hide();
				$("#section_setting_validation_failed p").text(
						messages['msg.sectionsettingvalidationfailed']);
				$("#section_setting_validation_failed").modal('show');
			}
		}
	});
	return isSectionSettingValidate;
}