var app = angular.module("check_form", []);

app.controller("CheckCtrl", function ($scope, $http) {
    $scope.checks = [];
    $scope.page;

    $scope.init = function (orderType,userId, pageNumber) {
        $http({
            method: "GET",
            url: "/api/cashier/" + userId + "/" + orderType + "?page=" + pageNumber,
            headers: {"Content-Type": "application/json"}
        }).then(function (data) {
            $scope.page = data.data;
            $scope.checks = data.data.content;
            console.log(data.data);
        });
    };
});