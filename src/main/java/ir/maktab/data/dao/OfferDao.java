package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferDao extends JpaRepository<Offer,Long> {
    Optional<Offer> findByIdentificationCode(UUID identificationCode);
    List<Offer> findByOrder(Order order);
    List<Offer> findByOrderOrderBySuggestedPriceAsc(Order order);
    List<Offer>findByExpert(Expert expert);
    Optional<Offer> findByOrderAndStatus(Order order, OfferStatus offerStatus);
}