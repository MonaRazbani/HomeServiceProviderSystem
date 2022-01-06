package dao;

import util.HibernateUtil;

public class InstructionDao extends HibernateUtil {
    private static InstructionDao instructionDao;

    public static InstructionDao instance() {

        if (instructionDao == null)
            instructionDao = new InstructionDao();

        return instructionDao;
    }

}
