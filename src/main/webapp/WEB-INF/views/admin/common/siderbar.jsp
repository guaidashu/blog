<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a class="menu-toggler" id="menu-toggler" href="#" style="line-height:32px;text-align:center;color:#ffffff;">
    <!-- <span class="menu-text"></span> -->
    <span>菜单</span>
</a>

<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->
    <ul class="nav nav-list">
        <li class="${requestScope.menu[0]}">
            <a href="<%=request.getContextPath()%>/admin/index">
                <i class="icon-home"></i>
                <span class="menu-text"> 首页 </span>
            </a>
        </li>


        <li class="${requestScope.menu[6]}">
            <a href="#" class="dropdown-toggle">
                <i class="icon-user"></i>
                <span class="menu-text"> 文章管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li class="${requestScope.menu[1]}">
                    <a href="<%=request.getContextPath()%>/articleManager/uploadArticle">
                        <i class="icon-list-alt"></i>
                        <span class="menu-text"> 文章发布 </span>
                    </a>
                </li>
                <!-- <li class="active"> -->
                <li class="${requestScope.menu[2]}">
                    <a href="<%=request.getContextPath()%>/articleManager/manager">
                        <i class="icon-double-angle-right"></i>
                        文章管理
                    </a>
                </li>

                <li class="${requestScope.menu[7]}">
                    <a href="<%=request.getContextPath()%>/articleManager/uploadArticleType">
                        <i class="icon-double-angle-right"></i>
                        文章类型上传
                    </a>
                </li>

                <li class="${requestScope.menu[8]}">
                    <a href="<%=request.getContextPath()%>/articleManager/typeManager">
                        <i class="icon-double-angle-right"></i>
                        文章类型管理
                    </a>
                </li>
            </ul>
        </li>

        <li class="${requestScope.menu[9]}">
            <a href="#" class="dropdown-toggle">
                <i class="icon-user"></i>
                <span class="menu-text"> 用户管理 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li class="${requestScope.menu[10]}">
                    <a href="<%=request.getContextPath()%>/admin/user/index">
                        <i class="icon-double-angle-right"></i>
                        用户信息
                    </a>
                </li>
            </ul>
        </li>


        <li class="${requestScope.menu[3]}">
            <a href="<%=request.getContextPath()%>/admin/uploadImg">
                <i class="icon-list-alt"></i>
                <span class="menu-text"> 图片页内容上传 </span>
            </a>
        </li>

        <li class="${requestScope.menu[4]}">
            <a href="<%=request.getContextPath()%>/admin/index">
                <i class="icon-list-alt"></i>
                <span class="menu-text"> 图片管理 </span>
            </a>
        </li>

        <li class="${requestScope.menu[5]}">
            <a href="<%=request.getContextPath()%>/admin/changePassword">
                <i class="icon-list-alt"></i>
                <span class="menu-text"> 修改密码 </span>
            </a>
        </li>

        <li>
            <a class="">
                <i class="icon-calendar"></i>
                <span class="menu-text">
									身份：
								</span>
            </a>
        </li>

        <li>
            <a style="cursor:pointer;" class="blog_exit">
                <i class="icon-picture"></i>
                <span class="menu-text"> 注销登录 </span>
            </a>
        </li>


    </ul><!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>