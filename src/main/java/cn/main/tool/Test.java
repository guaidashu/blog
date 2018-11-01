package cn.main.tool;

import cn.main.dao.DAOFactory;
import cn.main.dao.DBConnection;
import cn.main.entity.User;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String args[]) {
//        testZZ();
//        testType();
//        System.out.println(getInsertId());
        File file = new File("test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("ok");
        }
    }


    public static String getInsertId() {
        Map<String, String> map = new HashMap<>();
        map.put("content", "测试");
        map.put("id", null);
        int result = DAOFactory.getTestInstance().insertTest(map);
        return map.get("id");
    }

    public static void testType() {
        String s = "1";
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println(s);
        }
    }

    public static void testMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("y1", "值一");
        map.put("t2", "值二");
        map.put("t3", "值三");
        for (String k : map.keySet()) {
            System.out.println(k);
        }
    }

    public static void testZZ() {
        String str = "/usr/ldsaocal//ueditor/upload/123456.jpg";
        String pattern = "(upload[\\w\\W]*+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        String name = null;
        m.find();
        System.out.println(m.group(0));
    }

    public static void testMysql() {
        Map<String, String> map = new HashMap<String, String>();
        String username = "奕弈";
        String password = Md5.get("wyysdsa!");
        String phone = "13739497421";
        String power = "1";
        map.put("username", username);
        map.put("password", password);
        map.put("phone", phone);
        map.put("power", power);
        DBConnection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        int result = 0;
        String sql = "insert into blog_user(username,password,phone,power)values(?,?,?,?)";
        try {
            conn = new DBConnection();
            pstm = conn.getConn().prepareStatement(sql);
            pstm.setString(1, map.get("username"));
            pstm.setString(2, map.get("password"));
            pstm.setString(3, map.get("phone"));
            pstm.setInt(4, Integer.parseInt(map.get("power")));
            pstm.executeUpdate();
            System.out.println("successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出错");
        }
    }
}
