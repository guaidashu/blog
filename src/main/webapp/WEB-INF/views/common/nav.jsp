<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="am-g am-g-fixed blog-fixed blog-text-center blog-header">
    <div class="am-u-sm-8 am-u-sm-centered">
        <img style="border-radius:100px;width:100px;" src="<%=request.getContextPath()%>/images/head.png"
             alt="Amaze UI Logo"/>
        <h2 class="am-hide-sm-only">奕弈的博客</h2>
    </div>
</header>
<hr>
<!-- nav start -->
<nav class="am-g am-g-fixed blog-fixed blog-nav">
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only blog-button"
            data-am-collapse="{target: '#blog-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="blog-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li class="${menu[0]}"><a href="<%=request.getContextPath()%>/index">首页</a></li>
            <li class="${menu[1]}"><a href="<%=request.getContextPath()%>/article">文章</a></li>
            <li class="${menu[2]}"><a href="<%=request.getContextPath()%>/img">图片</a></li>
            <li class="${menu[3]}"><a href="<%=request.getContextPath()%>/guestbook">留言板</a></li>
            <li><a href="http://wpa.qq.com/msgrd?v=3&uin=1023767856&site=qq&menu=yes">联系我</a></li>
        </ul>
        <form class="am-topbar-form am-topbar-right am-form-inline" role="search">
            <div class="am-form-group">
                <input type="text" class="am-form-field am-input-sm" placeholder="搜索">
            </div>
        </form>
    </div>
</nav>
<hr>
<!-- nav end -->