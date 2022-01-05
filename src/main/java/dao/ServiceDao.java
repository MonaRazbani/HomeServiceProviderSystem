package dao;

public class ServiceDao {
    private static ServiceDao serviceDao;

    public static ServiceDao instance() {
        if (serviceDao == null)
            serviceDao = new ServiceDao();
        return serviceDao;
    }
}
