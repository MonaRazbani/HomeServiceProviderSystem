package ir.maktab.dto.modelDtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
public class SubServiceDto {
    private String name ;
    private String explanation;
    private String baseCost ;
    @NotNull(message = "select serviceCategory")
    private String serviceCategoryName;

}
