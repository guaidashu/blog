package cn.main.service;

import cn.main.config.SecureConfig;
import cn.main.tool.QQLogin;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-19
 * Description:
 */
public class ThirdPartyService {

    /**
     * qq第三方登录 重定向的登录页url 构造
     * @return
     */
    public String getQQLoginUrl() {
        String url = "https://graph.qq.com/oauth2.0/authorize";
        Map<String, String> qqLoginConfig = SecureConfig.getQQLoginConfig();
        url = url + "?response_type=code&client_id=" + qqLoginConfig.get("appid") + "&redirect_uri=" + qqLoginConfig.get("redirect_uri") + "&state=dsafsdwa";
        return url;
    }

    /**
     * qq第三方登录回调 事件处理
     *
     * @param request
     */
    public void qqLoginCallback(HttpServletRequest request) {
        QQLogin qqLogin = new QQLogin(request, SecureConfig.getQQLoginConfig());
        qqLogin.getOpenIdFromApi();
        Map<String, String> userInfo = qqLogin.getUserInfo();
    }
}
