// The Browser API key obtained from the Google Developers Console.
var developerKey = 'AIzaSyAPdnN2wgTDIx7C5VX39taOH_0WHW-IBsc';

var pickerApiLoaded = false;
var oauthToken;

// Use the API Loader script to load google.picker and gapi.auth.
function googlePickerApiLoad(token) {
	oauthToken = token;
	gapi.load('picker', {
		'callback' : onPickerApiLoad
	});
}

function onPickerApiLoad() {
	pickerApiLoaded = true;
	createPicker();
}

// Create and render a Picker object for picking user Photos.
// enableFeature(google.picker.Feature.MINE_ONLY). is used for showing mine
// documents.
// enableFeature(google.picker.Feature.MULTISELECT_ENABLED). multi select is
// enable
// setMaxItems(3). is for selecting max items.
function createPicker() {
	if (pickerApiLoaded && oauthToken) {
		var view = new google.picker.DocsView(google.picker.ViewId.DOCS);
		var imageAndVideoView = new google.picker.DocsView(
				google.picker.ViewId.DOCS_IMAGES_AND_VIDEOS);
		var documentView = new google.picker.DocsView(
				google.picker.ViewId.DOCUMENTS);
		var pdfView = new google.picker.DocsView(google.picker.ViewId.PDFS);
		var presentationView = new google.picker.DocsView(
				google.picker.ViewId.PRESENTATIONS);
		var spreadsheetView = new google.picker.DocsView(
				google.picker.ViewId.SPREADSHEETS);
		var picker = new google.picker.PickerBuilder().enableFeature(
				google.picker.Feature.MINE_ONLY).addView(imageAndVideoView)
				.setLocale(currentLocale).addView(pdfView).addView(
						presentationView).setOAuthToken(oauthToken)
				.setDeveloperKey(developerKey).setCallback(pickerCallback)
				.build();

		/*
		 * var picker1 = new google.picker.PickerBuilder().enableFeature(
		 * google.picker.Feature.MINE_ONLY).enableFeature(
		 * google.picker.Feature.MULTISELECT_ENABLED).addView(
		 * imageAndVideoView).addView(pdfView).addView(presentationView)
		 * .setOAuthToken(oauthToken).setDeveloperKey(developerKey)
		 * .setCallback(pickerCallback).setMaxItems(3).build();
		 */
		picker.setVisible(true);
	}
}

/**
 * @summary This is call back function from google picker.
 * @param data
 */
function pickerCallback(data) {
	var url = 'nothing';
	var fileId;
	if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
		var doc = data[google.picker.Response.DOCUMENTS][0];
		url = doc[google.picker.Document.URL];
		uploadGoogleDriveFile(data.docs[0]);
	}
	/*
	 * var message = 'You picked: ' + url;
	 * document.getElementById('result').innerHTML = message;
	 */

}

/**
 * @summary This is used for sending file id and token so that server can
 *          download this file from google drive.
 * @param googleFileData
 */
function uploadGoogleDriveFile(googleFileData) {
	if (googleFileData.sizeBytes > 10000000) {
		$("#google_drive_dropbox_file_alert p").text(
				uploadcontentmessages['msg.filesizeexceed'].replace(
						'#filename', googleFileData.name));
		$("#google_drive_dropbox_file_alert").modal('show');
	} else {
		$
				.ajax({
					url : 'googledriveanddropboxservice/downloadGoogleDriveFile?token='
							+ encodeURIComponent(oauthToken),
					type : "POST",
					data : JSON.stringify(googleFileData),
					contentType : "application/json",
					beforeSend : function(xhr) {
						$("#googlePickerOverlay").show();
					},
					error : function() {
						$("#googlePickerOverlay").hide();
						$("#google_drive_dropbox_file_alert p").text(
								messages['msg.somethingwentwrong']);
						$("#google_drive_dropbox_file_alert").modal('show');
					},
					success : function(contentId) {
						$("#googlePickerOverlay").hide();
						$('#contentlisttable').DataTable().draw();
						if (contentId > 0) {
							$("#google_drive_dropbox_success_file_alert p")
									.text(
											messages['msg.filehasbeenuploaded']
													.replace('#filename',
															googleFileData.name));
							$("#google_drive_dropbox_success_file_alert")
									.modal('show');
						} else {
							$("#google_drive_dropbox_file_alert p").text(
									messages['msg.somethingwentwrong']);
							$("#google_drive_dropbox_file_alert").modal('show');
						}
					}
				});
	}
}