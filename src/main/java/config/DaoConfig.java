package config;

import dao.CustomerDao;
import dao.ExpertDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public CustomerDao customerDao (){
        return CustomerDao.instance();
    }
    @Bean
    public ExpertDao expertDao(){
        return ExpertDao.instance();
    }

}
