<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<script>
	var currentpagemenuid = 'course';
</script>
<%@ include file="include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
fieldset.scheduler-border {
	border: 1px groove #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #000;
	box-shadow: 0px 0px 0px 0px #000;
}

legend.scheduler-border {
	font-size: 1.2em !important;
	font-weight: bold !important;
	text-align: left !important;
	width: auto;
	padding: 0 10px;
	border-bottom: none;
}
</style>
</head>

<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>

		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper"
				style="overflow-x: hidden; overflow-y: auto;" id="users">
				<section class="content-header">
					<h1>
						<%-- <spring:message code="lbl.courses" text="Courses" /> --%>
					</h1>
				</section>
				<!-- Main content -->
				<section class="content" style="text-align: center">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
						<fieldset class="scheduler-border">
							<legend class="scheduler-border" id="course_launch_header">Upload
								Scorm Course</legend>
							<div class="col-sm-12" id="progress_div" style="display: none">
								<div class="progress-group">
									<span id="progress_msg">Uploading</span> <span
										class="progress-number"><span id="progress_percentage">0</span>/100%</span>
									<div class="progress">
										<div id="progress_bar" class="progress-bar progress-bar-aqua"
											style="width: 0%"></div>
									</div>
								</div>
							</div>
							<div class="col-sm-12 form-group">
								<input type="file" id="zip_file" name="zipFile"
									onchange="changeFile()">
								<p class="requireFld" id="zip_file_error"
									style="text-align: left; font-size: 15px"></p>
							</div>

							<div class="col-sm-12 form-group">
								<button id='_submit' type="button"
									class="btn btn-flat btn-success button-width-large"
									onclick="uploadFile()">Upload File</button>
							</div>
						</fieldset>
					</div>
					<div class="col-sm-2"></div>
				</section>


			</div>
			<!-- content-wrapper -->
		</div>
		<!-- ./wrapper -->
	</div>
	<script type="text/javascript">
		var messages = new Array();
		messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true' />";
		messages['msg.fileisrequired'] = "<spring:message code='msg.fileisrequired' text='File is required.' javaScriptEscape='true' />";
		messages['msg.invalidfileselected'] = "<spring:message code='msg.invalidfileselected' arguments='#filetype' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
	</script>
	<script>
		var changeFile = function() {
			$("#zip_file_error").fadeOut();
		}
		/**
		 * @summary function for file validation.
		 * @returns {Boolean}
		 */
		var fileValidation = function() {
			var validExts = new Array("zip");
			var zip_file = document.getElementById("zip_file");
			if (($("#zip_file").val().trim()).length == 0) {
				$("#zip_file_error").text(messages['msg.fileisrequired']);
				$("#zip_file_error").fadeIn();
				return false;
			}
			if (!checkfile(zip_file)) {
				$("#zip_file_error").text(
						messages['msg.invalidfileselected'].replace(
								"#filetype", validExts.toString()));
				$("#zip_file_error").fadeIn();
				return false;
			}

			return true;
		}

		/**
		 * @summary function for checking that excel file is valid or not.
		 * @param sender
		 * @returns {Boolean}
		 */
		function checkfile(sender) {
			var validExts = new Array(".zip");
			var fileExt = sender.value;
			fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
			if (validExts.indexOf(fileExt) < 0) {
				return false;
			} else
				return true;
		}

		function uploadFile() {
			fileValidation();
		}

		function uploadFile() {
			if (fileValidation()) {
				$("#progress_div").show();
				var fileObj = document.getElementById('zip_file');
				var file = fileObj.files[0];
				var formData = new FormData();
				formData.append("zipFile", file);
				var oReq = new XMLHttpRequest();
				oReq.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						var courseId = this.responseText;
						if (courseId > 0) {
							location.href = 'addeditscormcourse?courseId='
									+ courseId;
						}
						else
						{
						fileUploadFailed();
						}
					}
				};
				oReq.upload.addEventListener("progress", updateProgress, false);
				oReq.addEventListener("load", transferComplete);
				oReq.addEventListener("error", transferFailed);
				oReq.addEventListener("abort", transferCanceled);
				oReq.open("POST", "uploadscormzipfile");
				$("#_submit").text("Uploading...");
				$("#_submit").prop('disabled',true);
				oReq.send(formData);
			}
		}
		// ...

		// progress on transfers from the server to the client (downloads)
		function updateProgress(oEvent) {
			if (oEvent.lengthComputable) {
				var percentComplete = oEvent.loaded / oEvent.total;
				percentComplete = parseInt(percentComplete * 100)
				if (percentComplete >= 100) {
					$("#progress_bar").removeClass("progress-bar-striped");
				}
				$("#progress_bar").css("width", percentComplete + "%");
				$("#progress_percentage").text(percentComplete);
			} else {
				console
						.log("Unable to compute progress information since the total size is unknown");
			}
		}

		function transferComplete(evt) {
			    $("#zip_file").val('');
				$("#progress_msg").text("Completed");
		}

		function transferFailed(evt) {
			$("#zip_file").val('');
			fileUploadFailed();
			console.log("An error occurred while transferring the file.");
		}

		function transferCanceled(evt) {
			$("#zip_file").val('');
			console.log("The transfer has been canceled by the user.");
		}
		
		
		function fileUploadFailed(){
			$("#_submit").text("Upload File");
			$("#_submit").prop('disabled',false);
			$("#zip_file").val('');
			$("#progress_div").hide();
			$("#zip_file_error").text(messages['msg.somethingwentwrong']);
			$("#zip_file_error").fadeIn();
		}
	</script>
</body>
</html>