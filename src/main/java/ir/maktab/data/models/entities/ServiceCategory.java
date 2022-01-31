package ir.maktab.data.models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @NotNull
    @Column(unique = true , nullable = false)
    private String name ;

    @ToString.Exclude
    @OneToMany(mappedBy = "serviceCategory",fetch = FetchType.LAZY)
    private List<SubService> subServiceList = new ArrayList<>();

}
