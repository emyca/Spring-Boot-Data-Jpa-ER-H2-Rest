package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CommentDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.CommentDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Comment;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.CommentModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CommentController {

    @Autowired
    @Qualifier("CommentServiceImpl")
    private CommentService service;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentDtoResponse> createCommentByPostId(
            @PathVariable("id") Long id,
            @RequestBody CommentDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CommentDtoResponse
                                    .Message.SUCCESS_CREATE_MSG.getMessage())
                            .postId(id)
                            .comment(CommentModel.getModel(
                                    service.createByPostId(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<CommentDtoResponse> getAllCommentsByPostId(
            @PathVariable("id") Long id) {
        try {
            List<Comment> list = service.getAllByPostId(id);
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new CommentDtoResponse.Builder()
                                    .status(HttpStatus.OK.value())
                                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                    .success(true)
                                    .message(CommentDtoResponse
                                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                                    .postId(id)
                                    .comments(CommentModel.getModel(list))
                                    .build()) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(new CommentDtoResponse.Builder()
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                    .success(false)
                                    .message(CommentDtoResponse
                                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                                    .postId(id)
                                    .comments(Collections.emptyList())
                                    .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> getCommentById(
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CommentDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .comment(CommentModel.getModel(
                                    service.getById(id)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> updateCommentById(
            @PathVariable("id") Long id,
            @RequestBody CommentDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(CommentDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .comment(CommentModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CommentDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> deleteCommentById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CommentDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(CommentDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new CommentDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(CommentDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

}
