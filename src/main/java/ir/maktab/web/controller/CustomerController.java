package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.models.entities.Offer;
import ir.maktab.data.models.entities.Order;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.exceptions.CustomerNotFound;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.services.CustomerService;
import ir.maktab.services.OfferService;
import ir.maktab.services.OrderService;
import ir.maktab.services.validation.OnCustomerLogin;
import ir.maktab.services.validation.OnCustomerSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")

public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OfferService offerService;

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

    @GetMapping("listOfOrder")
    public ModelAndView showListOfOrderPage (@SessionAttribute("customerDto")CustomerDto customerDto){

        if(customerDto==null)
            throw new AccessDenied() ;

        List<Order> customerOrder = orderService.findOrderByCustomer(customerDto);

        return new ModelAndView("/customer/selectOrder", "customerOrder",customerOrder);
    }

    @GetMapping("/selectOrder/{identificationCode}")
    public ModelAndView showOfferOfOrderPage(@SessionAttribute("customerDto")CustomerDto customerDto,
                                             @PathVariable String identificationCode){
        if (customerDto==null)
            throw new AccessDenied();

        Order order = orderService.findOrderByIdentificationCode(UUID.fromString(identificationCode));
        List<OfferDto> offerDtosOfOrder = offerService.findOfferDtosOfOrder(order);

        return new ModelAndView("customer/showOfferOfOrder","offerDtosOfOrder",offerDtosOfOrder);
    }

    @GetMapping("/showOfferOfOrder/{identificationCode}")
    public ModelAndView showOfferDtosOfOrderPage(@SessionAttribute("customerDto")CustomerDto customerDto,
                                                 @PathVariable String identificationCode,
                                                 HttpServletRequest request) throws ParseException {

        if (customerDto==null)
            throw new AccessDenied();

        Offer offer = offerService.findOfferByIdentificationCode(UUID.fromString(identificationCode));
        offerService.acceptOfferForOrder(offer);

        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        return new ModelAndView(lastView);
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