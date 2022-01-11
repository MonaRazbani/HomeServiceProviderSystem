package dto.modelDtos;

import lombok.Data;

@Data
public class AddressDto {
    private long id ;
    private String city ;
    private String street ;
    private String alley ;
    private String houseNumber;
    private String floorNumber;
    private String HouseUnitNumber;

}
