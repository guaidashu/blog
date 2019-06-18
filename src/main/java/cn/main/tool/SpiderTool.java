package cn.main.tool;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.*;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description:
 */
public class SpiderTool {
    /**
     * 获取网页信息
     * @param address
     * @return String
     */
    public static String getData(String address) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(address);
        method.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686)Gecko/20071127 Firefox/2.0.0.11");
        method.addRequestHeader("Referer", address);
        String data = "failed";
        try {
            client.executeMethod(method);
            InputStream responseBody = method.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody, "utf-8"));
            String line;
            // 抓取类
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
            data = stringBuffer.toString();
            responseBody.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return data;
    }
}
