systemModule.controller("errorCtrl", ["$scope", "$rootScope", "jbase", "getMsg", "api", function (o, r, e, t, l) {
    l.log.error(function (r) {
        o.log = r
    })
}]);