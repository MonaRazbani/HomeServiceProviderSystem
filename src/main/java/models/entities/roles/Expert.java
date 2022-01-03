package models.entities.roles;

import lombok.Data;
import models.entities.Service;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Expert extends User{
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;
    private int rate ;
    @ManyToMany
    private List<Service> services ;
}
