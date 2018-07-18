package cn.main.controller;

import cn.main.dao.DAOFactory;
import cn.main.entity.Article;
import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import cn.main.tool.GetWXImage;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request)
    {
        ModelAndView model = new ModelAndView("test/test");
        Map<String, String> map = new HashMap<String, String>();
        map.put("姓名", "奕弈");
        map.put("电话", "13739497421");
        model.addObject("data", map);
        model.addObject("path", request.getContextPath());
        Map article = null;
        try {
            article = DAOFactory.getArticleInstance().queryById(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("article", article);
        return model;
    }

    @RequestMapping(value = "curl", method = RequestMethod.GET)
    public void curl(HttpServletRequest request, HttpServletResponse response)
    {
        String address = request.getParameter("url");
//        String address = "https://mp.weixin.qq.com/s?__biz=MzU4MjAyOTA3MA==&mid=2247486246&idx=1&sn=e2bdc216c8dc84151e2605959d8d4134&chksm=fdbfd147cac8585191ffae9aaffc708cf58c78892f4ed136bc361f39ea765c9eedeb39a2645d&scene=27#wechat_redirect";
        response.setContentType("text/html;");
        GetWXImage getWXImage = new GetWXImage();
        getWXImage.setAddress(address);
        String data = getWXImage.getData();

        try {
            PrintWriter pw = response.getWriter();
            pw.write(data);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "curlPre", method = RequestMethod.GET)
    public ModelAndView curlPre(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("test/curlPre");
        model.addObject("title", "测试图片抓取");
        String path = request.getContextPath();
        String value = "https://mp.weixin.qq.com/s?__biz=MzU4MjAyOTA3MA==&mid=2247486246&idx=1&sn=e2bdc216c8dc84151e2605959d8d4134&chksm=fdbfd147cac8585191ffae9aaffc708cf58c78892f4ed136bc361f39ea765c9eedeb39a2645d&scene=27#wechat_redirect";
        try{
            value = URLEncoder.encode(value, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addObject("url", value);
        model.addObject("path",path);
        return model;
    }

    @RequestMapping(value = "testInsert", method = RequestMethod.GET)
    public @ResponseBody
    String testInsert(){
        Map<Object, Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("content", "测试");
        DAOFactory.getTestInstance().insertTest(map);
        return  (String) map.get("id");
    }
}
