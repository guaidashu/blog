<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

                <!-- 内容开始 -->
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12 dm_info_data">
                                <div class="table-responsive dm_info_data_page" data-page="">
                                    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="center">
                                                <label>
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"></span>
                                                </label>
                                            </th>
                                            <th>序号</th>
                                            <th>类型名</th>

                                            <th>操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="item" items="${typeList}">
                                            <tr>
                                                <td class="center">
                                                    <label>
                                                        <input type="checkbox" class="ace"/>
                                                        <span class="lbl"></span>
                                                    </label>
                                                </td>

                                                <td>
                                                    <a href="#"
                                                       target="_blank">${item.id}</a>
                                                </td>
                                                <td>${item.type_name}</td>

                                                <td>
                                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-success dm_look"
                                                                data-id="${item.id}">
                                                            <i class="icon-zoom-in bigger-120"></i>
                                                        </button>

                                                        <button class="btn btn-xs btn-info dm_change_password"
                                                                data-id="${item.id}" title="修改">
                                                            <i class="icon-edit bigger-120"></i>
                                                        </button>

                                                        <button class="btn btn-xs btn-danger dm_delete"
                                                                data-id="${item.id}" title="删除">
                                                            <i class="icon-trash bigger-120"></i>
                                                        </button>
                                                    </div>

                                                    <div class="visible-xs visible-sm hidden-md hidden-lg">
                                                        <div class="inline position-relative">
                                                            <button class="btn btn-minier btn-primary dropdown-toggle"
                                                                    data-toggle="dropdown">
                                                                <i class="icon-cog icon-only bigger-110"></i>
                                                            </button>

                                                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
                                                                <li>
                                                                    <a href="#" class="tooltip-info dm_look"
                                                                       data-rel="tooltip" title="View"
                                                                       data-id="${item.id}">
																				<span class="blue">
																					<i class="icon-zoom-in bigger-120"></i>
																				</span>
                                                                    </a>
                                                                </li>

                                                                <li>
                                                                    <a href="#"
                                                                       class="tooltip-success dm_change_password"
                                                                       data-rel="tooltip" title="Edit"
                                                                       data-id="${item.id}">
																				<span class="green">
																					<i class="icon-edit bigger-120"></i>
																				</span>
                                                                    </a>
                                                                </li>

                                                                <li>
                                                                    <a href="#" class="tooltip-error dm_delete"
                                                                       data-rel="tooltip" title="删除"
                                                                       data-id="${item.id}">
																				<span class="red">
																					<i class="icon-trash bigger-120"></i>
																				</span>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="modal-footer no-margin-top">
                                        <!-- <button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
                                            <i class="icon-remove"></i>
                                            Close
                                        </button> -->

                                        <ul class="pagination pull-right no-margin">
                                            <li class="prev disabled">
                                                <a href="#">
                                                    <i class="icon-double-angle-left"></i>
                                                </a>
                                            </li>

                                            <li class="active">
                                                <a href="#">1</a>
                                            </li>

                                            <li>
                                                <a href="#">2</a>
                                            </li>

                                            <li>
                                                <a href="#">3</a>
                                            </li>

                                            <li class="next">
                                                <a href="#">
                                                    <i class="icon-double-angle-right"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div><!-- /.table-responsive -->

                            </div><!-- /span -->
                        </div><!-- /row -->

                        <div class="hr hr-18 dotted hr-double"></div>

                        <style>
                            .table-header .close {
                                margin-top: 6px;
                            }

                            .modal-header .close {
                                font-size: 26px;
                            }
                        </style>

                        <a href="#modal-table_1" class="dm_modal_1" data-toggle="modal" style="display:none;"></a>
                        <div id="modal-table_1" class="modal fade" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header no-padding">
                                        <div class="table-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                <span class="white">&times;</span>
                                            </button>
                                            详细信息
                                        </div>
                                    </div>

                                    <div class="modal-body no-padding" style="margin: 10px">
                                        <form class="form-horizontal dm_look_data" id="dm_info_form" role="form">
                                        </form>
                                    </div>

                                    <div class="modal-footer no-margin-top">
                                        <button class="btn btn-sm btn-success pull-center dm_look_handle"
                                                data-dismiss="modal">
                                            <i class="icon-ok"></i>
                                            确认
                                        </button>

                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- PAGE CONTENT ENDS -->

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
            this.body.delegate(".dm_delete", "click", function () {
                if (confirm("确定删除？")) {
                    self.delete($(this));
                }
            });
            // this.test();
        };
        index_function.prototype = {
            test: function () {
                yy_init("model");
            },
            delete: function (obj) {
                var self = this;
                var id = obj.attr("data-id");
                id = $.trim(id);
                $.ajax({
                    url: "<%=request.getContextPath()%>/articleManager/typeManagerDelete",
                    type: "POST",
                    dataType: "json",
                    data: {"id": id},
                    success: function (data) {
                        if (data.text == "ok") {
                            yy_init("删除成功");
                            obj.parent().parent().parent().remove();
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

