var app = angular.module("check_form", []);

app.controller("CheckCtrl", function ($scope, $http) {
    $scope.checks = [];
    $scope.page;

    $scope.init = function (userId, pageNumber) {
        $http({
            method: "GET",
            url: "/senior_cashier_menu/cashier/" + userId + "/checks?page=" + pageNumber,
            headers: {"Content-Type": "application/json"}
        }).then(function (data) {
            $scope.page = data.data;
            $scope.checks = data.data.content;
            console.log(data.data);
        });
    };
});