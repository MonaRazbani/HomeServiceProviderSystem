package ir.maktab.services;

import ir.maktab.dao.AddressDao;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.exceptions.AddressNotFound;
import ir.maktab.models.entities.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
        address.setIdentificationCode(UUID.randomUUID());

        addressDao.save(address);
        return address;
    }

    public void updateAddress(AddressDto addressDto) {

        Address address = modelMapper.map(addressDto, Address.class);
        long addressId = findAddressId(addressDto.getIdentificationCode());
        address.setId(addressId);

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
            return modelMapper.map(address.get(), AddressDto.class);
        else
            throw new AddressNotFound();
    }

    public Address findAddressByIdentificationCode(UUID identificationCode) {
        Optional<Address> address = addressDao.findByIdentificationCode(identificationCode);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFound();
    }

    public long findAddressId(UUID identificationCode){
        Address address = findAddressByIdentificationCode(identificationCode);
        return address.getId();
    }
}
