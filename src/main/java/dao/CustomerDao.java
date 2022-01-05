package dao;

import models.entities.roles.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import validation.ControlInput;

import javax.persistence.NoResultException;

public class CustomerDao extends HibernateUtil {

    private static CustomerDao customerDao;

    public static CustomerDao instance() {

        if (customerDao == null)
            customerDao = new CustomerDao();

        return customerDao;
    }

    public void save(Customer customer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(customer);
        transaction.commit();
        session.close();
    }

    public Customer findByEmail(String email) throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer customer WHERE customer.email =: email ");
        query.setParameter("email",email);
        Customer result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result ;
    }

    public void update(Customer customer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }
}

