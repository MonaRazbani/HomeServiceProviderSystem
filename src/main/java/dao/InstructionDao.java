package dao;

import models.entities.Instruction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

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
}
