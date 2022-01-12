package ir.maktab.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(nullable = false)
    private double rate ;
    private String comment ;
    @OneToOne
    private Order order;
}
