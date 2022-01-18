package ir.maktab.dto.modelDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private UUID identificationCode;
    private String city ;
    private String street ;
    private String alley ;
    private String houseNumber;
    private String floorNumber;
    private String HouseUnitNumber;

}
