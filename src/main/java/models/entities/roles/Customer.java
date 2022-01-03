package models.entities.roles;

import models.entities.Order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer extends User {
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
  private List<Order> orders ;

}
