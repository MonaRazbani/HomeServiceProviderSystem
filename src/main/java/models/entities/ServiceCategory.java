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
    private String name ;
    @OneToMany(mappedBy = "serviceCategory")
    private List<Service> serviceList = new ArrayList<>();


}
