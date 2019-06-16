package cn.main.controller;

import cn.main.config.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "guestbook")
public class GuestbookController {
    private String[] menu;

    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("guestbook/index");
        menu = Config.getPreMenu();
        menu[3] = "am-active";
        request.setAttribute("menu", menu);
        model.addObject("title", "留言板");
        return model;
    }
}
