package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.CustomerNotFound;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.services.CustomerService;
import ir.maktab.services.validation.OnCustomerLogin;
import ir.maktab.services.validation.OnCustomerSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    public String registerCustomer(@ModelAttribute("customerDto") @Validated(OnCustomerSignup.class) CustomerDto customerDto) {

        customerService.saveCustomer(customerDto);

        return "customer/dashboard";
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("customer/login", "customerDto", new CustomerDto());
    }

    @PostMapping("/submitLogin")
    public String loginCustomer(@ModelAttribute("customerDto") @Validated(OnCustomerLogin.class) CustomerDto customerDto,
                                Model model) {
        customerService.loginCustomer(customerDto);
        return "customer/dashboard";
    }


    @GetMapping("/dashboard")
    public ModelAndView showDashboard() {
        return null;
    }
    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
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