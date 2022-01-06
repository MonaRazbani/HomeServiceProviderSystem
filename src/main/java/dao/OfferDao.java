package dao;

import util.HibernateUtil;

public class OfferDao extends HibernateUtil {
    private static OfferDao offerDao;

    public static OfferDao instance() {

        if (offerDao == null)
            offerDao = new OfferDao();

        return offerDao;
    }

}
