<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>

.content-wrapper {
margin: auto;
margin-left:230px;
}

iframe body{
text-align: center;
}

@media only screen and (min-width: 767px) {
    .content-wrapper {
        /* background-color: yellow; */
        margin-left:230px;
    }
}
@media only screen and (max-width: 760px) {
  .content-wrapper {
       /*  background-color: pink; */
        margin-left:0px;
    }
}  
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
<c:set var="type" value="pdf"></c:set>
	<div class="wrapper">
	<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy"
				src="<spring:url value='/resources/images/loading.gif'/>"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<div class="col-sm-12" >		
		<div class="content-wrapper">
			<section class="content-header">
			<div class="pull-left"><h3 style="margin:0">
			<c:if test="${content.contentId!=null}">
			  <spring:message code="lbl.edituploadedcontent" text="Edit Uploaded Content"/>
			</c:if>
			<c:if test="${content.contentId==null}">
			  <spring:message code="lbl.uploadnewcontent" text="Uploaded New Content"/>
			</c:if>
			</h3></div>			
			<br/>			
			</section>
			<section class="content">
			<div class="row">			 
				<div class="col-md-12">				
					<div class="box no-border col-md-12">
						<!-- /.box-header -->
						<div class="box-body no-padding">
				  <form id="form" action="saveuploadedcontent" name="uploadcontentform" method="post" enctype="multipart/form-data">
                  <div class="box-body">
                    <div class="form-group">
                      <label><spring:message code="lbl.title" text="Title"/></label>
                      <input type="text" placeholder="<spring:message code="lbl.entertitle" text="Enter Title"/>" id="contentName" name="contentName" value="${content.contentName}" class="form-control">
                    </div>
                    <c:if test="${content.isExternalURL==0}">
                    <div class="form-group">
               		  <label><spring:message code="lbl.uploadcontent" text="Upload Content"/></label>
                       <c:if test="${content.contentId!=null}">
                        &nbsp;&nbsp;<span id="spancontent">${content.content}</span>
                      </c:if>
                      <input type="file" id="filepath" name="filepath">
                       <input type="hidden" id="isExternalURL" name="isExternalURL" value="0">                                          
                       <p class="help-block">
                        <spring:message code="lbl.note" text="Note"/> : <spring:message code="msg.canuploadspecifiedfiletype" text="You can upload file of specific type(image,pdf,ppt,video)."/>
                       </p>                                            
                    </div>
                    </c:if>
                    <c:if test="${content.isExternalURL==1}">
                    <div class="form-group">
                      <label><spring:message code="lbl.embedurl" text="Embed URL"/></label>                       
                      <input type="text" id="content" name="content" value="${content.content}"
                        class="form-control">
                      <input type="hidden" id="isExternalURL" name="isExternalURL" value="1">
                    </div>
                    </c:if>
                    <div class="form-group">
                      <label><spring:message code="lbl.description" text="Description"/></label>
                      <textarea  placeholder="<spring:message code="lbl.enterdescription" text="Enter Description"/>" id="description"
                       name="description"  class="form-control textAreaControl" maxlength="1024">${content.description}</textarea>                       
                      
                    </div>
                    <div class="form-group">
                      <c:if test="${content.contentType=='IMAGE'}">
                        <div class="">
                          <img src="${content.contentPath}"
								onerror="this.src='<spring:url value='/resources/images/text.png'/>'" alt="..."
								style="width: 300px; height: 200px;">
                        </div>
                      </c:if>
                      <c:if test="${content.contentType=='PDF'}">
                        <div class="">
                        	<c:set var="type" value="pdf"/>
                          <img src="<spring:url value='/resources/images/c-pdf.png'/>">
                          <a href="javascript:void(0)" onclick="showContentNew('${content.contentPath}','pdf')"><spring:message code="lbl.clickforview" text="click for view"/></a>
                            <%-- <c:set var="pdfsrc" value="${fn:split(content.contentPath, '/')}" /> --%>
                            <input type="hidden" id="contentUrl" name="contentUrl" value="https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc=${content.contentPath}">
                            <%-- <input type="hidden" id="contentUrl" name="contentUrl" value="${content.contentPath}"> --%>
							<input type="hidden" id="contentPages" name="contentPages" value="${content.numPages}">
							<input type="hidden" id="time" name="time" value="1">
                        </div>
                      </c:if>
                      <c:if test="${content.contentType=='PPT'}">
                        <div class="">
                        	<c:set var="type" value="png"/>
                          <img src="<spring:url value='/resources/images/c-ppt.png'/>" >
                          <%-- <a href="#" onclick="viewcontent('${content.contentPath}')"><spring:message code="lbl.clickforview" text="click for view"/></a> --%>
                          <a href="javascript:void(0)" onclick="showContentNew('${content.contentPath}','png')"><spring:message code="lbl.clickforview" text="click for view"/></a>
                          <input type="hidden" id="contentUrl" name="contentUrl" value="${content.contentPath}">
							<input type="hidden" id="contentPages" name="contentPages" value="${content.numPages}">
							<input type="hidden" id="time" name="time" value="1">
                        </div>
                      </c:if>
                      <c:if test="${content.contentType=='VIDEO' && content.isExternalURL==0}">
                        <div class="">
                          <iframe src="${content.contentPath}" style="width: 300px; height: 200px;"></iframe>
                        </div>
                      </c:if>
                      <c:if test="${content.isExternalURL==1}">
                      
					<div id="promoVideo"></div>																
						
                      </c:if>
                      
                    </div>
                    <div class="checkbox icheck">
                      <label>
                        <c:if test="${content.visiblity==null}">
                          <input type="checkbox" id="visiblity" name="visiblity" value="1"> <spring:message code="lbl.allowsharemedia" text="Allow Share media"/>
                        </c:if>
                        <c:if test="${content.visiblity!=null}">
                          <input type="checkbox" id="visiblity" checked="checked" name="visiblity" value="1"> 
                          <spring:message code="lbl.allowsharemedia" text="Allow Share media"/>
                        </c:if>
                        
                      </label>
                    </div>
                  </div><!-- /.box-body -->
                   <input type="hidden" id="contentId" name="contentId" value="${content.contentId}">
                   <c:if test="${content.isExternalURL==0}">
                        <input type="hidden" id="content" name="content" value="${content.content}">
                      </c:if>                   
                  <div class="box-footer">
                    <button class="btn btn-default btn-flat button-width-large" id="cancel" type="button"><spring:message code="lbl.cancel" text="Cancel"/></button>
                    <button class="btn btn-success btn-flat button-width-large pull-right" id="savebtn" type="button"><spring:message code="lbl.submit" text="Submit"/></button>
                  </div>
                </form>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /. box -->
				</div>
				<!-- /.col -->
			</div>
			</section>
		</div>
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	</div>	
	<div class="modal" id="showContentid" role="dialog" aria-labelledby="showContent">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content ">
				<div class="modal-body page-background-color">
					<div class="row">
						<div class="col-xs-12" style="text-align: center" id="frame">
						</div>
						<div class="col-xs-12" style="min-height: 30px">
							<div style="float: left; width: 30%; padding: 2%;">
                          		<button disabled="disabled" type="button" id="previous" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCall(0,'${type}');"><span><i class="glyphicon glyphicon-chevron-left"></i>&nbsp;<spring:message code="lbl.previous" text="Previous"/></span></button>
                          	</div>
                          	<div style="float: left; width: 40%; padding: 2%; text-align: center;">
                          	   <spring:message code="lbl.showingpage" text="Showing <span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span> of ${content.numPages}"  arguments="<span id='pageTextCount' class='btn' style='padding: 6px 0px;'>1</span>^${content.numPages}" htmlEscape="false" argumentSeparator="^"/>
                          	</div>
                          	<div style="float: right; width: 30%; padding: 2%;">
                          	<c:if test="${content.numPages == 1}">
                          	  <button disabled="disabled" type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCall(1,'${type}');"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button>
                          	</c:if>
                          	<c:if test="${content.numPages > 1}">
                          	  <button type="button" style="float: right;" id="next" class="margin-left btn btn-flat btn-success button-width-large" onclick="pageCall(1,'${type}');"><span><spring:message code="lbl.next" text="Next"/>&nbsp;<i class="glyphicon glyphicon-chevron-right"></i></span></button> 
                          	</c:if>
                          	</div>
						</div>
						<div class="col-xs-12" style="min-height: 30px;padding-right:3.3%">
							<button type="button" onclick="closeModalnew()"
								class="btn btn-success btn-flat  button-width-large pull-right"
								style="margin: auto" data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</body>
<script type="text/javascript">
       var messages = new Array();
       /**
        * multichoicetypequestion.js
        */
        messages['lbl.answernumber'] = "<spring:message code='lbl.answernumber' text='Answer No.' javaScriptEscape='true' />";
        messages['lbl.removeanswer'] = "<spring:message code='lbl.removeanswer' text='Remove Answer' javaScriptEscape='true' />";
        messages['msg.atleastonequestionisrequired'] = "<spring:message code='msg.atleastonequestionisrequired' text='Please create at least one question.' javaScriptEscape='true' />";
        messages['lbl.questiontime'] = "<spring:message code='lbl.questiontime' text='Question Time' javaScriptEscape='true' />";
        messages['lbl.questionpage'] = "<spring:message code='lbl.questionpage' text='Question Page' javaScriptEscape='true' />";
        messages['lbl.questionat'] = "<spring:message code='lbl.questionat' arguments='#time' text='Question at #time' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['lbl.questionatpage'] = "<spring:message code='lbl.questionatpage' arguments='#time' text='Question at page #time' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';' />";
        messages['lbl.questiontype'] = "<spring:message code='lbl.questiontype' text='Question Type' javaScriptEscape='true' />";
        messages['lbl.answers'] = "<spring:message code='lbl.answers' text='Answers' javaScriptEscape='true' />";
        messages['lbl.question'] = "<spring:message code='lbl.question' text='Question' javaScriptEscape='true' />";
       </script> 
<script src="<spring:url value='/resources/js/youtubeplayer.js?v=1'/>"></script>
<script src="resources/js/questionlibrary/build/multichoicetypequestion.js?v=3"></script>
<script>
      $(function () {
    	  $(".treeview").removeClass("active");
	      $("#uploadcontent").addClass("active");
	      $("#uploadcontent .treeview-menu > #uploadcontent").addClass("active");
	      
	      $('input').iCheck({
	          checkboxClass: 'icheckbox_square-green',
	          radioClass: 'iradio_square-green',
	          increaseArea: '20%' // optional
	        });
	      
	      var isurl = '${content.isExternalURL}';	      
	      if(isurl==1){
	    	  playVideo('${content.content}');
	      }
	      
	      $("#savebtn").click(function() {
	    	  
	    	  var flag = validateEditUploadContent();	    	  
	    	  if(flag==0){
	    		  $("#overlay").show();
	    		  $("#form").submit();  
	    	  }				
			})
			
			$("#cancel").click(function(){
				location.href="listuploadcontent";
			});
          });
      
      var viewcontent=function(path){
    	  //location.href="http://docs.google.com/viewerng/viewer?url="+path;
    	  window.open("http://docs.google.com/viewerng/viewer?url="+path);
      }
      
      var viewcontentpdf=function(path){    	 
    	  window.open(path);
      }
      
      /* Function for validate content upload available space */
      var validateContentAvailableSpace = function(){
    	  var inp = document.getElementById('filepath');
    	  var size = inp.files[0].size;
    	  var k = 0;
    	  $.ajax({
				url : "validateContentAvailableSpace?fileSize="+size,
				type : 'GET',
				async : false,		
				error : (function() {
					alert("server error");
				}),
				success : function(res) {					
					if(res.status){
						k = 0;
					}else{
						k = 1;
						$('.help-block').html('');        		 
	            		$('.help-block').append("<span class='text-red'>"+uploadcontentmessages['msg.remainingspacefororg'].replace('#space',response.remainSpaceMB)+"</span>");
					}
				}
	      });
    	  
    	  return k;
      }
      
      
     /* function for validate details of upload content at edit */ 
     var validateEditUploadContent=function(){
    	 
    	  $("#contentName").css({"border-color" : ""});	
 		  $("#contentName").next('span').remove();
 		  $("#description").css({"border-color" : ""});	
 		  $("#description").next('span').remove();
 		  $("#content").css({"border-color" : ""});	
 		  $("#content").next('span').remove();
 		  $("#filepath").css({"border-color" : ""});	
		  $("#filepath").next('span').remove();
    	 
    	 var flag = 0;
   	  
		  if($("#contentName").val()==""){
   		  $('#contentName').css({"border-color" : "red"});
				$('#contentName').after("<span class='text-red'>"+uploadcontentmessages['msg.empty']+"</span>");
				flag = 1;
   	      }
		  
		  if($("#contentName").val().length>50){
   		      $('#contentName').css({"border-color" : "red"});
			  $('#contentName').after("<span class='text-red'>"+uploadcontentmessages['msg.maxcharacterlength'].replace('#maxlength',50)+"</span>");
			  flag = 1;
   	      }
		  
		  if($("#description").val()!="" && $("#description").val().length>2000){
   		  $('#description').css({"border-color" : "red"});
			  $('#description').after("<span class='text-red'>"+uploadcontentmessages['msg.maxcharacterlength'].replace('#maxlength',2000)+"</span>");
			  flag = 1;
   	      }
		  if($("#filepath").val()!="" && $("#isExternalURL").val()!=1){
			  if(validateFileTypeAndSize()){
				  var k = validateContentAvailableSpace();
				  if(k==1){
					  flag = 1;
				  }
			  }else{
				  flag = 1; 
			  }
			    
		  }		  	    		  
		  
		  if($("#isExternalURL").val()==1){
			  if($("#content").val()==""){
				  $('#content').css({"border-color" : "red"});
				  $('#content').after("<span class='text-red'>"+uploadcontentmessages['msg.contenturlisrequired']+"</span>");
				  flag = 1;
			  }else{
				  var s = checkPromoURL($("#content").val());
				  if(s!=true){
					  $('#content').css({"border-color" : "red"});
					  $('#content').after("<span class='text-red'>"+uploadcontentmessages['msg.invalidcontenturl']+"</span>");
					  flag = 1;
				  }
			  }
			  
		  }		  
    	return flag; 
     }
   
     /* function for validate file type and size */ 
     var validateFileTypeAndSize=function(){    	 
    	 var flag = 0;
    	 var inp = document.getElementById('filepath');
   	     var size = inp.files[0].size;
         var extArray = ['png','jpeg','jpg','gif','bmp','tiff','pdf','mpeg','mp4','avi','ppt','pptx'];
         if(inp.files.length>0){        	 
        	     var str = '';
    			 size = parseFloat(size / 1024).toFixed(2);
    			 if(size>10240){
    				 str+='<span class="text-red">'+uploadcontentmessages['msg.filesizeexceed'].replace('#filename',inp.files.item(0).name)+'</span><br>'
    				 flag = 1;
    			  }
    			 
    			 if (extArray.indexOf(inp.files[0].name.split('.').pop()) > -1) {    				    
    			  } else {
    					str+='<span class="text-red">'+uploadcontentmessages['msg.filehavingotherspecifiedtype'].replace('#filename',inp.files.item(0).name)+'</span><br>'
       				 flag = 1;
    			 }
        	 
        	 if(flag==1){
        		 $('#filepath').css({"border-color" : "red"});
   			     $('#filepath').after("<span class='text-red'>"+str+"</span>");
        	 }
    	 
       }
       return flag==0?true:false;  
   }      
    
     function showContentNew(contentURL,type) {
    	 _pdfsrc = contentURL;
      	contentURL+="/page_"+$("#time").val()+"."+type;
  		$('.modal-lg').css('width', '90%');
  		$("#frame").empty();
  		var frameHTML = "";
  		if(type=='pdf')
  		{
	    	/* _pdfsrc = contentURL.split('/');
	    	var _courseDir = $.trim(_pdfsrc[3]);
	    	var _pdfDir = $.trim(_pdfsrc[4]);	 */
	    	var _objectDataPath = 'https://s3.ap-south-1.amazonaws.com/pdf.js/pdfjs/web/viewer.html?pdfsrc='+_pdfsrc+'/page_'+$("#time").val()+'.'+type;
  		    frameHTML = '<object type="text/html" id="pafContent" data="'+ _objectDataPath
  				+ '"  style="width:100%;max-width:100%;height:500px"></object>';
  		}
  		else if(type=='png'){
  			frameHTML = '<img id="pafContent" src="'+contentURL+'" style="width: 100%; max-height: 450px">';
  		}
  		$("#frame").append(frameHTML);  
      	$("#showContentid").modal({
 			backdrop : 'static',
 			keyboard : false
 		});
      	 
      }
     
</script>
</html>