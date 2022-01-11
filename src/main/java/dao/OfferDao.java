package dao;

import models.entities.Offer;
import models.entities.Order;
import models.enums.OrderStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OfferDao extends PagingAndSortingRepository<Offer,Long> {

    List<Offer> findByOrder(Order order);
}