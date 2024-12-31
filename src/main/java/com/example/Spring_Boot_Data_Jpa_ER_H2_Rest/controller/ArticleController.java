package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.controller;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ArticleDtoResponse;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Article;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.model.ArticleModel;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/articles")
public class ArticleController {

    @Autowired
    @Qualifier("ArticleServiceImpl")
    private ArticleService service;

    @PostMapping
    public ResponseEntity<ArticleDtoResponse> createArticle(
            @RequestBody ArticleDtoRequest request) {
        Article article = service.create(request);
        return (article != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.CREATED.value())
                                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                                .success(true)
                                .message(ArticleDtoResponse
                                        .Message.SUCCESS_CREATE_MSG.getMessage())
                                .article(ArticleModel.getModel(article))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.NO_CONTENT.value())
                                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                                .success(false)
                                .message(ArticleDtoResponse
                                        .Message.FAILURE_CREATE_MSG.getMessage())
                                .build());
    }

    @GetMapping
    public ResponseEntity<ArticleDtoResponse> getAllArticles() {
        List<Article> list = service.getAll();
        if (!list.isEmpty()) {
            List<ArticleModel> _list = new ArrayList<>();
            for (Article article : list)
                _list.add(ArticleModel.getModel(article));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArticleDtoResponse.Builder()
                            .status(HttpStatus.OK.value())
                            .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                            .success(true)
                            .message(ArticleDtoResponse
                                    .Message.SUCCESS_GET_LIST_MSG.getMessage())
                            .articles(_list)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArticleDtoResponse.Builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .success(false)
                            .message(ArticleDtoResponse
                                    .Message.FAILURE_GET_LIST_MSG.getMessage())
                            .articles(Collections.emptyList())
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDtoResponse> getArticleById(
            @PathVariable("id") Long id) {
        Article article = service.getById(id);
        return (article != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ArticleDtoResponse
                                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .article(ArticleModel.getModel(article))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ArticleDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDtoResponse> updateArticleById(
            @PathVariable("id") Long id,
            @RequestBody ArticleDtoRequest request) {
        Article article = service.updateById(id, request);
        return (article != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ArticleDtoResponse
                                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .article(ArticleModel.getModel(article))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ArticleDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleDtoResponse> deleteArticleById(
            @PathVariable(value = "id") Long id) {
        return (service.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.OK.value())
                                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                                .success(true)
                                .message(ArticleDtoResponse
                                        .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build()) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(new ArticleDtoResponse.Builder()
                                .status(HttpStatus.NOT_FOUND.value())
                                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                                .success(false)
                                .message(ArticleDtoResponse
                                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                                        .formatted(id))
                                .build());
    }
}
