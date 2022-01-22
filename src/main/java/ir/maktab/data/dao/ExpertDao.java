package ir.maktab.data.dao;

import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.entities.roles.Expert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExpertDao extends PagingAndSortingRepository<Expert,Long> {
   Optional<Expert> findByEmail(String email);
Optional<Expert> findByEmailAndPassword(String email, String password);
}
