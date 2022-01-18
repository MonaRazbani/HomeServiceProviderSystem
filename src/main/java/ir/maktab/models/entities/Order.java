package ir.maktab.models.entities;

import lombok.*;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.models.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Column(columnDefinition = "BINARY(16)")
    private UUID identificationCode;
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
