package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Consumer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Domicile;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.ConsumerMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ConsumerRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.DomicileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ConsumerServiceImpl")
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private DomicileRepository domicileRepository;

    @Override
    public Consumer create(ConsumerDtoRequest request) {
        return consumerRepository
                .saveAndFlush(consumerMapper
                        .dtoCreateToEntity(request));
    }

    @Override
    public List<Consumer> getAll() {
        return consumerRepository.findAll();
    }

    @Override
    public Consumer getById(Long id) {
        return consumerRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Consumer updateById(Long id, ConsumerDtoRequest request) {
        Optional<Consumer> consumerOptional = consumerRepository.findById(id);
        Optional<Domicile> domicileOptional = domicileRepository.findById(id);
        return (consumerOptional.isPresent() & domicileOptional.isPresent()) ?
                consumerRepository.saveAndFlush(
                        consumerMapper.dtoUpdateByIdToEntity(
                                id, request,
                                consumerOptional.get(),
                                domicileOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (consumerRepository.findById(id).isPresent()) {
            consumerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
