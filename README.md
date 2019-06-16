**Add some file**

If you want to use this blog source code.
You should add src/main/java/cn/main/dao/DBConfig.java, this file is a config which article dao needs.
 is its content:

    package cn.main.dao;
    
    /**
     * @Author: yy
     * @Date: 18-11-11 22:00
     * @Description:
     **/
    public class DBConfig {
        public String getUsername() {
            return username;
        }
    
        public String getPassword() {
            return password;
        }
    
        private String username = "";
        private String password = ";
    }



