package cn.main.controller;

import cn.main.config.Config;
import cn.main.dao.DAOFactory;
import cn.main.tool.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yy
 * @Date: 18-11-12 16:30
 * @Description:
 **/
@SuppressWarnings("Duplicates")
@Controller
@RequestMapping(value = {"index", "/", ""})
public class IndexController {
    private String[] menu;

    @RequestMapping(value = {"/", "index", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpSession session, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("index/index");
        model.addObject("title", "首页");
        menu = Config.getPreMenu();
        menu[0] = "am-active";
        request.setAttribute("menu", menu);
        List<Map> articleList = null;
        try {
            Map map = new HashMap();
            map.put("limit_start", 0);
            map.put("limit_num", 10);
            articleList = DAOFactory.getArticleInstance().queryAll(map);
            articleList = Tool.getDateObject(articleList, "upload_time");
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            articleList = Tool.getAboutData(articleList, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("user", session.getAttribute("user"));
        model.addObject("articleList", articleList);
        return model;
    }

}