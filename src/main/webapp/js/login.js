;(function ($) {
    var log_function = function () {
        var self = this;
        this.body = $(document.body);
        this.width = $(document).width();
        this.log_boolean = true;
        this.validate_boolean = false;
        this.validate_count = 0;
        document.getElementById("log_user").focus();
        //方便自适应调试
        //yy_init(this.width);
        this.body.delegate(".log_btn", "click", function () {
            self.log_check();
        });
        this.body.delegate(".validate_btn", "click", function () {
            self.validate_check();
        });
        this.body.delegate("#log_user,#log_password", "focus", function () {
            self.log_boolean = true;
        });
        this.body.delegate("#log_user,#log_password", "blur", function () {
            self.log_boolean = false;
        });
        this.body.delegate("#validate_input", "focus", function () {
            self.validate_boolean = true;
        });
        this.body.delegate("#validate_btn", "blur", function () {
            self.validate_boolean = false;
        });
        $(document).keydown(function (e) {
            if (e.keyCode == 13) {
                if (self.log_boolean) {
                    self.log_check();
                }
                if (self.validate_boolean) {
                    self.validate_check();
                }
            }
        });

    };
    log_function.prototype = {
        log_check: function () {//登录检查函数
            var self = this;
            var username = document.getElementById("log_user").value;
            username = $.trim(username);
            var password = document.getElementById("log_password").value;
            password = $.trim(password);
            if (!username) {
                yy_init("请输入用户名或者帐号");
                return;
            } else if (!password) {
                yy_init("密码不能为空");
                return;
            }
            if (self.validate_count >= 3) {
                validate_show();
                return;
            }
            $.ajax({
                url: "/login/loginHandle.html",
                type: "POST",
                dataType: "json",
                data: {"username": username, "password": password},
                success: function (data) {
                    if (data.text == "ok") {
                        location.href = "/index/index.html";
                    } else if (data.text == "error_user") {
                        yy_init("没有此用户");
                    } else if (data.text == "error_password") {
                        yy_init("密码错误");
                    } else if (data.text == "validate") {
                        yy_init("请先输入验证码");
                        return;
                    } else {
                        yy_init("登录失败");
                    }
                    self.validate_get();//每次密码错误或者帐号不存在则进行一次获取
                },
                error: function (data, status, e) {
                    self.validate_get();//每次密码错误或者帐号不存在则进行一次获取
                    console.log(e);
                }
            });
        },
        validate_get: function () {//获取密码输入错误次数函数
            var self = this;
            $.ajax({
                url: "/getValidateCount.html",
                type: "GET",
                dataType: "json",
                data: {},
                success: function (data) {
                    self.validate_count = data.text;
                },
                error: function (data, status, e) {
                    console.log(e);
                }
            });
        },
        validate_check: function () {//验证码检查函数
            var self = this;
            var validate = document.getElementById("validate_input").value;
            validate = $.trim(validate);
            if (!validate) {
                yy_init("验证码不能为空噢");
                return;
            }
            $.ajax({
                url: "/validateCheck.html",
                type: "POST",
                dataType: "json",
                data: {"validate": validate},
                success: function (data) {
                    if (data.text == "ok") {
                        self.validate_get();
                        yy_init("验证成功,请再次点击登录");
                        $(".validate_envelop").fadeOut(500);
                        $(".validate_popup").fadeOut(500, function () {
                            $(".validate_envelop").remove();
                            $(".validate_popup_container").remove();
                        });
                    } else {
                        yy_init("验证码错误");
                        self.validate_change();
                    }
                },
                error: function (data, status, e) {
                    console.log(e);
                    self.validate_change();
                }
            });
        },
        validate_change: function () {//验证码刷新
            var self = this;
            document.getElementById("validate_img").src = "/validate.html?r=" + Math.random() * 10000000;
        }
    }
    window['log_function'] = log_function;
})(jQuery);