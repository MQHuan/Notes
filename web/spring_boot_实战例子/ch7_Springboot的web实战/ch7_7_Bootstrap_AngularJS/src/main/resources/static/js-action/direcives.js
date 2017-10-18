
// 定义一个指令名为datePicker
actionApp.directive('datePicker', function () {
    return {
        restrict: 'AC',// 限制为属性指令和样式指令
        link: function (scope, elem, attrs) {// 使用link 方法来定义指令，在link方法内可使用当前scope,当前元素及元素属性
            // scope.treeObj = $.fn.zTree.init(elem, scope.settings);
            // 初始化jqueryui的dataPicker (jquery的写法是$('#id').datePicker())
            elem.datepicker();
        }
    };
});