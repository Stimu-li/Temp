angular.module('orderApp', [])
.controller('OrderController', ['$scope', function($scope) {
    $scope.order = {};
    $scope.message = '';

    $scope.submitOrder = function() {
        if ($scope.orderForm.$valid) {
            $scope.message = `Order submitted! \nName: ${$scope.order.name} \nEmail: ${$scope.order.email} \nProduct: ${$scope.order.product} \nQuantity: ${$scope.order.quantity}`;
            // Reset form
            $scope.order = {};
        } else {
            $scope.message = 'Please fill in all required fields.';
        }
    };
}]);
