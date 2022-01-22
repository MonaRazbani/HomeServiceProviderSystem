package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferDao extends PagingAndSortingRepository<Offer,Long> {
    Optional<Offer> findByIdentificationCode(UUID identificationCode);
    List<Offer> findByOrder(Order order);
    List<Offer> findByOrderOrderBySuggestedPriceAsc(Order order);

}