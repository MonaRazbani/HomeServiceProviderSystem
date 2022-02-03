package ir.maktab.web.controller;

import ir.maktab.dto.mapper.SubServiceMapper;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.exceptions.CustomerNotFound;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.services.CustomerService;
import ir.maktab.services.ServiceCategoryService;
import ir.maktab.services.SubServiceService;
import ir.maktab.services.validation.OnCustomerLogin;
import ir.maktab.services.validation.OnCustomerSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")

public class CustomerController {
    private final CustomerService customerService;

    @GetMapping(value = "/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("customer/signup", "customerDto", new CustomerDto());
    }

    @PostMapping("/submitSignup")
    public String registerCustomer(@ModelAttribute("customerDto") @Validated(OnCustomerSignup.class) CustomerDto customerDto,
                                   HttpSession httpSession) {

        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        httpSession.setAttribute("customerDto",saveCustomer);

        return "customer/dashboard";
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("customer/login", "customerDto", new CustomerDto());
    }

    @PostMapping("/submitLogin")
    public String loginCustomer(@ModelAttribute("customerDto") @Validated(OnCustomerLogin.class) CustomerDto customerDto,
                                HttpSession httpSession) {
        CustomerDto loginCustomer = customerService.loginCustomer(customerDto);
        httpSession.setAttribute("customerDto", loginCustomer);
        return "customer/dashboard";
    }

    @GetMapping("/dashboard")
    public ModelAndView showDashboard() {
        return null;
    }

    @ExceptionHandler(value = CustomerNotFound.class)
    public ModelAndView loginExceptionHandler(CustomerNotFound ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("customerDto", new CustomerDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("customer/login", model);
    }

    @ExceptionHandler(value = DuplicateEmail.class)
    public ModelAndView signupExceptionHandler(DuplicateEmail ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("customerDto", new CustomerDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("customer/signup", model);
    }
}