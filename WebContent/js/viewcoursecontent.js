/**
 * @summary This file would be used for performing common operation for view course content by trainee.
 * @author ankur
 * @date 9 Jan 2017
 */

/**
 * @summary instance of timer.
 */
var sectionTrackingTimer;

/**
 * @summary function for tracking spent time on section by user.
 * @param duration
 *            which indicates the spent time on current section.
 * @param position
 *            which indicates the current section position is section list.
 * @param spentTimeIsLessThanMinRequiredSpentTime
 *            which indicates that spent time of user on current section is less
 *            than minimum required time.
 * @returns no.
 */
function startSectionTrackingTimer(duration, position,
		spentTimeIsLessThanMinRequiredSpentTime) {
	var start = Date.now(), diff, minutes, seconds;
	duration = duration + 1;
	function timer() {
		// get the number of seconds that have elapsed since
		// startTimer() was called
		diff = duration + (((Date.now() - start) / 1000) | 0);
		/**
		 * @summary increase the total spent time on current section on every
		 *          section.
		 */
		sectionJson[position].totalSpentTime = diff;

		/**
		 * @summary calculate the spent minutes from spent time.
		 */
		minutes = (diff / 60) | 0;

		/**
		 * @summary calculate the spent seconds from spent time.
		 */
		seconds = (diff % 60) | 0;

		/**
		 * @summary if seconds or minutes are not 2 digit number( means are less
		 *          than 10) than add 0 as string in minute or seconds.
		 * @example if second is 9 then add 0 before 9 so that second could show
		 *          user like this "09".
		 */
		minutes = minutes < 10 ? "0" + minutes : minutes;
		seconds = seconds < 10 ? "0" + seconds : seconds;

		/**
		 * @summary set spent time for showing to user like a timer.
		 */
		$("#total_spent_time_on_section").text(minutes + ":" + seconds);

		/**
		 * @summary if spent time of user on current section is less than
		 *          minimum required time.
		 */
		if (spentTimeIsLessThanMinRequiredSpentTime) {

			/**
			 * @summary if total spent time is equal or greater than minimum
			 *          required time.
			 */
			if (diff >= sectionJson[position].minTimeSpent) {

				/**
				 * @summary if quiz is mandatory.
				 */
				if (sectionJson[position].isQuizMandatory == 1) {

					/**
					 * @summary checking that if user has been passed the
					 *          passing criteria for quiz.
					 */
					if (sectionvalidationforquizmanadatory(sectionJson[position].sectionId)) {

						/**
						 * @summary make true to variable which indicates that
						 *          user has passed the passing criteria for
						 *          current section.
						 */
						sectionJson[position].isPassedTheCriteria = true;

						/**
						 * @summary make changes in section's ui setting (like
						 *          enable or disable the button or show/hide
						 *          model.)
						 */
						changeUISettings();
					}
				}

				/**
				 * @summary if quiz is not mandatory.
				 */
				else {

					/**
					 * @summary make changes in section's ui setting (like
					 *          enable or disable the button or show/hide
					 *          model.)
					 */
					changeUISettings();

				}
			}
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
	sectionTrackingTimer = setInterval(timer, 1000);

	/**
	 * @summary function for changing the ui changes according to section's
	 *          setting.
	 */
	function changeUISettings() {
		/**
		 * @summary make false to variable which indicates that spent time of
		 *          user on current section is greater than minimum required
		 *          time.
		 */
		spentTimeIsLessThanMinRequiredSpentTime = false;

		/**
		 * @summary if current section is last section of course.
		 */
		if (position == sectionJson.length - 1) {

			/**
			 * @summary show message to user that you have completed course as
			 *          pop up.
			 */
			$("#contentErrorAlert p").text(messages['msg.coursecompleted']);
			$("#contentErrorAlert").modal('show');
		}
		/**
		 * @summary if current section is not last section of course.
		 */
		else {

			/**
			 * @summary enable the button for jumping the next section.
			 */
			$("#next-section-button").removeClass("list-li-disabled");

			/**
			 * @summary show message to user that now you can jump on next
			 *          section as pop up.
			 */
			$("#contentErrorAlert p").text(messages['msg.jumponnextsection']);
			$("#contentErrorAlert").modal('show');
		}
	}
}

/**
 * @summary This function is used to stop the running timer.
 */
function stopSectionTrackingTimer() {
	if (sectionTrackingTimer != null) {
		clearInterval(sectionTrackingTimer);
	}
}

/**
 * @summary This is used fetching data from server for find out that user can
 *          jump on next section or not. if quiz is mandatory
 */
function sectionvalidationforquizmanadatory(sectionId) {
	var isValidate = false;
	$.ajax({
		url : 'api/sectionvalidationforquizmanadatory/'
				+ parseInt($.trim($("#userId").val())) + '/'
				+ parseInt($.trim(viewCourseId)) + '/' + sectionId,
		beforeSend : function(xhr) { // @summary appending data in header
			// before sending request.
			xhr.setRequestHeader('authorization', 'Browser');
			xhr.setRequestHeader('timestamp', "Browser");
		},
		type : 'GET',
		async : false,
		error : function() {
		},
		success : function(response) {
			isValidate = response.isPassedTheCriteria;
		}
	});
	return isValidate;
}