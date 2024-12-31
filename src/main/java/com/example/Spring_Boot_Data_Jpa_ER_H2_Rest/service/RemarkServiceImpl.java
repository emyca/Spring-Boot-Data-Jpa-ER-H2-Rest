package com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.service;

import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.dto.RemarkDtoRequest;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Article;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.entity.Remark;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.exception.ResourceException;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.ArticleRepository;
import com.example.Spring_Boot_Data_Jpa_ER_H2_Rest.repository.RemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RemarkServiceImpl")
public class RemarkServiceImpl implements RemarkService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RemarkRepository remarkRepository;

    @Override
    public Remark createByArticleId(Long id, RemarkDtoRequest request)
            throws ResourceException {
        return articleRepository.findById(id).map(article -> {
            Remark remark = new Remark();
            remark.setConsideration(request.consideration());
            remark.setArticle(article);
            article.getRemarks().add(remark);
            return remarkRepository.save(remark);
        }).orElseThrow(() ->
                new ResourceException(
                        "Article with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public List<Remark> getAllByArticleId(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Article with id %s has not been found!"
                                        .formatted(id)));
        return new ArrayList<>(article.getRemarks());
    }

    @Override
    public Remark getById(Long id) {
        return remarkRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Remark with id %s has not been found!"
                                        .formatted(id)));
    }

    @Override
    public Remark updateById(Long id, RemarkDtoRequest request) {
        Remark remark = remarkRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceException(
                                "Remark with id %s has not been found!"
                                        .formatted(id)));
        remark.setConsideration(request.consideration());
        return remarkRepository.save(remark);
    }

    @Override
    public boolean deleteById(Long id) {
        if (remarkRepository.findById(id).isPresent()) {
            remarkRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
