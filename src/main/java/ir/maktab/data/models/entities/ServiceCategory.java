package ir.maktab.data.models.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true , nullable = false)
    private String name ;

    @ToString.Exclude
    @OneToMany(mappedBy = "serviceCategory",fetch = FetchType.EAGER)
    private List<SubService> subServiceList = new ArrayList<>();


}
