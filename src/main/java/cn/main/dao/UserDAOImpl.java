package cn.main.dao;

import cn.main.entity.User;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDao{
    public int insert(Map<String, String> map) throws Exception {
        DBConnection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int result = 0;
        String sql = "insert into blog_user(username,password,phone,power,registertime)values(?,?,?,?,?)";
        try{
            conn = new DBConnection();
            pstm = conn.getConn().prepareStatement(sql);
            pstm.setString(1, map.get("username"));
            pstm.setString(2, map.get("password"));
            pstm.setString(3, map.get("phone"));
            pstm.setInt(4, Integer.parseInt(map.get("power")));
            pstm.setString(5, Calendar.getInstance().getTimeInMillis() + "");
            result = pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }finally {
            if(conn != null){
                conn.close();
            }
        }
        return result;
    }

    public int delete() throws Exception {
        return 0;
    }

    @Override
    public int updatePassword(Map map) {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.update("user.updatePassword", map);
        sqlSession.commit();
        return result;
    }

    public User queryByPhone(String phone) throws Exception {
        DBConnection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from blog_user where phone=?";
        try{
            conn = new DBConnection();
            pstm = conn.getConn().prepareStatement(sql);
            pstm.setString(1, phone);
            rs = pstm.executeQuery();
            // 获取结果
            while(rs.next()){
                user = new User();
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setPower(rs.getInt(5));
            }
            if(pstm != null){
                pstm.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.close();
            }
        }
        return user;
    }

    public User queryById(String userid) throws Exception {
        return null;
    }

    public List<User> queryAll() throws Exception {
        return null;
    }
}
