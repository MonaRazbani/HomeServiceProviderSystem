package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dto.mapper.SubServiceMapper;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.exceptions.InvalidSuggestedPrice;
import ir.maktab.exceptions.OrderWithoutSubService;
import ir.maktab.services.OrderService;
import ir.maktab.services.ServiceCategoryService;
import ir.maktab.services.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final SubServiceService subServiceService;
    private final ServiceCategoryService serviceCategoryService;
    private final OrderService orderService;


    @GetMapping("/listOfServiceCategory")
    public ModelAndView listOfServiceCategoryPage(HttpSession httpSession) {
        CustomerDto customerDto = (CustomerDto) httpSession.getAttribute("customerDto");
        if (customerDto == null)
            throw new AccessDenied();

        List<String> categoryServiceAll = serviceCategoryService.findAll().
                stream().
                map(ServiceCategoryDto::getName).
                collect(Collectors.toList());

        return new ModelAndView("order/selectServiceCategory", "categoryServiceAll", categoryServiceAll);
    }

    @GetMapping("/selectServiceCategory/{name}")
    public ModelAndView showSelectSubServicePage(HttpSession httpSession, @PathVariable String name) {
        if (httpSession.getAttribute("customerDto") == null)
            throw new AccessDenied();

        List<String> subServiceDtoList = subServiceService.findSubServicesOfServiceCategory(name).
                stream().
                map(SubServiceDto::getName).
                collect(Collectors.toList());

        return new ModelAndView("order/selectSubService", "subServiceDtoList", subServiceDtoList);
    }

    @PostMapping("/selectSubService")
    public ModelAndView selectSubServiceProcess(HttpSession httpSession,
                                                @RequestParam("subServiceName") String subServiceName) {
        if (httpSession.getAttribute("customerDto") == null)
            throw new AccessDenied();

        SubServiceDto orderSubServiceDto = SubServiceMapper.toSubServiceDto(subServiceService.findByName(subServiceName));
        httpSession.setAttribute("orderSubServiceDto", orderSubServiceDto);

        return new ModelAndView("order/submitOrder", "orderDto", new OrderDto());

    }

    @PostMapping("/submitOrder")
    public String showSubmitPage(@ModelAttribute("orderDto") OrderDto orderDto,
                                 @SessionAttribute("orderSubServiceDto") SubServiceDto orderSubServiceDto,
                                 @SessionAttribute("customerDto") CustomerDto customerDto) {

        if (customerDto == null)
            throw new AccessDenied();

        if (orderSubServiceDto == null)
            throw new OrderWithoutSubService();

        orderDto.setCustomer(customerDto);
        orderDto.setSubService(orderSubServiceDto);

        orderService.saveOrder(orderDto);

        return "order/showCustomerOrders";
    }

    @GetMapping("/payOrder")

    public ModelAndView showPayOrderPage (@SessionAttribute("customerDto")CustomerDto customerDto,
                                          @SessionAttribute("orderDto") OrderDto orderDto,
                                          @ModelAttribute("commentDto") CommentDto commentDto) {

        if (customerDto != null && orderDto != null)
            throw new AccessDenied();
return null;

    }

    @ExceptionHandler(value = OrderWithoutSubService.class)
    public String signupExceptionHandler(OrderWithoutSubService ex) {
        return "customer/listOfServiceCategory";
    }

    @ExceptionHandler(value = InvalidSuggestedPrice.class)
    public ModelAndView bindExceptionHandler(InvalidSuggestedPrice ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, "error", ex.getMessage());
    }

}
