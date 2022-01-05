package services;

import dao.ServiceDao;
import lombok.Data;

@Data
public class ServiceService {
    private ServiceDao serviceDao ;
    private static ServiceService serviceService ;

    public static ServiceService instance(){
        if (serviceService == null)
            serviceService = new ServiceService();
        return serviceService;
    }
}
