package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AddressServiceTest {
    AddressService addressService ;
    @BeforeEach
    void init (){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        addressService= context.getBean(AddressService.class);
    }
}
