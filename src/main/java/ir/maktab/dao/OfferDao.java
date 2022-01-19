package ir.maktab.dao;

import ir.maktab.models.entities.Offer;
import ir.maktab.models.entities.Order;
import org.springframework.data.domain.Sort;
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