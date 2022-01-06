package dao;

import models.entities.roles.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class CustomerDao extends HibernateUtil {

    private static CustomerDao customerDao;

    public static CustomerDao instance() {

        if (customerDao == null)
            customerDao = new CustomerDao();

        return customerDao;
    }

    public void save(Customer customer)  {
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

    public Customer getCustomerWithInstruction (Customer customer ){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer customer  WHERE customer.email =: email");
        query.setParameter("email" , customer.getEmail());
        Customer result = query.getSingleResult();
        customer.setInstructions(result.getInstructions());
        transaction.commit();
        session.close();
        return customer;
    }


}

