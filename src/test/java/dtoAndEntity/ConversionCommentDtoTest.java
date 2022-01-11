package dtoAndEntity;

import config.SpringConfig;
import dto.modelDtos.AddressDto;
import models.entities.Address;
import models.entities.Comment;
import models.entities.roles.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CommentService;

public class ConversionCommentDtoTest {
    CommentService commentService;
    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
         commentService= context.getBean(CommentService.class);
         new Customer()
        Comment comment = new Comment();
        comment.setComment("good");
        comment.setRate(4.5);
        comment.setComment();
    }

    @Test
    public void whenConvertCommentEntityToCommentDto_thenCorrect() {


        AddressDto addressDto = modelMapper.map(address, AddressDto.class);

        Assertions.assertEquals(address.getCity(), addressDto.getCity());
        Assertions.assertEquals(address.getStreet(), addressDto.getStreet());
        Assertions.assertEquals(address.getAlley(), addressDto.getAlley());
        Assertions.assertEquals(address.getFloorNumber(), addressDto.getFloorNumber());
        Assertions.assertEquals(address.getHouseUnitNumber(), addressDto.getHouseUnitNumber());
        Assertions.assertEquals(address.getHouseNumber(), addressDto.getHouseNumber());

    }

    @Test
    public void whenConvertAddressDtoToAddressEntity_thenCorrect() {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("tabriz");
        addressDto.setStreet("azadi");
        addressDto.setAlley("saba");
        addressDto.setFloorNumber("1");
        addressDto.setHouseNumber("1");
        addressDto.setHouseUnitNumber("1");

        Address address = modelMapper.map(addressDto, Address.class);

        Assertions.assertEquals(addressDto.getCity(), address.getCity());
        Assertions.assertEquals(addressDto.getStreet(), address.getStreet());
        Assertions.assertEquals(addressDto.getAlley(), address.getAlley());
        Assertions.assertEquals(addressDto.getFloorNumber(), address.getFloorNumber());
    }
}
