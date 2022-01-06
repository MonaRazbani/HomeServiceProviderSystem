package models.entities;

import lombok.Data;
import models.entities.roles.Expert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Expert expert ;
    @CreationTimestamp
    private Date creationDate ;
    private double suggestedPrice ;
    private int suggestedDurationOfService ;
    private Date startDate ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Instruction instruction;
}
