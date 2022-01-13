package ir.maktab.dao;

import ir.maktab.models.entities.Offer;
import ir.maktab.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferDao extends JpaRepository<Offer,Long> {

    List<Offer> findByOrder(Order order);
    List<Offer> findByOrderOrderBySuggestedPriceAsc(Order order);

}