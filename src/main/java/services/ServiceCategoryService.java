package services;

import dao.ServiceCategoryDao;
import lombok.Data;
import models.entities.ServiceCategory;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import validation.ControlInput;
@Data
public class ServiceCategoryService {
    private ServiceCategoryDao serviceCategoryDao ;
    private ControlInput controlInput;
    private static ServiceCategoryService serviceCategoryService ;

    public static ServiceCategoryService instance(){
        if (serviceCategoryService == null)
            serviceCategoryService = new ServiceCategoryService();
        return serviceCategoryService;
    }

    public void addNewServiceCategory (String serviceCategoryName){
        try {
            try {
                ServiceCategory serviceCategory = new ServiceCategory();
                serviceCategory.setName(serviceCategoryName);
                serviceCategoryDao.save(serviceCategory);
            }catch (PropertyValueException propertyValueException){
                System.out.println("service category name can't be null");
            }
        } catch (ConstraintViolationException exception){
            System.out.println("service category already exist");;
        }

    }
}
