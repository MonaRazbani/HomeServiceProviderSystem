package util;

import models.entities.*;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.entities.roles.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    static SessionFactory sessionFactory ;

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                Configuration configuration = new Configuration();

                try {
                    Properties properties = new Properties();
                    properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                    properties.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/home_service_provider_system");
                    properties.put("hibernate.connection.username", "mona_razbani");
                    properties.put("hibernate.connection.password", "Pendi_Pengi142131");
                    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                    properties.put("hibernate.show_sql", "true");
                    properties.put("hibernate.hbm2ddl.auto", "update");
                    configuration.setProperties(properties);
                    configuration.addAnnotatedClass(User.class);
                    configuration.addAnnotatedClass(Customer.class);
                    configuration.addAnnotatedClass(Expert.class);
                    configuration.addAnnotatedClass(Instruction.class);
                    configuration.addAnnotatedClass(Offer.class);
                    configuration.addAnnotatedClass(ServiceCategory.class);
                    configuration.addAnnotatedClass(Service.class);
                    configuration.addAnnotatedClass(Address.class);
                    ServiceRegistry serviceRegistry = (new StandardServiceRegistryBuilder()).applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

            return sessionFactory;
        }
    }


