package dto.modelDtos;

import lombok.Data;
@Data
public class SubServiceDto {
    private long id ;
    private String name ;
    private String explanation;
    private double baseCost ;
    private ServiceCategoryDto serviceCategoryDto ;

}
