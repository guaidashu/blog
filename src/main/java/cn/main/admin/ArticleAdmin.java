package cn.main.admin;

import cn.main.config.Config;
import cn.main.dao.ArticleDao;
import cn.main.dao.DAOFactory;
import cn.main.entity.User;
import cn.main.tool.Check;
import cn.main.tool.ResultJson;
import cn.main.tool.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 文章管理控制器(后台)
 */
@Controller
@RequestMapping("articleManager")
public class ArticleAdmin {
    private String menu[];

    /**
     * 文章上传前台页面
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
            // 移动文件到指定上传目录
            try {
                files[i].transferTo(new File(fileNewNameReal[i]));
            } catch (IOException e) {
                flag = false;
                e.printStackTrace();
            }
        }
        if (!flag) {
            resultJson.setText("图片上传出错，未知错误");
        } else {
            resultJson.setText(request.getContextPath() + "/upload/" + fileNewName[0]);
            resultJson.setId(fileNewNameReal[0]);
            resultJson.setReply("1");
        }
        return resultJson;
    }

    /**
     * 文章上传 后台处理方法
     * @param move
     * @param type
     * @param title
     * @param describe
     * @param content
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "uploadArticleHandle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson uploadArticleHandle(@RequestParam("move[]") String[] move, @RequestParam("type") String type, @RequestParam("title") String title, @RequestParam("describe") String describe, @RequestParam("content") String content, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
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
        BASE64Decoder base64Decoder = new BASE64Decoder();
        String imgpath = null;
        for (String v : move) {
            try {
                // 获取描述的图片路径(需要存到数据库)
                imgpath = new String(base64Decoder.decodeBuffer(v), "UTF-8");
                String pattern = "(upload[\\w\\W]*+)";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(imgpath);
                m.find();
                imgpath = m.group(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, String> map = new HashMap<String, String>();
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
        ArticleDao articleDao = DAOFactory.getArticleInstance();
        int result = 0;
        try {
            result = articleDao.insert(map);
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            list = DAOFactory.getArticleInstance().queryAll();
            // 获取文章类型
            List<Map> typeName = DAOFactory.getArticleTypeInstance().queryAll();
            list = Tool.getAboutData(list, typeName, "article_type", "article_type", "id", "type_name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = Tool.getDateObject(list, "upload_time");
        model.addObject("list", list);
        request.setAttribute("menu", menu);
        return model;
    }

    /**
     * 文章管理后台处理函数
     * @param response
     * @param session
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody ResultJson delete(HttpServletResponse response, HttpSession session, HttpServletRequest request, @RequestParam("id") int id){
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
        if(result != 0){
            resultJson.setText("ok");
        }else{
            resultJson.setText("删除失败，请稍后再试");
        }
        return resultJson;
    }

    /**
     * 文章类型上传
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "uploadArticleType", method = RequestMethod.GET)
    public ModelAndView uploadArticleType(HttpServletRequest request, HttpServletResponse response, HttpSession session){
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
     * @param content
     * @param session
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "uploadArticleTypeHandle", method = RequestMethod.POST)
    public @ResponseBody ResultJson uploadArticleTypeHandle(@RequestParam("content") String content, HttpSession session, HttpServletResponse response, HttpServletRequest request){
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
        if(result == 1){
            resultJson.setText("ok");
        }else {
            resultJson.setText("上传出错，请稍后再试！");
        }
        return resultJson;
    }

    /**
     * 修改文章前台页面
     * @param session
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "modifyArticle", method = RequestMethod.GET)
    public ModelAndView modifyArticle(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        // 判断是否登录
        if (!Check.checkAdminLogin(session)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        return model;
    }

    /**
     * 文章类型管理（删除修改等,前端页面）
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "typeManager", method = RequestMethod.GET)
    public ModelAndView typeManager(HttpSession session, HttpServletRequest request, HttpServletResponse response){
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
     * @param id
     * @return
     */
    @RequestMapping(value = "typeManagerDelete", method = RequestMethod.POST)
    public @ResponseBody ResultJson typeManagerDelete(@RequestParam("id") int id){
        ResultJson resultJson = new ResultJson();
        int result = DAOFactory.getArticleTypeInstance().delete(id);
        if(result == 1){
            resultJson.setText("ok");
        }else{
            resultJson.setText("删除失败，请稍后再试！");
        }
        return resultJson;
    }

}






























