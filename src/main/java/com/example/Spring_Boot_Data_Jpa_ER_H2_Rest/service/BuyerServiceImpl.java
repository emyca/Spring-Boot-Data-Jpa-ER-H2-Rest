package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Address;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Buyer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.BuyerMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.AddressRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("BuyerServiceImpl")
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerMapper buyerMapper;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Buyer create(BuyerDtoRequest request) {
        return buyerRepository
                .saveAndFlush(buyerMapper
                        .dtoCreateToEntity(request));
    }

    @Override
    public List<Buyer> getAll() {
        return buyerRepository.findAll();
    }

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Buyer getById(Long id) {
        return buyerRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Buyer updateById(Long id,
                            BuyerDtoRequest request) {
        Optional<Buyer> buyerOptional = buyerRepository.findById(id);
        Optional<Address> addressOptional = addressRepository.findById(id);
        return (buyerOptional.isPresent() & addressOptional.isPresent()) ?
                buyerRepository.saveAndFlush(
                        buyerMapper.dtoUpdateByIdToEntity(
                                id, request,
                                buyerOptional.get(),
                                addressOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (buyerRepository.findById(id).isPresent()) {
            buyerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
