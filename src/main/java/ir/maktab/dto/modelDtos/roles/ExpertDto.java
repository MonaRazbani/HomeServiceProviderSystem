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
public class ExpertDto {
    private String firstName;
    private String lastName;
    private String email;
    private UserStatus status;
    private RoleType roleType;
    private byte[] photo;
    private float rate ;
}
