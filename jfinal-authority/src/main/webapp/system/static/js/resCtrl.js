systemModule.controller("resCtrl", ["$scope", "$rootScope", "$stateParams", "$window", "api", "D", "jbase", "$filter", "NgTableParams", function (e, t, i, n, c, o, s, a, l) {
    i.id && (e.showBack = !0), e.icons = o.icons, e.f = {}, t.reset(function () {
        c.res.delete({id: e.delId}, function () {
            e.getList({id: i.id}), t.refresh()
        })
    }), e.tableParams = new l({page: 1, count: 100}, {
        counts: [], getData: function (t) {
            t.resolve(e.list)
        }
    }), e.getList = function (t) {
        c.res.list(t, function (t) {
            e.list = t.data.list, e.parent = t.data.parent, e.tableParams.reload()
        })
    }, e.getList({id: i.id}), e.go = function (e) {
        n.location.href = "#/system/res?id=" + e
    }, e.back = function () {
        n.history.back()
    }, e.post = function () {
        e.f.iconCls = e.f.icon.name, e.f.icon = null;
        var n = s.toParam("res", e.f, ["childSize"]);
        c.res.save(n, function () {
            e.showModal = !1, e.getList({id: i.id}), t.refresh()
        })
    }, e.edit = function (t) {
        e.f = s.find("id", t, e.list), e.f.icon = {name: e.f.iconCls}, e.title = "编辑", e.showModal = !0
    }, e.add = function () {
        e.f = {seq: 10, type: 1, icon: {name: "am-icon-folder"}}, e.parent && (e.f.pid = e.parent.id), e.showModal = !0, e.title = "添加"
    }, e.delete = function (i) {
        t.confirm = !0, e.delId = i
    }, e.batchDelete = function () {
        var n = [];
        angular.forEach(e.checkboxes.items, function (e, t) {
            e && n.push(t)
        }), 0 != n.length && (t.confirmBatch = !0, t.$watch("okBatch", function (o) {
            o && c.res.batchDelete({ids: n.join(",")}, function () {
                e.getList({id: i.id}), t.refresh()
            })
        }))
    }, e.checkboxes = {checked: !1, items: {}}, e.$watch("checkboxes.checked", function (t) {
        angular.forEach(e.list, function (i) {
            angular.isDefined(i.id) && (e.checkboxes.items[i.id] = t)
        })
    }), e.$watch("checkboxes.items", function () {
        if (e.list) {
            var t = 0, i = 0, n = e.list.length;
            angular.forEach(e.list, function (n) {
                t += e.checkboxes.items[n.id] || 0, i += !e.checkboxes.items[n.id] || 0
            }), (0 == i || 0 == t) && (e.checkboxes.checked = t == n), angular.element(document.getElementById("select_all")).prop("indeterminate", 0 != t && 0 != i)
        }
    }, !0)
}]);