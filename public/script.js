'use strict';

const URL_USER = "/rest/user/";

var mon_app = angular.module('mon_app', []);

mon_app.controller('userController', [ '$scope', '$http', function($scope, $http) {
	$scope.list = [];
    $scope.current = {};
    $scope.displayForm = false;

    $scope.fct_list = function(){
        $http.get(URL_USER).success(function(response) {
            $scope.list = response;
        }).error(function(error){
            console.log("error:" + error);
        });
        $scope.current = {};
        $scope.displayForm = false;
    }

    $scope.fct_list();

    $scope.updateUser = function(user){
        $scope.current = user;
        $scope.displayForm = true;
    }

    $scope.createUser = function(){
        $scope.current = {};
        $scope.displayForm = true;
    }

	$scope.editUser = function(){
		$http({
			method: 'POST',
			headers: [{'Content-Type': 'application/json'}],
			url: URL_USER,
			data: $scope.current,
		}).success(function (data) {
            $scope.fct_list();
		}).error(function (error) {
            console.log("error:" + error);
		});
	}

	$scope.deleteUser = function(user){
		$http({
			method: 'DELETE',
			url: URL_USER + user.id,
		}).success(function (data) {
            $scope.fct_list();
		}).error(function (error) {
            console.log("error:" + error);
		});
	}

} ]);
