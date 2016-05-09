systemModule.controller("bugCtrl", ["$scope", "$rootScope", "jbase", "getMsg", "api", "NgTableParams", function (e, t, a, o, s, l) {
    e.f = {}, t.reset(function () {
        s.bug.delete({id: t.delId}, function () {
            e.tableParams.reload()
        })
    }), e.tableParams = new l({page: 1, count: 10, sortOrder: "desc", sortName: "date"}, {
        getData: function (t, a) {
            s.bug.list(a.$params, function (o) {
                e.list = o.data.list, a.total(o.data.total), t.resolve(e.list)
            })
        }
    }), e.edit = function (t) {
        e.f = t, e.editor.setValue(t.des)
    }, e.save = function () {
        e.f.des = e.editor.getValue(), s.bug.save(e.f, function () {
            e.tableParams.reload(), e.clean()
        })
    }, e.clean = function () {
        e.f = {}, e.editor.setValue("")
    }
}]);