package ir.maktab.data.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(columnDefinition = "BINARY(16)",unique = true)
    private UUID identificationCode;
    private String address;
   /* private String city;
    private String street;
    private String alley;
    private String houseNumber;
    private String floorNumber;
    private String HouseUnitNumber;*/

}
