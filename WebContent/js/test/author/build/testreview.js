/**
 * @summary This would be called on click event of button with flat-red class
 */
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
 * @summary event would be fire on click of button.
 */
$(document).on('ifClicked','.selectedEmail',function() {
					/**
					 * checking that user has clicked for select the checkbox.
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
						/**
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
							/**
							 * removing div of this email.
							 */
							$(id).remove();
						}
					}
					/**
					 * checking the length of email list and according to it
					 * changing the behavior of shared button.
					 */
					if ((groupList.length == 0) && (emailList.length == 0)) {
						$('#sharedTestButton').prop('disabled', true);
					} else {
						$('#sharedTestButton').prop('disabled', false);
					}

				}).trigger('change');

/**
 * @summary event would be fire on selectedEmail class
 */
$(document).on('ifClicked','.selectedEmail1',function() {
					/**
					 * checking that user has clicked for select the checkbox.
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
							/**
							 * removing div of this email.
							 */
							$(id).remove();
						}
					}
					/**
					 * checking the length of email list and according to it
					 * changing the behavior of shared button.
					 */
					if ((groupList.length == 0) && (emailList.length == 0)) {
						$('#sharedTestButton').prop('disabled', true);
					} else {
						$('#sharedTestButton').prop('disabled', false);
					}

				}).trigger('change');

/**
 * @summary event would be fire for performing function on list item for sharing
 *          email when user removes the any email from list.
 */
$(document).on('click', '.select-email-list > .fa-close ', function() {
	/**
	 * get the list id of this close button.
	 */
	var id = $(this).closest(".list-margin").attr("id");
	/**
	 * getting all checked checkbox value from data table.
	 */
	$(":checkbox:checked", sharedTestTable.fnGetNodes()).each(function(i) {
		/**
		 * checking that removing email from list exist in email list data
		 * table.
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
	var index = getIndex(emailList, id);
	/**
	 * if email exists in email list
	 */
	if (index != null) {
		/**
		 * remove the email from email list array.
		 */
		emailList.splice(index, 1);
		delete map[id];
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
	/**
	 * according to email array length perform operations on shared button.
	 */
	if ((groupList.length == 0) && (emailList.length == 0)) {
		$('#sharedTestButton').prop('disabled', true);
	} else {
		$('#sharedTestButton').prop('disabled', false);
	}

});

/**
 * @summary event would be fire when remove group name from selected list for
 *          share the assessment.
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
		 * checking that removing email from list exist in email list data
		 * table.
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
		$('#sharedTestButton').prop('disabled', true);
	} else {
		$('#sharedTestButton').prop('disabled', false);
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
		map[email] = '0';
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
		$(":checkbox:not(:checked)", sharedTestTable.fnGetNodes()).each(
				function(i) {
					/**
					 * if adding email exists also in data table.
					 */
					if (email == $(this).val()) {
						/**
						 * make checked to that email also in data table.
						 */
						$(this).iCheck('check');
						var arr = email.split("@");
						map[email] = $("#" + arr[0]).val();
					}
				});
		/**
		 * make disable to shared button.
		 */
		$('#sharedTestButton').prop('disabled', false);
		$("#sharedEmail").val('');
		$("#sharedEmail").removeClass("errorBorder");
		$("#sharedEmailError1").fadeOut();
		$("#sharedEmailError2").fadeOut();
		$("#sharedEmailError3").fadeOut();
		$("#sharedEmailError4").fadeOut();
	}
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
				$("#sharedEmail").val('');
				$("#shareTestMessage").val('');
				$("#sharedEmail").removeClass("errorBorder");
				$("#shareTestMessage").removeClass("errorBorder");
				$("#sharedEmailError1").fadeOut();
				$("#sharedEmailError2").fadeOut();
				$("#sharedEmailError3").fadeOut();
				$("#sharedEmailError4").fadeOut();
				$("#shareTestMessageError").fadeOut();
				$("#emaillistgroup").empty();
				$(".modal-lg").css('width', '80%');
				$("#success").modal('show');
			} else {
				$("#overlay").hide();
			}
		}
	});
}

/**
 * @summary function is used trainee list for selected for sharing the course.
 */
var getUserList = function() {
	$.ajax({
		url : 'allUsers?action=' + 'test',
		typel : 'GET',
		error : function() {
			$("#overlay").hide();
		},
		success : function(traineelist) {

			appendInSharedTable1(traineelist.groupList);
			appendInSharedTable(traineelist.userList);
			$("#overlay").hide();
			$("#sharedEmail").val('');
			$("#shareTestMessage").val('');
			$("#sharedEmail").removeClass("errorBorder");
			$("#shareTestMessage").removeClass("errorBorder");
			$("#sharedEmailError1").fadeOut();
			$("#sharedEmailError2").fadeOut();
			$("#sharedEmailError3").fadeOut();
			$("#sharedEmailError4").fadeOut();
			$("#shareTestMessageError").fadeOut();
			$("#emaillistgroup").empty();
			$(".modal-lg").css('width', '80%');
			$("#success").modal('show');

		}
	});
}

/**
 * function is used for appending GroupName .
 */
var appendInSharedTable1 = function(user) {
	/**
	 * destroy the instance of data table.
	 */
	$('#sharedGroupTable').DataTable().destroy();
	/**
	 * before adding any data in table body, make it empty for no duplicate data
	 * if operation performs again.
	 */
	$("#sharedGroupList").empty();
	/**
	 * make empty to trainer list.
	 */
	trainerList = [];

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
			'targets' : [ 0 ]
		} ], // hide sort icon on action
		'aaSorting' : [],
		'pageLength' : 8,
		"bDestroy" : true,
		'language' : datatablelanguagejson
	/*
	 * "bLengthChange" : false, "bInfo":false,
	 */
	});
}

/**
 * function is used for appending emails of trainee in table.
 */
var appendInSharedTable = function(user) {
	/**
	 * destroy the instance of data table.
	 */
	$('#sharedTestTable').DataTable().destroy();
	/**
	 * before adding any data in table body, make it empty for no duplicate data
	 * if operation performs again.
	 */
	$("#sharedEmailList").empty();
	/**
	 * make empty to trainer list.
	 */
	trainerList = [];
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

	sharedTestTable = $("#sharedTestTable").dataTable({
		'columnDefs' : [ {
			'orderable' : false,
			'targets' : [ 0 ]
		} ], // hide sort icon on action
		'aaSorting' : [],
		'pageLength' : 8,
		"bDestroy" : true,
		'language' : datatablelanguagejson
	/*
	 * "bLengthChange" : false, "bInfo":false,
	 */
	});
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
	if ($("#shareTestMessage").val().trim().length > 0) {
		$("#shareTestMessage").removeClass("errorBorder");
		$("#shareTestMessageError").fadeOut();
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
 * @summary This is used for creating mapping of all users with test for sharing
 *          the test.
 * 
 * @return no.
 */
function shared() {
	var message = $("#shareTestMessage").val().trim();
	if (message.length == 0) {
		$("#shareTestMessage").addClass('errorBorder');
		$("#shareTestMessageError").fadeIn();
	} else {
		$("#overlay").show();
		$.ajax({
			type : 'POST',
			url : 'sharetesttouser',
			data : "emailList=" + emailList + "&groupList=" + groupList
					+ "&emailMap=" + JSON.stringify(map) + "&testId="
					+ parseInt(testId) + "&message="
					+ encodeURIComponent(message),
			success : function(testStatus) {
				$("#success").modal('hide');
				$("#overlay").hide();
				if (testStatus) {
					window.location.href = 'testList';
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
 * @summary function for submiting the test status form.
 */
function saveAllDetail(Obj) {
	var id = Obj.id;
	if (id == 'save') {
		$("#testStatus").val('1');
		saveTest(id);

	}
	if (id == 'publish') {
		if (checkSchedule()) {
			$("#testPublishPopUp").modal('show');
		}
	}
	if (id == 'share') {
		if (checkSchedule()) {
			getUserList();
		}
	}
}

/**
 * @summary Save Test according to its status.
 */
function saveTest(id) {
	var formData = new FormData($("#questionForm")[0]);
	$.ajax({
		type : 'POST',
		url : 'saveTestStatus',
		processData : false,
		contentType : false,
		data : formData,
		success : function(responce) {
			if (id == 'save') {
				location.href = 'testList';
			}
			if (id == 'share') {
				location.href = 'testList';
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {

		}
	});
}

/**
 * @summary This function is used for published the test.
 */
function publishTest() {
	$("#testStatus").val('2');
	saveTest("share");
}

/**
 * @summary redirect on test info tab from Preview tab.
 */
var testInfoTabFromPreviewTab = function() {
	if (courseId != null && courseSectionId != null) {
		var url = 'addEditTest?testId=' + parseInt(testId) + '&courseId='
				+ courseId + '&sectionId=' + courseSectionId;
		window.location.href = contentId != null ? url + '&contentId='
				+ contentId : url;
	} else {
		location.href = 'addEditTest?testId=' + parseInt(testId);
	}
}

/**
 * @summary redirect on question tab from Preview tab.
 */
var addQuestionTabFromPreviewTab = function() {
	if (courseId != null && courseSectionId != null) {
		var url = 'prepareTestQuestions?testId=' + parseInt(testId)
				+ '&courseId=' + courseId + '&sectionId=' + courseSectionId;
		window.location.href = contentId != null ? url + '&contentId='
				+ contentId : url;
	} else {
		location.href = 'prepareTestQuestions?testId=' + parseInt(testId) + '';
	}
}

/**
 * @summary function is used for checking that if test is schedued then its
 *          publish time is expire or not.
 */
var checkSchedule = function() {
	var isCorrect = true;
	if (isSchedule == 1) {
		$.ajax({
			url : 'checkTestScheduleTime?testId=' + parseInt(testId),
			type : 'GET',
			async : false,
			error : function(err) {

			},
			success : function(status) {
				if (status) {
					isCorrect = false;
					$("#publishAlert p").text(
							messages['msg.assessmentscheduledateexpire']);
					$("#dId").text(messages['lbl.edit']);
					$("#dId").attr('onclick', 'testInfoTabFromPreviewTab()');
					$("#publishAlert").modal('show');
				}
			},
		});
	}
	return isCorrect;
}

/**
 * @summary This is used for when any click event is fired on element by user
 *          which has sectionDelete class.
 * 
 * @return no.
 */
var currentObject;
$(document).on('click', '.sectionDelete', function() {
	$("#publishAlert p").text(messages['msg.deletesection']);
	currentObject = this;
	$("#dId").text(messages['lbl.save']);
	$("#dId").attr('onclick', 'deleteSection()');
	$("#publishAlert").modal('show');
});

/**
 * @summary This is used for removing the delete section from the list.
 * @returns no.
 */
function deleteSection() {
	$("#publishAlert").modal('hide');
	$("#overlay").show();
	var sectiondivId = $(currentObject).attr("id").split('sectionDelete');
	var sectionId = sectiondivId[1];
	$.ajax({
		url : 'deletetestsection',
		type : 'POST',
		data : 'testId=' + parseInt(testId) + "&sectionId="
				+ parseInt(sectionId),
		error : function() {
			$("#overlay").hide();
		},
		success : function(isDeleted) {
			$("#overlay").hide();
			$("#emptySection p").text(messages['msg.deleteerror']);
			$("#emptySection").modal('show');
			if (isDeleted) {
				refreshCurrentPage();
			} else {
				$("#emptySection p").text(messages['msg.deleteerror']);
				$("#emptySection").modal('show');
			}
		}
	});
}

/**
 * @summary refresh to current page.
 */
var refreshCurrentPage = function() {
	if (courseId != null && courseSectionId != null) {
		var url = 'testreview?testId=' + parseInt(testId) + '&courseId='
				+ courseId + '&sectionId=' + courseSectionId;
		window.location.href = contentId != null ? url + '&contentId='
				+ contentId : url;
	} else {
		location.href = 'testreview?testId=' + parseInt(testId);
	}
}