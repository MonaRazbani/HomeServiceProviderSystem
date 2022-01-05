package config;

import dao.CustomerDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import services.CustomerService;
import validation.ControlInput;

@Configuration
@Import(value = {DaoConfig.class,ValidationConfig.class})

public class ServiceConfig {
    @Bean
    public CustomerService customerService (CustomerDao customerDao , ControlInput controlInput){
        CustomerService customerService = new CustomerService();
        customerService.setCustomerDao(customerDao);
        customerService.setControlInput(controlInput);
        return customerService;

    }
}
