package dao;

import models.entities.Comment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import services.CommentService;
import util.HibernateUtil;

public class CommentDao extends HibernateUtil {
    private static CommentDao commentDao;

    public static CommentDao instance() {

        if (commentDao == null)
            commentDao = new CommentDao();
        return commentDao;
    }

    public void save(Comment comment1) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(comment1);
        transaction.commit();
        session.close();
    }

}
