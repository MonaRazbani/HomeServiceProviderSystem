package config;

import dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public CustomerDao customerDao() {
        return CustomerDao.instance();
    }

    @Bean
    public ExpertDao expertDao() {
        return ExpertDao.instance();
    }

    @Bean
    public ServiceCategoryDao serviceCategoryDao() {
        return ServiceCategoryDao.instance();
    }

    @Bean
    public ServiceDao serviceDao() {
        return ServiceDao.instance();
    }

    @Bean
    public InstructionDao instructionDao() {
        return InstructionDao.instance();
    }

    @Bean
    public OfferDao offerDao() {
        return OfferDao.instance();
    }

    @Bean
    public AddressDao addressDao() {
        return AddressDao.instance();
    }

}
