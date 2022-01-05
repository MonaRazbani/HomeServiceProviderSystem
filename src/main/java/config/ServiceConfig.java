package config;

import dao.CustomerDao;
import dao.ExpertDao;
import dao.ServiceCategoryDao;
import dao.ServiceDao;
import models.entities.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.CustomerService;
import services.ExpertService;
import services.ServiceCategoryService;
import services.ServiceService;
import validation.ControlInput;

@Configuration
@Import(value = {DaoConfig.class,ValidationConfig.class})

public class ServiceConfig {
    @Bean
    public CustomerService customerService (CustomerDao customerDao , ControlInput controlInput){
        CustomerService customerService = CustomerService.instance();
        customerService.setCustomerDao(customerDao);
        customerService.setControlInput(controlInput);
        return customerService;

    }
    @Bean
    public ExpertService expertService (ExpertDao expertDao , ControlInput controlInput ,ServiceDao serviceDao){
        ExpertService expertService = ExpertService.instance();
        expertService.setExpertDao(expertDao);
        expertService.setControlInput(controlInput);
        expertService.setServiceDao(serviceDao);
        return expertService;
    }
    @Bean
    public ServiceCategoryService serviceCategoryService (ServiceCategoryDao serviceCategoryDao , ControlInput controlInput){
        ServiceCategoryService serviceCategoryService = ServiceCategoryService.instance();
        serviceCategoryService.setServiceCategoryDao(serviceCategoryDao);
        serviceCategoryService.setControlInput(controlInput);
        return serviceCategoryService;
    }
    @Bean
    public ServiceService serviceService (ServiceDao serviceDao , ServiceCategoryDao serviceCategoryDao ){
        ServiceService serviceService = ServiceService.instance();
        serviceService.setServiceDao(serviceDao);
        serviceService.setServiceCategoryDao(serviceCategoryDao);
        return serviceService;
    }
}
