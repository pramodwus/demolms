app.controller('MainController', function ($scope, $http, $location, $timeout) {

    $scope.kfeatures = [];
    $scope.benefits = [];

    $scope.navClick = function (e, id) {

        $("#nav").find("li.active").removeClass("active");
        $(e.currentTarget).parent().addClass("active");

        $('html, body').animate({
            scrollTop: $('#' + id).offset().top
        }, 400);
    };


    $scope.goToTop = function () {

        $("html, body").scrollTop(0);
    };

    $scope.goToBlog = function () {
        window.location.href = "http://www.bellurbis.com/blog/qbis/";
    }

    $http.get("resources/app/js/data/kfeatures.json").then(function (response) {
        $scope.kfeatures = response.data;
        console.log(response.data);
    });

    $http.get("resources/app/js/data/benefits.json").then(function (response) {
        $scope.benefits = response.data;
        console.log(response.data);
    });
    
    /**
     * login for sending request for request demo. 
     */
    $scope.requestuser = {};
    $scope.requestuser.isDisabled = false; 
    $scope.requestuser.error = '';
    $scope.sendReqestDemoRequest = function(){
    	if($scope.requestuser.email!=null){
        	$scope.requestuser.isDisabled = true;
    		var reqbody = {		
    				 "email": $scope.requestuser.email
    		          };
    		$http.post("requestfordemo",reqbody).then(successCallback, errorCallback);
    		function successCallback(data, status, headers, config) {
    			$scope.requestuser.error = '';
    			$scope.requestuser.email = null;
    			showSuccessRequestResult();
    		};
    		function errorCallback(data, status, headers, config) {
    			$scope.requestuser.error = 'Something went wrong, try again.';
    			$timeout(function(){
    				$scope.requestuser.error = '';
    			},3000);
    		};
    	}
    	$scope.requestuser.isDisabled = false;
    };
    
    /**
     * login for sending request for contact us. 
     */
    $scope.contactus = {};
    $scope.contactus.error = '';
    $scope.contactus.isDisabled = false;
    $scope.sendContactUsRequest = function(){
    	if($scope.contactus.email!=null && $scope.contactus.name!=null){
        	$scope.contactus.isDisabled = true;
    		var reqbody = {		
    				 "email": $scope.contactus.email,
    				 "name":  $scope.contactus.name
    		          };
    		$http.post("sendcontactusrequest",reqbody).then(contactUsSuccessCallback, contactUsErrorCallback);
    		function contactUsSuccessCallback(data, status, headers, config) {
    			$scope.contactus.error = '';
    			$scope.contactus.email = null;
    			$scope.contactus.name = null;
    			showSuccessRequestResult();
    		};
    		function contactUsErrorCallback(data, status, headers, config) {
    			$scope.contactus.error = 'Something went wrong, try again.';
    			$timeout(function(){
    				$scope.contactus.error = '';
    			},2000);
    		};
    	}
    	 $scope.contactus.isDisabled = false;
    };
    
});


function showSuccessRequestResult(){
	$("#demo_request_success").modal('show');
	setTimeout(function(){$("#demo_request_success").modal('hide');},3000);
}