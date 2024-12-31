package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PurchaserDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PurchaserDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Habitation;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Purchaser;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.PurchaserModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.PurchaserService;
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
public class PurchaserController {

    @Autowired
    @Qualifier("PurchaserServiceImpl")
    private PurchaserService service;

    @PostMapping("/purchasers")
    public ResponseEntity<PurchaserDtoResponse> createPurchaser(
            @RequestBody PurchaserDtoRequest request) {
        Purchaser purchaser = service.create(request);
        return (purchaser != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(PurchaserDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .purchaser(PurchaserModel.getModel(purchaser))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(PurchaserDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping("/purchasers")
    public ResponseEntity<PurchaserDtoResponse> getAllPurchasers() {
        List<Purchaser> list = service.getAll();
        if (!list.isEmpty()) {
            List<PurchaserModel> _list = new ArrayList<>();
            for (Purchaser purchaser : list)
                _list.add(PurchaserModel.getModel(purchaser));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PurchaserDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(PurchaserDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .purchaserList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PurchaserDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(PurchaserDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .purchaserList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/habitations")
    public ResponseEntity<PurchaserDtoResponse> getAllPurchasersByHabitation() {
        List<Habitation> list = service.getHabitations();
        if (!list.isEmpty()) {
            List<PurchaserModel> _list = new ArrayList<>();
            for (Habitation habitation : list)
                _list.add(PurchaserModel.getModel(habitation));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PurchaserDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(PurchaserDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .purchaserList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PurchaserDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(PurchaserDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .purchaserList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/purchasers/{id}")
    public ResponseEntity<PurchaserDtoResponse> getPurchaserById(
            @PathVariable("id") Long id) {
        Purchaser purchaser = service.getById(id);
        return (purchaser != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(PurchaserDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .purchaser(PurchaserModel.getModel(purchaser))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(PurchaserDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/purchasers/{id}")
    public ResponseEntity<PurchaserDtoResponse> updatePurchaserById(
            @PathVariable("id") Long id,
            @RequestBody PurchaserDtoRequest request) {
        Purchaser purchaser = service.updateById(id, request);
        return (purchaser != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(PurchaserDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .purchaser(PurchaserModel.getModel(purchaser))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(PurchaserDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/purchasers/{id}")
    public ResponseEntity<PurchaserDtoResponse> deletePurchaserById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(PurchaserDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PurchaserDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(PurchaserDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
