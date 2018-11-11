;(function ($) {
    var calorie_function = function () {
        var self = this;
        this.body = $(document.body);
        this.autoParentHeight();
        // flag与后台的flag相对应。用来判断是早中还是晚
        this.calorieFlag = 0;
        // timeContainer 用来判断当前食物添加应当添加到哪个容器里
        this.timeContainer = ".nl_time_morning";
        this.timeContainerChild = ".nl_time_morning_container";
        this.calorieTotal = "#nl_time_morning_total";
        this.body.delegate(".nl_time_li", "click", function () {
            self.timeChange($(this));
        });

        this.body.delegate(".nl_calorie_btn", "click", function () {
            self.saveCalorie();
        });

        this.body.delegate(".nl_calorie_click", "click", function () {
            self.addFood($(this));
        });

        this.body.delegate(".nl_time_food_remove", "click", function () {
            self.removeFood($(this));
        });

        window.onresize = function () {
            self.autoParentHeight();
        }
        document.getElementById("nl_calorie_value").focus();
        // this.test();
    };
    calorie_function.prototype = {
        test: function () {
            var self = this;
            yy_init($(document).width());
        },
        autoParentHeight: function () {
            var self = this;
            var screenWidth = parseInt($(document).width());
            var leftHeight = parseInt($(".nl_recommend_left").css("height"));
            var rightHeight = parseInt($(".nl_recommend_right").css("height"));
            if (screenWidth > 750) {
                if (rightHeight > leftHeight) {
                    $(".nl_recommend_container").css("height", rightHeight + "px");
                } else {
                    $(".nl_recommend_container").css("height", leftHeight + "px");
                }
            } else {
                $(".nl_recommend_container").css("height", leftHeight + 30 + rightHeight + "px");
            }
        },
        timeChange: function (obj) {
            var self = this;
            var id = obj.attr("data-id");
            var arrContainer = new Array(".nl_time_morning", ".nl_time_noon", ".nl_time_night");
            var arrContainerChild = new Array(".nl_time_morning_container", ".nl_time_noon_container", ".nl_time_night_container");
            var arrTotal = new Array("#nl_time_morning_total", "#nl_time_noon_total", "#nl_time_night_total");
            // 显示容器并隐藏上一个容器
            $(self.timeContainer).css("display", "none");
            $(arrContainer[id]).css("display", "block");
            // 把容器切换
            self.timeContainer = arrContainer[id];
            self.timeContainerChild = arrContainerChild[id];
            self.calorieTotal = arrTotal[id];
            // 更改flag
            self.calorieFlag = id;
            $(".nl_time_bottom_active").addClass("nl_time_bottom_negative");
            $(".nl_time_bottom_active").removeClass("nl_time_bottom_active");
            obj.removeClass("nl_time_bottom_negative");
            obj.addClass("nl_time_bottom_active");
            $(".nl_time_bottom_label_active").addClass("nl_time_bottom_label_negative");
            $(".nl_time_bottom_label_active").removeClass("nl_time_bottom_label_active");
            obj.children().addClass("nl_time_bottom_label_active");
            obj.children().removeClass("nl_time_bottom_label_negative");
        },
        addFood: function (obj) {
            var self = this;
            var name = obj.attr("data-name");
            var power = obj.attr("data-power");
            var pattern = /^([\w\W]*?)\(每100克\)$/g;
            var powerNum = /^([0-9]*?)大卡\(每100克\)$/g;
            powerNum = power.replace(pattern, "$1");
            var power = power.replace(pattern, "$1");
            var nowTime = new Date();
            var nowYear = nowTime.getFullYear();
            nowYear = nowYear + "-" + nowTime.getMonth() + 1;
            nowYear = nowYear + ":" + nowTime.getDate();
            nowYear = nowYear + ":" + nowTime.getHours();
            nowYear = nowYear + " " + nowTime.getMinutes();
            nowYear = nowYear + " " + nowTime.getSeconds();
            nowYear = md5(nowYear + Math.random() * 10000);
            var str = "<div class='nl_time_food_container'><div class='nl_time_food_name' title='" + name + "'>" + name + "</div>" +
                "<div class='nl_time_food_remove' data-name='" + name + "' data-power='" + power + "' data-id='" + nowYear + "'>移除</div>" +
                "<div>" + power + "</div></div>";
            $(self.timeContainerChild).append(str);
            var total = $(self.calorieTotal).html();
            powerNum = parseInt(powerNum);
            total = parseInt(total);
            total = total + powerNum;
            $(self.calorieTotal).html(total);
            self.autoParentHeight();
            total = parseInt(total) + 1;
            // 进行加数后，我们就把它给统计到后台。
            // 存入cookie，这样的话，用户进行搜索和页面跳转都不会遗失掉已经点击的数据
            // 点击确定后如果登陆了，则进行存入数据库。
            // 否则提示登录
            $.ajax({
                url: "/counter/calorieSetCookie.html",
                type: "GET",
                dataType: "json",
                data: {
                    "foodName": name,
                    "powerNum": powerNum,
                    "flag": (parseInt(self.calorieFlag) + 1),
                    "total": total,
                    "id": nowYear
                },
                success: function (data) {
                    if (data.text == "failed") {
                        yy_init("系统错误，请稍后再试");
                    }
                },
                error: function (data, status, e) {
                    console.log(e);
                }
            });
        },
        removeFood: function (obj) {
            var self = this;
            var power = obj.attr("data-power");
            var id = obj.attr("data-id");
            power = parseInt(power);
            obj.parent().remove();
            var total = parseInt($(self.calorieTotal).html());
            total = total - power;
            $(self.calorieTotal).html(total);
            $.ajax({
                url: "/counter/removeCookie.html",
                type: "GET",
                dataType: "json",
                data: {"id": id, "powerNum": power, "flag": (parseInt(self.calorieFlag) + 1)},
                success: function (data) {
                    if (data.text != "ok") {
                        yy_init("移除失败，请稍后再试");
                    }
                },
                error: function (data, status, e) {
                    console.log(e);
                }
            });
        },
        saveCalorie: function () {
            var self = this;
            $.ajax({
                url: "/counter/saveCalorie.html",
                type: "GET",
                dataType: "json",
                data: {},
                success: function (data) {
                    if (data.text == "ok") {
                        yy_init("保存成功");
                    } else if (data.text == "user") {
                        yy_time_init("请先<a href='/login/index.html'>登录</a>", "南音小姐姐的提示", 0);
                    } else {
                        yy_init("保存失败");
                    }
                },
                error: function (data, status, e) {
                    console.log(e);
                }
            });
        }
    },
        window['calorie_function'] = calorie_function;
})(jQuery);