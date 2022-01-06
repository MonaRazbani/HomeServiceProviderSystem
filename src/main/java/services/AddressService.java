package services;

import dao.AddressDao;
import lombok.Data;
import models.entities.Address;
import models.entities.roles.Customer;
import models.enums.Gender;
import models.enums.UserStatus;
@Data
public class AddressService {
    private  static AddressDao addressDao ;
    public static Address createAddress (String addressInfo ){

        try {
            String[] AddressInfoSplit = addressInfo.split(",");
            String city = AddressInfoSplit[0];
            String street = AddressInfoSplit[1];
            String alley = AddressInfoSplit[2];
            String houseNumber = AddressInfoSplit[3];
            String floorNumber = AddressInfoSplit[4];
            String HouseUnitNumber = AddressInfoSplit[5];
            Address address = Address.AddressBuilder.anAddress()
                    .withCity(city)
                    .withStreet(street)
                    .withAlley(alley)
                    .withHouseNumber(houseNumber)
                    .withFloorNumber(floorNumber)
                    .withHouseUnitNumber(HouseUnitNumber)
                    .build();
            return address;

        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }
}
