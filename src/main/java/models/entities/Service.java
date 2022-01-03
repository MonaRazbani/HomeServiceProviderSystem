package models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name ;
    private String explanation;
    private double cost ;
    @ManyToOne
    private ServiceCategory serviceCategory ;
}
