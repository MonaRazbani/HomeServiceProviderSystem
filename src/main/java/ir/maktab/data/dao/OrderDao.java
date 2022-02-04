package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OrderStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDao extends PagingAndSortingRepository<Order,Long> {

    List<Order> findByCustomer (Customer customer) ;

    List<Order> findByExpertAndStatus(Expert expert, OrderStatus status) ;

    Optional<Order> findById(long id);

    Optional<Order> findByIdentificationCode(UUID identificationCode);

    List<Order> findByStatusAndSubService (OrderStatus status, SubService subService);

}
