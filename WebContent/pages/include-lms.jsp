<!-- Page containing the common tag libs required, this page to be included by all other JSP pages. -->
<!-- Bootstrap 3.3.5 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.qbis.common.ConstantUtil"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%-- <link rel="shortcut icon" type="image/png"
	href="<spring:url value='/resources/images/qbisfavicon.png' />"> --%>
<title>eLuminate</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/bootstrap/css/bootstrap-tour.min.css'/>">
<link rel="stylesheet"	href="<spring:url value='/resources/adminlte/bootstrap/css/stylehome.css'/>">	
<!-- Font Awesome -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/font-awesome-4.4.0/css/font-awesome.min.css'/>">
<!-- Ionicons -->
<!-- <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"> -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/ionicons-2.0.1/css/ionicons.min.css'/>">
<!-- DataTables -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/datatables/dataTables.bootstrap.css'/>">
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/skins/_all-skins.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/skins/skin-blue.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/iCheck/flat/blue.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/toggle/lib/ToggleSwitch.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/addTest.css'/>">
<!-- Select2 -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/select2/select2.min.css'/>">
<!-- Theme style -->
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/dist/css/AdminLTE.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/iCheck/all.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/common.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/adminlte/plugins/daterangepicker/daterangepicker.css'/>">
	
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery 2.1.4 -->
<link href="resources/adminlte/plugins/intro-js/introjs.css" rel="stylesheet">
<script
	src="<spring:url value='/resources/adminlte/plugins/jQuery/jQuery-2.1.4.min.js'/>"></script>
<script
	src="<spring:url value='/resources/adminlte/plugins/jQueryUI/jquery-ui.js'/>"></script>
<!-- Bootstrap 3.3.5 -->
<script
	src="resources/adminlte/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script
	src="<spring:url value='/resources/adminlte/plugins/datatables/jquery.dataTables.min.js'/>"></script>
<script
	src="<spring:url value='/resources/adminlte/plugins/datatables/dataTables.bootstrap.min.js'/>"></script>
<!-- date-range-picker -->
<script
	src="<spring:url value='/resources/adminlte/plugins/daterangepicker/moment.min.js'/>"></script>
<script
	src="<spring:url value='/resources/adminlte/plugins/daterangepicker/daterangepicker.js'/>"></script>
<!-- SlimScroll -->
<script
	src="<spring:url value='/resources/adminlte/plugins/slimScroll/jquery.slimscroll.min.js'/>"></script>
<!-- FastClick -->
<script
	src="<spring:url value='/resources/adminlte/plugins/fastclick/fastclick.min.js'/>"></script>
<!-- AdminLTE App -->
<script
	src="<spring:url value='/resources/adminlte/dist/js/app.min.js'/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<spring:url value='/resources/adminlte/dist/js/demo.js'/>"></script>
<!-- iCheck -->
<script
	src="<spring:url value='/resources/adminlte/plugins/iCheck/icheck.min.js'/>"></script>
<script src="<spring:url value='/resources/js/imgcentering.js'/>"></script>
<!-- website tour js -->
<script
	src="<spring:url value='/resources/adminlte/bootstrap/js/bootstrap-tour.min.js'/>"></script>
<!-- angular 1.5.8 js library -->
<script src="<spring:url value='/resources/js/angular.min.js'/>"></script>
<!-- Lazy Image -->
<script type="text/javascript" src="resources/adminlte/plugins/jquery-lazy/jquery.lazy.min.js"></script>
<script type="text/javascript" src="resources/adminlte/plugins/jquery-lazy/jquery.lazy.plugins.min.js"></script>
<style>
p {
	word-break: break-all;
}
.example-modal .modal {
	position: relative;
	top: auto;
	bottom: auto;
	right: auto;
	left: auto;
	display: block;
	z-index: 1;
}

.example-modal .modal {
	background: transparent !important;
}

.numberCircle {
	border-radius: 50%;
	width: 36px;
	height: 36px;
	padding: 6px;
	background: #fff;
	border: 1px solid #666;
	color: #666;
	text-align: center;
	font: 12px Arial, sans-serif;
	margin-right: 20px;
}

@font-face {
	font-family: SFUIDisplay-Light;
	src: url('resources/fonts/SF-UI-Display-Light.otf');
}

@font-face {
	font-family: SFUIDisplay-Regular;
	src: url('resources/fonts/SF-UI-Display-Regular.otf');
}

@font-face {
	font-family: SFUIDisplay-Semibold;
	src: url('resources/fonts/SF-UI-Display-Semibold.otf');
}

label {
	/* font-family: SFUIDisplay-Light !important;
color:#7d8181 !important; */
	
}
.wrapper {
min-height:750px !important;
}
</style>

<style>
.sidebar-menu>li.active>a {
	color: #000;
	background: #f4f4f5;
	border-left: 3px solid #00B06C;
}

.sidecontent {
	color: #7d8181;
	font-size: 15px;
}

.form-control {
	border-radius: 0;
	box-shadow: none;
	border-color: #d0d0d0;
}

.skin-black-light .wrapper, .skin-black-light .main-sidebar,
	.skin-black-light .left-side {
	background-color: #f9fafc;
	border: 1px solid #d0d0d0;
	margin-top: 15px;
}

.capitalize {
	text-transform: capitalize;
}

</style>

<style>
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	background-color: #00A65A;
}

.select2-container--default .select2-selection--multiple .select2-selection__choice
	{
	background-color: #00A65A;
	border-radius: 0px;
}

.wrapper {
	margin-top: 0px !important;
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


@font-face {
	font-family: 'Century Gothic';
	src: url('resources/fonts/CenturyGothic.ttf');
}
h1, h2, h3, h4, h5,h6,div,select,input, p, button ,label, textarea,ul ,span, a{
	font-family: 'Century Gothic' !important;
}
</style>
<script type="text/javascript">
        var paggingmessages = new Array();
        paggingmessages['lbl.first'] = "<spring:message code='lbl.first' text='First' javaScriptEscape='true' />";
        paggingmessages['lbl.previous'] = "<spring:message code='lbl.previous' text='Previous' javaScriptEscape='true' />";
        paggingmessages['lbl.next'] = "<spring:message code='lbl.next' text='Next' javaScriptEscape='true' />";
        paggingmessages['lbl.last'] = "<spring:message code='lbl.last' text='Last' javaScriptEscape='true' />";
        paggingmessages['lbl.paginationentrymsg'] = "<spring:message code='lbl.paginationentrymsg' text='Showing #firstentry to #totalcurrectpageentry of #totalentry entries' arguments='#firstentry;#totalcurrectpageentry;#totalentry' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        
        paggingmessages['lbl.datatableemptytable'] = "<spring:message code='lbl.datatableemptytable' text='No data available in table' javaScriptEscape='true' />";
        paggingmessages['lbl.datatableinfo'] = "<spring:message code='lbl.datatableinfo' arguments='_START_;_END_;_TOTAL_;' text='Showing _START_ to _END_ of _TOTAL_ entries' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';'/>";
        paggingmessages['lbl.datatableinfoempty'] = "<spring:message code='lbl.datatableinfoempty' text='Showing 0 to 0 of 0 entries' javaScriptEscape='true' />";
        paggingmessages['lbl.datatableinfofiltered'] = "<spring:message code='lbl.datatableinfofiltered' arguments='_MAX_' text='(filtered from _MAX_ total entries)' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        paggingmessages['lbl.datatablelengthmenu'] = "<spring:message code='lbl.datatablelengthmenu' arguments='_MENU_' text='Show _MENU_ entries' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        paggingmessages['lbl.datatableloadingrecords'] = "<spring:message code='lbl.datatableloadingrecords' text='Loading...' javaScriptEscape='true' />";
        paggingmessages['lbl.datatableprocessing'] = "<spring:message code='lbl.datatableprocessing' text='Processing...' javaScriptEscape='true' />";
        paggingmessages['lbl.datatablesearch'] = "<spring:message code='lbl.datatablesearch' text='Search:' javaScriptEscape='true' />";
        paggingmessages['lbl.datatablezerorecords'] = "<spring:message code='lbl.datatablezerorecords' text='No matching records found' javaScriptEscape='true' />";
        paggingmessages['lbl.sunday'] = "<spring:message code='lbl.sunday' text='Su' javaScriptEscape='true' />";
        paggingmessages['lbl.monday'] = "<spring:message code='lbl.monday' text='Mo' javaScriptEscape='true' />";
        paggingmessages['lbl.tuesday'] = "<spring:message code='lbl.tuesday' text='Tu' javaScriptEscape='true' />";
        paggingmessages['lbl.wednesday'] = "<spring:message code='lbl.wednesday' text='We' javaScriptEscape='true' />";
        paggingmessages['lbl.thursday'] = "<spring:message code='lbl.thursday' text='Th' javaScriptEscape='true' />";
        paggingmessages['lbl.friday'] = "<spring:message code='lbl.friday' text='Fr' javaScriptEscape='true' />";
        paggingmessages['lbl.saturday'] = "<spring:message code='lbl.saturday' text='Sa' javaScriptEscape='true' />";
        paggingmessages['lbl.jan']="<spring:message code='lbl.jan' text='Jan' javaScriptEscape='true'/>";
        paggingmessages['lbl.feb']="<spring:message code='lbl.feb' text='Feb' javaScriptEscape='true'/>";
        paggingmessages['lbl.mar']="<spring:message code='lbl.mar' text='Mar' javaScriptEscape='true'/>";
        paggingmessages['lbl.apr']="<spring:message code='lbl.apr' text='Apr' javaScriptEscape='true'/>";
        paggingmessages['lbl.may']="<spring:message code='lbl.may' text='May' javaScriptEscape='true'/>";
        paggingmessages['lbl.jun']="<spring:message code='lbl.jun' text='Jun' javaScriptEscape='true'/>";
        paggingmessages['lbl.jul']="<spring:message code='lbl.jul' text='Jul' javaScriptEscape='true'/>";
        paggingmessages['lbl.aug']="<spring:message code='lbl.aug' text='Aug' javaScriptEscape='true'/>";
        paggingmessages['lbl.sep']="<spring:message code='lbl.sep' text='Sep' javaScriptEscape='true'/>";
        paggingmessages['lbl.oct']="<spring:message code='lbl.oct' text='Oct' javaScriptEscape='true'/>";
        paggingmessages['lbl.nov']="<spring:message code='lbl.nov' text='Nov' javaScriptEscape='true'/>";
        paggingmessages['lbl.dec']="<spring:message code='lbl.dec' text='Dec' javaScriptEscape='true'/>";
        paggingmessages['lbl.apply'] = "<spring:message code='lbl.apply' text='Apply' javaScriptEscape='true' />";
        paggingmessages['lbl.cancel'] = "<spring:message code='lbl.cancel' text='Cancel' javaScriptEscape='true' />";
        paggingmessages['lbl.to'] = "<spring:message code='lbl.to' text='To' javaScriptEscape='true' />";
        paggingmessages['lbl.from'] = "<spring:message code='lbl.from' text='From' javaScriptEscape='true' />";
        
        var uploadcontentmessages = new Array();
        uploadcontentmessages['lbl.name'] = "<spring:message code='lbl.name' text='Name' javaScriptEscape='true' />";
        uploadcontentmessages['lbl.title'] = "<spring:message code='lbl.title' text='Title' javaScriptEscape='true' />";
        uploadcontentmessages['lbl.entercontenttitle'] = "<spring:message code='lbl.entercontenttitle' text='Enter content title' javaScriptEscape='true' />";
        uploadcontentmessages['msg.maximumfilelimit'] = "<spring:message code='msg.maximumfilelimit' arguments='#maxfiles' text='Maximum #maxfiles files can be uploaded.' javaScriptEscape='true'  htmlEscape='false' argumentSeparator=';' />";
        uploadcontentmessages['msg.filesizeexceed'] = "<spring:message code='msg.filesizeexceed' arguments='#filename' text='#filename file having size larger than 10MB.' javaScriptEscape='true'  htmlEscape='false' argumentSeparator=';'/>";
        uploadcontentmessages['msg.filehavingotherspecifiedtype'] = "<spring:message code='msg.filehavingotherspecifiedtype' arguments='#filename' text='#filename file having type other than specified types.' javaScriptEscape='true'  htmlEscape='false' argumentSeparator=';'/>";
        uploadcontentmessages['msg.atleastacontentforupload'] = "<spring:message code='msg.atleastacontentforupload' text='Select atleast a content for upload.' javaScriptEscape='true' />";
        uploadcontentmessages['msg.remainingspacefororg'] = "<spring:message code='msg.remainingspacefororg' arguments='#space' text='Now the remaining space for your organization is #space mb.' javaScriptEscape='true'  htmlEscape='false' argumentSeparator=';' />";
        uploadcontentmessages['msg.maxcharacterlength'] = "<spring:message code='msg.maxcharacterlength' arguments='#maxlength' text='Maximum #maxlength characters allowed.' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
        uploadcontentmessages['placeholder.promotionalvideo'] = "<spring:message code='placeholder.promotionalvideo' text='Add link from youtube' javaScriptEscape='true' />";
        uploadcontentmessages['lbl.contenturlinorder'] = "<spring:message code='lbl.contenturlinorder' text='Content URL #ordernumber' arguments='#ordernumber' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
        uploadcontentmessages['lbl.entertitle'] = "<spring:message code='lbl.entertitle' text='Enter Title' javaScriptEscape='true' />";
        uploadcontentmessages['msg.contentmandatory'] = "<spring:message code='msg.contentmandatory' text='Content #ordernumber is mandatory.' arguments='#ordernumber' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
        uploadcontentmessages['msg.contenturlinvalid'] = "<spring:message code='msg.contenturlinvalid' text='Content URL #ordernumber is invalid.' arguments='#ordernumber' htmlEscape='false' javaScriptEscape='true' argumentSeparator=';'/>";
        uploadcontentmessages['msg.selectcontentforupload'] = "<spring:message code='msg.selectcontentforupload' text='Select a content for upload.' javaScriptEscape='true' />";
        uploadcontentmessages['msg.empty'] = "<spring:message code='msg.empty' text='This field is required.' javaScriptEscape='true' />";
        uploadcontentmessages['msg.contentinusecannotdelete'] = "<spring:message code='msg.contentinusecannotdelete' text='#contentname is in use. Please remove content from following courses #course to delete permanently from Content Library.' arguments='#contentname;#course' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        uploadcontentmessages['msg.contenturlisrequired']="<spring:message code='msg.contenturlisrequired' text='Content URL is mandatory.' javaScriptEscape='true' />";
        uploadcontentmessages['msg.invalidcontenturl']="<spring:message code='msg.invalidcontenturl' text='Invalid content URL.' javaScriptEscape='true' />";
</script>   
<!-- data table language json -->
<script
	src="<spring:url value='/resources/js/datatablelanguageinfo.js'/>"></script>             
<!-- This is used for defining the question type -->
<script>
/**
 * @summary all question' type
 */
    var MULTIPLE_CHOICE_TYPE;
    var SINGLE_CHOICE_TYPE;
    var SORT_LIST_TYPE;
    var CHOICE_MATRIX_TYPE;
    var CLASSIFICATION_TYPE;
    var MATCH_LIST;
$(document).ready(function(){
	MULTIPLE_CHOICE_TYPE = <%=ConstantUtil.MULTIPLE_CHOICE_TYPE %>;
	SINGLE_CHOICE_TYPE = <%=ConstantUtil.SINGLE_CHOICE_TYPE %>;
	SORT_LIST_TYPE = <%=ConstantUtil.SORT_LIST_TYPE %>;
	CHOICE_MATRIX_TYPE = <%=ConstantUtil.CHOICE_MATRIX_TYPE %>;
	CLASSIFICATION_TYPE = <%=ConstantUtil.CLASSIFICATION_TYPE %>;
	MATCH_LIST = <%=ConstantUtil.MATCH_LIST%>;
});
</script>
