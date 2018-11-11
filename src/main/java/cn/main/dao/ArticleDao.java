package cn.main.dao;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    int insert(Map<String, String> map) throws Exception;

    int update(Map<String, String> map) throws Exception;

    int deleteById(int id) throws Exception;

    Map queryById(int id) throws Exception;

    List<Map> queryAll() throws Exception;
}
