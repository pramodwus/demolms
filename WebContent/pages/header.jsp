<!-- Main Header -->
<%@page import="com.qbis.model.User,com.qbis.common.ConstantUtil"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
.navbar-nav>.user-menu>.dropdown-menu>li.user-header {
	height: 190px;
    margin-left: -1px;
    margin-right: -1px;
}

/* .main-header .logo {
	min-height: 59px !important;
} */
.sidebar-toggle, .sr-only {
	font-family: fontAwesome !important;
}

.navbar-nav>.user-menu>.dropdown-menu>li.user-header>p {
	font-size: 15px !important;
}


.main-header .logo {
	width: 217px;
}

.main-header>.navbar {
	margin-left: 217px;
}

@media ( max-width : 767px) {
	.main-header .logo, .main-header .navbar {
		width: 100%;
	}
}

@media ( max-width : 767px) {
	.main-header .navbar {
		margin: 0;
	}
}

@media ( max-width : 767px) {
	.skin-black-light .main-header>.logo {
		background-color: #fff;
		border-bottom: 1px solid #EEEEEE;
		border-right: none;
	}
	.skin-black-light .main-header>.logo:hover {
		background-color: #fff;
	}
	
	.navbar-custom-menu .navbar-nav>li.user-menu>a {
		padding-top: 20px;
		padding-bottom: 30px;
	}
}

</style>
<header class="main-header">
	<!-- Logo -->
	<a class="logo"> <!-- mini logo for sidebar mini 50x50 pixels --> <span
		class="logo-mini"> <!-- <b style="color:green;">Q</b> --> <img
			src="<spring:url value='/resources/images/logo-mini.png'/>" />
	</span> <!-- logo for regular state and mobile devices --> <span
		class="logo-lg"><img
			src="<spring:url value='/resources/images/eLuminateLogo.png'/>" /></span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<div class="col-sm-3">

			<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
				role="button"> <span class="sr-only">Toggle navigation</span>
			</a>
		</div>


		<div class="input-group col-sm-4 pull-left hide">
			<input type="text" class="form-control" placeholder="Search...">
			<span class="input-group-addon"><i class="fa fa-search"></i></span>
		</div>


		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<%-- <li class="dropdown notifications-menu"><a
					class="dropdown-toggle" aria-expanded="false"> <spring:message
							code="lbl.language" text="Language" />: <select
						onchange="changeSystemLanguage()" id="changesystemlanguage">
							<option value="en"><spring:message
									code="systemlanguge.english" text="English" /></option>
							<option value="hi"><spring:message
									code="systemlanguge.hindi" text="Hindi" /></option>
					</select>
				</a></li> --%>

				<li class="dropdown notifications-menu" id="notificationLi"></li>
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="<%=((User) request.getSession().getAttribute("userSession"))
					.getImage()%>"
						onerror="this.src='<spring:url value='/resources/images/icon-admin.png'/>'"
						class="user-image" alt="User Image"> <span class="hidden-xs"><%=((User) request.getSession().getAttribute("userSession"))
					.getFirstName() == "" ? ((User) request.getSession()
					.getAttribute("userSession")).getEmail() : ((User) request
					.getSession().getAttribute("userSession")).getFirstName()
					+ "  "
					+ ((User) request.getSession().getAttribute("userSession"))
							.getLastName() + " "%>&nbsp;</span>
				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header"><img
							src="<%=((User) request.getSession().getAttribute("userSession"))
					.getImage()%>"
							onerror="this.src='<spring:url value='/resources/images/icon-admin.png'/>'"
							class="img-circle" alt="User Image">

							<p>
								<%=((User) request.getSession().getAttribute("userSession"))
					.getFirstName() == "" ? ((User) request.getSession()
					.getAttribute("userSession")).getEmail() : ((User) request
					.getSession().getAttribute("userSession")).getFirstName()
					+ "  "
					+ ((User) request.getSession().getAttribute("userSession"))
							.getLastName() + " "%>
							</p>
							<%-- <button type="button" class="btn btn-default btn-flat"
								onclick="location.href='addeditprofile'">
								<spring:message code="lbl.editprofile" text="Edit Profile" />
							</button> --%></li>

						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a class="btn btn-default btn-flat"><spring:message
										code="lbl.cancel" text="Cancel" /></a>
							</div>
							<div class="pull-right">
								<a href="logout" class="btn btn-default btn-flat"><spring:message
										code="lbl.signout" text="Sign Out" /></a>
							</div>
						</li>
					</ul></li>
				<!-- Control Sidebar Toggle Button -->


			</ul>
		</div>
	</nav>
</header>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-86949462-1',  {
	  'cookieDomain': 'auto'});
  ga('send', 'pageview');

</script>
<script type="text/javascript">
        var headermessages = new Array();
        headermessages['lbl.noofnotification'] = "<spring:message code='lbl.noofnotification' text='You have #notification notifications.' arguments='#notification' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';'/>";
        </script>
<script>
        var currentLanguage='${pageContext.response.locale}';
        $(document).ready(function(){ 
        	var languageExist = $("#changesystemlanguage option[value='"+currentLanguage+"']").val();
        	if(languageExist!=null){
            	$("#changesystemlanguage").val(currentLanguage);
        	}
        	else
        		{
        		$("#changesystemlanguage").val('en');
        		}
        	//getNotification();
            onlyNum();
        });
        
        var getNotification=function(){
        	
        	$.ajax({
    			url : "getnotifications",
    			type : 'GET',
    			dataType : 'json',
    			contentType : 'application/json',
    			async : false,		
    			error : (function() {
    				alert("server error");
    			}),
    			success : function(data) {
    			  var res = data.list;
    			    $("#notificationLi").html('');
    			    var str='';
    			    str+='<a href="#" class="dropdown-toggle" data-toggle="dropdown">';					  
    				str+='<i class="fa fa-bell-o"></i>';
    				str+='<span class="label label-warning">'+res.length+'</span>';
                    str+='</a>';
                	str+='<ul class="dropdown-menu">';
                    str+='<li class="header">'+headermessages['lbl.noofnotification'].replace("#notification",res.length)+'</li>';
                    str+='<li>';
                    str+='<ul class="menu">';
    				for (var i = 0; i < res.length; i++) {
                    str+='<li>';
                    if(res[i].image!=null){
                    	str+='<a href="'+res[i].url+'"><div class="pull-left">';
    	                str+='<img class="" src="'+res[i].image+'"></div>&nbsp;'+res[i].notifyText+'</a>';	
                    }                               
                    str+='</li>'; 
    				}
                    str+='</ul>';
                    str+='</li>';  
                    str+='</ul>';
                    
                    $("#notificationLi").append(str);
    			  
    			}
      });
        	
        }
      
      /**
       * @summary This function is used for changing the system language.
       */
      function changeSystemLanguage(){
    	  var domainName='<%=ConstantUtil.DOMAIN_NAME%>';
    	  var language=$("#changesystemlanguage").val().trim();
    	  var loc = (window.location).toString();
    	  var slashindex=loc.lastIndexOf('/');
    	  var domainindex=loc.indexOf(domainName);
    	  var localeindex=loc.lastIndexOf('locale=');
    	  if(localeindex>0){
    		  location.href=loc.replace('locale='+currentLanguage,'locale='+language);
    	  }
    	  else if(slashindex<domainindex){
    		  location.href=loc+"/?locale="+language;
    	  }
    	  else if((slashindex+1)==loc.length){
    		  location.href=loc+"?locale="+language;
    	  }
    	  else if(loc.indexOf('?')<0){
    		  location.href=loc+"?locale="+language;
    	  }
    	  
    	  else if(loc.indexOf('?')>0){
    		  location.href=loc+"&locale="+language;
    	  }
      }  
        var onlyNum=function(){	
        	$(".eOnlyNum").keydown(function(event) {
        		// Allow: backspace, delete, tab, escape, and enter
        	        if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || 
        	        		//Allow: Ctrl+A
        	            (event.keyCode == 65 && event.ctrlKey === true) || 
        	            //Allow: home, end, left, right
        	            (event.keyCode >= 35 && event.keyCode <= 39)) {
        	        	//let it happen, don't do anything
        	                 return;
        	        }
        	        else {
        	        	//Ensure that it is a number and stop the keypress
        	            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
        	                event.preventDefault(); 
        	            }   
        	        }
        	 });	
        	
        }
        
</script>

