package cn.main.tool;

/**
 * Create by yy
 * Date: 2017-06-17
 * Description: 默认返回的json 数据格式
 */
public class ResultJson {
    private String text;
    private String id;
    private String reply;
    private String imageName;
    private int code;
    private String result;
    private String msg;

    public ResultJson() {
        this.text = null;
        this.id = null;
        this.reply = null;
        this.imageName = null;
        this.code = 0;
        this.result = null;
        this.msg = null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
