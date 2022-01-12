package ir.maktab.dao;
import ir.maktab.models.entities.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressDao extends PagingAndSortingRepository<Address,Long> {
}
