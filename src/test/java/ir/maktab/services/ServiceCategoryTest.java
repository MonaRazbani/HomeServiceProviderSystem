package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.exceptions.DuplicateServiceCategory;
import ir.maktab.models.entities.ServiceCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServiceCategoryTest {
    ServiceCategoryService serviceCategoryService;
    ServiceCategoryDto serviceCategoryDto;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        serviceCategoryService = context.getBean(ServiceCategoryService.class);
        serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName("Cleaning");
    }

    @Test
    void serviceCategoryService_CallsSaveServiceCategory_ResponseTrue() {
        serviceCategoryService.saveServiceCategory(serviceCategoryDto);
        ServiceCategory serviceCategory = serviceCategoryService.findByName(serviceCategoryDto.getName());
        Assertions.assertEquals(serviceCategory.getName(), serviceCategoryDto.getName());
    }

    @Test
    void serviceCategoryService_CallsSaveServiceCategoryService_ReturnException() {
        //use bellow comment when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        /*serviceCategoryService.saveServiceCategory(serviceCategoryDto); */
        DuplicateServiceCategory result = Assertions.assertThrows(DuplicateServiceCategory.class, () ->
                serviceCategoryService.saveServiceCategory(serviceCategoryDto));
        Assertions.assertEquals("this serviceCategory already exists", result.getMessage());
    }
}
