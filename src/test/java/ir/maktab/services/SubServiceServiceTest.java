package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.exceptions.DuplicateSubService;
import ir.maktab.models.entities.SubService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SubServiceServiceTest {
    SubServiceService subServiceService;
    ServiceCategoryDto serviceCategoryDto;
    SubServiceDto subServiceDto;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        subServiceService = context.getBean(SubServiceService.class);
        serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName("Cleaning");
        serviceCategoryDto.setId(4L);//Based on database

        subServiceDto = new SubServiceDto();
        subServiceDto.setName("house cleaning");
        subServiceDto.setExplanation("cleaning the house");
        subServiceDto.setServiceCategory(serviceCategoryDto);
    }

    @Test
    void serviceCategoryService_CallsSaveServiceCategory_ResponseTrue() {
        subServiceService.saveSubService(subServiceDto);
        SubService subService = subServiceService.findByName(subServiceDto.getName());
        Assertions.assertEquals(subService.getName(), subServiceDto.getName());
    }

    @Test
    void serviceCategoryService_CallsSaveServiceCategoryService_ReturnException() {
        //use bellow comment when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        /*SubService subService = subServiceService.findByName(subServiceDto.getName()); */
        DuplicateSubService result = Assertions.assertThrows(DuplicateSubService.class, () ->
                subServiceService.saveSubService(subServiceDto));
        Assertions.assertEquals("this subService already exists", result.getMessage());
    }
}
