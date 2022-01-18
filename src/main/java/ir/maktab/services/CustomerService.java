package ir.maktab.services;

import ir.maktab.dao.CustomerDao;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.CommentNotFound;
import ir.maktab.exceptions.CustomerNotFound;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.WrongPassword;
import ir.maktab.models.entities.Comment;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.validation.ControlInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerDao customerDao, ControlInput controlInput, ModelMapper modelMapper) {
        this.customerDao = customerDao;
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
    }


    public void saveCustomer(CustomerDto customerDto) {
        if (controlInput.isValidCustomerDtoInfo(customerDto)) {

            if (customerDao.findByEmail(customerDto.getEmail()).isEmpty()) {
                Customer customer = modelMapper.map(customerDto, Customer.class);
                customer.setIdentificationCode(UUID.randomUUID());
                customerDao.save(customer);
            } else
                throw new DuplicateEmail();
        } else
            throw new RuntimeException("signup Fail");
    }

    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        long customerId = findCustomerId(customerDto.getEmail());
        customer.setId(customerId);
        customerDao.save(customer);

    }


    public Customer findCustomerByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            Optional<Customer> customer = customerDao.findByEmail(email);
            if (customer.isPresent()) {
                return customer.get();
            } else throw new CustomerNotFound();
        } else
            throw new RuntimeException("searching fail ");
    }

    public void changePasswordForCustomer(CustomerDto customerDto, String currentPassword, String newPassword) {
        if (controlInput.isValidPassword(newPassword)) {
            Customer customer = findCustomerByEmail(customerDto.getEmail());
            if (customer.getPassword().equals(currentPassword)) {
                customer.setPassword(newPassword);
                customerDao.save(customer);
                System.out.println("done");
            } else
                throw new WrongPassword();
        }
    }



    public long findCustomerId(String email){
        Customer customer = findCustomerByEmail(email);
        return customer.getId();
    }


}
