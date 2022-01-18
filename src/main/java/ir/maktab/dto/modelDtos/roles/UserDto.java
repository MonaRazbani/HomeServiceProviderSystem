package ir.maktab.dto.modelDtos.roles;

import ir.maktab.models.enums.Gender;
import ir.maktab.models.enums.RoleType;
import ir.maktab.models.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID identificationCode;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserStatus status;
    private RoleType roleType;
    private Gender gender;
    private double credit;
}
