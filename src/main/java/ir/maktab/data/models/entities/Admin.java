package ir.maktab.data.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true)
    private String username ;

    private String password ;

}
