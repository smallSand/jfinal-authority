systemModule.controller("indexCtrl", ["$scope", "$rootScope", "jbase", "getMsg", "api", function (o, t, a, e, n) {
    n.log.browser(function (t) {
        o.browser = t.data
    }), n.log.data(function (t) {
        o.data = t.data
    })
}]);