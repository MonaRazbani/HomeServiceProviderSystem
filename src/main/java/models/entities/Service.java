package models.entities;

import lombok.Data;
import models.entities.roles.Expert;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    @ManyToMany(mappedBy = "services" )
    private List<Expert> expertList ;

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", explanation='" + explanation + '\'' +
                ", baseCost=" + baseCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return id == service.id  && Objects.equals(name, service.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
