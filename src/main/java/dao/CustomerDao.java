package dao;

import models.entities.roles.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import util.HibernateUtil;

import java.util.List;

public interface CustomerDao extends PagingAndSortingRepository<Customer,Long> {

    List<Customer> findByEmail (String email);

    /*public Customer getCustomerWithInstruction (Customer customer ){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer customer  WHERE customer.email =: email");
        query.setParameter("email" , customer.getEmail());
        Customer result = query.getSingleResult();
        customer.setOrders(result.getOrders());
        transaction.commit();
        session.close();
        return customer;*/
    }



