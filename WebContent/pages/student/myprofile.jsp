<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
            crossorigin="anonymous">
                            <link rel="stylesheet" href="<spring:url value='/resources/css/newcommon.css'/>">
                            <link rel="stylesheet" href="<spring:url value='/resources/css/myprofile.css'/>">
        <title>eLuminate</title>
         <style>
    .requireFld{
    	font-size: 12px;
    	color: red;
    	float: left; 
    	width: 4em; 
    	margin-right: 1em; 
    }
    </style>
        
    </head>
<body>
     <nav class="navbar navbar-expand-md sticky-top navbar-light border">
        <div class="brand-logo">
            <a href="https://dev.eluminate.in/#/"><img src="resources/images/eLuminateHeaderLogo.png" class="header-img-logo"></a>
        </div>
        <button class="navbar-toggler" data-toggle="collapse" data-target="#eluminateNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="eluminateNavbar">
            <ul class="navbar-nav align-items-center ml-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link px-3"></a>
                </li>
                <!-- <li class="nav-item">
                    <a href="#" class="nav-link px-4">
                        <span><i class="fas fa-mobile-alt fa-icon px-3"></i>Download App</span>
                    </a>
                </li> -->
                <c:if test="${flag eq false}">
                <li class="nav-item"><a
						href="https://dev.eluminate.in/#/all-courses" class="nav-link px-4">
							<!--  <span><i
								class="fas fa-mobile-alt px-3"></i>PreBook This Course</span> -->
							<button class="btn subscribe-button text-white font-weight-bold"
								type="button">Subscribe NOW!</button>
					</a></li>
					</c:if>
                <li class="nav-item">
                    <div class="dropdown">
                        <ul class="list-inline dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                            <li class="list-inline-item mr-0">
                                <a href="#" class="nav-link font-weight-bold text-dark">${userName}</a>
                            </li>
                            <li class="list-inline-item">
                                <a href="#" class="">
                                    <img src="resources/images/userimage.png" class="header-user-image">
                                </a>
                            </li>
                        </ul>
                        <div class="dropdown-menu dropdown-menu-right new-style" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="">Profile</a>
                            <a class="dropdown-item" href="logout">Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <!-- -----------Sidebar Code Comes Here------------- -->
            <div class="col-md-3 col-xs-12 pl-0 pr-0" id="sidebar">
                <div class="list-group sidebar text-center text-md-left">
                   <!--  <div class="course-list-div">
                        <small class="course-div-heading">COURSES</small>
                        <div class="course-div-margin course-main-div">
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-mosque"></i>
                                </div>
                                <div class="course-name">History</div>
                            </a>
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-globe"></i>
                                </div>
                                <div class="course-name">Geography</div>
                            </a>
                        </div>
                    </div> -->
                     <div class="course-list-div">
						<small class="course-div-heading">COURSES</small>
						<c:forEach  items="${courses}" var="course">
							<div class="course-div-margin course-main-div">
							  <form method="post" action="coursechapter" id="coursechapterform${course.courseId}">
		                      <input type="hidden" name="courseId" value="${course.courseId}"/>
						      </form>
					 <a class="course-div courses-div flex_alignMiddle" href="javascript:void(0)" onclick="document.getElementById('coursechapterform${course.courseId}').submit(); return false;">
					
					<%-- 			<a class="course-div courses-div flex_alignMiddle"
									href="coursechapter?courseId=${course.courseId}"> --%>
           
									<div class="icon-div">
										<i class="fas fa-${course.courseIcon}"></i>
									</div>
									<div class="course-name">${course.courseName}</div>
								<!-- 	<a href="newdashboard">Click Here</a>
								 --></a>
							</div>
						</c:forEach>
					</div>
                    <!-- <div class="course-list-div">
                        <small class="course-div-heading">My Study Box</small>
                        <div class="course-div-margin course-main-div">
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-clipboard-list"></i>
                                </div>
                                <div class="course-name">Practise Tests</div>
                            </a>
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-bookmark"></i>
                                </div>
                                <div class="course-name">Bookmarks</div>
                            </a>
                            <a class="course-div flex_alignMiddle" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-comment-alt"></i>
                                </div>
                                <div class="course-name">Doubts</div>
                            </a>
                        </div>
                    </div> -->
                    <!-- <div class="course-list-div">
                        <small class="course-div-heading">Me</small>
                        <div class="course-div-margin course-main-div">
                            <a class="course-div flex_alignMiddle active non-active" href="#">
                                <div class="icon-div">
                                    <i class="fas fa-clipboard-list"></i>
                                </div>
                                <div class="course-name">Profile</div>
                            </a>
                        </div>
                    </div> -->
                </div>
            </div>
            <!-- -----------Sidebar Code end Here------------- -->



            <main class="col pl-2 pt-2 content-wrapper">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="studentdashboard"><i class="fas fa-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="">Profile</a></li>
                    </ol>
                </nav>
                <div class="main-right-div">
                    <div class="row">
                        <div class="col-sm-12">
                            <h1 class="course-heading">My Profile</h1>
                            <div class="bg-white rounded">
                                <div class="row d-flex align-items-center py-4 px-4">
                                    <div class="col-sm-2 text-center">
                                        <div class="image-upload">
                                            <label for="file-input">
                                                <img src="${user.image}" class="profile-image"/>
                                            </label>                                                
                                          <input id="file-input" type="file" onchange="uploadFile()"/>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                            
                                        <table class="table">     
                                            <tbody>
                                                <tr>                                                     
                                                    <td>${profile.first_name} ${profile.last_name}</td>  
                                                </tr>
                                                <tr>
                                                    <td>${profile.address}</td>
                                                </tr>
                                               
                                                <tr>
                                                    <td>+91-${profile.contact_info}</td>
                                                </tr>
                                                <tr>
                                                    <td>${profile.email}</td>
                                                </tr>
                                            </tbody>
                                        </table> 
                                        
                                    </div>
                                    <div class="col-sm-4 align-items-center">
                                            <button class="update-view float-right mb-3" data-toggle="modal" data-target="#exampleModal">Edit Profile</button>
                                        <!-- <table class="table">
                                            <tbody>
                                                <tr>                                                    
                                                    <td><i class="fas fa-bolt stat-icon"></i></td>
                                                    <td>Questions Practiced</td>
                                                    <td>10/14</td>                                                    
                                                </tr>
                                                <tr>                                                    
                                                    <td><i class="fab fa-squarespace stat-icon"></i></td>
                                                    <td>Time Taken</td>
                                                    <td>00:00</td>                                                    
                                                </tr>
                                                <tr>                                                    
                                                    <td><i class="fas fa-tasks stat-icon"></i></td>
                                                    <td>Recent Activities</td>
                                                    <td>4/9</td>                                     
                                                </tr>
                                            </tbody>
                                        </table> -->
                                    </div>
                                </div>                                                          
                            </div>
                        </div>
                    </div>
                <div class="row mt-4">
                    <div class="col-sm-12">
                        <h1 class="course-heading">Change Password</h1>
                            <div class="_3aP9G">
                                <div class="_1BUGv">
                                    <div class="_1HJ1t">
                                        <div class="_2CqHs"></div>
                                        <div class="_1PVef my-2">
                                            <div class="">
                                                <label class="_2_zbK _1bIop">Old Password</label>
                                                <input placeholder="Enter your Old password" class="_1E3qC" type="password" id="oldpassword" value="" style="width: 100%;">
                                            </div>
                                        </div>
                                        <div class="_1PVef my-2">
                                            <div class="">
                                                <label class="_2_zbK _1bIop">New Password</label>
                                                <input placeholder="Enter your new password" class="_1E3qC" type="password" id="password" value="" style="width: 100%;">
                                            </div>
                                        </div>
                                        <div class="_1PVef my-4">
                                            <div class="">
                                                <label class="_2_zbK _1bIop">Confirm New Password</label>
                                                <input placeholder="Enter your new password again" class="_1E3qC" type="password" id="confirmpassword" value="" style="width: 100%;">
                                            </div>
                                        </div>
                                    </div>
                                </div>                               
                            </div>                           
                            <div class="_3blqF"></div>
                      <a href="#" onClick="updatepassword();" style="text-decoration:none;"><div class="mt-30 WPwKG 2eqKy 2qNEh _2ouUF mb-3" style="width: 180px;">Set Password</div></a>                            
                    </div>
                </div>
            </div>          
        </div>
    </main>
    </div>
</div>  
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <!-- <h5 class="modal-title" id="exampleModalLabel">Edit Profile</h5> -->
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
           <%--  <div class="modal-body text-center">
                
                            <div class="form-group">
                                <!-- <label>Name</label> -->
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" value="${profile.first_name}" required>
                            </div>
                            <div class="form-group">
                                <!-- <label>Class</label> -->
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" value="${profile.last_name}" required="required">
                            </div>
                            <div class="form-group">
                                <!-- <label>School Name</label> -->
                                <input type="text" class="form-control" id="address" name="address" placeholder="Enter address" value="${profile.address}" required="required">
                            </div>
                            <div class="form-group">
                                    <!-- <label>Phone Number</label> -->
                                    <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="Enter mobile no." value="${profile.contact_info}" required="required">
                                </div>

                            <div class="form-group">
                                <!-- <label>Email address</label> -->
                                <input type="email" class="form-control" id="email" placeholder="Enter email" value="${user.email}" readonly="readonly" required="required">
                            </div>
                            <button class="save-changes" type="submit" onclick="save();">Save Changes</button>
                        
            </div> --%>
            <div class="modal-body text-center">
                
                            <div class="form-group">
                                <!-- <label>Name</label> -->
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" onkeyup="catvalidate()" value="${profile.first_name}"  required>
                            	<label class="requireFld" id="firstNameError">not valid</label>
                            </div>
                            <div class="form-group">
                                <!-- <label>Class</label> -->
                                <input type="text" class="form-control" id="lastName"   name="lastName" placeholder="Enter last name" onkeyup="catvalidate()" value="${profile.last_name}" required="required">
                            	<label class="requireFld" id="lastNameError">not valid</label>
                            </div>
                            <div class="form-group">
                                <!-- <label>School Name</label> -->
                               
                                <input type="text" class="form-control" id="address" name="address" placeholder="Enter address" onkeyup="catvalidate()" value="${profile.address}" required="required">
                            	<label class="requireFld" id="addressError">not valid</label>
                            </div> 
                            <div class="form-group">
                                    <!-- <label>Phone Number</label> -->
                                    <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="Enter mobile no." onkeyup="catvalidate()" value="${profile.contact_info}" required="required">
                                	<label class="requireFld" id="phonenumberError">not valid</label>
                                </div>

                            <div class="form-group">
                                <!-- <label>Email address</label> -->
                                <input type="email" class="form-control" id="email" placeholder="Enter email" onkeyup="catvalidate()" value="${profile.email}" readonly="readonly" required="required">
                            </div>
                            <button class="save-changes" type="submit" onclick="save();">Save Changes</button>
                        
            </div>

            
            <input type="hidden" value=${token } id="token"/>
            <!-- <div class="modal-footer">
               
              <button type="button" class="btn btn-primary">Save changes</button>
            </div> -->
          </div>
        </div>
      </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
</body>
</html>

<script>


function save() {
	debugger;
	var flag = validate();
	if (flag == true) {

   var updateData=createJson();
   var data=JSON.stringify(updateData);
    console.log(data);  
   $.ajax({
		    type :'POST',
		    crossDomain: true,
			dataType : 'json',
			contentType : "application/json",
		    url:'api/update',
		    headers: {
		    	 'Authorization': $("#token").val() 
		    	 },
		    data:data,
		    success: function (response) {
		    	
		    	 if(response.code == 200)
		    		 {
		    		 alert("Profile  Updated Sucessfully");
			    		
		    	  location.reload();
		    		 
		    		 }
		    	   else
		    		 {
		    		 alert("Profile Not Updated Sucessfully");
		    		 location.reload();
		    		 }
		   }
		}); 
	}
   
 }
 

function createJson() {
	var firstName= $("#firstName").val();
	var lastName= $("#lastName").val();
	var address=$("#address").val();
	var mobileNo= $("#phonenumber").val();
	var email=$("#email").val();

	var userData = {
		"first_name" : firstName,
		"last_name" : lastName,
		"address":address,
		"contact_info": mobileNo,
		"email":email
	};
	return userData;
}

 
 $("#file-input").change(function(){
	 readURL(this);
 });
 
 function readURL(input) {
 if (input.files && input.files[0]) {
 var reader = new FileReader();
 if(input.files[0].size>1048576) {
 alert('File size is larger than 1MB!');
 return;
 }
 if(input.files[0]['type'] != 'image/jpeg' && input.files[0]['type'] != 'image/png' && input.files[0]['type'] != 'image/jpg'){
 alert("Please select png,jpg or jpeg image.");
 return;
 } 
 reader.onload = function (e) {
 $('.profile-image').attr('src', e.target.result);
 }

 reader.readAsDataURL(input.files[0]);
 }
 }

 function updatepassword() {

 var updateData=create();
 if(updateData != false)
 {
 var data=JSON.stringify(updateData);
 $.ajax({
 type :'POST',
 crossDomain: true,
 dataType : 'json',

 contentType : "application/json",
 url:'updatepassword1',
 headers: {
 'Authorization': $("#token").val() 
 },
 data:data,

 success: function (response) {
	 
 if(response == true){
alert("password updated successfully");
 }
 else{
 alert("Old password not correct");
 }
 }
 }); 

 }
 }


 function create() {
 var password=$("#password").val();
 var confirmPassword=$("#confirmpassword").val();
 var oldPassword=$("#oldpassword").val();
 var passwordvalid = /^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/;
 if(oldPassword== null || oldPassword.length<=0)
 {
 alert("Old Password cannot be empty");
 return false;
 }
 if(password== null || password.length<=0)
 {
 alert("New Password cannot be empty");
 return false;
 }

 if(confirmPassword== null || confirmPassword.length<=0)
 {
 alert("Confirm password cannot be empty");
 return false;
 }
 if(password==confirmPassword )
 {
 if(passwordvalid.test(password))
 {
 try{
 var userData = {
 "password" : password,
 "confirmPassword":confirmPassword,
 "oldPassword":oldPassword
 };
 }
 catch(e){
 }
 return userData;
 }
 else
 {
 alert("Must be at least 8 characters 1 number, 1 lowercase, 1 uppercase letter , 1 special character from @#$%&");
 return false;
 }
 }
 else{
 alert("New Password & Confirm Password does not match");
 return false;
 }
 }

  function uploadFile()
 {
 debugger;
  var files=$("#file-input").val();
 var fileInput = document.getElementById('file-input');

 var file = fileInput.files[0];
 var formData = new FormData();
 formData.append('file', file);
 $.ajax({
 url : "saveprofilepicture",
 data : formData,
 type : "POST",
 contentType: false,
 processData: false, 
 contentType:false,
 success : function(result) {
	 
console.log(result);

//location.href = 'myprofile';
 //...;
 },
 error : function(result){
 //...;
 }
 });
 }
 
/*  function uploadFile()
 {
	 debugger;
	 var fileInput = document.getElementById('file-input');

	 var file = fileInput.files[0];
	 
 var form = new FormData();
 form.append("image", file);
 
 $.ajax({
	 
	  async: true,
	   
	   url: "https://qa.eluminate.in/api/profile/image/2/",
	   type: "PUT",
	   headers: {
	     "Authorization": "Token eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNTYzNjkwMzM3LCJpc19zdGFmZiI6ZmFsc2V9.cJ-cKrL9_2Q-reYppK_I8d1iA6olFYTkYLDFV8Bx6Jo",
	     "cache-control": "no-cache"
	   },
	   processData: false,
	   contentType: false,
	   mimeType: "multipart/form-data",
	   data: form,
	   
	   success : function(result) {
			 
		   console.log(result);

		     },
	 
 })
 }
 */
/*  var settings = {
   "async": true,
   "crossDomain": true,
   "url": "https://dev.eluminate.in/api/profile/image/2/",
   "method": "PUT",
   "headers": {
     "authorization": "Token eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MiwiZXhwIjoxNTYzNjkwMzM3LCJpc19zdGFmZiI6ZmFsc2V9.cJ-cKrL9_2Q-reYppK_I8d1iA6olFYTkYLDFV8Bx6Jo",
     "cache-control": "no-cache"
   },
   "processData": false,
   "contentType": false,
   "mimeType": "multipart/form-data",
   "data": form
 }

 $.ajax(settings).done(function (response) {
   console.log(response);
 });
 }
  */
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 $(document).ready(function(){
		$(".requireFld").fadeOut();
	    $( "#firstName" ).keypress(function(e) {
	        if((e.keyCode >32 && e.keyCode<65) || e.keyCode>123 ){
				//alert(String.fromCharCode(e.keyCode)+' is not a valid character');
				/* $("#nameError").text("not valid");
				$("#name").css("border-color", "#c95b5b");
				$("#nameError").fadeIn();
				//$("#name").focus(); */
				 e.preventDefault();
	        }
				});
	    $( "#lastName" ).keypress(function(e) {
	        if((e.keyCode >32 && e.keyCode<65) || e.keyCode>123 ){
				//alert(String.fromCharCode(e.keyCode)+' is not a valid character');
				/* $("#nameError").text("not valid");
				$("#name").css("border-color", "#c95b5b");
				$("#nameError").fadeIn();
				//$("#name").focus(); */
				 e.preventDefault();
	        }
				});
		
			 $( "#phonenumber" ).keypress(function(e) {
				 var str=$('#phonenumber').val();
				
				 if((e.keyCode>32 && e.keyCode<48) || e.keyCode>57 || str.length>9){
					//alert('number not valid');
					 e.preventDefault();
				 }
				 
				 
			 });
		});

 function validate() {
		debugger;
		var flag = true;
		if ($('#firstName').val().trim() == '') {
			$("#firstNameError").fadeIn();
			flag = false;
		}
		if ($('#lastName').val().trim() == '') {
			$("#lastNameError").fadeIn();
			flag = false;
		}
		if ($('#address').val().trim() == '') {
			$("#addressError").fadeIn();
			flag = false;
		}
		if ($('#phonenumber').val().trim() == '') {
			$("#phonenumberError").fadeIn();
			flag = false;
		}
		if ($('#phonenumber').val().length<10) {
			$("#phonenumberError").fadeIn();
			flag = false;
		}
		return flag;
	}

	function catvalidate() {
		if ($('#firstName').val().length > 0) {
			$("#firstNameError").fadeOut();
		}
		if ($('#lastName').val().length > 0) {
			$("#lastNameError").fadeOut();
		}
		if ($('#address').val().length > 0) {
			$("#addressError").fadeOut();
		}
		if ($('#phonenumber').val().length > 0) {
			$("#phonenumberError").fadeOut();
		}
	}





/* $("#file-input").change(function(){
	readURL(this);
	});
	function readURL(input) {
	if (input.files && input.files[0]) {
	var reader = new FileReader();
	if(input.files[0].size>1048576) {
	alert('File size is larger than 1MB!');
	return;
	}
	if(input.files[0]['type'] != 'image/jpeg' && input.files[0]['type'] != 'image/png' && input.files[0]['type'] != 'image/jpg'){
	alert("Please select png,jpg or jpeg image.");
	return;
	} 
	reader.onload = function (e) {
	$('.profile-image').attr('src', e.target.result);
	}

	reader.readAsDataURL(input.files[0]);
	}
	}

	function updatepassword() {

	var updateData=createJson();
	if(updateData != false)
	{
	var data=JSON.stringify(updateData);
	$.ajax({
	type :'POST',
	crossDomain: true,
	dataType : 'json',

	contentType : "application/json",
	url:'updatepassword1',
	headers: {
	'Authorization': $("#token").val() 
	},
	data:data,

	success: function (response) {
	if(respone='true'){
	alert("password updated successfully");
	}
	else{
	alert("error in updation");
	}
	}
	}); 

	}
	}


	function createJson() {
	var password=$("#password").val();
	var confirmPassword=$("#confirmpassword").val();
	var passwordvalid = /^.*(?=.{8,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/;
	if(password== null || password.length<=0)
	{
	alert("New Password cannot be empty");
	return false;
	}
	if(confirmPassword== null || confirmPassword.length<=0)
	{
	alert("Confirm pssword cannot be empty");
	return false;
	}
	if(password==confirmPassword )
	{
	if(passwordvalid.test(password))
	{
	try{
	var userData = {
	"password" : password,
	};
	}
	catch(e){
	}
	return userData;
	}
	else
	{
	alert("Must be at least 8 characters 1 number, 1 lowercase, 1 uppercase letter , 1 special character from @#$%&");
	return false;
	}
	}
	else{
	alert("password & Confirm Password does not match");
	return false;
	}
	}
 */
 
</script>




