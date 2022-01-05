package dao;

import models.entities.ServiceCategory;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;

public class ServiceCategoryDao extends HibernateUtil {

    private static ServiceCategoryDao serviceCategoryDao;

    public static ServiceCategoryDao instance() {
        if (serviceCategoryDao == null)
            serviceCategoryDao = new ServiceCategoryDao();
        return serviceCategoryDao;
    }
    public void save (ServiceCategory serviceCategory)throws ConstraintViolationException, PropertyValueException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(serviceCategory);
        transaction.commit();
        session.close();
    }
    public ServiceCategory findByName(String name )throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<ServiceCategory> query = session.createQuery("FROM ServiceCategory serviceCategory WHERE serviceCategory.name=: name ");
        query.setParameter("name",name);
        ServiceCategory result = query.getSingleResult();
        return result;
    }

}
