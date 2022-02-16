package ir.maktab.web.controller;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.models.enums.TransactionType;
import ir.maktab.dto.BankCardDto;
import ir.maktab.dto.mapper.SubServiceMapper;
import ir.maktab.dto.modelDtos.*;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.exceptions.CreditBalanceNotEnough;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/submitComment")
    public ModelAndView showSubmitCommentPage(@SessionAttribute("customerDto") CustomerDto customerDto,
                                              @SessionAttribute("orderDto") OrderDto orderDto) {
        if (customerDto == null && orderDto == null)
            throw new AccessDenied();

        return new ModelAndView("order/submitComment", "commentDto", new CommentDto());

    }

    @PostMapping("/submitComment")
    public String submitCommentProcess(@SessionAttribute("customerDto") CustomerDto customerDto,
                                       @SessionAttribute("orderDto") OrderDto orderDto,
                                       @ModelAttribute("commentDto") CommentDto commentDto,
                                       HttpSession httpSession) {

        if (customerDto == null && orderDto == null)
            throw new AccessDenied();
        orderService.setCommentForOrder(orderDto, commentDto);
        httpSession.removeAttribute("orderDto");

        return "/customer/dashboard";
    }


    @GetMapping("/payOrder")

    public String showPayOrderPage(@SessionAttribute("customerDto") CustomerDto customerDto,
                                   @SessionAttribute("orderDto") OrderDto orderDto,
                                   @SessionAttribute("acceptedOfferOfOrder") OfferDto acceptedOfferOfOrder) {

        if (customerDto == null && orderDto == null && acceptedOfferOfOrder == null)
            throw new AccessDenied();


        return "/order/payOrder";
    }

    @GetMapping("/paymentWithBankCard")
    private ModelAndView showPaymentWithBankCart(HttpSession httpSession) {

        CustomerDto customerDto = (CustomerDto) httpSession.getAttribute("customerDto");
        OrderDto orderDto = (OrderDto) httpSession.getAttribute("orderDto");
        OfferDto acceptedOfferOfOrder = (OfferDto) httpSession.getAttribute("acceptedOfferOfOrder");

        if (customerDto == null && orderDto == null && acceptedOfferOfOrder == null)
            throw new AccessDenied();

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOrder(orderDto);
        transactionDto.setPrice(Double.parseDouble(acceptedOfferOfOrder.getSuggestedPrice()));
        transactionDto.setType(TransactionType.WITHDRAW_BY_BANK_CARD);

        httpSession.setAttribute("transactionDto", transactionDto);

        return new ModelAndView("/order/paymentWithBankCard", "bankCartDto", new BankCardDto());
    }

    @PostMapping("/paymentWithBankCard")
    private ModelAndView paymentWithBankCart(@SessionAttribute("customerDto") CustomerDto customerDto,
                                             @SessionAttribute("orderDto") OrderDto orderDto,
                                             @ModelAttribute("bankCardDto") BankCardDto bankCardDto) {

        if (customerDto != null && orderDto != null)
            throw new AccessDenied();
        //todo
        return null;
    }

    @GetMapping("/paymentWithCredit")
    private String showPaymentWithCredit(HttpSession httpSession) {

        CustomerDto customerDto = (CustomerDto) httpSession.getAttribute("customerDto");
        OrderDto orderDto = (OrderDto) httpSession.getAttribute("orderDto");
        OfferDto acceptedOfferOfOrder = (OfferDto) httpSession.getAttribute("acceptedOfferOfOrder");

        if (customerDto == null && orderDto == null && acceptedOfferOfOrder == null)
            throw new AccessDenied();

        if (Double.parseDouble(acceptedOfferOfOrder.getSuggestedPrice()) > customerDto.getCredit())
            throw new CreditBalanceNotEnough();

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOrder(orderDto);
        transactionDto.setPrice(Double.parseDouble(acceptedOfferOfOrder.getSuggestedPrice()));
        transactionDto.setType(TransactionType.WITHDRAW_BY_CREDIT);

        orderService.paymentWithCredit(transactionDto,orderDto);

        httpSession.removeAttribute("orderDto");

        return "/customer/selectOrder";
    }

    @ExceptionHandler(value = OrderWithoutSubService.class)
    public String signupExceptionHandler(OrderWithoutSubService ex) {
        return "customer/listOfServiceCategory";
    }

    @ExceptionHandler(value = InvalidSuggestedPrice.class)
    public ModelAndView xxxx(InvalidSuggestedPrice ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, "error", ex.getMessage());
    }

}
