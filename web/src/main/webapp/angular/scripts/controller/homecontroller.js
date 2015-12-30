// retrieve the current app
var app = angular.module('socialnetApp');

// add a new controller
app.controller('HomeController', ['userService', '$scope', function(userService, $scope){


    $scope.users = [];

    userService.getUsers(function(users){
        $scope.users = users;
    },
    function(response){
        alert("error retrieving users")
    });


}]);