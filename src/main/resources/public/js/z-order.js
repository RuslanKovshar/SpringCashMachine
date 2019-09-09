var app = angular.module("z-order_form", []);

app.controller("ZCtrl", function ($scope, $http) {
    $scope.checks = [];

    $scope.init = function (userId) {
        $http({
            method: "GET",
            url: "/api/cashier/" + userId + "/z-report",
            headers: {"Content-Type": "application/json"}
        }).then(function (data) {
            $scope.checks = data.data;
            console.log(data.data);
        });
    };
});