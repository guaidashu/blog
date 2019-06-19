package cn.main.controller;

import cn.main.model.ArticleModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yy
 * @Date: 18-11-12 16:30
 * @Description:
 **/
@SuppressWarnings("Duplicates")
@Controller
@RequestMapping(value = "article")
public class ArticleController {
    private ArticleModel articleModel;

    public ArticleController() {
        this.articleModel = new ArticleModel();
    }

    //    文章
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView article(HttpServletRequest request) {
        return this.articleModel.viewArticle(request);
    }

    //    文章详情
    @RequestMapping(value = "articleDetails", method = RequestMethod.GET)
    public ModelAndView articleDetails(@RequestParam("id") int id, HttpServletRequest request) {
        return this.articleModel.articleDetails(id, request);
    }

}
