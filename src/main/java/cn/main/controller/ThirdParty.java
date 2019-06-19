package cn.main.controller;

import cn.main.service.ThirdPartyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description:
 */

@Controller
@RequestMapping(value = "thirdParty")
public class ThirdParty {

    private ThirdPartyService thirdPartyService;

    public ThirdParty() {
        this.thirdPartyService = new ThirdPartyService();
    }

    /**
     * qq第三方登录授权页面
     *
     * @param response
     */
    @RequestMapping(value = "qqLogin", method = RequestMethod.GET)
    public void qqLogin(HttpServletResponse response) {
        String url = this.thirdPartyService.getQQLoginUrl();
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
        this.thirdPartyService.qqLoginCallback(request);
        // try {
        //     response.sendRedirect("");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}
