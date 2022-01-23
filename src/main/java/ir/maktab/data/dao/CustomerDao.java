package ir.maktab.data.dao;

import ir.maktab.data.models.entities.roles.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail (String email);
    Optional<Customer> findByEmailAndPassword(String email, String password);

    }



