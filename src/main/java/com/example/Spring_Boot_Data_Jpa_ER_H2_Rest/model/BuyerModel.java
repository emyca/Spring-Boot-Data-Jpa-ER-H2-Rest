package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Address;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Buyer;
import lombok.Data;

@Data
public class BuyerModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    public static BuyerModel getModel(Buyer buyer) {
        BuyerModel model = new BuyerModel();
        model.setId(buyer.getId());
        model.setFirstName(buyer.getFirstName());
        model.setLastName(buyer.getLastName());
        model.setEmail(buyer.getEmail());
        Address _address = new Address();
        _address.setId(buyer.getAddress().getId());
        _address.setCity(buyer.getAddress().getCity());
        _address.setStreet(buyer.getAddress().getStreet());
        _address.setBuilding(buyer.getAddress().getBuilding());
        _address.setApartment(buyer.getAddress().getApartment());
        model.setAddress(_address);
        return model;
    }

    public static BuyerModel getModel(Address address) {
        BuyerModel model = new BuyerModel();
        model.setId(address.getBuyer().getId());
        model.setFirstName(address.getBuyer().getFirstName());
        model.setLastName(address.getBuyer().getLastName());
        model.setEmail(address.getBuyer().getEmail());
        Address _address = new Address();
        _address.setId(address.getId());
        _address.setCity(address.getCity());
        _address.setStreet(address.getStreet());
        _address.setBuilding(address.getBuilding());
        _address.setApartment(address.getApartment());
        model.setAddress(_address);
        return model;
    }
}
