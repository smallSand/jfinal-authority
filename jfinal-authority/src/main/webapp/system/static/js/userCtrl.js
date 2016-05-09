systemModule.controller("userCtrl", ["$scope", "$rootScope", "$stateParams", "$window", "api", "D", "jbase", "NgTableParams", function (e, i, t, a, l, o, n, s) {
    i.reset(function () {
        l.user.delete({id: i.delId}, function () {
            e.tableParams.reload()
        })
    }), e.tableParams = new s({page: 1, count: 10}, {
        getData: function (i, t) {
            l.user.list(t.$params, function (t) {
                e.list = t.data.list, i.resolve(e.list)
            })
        }
    }), e.edit = function (e) {
        a.location.href = "#/system/user.edit?id=" + e.id
    }, e.freeze = function (i) {
        l.user.freeze({id: i.id, status: i.status}, function () {
            e.tableParams.reload()
        })
    }
}]), systemModule.controller("userEditCtrl", ["$scope", "$rootScope", "$stateParams", "$window", "api", "D", "jbase", function (e, i, t, a, l, o, n) {
    e.title = "新增用户", e.modal = !1, e.modalPwd = !1, e.modalEmail = !1, e.emailError = !1, e.resend = !0, e.time = 60, e.f = {icon: _uri("system/static/i/10.jpg")}, l.role.list(function (i) {
        e.roles = i
    }), t.id && (e.title = "编辑用户", l.user.getUser({id: t.id}, function (i) {
        e.f = i.data, e.f.role_ids && (e.f.role_ids = parseInt(e.f.role_ids)), console.log(e.f)
    })), e.setIcon = function (i) {
        e.modal = !1, e.f.icon = _uri("system/static/i/") + i + ".jpg"
    }, e.submit = function () {
        var i = n.toParam("user", e.f, ["role_ids"]);
        l.user.save(i, function (i) {
            200 == i.code && (e.f.id ? a.location.href = "#/system/user" : e.reSendEmail())
        })
    }, e.reSendEmail = function (i) {
        var t = function () {
            e.time > 0 ? (e.time--, n.delay(t, null, 1e3)) : (e.time = null, e.resend = !1)
        };
        l.email.sendValEmail({email: i}, function () {
            e.resend = !0, i || (e.modalEmail = !0), e.time = 60, t()
        })
    }, e.showModalPwd = function () {
        e.reSendEmail(e.f.email), e.modalPwd = !0
    }, e.modifyPwd = function () {
        e.valEmail(function () {
            e.f.pwd = e.pwd, e.modalPwd = !1
        })
    }, e.checkEmail = function (i) {
        return e.f.email && $(i.field).is(".email") ? $.ajax({
            url: _uri("system/email/existEmail?email=") + e.f.email,
            dataType: "json"
        }).then(function (e) {
            return i.valid = 200 == e.code ? !0 : !1, i
        }, function () {
            return i.valid = !1, i
        }) : void 0
    }, e.valEmail = function (i) {
        l.email.valEmail({val: e.val}, function (t) {
            e.modalEmail = !1, 200 == t.code && (i || n.delay(function () {
                a.location.href = "#/system/user"
            }, null, 555), console.log(i), i && i(t), e.val = null)
        })
    }
}]);