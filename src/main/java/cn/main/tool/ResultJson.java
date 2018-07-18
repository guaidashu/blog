package cn.main.tool;

public class ResultJson {
    private String text;
    private String id;
    private String reply;
    private String imageName[];

    public ResultJson()
    {
        this.text = null;
        this.id = null;
        this.reply = null;
        this.imageName = null;
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

    public String[] getImageName() {
        return imageName;
    }

    public void setImageName(String[] imageName) {
        this.imageName = imageName;
    }
}
