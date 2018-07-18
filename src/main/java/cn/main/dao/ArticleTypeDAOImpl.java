package cn.main.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ArticleTypeDAOImpl implements ArticleTypeDao {
    @Override
    public List<Map> queryAll() {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        List<Map> list = sqlSession.selectList("articleType.queryAll");
        return list;
    }

    @Override
    public int insert(Map map) {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.insert("articleType.insert", map);
        sqlSession.commit();
        return result;
    }

    @Override
    public int delete(int id) {
        SqlSession session = GetFactorySession.getSqlSession();
        int result = session.delete("articleType.delete", id);
        session.commit();
        return result;
    }
}
