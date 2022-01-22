package ir.maktab.data.dao;

import ir.maktab.data.models.entities.ServiceCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServiceCategoryDao extends PagingAndSortingRepository<ServiceCategory,Long> {

    Optional<ServiceCategory> findByName(String name);
}
