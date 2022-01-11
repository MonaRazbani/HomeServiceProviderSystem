package dto.modelDtos.roles;

import lombok.*;
import models.enums.RoleType;
import models.enums.UserStatus;
@Data
@Builder
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
