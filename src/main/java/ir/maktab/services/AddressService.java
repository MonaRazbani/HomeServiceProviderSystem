package ir.maktab.services;

import ir.maktab.dao.AddressDao;
import ir.maktab.dto.mappingMethod.MapperObject;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.models.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AddressService {
    private final AddressDao addressDao ;
    private final MapperObject mapperObject;
    @Autowired
    public AddressService(AddressDao addressDao, MapperObject mapperObject) {
        this.addressDao = addressDao;
        this.mapperObject = mapperObject;
    }

    public  Address saveNewAddressToDB (AddressDto addressDto){
        Address address = mapperObject.addressDtoMapToAddress(addressDto);
            addressDao.save(address);
            return address;
    }

}
