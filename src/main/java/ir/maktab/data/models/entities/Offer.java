package ir.maktab.data.models.entities;

import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OfferStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @EqualsAndHashCode.Include
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
