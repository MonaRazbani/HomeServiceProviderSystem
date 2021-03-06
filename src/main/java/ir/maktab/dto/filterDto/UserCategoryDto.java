package ir.maktab.dto.filterDto;

import ir.maktab.data.models.enums.RoleType;
import lombok.Data;

@Data
public class UserCategoryDto extends BasePaginationDto {
    private String email;
    private String firstName;
    private String lastName;
    private String subServiceName;
    private RoleType roleType;
}
