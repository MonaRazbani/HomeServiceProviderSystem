package dao;

import models.entities.Service;
import models.entities.ServiceCategory;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;

public class ServiceDao extends HibernateUtil {
    private static ServiceDao serviceDao;

    public static ServiceDao instance() {
        if (serviceDao == null)
            serviceDao = new ServiceDao();
        return serviceDao;
    }
    public void save (Service service)throws ConstraintViolationException, PropertyValueException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(service);
        transaction.commit();
        session.close();
    }
    public Service findByName(String name )throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Service> query = session.createQuery("FROM Service service WHERE service.name=: name ");
        query.setParameter("name",name);
        Service result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result;
    }


}
