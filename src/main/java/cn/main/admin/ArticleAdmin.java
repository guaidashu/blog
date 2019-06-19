package cn.main.admin;

import cn.main.config.Config;
import cn.main.dao.DAOFactory;
import cn.main.entity.User;
import cn.main.model.ArticleModel;
import cn.main.service.ArticleService;
import cn.main.tool.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @Author: yy
 * @Date: 18-11-12 16:30
 * @Description: 文章管理控制器(后台)
 **/
@SuppressWarnings("Duplicates")
@Controller
@RequestMapping("articleManager")
public class ArticleAdmin {
    private String menu[];

    private ArticleModel articleModel;
    private ArticleService articleService;

    public ArticleAdmin() {
        this.articleModel = new ArticleModel();
        this.articleService = new ArticleService();
    }

    /**
     * 文章上传前台页面
     *
     * @param request
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "uploadArticle", method = RequestMethod.GET)
    public ModelAndView uploadArticle(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.uploadArticle(request, session, response);
    }

    /**
     * 单图/多图上传处理
     *
     * @param files
     * @param request
     * @param session
     * @param response
     * @return
     */
    // 文章图片上传处理
    @RequestMapping(value = "uploadArticleImgHandle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson uploadArticleImgHandle(@RequestParam("file[]") MultipartFile[] files, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.uploadArticleImgHandle(files, request, session, response);
    }

    /**
     * 文章上传 后台处理方法
     *
     * @param move
     * @param type
     * @param title
     * @param describe
     * @param content
     * @param session
     * @param request
     * @param response
     * @param handleType
     * @return
     */
    @RequestMapping(value = "uploadArticleHandle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson uploadArticleHandle(@RequestParam("move[]") String[] move, @RequestParam("type") String type, @RequestParam("title") String title, @RequestParam("describe") String describe, @RequestParam("content") String content, HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam("handleType[]") String[] handleType) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.uploadArticleHandle(move, type, title, describe, content, session, handleType);
    }

    /**
     * 文章管理前台页面
     *
     * @param response
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "manager", method = RequestMethod.GET)
    public ModelAndView manager(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.manager(response, request, session);
    }

    /**
     * 文章管理后台 删除 处理函数
     *
     * @param response
     * @param session
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson delete(HttpServletResponse response, HttpSession session, HttpServletRequest request, @RequestParam("id") int id) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.deleteArticle(id);
    }

    /**
     * 文章类型上传
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "uploadArticleType", method = RequestMethod.GET)
    public ModelAndView uploadArticleType(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/article/uploadArticleType");
        model.addObject("title", "文章类型上传");
        model.addObject("classurl", request.getContextPath() + "/articleManager/uploadArticle");
        model.addObject("classname", "文章");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[7] = "active";
        menu[6] = "open active";
        request.setAttribute("menu", menu);
        return model;
    }

    /**
     * 文章类型上传处理方法
     *
     * @param content
     * @param session
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadArticleTypeHandle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson uploadArticleTypeHandle(@RequestParam("content") String content, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.uploadArticleTypeHandle(content);
    }

    /**
     * 修改文章前台页面
     *
     * @param session
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "modifyArticle", method = RequestMethod.GET)
    public ModelAndView modifyArticle(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.modifyArticle(request, session);
    }

    /**
     * 文章类型管理（删除修改等,前端页面）
     *
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "typeManager", method = RequestMethod.GET)
    public ModelAndView typeManager(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView model = new ModelAndView("admin/article/typeManager");
        model.addObject("title", "文章类型管理");
        model.addObject("classurl", request.getContextPath() + "/articleManager/uploadArticle");
        model.addObject("classname", "文章");
        request.setAttribute("user", (User) session.getAttribute("user"));
        List<Map> typeList = DAOFactory.getArticleTypeInstance().queryAll();
        model.addObject("typeList", typeList);
        menu = new Config().getMenu();
        menu[6] = "open active";
        menu[8] = "active";
        request.setAttribute("menu", menu);
        return model;
    }

    /**
     * 文章类型删除处理方法
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "typeManagerDelete", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson typeManagerDelete(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ResultJson resultJson = new ResultJson();
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                resultJson.setText("failed");
                return resultJson;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.articleModel.typeManagerDelete(resultJson, id);
    }

    /**
     * 上传图片删除
     *
     * @param imgPath
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "deleteImage", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson deleteImage(@RequestParam("filename") String imgPath, HttpServletRequest request, HttpSession session) {
        ResultJson resultJson = new ResultJson();
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                resultJson.setText("failed");
                return resultJson;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.articleService.deleteImage(resultJson, imgPath, request);
    }

}






























