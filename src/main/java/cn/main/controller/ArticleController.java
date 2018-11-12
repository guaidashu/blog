package cn.main.controller;

import cn.main.config.Config;
import cn.main.dao.DAOFactory;
import cn.main.tool.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
@RequestMapping(value = "article")
public class ArticleController {
    private String[] menu;

    //    文章
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView article(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("article/index");
        menu = Config.getPreMenu();
        menu[1] = "am-active";
        request.setAttribute("menu", menu);
        List<Map> articleList = null;
        int pageNum = 0;
        int page = 0;
        try {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                pageNum = (DAOFactory.getArticleInstance().queryCountAll()/10) + 1;
                if (page > pageNum){
                    page = 0;
                } else if (page > 0) {
                    page = page - 1;
                } else if(page < 0){
                    page = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            int limit_start = page * 10;
            Map map = new HashMap();
            map.put("limit_start", limit_start);
            map.put("limit_num", 10);
            articleList = DAOFactory.getArticleInstance().queryAll(map);
            articleList = Tool.getDateObject(articleList, "upload_time");
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            articleList = Tool.getAboutData(articleList, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("articleList", articleList);
        model.addObject("title", "文章");
        model.addObject("pageNum", pageNum);
        model.addObject("currentPage", page + 1);
        return model;
    }

    //    文章详情
    @RequestMapping(value = "articleDetails", method = RequestMethod.GET)
    public ModelAndView articleDetails(@RequestParam("id") int id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("article/articleDetails");
        menu = Config.getPreMenu();
        menu[1] = "am-active";
        request.setAttribute("menu", menu);
        Map article = null;
        Date date = null;
        try {
            article = DAOFactory.getArticleInstance().queryById(id);
            date = new Date(Long.parseLong((String) article.get("upload_time")));
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            article = Tool.getAboutDataSingle(article, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (article == null) {
            try {
                int page = 0;
                int limit_start = page * 10;
                Map map = new HashMap();
                map.put("limit_start", limit_start);
                map.put("limit_num", 1);
                article = DAOFactory.getArticleInstance().queryAll(map).get(0);
                date = new Date(Long.parseLong((String) article.get("upload_time")));
                List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
                article = Tool.getAboutDataSingle(article, typeName, "article_type", "article_type", "id", "type_name");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (article == null) {
            article.put("show_img", "images/article_common.jpg");
            article.put("title", "访问的数据不存在");
            article.put("author", "奕弈");
            article.put("article_type", "Code");
            article.put("content", null);
            date = new Date();
        }
        model.addObject("date", date);
        model.addObject("article", article);
        return model;
    }


}
