package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address dtoCreateToEntity(BuyerDtoRequest request) {
        Address address = new Address();
        address.setCity(request.city());
        address.setStreet(request.street());
        address.setBuilding(request.building());
        address.setApartment(request.apartment());
        return address;
    }

    public Address dtoUpdateToEntity(BuyerDtoRequest request,
                                     Address addressToUpdate) {
        addressToUpdate.setCity(request.city());
        addressToUpdate.setStreet(request.street());
        addressToUpdate.setBuilding(request.building());
        addressToUpdate.setApartment(request.apartment());
        return addressToUpdate;
    }
}
