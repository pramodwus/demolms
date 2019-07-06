var SESSIONID;
/**
 * @summary This is used for saving the details of section.

 * 
 * @return no.
 */
function saveSection() {
	if (sectionNameValidation()) {
		var data = $("#sectionForm").serialize();
		$.ajax({
			type : 'POST',
			url : 'savecoursesection',
			data : data,
			success : function(data) {
				location.href = 'addEditCourseMaterial?courseId='+parseInt($.trim($("#courseid").val()));
				/*$("#sectionName").val("");
				var sectionData = data.split("####");
				var isupdate = sectionData[0].trim()
				var sectionId = sectionData[1].trim();
				var sectionName = sectionData[2].trim();
				var courseId = $("#courseid").val();
				if (isupdate == 1) {
					$("#sectionNameText" + sectionId).text(sectionName);
				} else {
					addSection(sectionId, sectionName, false);
				}*/
			}
		});
		$("#sectionPop").modal('hide');
	}
}



function saveSession1(courseId,sectionId) {

  
	// var data = $("#sessionForm").serialize();
	var sessionName=$("#sessionName"+sectionId).val();	
	var data= { "courseId":courseId, 
				"sectionId":sectionId,
				"sessionName":sessionName,
				"isChapterTest":0
			 }
	
	 $.ajax({
			type : 'POST',
		     url : 'savecoursesession',
			data : data,
			success : function(data) {
		 location.href = 'addEditCourseMaterial?courseId='+parseInt($.trim($("#courseid").val()));
				/*$("#sectionName").val("");
				var sectionData = data.split("####");
				var isupdate = sectionData[0].trim()
				var sectionId = sectionData[1].trim();
				var sectionName = sectionData[2].trim();
				var courseId = $("#courseid").val();
				if (isupdate == 1) {
					$("#sectionNameText" + sectionId).text(sectionName);
				} else {
					addSection(sectionId, sectionName, false);
				}*/
			}
		});
		$("#sessionPop").modal('hide');
		$(document).ready(function(){
	        $("#txt_name").keyup(function(){
	          
	        });
	    })
//		$("#sessionForm").val("");
	
}


function saveSessionForTestChapter(courseId,sectionId) {

		//var data = $("#sessionForm1"+sectionId).serialize();
	var sessionName=$("#sessionName"+sectionId).val();	
	var data= { "courseId":courseId, 
			"sectionId":sectionId,
			"sessionName":sessionName,
			"isChapterTest":1
		 }

		$.ajax({
			type : 'POST',
			url : 'savecoursesession',
			data : data,
			success : function(data) {
			location.href = 'addEditCourseMaterial?courseId='+parseInt($.trim($("#courseid").val()));
				/*$("#sectionName").val("");
				var sectionData = data.split("####");
				var isupdate = sectionData[0].trim()
				var sectionId = sectionData[1].trim();
				var sectionName = sectionData[2].trim();
				var courseId = $("#courseid").val();
				if (isupdate == 1) {
					$("#sectionNameText" + sectionId).text(sectionName);
				} else {
					addSection(sectionId, sectionName, false);
				}*/
			}
		});
		$("#sessionPop1").modal('hide');
	
}

function updateSessionName(sessionId) {
	SESSIONID=sessionId;

	$("#sectionName").css({"border-color" : ""});	
	$("#sectionName").next('span').remove();
	$("#sectionNameError1").fadeOut();
	$("#sectionNameError2").fadeOut();
	$("#sectionHeading").text(messages['lbl.editsectionname']);
	$("#saveSectionButton").text(messages['lbl.update']);
	$("#sectionFormsectionId").val("");
	$("#sectionName").val("");
	$("#sessionPop").modal('show');

}
function saveSessionUpdate()
{
 var sessionName=$("#sectionname").val();	

var data= { "sessonId":SESSIONID, 
		"sessionName":sessionName
	 }
$.ajax({
	type : 'POST',
	url : 'updateSession',
	data:data,
	success : function(result) {
		location.reload();
		if (result=="true") {
			
			// location.reload();
			/*$(currentObject).closest("div.totalSections").remove();
			$('#suggestioncontentlist').DataTable().draw();
			var totalSections = $(".totalSections").length;
			if(totalSections == 0){
				$("#course-content-submit-button").text(messages['lbl.savelater']);
				$("#course-content-submit-button").attr("onclick","location.href='courselist'");
			}*/
		} else {
			alert("Sorry you cannot edit session name");
		}
	//location.href = 'addEditCourseMaterial?courseId='+parseInt($.trim($("#courseid").val()));
		/*$("#sectionName").val("");
		var sectionData = data.split("####");
		var isupdate = sectionData[0].trim()
		var sectionId = sectionData[1].trim();
		var sectionName = sectionData[2].trim();
		var courseId = $("#courseid").val();
		if (isupdate == 1) {
			$("#sectionNameText" + sectionId).text(sectionName);
		} else {
			addSection(sectionId, sectionName, false);
		}*/
	}
});
$("#sessionPop").modal('hide');

}

/**
 * @summary This is used for showing popup form for fill up section details.
 * 
 * @return no.
 */
function sessionCreatePopup() {
	$("#sessionName").css({"border-color" : ""});
	$("#sessionName").next('span').remove();
	$("#sessionNameError1").fadeOut();
	$("#sessionNameError2").fadeOut();
	if (sectionValidation()) {
		$("#sessionHeading").text(messages['lbl.createnewsection']);
		$("#saveSessionButton").text(messages['lbl.save']);
		$("#sessionFormsectionId").val("");
		$("#sessionName").val("");
		$("#sessionPop").modal('show');
	} else {
		$("#emptySession p").text(messages['msg.anysectioncontentcannotbeempty']);
		$("#emptySession").modal('show');
	}
}



function sectionCreatePopup() {
	$("#sectionName").css({"border-color" : ""});
	$("#sectionName").next('span').remove();
	$("#sectionNameError1").fadeOut();
	$("#sectionNameError2").fadeOut();
	if (sectionValidation()) {
		$("#sectionHeading").text(messages['lbl.createnewsection']);
		$("#saveSectionButton").text(messages['lbl.save']);
		$("#sectionFormsectionId").val("");
		$("#sectionName").val("");
		$("#sectionPop").modal('show');
	} else {
		$("#emptySection p").text(messages['msg.anysectioncontentcannotbeempty']);
		$("#emptySection").modal('show');
	}
}

/**
 * @summary This is used for changing section name.
 * 
 * @return no.
 */
function updateSectionName(sectionId) {
	$("#sectionName").css({"border-color" : ""});	
	$("#sectionName").next('span').remove();
	$("#sectionNameError1").fadeOut();
	$("#sectionNameError2").fadeOut();
	$("#sectionHeading").text(messages['lbl.editsectionname']);
	$("#saveSectionButton").text(messages['lbl.update']);
	$("#sectionFormsectionId").val("");
	$("#sectionName").val("");
	$("#sectionFormsectionId").val(sectionId);
	$("#sectionName").val($("#sectionNameText" + sectionId).text());
	$("#sectionPop").modal('show');
}

/**
 * @summary This is used for showing popup after clicking the publish button.
 * 
 * @return no.
 */
/*function publishPopup() {
	if (!coursehasSection()) {
		$("#emptySection p").text(
				"Minimum One section is required for publish the course.");
		$("#emptySection").modal('show');

	} else if (!selectedSectionValidation()) {
		$("#emptySection p").text("Selected Section Content can not be empty.");
		$("#emptySection").modal('show');
	} else if (checkedSectionCount() == 0) {
		$("#emptySection p").text(
				"You have no choosed any section for publishing.");
		$("#emptySection").modal('show');
	}

	else {
		$("#selectedSection").empty();
		var checkboxes = document.getElementsByName("sectionSelect");
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				var str = '<div>' + '<span>&emsp;&emsp;&emsp;&emsp;'
						+ $("#sectionNameText" + checkboxes[i].value).text()
						+ '</span>'
						+ '<input type="hidden" name="selectedSection" value="'
						+ checkboxes[i].value + '">' + '</div>';
				$("#selectedSection").append(str);
			}
		}
		$("#coursePublished").modal('show');
	}
}*/

/**
 * @summary This is used for checking that only selected sections have contents
 *          when user click on publish.
 * 
 * @return {boolean type}valid Return true if all selected section's has minimum
 *         one content otherwise false;
 */
/*function selectedSectionValidation() {
	var loopcount = 0;
	var valid = true;
	var totalSections = $(".totalSections").length;
	var checkboxes = document.getElementsByName("sectionSelect");
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			var totalSectionContentDiv = $('#sectionContent'
					+ checkboxes[i].value + ' > div').length;
			if (totalSectionContentDiv == 0 && loopcount == 0) {
				valid = false;
				loopcount++;
			}
		}
	}
	return valid;
}*/
/**
 * @summary This is used for checking that user has checked minimum one section
 *          for publishing with course.
 * 
 * @return {boolean type}totalcheckedSection returns true if user has choosed to
 *         minimum one section for publishing otherwise false.
 */
/*function checkedSectionCount() {
	var totalcheckedSection = 0;
	var checkboxes = document.getElementsByName("sectionSelect");
	for (var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i].checked) {
			totalcheckedSection++;
		}
	}
	return totalcheckedSection;
}*/

/**
 * @summary This is used for checking that all sections have contents when user
 *          click on publish.
 * 
 * @return {boolean type}valid Return true if all section's has minimum one
 *         content otherwise false;
 */
function sectionValidation() {
	var loopcount = 0;
	var valid = true;
	var totalSections = $(".totalSections").length;
	if (totalSections > 0) {
		$('.totalSections').each(
				function() {
					var totalSectionContentDiv = $(
							'.totalSectionContent > div', $(this)).length;
					if (totalSectionContentDiv == 0 && loopcount == 0) {
						valid = false;
						loopcount++;
					}

				}

		);
	}
	return valid;
}

/**
 * @summary This is used for checking that course has minimum one section or
 *          not.
 * 
 * @return {boolean type}valid Return true if course has minimum one section
 *         otherwise false.
 */
/*function coursehasSection() {
	var valid = true;
	var totalSections = $(".totalSections").length;
	if (totalSections == 0) {
		valid = false;
	}
	return valid;
}*/

/**
 * @summary This is used for saving the content(media type) of a particular
 *          section.
 * @param This
 *            is first parameter which has information of about choosing file's
 *            path from file manager.
 * @param This
 *            is second parameter Which has information for which section's file
 *            is choosed.
 * @return no.
 */
function saveSectionData(path, linkId) {
	var id = linkId.split("##");
	var courseId = $("#courseid").val();
	var sectionId = id[0];
	var contentId = id[1];
	$.ajax({
		type : 'POST',
		url : 'savecoursesectionmaterial',
		data : "contentPath=" + path + "&courseId=" + courseId + "&sectionId="
				+ sectionId + "&contentId=" + contentId,
		success : function(data) {
			var str = data.split("####");
			var contentTypeId = str[0].trim();
			if (contentTypeId > 0) {
				addSectionContent(data);
				$("#overlay").hide();
			} else {
				$("#overlay").hide();
				$("#fileType").modal('show');
			}
		}

	});
}

/**
 * @summary This is used for adding the created new section div into your page.
 * @param sectionId
 *            This is first parameter which has id of new created section.
 * @param sectionName
 *            This is second parameter which has name of new created section.
 * @return no.
 */
function addSection(sectionId, sectionName) {
	var totalSections = $(".totalSections").length;
	var str='<div class="section totalSections row no-left-right-margin">'
		    
		    + '<div class="input-group">'
		    + '<div class="section_name_div" id="sectionNameText'+sectionId+'">'+sectionName+'</div>'
		    + '<div class="input-group-addon">'
		    
		    + '<div class="pull-right">'
		    + '<a style="color:#dd4b39" class="cursor-pointer" onclick="updateSectionName('+sectionId+');" title="'+messages['lbl.editname']+'"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;'
			+ '<a style="color:#dd4b39" class="cursor-pointer sectionDelete" id="sectionDelete'+sectionId+'" title="'+messages['lbl.delete']+'"><i class="fa fa-trash-o"></i></a>&nbsp;&nbsp;	 '
			+ '<a style="color:#dd4b39" class="cursor-pointer" onclick="sectionSettingInfoPopup('+sectionId+')" title="'+messages['lbl.setting']+'"><i class="fa fa-gears"></i></a>'
		    + '</div>'
		    
		    + '</div>'
		    + '</div>'
		    
		    + '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ messages['lbl.note']+' : '+messages['msg.sectionmusthavecontentforpublish']+'</p>'
		    
		    + '<div>'
		    + '<div class="well1 content_dropzone" id="divfordragcontent_'+sectionId+'" style="display:none" ondrop="dropContent(event)" ondragover="allowDropContent(event)"><h2 class="text-align content_dropzone_h" id="divfordragcontenth_'+sectionId+'">'+messages['lbl.dragherefromlist']+'</h2></div>'
		    
		    + '<div id="sectionContent'+sectionId+'" class="totalSectionContent sortable"></div>'
		    + '<h3 style="text-align: center;" class="hide">'+messages['lbl.autouploadlogo']+'</h3>'
		    + '<h3 style="text-align: center;" class="hide">'+messages['lbl.dropfilesheretoautoupload']+'</h3>'
		    + '</div>'
		    
		    + '<div class="row">'
		    + '<div class="col-sm-12">'
		    
		    + '<div class="col-sm-4">'
		    + '<h3 style="text-align: center;"><a style="cursor: pointer" id="'+sectionId+'##0" onclick="contentlibrarypage('+sectionId+')"><img src="resources/adminlte/dist/img/courseinfo.png"></a></h3>'
		    + '<h4 style="text-align: center;">'+messages['lbl.addcontentfromlibrary']+'</h4>'
		    + '</div>'
		    
		    + '<div class="col-sm-4" style="border-left: 1px solid #eee; border-right: 1px solid #eee;">'
		    + '<h3 style="text-align: center;"><a style="cursor: pointer" id="bulkbtn'+sectionId+'" onclick="openbulkmodal('+sectionId+')"><img src="resources/adminlte/dist/img/courseinfo.png"></a></h3>'
		    + '<h4 style="text-align: center;">'+messages['lbl.addfilefromexternal']+'</h4>'
		    + '</div>'
		    
		    + '<div class="col-sm-4">'
		    + '<h3 style="text-align:center;"><a style="cursor: pointer" id="addTest'+sectionId+'" onclick="testPopup('+sectionId+');"><img src="resources/adminlte/dist/img/courseinfo.png"></a></h3>'
		    + '<h4 style="text-align: center;">'+messages['lbl.addassessment']+'</h4>'
		    + '</div>'
		    
		    + '</div>'
		    + '</div>'
		    
		     
		    + '</div>';
	$("#sectiondiv").append(str);
	$(".sortable").sortable({
		axis : "y",
		cursor : "move", // Defines the cursor that is being shown while
							// sorting.
		distance : 5, // Tolerance, in pixels, for when sorting should start.
						// If specified, sorting will not start until after
						// mouse is dragged beyond distance. Can be used to
						// allow for clicks on elements within a handle.
		// containment: "parent", // Defines a bounding box that the sortable
		// items are constrained to while dragging.
		opacity : 0.5, // Defines the opacity of the helper while sorting. From
						// 0.01 to 1.
		revert : true, // Whether the sortable items should revert to their new
						// positions using a smooth animation.
		deactivate : function(event, ui) {
			$(".contentItem").css("zIndex", ''); /*
													 * remove z-index from
													 * class.
													 */
		},
		update : function(event, ui) {
			updateContentOrder($(this).sortable("toArray"));
		}
	});
	$('#suggestioncontentlist').DataTable().draw();
	if(totalSections == 0){
		var _courseId = parseInt($.trim($("#courseid").val()));
		$("#course-content-submit-button").text(messages['lbl.next']);
		$("#course-content-submit-button").attr("onclick","validateSectionSetting()");
	}
}

/**
 * @summary This is used for added new created content div for a section inside
 *          that section div.
 * @param contentData
 *            All required data like id, name e.t.c is extracted from
 *            contentData .
 * @return no.
 */
function addSectionContent(contentData) {
	var content = contentData.split("####");
	var contentTypeId = content[0].trim();
	var isSaveOrUpdate = content[1].trim();
	var contentName = content[2].trim();
	var contentType = content[3].trim();
	var sectionId = content[4].trim();
	var contentPath = content[5].trim();
	var contentId = content[6].trim();
	var isTest = content[7].trim();
	var contentIcon = content[8].trim();
	if (isSaveOrUpdate == 0) {
		var ids = sectionId+ '##'+contentId;
		
		var str ='<div class="well1 well-sm contentItem" id="sortable_'+contentId+'">'
			    +'<img src="resources/images/'+contentIcon+'" class="contentIcon" id="contentIcon'+contentId+'">'
			    +'<label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a id="contentName'+contentId+'" class="cursor-pointer" onclick="showContent(\''+contentPath+','+contentType+'\')">'+contentName+'</a></label>'
			    +'<div class="pull-right">'
			    +'<div class="dropdown">'
			    +'<a id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="content-dropdown icon-dropdown"> <img src="resources/adminlte/dist/img/ellipsis-v.png" class="shape"/></a>'
			    +'<ul class="dropdown-menu dropdown-menu-right" aria-labelledby=" dlabel">'
			    +'<li><a id="' + sectionId + '##' + contentId + '" onclick="renametitle('+ids+')" class="cursor-pointer">'+messages['lbl.edit']+'</a></li>'
			    +'<li class="divider"></li>'
			    +'<li><a id="contentDelete'+contentId+'" class="btndelete cursor-pointer">'+messages['lbl.delete']+'</a></li>'
			    +'</ul>'
			    +'</div>'
			    +'</div>'
			    +'</div>';
		
/*		var str1 = '<div class="col-xs-12 contentItem" id="sortable_'
				+ contentId
				+ '">'
				+ '<div class="col-xs-12" style="min-height:20px"></div>'
				+ '<div class="col-xs-12 page-background-color content-line-height" style="min-height:40px">'
				+ '<div class="pull-left content-padding-image">'
				+ '<img src="resources/images/'
				+ contentIcon
				+ '" onerror="this.src=\'resources/images/iconerror.png\'" class="contentIcon" id="contentIcon'+ contentId +'">'
				+ '</div>'
				+ '<div class="col-xs-3">'
				+ '<label class="pull-left">'
				+ '<a  id="contentName'
				+ contentId
				+ '" class="cursor-pointer" onclick="showContent(\''+ contentPath+ ','+contentType+'\')">'
				+ contentName
				+ '</a>'
				+ '</label>'
				+ '</div>'
				+ '<div class="col-md-7  col-xs-7  ">'
				+ '<label class="pull-left">Type:</label><text class="pull-left" id="contentType'
				+ contentId
				+ '">&emsp;'
				+ contentType
				+ '</text>'
				+ '</div>'
				+ '<div class="pull-right">'
				+ '<div class="dropdown">'
				+ '<a id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="content-dropdown icon-dropdown">'
				+ '<i class=" fa fa-ellipsis-v"></i>'
				+ '</a>'
				+ '<ul class="dropdown-menu dropdown-menu-right " aria-labelledby=" dlabel">'
				+ '<li>' + '<a id="' + sectionId + '##' + contentId
				+ '" class="cursor-pointer" onclick="renametitle('+ids+')">Edit</a>' + '</li>'
				+ '<li class="divider"></li>' 
				+ '<li>' + '<a id="contentDelete'
				+ contentId + '"  class="btndelete cursor-pointer">Delete</a>'
				+ '</li>' + '</ul>' + '</div>' + '</div>' + '</div>' + '</div>';*/
		
		/*$("#heading" + sectionId).hide();*/
		$("#sectionContent" + sectionId).append(str);
		if (isTest == 1) {
			var courseId = $("#courseid").val();
			var id = sectionId + '##' + contentId;
			var obj = document.getElementById(id);
			$(obj).removeClass();
			var url = 'addEditTest?courseId=' + courseId + '&sectionId='
					+ sectionId + '&testId='
					+ contentPath.substring(contentPath.lastIndexOf("=") + 1)
					+ '&contentId=' + contentId;
			$(obj).attr('onclick', "location.href='" + url + "'");
			$("#contentName" + contentId).attr('onclick',
					"showContent('" + contentPath + "','" + contentType + "')");
		}
		$(".selector").sortable("refresh");
	} else {
		$("#contentName" + contentId).text(contentName);
		$("#contentIcon" + contentId).attr('src','resources/images/'+ contentIcon);
		$("#contentName" + contentId).attr('onclick',
				"showContent('" + contentPath + "','" + contentType + "')");
		/*$("#contentType" + contentId).html("&emsp;" + contentType);*/
	}

}
/**
 * @summary This is used for update the order of content when user sort the
 *          content using draggble event.
 * @param This
 *            is only parameter which has id of all content's div in sortable
 *            order.
 * @return no.
 */
function updateContentOrder(sortingOrderContentId) {
	$.ajax({
		type : 'POST',
		url : 'updatecontentorder',
		data : "contentDivID=" + sortingOrderContentId,
		success : function(data) {
			// alert("getted");
		},
		error : function() {
			$("#emptySection p").text(messages['msg.somethingwentwrong']);
			$("#emptySection").modal('show');
		}

	});
}
/**
 * @summary This is used for checking that section name provided by user is
 *          valid or not.
 * 
 * @return {boolean type}true/false This returns true if section name is valid
 *         after performing all condition like length,alphanumeric value e.t.c.
 *         otherwise false;
 */
function sectionNameValidation() {
	
	$("#sectionName").css({"border-color" : ""});	
	$("#sectionName").next('span').remove();
	
	/**
	 * regrex condtion for aluphanumeric value.
	 */
	var regrex = /^[a-zA-Z0-9 ]+$/;
	/**
	 * getting section's name
	 */
	var sectionName = $("#sectionName").val().trim();
	/**
	 * checking section's name empty.
	 */
	if (sectionName.length == 0) {
		$("#sectionName").css("border-color", "#c95b5b");
		$("#sectionNameError1").fadeIn();
		document.sectionForm.sectionName.focus();
		return false;
	}
	
	
	if (sectionName.length > 200) {		
		$("#sectionName").css("border-color", "#c95b5b");
		$('#sectionName').after("<span class='text-red'>"+messages['msg.maxcharacterlength'].replace('#maxlength',200)+"</span>"); 
		document.sectionForm.sectionName.focus();
		return false;
	}	
	
	/**
	 * checking section's name is alphanumeric.
	 */
	if (!regrex.test(sectionName)) {
		$("#sectionName").css("border-color", "#c95b5b");
		$("#sectionNameError2").fadeIn();
		document.sectionForm.sectionName.focus();
		return false;
	}
	
	
	return (true);
}
/**
 * @summary This is used for fade out showing validation's error on front end on
 *          key up or key down by user.
 * 
 * @return no
 */
function sectionNameKey() {
	var sectionName = $("#sectionName").val().trim();
	if (sectionName.length > 0) {
		$("#sectionName").css("border-color", "#7ac17d");
		$("#sectionNameError1").fadeOut();
		$("#sectionNameError2").fadeOut();
	}
}

/**
 * @summary This is used for when any click event is fired on element by user
 *          which has btndelete class.
 * 
 * @return no.
 */
$(document).on('click', '.btndelete', function() {
	$("#deleteAlert p").text(messages['msg.content.delete']);
	currentObject = this;
	$("#deleteButton").attr('onclick', 'deleteContent()');
	$("#deleteAlert").modal('show');
});

/**
 * @summary This is used for when any click event is fired on element by user
 *          which has sectionDelete class.
 * 
 * @return no.
 */
$(document).on('click', '.sectionDelete', function() {
	
	$("#deleteAlert p").text(messages['msg.deletesection']);
	currentObject = this;
	$("#deleteButton").attr('onclick', 'deleteSection()');
	$("#deleteAlert").modal('show');
});


$(document).on('click', '.sessionDelete', function() {
	$("#deleteAlert p").text(messages['msg.deletesection']);
	currentObject = this;
	$("#deleteButton").attr('onclick', 'deleteSession()');
	$("#deleteAlert").modal('show');
});

function deleteSession() {
	
	var sessionId = $(currentObject).attr("id").split('sectionDelete');
	var sessionId = sessionId[1];
	
	$.ajax({
		type : 'GET',
	 url : 'deleteSession?sessionId=' + sessionId,
		success : function(result) {
			console.log(result);
			if (result=="true") {
			
				 location.reload();
				/*$(currentObject).closest("div.totalSections").remove();
				$('#suggestioncontentlist').DataTable().draw();
				var totalSections = $(".totalSections").length;
				if(totalSections == 0){
					$("#course-content-submit-button").text(messages['lbl.savelater']);
					$("#course-content-submit-button").attr("onclick","location.href='courselist'");
				}*/
			} else {
				alert("Sorry we cannot delete section");
			}
		}

	});
}

/**
 * @summary This is used for performing operation for deleting section and its
 *          content.
 * 
 * @return no.
 */
function deleteSection() {
	var sectiondivId = $(currentObject).attr("id").split('sectionDelete');
	var sectionId = sectiondivId[1];
	
	$.ajax({
		type : 'GET',
		url : 'deletecoursecontent?sectionId=' + sectionId,
		success : function(result) {
			if (result) {
				$(currentObject).closest("div.totalSections").remove();
				$('#suggestioncontentlist').DataTable().draw();
				var totalSections = $(".totalSections").length;
				if(totalSections == 0){
					$("#course-content-submit-button").text(messages['lbl.savelater']);
					$("#course-content-submit-button").attr("onclick","location.href='courselist'");
				}
			} else {
				alert("First Delete All Session");
			}
		}

	});
}
/**
 * @summary This is used for performing operation for deleting content.
 * 
 * @return no.
 */
function deleteContent() {
	
	var contentdivId = $(currentObject).attr("id").split('contentDelete');
	var mapSectionId = contentdivId[0].trim();
	var contentId = contentdivId[1].trim();
	$.ajax({
		type : 'GET',
		url : 'deletecoursecontent?contentId=' + contentId+'&mapSectionId='+mapSectionId,
		success : function(result) {
			if (result) {
				$(currentObject).closest("div.contentItem").remove();
				$('#suggestioncontentlist').DataTable().draw();
			} else {

			}
		}

	});
}

/**
 * @summary This is used for showing content of Course.
 * 
 * @return no.
 */
function showContent(contentURL,type) {
	console.log(contentURL+" "+type);
	$("#promoVideoDiv").append('<div id="promoVideo"></div>');
	
	$("#frame").html("");
	if(type=="PPT"){
		/*window.open("http://docs.google.com/viewerng/viewer?url="+contentURL);*/
		$('.modal-lg').css('width', '90%');		
		var frameHTML = '<iframe src="http://docs.google.com/viewerng/viewer?url='
				+ contentURL
				+ '&embedded=true" style="width:100%;height:500px" frameborder="0" allowfullscreen webkitallowfullscreen></iframe>';
		$("#frame").append(frameHTML);		
	}else if(type=="URL"){		
 		playVideo(contentURL);
 	}else{
		$('.modal-lg').css('width', '90%');		
		var frameHTML = '<iframe src="'
				+ contentURL
				+ '" style="width:100%;height:500px" allowfullscreen webkitallowfullscreen></iframe>';
		$("#frame").append(frameHTML);			
	}
	$("#showContent").modal({
		backdrop : 'static',
		keyboard : false
	});	
}

var getalreadycreatedcourse = function(){
	var courseId = parseInt($("#courseid").val());
	$("#overlay").show();
	$("#courselist").html("");
	$("#courselist").load('getallsectionlist?courseId='+courseId,function(response,status,xhr){
		$("#overlay").hide();
	});
}

function closeModalnew(){
	$("#promoVideo").remove();
	$('#promoVideoDiv').empty();
	$('#frame').empty();
}





/**
 * @summary This is used for getting processing of content list from server side based on search filter.
 * @param contentType
 * @param visiblity
 * @returns no
 */
function getSuggestionContentList() {
	var courseId = parseInt($("#courseid").val().trim());
	var contentURL = 'getContentSuggestionList?courseId='+courseId;
	if ($('#suggestioncontentlist').DataTable() != null) {
		$('#suggestioncontentlist').DataTable().destroy();
	}
	$('#suggestioncontentlist')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"bFilter": false,
						"bLengthChange": false,
						"bSort": false,
						"bInfo": false,
						"iDisplayLength": 6,
						"sPaginationType": "full",
						"language" : datatablelanguagejson,
						"ajax" : {
							"url" : contentURL,
							"type" : "GET",
							"data" : function(data) {
								planify(data);
							}
						},
						"columns" : [
						{
							"data" : "contentName"
						}
						],
						"columnDefs" : [
								{
									// The `data` parameter refers to the data for the cell (defined by the
									// `data` option, which defaults to the column being worked with, in
									// this case `data: 0`.
									"render" : function(data, type, row) {
										var action = '<div draggable="true" ondragstart="dragContent(event)" ondragend="dragEnd(event)" id="draggedContent_'+row.contentId+'" style="cursor:pointer"><div class="input-group"><div class="input-group-addon" style="background-color:transparent;padding:12px"><img src="resources/images/'+row.contentIconPath+'" class="contentIcon"></div><div style="padding:12px"><h5 style="text-align:center">'+(data.length>23?data.substring(0,23)+'...':data)+'</h5></div></div></div>';
										return action;
									},
									"targets" : 0
								}],
					"drawCallback" : function(data) {
						var createdSectionLength = $(".totalSections").length
						if(data.jqXHR.responseJSON.recordsTotal>0 && createdSectionLength>0){
							$("#section_content_div").removeClass().addClass('col-sm-9');
							$("#suggestion_content_div").show();
						}
						else
							{
							$("#section_content_div").removeClass().addClass('col-sm-12');
							$("#suggestion_content_div").hide();
							}
					}			
					});
		      $("#suggestioncontentlist_paginate").parent().removeClass('col-sm-7').addClass('col-sm-12');
              $("#suggestioncontentlist").closest('div').css("padding","0px !important");		
}

/**
 * @summary this function is used for cpnverting 3d array data into 2d array data of columns of data tables.
 * @param data
 * @returns no.
 */
function planify(data) {
	for (var i = 0; i < data.columns.length; i++) {
		column = data.columns[i];
		column.searchRegex = column.search.regex;
		column.searchValue = column.search.value;
		delete (column.search);
	}
}

/**
 * @summary This is used for performing operation after completed dragged activity.
 * @param ev
 */
function dragEnd(ev){
	$(".content_dropzone").hide();
}

/**
 * @summary This function is used for allowing the drop feature on an element.
 * @param ev
 * @returns no.
 */
function allowDropContent(ev) {
	ev.preventDefault();
}

/**
 * @summary This function would be call when user start drag activity on an
 *          element.
 * @param ev
 * @returns no.
 */
function dragContent(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
	$(".content_dropzone").show();
}
/**
 * @summart This function is used to get action after drop an element.
 * @param ev
 * @returns no.
 */
function dropContent(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	if ($(ev.target).hasClass('content_dropzone') || $(ev.target).hasClass('content_dropzone_h')) {
		var sectionarr = (ev.target.id).split('_');
		var sectionIdfordrop = sectionarr[1].trim();
		var sourceobject = document.getElementById(data)
		var sourceobjectid = $(sourceobject).attr('id');
		var sourceobjectidarr = sourceobjectid.split('_');
		var draggedContentId = sourceobjectidarr[1].trim();
		if(draggedContentId>0){
			mapDragContent(sectionIdfordrop, draggedContentId);
		}
		else
			{
			
			}
	}
}

/**
 * @summary This is used for creating mapping of dragged content with section. 
 * @param sectionId
 * @param contentId
 */
function mapDragContent(sectionId, contentId){
	var courseId = $("#courseid").val();
	$.ajax({
		url:"mapsuggestioncontentintosection?courseId="+parseInt(courseId)+"&sectionId="+parseInt(sectionId)+"&contentId="+parseInt(contentId),
		type:"GET",
		error:function(){
			alert("error");
		},
		success:function(content){
			$('#suggestioncontentlist').DataTable().draw();
			addSectionContentFromSuggesstionList(sectionId,content);
		}
	});
}

/**
 * @summary This is used for adding content in section.
 * @param sectionId
 * @param content
 */
function addSectionContentFromSuggesstionList(sectionId,content) {
	var isTest = 0;
	var isSaveOrUpdate = 0;
	var courseId = $("#courseid").val();
	if (isSaveOrUpdate == 0) {
		var ids = sectionId+ '##'+content.contentId;
		var contentViewdata = '';
		var editClass="";
		if(content.contentType=='PPT' || content.contentType=='PPTX' || content.contentType=='PDF' || (content.contentType=='VIDEO' && content.questionList!=null && content.questionList.length>0)){
			contentViewdata = '<a id="contentName'+content.contentId+'" class="cursor-pointer" href="coursepreview?courseId='+courseId+'&isPublish=0&contentId='+content.contentId+'&origin=true">'
				+ (content.contentName!=null ? content.contentName : content.content)
	           + '</a>'
		}
		
		if(content.contentType!='PPT' && content.contentType!='PPTX' && content.contentType!='PDF' && (content.contentType!='VIDEO' || content.questionList==null || content.questionList.length==0)){
			contentViewdata = '<a id="contentName'+content.contentId+'" class="cursor-pointer" onclick="showContent(\''+content.contentPath+'\',\''+content.contentType+'\')">'
				+ (content.contentName!=null ? content.contentName : content.content)
	           + '</a>'
		}
		
		if(content.contentType=='TEST'){
			editClass = 'hide';
			$("#sectionNameText"+sectionId).closest('.totalSections').removeClass("section_setting_validation_failed");
		}
		var str ='<div class="well1 well-sm contentItem" id="sortable_'+content.contentId+'">'
			    +'<img src="resources/images/'+content.contentIconPath+'" class="contentIcon" id="contentIcon'+content.contentId+'">'
			    +'<label>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;'+contentViewdata+'</label>'
			    +'<div class="pull-right">'
			    +'<div class="dropdown">'
			    +'<a id="dLabel" data-target="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" class="content-dropdown icon-dropdown"> <img src="resources/adminlte/dist/img/ellipsis-v.png" class="shape"/></a>'
			    +'<ul class="dropdown-menu dropdown-menu-right" aria-labelledby=" dlabel">'
			    +'<li class="'+editClass+'"><a id="' + sectionId + '##' + content.contentId + '" onclick="renametitle(\''+ids+'\')" class="cursor-pointer">'+messages['lbl.edit']+'</a></li>'
			    +'<li class="divider '+editClass+'"></li>'
			    +'<li><a id="'+sectionId+'contentDelete'+content.contentId+'" class="btndelete cursor-pointer">'+messages['lbl.delete']+'</a></li>'
			    +'</ul>'
			    +'</div>'
			    +'</div>'
			    +'</div>';
		$("#sectionContent" + sectionId).append(str);
		$(".selector").sortable("refresh");
	} 
}




/**
 * @start starting of section setting 's code
 */



/**
 * This is used getting section info.
 * @param sectionId
 */
function sectionSettingInfoPopup(sectionId){
	angular.element(document.getElementById("sectionSettingController")).scope().getSectionSettingInfo(parseInt($.trim(sectionId)));
}


var app = angular.module("sectionSettingApp",[]);
angular.module('ngMessagesExample', ['ngMessages']);
app.controller('sectionSettingController',sectionSettingController);

/**
 * @summary This is controller used for controlling the section setting form scope.
 */
function sectionSettingController($scope,$http){
	$scope.courseId = parseInt($.trim($("#courseid").val()));
	$scope.IsVisible = false;
	$scope.passingCriteria;
	
	/**
	 * @summary This is used for show and hide the passing criteria div.
	 */
	$scope.showHidePassingCriteria = function (sectionContent) {
		if(sectionContent.isQuizMandatory == 1){
			sectionContent.passingCriteria = $scope.passingCriteria;
			$("#passingCriteria-required-error").closest(".form-row").removeClass("form-error");
			$("#passingCriteria-required-error").fadeOut();
			$("#passingCriteria-value-invalid").fadeOut();
		}
        $scope.IsVisible = $scope.IsVisible ? false : true;
    }
	
	/**
	 * @summary This is used getting section's setting data.
	 */
	$scope.getSectionSettingInfo = function(sectionId){
		$http.get("getCourseSectionSettingInfo?courseId="+$scope.courseId+"&sectionId="+sectionId).then(function(response){
			$scope.sectionContent = response.data;
			$scope.IsVisible = response.data.isQuizMandatory==1 ? true:false;
			$scope.passingCriteria = response.data.passingCriteria;
			$("#minTimeSpent-required-error").closest(".form-row").removeClass("form-error");
			$("#passingCriteria-value-invalid").closest(".form-row").removeClass("form-error");
			$("#setting-form-submit-error").fadeOut();
			$(".validation--error").fadeOut();
			$("#sectionSettingPop").modal('show');
		});
	};

	/**
	 * @summary This is used fade out the validation error for section section form's data.
	 */
	$scope.keyValidateforSettingForm = function(){
		var _passingCriteria = $.trim($('#sectionSettingForm input[name=passingCriteria]').val());
		var _minTimeSpent = $.trim($('#sectionSettingForm input[name=minTimeSpent]').val());
		if(_passingCriteria.length>0){
			$("#passingCriteria-required-error").closest(".form-row").removeClass("form-error");
			$("#passingCriteria-required-error").fadeOut();
			$("#passingCriteria-value-invalid").fadeOut();
			$("#setting-form-submit-error").fadeOut();
		}
		if(_minTimeSpent.length>0){
			$("#minTimeSpent-required-error").closest(".form-row").removeClass("form-error");
			$("#minTimeSpent-required-error").fadeOut();
			$("#minTimeSpent-value-invalid").fadeOut();
			$("#setting-form-submit-error").fadeOut();
		}
	};
	
	/**
	 * @summary This is used for updating the section setting form data.
	 */
	$scope.updateSectionSettingInfo = function(){
		var _isQuizMandatory = $('#sectionSettingForm input[name=isQuizMandatory]:checked').val();
		var _minTimeSpent = $.trim($('#sectionSettingForm input[name=minTimeSpent]').val());
		var isValid = true;
		if(_isQuizMandatory == 1){
			var _passingCriteria = $.trim($('#sectionSettingForm input[name=passingCriteria]').val());
			if(_passingCriteria == ''){
				isValid = false;
				$("#passingCriteria-required-error").closest(".form-row").addClass("form-error");
				$("#passingCriteria-required-error").fadeIn();
				$('#sectionSettingForm input[name=passingCriteria]').focus();
			}
			else if (!_passingCriteria.match(/^\d*$/)) {
				isValid = false;
				$("#passingCriteria-value-invalid").closest(".form-row").addClass("form-error");
				$("#passingCriteria-value-invalid").fadeIn();
				$('#sectionSettingForm input[name=passingCriteria]').focus();
			}
			else if(parseInt(_passingCriteria)<1 || parseInt(_passingCriteria)>100){
				isValid = false;
				$("#passingCriteria-value-invalid").closest(".form-row").addClass("form-error");
				$("#passingCriteria-value-invalid").fadeIn();
				$('#sectionSettingForm input[name=passingCriteria]').focus();
			}
		}
		
		if(isValid && _minTimeSpent == ''){
			isValid = false;
			$("#minTimeSpent-required-error").closest(".form-row").addClass("form-error");
			$("#minTimeSpent-required-error").fadeIn();
			$('#sectionSettingForm input[name=minTimeSpent]').focus();
		}
		
		if(isValid && (!_minTimeSpent.match(/^\d*$/) || _minTimeSpent < 5 || _minTimeSpent > 3600)){
			isValid = false;
			$("#minTimeSpent-value-invalid").closest(".form-row").addClass("form-error");
			$("#minTimeSpent-value-invalid").fadeIn();
			$('#sectionSettingForm input[name=minTimeSpent]').focus();
		}
	    
		/**
		 * @summary if section setting form is validate then submit the form.
		 */
		if(isValid){
		$("#overlay").show();
	    var data = {
			      'courseId'              : $('#sectionSettingForm input[name=courseId]').val(),
			      'sectionId'             : $('#sectionSettingForm input[name=sectionId]').val(),
			      'isQuizMandatory'       : $('#sectionSettingForm input[name=isQuizMandatory]:checked').val(),
			      'passingCriteria'       : $('#sectionSettingForm input[name=passingCriteria]').val(),
			      'minTimeSpent'          : $('#sectionSettingForm input[name=minTimeSpent]').val()
			       };
		var config = {
                headers : {
                    'Content-Type': 'application/json'
                }
            }
		$http.post("updateCourseSectionSettingInfo",
				data, config).then(function(response){
					if(response.data){
						$("#overlay").hide();
						$("#sectionSettingPop").modal('hide');
						setTimeout(function() {
							$("#section-setting-updated p").text(messages['msg.sectionsettingupdatedsuccess']);
							$("#section-setting-updated").modal('show');
							setTimeout(function() {
								$("#section-setting-updated").modal('hide');
								 }, 1000);
							 }, 500);
					}
					else
				     {
						$("#setting-form-submit-error").fadeIn();
						$("#overlay").hide();
				     }
		     });
		}
	}	
}

/**
 * @summary directive for using iCheck on radio buttons.
 */
app.directive('icheck', function($timeout, $parse) {
    return {
        link: function($scope, element, $attrs) {
            return $timeout(function() {
                var ngModelGetter, value;
                ngModelGetter = $parse($attrs['ngModel']);
                value = $parse($attrs['ngValue'])($scope);
                $scope.$watch($attrs['ngModel'], function(changedValue){
                    $(element).iCheck('update');
                });
                return $(element).iCheck({
                    radioClass: 'iradio_flat-green',
                }).on('ifChanged', function(event) {
                        if ($(element).attr('type') === 'radio' && $attrs['ngModel']) {
                            return $scope.$apply(function() {
                                return ngModelGetter.assign($scope, value);
                            });
                        }
                    }).on('ifClicked',function(event){
                    	$scope.showHidePassingCriteria($scope.sectionContent);
                });
            });
        }
    };
});

/**
 * @end ending of section setting 's code
 */

/**
 * @summary This is used for validation the section setting.
 */
var validateSectionSetting = function(){
	$("#overlay").show();
	$.ajax({
		  url:'checksectionsettingvalidation?courseId='+parseInt($.trim($("#courseid").val())),
		  method:'GET',
		  error:function(){
			  $("#overlay").hide();
			  $("#emptySection p").text(messages['msg.somethingwentwrong']);
			  $("#emptySection").modal('show');
		  },
		  success:function(response){
			  if(response.status == 200){
				  location.href = 'courseViewController?courseId='+parseInt($.trim($("#courseid").val()))+'&isPublish='+$("#publish").val();
			  }	  
			  else{
				  $("#setting_validation_failed_section_list").empty();
				  for(var i=0;i<response.errorSectionList.length;i++){
					  $("#setting_validation_failed_section_list").append("<li>"+response.errorSectionList[i].sectionName+"</li>");
					  $("#sectionNameText"+response.errorSectionList[i].sectionId).closest('.totalSections').addClass("section_setting_validation_failed");
				  }
				  $("#overlay").hide();
				  $("#section_setting_validation_failed p").text(messages['msg.sectionsettingvalidationfailed']);
				  $("#section_setting_validation_failed").modal('show');
				 
			  }
		  }
	});
	
	
	

}

$(document).ready(function(){
	$("#hideTemplate").click(function(){
    $(".feedbackTemplate").hide();
    $("#mytemplate").show();
   
  });
  
});
function openModel(subject,mail,sessionId){
	alert(mail);
	$('#subject'+sessionId).prop('value',subject);
	$('#mail'+sessionId).prop('value',mail);
	$(".feedbackTemplate").show();
	    $("#mytemplate").hide();
	 
};
function sendDoubtMail(sessionId){
var dataString={
		 'mail':$('#mail'+sessionId).val(),
		 'subject':$('#subject'+sessionId).val(),
		 'body':$('#body'+sessionId).val()
 };
var data=JSON.stringify(dataString);
	debugger;
	$.ajax({
		url:"sendMail",
		type:"POST",
		dataType: 'json',
        contentType: 'application/json',
		data:data,
		success:function(response){
			if(response.code==200 && response.result != null)
				alert("sucess");
			else
				alert("fail");
			
		}
	});
}