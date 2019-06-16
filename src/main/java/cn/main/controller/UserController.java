package cn.main.controller;

import cn.main.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("user/user");
        model.addObject("path", request.getContextPath());
        return model;
    }

    @RequestMapping(value = "/userHandle", method = RequestMethod.POST)
    public ModelAndView userHandle(User user) throws Exception {
        ModelAndView model = new ModelAndView("user/userHandle");
        List<User> list = new ArrayList<User>();
        list.add(user);
        model.addObject("data", list);
        return model;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView model = new ModelAndView("user/userHandle");
        return model;
    }
}
