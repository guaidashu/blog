package cn.main.controller;

import cn.main.config.SecureConfig;
import cn.main.tool.QQLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description:
 */

@Controller
@RequestMapping(value = "thirdParty")
public class ThirdParty {
    /**
     * qq第三方登录授权页面
     *
     * @param response
     */
    @RequestMapping(value = "qqLogin", method = RequestMethod.GET)
    public void qqLogin(HttpServletResponse response) {
        String url = "https://graph.qq.com/oauth2.0/authorize";
        Map<String, String> qqLoginConfig = SecureConfig.getQQLoginConfig();
        url = url + "?response_type=code&client_id=" + qqLoginConfig.get("appid") + "&redirect_uri=" + qqLoginConfig.get("redirect_uri") + "&state=dsafsdwa";
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * qq第三方登录回调页面
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "qqLoginCallback", method = RequestMethod.GET)
    public @ResponseBody
    void qqLoginCallback(HttpServletRequest request, HttpServletResponse response) {
        QQLogin qqLogin = new QQLogin(request, SecureConfig.getQQLoginConfig());
        qqLogin.getOpenIdFromApi();
        Map<String, String> userInfo = qqLogin.getUserInfo();
        // try {
        //     response.sendRedirect("");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}
