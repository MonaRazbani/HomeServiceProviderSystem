package ir.maktab.data.dao;

import ir.maktab.data.models.entities.SubService;
import ir.maktab.dto.filterDto.ProjectionSunServiceDto;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Registered
public interface SubServiceDao extends JpaRepository<SubService,Long> , JpaSpecificationExecutor<SubService> {
    Optional<SubService> findByName (String name);
    @Query("select s.name as name from SubService s")
    List<ProjectionSunServiceDto> findOnlyName();
}
