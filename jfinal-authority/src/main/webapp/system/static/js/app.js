var duoshuoQuery = {short_name: "jayqqaa12"}, systemModule = angular.module("systemModule", []), AdminApp = angular.module("AdminApp", ["ngAnimate", "ngSanitize", "ui.select", "ngTable", "jbaseModule", "systemModule"]);
_uri = function (e) {
    return "" + e
}, AdminApp.run(["$rootScope", "$state", "$stateParams", "$window", function (e, t, n, o) {
    e.$state = t, e.$stateParams = n, e.$window = o, e.system = {}, e.app = {}, e.push = {}, e.go = function (e) {
        o.location.href = e
    }, e.delete = function (t) {
        e.confirm = !0, e.delId = t
    }, e.reset = function (t) {
        e.ok = !1, e.confirm = !1, e.confirmBatch = !1, e.okBatch = !1, e.okWatch && e.okWatch(), e.okWatch = e.$watch("ok", function (n) {
            n && (e.ok = !1, t && t())
        })
    }, e.hasPermission = function (t) {
        return -1 == e.urls.indexOf(t) ? !0 : -1 != e.auth_urls.indexOf(t) ? !0 : !1
    }, e.$on("$stateChangeError", function (e, t, n, i, a, s) {
        404 == s.status && (o.location.href = "#/system/404"), 401 == s.status && (o.location.href = "/")
    })
}]), AdminApp.config(["$translateProvider", function (e) {
    e.useStaticFilesLoader({prefix: _uri("system/static/i18n/"), suffix: ".json"}), e.preferredLanguage("zh_CH")
}]), AdminApp.config(["$stateProvider", "$urlRouterProvider", function (e, t) {
    e.state("login", {url: "/login", templateUrl: _uri("system/tpls/login.html")}).state("main", {
        url: "/:module/:name?id=",
        views: {
            "": {templateUrl: _uri("system/tpls/main.html")}, "index@main": {
                templateUrl: function (e) {
                    return window.scrollTo(0, 0), e.name || (e.name = "index"), e.module || (e.module = "system"), _uri("") + e.module + "/tpls/" + e.name + ".html"
                }
            }
        }
    }), t.otherwise("/login")
}]);