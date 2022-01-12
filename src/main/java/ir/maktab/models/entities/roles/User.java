package ir.maktab.models.entities.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ir.maktab.models.enums.Gender;
import ir.maktab.models.enums.RoleType;
import ir.maktab.models.enums.UserStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
    @CreationTimestamp
    private Date creationDate;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;


}
