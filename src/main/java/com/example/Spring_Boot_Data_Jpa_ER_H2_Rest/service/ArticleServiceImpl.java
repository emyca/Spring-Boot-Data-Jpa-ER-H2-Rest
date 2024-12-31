package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.ArticleDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Article;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.mapper.ArticleMapper;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ArticleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article create(ArticleDtoRequest request) {
        return articleRepository.save(
                articleMapper.dtoCreateToEntity(request));
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Article updateById(Long id, ArticleDtoRequest request) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        return articleOptional.map(article ->
                        articleRepository.saveAndFlush(
                                articleMapper.dtoUpdateByIdToEntity(
                                        id, request, article)))
                .orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if (articleRepository.findById(id).isPresent()) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
