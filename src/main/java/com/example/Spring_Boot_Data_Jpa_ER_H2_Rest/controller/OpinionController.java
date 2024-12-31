package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.OpinionDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.OpinionDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Opinion;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.OpinionModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OpinionController {

    @Autowired
    @Qualifier("OpinionServiceImpl")
    private OpinionService service;

    @PostMapping("/stories/{id}/opinions")
    public ResponseEntity<OpinionDtoResponse> createOpinionByStoryId(
            @PathVariable("id") Long id,
            @RequestBody OpinionDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(OpinionDtoResponse
                                    .Message.SUCCESS_CREATE_MSG.getMessage())
                            .storyId(id)
                            .opinion(OpinionModel.getModel(
                                    service.createByStoryId(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/stories/{id}/opinions")
    public ResponseEntity<OpinionDtoResponse> getAllOpinionsByStoryId(
            @PathVariable("id") Long id) {
        try {
            List<Opinion> list = service.getAllByStoryId(id);
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new OpinionDtoResponse.Builder()
                                    .status(HttpStatus.OK.value())
                                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                    .success(true)
                                    .message(OpinionDtoResponse
                                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                                    .storyId(id)
                                    .opinions(OpinionModel.getModel(list))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new OpinionDtoResponse.Builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                    .success(false)
                                    .message(OpinionDtoResponse
                                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                                    .storyId(id)
                                    .opinions(Collections.emptyList())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/opinions/{id}")
    public ResponseEntity<OpinionDtoResponse> getOpinionById(
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(OpinionDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .opinion(OpinionModel.getModel(
                                    service.getById(id)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/opinions/{id}")
    public ResponseEntity<OpinionDtoResponse> updateOpinionById(
            @PathVariable("id") Long id,
            @RequestBody OpinionDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(OpinionDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .opinion(OpinionModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/opinions/{id}")
    public ResponseEntity<OpinionDtoResponse> deleteOpinionById(
            @PathVariable(value = "id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(OpinionDtoResponse
                                    .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new OpinionDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }
}
