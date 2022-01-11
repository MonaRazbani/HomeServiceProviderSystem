package dao;

import models.entities.roles.Expert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpertDao extends PagingAndSortingRepository<Expert,Long> {
   Optional<Expert> findByEmail(String email);
}
