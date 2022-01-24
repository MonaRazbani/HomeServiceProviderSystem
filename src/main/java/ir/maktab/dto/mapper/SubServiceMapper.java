package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.dto.modelDtos.SubServiceDto;

public class SubServiceMapper {

    public static SubServiceDto toSubServiceDto(SubService subService) {
        SubServiceDto subServiceDto = new SubServiceDto();

        subServiceDto.setName(subService.getName());
        subServiceDto.setExplanation(subServiceDto.getExplanation());
        subServiceDto.setServiceCategoryName(subServiceDto.getServiceCategoryName());
        subServiceDto.setBaseCost(String.valueOf(subService.getBaseCost()));

        return subServiceDto;
    }

    public static SubService toSubService(SubServiceDto subServiceDto) {
        SubService subService = new SubService();

        subService.setName(subServiceDto.getName());
        subService.setExplanation(subServiceDto.getExplanation());
        subService.setBaseCost(Double.parseDouble(subServiceDto.getBaseCost()));
        subService.setServiceCategory(ServiceCategory.
                builder().
                name(subServiceDto.getServiceCategoryName()).
                build());

        return subService;
    }

}
