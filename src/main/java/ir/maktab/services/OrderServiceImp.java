package ir.maktab.services;

import ir.maktab.data.dao.OrderDao;
import ir.maktab.data.models.entities.Comment;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.data.models.enums.TransactionStatus;
import ir.maktab.data.models.enums.TransactionType;
import ir.maktab.dto.mapper.OrderMapper;
import ir.maktab.dto.modelDtos.*;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidSuggestedPrice;
import ir.maktab.exceptions.OrderNotFound;
import ir.maktab.exceptions.PaymentFail;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final ControlInput controlInput;
    private final ControlEdition controlEdition;
    private final OrderDao orderDao;
    private final AddressService addressService;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final SubServiceService subServiceService;
    private final ModelMapper modelMapper;
    private final OrderMapper orderMapper;
    private final CommentService commentService;
    private final TransactionService transactionService;


    @Override
    public Order saveOrder(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        Customer customer = customerService.findCustomerByEmail(order.getCustomer().getEmail());
        SubService subService = subServiceService.findByName(order.getSubService().getName());

        if (controlInput.isValidSuggestedPrice(order.getSubService(), order.getSuggestedPrice())) {

            addressService.saveAddress(orderDto.getAddress());
            order.setIdentificationCode(UUID.randomUUID());
            order.setCustomer(customer);
            order.setSubService(subService);
            order.setStatus(OrderStatus.WAITING_FOR_CHOOSING_EXPERT);

            return orderDao.save(order);

        } else
            throw new InvalidSuggestedPrice();
    }

    @Override
    public void editOrderExplanation(OrderDto orderDto, String newExplanation) {
        orderDto.setExplanation(newExplanation);
        updateOrder(orderDto);
    }

    @Override
    public void editOrderAddress(OrderDto orderDto, AddressDto newAddressDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {

            newAddressDto.setIdentificationCode(orderDto.getAddress().getIdentificationCode());
            addressService.updateAddress(newAddressDto);

        } else
            throw new RuntimeException("edit Order Address Fail");
    }

    @Override
    public void editOrderSuggestedPrice(OrderDto orderDto, double suggestedPrice) {
        orderDto.setSuggestedPrice(suggestedPrice);
        updateOrder(orderDto);
    }

    @Override
    public void editOrderServiceType(OrderDto orderDto, SubServiceDto subServiceDto) {
        orderDto.setSubService(subServiceDto);
        updateOrder(orderDto);
    }

    @Override
    public void deleteOrder(OrderDto orderDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {

            Order order = modelMapper.map(orderDto, Order.class);
            long orderId = findOrderId(orderDto.getIdentificationCode());
            order.setId(orderId);

            orderDao.delete(order);
        } else
            throw new RuntimeException("delete Order fail");
    }

    @Override
    public List<Order> findOrderByCustomer(CustomerDto customerDto) {

        Customer customer = customerService.findCustomerByEmail(customerDto.getEmail());
        List<Order> orders = orderDao.findByCustomer(customer);
        if (!orders.isEmpty())
            return orders;
        else
            throw new OrderNotFound();
    }

    @Override
    public List<Order> findOrderByExpertAndStatus(ExpertDto expertDto, OrderStatus status) {

        Expert expert = expertService.findExpertByEmail(expertDto.getEmail());
        List<Order> orders = orderDao.findByExpertAndStatus(expert, status);
        if (!orders.isEmpty())
            return orders;
        else throw new OrderNotFound();
    }

    @Override
    public Order findOrderById(long id) {
        Optional<Order> order = orderDao.findById(id);
        if (order.isPresent())
            return order.get();
        else
            throw new OrderNotFound();
    }

    @Override
    public Order findOrderByIdentificationCode(UUID identificationCode) {
        Optional<Order> order = orderDao.findByIdentificationCode(identificationCode);

        if (order.isEmpty())
            throw new OrderNotFound();

        return order.get();
    }

    @Override
    public OrderDto findOrderDtoByIdentificationCode(UUID identificationCode) {
        Optional<Order> order = orderDao.findByIdentificationCode(identificationCode);
        if (order.isPresent())
            return orderMapper.toOrderDto(order.get());
        else throw new OrderNotFound();
    }

    @Override
    public long findOrderId(UUID identificationCode) {
        Order order = findOrderByIdentificationCode(identificationCode);
        return order.getId();
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        if (controlEdition.isValidToEdit(order.getStatus())) {
            long orderId = findOrderId(order.getIdentificationCode());
            order.setId(orderId);
            orderDao.save(order);
        } else
            throw new EditionDenied();
    }

    @Override
    public void updateOrder(Order order) {

        if (controlEdition.isValidToEdit(order.getStatus())) {
            long orderId = findOrderId(order.getIdentificationCode());
            order.setCustomer(customerService.findCustomerByEmail(order.getCustomer().getEmail()));
            order.setId(orderId);
            orderDao.save(order);
        } else
            throw new EditionDenied();
    }


    @Override
    public List<OrderDto> findOrderByStatusAndSubService(OrderStatus orderStatus, SubService subService) {

        List<Order> orders = orderDao.findByStatusAndSubService(orderStatus, subService);

        if (orders.isEmpty())
            throw new OrderNotFound();

        return orders
                .stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findOrderForExpertBasedOnSubService(ExpertDto expertDto) {
        List<SubService> subServiceByExpert = subServiceService.findSubServiceByExpert(expertDto);
        List<OrderDto> orderList = new ArrayList<>();
        for (SubService subService : subServiceByExpert) {

            List<OrderDto> orderByStatusAndSubService =
                    findOrderByStatusAndSubService(OrderStatus.WAITING_FOR_CHOOSING_EXPERT, subService);
            orderList.addAll(orderByStatusAndSubService);
        }
        return orderList;
    }

    @Override
    public void changeOrderStatus(Order order, OrderStatus orderStatus) {
        order.setStatus(orderStatus);
        if (orderStatus == OrderStatus.DONE)
            order.setPerformedOrder(new java.util.Date());
        orderDao.save(order);
    }

    @Override
    public void setCommentForOrder(OrderDto orderDto, CommentDto commentDto) {
        Order order = findOrderByIdentificationCode(orderDto.getIdentificationCode());
        Comment comment = commentService.saveComment(commentDto);
        order.setComment(comment);
        orderDao.save(order);
    }

    @Override
    public void paymentWithCredit(TransactionDto transactionDto, OrderDto orderDto) {

        Order order = findOrderByIdentificationCode(orderDto.getIdentificationCode());
        order.getCustomer().setCredit(order.getCustomer().getCredit() - transactionDto.getPrice());
        Customer updateCustomer = customerService.updateCustomer(order.getCustomer());

        if (updateCustomer.getCredit() == orderDto.getCustomer().getCredit() - transactionDto.getPrice()) {

            transactionDto.setStatus(TransactionStatus.SUCCESSFUL);

            changeOrderStatus(order, OrderStatus.PAID);
            transactionService.save(transactionDto, order);

            TransactionDto depositTransaction = TransactionDto.builder()
                    .order(orderDto)
                    .price(transactionDto.getPrice() * 0.7)
                    .type(TransactionType.DEPOSIT)
                    .build();

            order.getExpert().setCredit(order.getExpert().getCredit() + depositTransaction.getPrice());
            Expert updateExpert = expertService.updateExpert(order.getExpert());

            if (updateExpert.getCredit() == orderDto.getExpert().getCredit() + depositTransaction.getPrice()) {

                depositTransaction.setStatus(TransactionStatus.SUCCESSFUL);
                transactionService.save(depositTransaction, order);
            }

        } else {
            transactionDto.setStatus(TransactionStatus.FAIL);
            transactionService.save(transactionDto, order);
            throw new PaymentFail();
        }
    }

}
