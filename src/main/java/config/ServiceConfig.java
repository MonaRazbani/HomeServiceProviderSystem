package config;

import dao.CustomerDao;
import dao.ExpertDao;
import dao.ServiceCategoryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.CustomerService;
import services.ExpertService;
import services.ServiceCategoryService;
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
    public ExpertService expertService (ExpertDao expertDao , ControlInput controlInput){
        ExpertService expertService = ExpertService.instance();
        expertService.setExpertDao(expertDao);
        expertService.setControlInput(controlInput);
        return expertService;
    }
    @Bean
    public ServiceCategoryService serviceCategoryService (ServiceCategoryDao serviceCategoryDao , ControlInput controlInput){
        ServiceCategoryService serviceCategoryService = ServiceCategoryService.instance();
        serviceCategoryService.setServiceCategoryDao(serviceCategoryDao);
        serviceCategoryService.setControlInput(controlInput);
        return serviceCategoryService;
    }
}
