package cn.main.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class VisitDAOImp implements VisitDao {

    @Override
    public int update(Map<String, String> map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.update("visit.update", map);
        sqlSession.commit();
        return result;
    }

    @Override
    public List<Map> queryOne() throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        List<Map> list = sqlSession.selectList("visit.queryOne");
        return list;
    }
}
