/**
 * Java Script file for some common java script functions for the application.
 */
var submitAjaxRequestCors = function(p_url, p_method, p_data, p_successfunc, p_show_loader){
	
	$.ajax({
		url : p_url,
		headers: {
            'Authorization': 'Bearer ' + $("#auth-token").val()
        },
		method : p_method,
		crossDomain: true,
		dataType : 'json',
		contentType : "application/json",
		data : p_data,
		beforeSend: function() {
		},
		complete: function(){
			$("#app_loader").hide();
        },
        error: function(){
        	$("#app_loader").hide();
        },
		statusCode: {
		},
		success : p_successfunc
		
	});	
}

/**
 * Method for submitting Ajax request to application.
 */
var submitAjaxRequest = function(p_url, p_method, p_data, p_successfunc){
	
	$.ajax({
		url : p_url,
		headers: {
            'sessionid': $('#sessionid').val()
        },
		method : p_method,
		dataType : 'json',
		contentType : "application/json",
		data : p_data,
		statusCode: {
			403: function() {
				location.href = "logout";
		    }
		},
		success : p_successfunc
		
	});
	
}
/**
 * Method for displaying status message returned from the service, input argument response is the response from service,
 * parent is the parent container of the fields against which the messages needs to be displayed.
 */
var displaystatus = function(response, parent){
	if(response.code == 1){
		$("#successdialog p").text(response.resultmsg[0].msgdescription);
		$('#successdialog').modal('show') ;
	}else if(response.code == 0){
		var i;
		for(i in response.resultmsg){
			var resultmsg = response.resultmsg[i];
			if(resultmsg.field != null){
				parent.find($("[name='"+resultmsg.field+"']")).css({"border-color" : "red","border-style":"solid","border-width":"1px"});
				parent.find($("[name='"+resultmsg.field+"']")).after(
						"<span class='text-red'>"+ " " +resultmsg.msgdescription+"</span>"
					);
			}else{
				$("#errordialog p").text(response.resultmsg[0].msgdescription);
				$("#errordialog").modal('show') ;
			}
			
		}
	}else if(response.code == 2){
		$("#warningdialog p").text(response.resultmsg[0].msgdescription);
		$("#warningdialog").modal('show') ;
	}
}
/**
 * Method to remove the span containing error message and red border color of error field. Method to be called on re-load
 * of pages to clear the error messages from screen.
 */
var clearErrorMessages = function(fieldlist){
	for(var i = 0; i < fieldlist.length; i++) {
		$("[name='"+fieldlist[i]+"']").css({"border-color" : "","border-style":"","border-width":""});
		$("[name='"+fieldlist[i]+"']").next('span').remove();
	}
	
}
/**
 * Method to format a input date.
 */
var formatDate = function(inputdate){
	
	var l_date 		= inputdate.getDate(),
		l_month 	= inputdate.getMonth()+1,
		l_year 		= inputdate.getFullYear(),
		l_hours 	= inputdate.getHours(),
		l_minutes 	= inputdate.getMinutes(),
		l_seconds	= inputdate.getSeconds();
	
	l_date 	= l_date.toString().length > '1' ? l_date : '0'+l_date;
	l_month = l_month.toString().length > '1' ? l_month : '0'+l_month;
	l_hours = l_hours.toString().length > '1' ? l_hours : '0'+l_hours;
	l_minutes = l_minutes.toString().length > '1' ? l_minutes : '0'+l_minutes;
	l_seconds = l_seconds.toString().length > '1' ? l_seconds : '0'+l_seconds;
	
	var outputdate = [l_date,l_month,l_year ].join('/')+' ' + [l_hours, l_minutes, l_seconds].join(':');
	
	return outputdate;
}

/**
 * Method to start timer.
 */
var countdown = function(sec){
    var seconds = sec;   
    function tick() {
        var counter = document.getElementById("timeleft");        
        seconds--;
        counter.innerHTML =
(seconds < 10 ? "0" : "") + String(seconds);
        if( seconds > 0 ) {
            setTimeout(tick, 1000);
        }
    }
    tick();
}

/**
 * Method to format a input date in DD/MM/YYYY pattern.
 */
var formatDateddmmyyyy = function(inputdate){
	
	var l_date 		= inputdate.getDate(),
		l_month 	= inputdate.getMonth()+1,
		l_year 		= inputdate.getFullYear()
		
	
	l_date 	= l_date.toString().length > '1' ? l_date : '0'+l_date;
	l_month = l_month.toString().length > '1' ? l_month : '0'+l_month;
	
	
	var outputdate = [l_date,l_month,l_year ].join('/');
	
	return outputdate;
}

/**
 * Method for displaying status message returned from the service, input argument response is the response from service,
 * on pop up pages.
 */
var displaystatuspopup = function(response, parent){
	if(response.code == 1){
		$("#successdialog p").text(response.resultmsg[0].msgdescription);
		$('#successdialog').modal('show') ;
	}else if(response.code == 0){
		var i;		
		for(i in response.resultmsg){
			var resultmsg = response.resultmsg[i];
			if(resultmsg.field != null){
				$("[name='"+resultmsg.field+"']").css({"border-color" : "red","border-style":"solid","border-width":"1px"});
				$("[name='"+resultmsg.field+"']").after(
						"<span class='text-red'>"+ " " +resultmsg.msgdescription+"</span>"
				);				
			}else{
				$("#errordialog p").text(response.resultmsg[0].msgdescription);
				$("#errordialog").modal('show') ;
			}
			
		}
	}else if(response.code == 2){
		$("#warningdialog p").text(response.resultmsg[0].msgdescription);
		$("#warningdialog").modal('show') ;
	}
}