package config;

import dao.CustomerDao;
import dao.ExpertDao;
import dao.ServiceCategoryDao;
import dao.ServiceDao;
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
    @Bean
    public ServiceCategoryDao serviceCategoryDao (){
        return ServiceCategoryDao.instance();
    }
    @Bean
    public ServiceDao serviceDao (){
        return ServiceDao.instance();
    }

}
