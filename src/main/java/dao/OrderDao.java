package dao;

import models.entities.Order;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.enums.OrderStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

public interface OrderDao extends PagingAndSortingRepository<Order,Long> {


    List<Order> findByCustomerAndStatus (Customer customer, OrderStatus status) ;

    List<Order> findByExpertAndStatus(Expert expert, OrderStatus status) ;

}
