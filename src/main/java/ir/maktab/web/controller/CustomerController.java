package ir.maktab.web.controller;

import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.services.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImp customerServiceImp;


    @GetMapping(value = "/signUp")
    public ModelAndView showRegisterPage() {
        ModelAndView signUp = new ModelAndView("signUp");
        signUp.addObject("customerDto", new CustomerDto());
        return signUp;
    }

    @RequestMapping(value = "/signUpProcess" ,method = RequestMethod.POST)
    public String saveCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto, BindingResult bindingResult, Model signUp) {
        try {
            if (!bindingResult.hasErrors()) {
                signUp.addAttribute("customerDto", customerDto);
                customerServiceImp.saveCustomer(customerDto);
                return "dashboard";
            } else
                return "customerSignUp";
        } catch (DuplicateEmail duplicateEmail) {

            signUp.addAttribute("message", duplicateEmail.getMessage());
            return "customerSignUp";

        }
    }
}
