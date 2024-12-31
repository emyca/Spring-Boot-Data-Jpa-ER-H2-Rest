package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Habitation;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Purchaser;

import java.util.List;

public interface PurchaserService {
    Purchaser create(PurchaserDtoRequest request);
    List<Purchaser> getAll();
    List<Habitation> getHabitations();
    Purchaser getById(Long id);
    Purchaser updateById(Long id, PurchaserDtoRequest request);
    boolean deleteById(Long id);
}
