systemModule.controller("mainCtrl", ["$scope", "$rootScope", "$state", "$timeout", "jbase", "getMsg", "api", function (o, e, l, n, t, r, u) {
    e.loadingTitle = r(Code.LOADING), e.refresh = function () {
        u.res.tree(null, function (o) {
            e.tree = o.data.tree
        }), u.res.urls(function (o) {
            e.urls = o.data.urls, e.auth_urls = o.data.auth_urls
        })
    }, e.loadClosed = function () {
    }, t.AJAX_FAIL_CALLBACK = function (o, n) {
        401 == n ? l.go("login") : 403 == n ? layer.msg(r(n)) : e.error = !0, console.log("fail callback  code=" + n), layer.closeAll("loading")
    }, e.user || (u.login.rsa(function (o) {
        o.code != Code.LOGING && l.go("login"), e.user = o.data.user
    }), e.refresh()), o.logout = function () {
        u.login.logout(function () {
            l.go("login"), e.user = null
        })
    }
}]);