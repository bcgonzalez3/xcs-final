// create the angularjs application

var app = angular.module('socialnetApp', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider) {

    $routeProvider
    .when('/',{
        templateUrl: 'views/home.html',
        controller: 'HomeController'
    })
    .when('/otherpage',{
        templateUrl: 'views/otherpage.html',
        controller: 'OtherPageController'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);