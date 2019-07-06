/**
 * Code for bulk upload content page
 */


/**
 * method for validate files on browse
 */
    /*var fileprocess = function(){    	
    	 $("#titlediv").html('');
    	 $('#errordiv').html('');
    	 $('#contentdiv').next('span').remove();
    	 var inp = document.getElementById('bulkfile');
    	 var str = '<table><thead><th width="50%">'+uploadcontentmessages['lbl.name']+'</th><th width="50%">'+uploadcontentmessages['lbl.title']+'</th><thead><tbody>';
    	 if(inp.files.length<=5){
    		 for (var i = 0; i < inp.files.length; ++i) {
    			 var filetitle = inp.files.item(i).name.substr(0,inp.files.item(i).name.lastIndexOf('.'));
    	    	   str+='<tr id="tr'+i+'"><td style="padding-right: 5px;">'+inp.files.item(i).name+'</td>'+
    	    	   '<td><input type="text" id="title'+i+'" value="'+filetitle+'" class="form-control" placeholder="'+uploadcontentmessages['lbl.entercontenttitle']+'" style="margin-bottom: 5px;"></td></tr>';    	   
    	    	 }
    	    	 '</table>';
    	    	 $("#titlediv").append(str);
    	 }else{    		 
    		 $("#bulkfile").val("");    		 
    		 $('#contentdiv').after("<span class='text-red'>"+uploadcontentmessages['msg.maximumfilelimit'].replace("#maxfiles",5)+"</span>");    		 
    	 }
    	 
     }*/
var duration=[];   
var fileprocess = function(){    	
    	 $("#titlediv").html('');
    	 $('#errordiv').html('');
    	 $('#contentdiv').next('span').remove();
    	 var inp = document.getElementById('bulkfile');
    	 var str = '<table><thead><th width="50%">'+uploadcontentmessages['lbl.name']+'</th><th width="50%">'+uploadcontentmessages['lbl.title']+'</th><thead><tbody>';
    	 if(inp.files.length<=5){
    		 for (var i = 0; i < inp.files.length; ++i) {
    			 var filetitle = inp.files.item(i).name.substr(0,inp.files.item(i).name.lastIndexOf('.'));
    	    	   str+='<tr id="tr'+i+'"><td style="padding-right: 5px;">'+inp.files.item(i).name+'</td>'+
    	    	   '<td><input type="text" id="title'+i+'" value="'+filetitle+'" class="form-control" placeholder="'+uploadcontentmessages['lbl.entercontenttitle']+'" style="margin-bottom: 5px;"></td></tr>';    	   
    	    	 }
    	    	 '</table>';
    	    	 $("#titlediv").append(str);
    	 }else{    		 
    		 $("#bulkfile").val("");    		 
    		 $('#contentdiv').after("<span class='text-red'>"+uploadcontentmessages['msg.maximumfilelimit'].replace("#maxfiles",5)+"</span>");    		 
    	 }
    	 alert('size==='+inp.files.length);
    	 for(i=0;i<=inp.files.length;i++){
	 	    var file = document.getElementById("bulkfile").files[i]; // Get uploaded file
	 	    validateFile(file) // Validate Duration
    	 }
}



     
    /**
     * method for validate files on submit
     */
     var savebulkfiles = function(action){
    	 $('#contentdiv').next('span').remove();
         var inp = document.getElementById('bulkfile');
         var extArray = ['mpeg','mp4','avi'];
        	 /* ['png','jpeg','jpg','gif','bmp','tiff','pdf','mpeg','mp4','avi','ppt','pptx'];*/
         if(inp.files.length>0){
        	 var flag = 0;
        	 var str = '';
        	 for (var i = 0; i < inp.files.length; ++i) {
    			 var size = inp.files[i].size;
    			 size = parseFloat(size / 1024).toFixed(2);
    			/* if(size>10240){
    				 str+='<span id="warnspan'+i+'" class="text-red">'+uploadcontentmessages['msg.filesizeexceed'].replace('#filename',inp.files.item(i).name)+'</span><br>'
    				 flag = 1;
    			  }*/
    			 //console.log(inp.files[i].name+" "+inp.files[i].name.split('.').pop());
    			 if (extArray.indexOf(inp.files[i].name.split('.').pop()) > -1) {
    				    //In the array!
    				} else {
    					str+='<span id="warnspan'+i+'" class="text-red">'+uploadcontentmessages['msg.filehavingotherspecifiedtype'].replace('#filename',inp.files.item(i).name)+'</span><br>'
       				 flag = 1;
    				}
    			 
    	    	 }
        	 
        	 if(flag==1){
        		 $('#errordiv').html('');        		 
        		 $('#errordiv').append(str);
        	 }else{        		 
        		 if(action=="list"){
        			 if(validatetitlelength()){        				 
        				 submitformdata();	 
        			 }        			 
        		 }        		     
        		 else{
        			 if(validatetitlelength()){
        			   submitformdataforcourse();
        			 }
        		 }
        			 
        	 }
         }else{
        	 $('#contentdiv').after("<span class='text-red'>"+uploadcontentmessages['msg.atleastacontentforupload']+"</span>");
         }        
        
     }
     
     var cancelWrongFile = function(i){
    	 var inp = document.getElementById('bulkfile');
    	 
    	 inp.files.item(i).remove();
    	 
    	 $('#warnspan'+i).remove();
    	 $('#tr'+i).remove();
     }
     
     /**
      * method for save contents on Upload content tab
      */
     var submitformdata = function(){
    	 debugger;
    	 $("#overlay").show();
    	 var inp = document.getElementById('bulkfile');
    	  var title = new Array();
         var formdata = new FormData();
         for (var i = 0; i < inp.files.length; ++i) {          
             formdata.append("files", inp.files.item(i));     
             title.push($("#title"+i).val()); 
              }    
         
         formdata.append("title", title);
         formdata.append("duration",duration);
         


         $.ajax({
              url : 'savemultiplecontents',
             method : 'POST',
             data : formdata,
             processData : false,
             contentType : false,             
             success : function(response, status,xhr) {            	 
            	 $("#overlay").hide();
            	 if(response.status){            		 
            		 $('#bulkmodal').modal('hide');
                	 location.href='listuploadcontent';
            	 }else{
            		 $('#errordiv').html('');        		 
            		 $('#errordiv').append("<span class='text-red'>"+uploadcontentmessages['msg.remainingspacefororg'].replace('#space',response.remainSpaceMB)+"</span>");
            	 }
            	 
            	 
             }
         });
    	 
     }
     
 	function validateFile(file) {
		debugger;
var video = document.createElement('video');
video.preload = 'metadata';
video.onloadedmetadata = function() {
	window.URL.revokeObjectURL(video.src);
	var seconds = Math.floor((parseInt(video.duration) % 60));
	var timeDuration = Math.floor(parseInt(video.duration) / 60);
	var totalTime = timeDuration + ":" + seconds;
	duration.push(totalTime);
	alert(duration);
}
video.src = URL.createObjectURL(file);
}



     
     /**
      * method for save contents at course creation time
      */
     var submitformdataforcourse = function(){
    	 $('#overlay').show();
    	 var inp = document.getElementById('bulkfile');
    	  var title = new Array();
         var formdata = new FormData();
         for (var i = 0; i < inp.files.length; ++i) {          
             formdata.append("files", inp.files.item(i));                 
             title.push($("#title"+i).val()); 
              } 
         
         formdata.append("title", title);
         $.ajax({
             url : 'savemultiplecontents',
             method : 'POST',
             data : formdata,
             processData : false,
             contentType : false,
             error: function(){
            	 $('#overlay').hide();
             },
             success : function(response, status,xhr) {
            	 var val = response.contentsId;
            	 
            	 if(response.status){            		 
            		 $.ajax({
               		   url:"mapcontentintosection?contents="+val+"&sectionId="+$("#sectionidmodal").val(),
               		   type:'POST',
               		   error : function(){
               			alert("error");
               		   },
               		   success : function(status){
                      	// debugger;

               			$('#overlay').hide();
               			$('#bulkmodal').modal('hide');
               			location.href="addEditCourseMaterial?courseId="+$("#courseid").val();
               		   }
               		});
            	 }else{
            		 $('#overlay').hide(); 
            		 $('#errordiv').html('');        		 
            		 $('#errordiv').append("<span class='text-red'>"+uploadcontentmessages['msg.remainingspacefororg'].replace('#space',response.remainSpaceMB)+"</span>");
            	 }            	 
             }
         });
    	 
     }
     
     /**
      * method for validate title length 
      */
     var validatetitlelength = function(){
    	 var inp = document.getElementById('bulkfile');
    	 var abc = 0;
    	 var status = true;
         var formdata = new FormData();
         for (var i = 0; i < inp.files.length; ++i) {
        	 if($("#title"+i).val()!="" && $("#title"+i).val().length>50){
        		 abc = 1;
        		 $("#title"+i).after("<span class='text-red'>"+uploadcontentmessages['msg.maxcharacterlength'].replace('#maxlength',50)+"</span>"); 
        		 $("#title"+i).css({"border-color" : "red","border-style":"solid","border-width":"1px"});        		 
        		 //break;
        	 }              
         }
         if(abc==1){        	 
        	 status = false;
         }else{        	 
        	 status = true;
         }
         
        	 return status;
     }
     
     /**
      * Function to add more url
      */
     var addMore = function(){	
         
     	var i = parseInt($("#count").val());	
     	if(i<5){
     		 $("#addmore"+(i-1)).html('');
     		 var str = '';
     		    str+='<div id="urldiv'+i+'" class="row col-xs-12 form-group">'
     		    str+='<span class="pull-right" id="addmore'+i+'"><a style="cursor: pointer;" onclick="addMore();"><font color="#00A65A"><i class="fa fa-plus-circle"></i></font></a>&nbsp;'
    		    str+='<a href="#" onclick="removeMore();"><font color="#00A65A"><i class="fa fa-minus-circle"></i></font></a></span>'
     		    str+='<div class="col-xs-7"><label>'
     		    str+=uploadcontentmessages['lbl.contenturlinorder'].replace('#ordernumber',(i+1))+'</label>'     		   
     		    str+='<input type="text" placeholder="'+uploadcontentmessages['placeholder.promotionalvideo']+'" class="form-control" id="url'+i+'" name="urls" >'
     		    str+='</div>'
     		    str+='<div class="col-xs-4"><label>'
     	     	str+='Title '+(i+1)+'</label>'     		   
     	     	str+='<input type="text" placeholder="'+uploadcontentmessages['lbl.entertitle']+'" class="form-control" id="titleurl'+i+'">'
     	     	str+='</div></div>';	
     		    $("#urlset").append(str);
     		    i++;
     		    $("#count").val(i);
     		    
     	}
        
     }

     /**
      * Function to remove more questions
      */
     var removeMore = function(){
     	var i = parseInt($("#count").val());	
         if(i>1){    	
         	$("#urldiv"+(i-1)).remove();         	
         	var str ='<a onclick="addMore();"><font color="#00A65A"><i class="fa fa-plus-circle"></i></font></a>&nbsp;'
         		str+='<a onclick="removeMore();"><font color="#00A65A"><i class="fa fa-minus-circle"></i></font></a>'
         	$("#addmore"+(i-2)).append(str);    
             $("#count").val((i-1));
         }
        
     }
     
     /**
      * method for upload contents of(URL types)
      */
     var submitcontenturl = function(action){    	     	 
         var formdata = new FormData();         
         var contentUrl = new Array();
         var title = new Array();
         for (var i = 0; i < $("#count").val(); ++i) {
        	 contentUrl.push($("#url"+i).val());
        	 title.push($("#titleurl"+i).val());
         }         
         formdata.append("contentUrl", contentUrl);
         formdata.append("title", title);
         if(validatecontenturl()){
        	 $.ajax({
                 url : 'savemultiplecontents',
                 method : 'POST',
                 data : formdata,
                 processData : false,
                 contentType : false,
                 success : function(response, status,xhr) {
                	 $('#overlay').hide();
                	 $('#bulkmodal').modal('hide');
                	 if(action!='list'){
                		 var val = response.contentsId;
                    	 $('#bulkmodal').modal('hide');
                    	 $.ajax({
                     		   url:"mapcontentintosection?contents="+val+"&sectionId="+$("#sectionidmodal").val(),
                     		   type:'POST',
                     		   error : function(){
                     			alert("error");
                     		   },
                     		   success : function(status){
                     			$('#overlay').hide();   
                     			location.href="addEditCourseMaterial?courseId="+$("#courseid").val();
                     		   }
                     		});
                	 }else{
                		 window.location.href='listuploadcontent';
                	 }
                 }
             });        	 
         }             	 
     }
     
     
     /**
      * method for validate content url 
      */
     var validatecontenturl = function(){
    	 var status = "";
    	 var flag = 0;
    	 var myRegExp=/^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
         for (var i = 0; i < $("#count").val(); ++i) {
        	 $("#url"+i).css({"border-color" : ""});	
	  		 $("#url"+i).next('span').remove();
	  		 $("#titleurl"+i).css({"border-color" : ""});	
	  		 $("#titleurl"+i).next('span').remove();
        	 if($("#url"+i).val()==""){        		 
        		 $("#url"+i).after("<span class='text-red'>"+uploadcontentmessages['msg.contentmandatory'].replace('#ordernumber',(i+1))+"</span>"); 
        		 $("#url"+i).css({"border-color" : "red","border-style":"solid","border-width":"1px"});
        		 flag=1;
        	 }else{        		 
        		 if (!myRegExp.test($("#url"+i).val())) {        			 
        			 $("#url"+i).after("<span class='text-red'>"+uploadcontentmessages['msg.contenturlinvalid'].replace('#ordernumber',(i+1))+"</span>"); 
            		 $("#url"+i).css({"border-color" : "red","border-style":"solid","border-width":"1px"});
        			 flag=1;
                	}        		 
        	 }
        	 
        	 if($("#titleurl"+i).val()!="" && $("#titleurl"+i).val().length>50){
        		 flag = 1;
        		 $("#titleurl"+i).after("<span class='text-red'>"+uploadcontentmessages['msg.maxcharacterlength'].replace('#maxlength',50)+"</span>"); 
        		 $("#titleurl"+i).css({"border-color" : "red","border-style":"solid","border-width":"1px"});
        	 }
        	 
         }
         if(flag==1){        	 
        	 status = false;
         }else{        	 
        	 status = true;
         }
         
        	 return status;
     }
     
     var cancelThisFile = function(k){
    	 $("#tr"+k).remove();
    	 $("#warnspan"+k).remove();
     }
     
     
     /*var validateAvailableSpaceOfOrg = function(){
    	 $.ajax({
				url : "validateAvailableSpaceOfOrg",
				type : 'GET',
				async : false,		
				error : (function() {
					alert("server error");
				}),
				success : function(data) {
					
				}
	         });
     }*/
     
     
     /**
      * method for validate files on submit
      */
      function singleUploadSubmit(){    
     	 $('#singlecontentdiv').next('span').remove();
          var inp = document.getElementById('singlefile');
          var extArray = ['mpeg','mp4','avi'];
          if(inp.files.length>0){
         	 var flag = 0;
         	 var str = '';
         	 for (var i = 0; i < inp.files.length; ++i) {
     			 var size = inp.files[i].size;
     			 size = parseFloat(size / 1024).toFixed(2);
     			 if(size>10240){
     				 str+='<span id="warnspan'+i+'" class="text-red">'+uploadcontentmessages['msg.filesizeexceed'].replace('#filename',inp.files.item(i).name)+'</span><br>'
     				 flag = 1;
     			  }
     			 //console.log(inp.files[i].name+" "+inp.files[i].name.split('.').pop());
     			 if (extArray.indexOf(inp.files[i].name.split('.').pop()) > -1) {
     				    //In the array!
     				} else {
     					str+='<span id="warnspan'+i+'" class="text-red">'+uploadcontentmessages['msg.filehavingotherspecifiedtype'].replace('#filename',inp.files.item(i).name)+'</span><br>'
        				 flag = 1;
     				}
     			 
     	    	 }
         	 
         	 if(flag==1){
         		 $('#singleerrordiv').html('');        		 
         		 $('#singleerrordiv').append(str);
         	 }else{  
         		submitSingleFileData();
         	 }
          }else{
         	 $('#singlecontentdiv').after("<span class='text-red'>"+uploadcontentmessages['msg.selectcontentforupload']+"</span>");
          }        
         
      }
      
      function singlefileprocess(){    	
     	 $("#singletitlediv").html('');
     	 $('#singleerrordiv').html('');
     	 $('#singlecontentdiv').next('span').remove();
     	 var inp = document.getElementById('singlefile');
     	 var filetitle = inp.files.item(0).name.substr(0,inp.files.item(0).name.lastIndexOf('.'));
     	 var str = '<table><thead><th width="50%">'+uploadcontentmessages['lbl.name']+'</th><th width="50%">'+uploadcontentmessages['lbl.title']+'</th><thead><tbody>';
    	   str+='<tr id="tr"><td style="padding-right: 5px;">'+inp.files.item(0).name+'</td>'+
    	   '<td><input type="text" id="singletitle" value="'+filetitle+'" maxlength="50" class="form-control" placeholder="'+uploadcontentmessages['lbl.entercontenttitle']+'" style="margin-bottom: 5px;"></td></tr>';    	   
    	 '</table>';
    	 $("#singletitlediv").append(str);
      }
      
      function submitSingleFileData(){
    	  $("#overlaySingle").show();
     	  var inp = document.getElementById('singlefile');
     	  var title = new Array();
          var formdata = new FormData();
          for (var i = 0; i < inp.files.length; ++i) {          
              formdata.append("files", inp.files.item(i));                 
              title.push($("#singletitle").val()); 
           }
          formdata.append("title", title);
          $.ajax({
              url : 'savemultiplecontents',
              method : 'POST',
              data : formdata,
              processData : false,
              contentType : false,
              error : function(){
            	  $("#overlaySingle").hide();
              },
              success : function(response, status,xhr) {
             	 if(response.status){     
                 	 window.location.href='createvideowithquestion?id='+response.contentsId[0]+"&mode=0";
             	 }else{
             		 $("#overlaySingle").hide();
             		 $('#singleerrordiv').html('');        		 
             		 $('#singleerrordiv').append("<span class='text-red'>"+uploadcontentmessages['msg.remainingspacefororg'].replace('#space',response.remainSpaceMB)+"</span>");
             	 }
             	 
             	 
              }
          });     	 
      }
      
      function pdfUploadSubmit(id){    	 
    	  $('#'+id+'contentdiv').next('span').remove();
    	  var inp = document.getElementById(id+'file');
    	  var extArray = [];
    	  if(id=='pdf'){
    		  extArray = ['pdf'];
    	  }else{
    		  extArray = ['ppt','pptx'];
    	  }
    	  if(inp.files.length>0){
          	 var flag = 0;
          	 var str = '';
          	 for (var i = 0; i < inp.files.length; ++i) {
      			 var size = inp.files[i].size;
      			 size = parseFloat(size / 1024).toFixed(2);
      			 if(size>10240){
      				 str+='<span id="warnspan'+i+'" class="text-red">'+uploadcontentmessages['msg.filesizeexceed'].replace('#filename',inp.files.item(i).name)+'</span><br>'
      				 flag = 1;
      			  }
      			 if (extArray.indexOf(inp.files[i].name.split('.').pop()) > -1) {
  				} else {
  					str+='<span id="warnspan'+i+'" class="text-red">'+uploadcontentmessages['msg.filehavingotherspecifiedtype'].replace('#filename',inp.files.item(i).name)+'</span><br>'
     				 flag = 1;
  				}
  	    	 }
          	 if(flag==1){
          		 $('#'+id+'errordiv').html('');        		 
          		 $('#'+id+'errordiv').append(str);
          	 }else{  
          		submitPDFFileData(id);
          	 }
           }else{
          	 $('#'+id+'contentdiv').after("<span class='text-red'>"+uploadcontentmessages['msg.selectcontentforupload']+"</span>");
           }
       }
      
      function pdffileprocess(id){    
      	 $("#"+id+"titlediv").html('');
      	 $('#'+id+'errordiv').html('');
      	 $('#'+id+'contentdiv').next('span').remove();
      	 var inp = document.getElementById(id+'file');
      	 var filetitle = inp.files.item(0).name.substr(0,inp.files.item(0).name.lastIndexOf('.'));
      	 var str = '<table><thead><th width="50%">'+uploadcontentmessages['lbl.name']+'</th><th width="50%">'+uploadcontentmessages['lbl.title']+'</th><thead><tbody>';
     	   str+='<tr id="tr"><td style="padding-right: 5px;">'+inp.files.item(0).name+'</td>'+
     	   '<td><input type="text" id="'+id+'title" value="'+filetitle+'" maxlength="50" class="form-control" placeholder="'+uploadcontentmessages['lbl.entercontenttitle']+'" style="margin-bottom: 5px;"></td></tr>';    	   
     	 '</table>';
     	 $("#"+id+"titlediv").append(str);
       }
      
      function submitPDFFileData(id){
    	  $('#overlaySingle').show();
     	  var inp = document.getElementById(id+'file');
     	  var title = new Array();
          var formdata = new FormData();
          for (var i = 0; i < inp.files.length; ++i) {          
              formdata.append("files", inp.files.item(i));                 
              title.push($("#"+id+"title").val()); 
           }
          formdata.append("title", title);
          $.ajax({
              url : 'savemultiplecontents',
              method : 'POST',
              data : formdata,
              processData : false,
              contentType : false,
              error:function(){
            	 $('#overlaySingle').hide();
              },
              success : function(response, status,xhr) {
             	 if(response.status){   
             		 var mo=1;
             		 if(id=='ppt'){
             			mo=2;
             		 }
             		window.location.href='createvideowithquestion?id='+response.contentsId[0]+"&mode="+mo;          		
             	 }else{
             		 $('#overlaySingle').hide();
             		 $('#'+id+'errordiv').html('');        		 
             		 $('#'+id+'errordiv').append("<span class='text-red'>"+uploadcontentmessages['msg.remainingspacefororg'].replace('#space',response.remainSpaceMB)+"</span>");
             	 }
             	 
             	 
              }
          });     	 
      }


