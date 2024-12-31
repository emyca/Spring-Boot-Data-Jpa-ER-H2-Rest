package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Place;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Shopper;
import lombok.Data;

@Data
public class ShopperModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Place place;

    public static ShopperModel getModel(Shopper shopper) {
        ShopperModel model = new ShopperModel();
        model.setId(shopper.getId());
        model.setFirstName(shopper.getFirstName());
        model.setLastName(shopper.getLastName());
        model.setEmail(shopper.getEmail());
        Place _place = new Place();
        _place.setId(shopper.getPlace().getId());
        _place.setCity(shopper.getPlace().getCity());
        _place.setStreet(shopper.getPlace().getStreet());
        _place.setBuilding(shopper.getPlace().getBuilding());
        _place.setApartment(shopper.getPlace().getApartment());
        model.setPlace(_place);
        return model;
    }

    public static ShopperModel getModel(Place place) {
        ShopperModel model = new ShopperModel();
        model.setId(place.getShopper().getId());
        model.setFirstName(place.getShopper().getFirstName());
        model.setLastName(place.getShopper().getLastName());
        model.setEmail(place.getShopper().getEmail());
        Place _place = new Place();
        _place.setId(place.getId());
        _place.setCity(place.getCity());
        _place.setStreet(place.getStreet());
        _place.setBuilding(place.getBuilding());
        _place.setApartment(place.getApartment());
        model.setPlace(_place);
        return model;
    }
}
