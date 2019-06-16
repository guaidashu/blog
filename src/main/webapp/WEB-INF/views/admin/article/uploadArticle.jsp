<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/put.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/lightbox.css"/>
    <title>${title}</title>
</head>

<body>
<jsp:include page="../common/navbar.jsp"/>
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <jsp:include page="../common/siderbar.jsp"/>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="<%=request.getContextPath()%>/admin/index">首页</a>
                    </li>

                    <li>
                        <a href="${classurl}">${classname}</a>
                    </li>
                    <li class="active">${title}</li>
                </ul><!-- .breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input"
                                           id="nav-search-input" autocomplete="off"/>
									<i class="icon-search nav-search-icon"></i>
								</span>
                    </form>
                </div><!-- #nav-search -->
            </div>

            <div class="page-content">

                <!-- 页面标题 -->
                <div class="widget-container-span" style="width:100%;">
                    <div class="widget-box transparent">
                        <div class="widget-header">
                            <h5 class="smaller">${title}</h5>
                        </div>
                    </div>
                </div>

                <!-- 内容开始 -->
                <div style="width:100%;height:30px;"></div>
                <div class="report_other_first">
                    <div class="report_title"><!-- 作品名字 -->
                        <div class="report_title_remind">文章标题</div>
                        <input type="text" name="title" class="report_title_input" id="title"/>
                    </div>
                </div>

                <div class="report_other_first">
                    <div class="report_title"><!-- 文章类型 -->
                        <div class="report_title_remind">文章类型</div>
                        <select class="form-control col-xs-12 col-sm-5" name="village" id="type"
                                data-placeholder="选择类型">
                            <option value="0">未选择</option>
                            <c:forEach var="item" items="${typeList}">
                                <option value="${item.id}">${item.type_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <!-- 加载编辑器的容器 -->
                        <script id="editor" name="editor" type="text/plain">

                        </script>
                    </div>
                </div>

                <div class="yy_spacing_20"></div>

                <!-- 主容器 -->
                <div class="right_container">
                    <div class="upload_container" style="border-radius: 0 0 5px 5px;">
                        <!-- <div contentEditable="true" class="content_input" id="content_input"></div> -->
                        <textarea class="content_input" id="content_input" placeholder="文章描述"
                                  style="border-radius: 5px;border: none;"></textarea>
                        <div class="upload_container_bottom">
                            <div class="upload_images_add"><!-- 点击添加图片 -->
                                <p><span class="upload_images_add_span" title="点击添加图片"></span></p>
                            </div>
                            <div class="upload_btn cursor_pointer">提交</div>
                        </div>
                        <div class="upload_images_container">
                            <form enctype="multipart/form-data" action="" method="POST" id="form"
                                  name="form" class="form">
                            </form>
                            <div class="images_show_container" id="images_show_container"></div>
                        </div>
                    </div>
                </div>

                <div class="yy_spacing_60"></div>
                <div class="yy_spacing_60"></div>
                <div class="yy_spacing_60"></div>

            </div>
        </div><!-- /#ace-settings-container -->
    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/lang/en/en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/lightbox.js"></script>

<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('editor', {
        autoHeight: true,
        initialContent: "文章内容",
        focus: true,
        initialFrameHeight: 300,
        initialFrameWidth: "100%",
        allowDivTransToP: false
    });
</script>
<script type="text/javascript">
    jQuery(function ($) {
        var oTable1 = $('#sample-table-2').dataTable({
            "aoColumns": [
                {"bSortable": false},
                null, null, null, null, null,
                {"bSortable": false}
            ]
        });


        $('table th input:checkbox').on('click', function () {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox')
                .each(function () {
                    this.checked = that.checked;
                    $(this).closest('tr').toggleClass('selected');
                });

        });


        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }
    })
</script>

<script type="text/javascript">
    ;(function ($) {
        var index_function = function () {
            var self = this;
            this.body = $(document.body);
            // this.test();
        };
        index_function.prototype = {
            test: function () {
                yy_init("model");
            }
        },
            window['index_function'] = index_function;
    })(jQuery);
    $(function () {
        var index = new index_function();
    });


    ;(function ($) {
        var put_function = function () {
            var self = this;
            this.body = $(document.body);
            this.validate_count = 0;//验证码次数纪录变量
            this.body.delegate(".upload_images_add_span", "click", function () {
                self.upload_images();
            });
            //刚开始便要获取一次验证码
            // this.validate_get();
            // this.body.delegate(".validate_btn", "click", function () {
            //     self.validate_check();
            // });
            this.upload_images_now;
            this.tmpId;
            this.images_show_container_auto();
            // 图片上传
            this.body.delegate("input[type='file']", "change", function () {
                self.images_show();
            });
            this.body.delegate(".images_show_delete", "click", function () {
                self.images_delete($(this));
            });

            this.body.delegate(".upload_btn", "click", function () {
                self.ajaxUpload();
            });
            window.onresize = function () {
                self.images_show_container_auto();
            }
            // this.test();
        };
        put_function.prototype = {
            test: function () {
                var self = this;
                yy_init($(document).width());
            },
            //图片input元素自动加载
            upload_images: function () {
                var self = this;
                //判断是否选择了图片，没有选择图片则移除
                if (self.upload_images_now) {
                    if (!document.getElementById(self.tmpId).value) {
                        $(self.upload_images_now).remove();
                    }
                }
                var obj = document.getElementsByName("images_show");
                // if (obj.length == 1) {
                //     yy_init("只能上传一张图片");
                //     return;
                // }
                var nowTime = new Date();
                var nowYear = nowTime.getFullYear();
                nowYear = nowYear + "-" + nowTime.getMonth() + 1;
                nowYear = nowYear + ":" + nowTime.getDate();
                nowYear = nowYear + ":" + nowTime.getHours();
                nowYear = nowYear + " " + nowTime.getMinutes();
                nowYear = nowYear + " " + nowTime.getSeconds();
                self.tmpId = md5(nowYear);//进行md5进行转换以保证id唯一性
                self.upload_images_now = "#" + self.tmpId;
                var str = '<input type="file" name="file[]" style="display:none;" id="' + self.tmpId + '" class="file" />';
                $("#form").append(str);
                $(self.upload_images_now).click();//点击相机图标的时候就进行图片选择，也就是input的点击
            },
            images_show: function () {
                var self = this;
                if (self.upload_images_now) {//判断最后一个文件file是否选择了内容，没有则清除
                    if (!document.getElementById(self.tmpId).value) {
                        $(self.upload_images_now).remove();
                    }
                }
                if (self.file_exit_check()) {
                    self.tmpId = null;
                    self.upload_images_now = null;
                    uploadTmp("form", "<%=request.getContextPath()%>/articleManager/uploadArticleImgHandle", function (data) {
                        //yy_init(data.text);
                        if (data.reply) {
                            var base64 = new Base64();
                            var str = '<div class="images_show" name="images_show">' +
                                '<div class="images_show_delete" name="images_show_delete" data-delete=' + base64.encode(data.id) + '>' +
                                '</div>' +
                                '<img src="' + data.text + '" class="tmp_images js-lightbox"  style="cursor: pointer;" data-role="lightbox" data-source="' + data.imageName + '" data-group="group-1" data-id="' + parseInt(Math.random() * 1000) + '" alt="加载失败" />' +
                                '</div>';
                            $(".images_show_container").append(str);
                        } else {
                            yy_init(data.text);
                        }
                        $(".file").remove();
                        var len = document.getElementsByName("images_show").length;
                        if (len != 0) {
                            $(".upload_images_container").css("display", "block");
                        } else {
                            $(".upload_images_container").css("display", "none");
                        }
                        self.images_show_container_auto();
                    });
                }
            },
            //检查是否有待上传的文件,返回值为boolean，有则为true，没有则为false
            file_exit_check: function () {
                var self = this;
                var doc = $("#form");
                var file = doc[0][0];
                if (file) {
                    return true;
                } else {
                    return false;
                }
            },
            //图片显示框高度自适应以及改变大小时的图片占比例自适应
            images_show_container_auto: function () {
                var self = this;
                var screenWidth = parseInt($(document).width());
                //yy_init(screeenWidth);
                var len = document.getElementsByName("images_show").length;
                var x = 0;
                if (screenWidth > 800) {
                    if ((len % 3) != 0 && len != 0) {
                        $(".images_show").css("height", (parseInt($(".images_show").css("width")) * 1.2) + "px");
                        x = Math.floor(len / 3) + 1;
                    } else {
                        $(".images_show").css("height", (parseInt($(".images_show").css("width")) * 1.2) + "px");
                        x = Math.floor(len / 3);
                    }
                } else {
                    if ((len % 2) != 0 && len != 0) {
                        $(".images_show").css("height", (parseInt($(".images_show").css("width")) * 1.2) + "px");
                        x = Math.floor(len / 2) + 1;
                    } else {
                        $(".images_show").css("height", (parseInt($(".images_show").css("width")) * 1.2) + "px");
                        x = Math.floor(len / 2);
                    }
                }
                $(".images_show_container").css("height", x * parseInt($(".images_show").css("height")) + "px");
            },
            //上传处理函数，包括要传送的数据，名字和值分别在dataName和dataValue里，都是数组
            ajaxUpload: function () {
                var self = this;
                // if (self.validate_count >= 3) {
                //     validate_show();
                //     return 0;
                // }
                // var str = ue.getContent();
                // yy_init(str);
                // return;
                var content = ue.getContent();
                var base64 = new Base64();
                content = base64.encode(content, "UTF-8");
                var title = document.getElementById("title").value;
                var type = document.getElementById("type").value;
                title = $.trim(title);
                type = $.trim(type);
                if (!title) {
                    yy_init("请输入文章标题");
                    return 0;
                }
                if (!type) {
                    yy_init("请选择文章类型");
                    return;
                }
                var describe = document.getElementById("content_input").value;
                describe = $.trim(describe);
                var dataMove = new Array();
                var obj = document.getElementsByName("images_show_delete");
                var len = 0;
                if (!document.getElementsByName("images_show").length || !describe) {
                    yy_init("必须有内容噢，一张图片和一点描述");
                    return 0;
                }
                if (describe.length > 400) {
                    yy_init("提交字数最多400噢");
                    return;
                }
                if (obj != null) {
                    len = obj.length;
                }
                for (var i = 0; i < len; i++) {
                    dataMove[i] = obj[i].getAttribute("data-delete");
                }
                $.ajax({
                    url: "<%=request.getContextPath()%>/articleManager/uploadArticleHandle",
                    type: "POST",
                    dataType: "json",
                    data: {
                        "type": type,
                        "title": title,
                        "describe": describe,
                        "content": content,
                        "move": dataMove,
                        "handleType": new Array("1")
                    },
                    success: function (data) {
                        if (data.text == "ok") {
                            yy_init("发布成功");
                            // self.validate_get();
                            document.getElementById("content_input").value = "";
                            $(".file").remove();
                            $(".images_show").remove();
                            var len = document.getElementsByName("images_show").length;
                            document.getElementById("title").value = "";
                            document.getElementById("type").value = "";
                            if (len != 0) {
                                $(".upload_images_container").css("display", "block");
                            } else {
                                $(".upload_images_container").css("display", "none");
                            }
                            self.images_show_container_auto();
                        } else {
                            yy_init(data.text);
                            // self.validate_get();
                            $(".file").remove();
                            $(".images_show").remove();
                            var len = document.getElementsByName("images_show").length;
                            if (len != 0) {
                                $(".upload_images_container").css("display", "block");
                            } else {
                                $(".upload_images_container").css("display", "none");
                            }
                            self.images_show_container_auto();
                        }
                    },
                    error: function (data, status, e) {
                        console.log(e);
                        // self.validate_get();
                    }
                });
            },
            images_delete: function (id) {
                var self = this;
                var delete_id = id.attr("data-delete");
                $.ajax({
                    url: "<%=request.getContextPath()%>/articleManager/deleteImage",
                    type: "POST",
                    dataType: "json",
                    data: {"filename": delete_id},
                    success: function (data) {
                        if (data.text == "ok") {
                            yy_init("移除成功！");
                            id.parent().remove();
                            self.images_show_container_auto();
                            var len = document.getElementsByName("images_show").length;
                            if (len != 0) {
                                $(".upload_images_container").css("display", "block");
                            } else {
                                $(".upload_images_container").css("display", "none");
                            }
                        } else {
                            yy_init(data.text);
                        }
                    },
                    error: function (data, status, e) {
                        console.log(e);
                    }
                });
                /*
                var doc=document.getElementsByName("images_show_delete");
                yy_init(doc[0].getAttribute("data-delete"));
                */
            },
            validate_change: function () {//验证码刷新
                var self = this;
                document.getElementById("validate_img").src = "/validate.html?r=" + Math.random() * 10000000;
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
        },
            window['put_function'] = put_function;
    })(jQuery);
    $(function () {
        var put = new put_function();
    });
</script>
</body>
</html>

