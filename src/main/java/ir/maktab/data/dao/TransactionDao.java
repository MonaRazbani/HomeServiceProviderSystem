package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionDao extends CrudRepository<Transaction,Long> {
    Optional<Transaction> findByIdentificationCode(UUID identificationCode );

}
