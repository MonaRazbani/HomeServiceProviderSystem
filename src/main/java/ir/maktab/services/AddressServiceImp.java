package ir.maktab.services;

import ir.maktab.data.dao.AddressDao;
import ir.maktab.data.models.entities.Address;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.exceptions.AddressNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service

public class AddressServiceImp implements AddressService {
    private final AddressDao addressDao;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressServiceImp(AddressDao addressDao, ModelMapper modelMapper) {
        this.addressDao = addressDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        address.setIdentificationCode(UUID.randomUUID());
        Address save = addressDao.save(address);
        return modelMapper.map(save, AddressDto.class);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {

        Address address = modelMapper.map(addressDto, Address.class);
        long addressId = findAddressId(addressDto.getIdentificationCode());
        address.setId(addressId);
        Address save = addressDao.save(address);
        return modelMapper.map(save, AddressDto.class);
    }

    @Override
    public Address findAddressById(long id) {
        Optional<Address> address = addressDao.findById(id);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFound();
    }

    @Override
    public Address findAddressByIdentificationCode(UUID identificationCode) {
        Optional<Address> address = addressDao.findByIdentificationCode(identificationCode);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFound();
    }

    @Override
    public long findAddressId(UUID identificationCode) {
        Address address = findAddressByIdentificationCode(identificationCode);
        return address.getId();
    }
}
