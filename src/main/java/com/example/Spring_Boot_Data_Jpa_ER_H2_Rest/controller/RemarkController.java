package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.RemarkDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.RemarkDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Remark;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.RemarkModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RemarkController {

    @Autowired
    @Qualifier("RemarkServiceImpl")
    private RemarkService service;

    @PostMapping("/articles/{id}/remarks")
    public ResponseEntity<RemarkDtoResponse> createRemarkByArticleId(
            @PathVariable("id") Long id,
            @RequestBody RemarkDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(RemarkDtoResponse
                                    .Message.SUCCESS_CREATE_MSG.getMessage())
                            .articleId(id)
                            .remark(RemarkModel.getModel(
                                    service.createByArticleId(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/articles/{id}/remarks")
    public ResponseEntity<RemarkDtoResponse> getAllRemarksByArticleId(
            @PathVariable("id") Long id) {
        try {
            List<Remark> list = service.getAllByArticleId(id);
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new RemarkDtoResponse.Builder()
                                    .status(HttpStatus.OK.value())
                                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                    .success(true)
                                    .message(RemarkDtoResponse
                                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                                    .articleId(id)
                                    .remarks(RemarkModel.getModel(list))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new RemarkDtoResponse.Builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                    .success(false)
                                    .message(RemarkDtoResponse
                                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                                    .articleId(id)
                                    .remarks(Collections.emptyList())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/remarks/{id}")
    public ResponseEntity<RemarkDtoResponse> getRemarkById(
            @PathVariable("id") Long id) {
        try {
            Remark remark = service.getById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(RemarkDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .articleId(RemarkModel.getArticleId(remark))
                            .remark(RemarkModel.getModel(remark))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/remarks/{id}")
    public ResponseEntity<RemarkDtoResponse> updateRemarkById(
            @PathVariable("id") Long id,
            @RequestBody RemarkDtoRequest request) {
        try {
            Remark remark = service.updateById(id, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(RemarkDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .articleId(RemarkModel.getArticleId(remark))
                            .remark(RemarkModel.getModel(remark))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RemarkDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/remarks/{id}")
    public ResponseEntity<RemarkDtoResponse> deleteRemarkById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new RemarkDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(RemarkDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new RemarkDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(RemarkDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
