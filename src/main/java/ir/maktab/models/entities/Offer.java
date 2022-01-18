package ir.maktab.models.entities;

import ir.maktab.models.enums.OfferStatus;
import lombok.Data;
import ir.maktab.models.entities.roles.Expert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(columnDefinition = "BINARY(16)")
    private UUID identificationCode;
    @ManyToOne(fetch = FetchType.EAGER)
    private Expert expert ;
    @CreationTimestamp
    private Date creationDate ;
    private double suggestedPrice ;
    private float suggestedDurationOfService ;
    @Temporal(TemporalType.TIME)
    private Date startDate ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
    @Enumerated(EnumType.STRING)
    private OfferStatus status ;
}
