<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="blog-footer">
    <div class="blog-text-center">Copyright © 2017 Designed by 南音 &nbsp;<a href="http://www.miitbeian.gov.cn/"
                                                                           target="_blank">蜀ICP备16013626号-3</a></div>
</footer>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="<%=request.getContextPath()%>/assets/js/amazeui.min.js"></script>
<script src="<%=request.getContextPath()%>/js/yy.js"></script>
<script>
    ;(function ($) {
        var nav_function = function () {
            var self = this;
            this.body = $(document.body);
            this.body.delegate(".blog_search_input", "focus", function () {
                $(".blog_search_input").keydown(function (e) {
                    var keyword = document.getElementById("blog_search_input").value;
                    keyword = $.trim(keyword);
                    if(e.keyCode == "13"){
                        location.href = "<%=request.getContextPath()%>/search/searchTitle?search=" + keyword;
                    }
                });
            });
        };
        nav_function.prototype = {
        }
        window['nav_function'] = nav_function;
    })(jQuery);

    $(function () {
        var nav_start = new nav_function();
    });
</script>