<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<link rel="stylesheet" href="resources/css/custom.css">
<%@ include file="include.jsp"%>
<script src="<spring:url value='/resources/js/uploadcontent.js'/>"></script>
<style>
.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-left: 0px;
	margin-right: 0px;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-top-color: #f1f1f1;
	border-bottom-color: #7d7d7d;
}

.nav-tabs-custom>.nav-tabs>li.active>a, .nav-tabs-custom>.nav-tabs>li.active:hover>a
	{
	background-color: #ECF0F5;
	color: #444;
	padding-top: 5px;
	padding-bottom: 5px;
}

.nav-tabs-custom {
	margin-bottom: 20px;
	background: #fff;
	box-shadow: 0 0px 0px rgba(0, 0, 0, 0.1);
	border-radius: 3px;
}

.nav-tabs {
	border-bottom: 0px solid #ddd !important;
}

#questionData th {
	vertical-align: top !important;
}

@media only screen and (min-width: 767px) {
	.content-wrapper {
		/* background-color: yellow; */
		margin-left: 230px;
	}
}

@media only screen and (max-width: 760px) {
	.content-wrapper {
		/*  background-color: pink; */
		margin-left: 0px;
	}
}

.google_drive_dropbox_modal_title .notifications .notification.notification-critical
	{
	background-position: left 12px top -387px;
}

.google_drive_dropbox_modal_title .notifications .notification {
	padding: 14px 15px 15px 44px;
	color: #BC1702;
	background: url('resources/images/msg_alert_icon.png') no-repeat;
	background-size: 20px;
}

#google_drive_dropbox_file_alert .modal-content p {
	color: #BC1702;
}

.google_drive_dropbox_success_modal_title .notifications .notification.notification-critical
	{
	background-position: left 12px top 12px;
}

.google_drive_dropbox_success_modal_title .notifications .notification {
	padding: 14px 15px 15px 44px;
	background: url('resources/images/msg_alert_icon.png') no-repeat;
	color: #216D0D;
	background-size: 20px;
}

p {
	word-break: break-word;
}

.dropdown_button_group_for_file_upload {
	background-color: #00A65A;
}

.dropdown_button_group_for_file_upload a {
	color: #fff !important;
}

.dropdown_button_group_for_file_upload>li>a:focus,
	.dropdown_button_group_for_file_upload>li>a:hover {
	color: #fff !important;
	text-decoration: none;
	background-color: #00A65A;
}

/**
 * This is for google drive picker pop up.
 */
.picker.dcs-s-dcs-t.picker-dialog {
	top: 60px !important;
}

.bulk_file_upload_modal .box-header, .single_file_upload_modal .box-header
	{
	min-height: 55px;
}

.bulk_file_upload_modal .box-header .box-title,
	.single_file_upload_modal .box-header .box-title {
	line-height: 2;
}

.single_upload_file_or {
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left: 30px
}
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini"
	style="min-width: 700px">
	<div class="wrapper">
		<div id="googlePickerOverlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<div class="row">
					<div class="col-sm-12">
						<section class="content-header">
							<div class="pull-left">
								<h3 style="margin: 0">
									<spring:message code="lbl.contentlibrary"
										text="Content Library" />
								</h3>
								<h3></h3>
							</div>
							<%-- <div class="btn pull-right hide">
								<button id="addnew" class="btn btn-flat bg-navy hide"
									onclick="location.href='adduploadcontent'">
									<spring:message code="lbl.uploadnewcontent"
										text="Upload New Content" />
								</button>
								<button id="bulkbtn11" class="btn btn-flat btn-success" onclick="onApiLoad()">
									<spring:message code="lbl.googledrive"
										text="Pick From Google Drive" />
								</button>
								<button id="bulkbtn1" class="btn btn-flat btn-success">
									<spring:message code="lbl.bulkuploadcontent"
										text="Bulk Upload Content" />
								</button>
								<button id="singlebtn1" class="btn btn-flat btn-success">
									<spring:message code="lbl.singleupload" text="Single Upload" />
								</button>
							</div> --%>
							<div class="pull-right">
								<div class="dropdown upload_content_button_dropdown">
									<button class="btn btn-success dropdown-toggle button-width-large" type="button" data-toggle="dropdown">
										<spring:message code="lbl.uploadcontent" text="Upload Content" />
										&nbsp; <span class="caret"></span>
									</button>
									<ul class="dropdown-menu dropdown-menu-right dropdown_button_group_for_file_upload" aria-labelledby="dlabel">
										<%-- <li>
										 <a onclick="getGoogleOauthToken()" class="cursor-pointer">
										    <spring:message code="lbl.pickfromgoogledrive" text="Pick From Google Drive" />
										 </a>
										</li>
										<li class="divider"></li>
										<li>
										   <a onclick="dropboxChooserInit()" class="cursor-pointer">
										       <spring:message code="lbl.pickfromdropbox" text="Pick From Dropbox" />
										   </a>
										</li>
										<li class="divider"></li> --%>
										<li>
										   <a class="cursor-pointer" id="bulkbtn">
										       <spring:message code="lbl.bulkuploadcontent" text="Bulk Upload Content" />
										   </a>
										</li>
										<li class="divider"></li>
										<li>
										    <a class="cursor-pointer" id="singlebtn">
										        <spring:message code="lbl.singleupload" text="Single Upload" />
										    </a>
									</li>
									</ul>
								</div>
							</div>

							<br />
						</section>
					</div>
				</div>
				<section class="content">
					<div class="row">
						<div class="col-md-12">
							<div class="box no-border">
								<!-- /.box-header -->
								<div class="box-body no-padding">
									<div class="col-lg-6 form-group" style="margin-top: 15px;">
										<label style="text-transform: capitalize; font-size: 14px"
											class="col-sm-3 col-lg-4 col-xs-12 col-md-3 control-label">
											<spring:message code="lbl.contenttype" text="Content Type" />
										</label>
										<div class="col-sm-9 col-lg-5 col-xs-12 col-md-9">
											<select id="type" class="form-control"
												style="padding: 5px 3px;"
												onchange="getcontentlistbyfilters();">
												<option value="-1" selected="selected"><spring:message
														code="lbl.all" text="All" /></option>
												<c:forEach items="${contentTypeList}" var="type">
													<c:if test="${type.contentType!='TEST'}">
														<c:if test="${typeid==type.contentType}">
															<option value="${type.contentType}" selected="selected">${type.contentType}</option>
														</c:if>
														<c:if test="${typeid!=type.contentType}">
															<option value="${type.contentType}">${type.contentType}</option>
														</c:if>
													</c:if>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-lg-6 form-group" style="margin-top: 15px;">
										<label style="text-transform: capitalize; font-size: 14px"
											class="col-sm-3 col-lg-3 col-xs-12 col-md-3 control-label">
											<spring:message code="lbl.sharemedia" text="Share Media" />
										</label>
										<div class="col-sm-9 col-lg-6 col-xs-12 col-md-9">
											<select id="visiblity" class="form-control"
												style="padding: 5px 3px;"
												onchange="getcontentlistbyfilters();">
												<option value="-1" selected="selected"><spring:message
														code="lbl.all" text="All" /></option>
												<c:if test="${visiblity==1}">
													<option value="0"><spring:message
															code="lbl.private" text="Private" /></option>
													<option value="1" selected="selected"><spring:message
															code="lbl.public" text="Public" /></option>
												</c:if>
												<c:if test="${visiblity==0}">
													<option value="0" selected="selected"><spring:message
															code="lbl.private" text="Private" /></option>
													<option value="1"><spring:message
															code="lbl.public" text="Public" /></option>
												</c:if>
												<c:if test="${visiblity==null || visiblity==-1}">
													<option value="0"><spring:message
															code="lbl.private" text="Private" /></option>
													<option value="1"><spring:message
															code="lbl.public" text="Public" /></option>
												</c:if>
											</select>
										</div>
									</div>
									<div class="col-xs-12 hide">
										<div class="mailbox-controls col-xs-12">
											<!-- Check all button -->
											<div class="btn-group">
												<button class="btn btn-default btn-sm">
													<i class="fa fa-trash-o"></i>
												</button>
											</div>
											<!-- /.btn-group -->
											<button class="btn btn-default btn-sm"
												onclick="location.href='test';">
												<i class="fa fa-refresh"></i>
											</button>
										</div>
									</div>
									<br />
									<div class="table-responsive mailbox-messages col-xs-12">
										<table class="table table-hover table-striped"
											id="contentlisttable">
											<thead>
												<tr>
													<!-- <th style="width: 3%"> -->
													<!-- <button
													class="btn btn-default btn-sm checkbox-toggle">
													<i class="fa fa-square-o"></i>
												</button> -->
													<!-- </th> -->
													<th><spring:message code="lbl.title" text="Title" /></th>
													<!-- <th>Content Name</th> -->
													<th><spring:message code="lbl.type" text="type" /></th>
													<th><spring:message code="lbl.sharemedia"
															text="Share Media" /></th>
													<th><spring:message code="lbl.courses" text="Courses" /></th>
													<th><spring:message code="lbl.questionadded"
															text="Question Added" /></th>
													<th><spring:message code="lbl.uploadeddate"
															text="Uploaded Date" /></th>
													<th><spring:message code="lbl.action" text="Action" /></th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
										<!-- /.table -->
									</div>
									<!-- /.mail-box-messages -->
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /. box -->
						</div>
						<!-- /.col -->
					</div>
				</section>
			</div>
			<!-- content-wrapper -->
		</div>
		<!-- End dataTable----->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	<!-- Start of Alert box for delete test -->
	<div class="modal fade" id="deleteAlert" tabindex="-1" role="dialog"
		aria-labelledby="deletetestAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p>
							<spring:message code="msg.content.delete"
								text="Are you sure to delete this content?" />
						</p>
						<button type="button" class="btn btn-default button-width"
							style="" data-dismiss="modal">
							<spring:message code="lbl.no" text="No" />
						</button>
						<button type="button" id="yesbtn"
							class="btn btn-success button-width">
							<spring:message code="lbl.yes" text="Yes" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End of Alert box -->
	<div class="modal fade" id="contentErrorAlert" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3></h3>
						<p></p>
						<button type="button"
							class="btn btn-primary button-width-large button-blue"
							data-dismiss="modal">
							<spring:message code="lbl.ok" text="Ok" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="bulkmodal" tabindex="-1" role="dialog"
		aria-labelledby="">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-md bulk_file_upload_modal">
				<div class="modal-content">
					<div class="box-header with-border">
						<h3 class="box-title">
							<spring:message code="lbl.uploadcontents" text="Upload Contents" />
						</h3>
						<div class="box-tools pull-right">
							<div class="nav-tabs-custom">
								<ul class="nav nav-tabs"
									style="border-bottom: 1px solid #dedede;">
									<li class="button-width-large active"
										style="margin-right: 0px; text-align: center; min-width: 150px;">
										<a href="#browseTab" data-toggle="tab"><spring:message
												code="lbl.uploadbybrowse" text="Upload by Browse" /></a>
									</li>
<%-- 									<li class=" button-width-large"
										style="margin-right: 0px; text-align: center; min-width: 110px;">
										<a href="#urlTab" data-toggle="tab"><spring:message
												code="lbl.embedurl" text="Embed URL" /></a>
									</li>
								 --%></ul>
							</div>
						</div>
					</div>
					<div id="overlay" class="overlay1"
						style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
						<img id="loading" class="lazy" src="resources/images/loading.gif"
							style="position: fixed; left: 50%; top: 50%;">
					</div>
					<div class="modal-body">
						<div class="tab-content">
							<div id="browseTab" class="tab-pane active">
								<div class="form-group" id="contentdiv">
									<!-- <label>Upload Contents : </label>  -->
									<input type="file" id="bulkfile" name="bulkfile"
										multiple="multiple" onchange="fileprocess();"> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.contenttype.valid"
											text="You can upload maximum 5 files of type(image, pdf, ppt, video, url)"
											arguments="5" htmlEscape="false" argumentSeparator=";" /></span>
								</div>
								<div id="titlediv"></div>
								<div id="errordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button 
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="savebulkfiles('list')">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>

							<div id="urlTab" class="tab-pane">
								<div id="urlset" class="row">
									<!-- <div id="urldiv0" class="form-group">
									<label> Content URL 1</label> 
									<span class="pull-right" id="addmore0">
									 <a href="#" onclick="addMore();">
									 <font color="#00A65A"><i class="fa fa-plus-circle"></i></font></a>&nbsp; 
									 <a href="#" onclick="removeMore();">
									 <font color="#00A65A"><i class="fa fa-minus-circle"></i></font></a>
									</span>
									<input class="form-control" id="url0" placeholder="Add link from youtube">
								</div> -->
                                     
                                     
                                   <!--   below commented code is for url div -->
                                   
									<div id="urldiv0" class="row col-xs-12 form-group">
										<span class="pull-right" id="addmore0"> <a href="#"
											onclick="addMore();"> <font color="#00A65A"><i
													class="fa fa-plus-circle"></i></font></a>&nbsp; <a href="#"
											onclick="removeMore();"> <font color="#00A65A"><i
													class="fa fa-minus-circle"></i></font></a>
										</span>
										<div class="col-xs-7">
											<label><spring:message code="lbl.contenturlinorder"
													text="Content URL 1" arguments="1" htmlEscape="false"
													argumentSeparator=";" /></label> <input
												placeholder="<spring:message code="placeholder.promotionalvideo" text="Add link from youtube"/>"
												id="url0" class="form-control">
										</div>
										<div class="col-xs-4">
											<label><spring:message code="lbl.titleinorder"
													text="Title 1" arguments="1" htmlEscape="false"
													argumentSeparator=";" /></label> <input
												placeholder="<spring:message code="lbl.entertitle" text="Enter Title"/>"
												id="titleurl0" class="form-control">
										</div>
									</div>

								</div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="submitcontenturl('list')">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
								<input type="hidden" id="count" value="1">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="singleUpload" tabindex="-1" role="dialog"
		aria-labelledby="">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-lg single_file_upload_modal">
				<div class="modal-content">
					<div class="box-header with-border">
						<h3 class="box-title">
							<spring:message code="lbl.uploadcontents" text="Upload Contents" />
						</h3>
						<div class="box-tools pull-right">
							<div class="nav-tabs-custom">
								<ul class="nav nav-tabs"
									style="border-bottom: 1px solid #dedede;">
									<li class="active"
										style="margin-right: 0px; text-align: center;"><a
										href="#singleUploadTab" data-toggle="tab"><spring:message
												code="lbl.videowithquestion" text="Video With Question" /></a>
									</li>
<%-- 									<li style="margin-right: 0px; text-align: center;"><a
										href="#pdfTab" data-toggle="tab"><spring:message
												code="lbl.pdfwithquestion" text="PDF With Question" /></a></li>
									<li style="margin-right: 0px; text-align: center;"><a
										href="#pptTab" data-toggle="tab"><spring:message
												code="lbl.pptwithvoice" text="PPT With Voice" /></a></li>
									 --%><!-- <li class=" button-width" style="margin-right:0px;text-align:center;width: 110px;">
								<a href="#richTextTab"
									data-toggle="tab">Rich Text</a></li>	 -->
								</ul>
							</div>
						</div>
					</div>
					<div id="overlaySingle" class="overlay1"
						style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
						<img id="loading" class="lazy" src="resources/images/loading.gif"
							style="position: fixed; left: 50%; top: 50%;">
					</div>
					<div class="modal-body">
						<div class="tab-content">
							<div id="singleUploadTab" class="tab-pane active">
								<%-- <a class="btn btn-success btn-flat" href="importFromLibray?flag=0">
								    <spring:message code="lbl.importfromlibrary" text="Import From Library"/>
								</a>
								<p class="single_upload_file_or">
									<spring:message code="lbl.or" text="OR" />
								</p> --%>
								<div class="form-group" id="singlecontentdiv">
									<!-- <label>Upload Contents : </label>  -->
									<input type="file" id="singlefile" name="singlefile"
										onchange="singlefileprocess();" /> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.onlyvideocanupload"
											text="You can upload only video file." /></span>
								</div>

								<div id="singletitlediv"></div>
								<div id="singleerrordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="singleUploadSubmit();">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>

							<div id="pdfTab" class="tab-pane">
								<a class="btn btn-success btn-flat"
									href="importFromLibray?flag=1"><spring:message
										code="lbl.importfromlibrary" text="Import From Library" /></a> <br />
								<p class="single_upload_file_or">
									<spring:message code="lbl.or" text="OR" />
								</p>
								<div class="form-group" id="pdfcontentdiv">
									<input type="file" id="pdffile" name="pdffile"
										onchange="pdffileprocess('pdf');" /> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.onlypdfcanupload"
											text="You can upload only PDF file." /></span>
								</div>

								<div id="pdftitlediv"></div>
								<div id="pdferrordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="pdfUploadSubmit('pdf');">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>

							<div id="pptTab" class="tab-pane">
								<a class="btn btn-success btn-flat"
									href="importFromLibray?flag=2"><spring:message
										code="lbl.importfromlibrary" text="Import From Library" /></a> <br />
								<p class="single_upload_file_or">
									<spring:message code="lbl.or" text="OR" />
								</p>
								<div class="form-group" id="pptcontentdiv">
									<input type="file" id="pptfile" name="pptfile"
										onchange="pdffileprocess('ppt');" /> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.onlypptcanupload"
											text="You can upload only ppt and pptx file." /></span>
								</div>

								<div id="ppttitlediv"></div>
								<div id="ppterrordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="pdfUploadSubmit('ppt');">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>
							<!-- <div id="richTextTab" class="tab-pane">
						        <div id="urlset">
								<div id="urldiv0" class="form-group">
									<label>Add Content</label> 
									<textarea class="form-control" id="url0" placeholder="Add link from youtube"></textarea>
								</div>
								</div>
								<div class="box-footer">
							    <button class="btn btn-default btn-flat button-width" type="button" onclick="clearbulkmodal();"
								data-dismiss="modal">Close</button>
							    <button class="btn btn-success btn-flat pull-right button-width" type="button"
								onclick="submitcontenturl('list')">Submit</button>
						 </div>
								 <input type="hidden" id="count" value="1">
						</div> -->
						</div>
					</div>
					
					
					<div class="modal fade" id="srtUploadTab" tabindex="-1" role="dialog"
		aria-labelledby="">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-lg single_file_upload_modal">
				<div class="modal-content">
					<div class="box-header with-border">
						<h3 class="box-title">
							<spring:message code="lbl.uploadcontents" text="Upload Contents" />
						</h3>
						<div class="box-tools pull-right">
							<div class="nav-tabs-custom">
								<ul class="nav nav-tabs"
									style="border-bottom: 1px solid #dedede;">
									<li class="active"
										style="margin-right: 0px; text-align: center;"><a
										href="srtUploadTab" data-toggle="tab"><spring:message
												code="lbl.videowithquestion" text="Video With Question" /></a>
									</li>
<%-- 									<li style="margin-right: 0px; text-align: center;"><a
										href="#pdfTab" data-toggle="tab"><spring:message
												code="lbl.pdfwithquestion" text="PDF With Question" /></a></li>
									<li style="margin-right: 0px; text-align: center;"><a
										href="#pptTab" data-toggle="tab"><spring:message
												code="lbl.pptwithvoice" text="PPT With Voice" /></a></li>
									 --%><!-- <li class=" button-width" style="margin-right:0px;text-align:center;width: 110px;">
								<a href="#richTextTab"
									data-toggle="tab">Rich Text</a></li>	 -->
								</ul>
							</div>
						</div>
					</div>
					<div id="overlaySingle" class="overlay1"
						style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
						<img id="loading" class="lazy" src="resources/images/loading.gif"
							style="position: fixed; left: 50%; top: 50%;">
					</div>
					<div class="modal-body">
						<div class="tab-content">
							<div id="singleUploadTab" class="tab-pane active">
								<%-- <a class="btn btn-success btn-flat" href="importFromLibray?flag=0">
								    <spring:message code="lbl.importfromlibrary" text="Import From Library"/>
								</a>
								<p class="single_upload_file_or">
									<spring:message code="lbl.or" text="OR" />
								</p> --%>
								<div class="form-group" id="singlecontentdiv">
									<!-- <label>Upload Contents : </label>  -->
									<input type="file" id="singlefile" name="singlefile"
										onchange="singlefileprocess();" /> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.onlyvideocanupload"
											text="You can upload only video file." /></span>
								</div>

								<div id="singletitlediv"></div>
								<div id="singleerrordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="singleUploadSubmit();">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>

							<div id="pdfTab" class="tab-pane">
								<a class="btn btn-success btn-flat"
									href="importFromLibray?flag=1"><spring:message
										code="lbl.importfromlibrary" text="Import From Library" /></a> <br />
								<p class="single_upload_file_or">
									<spring:message code="lbl.or" text="OR" />
								</p>
								<div class="form-group" id="pdfcontentdiv">
									<input type="file" id="pdffile" name="pdffile"
										onchange="pdffileprocess('pdf');" /> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.onlypdfcanupload"
											text="You can upload only PDF file." /></span>
								</div>

								<div id="pdftitlediv"></div>
								<div id="pdferrordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="pdfUploadSubmit('pdf');">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>

							<div id="pptTab" class="tab-pane">
								<a class="btn btn-success btn-flat"
									href="importFromLibray?flag=2"><spring:message
										code="lbl.importfromlibrary" text="Import From Library" /></a> <br />
								<p class="single_upload_file_or">
									<spring:message code="lbl.or" text="OR" />
								</p>
								<div class="form-group" id="pptcontentdiv">
									<input type="file" id="pptfile" name="pptfile"
										onchange="pdffileprocess('ppt');" /> <span><spring:message
											code="lbl.note" text="Note" /> : <spring:message
											code="msg.onlypptcanupload"
											text="You can upload only ppt and pptx file." /></span>
								</div>

								<div id="ppttitlediv"></div>
								<div id="ppterrordiv"></div>
								<div class="box-footer">
									<button class="btn btn-default btn-flat button-width"
										type="button" onclick="clearbulkmodal();" data-dismiss="modal">
										<spring:message code="lbl.close" text="Close" />
									</button>
									<button
										class="btn btn-success btn-flat pull-right button-width"
										type="button" onclick="pdfUploadSubmit('ppt');">
										<spring:message code="lbl.submit" text="Submit" />
									</button>
								</div>
							</div>
							<!-- <div id="richTextTab" class="tab-pane">
						        <div id="urlset">
								<div id="urldiv0" class="form-group">
									<label>Add Content</label> 
									<textarea class="form-control" id="url0" placeholder="Add link from youtube"></textarea>
								</div>
								</div>
								<div class="box-footer">
							    <button class="btn btn-default btn-flat button-width" type="button" onclick="clearbulkmodal();"
								data-dismiss="modal">Close</button>
							    <button class="btn btn-success btn-flat pull-right button-width" type="button"
								onclick="submitcontenturl('list')">Submit</button>
						 </div>
								 <input type="hidden" id="count" value="1">
						</div> -->
						</div>
					</div>
					
					
					
					
					<!-- dfdfd -->
					
				</div>
			</div>
		</div>
	</div>
					
					<!-- dfdfd -->
					
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="showContentid" role="dialog"
		aria-labelledby="showContent">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body page-background-color">
					<div class="row">
						<div class="col-xs-12" style="text-align: center" id="frame">
						</div>
						<div class="col-xs-12" style="text-align: center"
							id="promoVideoDiv">
							<div id="promoVideo"></div>
						</div>
						<div class="col-xs-12" style="min-height: 30px"></div>
						<div class="col-xs-12" style="min-height: 30px">
							<button type="button" onclick="closeModalnew()"
								class="btn btn-success btn-flat  button-width-large pull-right"
								style="margin: auto" data-dismiss="modal">
								<spring:message code="lbl.close" text="Close" />
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!--  Alert for showing error while uploading file form google drive or dropbox.-->
	<div class="modal fade" id="google_drive_dropbox_file_alert"
		tabindex="-1" role="dialog"
		aria-labelledby="google_drive_dropbox_file_alert">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="modal-title google_drive_dropbox_modal_title">
							<div class="notifications" tabindex="-1">
								<span class="notification notification-critical" role="alert">
									<spring:message code="lbl.operationfailed"
										text="Operation Failed" />
								</span>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="row no-margin">
							<p></p>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-danger button-width btn-flat"
							data-dismiss="modal">
							<spring:message code="lbl.ok" text="OK" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--  Alert for showing success message while uploading file form google drive or dropbox.-->
	<div class="modal fade" id="google_drive_dropbox_success_file_alert"
		tabindex="-1" role="dialog"
		aria-labelledby="google_drive_dropbox_success_file_alert">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="modal-title google_drive_dropbox_success_modal_title">
							<div class="notifications" tabindex="-1">
								<span class="notification notification-critical" role="alert">
									<spring:message code="lbl.fileuploaded" text="File Uploaded" />
								</span>
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="row no-margin text-align">
							<p></p>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button"
							class="btn btn-success button-width btn-flat"
							data-dismiss="modal">
							<spring:message code="lbl.ok" text="OK" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="google_oauth_iframe" style="display: none"></div>
	<%@ include file="dialogs.jsp"%>
</body>
<script type="text/javascript">
	var messages = new Array();
	messages['msg.somethingwentwrong'] = "<spring:message code='msg.somethingwentwrong' javaScriptEscape='true' />";
	messages['lbl.public'] = "<spring:message code='lbl.public' text='Public' javaScriptEscape='true' />";
	messages['lbl.private'] = "<spring:message code='lbl.private' text='Private' javaScriptEscape='true' />";
	messages['lbl.yes'] = "<spring:message code='lbl.yes' text='Yes' javaScriptEscape='true' />";
	messages['lbl.no'] = "<spring:message code='lbl.no' text='No' javaScriptEscape='true' />";
	messages['msg.contentinusecannotedit'] = "<spring:message code='msg.contentinusecannotedit' text='#contentname is in use, can\'t be edit.' arguments='#contentname' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
	messages['msg.cannotbedeletedalreadyinuse'] = "<spring:message code='msg.cannotbedeletedalreadyinuse' text='Cannot be deleted already in use.' javaScriptEscape='true' />";
	messages['msg.contentinusecannotdelete'] = "<spring:message code='msg.contentinusecannotdelete' text='#contentname is in use. Please remove content from following courses #course to delete permanently from Content Library.' arguments='#contentname;#course' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
	messages['msg.filehasbeenuploaded'] = "<spring:message code='msg.filehasbeenuploaded' text='#filename has been successfully uploaded.' arguments='#filename' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
</script>
<script src="<spring:url value='/resources/js/youtubeplayer.js?v=1'/>"></script>
<script
	src="<spring:url value='/resources/js/googlefilepicker.js?v=2'/>"></script>
<script type="text/javascript" src="https://apis.google.com/js/api.js"></script>
<script type="text/javascript"
	src="https://www.dropbox.com/static/api/2/dropins.js" id="dropboxjs"
	data-app-key="f4mmu9v9ntnueyp"></script>
<script src="<spring:url value='/resources/js/dropboxchooser.js?v=1'/>"></script>
<script>
	var player;
	var currentLocale = '${currentLocale}';
	var domainName = '${domainName}';
	$(document).ready(function() {

		/**
		 * @summary add event listener on current window for receiving message and it is used inside google picker beacuse this will catch te token message from google auth iframe page.
		 */
		if (window.addEventListener) {
			window.addEventListener("message", receive, false);
		} else {
			if (window.attachEvent) {
				window.attachEvent("onmessage", receive, false);
			}
		}

		var userStatus = '${userStatus==0}';
		if (userStatus == 'true') {
			$("#addnew").attr("disabled", "disabled");
			$(".callout").removeClass('hide');
		}
		$(".treeview").removeClass("active");
		$("#uploadcontent").addClass("active");
		$("#uploadcontent .treeview-menu > #uploadcontent").addClass("active");

		$('.mailbox-messages input[type="checkbox"]').iCheck({
			checkboxClass : 'icheckbox_flat-blue',
			radioClass : 'iradio_flat-blue'
		});

		$("#bulkbtn").click(function() {
			$("#bulkmodal").modal('show');
		});
		$("#singlebtn").click(function() {
			$("#singleUpload").modal('show');
		});
		/**
		 * getting content list from server.
		 */
		getContentListData();
	});

	var edituploadedcontent = function(id) {
		location.href = "edituploadedcontent?id=" + id;
	}

	var deleteuploadedcontent = function(id) {
		$("#yesbtn").attr('onclick', 'ajaxDelete(' + id + ')');
		$('#deleteAlert').modal('show');
		//location.href="deleteuploadedcontent?id="+id;
	}

	var ajaxDelete = function(contentId) {
		$('#deleteAlert').modal('hide');
		if (contentId > 0) {
			//$("#overlay").show();
			$.ajax({
				url : "deleteuploadedcontent?contentId=" + contentId,
				type : 'POST',
				error : function() {
					$("#overlay").hide();
					$("#contentErrorAlert p").text(
							messages['msg.somethingwentwrong']);
					$("#contentErrorAlert").modal('show');
				},
				success : function(status) {
					//$("#overlay").hide();
					if (status) {
						$('#contentlisttable').DataTable().draw();
						//location.href = 'listuploadcontent';
					} else {
						$("#contentErrorAlert p").text(
								messages['msg.cannotbedeletedalreadyinuse']);
						$("#contentErrorAlert").modal('show');
					}
				}
			});
		}

	}

	/**
	 * @summary Show Mapped Course Content 
	 * @param course
	 * @param contentName
	 */
	function showMappedCourseContent(course, contentName) {
		$("#warningdialog p")
				.text(
						messages['msg.contentinusecannotdelete'].replace(
								'#contentname', contentName).replace('#course',
								course));
		$("#warningdialog").modal('show');
	}
	
	/**
	 * @summary warning model for editing the content.
	 * @param contentName
	 */
	function showContentCantEdit(contentName) {
		$("#warningdialog p")
				.text(
						messages['msg.contentinusecannotedit'].replace(
								'#contentname', contentName));
		$("#warningdialog").modal('show');
	}

	/**
	 * @summary This is used for getting content list based on filters.
	 */
	var getcontentlistbyfilters = function() {
		var contentType = $("#type").val().trim();
		var visibility = $("#visiblity").val().trim();
		getContentListData(contentType, visibility);
	};

	/**
	 * @summary this is used clearing the bulk modal.
	 */
	var clearbulkmodal = function() {
		/* $("#bulkfile").val('');
		$("#titlediv").html('');
		$("#errordiv").html('');
		$("#contentdiv").next('span').remove(); */
		location.href = 'listuploadcontent';
	}

	/**
	 * @summary This is used for showing content of Course.
	 * 
	 * @return no.
	 */
	function showContentNew(contentURL, type) {
		ga('send', {
			hitType : 'event',
			eventCategory : 'MediaContents',
			eventAction : 'viewed',
			eventLabel : type
		});
		if (type == "PPT") {
			/*window.open("http://docs.google.com/viewerng/viewer?url="+contentURL);*/
			$('.modal-lg').css('width', '90%');
			$("#frame").empty();
			var frameHTML = '<iframe  src="http://docs.google.com/viewerng/viewer?url='
					+ contentURL
					+ '&embedded=true" style="width:100%;height:500px" frameborder="0" allowfullscreen webkitallowfullscreen></iframe>';
			$("#frame").append(frameHTML);
		} else if (type == "URL") {
			$("#promoVideoDiv").append('<div id="promoVideo"></div>');
			playVideo(contentURL);
		} else {
			$('.modal-lg').css('width', '90%');
			$("#frame").empty();
			var frameHTML = '<iframe src="'
					+ contentURL
					+ '"  style="width:100%;height:500px" frameborder="0" allowfullscreen webkitallowfullscreen></iframe>';
			$("#frame").append(frameHTML);
		}
		$("#showContentid").modal({
			backdrop : 'static',
			keyboard : false
		});

	}

	/**
	 * @summary This is used for closing the modal.
	 */
	function closeModalnew() {
		$("#promoVideo").remove();
		$('#promoVideoDiv').empty();
		$('#frame').empty();

	}

	/**
	 * @summary Instance of datatable
	 */
	var contentListDataTable;

	/**
	 * @summary This is used for getting processing of content list from server side based on search filter.
	 * @param contentType
	 * @param visiblity
	 * @returns no
	 */
	function getContentListData(contentType, visiblity) {
		var contentURL = 'listuploadcontentserversideprocessing';
		if (contentType == null && visiblity == null) {

		} else if (contentType != '-1' && visiblity != '-1') {
			contentURL = contentURL + "?type=" + contentType + "&visiblity="
					+ visiblity;
		} else if (contentType == '-1' && visiblity != '-1') {
			contentURL = contentURL + "?visiblity=" + visiblity;
		} else if (contentType != '-1' && visiblity == '-1') {
			contentURL = contentURL + "?type=" + contentType;
		}
		if (contentListDataTable != null) {
			$('#contentlisttable').DataTable().destroy();
		}
		contentListDataTable = $('#contentlisttable')
				.DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							'aaSorting' : [],
							"pagingType" : "full_numbers",
							"language" : datatablelanguagejson,
							"ajax" : {
								"url" : contentURL,
								"type" : "GET",
								"data" : function(data) {
									planify(data);
								}
							},
							"columns" : [ {
								"data" : "contentName"
							}, {
								"data" : "contentType"
							}, {
								"data" : "visiblity"
							}, {
								"data" : "courseMapped"
							}, {
								"data" : "videoContentId"
							}, {
								"data" : "createdDate"
							}, {

							} ],
							"columnDefs" : [
									{
										// The `data` parameter refers to the data for the cell (defined by the
										// `data` option, which defaults to the column being worked with, in
										// this case `data: 0`.
										"render" : function(data, type, row) {
											var contentTitle = '';
											if (data != null) {
												if (row.contentType != 'PPTX'
														&& row.contentType != 'PPT'
														&& row.contentType != 'PDF'
														&& (row.videoContentId == null || row.videoContentId == 0)) {
													contentTitle = '<a style="cursor: pointer" onclick="showContentNew(\''
															+ row.contentPath
															+ '\',\''
															+ row.contentType
															+ '\')">'
															+ data
															+ '</a>';
												} else if (row.contentType != 'PPTX'
														|| row.contentType != 'PPT'
														|| row.contentType == 'PDF'
														|| (row.videoContentId != null && row.videoContentId != 0)) {
													contentTitle = '<a class="cursor-pointer" href="viewVideoQuestion?contentId='
															+ row.contentId
															+ '">'
															+ data
															+ '</a>';
												}
											} else {
												if (row.contentType != 'PPTX'
														&& row.contentType != 'PPT'
														&& row.contentType != 'PDF'
														&& (row.videoContentId == null || row.videoContentId == 0)) {
													contentTitle = '<a style="cursor: pointer" onclick="showContentNew(\''
															+ row.contentPath
															+ '\',\''
															+ row.contentType
															+ '\')">'
															+ row.content
															+ '</a>';
												} else if (row.contentType != 'PPTX'
														|| row.contentType != 'PPT'
														|| row.contentType == 'PDF'
														|| (row.videoContentId != null && row.videoContentId != 0)) {
													contentTitle = '<a class="cursor-pointer" href="viewVideoQuestion?contentId='
															+ row.contentId
															+ '">'
															+ row.content
															+ '</a>';
												}
											}
											return contentTitle;
										},
										"targets" : 0
									},
									{
										"render" : function(data, type, row) {
											var contentType = '';
											if (data == 1) {
												contentType = messages['lbl.public'];
											} else {
												contentType = messages['lbl.private'];
											}
											return contentType;
										},
										"targets" : 2
									},
									{
										"render" : function(data, type, row) {
											return data == null ? "" : data;
										},
										"targets" : 3
									},
									{
										"render" : function(data, type, row) {
											var isQuesAdded = '';
											if ((row.videoContentId == null || row.videoContentId == 0)
													&& (row.audioContentId == null || row.audioContentId == 0)) {
												isQuesAdded = messages['lbl.no']
											} else if ((row.videoContentId != null && row.videoContentId != 0)
													|| (row.audioContentId != null && row.audioContentId != 0)) {
												isQuesAdded = messages['lbl.yes']
											}
											return isQuesAdded;
										},
										"targets" : 4,
										"orderable" : false
									},
									{
										"render" : function(data, type, row) {
											var html = '';
											if ((row.videoContentId == null || row.videoContentId == 0)
													&& (row.audioContentId == null || row.audioContentId == 0)) {
											
												if (row.courseMapped != null) {
													html = html
													+ '<a style="cursor: pointer" onclick="showContentCantEdit(\''
													+ row.contentName
													+ '\')">'
													+ '<font color="#00A65A"><i class="fa fa-edit"></i></font>'
													+ '</a>&nbsp;&nbsp;';
													
													html = html
															+ '<a style="cursor: pointer" onclick="showMappedCourseContent(\''
															+ row.courseMapped
															+ '\',\''
															+ row.contentName
															+ '\')">'
															+ '<font color="#00A65A"><i class="fa fa-trash"></i></font>'
															+ '</a>'
															+ '<button class="btn btn-success" onclick="showMappedCourseContent(\''
															+ row.courseMapped
															+ '\',\''
															+ row.contentName
															+ '\')" >click me </button>';
												} else {
													html = html
													+ '<a style="cursor: pointer" onclick="edituploadedcontent('
													+ row.contentId
													+ ')">'
													+ '<font color="#00A65A"><i class="fa fa-edit"></i></font>'
													+ '</a>&nbsp;&nbsp;';
													
													html = html
															+ '<a style="cursor: pointer" onclick="showfun()">'
															+ '<font color="#00A65A"><i class="fa fa-trash" >gvfyttkgfv</i></font>'
															+ '</a>';
												}
											} else if ((row.videoContentId != null && row.videoContentId != 0)
													|| (row.audioContentId != null && row.audioContentId != 0)) {
												html = '&nbsp;';
											}
											return html;
										},
										"targets" : 6,
										"orderable" : false
									} ]
						});
	}

	/**
	 * @summary this function is used for cpnverting 3d array data into 2d array data of columns of data tables.
	 * @param data
	 * @returns no.
	 */
	 function showfun(){
		 debugger;
		 alert('hello');
		 $("#srtUploadTab").modal('show');
		 //$('#srtUploadTab').show();
	 }
	function planify(data) {
		for (var i = 0; i < data.columns.length; i++) {
			column = data.columns[i];
			column.searchRegex = column.search.regex;
			column.searchValue = column.search.value;
			delete (column.search);
		}
	}

	/**
	 * @summary This function is used getting absolute path from url.
	 */
	function getAbsolutePath() {
		var loc = window.location;
		var pathName = loc.pathname.substring(0,
				loc.pathname.lastIndexOf('/') + 1);
		return loc.href
				.substring(
						0,
						loc.href.length
								- ((loc.pathname + loc.search + loc.hash).length - pathName.length));
	}

	/**
	 * @summary This is used for receiving the post message.
	 * @param event
	 */
	function receive(event) {
		var data = event.data;
		if (typeof (window[data.func]) == "function") {
			window[data.func].call(null, data.params[0]);
		}
	}

	/**
	 * @summary This is used for getting token message from google oauth.
	 * @param token
	 */
	function alertMyMessage(token) {
		//console.log("token " + token)
		if (token != null && $.trim(token) != '') {
			googlePickerApiLoad(token);
		}
	}

	/**
	 * @summary This function is used calling google auth using iframe for getting token.
	 */
	function getGoogleOauthToken() {
		try {
			var absPath = getAbsolutePath();
			var originPath = absPath.replace(window.location.hostname,
					domainName);
			originPath = originPath
					+ "googledriveanddropboxservice/googleoauth?absPath="
					+ absPath;
			//console.log("originPath " + originPath);
			var iframeHtml = "<iframe src='"+originPath+"'></iframe>";
			$("#google_oauth_iframe").empty();
			$("#google_oauth_iframe").html(iframeHtml);
		} catch (err) {
			console.log(err.message);
		}
	}
</script>
</html>