package ir.maktab.services;

import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidSuggestedPrice;
import ir.maktab.exceptions.OrderNotFound;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order saveOrder(OrderDto orderDto);

    void editOrderExplanation(OrderDto orderDto, String newExplanation);

    void editOrderAddress(OrderDto orderDto, AddressDto newAddressDto);

    void editOrderSuggestedPrice(OrderDto orderDto, double suggestedPrice);

    void editOrderServiceType(OrderDto orderDto, SubServiceDto subServiceDto);

    void deleteOrder(OrderDto orderDto);

    List<Order> findOrderByCustomerAndStatus(CustomerDto customerDto, OrderStatus status);

    List<Order> findOrderByExpertAndStatus(ExpertDto expertDto, OrderStatus status);

    Order findOrderById(long id);

    Order findOrderByIdentificationCode(UUID identificationCode);

    long findOrderId (UUID identificationCode);

    void updateOrder(OrderDto orderDto);

    void updateOrder(Order order);

    void setOrderDtoStatusDone(OrderDto orderDto);

    List<OrderDto> findOrderByStatus(OrderStatus orderStatus);

}
