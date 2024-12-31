package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ClientDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Client;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Residence;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.ClientMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ClientRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ClientServiceImpl")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ResidenceRepository residenceRepository;

    @Override
    public Client create(ClientDtoRequest request) {
        return clientRepository
                .saveAndFlush(clientMapper
                        .dtoCreateToEntity(request));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Client updateById(Long id, ClientDtoRequest request) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Optional<Residence> residenceOptional = residenceRepository.findById(id);
        return (clientOptional.isPresent() & residenceOptional.isPresent()) ?
                clientRepository.saveAndFlush(
                        clientMapper.dtoUpdateByIdToEntity(
                                id, request,
                                clientOptional.get(),
                                residenceOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (clientRepository.findById(id).isPresent()) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
