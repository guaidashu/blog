package cn.main.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by yy
 * Date: 2019-06-17
 * Description:
 */
public class SecureConfig {
    /**
     * 获取七牛云的上传配置
     *
     * @return
     */
    public static Map getQiNiuYunConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("accessKey", "");
        map.put("secretKey", "");
        map.put("bucket", "");
        return map;
    }
}
