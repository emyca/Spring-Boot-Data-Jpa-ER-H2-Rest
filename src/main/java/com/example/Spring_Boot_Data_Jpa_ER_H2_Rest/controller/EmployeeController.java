package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.*;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Employee;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.EmployeeModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.EmployeeService;
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
public class EmployeeController {

    @Autowired
    @Qualifier("EmployeeServiceImpl")
    private EmployeeService service;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDtoResponse> createEmployee(
            @RequestBody EmployeeDtoRequest request) {
        Employee employee = service.create(request);
        return (employee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new EmployeeDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(EmployeeDtoResponse
                                        .Message.SUCCESS_SAVE_MSG.getMessage())
                                .employee(EmployeeModel.getModel(employee))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new EmployeeDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(EmployeeDtoResponse
                                        .Message.FAILURE_SAVE_MSG.getMessage())
                                .build());
    }

    @GetMapping("/employees")
    public ResponseEntity<EmployeeDtoResponse> getAllEmployees() {
        List<Employee> list = service.getAll();
        if (!list.isEmpty()) {
            List<EmployeeModel> _list = new ArrayList<>();
            for (Employee employee : list)
                _list.add(EmployeeModel.getModel(employee));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(EmployeeDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .employees(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(EmployeeDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .employees(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDtoResponse> getEmployeeById(
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(EmployeeDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .employee(EmployeeModel.getModel(service.getById(id)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/projects/{id}/employees")
    public ResponseEntity<EmployeeDtoResponse> getAllEmployeesByProjectId(
            @PathVariable("id") Long id) {
        try {
            List<Employee> list = service.getAllEmployeesByProjectId(id);
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new EmployeeDtoResponse.Builder()
                                    .status(HttpStatus.OK.value())
                                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                    .success(true)
                                    .message(EmployeeDtoResponse
                                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                                    .projectId(id)
                                    .employees(EmployeeModel.getModel(list))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new EmployeeDtoResponse.Builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                    .success(false)
                                    .message(EmployeeDtoResponse
                                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                                    .employees(Collections.emptyList())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDtoResponse> updateEmployeeById(
            @PathVariable("id") Long id,
            @RequestBody EmployeeDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(EmployeeDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .employee(EmployeeModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeDtoResponse> deleteEmployeeById(
            @PathVariable(value = "id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(EmployeeDtoResponse
                                    .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new EmployeeDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

}
