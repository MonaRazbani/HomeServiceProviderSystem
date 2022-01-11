package models.entities;

import lombok.*;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.enums.OfferStatus;
import models.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "CustomerOrder")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer ;
    private double suggestedPrice ;
    private String explanation ;
    @ManyToOne(fetch = FetchType.EAGER)
    private SubService subService;
    private Date performedOrder ;
    @ManyToOne
    private Address address;
    @CreationTimestamp
    private Date creation ;
    @ManyToOne
    private Expert expert ;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @OneToOne
    private Comment comment ;
    @ToString.Exclude
    @OneToMany (cascade = CascadeType.ALL , mappedBy = "order")
    private List<Offer> offerList;

}
