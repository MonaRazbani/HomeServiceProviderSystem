package models.entities.roles;

import lombok.Data;
import models.entities.Service;
import models.enums.Gender;
import models.enums.UserStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Expert extends User{
    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;
    private int rate ;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Service> services ;

    public static final class ExpertBuilder {
        private byte[] photo;
        private int rate ;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private UserStatus status ;
        private Date creationDate ;
        private Gender gender ;

        private ExpertBuilder() {
        }

        public static ExpertBuilder anExpert() {
            return new ExpertBuilder();
        }

        public ExpertBuilder withPhoto(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public ExpertBuilder withRate(int rate) {
            this.rate = rate;
            return this;
        }

        public ExpertBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ExpertBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ExpertBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ExpertBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public ExpertBuilder withStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public ExpertBuilder withCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public ExpertBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Expert build() {
            Expert expert = new Expert();
            expert.setPhoto(photo);
            expert.setRate(rate);
            expert.setFirstName(firstName);
            expert.setLastName(lastName);
            expert.setEmail(email);
            expert.setPassword(password);
            expert.setStatus(status);
            expert.setCreationDate(creationDate);
            expert.setGender(gender);
            return expert;
        }
    }
}
