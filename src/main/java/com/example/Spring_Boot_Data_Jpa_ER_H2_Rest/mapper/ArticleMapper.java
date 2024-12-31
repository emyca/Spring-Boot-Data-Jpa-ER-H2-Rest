package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public Article dtoCreateToEntity(ArticleDtoRequest request) {
        Article article = new Article();
        article.setId(request.id());
        article.setTitle(request.title());
        article.setContent(request.content());
        return article;
    }

    public Article dtoUpdateByIdToEntity(Long id,
                                         ArticleDtoRequest request,
                                         Article articleToUpdate) {
        articleToUpdate.setId(id);
        articleToUpdate.setTitle(request.title());
        articleToUpdate.setContent(request.content());
        return articleToUpdate;
    }
}
