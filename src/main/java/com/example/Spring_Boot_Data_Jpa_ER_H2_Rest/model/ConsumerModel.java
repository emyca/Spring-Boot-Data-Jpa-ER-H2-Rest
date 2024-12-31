package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Consumer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Domicile;
import lombok.Data;

@Data
public class ConsumerModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Domicile domicile;

    public static ConsumerModel getModel(Consumer consumer) {
        ConsumerModel model = new ConsumerModel();
        model.setId(consumer.getId());
        model.setFirstName(consumer.getFirstName());
        model.setLastName(consumer.getLastName());
        model.setEmail(consumer.getEmail());
        Domicile _domicile = new Domicile();
        _domicile.setId(consumer.getId());
        _domicile.setCity(consumer.getDomicile().getCity());
        _domicile.setStreet(consumer.getDomicile().getStreet());
        _domicile.setBuilding(consumer.getDomicile().getBuilding());
        _domicile.setApartment(consumer.getDomicile().getApartment());
        model.setDomicile(_domicile);
        return model;
    }
}
