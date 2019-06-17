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


Next, for using database, you should add a xml file called "mybatis-config.xml" in src/main/java/cn/main/mapper

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <!-- 设置别名 -->
        <typeAliases>
            <package name="cn.main.entity"/>
        </typeAliases>
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.jdbc.Driver"/>
                    <property name="url"
                              value="jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false"/>
                    <property name="username" value=""/>
                    <property name="password" value=""/>
                </dataSource>
            </environment>
        </environments>
        <mappers>
            <mapper resource="cn/main/mapper/Article.xml"/>
            <mapper resource="cn/main/mapper/Test.xml"/>
            <mapper resource="cn/main/mapper/PersonSign.xml"/>
            <mapper resource="cn/main/mapper/ArticleType.xml"/>
            <mapper resource="cn/main/mapper/User.xml"/>
            <mapper resource="cn/main/mapper/Visit.xml"/>
        </mappers>
    </configuration>

For upload images in admin manager system, you should add a file called SecureConfig.java in src/main/java/cn/main/config/ and the content you should add is follow:

    /**
     * 获取七牛云的上传配置
     *
     * @return
     */
     public static Map getQiNiuYunConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", "");
        map.put("secretKey", "");
        map.put("bucket", "");
        return map;
     }