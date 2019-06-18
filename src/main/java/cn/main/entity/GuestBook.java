package cn.main.entity;

/**
 * Create by yy
 * Date: 2019-06-18
 * Description:
 */
public class GuestBook {
    private int id;
    private String content;
    private int blog_article_id;
    private int blog_user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBlog_article_id() {
        return blog_article_id;
    }

    public void setBlog_article_id(int blog_article_id) {
        this.blog_article_id = blog_article_id;
    }

    public int getBlog_user_id() {
        return blog_user_id;
    }

    public void setBlog_user_id(int blog_user_id) {
        this.blog_user_id = blog_user_id;
    }
}
