package dao;
import models.entities.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

@Repository
public interface AddressDao extends PagingAndSortingRepository<Address,Long> {

}
