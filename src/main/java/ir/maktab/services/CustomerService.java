package ir.maktab.services;

import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.dto.modelDtos.roles.CustomerDto;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    Customer updateCustomer(Customer customer);

    CustomerDto loginCustomer(CustomerDto customerDto);

    Customer findCustomerByEmail(String email);

    void changePasswordForCustomer(CustomerDto customerDto, String currentPassword, String newPassword);

    long findCustomerId(String email);


}
