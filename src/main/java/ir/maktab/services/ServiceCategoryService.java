package ir.maktab.services;

import ir.maktab.dao.ServiceCategoryDao;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.exceptions.DuplicateServiceCategory;
import ir.maktab.exceptions.ServiceCategoryNotFound;
import ir.maktab.models.entities.ServiceCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ServiceCategoryService {
    private final ServiceCategoryDao serviceCategoryDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceCategoryService(ServiceCategoryDao serviceCategoryDao, ModelMapper modelMapper) {
        this.serviceCategoryDao = serviceCategoryDao;
        this.modelMapper = modelMapper;
    }


    public void saveServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        if (serviceCategoryDao.findByName(serviceCategoryDto.getName()).isEmpty()) {
            ServiceCategory newServiceCategory = modelMapper.map(serviceCategoryDto, ServiceCategory.class);
            serviceCategoryDao.save(newServiceCategory);
        } else
            throw new DuplicateServiceCategory();
    }

    public void updateServiceCategory(ServiceCategoryDto serviceCategoryDto) {
        if (serviceCategoryDao.findByName(serviceCategoryDto.getName()).isPresent()) {
            ServiceCategory newServiceCategory = modelMapper.map(serviceCategoryDto, ServiceCategory.class);
            serviceCategoryDao.save(newServiceCategory);
        } else
            throw new ServiceCategoryNotFound();
    }

    public ServiceCategory findByName(String name) {
        Optional<ServiceCategory> serviceCategory = serviceCategoryDao.findByName(name);
        if (serviceCategory.isPresent()) {
            return serviceCategory.get();
        } else
            throw new ServiceCategoryNotFound();
    }

}
