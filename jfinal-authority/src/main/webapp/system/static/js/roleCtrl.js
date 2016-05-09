systemModule.controller("roleCtrl", ["$scope", "$rootScope", "$filter", "api", "jbase", "NgTableParams", function (e, t, r, s, o, a) {
    t.reset(function () {
        s.role.delete({id: t.delId}, function () {
            e.tableParams.reload(), t.refresh()
        })
    }), e.tableParams = new a({page: 1, count: 100}, {
        getData: function (t) {
            s.role.list(function (s) {
                s = r("orderBy")(s, "seq"), e.list = s, t.resolve(e.list)
            })
        }
    }), e.edit = function (r) {
        var s = o.find("id", r.id, e.list);
        delete s.res_names, t.system.role = s, t.go("#/system/role.edit?id=" + r.id)
    }
}]), systemModule.controller("roleEditCtrl", ["$scope", "$rootScope", "$filter", "api", "jbase", function (e, t, r, s, o) {
    e.f = {}, e.title = "添加角色", e.ztree = {}, t.$stateParams.id && (e.f = t.system.role, e.readonly = !0), e.submit = function () {
        var r = e.ztree.getCheckedNodes(!0);
        e.f.res_ids = o.toArray("id", r).join(","), e.f.pname = null, s.role.save(e.f, function () {
            t.refresh(), t.go("#/system/role")
        })
    }, s.res.ztree(function (t) {
        if (e.setting = {view: {selectedMulti: !1}, check: {enable: !0}, data: {simpleData: {enable: !0}}}, e.f.res_ids) {
            var r = e.f.res_ids.split(",");
            for (var s in t.data.ztree)for (var o in r)t.data.ztree[s].id == r[o] && (t.data.ztree[s].checked = !0)
        }
        e.znodes = t.data.ztree
    })
}]);