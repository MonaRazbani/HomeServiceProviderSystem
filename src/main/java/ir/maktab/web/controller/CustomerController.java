package ir.maktab.web.controller;

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
import org.springframework.ui.Model;
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
    private final ServiceCategoryService serviceCategoryService;
    private final SubServiceService subServiceService;

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
                                HttpSession httpSession) {
        CustomerDto loginCustomer = customerService.loginCustomer(customerDto);
        httpSession.setAttribute("customerDto",loginCustomer);
        return "customer/dashboard";
    }

    @GetMapping("/listOfServiceCategory")
    public ModelAndView listOfServiceCategoryPage(HttpSession httpSession) {
        CustomerDto customerDto = (CustomerDto) httpSession.getAttribute("customerDto");
        if ( customerDto == null)
            throw new AccessDenied();

        List<String> categoryServiceAll = serviceCategoryService.findAll().
                stream().
                map(ServiceCategoryDto::getName).
                collect(Collectors.toList());

        Map<String, Object> model = new HashMap<>();
        model.put("categoryServiceAll", categoryServiceAll);
        model.put("serviceCategoryDto", new ServiceCategoryDto());

        return new ModelAndView("customer/selectServiceCategory", model);
    }

    @PostMapping("/selectServiceCategory")
    public ModelAndView selectServiceCategory(HttpSession httpSession,
                                              @ModelAttribute("orderDto") OrderDto orderDto) {
        if (httpSession.getAttribute("customerDto") == null)
            throw new AccessDenied();
        return new ModelAndView("customer/selectSubService");
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