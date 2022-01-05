package dao;

import models.entities.roles.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class CustomerDao extends HibernateUtil {
    public void save(Customer customer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
        session.close();
    }
}

