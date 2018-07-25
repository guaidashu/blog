package cn.main.admin;

import cn.main.config.Config;
import cn.main.entity.User;
import cn.main.tool.Check;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 用户管理控制器(后台)
 */
@Controller
@RequestMapping(value = "admin/user")
public class UserAdmin {
    private String menu[];

    /**
     * 用户信息方法
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/user/index");
        model.addObject("title", "用户信息");
        model.addObject("classurl", request.getContextPath() + "/admin/user");
        model.addObject("classname", "用户管理");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[9] = "open active";
        menu[10] = "active";
        request.setAttribute("menu", menu);
        return model;
    }
}
