<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/markdown/css/editormd.css">
    <title>${article.title}</title>
    <meta name="keywords" content="${article.title}">
    <meta name="description" content="${article.article_describe}">
</head>

<body id="blog-article-sidebar">
<jsp:include page="../common/nav.jsp"/>

<!-- content srart -->
<style type="text/css">
    .article_content img {
        max-width: 100%;
    }
</style>
<div class="am-g am-g-fixed blog-fixed blog-content">
    <div class="am-u-md-8 am-u-sm-12">
        <article class="am-article blog-article-p">
            <div class="am-article-hd">
                <h1 class="am-article-title blog-text-center">${article.title}</h1>
                <p class="am-article-meta blog-text-center">
                    <span><a href="#" class="blog-color">${article.article_type} &nbsp;</a></span>-
                    <span><a href="#">@${article.author} &nbsp;</a></span>-
                    <span><a href="#"><fmt:formatDate value="${date}"
                                                      pattern="yyy/MM/dd"/></a></span>
                </p>
            </div>
            <div class="article_content" style="margin-bottom: 0px;">
                <img src="<%=request.getContextPath()%>/${article.show_img}"
                     style="max-width: 100%; margin-bottom: 20px;"/>
                ${article.content}
            </div>
        </article>

        <div class="am-g blog-article-widget blog-article-margin">
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-7 am-u-sm-centered blog-text-center">
                <span class="am-icon-tags"> &nbsp;</span><a href="#">${article.article_type}</a>
                <hr>
                <a href=""><span class="am-icon-qq am-icon-fw am-primary blog-icon"></span></a>
                <a href=""><span class="am-icon-wechat am-icon-fw blog-icon"></span></a>
                <a href=""><span class="am-icon-weibo am-icon-fw blog-icon"></span></a>
            </div>
        </div>

        <hr>
        <div class="am-g blog-author blog-article-margin">
            <div class="am-u-sm-3 am-u-md-3 am-u-lg-2">
                <img src="<%=request.getContextPath()%>/images/user/header.png" alt=""
                     class="blog-author-img am-circle">
            </div>
            <div class="am-u-sm-9 am-u-md-9 am-u-lg-10">
                <h3><span>作者 &nbsp;: &nbsp;</span><span class="blog-color">${article.author}</span></h3>
                <p>喵喵喵，你在心上</p>
            </div>
        </div>
        <hr>
        <ul class="am-pagination blog-article-margin">
            <li class="am-pagination-prev"><a
                    href="<%=request.getContextPath()%>/article/articleDetails?id=${article.id-1}" class="">&laquo;
                一切的回顾</a></li>
            <li class="am-pagination-next"><a
                    href="<%=request.getContextPath()%>/article/articleDetails?id=${article.id+1}">不远的未来 &raquo;</a>
            </li>
        </ul>

        <hr>

        <form class="am-form am-g">
            <h3 class="blog-comment">评论</h3>
            <fieldset>
                <div class="am-form-group am-u-sm-4 blog-clear-left">
                    <input type="text" class="" placeholder="名字">
                </div>
                <div class="am-form-group am-u-sm-4">
                    <input type="email" class="" placeholder="邮箱">
                </div>

                <div class="am-form-group am-u-sm-4 blog-clear-right">
                    <input type="password" class="" placeholder="网站">
                </div>

                <div class="am-form-group">
                    <div id="editormd">
                        <textarea class="" style="display: none;"></textarea>
                    </div>
                </div>

                <p>
                    <button type="submit" class="am-btn am-btn-default">发表评论</button>
                </p>
            </fieldset>
        </form>

        <hr>
    </div>

    <jsp:include page="../common/right.jsp"/>


    <jsp:include page="../common/bottom.jsp"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/markdown/src/editormd.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/markdown/editormd.min.js"></script>
    <script type="text/javascript">
        uParse('.article_content', {
            rootPath: '<%=request.getContextPath()%>/ueditor'
        });
        var markdownEditor;
        $(function () {
            markdownEditor = editormd("editormd", {
                height: "370",
                syncScrolling: "single",
                path: "<%=request.getContextPath()%>/markdown/lib/",
                placeholder: "此处写你的评论呦···",
                emoji: true,
                pluginPath: "<%=request.getContextPath()%>/markdown/plugins/",
                autoFocus: false,
                saveHTMLToTextarea: true,
                dialogLockScreen: false,
                toolbarAutoFixed: false
            });
        });
    </script>
    <script type="text/javascript">
        ;(function ($) {
            var index_function = function () {
                var self = this;
                this.body = $(document.body);
                // this.test();
                window.onload = function () {
                    self.sendMessage();
                }
            };
            index_function.prototype = {
                test: function () {
                    var self = this;
                    yy_init("ok");
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