package cn.main.admin;

import cn.main.config.Config;
import cn.main.dao.ArticleDao;
import cn.main.dao.DAOFactory;
import cn.main.entity.User;
import cn.main.tool.Check;
import cn.main.tool.ImageScaleTool;
import cn.main.tool.ResultJson;
import cn.main.tool.Tool;
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
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        ModelAndView model = new ModelAndView("admin/article/uploadArticle");
        model.addObject("title", "文章上传");
        model.addObject("classurl", request.getContextPath() + "/articleManager/uploadArticle");
        model.addObject("classname", "文章");
        request.setAttribute("user", (User) session.getAttribute("user"));
        List<Map> typeList = DAOFactory.getArticleTypeInstance().queryAll();
        model.addObject("typeList", typeList);
        menu = new Config().getMenu();
        menu[1] = "active";
        menu[6] = "open active";
        request.setAttribute("menu", menu);
        return model;
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
        ResultJson resultJson = new ResultJson();
        ArrayList<String> fileNameArr = new ArrayList<String>();
        ArrayList<String> fileType = Config.getAllowImageType();
        ArrayList<String> fileTypeArr = new ArrayList<String>();
        String fileName;
        for (MultipartFile file : files) {
            fileName = file.getOriginalFilename();
            fileTypeArr.add(fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null);
            fileNameArr.add(fileName);
        }
        // 判断图片类型是否合法
        for (String v : fileTypeArr) {
            if (!fileType.contains(v)) {
                resultJson.setText("图片类型不合法");
                return resultJson;
            }
        }
        int len = fileNameArr.size();
        boolean flag = true;
        // 新创建文件名
        String fileNewName[] = new String[len];
        String fileNewNameReal[] = new String[len];
        String originFileNewName[] = new String[len];
        // 判断上传文件夹是否存在，不存在则创建
        String dir = request.getSession().getServletContext().getRealPath("/") + "upload/";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        // 遍历产生新的文件名同时进行保存和返回
        for (int i = 0; i < len; i++) {
            fileNewName[i] = Calendar.getInstance().getTimeInMillis() + "" + (int) (Math.random() * 100) + "." + fileTypeArr.get(i);
            fileNewNameReal[i] = dir + fileNewName[i];
            // 原图路径
            originFileNewName[i] = dir + "origin_" + fileNewName[i];
            // 移动文件到指定上传目录
            try {
                files[i].transferTo(new File(originFileNewName[i]));
                // 移动完成后，进行压缩(剪裁)
                Map<String, Object> param = new HashMap<>();
                param.put("type", 2);
                param.put("targetWidth", 764);
                ImageScaleTool.scaleImage(originFileNewName[i], fileNewNameReal[i], param);
            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
            }
        }
        if (!flag) {
            resultJson.setText("图片上传出错，未知错误");
        } else {
            resultJson.setText(request.getContextPath() + "/upload/" + fileNewName[0]);
            resultJson.setImageName(request.getContextPath() + "/upload/origin_" + fileNewName[0]);
            resultJson.setId(fileNewNameReal[0]);
            resultJson.setReply("1");
        }
        return resultJson;
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
        ResultJson resultJson = new ResultJson();
        // 解密(base64加密)描述的图片路径
        String imgpath = null;
        for (String v : move) {
            try {
                // 获取描述的图片路径(需要存到数据库)
                imgpath = Tool.base64Decode(v);
                String pattern = "([\\w\\W]*?)upload/([\\w\\W]*+)";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(imgpath);
                m.find();
                imgpath = m.group(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("type", type);
        try {
            String username = ((User) session.getAttribute("user")).getUsername();
            map.put("author", username);
        } catch (Exception e) {
            resultJson.setText("请先登录");
            return resultJson;
        }
        map.put("show_img", "upload/" + imgpath);
        content = Tool.base64Decode(content);
        map.put("content", content);
        map.put("upload_time", Tool.getTimeStamp());
        map.put("describe", describe);
        map.put("origin_img", "upload/origin_" + imgpath);
        ArticleDao articleDao = DAOFactory.getArticleInstance();
        int result = 0;
        if (Integer.parseInt(handleType[0]) == 1) {
            try {
                result = articleDao.insert(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                map.put("id", handleType[1]);
                result = articleDao.update(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result == 2) {
            String str = "";
            for (String s : map.keySet()) {
                str = str + ", ";
                str = str + map.get(s);
            }
            resultJson.setText(str);
        } else if (result == 1) {
            resultJson.setText("ok");
        } else {
            resultJson.setText("failed");
        }
        return resultJson;
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
        ModelAndView model = new ModelAndView("admin/article/manager");
        model.addObject("title", "文章管理");
        model.addObject("classurl", request.getContextPath() + "/articleManager/uploadArticle");
        model.addObject("classname", "文章");
        request.setAttribute("user", (User) session.getAttribute("user"));
        menu = new Config().getMenu();
        menu[2] = "active";
        menu[6] = "open active";
        List<Map> list = null;
        int pageNum = 0;
        int page = 0;
        try {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                pageNum = (DAOFactory.getArticleInstance().queryCountAll() / 10) + 1;
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
            list = DAOFactory.getArticleInstance().queryAll(map);
            // 获取文章类型
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            list = Tool.getAboutData(list, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = Tool.getDateObject(list, "upload_time");
        model.addObject("list", list);
        model.addObject("pageNum", pageNum);
        model.addObject("currentPage", page + 1);
        request.setAttribute("menu", menu);
        return model;
    }

    /**
     * 文章管理后台处理函数
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
        ResultJson resultJson = new ResultJson();
        // 进行删除
        int result = 0;
        try {
            result = DAOFactory.getArticleInstance().deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != 0) {
            resultJson.setText("ok");
        } else {
            resultJson.setText("删除失败，请稍后再试");
        }
        return resultJson;
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
        ResultJson resultJson = new ResultJson();
        Map<Object, Object> map = new HashMap<>();
        map.put("content", content);
        int result = DAOFactory.getArticleTypeInstance().insert(map);
        if (result == 1) {
            resultJson.setText("ok");
        } else {
            resultJson.setText("上传出错，请稍后再试！");
        }
        return resultJson;
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
        Map article;
        // 获取文章id
        int articleId;
        try {
            articleId = Integer.parseInt(request.getParameter("id"));
            article = DAOFactory.getArticleInstance().queryById(articleId);
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            article.put("article_type_id", article.get("article_type"));
            article = Tool.getAboutDataSingle(article, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            return new ModelAndView("error/error");
        }
        ModelAndView model = new ModelAndView("admin/article/modifyArticle");
        model.addObject("title", "文章修改");
        model.addObject("classurl", request.getContextPath() + "/articleManager/uploadArticle");
        model.addObject("classname", "文章");
        request.setAttribute("user", (User) session.getAttribute("user"));
        List<Map> typeList = DAOFactory.getArticleTypeInstance().queryAll();
        model.addObject("typeList", typeList);
        menu = new Config().getMenu();
        menu[6] = "open active";
        request.setAttribute("menu", menu);
        model.addObject("imgPath", request.getSession().getServletContext().getRealPath("/"));
        model.addObject("article", article);
        return model;
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
        int result = DAOFactory.getArticleTypeInstance().delete(id);
        if (result == 1) {
            resultJson.setText("ok");
        } else {
            resultJson.setText("删除失败，请稍后再试！");
        }
        return resultJson;
    }

    @RequestMapping(value = "deleteImage", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson deleteImage(@RequestParam("filename") String imgPath, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
        imgPath = Tool.base64Decode(imgPath);
        File file = new File(imgPath);
        String pattern = "([\\w\\W]*?)upload/([\\w\\W]*+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(imgPath);
        m.find();
        imgPath = m.group(2);
        File originFile = new File(request.getSession().getServletContext().getRealPath("/") + "upload/origin_" + imgPath);
        if (file.exists()) {
            boolean result = file.delete();
            try {
                boolean result2 = originFile.delete();
            } catch (Exception e) {

            }
            if (result) {
                resultJson.setText("ok");
            } else {
                resultJson.setText("删除失败");
            }
        } else {
            resultJson.setText("图片不存在");
        }
        return resultJson;
    }

}






























