package services;

import dao.ServiceCategoryDao;
import dao.SubServiceDao;
import dto.mappingMethod.MapperObject;
import dto.modelDtos.SubServiceDto;
import exceptions.NoCategoryServiceForService;
import models.entities.ServiceCategory;
import models.entities.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ServiceService {
    private final SubServiceDao subServiceDao;
    private final ServiceCategoryDao serviceCategoryDao ;
    private final MapperObject mapperObject ;

    @Autowired
    public ServiceService(SubServiceDao subServiceDao, ServiceCategoryDao serviceCategoryDao, MapperObject mapperObject) {
        this.subServiceDao = subServiceDao;
        this.serviceCategoryDao = serviceCategoryDao;
        this.mapperObject = mapperObject;
    }

    public void saveNewService (SubServiceDto subServiceDto){
        SubService subService = mapperObject.subServiceDtoMapToSubService(subServiceDto);
            if (subService.getServiceCategory() == null)
                throw new NoCategoryServiceForService();
            subServiceDao.save(subService);
    }
}
