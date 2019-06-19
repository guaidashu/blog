package cn.main.controller;

import cn.main.config.Config;
import cn.main.dao.DAOFactory;
import cn.main.model.SearchModel;
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
    private SearchModel searchModel;

    public SearchController() {
        this.searchModel = new SearchModel();
    }

    /**
     * 根据标题搜索
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = {"searchTitle", "index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) {
        return this.searchModel.searchTitle(request, session);
    }

    /**
     * 根据类型搜索
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "searchType", method = RequestMethod.GET)
    public ModelAndView searchType(HttpServletRequest request, HttpSession session) {
        return this.searchModel.searchType(request, session);
    }
}
