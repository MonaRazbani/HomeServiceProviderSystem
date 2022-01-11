package services;

import dao.AddressDao;
import dto.modelDtos.AddressDto;
import models.entities.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AddressService {
    private final AddressDao addressDao ;
    private final ModelMapper modelMapper;
    @Autowired
    public AddressService(AddressDao addressDao, ModelMapper modelMapper) {
        this.addressDao = addressDao;
        this.modelMapper = modelMapper;
    }

    public  Address saveNewAddressToDB (AddressDto addressDto){
        Address address = modelMapper.map(addressDto,Address.class);
            addressDao.save(address);
            return address;
    }
}
