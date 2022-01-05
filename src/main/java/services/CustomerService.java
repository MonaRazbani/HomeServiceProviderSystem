package services;

import dao.CustomerDao;
import lombok.Getter;
import lombok.Setter;
import models.entities.roles.Customer;
import models.enums.Gender;
import models.enums.UserStatus;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import validation.ControlInput;

@Getter
@Setter
public class CustomerService {
    private CustomerDao customerDao =new CustomerDao() ;
    private ControlInput controlInput = new ControlInput();

    public void AddCustomer(String customerInfo) {
        try {
            String[] customerInfoSplit = customerInfo.split(",");
            String firstName = customerInfoSplit[0];
            String lastName = customerInfoSplit[1];
            String email = customerInfoSplit[2];
            String password = customerInfoSplit[3];
            Gender gender = Gender.valueOf(customerInfoSplit[4]);
            if (controlInput.isValidName(firstName)
                    && controlInput.isValidName(lastName)
                    && controlInput.isValidEmail(email)
                    && controlInput.isValidPassword(password)) {
                Customer customer = Customer.CustomerBuilder.aCustomer()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withGender(gender)
                        .withEmail(email)
                        .withPassword(password)
                        .withStatus(UserStatus.NEW)
                        .build();

                customerDao.save(customer);
            }

        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    }

}
