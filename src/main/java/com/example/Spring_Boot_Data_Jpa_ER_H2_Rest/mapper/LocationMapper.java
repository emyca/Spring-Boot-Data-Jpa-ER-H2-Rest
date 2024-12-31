package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public Location dtoCreateToEntity(CustomerDtoRequest request) {
        Location location = new Location();
        location.setCity(request.city());
        location.setStreet(request.street());
        location.setBuilding(request.building());
        location.setApartment(request.apartment());
        return location;
    }

    public Location dtoUpdateToEntity(CustomerDtoRequest request,
                                         Location locationToUpdate) {
        locationToUpdate.setCity(request.city());
        locationToUpdate.setStreet(request.street());
        locationToUpdate.setBuilding(request.building());
        locationToUpdate.setApartment(request.apartment());
        return locationToUpdate;
    }
}
