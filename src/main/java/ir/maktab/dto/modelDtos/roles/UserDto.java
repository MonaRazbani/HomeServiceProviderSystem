package ir.maktab.dto.modelDtos.roles;

import ir.maktab.data.models.enums.Gender;
import ir.maktab.data.models.enums.RoleType;
import ir.maktab.data.models.enums.UserStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull
    @Size(min = 3, message = "You can't leave this empty.")
    private String firstName;
    @NotNull
    @Size(min = 3, message = "You can't leave this empty.")
    private String lastName;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email")
    private String email;
    @Size(min = 8, message = "password length's  must more than 8 ")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z]).{8,32}$", message = "password must contains words and numbers")
    private String password;
    private UserStatus status;
    private RoleType roleType;
    private Gender gender;
    private double credit;
}
