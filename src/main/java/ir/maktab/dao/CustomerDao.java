package ir.maktab.dao;

import ir.maktab.models.entities.roles.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerDao extends PagingAndSortingRepository<Customer,Long> {
    Optional<Customer> findByEmail (String email);
    Optional<Customer> findByIdentificationCode(UUID identificationCode);

    }



