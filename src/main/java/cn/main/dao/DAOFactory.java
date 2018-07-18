package cn.main.dao;

public class DAOFactory {
    public static UserDao getUserDAOInstance() {
        return new UserDAOImpl();
    }

    public static ArticleDao getArticleInstance() {
        return new ArticleDAOImp();
    }

    public static TestDao getTestInstance() {
        return new TestDAOImpl();
    }

    public static ArticleTypeDao getArticleTypeInstance() {
        return new ArticleTypeDAOImpl();
    }

    public static PersonSignDao getPersonSignInstance() {
        return new PersonSignDAOImpl();
    }

}
