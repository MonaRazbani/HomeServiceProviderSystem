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
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private UserStatus status;
    private RoleType roleType;

}
