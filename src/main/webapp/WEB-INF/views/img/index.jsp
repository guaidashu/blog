<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <title>${title}</title>
</head>

<body id="blog-article-sidebar">
<jsp:include page="../common/nav.jsp"/>

<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed blog-content">
    <figure data-am-widget="figure" class="am am-figure am-figure-default " data-am-figure="{  pureview: 'true' }">
        <div id="container">
            <div><img src="images/01.jpg">
                <h3>Agfa</h3></div>
            <div><img src="images/02.jpg">
                <h3>Auto</h3></div>
            <div><img src="images/03.jpg">
                <h3>Bald eagle</h3></div>
            <div><img src="images/04.jpg">
                <h3>Black swan</h3></div>
            <div><img src="images/05.jpg">
                <h3>Book shelf</h3></div>
            <div><img src="images/06.jpg">
                <h3>Camera</h3></div>
            <div><img src="images/07.jpg">
                <h3>Camera</h3></div>
            <div><img src="images/25.jpg">
                <h3>Vintage camera</h3></div>
            <div><img src="images/09.jpg">
                <h3>Coffee</h3></div>
            <div><img src="images/10.jpg">
                <h3>Cookies</h3></div>
            <div><img src="images/11.jpg">
                <h3>Cubes</h3></div>
            <div><img src="images/12.jpg">
                <h3>DJ</h3></div>
            <div><img src="images/13.jpg">
                <h3>Doors</h3></div>
            <div><img src="images/14.jpg">
                <h3>Matchbox</h3></div>
            <div><img src="images/15.jpg">
                <h3>Freiburg</h3></div>
            <div><img src="images/16.jpg">
                <h3>Henna</h3></div>
            <div><img src="images/17.jpg">
                <h3>Home office</h3></div>
            <div><img src="images/18.jpg">
                <h3>iPad</h3></div>
            <div><img src="images/19.jpg">
                <h3>Keyboard</h3></div>
            <div><img src="images/20.jpg">
                <h3>Lynx</h3></div>
            <div><img src="images/21.jpg">
                <h3>Mac</h3></div>
            <div><img src="images/22.jpg">
                <h3>Notebook</h3></div>
            <div><img src="images/23.jpg">
                <h3>Thoughts</h3></div>
            <div><img src="images/24.jpg">
                <h3>Office</h3></div>
            <div><img src="images/25.jpg">
                <h3>Children</h3></div>
            <div><img src="images/26.jpg">
                <h3>Portrait</h3></div>
            <div><img src="images/27.jpg">
                <h3>Startup</h3></div>
            <div><img src="images/28.jpg">
                <h3>Sun</h3></div>
            <div><img src="images/29.jpg">
                <h3>The Eiffel Tower</h3></div>
            <div><img src="images/30.jpg">
                <h3>Water drops</h3></div>

        </div>
    </figure>
</div>


<jsp:include page="../common/bottom.jsp"/>
<script src="assets/js/pinto.min.js"></script>
<script src="assets/js/img.js"></script>
<script type="text/javascript">
    ;(function ($) {
        var index_function = function () {
            var self = this;
            this.body = $(document.body);
            window.onload = function () {
                self.loadPage();
            }
        };
        index_function.prototype = {
            test: function () {
                var self = this;
                yy_init("model");
            },
            loadPage: function () {
                var self = this;
                var url = js_GET();
                if (!url.id) {
                    location.href = "<%=request.getContextPath()%>/img?id=" + Math.random();
                }
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
