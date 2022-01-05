package dao;

import models.entities.Service;
import models.entities.roles.Expert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.Set;

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
        session.update(expert);
        transaction.commit();
        session.close();
        /*Query<Expert> query1 = session.createQuery("FROM Expert expert WHERE expert.id =:id ");
        Query<Expert> query2 = session.createQuery("FROM Service service WHERE service.id =:id ");

        query1.setParameter("id",expert.getId());
        query2.setParameter("id",service.getId());
        Query query = session.createNativeQuery("DELETE FROM expert_service WHERE expertList_id=:expertId AND expertList_id=:serviceId ");
        query.setParameter("expertId",expert.getId());
        query.setParameter("serviceId",service.getId());*/

    }
}
