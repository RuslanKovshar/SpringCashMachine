var app = angular.module("check_form", []);

app.controller("CheckCtrl", function ($scope, $http) {
    $scope.products = [];

    $http({
        method: "GET",
        url: "/get_products",
        headers: {"Content-Type": "application/json"}
    }).then(function (data) {
        $scope.products = data.data.products;
    });

/*    $scope.getUrl = function (id) {
        return '/account/users/' + id + '/account_history';
    };*/
});