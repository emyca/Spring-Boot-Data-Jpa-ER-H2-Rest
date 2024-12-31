package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Customer;
import lombok.Data;

@Data
public class CustomerModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String street;
    private String building;
    private String apartment;

    public static CustomerModel getModel(Customer customer) {
        CustomerModel model = new CustomerModel();
        model.setId(customer.getId());
        model.setFirstName(customer.getFirstName());
        model.setLastName(customer.getLastName());
        model.setEmail(customer.getEmail());
        model.setCity(customer.getLocation().getCity());
        model.setStreet(customer.getLocation().getStreet());
        model.setBuilding(customer.getLocation().getBuilding());
        model.setApartment(customer.getLocation().getApartment());
        return model;
    }
}
