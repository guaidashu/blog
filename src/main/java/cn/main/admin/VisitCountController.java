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

@Controller
@RequestMapping(value = "admin/visit")
public class VisitCountController {
    private String menu[];

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/visit/index");
        model.addObject("title", "访问统计");
        model.addObject("classurl", request.getContextPath() + "/admin/visit/index");
        model.addObject("classname", "访问统计");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[11] = "active";
        request.setAttribute("menu", menu);
        return model;
    }
}
