package cn.main.controller;

import cn.main.config.Config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "img")
public class ImgController {
    private String[] menu;

    // 图片
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView img(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("img/index");
        model.addObject("title", "图片");
        menu = Config.getPreMenu();
        menu[2] = "am-active";
        request.setAttribute("menu", menu);
        return model;
    }

}
