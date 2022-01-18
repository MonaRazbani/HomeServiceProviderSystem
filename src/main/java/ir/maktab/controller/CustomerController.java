package ir.maktab.controller;

import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
/*    @RequestMapping(value = "/customer")
    @Autowired
    CustomerService customerService;
    @RequestMapping("/doLogin")
    public String doLogin(@ModelAttribute("customerDto") CustomerDto customerDto, ModelAndView modelAndView) {
        *//*if ("mona".equals(loginDto.getName()) && "1234".equals(loginDto.getPassword())) {
            model.addAttribute("loginDto", loginDto);
            return "dashboard";
        } else
            return "error";*//*
    }*/
    @RequestMapping("/customerSignUp")
    public ModelAndView showSignupPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customerSignUp");
        modelAndView.addObject("customerDto", new CustomerDto());
        modelAndView.addObject("password", new String());
        return modelAndView;
    }
   /* @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView signupProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") Login login) {
        ModelAndView mav = null;

        User user = userService.validateUser(login);

        if (null != user) {
            mav = new ModelAndView("welcome");
            mav.addObject("firstname", user.getFirstname());
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }
*/
   @RequestMapping(value = "/signUpProcess", method = RequestMethod.POST)
   public ModelAndView saveCustomer(@ModelAttribute("customerDto") CustomerDto customerDto,
                                    @RequestParam("password") String password , ModelAndView modelAndView) {
       String resultMessage;
       try {
          customerService.saveCustomer(customerDto, password);
          resultMessage="done";
      }catch (RuntimeException runtimeException){
          resultMessage= runtimeException.getMessage();
      }
       modelAndView.getModelMap().addAttribute("message",resultMessage);
       return modelAndView;
   }
}
