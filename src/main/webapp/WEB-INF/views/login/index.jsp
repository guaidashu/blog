<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" type="image/x-icon" href="${path}/images/ooopic_1460463927.ico" media="screen" />
    <link rel="stylesheet" type="text/css" href="${path}/asset/css/amazeui.css" />
    <link rel="stylesheet" type="text/css" href="${path}/css/body.css" />
    <link rel="stylesheet" type="text/css" href="${path}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${path}/css/yy.css" />
    <link rel="stylesheet" href="${path}/asset/css/admin.css" />
    <link rel="stylesheet" href="${path}/asset/css/core.css" />
    <link rel="stylesheet" href="${path}/asset/css/menu.css" />
    <link rel="stylesheet" type="text/css" href="${path}/css/navigation.css" />

    <link rel="stylesheet" href="${path}/asset/css/page/typography.css" />
    <link rel="stylesheet" href="${path}/asset/css/component.css" />
    <link rel="stylesheet" href="${path}/asset/css/page/form.css" />
    <meta property="wb:webmaster" content="a41cfd27cbc434bc" />
    <title>${requestScope.title}</title>
</head>
<body>
<div class="account-pages">
    <div class="wrapper-page">
        <div class="text-center">
            <a href="index.html" class="logo"><span>Blog<span></span></span></a>
        </div>

        <div class="m-t-40 card-box">
            <div class="text-center">
                <h4 class="text-uppercase font-bold m-b-0">Sign In</h4>
            </div>
            <div class="panel-body">
                <form class="am-form">
                    <div class="am-g">
                        <div class="am-form-group">
                            <input type="text" class="am-radius" id="username" name="username" placeholder="Username">
                        </div>

                        <div class="am-form-group form-horizontal m-t-20">
                            <input type="password" class="am-radius" id="password" placeholder="Password">
                        </div>

                        <div class="am-form-group ">
                            <label style="font-weight: normal;color: #999;">
                                <input type="checkbox" class="remeber"> Remember me
                            </label>
                        </div>

                        <div class="am-form-group ">
                            <button type="button" class="am-btn am-btn-primary am-radius dm_login_btn" style="width: 100%;height: 100%;">Log In</button>
                        </div>

                        <div class="am-form-group ">
                            <a href="page-recoverpw.html" class="text-muted"><i class="fa fa-lock m-r-5"></i> Forgot your password（忘记密码）?</a>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>
<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="${path}/asset/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<script type="text/javascript" src="${path}/js/jquery-1.11.3.min.js"></script>
<!--<![endif]-->
<!--[if lt IE9]>
<script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${path}/asset/js/amazeui.min.js"></script>
<script type="text/javascript" src="${path}/js/jquery.scrollTo.js"></script>
<script type="text/javascript" src="${path}/js/yy.js"></script>

<script type="text/javascript">
    ;(function($){
        var index_function = function()
        {
            var self = this;
            this.body = $(document.body);
            this.keydown_flag = false;
            this.body.delegate(".dm_login_btn", "click", function(){
                self.login();
            });

            // 回车事件绑定处
            $("input").keydown(function(e){
                if(e.keyCode == 13){
                    setTimeout(function(){
                        self.login();
                    }, 10);
                }
            });
            // this.test();
        };
        index_function.prototype = {
            test:function()
            {
                var self = this;
                yy_init("ok");
            },
            login:function()
            {
                var self = this;
                var username = document.getElementById("username").value;
                username = $.trim(username);
                var password = document.getElementById("password").value;
                password = $.trim(password);
                if(!username){
                    yy_init("用户名不能为空");
                    return;
                }
                if(!password){
                    yy_init("密码不能为空");
                    return;
                }
                $.ajax({
                    url:"<%=request.getContextPath()%>/login/handle",
                    type:"POST",
                    dataType:"json",
                    data:{"username":username, "password":password},
                    success:function(data){
                        if(data.text == "ok"){
                            // yy_init("登录成功");
                            var referer = document.referrer;
                            console.log(referer);
                            if(!referer){
                                location.href = "<%=request.getContextPath()%>/index";
                            }else{
                                location.href = referer;
                            }

                        }else if(data.text == "admin"){
                            location.href = "<%=request.getContextPath()%>/admin";
                        }else{
                            yy_init(data.text);
                        }
                    },
                    error:function(data, status, e){
                        console.log(e);
                    }
                });
            }
        },
            window['index_function'] = index_function;
    })(jQuery);
    $(function(){
        var index = new index_function();
    });
</script>
</body>
</html>