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
@RequestMapping(value = "admin")
public class ImageAdmin {
    private String[] menu;

    /**
     * 图片管理首页(前端控制器)
     * @param request
     * @param response
     * @param session
     * @return
     */

    @RequestMapping(value = "uploadImg", method = RequestMethod.GET)
    public ModelAndView uploadImg(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/index/uploadImg");
        model.addObject("title", "图片上传");
        model.addObject("classurl", request.getContextPath() + "/admin/uploadImg");
        model.addObject("classname", "图片");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[3] = "active";
        request.setAttribute("menu", menu);
        return model;
    }
}
