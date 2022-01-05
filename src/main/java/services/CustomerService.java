package services;

import models.enums.Gender;

public class CustomerService {

    public  void AddCustomer(String customerInfo){
        String [] customerInfoSplit = customerInfo.split(",");
        String firstName = customerInfoSplit[0];
        String LastName = customerInfoSplit[1];
        String email = customerInfoSplit[2];
        String password = customerInfoSplit[3];
        Gender gender = Gender.valueOf(customerInfoSplit[4]);

    }
}
