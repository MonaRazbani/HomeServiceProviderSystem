package ir.maktab.dto.modelDtos.roles;

import ir.maktab.models.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ir.maktab.models.enums.RoleType;
import ir.maktab.models.enums.UserStatus;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends UserDto{

}
