package services;

import dao.CustomerDao;
import dto.modelDtos.roles.CustomerDto;
import exceptions.CustomerNotFound;
import exceptions.WrongPassword;
import models.entities.roles.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.ControlEdition;
import validation.ControlInput;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;

    @Autowired
    public CustomerService(CustomerDao customerDao, ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition) {
        this.customerDao = customerDao;
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
    }


    public void saveCustomer(CustomerDto customerDto, String password) {
        if (controlInput.isValidCustomerDtoInfo(customerDto, password)) {

            Customer customer = modelMapper.map(customerDto, Customer.class);
            customer.setPassword(password);
            customerDao.save(customer);

        } else
            throw new RuntimeException("signup Fail");
    }

    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerDao.save(customer);

    }


    public CustomerDto findCustomerDtoByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            List<Customer> customers = customerDao.findByEmail(email);
            if (!customers.isEmpty()) {
                Customer customer = customers.get(0);
                CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
                return customerDto;
            } else throw new CustomerNotFound();
        } else
            throw new RuntimeException("searching fail ");
    }

    public Customer findCustomerByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            List<Customer> customers = customerDao.findByEmail(email);
            if (!customers.isEmpty()) {
                Customer customer = customers.get(0);
                return customer;
            } else throw new CustomerNotFound();
        } else
            throw new RuntimeException("searching fail ");
    }

    public void changePasswordForCustomer(CustomerDto customerDto, String currentPassword, String newPassword) {
        Customer customer = findCustomerByEmail(customerDto.getEmail());
        if (customer.getPassword().equals(currentPassword)) {
            customer.setPassword(newPassword);
            customerDao.save(customer);
            System.out.println("done");
        } else
            throw new WrongPassword();
    }

}
