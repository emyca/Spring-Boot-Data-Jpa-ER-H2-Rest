package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ClientDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Residence;
import org.springframework.stereotype.Component;

@Component
public class ResidenceMapper {

    public Residence dtoCreateToEntity(ClientDtoRequest request) {
        Residence residence = new Residence();
        residence.setCity(request.city());
        residence.setStreet(request.street());
        residence.setBuilding(request.building());
        residence.setApartment(request.apartment());
        return residence;
    }

    public Residence dtoUpdateToEntity(ClientDtoRequest request,
                                       Residence residenceToUpdate) {
        residenceToUpdate.setCity(request.city());
        residenceToUpdate.setStreet(request.street());
        residenceToUpdate.setBuilding(request.building());
        residenceToUpdate.setApartment(request.apartment());
        return residenceToUpdate;
    }
}
