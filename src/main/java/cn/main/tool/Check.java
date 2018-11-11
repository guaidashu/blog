package cn.main.tool;

import cn.main.entity.User;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Check {
    public static boolean checkAdminLogin(HttpSession session) {
        User user = null;
        try {
            user = (User) session.getAttribute("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            return false;
        } else {
            if ((user.getPower() + "").equals("1")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean checkLogin(HttpSession session) {
        User user = null;
        try {
            user = (User) session.getAttribute("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
}
