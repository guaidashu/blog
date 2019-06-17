package cn.main.admin;

import cn.main.config.SecureConfig;
import cn.main.tool.QiNiuYun;
import cn.main.tool.UeditorJson;
import com.baidu.ueditor.ActionEnter;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Controller
public class UploadImage {
    @RequestMapping(value = "ueditor/jsp/ueditorUploadImage")
    public @ResponseBody
    String ueditorUploadImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Type", "text/html");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String exec = new ActionEnter(request, rootPath).exec();
        if (request.getParameter("noCache") != null) {
            return exec;
        }
        JSONObject jsonObject = JSONObject.fromObject(exec);
        UeditorJson ueditorJson = (UeditorJson) JSONObject.toBean(jsonObject, UeditorJson.class);
        // 将图片传到七牛云
        QiNiuYun qiNiuYun = new QiNiuYun(SecureConfig.getQiNiuYunConfig());
        qiNiuYun.uploadFile(rootPath + ueditorJson.getUrl(), ueditorJson.getTitle());
        // 删除本地的图片
        File file = new File(rootPath + ueditorJson.getUrl());
        if (file.exists()) {
            file.delete();
        }
        ueditorJson.setUrl("/" + ueditorJson.getTitle());
        JSONObject json = JSONObject.fromObject(ueditorJson);
        exec = json.toString();
        return exec;
    }
}
