package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Admin;
import ir.maktab.data.models.entities.roles.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends PagingAndSortingRepository <Admin ,Long>{

    Optional<Admin> findByUsername(String username);

    Optional<Customer> findByUsernameAndPassword(String username, String password);
}
