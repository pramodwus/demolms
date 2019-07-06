<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="include.jsp"%>
<link rel="stylesheet" href="resources/css/custom.css">
<style type="text/css">
#userList_filter {
	padding-top: 5px;
}

#userTableDiv {
	max-height: 500px;
	overflow-x: hidden;
	overflow-y: auto;
	border: 1px solid #ccc;
	padding-top: 10px;
}
</style>
</head>
<body class="hold-transition skin-black-light sidebar-mini">
	<div class="wrapper">
		<div id="overlay" class="overlay1"
			style="display: none; position: fixed; left: 0; top: 0; bottom: 0; right: 0; background: #fff; opacity: 0.8; filter: alpha(opacity = 80); z-index: 9999;">
			<img id="loading" class="lazy" src="resources/images/loading.gif"
				style="position: fixed; left: 50%; top: 50%;">
		</div>
		<%@ include file="header.jsp"%>
		<%@ include file="leftmenu.jsp"%>
		<!-- start dataTable----->
		<div class="col-sm-12">
			<div class="content-wrapper">
				<section class="content">
					<div class="row">
						<div class="col-md-12">
							<div class="box no-border">
								<div class="box-header with-border">
									<h3 class="box-title">
										<spring:message code="lbl.addnewgroup" text="Add New Group" />
									</h3>
								</div>
								<!-- /.box-header -->
								<form action="createGroup" name="createGroupForm"
									id="createGroupForm" method="post">
									<div class="box-body">
										<div class="form-group col-sm-12">
											<label for="email" class="col-sm-2 control-label"><spring:message
													code="lbl.groupname" text="Group Name" /></label>
											<div class="col-sm-10">
												<input type="text" class="form-control" maxlength="120"
													id="groupName" name="groupName"
													placeholder="<spring:message code="lbl.groupname" text="Group Name"/>"
													onkeyup="keyValidate()"> <label class="requireFld"
													for="form-field-1" id="groupError"><spring:message
														code="msg.empty" text="This field is required." /></label> <label
													class="requireFld" for="groupName" id="groupError1"><spring:message
														code="msg.groupalreadyexist"
														text="Group name is already exist. Please enter another name." /></label>
											</div>
										</div>
										<div class="form-group col-sm-12">
											<label for="role11" class="col-sm-2 control-label"><spring:message
													code="lbl.userlist" text="User List" /></label>
											<div class="col-sm-10">		
											<div class="col-sm-12" id="userTableDiv">
												<c:set var="i" value="0" />
												<table class="table table-hover table-striped" id="userList">
													<thead>
														<tr>
															<th style="width: 10%"><input type="checkbox"
																class="selectedallgroup" id="all" name="all"
																onclick="checkedAll();" /> <label for="all"></label></th>
															<th style="width: 90%"><spring:message
																	code="lbl.emailid" text="Email Id" /></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${userlist}" var="user">
															<tr>
																<td class="no-sort" style="width: 10%"><input
																	type="checkbox" value="${user.userId}"
																	class="selectedgroup" id="checkbox${i}"
																	name="checkbox${i}" /> <label for="checkbox${i}"></label>
																</td>
																<td style="width: 90%">${user.email}</td>
															</tr>
															<c:set var="i" value="${i+1}" />
														</c:forEach>

													</tbody>
												</table>
												<input type="hidden" value="${i}" id="userCount"
													name="userCount"> <label class="requireFld"
													for="form-field-1" id="userError"><spring:message
														code="msg.selectminoneuser"
														text="Please select at least one user." /></label>
											</div>
											</div>
										</div>
									</div>
									<!-- /.box-body -->
									<div class="box-footer">
										<div class="col-sm-12">
											<div class="col-sm-12">
												<button type="button"
													class="btn btn-default btn-flat button-width-large"
													onclick="location.href='userlist'">
													<spring:message code="lbl.cancel" text="Cancel" />
												</button>
												<button type="button"
													class="btn btn-success btn-flat button-width-large pull-right"
													onclick="submitForm()">
													<spring:message code="lbl.creategroup" text="Create Group" />
												</button>
											</div>
										</div>
									</div>
									<!-- /.box-footer -->
								</form>
							</div>
						</div>
						<!-- /.col -->
					</div>
				</section>
			</div>
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- ./wrapper -->
</body>
<script>
	var flag = false;
	$(document)
			.ready(
					function() {
						$(".treeview").removeClass("active");
						$("#users").addClass("active");
						$("#users .treeview-menu > #usersInvite").addClass(
								"active");
						$("#userList").dataTable({
							"sDom" : "f",
							"order" : [],
							"columnDefs" : [ {
								"targets" : 'no-sort',
								"orderable" : false
							} ]
						});
						$(
								'input[type="checkbox"].selectedgroup,input[type="checkbox"].selectedallgroup')
								.iCheck({
									checkboxClass : 'icheckbox_square-green'
								});

					});
	$(document).on('ifClicked', '.selectedallgroup', function() {
		var userCount = $("#userCount").val();
		if (userCount > 0) {
			if (!$(this).is(':checked')) {
				$('.selectedgroup').iCheck('check');
				flag = true;
				userDivCheck();
			} else {
				$('.selectedgroup').iCheck('uncheck');
				flag = false;
			}
		}
	});
	$(document).on('ifChecked', '.selectedgroup', function() {
		var checkLength = $(".selectedgroup:checked").length;
		var userCount = $("#userCount").val();
		flag = true;
		userDivCheck();
		if (checkLength == userCount) {
			$('.selectedallgroup').iCheck('check');
		}
	});
	$(document).on('ifUnchecked', '.selectedgroup', function() {
		var checkLength = $(".selectedgroup:checked").length;
		if (checkLength != $("#userCount").val()) {
			$('.selectedallgroup').iCheck('uncheck');
		}
		if (checkLength == 0) {
			flag = false;
		}
	});
	function validate() {
		var groupName = $.trim($('#groupName').val());
		if (groupName == "") {
			$("#groupName").css("border-color", "#c95b5b");
			$("#groupError").fadeIn();
			$('#groupName').focus();
			return false;
		}
		if (!flag) {
			$("#userTableDiv").css("border", "1px solid #c95b5b");
			$("#userError").fadeIn();
			return false;
		}
		return (true);
	}
	function keyValidate() {
		var groupName = $.trim($('#groupName').val());
		if (groupName.length > 0) {
			$("#groupName").css("border-color", "");
			;
			$("#groupError").fadeOut();
			$("#groupError1").fadeOut();
		}
	}
	function submitForm() {
		if (validate()) {
			var groupName = $.trim($('#groupName').val());
			$.ajax({
				type : 'POST',
				url : "checkGroupName",
				data : "groupName=" + encodeURIComponent(groupName),
				error : function() {
				},
				success : function(data) {
					if (data) {
						$("#groupName").css("border-color", "#c95b5b");
						$("#groupError1").fadeIn();
						$('#groupName').focus();
					} else {
						$("#createGroupForm").submit();
					}
				}
			});
		}
	}
	function userDivCheck() {
		$("#userTableDiv").css("border", "1px solid #ccc");
		$("#userError").fadeOut();
	}
</script>

</html>