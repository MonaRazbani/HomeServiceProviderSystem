package ir.maktab.data.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(columnDefinition = "BINARY(16)",unique = true)
    private UUID identificationCode;

    @Column(nullable = false)
    private double rate ;

    private String comment ;

    @OneToOne
    private Order order;
}
