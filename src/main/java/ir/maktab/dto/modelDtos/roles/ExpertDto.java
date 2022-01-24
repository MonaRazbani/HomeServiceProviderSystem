package ir.maktab.dto.modelDtos.roles;

import ir.maktab.services.validation.OnCustomerSignup;
import ir.maktab.services.validation.OnExpertSignup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto extends UserDto{
    private byte[] photo;
    private float rate ;
    private String rateString;
}
