package ir.maktab.services;

import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;

import java.util.List;

public interface ServiceCategoryService {

    void saveServiceCategory(ServiceCategoryDto serviceCategoryDto);

    void updateServiceCategory(ServiceCategoryDto serviceCategoryDto);

    ServiceCategory findByName(String name);
    List<ServiceCategoryDto> findAll();

}
