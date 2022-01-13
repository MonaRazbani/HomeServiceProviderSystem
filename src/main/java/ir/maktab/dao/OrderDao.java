package ir.maktab.dao;

import ir.maktab.models.entities.Order;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.models.enums.OrderStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDao extends PagingAndSortingRepository<Order,Long> {

    List<Order> findByCustomerAndStatus (Customer customer, OrderStatus status) ;

    List<Order> findByExpertAndStatus(Expert expert, OrderStatus status) ;

    Optional<Order> findById(long id);

}
