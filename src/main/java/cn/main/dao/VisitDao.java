package cn.main.dao;

import java.util.List;
import java.util.Map;

public interface VisitDao {
    int update(Map<String, String> map) throws Exception;

    List<Map> queryOne() throws Exception;
}
