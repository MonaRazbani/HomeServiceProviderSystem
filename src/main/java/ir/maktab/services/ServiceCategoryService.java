package ir.maktab.services;

import ir.maktab.dao.ServiceCategoryDao;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceCategoryService {
    private final ServiceCategoryDao serviceCategoryDao ;

@Autowired
    public ServiceCategoryService(ServiceCategoryDao serviceCategoryDao) {
        this.serviceCategoryDao = serviceCategoryDao;
    }


    public void saveNewServiceCategory (ServiceCategoryDto serviceCategoryDto){


    }

}
