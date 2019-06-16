package cn.main.admin;

import cn.main.config.Config;
import cn.main.dao.DAOFactory;
import cn.main.entity.User;
import cn.main.tool.Check;
import cn.main.tool.Md5;
import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 后台管理主入口类
 */
@SuppressWarnings("Duplicates")
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    private String menu[];

    /**
     * 后台首页
     *
     * @param request
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = {"/", "index", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/index/index");
        model.addObject("title", "后台首页");
        model.addObject("classurl", request.getContextPath() + "/admin/index");
        model.addObject("classname", "首页");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[0] = "active";
        request.setAttribute("menu", menu);
        return model;
    }

    /**
     * 修改密码页面前端
     *
     * @param request
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.GET)
    public ModelAndView changePassword(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/index/changePassword");
        model.addObject("title", "修改密码");
        model.addObject("classurl", request.getContextPath() + "/admin/index");
        model.addObject("classname", "首页");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[5] = "active";
        request.setAttribute("menu", menu);
        return model;
    }

    /**
     * 修改密码处理方法
     *
     * @param request
     * @param session
     * @param response
     * @param old_password
     * @param password
     * @return
     */
    @RequestMapping(value = "changePasswordHandle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson changePasswordHandle(HttpServletRequest request, HttpSession session, HttpServletResponse response, @RequestParam("old_password") String old_password, @RequestParam("password") String password) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ResultJson resultJson = new ResultJson();
        User user = (User) session.getAttribute("user");
        if (!Md5.get(old_password).equals(user.getPassword())) {
            resultJson.setText("原密码错误");
            return resultJson;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getUserid());
        map.put("password", Md5.get(password));
        int result = DAOFactory.getUserDAOInstance().updatePassword(map);
        if (result == 1) {
            resultJson.setText("ok");
        } else {
            resultJson.setText("密码修改出错");
        }
        return resultJson;
    }

}
