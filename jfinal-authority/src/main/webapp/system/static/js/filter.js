systemModule.filter("optionType", function () {
    var t = function (t) {
        return 1 == t ? "访问" : 2 == t ? "登录" : 3 == t ? "保存" : 5 == t ? "删除" : void 0
    };
    return t
}), systemModule.filter("userStatus", function () {
    var t = function (t) {
        return 1 == t ? "正常" : 2 == t ? "冻结" : void 0
    };
    return t
});