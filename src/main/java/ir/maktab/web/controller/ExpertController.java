package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.CustomerNotFound;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.ExpertNotFound;
import ir.maktab.services.ExpertService;
import ir.maktab.services.validation.OnExpertLogin;
import ir.maktab.services.validation.OnExpertSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expert")

public class ExpertController {
    private final ExpertService expertService;


    @GetMapping(value = "/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("expert/signup", "expertDto", new ExpertDto());
    }

    @PostMapping("/submitSignup")
    public ModelAndView registerCustomer(@ModelAttribute("expertDto") @Validated(OnExpertSignup.class) ExpertDto expertDto,
                                   @RequestParam("image") CommonsMultipartFile image,ModelAndView modelAndView) {

        expertService.saveExpert(expertDto, image);
        modelAndView.setViewName("expert/dashboard");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("expert/login", "expertDto", new ExpertDto());
    }

    @PostMapping("/submitLogin")
    public String loginCustomer(@ModelAttribute("ExpertDto") @Validated(OnExpertLogin.class) ExpertDto expertDto,
                                Model model) {
        expertService.loginExpert(expertDto);
        return "expert/dashboard";
    }


    @GetMapping("/dashboard")
    public ModelAndView showDashboard() {
        return null;
    }

/*
    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }

    @ExceptionHandler(value = ExpertNotFound.class)
    public ModelAndView loginExceptionHandler(ExpertNotFound ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("expertDto", new ExpertDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("expert/login", model);
    }

    @ExceptionHandler(value = DuplicateEmail.class)
    public ModelAndView signupExceptionHandler(DuplicateEmail ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("expertDto", new ExpertDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("expert/signup", model);
    }*/
}