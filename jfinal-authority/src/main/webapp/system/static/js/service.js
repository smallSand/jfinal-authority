Code = {
    LOADING: 101,
    LOGING: 102,
    NULL: 404,
    ERROR: 500,
    RELOGIN: 1001,
    USER_NOT_EXIT: 1003,
    USER_PWD_ERROR: 1004,
    USER_FRRZE: 1005,
    USER_ATTEMPT: 1006,
    USER_AUTH: 1007,
    EMAIL_VAL_ERROR: 1100,
    EMAIL_EXIT: 1101,
    CAPTCHA_ERROR: 1200,
    FILE_TYPE_ERROR: 11e3,
    IMG_SIZE_ERROR: 12e3,
    APK_ERROR: 13e3,
    PUSH_ERROR: 9e3
}, systemModule.factory("getMsg", [function () {
    var e = function (e) {
        return e == Code.LOADING ? "正在加载..." : e == Code.ERROR ? "服务器错误" : 403 == e ? "没有操作权限 请联系管理员" : e == Code.USER_NOT_EXIT ? "用户不存在" : e == Code.USER_PWD_ERROR ? "用户名或密码错误" : e == Code.USER_FRRZE ? "用户被封了" : e == Code.USER_ATTEMPT ? "密码错误次数太多,请一小时后再试" : e == Code.USER_AUTH ? "没有权限" : e == Code.LOGING ? "已经登录过正在自动登录..." : e == Code.EMAIL_VAL_ERROR ? "邮箱验证错误" : e == Code.EMAIL_EXIT ? "邮箱已经存在了" : e == Code.IMG_SIZE_ERROR ? "图片大小错误" : e == Code.APK_ERROR ? "apk解析错误请重新上传" : e == Code.FILE_TYPE_ERROR ? "文件上传类型错误" : e == Code.CAPTCHA_ERROR ? "验证码错误" : e == Code.PUSH_ERROR ? "推送设置错误请检查" : void 0
    };
    return e
}]), systemModule.factory("api", ["$rootScope", "jbase", "getMsg", function (e, a, n) {
    var o = {
        post: function (o, m, i) {
            layer.load(), a.post(o, m, function (a) {
                500 == a.code ? e.error = !0 : a.code > 200 && (e.warn = !0, layer.msg(n(a.code))), i && i(a), layer.closeAll("loading")
            })
        }, upload: function (o, m, i, c, t) {
            return o.target.files.length > 0 && -1 == o.target.files[0].name.indexOf(t) ? void layer.msg(n(Code.FILE_TYPE_ERROR)) : void a.upload(o, m, i, function (a) {
                500 == a.code ? e.error = !0 : a.code > 200 && (e.warn = !0, layer.msg(n(a.code))), c && c(a)
            })
        }, login: {
            captchaUrl: function () {
                return _uri("captcha?time=") + (new Date).getTime()
            }, logout: function (e) {
                o.post(_uri("logout"), null, e)
            }, login: function (e, a) {
                o.post(_uri("login"), e, a)
            }, rsa: function (e) {
                o.post(_uri("rsa"), null, e)
            }
        }, res: {
            urls: function (e) {
                o.post(_uri("system/res/urls"), null, e)
            }, ztree: function (e) {
                o.post(_uri("system/res/ztree"), null, e)
            }, tree: function (e, a) {
                o.post(_uri("system/res/tree"), e, a)
            }, list: function (e, a) {
                o.post(_uri("system/res/list"), e, a)
            }, save: function (e, a) {
                o.post(_uri("system/res/save"), e, a)
            }, "delete": function (e, a) {
                o.post(_uri("system/res/delete"), e, a)
            }, batchDelete: function (e, a) {
                o.post(_uri("system/res/batchDelete"), e, a)
            }
        }, role: {
            list: function (e) {
                o.post(_uri("system/role/list"), null, e)
            }, save: function (e, n) {
                var m = a.toParam("role", e, ["res_ids"]);
                o.post(_uri("system/role/save"), m, n)
            }, "delete": function (e, a) {
                o.post(_uri("system/role/delete"), e, a)
            }
        }, user: {
            freeze: function (e, a) {
                o.post(_uri("system/user/freeze"), e, a)
            }, list: function (e, a) {
                o.post(_uri("system/user/list"), e, a)
            }, save: function (e, a) {
                o.post(_uri("system/user/save"), e, a)
            }, "delete": function (e, a) {
                o.post(_uri("system/user/delete"), e, a)
            }, getUser: function (e, a) {
                o.post(_uri("system/user/getUser"), e, a)
            }
        }, email: {
            sendValEmail: function (e, a) {
                o.post(_uri("system/email/sendValEmail"), e, a)
            }, valEmail: function (e, a) {
                o.post(_uri("system/email/valEmail"), e, a)
            }, existEmail: function (e, a) {
                o.post(_uri("system/email/existEmail"), e, a)
            }
        }, log: {
            list: function (e, a) {
                o.post(_uri("system/log/list"), e, a)
            }, browser: function (e) {
                o.post(_uri("system/log/browser"), null, e)
            }, data: function (e) {
                o.post(_uri("system/log/data"), null, e)
            }, error: function (e) {
                o.post(_uri("system/log/error"), null, e)
            }, chart: function (e) {
                o.post(_uri("system/log/chart"), null, e)
            }
        }, bug: {
            list: function (e, a) {
                o.post(_uri("system/bug/list"), e, a)
            }, save: function (e, n) {
                var m = a.toParam("bug", e);
                o.post(_uri("system/bug/save"), m, n)
            }, "delete": function (e, a) {
                o.post(_uri("system/bug/delete"), e, a)
            }, status: function (e, a) {
                o.post(_uri("system/bug/status"), e, a)
            }
        }
    };
    return o
}]), systemModule.factory("D", ["$rootScope", function () {
    var e = {icons: [{name: "am-icon-at"}, {name: "am-icon-bell-slash"}, {name: "am-icon-bell-slash-o"}, {name: "am-icon-bicycle"}, {name: "am-icon-binoculars"}, {name: "am-icon-birthday-cake"}, {name: "am-icon-futbol-o"}, {name: "am-icon-google-wallet"}, {name: "am-icon-ils"}, {name: "am-icon-ioxhost"}, {name: "am-icon-lastfm"}, {name: "am-icon-lastfm-square"}, {name: "am-icon-line-chart"}, {name: "am-icon-meanpath"}, {name: "am-icon-newspaper-o"}, {name: "am-icon-paint-brush"}, {name: "am-icon-paypal"}, {name: "am-icon-pie-chart"}, {name: "am-icon-yelp"}, {name: "am-icon-anchor"}, {name: "am-icon-archive"}, {name: "am-icon-area-chart"}, {name: "am-icon-arrows"}, {name: "am-icon-arrows-h"}, {name: "am-icon-arrows-v"}, {name: "am-icon-asterisk"}, {name: "am-icon-at"}, {name: "am-icon-automobile"}, {name: "am-icon-ban"}, {name: "am-icon-bank"}, {name: "am-icon-bar-chart"}, {name: "am-icon-bar-chart-o"}, {name: "am-icon-barcode"}, {name: "am-icon-bookmark"}, {name: "am-icon-bookmark-o"}, {name: "am-icon-briefcase"}, {name: "am-icon-bug"}, {name: "am-icon-building"}, {name: "am-icon-building-o"}, {name: "am-icon-bullhorn"}, {name: "am-icon-bullseye"}, {name: "am-icon-bus"}, {name: "am-icon-cab"}, {name: "am-icon-calculator"}, {name: "am-icon-calendar"}, {name: "am-icon-calendar-o"}, {name: "am-icon-cc"}, {name: "am-icon-certificate"}, {name: "am-icon-check"}, {name: "am-icon-check-circle"}, {name: "am-icon-check-circle-o"}, {name: "am-icon-check-square"}, {name: "am-icon-check-square-o"}, {name: "am-icon-child"}, {name: "am-icon-circle"}, {name: "am-icon-circle-o"}, {name: "am-icon-circle-o-notch"}, {name: "am-icon-circle-thin"}, {name: "am-icon-clock-o"}, {name: "am-icon-close"}, {name: "am-icon-cloud"}, {name: "am-icon-compass"}, {name: "am-icon-copyright"}, {name: "am-icon-credit-card"}, {name: "am-icon-crop"}, {name: "am-icon-crosshairs"}, {name: "am-icon-cube"}, {name: "am-icon-cubes"}, {name: "am-icon-cutlery"}, {name: "am-icon-dashboard"}, {name: "am-icon-database"}, {name: "am-icon-desktop"}, {name: "am-icon-dot-circle-o"}, {name: "am-icon-download"}, {name: "am-icon-edit"}, {name: "am-icon-ellipsis-h"}, {name: "am-icon-ellipsis-v"}, {name: "am-icon-envelope"}, {name: "am-icon-exclamation"}, {name: "am-icon-exclamation-circle"}, {name: "am-icon-exclamation-triangle"}, {name: "am-icon-external-link"}, {name: "am-icon-external-link-square"}, {name: "am-icon-eye"}, {name: "am-icon-eye-slash"}, {name: "am-icon-eyedropper"}, {name: "am-icon-fax"}, {name: "am-icon-female"}, {name: "am-icon-fighter-jet"}, {name: "am-icon-file-archive-o"}, {name: "am-icon-file-audio-o"}, {name: "am-icon-file-code-o"}, {name: "am-icon-file-excel-o"}, {name: "am-icon-file-image-o"}, {name: "am-icon-fire"}, {name: "am-icon-fire-extinguisher"}, {name: "am-icon-flag"}, {name: "am-icon-flag-checkered"}, {name: "am-icon-flag-o"}, {name: "am-icon-flash"}, {name: "am-icon-flask"}, {name: "am-icon-folder"}, {name: "am-icon-folder-o"}, {name: "am-icon-folder-open"}, {name: "am-icon-home"}, {name: "am-icon-eur"}, {name: "am-icon-euro"}, {name: "am-icon-gbp"}, {name: "am-icon-ils"}, {name: "am-icon-inr"}, {name: "am-icon-jpy"}, {name: "am-icon-krw"}, {name: "am-icon-money"}, {name: "am-icon-file-text-o"}, {name: "am-icon-files-o"}, {name: "am-icon-floppy-o"}, {name: "am-icon-font"}, {name: "am-icon-header"}, {name: "am-icon-indent"}, {name: "am-icon-italic"}, {name: "am-icon-link"}, {name: "am-icon-list"}, {name: "am-icon-list-alt"}, {name: "am-icon-list-ol"}, {name: "am-icon-user-md"}, {name: "am-icon-wheelchair"}]};
    return e
}]);