package cn.main.tool;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description: QQ登录第三方接口调用等操作
 */
public class QQLogin {
    private String accessToken;
    private String openId;
    private Map<String, String> config;
    private Map<String, String> userInfo;

    /**
     * 构造方法，初始化配置并且获取 access_token 写入类属性里。
     *
     * @param request
     * @param config
     */
    public QQLogin(HttpServletRequest request, Map<String, String> config) {
        this.accessToken = request.getParameter("access_token");
        this.config = config;
    }

    /**
     * 获取已经获取到的 access_token
     *
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 获取已经拿到的openid，前提是已经调用过 getOpenIdFromApi
     *
     * @return
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 调用qq给的api接口获取openid
     */
    public void getOpenIdFromApi() {
        String url = "https://graph.qq.com/oauth2.0/me?access_token=" + this.accessToken;
        this.openId = SpiderTool.getData(url);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public Map<String, String> getUserInfo() {
        String url = "https://graph.qq.com/user/get_user_info?access_token=" + this.accessToken + "&oauth_consumer_key=" + this.config.get("appid") + "&openid=" + this.openId;
        String result = SpiderTool.getData(url);
        Tool.saveDataToFile(result, "/Users/cpx/code/java/blog/target/blog/upload/test.txt");
        return this.userInfo;
    }

}
