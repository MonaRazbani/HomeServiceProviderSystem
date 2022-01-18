package ir.maktab.dao;
import ir.maktab.models.entities.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressDao extends PagingAndSortingRepository<Address,Long> {
    Optional<Address> findByIdentificationCode(UUID identificationCode);
}
