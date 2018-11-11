;(function () {
    var register_function = function () {
        var self = this;
        this.body = $(document.body);
        this.validate_count = 0;
        this.login_check_boolean = true;
        this.width = $(document).width();
        document.getElementById("login_phone").focus();
        //宽度弹出便于设置自适应
        //alert(self.width);
        this.body.delegate("#login_login", "click", function () {
            self.login_switch_login();
        });
        this.body.delegate("#login_bound", "click", function () {
            self.login_switch_bound();
        });
        this.body.delegate(".login_btn", "click", function () {
            if (self.login_check_boolean) {
                self.login_check_boolean = false;
                self.login_check();
            } else {
                yy_init("正在注册ing,请耐心等候");
            }
        });
        this.body.delegate(".validate_btn", "click", function () {
            self.validate_check();
        });
        this.body.delegate(".login_bound_btn", "click", function () {
            self.login_bound_check();
        });
        this.body.delegate(".login_statement_close,.login_statement_btn", "click", function () {
            $(".login_statement").fadeOut();
            $(".login_statement_envelop").fadeOut();
        });
        this.body.delegate(".login_direct", "click", function () {
            $(".login_statement").fadeIn();
            $(".login_statement_envelop").fadeIn();
        });
    };
    register_function.prototype = {
        login_switch_login: function () {//切换到注册面板
            var self = this;
            $(".login_bound").css({
                "display": "none"
            });
            $("#login_login").addClass("login_remind_bottom");
            $("#login_bound").removeClass("login_remind_bottom");
            $(".login_panel").css({
                "display": "block"
            });
        },
        login_switch_bound: function () {//切换到绑定面板
            $(".login_panel").css({
                "display": "none"
            });
            $("#login_bound").addClass("login_remind_bottom");
            $("#login_login").removeClass("login_remind_bottom");
            $(".login_bound").css({
                "display": "block"
            });
        },
        login_check: function () {//注册实现函数
            var self = this;

            //邮箱
            var name = document.getElementById("login_name").value;
            name = $.trim(name);

            //电话号码
            var phone = document.getElementById("login_phone").value;
            phone = $.trim(phone);
            //密码
            var password = document.getElementById("login_password").value;
            password = $.trim(password);
            //确认密码
            var repassword = document.getElementById("login_repassword").value;
            repassword = $.trim(repassword);

            if (!password || !repassword || !phone || !name) {
                self.login_check_boolean = true;
                yy_init("有信息未完善");
                return;
            }
            if (password != repassword) {
                self.login_check_boolean = true;
                yy_init("两次密码输入不一致");
                return;
            }
            if (!phoneCheck(phone)) {
                yy_init("这不是一个正确的手机号噢");
                return;
            }
            if (name.length > 10) {
                yy_init("用户名最多10位噢");
                return;
            }
            if (password.length > 16) {
                yy_init("密码最多16位噢");
                return;
            }
            if (self.validate_count >= 3) {
                self.login_check_boolean = true;
                validate_show();
                return;
            }
            // yy_time_init("注册中请稍候<div class='login_wait'><span id='login_wait'>4</span>秒</div>","注册小提示");
            self.login_wait();
            $.ajax({
                url: "/login/registerHandle.html",
                type: "POST",
                dataType: "json",
                data: {"name": name, "password": password, "phone": phone},
                success: function (data) {
                    self.login_check_boolean = true;
                    self.validate_get();
                    if (data.text == "ok") {
                        yy_init("注册成功");
                        document.getElementById("login_name").value = "";
                        //电话号码
                        document.getElementById("login_phone").value = "";
                        //密码
                        document.getElementById("login_password").value = "";
                        //确认密码
                        document.getElementById("login_repassword").value = "";
                        // location.href="/login/index.html";
                    } else if (data.text == "exist") {
                        yy_init("该手机号已被注册");
                    } else {
                        yy_init("注册失败");
                    }
                },
                error: function (data, status, e) {
                    yy_alert_exit();
                    console.log(e);
                }
            });
        },
        validate_get: function () {//获取验证码
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
        validate_check: function () {
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
                        yy_init("验证成功，请再次点击操作");
                        $(".validate_envelop").fadeOut(500);
                        $(".validate_popup").fadeOut(500, function () {
                            $(".validate_envelop").remove();
                            $(".validate_popup_container").remove();
                        });
                        self.login_check_boolean = true;
                    } else {
                        yy_init("验证码错误");
                        self.validate_change();
                    }
                    self.validate_get();
                },
                error: function (data, status, e) {
                    console.log(e);
                }
            });
        },
        validate_change: function () {//验证码刷新
            var self = this;
            document.getElementById("validate_img").src = "/validate.html?r=" + Math.random() * 10000000;
        },
        login_bound_check: function () {//绑定检查
            var self = this;
            var username = document.getElementById("login_bound_user").value;
            var password = document.getElementById("login_bound_password").value;
            username = $.trim(username);
            password = $.trim(password);
            if (!username) {
                yy_init("请完善用户账户或手机号");
                return;
            }
            if (!password) {
                yy_init("请填写密码");
                return;
            }
            if (self.validate_count >= 3) {
                validate_show();
                return;
            }
            $.ajax({
                url: "/Home/Login/bound.shtml",
                type: "POST",
                dataType: "json",
                data: {"username": username, "password": password},
                success: function (data) {
                    if (data.text == "ok") {
                        yy_init("绑定成功");
                        location.href = "/Home/Login";
                        self.validate_get();
                    } else {
                        yy_init(data.text);
                        self.validate_get();
                    }
                },
                error: function (data, status, e) {
                    console.log(e);
                    self.validate_get();
                }
            });
        },
        login_wait: function () {
            var self = this;
            var obj = document.getElementById("login_wait");
            if (obj == null) {
                return;
            }
            var tmp_str = parseInt($.trim(obj.innerHTML));
            if (tmp_str == 0) {
                yy_time_exit();
                return;
            }
            tmp_str = tmp_str - 1;
            obj.innerHTML = tmp_str;
            setTimeout(function () {
                self.login_wait();
            }, 1000);
        }
    }
    window['register_function'] = register_function;
})(jQuery);
