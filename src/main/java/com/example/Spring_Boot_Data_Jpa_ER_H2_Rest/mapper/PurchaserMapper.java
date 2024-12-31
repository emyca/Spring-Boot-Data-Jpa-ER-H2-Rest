package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Habitation;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Purchaser;
import org.springframework.stereotype.Component;

@Component
public class PurchaserMapper {

    public Purchaser dtoCreateToEntity(PurchaserDtoRequest request) {
        Purchaser purchaser = new Purchaser();
        purchaser.setId(request.id());
        purchaser.setFirstName(request.firstName());
        purchaser.setLastName(request.lastName());
        purchaser.setEmail(request.email());
        Habitation habitation = new HabitationMapper()
                .dtoCreateToEntity(request);
        purchaser.setHabitation(habitation);
        return purchaser;
    }

    public Purchaser dtoUpdateByIdToEntity(Long id,
                                       PurchaserDtoRequest request,
                                       Purchaser purchaserToUpdate,
                                       Habitation habitationToUpdate) {
        purchaserToUpdate.setId(id);
        habitationToUpdate.setId(id);
        purchaserToUpdate.setFirstName(request.firstName());
        purchaserToUpdate.setLastName(request.lastName());
        purchaserToUpdate.setEmail(request.email());
        Habitation habitation = new HabitationMapper()
                .dtoUpdateToEntity(request, habitationToUpdate);
        purchaserToUpdate.setHabitation(habitation);
        return purchaserToUpdate;
    }
}
