package cn.main.dao;

import cn.main.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public int insert(Map<String, String> map) throws Exception;

    public int delete() throws Exception;

    int updatePassword(Map map);

    public User queryByPhone(String phone) throws Exception;

    public User queryById(String userid) throws Exception;

    public List<User> queryAll() throws Exception;
}
