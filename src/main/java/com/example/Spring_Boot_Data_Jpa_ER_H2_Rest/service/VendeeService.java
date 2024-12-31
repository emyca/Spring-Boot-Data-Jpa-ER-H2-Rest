package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Abode;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Vendee;

import java.util.List;

public interface VendeeService {
    Vendee create(VendeeDtoRequest request);
    List<Vendee> getAll();
    List<Abode> getAbodes();
    Vendee getById(Long id);
    Vendee updateById(Long id, VendeeDtoRequest request);
    boolean deleteById(Long id);
}
