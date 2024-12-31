package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Habitation;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Purchaser;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.PurchaserMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.HabitationRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.PurchaserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("PurchaserServiceImpl")
public class PurchaserServiceImpl implements PurchaserService {

    @Autowired
    private PurchaserMapper purchaserMapper;
    @Autowired
    private PurchaserRepository purchaserRepository;
    @Autowired
    private HabitationRepository habitationRepository;

    @Override
    public Purchaser create(PurchaserDtoRequest request) {
        return purchaserRepository
                .saveAndFlush(purchaserMapper
                        .dtoCreateToEntity(request));
    }

    @Override
    public List<Purchaser> getAll() {
        return purchaserRepository.findAll();
    }

    @Override
    public List<Habitation> getHabitations() {
        return habitationRepository.findAll();
    }

    @Override
    public Purchaser getById(Long id) {
        return purchaserRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Purchaser updateById(Long id, PurchaserDtoRequest request) {
        Optional<Purchaser> purchaserOptional = purchaserRepository.findById(id);
        Optional<Habitation> habitationOptional = habitationRepository.findById(id);
        return (purchaserOptional.isPresent() & habitationOptional.isPresent()) ?
                purchaserRepository.saveAndFlush(
                        purchaserMapper.dtoUpdateByIdToEntity(
                                id, request,
                                purchaserOptional.get(),
                                habitationOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (purchaserRepository.findById(id).isPresent()) {
            purchaserRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
