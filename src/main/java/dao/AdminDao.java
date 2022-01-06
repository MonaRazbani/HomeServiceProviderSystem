package dao;

import models.entities.roles.Admin;
import models.entities.roles.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class AdminDao extends HibernateUtil {
    private static AdminDao adminDao;

    public static AdminDao instance() {

        if (adminDao == null)
            adminDao = new AdminDao();

        return adminDao;
    }
    public void save (Admin admin){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
        session.close();
    }
    public Admin findByUsername(String username){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Admin> query = session.createQuery("FROM Admin admin WHERE admin.username =: username ");
        query.setParameter("username",username);
        Admin result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result ;
    }

}
