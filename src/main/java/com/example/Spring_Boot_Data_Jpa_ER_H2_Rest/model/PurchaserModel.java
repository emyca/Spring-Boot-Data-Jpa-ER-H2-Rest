package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Habitation;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Purchaser;
import lombok.Data;

@Data
public class PurchaserModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Habitation habitation;

    public static PurchaserModel getModel(Purchaser purchaser) {
        PurchaserModel model = new PurchaserModel();
        model.setId(purchaser.getId());
        model.setFirstName(purchaser.getFirstName());
        model.setLastName(purchaser.getLastName());
        model.setEmail(purchaser.getEmail());
        Habitation _habitation = new Habitation();
        _habitation.setId(purchaser.getHabitation().getId());
        _habitation.setCity(purchaser.getHabitation().getCity());
        _habitation.setStreet(purchaser.getHabitation().getStreet());
        _habitation.setBuilding(purchaser.getHabitation().getBuilding());
        _habitation.setApartment(purchaser.getHabitation().getApartment());
        model.setHabitation(_habitation);
        return model;
    }

    public static PurchaserModel getModel(Habitation habitation) {
        PurchaserModel model = new PurchaserModel();
        model.setId(habitation.getPurchaser().getId());
        model.setFirstName(habitation.getPurchaser().getFirstName());
        model.setLastName(habitation.getPurchaser().getLastName());
        model.setEmail(habitation.getPurchaser().getEmail());
        Habitation _habitation = new Habitation();
        _habitation.setId(habitation.getId());
        _habitation.setCity(habitation.getCity());
        _habitation.setStreet(habitation.getStreet());
        _habitation.setBuilding(habitation.getBuilding());
        _habitation.setApartment(habitation.getApartment());
        model.setHabitation(_habitation);
        return model;
    }
}
