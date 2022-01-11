package dao;

import models.entities.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends PagingAndSortingRepository {

    Optional<Admin> findByUsername(String username);

}
