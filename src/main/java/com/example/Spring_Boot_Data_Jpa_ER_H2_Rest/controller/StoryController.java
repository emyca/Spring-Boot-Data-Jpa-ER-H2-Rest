package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StoryDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.StoryDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Story;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.StoryModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/stories")
public class StoryController {

    @Autowired
    @Qualifier("StoryServiceImpl")
    private StoryService service;

    @PostMapping
    public ResponseEntity<StoryDtoResponse> createStory(
            @RequestBody StoryDtoRequest request) {
        Story story = service.create(request);
        return (story != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new StoryDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(StoryDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .story(StoryModel.getModel(story))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new StoryDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(StoryDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping
    public ResponseEntity<StoryDtoResponse> getAllStories() {
        List<Story> list = service.getAll();
        if (!list.isEmpty()) {
            List<StoryModel> _list = new ArrayList<>();
            for (Story story : list)
                _list.add(StoryModel.getModel(story));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StoryDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .stories(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(StoryDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .stories(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoryDtoResponse> getStoryById(
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StoryDtoResponse
                                    .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .story(StoryModel.getModel(
                                    service.getById(id)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoryDtoResponse> updateStoryById(
            @PathVariable("id") Long id,
            @RequestBody StoryDtoRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StoryDtoResponse
                                    .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .story(StoryModel.getModel(
                                    service.updateById(id, request)))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StoryDtoResponse> deleteStoryById(
            @PathVariable(value = "id") Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(StoryDtoResponse
                                    .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                    .formatted(id))
                            .build());
        } catch (ResourceException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(e.getMessage())
                            .build());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new StoryDtoResponse.Builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .success(false)
                            .message("Can't delete the Story with id " + id + ". " +
                                    "Referential integrity constraint violation. " +
                                    "First delete Opinion(s) associated with this Story!")
                            .build());
        }
    }
}
