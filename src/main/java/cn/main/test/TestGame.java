package cn.main.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by yy
 * Date: 2019-06-19
 * Description:
 */
@Controller
@RequestMapping(value = "testGame")
public class TestGame {
    @RequestMapping(value = "game", method = RequestMethod.GET)
    public ModelAndView game() {
        return new ModelAndView("test/testGame");
    }

    @RequestMapping(value = "game2", method = RequestMethod.GET)
    public ModelAndView game2() {
        return new ModelAndView("test/testGame2");
    }

}
