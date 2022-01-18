package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.ServiceCategoryDto;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.models.entities.Order;
import ir.maktab.models.enums.OrderStatus;
import ir.maktab.models.enums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {
    OrderService orderService;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        orderService = context.getBean(OrderService.class);

    }

    @Test
    void orderService_CallsSaveOrder_ResponseTrue() {
        ExpertDto expertDto = ExpertDto.builder().
                firstName("ali").
                lastName("hashemi").
                email("ali@gmail.com").
                roleType(RoleType.EXPERT).
                id(2L).//based on database
                        build();
        CustomerDto customerDto = CustomerDto.builder().
                firstName("mona").
                lastName("raz").
                email("mona@gmail.com").
                roleType(RoleType.CUSTOMER).
                id(1L).//based on database
                        build();
        AddressDto addressDto = AddressDto.builder().
                city("tabriz").
                alley("saba").
                street("azadi").
                houseNumber("1").
                HouseUnitNumber("1").
                floorNumber("1").build();
        ServiceCategoryDto serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName("cleaning");
        serviceCategoryDto.setId(4L);//based on database

        SubServiceDto subServiceDto = new SubServiceDto();
        subServiceDto.setName("house cleaning");
        subServiceDto.setExplanation("cleaning the house");
        subServiceDto.setBaseCost(100000);
        subServiceDto.setServiceCategory(serviceCategoryDto);
        subServiceDto.setId(2L);//based on database

        OrderDto orderDto = OrderDto.builder().expert(expertDto).
                customer(customerDto).
                address(addressDto).
                subService(subServiceDto).
                explanation("xxxxxx").
                status(OrderStatus.STARTED).
                build();
        Order order = orderService.saveOrder(customerDto, 100000, "xxxxxx", addressDto, subServiceDto);
        Assertions.assertEquals(order.getExpert().getEmail(), orderDto.getExpert().getEmail());
        Assertions.assertEquals(order.getSuggestedPrice(), 100000);
        Assertions.assertEquals(order.getExplanation(), "xxxxxx");

    }

    @Test
    void orderService_CallsEditOrderExplanation_ResponseFalse() {
        String newExplanation = "mmmmmmmmmm"; //
        OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        orderService.editOrderExplanation(orderDtoById, newExplanation);
        OrderDto orderDtoChanged = orderService.findOrderDtoById(3);//value of order based on database

        Assertions.assertFalse(orderDtoById.getExplanation().equals(orderDtoChanged.getExplanation()));
    }

    @Test
    void orderService_CallsEditEditOrderAddress_ResponseFalse() {
        AddressDto newAddressDto = new AddressDto();//when hibernate.hbm2ddl.auto is 'update' change user's email to have correct test
        newAddressDto = AddressDto.builder().
                city("shiraz").
                alley("saba2").
                street("azadi2").
                houseNumber("1").
                HouseUnitNumber("1").
                floorNumber("1").build();

        OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        orderService.editOrderAddress(orderDtoById, newAddressDto);
        OrderDto orderDtoChanged = orderService.findOrderDtoById(3);//value of order based on database

        Assertions.assertFalse(orderDtoById.getAddress().equals(orderDtoChanged.getAddress()));
    }

    @Test
    void orderService_CallsEditOrderSuggestedPrice_ResponseFalse() {
        double newSuggestedPrice = 300000; //when hibernate.hbm2ddl.auto is 'update' change property to have correct test
        OrderDto orderDtoBeforeChange = orderService.findOrderDtoById(3);//value of order based on database
        orderService.editOrderSuggestedPrice(orderDtoBeforeChange, newSuggestedPrice);
        OrderDto orderDtoChanged = orderService.findOrderDtoById(3);//value of order based on database

        Assertions.assertFalse(orderDtoBeforeChange.getSuggestedPrice() == orderDtoChanged.getSuggestedPrice());
    }

    @Test
    void orderService_CallsEditOrderServiceType_ResponseFalse() {
        //when hibernate.hbm2ddl.auto is 'update' change property to have correct test

        ServiceCategoryDto serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName("cleaning");
        serviceCategoryDto.setId(4L);//based on database

        SubServiceDto newSubServiceDto = new SubServiceDto();
        newSubServiceDto.setName("wall cleaning");
        newSubServiceDto.setExplanation("cleaning the wall");
        newSubServiceDto.setBaseCost(10000);
        newSubServiceDto.setServiceCategory(serviceCategoryDto);
        newSubServiceDto.setId(3L);//based on database


        OrderDto orderDtoBeforeChange = orderService.findOrderDtoById(3);//value of order based on database
        orderService.editOrderServiceType(orderDtoBeforeChange, newSubServiceDto);
        OrderDto orderDtoChanged = orderService.findOrderDtoById(3);//value of order based on database

        Assertions.assertFalse(orderDtoBeforeChange.getSubService().equals(orderDtoChanged.getSubService()));
    }


    @Test
    void orderService_CallsEditEditOrderAddress_OrderDoneStatus_ReturnException() {
        AddressDto newAddressDto = AddressDto.builder().
                city("shiraz").
                alley("saba2").
                street("azadi2").
                houseNumber("1").
                HouseUnitNumber("1").
                floorNumber("1").build();
//use bellow when order status is not 'DONE'
      /*  OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        orderService.setOrderDtoStatusDone(orderDtoById);*/
        OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        EditionDenied result = Assertions.assertThrows(EditionDenied.class, () ->
                orderService.editOrderAddress(orderDtoById,newAddressDto));
        Assertions.assertEquals("edition denied", result.getMessage());

    }
    @Test
    void orderService_CallsEditOrderServiceType_OrderDoneStatus_ReturnException() {
        ServiceCategoryDto serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName("cleaning");
        serviceCategoryDto.setId(4L);//based on database

        SubServiceDto newSubServiceDto = new SubServiceDto();
        newSubServiceDto.setName("wall cleaning");
        newSubServiceDto.setExplanation("cleaning the wall");
        newSubServiceDto.setBaseCost(10000);
        newSubServiceDto.setServiceCategory(serviceCategoryDto);
        newSubServiceDto.setId(3L);//based on database

        OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        //use bellow when order status is not 'DONE'
      /*  OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        orderService.setOrderDtoStatusDone(orderDtoById);*/
        EditionDenied result = Assertions.assertThrows(EditionDenied.class, () ->
                orderService.editOrderServiceType(orderDtoById, newSubServiceDto));
        Assertions.assertEquals("edition denied", result.getMessage());

    }

    @Test
    void orderService_CallsEditOrderExplanation_OrderDoneStatus_ReturnException() {
        OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        orderService.setOrderDtoStatusDone(orderDtoById);
        EditionDenied result = Assertions.assertThrows(EditionDenied.class, () ->
                orderService.editOrderExplanation(orderDtoById, "123456789asdgh"));
        Assertions.assertEquals("edition denied", result.getMessage());
    }

    @Test
    void orderService_CallsEditOrderSuggestedPrice_OrderDoneStatus_ReturnException() {
        double newSuggestedPrice = 300000;
        OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        //use bellow when order status is not 'DONE'
      /*  OrderDto orderDtoById = orderService.findOrderDtoById(3);//value of order based on database
        orderService.setOrderDtoStatusDone(orderDtoById);*/
        EditionDenied result = Assertions.assertThrows(EditionDenied.class, () ->
                orderService.editOrderSuggestedPrice(orderDtoById,newSuggestedPrice));
        Assertions.assertEquals("edition denied", result.getMessage());
    }


}
