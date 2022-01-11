package models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import models.entities.roles.Expert;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id ;
    @Column(unique = true , nullable = false)
    @EqualsAndHashCode.Include
    private String name ;
    private String explanation;
    private double baseCost ;
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceCategory serviceCategory ;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "subService")
    private List<Order> instructionsOfServices;
    @ManyToMany(mappedBy = "subServices")
    @ToString.Exclude
    private List<Expert> expertList ;


}
