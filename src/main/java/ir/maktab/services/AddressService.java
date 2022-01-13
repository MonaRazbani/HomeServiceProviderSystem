package ir.maktab.services;

import ir.maktab.dao.AddressDao;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.exceptions.AddressNotFound;
import ir.maktab.models.entities.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AddressService {
    private final AddressDao addressDao;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressService(AddressDao addressDao, ModelMapper modelMapper) {
        this.addressDao = addressDao;

        this.modelMapper = modelMapper;
    }

    public Address saveAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        addressDao.save(address);
        return address;
    }

    public void updateAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        addressDao.save(address);
    }

    public Address findAddressById(long id) {
        Optional<Address> address = addressDao.findById(id);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFound();
    }
   public AddressDto findAddressDtoById(long id) {
        Optional<Address> address = addressDao.findById(id);
        if (address.isPresent())
            return modelMapper.map(address.get(),AddressDto.class);
        else
            throw new AddressNotFound();
    }
}
