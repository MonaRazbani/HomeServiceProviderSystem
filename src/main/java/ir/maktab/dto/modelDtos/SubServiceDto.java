package ir.maktab.dto.modelDtos;

import lombok.Data;
@Data
public class SubServiceDto {
    private String name ;
    private String explanation;
    private double baseCost ;
    private ServiceCategoryDto serviceCategory;

}
