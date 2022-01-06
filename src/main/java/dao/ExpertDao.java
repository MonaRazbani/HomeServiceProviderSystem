package dao;

import models.entities.Service;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

public class ExpertDao extends HibernateUtil {

    private static ExpertDao expertDao;

    public static ExpertDao instance() {

        if (expertDao == null)
            expertDao = new ExpertDao();

        return expertDao;
    }

    public void save(Expert expert) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(expert);
        transaction.commit();
        session.close();
    }

    public Expert findByEmail(String email) throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Expert> query = session.createQuery("FROM Expert expert WHERE expert.email =: email ");
        query.setParameter("email",email);
        Expert result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result ;
    }

    public void update(Expert expert) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }

    public void addExpertServices(Expert expert , Service service){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        expert.getServices().add(service);
        session.update(expert);
        transaction.commit();
        session.close();
    }
    public void removeExpertServices(Expert expert , Service service){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        expert.getServices().remove(service);
        System.out.println(expert.getServices());
        session.update(expert);
        transaction.commit();
        session.close();
    }

    public List<Expert> filterDynamic(String firstName, String lastName , String email) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Customer.class, "expert");
        if (firstName !=null ) {
            Criterion firstNameFilter = Restrictions.eq("expert.firstName", firstName);
            criteria.add(firstNameFilter);
        }
        if (lastName!= null) {
            Criterion lastNameFilter = Restrictions.eq("expert.lastName", lastName);
            criteria.add(lastNameFilter);
        }
        if (email!= null) {
            Criterion emailFilter = Restrictions.eq("expert.email", email);
            criteria.add(emailFilter);
        }
        List found = criteria.list();
        transaction.commit();
        session.close();

        return found;
    }

}
