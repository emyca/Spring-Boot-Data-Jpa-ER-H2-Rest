package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Domicile;
import org.springframework.stereotype.Component;

@Component
public class DomicileMapper {

    public Domicile dtoCreateToEntity(ConsumerDtoRequest request) {
        Domicile domicile = new Domicile();
        domicile.setCity(request.city());
        domicile.setStreet(request.street());
        domicile.setBuilding(request.building());
        domicile.setApartment(request.apartment());
        return domicile;
    }

    public Domicile dtoUpdateToEntity(ConsumerDtoRequest request,
                                       Domicile domicileToUpdate) {
        domicileToUpdate.setCity(request.city());
        domicileToUpdate.setStreet(request.street());
        domicileToUpdate.setBuilding(request.building());
        domicileToUpdate.setApartment(request.apartment());
        return domicileToUpdate;
    }
}
