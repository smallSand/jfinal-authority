systemModule.directive("weather", function () {
    return {
        restrict: "A", link: function (t, e) {
            var n = {
                lang: {day: "白天", night: "夜晚", temp: "°C", wind: "级风", wangzimo: "12shu"}, convert: function (t) {
                    var e = {
                        cloudy: ["多云", "多云转阴", "晴转多云", "阴转多云"],
                        overcast: ["阴", "雾", "沙尘暴", "浮尘", "扬沙", "强沙尘暴"],
                        rainy: ["多云转小雨", "小雨转多云", "小雨", "中雨", "大雨", "暴雨", "大暴雨", "特大暴雨", "冻雨", "小雨转中雨", "中雨转大雨", "大雨转暴雨", "暴雨转大暴雨", "大暴雨转特大暴雨", "阵雨", "雷阵雨", "雷阵雨伴有冰雹"],
                        sleet: ["雨夹雪"],
                        snow: ["阵雪", "小雪", "中雪", "大雪", "暴雪", "小雪转中雪", "中雪转大雪", "大雪转暴雪", "中雪转小雪", "大雪转中雪", "暴雪转大雪"],
                        sunshine: ["晴"]
                    }, n = "", r = "";
                    for (r in e)if ($.inArray(t, e[r]) > -1) {
                        n = r;
                        break
                    }
                    return n || r || "sunshine"
                }
            };
            $.getScript("http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&day=0&city=&charset=utf-8", function () {
                var t, r = window.SWther.w;
                for (var t in r);
                r = r[t][0];
                {
                    var a = {
                        city: t,
                        date: SWther.add.now.split(" ")[0] || "",
                        day_weather: r.s1,
                        night_weather: r.s2,
                        day_temp: r.t1,
                        night_temp: r.t2,
                        day_wind: r.p1,
                        night_wind: r.p2
                    }, i = new Date(SWther.add.now).getHours(), h = i > 18 ? a.day_weather : a.night_weather;
                    e.weather({sky: n.convert(h), weatherData: a, config: n})
                }
            })
        }
    }
});