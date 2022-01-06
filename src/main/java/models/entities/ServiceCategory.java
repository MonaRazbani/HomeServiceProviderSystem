package models.entities;

import lombok.Data;

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
    @OneToMany(mappedBy = "serviceCategory",fetch = FetchType.EAGER)
    private List<Service> serviceList = new ArrayList<>();

    @Override
    public String toString() {
        return "ServiceCategory{" +
                "name='" + name + '\'' +
                ", serviceList=" + serviceList +
                '}';
    }
}
