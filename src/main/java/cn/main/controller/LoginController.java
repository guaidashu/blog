package cn.main.controller;

import cn.main.dao.DAOFactory;
import cn.main.dao.UserDao;
import cn.main.entity.User;
import cn.main.tool.Md5;
import cn.main.tool.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "login")
@SessionAttributes("user")
public class LoginController {
    @RequestMapping(value = {"index", "/", ""}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("login/index");
        model.addObject("title", "登录");
        model.addObject("path", request.getContextPath());
        return model;
    }

    @RequestMapping(value = "handle", method = RequestMethod.POST)
    public @ResponseBody
    ResultJson handle(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        response.setCharacterEncoding("UTF-8");
        // 获取信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 进行数据库信息获取
        UserDao userDao = DAOFactory.getUserDAOInstance();
        ResultJson resultJson = new ResultJson();
        try {
            User user = userDao.queryByPhone(username);
            if (user != null) {
                if (user.getPassword().equals(Md5.get(password))) {
                    // 返回flag表示登录成功
                    session.setAttribute("user", user);
                    if(user.getPower() == 1){
                        resultJson.setText("admin");
                    }else{
                        resultJson.setText("ok");
                    }
                } else {
                    // 返回flag表示登录失败
                    resultJson.setText("密码错误");
                }
            } else {
                // 返回flag表示登录失败
                resultJson.setText("用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultJson;
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public void register(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = new HashMap<String, String>();
        String username = "<奕弈></p>";
        String password = Md5.get("wyysdsa!");
        String phone = "13739497421";
        String power = "1";
        map.put("username", username);
        map.put("password", password);
        map.put("phone", phone);
        map.put("power", power);
        int result = 0;
        try {
            UserDao userDao = DAOFactory.getUserDAOInstance();
            result = userDao.insert(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            PrintWriter pw = response.getWriter();
            if(result == 1){
                pw.write(result + "");
            }else{
                pw.write(result + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "loginOut", method = RequestMethod.GET)
    public @ResponseBody
    ResultJson loginOut(HttpSession session) {
        ResultJson resultJson = new ResultJson();
        session.invalidate();
        resultJson.setText("ok");
        return resultJson;
    }

}
