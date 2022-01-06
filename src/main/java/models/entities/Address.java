package models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String city ;
    private String street ;
    private String alley ;
    private String houseNumber;
    private String floorNumber;
    private String HouseUnitNumber;

    public static final class AddressBuilder {
        private String city ;
        private String street ;
        private String alley ;
        private String houseNumber;
        private String floorNumber;
        private String HouseUnitNumber;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withAlley(String alley) {
            this.alley = alley;
            return this;
        }

        public AddressBuilder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder withFloorNumber(String floorNumber) {
            this.floorNumber = floorNumber;
            return this;
        }

        public AddressBuilder withHouseUnitNumber(String HouseUnitNumber) {
            this.HouseUnitNumber = HouseUnitNumber;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.setCity(city);
            address.setStreet(street);
            address.setAlley(alley);
            address.setHouseNumber(houseNumber);
            address.setFloorNumber(floorNumber);
            address.setHouseUnitNumber(HouseUnitNumber);
            return address;
        }
    }
}
