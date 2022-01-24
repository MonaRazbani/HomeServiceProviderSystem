package ir.maktab.dto.modelDtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class ServiceCategoryDto {
    @NotNull(message = "enter ServiceCategory Name")
    private String name ;
}
