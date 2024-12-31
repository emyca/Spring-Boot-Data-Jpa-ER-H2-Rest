package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Abode;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Vendee;
import org.springframework.stereotype.Component;

@Component
public class VendeeMapper {

    public Vendee dtoCreateToEntity(VendeeDtoRequest request) {
        Vendee vendee = new Vendee();
        vendee.setFirstName(request.firstName());
        vendee.setLastName(request.lastName());
        vendee.setEmail(request.email());
        vendee.setAbode(null);
        return vendee;
    }

    public Vendee dtoUpdateByIdToEntity(Long id,
                                        VendeeDtoRequest request,
                                        Vendee vendeeToUpdate,
                                        Abode abodeToUpdate) {
        vendeeToUpdate.setId(id);
        abodeToUpdate.setId(id);
        vendeeToUpdate.setFirstName(request.firstName());
        vendeeToUpdate.setLastName(request.lastName());
        vendeeToUpdate.setEmail(request.email());
        Abode abode = new AbodeMapper()
                .dtoUpdateToEntity(request, abodeToUpdate);
        vendeeToUpdate.setAbode(abode);
        return vendeeToUpdate;
    }
}
