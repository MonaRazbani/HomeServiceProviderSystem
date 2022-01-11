package dto.modelDtos.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.enums.RoleType;
import models.enums.UserStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserStatus status;
    private RoleType roleType;
    private byte[] photo;
    private float rate ;
}
