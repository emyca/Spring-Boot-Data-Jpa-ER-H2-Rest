package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Place;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Shopper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.ShopperMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.PlaceRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("ShopperServiceImpl")
public class ShopperServiceImpl implements ShopperService {

    @Autowired
    private ShopperMapper shopperMapper;
    @Autowired
    private ShopperRepository shopperRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Shopper create(ShopperDtoRequest request) {
        return shopperRepository
                .saveAndFlush(shopperMapper
                        .dtoCreateToEntity(request));
    }

    @Override
    public List<Shopper> getAll() {
        return shopperRepository.findAll();
    }

    @Override
    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Shopper getById(Long id) {
        return shopperRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Shopper updateById(Long id, ShopperDtoRequest request) {
        Optional<Shopper> shopperOptional = shopperRepository.findById(id);
        Optional<Place> placeOptional = placeRepository.findById(id);
        return (shopperOptional.isPresent() & placeOptional.isPresent()) ?
                shopperRepository.saveAndFlush(
                        shopperMapper.dtoUpdateByIdToEntity(
                                id, request,
                                shopperOptional.get(),
                                placeOptional.get())) : null;
    }

    @Override
    public boolean deleteById(Long id) {
        if (shopperRepository.findById(id).isPresent()) {
            shopperRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
