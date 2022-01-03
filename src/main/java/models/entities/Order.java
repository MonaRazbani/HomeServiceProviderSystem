package models.entities;

import lombok.Data;
import models.entities.roles.Customer;
import models.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne
    private Customer customer ;
    @OneToMany (cascade = CascadeType.ALL , mappedBy = "order")
    private List<Offer> offerList;
    private double suggestedPrice ;
    private String explanation ;
    private Date doneService;
    @OneToOne
    private Address address;
    @CreationTimestamp
    private Date creation ;
    @OneToOne
    private Offer acceptedOffer ;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}
