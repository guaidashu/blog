;(function ($) {
    var navigation_function = function () {
        var self = this;
        this.nl_navigation_carousel = new Array(false, true, true);
        this.nl_navigation_carousel_now = 0;
        this.body = $(document.body);
        setTimeout(function () {
            self.autoLoadImage();
        }, 4000);
        // this.test();
        document.getElementById("nl_search_input").focus();
        this.body.delegate(".nl_exit", "click", function () {
            self.loginExit();
        });
    };
    navigation_function.prototype = {
        test: function () {
            var width = parseInt($(document).width());
            yy_init(width);
        },
        loginExit: function () {
            var self = this;
            $.ajax({
                url: "/login/loginExit.html",
                type: "GET",
                dataType: "json",
                data: {},
                success: function (data) {
                    if (data.text == "ok") {
                        location.href = "/index/index.html";
                    } else {
                        yy_init("退出失败，请重试");
                    }
                },
                error: function (data, status, e) {
                    console.log(e);
                }
            });
        },
        autoLoadImage: function () {
            var self = this;
            var imgArr = new Array("bg_nav_1.jpg", "bg_nav_2.jpg", "bg_nav_3.jpg");
            var imgContainerArr = new Array(".nl_navigation_carousel_1", ".nl_navigation_carousel_2", ".nl_navigation_carousel_3");
            var len = imgArr.length;
            // 第一张图片不用进行js加载，另外两张背景图则需要判断是否是第一次加载，若是第一次加载则把对应的布尔值变为false
            // 下次便不会加载了
            for (var i = 0; i < len; i++) {
                if (self.nl_navigation_carousel[i]) {
                    self.nl_navigation_carousel[i] = false;
                    $(imgContainerArr[i]).css("background-image", "url(" + "/images/nl/" + imgArr[i] + ")");
                }
            }
            $(imgContainerArr[self.nl_navigation_carousel_now]).fadeOut(1000);
            self.nl_navigation_carousel_now = self.nl_navigation_carousel_now + 1;
            if (self.nl_navigation_carousel_now == 3) {
                self.nl_navigation_carousel_now = 0;
            }
            $(imgContainerArr[self.nl_navigation_carousel_now]).fadeIn(1500, function () {
                setTimeout(function () {
                    self.autoLoadImage();
                }, 4000);
            });

        }
    }
    window['navigation_function'] = navigation_function;
})(jQuery);
$(function () {
    var navigation = new navigation_function();
});
