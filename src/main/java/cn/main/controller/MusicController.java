package cn.main.controller;

import cn.main.model.MusicModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Create by yy
 * Date: 2019-06-20
 * Description:
 */

@Controller
@RequestMapping(value = "music")
public class MusicController {

    private MusicModel musicModel;

    public MusicController() {
        this.musicModel = new MusicModel();
    }

    @RequestMapping(value = {"index", "", ""}, method = RequestMethod.GET)
    public ModelAndView index() {
        return this.musicModel.getMusicList();
    }
}
