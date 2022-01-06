package dao;

import models.entities.Instruction;
import models.entities.Offer;
import models.enums.InstructionStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

public class OfferDao extends HibernateUtil {
    private static OfferDao offerDao;

    public static OfferDao instance() {

        if (offerDao == null)
            offerDao = new OfferDao();

        return offerDao;
    }

    public void save(Offer offer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(offer);
        transaction.commit();
        session.close();
    }

    public void update(Offer offer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(offer);
        transaction.commit();
        session.close();
    }

    public List<Offer> findOffersOfInstruction(Instruction instruction, InstructionStatus status) throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Offer> query = session.createQuery("FROM Offer offer WHERE Offer.instruction=:instruction");
        query.setParameter("instruction",instruction);
        List<Offer> results = query.getResultList();
        return results;
    }

    public Offer findById(long offerId) {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query<Offer> query = session.createQuery("FROM Offer offer WHERE offer.id =: id ");
            query.setParameter("id",offerId);
            Offer result = query.getSingleResult();
            transaction.commit();
            session.close();
            return result ;
        }

    public void delete(Offer offer) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(offer);
        transaction.commit();
        session.close();
    }
}


