;(function ($) {
    var navigation_function = function () {
        var self = this;
        this.body = $(document.body);
        // this.test();
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
    }
    window['navigation_function'] = navigation_function;
})(jQuery);
$(function () {
    var navigation = new navigation_function();
});
