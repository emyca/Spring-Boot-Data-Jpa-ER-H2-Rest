package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Customer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer dtoCreateToEntity(CustomerDtoRequest request) {
        Customer customer = new Customer();
        customer.setId(request.id());
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setLocation(new LocationMapper()
                .dtoCreateToEntity(request));
        return customer;
    }

    public Customer dtoUpdateByIdToEntity(Long id,
                                          CustomerDtoRequest request,
                                          Customer customerToUpdate,
                                          Location locationToUpdate) {
        customerToUpdate.setId(id);
        locationToUpdate.setId(id);
        customerToUpdate.setFirstName(request.firstName());
        customerToUpdate.setLastName(request.lastName());
        customerToUpdate.setEmail(request.email());
        customerToUpdate.setLocation(new LocationMapper()
                .dtoUpdateToEntity(request, locationToUpdate));
        return customerToUpdate;
    }
}
