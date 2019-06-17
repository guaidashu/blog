package cn.main.tool;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * Create by yy
 * Date: 2019-06-17
 * Description:
 */
public class QiNiuYun {
    private String accessKey;
    private String secretKey;
    private String bucket;

    public QiNiuYun() {
        accessKey = "Dcd9yIrVG2t7NwbTZPCBKq3TWEg2-ZBfQ4aC-ze4";
        secretKey = "VElmDBf0W_z68UEeuMa0iSbN3XziQFk_s--pNC0b";
        bucket = "blog_image";
    }

    public QiNiuYun(String accessKey, String secretKey, String bucket) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * 获取上传凭证
     * @return
     */
    private String getToken() {
        Auth auth = Auth.create(this.accessKey, this.secretKey);
        return auth.uploadToken(this.bucket);
    }

    /**
     * 上传
     * @param localFileName
     * @param uploadFileName
     * @return
     */
    public DefaultPutRet uploadFile(String localFileName, String uploadFileName) {
        // 构造用于上传的Configuration 对象
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        // 获取上传凭证
        String token = this.getToken();
        DefaultPutRet putRet = null;
        try {
            // 开始上传
            Response response = uploadManager.put(localFileName, uploadFileName, token);
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return putRet;
    }
}
