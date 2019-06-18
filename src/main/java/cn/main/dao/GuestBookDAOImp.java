package cn.main.dao;

import cn.main.entity.GuestBook;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description:
 */
public class GuestBookDAOImp implements GuestBookDao {
    @Override
    public int insert(Map<String, String> map) {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        return sqlSession.insert("guestbook.insert", map);
    }

    @Override
    public int delete(Map<String, String> map) {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        return sqlSession.update("guestbook.delete", map);
    }

    @Override
    public List<GuestBook> queryAllByArticleId(Map<String, String> map) {
        return null;
    }
}
