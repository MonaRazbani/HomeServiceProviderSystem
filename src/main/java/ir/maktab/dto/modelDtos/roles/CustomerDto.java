package ir.maktab.dto.modelDtos.roles;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ir.maktab.models.enums.RoleType;
import ir.maktab.models.enums.UserStatus;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserStatus status;
    private RoleType roleType;

}
