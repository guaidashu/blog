<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <title>${title}</title>
</head>
<body id="blog">
<jsp:include page="../common/nav.jsp"/>

<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
    <div class="am-u-md-8 am-u-sm-12">
        <!-- 主内容开始 -->
    </div>

    <jsp:include page="../common/right.jsp"/>

    <jsp:include page="../common/bottom.jsp"/>
    <script type="text/javascript">
        ;(function ($) {
            var index_function = function () {
                var self = this;
                this.body = $(document.body);
            };
            index_function.prototype = {
                test: function () {
                    yy_init("model");
                }
            }
            window['index_function'] = index_function;
        })(jQuery);

        $(function () {
            var index = new index_function();
        });
    </script>
</body>
</html>