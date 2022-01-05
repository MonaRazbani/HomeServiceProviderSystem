package models.entities;

import lombok.Data;
import models.entities.roles.Expert;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(unique = true , nullable = false)
    private String name ;
    private String explanation;
    private double baseCost ;
    @ManyToOne
    private ServiceCategory serviceCategory ;
    @ManyToMany(mappedBy = "services",cascade = CascadeType.ALL)
    private List<Expert> expertList ;
}
