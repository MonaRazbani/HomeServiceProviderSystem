package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.*;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.models.enums.OrderStatus;
import ir.maktab.models.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {
    OrderService orderService;
    ExpertDto expertDto;
    CustomerDto customerDto;
    OfferDto offerDto;
    OrderDto orderDto;
    AddressDto addressDto;
    ServiceCategoryDto serviceCategoryDto;
    SubServiceDto subServiceDto;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        orderService = context.getBean(OrderService.class);
        expertDto = ExpertDto.builder().
                firstName("ali").
                lastName("hashemi").
                email("ali@gmail.com").
                roleType(RoleType.EXPERT).
                build();
        customerDto = CustomerDto.builder().
                firstName("mona").
                lastName("raz").
                email("mona@gmail.com").
                roleType(RoleType.CUSTOMER).
                build();
        addressDto = AddressDto.builder().
                city("tabriz").
                alley("saba").
                street("azadi").
                houseNumber("1").
                HouseUnitNumber("1").
                floorNumber("1").build();
        serviceCategoryDto = new ServiceCategoryDto();
        serviceCategoryDto.setName("cleaning");

        subServiceDto = new SubServiceDto();
        subServiceDto.setName("house cleaning");
        subServiceDto.setExplanation("cleaning the house");
        subServiceDto.setServiceCategory(serviceCategoryDto);

        orderDto = OrderDto.builder().expert(expertDto).
                customer(customerDto).
                address(addressDto).
                subService(subServiceDto).
                explanation("xxxxxx").
                status(OrderStatus.STARTED).
                build();
    }
    @Test
    void orderService_CallsSaveOrder_CheckWithFindByEmail_ResponseTrue(){
        orderService.saveOrder(customerDto,100000,"xxxxxx",addressDto,subServiceDto);

    }
}
