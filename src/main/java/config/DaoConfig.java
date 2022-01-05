package config;

import dao.CustomerDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public CustomerDao customerDao (){
        return new CustomerDao();
    }

}
