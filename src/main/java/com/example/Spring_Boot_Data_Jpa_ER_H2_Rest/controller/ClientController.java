package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ClientDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ClientDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Client;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.ClientModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    @Autowired
    @Qualifier("ClientServiceImpl")
    private ClientService service;

    @PostMapping
    public ResponseEntity<ClientDtoResponse> createClient(
            @RequestBody ClientDtoRequest request) {
        Client client = service.create(request);
        return (client != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(ClientDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .client(ClientModel.getModel(client))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(ClientDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping
    public ResponseEntity<ClientDtoResponse> getAllClients() {
        List<Client> list = service.getAll();
        if (!list.isEmpty()) {
            List<ClientModel> _list = new ArrayList<>();
            for (Client client : list)
                _list.add(ClientModel.getModel(client));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ClientDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ClientDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .clientList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ClientDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(ClientDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .clientList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> getClientById(
            @PathVariable("id") Long id) {
        Client client = service.getById(id);
        return (client != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ClientDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .client(ClientModel.getModel(client))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ClientDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> updateClientById(
            @PathVariable("id") Long id,
            @RequestBody ClientDtoRequest request) {
        Client client = service.updateById(id, request);
        return (client != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ClientDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .client(ClientModel.getModel(client))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ClientDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDtoResponse> deleteClientById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ClientDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ClientDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ClientDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
