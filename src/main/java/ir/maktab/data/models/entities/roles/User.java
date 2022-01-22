package ir.maktab.data.models.entities.roles;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ir.maktab.data.models.enums.Gender;
import ir.maktab.data.models.enums.RoleType;
import ir.maktab.data.models.enums.UserStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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

    @Column(unique = true,nullable = false)
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

    private double credit;


}
