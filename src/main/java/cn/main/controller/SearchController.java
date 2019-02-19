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
@RequestMapping(value = "search")
public class SearchController {
    private String[] menu;

    /**
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = {"searchTitle", "index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) {
        String searchCondition;
        try {
            searchCondition = request.getParameter("search");
        } catch (Exception e) {
            searchCondition = "";
        }
        ModelAndView model = new ModelAndView("search/search");
        model.addObject("title", searchCondition);
        menu = Config.getPreMenu();
        menu[1] = "am-active";
        request.setAttribute("menu", menu);
        request.setAttribute("searchCondition", searchCondition);
        List<Map> articleList = null;
        int pageNum = 0;
        int page = 0;
        try {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                Map numMap = new HashMap();
                numMap.put("article_title", searchCondition);
                pageNum = (DAOFactory.getArticleInstance().queryCountTitle(numMap) / 10) + 1;
                if (page > pageNum) {
                    page = 0;
                } else if (page > 0) {
                    page = page - 1;
                } else if (page < 0) {
                    page = 0;
                }
            } catch (Exception e) {
                page = 0;
                e.printStackTrace();
            }
            int limit_start = page * 10;
            Map map = new HashMap();
            map.put("limit_start", limit_start);
            map.put("limit_num", 10);
            map.put("article_title", "%" + searchCondition + "%");
            articleList = DAOFactory.getArticleInstance().queryByTitle(map);
            articleList = Tool.getDateObject(articleList, "upload_time");
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            articleList = Tool.getAboutData(articleList, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("user", session.getAttribute("user"));
        model.addObject("articleList", articleList);
        model.addObject("pageNum", pageNum);
        page = page + 1;
        model.addObject("currentPage", page);
        int nextPage;
        int prevPage;
        if (page == pageNum && page > 1) {
            prevPage = page - 1;
            nextPage = page;
        } else if (page < pageNum && page > 1) {
            prevPage = page - 1;
            nextPage = page + 1;
        } else if (page == 1 && pageNum == 1){
            prevPage = page;
            nextPage = page;
        } else {
            prevPage = page;
            nextPage = page + 1;
        }
        model.addObject("nextPage", request.getContextPath() + "/search/searchTitle?search=" + searchCondition + "&page=" + nextPage);
        model.addObject("prevPage", request.getContextPath() + "/search/searchTitle?search=" + searchCondition + "&page=" + prevPage);
        return model;
    }

    @RequestMapping(value = "searchType", method = RequestMethod.GET)
    public ModelAndView searchType(HttpServletRequest request, HttpSession session){
        String searchCondition;
        try {
            searchCondition = request.getParameter("searchType");
        } catch (Exception e) {
            searchCondition = "";
        }
        ModelAndView model = new ModelAndView("search/search");
        String type_name;
        try{
            type_name = DAOFactory.getArticleTypeInstance().queryById(Integer.parseInt(searchCondition)).get("type_name").toString();
        }catch (Exception e){
            type_name = "搜索结果";
        }
        model.addObject("title", type_name);
        menu = Config.getPreMenu();
        menu[1] = "am-active";
        request.setAttribute("menu", menu);
        List<Map> articleList = null;
        int pageNum = 0;
        int page = 0;
        try {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                Map numMap = new HashMap();
                numMap.put("article_type", searchCondition);
                pageNum = (DAOFactory.getArticleInstance().queryCountType(numMap) / 10) + 1;
                if (page > pageNum) {
                    page = 0;
                } else if (page > 0) {
                    page = page - 1;
                } else if (page < 0) {
                    page = 0;
                }
            } catch (Exception e) {
                page = 0;
                e.printStackTrace();
            }
            int limit_start = page * 10;
            Map map = new HashMap();
            map.put("limit_start", limit_start);
            map.put("limit_num", 10);
            map.put("article_type", searchCondition);
            articleList = DAOFactory.getArticleInstance().queryByType(map);
            articleList = Tool.getDateObject(articleList, "upload_time");
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            articleList = Tool.getAboutData(articleList, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("user", session.getAttribute("user"));
        model.addObject("articleList", articleList);
        model.addObject("pageNum", pageNum);
        page = page + 1;
        model.addObject("currentPage", page);
        int nextPage;
        int prevPage;
        if (page == pageNum && page > 1) {
            prevPage = page - 1;
            nextPage = page;
        } else if (page < pageNum && page > 1) {
            prevPage = page - 1;
            nextPage = page + 1;
        } else if (page == 1 && pageNum == 1){
            prevPage = page;
            nextPage = page;
        } else {
            prevPage = page;
            nextPage = page + 1;
        }
        model.addObject("nextPage", request.getContextPath() + "/search/searchType?searchType=" + searchCondition + "&page=" + nextPage);
        model.addObject("prevPage", request.getContextPath() + "/search/searchType?searchType=" + searchCondition + "&page=" + prevPage);
        return model;
    }
}
