package cn.main.entity;

/**
 * 文章实体类
 */

public class Article {
    private String title;
    private String author;
    private String upload_time;
    private String article_type;
    private String show_img;
    private String content;
    private String article_describe;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getShow_img() {
        return show_img;
    }

    public void setShow_img(String show_img) {
        this.show_img = show_img;
    }

    public String getArticle_type() {
        return article_type;
    }

    public void setArticle_type(String article_type) {
        this.article_type = article_type;
    }

    public String getArticle_describe() {
        return article_describe;
    }

    public void setArticle_describe(String article_describe) {
        this.article_describe = article_describe;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }
}
