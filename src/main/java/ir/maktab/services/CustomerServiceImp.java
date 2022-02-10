package ir.maktab.services;

import ir.maktab.data.dao.CustomerDao;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.CustomerNotFound;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.WrongPassword;
import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.enums.RoleType;
import ir.maktab.data.models.enums.UserStatus;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerDao customerDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;


    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        if (customerDao.findByEmail(customerDto.getEmail()).isEmpty()) {
            Customer customer = modelMapper.map(customerDto, Customer.class);
            customer.setRoleType(RoleType.CUSTOMER);
            customer.setStatus(UserStatus.NEW);
            Customer save = customerDao.save(customer);
            return modelMapper.map(save, CustomerDto.class);
        } else
            throw new DuplicateEmail();
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        long customerId = findCustomerId(customerDto.getEmail());
        customer.setId(customerId);
        Customer save = customerDao.save(customer);
        return modelMapper.map(save, CustomerDto.class);

    }

    @Override
    public Customer updateCustomer(Customer customer) {
       return customerDao.save(customer) ;
    }

    @Override
    public CustomerDto loginCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Optional<Customer> found = customerDao.findByEmailAndPassword(customer.getEmail(),customer.getPassword());
        if (found.isPresent()){
            return modelMapper.map(found.get(),CustomerDto.class);
        }else
            throw new CustomerNotFound();
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            Optional<Customer> customer = customerDao.findByEmail(email);
            if (customer.isPresent()) {
                return customer.get();
            } else throw new CustomerNotFound();
        } else
            throw new RuntimeException("searching fail ");
    }

    @Override
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

    @Override
    public long findCustomerId(String email){
        Customer customer = findCustomerByEmail(email);
        return customer.getId();
    }

}
