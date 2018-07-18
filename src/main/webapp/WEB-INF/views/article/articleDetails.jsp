<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <title>${article.title}</title>
</head>

<body id="blog-article-sidebar">
<jsp:include page="../common/nav.jsp"/>

<!-- content srart -->
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
                <img src="<%=request.getContextPath()%>/${article.show_img}" style="max-width: 100%; margin-bottom: 20px;"/>
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
            <li class="am-pagination-prev"><a href="#" class="">&laquo; 一切的回顾</a></li>
            <li class="am-pagination-next"><a href="">不远的未来 &raquo;</a></li>
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
                    <textarea class="" rows="5" placeholder="一字千金"></textarea>
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
    <script src="<%=request.getContextPath()%>/ueditor/ueditor.parse.js"></script>
    <script type="text/javascript">
        uParse('.article_content', {
            rootPath: '<%=request.getContextPath()%>/ueditor'
        });
    </script>
</body>
</html>