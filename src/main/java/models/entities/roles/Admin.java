package models.entities.roles;

import lombok.Data;
import models.enums.RoleStatus;

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
    @Enumerated(value =EnumType.STRING)
    private static RoleStatus role = RoleStatus.ADMIN;

}
