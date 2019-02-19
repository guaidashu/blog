package cn.main.dao;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    int insert(Map<String, String> map) throws Exception;

    int update(Map<String, String> map) throws Exception;

    int deleteById(int id) throws Exception;

    Map queryById(int id) throws Exception;

    List<Map> queryAll(Map map) throws Exception;

    int queryCountAll() throws Exception;

    List<Map> queryByType(Map map) throws Exception;

    List<Map> queryByTitle(Map map) throws Exception;

    int queryCountType(Map map) throws Exception;

    int queryCountTitle(Map map) throws Exception;
}
