package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Place;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Shopper;

import java.util.List;

public interface ShopperService {
    Shopper create(ShopperDtoRequest request);
    List<Shopper> getAll();
    List<Place> getPlaces();
    Shopper getById(Long id);
    Shopper updateById(Long id, ShopperDtoRequest request);
    boolean deleteById(Long id);
}
