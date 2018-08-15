<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <jsp:include page="../common/header.jsp"/>
    <meta property="qc:admins" content="1463510721671134316375" />
    <meta name="keywords" content="奕弈的博客">
    <meta name="description" content="喵喵喵，你在心上。一步一个脚印，慢慢的走出自己路。打好基础，不好高骛远，总会有丰富的收获。相信天道酬勤。">
    <meta name="baidu-site-verification" content="AkBZGgIzhe" />
    <title>奕弈的博客</title>
</head>
<body id="blog">
<jsp:include page="../common/nav.jsp"/>
<!-- banner start -->
<div class="am-g am-g-fixed blog-fixed am-u-sm-centered blog-article-margin">
    <div data-am-widget="slider" class="am-slider am-slider-b1" data-am-slider='{&quot;controlNav&quot;:false}'>
        <ul class="am-slides">
            <li>
                <img src="<%=request.getContextPath()%>/assets/i/b1.jpg">
                <div class="blog-slider-desc am-slider-desc ">
                    <div class="blog-text-center blog-slider-con">
                        <span><a href="" class="blog-color">Article &nbsp;</a></span>
                        <h1 class="blog-h-margin"><a href="">总在思考一句积极的话</a></h1>
                        <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。
                        </p>
                        <span class="blog-bor">2015/10/9</span>
                        <br><br><br><br><br><br><br>
                    </div>
                </div>
            </li>
            <li>
                <img src="<%=request.getContextPath()%>/assets/i/b2.jpg">
                <div class="am-slider-desc blog-slider-desc">
                    <div class="blog-text-center blog-slider-con">
                        <span><a href="" class="blog-color">Article &nbsp;</a></span>
                        <h1 class="blog-h-margin"><a href="">总在思考一句积极的话</a></h1>
                        <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。
                        </p>
                        <span>2015/10/9</span>
                    </div>
                </div>
            </li>
            <li>
                <img src="<%=request.getContextPath()%>/assets/i/b3.jpg">
                <div class="am-slider-desc blog-slider-desc">
                    <div class="blog-text-center blog-slider-con">
                        <span><a href="" class="blog-color">Article &nbsp;</a></span>
                        <h1 class="blog-h-margin"><a href="">总在思考一句积极的话</a></h1>
                        <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。
                        </p>
                        <span>2015/10/9</span>
                    </div>
                </div>
            </li>
            <li>
                <img src="<%=request.getContextPath()%>/assets/i/b2.jpg">
                <div class="am-slider-desc blog-slider-desc">
                    <div class="blog-text-center blog-slider-con">
                        <span><a href="" class="blog-color">Article &nbsp;</a></span>
                        <h1 class="blog-h-margin"><a href="">总在思考一句积极的话</a></h1>
                        <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。
                        </p>
                        <span>2015/10/9</span>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<!-- banner end -->

<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
    <div class="am-u-md-8 am-u-sm-12">
        <c:forEach var="article" items="${articleList}">
            <article class="am-g blog-entry-article" style="padding-bottom: 2rem">
                <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img" style="padding-bottom: 1rem">
                    <img src="<%=request.getContextPath()%>/${article.show_img}" alt="" class="am-u-sm-12">
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
</body>
</html>