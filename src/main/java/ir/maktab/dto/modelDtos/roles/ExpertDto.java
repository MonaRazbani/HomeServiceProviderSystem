package ir.maktab.dto.modelDtos.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ir.maktab.models.enums.RoleType;
import ir.maktab.models.enums.UserStatus;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto extends UserDto{
    private byte[] photo;
    private float rate ;
}
