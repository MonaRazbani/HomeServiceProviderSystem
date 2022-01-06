package models.entities.roles;

import lombok.Data;
import models.entities.Instruction;
import models.enums.Gender;
import models.enums.UserStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
@Data
@Entity
public class Customer extends User {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Instruction> instructions;


    public static final class CustomerBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private UserStatus status ;
        private Date creationDate ;
        private Gender gender ;

        private CustomerBuilder() {
        }

        public static CustomerBuilder aCustomer() {
            return new CustomerBuilder();
        }

        public CustomerBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder withStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public CustomerBuilder withCreationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public CustomerBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setStatus(status);
            customer.setCreationDate(creationDate);
            customer.setGender(gender);
            return customer;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
