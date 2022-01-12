/*
package ir.maktab.ir.maktab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.ir.maktab.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.ir.maktab.util.Properties;
@EnableJpaRepositories(basePackages = "ir.maktab.data.ir.maktab.dao" )//enable spring data Jpa & path ir.maktab.dao package
@PropertySource("classpath:database.properties")
@Configuration
@EnableTransactionManagement // for managing transactions by ioc container
public class DatabaseConfig  {
    @Autowired
    private Environment environment ;
    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));

        return dataSource;
    }
    @Bean
            //
    //EntityManagerFactoryBean   این خودش میره این پروپرتی ها رو تو خودت اینتیتی منیجر فکتوری که تو ای پی آی که تو جی پی ای هست ست میکنه
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());// اینجا باید بگیم از کدوم جی پی ای پروایدر استفاده میکنه
        entityManagerFactoryBean.setPackagesToScan("ir.maktab.data.model");//which package we want mapping entity

        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }
    private Properties hibernateProperties(){
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        //jpaProperties.put("hibernate.ejb.naming_strategy", environment.getRequiredProperty("hibernate.ejb.naming_strategy"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        //jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return jpaProperties;
    }


    @Configuration
    //for managing transaction we cant commit it ourselves, so we manage it by this class
    class PersistenceContext {
        @Bean
        JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory);
            return transactionManager;
        }
    }

}
*/
package ir.maktab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "ir.maktab.dao")
@PropertySource("classpath:database.properties")
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ir.maktab.models");

        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibernateProperties(){
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        //jpaProperties.put("hibernate.ejb.naming_strategy", environment.getRequiredProperty("hibernate.ejb.naming_strategy"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        //jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.connection.pool.size",environment.getRequiredProperty("hibernate.connection.pool.size"));
        return jpaProperties;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
