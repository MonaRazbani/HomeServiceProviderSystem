package dao;

import util.HibernateUtil;

public class AddressDao extends HibernateUtil {
    private static AddressDao addressDao;

    public static AddressDao instance() {

        if (addressDao == null)
            addressDao = new AddressDao();

        return addressDao;
    }

}
