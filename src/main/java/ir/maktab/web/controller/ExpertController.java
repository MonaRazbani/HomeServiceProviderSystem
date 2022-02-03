package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.ExpertNotFound;
import ir.maktab.services.ExpertService;
import ir.maktab.services.OrderService;
import ir.maktab.services.ServiceCategoryService;
import ir.maktab.services.SubServiceService;
import ir.maktab.services.validation.OnExpertLogin;
import ir.maktab.services.validation.OnExpertSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expert")

public class ExpertController {
    private final ExpertService expertService;
    private final ServiceCategoryService serviceCategoryService;
    private final SubServiceService subServiceService;
    private final OrderService orderService;


    @GetMapping(value = "/signup")
    public ModelAndView showSignupPage() {
        return new ModelAndView("expert/signup", "expertDto", new ExpertDto());
    }

    @PostMapping("/submitSignup")
    public ModelAndView registerCustomer(@ModelAttribute("expertDto") @Validated(OnExpertSignup.class) ExpertDto expertDto,
                                         @RequestParam("image") CommonsMultipartFile image, ModelAndView modelAndView) {

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
                                HttpSession httpSession) {
        ExpertDto expertDtoLogin = expertService.loginExpert(expertDto);
        httpSession.setAttribute("expertDto", expertDtoLogin);
        return "expert/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboardPage(HttpSession httpSession){

        if (httpSession.getAttribute("expertDto")==null)
            throw new AccessDenied();

        return "expert/dashboard";
    }

    @GetMapping("/selectServiceCategory")
    public ModelAndView showSelectServiceCategoryPage (HttpSession httpSession){

        if (httpSession.getAttribute("expertDto")==null)
            throw new AccessDenied();

        List<ServiceCategoryDto> allServiceCategories = serviceCategoryService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("allServiceCategories", new ArrayList<ServiceCategoryDto>());
        model.put("serviceCategoryDto", new ServiceCategoryDto());

        return new ModelAndView("expert/selectServiceCategory",model);
    }

    @PostMapping(value = "/selectServiceCategoryProcess" )
    public String selectServiceCategoryProcess(HttpSession httpSession ,
                                               @ModelAttribute("serviceCategoryDto") ServiceCategoryDto serviceCategoryDto){

        if (httpSession.getAttribute("expertDto")==null)
            throw new AccessDenied();
        List<ServiceCategoryDto> allServiceCategories = serviceCategoryService.findAll();
        httpSession.setAttribute("serviceCategoryDto",serviceCategoryDto);

        return "expert/selectSubService";
    }

    @GetMapping("/selectSubService")
    public ModelAndView showSelectSubServicePage(HttpSession httpSession){

        if (httpSession.getAttribute("expertDto") == null) {
            throw new AccessDenied();
        }

        List<String> subServices= subServiceService.findAll().stream()
                .map(SubServiceDto::getName)
                .collect(Collectors.toList());


        Map<String, Object> model = new HashMap<>();
        model.put("subServices",subServices );
        model.put("subServiceDto", new SubServiceDto());

        return new ModelAndView("expert/selectSubService", model);
    }

    @PostMapping("/selectSubService")
    public String selectSubServiceProcess(HttpSession httpSession,
                                          @RequestParam("subServiceName")String subServiceName){

        ExpertDto expertDto = (ExpertDto) httpSession.getAttribute("expertDto");
        if ( expertDto == null)
            throw new AccessDenied();

        expertService.addSubServiceToExpertSubServices(expertDto,subServiceName);

        return "expert/dashboard";

    }


    @GetMapping("/listOfOrders")
    public ModelAndView showListOfOrder(HttpSession httpSession){

        if (httpSession.getAttribute("expertDto") == null) {
            throw new AccessDenied();
        }

        List<OrderDto> orderDtoList = orderService.findOrderByStatus(OrderStatus.WAITING_FOR_CHOOSING_EXPERT);

        Map<String, Object> model = new HashMap<>();
        model.put("orderDtoList",orderDtoList );
        model.put("OrderDto", new OfferDto());

        return new ModelAndView("/expert/listOfOrders",model);
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
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }
    @ExceptionHandler(value = AccessDenied.class)
    public ModelAndView bindExceptionHandler(AccessDenied ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, "error", ex.getMessage());
    }

}