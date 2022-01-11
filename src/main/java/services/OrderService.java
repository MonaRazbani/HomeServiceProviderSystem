package services;

import dao.AddressDao;
import dao.OrderDao;
import dao.SubServiceDao;
import dto.modelDtos.AddressDto;
import dto.modelDtos.OrderDto;
import dto.modelDtos.SubServiceDto;
import dto.modelDtos.roles.CustomerDto;
import dto.modelDtos.roles.ExpertDto;
import exceptions.OrderNotFound;
import exceptions.SubServiceNotFound;
import models.entities.Address;
import models.entities.Order;
import models.entities.SubService;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.enums.OrderStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import validation.ControlEdition;
import validation.ControlInput;

import java.util.List;

@Service
public class OrderService {
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;
    private final OrderDao orderDao;
    private final AddressDao addressDao;
    private final SubServiceDao subServiceDao ;

    public OrderService( ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition, OrderDao orderDao,  AddressDao addressDao, SubServiceDao subServiceDao) {
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
        this.orderDao = orderDao;
        this.addressDao = addressDao;
        this.subServiceDao = subServiceDao;
    }


    public void saveNewOrder(CustomerDto customerDto, double suggestedPrice, String explanation, AddressDto addressDto, SubServiceDto subServiceDto) {
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
            orderDao.save(newOrder);
        }
        else
            throw new RuntimeException("saving order fail");
    }

    public void editOrderExplanation(OrderDto orderDto, String newExplanation) {
        Order order = modelMapper.map(orderDto,Order.class);
            if (controlEdition.isValidToEdit(order.getStatus())) {
                orderDto.setExplanation(newExplanation);
                orderDao.save(order);
            }else
                throw new RuntimeException("edit Order Explanation fail");
    }

    public void editOrderAddress(OrderDto orderDto, AddressDto newAddressDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())){
            newAddressDto.setId(orderDto.getAddressDto().getId());
            Address newAddress = modelMapper.map(newAddressDto,Address.class);
                addressDao.save(newAddress);
        }else
            throw new RuntimeException("edit Order Address Fail");
    }

    public void editOrderSuggestedPrice(OrderDto orderDto , double suggestedPrice) {
        Order order = modelMapper.map(orderDto,Order.class);
        if (controlEdition.isValidToEdit(order.getStatus())) {
            orderDto.setSuggestedPrice(suggestedPrice);
            orderDao.save(order);
        }else
            throw new RuntimeException("edit Order SuggestedPrice fail");
    }


    public void editOrderServiceType(OrderDto orderDto, SubServiceDto subServiceDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {
            Order order = modelMapper.map(orderDto, Order.class);
            SubService subService = findSubServiceBySubServiceDto(subServiceDto);
            if (subService != null) {
                order.setSubService(subService);
                orderDao.save(order);
            }
        }else
            throw new RuntimeException("edit Order subService fail");
    }

    public void deleteOrderFromCustomer(OrderDto orderDto) {
        if (controlEdition.isValidToEdit(orderDto.getStatus())) {
            Order order = modelMapper.map(orderDto, Order.class);
            orderDao.delete(order);
        }else
            throw new RuntimeException("delete Order fail");
    }

    public List<Order> findOrderByCustomerAndStatus(CustomerDto customerDto, OrderStatus status) {
        Customer customer = modelMapper.map(customerDto,Customer.class);
        List<Order> orders = orderDao.findByCustomerAndStatus(customer, status);
        if (!orders.isEmpty())
            return orders;
        else
            throw new OrderNotFound();
    }

    public List<Order> findOrderByExpertAndStatus(ExpertDto expertDto, OrderStatus status) {
        Expert expert = modelMapper.map(expertDto,Expert.class);
        List<Order> orders = orderDao.findByExpertAndStatus(expert, status);
        if (!orders.isEmpty())
            return orders;
        else throw new OrderNotFound();
    }

    public SubService findSubServiceBySubServiceDto(SubServiceDto subServiceDto) {
        List<SubService> subServicesFound = subServiceDao.findByName(subServiceDto.getName());
        if (!subServicesFound.isEmpty()) {
            return subServicesFound.get(0);
        } else
            throw new SubServiceNotFound();
    }

}
