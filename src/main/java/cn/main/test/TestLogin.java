package cn.main.test;

import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description:
 */

@Controller
@RequestMapping(value = "test")
public class TestLogin {
    /**
     * 登录页面
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "testLogin", method = RequestMethod.GET)
    public ModelAndView testLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("test/login");
        String path = request.getContextPath();
        modelAndView.addObject("path", path);
        return modelAndView;
    }

    /**
     * 登录表单提交数据的处理
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "testDoLogin", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson testDoLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String PASSWORD = "wyysdsa!";
        String USERNAME = "13739497421";
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        ResultJson resultJson = new ResultJson();
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            resultJson.setCode(0);
        } else {
            resultJson.setCode(1);
        }
        return resultJson;
    }
}
