var openUploadModal = function(action){
	//alert(action);	
	$("#action").val('');
	$("#action").val(action);	
	$("#uploadUserPopup").modal('show');
}

/**
 * @summary function for calling on upload file button.
 * @returns no.
 */
$("#uploadUserFile").click(function() {
	clearErrorMsg();
	if (fileValidation()) {
		submitFile();
	}
});

/**
 * @summary function for file validation.
 * @returns {Boolean}
 */
var fileValidation = function() {
	var validExts = new Array(".xls", ".xlsx");
	var quesFile = document.getElementById("userFile");
	if (($("#userFile").val().trim()).length == 0) {
		$("#userFile").after("<span class='text-red'>File is mandatory.</span>"); 
		$("#userFile").css({"border-color" : "red","border-style":"solid","border-width":"1px"}); 
		return false;
	}
	if (!checkfile(quesFile)) {
		$("#userFile").after("<span class='text-red'>Select a valid file type of xls or xlsx.</span>"); 
		$("#userFile").css({"border-color" : "red","border-style":"solid","border-width":"1px"}); 
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
	var validExts = new Array(".xls", ".xlsx");
	var fileExt = sender.value;
	fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
	if (validExts.indexOf(fileExt) < 0) {
		return false;
	} else
		return true;
}



/**
 * @summary function for uploading excel sheet through ajax.
 * @returns no.
 */
var submitFile = function() {
	var formData = new FormData();
	formData.append("userFile", $("#userFile").prop("files")[0]);
	formData.append("fileName", $("#userFile").val());
	formData.append("action", $("#action").val());
	
	$("#overlay").show();
	$.ajax({
				type : 'POST',
				url : 'uploadUserFile',
				processData : false,
				contentType : false,
				data : formData,
				success : function(result) {
					$("#overlay").hide();
					console.log(result);
					if(result.status==1){
						location.href="userlist"	
					}else{
						var arr = result.errorlist;						
						var str='';
						for (var i = 0; i < arr.length; i++) {
							str+='<span class="text-red">'+arr[i]+'</span><br>';
						}
						$("#errorMsg").append(str);
					}
					
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$("#overlay").hide();
					var err = JSON.parse(xhr.responseText);
					console.log(err);					
				}
			});
}

var clearErrorMsg = function(){
	$("#errorMsg").html('');
	$('#userFile').next('span').remove();
	$("#userFile").css({"border-color" : ""}); 
}

var onModalClose = function(){	 
	$('#userFile').val('');
	clearErrorMsg();
}