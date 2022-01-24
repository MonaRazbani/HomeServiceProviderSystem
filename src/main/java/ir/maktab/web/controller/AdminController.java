package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.dto.filterDto.UserCategoryDto;
import ir.maktab.dto.modelDtos.AdminDto;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.dto.modelDtos.roles.UserDto;
import ir.maktab.exceptions.AdminNotFound;
import ir.maktab.exceptions.BadFilterSearching;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.DuplicateSubService;
import ir.maktab.services.AdminService;
import ir.maktab.services.ServiceCategoryService;
import ir.maktab.services.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"adminDto"})
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;
    private final SubServiceService subServiceService;
    private final ServiceCategoryService serviceCategoryService ;



    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("admin/login", "adminDto", new AdminDto());
    }

    @PostMapping("/submitLogin")
    public ModelAndView loginCustomer(@ModelAttribute("adminDto") @Validated AdminDto adminDto) {
        adminService.loginAdmin(adminDto);
        return new ModelAndView("admin/dashboard");
    }


    @GetMapping("/searchUser")
    public ModelAndView showSearchUserPage( HttpSession session){
        List<SubServiceDto> serviceServiceDtoAll = subServiceService.findAll();

        session.setAttribute("serviceServiceDtoAll",serviceServiceDtoAll);

        Map<String, Object> model = new HashMap<>();
        model.put("userCategoryDto", new UserCategoryDto());
        model.put("serviceServiceDtoAll",serviceServiceDtoAll);

        return new ModelAndView("admin/searchUser",model);
    }

    @PostMapping("/searchUserProcess")
    public ModelAndView searchUser(@ModelAttribute("userCategoryDto") UserCategoryDto userCategoryDto, ModelAndView modelAndView){
        List<UserDto> userDtoList = adminService.UserDynamicSearch(userCategoryDto);
        modelAndView.addObject("userDtoList",userDtoList);
        modelAndView.setViewName("admin/searchUser");
        return modelAndView;
    }
    @GetMapping(value = "/addSubService")
    public ModelAndView showAddSubServicePage(){
        List<String> serviceCategoryNameAll= serviceCategoryService.findAll().
                stream().
                map(serviceCategoryDto -> serviceCategoryDto.getName()).
                collect(Collectors.toList());
        Map<String, Object> model = new HashMap<>();
        model.put("subServiceDto", new SubServiceDto());
        model.put("serviceCategoryNameAll",serviceCategoryNameAll);

        return new ModelAndView("admin/addSubService",model);
    }

    @PostMapping("/addSubServiceProcess")
    public String addSubService(@ModelAttribute("subServiceDto")SubServiceDto subServiceDto){
        subServiceService.saveSubService(subServiceDto);

        return "admin/dashboard";
    }

    @GetMapping("/addServiceCategory")
    public ModelAndView showAddServiceCategory (){
        return new ModelAndView("admin/addServiceCategory","serviceCategoryDto",new ServiceCategoryDto());
    }

    @PostMapping("/addServiceCategoryProcess")
    public String addServiceCategory(@ModelAttribute("serviceCategoryDto")ServiceCategoryDto serviceCategoryDto){
        serviceCategoryService.saveServiceCategory(serviceCategoryDto);
        return "admin/dashboard";
    }


    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());
    }

    @ExceptionHandler(value = AdminNotFound.class)
    public ModelAndView loginExceptionHandler(AdminNotFound ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("adminDto", new AdminDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("admin/login", model);
    }

    @ExceptionHandler(value = BadFilterSearching.class)
    public ModelAndView loginExceptionHandler(BadFilterSearching ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("userCategoryDto", new UserCategoryDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("admin/searchUser", model);
    }

    @ExceptionHandler(value = DuplicateSubService.class)
    public ModelAndView signupExceptionHandler(DuplicateSubService ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("subServiceDto", new SubServiceDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("admin/addSubService", model);
    }


}
