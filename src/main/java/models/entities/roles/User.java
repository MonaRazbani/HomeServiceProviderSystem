package models.entities.roles;

import lombok.Builder;
import lombok.Data;
import models.enums.Gender;
import models.enums.UserStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String firstName;
    private String lastName;
@Column(unique = true)
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status ;
    @CreationTimestamp
    private Date creationDate ;
    @Enumerated(value = EnumType.STRING)
    private Gender gender ;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", gender=" + gender +
                '}';
    }
}
