package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.InvalidSuggestedPrice;
import ir.maktab.models.entities.SubService;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.models.enums.Gender;
import ir.maktab.models.enums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class CustomerServiceTest {
    CustomerService customerService ;
    CustomerDto customerDto ;
    @BeforeEach
    void init (){
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
         customerService.saveCustomer(customerDto,password);
        CustomerDto customerDtoByEmail = customerService.findCustomerDtoByEmail(customerDto.getEmail());
        Assertions.assertEquals(customerDto.getEmail(), customerDtoByEmail.getEmail());
        Assertions.assertEquals(customerDto.getFirstName(),customerDtoByEmail.getFirstName());
        Assertions.assertEquals(customerDto.getLastName(),customerDtoByEmail.getLastName());
        Assertions.assertEquals(customerDto.getRoleType(),customerDtoByEmail.getRoleType());
    }
@Test
    void customerService_CallsSaveCustomer_ReturnException(){
    String password = "123456mona";
    customerService.saveCustomer(customerDto,password);
    DuplicateEmail result = Assertions.assertThrows(DuplicateEmail.class,()->customerService.saveCustomer(customerDto,password));
    Assertions.assertEquals("This email has been used before",result.getMessage());


}
       /* SubService subService = new SubService();
        subService.setBaseCost(1000);
        InvalidSuggestedPrice result = Assertions.assertThrows(InvalidSuggestedPrice.class,()-> controlInput.isValidSuggestedPrice(subService,900));
        Assertions.assertEquals("invalid SuggestedPrice , SuggestedPrice must be more than subService base cost ",result.getMessage());
    }

    void controlInput_CallIsValidPhoto_ResponseTrue() {
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\1.jpg");
        boolean result = controlInput.isValidPhoto(file);
        Assertions.assertTrue(result);
    }*/

}
