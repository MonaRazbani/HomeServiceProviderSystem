package ir.maktab.services;

import ir.maktab.data.models.entities.SubService;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;

import java.util.List;

public interface SubServiceService {

    void saveSubService(SubServiceDto subServiceDto) ;

    void updateSubService(SubServiceDto subServiceDto);

    SubService findByName(String name);

    List<SubServiceDto> findAll();

    List<SubServiceDto> findSubServicesOfServiceCategory(String serviceCategoryName);

    List<SubService> findSubServiceByExpert (ExpertDto expertDto);

}
