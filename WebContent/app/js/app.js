var app = angular.module('qBis', ['ngRoute']);

app.config(function ($routeProvider,$locationProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "resources/app/template/main.html",
            controller: "MainController"
        })
        .when("/termsofuse", {
            templateUrl: "resources/app/template/termsofuse.html",
            controller: "Temp"
        })
        .when("/privacypolicy", {
            templateUrl: "resources/app/template/privacypolicy.html",
            controller: "Temp"
        })
        .when("/faq", {
            templateUrl: "resources/app/template/faq.html",
            controller: "Temp"
        })
        .otherwise({
            template: "<h1>Nothing</h1><p>Nothing has been selected</p>"
        });
   /* $locationProvider.html5Mode(true).hashPrefix('*');*/
    /*$locationProvider.html5Mode(false);
    $locationProvider.hashPrefix('');*/
});