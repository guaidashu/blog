package cn.main.controller;

import cn.main.dao.DAOFactory;
import cn.main.dao.UserDao;
import cn.main.entity.User;
import cn.main.model.LoginModel;
import cn.main.tool.Md5;
import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录控制器
 */
@Controller
@RequestMapping(value = "login")
@SessionAttributes("user")
public class LoginController {

    private LoginModel loginModel;

    public LoginController() {
        this.loginModel = new LoginModel();
    }

    /**
     * 登录主页面方法
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("login/index");
        model.addObject("title", "登录");
        model.addObject("path", request.getContextPath());
        return model;
    }

    /**
     * 登录处理方法
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "handle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson handle(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return this.loginModel.handleLogin(request, response, session);
    }

    /**
     * 注册页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("login/register");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 注册方法
     *
     * @param response
     */
    @RequestMapping(value = "registerHandle", method = RequestMethod.GET)
    public @ResponseBody
    ResultJson registerHandle(HttpServletRequest request, HttpServletResponse response) {
        return this.loginModel.handleRegister(request, response);
    }

    /**
     * 退出登录方法
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "loginOut", method = RequestMethod.GET)
    public @ResponseBody
    ResultJson loginOut(HttpSession session) {
        ResultJson resultJson = new ResultJson();
        session.invalidate();
        resultJson.setText("ok");
        return resultJson;
    }

}
