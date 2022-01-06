package dao;

import models.entities.Address;
import models.entities.Instruction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class AddressDao extends HibernateUtil {
    private static AddressDao addressDao;

    public static AddressDao instance() {

        if (addressDao == null)
            addressDao = new AddressDao();

        return addressDao;
    }
    public void save (Address address){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
        session.close();
    }
    public void update (Address address){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
        session.close();
    }


}
