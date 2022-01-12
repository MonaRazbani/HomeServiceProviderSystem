package ir.maktab.dao;

import jdk.jfr.Registered;
import ir.maktab.models.entities.SubService;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@Registered
public interface SubServiceDao extends PagingAndSortingRepository<SubService,Long> {
    Optional<SubService> findByName (String name);



}
