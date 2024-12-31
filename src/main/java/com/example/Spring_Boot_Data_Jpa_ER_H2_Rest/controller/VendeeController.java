package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.VendeeDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.VendeeDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Abode;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Vendee;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.VendeeModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.VendeeService;
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
public class VendeeController {

    @Autowired
    @Qualifier("VendeeServiceImpl")
    private VendeeService service;

    @PostMapping("/vendees")
    public ResponseEntity<VendeeDtoResponse> createVendee(
            @RequestBody VendeeDtoRequest request) {
        Vendee vendee = service.create(request);
        return (vendee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(VendeeDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .vendee(VendeeModel.getModel(vendee))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(VendeeDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping("/vendees")
    public ResponseEntity<VendeeDtoResponse> getAllVendees() {
        List<Vendee> list = service.getAll();
        if (!list.isEmpty()) {
            List<VendeeModel> _list = new ArrayList<>();
            for (Vendee vendee : list)
                _list.add(VendeeModel.getModel(vendee));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new VendeeDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(VendeeDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .vendeeList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new VendeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(VendeeDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .vendeeList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/abodes")
    public ResponseEntity<VendeeDtoResponse> getAllVendeesByAbode() {
        List<Abode> list = service.getAbodes();
        if (!list.isEmpty()) {
            List<VendeeModel> _list = new ArrayList<>();
            for (Abode abode : list)
                _list.add(VendeeModel.getModel(abode));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new VendeeDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(VendeeDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .vendeeList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new VendeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(VendeeDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .vendeeList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/vendees/{id}")
    public ResponseEntity<VendeeDtoResponse> getVendeeById(
            @PathVariable("id") Long id) {
        Vendee vendee = service.getById(id);
        return (vendee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(VendeeDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .vendee(VendeeModel.getModel(vendee))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(VendeeDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/vendees/{id}")
    public ResponseEntity<VendeeDtoResponse> updateVendeeById(
            @PathVariable("id") Long id,
            @RequestBody VendeeDtoRequest request) {
        Vendee vendee = service.updateById(id, request);
        return (vendee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(VendeeDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .vendee(VendeeModel.getModel(vendee))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(VendeeDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/vendees/{id}")
    public ResponseEntity<VendeeDtoResponse> deleteVendeeById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(VendeeDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new VendeeDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(VendeeDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
