package ir.maktab.data.models.entities.roles;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ir.maktab.data.models.entities.Order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
public class Customer extends User {

}
