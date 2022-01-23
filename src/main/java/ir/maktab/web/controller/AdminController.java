package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.filterDto.UserCategoryDto;
import ir.maktab.dto.modelDtos.AdminDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.dto.modelDtos.roles.UserDto;
import ir.maktab.exceptions.AdminNotFound;
import ir.maktab.exceptions.BadFilterSearching;
import ir.maktab.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"adminDto"})
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;


    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("admin/login", "adminDto", new AdminDto());
    }

    @PostMapping("/submitLogin")
    public String loginCustomer(@ModelAttribute("adminDto") @Validated AdminDto adminDto,
                                Model model) {
        adminService.loginAdmin(adminDto);
        return "admin/dashboard";
    }


    @GetMapping("searchUser")
    public ModelAndView showSearchUserPage(ModelAndView modelAndView){
       return new ModelAndView("admin/searchUser","userCategoryDto", new UserCategoryDto());
    }

    @PostMapping("searchUserProcess")
    public ModelAndView searchUser(@ModelAttribute("userCategoryDto") UserCategoryDto userCategoryDto, ModelAndView modelAndView){
        List<UserDto> userDtoList = adminService.UserDynamicSearch(userCategoryDto);
        modelAndView.addObject("userDtoList",userDtoList);
        modelAndView.setViewName("admin/searchUser");
        return modelAndView;
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

}
