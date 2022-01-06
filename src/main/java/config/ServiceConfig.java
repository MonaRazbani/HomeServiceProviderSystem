package config;

import dao.*;
import models.entities.Offer;
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
    public InstructionService instructionService (ServiceDao serviceDao ,AddressDao addressDao,InstructionDao instructionDao,ControlInput controlInput){
        InstructionService instructionService = InstructionService.instance();
        instructionService.setServiceDao(serviceDao);
        instructionService.setInstructionDao(instructionDao);
        instructionService.setControlInput(controlInput);
        instructionService.setAddressDao(addressDao);
        return instructionService;
    }
    @Bean
    public OfferService offerService ( InstructionDao instructionDao, OfferDao offerDao){
        OfferService offerService = OfferService.instance();
        offerService.setInstructionDao(instructionDao);
        offerService.setOfferDao(offerDao);
        return offerService;
    }
    @Bean
    public CommentService commentService ( InstructionDao instructionDao, CommentDao commentDao ,ControlInput controlInput ){
        CommentService commentService = CommentService.instance();
        commentService.setInstructionDao(instructionDao);
        commentService.setCommentDao(commentDao);
        commentService.setControlInput(controlInput);
        return commentService;
    }
      @Bean
    public AdminService adminService( AdminDao adminDao,  UserDao userDao ,ControlInput controlInput ){

        AdminService adminService = AdminService.instance();
        adminService.setAdminDao(adminDao);
        adminService.setUserDao(userDao);
        adminService.setControlInput(controlInput);
        return adminService;
    }

}
