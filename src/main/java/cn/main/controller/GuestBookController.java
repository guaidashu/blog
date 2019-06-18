package cn.main.controller;

import cn.main.config.Config;
import cn.main.tool.Check;
import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "guestBook")
public class GuestBookController {
    private String[] menu;

    /**
     * 留言板，但是目前来看好像暂时没什么用
     * @param request
     * @return
     */
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("guestbook/index");
        menu = Config.getPreMenu();
        menu[3] = "am-active";
        request.setAttribute("menu", menu);
        model.addObject("title", "留言板");
        return model;
    }

    /**
     * 存储留言
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = {"saveGuestBook"}, method = RequestMethod.POST)
    public @ResponseBody
    ResultJson saveGuestBook(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (!Check.checkLogin(session)){
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ResultJson resultJson = new ResultJson();
        return resultJson;
    }
}
