package cn.main.dao;

// 数据库连接类
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String DBDRIVER = "com.mysql.jdbc.Driver";
    private final String DBURL = "jdbc:mysql://127.0.0.1:3306/blog";
    private final String DBUSER = "yy";
    private final String DBPASSWORD = "wyysdsa!";
    private Connection conn = null;

    public DBConnection() {
        try {
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void close() {
        if(this.conn != null){
            try{
                this.conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
