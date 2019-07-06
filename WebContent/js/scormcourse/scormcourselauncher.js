var WINDOW_OBJECT = null;
function lanchScormCourse(courselink) {
	WINDOW_OBJECT = window
			.open(
					courselink,
					"_blank",
					"toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=300,width=700,height=500");
	//$("#course_launch_header").text("Course Launched");
	$("#course_launch_msg")
			.text(
					"We launched your course in a new window but if you do not see it, a popup blocker may be preventing it from opening. Please disable popup blockers for this site.");
	$(WINDOW_OBJECT).on('beforeunload', function() {
		$("#course_launch_header").text("Course Closed");
		$("#course_launch_msg").text("You have closed the launched course.");
		var _API = window.API_1484_11;
		_API.Commit("");
		var _is_terminate = _API.Terminate("");
		if (_is_terminate) {
			closeScormCourse();
		}

	});
}

function submit_Scorm_2004_API_Result(json) {
	var is_course_has_multiple_sco = $.trim($("#is_course_has_multiple_sco").val());
	if(is_course_has_multiple_sco == 'false'){
		$.redirect("scormcourseresult", json);
	}
}

function closeScormCourse() {
	var _API = window.API_1484_11;
	if (WINDOW_OBJECT && !WINDOW_OBJECT.closed) {
		WINDOW_OBJECT.close();
	}
	var _sessiontimeinscormformat = null;
	var _sessiontimeinms = null;
	var _sessiontimeinhourformat = null;
	var score_scaled = null;
	var track_time_by_scorm = parseMillisecondsIntoReadableTime(Math
			.abs(track_scrom_start_time.getTime()
					- track_scrom_end_time.getTime()));
	try {
		_sessiontimeinscormformat = _API.GetValue("cmi.session_time");
		_sessiontimeinms = ConvertScorm2004TimeToMS(_sessiontimeinscormformat);
		_sessiontimeinhourformat = parseMillisecondsIntoReadableTime(_sessiontimeinms);
		score_scaled = _API.GetValue("cmi.score.scaled");
		score_scaled = ($.trim(score_scaled)).length > 0 ? score_scaled * 100
				: "NA";
		_sessiontimeinhourformat = _sessiontimeinms != 0 ? _sessiontimeinhourformat
				: track_time_by_scorm;
	} catch (err) {
		console.log(err.message);
	}
	var json = {
		"success_status" : _API.GetValue("cmi.success_status"),
		"completion_status" : _API.GetValue("cmi.completion_status"),
		"total_time" : _API.GetValue("cmi.total_time"),
		"score.scaled" : score_scaled,
		"session_time" : _sessiontimeinhourformat
	}
	submit_Scorm_2004_API_Result(json);
}

function ConvertScorm2004TimeToMS(strIso8601Time) {
	var intTotalMs = 0;
	try {
		var strNumberBuilder;
		var strCurrentCharacter;
		var blnInTimeSection;
		var Seconds = 0;
		var Minutes = 0;
		var Hours = 0;
		var Days = 0;
		var Months = 0;
		var Years = 0;
		var MILLISECONDS_PER_SECOND = 1000;
		var MILLISECONDS_PER_MINUTE = MILLISECONDS_PER_SECOND * 60;
		var MILLISECONDS_PER_HOUR = MILLISECONDS_PER_MINUTE * 60;
		var MILLISECONDS_PER_DAY = MILLISECONDS_PER_HOUR * 24;
		var MILLISECONDS_PER_MONTH = MILLISECONDS_PER_DAY
				* (((365 * 4) + 1) / 48);
		var MILLISECONDS_PER_YEAR = MILLISECONDS_PER_MONTH * 12;
		strIso8601Time = new String(strIso8601Time);
		strNumberBuilder = "";
		strCurrentCharacter = "";
		blnInTimeSection = false;
		for (var i = 1; i < strIso8601Time.length; i++) {
			strCurrentCharacter = strIso8601Time.charAt(i);
			if (IsIso8601SectionDelimiter(strCurrentCharacter)) {
				switch (strCurrentCharacter.toUpperCase()) {
				case "Y":
					Years = parseInt(strNumberBuilder, 10);
					break;
				case "M":
					if (blnInTimeSection) {
						Minutes = parseInt(strNumberBuilder, 10);
					} else {
						Months = parseInt(strNumberBuilder, 10);
					}
					break;
				case "D":
					Days = parseInt(strNumberBuilder, 10);
					break;
				case "H":
					Hours = parseInt(strNumberBuilder, 10);
					break;
				case "S":
					Seconds = parseFloat(strNumberBuilder);
					break;
				case "T":
					blnInTimeSection = true;
					break;
				}
				strNumberBuilder = "";
			} else {
				strNumberBuilder += "" + strCurrentCharacter;
			}
		}
		intTotalMs = (Years * MILLISECONDS_PER_YEAR)
				+ (Months * MILLISECONDS_PER_MONTH)
				+ (Days * MILLISECONDS_PER_DAY)
				+ (Hours * MILLISECONDS_PER_HOUR)
				+ (Minutes * MILLISECONDS_PER_MINUTE)
				+ (Seconds * MILLISECONDS_PER_SECOND);
		intTotalMs = Math.round(intTotalMs);
	} catch (message) {
		console.log(err.message);
	}
	return intTotalMs;
}

function IsIso8601SectionDelimiter(str) {
	if (str.search(/[PYMDTHS]/) >= 0) {
		return true;
	} else {
		return false;
	}
}

function parseMillisecondsIntoReadableTime(milliseconds) {
	//Get hours from milliseconds
	var hours = milliseconds / (1000 * 60 * 60);
	var absoluteHours = Math.floor(hours);
	var h = absoluteHours > 9 ? absoluteHours : '0' + absoluteHours;

	//Get remainder from hours and convert to minutes
	var minutes = (hours - absoluteHours) * 60;
	var absoluteMinutes = Math.floor(minutes);
	var m = absoluteMinutes > 9 ? absoluteMinutes : '0' + absoluteMinutes;

	//Get remainder from minutes and convert to seconds
	var seconds = (minutes - absoluteMinutes) * 60;
	var absoluteSeconds = Math.floor(seconds);
	var s = absoluteSeconds > 9 ? absoluteSeconds : '0' + absoluteSeconds;

	return h + ':' + m + ':' + s;
}

function _ScormProcessSetValue(element, value) {
	var _API = window.API_1484_11;
	var result = _API.setValueUsingLMSBeforeInit(element, value);
	return result;
}

function _ScormProcessGetValue(element, checkError) {
	var _API = window.API_1484_11;
	var result = _API.getValueUsingLMSBeforeInit(element);
	return result;
}