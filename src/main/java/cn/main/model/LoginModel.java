package cn.main.model;

import cn.main.dao.DAOFactory;
import cn.main.dao.UserDao;
import cn.main.entity.User;
import cn.main.tool.Md5;
import cn.main.tool.ResultJson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-19
 * Description: 登录模型类，完成登录注册主要逻辑
 */
public class LoginModel {

    /**
     * 登录具体处理逻辑
     *
     * @param request
     * @param response
     * @param session
     * @return
     */
    public ResultJson handleLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
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
                    if (user.getPower() == 1) {
                        resultJson.setText("admin");
                    } else {
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

    /**
     * 注册 具体实现 逻辑处理
     *
     * @param request
     * @param response
     * @return
     */
    public ResultJson handleRegister(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = Md5.get(request.getParameter("password"));
        String phone = request.getParameter("phone");
        String power = "2";
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
        ResultJson resultJson = new ResultJson();
        if (result == 1) {
            resultJson.setCode(result);
        } else {
            resultJson.setCode(result);
        }
        return resultJson;
    }
}
