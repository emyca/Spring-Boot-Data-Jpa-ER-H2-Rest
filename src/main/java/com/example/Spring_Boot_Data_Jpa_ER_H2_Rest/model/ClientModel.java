package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Client;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Residence;
import lombok.Data;

@Data
public class ClientModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Residence residence;

    public static ClientModel getModel(Client client) {
        ClientModel model = new ClientModel();
        model.setId(client.getId());
        model.setFirstName(client.getFirstName());
        model.setLastName(client.getLastName());
        model.setEmail(client.getEmail());
        Residence _residence = new Residence();
        _residence.setId(client.getResidence().getId());
        _residence.setCity(client.getResidence().getCity());
        _residence.setStreet(client.getResidence().getStreet());
        _residence.setBuilding(client.getResidence().getBuilding());
        _residence.setApartment(client.getResidence().getApartment());
        model.setResidence(_residence);
        return model;
    }
}
