package ir.maktab.data.dao;

import jdk.jfr.Registered;
import ir.maktab.data.models.entities.SubService;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@Registered
public interface SubServiceDao extends PagingAndSortingRepository<SubService,Long> {
    Optional<SubService> findByName (String name);



}
