<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/custom.css'/>">
<style>
.content-wrapper {
	margin: auto;
	margin-left: 230px;
}

@media only screen and (min-width: 767px) {
	.content-wrapper {
		/* background-color: yellow; */
		customersCtrl margin-left: 230px;
	}
}

@media only screen and (max-width: 760px) {
	.content-wrapper {
		/*  background-color: pink; */
		margin-left: 0px;
	}
}

.lrn h4, .lrn .h4, .lrn h5, .lrn .h5, .lrn h6, .lrn .h6 {
    font-weight: 300;
}


[class^="icon-"], [class*=" icon-"] {
    speak: none;
    font-style: normal;
    font-weight: normal;
    font-variant: normal;
    text-transform: none;
    /* line-height: 1; */
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;

}
[class^="icon-"]:before, [class*=" icon-"]:before {
    position: relative;
}










.lrn {
    color: #4d4d4d;
}

.lrn {
    line-height: 1.4em;
    font-size: 14px;
}

.lrn .lrn-tiles-list-wrapper {
    display: table-cell;
    width: 10000px;
}
.lrn *, .lrn *:before, .lrn *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

.lrn .ui:after, .lrn .lrn-sect-header:after, .lrn .lrn-tiles-list:after, .lrn .lrn-form-group-wrapper:after, .lrn .lrn-form-control-wrapper .lrn-form-el-removable:after, .lrn .lrn-form-header:after, .lrn .lrn-fieldset-group:after, .lrn .lrn-form-control-ckeditor .cke_textarea_inline:after {
    display: table;
    content: "";
    clear: both;
}

.lrn .lrn-tiles-list {
    padding: 0;
    list-style: none;
    margin-left: -12px;
    margin-right: -12px;
}

@media (min-width: 1050px) {
.lrn .lrn-tiles-list > li {
    float: left;
    width: 25%;
}
}

/* @media (min-width: 840px){
.lrn .lrn-tiles-list > li {
    float: left;
    width: 33.33333%;
}
} */

/* @media (min-width: 620px){
.lrn .lrn-tiles-list > li {
    float: left;
    width: 50%;
}
} */

.lrn .lrn-tiles-list > li {
    cursor: pointer;
    padding-left: 12px;
    padding-right: 12px;
}

/* .lrn ul, .lrn ol {
    list-style-position: outside;
    list-style-type: disc;
}
.lrn ul, .lrn ol, .lrn dl {
    margin: 0 0 1em;
    padding-left: 2.8571428571em;
}
.lrn ul, .lrn ol {
    margin-top: 0;
    margin-bottom: 10px;
} */
.lrn *, .lrn *:before, .lrn *:after {
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

.lrn .lrn-tiles-list .lrn-tile-preview {
    position: relative;
    height: 150px;
}

.lrn .thumbnail {
    display: block;
    margin-bottom: 20px;
}

.lrn .img-thumbnail, .lrn .thumbnail {
    padding: 4px;
    line-height: 1.428571429;
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 4px;
    -webkit-transition: all 0.2s ease-in-out;
    transition: all 0.2s ease-in-out;
    display: inline-block;
    max-width: 100%;
    height: auto;
}

.lrn .lrn-tiles-list {
    list-style: none;
}

.lrn a {
    background: transparent;
}

.lrn .lrn-tiles-list .lrn-tile-preview .caption {
    background-color: rgba(0, 0, 0, 0.83);
    position: absolute;
    bottom: 0;
    left: -1px;
    right: -1px;
    border-radius: 0 0 4px 4px;
}

.lrn .thumbnail .caption {
    padding: 9px;
    color: #333333;
}

.lrn .img-thumbnail, .lrn .thumbnail {
    line-height: 1.428571429;
    }
    
    .lrn .lrn-tiles-list .lrn-tile-preview > img {
    max-height: 100%;
}
.lrn .thumbnail > img {
    display: block;
    max-width: 100%;
    height: auto;
    margin-left: auto;
    margin-right: auto;
}
.lrn img {
    max-width: 100%;
    height: auto;
}
.lrn img {
    vertical-align: middle;
}
.lrn img {
    border: 0;
}

.lrn .lrn-tiles-list .lrn-tile-preview .caption p {
    margin: 0;
    height: 0;
    opacity: 0;
    font-size: 0.85714em;
    color: #888888;
}

.lrn .lrn-tiles-list .lrn-tile-heading {
    overflow: hidden;
    margin: 0 0 5px;
    line-height: 1.2;
    font-size: 1em;
    color: #cccccc;
    text-overflow: ellipsis;
    white-space: nowrap;
}



.lrn .lrn-tiles-list .lrn-tile-preview .icon-info {
    display: none;
    right: 10px;
    position: absolute;
    top: 10px;
    font-size: 1em;
    z-index: 2000;
    background-color:#fff;
    color:black;
    width:15px;
    height:12px;
    padding:3px;
    padding-left: 5px;
    border-radius:3px;
    line-height: .4;
}


.lrn .lrn-tiles-list .lrn-tile-preview:hover .icon-info {
    display: block;
}


.lrn .lrn-tiles-list .lrn-tile-preview:hover .caption h4 {
    color: #fff;
}

.lrn .lrn-tiles-list .lrn-tile-preview:hover .lrn-tile-heading {
    padding-right: 15px;
}
.lrn .lrn-tiles-list .lrn-tile-preview:hover .icon-info {
    display: block;
}

.lrn .lrn-tiles-list .lrn-tile-preview:hover .caption p {
    opacity: 1;
    height: 100%;
}

.icon-info:before {
    content: "?";
        font-size: x-small;
}
  
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<section class="content-header">
					<h1>
						<spring:message code="lbl.choosequestiontype" />
					</h1>
				</section>
				<section class="content lrn">


					<div id="tiles-questions-outlet" class="lrn-tiles-list-wrapper">
						<div>
							<ul class="lrn-tiles-list thumbnails">
						  <c:forEach items="${quesTypeList}" var="quesTypeList">
						  <c:set var="questiontypeimageurl"></c:set>
						  <c:set var="questiontypedesc"></c:set>
						  
						  <c:if test="${quesTypeList.questionType == 1}">
						  <c:set var="questiontypeimageurl" value="multichoicequestiontype.png"></c:set>
						  <c:set var="questiontypedesc" value="Multiple Choice Question with multiple responses"></c:set>
						  </c:if>
						  
						  <c:if test="${quesTypeList.questionType == 2}">
						  <c:set var="questiontypeimageurl" value="singlechoicequestiontype.png"></c:set>
						  <c:set var="questiontypedesc" value="Standard Single Choice Question"></c:set>
						  </c:if>
						  
						  <c:if test="${quesTypeList.questionType == 3}">
						  <c:set var="questiontypeimageurl" value="sortlistquestiontype.png"></c:set>
						  <c:set var="questiontypedesc" value="Sort list by dragging items to the Target area into the correct order"></c:set>
						  </c:if>
						  
						   <c:if test="${quesTypeList.questionType == 4}">
						  <c:set var="questiontypeimageurl" value="choicematrixquestiontype.png"></c:set>
						  <c:set var="questiontypedesc" value="Choice Matrix with given option columns. Table format"></c:set>
						  </c:if>
						  
						  <c:if test="${quesTypeList.questionType == 5}">
						  <c:set var="questiontypeimageurl" value="classificationquestiontype.png"></c:set>
						  <c:set var="questiontypedesc" value="Drag and drop responses into grid with given columns and given rows"></c:set>
						  </c:if>
						  
						   <c:if test="${quesTypeList.questionType == 6}">
						  <c:set var="questiontypeimageurl" value="matchlistquestiontype.png"></c:set>
						  <c:set var="questiontypedesc" value="Drag and Drop responses to match a predefined list"></c:set>
						  </c:if>
						  
						   <li onclick="location.href='addeditquestion?type=${quesTypeList.questionType}'"><div class="questionTypePreview lrn-tile-preview thumbnail">
										<img
											src="resources/images/questiontypes/${questiontypeimageurl}">
										<div class="caption" id="subdomain">
											<h4 class="lrn-tile-heading"
												title="Multiple Choice – Standard">${quesTypeList.questionTypeName}</h4>
											<a href="javascript:void(0)"
												rel="tooltip " title="Documentation" target="_blank"><i
												class="icon-info helpIcon"></i></a>
											<p>${questiontypedesc}</p>
										</div>
							 </div>
						</li>
						   
						   </c:forEach>
							</ul>
						</div>
					</div>

				</section>
			</div>
		</div>
		<!-- content-wrapper -->
		<!-- End dataTable----->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
</body>
<script>
	$(document).ready(function() {
		$(".treeview").removeClass("active");
		$("#questionList").addClass("active");
		$("#questionList .treeview-menu > #questionList")
				.addClass("active");
	});
</script>
</html>