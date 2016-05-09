systemModule.controller("loginCtrl", ["$scope", "$rootScope", "$timeout", "$state", "jbase", "getMsg", "api", function (e, o, a, c, n, l, i) {
    e.remember = !1, e.click = !1, o.loadingTitle = l(Code.LOGING), e.changeCaptcha = function () {
        e.captchaUrl = i.login.captchaUrl()
    }, e.changeCaptcha(), i.login.rsa(function (a) {
        a.code == Code.LOGING ? (e.msg = l(a.code), o.loading = !0, n.delay(function () {
            o.loading = !1
        }, null, 555)) : (a.data.email && (e.email = a.data.email, e.remember = !0), e.exponent = a.data.exponent, e.modulus = a.data.modulus)
    }), o.loadClosed = function () {
        c.go("main")
    }, e.login = function (o) {
        if (o && o.preventDefault(), e.email && e.pwd && !e.click) {
            e.click = !0;
            var a = "email=" + e.email + "&pwd=" + e.pwd, t = n.rsa(e.exponent, e.modulus, a);
            i.login.login({key: t, remember: e.remember, code: e.code}, function (o) {
                200 == o.code ? c.go("main") : (e.changeCaptcha(), e.click = !1, e.msg = l(o.code), o.code == Code.CAPTCHA_ERROR && (e.captcha = !0))
            })
        }
    }
}]);