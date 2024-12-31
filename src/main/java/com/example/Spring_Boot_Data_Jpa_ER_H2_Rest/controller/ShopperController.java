package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ShopperDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ShopperDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Place;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Shopper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.ShopperModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ShopperController {

    @Autowired
    @Qualifier("ShopperServiceImpl")
    private ShopperService service;

    @PostMapping("/shoppers")
    public ResponseEntity<ShopperDtoResponse> createShopper(
            @RequestBody ShopperDtoRequest request) {
        Shopper shopper = service.create(request);
        return (shopper != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(ShopperDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .shopper(ShopperModel.getModel(shopper))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(ShopperDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping("/shoppers")
    public ResponseEntity<ShopperDtoResponse> getAllShoppers() {
        List<Shopper> list = service.getAll();
        if (!list.isEmpty()) {
            List<ShopperModel> _list = new ArrayList<>();
            for (Shopper shopper : list)
                _list.add(ShopperModel.getModel(shopper));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ShopperDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ShopperDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .shopperList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ShopperDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(ShopperDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .shopperList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/places")
    public ResponseEntity<ShopperDtoResponse> getAllShoppersByPlace() {
        List<Place> list = service.getPlaces();
        if (!list.isEmpty()) {
            List<ShopperModel> _list = new ArrayList<>();
            for (Place place : list)
                _list.add(ShopperModel.getModel(place));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ShopperDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ShopperDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .shopperList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ShopperDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(ShopperDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .shopperList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/shoppers/{id}")
    public ResponseEntity<ShopperDtoResponse> getShopperById(
            @PathVariable("id") Long id) {
        Shopper shopper = service.getById(id);
        return (shopper != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ShopperDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .shopper(ShopperModel.getModel(shopper))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ShopperDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/shoppers/{id}")
    public ResponseEntity<ShopperDtoResponse> updateShopperById(
            @PathVariable("id") Long id,
            @RequestBody ShopperDtoRequest request) {
        Shopper shopper = service.updateById(id, request);
        return (shopper != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ShopperDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .shopper(ShopperModel.getModel(shopper))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ShopperDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/shoppers/{id}")
    public ResponseEntity<ShopperDtoResponse> deleteShopperById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ShopperDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ShopperDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ShopperDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
