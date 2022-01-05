package services;

import dao.ServiceCategoryDao;
import dao.ServiceDao;
import exceptions.NoCategoryServiceForService;
import lombok.Data;
import models.entities.Service;
import models.entities.ServiceCategory;
import models.entities.roles.Expert;

import javax.persistence.NoResultException;

@Data
public class ServiceService {
    private ServiceDao serviceDao ;
    private ServiceCategoryDao serviceCategoryDao ;
    private static ServiceService serviceService ;

    public static ServiceService instance(){
        if (serviceService == null)
            serviceService = new ServiceService();
        return serviceService;
    }


    public void addNewService (String serviceName , String explanation , double baseCost , String serviceCategoryName){
        Service service = new Service();
        service.setName(serviceName);
        service.setExplanation(explanation);
        service.setBaseCost(baseCost);
        try {
            ServiceCategory serviceCategory = serviceCategoryDao.findByName(serviceCategoryName);
            service.setServiceCategory(serviceCategory);
        }catch (NoResultException noResultException){
            System.out.println("there in no service category by this name first add service category");
        }
        try {
            if (service.getServiceCategory() == null)
                throw new NoCategoryServiceForService();
            serviceDao.save(service);
        }catch (NoCategoryServiceForService noCategoryServiceForService){
            System.out.println(noCategoryServiceForService.getMessage());
        }
    }
}
