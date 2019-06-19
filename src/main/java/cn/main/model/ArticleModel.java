package cn.main.model;

import cn.main.config.Config;
import cn.main.config.SecureConfig;
import cn.main.dao.ArticleDao;
import cn.main.dao.DAOFactory;
import cn.main.entity.User;
import cn.main.tool.ImageScaleTool;
import cn.main.tool.QiNiuYun;
import cn.main.tool.ResultJson;
import cn.main.tool.Tool;
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
 * Create by yy
 * Date: 2019-06-19
 * Description: 文章模型类
 */
@SuppressWarnings("Duplicates")
public class ArticleModel {

    private String[] menu;

    /**
     * 文章列表页视图 具体数据逻辑处理
     *
     * @param request
     * @return
     */
    public ModelAndView viewArticle(HttpServletRequest request) {
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
                pageNum = (DAOFactory.getArticleInstance().queryCountAll() / 10) + 1;
                if (page > pageNum) {
                    page = 0;
                } else if (page > 0) {
                    page = page - 1;
                } else if (page < 0) {
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
        page = page + 1;
        model.addObject("pageNum", pageNum);
        model.addObject("currentPage", page);
        int prevPage;
        int nextPage;
        if (page == pageNum && page > 1) {
            prevPage = page - 1;
            nextPage = page;
        } else if (page < pageNum && page > 1) {
            prevPage = page - 1;
            nextPage = page + 1;
        } else if (page == 1 && pageNum == 1) {
            prevPage = page;
            nextPage = page;
        } else {
            prevPage = page;
            nextPage = 1 + page;
        }
        model.addObject("nextPage", request.getContextPath() + "/article?page=" + nextPage);
        model.addObject("prevPage", request.getContextPath() + "/article?page=" + prevPage);
        return model;
    }

    /**
     * 文章详情 具体数据获取和处理 逻辑处理
     *
     * @param id
     * @param request
     * @return
     */
    public ModelAndView articleDetails(int id, HttpServletRequest request) {
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
        // 如果没有获取到文章，则获取第一条数据(第一篇文章)
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

    /**
     * 后台文章上传 具体逻辑处理
     *
     * @param files
     * @param request
     * @param session
     * @param response
     * @return
     */
    public ResultJson uploadArticleImgHandle(MultipartFile[] files, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
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
            Map<String, String> qiNiuYunConfig = SecureConfig.getQiNiuYunConfig();
            QiNiuYun qiNiuYun = new QiNiuYun(qiNiuYunConfig);
            qiNiuYun.uploadFile(request.getSession().getServletContext().getRealPath("/") + "upload/" + fileNewName[0], fileNewName[0]);
            qiNiuYun.uploadFile(request.getSession().getServletContext().getRealPath("/") + "upload/origin_" + fileNewName[0], "origin_" + fileNewName[0]);
            File file = new File(request.getSession().getServletContext().getRealPath("/") + "upload/" + fileNewName[0]);
            if (file.exists()) {
                file.delete();
            }
            File originFile = new File(request.getSession().getServletContext().getRealPath("/") + "upload/origin_" + fileNewName[0]);
            if (originFile.exists()) {
                originFile.delete();
            }
            // resultJson.setText(request.getContextPath() + "/upload/" + fileNewName[0]);
            // resultJson.setImageName(request.getContextPath() + "/upload/origin_" + fileNewName[0]);
            resultJson.setText("http://image.tan90.club/" + fileNewName[0]);
            resultJson.setImageName("http://image.tan90.club/origin_" + fileNewName[0]);
            resultJson.setId(fileNewNameReal[0]);
            resultJson.setReply("1");
        }
        return resultJson;
    }

    /**
     * 上传文章前端页面 数据设置 逻辑处理
     *
     * @param request
     * @param session
     * @param response
     * @return
     */
    public ModelAndView uploadArticle(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
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
     * 文章上传 后端 具体逻辑处理
     *
     * @param move
     * @param type
     * @param title
     * @param describe
     * @param content
     * @param session
     * @param handleType
     * @return
     */
    public ResultJson uploadArticleHandle(String[] move, String type, String title, String describe, String content, HttpSession session, String[] handleType) {
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
                imgpath = Tool.base64Decode(v);
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
        map.put("show_img", imgpath);
        content = Tool.base64Decode(content);
        map.put("content", content);
        map.put("upload_time", Tool.getTimeStamp());
        map.put("describe", describe);
        map.put("origin_img", "origin_" + imgpath);
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
    public ModelAndView manager(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
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
     * 文章删除具体逻辑实现函数
     *
     * @param id
     * @return
     */
    public ResultJson deleteArticle(int id) {
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
     * 文章类型上传逻辑处理函数
     *
     * @param content
     * @return
     */
    public ResultJson uploadArticleTypeHandle(String content) {
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
     * 修改文章 前端页面
     * @param request
     * @param session
     * @return
     */
    public ModelAndView modifyArticle(HttpServletRequest request, HttpSession session) {
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
     * 文章类型删除逻辑处理
     * @param resultJson
     * @param id
     * @return
     */
    public ResultJson typeManagerDelete(ResultJson resultJson, int id) {
        int result = DAOFactory.getArticleTypeInstance().delete(id);
        if (result == 1) {
            resultJson.setText("ok");
        } else {
            resultJson.setText("删除失败，请稍后再试！");
        }
        return resultJson;
    }
}
