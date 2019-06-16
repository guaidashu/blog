<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/header.jsp"/>
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

                <div class="yy_spacing_30"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <form class="form-horizontal" role="form" id="article_type_upload_form" onsubmit="return false">

                            <div class="form-group">
                                <label class="col-sm-4 control-label no-padding-right" for="doc-ipt-content-1">
                                    旧密码 </label>

                                <div class="col-sm-8">
                                    <input type="password" id="doc-ipt-content-1" placeholder="原密码" name="type"
                                           class="col-xs-12 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label no-padding-right" for="doc-ipt-content-2">
                                    新密码 </label>

                                <div class="col-sm-8">
                                    <input type="password" id="doc-ipt-content-2" placeholder="新密码" name="type"
                                           class="col-xs-12 col-sm-5"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-4 control-label no-padding-right" for="doc-ipt-content-3">
                                    确认密码 </label>

                                <div class="col-sm-8">
                                    <input type="password" id="doc-ipt-content-3" placeholder="确认密码" name="type"
                                           class="col-xs-12 col-sm-5"/>
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-actions center">
                                <button type="button" class="btn btn-sm btn-success handle_btn">
                                    提交
                                    <i class="icon-arrow-right icon-on-right bigger-110"></i>
                                </button>
                            </div>


                        </form>
                    </div>
                </div>


            </div>
        </div><!-- /#ace-settings-container -->
    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<jsp:include page="../common/footer.jsp"/>

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
            this.body.delegate(".handle_btn", "click", function () {
                self.handle();
            });
            $("input").keydown(function (e) {
                if (e.keyCode == 13) {
                    setTimeout(function () {
                        self.handle();
                    }, 10);
                }
            });
            // this.test();
        };
        index_function.prototype = {
            test: function () {
                yy_init("model");
            },
            handle: function () {
                var self = this;
                var old_password = document.getElementById("doc-ipt-content-1").value;
                var password = document.getElementById("doc-ipt-content-2").value;
                var re_password = document.getElementById("doc-ipt-content-3").value;
                old_password = $.trim(old_password);
                password = $.trim(password);
                re_password = $.trim(re_password);
                if (!old_password || !password || !re_password) {
                    yy_init("请完善信息");
                    return;
                }
                if (password != re_password) {
                    yy_init("两次密码不一致");
                    return;
                }
                $.ajax({
                    url: "<%=request.getContextPath()%>/admin/changePasswordHandle",
                    type: "POST",
                    dataType: "json",
                    data: {"old_password": old_password, "password": password},
                    success: function (data) {
                        if (data.text == "ok") {
                            yy_init("修改成功");
                            document.getElementById("doc-ipt-content-1").value = "";
                            document.getElementById("doc-ipt-content-2").value = "";
                            document.getElementById("doc-ipt-content-3").value = "";
                        } else {
                            yy_init("修改失败");
                        }
                    },
                    error: function (data, status, e) {
                        console.log(e);
                    }
                });
            }
        },
            window['index_function'] = index_function;
    })(jQuery);
    $(function () {
        var index = new index_function();
    })
</script>

</body>
</html>

