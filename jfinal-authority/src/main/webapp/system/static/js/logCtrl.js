systemModule.controller("logCtrl", ["$scope", "$rootScope", "jbase", "getMsg", "api", "NgTableParams", function (t, a, e, o, s, r) {
    t.chart = !1, t.tableParams = new r({page: 1, count: 10, sortOrder: "desc", sortName: "date"}, {
        getData: function (a, e) {
            s.log.list(e.$params, function (o) {
                t.list = o.data.list, e.total(o.data.total), a.resolve(t.list)
            })
        }
    }), t.showChart = function () {
        t.chart = !t.chart, s.log.chart(function (t) {
            var a = echarts.init(document.getElementById("main"));
            a.setOption(t)
        })
    }, t.setDate = function () {
        t.tableParams.$params.dateStart = t.dateStart, t.tableParams.$params.dateEnd = t.dateEnd, console.log("setdate")
    }
}]);