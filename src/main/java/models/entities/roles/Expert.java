package models.entities.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import models.entities.SubService;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Expert extends User{
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;
    private float rate ;
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<SubService> subServices = new HashSet<>();

}
