package ir.maktab.services;

import ir.maktab.dao.AddressDao;
import ir.maktab.dao.OrderDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidSuggestedPrice;
import ir.maktab.exceptions.OrderNotFound;
import ir.maktab.exceptions.SubServiceNotFound;
import ir.maktab.models.entities.Address;
import ir.maktab.models.entities.Order;
import ir.maktab.models.entities.SubService;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.models.enums.OrderStatus;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final ControlInput controlInput;
    private final ControlEdition controlEdition;
    private final OrderDao orderDao;
    private final AddressDao addressDao;
    private final SubServiceDao subServiceDao;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition,
                        OrderDao orderDao, AddressDao addressDao, SubServiceDao subServiceDao) {
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
        this.orderDao = orderDao;
        this.addressDao = addressDao;
        this.subServiceDao = subServiceDao;
    }


    public Order saveOrder(CustomerDto customerDto, double suggestedPrice, String explanation, AddressDto addressDto, SubServiceDto subServiceDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        Customer customer = modelMapper.map(customerDto, Customer.class);
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        if (controlInput.isValidSuggestedPrice(subService, suggestedPrice)) {
            Order newOrder = new Order();
            newOrder.setCustomer(customer);
            newOrder.setExplanation(explanation);
            newOrder.setSuggestedPrice(suggestedPrice);
            newOrder.setSubService(subService);
            newOrder.setStatus(OrderStatus.STARTED);
            newOrder.setAddress(address);
            return orderDao.save(newOrder);

        } else
            throw new InvalidSuggestedPrice();
    }

    public void editOrderExplanation(OrderDto orderDto, String newExplanation) {
        orderDto.setExplanation(newExplanation);
        updateOrder(orderDto);
    }

    public AddressDto editOrderAddress(OrderDto orderDto, AddressDto newAddressDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {

            newAddressDto.setIdentificationCode(orderDto.getAddress().getIdentificationCode()
            );
            Address newAddress = modelMapper.map(newAddressDto, Address.class);

            Address addressChanged = addressDao.save(newAddress);
            return modelMapper.map(addressChanged, AddressDto.class);
        } else
            throw new RuntimeException("edit Order Address Fail");
    }

    public void editOrderSuggestedPrice(OrderDto orderDto, double suggestedPrice) {
        orderDto.setSuggestedPrice(suggestedPrice);
        updateOrder(orderDto);
    }

    public void editOrderServiceType(OrderDto orderDto, SubServiceDto subServiceDto) {
        orderDto.setSubService(subServiceDto);
        updateOrder(orderDto);
    }

    public void deleteOrder(OrderDto orderDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {
            Order order = modelMapper.map(orderDto, Order.class);
            orderDao.delete(order);
        } else
            throw new RuntimeException("delete Order fail");
    }

    public List<Order> findOrderByCustomerAndStatus(CustomerDto customerDto, OrderStatus status) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        List<Order> orders = orderDao.findByCustomerAndStatus(customer, status);
        if (!orders.isEmpty())
            return orders;
        else
            throw new OrderNotFound();
    }

    public List<Order> findOrderByExpertAndStatus(ExpertDto expertDto, OrderStatus status) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        List<Order> orders = orderDao.findByExpertAndStatus(expert, status);
        if (!orders.isEmpty())
            return orders;
        else throw new OrderNotFound();
    }


    public OrderDto findOrderDtoById(long id) {
        Optional<Order> order = orderDao.findById(id);
        if (order.isPresent()) {
            Order orderFound = order.get();
            OrderDto orderDto = modelMapper.map(orderFound, OrderDto.class);
            return orderDto;
        } else
            throw new OrderNotFound();
    }

    public Order findOrderById(long id) {
        Optional<Order> order = orderDao.findById(id);
        if (order.isPresent())
            return order.get();
        else
            throw new OrderNotFound();
    }

    public void updateOrder(OrderDto orderDto) {
        Order order = findOrderById(orderDto.getId());
        if (controlEdition.isValidToEdit(order.getStatus())) {
            orderDao.save(order);
        } else
            throw new EditionDenied();
    }

    public void setOrderDtoStatusDone(OrderDto orderDto) {
        Order order = findOrderById(orderDto.getId());
        if (controlEdition.isValidToEdit(order.getStatus())) {
            order.setStatus(OrderStatus.DONE);
            orderDao.save(order);
        } else
            throw new EditionDenied();
    }

}
