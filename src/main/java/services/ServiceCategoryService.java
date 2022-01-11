package services;

import dao.ServiceCategoryDao;
import dto.modelDtos.ServiceCategoryDto;
import models.entities.ServiceCategory;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.ControlInput;

import javax.persistence.Access;

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
