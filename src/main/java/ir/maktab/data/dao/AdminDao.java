package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Admin;
import ir.maktab.data.models.entities.roles.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends CrudRepository<Admin ,Long>  {

    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByUsernameAndPassword(String username, String password);
}
