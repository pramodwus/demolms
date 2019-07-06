/**
 * @summary This is used for showing popup after clicking the publish button.
 * 
 * @return no.
 */
function publishScormCoursePopup(action) {
	/**
	 * @summary checking that user has provided all important information about course.
	 */
	if (!checkCourseBasicInfo()) {
		$("#emptySection p").text(messages['msg.coursemandatoryinfo.required']);
		$("#emptySection").modal('show');
	}
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
	 * @summary publish or share the course.
	 */
	else {
		$("#selectedSection").empty();
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
function publisScormCourse() {
	$("#coursePublished").modal('hide');
	$("#overlay").show();
	var courseId = $.trim($("#courseid").val());
	$.ajax({
		type : 'POST',
		url : 'publishedscormcourse?action=publish&courseId='+courseId,
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
 * @summary This is used for creating mapping of all users with course for
 *          sharing the course.
 * 
 * @return no.
 */
function sharedScorm() {
	var message = $("#shareCourseMessage").val().trim();
	if (message.length == 0) {
		$("#shareCourseMessage").addClass('errorBorder');
		$("#shareCourseMessageError").fadeIn();
	} else {
		$("#overlay").show();
		var courseId = $.trim($("#courseid").val());
		$.ajax({
			type : 'POST',
			url : 'publishedscormcourse?action=publish&courseId='+courseId,
			error : function() {
				$("#overlay").hide();
			},
			success : function(courseStatus) {
				if (courseStatus) {
					shareScormCourse();
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

function shareScormCourse() {
	var message = $("#shareCourseMessage").val().trim();
	var courseId = $("#courseid").val();
	$.ajax({
		type : 'POST',
		url : 'publishedscormcourse?action=share',
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





