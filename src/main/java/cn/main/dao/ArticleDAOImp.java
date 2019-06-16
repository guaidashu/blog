package cn.main.dao;

import org.apache.ibatis.session.SqlSession;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticleDAOImp implements ArticleDao {
    @Override
    public int insert(Map<String, String> map) throws Exception {
        DBConnection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        String sql = "insert into blog_article(title,article_type,author,show_img,content,upload_time,article_describe,origin_img)values(?,?,?,?,?,?,?,?)";
        try {
            conn = new DBConnection();
            pstm = conn.getConn().prepareStatement(sql);
            pstm.setString(1, map.get("title"));
            pstm.setInt(2, Integer.parseInt(map.get("type")));
            pstm.setString(3, map.get("author"));
            pstm.setString(4, map.get("show_img"));
            pstm.setString(5, map.get("content"));
            pstm.setString(6, map.get("upload_time"));
            pstm.setString(7, map.get("describe"));
            pstm.setString(8, map.get("origin_img"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        try {
            result = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

        if (conn != null) {
            conn.close();
        }
        return result;
    }

    @Override
    public int update(Map<String, String> map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.update("article.updateById", map);
        sqlSession.commit();
        return result;
    }

    @Override
    public int deleteById(int id) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        Map<String, String> map = new HashMap<>();
        map.put("id", id + "");
        int result = sqlSession.delete("article.deleteById", map);
        sqlSession.commit();
        return result;
    }

    @Override
    public Map queryById(int id) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        Map article = null;
        article = sqlSession.selectOne("article.queryById", id);
        return article;
    }

    @Override
    public List<Map> queryAll(Map map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        List<Map> articleList = null;
        articleList = sqlSession.selectList("article.queryAll", map);
        return articleList;
    }

    @Override
    public int queryCountAll() throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.selectOne("article.queryCountAll");
        return result;
    }

    @Override
    public List<Map> queryByType(Map map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        List<Map> articleList = null;
        articleList = sqlSession.selectList("article.queryByType", map);
        return articleList;
    }

    @Override
    public List<Map> queryByTitle(Map map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        List<Map> articleList = null;
        articleList = sqlSession.selectList("article.queryByTitle", map);
        return articleList;
    }

    @Override
    public int queryCountType(Map map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.selectOne("article.queryCountType", map);
        return result;
    }

    @Override
    public int queryCountTitle(Map map) throws Exception {
        SqlSession sqlSession = GetFactorySession.getSqlSession();
        int result = sqlSession.selectOne("article.queryCountTitle", map);
        return result;
    }
}


































