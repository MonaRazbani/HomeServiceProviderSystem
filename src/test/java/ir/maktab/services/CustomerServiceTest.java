package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.*;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.models.enums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerServiceTest {
    CustomerService customerService;
    CustomerDto customerDto;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerService = context.getBean(CustomerService.class);
        customerDto = CustomerDto.builder().
                firstName("mona").
                lastName("raz").
                email("mona@gmail.com").
                roleType(RoleType.CUSTOMER).
                build();

    }

    @Test
    void customerService_CallsSaveCustomer_CheckWithFindByEmail_ResponseTrue() {
        String password = "123456mona";
        customerService.saveCustomer(customerDto, password);
        CustomerDto customerDtoByEmail = customerService.findCustomerDtoByEmail(customerDto.getEmail());
        Assertions.assertEquals(customerDto.getEmail(), customerDtoByEmail.getEmail());
        Assertions.assertEquals(customerDto.getFirstName(), customerDtoByEmail.getFirstName());
        Assertions.assertEquals(customerDto.getLastName(), customerDtoByEmail.getLastName());
        Assertions.assertEquals(customerDto.getRoleType(), customerDtoByEmail.getRoleType());
    }

    @Test
    void customerService_CallsSaveCustomer_ReturnException() {
        String password = "123456mona";
        customerService.saveCustomer(customerDto, password);
        DuplicateEmail result = Assertions.assertThrows(DuplicateEmail.class, () -> customerService.saveCustomer(customerDto, password));
        Assertions.assertEquals("This email has been used before", result.getMessage());
    }
    @Test
    void customerService_CallsFindCustomerByEmail_ResponseTrue(){
//        String password = "123456mona";//use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
//        customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        Customer customerByEmail = customerService.findCustomerByEmail(customerDto.getEmail());
        Assertions.assertEquals(customerByEmail.getEmail(), customerByEmail.getEmail());
        Assertions.assertEquals(customerByEmail.getFirstName(), customerByEmail.getFirstName());
        Assertions.assertEquals(customerByEmail.getLastName(), customerByEmail.getLastName());
        Assertions.assertEquals(customerByEmail.getRoleType(), customerByEmail.getRoleType());
    }
    @Test
    void customerService_CallsFindCustomerDtoByEmail_UseInvalidEmail_ReturnException() {
        // String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        InvalidEmail result = Assertions.assertThrows(InvalidEmail.class, () -> customerService.findCustomerDtoByEmail("mana@gmail"));
        Assertions.assertEquals("invalid email", result.getMessage());
    }
    @Test
    void customerService_CallsFindCustomerDtoByEmail_UseUnSaveEmail_ReturnException() {
        //String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        CustomerNotFound result = Assertions.assertThrows(CustomerNotFound.class, () -> customerService.findCustomerDtoByEmail("ali@gmail.com"));
        Assertions.assertEquals("customer not found", result.getMessage());
    }

 @Test
    void customerService_CallsFindCustomerDtoByEmail_ResponseTrue(){
        String password = "123456mona";
        customerService.saveCustomer(customerDto, password);
        CustomerDto customerDtoByEmail = customerService.findCustomerDtoByEmail(customerDto.getEmail());
        Assertions.assertEquals(customerDtoByEmail.getEmail(), customerDtoByEmail.getEmail());
        Assertions.assertEquals(customerDtoByEmail.getFirstName(), customerDtoByEmail.getFirstName());
        Assertions.assertEquals(customerDtoByEmail.getLastName(), customerDtoByEmail.getLastName());
        Assertions.assertEquals(customerDtoByEmail.getRoleType(), customerDtoByEmail.getRoleType());
    }
    @Test
    void customerService_CallsFindCustomerByEmail_UseInvalidEmail_ReturnException() {
        // String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        InvalidEmail result = Assertions.assertThrows(InvalidEmail.class, () -> customerService.findCustomerByEmail("mana@gmail"));
        Assertions.assertEquals("invalid email", result.getMessage());
    }
    @Test
    void customerService_CallsFindCustomerByEmail_UseUnSaveEmail_ReturnException() {
        //String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        CustomerNotFound result = Assertions.assertThrows(CustomerNotFound.class, () -> customerService.findCustomerByEmail("ali@gmail.com"));
        Assertions.assertEquals("customer not found", result.getMessage());
    }
    @Test
    void customerService_CallsChangePasswordForCustomer_ResponseTrue(){
        //String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        Customer customerByEmail = customerService.findCustomerByEmail(customerDto.getEmail());
        String newPassword = "mona76543218";
        customerService.changePasswordForCustomer(customerDto, customerByEmail.getPassword(), newPassword);
        Customer customerWhichWasChangedPassword = customerService.findCustomerByEmail(customerDto.getEmail());
        Assertions.assertEquals(customerWhichWasChangedPassword.getPassword(),newPassword);

    }
    @Test
    void customerService_CallsChangePasswordForCustomer_UseWrongPassword_ReturnException(){
        //String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        Customer customerByEmail = customerService.findCustomerByEmail(customerDto.getEmail());
        String newPassword = "mona76543218";
        WrongPassword result = Assertions.assertThrows(WrongPassword.class, () ->
                customerService.changePasswordForCustomer(customerDto,"123456789asdgh",newPassword));
        Assertions.assertEquals("wrong Password", result.getMessage());
    }
 @Test
    void customerService_CallsChangePasswordForCustomer_i_ReturnException(){
        //String password = "123456mona";
        //customerService.saveCustomer(customerDto, password); //use it when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        Customer customerByEmail = customerService.findCustomerByEmail(customerDto.getEmail());
        String newPassword = "1234";
        InvalidPassword result = Assertions.assertThrows(InvalidPassword.class, () ->
                customerService.changePasswordForCustomer(customerDto,customerByEmail.getPassword(),newPassword));
        Assertions.assertEquals("password must contains words and numbers and password length's  must more than 8 ", result.getMessage());
    }

}

