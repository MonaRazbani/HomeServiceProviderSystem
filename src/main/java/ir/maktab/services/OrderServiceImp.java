package ir.maktab.services;

import ir.maktab.data.dao.OrderDao;
import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidSuggestedPrice;
import ir.maktab.exceptions.OrderNotFound;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.data.models.enums.OrderStatus;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{
    private final ControlInput controlInput;
    private final ControlEdition controlEdition;
    private final OrderDao orderDao;
    private final AddressServiceImp addressServiceImp;
    private final CustomerServiceImp customerServiceImp;
    private final ExpertServiceImp expertService;
    private final ModelMapper modelMapper;

    @Override
    public Order saveOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        if (controlInput.isValidSuggestedPrice(order.getSubService(), order.getSuggestedPrice())) {
            order.setIdentificationCode(UUID.randomUUID());
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
            addressServiceImp.updateAddress(newAddressDto);

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
    public List<Order> findOrderByCustomerAndStatus(CustomerDto customerDto, OrderStatus status) {

        Customer customer = customerServiceImp.findCustomerByEmail(customerDto.getEmail());
        List<Order> orders = orderDao.findByCustomerAndStatus(customer, status);
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
        if (order.isPresent())
            return order.get();
        else
            throw new OrderNotFound();
    }

    @Override
    public long findOrderId (UUID identificationCode) {
        Order order = findOrderByIdentificationCode(identificationCode);
        return order.getId();
    }

    @Override
    public void updateOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto,Order.class);

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
            order.setId(orderId);
            orderDao.save(order);
        } else
            throw new EditionDenied();
    }

    @Override
    public void setOrderDtoStatusDone(OrderDto orderDto) {
        Order order = findOrderByIdentificationCode(orderDto.getIdentificationCode());
        if (controlEdition.isValidToEdit(order.getStatus())) {
            order.setStatus(OrderStatus.DONE);
            orderDao.save(order);
        } else
            throw new EditionDenied();
    }

}
