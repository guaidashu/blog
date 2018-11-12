<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/lightbox.css" />
    <title>${title}</title>
</head>
<body id="blog">
<jsp:include page="../common/nav.jsp"/>

<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
    <div class="am-u-md-8 am-u-sm-12">
        <!-- 主内容开始 -->
        <c:forEach var="article" items="${articleList}">
            <article class="am-g blog-entry-article" style="padding-bottom: 2rem;">
                <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img" style="padding-bottom: 1rem">
                    <img src="<%=request.getContextPath()%>/${article.show_img}" class="am-u-sm-12 js-lightbox" style="cursor: pointer;" data-role="lightbox" data-source="<%=request.getContextPath()%>/${article.origin_img}" data-group="group-1" data-id="${article.id}" alt="加载失败" />
                </div>
                <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                    <span><a href="#" class="blog-color">${article.article_type} &nbsp;</a></span>
                    <span> @${article.author} &nbsp;</span>
                    <span><fmt:formatDate value="${article.upload_time}"
                                          pattern="yyy/MM/dd"/></span>
                    <h1>
                        <a href="<%=request.getContextPath()%>/article/articleDetails?id=${article.id}"
                           target="_blank">${article.title}</a>
                    </h1>
                    <p>${article.article_describe}
                    </p>
                    <p><a href="" class="blog-continue">continue reading</a></p>
                </div>
            </article>
        </c:forEach>

        <ul class="am-pagination">
            <li class="am-pagination-prev"><a href="">&laquo; Prev</a></li>
            <li class="am-pagination-next"><a href="">Next &raquo;</a></li>
        </ul>
    </div>

    <jsp:include page="../common/right.jsp"/>

    <jsp:include page="../common/bottom.jsp"/>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/lightbox.js"></script>

    <script type="text/javascript">
        ;(function ($) {
            var index_function = function () {
                var self = this;
                this.body = $(document.body);
                window.onload = function () {
                    self.sendMessage();
                }
            };
            index_function.prototype = {
                test: function () {
                    yy_init("model");
                },
                sendMessage: function () {
                    var self = this;
                    $.ajax({
                        url: "<%=request.getContextPath()%>/visit/index",
                        type: "GET",
                        dataType: "json",
                        data: {},
                        success: function (data) {
                            if (data.text == "ok") {
                                console.log("访问计数+1");
                            }
                        },
                        error: function (data, status, e) {
                            console.log(e);
                        }
                    });
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