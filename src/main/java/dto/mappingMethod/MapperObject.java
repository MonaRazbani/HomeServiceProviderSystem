package dto.mappingMethod;

import dto.modelDtos.*;
import dto.modelDtos.roles.CustomerDto;
import dto.modelDtos.roles.ExpertDto;
import models.entities.*;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import org.modelmapper.ModelMapper;

import java.util.Date;


public class MapperObject {
    private static final ModelMapper modelMapper = new ModelMapper();

    public Customer customerDtoMapToCustomer(CustomerDto customerDto){
        return modelMapper.map(customerDto,Customer.class);
    }

    public CustomerDto customerMapToCustomerDto(Customer customer){
        return modelMapper.map(customer,CustomerDto.class);
    }

    public Expert expertDtoMapToExpert(ExpertDto expertDto){
        return modelMapper.map(expertDto,Expert.class);
    }

    public ExpertDto expertMapToExpertDto(Expert expert){
        return modelMapper.map(expert,ExpertDto.class);
    }

    public Comment commentDtoMapToComment(CommentDto commentDto){
        return modelMapper.map(commentDto,Comment.class);
    }

    public CommentDto commentMapToCommentDto(Comment comment){
        return modelMapper.map(comment,CommentDto.class);
    }
    public ServiceCategory serviceCategoryDtoMapToServiceCategory(ServiceCategoryDto serviceCategoryDto){
        return modelMapper.map(serviceCategoryDto,ServiceCategory.class);
    }

    public ServiceCategoryDto serviceCategoryMapToServiceCategoryDto(ServiceCategory serviceCategory){
        return modelMapper.map(serviceCategory,ServiceCategoryDto.class);
    }
    public SubService subServiceDtoMapToSubService(SubServiceDto subServiceDto){
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        ServiceCategory serviceCategory = serviceCategoryDtoMapToServiceCategory(subServiceDto.getServiceCategoryDto());
        subService.setServiceCategory(serviceCategory);
        return subService;
    }

    public SubServiceDto subServiceMapToSubServiceDto(SubService subService){
        SubServiceDto subServiceDto = modelMapper.map(subService, SubServiceDto.class);
        ServiceCategoryDto serviceCategoryDto = serviceCategoryMapToServiceCategoryDto(subService.getServiceCategory());
        subServiceDto.setServiceCategoryDto(serviceCategoryDto);
        return subServiceDto;
    }

    public Address addressDtoMapToAddress(AddressDto addressDto){
        return modelMapper.map(addressDto,Address.class);
    }

    public  AddressDto addressMapToAddressDto(Address address){
        return modelMapper.map(address,AddressDto.class);
    }

    public Order orderDtoMapToOrder(OrderDto orderDto){
        Order order = modelMapper.map(orderDto,Order.class);
        Customer customer = customerDtoMapToCustomer(orderDto.getCustomerDto());
        Address address = addressDtoMapToAddress(orderDto.getAddressDto());
        Expert expert = expertDtoMapToExpert(orderDto.getExpertDto());
        Comment comment = commentDtoMapToComment(orderDto.getCommentDto());
        order.setCustomer(customer);
        order.setAddress(address);
        order.setExpert(expert);
        order.setComment(comment);
        return order;
    }

    public OrderDto orderMapToOrderDto(Order order){
        OrderDto orderDto = modelMapper.map(order,OrderDto.class);
        CustomerDto customerDto = customerMapToCustomerDto(order.getCustomer());
        AddressDto addressDto = addressMapToAddressDto(order.getAddress());
        ExpertDto expertDto = expertMapToExpertDto(order.getExpert());
        CommentDto commentDto = commentMapToCommentDto(order.getComment());
        orderDto.setCustomerDto(customerDto);
        orderDto.setAddressDto(addressDto);
        orderDto.setExpertDto(expertDto);
        orderDto.setCommentDto(commentDto);
        return orderDto;
    }


    public Offer offerDtoMapToOffer(OfferDto offerDto){
        Offer offer = modelMapper.map(offerDto,Offer.class);
        Order order = orderDtoMapToOrder(offerDto.getOrder());
        Expert expert = expertDtoMapToExpert(offerDto.getExpert());
        offer.setOrder(order);
        offer.setExpert(expert);
        return offer;
    }


}
