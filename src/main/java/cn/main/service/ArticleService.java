package cn.main.service;

import cn.main.tool.ResultJson;
import cn.main.tool.Tool;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by yy
 * Date: 2019-06-19
 * Description:
 */

@SuppressWarnings("Duplicates")
public class ArticleService {

    /**
     * 上传图片删除
     *
     * @param resultJson
     * @param imgPath
     * @param request
     * @return
     */
    public ResultJson deleteImage(ResultJson resultJson, String imgPath, HttpServletRequest request) {
        imgPath = Tool.base64Decode(imgPath);
        File file = new File(imgPath);
        String pattern = "([\\w\\W]*?)upload/([\\w\\W]*+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(imgPath);
        m.find();
        imgPath = m.group(2);
        File originFile = new File(request.getSession().getServletContext().getRealPath("/") + "upload/origin_" + imgPath);
        if (file.exists()) {
            boolean result = file.delete();
            try {
                boolean result2 = originFile.delete();
            } catch (Exception e) {

            }
            if (result) {
                resultJson.setText("ok");
            } else {
                resultJson.setText("删除失败");
            }
        } else {
            resultJson.setText("图片不存在");
        }
        return resultJson;
    }
}
