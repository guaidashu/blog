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
                                    文章类型名 </label>

                                <div class="col-sm-8">
                                    <input type="text" id="doc-ipt-content-1" placeholder="文章类型名" name="type"
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
            0
        }
    })
</script>

<script type="text/javascript">
    ;(function ($) {
        var index_function = function () {
            var self = this;
            this.body = $(document.body);
            // this.test();
            this.body.delegate(".handle_btn", "click", function () {
                self.upload();
            });
            $("#article_type_upload_form input").keydown(function (e) {
                if (e.keyCode == 13) {
                    setTimeout(function () {
                        self.upload();
                    });
                }
            });
        };
        index_function.prototype = {
            test: function () {
                yy_init("model");
            },
            upload: function () {
                var self = this;
                var content = document.getElementById("doc-ipt-content-1").value;
                content = $.trim(content);
                if (!content) {
                    yy_init("请输入内容");
                    return;
                }
                $.ajax({
                    url: "<%=request.getContextPath()%>/articleManager/uploadArticleTypeHandle",
                    type: "POST",
                    dataType: "json",
                    data: {"content": content},
                    success: function (data) {
                        if (data.text == "ok") {
                            yy_init("上传成功");
                        } else {
                            yy_init(data.text);
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

