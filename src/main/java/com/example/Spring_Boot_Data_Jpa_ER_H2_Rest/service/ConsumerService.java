package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Consumer;

import java.util.List;

public interface ConsumerService {
    Consumer create(ConsumerDtoRequest request);
    List<Consumer> getAll();
    Consumer getById(Long id);
    Consumer updateById(Long id, ConsumerDtoRequest request);
    boolean deleteById(Long id);
}
