package ir.maktab.data.models.entities;

import ir.maktab.data.models.entities.roles.Expert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(mappedBy = "subServices")
    @ToString.Exclude
    private List<Expert> expertList ;


}
