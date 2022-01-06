package config;

import dao.*;
import models.entities.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.*;
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

    } @Bean
    public AddressService addressService (AddressDao addressDao ){
        AddressService addressService = new AddressService();
        addressService.setAddressDao(addressDao);
        return addressService;

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
    @Bean
    public InstructionService instructionService (ServiceDao serviceDao , ExpertDao expertDao , CustomerDao customerDao, OfferDao offerDao,ControlInput controlInput,AddressDao addressDao,InstructionDao instructionDao){
        InstructionService instructionService = InstructionService.instance();
        instructionService.setServiceDao(serviceDao);
        instructionService.setExpertDao(expertDao);
        instructionService.setCustomerDao(customerDao);
        instructionService.setOfferDao(offerDao);
        instructionService.setInstructionDao(instructionDao);
        instructionService.setControlInput(controlInput);
        instructionService.setAddressDao(addressDao);

        return instructionService;
    }
}
