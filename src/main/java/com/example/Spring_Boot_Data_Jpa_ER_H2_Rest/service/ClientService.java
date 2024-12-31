package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ClientDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Client;

import java.util.List;

public interface ClientService {
    Client create(ClientDtoRequest request);
    List<Client> getAll();
    Client getById(Long id);
    Client updateById(Long id, ClientDtoRequest request);
    boolean deleteById(Long id);
}
