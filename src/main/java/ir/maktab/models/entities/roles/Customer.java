package ir.maktab.models.entities.roles;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ir.maktab.models.entities.Order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders;

}
