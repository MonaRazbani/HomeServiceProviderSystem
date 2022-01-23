package ir.maktab.data.dao;
import ir.maktab.data.models.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressDao extends CrudRepository<Address,Long> {
    Optional<Address> findByIdentificationCode(UUID identificationCode);
}
