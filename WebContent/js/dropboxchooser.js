var options = {

	// Required. Called when a user selects an item in the Chooser.
	success : function(files) {
		uploadDropboxFile(files[0]);
	},

	// Optional. Called when the user closes the dialog without selecting a file
	// and does not include any parameters.
	cancel : function() {

	},

	// Optional. "preview" (default) is a preview link to the document for
	// sharing,
	// "direct" is an expiring link to download the contents of the file. For
	// more
	// information about link types, see Link types below.
	linkType : "direct", // or "direct"

	// Optional. A value of false (default) limits selection to a single file,
	// while
	// true enables multiple file selection.
	multiselect : false, // or true

	// Optional. This is a list of file extensions. If specified, the user will
	// only be able to select files with these extensions. You may also specify
	// file types, such as "video" or "images" in the list. For more
	// information,
	// see File types below. By default, all extensions are allowed.
	extensions : [ '.pdf', '.png', '.ppt', '.pptx', '.jpg', '.jpeg', '.mp4',
			'.gif', '.mpeg' ],
};

function dropboxChooserInit() {
	Dropbox.choose(options);
}

/**
 * @summary This is used for sending dropbox file data so that server can
 *          download this file from dropbox.
 * @param dropboxFile
 */
function uploadDropboxFile(dropboxFile) {
	if (dropboxFile.bytes > 10000000) {
		$("#google_drive_dropbox_file_alert p").text(
				uploadcontentmessages['msg.filesizeexceed'].replace(
						'#filename', dropboxFile.name));
		$("#google_drive_dropbox_file_alert").modal('show');
	} else {
		$
				.ajax({
					url : 'googledriveanddropboxservice/downloadDropboxFile',
					type : "POST",
					data : JSON.stringify(dropboxFile),
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
															dropboxFile.name));
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