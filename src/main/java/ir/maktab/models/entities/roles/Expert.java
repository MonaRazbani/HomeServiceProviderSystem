package ir.maktab.models.entities.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import ir.maktab.models.entities.SubService;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Expert extends User implements Comparable<Expert> {
    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;
    private float rate;
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<SubService> subServices = new HashSet<>();


    @Override
    public int compareTo(Expert o) {
        return Float.compare(getRate(), o.getRate());
    }
}

