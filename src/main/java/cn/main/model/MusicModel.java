package cn.main.model;

import org.springframework.web.servlet.ModelAndView;

/**
 * Create by yy
 * Date: 2019-06-20
 * Description:
 */
public class MusicModel {
    public ModelAndView getMusicList() {
        ModelAndView modelAndView = new ModelAndView("music/music");
        return modelAndView;
    }
}
