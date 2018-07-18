package cn.main.dao;

import java.util.List;
import java.util.Map;

/**
 * 文章类型接口类
 *
 */

public interface ArticleTypeDao {
    List<Map> queryAll();
    int insert(Map map);
    int delete(int id);
}
