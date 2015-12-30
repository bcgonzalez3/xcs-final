angular.module('socialnetApp')
.factory('userService', ['$http', function($http){

    return {
        getUsers: function(onSuccess, onFail) {
            //configure http auth 
            $http.defaults.headers.common.Authorization =
                'Basic '+btoa('usuario2:pass2');

            $http.get('http://localhost:8080/web-0.0.1-SNAPSHOT/api/user/login')
            .success(onSuccess)
            .error(onFail);
        }
    }
}]);