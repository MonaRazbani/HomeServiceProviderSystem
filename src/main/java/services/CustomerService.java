package services;

import dao.CustomerDao;
import dao.ExpertDao;
import lombok.Getter;
import lombok.Setter;
import models.entities.roles.Customer;
import models.enums.Gender;
import models.enums.UserStatus;
import models.enums.UserType;
import validation.ControlInput;

import javax.persistence.NoResultException;

@Getter
@Setter
public class CustomerService {
    private CustomerDao customerDao ;
    private ControlInput controlInput ;
    private static CustomerService customerService;

    public static CustomerService instance() {

        if (customerService == null)
            customerService = new CustomerService();

        return customerService;
    }

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
                        .withUserType(UserType.CUSTOMER)
                        .build();

                customerDao.save(customer);
            }

        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    }

    public Customer findCustomerByEmail(String email) {
        Customer customer = null;
        if (controlInput.isValidEmail(email)) {
            try {
                customer = customerDao.findByEmail(email);

            } catch (NoResultException noResultException) {
                System.out.println("Customer not found!");
            }
        }
        return customer;
    }

    public void changePasswordForCustomer(Customer customer , String currentPassword , String newPassword){
        if (customer.getPassword().equals(currentPassword)){
            customer.setPassword(newPassword);
            customerDao.update(customer);
            System.out.println("done");
        }else System.out.println("changing password fail because you current password not correct ");

    }

}
