package ir.maktab.dto.modelDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private UUID identificationCode;
    @NotNull(message = "You can't leave this empty.")
    private String address ;
  /*  private String street ;
    private String alley ;
    private String houseNumber;
    private String floorNumber;
    private String HouseUnitNumber;*/

}
