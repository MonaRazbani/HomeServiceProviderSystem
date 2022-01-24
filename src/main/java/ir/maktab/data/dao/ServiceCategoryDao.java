package ir.maktab.data.dao;

import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.data.models.entities.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServiceCategoryDao extends JpaRepository<ServiceCategory,Long>, JpaSpecificationExecutor<ServiceCategory> {

    Optional<ServiceCategory> findByName(String name);
}
