var app = angular.module("check_form", []);

app.controller("CheckCtrl", function ($scope, $http) {
    $scope.checks = [];

    $scope.init = function (url) {
        $http({
            method: "GET",
            url: url,
            headers: {"Content-Type": "application/json"}
        }).then(function (data) {
            $scope.checks = data.data.content;
            console.log(data.data);
        });
    };
});