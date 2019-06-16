package cn.main.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class TestDAOImpl implements TestDao {
    @Override
    public int insertTest(Map map) {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result;
        result = sqlSession.insert("test.insertTest", map);
        sqlSession.commit();
        return result;
    }
}
