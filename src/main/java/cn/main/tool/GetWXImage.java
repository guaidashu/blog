package cn.main.tool;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("Duplicates")
public class GetWXImage {
    // 要抓取的地址
     private String address;
    // 抓取类
    private HttpURLConnection conn = null;
    private URL url = null;
    private InputStream in = null;
    private BufferedReader reader = null;
    private StringBuffer stringBuffer = null;

    public GetWXImage()
    { }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getDataHttpURLConnection()
    {
        try{
            // 构造连接
            url = new URL(address);
            conn = (HttpURLConnection)url.openConnection();
            // 设置连接超时时间
            conn.setConnectTimeout(100000);
            // 设置读取内容超时时间
            conn.setReadTimeout(100000);
            // 设置头
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686)Gecko/20071127 Firefox/2.0.0.11");
            // 设置是否可以使用  conn.getInputStream().read();
            conn.setDoInput(true);
            // 连接
            conn.connect();
            in = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            stringBuffer = new StringBuffer();
            String line = null;
            while((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接
            conn.disconnect();
            try{
                in.close();
                reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return stringBuffer.toString();
    }

    public String getData()
    {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(address);
        method.addRequestHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux i686)Gecko/20071127 Firefox/2.0.0.11");
        method.addRequestHeader("Referer", address);
        String data = "failed";
        try {
            client.executeMethod(method);
            InputStream responseBody = method.getResponseBodyAsStream();
            // 保存成图片文件
            String path = "test.jpg";
            byte[] bs = new byte[1024];
            int len;
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            OutputStream os = new FileOutputStream(file);
            while((len = responseBody.read(bs)) != -1){
                os.write(bs, 0, len);
            }
            os.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody, "utf-8"));
            String line = null;
            stringBuffer = new StringBuffer();
            while((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
            data = stringBuffer.toString();
            responseBody.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            method.releaseConnection();
        }
        return data;
    }

//    public static void main(String args[])
//    {
//        GetWXImage getWXImage = new GetWXImage();
//        String url = "https://mp.weixin.qq.com/s?__biz=MzU4MjAyOTA3MA==&mid=2247486246&idx=1&sn=e2bdc216c8dc84151e2605959d8d4134&chksm=fdbfd147cac8585191ffae9aaffc708cf58c78892f4ed136bc361f39ea765c9eedeb39a2645d&scene=27#wechat_redirect";
//        getWXImage.setAddress(url);
//        String data = getWXImage.getData();
//        System.out.println(data);
//    }
}
