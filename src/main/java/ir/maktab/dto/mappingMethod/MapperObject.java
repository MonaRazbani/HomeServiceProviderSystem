package ir.maktab.dto.mappingMethod;

import ir.maktab.dto.modelDtos.*;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.models.entities.*;
import ir.maktab.models.entities.roles.Customer;
import ir.maktab.models.entities.roles.Expert;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class MapperObject {
    private final ModelMapper modelMapper;
    public final static long fixedIdentificationCode =1000000;

    public MapperObject(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


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
        ServiceCategory serviceCategory = serviceCategoryDtoMapToServiceCategory(subServiceDto.getServiceCategory());
        subService.setServiceCategory(serviceCategory);
        return subService;
    }

    public SubServiceDto subServiceMapToSubServiceDto(SubService subService){
        SubServiceDto subServiceDto = modelMapper.map(subService, SubServiceDto.class);
        ServiceCategoryDto serviceCategoryDto = serviceCategoryMapToServiceCategoryDto(subService.getServiceCategory());
        subServiceDto.setServiceCategory(serviceCategoryDto);
        return subServiceDto;
    }

    public Address addressDtoMapToAddress(AddressDto addressDto){
        return modelMapper.map(addressDto,Address.class);
    }

    public  AddressDto addressMapToAddressDto(Address address){
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto ;
    }

    public Order orderDtoMapToOrder(OrderDto orderDto){
        Order order = modelMapper.map(orderDto,Order.class);
        Customer customer = customerDtoMapToCustomer(orderDto.getCustomer());
        Address address = addressDtoMapToAddress(orderDto.getAddress());
        Expert expert = expertDtoMapToExpert(orderDto.getExpert());
        Comment comment = commentDtoMapToComment(orderDto.getComment());
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
        orderDto.setCustomer(customerDto);
        orderDto.setAddress(addressDto);
        orderDto.setExpert(expertDto);
        orderDto.setComment(commentDto);
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
