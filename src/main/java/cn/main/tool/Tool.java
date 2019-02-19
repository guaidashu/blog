package cn.main.tool;
import java.util.Base64;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Tool {
    /**
     * 将对应的时间戳转化为Date对象 ，返回List集合，其他数据不变
     *
     * @param list    要处理的数据集合
     * @param columns 要处理的字段名
     * @return
     */
    public static List<Map> getDateObject(List<Map> list, String columns) {
        int i = 0;
        for (Map map : list) {
            list.get(i++).put(columns, new Date(Long.parseLong((String) map.get(columns))));
        }
        return list;
    }

    /**
     * 获取时间戳，返回时间戳
     */
    public static String getTimeStamp() {
        return Calendar.getInstance().getTimeInMillis() + "";
    }

    /**
     * base64解密程序
     * content为字符串需要解密的数据
     * 返回解密之后的字符串
     */
    public static String base64Decode(String content) {
        String result = "";
        try {
            result = new String(Base64.getDecoder().decode(content), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取关联数据(一般是id关联)
     * list 为需要处理的数据集
     * aboutList是关联数据集
     * columns 为要处理的字段的主键
     * handleColumns 为要处理的字段
     * aboutColumns 为关联的字段的主键
     * keyColumns 为关联的主键
     */
    public static List<Map> getAboutData(List<Map> list, List<Map> aboutList, String columns, String handleColumns, String aboutColumns, String keyColunms) {
        int i = 0;
        for (Map map : list) {
            for (Map map2 : aboutList) {
                if ((map.get(columns) + "").equals(map2.get(aboutColumns) + "")) {
                    list.get(i++).put(handleColumns, map2.get(keyColunms));
                    break;
                }
            }
        }
        return list;
    }

    /**
     * @param map
     * @param aboutList
     * @param columns
     * @param handleColumns
     * @param aboutColumns
     * @param keyColunms
     * @return
     */
    public static Map getAboutDataSingle(Map map, List<Map> aboutList, String columns, String handleColumns, String aboutColumns, String keyColunms) {
        for (Map map2 : aboutList) {
            if ((map.get(columns) + "").equals(map2.get(aboutColumns) + "")) {
                map.put(handleColumns, map2.get(keyColunms));
                break;
            }
        }
        return map;
    }

}
