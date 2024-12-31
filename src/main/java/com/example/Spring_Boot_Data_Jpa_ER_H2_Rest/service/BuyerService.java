package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Address;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Buyer;

import java.util.List;

public interface BuyerService {
    Buyer create(BuyerDtoRequest request);
    List<Buyer> getAll();
    List<Address> getAddresses();
    Buyer getById(Long id);
    Buyer updateById(Long id, BuyerDtoRequest request);
    boolean deleteById(Long id);
}
