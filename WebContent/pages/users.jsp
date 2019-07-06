<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style>
.body-color {
background-color:#F0F0F0;
}
.color-mainblue{
color:#05B26F;
}
.button-color-blue{
background-color:white;
color:#4B5960;
border:0.1em solid #4B5960;
}
.button-green-color{
border-width: 0.1em;
color:white;
border:1px solid #05B26F;
background-color:#05B26F;
}

.bottom-border{
border:1px solid #F0F0F0;
margin:0px;
height:100%
}
.content-wrapper {
margin: auto;
margin-left:230px;
}
.nav-tabs-custom>.nav-tabs>li.active {
	border-bottom-color: #7d7d7d;
}

.nav-tabs-custom>.nav-tabs>li {
	border-bottom: 3px solid transparent;
	margin-bottom: -2px;
	margin-right: 5px;
}

.nav-tabs-custom>.nav-tabs>li.active {
	border-top-color: #f1f1f1;
}

.nav-tabs-custom>.nav-tabs>li.active>a, .nav-tabs-custom>.nav-tabs>li.active:hover>a
	{
	background-color: #ECF0F5;
	color: #444;
}

.content-header .input-group-addon{
background-color:transparent !important;
padding-right:0px !important;
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
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position:fixed;left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>        
		<!-- start dataTable----->
		<div class="col-sm-12" >
		<div class="content-wrapper">
			<section class="content-header">
	            <c:if test="${action==null}">
	             <div class="input-group">
	            	<h3><spring:message code="lbl.users" text="Users"/>/<spring:message code="lbl.groups" text="Groups"/></h3>
		            <div class="input-group-addon usersdivbutton">								
						<button id="addnewuser" class="btn btn-flat btn-success button-width-large" onclick="createNewUsers()"><spring:message code="lbl.inviteuser" text="Invite User"/></button>
					</div>
					<div class="input-group-addon groupsdivbutton">								
						<button id="addnewgroup" class="btn btn-flat btn-success button-width-large" onclick="createNewGroups()"><spring:message code="lbl.createnewgroup" text="Create New Group"/></button>
					</div>
					</div>
					<div style="padding-top: 10px">
						<div style="background-color: #ECF0F5;" class="nav-tabs-custom">
							<ul class="nav nav-tabs" style="border-bottom: 1px solid #dedede;">
								<li class="active button-width" style="margin-right:0px;text-align:center;"><a href="#" onclick="showTab('usersdiv')" data-toggle="tab"><spring:message code="lbl.users" text="Users"/></a></li>
								<li class="button-width" style="margin-right:0px;text-align:center;"><a href="#" onclick="showTab('groupsdiv')" data-toggle="tab"><spring:message code="lbl.groups" text="Groups"/></a></li>
							</ul>
						</div>
					</div>
				</c:if>
				<c:if test="${action=='student'}">
				    <div class="input-group">
	            	<h3><spring:message code="lbl.trainee" text="Trainee"/></h3>
		            <div class="input-group-addon">								
								<button type="button" 
									class="btn btn-flat btn-success button-width-large"
									data-toggle="modal"
									onclick="openUploadModal('student');"><spring:message code="lbl.uploadexcel" text="Upload Excel"/></button> &nbsp;
									<button id="addnewuser" class="btn btn-flat btn-success button-width-large"
						           onclick="createNewUsers()"><spring:message code="lbl.invitetrainee" text="Invite Trainee"/></button>
					</div>
					</div>
				</c:if>
				<c:if test="${action=='teacher'}">
				    <div class="input-group">
	            	<h3><spring:message code="lbl.trainer" text="Trainer"/></h3>
		            <div class="input-group-addon">
					<button type="button" class="btn btn-flat btn-success button-width-large" 
									data-toggle="modal"
									onclick="openUploadModal('teacher');"><spring:message code="lbl.uploadexcel" text="Upload Excel"/></button> &nbsp;							
									<button id="addnewuser" class="btn btn-flat btn-success button-width-large"
						           onclick="createNewUsers()"><spring:message code="lbl.invitetrainer" text="Invite Trainer"/></button>
					</div>
					</div>
				</c:if>						
			</section>
			<section class="content">
				<div class="tab-content" style="background-color: #ECF0F5 !important;">
					<div class="tab-pane active usersdiv">
						<div class="row">			 
							<div class="col-md-12">
								<div class="callout callout-danger hide" style="margin-bottom: 5px;">				
				                    <label><spring:message code="msg.account.not.verified" text="Verify your email address!"/></label>
				                     <label class="pull-right">
				                    	<a href="#" onclick="resendverificationlink()"><spring:message code="msg.resend.verification.mail" text="Resend verification email"/></a>                    
				                    </label>                   
				                </div>
								<div class="box no-border">
									<!-- /.box-header -->
									<div class="box-body ">
									 <div class="col-lg-6 form-group" style="margin-top: 15px;">
                                       <label style="text-transform: capitalize; font-size: 14px" class="col-sm-3 col-lg-3 col-xs-12 col-md-3 control-label">
										<spring:message code="lbl.status" text="Status"/>
                                       </label>
                                       <div class="col-sm-9 col-lg-6 col-xs-12 col-md-9">								
												    <select id="type" class="form-control" style="padding: 5px 3px;" onchange="getuserlistbyfilters();">
														
															    <c:if test="${status==-1}">
															        <option value="-1"  selected="selected" ><spring:message code="lbl.all" text="All"/></option>	
															    </c:if>    
															    <c:if test="${status!=-1}">
															      <option value="-1"><spring:message code="lbl.all" text="All"/></option>	
															    </c:if>
															      <c:if test="${status==0}">
															        <option value="0"  selected="selected"><spring:message code="lbl.invited" text="Invited"/></option>	
															    </c:if>    
															    <c:if test="${status!=0}">
															      <option value="0"><spring:message code="lbl.invited" text="Invited"/></option>	
															    </c:if>
															    <c:if test="${status=='1'}">
															        <option value="1"  selected="selected"><spring:message code="lbl.active" text="Active"/></option>	
															    </c:if>    
															    <c:if test="${status!='1'}">
															      <option value="1"><spring:message code="lbl.active" text="Active"/></option>	
															    </c:if>
															
													</select>
                                      </div>
                               </div>
										<div class="table-responsive mailbox-messages col-xs-12">
											<table class="table table-hover table-striped" id="userList">
												<thead>
													<tr>
													    <th><spring:message code="lbl.emailid" text="Email Id"/></th>
														<th><spring:message code="lbl.name" text="Name"/></th>
														<th><spring:message code="lbl.phonenumber" text="Phone Number"/></th>
														<th><spring:message code="lbl.rolename" text="Role Name"/></th>
														<th><spring:message code="lbl.status" text="Status"/></th>
													</tr>
												</thead>
												
												<tbody>
													<c:forEach items="${userlist}" var="user">
														<tr>
															<td>${user.email}</td>
															<td>${user.firstName}&nbsp;&nbsp;${user.lastName}</td>
															<td>${user.mobile}</td>
															<td>${user.roleType}</td>
															<td>
																<c:if test="${user.userStatus==1}">
															     	<spring:message code="lbl.active" text="Active"/>
															   </c:if>
															   <c:if test="${user.userStatus==0}">
															     	<spring:message code="lbl.invited" text="Invited"/>
															   </c:if>
															</td>
														</tr>
													</c:forEach>
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
					</div>
					
					<div class="tab-pane groupsdiv">
						<div class="row">			 
							<div class="col-md-12">
								<div class="box no-border">
									<!-- /.box-header -->
									<div class="box-body ">
										<div class="table-responsive mailbox-messages col-xs-12">
											<table class="table table-hover table-striped" id="groupList">
												<thead>
													<tr>
													    <th style="width: 10%"><spring:message code="lbl.sno" text="S.No."/></th>
														<th style="width: 75%"><spring:message code="lbl.groupname" text="Group Name"/></th>
													    <th style="width: 15%"><spring:message code="lbl.numberoftrainee" text="No. Of Trainee"/></th>
													</tr>
												</thead>
												<tbody>
													<c:set var="count" value="1"/>
													<c:forEach items="${grouplist}" var="group">
														<tr>
															<td style="width: 10%">${count}</td>
															<td style="width: 75%">${group.groupName}</td>
															<td style="width: 15%">${group.userMapInGroupCount}</td>
														</tr>
														<c:set var="count" value="${count+1}"/>
													</c:forEach>
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
					</div>
				</div>		
			</section>
			</div>
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->
	
<div class="modal fade" id="successdialog" tabindex="-1"
		role="dialog" aria-labelledby="successdialog">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-body" style="text-align: center">
						<h3>
							<strong></strong>
						</h3>
						<p><spring:message code="msg.success.verification.mail" text="Email verification link has been sent successfully! Verify your email address and login again."/></p>
						<button type="button" class="btn btn-success button-width-large btn-flat"
						data-dismiss="modal"><spring:message code="lbl.close" text="Close"/></button>
						</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- pop up for uploading Student/Teacher from excel File -->
	<div class="modal fade" id="uploadUserPopup" tabindex="-1"
		role="dialog" aria-labelledby="testAlert">
		<div class=" col-md-12 col-sm-12 col-xs-12">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header" style="border: 0px">
						<button type="button" class="close" data-dismiss="modal" onclick="onModalClose('${action}')">&times;</button>
					</div>
					<div class="modal-body">
						<div class="row" style="margin: 0">
							<form name="userFileForm" id="userFileForm"
								enctype="multipart/form-data">
								<h4 style="margin-top: -10px; margin-bottom: 10px;">
								<spring:message code="lbl.uploadexcel" text="Upload Excel"/>
								</h4>								
								<input type="file" class="form-group" id="userFile"
									name="userFile">
									<div id="errorMsg"></div>
									<div class="form-group"><a href="${excelSheet}" class="pull-left"
									style="cursor: pointer" download>
									<spring:message code="lbl.downloadsample" text="Download Sample"/>
									</a></div>
								<button type="button" id="uploadUserFile"
									class="btn btn-success pull-right">
								<spring:message code="lbl.upload" text="Upload"/>	
                                </button>
								<input type="hidden" id="action">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="dialogs.jsp"%>
</body>
<script type="text/javascript">
        var messages = new Array();
        messages['msg.maximumuserlimit'] = "<spring:message code='msg.maximumuserlimit' text='Your organization can create maximum #user Users.' arguments='#user' javaScriptEscape='true' htmlEscape='false' argumentSeparator=';'/>";
</script>        
<!-- create user script -->
<script
	src="<spring:url value='/resources/js/createuser.js'/>"></script>
<script>
function getuserlistbyfilters()
{
	if($("#type").val()==-1)
		{
		location.href="userlist";
		}
	else
		{
		location.href="userlist?status="+$("#type").val();
		}
	
}
var action = '${action}';
/*  $(document).ready(function() {
    $('#userList').DataTable( {
        initComplete: function () {
            this.api().columns().every( function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );
 
                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        }
    } );
} ); */

/**
 * @summary function would be `called on document ready.
 */
      $(function () {
      	  $(".treeview").removeClass("active");
      	  $("#users").addClass("active");
      	  if(action==""){
      		  $("#usersInvite").addClass("active");   
        	  showTab("usersdiv"); 		  
      	  }else if(action=="student"){    		  
      		  $("#studentInvite").addClass("active");
      	  }else{    		  
      		  $("#teacherInvite").addClass("active");
      	  }
    	  
    	  var userStatus='${userStatus==0}'; 
    	  $('.imgset img').css({'max-width':'100%','height': 'auto'});
    	  if(userStatus=='true'){    		  
    		  $("#addnew").attr("disabled","disabled");    		  
          	  $(".callout").removeClass('hide');
    	  }    	  
    	  	      
          $("#userList").dataTable({'aaSorting': [],"language": datatablelanguagejson});
          $("#groupList").dataTable({'aaSorting': [],"language": datatablelanguagejson});
          });         
        
          
      /**
       * @summary function for re-sending verification link in case of not-verify through Ajax Calling.
       */
      var resendverificationlink = function(email){
			var email='<%=((User) request.getSession().getAttribute("userSession"))
					.getEmail()%>';
	    	  $.ajax({
	    		  url : "sendVerifyMail",
					type : 'POST',
					//async : false,		
					error : (function() {
						alert("server error");
					}),
					success : function(data) {
						if(data){						
							$(".callout").addClass('hide');
							$("#successdialog").modal('show');
						}
					}
	  	  });
      }
      
      var createNewUsers = function(){
    	  $.ajax({
				url : "createNewUserLicesneValidate",
				type : 'GET',
				async : false,		
				error : (function() {
					alert("server error");
				}),
				success : function(data) {
					if(data.valid){
						if($.trim(action)=="teacher"){
							location.href='addedituser?action=trainer';
				      	  }else if($.trim(action)=="student"){    		  
				      		location.href='addedituser?action=trainee';
				      	  }else{    		  
				      		location.href='addedituser';
				      	  }
					}else{
						$("#warningdialog p").text(messages['msg.maximumuserlimit'].replace('#user',data.count));
						$("#warningdialog").modal('show') ;
						$("#addnewuser").attr("disabled",true);						
					}
				}
	  });
    	  
      }
      var createNewGroups = function(){
		location.href='addeditgroup';
      }
      function showTab(id){
    	  $(".usersdiv").removeClass("active");
    	  $(".groupsdiv").removeClass("active");
    	  $(".usersdivbutton").hide();
    	  $(".groupsdivbutton").hide();
    	  $("."+id).addClass("active");
    	  $("."+id+"button").show();
      }
    </script>
</html>