package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Address;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Buyer;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {

    public Buyer dtoCreateToEntity(BuyerDtoRequest request) {
        Buyer buyer = new Buyer();
        buyer.setId(request.id());
        buyer.setFirstName(request.firstName());
        buyer.setLastName(request.lastName());
        buyer.setEmail(request.email());
        buyer.setAddress(new AddressMapper()
                .dtoCreateToEntity(request));
        return buyer;
    }

    public Buyer dtoUpdateByIdToEntity(Long id,
                                       BuyerDtoRequest request,
                                       Buyer buyerToUpdate,
                                       Address addressToUpdate) {
        buyerToUpdate.setId(id);
        addressToUpdate.setId(id);
        buyerToUpdate.setFirstName(request.firstName());
        buyerToUpdate.setLastName(request.lastName());
        buyerToUpdate.setEmail(request.email());
        buyerToUpdate.setAddress(new AddressMapper()
                .dtoUpdateToEntity(request, addressToUpdate));
        return buyerToUpdate;
    }
}
