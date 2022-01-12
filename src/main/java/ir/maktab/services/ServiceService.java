package ir.maktab.services;

import ir.maktab.dao.ServiceCategoryDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.dto.mappingMethod.MapperObject;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.exceptions.NoCategoryServiceForService;
import ir.maktab.models.entities.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
