//package dtoAndEntity;
//
//import ir.maktab.config.SpringConfig;
//import ir.maktab.dao.CommentDao;
//import ir.maktab.dto.mappingMethod.MapperObject;
//import ir.maktab.dto.modelDtos.AddressDto;
//import ir.maktab.dto.modelDtos.CommentDto;
//import ir.maktab.models.entities.*;
//import ir.maktab.models.entities.roles.Customer;
//import ir.maktab.models.entities.roles.Expert;
//import ir.maktab.models.enums.Gender;
//import ir.maktab.models.enums.OrderStatus;
//import ir.maktab.models.enums.RoleType;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import ir.maktab.services.CommentService;
//
//public class ConversionCommentDtoTest {
//    MapperObject mapperObject;
//    Customer customer;
//    Expert expert;
//    Address address;
//    ServiceCategory serviceCategory;
//    SubService subService;
//    Order order;
//    Comment comment;
//
//
//    @BeforeEach
//    void init() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        mapperObject = context.getBean(MapperObject.class);
//        customer = Customer.builder().
//                firstName("mona").
//                lastName("raz").
//                gender(Gender.FEMALE).
//                email("mona@gmail.com").
//                password("123456mona").
//                roleType(RoleType.CUSTOMER).
//                build();
//        expert = Expert.builder().
//                firstName("ali").
//                lastName("hashemi").
//                gender(Gender.MALE).
//                email("ali@gmail.com").
//                password("123456ali").
//                roleType(RoleType.EXPERT).
//                build();
//        address = Address.builder().
//                city("tabriz").
//                alley("saba").
//                street("azadi").
//                houseNumber("1").
//                HouseUnitNumber("1").
//                floorNumber("1").
//                id(1L).build();
//        serviceCategory = new ServiceCategory();
//        serviceCategory.setName("cleaning");
//        serviceCategory.setId(1);
//        subService = new SubService();
//        subService.setName("house cleaning");
//        subService.setExplanation("cleaning the house");
//        subService.setId(1);
//        subService.setServiceCategory(serviceCategory);
//
//        order = Order.builder().expert(expert).
//                customer(customer).
//                address(address).
//                subService(subService).
//                explanation("xxxxxx").
//                status(OrderStatus.STARTED).
//                build();
//        comment = new Comment();
//        comment.setComment("good");
//        comment.setRate(4.5);
//        comment.setOrder(order);
//    }
//
//    @Test
//    public void whenConvertCommentEntityToCommentDto_thenCorrect() {
//
//        CommentDto commentDto = mapperObject.commentMapToCommentDto(comment);
//
//        Assertions.assertEquals(comment.);
//        Assertions.assertEquals(address.getStreet(), addressDto.getStreet());
//        Assertions.assertEquals(address.getAlley(), addressDto.getAlley());
//        Assertions.assertEquals(address.getFloorNumber(), addressDto.getFloorNumber());
//        Assertions.assertEquals(address.getHouseUnitNumber(), addressDto.getHouseUnitNumber());
//        Assertions.assertEquals(address.getHouseNumber(), addressDto.getHouseNumber());
//
//    }
//
//    @Test
//    public void whenConvertAddressDtoToAddressEntity_thenCorrect() {
//        AddressDto addressDto = new AddressDto();
//        addressDto.setCity("tabriz");
//        addressDto.setStreet("azadi");
//        addressDto.setAlley("saba");
//        addressDto.setFloorNumber("1");
//        addressDto.setHouseNumber("1");
//        addressDto.setHouseUnitNumber("1");
//
//        Address address = mapperObject.addressDtoMapToAddress(addressDto);
//
//        Assertions.assertEquals(addressDto.getCity(), address.getCity());
//        Assertions.assertEquals(addressDto.getStreet(), address.getStreet());
//        Assertions.assertEquals(addressDto.getAlley(), address.getAlley());
//        Assertions.assertEquals(addressDto.getFloorNumber(), address.getFloorNumber());
//    }
//}
