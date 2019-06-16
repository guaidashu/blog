;(function ($) {
    var data_function = function () {
        var self = this;
        this.body = $(document.body);
        this.autoParentHeight();
        window.onresize = function () {
            self.autoParentHeight();
        }
        $.scrollTo(".nl_main_container", 300);
        // this.test();
    };
    data_function.prototype = {
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
        }
    },
        window['data_function'] = data_function;
})(jQuery);