package cn.main.admin;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return new ActionEnter(request, rootPath).exec();
    }
}
