package ir.maktab.dao;

import ir.maktab.models.entities.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends PagingAndSortingRepository <Admin ,Long>{

    Optional<Admin> findByUsername(String username);

}
