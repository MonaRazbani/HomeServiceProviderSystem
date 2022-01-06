package dao;

import models.entities.Instruction;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.enums.InstructionStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

public class InstructionDao extends HibernateUtil {
    private static InstructionDao instructionDao;

    public static InstructionDao instance() {

        if (instructionDao == null)
            instructionDao = new InstructionDao();

        return instructionDao;
    }

    public void save (Instruction instruction){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(instruction);
        transaction.commit();
        session.close();
    }

    public void update(Instruction instruction) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(instruction);
        transaction.commit();
        session.close();
    }

    public void delete(Instruction instruction) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(instruction);
        transaction.commit();
        session.close();
    }

    public Instruction findById (long id){
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Instruction> query = session.createQuery("FROM Instruction instruction WHERE instruction.id =: id ");
        query.setParameter("id",id);
        Instruction result = query.getSingleResult();
        transaction.commit();
        session.close();
        return result ;
    }

    public List<Instruction> findInstructionByCustomerAndStatus (Customer customer, InstructionStatus status)throws NoResultException {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Instruction> query = session.createQuery("FROM Instruction instruction WHERE" +
                " instruction.customer =: customerId AND instruction.status=:status " );
        query.setParameter("customerId",customer.getId());
        query.setParameter("status",status);
        List<Instruction> result = query.getResultList();
        transaction.commit();
        session.close();
        return result ;
    }
}
