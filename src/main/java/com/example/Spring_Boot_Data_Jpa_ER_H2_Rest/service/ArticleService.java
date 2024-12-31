package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Article;

import java.util.List;

public interface ArticleService {
    Article create(ArticleDtoRequest request);
    List<Article> getAll();
    Article getById(Long id);
    Article updateById(Long id, ArticleDtoRequest request);
    boolean deleteById(Long id);
}
