
// 定义模块actionApp,并依赖于路由模块ngRout
var actionApp = angular.module('actionApp', ['ngRoute']);

// 配置路由， 并注入$routeProvider来配置
actionApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/oper', {// /oper 为路由名称
        controller:'View1Controller', // controller 定义的是路由的控制器名称
        templateUrl:'views/view1.html', // templateUrl定义的是视图的真正地址
    }).when('/directive', {
        controller:'View2Controller',
        templateUrl:'views/view2.html',
    });
}]);