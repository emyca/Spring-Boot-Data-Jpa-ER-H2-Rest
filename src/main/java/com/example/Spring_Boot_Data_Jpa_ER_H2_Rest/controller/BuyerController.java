package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.BuyerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.BuyerDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Address;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Buyer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.BuyerModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.BuyerService;
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
public class BuyerController {

    @Autowired
    @Qualifier("BuyerServiceImpl")
    private BuyerService service;

    @PostMapping("/buyers")
    public ResponseEntity<BuyerDtoResponse> createBuyer(
            @RequestBody BuyerDtoRequest request) {
        Buyer buyer = service.create(request);
        return (buyer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(BuyerDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .buyer(BuyerModel.getModel(buyer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(BuyerDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping("/buyers")
    public ResponseEntity<BuyerDtoResponse> getAllBuyers() {
        List<Buyer> list = service.getAll();
        if (!list.isEmpty()) {
            List<BuyerModel> _list = new ArrayList<>();
            for (Buyer buyer : list)
                _list.add(BuyerModel.getModel(buyer));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BuyerDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(BuyerDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .buyerList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BuyerDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(BuyerDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .buyerList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/addresses")
    public ResponseEntity<BuyerDtoResponse> getAllBuyersByAddress() {
        List<Address> list = service.getAddresses();
        if (!list.isEmpty()) {
            List<BuyerModel> _list = new ArrayList<>();
            for (Address address : list)
                _list.add(BuyerModel.getModel(address));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BuyerDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(BuyerDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .buyerList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BuyerDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(BuyerDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .buyerList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/buyers/{id}")
    public ResponseEntity<BuyerDtoResponse> getBuyerById(
            @PathVariable("id") Long id) {
        Buyer buyer = service.getById(id);
        return (buyer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(BuyerDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .buyer(BuyerModel.getModel(buyer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(BuyerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/buyers/{id}")
    public ResponseEntity<BuyerDtoResponse> updateBuyerById(
            @PathVariable("id") Long id,
            @RequestBody BuyerDtoRequest request) {
        Buyer buyer = service.updateById(id, request);
        return (buyer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(BuyerDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .buyer(BuyerModel.getModel(buyer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(BuyerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/buyers/{id}")
    public ResponseEntity<BuyerDtoResponse> deleteBuyerById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(BuyerDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new BuyerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(BuyerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}

