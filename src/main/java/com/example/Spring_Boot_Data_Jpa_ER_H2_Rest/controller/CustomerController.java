package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CustomerDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CustomerDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Customer;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.CustomerModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    @Qualifier("CustomerServiceImpl")
    private CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDtoResponse> createCustomer(
            @RequestBody CustomerDtoRequest request) {
        Customer customer = service.create(request);
        return (customer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(CustomerDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .customer(CustomerModel.getModel(customer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(CustomerDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping
    public ResponseEntity<CustomerDtoResponse> getAllCustomers() {
        List<Customer> list = service.getAll();
        if (!list.isEmpty()) {
            List<CustomerModel> _list = new ArrayList<>();
            for (Customer customer : list)
                _list.add(CustomerModel.getModel(customer));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomerDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CustomerDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .customerList(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomerDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(CustomerDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .customerList(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> getCustomerById(
            @PathVariable("id") Long id) {
        Customer customer = service.getById(id);
        return (customer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(CustomerDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .customer(CustomerModel.getModel(customer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(CustomerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> updateCustomerById(
            @PathVariable("id") Long id,
            @RequestBody CustomerDtoRequest request) {
        Customer customer = service.updateById(id, request);
        return (customer != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(CustomerDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .customer(CustomerModel.getModel(customer))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(CustomerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> deleteCustomerById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(CustomerDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CustomerDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(CustomerDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
