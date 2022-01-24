package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;

public class ServiceCategoryMapper {
    public static ServiceCategory toServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        return ServiceCategory.builder()
                .name(serviceCategoryDto.getName())
                .build();
    }
    public static ServiceCategoryDto toServiceCategoryDto(ServiceCategory serviceCategory) {
        ServiceCategoryDto serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName(serviceCategory.getName());
        return serviceCategoryDto;
    }
}
