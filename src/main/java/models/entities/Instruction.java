package models.entities;

import lombok.Data;
import models.entities.roles.Customer;
import models.enums.InstructionStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer ;
    @OneToMany (cascade = CascadeType.ALL , mappedBy = "instruction")
    private List<Offer> offerList;
    private double suggestedPrice ;
    private String explanation ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Service service ;
    private Date doneService;
    @OneToOne
    private Address address;
    @CreationTimestamp
    private Date creation ;
    @OneToOne
    private Offer acceptedOffer ;
    @Enumerated(value = EnumType.STRING)
    private InstructionStatus status;
    @OneToOne
    private Comment comment ;

    @Override
    public String toString() {
        return "Instruction{" +
                "customer=" + customer +
                ", suggestedPrice=" + suggestedPrice +
                ", explanation='" + explanation + '\'' +
                ", service=" + service +
                ", doneService=" + doneService +
                ", address=" + address +
                ", creation=" + creation +
                ", acceptedOffer=" + acceptedOffer +
                ", status=" + status +
                ", comment=" + comment +
                '}';
    }
}
