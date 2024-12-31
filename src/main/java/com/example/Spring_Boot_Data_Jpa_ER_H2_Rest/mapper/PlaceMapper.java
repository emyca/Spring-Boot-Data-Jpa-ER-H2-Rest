package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {

    public Place dtoCreateToEntity(ShopperDtoRequest request) {
        Place place = new Place();
        place.setCity(request.city());
        place.setStreet(request.street());
        place.setBuilding(request.building());
        place.setApartment(request.apartment());
        return place;
    }

    public Place dtoUpdateToEntity(ShopperDtoRequest request,
                                   Place placeToUpdate) {
        placeToUpdate.setCity(request.city());
        placeToUpdate.setStreet(request.street());
        placeToUpdate.setBuilding(request.building());
        placeToUpdate.setApartment(request.apartment());
        return placeToUpdate;
    }
}
