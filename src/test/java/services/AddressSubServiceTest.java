package services;

import config.SpringConfig;
import dto.modelDtos.AddressDto;
import models.entities.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AddressSubServiceTest {
    AddressService addressService;
    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        addressService = context.getBean(AddressService.class);
    }

    @Test
    public void whenConvertAddressEntityToAddressDto_thenCorrect() {
        Address address = new Address();
        address.setId(1L);
        address.setCity("tabriz");
        address.setStreet("azadi");
        address.setAlley("saba");
        address.setFloorNumber("1");
        address.setHouseNumber("1");
        address.setHouseUnitNumber("1");


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