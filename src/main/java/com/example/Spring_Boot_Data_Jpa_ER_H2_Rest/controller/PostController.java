package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PostDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.PostDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Post;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.PostModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    @Qualifier("PostServiceImpl")
    private PostService service;

    @PostMapping
    public ResponseEntity<PostDtoResponse> createPost(
            @RequestBody PostDtoRequest request) {
        Post post = service.create(request);
        return (post != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(PostDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .post(PostModel.getModel(post))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(PostDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping
    public ResponseEntity<PostDtoResponse> getAllPosts() {
        List<Post> list = service.getAll();
        if (!list.isEmpty()) {
            List<PostModel> _list = new ArrayList<>();
            for (Post post : list)
                _list.add(PostModel.getModel(post));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PostDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(PostDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .posts(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new PostDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(PostDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .posts(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDtoResponse> getPostById(
            @PathVariable("id") Long id) {
        Post post = service.getById(id);
        return (post != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(PostDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .post(PostModel.getModel(post))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(PostDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDtoResponse> updatePostById(
            @PathVariable("id") Long id,
            @RequestBody PostDtoRequest request) {
        Post post = service.updateById(id, request);
        return (post != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(PostDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .post(PostModel.getModel(post))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(PostDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDtoResponse> deletePostById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(PostDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new PostDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(PostDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
