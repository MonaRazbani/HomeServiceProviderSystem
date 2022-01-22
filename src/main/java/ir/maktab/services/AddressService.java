package ir.maktab.services;

import ir.maktab.data.models.entities.Address;
import ir.maktab.dto.modelDtos.AddressDto;

import java.util.UUID;

public interface AddressService {
    AddressDto saveAddress(AddressDto addressDto);

    AddressDto updateAddress(AddressDto addressDto);

    Address findAddressById(long id);

    Address findAddressByIdentificationCode(UUID identificationCode);

    long findAddressId(UUID identificationCode);

}
