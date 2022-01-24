package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;

public class ServiceCategoryMapper {
    public static ServiceCategory toServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        return ServiceCategory.builder()
                .name(serviceCategoryDto.getName())
                .build();
    }
    public static ServiceCategory toServiceCategoryDto(ServiceCategory serviceCategory) {
        return ServiceCategory.builder()
                .name(serviceCategory.getName())
                .build();
    }
}
