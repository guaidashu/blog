package cn.main.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class PersonSignDAOImpl implements PersonSignDao {
    @Override
    public String queryById(int id) {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        String result = sqlSession.selectOne("personSign.queryById", id);
        return result;
    }
}
