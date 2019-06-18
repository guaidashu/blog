package cn.main.dao;

import cn.main.entity.GuestBook;

import java.util.List;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description: 留言抽象类
 */
public interface GuestBookDao {
    /**
     * 添加
     *
     * @param map
     * @return
     */
    int insert(Map<String, String> map);

    /**
     * 删除
     *
     * @param map
     * @return
     */
    int delete(Map<String, String> map);

    /**
     * 根据文章id获取对应的所有留言
     *
     * @param map
     * @return
     */
    List<GuestBook> queryAllByArticleId(Map<String, String> map);
}
