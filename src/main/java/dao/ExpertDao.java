package dao;

import models.entities.roles.Customer;
import models.entities.roles.Expert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;

public class ExpertDao extends HibernateUtil {
    public void save(Expert expert) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(expert);
        transaction.commit();
        session.close();
    }

    public Expert findByEmail(String email) throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Expert> query = session.createQuery("FROM Expert expert WHERE expert.email =: email ");
        query.setParameter("email",email);
        Expert result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result ;
    }

    public void update(Expert expert) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }
}
