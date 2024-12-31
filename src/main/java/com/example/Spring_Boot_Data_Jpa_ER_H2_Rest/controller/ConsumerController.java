package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ConsumerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ConsumerDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Consumer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.ConsumerModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/consumers")
public class ConsumerController {

    @Autowired
    @Qualifier("ConsumerServiceImpl")
    private ConsumerService service;

    @PostMapping
    public ResponseEntity<ConsumerDtoResponse> createConsumer(
            @RequestBody ConsumerDtoRequest request) {
        Consumer consumer = service.create(request);
        return (consumer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(ConsumerDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .consumer(ConsumerModel.getModel(consumer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(ConsumerDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping
    public ResponseEntity<ConsumerDtoResponse> getAllConsumers() {
        List<Consumer> list = service.getAll();
        if (!list.isEmpty()) {
            List<ConsumerModel> _list = new ArrayList<>();
            for (Consumer consumer : list)
                _list.add(ConsumerModel.getModel(consumer));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ConsumerDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ConsumerDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .consumerList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ConsumerDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(ConsumerDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .consumerList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerDtoResponse> getConsumerById(
            @PathVariable("id") Long id) {
        Consumer consumer = service.getById(id);
        return (consumer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ConsumerDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .consumer(ConsumerModel.getModel(consumer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ConsumerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumerDtoResponse> updateConsumerById(
            @PathVariable("id") Long id,
            @RequestBody ConsumerDtoRequest request) {
        Consumer consumer = service.updateById(id, request);
        return (consumer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ConsumerDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .consumer(ConsumerModel.getModel(consumer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ConsumerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ConsumerDtoResponse> deleteConsumerById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ConsumerDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ConsumerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ConsumerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
