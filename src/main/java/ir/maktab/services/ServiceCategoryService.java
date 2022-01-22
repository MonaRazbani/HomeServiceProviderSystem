package ir.maktab.services;

import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.exceptions.DuplicateServiceCategory;
import ir.maktab.exceptions.ServiceCategoryNotFound;

import java.util.Optional;

public interface ServiceCategoryService {

    void saveServiceCategory(ServiceCategoryDto serviceCategoryDto);

    void updateServiceCategory(ServiceCategoryDto serviceCategoryDto);

    ServiceCategory findByName(String name);

}
